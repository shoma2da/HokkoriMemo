package com.example.withpartner;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        initViews(detailData);
    }
    
    private DetailData getDetailData() {
        String type = getIntent().getStringExtra(PARAM_TYPE);
        return SQLiteDetailData.create(getApplicationContext(), type);
    }
    
    private void updateViews(DetailData detailData) {
        ((ImageView)findViewById(R.id.image_type_icon)).setImageResource(detailData.getImageResourceId());
        ((TextView)findViewById(R.id.text_type_title)).setText(detailData.getTitle());
    }
    
    private void initViews(final DetailData detailData) {
        final EditText editText = (EditText)findViewById(R.id.edittext_hokkori);
        final View hokkoriAddButton = findViewById(R.id.button_hokkori_add);
        hokkoriAddButton.setClickable(false);
        
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (null == editText.getText() || "".equals(editText.getText().toString()) ) {
                    hokkoriAddButton.setClickable(false);
                } else {
                    hokkoriAddButton.setClickable(true);
                }
            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void afterTextChanged(Editable s) { }
        });
        hokkoriAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailActivity.this, "押された", Toast.LENGTH_SHORT).show();
                detailData.addHokkoriList(editText.getText().toString());
            }
        });
    }
}
