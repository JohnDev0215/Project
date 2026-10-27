package com.google.android.material.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"AppCompatCustomView"})
public class VisibilityAwareImageButton extends ImageButton {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2383b;

    public VisibilityAwareImageButton(Context context) {
        this(context, null);
    }

    public final void b(int i3, boolean z3) {
        super.setVisibility(i3);
        if (z3) {
            this.f2383b = i3;
        }
    }

    public final int getUserSetVisibility() {
        return this.f2383b;
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i3) {
        b(i3, true);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        this.f2383b = getVisibility();
    }
}
