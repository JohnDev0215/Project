package com.google.android.setupdesign.template;

import android.util.Log;
import android.widget.ScrollView;
import com.google.android.setupdesign.template.RequireScrollMixin;
import com.google.android.setupdesign.view.BottomScrollView;

/* JADX INFO: loaded from: classes.dex */
public class ScrollViewScrollHandlingDelegate implements RequireScrollMixin.ScrollHandlingDelegate, BottomScrollView.BottomScrollListener {
    private final RequireScrollMixin requireScrollMixin;

    public ScrollViewScrollHandlingDelegate(RequireScrollMixin requireScrollMixin, ScrollView scrollView) {
        this.requireScrollMixin = requireScrollMixin;
        if (scrollView instanceof BottomScrollView) {
            return;
        }
        Log.w("ScrollViewDelegate", "Cannot set non-BottomScrollView. Found=" + scrollView);
    }

    @Override // com.google.android.setupdesign.view.BottomScrollView.BottomScrollListener
    public void onScrolledToBottom() {
        this.requireScrollMixin.notifyScrollabilityChange(false);
    }

    @Override // com.google.android.setupdesign.view.BottomScrollView.BottomScrollListener
    public void onRequiresScroll() {
        this.requireScrollMixin.notifyScrollabilityChange(true);
    }
}
