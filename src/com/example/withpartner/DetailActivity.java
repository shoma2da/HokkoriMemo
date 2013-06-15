package com.example.withpartner;

import java.util.Iterator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.withpartner.model.DetailData;
import com.example.withpartner.model.SQLiteDetailData;

public class DetailActivity extends Activity {
    public static final String PARAM_TYPE = "type";
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);
        
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        ((ListView)findViewById(R.id.listview_hokkori)).setAdapter(mAdapter);
        
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
        
        for (Iterator<String> iterator = detailData.getHokkoriList().iterator(); iterator.hasNext(); ) {
            String hokkori = iterator.next();
            addHokkori(hokkori);
        }
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
                String hokkori = editText.getText().toString();
                boolean result = detailData.addHokkoriList(hokkori);
                if (result) {
                    addHokkori(hokkori);
                }
            }
        });
    }
    
    private void addHokkori(String hokkori) {
        mAdapter.add(hokkori);
    }
}
