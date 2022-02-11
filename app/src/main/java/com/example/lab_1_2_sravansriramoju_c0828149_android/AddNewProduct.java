package com.example.lab_1_2_sravansriramoju_c0828149_android;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lab_1_2_sravansriramoju_c0828149_android.Model.ProductModel;
import com.example.lab_1_2_sravansriramoju_c0828149_android.Utilities.DBHelper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddNewProduct extends BottomSheetDialogFragment {

    public static final String TAG = "AddNewProduct";

    private EditText productName;
    private EditText productDescription;
    private EditText productPrice;
    private EditText providerLatitude;
    private EditText providerLongitude;
    private Button mSaveButton;

    private DBHelper mDB;

    public static AddNewProduct newInstance(){
        return new AddNewProduct();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_new_product, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productName = view.findViewById(R.id.productname);
        productDescription = view.findViewById(R.id.productdescription);
        productPrice = view.findViewById(R.id.productprice);
        providerLatitude = view.findViewById(R.id.providerlatitude);
        providerLongitude = view.findViewById(R.id.providerlongitude);
        mSaveButton = view.findViewById(R.id.button_save);

        mDB = new DBHelper(getActivity());

        boolean isUpdate = false;

        Bundle bundle = getArguments();
        if (bundle != null) {
            isUpdate = true;
            int id = bundle.getInt("id");
            String prodName = bundle.getString("name");
            System.out.println(prodName);
            productName.setText(prodName);
            String prodDesc = bundle.getString("description");
            productDescription.setText(prodDesc);
            double prodPrice = bundle.getDouble("price");
            productPrice.setText(String.valueOf(prodPrice));
            double providerLat = bundle.getDouble("latitude");
            providerLatitude.setText(String.valueOf(providerLat));
            double providerLong = bundle.getDouble("longitude");
            providerLongitude.setText(String.valueOf(providerLong));

        }

        final boolean finalisUpdate = isUpdate;
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Insert method in Add New Product class");
                String name = productName.getText().toString();
                String description = productDescription.getText().toString();
                Double price = Double.parseDouble(String.valueOf(productPrice.getText()));
                Double latitude = Double.parseDouble(String.valueOf(providerLatitude.getText()));
                Double longitude = Double.parseDouble(String.valueOf(providerLongitude.getText()));

                    ProductModel item = new ProductModel();
                    item.setProductName(name);
                    item.setDescription(description);
                    item.setPrice(price);
                    item.setLatitude(latitude);
                    item.setLongitude(longitude);


                    if (finalisUpdate) {
                        mDB.updateProduct(item.getProductId(), name, description, price, latitude, longitude);
                    }
                    else {
                        mDB.insertProduct(item);
                    }

                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();

        if (activity instanceof OnDialogCloseListener){
            ((OnDialogCloseListener) activity).onDialogClose(dialog);
        }
    }
}
