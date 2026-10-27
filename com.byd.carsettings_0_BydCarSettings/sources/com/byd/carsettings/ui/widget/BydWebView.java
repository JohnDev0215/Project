package com.byd.carsettings.ui.widget;

import android.app.UiModeManager;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/* JADX INFO: loaded from: classes2.dex */
public class BydWebView extends WebView {
    public BydWebView(Context context) {
        super(context);
        a(context);
    }

    public final void a(Context context) {
        getSettings().setBlockNetworkImage(false);
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        if (uiModeManager == null || uiModeManager.getNightMode() != 2) {
            return;
        }
        setBackgroundColor(0);
    }

    public BydWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public BydWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }
}
