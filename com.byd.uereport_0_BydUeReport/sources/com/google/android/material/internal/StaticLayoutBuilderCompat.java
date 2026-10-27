package com.google.android.material.internal;

import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import java.lang.reflect.Constructor;
import n0.h;

/* JADX INFO: loaded from: classes2.dex */
public final class StaticLayoutBuilderCompat {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f3951a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Constructor<StaticLayout> f3952b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Object f3953c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public CharSequence f3954d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final TextPaint f3955e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f3956f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f3958h;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f3962l;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f3957g = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Layout.Alignment f3959i = Layout.Alignment.ALIGN_NORMAL;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f3960j = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f3961k = true;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public TextUtils.TruncateAt f3963m = null;

    public static class StaticLayoutBuilderCompatException extends Exception {
        public StaticLayoutBuilderCompatException(Throwable th) {
            super("Error thrown initializing StaticLayout " + th.getMessage(), th);
        }
    }

    public StaticLayoutBuilderCompat(CharSequence charSequence, TextPaint textPaint, int i8) {
        this.f3954d = charSequence;
        this.f3955e = textPaint;
        this.f3956f = i8;
        this.f3958h = charSequence.length();
    }

    public static StaticLayoutBuilderCompat c(CharSequence charSequence, TextPaint textPaint, int i8) {
        return new StaticLayoutBuilderCompat(charSequence, textPaint, i8);
    }

    public StaticLayout a() throws StaticLayoutBuilderCompatException {
        if (this.f3954d == null) {
            this.f3954d = "";
        }
        int iMax = Math.max(0, this.f3956f);
        CharSequence charSequenceEllipsize = this.f3954d;
        if (this.f3960j == 1) {
            charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, this.f3955e, iMax, this.f3963m);
        }
        int iMin = Math.min(charSequenceEllipsize.length(), this.f3958h);
        this.f3958h = iMin;
        if (Build.VERSION.SDK_INT < 23) {
            b();
            try {
                return (StaticLayout) ((Constructor) h.e(f3952b)).newInstance(charSequenceEllipsize, Integer.valueOf(this.f3957g), Integer.valueOf(this.f3958h), this.f3955e, Integer.valueOf(iMax), this.f3959i, h.e(f3953c), Float.valueOf(1.0f), Float.valueOf(0.0f), Boolean.valueOf(this.f3961k), null, Integer.valueOf(iMax), Integer.valueOf(this.f3960j));
            } catch (Exception e8) {
                throw new StaticLayoutBuilderCompatException(e8);
            }
        }
        if (this.f3962l) {
            this.f3959i = Layout.Alignment.ALIGN_OPPOSITE;
        }
        StaticLayout.Builder builderObtain = StaticLayout.Builder.obtain(charSequenceEllipsize, this.f3957g, iMin, this.f3955e, iMax);
        builderObtain.setAlignment(this.f3959i);
        builderObtain.setIncludePad(this.f3961k);
        builderObtain.setTextDirection(this.f3962l ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR);
        TextUtils.TruncateAt truncateAt = this.f3963m;
        if (truncateAt != null) {
            builderObtain.setEllipsize(truncateAt);
        }
        builderObtain.setMaxLines(this.f3960j);
        return builderObtain.build();
    }

    public final void b() throws StaticLayoutBuilderCompatException {
        Class<?> cls;
        if (f3951a) {
            return;
        }
        try {
            boolean z7 = this.f3962l && Build.VERSION.SDK_INT >= 23;
            if (Build.VERSION.SDK_INT >= 18) {
                cls = TextDirectionHeuristic.class;
                f3953c = z7 ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR;
            } else {
                ClassLoader classLoader = StaticLayoutBuilderCompat.class.getClassLoader();
                String str = this.f3962l ? "RTL" : "LTR";
                Class<?> clsLoadClass = classLoader.loadClass("android.text.TextDirectionHeuristic");
                Class<?> clsLoadClass2 = classLoader.loadClass("android.text.TextDirectionHeuristics");
                f3953c = clsLoadClass2.getField(str).get(clsLoadClass2);
                cls = clsLoadClass;
            }
            Class cls2 = Integer.TYPE;
            Class cls3 = Float.TYPE;
            Constructor<StaticLayout> declaredConstructor = StaticLayout.class.getDeclaredConstructor(CharSequence.class, cls2, cls2, TextPaint.class, cls2, Layout.Alignment.class, cls, cls3, cls3, Boolean.TYPE, TextUtils.TruncateAt.class, cls2, cls2);
            f3952b = declaredConstructor;
            declaredConstructor.setAccessible(true);
            f3951a = true;
        } catch (Exception e8) {
            throw new StaticLayoutBuilderCompatException(e8);
        }
    }

    public StaticLayoutBuilderCompat d(Layout.Alignment alignment) {
        this.f3959i = alignment;
        return this;
    }

    public StaticLayoutBuilderCompat e(TextUtils.TruncateAt truncateAt) {
        this.f3963m = truncateAt;
        return this;
    }

    public StaticLayoutBuilderCompat f(boolean z7) {
        this.f3961k = z7;
        return this;
    }

    public StaticLayoutBuilderCompat g(boolean z7) {
        this.f3962l = z7;
        return this;
    }

    public StaticLayoutBuilderCompat h(int i8) {
        this.f3960j = i8;
        return this;
    }
}
