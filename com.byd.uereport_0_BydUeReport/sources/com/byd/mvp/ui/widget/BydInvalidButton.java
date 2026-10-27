package com.byd.mvp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import g4.b;

/* JADX INFO: loaded from: classes2.dex */
public class BydInvalidButton extends Button implements b {
    public BydInvalidButton(Context context) {
        super(context);
        a(context);
    }

    public final void a(Context context) {
        setAllCaps(false);
        setPadding(1, 1, 1, 1);
    }

    @Override // g4.b
    public /* bridge */ /* synthetic */ void setInvalid(boolean z7) {
        super.setInvalid(z7);
    }

    public BydInvalidButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public BydInvalidButton(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        a(context);
    }
}
