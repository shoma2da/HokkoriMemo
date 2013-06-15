package com.example.withpartner;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.withpartner.model.DetailData;
import com.example.withpartner.model.SQLiteDetailData;

public class DetailActivity extends Activity {
    public static final String PARAM_TYPE = "type";
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);
        
        DetailData detailData = getDetailData();
        updateViews(detailData);
    }
    
    private DetailData getDetailData() {
        String type = getIntent().getStringExtra(PARAM_TYPE);
        return SQLiteDetailData.create(getApplicationContext(), type);
    }
    
    private void updateViews(DetailData detailData) {
        ((ImageView)findViewById(R.id.image_type_icon)).setImageResource(detailData.getImageResourceId());
        ((TextView)findViewById(R.id.text_type_title)).setText(detailData.getTitle());
    }
}
