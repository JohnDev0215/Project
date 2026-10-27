package io.sentry.android.core;

import io.sentry.EnumC1495p1;

/* JADX INFO: renamed from: io.sentry.android.core.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class AbstractC1434j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f16242a;

    static {
        int[] iArr = new int[EnumC1495p1.values().length];
        f16242a = iArr;
        try {
            iArr[EnumC1495p1.INFO.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f16242a[EnumC1495p1.WARNING.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f16242a[EnumC1495p1.ERROR.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f16242a[EnumC1495p1.FATAL.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            f16242a[EnumC1495p1.DEBUG.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
    }
}
