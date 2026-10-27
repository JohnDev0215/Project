package com.amazon.music.views.library.utils;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: StringUIUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/views/library/utils/StringUIUtils;", "", "()V", "highlightedMessage", "Landroid/text/SpannableString;", "text", "", "stringToHighlight", "colorKey", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Landroid/text/SpannableString;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StringUIUtils {
    public static final StringUIUtils INSTANCE = new StringUIUtils();

    private StringUIUtils() {
    }

    public final SpannableString highlightedMessage(String text, String stringToHighlight, Integer colorKey) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (stringToHighlight == null || colorKey == null) {
            return null;
        }
        SpannableString spannableString = new SpannableString(text);
        int iIndexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) text, StringsKt__StringsKt.trim((CharSequence) stringToHighlight).toString(), 0, false, 6, (Object) null);
        spannableString.setSpan(new ForegroundColorSpan(colorKey.intValue()), iIndexOf$default, Math.min(stringToHighlight.length() + iIndexOf$default, text.length()), 33);
        return spannableString;
    }
}
