package com.google.android.material.internal;

import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;

/* JADX INFO: compiled from: StaticLayoutBuilderCompat.java */
/* JADX INFO: loaded from: classes.dex */
final class g {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    static final int f4643n = 1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private CharSequence f4644a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final TextPaint f4645b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f4646c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4648e;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f4655l;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f4647d = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Layout.Alignment f4649f = Layout.Alignment.ALIGN_NORMAL;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f4650g = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f4651h = 0.0f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private float f4652i = 1.0f;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f4653j = f4643n;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f4654k = true;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private TextUtils.TruncateAt f4656m = null;

    /* JADX INFO: compiled from: StaticLayoutBuilderCompat.java */
    static class a extends Exception {
    }

    private g(CharSequence charSequence, TextPaint textPaint, int i7) {
        this.f4644a = charSequence;
        this.f4645b = textPaint;
        this.f4646c = i7;
        this.f4648e = charSequence.length();
    }

    public static g b(CharSequence charSequence, TextPaint textPaint, int i7) {
        return new g(charSequence, textPaint, i7);
    }

    public StaticLayout a() {
        if (this.f4644a == null) {
            this.f4644a = "";
        }
        int iMax = Math.max(0, this.f4646c);
        CharSequence charSequenceEllipsize = this.f4644a;
        if (this.f4650g == 1) {
            charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, this.f4645b, iMax, this.f4656m);
        }
        int iMin = Math.min(charSequenceEllipsize.length(), this.f4648e);
        this.f4648e = iMin;
        if (this.f4655l && this.f4650g == 1) {
            this.f4649f = Layout.Alignment.ALIGN_OPPOSITE;
        }
        StaticLayout.Builder builderObtain = StaticLayout.Builder.obtain(charSequenceEllipsize, this.f4647d, iMin, this.f4645b, iMax);
        builderObtain.setAlignment(this.f4649f);
        builderObtain.setIncludePad(this.f4654k);
        builderObtain.setTextDirection(this.f4655l ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR);
        TextUtils.TruncateAt truncateAt = this.f4656m;
        if (truncateAt != null) {
            builderObtain.setEllipsize(truncateAt);
        }
        builderObtain.setMaxLines(this.f4650g);
        float f7 = this.f4651h;
        if (f7 != 0.0f || this.f4652i != 1.0f) {
            builderObtain.setLineSpacing(f7, this.f4652i);
        }
        if (this.f4650g > 1) {
            builderObtain.setHyphenationFrequency(this.f4653j);
        }
        return builderObtain.build();
    }

    public g c(Layout.Alignment alignment) {
        this.f4649f = alignment;
        return this;
    }

    public g d(TextUtils.TruncateAt truncateAt) {
        this.f4656m = truncateAt;
        return this;
    }

    public g e(int i7) {
        this.f4653j = i7;
        return this;
    }

    public g f(boolean z6) {
        this.f4654k = z6;
        return this;
    }

    public g g(boolean z6) {
        this.f4655l = z6;
        return this;
    }

    public g h(float f7, float f8) {
        this.f4651h = f7;
        this.f4652i = f8;
        return this;
    }

    public g i(int i7) {
        this.f4650g = i7;
        return this;
    }
}
