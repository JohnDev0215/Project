package com.google.android.material.textfield;

import android.content.Context;
import com.google.android.material.internal.CheckableImageButton;

/* JADX INFO: compiled from: EndIconDelegate.java */
/* JADX INFO: loaded from: classes.dex */
abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    TextInputLayout f3755a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Context f3756b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    CheckableImageButton f3757c;

    e(TextInputLayout textInputLayout) {
        this.f3755a = textInputLayout;
        this.f3756b = textInputLayout.getContext();
        this.f3757c = textInputLayout.getEndIconView();
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
