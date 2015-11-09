package com.tipstat.hackerearth.tipstat;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sivakishore.meka on 11/6/2015.
 */
public class CustomAdapter extends BaseAdapter {

    Context mContext = null;
    List<Member> mMemberList = null;

    CustomAdapter(Context context, List<Member> memberList) {
        this.mContext = context;
        this.mMemberList = memberList;
    }

    @Override
    public int getCount() {
        return mMemberList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMemberList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            holder.status = (TextView) convertView.findViewById(R.id.list_item_status);
            holder.next = (TextView) convertView.findViewById(R.id.list_item_next);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.status.setText(mMemberList.get(position).getStatus());
        Typeface iconFont = FontManager.getTypeface(mContext, FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(holder.next, iconFont);
        return convertView;
    }

    private class ViewHolder {
        TextView status, next;

    }
}
