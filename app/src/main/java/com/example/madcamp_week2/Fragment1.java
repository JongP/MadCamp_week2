package com.example.madcamp_week2;

import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.madcamp_week2.server.RestResult;
import com.example.madcamp_week2.server.RetrofitInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment1 extends Fragment {

    private ArrayList<Item> mArrayList;
    private DictionaryAdapter mAdapter;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://172.10.18.117:80";

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_id);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mArrayList = new ArrayList<>();

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        view.findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTestServer();
            }
        });



        // parsing json
        AssetManager assetManager = getActivity().getAssets();

        try {
            InputStream is = assetManager.open("jsonDirectory/restaurantList.json");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            StringBuffer buffer = new StringBuffer();
            String line = reader.readLine();
            while (line!=null){
                buffer.append(line+"\n");
                line = reader.readLine();
            }

            String jsonData = buffer.toString();

            JSONArray jsonArray = new JSONArray(jsonData);

            for(int i = 0; i < jsonArray.length(); i++){
                switch (i){
                    case 0:
                        mArrayList.add(new Item("한식"));
                        break;
                    case 4:
                        mArrayList.add(new Item("일식"));
                        break;
                    case 13:
                        mArrayList.add(new Item("양식"));
                        break;
                    case 15:
                        mArrayList.add(new Item("기타 외국 음식"));
                        break;
                    case 17:
                        mArrayList.add(new Item("Pub & Bar"));
                        break;
                }

                JSONObject jo = jsonArray.getJSONObject(i);

                String name = jo.getString("name");
                String contact = jo.getString("contact");

                Dictionary data = new Dictionary(i+"", name, contact);
                mArrayList.add(new Item(data));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mAdapter = new DictionaryAdapter(mArrayList);

        mAdapter.setOnItemClickListener(new DictionaryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String phoneNumber = mArrayList.get(position).dict.getContact();
                phoneNumber = phoneNumber.replace("-", "");
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber)));
            }
        });

        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        return view;
    }

    private void handleTestServer() {
        Call<List<RestResult>> call = retrofitInterface.executeGetAllRest();
        call.enqueue(new Callback<List<RestResult>>() {
            @Override
            public void onResponse(Call<List<RestResult>> call, Response<List<RestResult>> response) {
                if(response.code()==200){
                    Log.d("evening", "onClick: test response success");
                    List<RestResult> list = new ArrayList<>();
                    list = response.body();
                    Log.d("evening", list.get(0).getName());
                }else if(response.code()==400){
                    Log.d("evening", "onClick: test response fail");
                }
                Log.d("evening", "response end");
            }
            @Override
            public void onFailure(Call<List<RestResult>> call, Throwable t) {
                Log.d("serverError",t.getMessage());
            }
        });
    }
}