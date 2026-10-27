package com.byd.systemsettings.wifi2.adapter;

import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import c4.a;
import com.byd.systemsettings.R;
import com.byd.systemsettings.wifi2.model.WifiSecurity;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SecurityListAdapter extends a<WifiSecurity> {

    public class SecurityHolder extends a<WifiSecurity>.C0013a {
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

    @Override // c4.a
    public void drag(int i8, int i9) {
        d4.a aVar = this.mDragItemCallback;
        if (aVar != null) {
            aVar.a(i8, i9);
        }
        notifyItemRemoved(i8);
    }

    @Override // c4.a
    public a<WifiSecurity>.C0013a initViewHolder(View view) {
        return new SecurityHolder(view);
    }

    @Override // c4.a
    public int loadLayoutId() {
        return R.layout.item_wifi_security_list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onBindViewHolder(RecyclerView.c0 c0Var, int i8) {
        SecurityHolder securityHolder = (SecurityHolder) c0Var;
        securityHolder.id = i8;
        Resources resources = securityHolder.tvName.getContext().getResources();
        WifiSecurity wifiSecurity = (WifiSecurity) this.data.get(i8);
        securityHolder.tvName.setText(wifiSecurity.getName());
        if (wifiSecurity.isChecked()) {
            securityHolder.ivChecked.setVisibility(0);
            securityHolder.tvName.setTextColor(resources.getColor(R.color.text_color_btn_blue));
        } else {
            securityHolder.ivChecked.setVisibility(8);
            securityHolder.tvName.setTextColor(resources.getColor(R.color.text_color_main_dilink_pagers));
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
