package com.google.android.material.tabs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.material.R$styleable;
import defpackage.g3;

/* JADX INFO: loaded from: classes.dex */
public class TabItem extends View {
    public final CharSequence b;
    public final Drawable c;
    public final int d;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        g3 g3VarU = g3.u(context, attributeSet, R$styleable.TabItem);
        this.b = g3VarU.p(R$styleable.TabItem_android_text);
        this.c = g3VarU.g(R$styleable.TabItem_android_icon);
        this.d = g3VarU.n(R$styleable.TabItem_android_layout, 0);
        g3VarU.w();
    }
}
