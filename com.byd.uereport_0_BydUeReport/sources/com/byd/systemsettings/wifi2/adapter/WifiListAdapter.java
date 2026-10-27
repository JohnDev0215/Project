package com.byd.systemsettings.wifi2.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import c4.a;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.systemsettings.R;
import com.byd.systemsettings.wifi2.model.WifiScanResult;
import com.byd.systemsettings.wifi2.util.ResUtils;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class WifiListAdapter extends a<WifiScanResult> {

    public class MyViewHolder extends a<WifiScanResult>.C0013a {
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

    public void checkItem(int i8) {
        int i9 = 0;
        while (i9 < this.data.size()) {
            ((WifiScanResult) this.data.get(i9)).setChecked(i9 == i8);
            i9++;
        }
        notifyDataSetChanged();
    }

    @Override // c4.a
    public void drag(int i8, int i9) {
        d4.a aVar = this.mDragItemCallback;
        if (aVar != null) {
            aVar.a(i8, i9);
        }
        notifyItemRemoved(i8);
    }

    @Override // c4.a
    public a<WifiScanResult>.C0013a initViewHolder(View view) {
        return new MyViewHolder(view);
    }

    @Override // c4.a
    public int loadLayoutId() {
        return R.layout.item_wifi_list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onBindViewHolder(RecyclerView.c0 c0Var, final int i8) {
        MyViewHolder myViewHolder = (MyViewHolder) c0Var;
        myViewHolder.id = i8;
        WifiScanResult wifiScanResult = (WifiScanResult) this.data.get(i8);
        myViewHolder.tvName.setText(wifiScanResult.getName());
        myViewHolder.ivRssi.setImageResource(ResUtils.getRssiImgId(wifiScanResult.getCipherType(), wifiScanResult.getRssiLevel()));
        myViewHolder.ivDetail.setOnClickListener(new View.OnClickListener() { // from class: com.byd.systemsettings.wifi2.adapter.WifiListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (WifiListAdapter.this.mCallback != null) {
                    WifiListAdapter.this.mCallback.onItemBtnClicked(i8, BtnType.BTN_0);
                }
            }
        });
        if (wifiScanResult.isChecked()) {
            myViewHolder.vBgChecked.setVisibility(0);
        } else {
            myViewHolder.vBgChecked.setVisibility(4);
        }
    }

    @Override // c4.a
    public void removeItemByPosition(int i8) {
        d4.a aVar = this.mDragItemCallback;
        if (aVar != null) {
            aVar.d(i8);
        }
        notifyItemRemoved(i8);
    }
}
