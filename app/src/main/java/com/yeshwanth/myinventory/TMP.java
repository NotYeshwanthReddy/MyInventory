package com.yeshwanth.myinventory;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class TMP extends Fragment {

    String[] TMP_Items = {"ChillyPotato", "FrenchFries", "Momos", "ChickenRoll"};
    String[] TMP_Costs = {"40", "40", "30", "90"};
    String[] TMP_Stock = {"90", "89", "90", "100"};
    String[] TMP_Item_ID = {"TMP001", "TMP002", "TMP003", "TMP004"};
    Context ctx;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tmp, container, false);
        RecyclerView rv = (RecyclerView) rootView.findViewById((R.id.RecyclerView));
        rv.setHasFixedSize(true);

        MyAdapter adapter = new MyAdapter(BackgroundTask.TMP_Items, BackgroundTask.TMP_Costs, BackgroundTask.TMP_Stock, BackgroundTask.TMP_Item_ID);
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        return rootView;
    }
}
