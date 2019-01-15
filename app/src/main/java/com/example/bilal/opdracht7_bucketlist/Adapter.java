package com.example.bilal.opdracht7_bucketlist;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.BucketItemViewHolder> {

    private List<Item> mItems;
    final private BucketItemClickListener mBucketItemClickListener;
    private static final String TAG = "BucketItemAdapter";

    public Adapter(List<Item> mItems, BucketItemClickListener listener) {
        this.mItems = mItems;
        this.mBucketItemClickListener = listener;
    }

    @NonNull
    @Override
    public Adapter.BucketItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.bucket_item, null);

        return new BucketItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter.BucketItemViewHolder holder, final int i) {
        final Item item = mItems.get(i);
        holder.vCheckBox.setOnCheckedChangeListener(null);
        holder.vTitel.setText(item.getTitel());
        holder.vDescription.setText(item.getDescription());
        holder.vCheckBox.setChecked(item.isChecked());

        if(item.isChecked()){
            holder.vTitel.setPaintFlags(holder.vTitel.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.vDescription.setPaintFlags(holder.vDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.vTitel.setPaintFlags(0);
            holder.vDescription.setPaintFlags(0);
        }
        holder.vCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mBucketItemClickListener.BucketItemOnClick(i, b);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class BucketItemViewHolder extends RecyclerView.ViewHolder {

        private TextView vTitel;
        private TextView vDescription;
        private CheckBox vCheckBox;

        private BucketItemViewHolder(@NonNull View v) {
            super(v);
            vTitel = v.findViewById(R.id.titleTextView);
            vDescription = v.findViewById(R.id.descriptionTextView);
            vCheckBox = v.findViewById(R.id.itemCheckBox);
        }


    }

    public interface BucketItemClickListener{
        void BucketItemOnClick (int i, boolean checked);
    }

    public void swapList (List<Item> newList) {
        mItems = newList;
        if (newList != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }
}
