package com.android.settingslib.widget;

import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class d implements Comparable<d> {
    abstract View.OnClickListener a();

    @Nullable
    public abstract CharSequence b();

    abstract int c();

    abstract Drawable d();

    abstract int e();

    abstract CharSequence f();

    @Nullable
    abstract CharSequence g();

    abstract void h(@IntRange(from = 0) int i);
}
