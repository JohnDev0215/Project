package com.byd.assistant;

/* JADX INFO: loaded from: classes.dex */
public class HPatch {
    public static native int patch(String str, String str2, String str3, long j);

    public static native int patchDir(String str, String str2, String str3, long j, long j2);

    static {
        System.loadLibrary("hpatchz");
    }

    public static int patch(String str, String str2, String str3) {
        return patch(str, str2, str3, -1L);
    }

    public static int patchDir(String str, String str2, String str3) {
        return patchDir(str, str2, str3, -1L, -1L);
    }
}
