package Q0;

import android.graphics.Paint;
import android.text.style.LineHeightSpan;

/* JADX INFO: loaded from: classes.dex */
public final class h implements LineHeightSpan {
    public final float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f6092m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f6093n;
    public final boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final float f6094p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f6095q = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f6096r = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f6097s = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f6098t = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f6099u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f6100v;

    public h(float f10, int i7, boolean z6, boolean z10, float f11) {
        this.l = f10;
        this.f6092m = i7;
        this.f6093n = z6;
        this.o = z10;
        this.f6094p = f11;
        if ((0.0f > f11 || f11 > 1.0f) && f11 != -1.0f) {
            throw new IllegalStateException("topRatio should be in [0..1] range or -1");
        }
    }

    @Override // android.text.style.LineHeightSpan
    public final void chooseHeight(CharSequence charSequence, int i7, int i10, int i11, int i12, Paint.FontMetricsInt fontMetricsInt) {
        int i13 = fontMetricsInt.descent;
        int i14 = fontMetricsInt.ascent;
        if (i13 - i14 <= 0) {
            return;
        }
        boolean z6 = i7 == 0;
        boolean z10 = i10 == this.f6092m;
        boolean z11 = this.o;
        boolean z12 = this.f6093n;
        if (z6 && z10 && z12 && z11) {
            return;
        }
        if (this.f6095q == Integer.MIN_VALUE) {
            int i15 = i13 - i14;
            int iCeil = (int) Math.ceil(this.l);
            int i16 = iCeil - i15;
            float fAbs = this.f6094p;
            if (fAbs == -1.0f) {
                fAbs = Math.abs(fontMetricsInt.ascent) / (fontMetricsInt.descent - fontMetricsInt.ascent);
            }
            int iCeil2 = (int) (i16 <= 0 ? Math.ceil(i16 * fAbs) : Math.ceil((1.0f - fAbs) * i16));
            int i17 = fontMetricsInt.descent;
            int i18 = iCeil2 + i17;
            this.f6097s = i18;
            int i19 = i18 - iCeil;
            this.f6096r = i19;
            if (z12) {
                i19 = fontMetricsInt.ascent;
            }
            this.f6095q = i19;
            if (z11) {
                i18 = i17;
            }
            this.f6098t = i18;
            this.f6099u = fontMetricsInt.ascent - i19;
            this.f6100v = i18 - i17;
        }
        fontMetricsInt.ascent = z6 ? this.f6095q : this.f6096r;
        fontMetricsInt.descent = z10 ? this.f6098t : this.f6097s;
    }
}
