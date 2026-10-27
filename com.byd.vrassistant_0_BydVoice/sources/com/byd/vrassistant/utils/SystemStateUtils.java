package com.byd.vrassistant.utils;

/* JADX INFO: loaded from: classes2.dex */
public class SystemStateUtils {
    public static boolean isPanoramicStart() {
        return SystemPropertiesUtils.getInt("sys.byd.pano_start", 0) == 1;
    }
}
