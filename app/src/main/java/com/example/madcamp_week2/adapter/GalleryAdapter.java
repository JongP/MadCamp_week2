package com.example.madcamp_week2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.madcamp_week2.R;
import com.example.madcamp_week2.model.Post;
import com.example.madcamp_week2.server.RetrofitInterface;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<Post> data;
    private static Context context;
    private String TAG = "GalleryAdapter: ";
    private String BASE_URL = "http://192.249.18.117:80";
    private Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);


    //private OnItemClickListener mListener;
    //private String[] sliderImage;

    int mItemViewType;

    public GalleryAdapter(Context context, ArrayList<Post> list, String[] sliderImage) {
        data = list;
        this.context = context;
        //this.sliderImage = sliderImage;
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        TextView restname;
        TextView title;
        //TextView content;
        TextView rate;
        //ImageView img;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_id);
            //content = itemView.findViewById(R.id.content_id);
            rate = itemView.findViewById(R.id.rate);
            restname = itemView.findViewById(R.id.name);
            //img = itemView.findViewById(R.id.img);

        }

//        public void bindSliderImage(String imageURL) {
//            Glide.with(context).load(imageURL).placeholder(R.drawable.loading).into(img);
//        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.gallery_item_list, parent, false);
        ListViewHolder vh = new ListViewHolder(view);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ListViewHolder hld = (ListViewHolder) holder;

        Post post = data.get(position);

        hld.restname.setText(post.getRestName());
        //hld.bindSliderImage(sliderImage[position]);
        hld.title.setText(post.getTitle());
        hld.rate.setText(post.getRate() + "");
        //hld.content.setText(post.getContent());
        //getServerImage(post.getId(),hld.img);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItemViewType;
    }

    private void getServerImage(String postId, ImageView imageView) {
        HashMap<String ,String > map = new HashMap<>();
        map.put("_id",postId);

        Call<ResponseBody> call = retrofitInterface.executePostGet(map);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse: start");
                Log.d(TAG, response.toString());
                InputStream is=null;
                Bitmap bitmap = null;
                if(response.body()!=null) is = response.body().byteStream();
                if(is==null)  Log.d(TAG, "onResponse: is is null");
                else bitmap = BitmapFactory.decodeStream(is);

                if(bitmap!=null) imageView.setImageBitmap(bitmap);


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFauilure: start");
            }
        });
    }
}
