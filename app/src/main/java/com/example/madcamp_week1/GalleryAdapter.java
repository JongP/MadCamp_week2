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
import com.example.madcamp_week1.MainActivity;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>{

    private ArrayList<ArrayList<String>> data = null;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView info, name;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.img);
            info = itemView.findViewById(R.id.info);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity)context;
            activity.replaceFragment(FragmentImageSlider.newInstance());
        }
    }

    public GalleryAdapter(Context context, ArrayList<ArrayList<String>> list) {
        data = list;
        this.context = context;
    }

    @NonNull
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.gallery_item, parent, false);
        GalleryAdapter.ViewHolder vh = new GalleryAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String text_name = data.get(position).get(1);
        String text_info = data.get(position).get(2);
        holder.name.setText(text_name);
        holder.info.setText(text_info);

        Drawable drawable = context.getResources().getDrawable(R.drawable.rest_1);
        holder.img.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
