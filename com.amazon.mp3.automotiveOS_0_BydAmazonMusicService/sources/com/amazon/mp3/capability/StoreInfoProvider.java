package com.amazon.mp3.capability;

import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class StoreInfoProvider {
    public static String getDeviceName() {
        return "android_browser_hawkfire";
    }

    public static void appendVersion(final Map<String, String> urlParams) {
        if (urlParams.containsKey("version")) {
            urlParams.put("version", "cc26d89");
        }
    }
}
