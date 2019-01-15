package com.example.bilal.opdracht7_bucketlist;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity  implements Adapter.BucketItemClickListener {

    @BindView(R.id.bucketListRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.titleEditText)
    EditText titleEditText;

    @BindView(R.id.descriptionEditText)
    EditText descriptionEditText;

    private List<Item> mItems;
    private Adapter mAdapter;
    private Model mMainViewModel;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMainViewModel = new Model(getApplicationContext());
        mMainViewModel.getItems().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                mItems = items;
                updateUI();
            }
        });
    }

    private void updateUI() {

        if (mAdapter == null) {
            mAdapter = new Adapter(mItems, this);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        } else {
            mAdapter.swapList(mItems);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void addBucketItem(){
        if(titleEditText.getText() != null && descriptionEditText.getText() != null) {
            String title = titleEditText.getText().toString();
            String description = descriptionEditText.getText().toString();
            Item item = new Item(title, description, false);
            mMainViewModel.insert(item);
            Snackbar.make(findViewById(R.id.ConstraintLayout), "Item Added", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            Snackbar.make(findViewById(R.id.ConstraintLayout), "Please fill in information first!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    @OnClick(R.id.addItemFAB)
    public void addItemFAB(){
        addBucketItem();
    }

    @Override
    public void BucketItemOnClick(int i, boolean checked) {
        if(mItems != null){
            mItems.get(i).setChecked(checked);
        }
    }
}
