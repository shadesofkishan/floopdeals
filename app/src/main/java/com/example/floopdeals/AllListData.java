package com.example.floopdeals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.floopdeals.adapter.RecyclerAdapter;
import com.example.floopdeals.model.Item;
import com.example.floopdeals.roomdb.ItemDatabase;

import java.util.List;

public class AllListData extends AppCompatActivity {

   private RecyclerView cart_recyclerView;
   private RecyclerAdapter adapter;
   private Handler handler = new Handler();
   private TextView noItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_list_data);

        noItem = findViewById(R.id.noItem);

        ItemDatabase db = Room.databaseBuilder(getApplicationContext(),
                ItemDatabase.class, "items-db").build();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                List<Item> itemList = db.itemDao().getAllItems();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!itemList.isEmpty())
                        {
                            cart_recyclerView = findViewById(R.id.cart_recyclerView);
                            cart_recyclerView.setLayoutManager(new LinearLayoutManager(AllListData.this));

                            adapter = new RecyclerAdapter(itemList);
                            cart_recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            noItem.setVisibility(View.GONE);
                        }
                        else
                        {
                            noItem.setVisibility(View.VISIBLE);
                        }

                    }
                });

            }
        });





    }
}