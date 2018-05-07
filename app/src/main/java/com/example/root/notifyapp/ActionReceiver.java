package com.example.root.notifyapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class ActionReceiver extends BroadcastReceiver {


    DatabaseHelper myDb;
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"recieved",Toast.LENGTH_SHORT).show();

        //String action=intent.getStringExtra("action");
        //if(action.equals("action1")){

             /*SharedPreferences sp = getSharedPreferences("prev", Activity.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();
        String prevs=sp.getString("prev","");*/


            String text = Clipboard_Utils.getDataFromClipboard(context);
            String[] text1 = text.split(":");

       /* if(!text.equals(prevs)) {

            editor.putString("prev",text);

            editor.apply();*/
        myDb = new DatabaseHelper(context);

            if (!text.equals("")) {

                if (text1[0].equals("http") || text1[0].equals("https")) {


                    boolean isInserted = myDb.insertData(text);


                    if (isInserted == true) {

                        Toast.makeText(context, "Data Inserted ", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(context, "Not a legal link", Toast.LENGTH_SHORT).show();


                    }


                } else
                    Toast.makeText(context, "Clipboard is empty.", Toast.LENGTH_SHORT).show();

            }

        //else if(action.equals("action2")){
         //   performAction2();

        //}
        //This is used to close the notification tray
        Intent it = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        context.sendBroadcast(it);
    }

    public void performAction1(){


    }



    public void performAction2(){

    }


}
