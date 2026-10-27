package com.byd.mvp.utils;

import com.byd.mvp.common.CarModel$Brand;

/* JADX INFO: loaded from: classes2.dex */
public final class AppConfig {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static AppMode f3002a = AppMode.DEBUG;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f3003b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static CarModel$Brand f3004c = CarModel$Brand.DYNASTY;

    public enum AppMode {
        DEBUG,
        TEST,
        PRODUCT
    }

    public static CarModel$Brand a() {
        return f3004c;
    }

    public static AppMode b() {
        return f3002a;
    }

    public static boolean c() {
        return f3003b;
    }

    public static void d(CarModel$Brand carModel$Brand) {
        f3004c = carModel$Brand;
    }

    public static void e(boolean z7) {
        f3003b = z7;
    }
}
