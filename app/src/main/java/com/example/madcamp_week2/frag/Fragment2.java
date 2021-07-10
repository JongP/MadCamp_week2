package com.example.madcamp_week2.frag;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.madcamp_week2.adapter.GalleryAdapter;
import com.example.madcamp_week2.MainActivity;
import com.example.madcamp_week2.R;
import com.example.madcamp_week2.Restaurants;
import com.example.madcamp_week2.server.RestResult;
import com.example.madcamp_week2.server.RetrofitInterface;
import com.example.madcamp_week2.user.UserData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Fragment2 extends Fragment {
    ArrayList<ArrayList<String>> list;
    GalleryAdapter adapter;
    MainActivity activity;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.249.18.117:80";
    private String TAG = "Frag2: ";

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



        //sendPost();

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
        adapter = new GalleryAdapter(activity, list, Restaurants.rest_images);
    }

    private void sendPost() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        UserData userData = new UserData();
        Log.d("static test", "onCreate: "+userData.getId());

        HashMap<String,String> map =  new HashMap<>();
        map.put("title","test_title");
        map.put("contnent","test_content");
        map.put("rate","4");
        map.put("rest","60e97819d7eff862aeb8f93b");
        map.put("user",userData.getId());
        Call<Void> call = retrofitInterface.executePostAdd(map);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code()==200) Log.d(TAG, "onResponse: response 200");
                else Log.d(TAG, "onResponse: response other");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });


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
                adapter.setItemViewType(GalleryAdapter.GRID);
                Drawable img_grid = getActivity().getResources().getDrawable(R.drawable.grid_on);
                Drawable img_list = getActivity().getResources().getDrawable(R.drawable.list_off);
                grid_button.setBackground(img_grid);
                list_button.setBackground(img_list);
                recyclerView.setLayoutManager(new GridLayoutManager(activity, 3));
            }
        });

        list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setItemViewType(GalleryAdapter.LIST);
                Drawable img_grid = getActivity().getResources().getDrawable(R.drawable.grid_off);
                Drawable img_list = getActivity().getResources().getDrawable(R.drawable.list_on);
                grid_button.setBackground(img_grid);
                list_button.setBackground(img_list);
                recyclerView.setLayoutManager(new LinearLayoutManager(activity));
            }
        });

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new GalleryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MainActivity activity = (MainActivity)getActivity();
                activity.switchToImagePage(position);
            }
        });

        return v;
    }

}