package com.byd.auto_emanual.fragment;

/* JADX INFO: loaded from: classes.dex */
public class RecommendHorizontalAdapter extends HorizontalAdapter {
    @Override // com.byd.auto_emanual.fragment.HorizontalAdapter, com.byd.auto_emanual.fragment.IAdapter
    public void setData(ClassifyData data, int orientation) {
        this.orientation = orientation;
        this.classifyData = data;
        this.data = NormalDataAdapter.getHorizontalData(data);
        notifyDataSetChanged();
    }
}
