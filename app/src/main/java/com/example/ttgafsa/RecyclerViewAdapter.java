package com.example.ttgafsa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ttgafsa.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(String model);
    }

    private final OnItemClickListener listener;
    private final List<String> dataList;
    private final Context context;

    public RecyclerViewAdapter(Context context, List<String> dataList, OnItemClickListener listener) {
        this.context = context;
        this.dataList = dataList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.model_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = dataList.get(position);
        holder.textView.setText(item);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_recycler);
            imageView = itemView.findViewById(R.id.arrow_search);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    String model = dataList.get(position);
                    listener.onItemClick(model);
                }
            });
        }
    }
}
