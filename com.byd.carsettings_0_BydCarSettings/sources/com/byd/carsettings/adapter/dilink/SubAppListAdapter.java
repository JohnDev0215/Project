package com.byd.carsettings.adapter.dilink;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import b.b.g.m.d.h;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.mvp.ui.adapter.BydAdapter;

/* JADX INFO: loaded from: classes2.dex */
public class SubAppListAdapter extends BydAdapter<h> {

    public class a implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2744a;

        public a(int i) {
            this.f2744a = i;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (SubAppListAdapter.this.mBtnCheckedCallback != null) {
                SubAppListAdapter.this.mBtnCheckedCallback.a(this.f2744a, z, BtnType.BTN_0);
            }
        }
    }

    public class b extends BydAdapter<h>.BaseViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ImageView f2746a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f2747b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public BydSwitch f2748c;

        public b(View view) {
            super(view);
            a(view);
        }

        public final void a(View view) {
            this.f2746a = (ImageView) view.findViewById(R.id.iv_app_icon_item_sub_app_list);
            this.f2747b = (TextView) view.findViewById(R.id.tv_app_name_item_sub_app_list);
            this.f2748c = (BydSwitch) view.findViewById(R.id.switch_item_sub_app_list);
        }
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public BydAdapter<h>.BaseViewHolder initViewHolder(View view) {
        return new b(view);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public int loadLayoutId() {
        return R.layout.item_sub_app_list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        b bVar = (b) viewHolder;
        h hVar = (h) this.data.get(i);
        bVar.f2746a.setImageDrawable(hVar.b());
        bVar.f2747b.setText(hVar.a());
        bVar.f2748c.setOnCheckedChangeListener(new a(i));
    }
}
