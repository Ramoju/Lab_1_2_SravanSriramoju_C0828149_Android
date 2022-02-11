package com.example.lab_1_2_sravansriramoju_c0828149_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.lab_1_2_sravansriramoju_c0828149_android.Adapter.ProductRVAdapter;
import com.example.lab_1_2_sravansriramoju_c0828149_android.Model.ProductModel;
import com.example.lab_1_2_sravansriramoju_c0828149_android.Utilities.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnDialogCloseListener{

    private RecyclerView mRecyclerView;
    private FloatingActionButton addProduct;
    private DBHelper db;
    private List<ProductModel> productsList;
    private ProductRVAdapter adapter;
    private TextView totalProducts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.productsRV);
        addProduct = findViewById(R.id.addnewproduct);
        totalProducts = findViewById(R.id.totalproductsvalue);

        db = new DBHelper(MainActivity.this);
        productsList = new ArrayList<>();
        adapter = new ProductRVAdapter(db,MainActivity.this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ProductsRVItemTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerView);


        productsList = db.getAllProducts();
        Collections.reverse(productsList);
        adapter.setProducts(productsList);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewProduct.newInstance().show(getSupportFragmentManager(), AddNewProduct.TAG);
            }
        });

        totalProducts.setText(String.valueOf(productsList.size()));
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        productsList = db.getAllProducts();
        Collections.reverse(productsList);
        adapter.setProducts(productsList);
        adapter.notifyDataSetChanged();
    }
}