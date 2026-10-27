package com.google.android.material.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"AppCompatCustomView"})
public class VisibilityAwareImageButton extends ImageButton {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3964d;

    public VisibilityAwareImageButton(Context context) {
        this(context, null);
    }

    public final void b(int i8, boolean z7) {
        super.setVisibility(i8);
        if (z7) {
            this.f3964d = i8;
        }
    }

    public final int getUserSetVisibility() {
        return this.f3964d;
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i8) {
        b(i8, true);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f3964d = getVisibility();
    }
}
