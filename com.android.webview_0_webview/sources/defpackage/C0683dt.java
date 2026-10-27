package defpackage;

import android.os.StrictMode;
import java.io.Closeable;

/* JADX INFO: renamed from: dt, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0683dt implements Closeable {
    public final StrictMode.ThreadPolicy s;
    public final StrictMode.VmPolicy t = null;

    public C0683dt(StrictMode.ThreadPolicy threadPolicy) {
        this.s = threadPolicy;
    }

    public static C0683dt A() {
        return new C0683dt(StrictMode.allowThreadDiskWrites());
    }

    public static C0683dt x() {
        return new C0683dt(StrictMode.allowThreadDiskReads());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        StrictMode.ThreadPolicy threadPolicy = this.s;
        if (threadPolicy != null) {
            StrictMode.setThreadPolicy(threadPolicy);
        }
        StrictMode.VmPolicy vmPolicy = this.t;
        if (vmPolicy != null) {
            StrictMode.setVmPolicy(vmPolicy);
        }
    }
}
