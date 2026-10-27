package com.google.android.material.theme;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.app.g;
import androidx.appcompat.widget.c;
import androidx.appcompat.widget.e;
import androidx.appcompat.widget.f;
import androidx.appcompat.widget.q;
import androidx.appcompat.widget.w;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import r2.a;

/* JADX INFO: loaded from: classes.dex */
public class MaterialComponentsViewInflater extends g {
    @Override // androidx.appcompat.app.g
    protected c b(Context context, AttributeSet attributeSet) {
        return new com.google.android.material.textfield.g(context, attributeSet);
    }

    @Override // androidx.appcompat.app.g
    protected e c(Context context, AttributeSet attributeSet) {
        return new MaterialButton(context, attributeSet);
    }

    @Override // androidx.appcompat.app.g
    protected f d(Context context, AttributeSet attributeSet) {
        return new a(context, attributeSet);
    }

    @Override // androidx.appcompat.app.g
    protected q j(Context context, AttributeSet attributeSet) {
        return new a3.a(context, attributeSet);
    }

    @Override // androidx.appcompat.app.g
    protected w n(Context context, AttributeSet attributeSet) {
        return new MaterialTextView(context, attributeSet);
    }
}
