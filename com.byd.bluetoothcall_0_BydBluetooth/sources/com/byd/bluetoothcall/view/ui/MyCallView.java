package com.byd.bluetoothcall.view.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: classes.dex */
public class MyCallView extends RelativeLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private transient a f3467b;

    public interface a {
        boolean dispatchKeyEvent(KeyEvent keyEvent);
    }

    public MyCallView(Context context) {
        super(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        a aVar = this.f3467b;
        if (aVar != null) {
            aVar.dispatchKeyEvent(keyEvent);
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public void setDispatchKeyEventListener(a aVar) {
        this.f3467b = aVar;
    }

    public MyCallView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyCallView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
