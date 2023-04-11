package com.example.floopdeals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.floopdeals.adapter.RecyclerAdapter;
import com.example.floopdeals.model.Item;
import com.example.floopdeals.roomdb.ItemDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText name,amount;
    Button addbtn,submitbtn,show_btn;

    RecyclerAdapter adapter;
    LinearLayout insertfield_layout;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ItemDatabase db = Room.databaseBuilder(getApplicationContext(),
                ItemDatabase.class, "items-db").build();

        name = findViewById(R.id.name_editText);
        amount = findViewById(R.id.amount_editText);
        addbtn = findViewById(R.id.add_btn);
        submitbtn = findViewById(R.id.submit_btn);
        show_btn = findViewById(R.id.show_btn);
        insertfield_layout = findViewById(R.id.insertfield_layout);


        show_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AllListData.class);
                startActivity(i);
            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pname = "",price = "";
                pname = name.getText().toString();
                price = amount.getText().toString();

                if (!pname.equals("") && !price.equals(""))
                {
                    submitbtn.setVisibility(View.GONE);
                    insertfield_layout.setVisibility(View.GONE);
                    Item newItem = new Item(pname,price);
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            db.itemDao().insertItem(newItem);

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    getToast("Insert Success! Please in show cart button !");
                                }
                            });

                        }
                    });
                    name.setText("");
                    amount.setText("");

                }
                else
                {
                    getToast("All fields are required !");
                }

            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                amount.setText("");
                submitbtn.setVisibility(View.VISIBLE);
                insertfield_layout.setVisibility(View.VISIBLE);
            }
        });
    }

    public void getToast(String msg)
    {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

}

/*

add to cart screen:


 make a layout with edit text Name and Amount.
 below this layout there are two buttons horizontally Add more and Submit.
 below these button show cart button.
 on click Add more button, layout should be repeat. whenever user click on Add more button layout will repeat
 then on submit click save the data  (name and amount can't be blank )
 on show cart button clicked, show saved cart data in list.

use MVVM method
 */