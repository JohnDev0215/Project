package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import b.b.m.g.g;

/* JADX INFO: loaded from: classes2.dex */
public class BydEditText extends EditText implements View.OnFocusChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public View.OnFocusChangeListener f3359a;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            g.a(BydEditText.this);
        }
    }

    public BydEditText(Context context) {
        super(context);
        a(context);
    }

    public final void a(Context context) {
        super.setOnFocusChangeListener(this);
        setTextAlignment(5);
        setGravity(8388627);
        setTextDirection(5);
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f3359a = null;
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        b.b.m.c.a.a("BydEditText", "---onFocusChange--hasFocus = " + z);
        View.OnFocusChangeListener onFocusChangeListener = this.f3359a;
        if (onFocusChangeListener != null) {
            onFocusChangeListener.onFocusChange(view, z);
        }
        if (z) {
            return;
        }
        postDelayed(new a(), 50L);
    }

    @Override // android.view.View
    public void setOnFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.f3359a = onFocusChangeListener;
    }

    public BydEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public BydEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }
}
