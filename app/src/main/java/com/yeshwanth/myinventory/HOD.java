package com.yeshwanth.myinventory;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.ByteArrayInputStream;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HOD extends Fragment {
    String[] HOD_Items = {"ChillyPotato", "FrenchFries", "Momos", "ChickenRoll", " ", " "};
    String[] HOD_Costs = {"40", "40", "30", "90", " ", " "};
    String[] HOD_Stock = {"90", "89", "90", "100", " ", " "};
    String[] HOD_Item_ID = {"TMP001", "TMP002", "TMP003", "TMP004", " ", " "};

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hod, container, false);
        RecyclerView rv = (RecyclerView) rootView.findViewById((R.id.RecyclerView));
        rv.setHasFixedSize(true);

        MyAdapter adapter = new MyAdapter(BackgroundTask.HOD_Items, BackgroundTask.HOD_Costs, BackgroundTask.HOD_Stock, BackgroundTask.HOD_Item_ID);
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        return rootView;
    }
}
