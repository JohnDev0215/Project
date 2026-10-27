package n4;

import android.util.Log;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class v {
    public static final File d = new File("/proc/self/fd");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static volatile v f19965e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f19966a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f19967b;
    public boolean c = true;

    public v() {
        new AtomicBoolean(false);
        this.f19966a = 20000;
    }

    public static v a() {
        if (f19965e == null) {
            synchronized (v.class) {
                try {
                    if (f19965e == null) {
                        f19965e = new v();
                    }
                } finally {
                }
            }
        }
        return f19965e;
    }

    public final boolean b(int i7, int i10, boolean z6, boolean z10) {
        boolean z11;
        if (!z6) {
            if (Log.isLoggable("HardwareConfig", 2)) {
                Log.v("HardwareConfig", "Hardware config disallowed by caller");
            }
            return false;
        }
        if (z10) {
            if (Log.isLoggable("HardwareConfig", 2)) {
                Log.v("HardwareConfig", "Hardware config disallowed because exif orientation is required");
            }
            return false;
        }
        if (i7 < 0 || i10 < 0) {
            if (Log.isLoggable("HardwareConfig", 2)) {
                Log.v("HardwareConfig", "Hardware config disallowed because of invalid dimensions");
            }
            return false;
        }
        synchronized (this) {
            try {
                int i11 = this.f19967b + 1;
                this.f19967b = i11;
                if (i11 >= 50) {
                    this.f19967b = 0;
                    int length = d.list().length;
                    long j3 = this.f19966a;
                    boolean z12 = ((long) length) < j3;
                    this.c = z12;
                    if (!z12 && Log.isLoggable("Downsampler", 5)) {
                        h6.n.t("Downsampler", "Excluding HARDWARE bitmap config because we're over the file descriptor limit, file descriptors " + length + ", limit " + j3);
                    }
                }
                z11 = this.c;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (z11) {
            return true;
        }
        if (Log.isLoggable("HardwareConfig", 2)) {
            Log.v("HardwareConfig", "Hardware config disallowed because there are insufficient FDs");
        }
        return false;
    }
}
