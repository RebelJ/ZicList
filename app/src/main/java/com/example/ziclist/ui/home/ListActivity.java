package com.example.ziclist.ui.home;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ziclist.R;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {


    ArrayAdapter<String> adapter;
    List listzik = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent link = getIntent();
        String action = link.getAction();
        String type = link.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(link); // Handle text being sent
            }
        }


        // intent name cession
        link = getIntent();
        String message = link.getStringExtra("NameCession");
        TextView textView = (TextView) findViewById(R.id.textNameCession);
        textView.setText(message);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                lectureList();
            }
        });

        final ListView list = (ListView) findViewById(R.id.MusiqueList);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listzik);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {

                // final String item = (String) parent.getItemAtPosition(position);

                //list.remove(item);
                // adapter.notifyDataSetChanged();
                // view.setAlpha(1);

            }

        });
    }

    public void lectureList() {
        String lienList = listzik.get(1).toString();
        Uri webpage = Uri.parse(lienList);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(webIntent, 0);
        boolean isIntentSafe = activities.size() > 0;
        if (isIntentSafe) {
            startActivity(webIntent);
        }
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            // Update UI to reflect text being shared
            String st  = extractPath(sharedText);
            listzik.add(st);
        }
    }

    public static String extractPath (String lien){
        String path;
        Integer a;
        Integer i = lien.length();
        a = lien.length()- 37;
        path = lien.substring(a,i);
        return path;
    }

    public void onClickButtonLien(View v)
    {

        TextView textView = (TextView) findViewById(R.id.editLien);
        String lien  = textView.getText().toString();
        listzik.add(lien);


    }





    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }




}