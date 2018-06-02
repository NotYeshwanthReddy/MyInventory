package com.yeshwanth.myinventory;
/*
 * Created by Yeshwanth on 25-02-2018. # _Yeshwanth_Reddy
 */

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public static RecyclerView.Adapter adapter;
    private Context mCtx;

    public static String[] Ordered_Item_Array = new String[3];
    public static String[] Ordered_Cost_Array = new String[3];
    public static String[] Ordered_Stock_Array = new String[3];
    public static String[] Ordered_Item_ID_Array = new String[3];
    public static int N_Cart=0;

    private String[] mNameDataSet;
    private String[] mCostDataSet;
    private String[] mStockDataSet;
    private String[] mItemIdDataSet;

    public MyAdapter(Context mCtx) {
        this.mCtx = mCtx;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private CardView mCardView;
        private TextView mItemName;
        private TextView mItemCost;
        private TextView mItemStock;
        private RelativeLayout mRelativeLayout;

        private MyViewHolder(View v){
            super(v);
            mCardView = (CardView) v.findViewById(R.id.MenuCardView);
            mItemName = (TextView) v.findViewById(R.id.ItemName);
            mItemCost = (TextView) v.findViewById(R.id.ItemCost);
            mItemStock = (TextView) v.findViewById(R.id.AvailableQuantity);
            mRelativeLayout = (RelativeLayout) v.findViewById(R.id.CardRelativeLayout);
        }
    }

    public MyAdapter(String[] myNameDataSet, String[] myCostDataSet, String[] myStockDataSet, String[] myItemIdDataSet){
        mNameDataSet = myNameDataSet;
        mCostDataSet = myCostDataSet;
        mStockDataSet = myStockDataSet;
        mItemIdDataSet = myItemIdDataSet;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewTpe){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_menu_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position){
        holder.mItemName.setText(mNameDataSet[position]);
        holder.mItemCost.setText(mCostDataSet[position]);
        holder.mItemStock.setText(mStockDataSet[position]);

        holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Log.i(TAG, "click : "+mNameDataSet[position]+"["+mStockDataSet[position]+"] "+" - ₹"+mCostDataSet[position]+" each");
                if(N_Cart <3){
                    Ordered_Item_Array[N_Cart] = mNameDataSet[position];
                    Ordered_Cost_Array[N_Cart] = mCostDataSet[position];
                    Ordered_Item_ID_Array[N_Cart] = mItemIdDataSet[position];
                    Log.i(TAG, "Saved : "+N_Cart+")"+Ordered_Item_ID_Array[N_Cart]+" - "+Ordered_Item_Array[N_Cart]+"["+Ordered_Stock_Array[N_Cart]+"] - ₹"+Ordered_Cost_Array[N_Cart]+" each");
//                    Toast.makeText(mCtx, Ordered_Item_Array[N_Cart], Toast.LENGTH_SHORT).show();
                    N_Cart++;
                }
            }
        });
    }

    @Override
    public int getItemCount(){
        int j=0;
        for(int i=0; i<100; i++){
            if(mNameDataSet[i] == (null)){
            }
            else{
                j++;
            }
        }
        Log.i(TAG, "return j="+j);
        return j;
    }
}

