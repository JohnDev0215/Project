package com.byd.mvp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.byd.mvp.ui.jsbridge.BridgeWebView;

/* JADX INFO: loaded from: classes2.dex */
public class BydBridgeWebView extends BridgeWebView {
    public BydBridgeWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.byd.mvp.ui.jsbridge.BridgeWebView
    public void i() {
        loadUrl("javascript:window.native_obj.getSourceCode(document.getElementsByTagName('html')[0].innerHTML)");
    }

    public BydBridgeWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public BydBridgeWebView(Context context) {
        super(context);
    }
}
