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
        setListenerAndTag(findViewById(R.id.button_family), Constatns.FAMILY);
        setListenerAndTag(findViewById(R.id.button_laugh), Constatns.LAUGH);
        setListenerAndTag(findViewById(R.id.button_memory), Constatns.MEMORY);
        setListenerAndTag(findViewById(R.id.button_present), Constatns.PRESENT);
        setListenerAndTag(findViewById(R.id.button_words), Constatns.WORDS);
        setListenerAndTag(findViewById(R.id.button_trip), Constatns.TRIP);
    }
    
    private void setListenerAndTag(View v, String tag) {
        v.setTag(tag);
        v.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.PARAM_TYPE, v.getTag().toString());
        startActivity(intent);
    }
}
