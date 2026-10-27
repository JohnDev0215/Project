package com.android.systemui.qs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.R;
import com.android.systemui.qs.tileimpl.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.util.StyleUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class QSPanelAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private boolean countLimited;
    private boolean flagType;
    private String mCategoryId;
    private String mCategoryLabel;
    private List<QSTile> mList = new ArrayList();
    private int maxCount = 0;

    public int getMaxCount() {
        return this.maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public boolean isCountLimited() {
        return this.countLimited;
    }

    public void setCountLimited(boolean countLimited) {
        this.countLimited = countLimited;
    }

    public QSPanelAdapter(String categoryLabel, String categoryId) {
        this.mCategoryLabel = categoryLabel;
        this.mCategoryId = categoryId;
    }

    public void refreshList(List<QSTile> list) {
        this.mList.clear();
        this.mList.addAll(list);
        notifyDataSetChanged();
    }

    public void releaseList() {
        for (QSTile qSTile : this.mList) {
            if (qSTile != null) {
                qSTile.release();
            }
        }
    }

    public String getCategoryLabel() {
        return this.mCategoryLabel;
    }

    public String getCategoryId() {
        return this.mCategoryId;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewCreateTileView;
        if (this.flagType) {
            viewCreateTileView = LayoutInflater.from(parent.getContext()).inflate(StyleUtil.getResLayout(R.layout.item_qs_panel, "item_qs_panel"), parent, false);
        } else {
            viewCreateTileView = this.mList.get(viewType).createTileView(parent);
        }
        return new ItemViewHolder(viewCreateTileView);
    }

    public void setFlagType(boolean flagType) {
        this.flagType = flagType;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        if (!this.flagType) {
            return position;
        }
        List<QSTile> list = this.mList;
        if (list == null) {
            return 0;
        }
        return list.get(position).getTileImageRes();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        this.mList.get(position).onBindViewHolder(holder, position);
        if (((QSTileImpl) this.mList.get(position)).isHide()) {
            holder.itemView.setVisibility(4);
        } else {
            holder.itemView.setVisibility(0);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<QSTile> list = this.mList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public List<QSTile> getData() {
        return this.mList;
    }

    public void update(boolean isVisible) {
        for (QSTile qSTile : this.mList) {
            if (qSTile != null) {
                qSTile.updateState(isVisible);
            }
        }
    }

    protected class ItemViewHolder extends RecyclerView.ViewHolder {
        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
