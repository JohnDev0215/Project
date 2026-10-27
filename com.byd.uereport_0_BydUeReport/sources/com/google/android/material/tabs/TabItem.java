package com.google.android.material.tabs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.material.R$styleable;
import r.e0;

/* JADX INFO: loaded from: classes2.dex */
public class TabItem extends View {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final CharSequence f4031d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Drawable f4032e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f4033f;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        e0 e0VarU = e0.u(context, attributeSet, R$styleable.TabItem);
        this.f4031d = e0VarU.p(R$styleable.TabItem_android_text);
        this.f4032e = e0VarU.g(R$styleable.TabItem_android_icon);
        this.f4033f = e0VarU.n(R$styleable.TabItem_android_layout, 0);
        e0VarU.w();
    }
}
