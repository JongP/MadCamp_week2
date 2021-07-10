package com.example.madcamp_week2.frag;

import android.content.Intent;
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

import com.example.madcamp_week2.model.Dictionary;
import com.example.madcamp_week2.adapter.DictionaryAdapter;
import com.example.madcamp_week2.model.Item;
import com.example.madcamp_week2.R;
import com.example.madcamp_week2.server.RestResult;
import com.example.madcamp_week2.server.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment1 extends Fragment {

    ArrayList<Item> mArrayList = new ArrayList<Item>();
    private DictionaryAdapter mAdapter;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.249.18.117:80";
    RecyclerView mRecyclerView;

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
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_id);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        handleTestServer();

        Log.i("evening", mArrayList.toString());

        for(Item i : mArrayList) {
            if (i.type == 0) {
                Log.d("evening", i.getCategory());
                Log.d("evening", "type 0");
            } else if (i.type == 1) {
                Log.d("evening", i.getDict().getName());
                Log.d("evening", "type 1");

            }
            Log.d("evening", "in for");

        }


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
                    List<RestResult> list;
                    list = response.body();
                    Log.d("evening", list.get(0).getName());

                    parsingRestResult(list);

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

    private void parsingRestResult(List<RestResult> list) {

        int[] numEachCategory = new int[5];
        Dictionary dict;
        int index;

        for(RestResult restaurant : list){

            switch (restaurant.getCategory()) {
                case "한식":
                    if (numEachCategory[0] == 0) {
                        index = 0;
                        if (mArrayList.size() > index)
                            mArrayList.add(index, new Item("한식"));
                        else
                            mArrayList.add(new Item("한식"));

                        Log.d("evening", "한식 header index : " + index);
                    }
                    numEachCategory[0]++;
                    index = numEachCategory[0];

                    dict = new Dictionary(restaurant.getName(), restaurant.getContact());

                    if (mArrayList.size() > index)
                        mArrayList.add(index, new Item("한식", dict));
                    else
                        mArrayList.add(new Item("한식", dict));

                    Log.d("evening", "한식 Item : " + mArrayList.get(index).dict.getName());

                    break;

                case "일식":
                    if (numEachCategory[1] == 0) {
                        index = numEachCategory[0] + 1;
                        if (mArrayList.size() > index)
                            mArrayList.add(index, new Item("일식"));
                        else
                            mArrayList.add(new Item("일식"));
                    }
                    numEachCategory[1]++;
                    index = numEachCategory[0] + 1 + numEachCategory[1];

                    dict = new Dictionary(restaurant.getName(), restaurant.getContact());

                    if (mArrayList.size() > index)
                        mArrayList.add(index, new Item("일식", dict));
                    else
                        mArrayList.add(new Item("일식", dict));
                    break;

                case "양식":
                    if (numEachCategory[2] == 0) {
                        index = numEachCategory[0] + 1 + numEachCategory[1] + 1;

                        if (mArrayList.size() > index)
                            mArrayList.add(index, new Item("양식"));
                        else
                            mArrayList.add(new Item("양식"));
                    }
                    numEachCategory[2]++;
                    index = numEachCategory[0] + 1
                            + numEachCategory[1] + 1
                            + numEachCategory[2];

                    dict = new Dictionary(restaurant.getName(), restaurant.getContact());

                    if (mArrayList.size() > index)
                        mArrayList.add(index, new Item("양식", dict));
                    else
                        mArrayList.add(new Item("양식", dict));
                    break;

                case "Pub & Bar":
                    if (numEachCategory[4] == 0) {
                        index = numEachCategory[0] + 1
                                + numEachCategory[1] + 1
                                + numEachCategory[2] + 1
                                + numEachCategory[3] + 1;
                        if (mArrayList.size() > index)
                            mArrayList.add(index, new Item("Pub & Bar"));
                        else
                            mArrayList.add(new Item("Pub & Bar"));
                    }
                    numEachCategory[4]++;
                    index = numEachCategory[0] + 1
                            + numEachCategory[1] + 1
                            + numEachCategory[2] + 1
                            + numEachCategory[3] + 1
                            + numEachCategory[4];

                    dict = new Dictionary(restaurant.getName(), restaurant.getContact());

                    if (mArrayList.size() > index)
                        mArrayList.add(index, new Item("Pub & Bar", dict));
                    else
                        mArrayList.add(new Item("Pub & Bar", dict));
                    break;

                default:
                    if (numEachCategory[3] == 0) {
                        index = numEachCategory[0] + 1
                                + numEachCategory[1] + 1
                                + numEachCategory[2] + 1;
                        if (mArrayList.size() > index)
                            mArrayList.add(index, new Item("기타 외국 음식"));
                        else
                            mArrayList.add(new Item("기타 외국 음식"));
                    }
                    numEachCategory[3]++;
                    index = numEachCategory[0] + 1
                            + numEachCategory[1] + 1
                            + numEachCategory[2] + 1
                            + numEachCategory[3];

                    dict = new Dictionary(restaurant.getName(), restaurant.getContact());
                    if (mArrayList.size() > index)
                        mArrayList.add(index, new Item("기타 외국 음식", dict));
                    else
                        mArrayList.add(new Item("기타 외국 음식", dict));
                    break;
            }
        }
    }

}