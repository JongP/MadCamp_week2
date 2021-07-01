package com.example.madcamp_week1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.LateinitKt;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>{

    private ArrayList<String> data = null;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text_content = itemView.findViewById(R.id.img_content);
        }
    }

    GalleryAdapter(ArrayList<String> list) {
        data = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.gallery_item, parent, false);
        GalleryAdapter.ViewHolder vh = new GalleryAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String text = data.get(position);
        holder.text_content.setText(text);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
