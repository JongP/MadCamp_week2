package com.example.madcamp_week1;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Fragment2 extends Fragment {
    ArrayList<ArrayList<String>> list;
    GalleryAdapter adapter;
    MainActivity activity;

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

        list = new ArrayList<>();

        AssetManager assetManager = getActivity().getAssets();

        try {
            InputStream is = assetManager.open("jsonDirectory/restaurantList.json");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            StringBuffer buffer = new StringBuffer();
            String line = reader.readLine();
            while (line != null) {
                buffer.append((line + "\n"));
                line = reader.readLine();
            }

            String jsonData = buffer.toString();

            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                ArrayList<String> list2 = new ArrayList<>();
                JSONObject jo = jsonArray.getJSONObject(i);
                String name = jo.getString("name");
                String contact = jo.getString("contact");
                list2.add(name);
                list2.add(contact);
                list.add(list2);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        activity = (MainActivity) getActivity();
        adapter = new GalleryAdapter(activity, list);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_2, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new GalleryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MainActivity activity = (MainActivity)getActivity();
                activity.switchPage(position);
            }
        });

        return v;
    }

}