package com.byd.btsetting.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import b.b.m.f.c.a.b;
import com.byd.bt.model.BTDevice;
import com.byd.bt.model.PhoneType;
import com.byd.carsettings.R;
import com.byd.mvp.ui.adapter.BydAdapter;

/* JADX INFO: loaded from: classes.dex */
public class DiscoverBtListAdapter extends BydAdapter<BTDevice> {

    public class a extends BydAdapter<BTDevice>.BaseViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ImageView f2558a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f2559b;

        public a(View view) {
            super(view);
            this.f2558a = (ImageView) view.findViewById(R.id.iv_bt_type_item_discover_bt_list);
            this.f2559b = (TextView) view.findViewById(R.id.tv_bt_name_item_discover_bt_list);
        }
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
    public BydAdapter<BTDevice>.BaseViewHolder initViewHolder(View view) {
        return new a(view);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public int loadLayoutId() {
        return R.layout.item_discover_bt_list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (i >= getItemCount()) {
            return;
        }
        a aVar = (a) viewHolder;
        aVar.id = i;
        BTDevice bTDevice = (BTDevice) this.data.get(i);
        aVar.f2559b.setText(bTDevice.getName());
        int iA = b.b.e.a.a(bTDevice.getType(), PhoneType.NORMAL, false);
        if (iA != 0) {
            aVar.f2558a.setImageResource(iA);
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
