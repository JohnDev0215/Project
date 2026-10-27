package com.google.android.material.internal;

import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import b.h.l.g;
import java.lang.reflect.Constructor;

/* JADX INFO: loaded from: classes.dex */
public final class StaticLayoutBuilderCompat {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f4775a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Constructor<StaticLayout> f4776b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Object f4777c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public CharSequence f4778d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final TextPaint f4779e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f4780f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4782h;
    public boolean l;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4781g = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Layout.Alignment f4783i = Layout.Alignment.ALIGN_NORMAL;
    public int j = Integer.MAX_VALUE;
    public boolean k = true;
    public TextUtils.TruncateAt m = null;

    public static class StaticLayoutBuilderCompatException extends Exception {
        public StaticLayoutBuilderCompatException(Throwable th) {
            super("Error thrown initializing StaticLayout " + th.getMessage(), th);
        }
    }

    public StaticLayoutBuilderCompat(CharSequence charSequence, TextPaint textPaint, int i2) {
        this.f4778d = charSequence;
        this.f4779e = textPaint;
        this.f4780f = i2;
        this.f4782h = charSequence.length();
    }

    public static StaticLayoutBuilderCompat c(CharSequence charSequence, TextPaint textPaint, int i2) {
        return new StaticLayoutBuilderCompat(charSequence, textPaint, i2);
    }

    public StaticLayout a() throws StaticLayoutBuilderCompatException {
        if (this.f4778d == null) {
            this.f4778d = "";
        }
        int iMax = Math.max(0, this.f4780f);
        CharSequence charSequenceEllipsize = this.f4778d;
        if (this.j == 1) {
            charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, this.f4779e, iMax, this.m);
        }
        int iMin = Math.min(charSequenceEllipsize.length(), this.f4782h);
        this.f4782h = iMin;
        if (Build.VERSION.SDK_INT < 23) {
            b();
            try {
                return (StaticLayout) ((Constructor) g.c(f4776b)).newInstance(charSequenceEllipsize, Integer.valueOf(this.f4781g), Integer.valueOf(this.f4782h), this.f4779e, Integer.valueOf(iMax), this.f4783i, g.c(f4777c), Float.valueOf(1.0f), Float.valueOf(0.0f), Boolean.valueOf(this.k), null, Integer.valueOf(iMax), Integer.valueOf(this.j));
            } catch (Exception e2) {
                throw new StaticLayoutBuilderCompatException(e2);
            }
        }
        if (this.l) {
            this.f4783i = Layout.Alignment.ALIGN_OPPOSITE;
        }
        StaticLayout.Builder builderObtain = StaticLayout.Builder.obtain(charSequenceEllipsize, this.f4781g, iMin, this.f4779e, iMax);
        builderObtain.setAlignment(this.f4783i);
        builderObtain.setIncludePad(this.k);
        builderObtain.setTextDirection(this.l ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR);
        TextUtils.TruncateAt truncateAt = this.m;
        if (truncateAt != null) {
            builderObtain.setEllipsize(truncateAt);
        }
        builderObtain.setMaxLines(this.j);
        return builderObtain.build();
    }

    public final void b() throws StaticLayoutBuilderCompatException {
        Class<?> cls;
        if (f4775a) {
            return;
        }
        try {
            boolean z = this.l && Build.VERSION.SDK_INT >= 23;
            if (Build.VERSION.SDK_INT >= 18) {
                cls = TextDirectionHeuristic.class;
                f4777c = z ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR;
            } else {
                ClassLoader classLoader = StaticLayoutBuilderCompat.class.getClassLoader();
                String str = this.l ? "RTL" : "LTR";
                Class<?> clsLoadClass = classLoader.loadClass("android.text.TextDirectionHeuristic");
                Class<?> clsLoadClass2 = classLoader.loadClass("android.text.TextDirectionHeuristics");
                f4777c = clsLoadClass2.getField(str).get(clsLoadClass2);
                cls = clsLoadClass;
            }
            Class cls2 = Integer.TYPE;
            Class cls3 = Float.TYPE;
            Constructor<StaticLayout> declaredConstructor = StaticLayout.class.getDeclaredConstructor(CharSequence.class, cls2, cls2, TextPaint.class, cls2, Layout.Alignment.class, cls, cls3, cls3, Boolean.TYPE, TextUtils.TruncateAt.class, cls2, cls2);
            f4776b = declaredConstructor;
            declaredConstructor.setAccessible(true);
            f4775a = true;
        } catch (Exception e2) {
            throw new StaticLayoutBuilderCompatException(e2);
        }
    }

    public StaticLayoutBuilderCompat d(Layout.Alignment alignment) {
        this.f4783i = alignment;
        return this;
    }

    public StaticLayoutBuilderCompat e(TextUtils.TruncateAt truncateAt) {
        this.m = truncateAt;
        return this;
    }

    public StaticLayoutBuilderCompat f(boolean z) {
        this.k = z;
        return this;
    }

    public StaticLayoutBuilderCompat g(boolean z) {
        this.l = z;
        return this;
    }

    public StaticLayoutBuilderCompat h(int i2) {
        this.j = i2;
        return this;
    }
}
