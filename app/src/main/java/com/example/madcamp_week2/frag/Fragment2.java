package com.example.madcamp_week2.frag;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madcamp_week2.RestaurantActivity;
import com.example.madcamp_week2.WritePostActivity;
import com.example.madcamp_week2.adapter.DictionaryAdapter;
import com.example.madcamp_week2.adapter.GalleryAdapter;
import com.example.madcamp_week2.MainActivity;
import com.example.madcamp_week2.R;
import com.example.madcamp_week2.Restaurants;
import com.example.madcamp_week2.model.Post;
import com.example.madcamp_week2.server.PostResult;
import com.example.madcamp_week2.server.RestResult;
import com.example.madcamp_week2.server.RetrofitInterface;
import com.example.madcamp_week2.user.UserData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;
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
    ArrayList<Post> list;
    GalleryAdapter adapter;
    MainActivity activity;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.249.18.117:80";
    private String TAG = "Frag2";
    RecyclerView recyclerView;

    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        list = new ArrayList<>();

        Log.d(TAG, "before handle server");
       // handleServer();
        Log.d(TAG, "handle server done");

        Log.d(TAG, "onCreate done. gallery adapter created");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView start");

        View v = inflater.inflate(R.layout.fragment_2, container, false);

        recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        Log.d(TAG, "set linear layout manager");

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        handleServer();
    }

    private void handleServer() {

        Log.d(TAG, "handle server start");

        Call<List<PostResult>> call = retrofitInterface.executeGetAllPost();

        call.enqueue(new Callback<List<PostResult>>() {
            @Override
            public void onResponse(Call<List<PostResult>> call, Response<List<PostResult>> response) {
                if(response.code()==200){

                    Log.d(TAG, "handle server onResponse");

                    List<PostResult> postlist = response.body();

                    parsingPostResult(postlist);

                    activity = (MainActivity) getActivity();
                    adapter = new GalleryAdapter(activity, list, Restaurants.rest_images);

                    recyclerView.setAdapter(adapter);
                    Log.d(TAG, "adapter is attached to recyclerView");

                    Log.d(TAG, "parsing Post result done");

                }else if(response.code()==400){

                    Log.d(TAG, "onResponse somehow failed");
                }

            }
            @Override
            public void onFailure(Call<List<PostResult>> call, Throwable t) {

                Toast.makeText(getContext(), "Server is closed.", Toast.LENGTH_LONG).show();
                Log.d(TAG, "handle server onFailure");
            }
        });
    }

    private void parsingPostResult(List<PostResult> postlist) {

        for (PostResult post : postlist) {
            Log.d("post id check", post.getId());
            Post post1 = new Post(post.getId(),post.getTitle(), post.getContent(), post.getRate(), post.getWriter(), post.getRest(), post.getRestName());

            if (!list.contains(post1)) {
                list.add(post1);
            }
        }
    }

}