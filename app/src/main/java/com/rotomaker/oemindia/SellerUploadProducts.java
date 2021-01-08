package com.rotomaker.oemindia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SellerUploadProducts extends AppCompatActivity {
    ImageView firstImage,secondImage,thirdImage;
    EditText firstproductname, firstprice, firstdescription,secondproductname,secondprice,seconddescription,thirdproductname,thirdprice,thirddescription;
    private String firstproductnamev, firstpricev, firstdescriptionv,secondproductnamev,secondpricev,seconddescriptionv,thirdproductnamev,thirdpricev,thirddescriptionv;


    private Bitmap bitmap1,bitmap2,bitmap3;

    private String KEY_IMAGE1 = "image1";
    private String KEY_IMAGE2 = "image2";
    private String KEY_IMAGE3 = "image3";

    private String KEY_NAME1 = "name1";
    private String KEY_NAME2 = "name2";
    private String KEY_NAME3 = "name3";

    private int PICK_IMAGE_REQUEST1 = 1;
    private int PICK_IMAGE_REQUEST2 = 2;
    private int PICK_IMAGE_REQUEST3 = 3;

    private Button submit;


    private RequestQueue mQueue;
    ProgressDialog progressDialog, pd, pdf;
    Activity mActivity;

    String  gstnovalue,companynamevalue,mobilenumber,email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_upload_products);

        mActivity = getParent();
        Intent intent = getIntent();
        mobilenumber = intent.getExtras().getString("mobilenumber");
        email = intent.getExtras().getString("email");
        gstnovalue = intent.getExtras().getString("gstnovalue");
        companynamevalue= intent.getExtras().getString("companynamevalue");
        // volley
        mQueue = Volley.newRequestQueue(getApplicationContext());

        submit = findViewById(R.id.upload);

        firstImage = (ImageView) findViewById(R.id.firstImage);
        secondImage = (ImageView) findViewById(R.id.secondImage);
        thirdImage = (ImageView) findViewById(R.id.thirdImage);

        firstproductname = (EditText) findViewById(R.id.firstproductname);
        firstprice = (EditText) findViewById(R.id.firstprice);
        firstdescription = (EditText) findViewById(R.id.firstdescription);

        secondproductname = (EditText) findViewById(R.id.secondproductname);
        secondprice = (EditText) findViewById(R.id.secondprice);
        seconddescription = (EditText) findViewById(R.id.seconddescription);


        thirdproductname = (EditText) findViewById(R.id.thirdproductname);
        thirdprice = (EditText) findViewById(R.id.thirdprice);
        thirddescription = (EditText) findViewById(R.id.thirddescription);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firstproductnamev = firstproductname.getText().toString();
                firstpricev       = firstprice.getText().toString();
                firstdescriptionv = firstdescription.getText().toString();


                secondproductnamev = secondproductname.getText().toString();
                secondpricev       = secondprice.getText().toString();
                seconddescriptionv = seconddescription.getText().toString();


                thirdproductnamev = thirdproductname.getText().toString();
                thirdpricev       = thirdprice.getText().toString();
                thirddescriptionv = thirddescription.getText().toString();

                if (firstproductname.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the firstproductname", Toast.LENGTH_SHORT).show();
                }

                if (firstprice.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the firstprice", Toast.LENGTH_SHORT).show();
                }

                if (firstdescription.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the firstdescription", Toast.LENGTH_SHORT).show();
                }


                if (secondproductname.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the secondproductname", Toast.LENGTH_SHORT).show();
                }

                if (secondprice.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the secondprice", Toast.LENGTH_SHORT).show();
                }

                if (seconddescription.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the seconddescription", Toast.LENGTH_SHORT).show();
                }



                if (thirdproductname.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the thirdproductname", Toast.LENGTH_SHORT).show();
                }

                if (thirdprice.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the thirdprice", Toast.LENGTH_SHORT).show();
                }

                if (thirddescription.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the thirddescription", Toast.LENGTH_SHORT).show();
                }



                if(firstproductname.getText().toString().isEmpty()&&firstprice.getText().toString().isEmpty()
                        &&firstdescription.getText().toString().isEmpty()&&secondproductname.getText().toString().isEmpty()
                        &&secondprice.getText().toString().isEmpty()&&seconddescription.getText().toString().isEmpty()
                        &&thirdproductname.getText().toString().isEmpty()&&thirdprice.getText().toString().isEmpty()
                        &&thirddescription.getText().toString().isEmpty())

                {
                    // here are fields are important
                }
                else{

                    progressDialog = new ProgressDialog(SellerUploadProducts.this);
                    progressDialog.setMessage(" Data Sending ...");
                    progressDialog.show();

                        jsonParse();

                }




            }
        });


        firstImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstshowFileChooser();
            }
        });

        secondImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondshowFileChooser();
            }
        });

        thirdImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thirdshowFileChooser();
            }
        });
    }


    private void jsonParse() {


        String url = "https://android.oemindia.com/mobileversion/seller/uploadproducts.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i("response", response);


                        if (response.equalsIgnoreCase("New record created successfully")) {

                            //here we are first deleting past progress dialog to show new dialog
                            progressDialog.dismiss();

                            pd = new ProgressDialog(SellerUploadProducts.this);
                            String checkedMark = "\u2713";
                            Log.i("checkedMark", checkedMark);
                            pd.setMessage("Sented Sucessfully" + " " + checkedMark + " " + checkedMark + " " + checkedMark);
                            pd.show();

                            new CountDownTimer(3000, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    // You don't need anything here
                                }

                                public void onFinish() {
                                    pd.dismiss();
                                }
                            }.start();

                            // Display the response string.




                        } else {
                            progressDialog.dismiss();
                            pdf = new ProgressDialog(SellerUploadProducts.this);
                            String wrong = "\u274C";
                            pdf.setMessage("Not Sended" + " " + wrong + " " + wrong);
                            pdf.show();
                            new CountDownTimer(3000, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    // You don't need anything here
                                }

                                public void onFinish() {
                                    pdf.dismiss();
                                }
                            }.start();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error", error + "");
            }
        }) {
            //adding parameters to the request
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                String image1 = getStringImage(bitmap1);
                String image2 = getStringImage(bitmap2);
                String image3 = getStringImage(bitmap3);

                // create instance of Random class
                Random rand = new Random();
                int rand_int1 = rand.nextInt(100);
                int rand_int2 =  rand.nextInt(100);
                int rand_int3 =  rand.nextInt(100);


                String picname1=companynamevalue.substring(0,2)+rand_int1;

                String picname2=companynamevalue.substring(0,2)+rand_int2;

                String picname3=companynamevalue.substring(0,2)+rand_int3;


                params.put(KEY_IMAGE1, image1);
                params.put(KEY_IMAGE2, image2);
                params.put(KEY_IMAGE3, image3);

                params.put(KEY_NAME1, picname1);
                params.put(KEY_NAME2, picname2);
                params.put(KEY_NAME3, picname3);



                params.put("firstproductnamev", firstproductnamev);
                params.put("firstpricev", firstpricev);
                params.put("firstdescriptionv",firstdescriptionv);


                params.put("secondproductnamev", secondproductnamev);
                params.put("secondpricev", secondpricev);
                params.put("seconddescriptionv",seconddescriptionv);



                params.put("thirdproductnamev", thirdproductnamev);
                params.put("thirdpricev", thirdpricev);
                params.put("thirddescriptionv",thirddescriptionv);


                params.put("mobilenumber", mobilenumber);
                params.put("email", email);
                params.put("gstnovalue",gstnovalue);
                params.put("companynamevalue",companynamevalue);




                return params;
            }
        };
        // Add the request to the RequestQueue.
        mQueue.add(stringRequest);

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }


    private void firstshowFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST1);
    }



    private void secondshowFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST2);
    }



    private void thirdshowFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST3);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST1 && resultCode == mActivity.RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                //Getting the Bitmap from Gallery
                firstImage.setVisibility(View.VISIBLE);
                bitmap1 = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                firstImage.setImageBitmap(bitmap1);



            } catch (IOException e) {
                e.printStackTrace();
            }
        }

     // second image

        if (requestCode == PICK_IMAGE_REQUEST2 && resultCode == mActivity.RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                //Getting the Bitmap from Gallery
                secondImage.setVisibility(View.VISIBLE);
                bitmap2 = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                secondImage.setImageBitmap(bitmap2);



            } catch (IOException e) {
                e.printStackTrace();
            }
        }

     // third image
        if (requestCode == PICK_IMAGE_REQUEST3 && resultCode == mActivity.RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                //Getting the Bitmap from Gallery
                thirdImage.setVisibility(View.VISIBLE);
                bitmap3 = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                thirdImage.setImageBitmap(bitmap3);



            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }





    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


}