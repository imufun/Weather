package com.example.imran.dhakaweather.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imran.dhakaweather.R;
import com.example.imran.dhakaweather.weather.Day;

/**
 * Created by Imran on 11/2/2015.
 */
public class DayAdapter extends BaseAdapter {

    public Context mContext;
    public Day[] mDays;

    public DayAdapter(Context context, Day[] Days) {
        this.mContext = context;
        mDays = Days;
    }


    @Override
    public int getCount() {
        return mDays.length;
    }

    @Override
    public Object getItem(int position) {
        return mDays[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.daily_list_itam, null);
            holder = new ViewHolder();
            holder.iconImaesView = (ImageView) convertView.findViewById(R.id.iconimageView);
            holder.dayNamelable = (TextView) convertView.findViewById(R.id.dayNamelable);
            holder.teamperatureLable = (TextView) convertView.findViewById(R.id.teamperatureLable);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return null;
    }


    public static class ViewHolder {

        ImageView iconImaesView;
        TextView teamperatureLable;
        TextView dayNamelable;


    }
}
