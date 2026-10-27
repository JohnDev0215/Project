package Q0;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/* JADX INFO: loaded from: classes.dex */
public final class a extends MetricAffectingSpan {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final float f6089m;

    public /* synthetic */ a(float f10, int i7) {
        this.l = i7;
        this.f6089m = f10;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        switch (this.l) {
            case 0:
                textPaint.baselineShift += (int) Math.ceil(textPaint.ascent() * this.f6089m);
                break;
            default:
                textPaint.setTextSkewX(textPaint.getTextSkewX() + this.f6089m);
                break;
        }
    }

    @Override // android.text.style.MetricAffectingSpan
    public final void updateMeasureState(TextPaint textPaint) {
        switch (this.l) {
            case 0:
                textPaint.baselineShift += (int) Math.ceil(textPaint.ascent() * this.f6089m);
                break;
            default:
                textPaint.setTextSkewX(textPaint.getTextSkewX() + this.f6089m);
                break;
        }
    }
}
