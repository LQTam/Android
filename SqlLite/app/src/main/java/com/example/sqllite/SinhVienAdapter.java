package com.example.sqllite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {
    private int layout;
    private Context context;
    List<SinhVien> sinhVienList;

    public SinhVienAdapter(int layout, Context context, List<SinhVien> sinhVienList) {
        this.layout = layout;
        this.context = context;
        this.sinhVienList = sinhVienList;
    }

    @Override
    public int getCount() {
        return sinhVienList.size();
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
        TextView tvName,tvMaSV,tvDTB;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(layout,null);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvMaSV = (TextView) convertView.findViewById(R.id.tvMaSV);
            viewHolder.tvDTB = (TextView) convertView.findViewById(R.id.tvDTB);
            convertView.setTag(viewHolder);
        }

        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SinhVien sv = sinhVienList.get(position);
        viewHolder.tvName.setText(sv.getName());
        viewHolder.tvMaSV.setText(sv.getMasv());
        viewHolder.tvDTB.setText(""+sv.getDtb());
        return convertView;
    }
}
