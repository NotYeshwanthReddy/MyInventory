package com.yeshwanth.myinventory;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MyCart extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Product> arrayList = new ArrayList<Product>();
    public ImageButton mBackButton;

    String useremail, username, userenroll, itemname, itemprice, itemquantity, itemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        username = (sharedPreferences.getString("UserName", "Name"));
        useremail = (sharedPreferences.getString("UserEmail", "xyz@gmail.com"));
        userenroll = (sharedPreferences.getString("UserID", "U101116FCS190"));


        recyclerView = (RecyclerView) findViewById(R.id.CartRecyclerView);
        int i=0;
        for (String name : MyAdapter.Ordered_Item_Array){
            Product product = new Product(MyAdapter.Ordered_Item_Array[i], MyAdapter.Ordered_Cost_Array[i], MyAdapter.Ordered_Stock_Array[i]);
            arrayList.add(product);
            i++;
        }
        adapter = new CartAdapter(arrayList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        mBackButton = (ImageButton) findViewById(R.id.CartBackButton);
        mBackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabOrder);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Order Successful", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                OrderItems();
            }
        });
    }

    public void OrderItems(){
        for(int i=0; i<3; i++){
            itemname = MyAdapter.Ordered_Item_Array[i];
//            itemprice = MyAdapter.Ordered_Cost_Array[i];
            itemquantity = "1";
            itemID = MyAdapter.Ordered_Item_ID_Array[i];

            Log.d("Ordering", "order");
            Log.i(TAG, "ordered : ["+itemID+"] "+itemname+"["+itemquantity+"] - ₹");
            String method = "order_items";

            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method,  userenroll, itemID, itemquantity);

//            Log.i(TAG, "clearing : "+MyAdapter.Ordered_Item_Array[i]+"["+MyAdapter.Ordered_Cost_Array[i]+"] - ₹"+MyAdapter.Ordered_Stock_Array[i]);
        }
    }
}
