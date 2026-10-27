package com.byd.auto_emanual.fragment;

/* JADX INFO: loaded from: classes.dex */
public class SearchHotAdapter extends HorizontalAdapter {
    public static int FROM_INDEX_IN_ALL;

    @Override // com.byd.auto_emanual.fragment.HorizontalAdapter, com.byd.auto_emanual.fragment.IAdapter
    public void setData(ClassifyData data, int orientation) {
        this.orientation = orientation;
        this.classifyData = data;
        this.data = NormalDataAdapter.getStartData(this.classifyData);
        notifyDataSetChanged();
    }

    @Override // com.byd.auto_emanual.fragment.HorizontalAdapter
    public int getFromIndexInAll() {
        return FROM_INDEX_IN_ALL;
    }
}
