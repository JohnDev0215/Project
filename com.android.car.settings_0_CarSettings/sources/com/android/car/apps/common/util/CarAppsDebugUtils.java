package com.android.car.apps.common.util;

/* JADX INFO: loaded from: classes5.dex */
public class CarAppsDebugUtils {
    public static String idHash(Object object) {
        return Integer.toHexString(System.identityHashCode(object));
    }
}
