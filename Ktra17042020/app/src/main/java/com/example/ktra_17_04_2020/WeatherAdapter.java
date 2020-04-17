package com.example.ktra_17_04_2020;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class WeatherAdapter  extends BaseAdapter {
    List<Weather> listWeather;
    int layout;
    Context context;
    public WeatherAdapter(List<Weather> listWeather, int layout, Context context) {
        this.listWeather = listWeather;
        this.layout = layout;
        this.context = context;
    }


    @Override
    public int getCount() {
        return listWeather.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView tvCity,tvTemporary,tvTypeWeather;
        ImageView imgViewWeather;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(layout,null);
            viewHolder.imgViewWeather = (ImageView) convertView.findViewById(R.id.imgViewWeather);
            viewHolder.tvCity =(TextView) convertView.findViewById(R.id.tvCity);
            viewHolder.tvTemporary =(TextView) convertView.findViewById(R.id.tvTemporary);
            viewHolder.tvTypeWeather =(TextView) convertView.findViewById(R.id.tvTypeWeather);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Weather weather = listWeather.get(position);
        viewHolder.tvCity.setText(weather.getCity());
        viewHolder.tvTemporary.setText(""+weather.getTemporary());
        viewHolder.tvTypeWeather.setText(weather.getTypeWeather());
        weather.setImage(weather.getTemporary() > 30 ? R.drawable.sunny : ((weather.getTemporary() >= 20 && weather.getTemporary() <=30) ? R.drawable.cloudy : R.drawable.rainy));
        viewHolder.imgViewWeather.setImageResource(weather.getImage());
        return convertView;
    }
}
