package com.example.madcamp_week2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.madcamp_week2.R;
import com.example.madcamp_week2.model.Post;


public class GalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<Post> data;
    private static Context context;

    //private OnItemClickListener mListener;
    private String[] sliderImage;

    int mItemViewType;

    public GalleryAdapter(Context context, ArrayList<Post> list, String[] sliderImage) {
        data = list;
        this.context = context;
        this.sliderImage = sliderImage;
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        TextView restname;
        TextView title;
        TextView content;
        TextView rate;
        ImageView img;
        int type;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_id);
            content = itemView.findViewById(R.id.content_id);
            rate = itemView.findViewById(R.id.rate);
            restname = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.img);
            //type = viewType;

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (listener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            listener.onItemClick(position);
//                        }
//                    }
//                }
//            });
        }

        public void bindSliderImage(String imageURL) {
            Glide.with(context).load(imageURL).placeholder(R.drawable.loading).into(img);
        }
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
        hld.bindSliderImage(sliderImage[position]);
        hld.title.setText(post.getTitle());
        hld.rate.setText(post.getRate() + "");
        hld.content.setText(post.getContent());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItemViewType;
    }
}
