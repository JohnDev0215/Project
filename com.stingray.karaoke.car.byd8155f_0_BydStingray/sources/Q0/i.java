package Q0;

import A7.m;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

/* JADX INFO: loaded from: classes.dex */
public final class i extends ReplacementSpan {
    public Paint.FontMetricsInt l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f6101m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f6102n;
    public boolean o;

    public final Paint.FontMetricsInt a() {
        Paint.FontMetricsInt fontMetricsInt = this.l;
        if (fontMetricsInt != null) {
            return fontMetricsInt;
        }
        m.j("fontMetrics");
        throw null;
    }

    public final int b() {
        if (this.o) {
            return this.f6102n;
        }
        throw new IllegalStateException("PlaceholderSpan is not laid out yet.");
    }

    @Override // android.text.style.ReplacementSpan
    public final void draw(Canvas canvas, CharSequence charSequence, int i7, int i10, float f10, int i11, int i12, int i13, Paint paint) {
    }

    @Override // android.text.style.ReplacementSpan
    public final int getSize(Paint paint, CharSequence charSequence, int i7, int i10, Paint.FontMetricsInt fontMetricsInt) {
        this.o = true;
        paint.getTextSize();
        this.l = paint.getFontMetricsInt();
        if (a().descent <= a().ascent) {
            throw new IllegalArgumentException("Invalid fontMetrics: line height can not be negative.");
        }
        this.f6101m = (int) Math.ceil(0.0f);
        this.f6102n = (int) Math.ceil(0.0f);
        if (fontMetricsInt != null) {
            fontMetricsInt.ascent = a().ascent;
            fontMetricsInt.descent = a().descent;
            fontMetricsInt.leading = a().leading;
            if (fontMetricsInt.ascent > (-b())) {
                fontMetricsInt.ascent = -b();
            }
            fontMetricsInt.top = Math.min(a().top, fontMetricsInt.ascent);
            fontMetricsInt.bottom = Math.max(a().bottom, fontMetricsInt.descent);
        }
        if (this.o) {
            return this.f6101m;
        }
        throw new IllegalStateException("PlaceholderSpan is not laid out yet.");
    }
}
