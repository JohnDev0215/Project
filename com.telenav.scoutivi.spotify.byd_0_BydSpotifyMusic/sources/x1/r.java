package x1;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import java.io.File;

/* JADX INFO: compiled from: HardwareConfigState.java */
/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final File f9792d = new File("/proc/self/fd");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static volatile int f9793e = 700;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static volatile int f9794f = 128;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static volatile r f9795g;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f9797b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f9798c = true;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f9796a = d();

    r() {
    }

    public static r a() {
        if (f9795g == null) {
            synchronized (r.class) {
                if (f9795g == null) {
                    f9795g = new r();
                }
            }
        }
        return f9795g;
    }

    private synchronized boolean b() {
        boolean z6 = true;
        int i7 = this.f9797b + 1;
        this.f9797b = i7;
        if (i7 >= 50) {
            this.f9797b = 0;
            int length = f9792d.list().length;
            if (length >= f9793e) {
                z6 = false;
            }
            this.f9798c = z6;
            if (!z6 && Log.isLoggable("Downsampler", 5)) {
                Log.w("Downsampler", "Excluding HARDWARE bitmap config because we're over the file descriptor limit, file descriptors " + length + ", limit " + f9793e);
            }
        }
        return this.f9798c;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:37:0x006b. Please report as an issue. */
    private static boolean d() {
        String str = Build.MODEL;
        if (str != null && str.length() >= 7) {
            String strSubstring = str.substring(0, 7);
            strSubstring.hashCode();
            switch (strSubstring) {
            }
        }
        return true;
    }

    public boolean c(int i7, int i8, boolean z6, boolean z7) {
        return z6 && this.f9796a && !z7 && i7 >= f9794f && i8 >= f9794f && b();
    }

    @TargetApi(26)
    boolean e(int i7, int i8, BitmapFactory.Options options, boolean z6, boolean z7) {
        boolean zC = c(i7, i8, z6, z7);
        if (zC) {
            options.inPreferredConfig = Bitmap.Config.HARDWARE;
            options.inMutable = false;
        }
        return zC;
    }
}
