package com.byd.systemsettings.wifi2.adapter;

import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import b.b.m.f.c.a.b;
import com.byd.carsettings.R;
import com.byd.mvp.ui.adapter.BydAdapter;
import com.byd.systemsettings.wifi2.model.WifiSecurity;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SecurityListAdapter extends BydAdapter<WifiSecurity> {

    public class SecurityHolder extends BydAdapter<WifiSecurity>.BaseViewHolder {
        public ImageView ivChecked;
        public TextView tvName;

        public SecurityHolder(View view) {
            super(view);
            this.tvName = (TextView) view.findViewById(R.id.tv_name_item_wifi_security_list);
            this.ivChecked = (ImageView) view.findViewById(R.id.iv_check_status_item_wifi_security_list);
        }
    }

    public SecurityListAdapter(List<WifiSecurity> list) {
        super(list);
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
    public BydAdapter<WifiSecurity>.BaseViewHolder initViewHolder(View view) {
        return new SecurityHolder(view);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public int loadLayoutId() {
        return R.layout.item_wifi_security_list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        SecurityHolder securityHolder = (SecurityHolder) viewHolder;
        securityHolder.id = i;
        Resources resources = securityHolder.tvName.getContext().getResources();
        WifiSecurity wifiSecurity = (WifiSecurity) this.data.get(i);
        securityHolder.tvName.setText(wifiSecurity.getName());
        if (wifiSecurity.isChecked()) {
            securityHolder.ivChecked.setVisibility(0);
            securityHolder.tvName.setTextColor(resources.getColor(R.color.text_color_btn_blue));
        } else {
            securityHolder.ivChecked.setVisibility(8);
            securityHolder.tvName.setTextColor(resources.getColor(R.color.text_color_main_dilink_pagers));
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
