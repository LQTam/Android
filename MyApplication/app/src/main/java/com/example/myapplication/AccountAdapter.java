package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AccountAdapter extends BaseAdapter {
    List<Account> listAccount;
    private int layout;
    private Context context;

    public AccountAdapter(List<Account> listAccount, int layout, Context context) {
        this.listAccount = listAccount;
        this.layout = layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listAccount.size();
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
        TextView tvEmail,tvPassword;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(layout,null);
            viewHolder.tvEmail = (TextView) convertView.findViewById(R.id.tvEmail);
            viewHolder.tvPassword = (TextView) convertView.findViewById(R.id.tvPassword);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Account account = listAccount.get(position);
        viewHolder.tvEmail.setText(account.getEmail());
        viewHolder.tvPassword.setText(account.getPassword());
        return convertView;
    }
}
