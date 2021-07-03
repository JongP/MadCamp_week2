package com.example.madcamp_week1;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

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

    private String[] rest_images = new String[] {
            "https://cdn.pixabay.com/photo/2019/12/26/10/44/horse-4720178_1280.jpg",
            "https://cdn.pixabay.com/photo/2020/11/04/15/29/coffee-beans-5712780_1280.jpg",
            "https://cdn.pixabay.com/photo/2020/09/02/18/03/girl-5539094_1280.jpg",
            "https://cdn.pixabay.com/photo/2014/03/03/16/15/mosque-279015_1280.jpg",
            "https://cdn.pixabay.com/photo/2019/12/26/10/44/horse-4720178_1280.jpg",
            "https://cdn.pixabay.com/photo/2020/11/04/15/29/coffee-beans-5712780_1280.jpg",
            "https://cdn.pixabay.com/photo/2020/09/02/18/03/girl-5539094_1280.jpg",
            "https://cdn.pixabay.com/photo/2014/03/03/16/15/mosque-279015_1280.jpg",
            "https://cdn.pixabay.com/photo/2019/12/26/10/44/horse-4720178_1280.jpg",
            "https://cdn.pixabay.com/photo/2020/11/04/15/29/coffee-beans-5712780_1280.jpg",
            "https://cdn.pixabay.com/photo/2020/09/02/18/03/girl-5539094_1280.jpg",
            "https://cdn.pixabay.com/photo/2014/03/03/16/15/mosque-279015_1280.jpg",
            "https://cdn.pixabay.com/photo/2019/12/26/10/44/horse-4720178_1280.jpg",
            "https://cdn.pixabay.com/photo/2020/11/04/15/29/coffee-beans-5712780_1280.jpg",
            "https://cdn.pixabay.com/photo/2020/09/02/18/03/girl-5539094_1280.jpg",
            "https://cdn.pixabay.com/photo/2014/03/03/16/15/mosque-279015_1280.jpg",
            "https://cdn.pixabay.com/photo/2019/12/26/10/44/horse-4720178_1280.jpg",
            "https://cdn.pixabay.com/photo/2020/11/04/15/29/coffee-beans-5712780_1280.jpg",
            "https://cdn.pixabay.com/photo/2020/09/02/18/03/girl-5539094_1280.jpg",
            "https://cdn.pixabay.com/photo/2014/03/03/16/15/mosque-279015_1280.jpg"
    };

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
        adapter = new GalleryAdapter(activity, list, rest_images);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_2, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        adapter.setItemViewType(GalleryAdapter.LIST);

        ImageButton grid_button = v.findViewById(R.id.grid_button);
        ImageButton list_button = v.findViewById(R.id.list_button);

        grid_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Drawable img_grid = getActivity().getResources().getDrawable(R.drawable.grid_on);
//                Drawable img_list = getActivity().getResources().getDrawable(R.drawable.list_off);
                grid_button.setImageResource(R.drawable.grid_on);
                list_button.setImageResource(R.drawable.list_off);
                recyclerView.setLayoutManager(new GridLayoutManager(activity, 3));
                adapter.setItemViewType(GalleryAdapter.GRID);
            }
        });

        list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Drawable img_grid = getActivity().getResources().getDrawable(R.drawable.grid_off);
//                Drawable img_list = getActivity().getResources().getDrawable(R.drawable.list_on);
                grid_button.setImageResource(R.drawable.grid_off);
                list_button.setImageResource(R.drawable.list_on);
                recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                adapter.setItemViewType(GalleryAdapter.LIST);
            }
        });

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