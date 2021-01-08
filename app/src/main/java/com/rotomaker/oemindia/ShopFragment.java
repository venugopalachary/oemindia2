package com.rotomaker.oemindia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShopFragment extends Fragment {


    EditText searchitem;
    RecyclerView rc;
    View view;
    List<Shops> shopsList;
    VendorAdapter madapter;

    VendorAdapter adapter;
    private String shopapi = "https://android.oemindia.com/mobileversion/shop/fetch_vendors.php";

    public ShopFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shop, container, false);
        rc = view.findViewById(R.id.recyclerView);
        searchitem = view.findViewById(R.id.editTextSearch);
       // recyclerView = findViewById(R.id.recylcerView);
        rc.setHasFixedSize(true);
        rc.setLayoutManager(new LinearLayoutManager(getContext()));

        //initializing the productlist
        shopsList = new ArrayList<>();


        //this method will fetch and parse json
        //to display it in recyclerview
        loadVendors();
        searchitem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                madapter.getFilter().filter(s);

            }
        });

        return view;
    }

    private void loadVendors() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, shopapi, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    //converting the string to json array object
                    JSONArray array = new JSONArray(response);

                    //traversing through all the object
                    for (int i = 0; i < array.length(); i++) {

                        //getting product object from json array
                        JSONObject shops = array.getJSONObject(i);

                        //adding the product to product list
                        shopsList.add(new Shops(
                                shops.getString("vendorid"),
                                shops.getString("product_image"),
                                shops.getString("product_name"),
                                shops.getString("companyname"),
                                shops.getString("companylocation"),
                                shops.getString("product_price"),
                                shops.getString("mobilenumber")


                        ));
                    }

                    //creating adapter object and setting it to recyclerview
                     madapter = new VendorAdapter(getActivity(), (ArrayList<Shops>) shopsList);
                    rc.setAdapter(madapter);
                    madapter.notifyDataSetChanged();

                    //creating adapter object and setting it to recyclerview

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @NotNull
            private Response.Listener<String> getListener() {
                return this;
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Mysmart", "" + error);
                        //   Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                //  String images = getStringImage(bitmap);
                //Log.i("Mynewsam",""+images);
                // param.put("description", Description);
                param.put("category", "3plyfacemask");
                param.put("action", "");
                //param.put("longitude", longitude);

                // param.put("image", images);
                return param;
            }

        };

        requestQueue.add(stringRequest);
    }

}



