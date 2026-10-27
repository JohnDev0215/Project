package com.byd.digitalkey.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.base.view.recyclerview.BaseListRecyclerAdapter;
import com.byd.base.view.recyclerview.BaseViewHolder;

/* JADX INFO: loaded from: classes2.dex */
public class DigitalKeyAdapter extends BaseListRecyclerAdapter<BydDigitalKeyItem> {
    public Context i;

    @Override // com.byd.base.view.recyclerview.BaseListRecyclerAdapter
    public void r(RecyclerView.ViewHolder viewHolder, int i, int i2) {
        BydDigitalKeyItem item;
        if (!(viewHolder instanceof BaseViewHolder) || (item = getItem(i)) == null) {
            return;
        }
        ((BaseViewHolder) viewHolder).c(item);
    }

    @Override // com.byd.base.view.recyclerview.BaseListRecyclerAdapter
    public RecyclerView.ViewHolder t(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        this.i = viewGroup.getContext();
        return new DigitalKeyItemHolder(layoutInflater, viewGroup, this);
    }
}
