package com.byd.mvp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/* JADX INFO: loaded from: classes2.dex */
public class InvalidButton extends Button {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2892d;

    public InvalidButton(Context context) {
        super(context);
        this.f2892d = false;
    }

    public void setInvalid(boolean z7) {
        this.f2892d = z7;
        setAlpha(z7 ? 0.3f : 1.0f);
        setEnabled(!z7);
    }

    public InvalidButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2892d = false;
    }

    public InvalidButton(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2892d = false;
    }
}
