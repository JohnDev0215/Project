package kotlinx.coroutines.internal;

/* JADX INFO: compiled from: SystemProps.kt */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int f7627a = Runtime.getRuntime().availableProcessors();

    public static final int a() {
        return f7627a;
    }

    public static final String b(String str) {
        try {
            return System.getProperty(str);
        } catch (SecurityException unused) {
            return null;
        }
    }
}
