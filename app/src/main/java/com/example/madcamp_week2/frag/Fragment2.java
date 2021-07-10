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
    private String TAG = "Frag2: ";
    String restName;

    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sendPost();

        list = new ArrayList<>();

        handleServer();

        activity = (MainActivity) getActivity();
        adapter = new GalleryAdapter(activity, list, Restaurants.rest_images);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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

    private void handleServer() {

        Call<List<PostResult>> call = retrofitInterface.executeGetAllPost();

        call.enqueue(new Callback<List<PostResult>>() {
            @Override
            public void onResponse(Call<List<PostResult>> call, Response<List<PostResult>> response) {
                if(response.code()==200){

                    List<PostResult> postlist = response.body();

                    parsingPostResult(postlist);

                }else if(response.code()==400){

                }

            }
            @Override
            public void onFailure(Call<List<PostResult>> call, Throwable t) {

                Toast.makeText(getContext(), "Server is closed.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void parsingPostResult(List<PostResult> postlist) {

        for (PostResult post : postlist) {

            request_one_rest_name(post.getRest());

            while(restName == null);
            Post post1 = new Post(post.getTitle(), post.getContent(), post.getRate(), post.getWriter(), post.getRest(), restName);

            if (!list.contains(post1)) {
                list.add(post1);
                restName = null;
            }
        }
    }

    private void request_one_rest_name(String restId) {

        HashMap<String , String> map = new HashMap<>();
        map.put("id", restId);

        Call<RestResult> call = retrofitInterface.executeGetOneRest(map);

        call.enqueue(new Callback<RestResult>() {
            @Override
            public void onResponse(@NotNull Call<RestResult> call, @NotNull Response<RestResult> response) {
                if (response.code() == 200) {

                    RestResult restResult = response.body();
                    restName = restResult.getName();

                } else if (response.code() == 400) {

                }
            }

            @Override
            public void onFailure(Call<RestResult> call, Throwable t) {

            }
        });

    }

}