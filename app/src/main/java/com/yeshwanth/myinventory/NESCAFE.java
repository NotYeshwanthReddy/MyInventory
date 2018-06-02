package com.yeshwanth.myinventory;
/*
 * Created by Yeshwanth on 24-02-2018. # _Yeshwanth_Reddy
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NESCAFE extends Fragment {
    String[] Nescafe_Items = {"ChillyPotato", "FrenchFries", "Momos", "ChickenRoll"};
    String[] Nescafe_Costs = {"40", "40", "30", "90"};
    String[] Nescafe_Stock = {"90", "89", "90", "100"};
    String[] Nescafe_Item_ID = {"TMP001", "TMP002", "TMP003", "TMP004"};
    Context ctx;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nescafe, container, false);
        RecyclerView rv = (RecyclerView) rootView.findViewById((R.id.RecyclerView));
        rv.setHasFixedSize(true);

        MyAdapter adapter = new MyAdapter(BackgroundTask.Nescafe_Items, BackgroundTask.Nescafe_Costs, BackgroundTask.Nescafe_Stock, BackgroundTask.Nescafe_Item_ID);
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager((getActivity()));
        rv.setLayoutManager(llm);
        return rootView;
    }
}
