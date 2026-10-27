package com.google.android.material.tabs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.f0;
import d.b.a.a.l;

/* JADX INFO: loaded from: classes.dex */
public class TabItem extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CharSequence f4241b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Drawable f4242c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f4243d;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        f0 f0VarU = f0.u(context, attributeSet, l.TabItem);
        this.f4241b = f0VarU.p(l.TabItem_android_text);
        this.f4242c = f0VarU.g(l.TabItem_android_icon);
        this.f4243d = f0VarU.n(l.TabItem_android_layout, 0);
        f0VarU.w();
    }
}
