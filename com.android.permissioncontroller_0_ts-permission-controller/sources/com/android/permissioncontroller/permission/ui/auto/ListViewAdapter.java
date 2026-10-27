package com.android.permissioncontroller.permission.ui.auto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.permissioncontroller.R;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class ListViewAdapter extends ArrayAdapter {
    private TextView mItemDetail;
    private PermissionItem mPermissionItem;
    private TextView mPermissionName;
    private ImageView mSwitch;
    private View mView;
    private final int resourceId;

    public ListViewAdapter(Context context, int textViewResourceId, List<PermissionItem> objects) {
        super(context, textViewResourceId, objects);
        this.resourceId = textViewResourceId;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        this.mPermissionItem = (PermissionItem) getItem(position);
        if (convertView == null) {
            this.mView = LayoutInflater.from(getContext()).inflate(this.resourceId, parent, false);
        } else {
            this.mView = convertView;
        }
        this.mSwitch = (ImageView) this.mView.findViewById(R.id.btn_image);
        this.mPermissionName = (TextView) this.mView.findViewById(R.id.text_title);
        this.mItemDetail = (TextView) this.mView.findViewById(R.id.text_detail);
        this.mSwitch.setImageResource(this.mPermissionItem.getImageId());
        this.mPermissionName.setText(this.mPermissionItem.getPermissionName());
        this.mItemDetail.setText(this.mPermissionItem.getDetailMessage());
        return this.mView;
    }
}
