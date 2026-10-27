package defpackage;

import android.os.StrictMode;
import java.io.Closeable;

/* JADX INFO: compiled from: StrictModeContext.java */
/* JADX INFO: loaded from: classes.dex */
public final class ku2 implements Closeable {
    public final StrictMode.ThreadPolicy e;
    public final StrictMode.VmPolicy f;

    public ku2(StrictMode.ThreadPolicy threadPolicy, StrictMode.VmPolicy vmPolicy) {
        this.e = threadPolicy;
        this.f = vmPolicy;
    }

    public static ku2 C() {
        return new ku2(StrictMode.allowThreadDiskWrites(), null);
    }

    public static ku2 F() {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitUnbufferedIo().build());
        return new ku2(threadPolicy, null);
    }

    public static ku2 q() {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        return new ku2(threadPolicy, null);
    }

    public static ku2 t() {
        return new ku2(StrictMode.allowThreadDiskReads(), null);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        StrictMode.ThreadPolicy threadPolicy = this.e;
        if (threadPolicy != null) {
            StrictMode.setThreadPolicy(threadPolicy);
        }
        StrictMode.VmPolicy vmPolicy = this.f;
        if (vmPolicy != null) {
            StrictMode.setVmPolicy(vmPolicy);
        }
    }
}
