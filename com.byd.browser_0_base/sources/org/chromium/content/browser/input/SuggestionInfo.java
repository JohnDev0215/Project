package org.chromium.content.browser.input;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class SuggestionInfo {
    public final int a;
    public final int b;
    public final String c;
    public final String d;
    public final String e;

    public SuggestionInfo(int i, String str, String str2, String str3, int i2) {
        this.a = i;
        this.b = i2;
        this.c = str;
        this.d = str2;
        this.e = str3;
    }

    @CalledByNative
    public static SuggestionInfo[] createArray(int i) {
        return new SuggestionInfo[i];
    }

    @CalledByNative
    public static void createSuggestionInfoAndPutInArray(SuggestionInfo[] suggestionInfoArr, int i, int i2, int i3, String str, String str2, String str3) {
        suggestionInfoArr[i] = new SuggestionInfo(i2, str, str2, str3, i3);
    }
}
