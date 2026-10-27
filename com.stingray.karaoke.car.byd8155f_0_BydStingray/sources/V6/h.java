package V6;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.Spanned;
import android.text.style.LeadingMarginSpan;

/* JADX INFO: loaded from: classes.dex */
public final class h implements LeadingMarginSpan {
    public final T6.f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f9545m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Paint f9546n = g.c;
    public int o;

    public h(T6.f fVar, String str) {
        this.l = fVar;
        this.f9545m = str;
    }

    @Override // android.text.style.LeadingMarginSpan
    public final void drawLeadingMargin(Canvas canvas, Paint paint, int i7, int i10, int i11, int i12, int i13, CharSequence charSequence, int i14, int i15, boolean z6, Layout layout) {
        int i16;
        if (z6 && (charSequence instanceof Spanned) && ((Spanned) charSequence).getSpanStart(this) == i14) {
            Paint paint2 = this.f9546n;
            paint2.set(paint);
            T6.f fVar = this.l;
            fVar.getClass();
            paint2.setColor(paint2.getColor());
            int i17 = fVar.c;
            if (i17 != 0) {
                paint2.setStrokeWidth(i17);
            }
            String str = this.f9545m;
            int iMeasureText = (int) (paint2.measureText(str) + 0.5f);
            int i18 = fVar.f8756a;
            if (iMeasureText > i18) {
                this.o = iMeasureText;
                i18 = iMeasureText;
            } else {
                this.o = 0;
            }
            if (i10 > 0) {
                i16 = ((i18 * i10) + i7) - iMeasureText;
            } else {
                i16 = (i18 - iMeasureText) + (i10 * i18) + i7;
            }
            canvas.drawText(str, i16, i12, paint2);
        }
    }

    @Override // android.text.style.LeadingMarginSpan
    public final int getLeadingMargin(boolean z6) {
        return Math.max(this.o, this.l.f8756a);
    }
}
