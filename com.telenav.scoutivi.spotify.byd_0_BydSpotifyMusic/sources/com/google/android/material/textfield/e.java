package com.google.android.material.textfield;

import android.content.Context;
import com.google.android.material.internal.CheckableImageButton;

/* JADX INFO: compiled from: EndIconDelegate.java */
/* JADX INFO: loaded from: classes.dex */
abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    TextInputLayout f4894a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Context f4895b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    CheckableImageButton f4896c;

    e(TextInputLayout textInputLayout) {
        this.f4894a = textInputLayout;
        this.f4895b = textInputLayout.getContext();
        this.f4896c = textInputLayout.getEndIconView();
    }

    abstract void a();

    boolean b(int i7) {
        return true;
    }

    void c(boolean z6) {
    }

    boolean d() {
        return false;
    }
}
