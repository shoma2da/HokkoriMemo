package com.example.withpartner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        
        initViews();
    }

    private void initViews() {
        findViewById(R.id.button_family).setOnClickListener(this);
        findViewById(R.id.button_laugh).setOnClickListener(this);
        findViewById(R.id.button_memory).setOnClickListener(this);
        findViewById(R.id.button_present).setOnClickListener(this);
        findViewById(R.id.button_words).setOnClickListener(this);
        findViewById(R.id.button_trip).setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }
}
