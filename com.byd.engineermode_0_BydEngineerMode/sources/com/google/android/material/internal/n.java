package com.google.android.material.internal;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* JADX INFO: compiled from: ViewOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
class n implements o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ViewOverlay f2841a;

    n(View view) {
        this.f2841a = view.getOverlay();
    }

    @Override // com.google.android.material.internal.o
    public void a(Drawable drawable) {
        this.f2841a.add(drawable);
    }

    @Override // com.google.android.material.internal.o
    public void b(Drawable drawable) {
        this.f2841a.remove(drawable);
    }
}
