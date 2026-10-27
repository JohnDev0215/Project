package Q0;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/* JADX INFO: loaded from: classes.dex */
public final class b extends MetricAffectingSpan {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f6090m;

    public /* synthetic */ b(Object obj, int i7) {
        this.l = i7;
        this.f6090m = obj;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        switch (this.l) {
            case 0:
                textPaint.setFontFeatureSettings((String) this.f6090m);
                break;
            case 1:
                textPaint.setTypeface((Typeface) this.f6090m);
                break;
            default:
                T6.f fVar = (T6.f) this.f6090m;
                fVar.getClass();
                textPaint.setTypeface(Typeface.MONOSPACE);
                textPaint.setTextSize(textPaint.getTextSize() * 0.87f);
                fVar.getClass();
                textPaint.bgColor = w0.c.E(textPaint.getColor(), 25);
                break;
        }
    }

    @Override // android.text.style.MetricAffectingSpan
    public final void updateMeasureState(TextPaint textPaint) {
        switch (this.l) {
            case 0:
                textPaint.setFontFeatureSettings((String) this.f6090m);
                break;
            case 1:
                textPaint.setTypeface((Typeface) this.f6090m);
                break;
            default:
                ((T6.f) this.f6090m).getClass();
                textPaint.setTypeface(Typeface.MONOSPACE);
                textPaint.setTextSize(textPaint.getTextSize() * 0.87f);
                break;
        }
    }
}
