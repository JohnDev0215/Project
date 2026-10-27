package com.wrsapi.wtp.jni;

/* JADX INFO: loaded from: classes.dex */
public class WtpJni {
    static {
        try {
            System.loadLibrary("wtp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static native void ConvertDirData(String str);

    public static native void convertData(String[][] strArr);

    public static native void convertFlag(int i);

    public static native String getCacheData();

    public static native String getPostData(String str, String str2);

    public static native String getRisklevel(String str, String str2);

    public static native String parseSalt(String str);
}
