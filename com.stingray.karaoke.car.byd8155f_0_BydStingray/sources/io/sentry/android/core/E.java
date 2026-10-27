package io.sentry.android.core;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class E {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f16047a;

    static {
        int[] iArr = new int[io.sentry.F.values().length];
        f16047a = iArr;
        try {
            iArr[io.sentry.F.DISCONNECTED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f16047a[io.sentry.F.CONNECTED.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
    }
}
