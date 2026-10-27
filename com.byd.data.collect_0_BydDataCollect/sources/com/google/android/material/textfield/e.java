package com.google.android.material.textfield;

import android.content.Context;
import com.google.android.material.internal.CheckableImageButton;

/* JADX INFO: compiled from: EndIconDelegate.java */
/* JADX INFO: loaded from: classes.dex */
abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    TextInputLayout f4347a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Context f4348b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    CheckableImageButton f4349c;

    e(TextInputLayout textInputLayout) {
        this.f4347a = textInputLayout;
        this.f4348b = textInputLayout.getContext();
        this.f4349c = textInputLayout.getEndIconView();
    }

    abstract void a();

    boolean b(int i2) {
        return true;
    }

    void c(boolean z) {
    }

    boolean d() {
        return false;
    }
}
