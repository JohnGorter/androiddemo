package com.example.johngorter.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Dialog;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;
import android.app.ProgressDialog;

public class MainActivity extends Activity {

    CharSequence[] items = {"Google", "Microsoft", "Android"};
    boolean[] itemsChecked = new boolean[items.length];

    ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // demo 2: removing the title..   requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View v){
        OnCreateDialog(0).show();
    }
    public void onClickProgress(View v){
        final ProgressDialog progress = ProgressDialog.show(this, "Doing stuff", "Please wait..", true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    progress.dismiss();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void onClickProgress2(View v){
        OnCreateDialog(1).show();
        progressdialog.setProgress(0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 15; i++){
                    try {
                        Thread.sleep(1000);

                        progressdialog.incrementProgressBy(100 / 15);
                    } catch(InterruptedException e){

                        e.printStackTrace();
                    }

                }
                progressdialog.dismiss();
            }
        }).start();



    }
    protected Dialog OnCreateDialog(int id){

        switch(id) {
            case 1:
                progressdialog = new ProgressDialog(this);
                progressdialog.setIcon(R.drawable.ic_launcher);
                progressdialog.setTitle("Downloading files...");
                progressdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressdialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Ok clicked!", Toast.LENGTH_SHORT).show();
                    }
                });
                progressdialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Cancel clicked!", Toast.LENGTH_SHORT).show();
                    }
                });
                return progressdialog;
            case 0:
                return new AlertDialog.Builder(this)
                        .setIcon(R.drawable.ic_launcher)
                        .setTitle("This is a dialog")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getBaseContext(), "OK Clicked", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getBaseContext(), "Cancel clicked!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setMultiChoiceItems(items, itemsChecked, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                Toast.makeText(getBaseContext(), items[which] + (isChecked ? " checked!" : " unchecked!"), Toast.LENGTH_SHORT).show();
                            }
                        }).create();

        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
