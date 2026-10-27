package com.google.android.material.textfield;

import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: NoEndIconDelegate.java */
/* JADX INFO: loaded from: classes.dex */
class g extends e {
    g(TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    @Override // com.google.android.material.textfield.e
    void a() {
        this.f3916a.setEndIconOnClickListener(null);
        this.f3916a.setEndIconDrawable((Drawable) null);
        this.f3916a.setEndIconContentDescription((CharSequence) null);
    }
}
