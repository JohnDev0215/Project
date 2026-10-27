package V6;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;

/* JADX INFO: loaded from: classes.dex */
public final class a implements LeadingMarginSpan {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final T6.f f9532m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Rect f9533n;
    public final Paint o;

    public a(T6.f fVar, int i7) {
        this.l = i7;
        switch (i7) {
            case 1:
                this.f9533n = g.f9543a;
                this.o = g.c;
                this.f9532m = fVar;
                break;
            default:
                this.f9533n = g.f9543a;
                this.o = g.c;
                this.f9532m = fVar;
                break;
        }
    }

    @Override // android.text.style.LeadingMarginSpan
    public final void drawLeadingMargin(Canvas canvas, Paint paint, int i7, int i10, int i11, int i12, int i13, CharSequence charSequence, int i14, int i15, boolean z6, Layout layout) {
        int width;
        switch (this.l) {
            case 0:
                T6.f fVar = this.f9532m;
                int i16 = fVar.f8757b;
                if (i16 == 0) {
                    i16 = (int) ((fVar.f8756a * 0.25f) + 0.5f);
                }
                Paint paint2 = this.o;
                paint2.set(paint);
                fVar.getClass();
                int iE = w0.c.E(paint2.getColor(), 25);
                paint2.setStyle(Paint.Style.FILL);
                paint2.setColor(iE);
                int i17 = i10 * i16;
                int i18 = i7 + i17;
                int i19 = i17 + i18;
                int iMin = Math.min(i18, i19);
                int iMax = Math.max(i18, i19);
                Rect rect = this.f9533n;
                rect.set(iMin, i11, iMax, i13);
                canvas.drawRect(rect, paint2);
                break;
            default:
                int i20 = ((i13 - i11) / 2) + i11;
                Paint paint3 = this.o;
                paint3.set(paint);
                T6.f fVar2 = this.f9532m;
                fVar2.getClass();
                paint3.setColor(w0.c.E(paint3.getColor(), 25));
                paint3.setStyle(Paint.Style.FILL);
                int i21 = fVar2.f8759f;
                if (i21 >= 0) {
                    paint3.setStrokeWidth(i21);
                }
                int strokeWidth = (int) ((((int) (paint3.getStrokeWidth() + 0.5f)) / 2.0f) + 0.5f);
                if (i10 > 0) {
                    width = canvas.getWidth();
                } else {
                    width = i7;
                    i7 -= canvas.getWidth();
                }
                Rect rect2 = this.f9533n;
                rect2.set(i7, i20 - strokeWidth, width, i20 + strokeWidth);
                canvas.drawRect(rect2, paint3);
                break;
        }
    }

    @Override // android.text.style.LeadingMarginSpan
    public final int getLeadingMargin(boolean z6) {
        switch (this.l) {
            case 0:
                return this.f9532m.f8756a;
            default:
                return 0;
        }
    }
}
