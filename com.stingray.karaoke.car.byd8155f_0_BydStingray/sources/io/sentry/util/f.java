package io.sentry.util;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f16921a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final boolean f16922b;

    static {
        try {
            f16921a = "The Android Project".equals(System.getProperty("java.vendor"));
        } catch (Throwable unused) {
            f16921a = false;
        }
        try {
            String property = System.getProperty("java.specification.version");
            if (property != null) {
                f16922b = Double.valueOf(property).doubleValue() >= 9.0d;
            } else {
                f16922b = false;
            }
        } catch (Throwable unused2) {
            f16922b = false;
        }
    }
}
