package V6;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.Layout;
import android.text.Spanned;
import android.text.style.LeadingMarginSpan;

/* JADX INFO: loaded from: classes.dex */
public final class b implements LeadingMarginSpan {
    public final T6.f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Paint f9534m = g.c;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final RectF f9535n = g.f9544b;
    public final Rect o = g.f9543a;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f9536p;

    public b(T6.f fVar, int i7) {
        this.l = fVar;
        this.f9536p = i7;
    }

    @Override // android.text.style.LeadingMarginSpan
    public final void drawLeadingMargin(Canvas canvas, Paint paint, int i7, int i10, int i11, int i12, int i13, CharSequence charSequence, int i14, int i15, boolean z6, Layout layout) {
        Rect rect = this.o;
        if (z6 && (charSequence instanceof Spanned) && ((Spanned) charSequence).getSpanStart(this) == i14) {
            Paint paint2 = this.f9534m;
            paint2.set(paint);
            T6.f fVar = this.l;
            fVar.getClass();
            int i16 = fVar.f8756a;
            paint2.setColor(paint2.getColor());
            int i17 = fVar.c;
            if (i17 != 0) {
                paint2.setStrokeWidth(i17);
            }
            int iSave = canvas.save();
            try {
                int iMin = Math.min(i16, (int) ((paint2.descent() - paint2.ascent()) + 0.5f)) / 2;
                int i18 = (i16 - iMin) / 2;
                if (i10 <= 0) {
                    i7 -= i16;
                }
                int i19 = i7 + i18;
                int i20 = i19 + iMin;
                int iDescent = (i12 + ((int) (((paint2.descent() + paint2.ascent()) / 2.0f) + 0.5f))) - (iMin / 2);
                int i21 = iMin + iDescent;
                int i22 = this.f9536p;
                if (i22 == 0 || i22 == 1) {
                    RectF rectF = this.f9535n;
                    rectF.set(i19, iDescent, i20, i21);
                    paint2.setStyle(i22 == 0 ? Paint.Style.FILL : Paint.Style.STROKE);
                    canvas.drawOval(rectF, paint2);
                } else {
                    rect.set(i19, iDescent, i20, i21);
                    paint2.setStyle(Paint.Style.FILL);
                    canvas.drawRect(rect, paint2);
                }
            } finally {
                canvas.restoreToCount(iSave);
            }
        }
    }

    @Override // android.text.style.LeadingMarginSpan
    public final int getLeadingMargin(boolean z6) {
        return this.l.f8756a;
    }
}
