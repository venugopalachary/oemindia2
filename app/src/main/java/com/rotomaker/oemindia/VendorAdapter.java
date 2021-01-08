package com.rotomaker.oemindia;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class VendorAdapter extends RecyclerView.Adapter<VendorAdapter.ViewHolder> {

    private List<Shops> shopsList;
    private List<Shops> filtershopsList;
    private Context mCtx;
    public VendorAdapter(Context mCtx, ArrayList<Shops> arrayList)
     {
        this.mCtx=mCtx;
        shopsList=arrayList;
        filtershopsList=arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View categoryitem= layoutInflater.inflate(R.layout.vendor_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(categoryitem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Shops shopslist = shopsList.get(position);

        Glide.with(mCtx)
                .load(shopslist.getImage())
                .into(holder.imageView);
        holder.productname.setText(shopslist.getProductname());
        holder.vendorlocation.setText(shopslist.getLocation());
        holder.price.setText(shopslist.getPrice());
        holder.companyname.setText(shopslist.getCompanyname());
        holder.getcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                callIntent.setData(Uri.parse("tel:9494962617"));
//                mCtx.startActivity(callIntent);
                Intent i = new Intent(Intent.ACTION_DIAL);
                String p = "tel:" + shopslist.getMobilenumber();
                i.setData(Uri.parse(p));
                mCtx.startActivity(i);
            }
        });
        holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    @Override
    public int getItemCount() {
        return shopsList.size();
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    filtershopsList = shopsList;
                } else {

                    ArrayList<Shops> mfiltershopsList = new ArrayList<>();

                    for (Shops shops : shopsList) {

                        if (shops.getProductname().toLowerCase().contains(charString) || shops.getCompanyname().toLowerCase().contains(charString) || shops.getLocation().toLowerCase().contains(charString)) {

                            filtershopsList.add(shops);
                        }
                    }

                    filtershopsList = mfiltershopsList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filtershopsList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filtershopsList = (ArrayList<Shops>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView productname,vendorlocation,price,companyname,getcall,message;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.vendors_im);
            this.productname = (TextView) itemView.findViewById(R.id.productname_tv);
            this.vendorlocation = (TextView) itemView.findViewById(R.id.vendorlocation_tv);
            this.price = (TextView) itemView.findViewById(R.id.price_tv);
            this.companyname = (TextView) itemView.findViewById(R.id.companyname_tv);
            this.getcall = (TextView) itemView.findViewById(R.id.phnenumber);
            this.message = (TextView) itemView.findViewById(R.id.message);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }

    }
}