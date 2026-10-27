package com.byd.vrsettings.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/* JADX INFO: loaded from: classes.dex */
public class MyWebView extends WebView {
    onOveredScroll onOveredScroll;

    public interface onOveredScroll {
        void onOvered(int i);
    }

    public MyWebView(Context context) {
        super(context);
        this.onOveredScroll = null;
    }

    public MyWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.onOveredScroll = null;
    }

    public MyWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.onOveredScroll = null;
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        super.onOverScrolled(i, i2, z, z2);
        onOveredScroll onoveredscroll = this.onOveredScroll;
        if (onoveredscroll != null) {
            onoveredscroll.onOvered(i2);
        }
    }

    public void setOnOveredScroll(onOveredScroll onoveredscroll) {
        this.onOveredScroll = onoveredscroll;
    }
}
