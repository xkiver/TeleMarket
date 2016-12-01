package com.google.android.gms.samples.vision.barcodereader;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.samples.vision.barcodereader.modelo.Producto;

import java.util.List;

/**
 * Created by Patricio on 01-12-2016.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private int numero;
    private List<Producto> mDataset;
    private Context mContext;
    //private DataBaseClass myDB;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;

        public ImageView mImageView;
        public Button mAddBtn;

        public ViewHolder(View v, int numero) {
            super(v);
            mTextView1 = (TextView) v.findViewById(R.id.textName);
            mTextView2 = (TextView) v.findViewById(R.id.textDescription);
            mTextView3 = (TextView) v.findViewById(R.id.textValor);
            mAddBtn = (Button) v.findViewById(R.id.add_btn);
            if (numero==2){
                mAddBtn.setVisibility(View.GONE);
            }
        }
    }

    public DataAdapter(Context mContext, List<Producto> myDataset , int number) {
        this.mContext = mContext;
        this.mDataset = myDataset;
        //myDB = new DataBaseClass(mContext);
        this.numero = number;
    }
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_view, parent, false);
        return new ViewHolder(v, numero);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Producto prod = mDataset.get(position);

        holder.mTextView1.setText(prod.getNombre());
        holder.mTextView2.setText(prod.getDescription());
        holder.mTextView3.setText(prod.getValor());
        /*holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = feed.getLink();
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                }
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                mContext.startActivity(browserIntent);
            }
        });*/

        //Glide.with(mContext).load(feed.getImage()).into(holder.mImageView);
        //Cursor total = myDB.getAllData();
        /*if(total.getCount() == 0 && numero == 2) {
            Toast.makeText(mContext, "USTED NO POSEE FAVORITOS", Toast.LENGTH_LONG).show();
        }
        Cursor res = myDB.getThatData(feed.getLink());
        if(res.getCount() == 0) {feed.setFavorite(false);}
        else{ feed.setFavorite(true);}

        if(feed.isFavorite()) {
            holder.mAddBtn.setText(mContext.getString(R.string.added));
        } else {
            holder.mAddBtn.setText(mContext.getString(R.string.like));
        }*/
        holder.mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * In this section, you have to manage the add behavior on local database
                 */
                /*feed.setFavorite(!feed.isFavorite());
                if(feed.isFavorite()) {
                    myDB.insertData(feed.getTitle(),
                            feed.getLink(),
                            feed.getAuthor(),
                            feed.getPublishedDate(),
                            feed.getContent(),
                            feed.getImage());
                    holder.mAddBtn.setText(mContext.getString(R.string.added));
                } else {
                    myDB.deleteData(feed.getLink());
                    holder.mAddBtn.setText(mContext.getString(R.string.like));
                }*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
