package com.google.android.material.textfield;

import android.content.Context;
import com.google.android.material.internal.CheckableImageButton;

/* JADX INFO: loaded from: classes.dex */
abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    TextInputLayout f3240a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Context f3241b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    CheckableImageButton f3242c;

    e(TextInputLayout textInputLayout) {
        this.f3240a = textInputLayout;
        this.f3241b = textInputLayout.getContext();
        this.f3242c = textInputLayout.getEndIconView();
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
