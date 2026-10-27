package com.amazon.ui.foundations.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: SpannableUtil.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002¨\u0006\u000e"}, d2 = {"Lcom/amazon/ui/foundations/utils/SpannableUtil;", "", "()V", "getSpannableWithEmphasis", "Landroid/text/Spannable;", "text", "", "deemphasizedText", "setBold", "", "spannableText", "rangeStart", "", "rangeEnd", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SpannableUtil {
    public static final SpannableUtil INSTANCE = new SpannableUtil();

    private SpannableUtil() {
    }

    public final Spannable getSpannableWithEmphasis(String text, String deemphasizedText) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(deemphasizedText, "deemphasizedText");
        int iIndexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) text, deemphasizedText, 0, false, 6, (Object) null);
        SpannableString spannableString = new SpannableString(text);
        if (iIndexOf$default >= 0) {
            setBold(spannableString, 0, iIndexOf$default);
            setBold(spannableString, iIndexOf$default + deemphasizedText.length(), text.length());
        } else {
            setBold(spannableString, 0, text.length());
        }
        return spannableString;
    }

    private final void setBold(Spannable spannableText, int rangeStart, int rangeEnd) {
        spannableText.setSpan(new StyleSpan(1), rangeStart, rangeEnd, 33);
    }
}
