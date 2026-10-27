package com.google.android.material.internal;

import android.annotation.SuppressLint;
import android.widget.ImageButton;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"AppCompatCustomView"})
public class k extends ImageButton {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3161b;

    public final void b(int i, boolean z) {
        super.setVisibility(i);
        if (z) {
            this.f3161b = i;
        }
    }

    public final int getUserSetVisibility() {
        return this.f3161b;
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i) {
        b(i, true);
    }
}
