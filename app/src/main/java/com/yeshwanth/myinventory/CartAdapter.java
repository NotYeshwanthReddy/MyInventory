package com.yeshwanth.myinventory;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/*
 * Created by Yeshwanth on 25-04-2018. # _Yeshwanth_Reddy
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{

    private ArrayList<Product> arrayList = new ArrayList<Product>();

    public CartAdapter(ArrayList<Product> arrayList){
        this.arrayList = arrayList;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_menu_item, parent, false);

        CartViewHolder cartViewHolder = new CartViewHolder(view);
        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        Product product = arrayList.get(position);
        holder.mItemName.setText(product.getname());
        holder.mItemCost.setText(product.getprice());
        holder.mItemStock.setText(product.getstock());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        private CardView mCardView;
        private TextView mItemName;
        private TextView mItemCost;
        private TextView mItemStock;
        private RelativeLayout mRelativeLayout;

        public CartViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.MenuCardView);
            mItemName = (TextView) itemView.findViewById(R.id.ItemName);
            mItemCost = (TextView) itemView.findViewById(R.id.ItemCost);
            mItemStock = (TextView) itemView.findViewById(R.id.AvailableQuantity);
            mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.CardRelativeLayout);
        }
    }
}
