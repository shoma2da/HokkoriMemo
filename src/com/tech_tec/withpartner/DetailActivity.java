package com.tech_tec.withpartner;

import java.util.Iterator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tech_tec.withpartner.component.HokkoriAdapter;
import com.tech_tec.withpartner.data.Hokkori;
import com.tech_tec.withpartner.model.DetailData;
import com.tech_tec.withpartner.model.SQLiteDetailData;

public class DetailActivity extends Activity {
    public static final String PARAM_TYPE = "type";
    
    private DetailData mDetailData;
    private ArrayAdapter<Hokkori> mAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (canAddText(v)) {
                    Hokkori hokkori = new Hokkori(v.getText().toString(), System.currentTimeMillis());
                    addHokkoriData(hokkori);
                }
                return false;
            }
            private boolean canAddText(TextView v) {
                return v != null && v.getText() != null && v.getText().toString().equals("") == false;
            }
            private void addHokkoriData(Hokkori hokkori) {
                Hokkori result = mDetailData.addHokkoriList(hokkori);
                if (result != null) {
                    addHokkoriToListView(result);
                    editText.setText("");
                }
            }
        });
        
        ListView listView = (ListView)findViewById(R.id.listview_hokkori);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
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
