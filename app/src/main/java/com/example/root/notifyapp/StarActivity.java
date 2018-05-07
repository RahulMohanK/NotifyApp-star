package com.example.root.notifyapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StarActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    MyAdapter adapterr;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ProgressBar progressBar;


    public static String s;
    private List<ListenItem> listenItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star);
        myDb = new DatabaseHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        // progressBar = (ProgressBar) findViewById(R.id.progressBar);//

        listenItems = new ArrayList<>();


        // insertData();
        showData();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                diss(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);
    }

    public void showData( ){


        Cursor res = myDb.getUpdateData();


        /*if(res.getCount()==0)
        {
            showMessage("Error","Nothing Found");
            return;
        }*/

        while(res.moveToNext()){

            ListenItem item = new ListenItem(res.getString(1));
            listenItems.add(item);
            adapter = new MyAdapter(listenItems,getApplicationContext());
            recyclerView.setAdapter(adapter);

        }



    }

    public void diss(int pos)
    {
        final ListenItem listen = listenItems.get(pos);

        myDb = new DatabaseHelper(this);

        myDb.updateNormal(listen.getLink());
        //holder.imageView.setImageResource(R.drawable.ic_star_old);
        Toast.makeText(this,"Unstar",Toast.LENGTH_LONG).show();





       /* if(isUpdate)
        {
        listenItems.remove(pos);
        /*}else
        {

        }*/



        //adapterr.notifyItemRemoved(pos);

        //Toast.makeText(this,"Unable To Delete",Toast.LENGTH_SHORT).show();


    }

}

