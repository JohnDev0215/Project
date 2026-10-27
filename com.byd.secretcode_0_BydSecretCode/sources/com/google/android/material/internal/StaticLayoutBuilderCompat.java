package com.google.android.material.internal;

import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import defpackage.z8;
import java.lang.reflect.Constructor;

/* JADX INFO: loaded from: classes.dex */
public final class StaticLayoutBuilderCompat {
    public static boolean a;
    public static Constructor<StaticLayout> b;
    public static Object c;
    public CharSequence d;
    public final TextPaint e;
    public final int f;
    public int h;
    public boolean l;
    public int g = 0;
    public Layout.Alignment i = Layout.Alignment.ALIGN_NORMAL;
    public int j = Integer.MAX_VALUE;
    public boolean k = true;
    public TextUtils.TruncateAt m = null;

    public static class StaticLayoutBuilderCompatException extends Exception {
        public StaticLayoutBuilderCompatException(Throwable th) {
            super("Error thrown initializing StaticLayout " + th.getMessage(), th);
        }
    }

    public StaticLayoutBuilderCompat(CharSequence charSequence, TextPaint textPaint, int i) {
        this.d = charSequence;
        this.e = textPaint;
        this.f = i;
        this.h = charSequence.length();
    }

    public static StaticLayoutBuilderCompat c(CharSequence charSequence, TextPaint textPaint, int i) {
        return new StaticLayoutBuilderCompat(charSequence, textPaint, i);
    }

    public StaticLayout a() throws StaticLayoutBuilderCompatException {
        if (this.d == null) {
            this.d = "";
        }
        int iMax = Math.max(0, this.f);
        CharSequence charSequenceEllipsize = this.d;
        if (this.j == 1) {
            charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, this.e, iMax, this.m);
        }
        int iMin = Math.min(charSequenceEllipsize.length(), this.h);
        this.h = iMin;
        if (Build.VERSION.SDK_INT < 23) {
            b();
            try {
                return (StaticLayout) ((Constructor) z8.c(b)).newInstance(charSequenceEllipsize, Integer.valueOf(this.g), Integer.valueOf(this.h), this.e, Integer.valueOf(iMax), this.i, z8.c(c), Float.valueOf(1.0f), Float.valueOf(0.0f), Boolean.valueOf(this.k), null, Integer.valueOf(iMax), Integer.valueOf(this.j));
            } catch (Exception e) {
                throw new StaticLayoutBuilderCompatException(e);
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

    public final void b() throws StaticLayoutBuilderCompatException {
        Class<?> cls;
        if (a) {
            return;
        }
        try {
            boolean z = this.l && Build.VERSION.SDK_INT >= 23;
            if (Build.VERSION.SDK_INT >= 18) {
                cls = TextDirectionHeuristic.class;
                c = z ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR;
            } else {
                ClassLoader classLoader = StaticLayoutBuilderCompat.class.getClassLoader();
                String str = this.l ? "RTL" : "LTR";
                Class<?> clsLoadClass = classLoader.loadClass("android.text.TextDirectionHeuristic");
                Class<?> clsLoadClass2 = classLoader.loadClass("android.text.TextDirectionHeuristics");
                c = clsLoadClass2.getField(str).get(clsLoadClass2);
                cls = clsLoadClass;
            }
            Class cls2 = Integer.TYPE;
            Class cls3 = Float.TYPE;
            Constructor<StaticLayout> declaredConstructor = StaticLayout.class.getDeclaredConstructor(CharSequence.class, cls2, cls2, TextPaint.class, cls2, Layout.Alignment.class, cls, cls3, cls3, Boolean.TYPE, TextUtils.TruncateAt.class, cls2, cls2);
            b = declaredConstructor;
            declaredConstructor.setAccessible(true);
            a = true;
        } catch (Exception e) {
            throw new StaticLayoutBuilderCompatException(e);
        }
    }

    public StaticLayoutBuilderCompat d(Layout.Alignment alignment) {
        this.i = alignment;
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

    public StaticLayoutBuilderCompat h(int i) {
        this.j = i;
        return this;
    }
}
