package com.android.car.ui.recyclerview;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes7.dex */
public interface ScrollBar {
    void adapterChanged(RecyclerView.Adapter adapter);

    void initialize(RecyclerView recyclerView, View view);

    void requestLayout();

    void setPadding(int i, int i2);
}
