package com.bumptech.glide.load.p.d;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final File f2345a = new File("/proc/self/fd");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile n f2346b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f2348d;
    private final int e;
    private int f;
    private boolean g = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f2347c = d();

    n() {
        int i;
        if (Build.VERSION.SDK_INT >= 28) {
            this.f2348d = 20000;
            i = 0;
        } else {
            this.f2348d = 700;
            i = 128;
        }
        this.e = i;
    }

    public static n a() {
        if (f2346b == null) {
            synchronized (n.class) {
                if (f2346b == null) {
                    f2346b = new n();
                }
            }
        }
        return f2346b;
    }

    private synchronized boolean b() {
        boolean z = true;
        int i = this.f + 1;
        this.f = i;
        if (i >= 50) {
            this.f = 0;
            int length = f2345a.list().length;
            if (length >= this.f2348d) {
                z = false;
            }
            this.g = z;
            if (!z && Log.isLoggable("Downsampler", 5)) {
                Log.w("Downsampler", "Excluding HARDWARE bitmap config because we're over the file descriptor limit, file descriptors " + length + ", limit " + this.f2348d);
            }
        }
        return this.g;
    }

    private static boolean d() {
        String str = Build.MODEL;
        if (str == null || str.length() < 7) {
            return true;
        }
        String strSubstring = str.substring(0, 7);
        strSubstring.hashCode();
        switch (strSubstring) {
            case "SM-A520":
            case "SM-G930":
            case "SM-G935":
            case "SM-G960":
            case "SM-G965":
            case "SM-J720":
            case "SM-N935":
                return Build.VERSION.SDK_INT != 26;
            default:
                return true;
        }
    }

    public boolean c(int i, int i2, boolean z, boolean z2) {
        int i3;
        return z && this.f2347c && Build.VERSION.SDK_INT >= 26 && !z2 && i >= (i3 = this.e) && i2 >= i3 && b();
    }

    @TargetApi(26)
    boolean e(int i, int i2, BitmapFactory.Options options, boolean z, boolean z2) {
        boolean zC = c(i, i2, z, z2);
        if (zC) {
            options.inPreferredConfig = Bitmap.Config.HARDWARE;
            options.inMutable = false;
        }
        return zC;
    }
}
