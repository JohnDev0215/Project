package io.sentry.android.core;

/* JADX INFO: renamed from: io.sentry.android.core.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class AbstractC1441q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f16290a;

    static {
        int[] iArr = new int[io.sentry.F.values().length];
        f16290a = iArr;
        try {
            iArr[io.sentry.F.CONNECTED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f16290a[io.sentry.F.UNKNOWN.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f16290a[io.sentry.F.NO_PERMISSION.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}
