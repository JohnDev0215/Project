package org.chromium.ui.text;

import android.text.SpannableString;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class SpanApplier {

    public static final class SpanInfo implements Comparable<SpanInfo> {
        final String mEndTag;
        int mEndTagIndex;
        final Object mSpan;
        final String mStartTag;
        int mStartTagIndex;

        public int hashCode() {
            return 0;
        }

        public SpanInfo(String str, String str2, Object obj) {
            this.mStartTag = str;
            this.mEndTag = str2;
            this.mSpan = obj;
        }

        @Override // java.lang.Comparable
        public int compareTo(SpanInfo spanInfo) {
            int i = this.mStartTagIndex;
            int i2 = spanInfo.mStartTagIndex;
            if (i < i2) {
                return -1;
            }
            return i == i2 ? 0 : 1;
        }

        public boolean equals(Object obj) {
            return (obj instanceof SpanInfo) && compareTo((SpanInfo) obj) == 0;
        }
    }

    public static SpannableString applySpans(String str, SpanInfo... spanInfoArr) {
        for (SpanInfo spanInfo : spanInfoArr) {
            spanInfo.mStartTagIndex = str.indexOf(spanInfo.mStartTag);
            spanInfo.mEndTagIndex = str.indexOf(spanInfo.mEndTag, spanInfo.mStartTagIndex + spanInfo.mStartTag.length());
        }
        Arrays.sort(spanInfoArr);
        StringBuilder sb = new StringBuilder(str.length());
        int length = 0;
        for (SpanInfo spanInfo2 : spanInfoArr) {
            if (spanInfo2.mStartTagIndex == -1 || spanInfo2.mEndTagIndex == -1 || spanInfo2.mStartTagIndex < length) {
                spanInfo2.mStartTagIndex = -1;
                throw new IllegalArgumentException(String.format("Input string is missing tags %s%s: %s", spanInfo2.mStartTag, spanInfo2.mEndTag, str));
            }
            sb.append((CharSequence) str, length, spanInfo2.mStartTagIndex);
            int length2 = spanInfo2.mStartTagIndex + spanInfo2.mStartTag.length();
            spanInfo2.mStartTagIndex = sb.length();
            sb.append((CharSequence) str, length2, spanInfo2.mEndTagIndex);
            length = spanInfo2.mEndTagIndex + spanInfo2.mEndTag.length();
            spanInfo2.mEndTagIndex = sb.length();
        }
        sb.append((CharSequence) str, length, str.length());
        SpannableString spannableString = new SpannableString(sb);
        for (SpanInfo spanInfo3 : spanInfoArr) {
            if (spanInfo3.mStartTagIndex != -1) {
                spannableString.setSpan(spanInfo3.mSpan, spanInfo3.mStartTagIndex, spanInfo3.mEndTagIndex, 0);
            }
        }
        return spannableString;
    }
}
