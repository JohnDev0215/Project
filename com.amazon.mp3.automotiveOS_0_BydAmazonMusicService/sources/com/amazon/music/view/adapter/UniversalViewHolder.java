package com.amazon.music.view.adapter;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes4.dex */
public final class UniversalViewHolder extends RecyclerView.ViewHolder {
    private Object model;

    public UniversalViewHolder(View view) {
        super(view);
    }

    void setModel(Object obj) {
        this.model = obj;
    }

    public Object getModel() {
        return this.model;
    }
}
