package com.example.withpartner.component;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
        
        final Hokkori hokkori = getItem(position);
        final String dateText = mFormat.format(new Date(hokkori.getTime()));
        
        TextView textView = (TextView)view.findViewById(R.id.textview_text);
        textView.setText(hokkori.getText());
        TextView dateView = (TextView)view.findViewById(R.id.textview_date);
        dateView.setText(dateText);
        TextView shareView = (TextView)view.findViewById(R.id.text_share);
        shareView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, String.format("%sに「%s」 #%s",
                                                                    dateText,
                                                                    hokkori.getText(),
                                                                    getContext().getString(R.string.app_name)
                                                                ));
                getContext().startActivity(intent);
            }
        });
        
        return view;
    }
}
