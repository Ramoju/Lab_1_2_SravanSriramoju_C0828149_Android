package com.example.lab_1_2_sravansriramoju_c0828149_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lab_1_2_sravansriramoju_c0828149_android.Model.ProductModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class ProductDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private TextView productName;
    private TextView productDescription;
    private TextView productPrice;
    private double latitude;
    private double longitude;

    GoogleMap gMap;
    FusedLocationProviderClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ProductModel prod = getIntent().getParcelableExtra("product");

        productName = findViewById(R.id.productnamevalue);
        productDescription = findViewById(R.id.productdescvalue);
        productPrice = findViewById(R.id.productpricevalue);

        productName.setText(prod.getProductName());
        productDescription.setText(prod.getDescription());
        productPrice.setText(String.valueOf(prod.getPrice()));


        latitude = prod.getLatitude();
        longitude = prod.getLongitude();

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        client = LocationServices.getFusedLocationProviderClient(this);
        supportMapFragment.getMapAsync(this);

//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            //getCurrentLocation();
//        }
    }

//    private void getCurrentLocation() {
//        Task<Location> task = client.getLastLocation();
//        task.addOnSuccessListener(new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//                if (location != null){
//
//                }
//            }
//        })
//    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;
        LatLng providerLocation = new LatLng(latitude, longitude);
        gMap.addMarker(new MarkerOptions()
        .position(providerLocation)
        .title("Provider's Location"));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(providerLocation));
    }
}