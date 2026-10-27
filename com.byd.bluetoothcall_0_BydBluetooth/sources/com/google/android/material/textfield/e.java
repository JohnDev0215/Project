package com.google.android.material.textfield;

import android.content.Context;
import com.google.android.material.internal.CheckableImageButton;

/* JADX INFO: compiled from: EndIconDelegate.java */
/* JADX INFO: loaded from: classes.dex */
abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    TextInputLayout f4071a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Context f4072b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    CheckableImageButton f4073c;

    e(TextInputLayout textInputLayout) {
        this.f4071a = textInputLayout;
        this.f4072b = textInputLayout.getContext();
        this.f4073c = textInputLayout.getEndIconView();
    }

    abstract void a();

    boolean b(int i) {
        return true;
    }

    void c(boolean z) {
    }

    boolean d() {
        return false;
    }
}
