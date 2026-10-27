package com.amazon.ui.foundations.utils;

import android.content.Context;
import android.text.TextPaint;
import android.widget.TextView;
import androidx.core.widget.TextViewCompat;
import com.amazon.ui.foundations.styles.FontSize;
import com.amazon.ui.foundations.styles.FontStyle;
import com.google.android.material.button.MaterialButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UIExtensions.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007\u001a\n\u0010\b\u001a\u00020\u0001*\u00020\t\u001a\u0012\u0010\n\u001a\u00020\u000b*\u00020\u00072\u0006\u0010\f\u001a\u00020\r¨\u0006\u000e"}, d2 = {"applyFontSize", "", "Landroid/widget/TextView;", "fontSize", "Lcom/amazon/ui/foundations/styles/FontSize;", "applyFontStyle", "fontStyle", "Lcom/amazon/ui/foundations/styles/FontStyle;", "setRightIcon", "Lcom/google/android/material/button/MaterialButton;", "toPaint", "Landroid/text/TextPaint;", "context", "Landroid/content/Context;", "DMMUIFoundations_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class UIExtensionsKt {
    public static final void setRightIcon(MaterialButton materialButton) {
        Intrinsics.checkNotNullParameter(materialButton, "<this>");
        TextViewCompat.setCompoundDrawablesRelative(materialButton, null, null, materialButton.getIcon(), null);
    }

    public static final void applyFontSize(TextView textView, FontSize fontSize) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Intrinsics.checkNotNullParameter(fontSize, "fontSize");
        FontUtil.INSTANCE.applyFontSize(textView, fontSize);
    }

    public static final void applyFontStyle(TextView textView, FontStyle fontStyle) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Intrinsics.checkNotNullParameter(fontStyle, "fontStyle");
        FontUtil.INSTANCE.applyFontStyle(textView, fontStyle);
    }

    public static final TextPaint toPaint(FontStyle fontStyle, Context context) {
        Intrinsics.checkNotNullParameter(fontStyle, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        float f = context.getResources().getDisplayMetrics().scaledDensity;
        float f2 = context.getResources().getDisplayMetrics().density;
        TextPaint textPaint = new TextPaint(1);
        textPaint.setTypeface(fontStyle.getTypeface());
        textPaint.setTextSize((fontStyle.getFontSize().getTextSize() * f2) / f);
        textPaint.setColor(fontStyle.getColor());
        return textPaint;
    }
}
