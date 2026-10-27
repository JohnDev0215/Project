package x6;

import A7.m;
import android.graphics.Paint;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f23799a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f23800b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f23801e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f23802f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f23803g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f23804h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f23805i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f23806j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f23807k;
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f23808m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f23809n;

    public d(int i7, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22) {
        this.f23799a = i7;
        this.f23800b = i10;
        this.c = i11;
        this.d = i12;
        this.f23801e = i13;
        this.f23802f = i14;
        this.f23803g = i15;
        this.f23804h = i16;
        this.f23805i = i17;
        this.f23806j = i18;
        this.f23807k = i19;
        this.l = i20;
        this.f23808m = i21;
        this.f23809n = i22;
    }

    public final a a(float f10, float f11, int i7, Paint paint, String str, float f12) {
        m.f(paint, "paint");
        m.f(str, "longestLineText");
        Paint paint2 = new Paint(paint);
        float f13 = (f11 - ((i7 + 1) * this.d)) / i7;
        paint2.setTextSize(f13);
        Paint.FontMetrics fontMetrics = paint2.getFontMetrics();
        float f14 = fontMetrics.descent - fontMetrics.ascent;
        paint2.setTextSize(f14 > f13 ? (f13 * f13) / f14 : f13);
        float fMeasureText = paint2.measureText(str);
        if (fMeasureText > f10) {
            float f15 = f10 / fMeasureText;
            f13 *= f15;
            paint2.setTextSize(paint2.getTextSize() * f15);
        }
        float textSize = paint2.getTextSize();
        if (textSize > f12) {
            paint2.setTextSize(f12);
            Paint.FontMetrics fontMetrics2 = paint2.getFontMetrics();
            f13 = ((fontMetrics2.descent - fontMetrics2.ascent) * f12) / f12;
        } else {
            f12 = textSize;
        }
        return new a(f13, f12);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return this.f23799a == dVar.f23799a && this.f23800b == dVar.f23800b && this.c == dVar.c && this.d == dVar.d && this.f23801e == dVar.f23801e && this.f23802f == dVar.f23802f && this.f23803g == dVar.f23803g && this.f23804h == dVar.f23804h && this.f23805i == dVar.f23805i && this.f23806j == dVar.f23806j && this.f23807k == dVar.f23807k && this.l == dVar.l && this.f23808m == dVar.f23808m && this.f23809n == dVar.f23809n;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f23809n) + AbstractC2641j.b(this.f23808m, AbstractC2641j.b(this.l, AbstractC2641j.b(this.f23807k, AbstractC2641j.b(this.f23806j, AbstractC2641j.b(this.f23805i, AbstractC2641j.b(this.f23804h, AbstractC2641j.b(this.f23803g, AbstractC2641j.b(this.f23802f, AbstractC2641j.b(this.f23801e, AbstractC2641j.b(this.d, AbstractC2641j.b(this.c, AbstractC2641j.b(this.f23800b, Integer.hashCode(this.f23799a) * 31, 31), 31), 31), 31), 31), 31), 31), 31), 31), 31), 31), 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("LyricsLayoutConfig(defaultLineCount=");
        sb.append(this.f23799a);
        sb.append(", minLineCount=");
        sb.append(this.f23800b);
        sb.append(", maxLineCount=");
        sb.append(this.c);
        sb.append(", lineVerticalMargin=");
        sb.append(this.d);
        sb.append(", lineHorizontalMargin=");
        sb.append(this.f23801e);
        sb.append(", singerViewDuetMargin=");
        sb.append(this.f23802f);
        sb.append(", singerViewSoloMargin=");
        sb.append(this.f23803g);
        sb.append(", breakBarTopMargin=");
        sb.append(this.f23804h);
        sb.append(", breakBarHeightSolo=");
        sb.append(this.f23805i);
        sb.append(", breakBarHeightDuet=");
        sb.append(this.f23806j);
        sb.append(", defaultLineHeight=");
        sb.append(this.f23807k);
        sb.append(", minLineHeight=");
        sb.append(this.l);
        sb.append(", duetTextBoxMargin=");
        sb.append(this.f23808m);
        sb.append(", nbSingerForGrid=");
        return M5.b.h(sb, this.f23809n, ")");
    }
}
