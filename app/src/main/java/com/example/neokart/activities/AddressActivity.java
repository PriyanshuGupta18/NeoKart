package com.example.neokart.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.neokart.R;
import com.example.neokart.adapters.AddressAdapter;
import com.example.neokart.models.AddressModel;
import com.example.neokart.models.MyCartModel;
import com.example.neokart.models.NewProductsModel;
import com.example.neokart.models.PopularProductsModel;
import com.example.neokart.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity {

    Button btn,pmt;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        toolbar=findViewById(R.id.address_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Object obj=getIntent().getSerializableExtra("item");

        btn=findViewById(R.id.add_address_btn);
        pmt=findViewById(R.id.payment_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddressActivity.this,AddAddressActivity.class);
                startActivity(intent);
            }
        });
        pmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double amount=0.0;
                if(obj instanceof NewProductsModel)
                {
                    NewProductsModel newProductsModel=(NewProductsModel) obj;
                    amount=newProductsModel.getPrice();
                }
                if(obj instanceof PopularProductsModel)
                {
                    PopularProductsModel popularProductsModel=(PopularProductsModel) obj;
                    amount=popularProductsModel.getPrice();
                }
                if(obj instanceof ShowAllModel)
                {
                    ShowAllModel showAllModel=(ShowAllModel) obj;
                    amount=showAllModel.getPrice();
                }
                Intent intent=new Intent(AddressActivity.this,PaymentActivity.class);
                intent.putExtra("amount",amount);
                startActivity(intent);
            }
        });
    }
}