package com.google.android.material.tabs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.material.R$styleable;
import o.e0;

/* JADX INFO: loaded from: classes.dex */
public class TabItem extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CharSequence f2452b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Drawable f2453c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2454d;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        e0 e0VarU = e0.u(context, attributeSet, R$styleable.TabItem);
        this.f2452b = e0VarU.p(R$styleable.TabItem_android_text);
        this.f2453c = e0VarU.g(R$styleable.TabItem_android_icon);
        this.f2454d = e0VarU.n(R$styleable.TabItem_android_layout, 0);
        e0VarU.w();
    }
}
