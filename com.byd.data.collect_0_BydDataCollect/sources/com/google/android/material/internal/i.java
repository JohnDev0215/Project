package com.google.android.material.internal;

import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import java.lang.reflect.Constructor;

/* JADX INFO: compiled from: StaticLayoutBuilderCompat.java */
/* JADX INFO: loaded from: classes.dex */
final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f4151a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Constructor<StaticLayout> f4152b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Object f4153c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private CharSequence f4154d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final TextPaint f4155e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f4156f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f4158h;
    private boolean l;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f4157g = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Layout.Alignment f4159i = Layout.Alignment.ALIGN_NORMAL;
    private int j = Integer.MAX_VALUE;
    private boolean k = true;
    private TextUtils.TruncateAt m = null;

    /* JADX INFO: compiled from: StaticLayoutBuilderCompat.java */
    static class a extends Exception {
        a(Throwable th) {
            super("Error thrown initializing StaticLayout " + th.getMessage(), th);
        }
    }

    private i(CharSequence charSequence, TextPaint textPaint, int i2) {
        this.f4154d = charSequence;
        this.f4155e = textPaint;
        this.f4156f = i2;
        this.f4158h = charSequence.length();
    }

    private void b() throws a {
        Class<?> cls;
        if (f4151a) {
            return;
        }
        try {
            boolean z = this.l && Build.VERSION.SDK_INT >= 23;
            if (Build.VERSION.SDK_INT >= 18) {
                cls = TextDirectionHeuristic.class;
                f4153c = z ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR;
            } else {
                ClassLoader classLoader = i.class.getClassLoader();
                String str = this.l ? "RTL" : "LTR";
                Class<?> clsLoadClass = classLoader.loadClass("android.text.TextDirectionHeuristic");
                Class<?> clsLoadClass2 = classLoader.loadClass("android.text.TextDirectionHeuristics");
                f4153c = clsLoadClass2.getField(str).get(clsLoadClass2);
                cls = clsLoadClass;
            }
            Class cls2 = Integer.TYPE;
            Class cls3 = Float.TYPE;
            Constructor<StaticLayout> declaredConstructor = StaticLayout.class.getDeclaredConstructor(CharSequence.class, cls2, cls2, TextPaint.class, cls2, Layout.Alignment.class, cls, cls3, cls3, Boolean.TYPE, TextUtils.TruncateAt.class, cls2, cls2);
            f4152b = declaredConstructor;
            declaredConstructor.setAccessible(true);
            f4151a = true;
        } catch (Exception e2) {
            throw new a(e2);
        }
    }

    public static i c(CharSequence charSequence, TextPaint textPaint, int i2) {
        return new i(charSequence, textPaint, i2);
    }

    public StaticLayout a() throws a {
        if (this.f4154d == null) {
            this.f4154d = "";
        }
        int iMax = Math.max(0, this.f4156f);
        CharSequence charSequenceEllipsize = this.f4154d;
        if (this.j == 1) {
            charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, this.f4155e, iMax, this.m);
        }
        int iMin = Math.min(charSequenceEllipsize.length(), this.f4158h);
        this.f4158h = iMin;
        if (Build.VERSION.SDK_INT < 23) {
            b();
            try {
                return (StaticLayout) ((Constructor) b.g.j.h.c(f4152b)).newInstance(charSequenceEllipsize, Integer.valueOf(this.f4157g), Integer.valueOf(this.f4158h), this.f4155e, Integer.valueOf(iMax), this.f4159i, b.g.j.h.c(f4153c), Float.valueOf(1.0f), Float.valueOf(0.0f), Boolean.valueOf(this.k), null, Integer.valueOf(iMax), Integer.valueOf(this.j));
            } catch (Exception e2) {
                throw new a(e2);
            }
        }
        if (this.l) {
            this.f4159i = Layout.Alignment.ALIGN_OPPOSITE;
        }
        StaticLayout.Builder builderObtain = StaticLayout.Builder.obtain(charSequenceEllipsize, this.f4157g, iMin, this.f4155e, iMax);
        builderObtain.setAlignment(this.f4159i);
        builderObtain.setIncludePad(this.k);
        builderObtain.setTextDirection(this.l ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR);
        TextUtils.TruncateAt truncateAt = this.m;
        if (truncateAt != null) {
            builderObtain.setEllipsize(truncateAt);
        }
        builderObtain.setMaxLines(this.j);
        return builderObtain.build();
    }

    public i d(Layout.Alignment alignment) {
        this.f4159i = alignment;
        return this;
    }

    public i e(TextUtils.TruncateAt truncateAt) {
        this.m = truncateAt;
        return this;
    }

    public i f(boolean z) {
        this.k = z;
        return this;
    }

    public i g(boolean z) {
        this.l = z;
        return this;
    }

    public i h(int i2) {
        this.j = i2;
        return this;
    }
}
