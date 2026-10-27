package V6;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.LeadingMarginSpan;
import android.text.style.MetricAffectingSpan;
import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class e extends MetricAffectingSpan implements LeadingMarginSpan {
    public final T6.f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Rect f9539m = g.f9543a;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Paint f9540n = g.c;
    public final int o;

    public e(T6.f fVar, int i7) {
        this.l = fVar;
        this.o = i7;
    }

    public final void a(TextPaint textPaint) {
        this.l.getClass();
        textPaint.setFakeBoldText(true);
        float[] fArr = T6.f.f8755g;
        int i7 = this.o;
        if (6 >= i7) {
            textPaint.setTextSize(textPaint.getTextSize() * fArr[i7 - 1]);
            return;
        }
        Locale locale = Locale.US;
        throw new IllegalStateException("Supplied heading level: " + i7 + " is invalid, where configured heading sizes are: `" + Arrays.toString(fArr) + "`");
    }

    @Override // android.text.style.LeadingMarginSpan
    public final void drawLeadingMargin(Canvas canvas, Paint paint, int i7, int i10, int i11, int i12, int i13, CharSequence charSequence, int i14, int i15, boolean z6, Layout layout) {
        int width;
        int i16 = this.o;
        if ((i16 == 1 || i16 == 2) && (charSequence instanceof Spanned) && ((Spanned) charSequence).getSpanEnd(this) == i15) {
            Paint paint2 = this.f9540n;
            paint2.set(paint);
            T6.f fVar = this.l;
            fVar.getClass();
            paint2.setColor(w0.c.E(paint2.getColor(), 75));
            paint2.setStyle(Paint.Style.FILL);
            int i17 = fVar.f8758e;
            if (i17 >= 0) {
                paint2.setStrokeWidth(i17);
            }
            float strokeWidth = paint2.getStrokeWidth();
            if (strokeWidth > 0.0f) {
                int i18 = (int) ((i13 - strokeWidth) + 0.5f);
                if (i10 > 0) {
                    width = canvas.getWidth();
                } else {
                    width = i7;
                    i7 -= canvas.getWidth();
                }
                Rect rect = this.f9539m;
                rect.set(i7, i18, width, i13);
                canvas.drawRect(rect, paint2);
            }
        }
    }

    @Override // android.text.style.LeadingMarginSpan
    public final int getLeadingMargin(boolean z6) {
        return 0;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        a(textPaint);
    }

    @Override // android.text.style.MetricAffectingSpan
    public final void updateMeasureState(TextPaint textPaint) {
        a(textPaint);
    }
}
