package com.example.madcamp_week1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Fragment2 extends Fragment {
    ArrayList<ArrayList<String>> list;
    GalleryAdapter adapter;

    public Fragment2() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment2 newInstance() {
        Fragment2 fragment = new Fragment2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] restArray = getResources().getStringArray(R.array.restaurants);
        list = new ArrayList<>();
        for (int i = 0; i < restArray.length; i++) {
            int getRes2 = getResources().getIdentifier(restArray[i], "array", getContext().getPackageName());
            String[] restInfo = getResources().getStringArray(getRes2);
            ArrayList<String> list2 = new ArrayList<>();
            for (int j = 0; j < restInfo.length; j++) {
                list2.add(restInfo[j]);
            }
            list.add(list2);
        }
        adapter = new GalleryAdapter(getActivity(), list);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_2, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(adapter);

        return v;
    }

}