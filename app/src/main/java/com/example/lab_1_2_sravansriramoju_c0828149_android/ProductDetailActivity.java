package com.example.lab_1_2_sravansriramoju_c0828149_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView productName;
    private TextView productDescription;
    private TextView productPrice;
    Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        intent = getIntent();

        productName = findViewById(R.id.productnamevalue);
        productDescription = findViewById(R.id.productdescvalue);
        productPrice = findViewById(R.id.productpricevalue);

        productName.setText(intent.getStringExtra("prodName"));
        productDescription.setText(intent.getStringExtra("proddesc"));
        productPrice.setText(intent.getStringExtra("prodprice"));
    }
}