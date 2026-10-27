package android.ext.services.watchdog;

/* JADX INFO: loaded from: classes.dex */
interface ExplicitHealthChecker {
    void cancel();

    String getSupportedPackageName();

    boolean isPending();

    void request();
}
