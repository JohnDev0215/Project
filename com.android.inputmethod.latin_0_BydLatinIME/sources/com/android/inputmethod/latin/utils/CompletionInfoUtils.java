package com.android.inputmethod.latin.utils;

import android.text.TextUtils;
import android.view.inputmethod.CompletionInfo;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class CompletionInfoUtils {
    private CompletionInfoUtils() {
    }

    public static CompletionInfo[] removeNulls(final CompletionInfo[] src) {
        CompletionInfo[] completionInfoArr = new CompletionInfo[src.length];
        int i = 0;
        for (int i2 = 0; i2 < src.length; i2++) {
            if (src[i2] != null && !TextUtils.isEmpty(src[i2].getText())) {
                completionInfoArr[i] = src[i2];
                i++;
            }
        }
        return (CompletionInfo[]) Arrays.copyOfRange(completionInfoArr, 0, i);
    }
}
