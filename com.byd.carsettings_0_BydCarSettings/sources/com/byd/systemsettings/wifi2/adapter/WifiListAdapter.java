package com.byd.systemsettings.wifi2.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import b.b.m.f.c.a.b;
import com.byd.carsettings.R;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.mvp.ui.adapter.BydAdapter;
import com.byd.systemsettings.wifi2.model.WifiScanResult;
import com.byd.systemsettings.wifi2.util.ResUtils;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WifiListAdapter extends BydAdapter<WifiScanResult> {

    public class MyViewHolder extends BydAdapter<WifiScanResult>.BaseViewHolder {
        public ImageView ivDetail;
        public ImageView ivRssi;
        public TextView tvName;
        public View vBgChecked;

        public MyViewHolder(View view) {
            super(view);
            this.ivRssi = (ImageView) view.findViewById(R.id.iv_wifi_rssi_item_wifi_list);
            this.tvName = (TextView) view.findViewById(R.id.tv_wifi_name_item_wifi_list);
            this.ivDetail = (ImageView) view.findViewById(R.id.iv_detail_item_wifi_list);
            this.vBgChecked = view.findViewById(R.id.v_item_bg_checked_item_wifi_list);
        }
    }

    public WifiListAdapter(List<WifiScanResult> list) {
        super(list);
    }

    public void checkItem(int i) {
        int i2 = 0;
        while (i2 < this.data.size()) {
            ((WifiScanResult) this.data.get(i2)).setChecked(i2 == i);
            i2++;
        }
        notifyDataSetChanged();
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter, b.b.m.f.c.a.a
    public void drag(int i, int i2) {
        b bVar = this.mDragItemCallback;
        if (bVar != null) {
            bVar.a(i, i2);
        }
        notifyItemRemoved(i);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public BydAdapter<WifiScanResult>.BaseViewHolder initViewHolder(View view) {
        return new MyViewHolder(view);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public int loadLayoutId() {
        return R.layout.item_wifi_list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        myViewHolder.id = i;
        WifiScanResult wifiScanResult = (WifiScanResult) this.data.get(i);
        myViewHolder.tvName.setText(wifiScanResult.getName());
        myViewHolder.ivRssi.setImageResource(ResUtils.getRssiImgId(wifiScanResult.getCipherType(), wifiScanResult.getRssiLevel()));
        myViewHolder.ivDetail.setOnClickListener(new View.OnClickListener() { // from class: com.byd.systemsettings.wifi2.adapter.WifiListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (WifiListAdapter.this.mCallback != null) {
                    WifiListAdapter.this.mCallback.onItemBtnClicked(i, BtnType.BTN_0);
                }
            }
        });
        if (wifiScanResult.isChecked()) {
            myViewHolder.vBgChecked.setVisibility(0);
        } else {
            myViewHolder.vBgChecked.setVisibility(4);
        }
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public void removeItemByPosition(int i) {
        b bVar = this.mDragItemCallback;
        if (bVar != null) {
            bVar.d(i);
        }
        notifyItemRemoved(i);
    }
}
