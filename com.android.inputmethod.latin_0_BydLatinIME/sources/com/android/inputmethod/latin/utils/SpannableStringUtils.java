package com.android.inputmethod.latin.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.SuggestionSpan;
import android.text.style.URLSpan;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public final class SpannableStringUtils {
    public static void copyNonParagraphSuggestionSpansFrom(Spanned source, int start, int end, Spannable dest, int destoff) {
        Object[] spans = source.getSpans(start, end, SuggestionSpan.class);
        for (int i = 0; i < spans.length; i++) {
            int spanFlags = source.getSpanFlags(spans[i]) & (-52);
            int spanStart = source.getSpanStart(spans[i]);
            int spanEnd = source.getSpanEnd(spans[i]);
            if (spanStart < start) {
                spanStart = start;
            }
            if (spanEnd > end) {
                spanEnd = end;
            }
            dest.setSpan(spans[i], (spanStart - start) + destoff, (spanEnd - start) + destoff, spanFlags);
        }
    }

    public static CharSequence concatWithNonParagraphSuggestionSpansOnly(CharSequence... text) {
        if (text.length == 0) {
            return "";
        }
        boolean z = true;
        if (text.length == 1) {
            return text[0];
        }
        int i = 0;
        while (true) {
            if (i >= text.length) {
                z = false;
                break;
            }
            if (text[i] instanceof Spanned) {
                break;
            }
            i++;
        }
        StringBuilder sb = new StringBuilder();
        for (CharSequence charSequence : text) {
            sb.append(charSequence);
        }
        if (!z) {
            return sb.toString();
        }
        SpannableString spannableString = new SpannableString(sb);
        int i2 = 0;
        for (int i3 = 0; i3 < text.length; i3++) {
            int length = text[i3].length();
            if (text[i3] instanceof Spanned) {
                copyNonParagraphSuggestionSpansFrom((Spanned) text[i3], 0, length, spannableString, i2);
            }
            i2 += length;
        }
        return new SpannedString(spannableString);
    }

    public static boolean hasUrlSpans(final CharSequence text, final int startIndex, final int endIndex) {
        URLSpan[] uRLSpanArr;
        return (text instanceof Spanned) && (uRLSpanArr = (URLSpan[]) ((Spanned) text).getSpans(startIndex - 1, endIndex + 1, URLSpan.class)) != null && uRLSpanArr.length > 0;
    }

    public static CharSequence[] split(final CharSequence charSequence, final String regex, final boolean preserveTrailingEmptySegments) {
        if (!(charSequence instanceof Spanned)) {
            return charSequence.toString().split(regex, preserveTrailingEmptySegments ? -1 : 0);
        }
        ArrayList arrayList = new ArrayList();
        Matcher matcher = Pattern.compile(regex).matcher(charSequence);
        boolean z = false;
        int iEnd = 0;
        while (matcher.find()) {
            arrayList.add(charSequence.subSequence(iEnd, matcher.start()));
            iEnd = matcher.end();
            z = true;
        }
        if (!z) {
            return new CharSequence[]{charSequence};
        }
        arrayList.add(charSequence.subSequence(iEnd, charSequence.length()));
        if (!preserveTrailingEmptySegments) {
            for (int size = arrayList.size() - 1; size >= 0 && TextUtils.isEmpty((CharSequence) arrayList.get(size)); size--) {
                arrayList.remove(size);
            }
        }
        return (CharSequence[]) arrayList.toArray(new CharSequence[arrayList.size()]);
    }
}
