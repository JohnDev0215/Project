package com.google.android.setupdesign.template;

import android.widget.AbsListView;
import android.widget.ListView;
import com.google.android.setupdesign.template.RequireScrollMixin;

/* JADX INFO: loaded from: classes.dex */
public class ListViewScrollHandlingDelegate implements RequireScrollMixin.ScrollHandlingDelegate, AbsListView.OnScrollListener {
    private final RequireScrollMixin requireScrollMixin;

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public ListViewScrollHandlingDelegate(RequireScrollMixin requireScrollMixin, ListView listView) {
        this.requireScrollMixin = requireScrollMixin;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (i + i2 >= i3) {
            this.requireScrollMixin.notifyScrollabilityChange(false);
        } else {
            this.requireScrollMixin.notifyScrollabilityChange(true);
        }
    }
}
