package V6;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.TextPaint;
import android.text.style.LeadingMarginSpan;
import android.text.style.MetricAffectingSpan;

/* JADX INFO: loaded from: classes.dex */
public final class c extends MetricAffectingSpan implements LeadingMarginSpan {
    public final T6.f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Rect f9537m = g.f9543a;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Paint f9538n = g.c;

    public c(T6.f fVar) {
        this.l = fVar;
    }

    @Override // android.text.style.LeadingMarginSpan
    public final void drawLeadingMargin(Canvas canvas, Paint paint, int i7, int i10, int i11, int i12, int i13, CharSequence charSequence, int i14, int i15, boolean z6, Layout layout) {
        int width;
        Paint paint2 = this.f9538n;
        paint2.setStyle(Paint.Style.FILL);
        this.l.getClass();
        paint2.setColor(w0.c.E(paint.getColor(), 25));
        if (i10 > 0) {
            width = canvas.getWidth();
        } else {
            i7 -= canvas.getWidth();
            width = i7;
        }
        Rect rect = this.f9537m;
        rect.set(i7, i11, width, i13);
        canvas.drawRect(rect, paint2);
    }

    @Override // android.text.style.LeadingMarginSpan
    public final int getLeadingMargin(boolean z6) {
        return this.l.d;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        this.l.getClass();
        textPaint.setTypeface(Typeface.MONOSPACE);
        textPaint.setTextSize(textPaint.getTextSize() * 0.87f);
    }

    @Override // android.text.style.MetricAffectingSpan
    public final void updateMeasureState(TextPaint textPaint) {
        this.l.getClass();
        textPaint.setTypeface(Typeface.MONOSPACE);
        textPaint.setTextSize(textPaint.getTextSize() * 0.87f);
    }
}
