package com.byd.mvp.utils;

import android.app.Application;
import b.b.m.g.d;
import com.byd.mvp.common.CarModel$Brand;

/* JADX INFO: loaded from: classes2.dex */
public final class AppConfig {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static AppMode f3862a = AppMode.DEBUG;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f3863b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static CarModel$Brand f3864c = CarModel$Brand.DYNASTY;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static int f3865d = -1;

    public enum AppMode {
        DEBUG,
        TEST,
        PRODUCT
    }

    public static CarModel$Brand a() {
        return f3864c;
    }

    public static AppMode b() {
        return f3862a;
    }

    public static void c(AppMode appMode, Application application, String[] strArr) {
        if (application == null) {
            return;
        }
        f3862a = appMode;
        d.a(application.getPackageName(), strArr);
    }

    public static boolean d() {
        return f3863b;
    }

    public static void e(CarModel$Brand carModel$Brand) {
        f3864c = carModel$Brand;
    }

    public static void f(int i) {
        f3865d = i;
    }

    public static void g(boolean z) {
        f3863b = z;
    }
}
