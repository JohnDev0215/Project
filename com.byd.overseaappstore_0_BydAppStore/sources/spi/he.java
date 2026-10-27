package spi;

import android.os.Build;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.VisibleForTesting;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class he {
    public static final boolean f;
    public static final boolean g;
    public static final File h;
    public static volatile he i;
    public static volatile int j;

    @GuardedBy("this")
    public int c;

    @GuardedBy("this")
    public boolean d = true;
    public final AtomicBoolean e = new AtomicBoolean(false);
    public final boolean a = true;
    public final int b = 20000;

    static {
        f = Build.VERSION.SDK_INT < 29;
        g = true;
        h = new File("/proc/self/fd");
        j = -1;
    }

    @VisibleForTesting
    public he() {
    }

    public static he a() {
        if (i == null) {
            synchronized (he.class) {
                if (i == null) {
                    i = new he();
                }
            }
        }
        return i;
    }

    public final boolean b(int i2, int i3, boolean z, boolean z2) {
        boolean z3;
        if (!z) {
            if (Log.isLoggable("HardwareConfig", 2)) {
                Log.v("HardwareConfig", "Hardware config disallowed by caller");
            }
            return false;
        }
        if (!this.a) {
            if (Log.isLoggable("HardwareConfig", 2)) {
                Log.v("HardwareConfig", "Hardware config disallowed by device model");
            }
            return false;
        }
        if (!g) {
            if (Log.isLoggable("HardwareConfig", 2)) {
                Log.v("HardwareConfig", "Hardware config disallowed by sdk");
            }
            return false;
        }
        if (f && !this.e.get()) {
            if (Log.isLoggable("HardwareConfig", 2)) {
                Log.v("HardwareConfig", "Hardware config disallowed by app state");
            }
            return false;
        }
        if (z2) {
            if (Log.isLoggable("HardwareConfig", 2)) {
                Log.v("HardwareConfig", "Hardware config disallowed because exif orientation is required");
            }
            return false;
        }
        if (i2 < 0) {
            if (Log.isLoggable("HardwareConfig", 2)) {
                Log.v("HardwareConfig", "Hardware config disallowed because width is too small");
            }
            return false;
        }
        if (i3 < 0) {
            if (Log.isLoggable("HardwareConfig", 2)) {
                Log.v("HardwareConfig", "Hardware config disallowed because height is too small");
            }
            return false;
        }
        synchronized (this) {
            int i4 = this.c + 1;
            this.c = i4;
            if (i4 >= 50) {
                this.c = 0;
                int length = h.list().length;
                long j2 = j != -1 ? j : this.b;
                boolean z4 = ((long) length) < j2;
                this.d = z4;
                if (!z4 && Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Excluding HARDWARE bitmap config because we're over the file descriptor limit, file descriptors " + length + ", limit " + j2);
                }
            }
            z3 = this.d;
        }
        if (z3) {
            return true;
        }
        if (Log.isLoggable("HardwareConfig", 2)) {
            Log.v("HardwareConfig", "Hardware config disallowed because there are insufficient FDs");
        }
        return false;
    }
}
