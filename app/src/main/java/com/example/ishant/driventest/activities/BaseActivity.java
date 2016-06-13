package com.example.ishant.driventest.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.ishant.driventest.R;

/**
 * Created by Ishant Rana on 13/06/16.
 */
public class BaseActivity extends AppCompatActivity {

    protected Toolbar setUpToolbar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Activity title
        TextView titleTextView = (TextView)toolbar.findViewById(R.id.title_text);
        titleTextView.setText(title);
        return toolbar;
    }
}
