package com.example.lab_1_2_sravansriramoju_c0828149_android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab_1_2_sravansriramoju_c0828149_android.AddNewProduct;
import com.example.lab_1_2_sravansriramoju_c0828149_android.MainActivity;
import com.example.lab_1_2_sravansriramoju_c0828149_android.Model.ProductModel;
import com.example.lab_1_2_sravansriramoju_c0828149_android.ProductDetailActivity;
import com.example.lab_1_2_sravansriramoju_c0828149_android.R;
import com.example.lab_1_2_sravansriramoju_c0828149_android.Utilities.DBHelper;

import java.util.List;

public class ProductRVAdapter extends RecyclerView.Adapter<ProductRVAdapter.MyViewHolder> {

    private List<ProductModel> products;
    private MainActivity activity;
    private DBHelper mDb;

    public ProductRVAdapter(DBHelper db, MainActivity activity){
        this.activity = activity;
        this.mDb = db;
    }

    @NonNull
    @Override
    public ProductRVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRVAdapter.MyViewHolder holder, int position) {
        final ProductModel item = products.get(position);
        //System.out.println(item.getProductName() + " " + item.getDescription());
        holder.productName.setText(item.getProductName());
        holder.productPrice.setText(String.valueOf(item.getPrice()));

        holder.moredetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ProductDetailActivity.class);
                intent.putExtra("product", item);
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (products == null) return 0;
        return products.size();
    }

    public Context getContext(){
        return activity;
    }

    public void setProducts(List<ProductModel> mList){
        this.products = mList;
        notifyDataSetChanged();
    }

    public void editProduct(int position){
        ProductModel prod = products.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", prod.getProductId());
        bundle.putString("name", prod.getProductName());
        bundle.putString("description", prod.getDescription());
        bundle.putString("price", String.valueOf(prod.getPrice()));
        bundle.putString("latitude", String.valueOf(prod.getLatitude()));
        bundle.putString("longitude", String.valueOf(prod.getLongitude()));

        AddNewProduct product = new AddNewProduct();
        product.setArguments(bundle);
        product.show(activity.getSupportFragmentManager(), product.getTag());
    }
    public void deleteProduct(int position) {
        ProductModel prod = products.get(position);
        mDb.deleteProduct(prod.getProductId());
        products.remove(position);
        notifyItemRemoved(position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView productName;
        TextView productPrice;
        Button moredetails;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            moredetails = itemView.findViewById(R.id.moreproddetails);
        }
    }
}
