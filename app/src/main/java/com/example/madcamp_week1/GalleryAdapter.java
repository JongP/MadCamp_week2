package com.example.madcamp_week1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>{
    private ArrayList<ArrayList<String>> data = null;
    private static Context context;

    private OnItemClickListener mListener;
    private String[] sliderImage;

    public static final int GRID = 0;
    public static final int LIST = 1;
    int mItemViewType;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setItemViewType(int viewType) {
        mItemViewType = viewType;
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView info, name;
        ImageView img;
        int type;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener, int viewType) {
            super(itemView);

            if (viewType == LIST) {
                name = itemView.findViewById(R.id.name);
            }
            img = itemView.findViewById(R.id.img);
            type = viewType;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public void bindSliderImage(String imageURL) {
            Glide.with(context).load(imageURL).into(img);
        }
    }

    public GalleryAdapter(Context context, ArrayList<ArrayList<String>> list, String[] sliderImage) {
        data = list;
        this.context = context;
        this.sliderImage = sliderImage;
    }

    @NonNull
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (viewType == GRID) {
            View view = inflater.inflate(R.layout.gallery_item_grid, parent, false);
            GalleryAdapter.ViewHolder vh = new ViewHolder(view, mListener, viewType);
            return vh;
        } else {
            View view = inflater.inflate(R.layout.gallery_item_list, parent, false);
            GalleryAdapter.ViewHolder vh = new ViewHolder(view, mListener, viewType);
            return vh;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder.type == LIST) {
            String text_name = data.get(position).get(0);
            holder.name.setText(text_name);
        }

        /* glide 로 바꿔서 가게 사진도 인터넷에서 받기 */
        Drawable drawable = context.getResources().getDrawable(R.drawable.rest_1);
//        holder.img.setImageDrawable(drawable);
        holder.bindSliderImage(sliderImage[position]);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
