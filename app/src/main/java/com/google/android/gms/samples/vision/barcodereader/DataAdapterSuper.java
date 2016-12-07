package com.google.android.gms.samples.vision.barcodereader;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.samples.vision.barcodereader.modelo.Producto;
import com.google.android.gms.samples.vision.barcodereader.modelo.Supermercado;

import java.util.List;

/**
 * Created by Luchiin on 06-12-2016.
 */

public class DataAdapterSuper extends RecyclerView.Adapter<DataAdapterSuper.ViewHolder> {
    private List<Supermercado> mDataset;
    private ItemClickListener clickListener;

    public DataAdapterSuper(List<Supermercado> myDataset) {
        mDataset = myDataset;
    }

    public void setOnClickListener(ItemClickListener listener) {
        this.clickListener = listener;
    }

    @Override
    public DataAdapterSuper.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.super_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Supermercado repo = mDataset.get(position);

        holder.mTextView.setText(repo.getNombre());
        holder.mDescriptionView.setText(repo.getRegion());
        holder.mActualView.setText(repo.getLugar());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mTextView;
        public TextView mDescriptionView;
        public TextView mActualView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.superName);
            mActualView = (TextView) v.findViewById(R.id.superLugar);
            mDescriptionView = (TextView) v.findViewById(R.id.superRegion);
            itemView.setOnClickListener(this); // bind the listener
        }
        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}

