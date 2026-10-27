package com.byd.mvp.utils;

import com.byd.mvp.common.CarModel$Brand;

/* JADX INFO: loaded from: classes2.dex */
public final class AppConfig {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static AppMode f762a = AppMode.DEBUG;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f763b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static CarModel$Brand f764c = CarModel$Brand.DYNASTY;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static int f765d = -1;

    public enum AppMode {
        DEBUG,
        TEST,
        PRODUCT
    }

    public static CarModel$Brand a() {
        return f764c;
    }

    public static boolean b() {
        return f763b;
    }
}
