package com.Healthwealth;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Mareesoftpc on 5/2/2017.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    String progname;
    public static final String KEY_PROGNM1 = "proname";
    public static final String KEY_PROID = "proid";

    Integer[] imageId = {R.drawable.weitdet, R.drawable.hel, R.drawable.hel, R.drawable.hel, R.drawable.hel, R.drawable.hel, R.drawable.hel, R.drawable.hel};
    private Context _context;
    private List<String> _listDataHeader;
    private HashMap<String, List<String>> _listDataChild;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.explistitm, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);


        final RadioButton rb = (RadioButton) convertView.findViewById(R.id.rbt);

        txtListChild.setText(childText);
        progname = txtListChild.getText().toString().trim();
        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = (new Intent(_context, ProgramDetails.class));
                i.putExtra(KEY_PROGNM1, String.valueOf(groupPosition + 1));
                i.putExtra(KEY_PROID, String.valueOf(childPosition + 1));
                _context.startActivity(i);
                rb.setChecked(false);
            }
        });
        return convertView;

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }
    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.explistgrp, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        ImageView iv = (ImageView) convertView.findViewById(R.id.iv_li);
        ImageView iv1 = (ImageView) convertView.findViewById(R.id.iv_dropup);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        iv.setImageResource(imageId[groupPosition]);
        iv1.setImageResource(R.drawable.setting_selector);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
