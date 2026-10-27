package com.google.android.material.tabs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import b.b.q.e0;
import c.b.a.a.l;

/* JADX INFO: loaded from: classes.dex */
public class TabItem extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CharSequence f4826b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Drawable f4827c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f4828d;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        e0 e0VarU = e0.u(context, attributeSet, l.TabItem);
        this.f4826b = e0VarU.p(l.TabItem_android_text);
        this.f4827c = e0VarU.g(l.TabItem_android_icon);
        this.f4828d = e0VarU.n(l.TabItem_android_layout, 0);
        e0VarU.w();
    }
}
