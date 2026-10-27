package com.google.android.material.internal;

import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import java.lang.reflect.Constructor;

/* JADX INFO: loaded from: classes.dex */
final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f3144a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Constructor<StaticLayout> f3145b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Object f3146c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private CharSequence f3147d;
    private final TextPaint e;
    private final int f;
    private int h;
    private boolean l;
    private int g = 0;
    private Layout.Alignment i = Layout.Alignment.ALIGN_NORMAL;
    private int j = Integer.MAX_VALUE;
    private boolean k = true;
    private TextUtils.TruncateAt m = null;

    static class a extends Exception {
        a(Throwable th) {
            super("Error thrown initializing StaticLayout " + th.getMessage(), th);
        }
    }

    private g(CharSequence charSequence, TextPaint textPaint, int i) {
        this.f3147d = charSequence;
        this.e = textPaint;
        this.f = i;
        this.h = charSequence.length();
    }

    private void b() throws a {
        Class<?> cls;
        if (f3144a) {
            return;
        }
        try {
            boolean z = this.l && Build.VERSION.SDK_INT >= 23;
            if (Build.VERSION.SDK_INT >= 18) {
                cls = TextDirectionHeuristic.class;
                f3146c = z ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR;
            } else {
                ClassLoader classLoader = g.class.getClassLoader();
                String str = this.l ? "RTL" : "LTR";
                Class<?> clsLoadClass = classLoader.loadClass("android.text.TextDirectionHeuristic");
                Class<?> clsLoadClass2 = classLoader.loadClass("android.text.TextDirectionHeuristics");
                f3146c = clsLoadClass2.getField(str).get(clsLoadClass2);
                cls = clsLoadClass;
            }
            Class cls2 = Integer.TYPE;
            Class cls3 = Float.TYPE;
            Constructor<StaticLayout> declaredConstructor = StaticLayout.class.getDeclaredConstructor(CharSequence.class, cls2, cls2, TextPaint.class, cls2, Layout.Alignment.class, cls, cls3, cls3, Boolean.TYPE, TextUtils.TruncateAt.class, cls2, cls2);
            f3145b = declaredConstructor;
            declaredConstructor.setAccessible(true);
            f3144a = true;
        } catch (Exception e) {
            throw new a(e);
        }
    }

    public static g c(CharSequence charSequence, TextPaint textPaint, int i) {
        return new g(charSequence, textPaint, i);
    }

    public StaticLayout a() throws a {
        if (this.f3147d == null) {
            this.f3147d = "";
        }
        int iMax = Math.max(0, this.f);
        CharSequence charSequenceEllipsize = this.f3147d;
        if (this.j == 1) {
            charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, this.e, iMax, this.m);
        }
        int iMin = Math.min(charSequenceEllipsize.length(), this.h);
        this.h = iMin;
        if (Build.VERSION.SDK_INT < 23) {
            b();
            try {
                return (StaticLayout) ((Constructor) a.g.j.h.c(f3145b)).newInstance(charSequenceEllipsize, Integer.valueOf(this.g), Integer.valueOf(this.h), this.e, Integer.valueOf(iMax), this.i, a.g.j.h.c(f3146c), Float.valueOf(1.0f), Float.valueOf(0.0f), Boolean.valueOf(this.k), null, Integer.valueOf(iMax), Integer.valueOf(this.j));
            } catch (Exception e) {
                throw new a(e);
            }
        }
        if (this.l) {
            this.i = Layout.Alignment.ALIGN_OPPOSITE;
        }
        StaticLayout.Builder builderObtain = StaticLayout.Builder.obtain(charSequenceEllipsize, this.g, iMin, this.e, iMax);
        builderObtain.setAlignment(this.i);
        builderObtain.setIncludePad(this.k);
        builderObtain.setTextDirection(this.l ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR);
        TextUtils.TruncateAt truncateAt = this.m;
        if (truncateAt != null) {
            builderObtain.setEllipsize(truncateAt);
        }
        builderObtain.setMaxLines(this.j);
        return builderObtain.build();
    }

    public g d(Layout.Alignment alignment) {
        this.i = alignment;
        return this;
    }

    public g e(TextUtils.TruncateAt truncateAt) {
        this.m = truncateAt;
        return this;
    }

    public g f(boolean z) {
        this.k = z;
        return this;
    }

    public g g(boolean z) {
        this.l = z;
        return this;
    }

    public g h(int i) {
        this.j = i;
        return this;
    }
}
