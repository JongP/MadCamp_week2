package com.example.madcamp_week1;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder>{

    private ArrayList<Dictionary> mList;

    // constructor
    public DictionaryAdapter(ArrayList<Dictionary> list){
        this.mList = list;
    }

    public class DictionaryViewHolder extends RecyclerView.ViewHolder {

        protected TextView index;
        protected TextView name;
        protected TextView contact;

        public DictionaryViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            this.index = (TextView) itemView.findViewById(R.id.index_id);
            this.name = (TextView) itemView.findViewById(R.id.name_id);
            this.contact = (TextView) itemView.findViewById(R.id.contact_id);
        }
    }

    @NonNull
    @NotNull
    @Override
    public DictionaryViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        DictionaryViewHolder viewHolder = new DictionaryViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DictionaryViewHolder holder, int position) {

        holder.index.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        holder.name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        holder.contact.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);

        holder.index.setGravity(Gravity.CENTER);
        holder.name.setGravity(Gravity.CENTER);
        holder.contact.setGravity(Gravity.CENTER);

        holder.index.setText(mList.get(position).getIndex());
        holder.name.setText(mList.get(position).getName());
        holder.contact.setText(mList.get(position).getContact());

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}
