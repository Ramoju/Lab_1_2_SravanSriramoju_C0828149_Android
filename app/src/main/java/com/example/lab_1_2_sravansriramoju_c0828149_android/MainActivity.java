package com.example.lab_1_2_sravansriramoju_c0828149_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.productsRV);
        addProduct = findViewById(R.id.addnewproduct);

        db = new DBHelper(MainActivity.this);
        productsList = new ArrayList<>();
        adapter = new ProductRVAdapter(db,MainActivity.this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ProductsRVItemTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

//        ProductModel p1 = new ProductModel(1, "Pen", 20, 43.2456, 45.1290, "Test");
//
//        productsList.add(p1);
//        db.insertProduct(p1);
//        productsList.add(new ProductModel(2, "Pencil", 30, 43.2456, 45.1290, "Test"));
//        productsList.add(new ProductModel(3, "Sketch", 30, 43.2456, 45.1290, "Test"));
//        productsList.add(new ProductModel(4, "Ruler", 30, 43.2456, 45.1290, "Test"));
//        productsList.add(new ProductModel(5, "Eraser", 34, 43.2456, 45.1290, "Test"));
//        productsList.add(new ProductModel(6, "Paper", 9, 43.2456, 45.1290, "Test"));
//        productsList.add(new ProductModel(7, "Compass", 39, 43.2456, 45.1290, "Test"));
//        productsList.add(new ProductModel(8, "Marker", 37, 43.2456, 45.1290, "Test"));
//        productsList.add(new ProductModel(9, "Pen 09", 30, 43.2456, 45.1290, "Test"));
//        productsList.add(new ProductModel(10, "Pen 10", 30, 43.2456, 45.1290, "Test"));

        productsList = db.getAllProducts();
        Collections.reverse(productsList);
        adapter.setProducts(productsList);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewProduct.newInstance().show(getSupportFragmentManager(), AddNewProduct.TAG);
            }
        });

    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        productsList = db.getAllProducts();
        Collections.reverse(productsList);
        adapter.setProducts(productsList);
        adapter.notifyDataSetChanged();
    }
}