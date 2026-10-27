package com.google.android.material.internal;

import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import java.lang.reflect.Constructor;
import k0.h;

/* JADX INFO: loaded from: classes.dex */
public final class StaticLayoutBuilderCompat {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f2370a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Constructor<StaticLayout> f2371b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Object f2372c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public CharSequence f2373d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final TextPaint f2374e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f2375f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2377h;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f2381l;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2376g = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Layout.Alignment f2378i = Layout.Alignment.ALIGN_NORMAL;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2379j = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f2380k = true;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public TextUtils.TruncateAt f2382m = null;

    public static class StaticLayoutBuilderCompatException extends Exception {
        public StaticLayoutBuilderCompatException(Throwable th) {
            super("Error thrown initializing StaticLayout " + th.getMessage(), th);
        }
    }

    public StaticLayoutBuilderCompat(CharSequence charSequence, TextPaint textPaint, int i3) {
        this.f2373d = charSequence;
        this.f2374e = textPaint;
        this.f2375f = i3;
        this.f2377h = charSequence.length();
    }

    public static StaticLayoutBuilderCompat c(CharSequence charSequence, TextPaint textPaint, int i3) {
        return new StaticLayoutBuilderCompat(charSequence, textPaint, i3);
    }

    public StaticLayout a() throws StaticLayoutBuilderCompatException {
        if (this.f2373d == null) {
            this.f2373d = "";
        }
        int iMax = Math.max(0, this.f2375f);
        CharSequence charSequenceEllipsize = this.f2373d;
        if (this.f2379j == 1) {
            charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, this.f2374e, iMax, this.f2382m);
        }
        int iMin = Math.min(charSequenceEllipsize.length(), this.f2377h);
        this.f2377h = iMin;
        if (Build.VERSION.SDK_INT < 23) {
            b();
            try {
                return (StaticLayout) ((Constructor) h.c(f2371b)).newInstance(charSequenceEllipsize, Integer.valueOf(this.f2376g), Integer.valueOf(this.f2377h), this.f2374e, Integer.valueOf(iMax), this.f2378i, h.c(f2372c), Float.valueOf(1.0f), Float.valueOf(0.0f), Boolean.valueOf(this.f2380k), null, Integer.valueOf(iMax), Integer.valueOf(this.f2379j));
            } catch (Exception e3) {
                throw new StaticLayoutBuilderCompatException(e3);
            }
        }
        if (this.f2381l) {
            this.f2378i = Layout.Alignment.ALIGN_OPPOSITE;
        }
        StaticLayout.Builder builderObtain = StaticLayout.Builder.obtain(charSequenceEllipsize, this.f2376g, iMin, this.f2374e, iMax);
        builderObtain.setAlignment(this.f2378i);
        builderObtain.setIncludePad(this.f2380k);
        builderObtain.setTextDirection(this.f2381l ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR);
        TextUtils.TruncateAt truncateAt = this.f2382m;
        if (truncateAt != null) {
            builderObtain.setEllipsize(truncateAt);
        }
        builderObtain.setMaxLines(this.f2379j);
        return builderObtain.build();
    }

    public final void b() throws StaticLayoutBuilderCompatException {
        Class<?> cls;
        if (f2370a) {
            return;
        }
        try {
            boolean z3 = this.f2381l && Build.VERSION.SDK_INT >= 23;
            if (Build.VERSION.SDK_INT >= 18) {
                cls = TextDirectionHeuristic.class;
                f2372c = z3 ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR;
            } else {
                ClassLoader classLoader = StaticLayoutBuilderCompat.class.getClassLoader();
                String str = this.f2381l ? "RTL" : "LTR";
                Class<?> clsLoadClass = classLoader.loadClass("android.text.TextDirectionHeuristic");
                Class<?> clsLoadClass2 = classLoader.loadClass("android.text.TextDirectionHeuristics");
                f2372c = clsLoadClass2.getField(str).get(clsLoadClass2);
                cls = clsLoadClass;
            }
            Class cls2 = Integer.TYPE;
            Class cls3 = Float.TYPE;
            Constructor<StaticLayout> declaredConstructor = StaticLayout.class.getDeclaredConstructor(CharSequence.class, cls2, cls2, TextPaint.class, cls2, Layout.Alignment.class, cls, cls3, cls3, Boolean.TYPE, TextUtils.TruncateAt.class, cls2, cls2);
            f2371b = declaredConstructor;
            declaredConstructor.setAccessible(true);
            f2370a = true;
        } catch (Exception e3) {
            throw new StaticLayoutBuilderCompatException(e3);
        }
    }

    public StaticLayoutBuilderCompat d(Layout.Alignment alignment) {
        this.f2378i = alignment;
        return this;
    }

    public StaticLayoutBuilderCompat e(TextUtils.TruncateAt truncateAt) {
        this.f2382m = truncateAt;
        return this;
    }

    public StaticLayoutBuilderCompat f(boolean z3) {
        this.f2380k = z3;
        return this;
    }

    public StaticLayoutBuilderCompat g(boolean z3) {
        this.f2381l = z3;
        return this;
    }

    public StaticLayoutBuilderCompat h(int i3) {
        this.f2379j = i3;
        return this;
    }
}
