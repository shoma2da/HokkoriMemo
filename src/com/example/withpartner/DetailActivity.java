package com.example.withpartner;

import java.util.Iterator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.withpartner.component.HokkoriAdapter;
import com.example.withpartner.data.Hokkori;
import com.example.withpartner.model.DetailData;
import com.example.withpartner.model.SQLiteDetailData;

public class DetailActivity extends Activity {
    public static final String PARAM_TYPE = "type";
    
    private DetailData mDetailData;
    private ArrayAdapter<Hokkori> mAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);
        
        mAdapter = new HokkoriAdapter(this);
        mDetailData = getDetailData();
        
        initViews();
        updateViews();
    }
    
    private DetailData getDetailData() {
        String type = getIntent().getStringExtra(PARAM_TYPE);
        return SQLiteDetailData.create(getApplicationContext(), type);
    }
    
    private void updateViews() {
        ((ImageView)findViewById(R.id.image_type_icon)).setImageResource(mDetailData.getImageResourceId());
        ((TextView)findViewById(R.id.text_type_title)).setText(mDetailData.getTitle());
        String countText = getString(R.string.hokkori_count, mDetailData.getHokkoriList().size());
        ((TextView)findViewById(R.id.text_type_count)).setText(countText);
        
        for (Iterator<Hokkori> iterator = mDetailData.getHokkoriList().iterator(); iterator.hasNext(); ) {
            Hokkori hokkori = iterator.next();
            addHokkoriToListView(hokkori);
        }
        
    }
    
    private void initViews() {
        final EditText editText = (EditText)findViewById(R.id.edittext_hokkori);
        final View hokkoriAddButton = findViewById(R.id.button_hokkori_add);
        
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (null == editText.getText() || "".equals(editText.getText().toString()) ) {
                    hokkoriAddButton.setEnabled(false);
                } else {
                    hokkoriAddButton.setEnabled(true);
                }
            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void afterTextChanged(Editable s) { }
        });
        hokkoriAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hokkori hokkori = new Hokkori(editText.getText().toString(), System.currentTimeMillis());
                boolean result = mDetailData.addHokkoriList(hokkori);
                if (result) {
                    addHokkoriToListView(hokkori);
                    editText.setText("");
                }
            }
        });
        
        ListView listView = (ListView)findViewById(R.id.listview_hokkori);
        listView.setAdapter(mAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(DetailActivity.this).setTitle(R.string.delete_confirm)
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
    
                        }
                    })
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleleHokkori(parent, position);
                        }
                    })
                .create().show();
                
                
                return false;
            }
            private void deleleHokkori(AdapterView<?> parent, int position) {
                HokkoriAdapter adapter = (HokkoriAdapter)parent.getAdapter();
                Hokkori hokkori = adapter.getItem(position);
                
                if (mDetailData.removeHokkoriList(hokkori)) {
                    adapter.remove(hokkori);
                }
            }
        });
    }
    
    private void addHokkoriToListView(Hokkori hokkori) {
        mAdapter.insert(hokkori, 0);
        
        String countText = getString(R.string.hokkori_count, mDetailData.getHokkoriList().size());
        ((TextView)findViewById(R.id.text_type_count)).setText(countText);
    }
}
