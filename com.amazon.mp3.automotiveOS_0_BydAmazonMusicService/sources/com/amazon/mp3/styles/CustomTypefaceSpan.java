package com.amazon.mp3.styles;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomTypefaceSpan.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u0012\u0010\t\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\bH\u0016J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\bH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/styles/CustomTypefaceSpan;", "Landroid/text/style/MetricAffectingSpan;", "font", "Landroid/graphics/Typeface;", "(Landroid/graphics/Typeface;)V", "update", "", "tp", "Landroid/text/TextPaint;", "updateDrawState", "textPaint", "updateMeasureState", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomTypefaceSpan extends MetricAffectingSpan {
    private final Typeface font;

    public CustomTypefaceSpan(Typeface typeface) {
        this.font = typeface;
    }

    @Override // android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint textPaint) {
        Intrinsics.checkNotNullParameter(textPaint, "textPaint");
        update(textPaint);
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        update(textPaint);
    }

    private final void update(TextPaint tp) {
        if (tp == null) {
            return;
        }
        Typeface typeface = tp.getTypeface();
        tp.setTypeface(Typeface.create(this.font, typeface == null ? 0 : typeface.getStyle()));
    }
}
