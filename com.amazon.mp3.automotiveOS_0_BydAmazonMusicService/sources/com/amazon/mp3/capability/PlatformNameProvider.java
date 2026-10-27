package com.amazon.mp3.capability;

import com.amazon.mp3.util.AmazonDevice;

/* JADX INFO: loaded from: classes3.dex */
public class PlatformNameProvider {
    public static String getPlatformNameForDMLSRequests() {
        return "Android";
    }

    public static String getPlatformNameForUserAgent() {
        return "Android";
    }

    public static String getPlatformName() {
        return AmazonDevice.isGrover() ? "Grover" : AmazonDevice.isCanary() ? "Canary" : "Android";
    }
}
