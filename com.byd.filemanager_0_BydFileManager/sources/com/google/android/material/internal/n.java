package com.google.android.material.internal;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* JADX INFO: compiled from: ViewOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
class n implements o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ViewOverlay f4341a;

    n(View view) {
        this.f4341a = view.getOverlay();
    }

    @Override // com.google.android.material.internal.o
    public void a(Drawable drawable) {
        this.f4341a.add(drawable);
    }

    @Override // com.google.android.material.internal.o
    public void b(Drawable drawable) {
        this.f4341a.remove(drawable);
    }
}
