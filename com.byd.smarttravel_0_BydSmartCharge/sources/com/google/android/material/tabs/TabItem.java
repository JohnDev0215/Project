package com.google.android.material.tabs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import b.b.f.e0;
import com.google.android.material.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class TabItem extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CharSequence f2234b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Drawable f2235c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2236d;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        e0 e0VarU = e0.u(context, attributeSet, R$styleable.TabItem);
        this.f2234b = e0VarU.p(R$styleable.TabItem_android_text);
        this.f2235c = e0VarU.g(R$styleable.TabItem_android_icon);
        this.f2236d = e0VarU.n(R$styleable.TabItem_android_layout, 0);
        e0VarU.w();
    }
}
