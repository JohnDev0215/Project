package io.sentry.android.core.internal.gestures;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f16180a;

    static {
        int[] iArr = new int[d.values().length];
        f16180a = iArr;
        try {
            iArr[d.Click.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f16180a[d.Scroll.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f16180a[d.Swipe.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f16180a[d.Unknown.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
    }
}
