package com.google.android.material.internal;

import android.annotation.SuppressLint;
import android.widget.ImageButton;

/* JADX INFO: compiled from: VisibilityAwareImageButton.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"AppCompatCustomView"})
public class m extends ImageButton {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4673e;

    public final void b(int i7, boolean z6) {
        super.setVisibility(i7);
        if (z6) {
            this.f4673e = i7;
        }
    }

    public final int getUserSetVisibility() {
        return this.f4673e;
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i7) {
        b(i7, true);
    }
}
