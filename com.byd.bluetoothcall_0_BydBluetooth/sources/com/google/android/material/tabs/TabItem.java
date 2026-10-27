package com.google.android.material.tabs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.e0;
import c.b.a.a.l;

/* JADX INFO: loaded from: classes.dex */
public class TabItem extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CharSequence f3996b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Drawable f3997c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f3998d;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        e0 e0VarU = e0.u(context, attributeSet, l.O5);
        this.f3996b = e0VarU.p(l.R5);
        this.f3997c = e0VarU.g(l.P5);
        this.f3998d = e0VarU.n(l.Q5, 0);
        e0VarU.w();
    }
}
