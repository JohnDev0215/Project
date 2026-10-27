package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import b.b.a.j.c.a;

/* JADX INFO: loaded from: classes2.dex */
public class BydInvalidButton extends Button implements a {
    public BydInvalidButton(Context context) {
        super(context);
        d(context);
    }

    public final void d(Context context) {
        setAllCaps(false);
        setPadding(1, 1, 1, 1);
    }

    @Override // b.b.a.j.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z) {
        super.setInvalid(z);
    }

    @Override // b.b.a.j.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z, boolean z2) {
        super.setInvalid(z, z2);
    }

    public BydInvalidButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d(context);
    }

    public BydInvalidButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        d(context);
    }
}
