package com.google.android.material.theme;

import a.b.k.s;
import a.b.p.d;
import a.b.p.f;
import a.b.p.g;
import a.b.p.r;
import a.b.p.z;
import android.content.Context;
import android.util.AttributeSet;
import b.d.a.a.h0.p;
import b.d.a.a.s.a;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

/* JADX INFO: loaded from: classes.dex */
public class MaterialComponentsViewInflater extends s {
    @Override // a.b.k.s
    public d a(Context context, AttributeSet attributeSet) {
        return new p(context, attributeSet);
    }

    @Override // a.b.k.s
    public f b(Context context, AttributeSet attributeSet) {
        return new MaterialButton(context, attributeSet);
    }

    @Override // a.b.k.s
    public g c(Context context, AttributeSet attributeSet) {
        return new a(context, attributeSet);
    }

    @Override // a.b.k.s
    public r d(Context context, AttributeSet attributeSet) {
        return new b.d.a.a.a0.a(context, attributeSet);
    }

    @Override // a.b.k.s
    public z e(Context context, AttributeSet attributeSet) {
        return new MaterialTextView(context, attributeSet);
    }
}
