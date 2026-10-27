package com.amazon.device.ads;

/* JADX INFO: loaded from: classes2.dex */
class Version {
    private static String buildVersion = "7.1.3";
    private static String devBuild = "(DEV)";
    private static String prefixVersion = "amznAdSDK-android-";
    private static String sdkVersion;

    Version() {
    }

    public static String getRawSDKVersion() {
        String str = buildVersion;
        if (str == null || str.equals("")) {
            return devBuild;
        }
        if (!str.endsWith("x")) {
            return str;
        }
        return str + devBuild;
    }

    public static String getSDKVersion() {
        if (sdkVersion == null) {
            sdkVersion = prefixVersion + getRawSDKVersion();
        }
        return sdkVersion;
    }
}
