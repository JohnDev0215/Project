package com.byd.mvp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import m4.e;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class BydEditText extends EditText implements View.OnFocusChangeListener {
    public BydEditText(Context context) {
        super(context);
        a(context);
    }

    public void a(Context context) {
        setOnFocusChangeListener(this);
        setTextAlignment(5);
        setGravity(8388627);
        setTextDirection(5);
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z7) {
        a.a("BydEditText", "---onFocusChange--hasFocus = " + z7);
        if (z7) {
            return;
        }
        e.b(this);
    }

    public BydEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public BydEditText(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        a(context);
    }
}
