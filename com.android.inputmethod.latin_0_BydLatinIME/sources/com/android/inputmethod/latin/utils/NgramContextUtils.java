package com.android.inputmethod.latin.utils;

import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public final class NgramContextUtils {
    private static final Pattern NEWLINE_REGEX = Pattern.compile("[\\r\\n]+");
    private static final Pattern SPACE_REGEX = Pattern.compile("\\s+");

    private NgramContextUtils() {
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x004e  */
    @javax.annotation.Nonnull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.android.inputmethod.latin.NgramContext getNgramContextFromNthPreviousWord(final java.lang.CharSequence r7, final com.android.inputmethod.latin.settings.SpacingAndPunctuations r8, final int r9) {
        /*
            if (r7 != 0) goto L5
            com.android.inputmethod.latin.NgramContext r7 = com.android.inputmethod.latin.NgramContext.EMPTY_PREV_WORDS_INFO
            return r7
        L5:
            java.util.regex.Pattern r0 = com.android.inputmethod.latin.utils.NgramContextUtils.NEWLINE_REGEX
            java.lang.String[] r7 = r0.split(r7)
            int r0 = r7.length
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L1c
            com.android.inputmethod.latin.NgramContext r7 = new com.android.inputmethod.latin.NgramContext
            com.android.inputmethod.latin.NgramContext$WordInfo[] r8 = new com.android.inputmethod.latin.NgramContext.WordInfo[r2]
            com.android.inputmethod.latin.NgramContext$WordInfo r9 = com.android.inputmethod.latin.NgramContext.WordInfo.BEGINNING_OF_SENTENCE_WORD_INFO
            r8[r1] = r9
            r7.<init>(r8)
            return r7
        L1c:
            java.util.regex.Pattern r0 = com.android.inputmethod.latin.utils.NgramContextUtils.SPACE_REGEX
            int r3 = r7.length
            int r3 = r3 - r2
            r7 = r7[r3]
            java.lang.String[] r7 = r0.split(r7)
            r0 = 3
            com.android.inputmethod.latin.NgramContext$WordInfo[] r2 = new com.android.inputmethod.latin.NgramContext.WordInfo[r0]
            com.android.inputmethod.latin.NgramContext$WordInfo r3 = com.android.inputmethod.latin.NgramContext.WordInfo.EMPTY_WORD_INFO
            java.util.Arrays.fill(r2, r3)
            r3 = r1
        L2f:
            if (r3 >= r0) goto L8a
            int r4 = r7.length
            int r4 = r4 - r9
            int r4 = r4 - r3
            int r5 = r4 + 1
            if (r5 < 0) goto L4e
            int r6 = r7.length
            if (r5 >= r6) goto L4e
            r5 = r7[r5]
            boolean r6 = r5.isEmpty()
            if (r6 != 0) goto L4e
            char r5 = r5.charAt(r1)
            boolean r5 = r8.isWordConnector(r5)
            if (r5 == 0) goto L4e
            goto L8a
        L4e:
            if (r4 >= 0) goto L55
            com.android.inputmethod.latin.NgramContext$WordInfo r7 = com.android.inputmethod.latin.NgramContext.WordInfo.BEGINNING_OF_SENTENCE_WORD_INFO
            r2[r3] = r7
            goto L8a
        L55:
            r4 = r7[r4]
            int r5 = r4.length()
            if (r5 > 0) goto L62
            com.android.inputmethod.latin.NgramContext$WordInfo r7 = com.android.inputmethod.latin.NgramContext.WordInfo.BEGINNING_OF_SENTENCE_WORD_INFO
            r2[r3] = r7
            goto L8a
        L62:
            int r5 = r5 + (-1)
            char r5 = r4.charAt(r5)
            boolean r6 = r8.isSentenceTerminator(r5)
            if (r6 == 0) goto L73
            com.android.inputmethod.latin.NgramContext$WordInfo r7 = com.android.inputmethod.latin.NgramContext.WordInfo.BEGINNING_OF_SENTENCE_WORD_INFO
            r2[r3] = r7
            goto L8a
        L73:
            boolean r6 = r8.isWordSeparator(r5)
            if (r6 != 0) goto L8a
            boolean r5 = r8.isWordConnector(r5)
            if (r5 == 0) goto L80
            goto L8a
        L80:
            com.android.inputmethod.latin.NgramContext$WordInfo r5 = new com.android.inputmethod.latin.NgramContext$WordInfo
            r5.<init>(r4)
            r2[r3] = r5
            int r3 = r3 + 1
            goto L2f
        L8a:
            com.android.inputmethod.latin.NgramContext r7 = new com.android.inputmethod.latin.NgramContext
            r7.<init>(r2)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.inputmethod.latin.utils.NgramContextUtils.getNgramContextFromNthPreviousWord(java.lang.CharSequence, com.android.inputmethod.latin.settings.SpacingAndPunctuations, int):com.android.inputmethod.latin.NgramContext");
    }
}
