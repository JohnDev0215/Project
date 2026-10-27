package com.neusoft.na.mots;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.webkit.WebView;

/* JADX INFO: loaded from: classes2.dex */
public class MotsWebView extends WebView {
    private int actionModeType;
    private ActionMode floatingActionMode;

    public MotsWebView(Context context) {
        super(context);
        this.actionModeType = -100;
    }

    public MotsWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.actionModeType = -100;
    }

    public MotsWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.actionModeType = -100;
    }

    public MotsWebView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.actionModeType = -100;
    }

    @Override // android.view.View
    public ActionMode startActionMode(ActionMode.Callback callback, int i) {
        Log.i("MotsWebView", "startActionMode type == " + i + " , " + System.currentTimeMillis());
        if (this.actionModeType != 1) {
            Log.i("MotsWebView", "actionModeType != ActionMode.TYPE_FLOATING , " + System.currentTimeMillis());
            this.actionModeType = 1;
            ActionMode actionModeStartActionMode = super.startActionMode(callback, i);
            this.floatingActionMode = actionModeStartActionMode;
            actionModeStartActionMode.getMenu().clear();
        }
        return this.floatingActionMode;
    }
}
