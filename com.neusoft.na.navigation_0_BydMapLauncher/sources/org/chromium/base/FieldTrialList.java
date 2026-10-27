package org.chromium.base;

/* JADX INFO: loaded from: classes3.dex */
public class FieldTrialList {
    private static native String nativeFindFullName(String str);

    private static native boolean nativeTrialExists(String str);

    private FieldTrialList() {
    }

    public static String findFullName(String str) {
        return nativeFindFullName(str);
    }

    public static boolean trialExists(String str) {
        return nativeTrialExists(str);
    }
}
