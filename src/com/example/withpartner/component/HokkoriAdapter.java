package com.example.withpartner.component;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.withpartner.R;
import com.example.withpartner.data.Hokkori;

public class HokkoriAdapter extends ArrayAdapter<Hokkori> {
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    
    private LayoutInflater mInflater;
    private int mLayoutId;

    public HokkoriAdapter(Context context) {
        super(context, 0);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLayoutId = R.layout.layout_hokkori;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(mLayoutId, null);
        }
        
        Hokkori hokkori = getItem(position);
        
        TextView textView = (TextView)view.findViewById(R.id.textview_text);
        textView.setText(hokkori.getText());
        TextView dateView = (TextView)view.findViewById(R.id.textview_date);
        dateView.setText(mFormat.format(new Date(hokkori.getTime())));
        
        return view;
    }
}
