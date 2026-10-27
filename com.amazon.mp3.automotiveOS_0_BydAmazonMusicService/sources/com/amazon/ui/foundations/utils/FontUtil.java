package com.amazon.ui.foundations.utils;

import android.os.Build;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;
import com.amazon.ui.foundations.styles.FontSize;
import com.amazon.ui.foundations.styles.FontStyle;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FontUtil.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\u0016\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J6\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u0010J\u001a\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00122\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¨\u0006\u0019"}, d2 = {"Lcom/amazon/ui/foundations/utils/FontUtil;", "", "()V", "applyAutoSize", "", "textView", "Landroid/widget/TextView;", "sizes", "", "", "applyFontSize", "view", "fontSize", "Lcom/amazon/ui/foundations/styles/FontSize;", "applyFontStyle", "fontStyle", "Lcom/amazon/ui/foundations/styles/FontStyle;", "text1", "", "fontStyle1", "text2", "fontStyle2", "getSpannableString", "Landroid/text/SpannableString;", "text", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FontUtil {
    public static final FontUtil INSTANCE = new FontUtil();

    private FontUtil() {
    }

    public final void applyFontSize(TextView view, FontSize fontSize) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(fontSize, "fontSize");
        view.setTextSize(0, fontSize.getTextSize());
        if (Build.VERSION.SDK_INT >= 28) {
            Integer lineHeight = fontSize.getLineHeight();
            view.setLineHeight(lineHeight == null ? fontSize.getTextSize() : lineHeight.intValue());
        }
    }

    public final void applyFontStyle(TextView view, FontStyle fontStyle) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(fontStyle, "fontStyle");
        applyFontSize(view, fontStyle.getFontSize());
        view.setTypeface(fontStyle.getTypeface());
        view.setTextColor(fontStyle.getColor());
        view.setAllCaps(Intrinsics.areEqual(fontStyle.getAllCaps(), Boolean.TRUE));
    }

    public final void applyFontStyle(TextView view, String text1, FontStyle fontStyle1, String text2, FontStyle fontStyle2) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setText(TextUtils.concat(getSpannableString(text1, fontStyle1), getSpannableString(text2, fontStyle2)));
    }

    public final SpannableString getSpannableString(String text, FontStyle fontStyle) {
        if (TextUtils.isEmpty(text)) {
            return new SpannableString("");
        }
        if (fontStyle == null) {
            return new SpannableString(text);
        }
        SpannableString spannableString = new SpannableString(text);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(fontStyle.getColor());
        Intrinsics.checkNotNull(text);
        spannableString.setSpan(foregroundColorSpan, 0, text.length(), 33);
        spannableString.setSpan(new StyleSpan(fontStyle.getTypeface().getStyle()), 0, text.length(), 33);
        spannableString.setSpan(new AbsoluteSizeSpan(fontStyle.getFontSize().getTextSize()), 0, text.length(), 33);
        return spannableString;
    }

    public final void applyAutoSize(TextView textView, List<Integer> sizes) {
        Intrinsics.checkNotNullParameter(textView, "textView");
        Intrinsics.checkNotNullParameter(sizes, "sizes");
        if (Build.VERSION.SDK_INT >= 26) {
            textView.setAutoSizeTextTypeUniformWithPresetSizes(CollectionsKt___CollectionsKt.toIntArray(sizes), 0);
        }
    }
}
