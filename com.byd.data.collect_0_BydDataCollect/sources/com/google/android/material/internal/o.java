package com.google.android.material.internal;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* JADX INFO: compiled from: ViewOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
class o implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ViewOverlay f4178a;

    o(View view) {
        this.f4178a = view.getOverlay();
    }

    @Override // com.google.android.material.internal.p
    public void a(Drawable drawable) {
        this.f4178a.add(drawable);
    }

    @Override // com.google.android.material.internal.p
    public void b(Drawable drawable) {
        this.f4178a.remove(drawable);
    }
}
