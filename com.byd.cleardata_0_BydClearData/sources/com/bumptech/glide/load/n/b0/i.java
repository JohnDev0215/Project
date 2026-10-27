package com.bumptech.glide.load.n.b0;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f2075a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f2076b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f2077c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f2078d;

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final int f2079a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Context f2080b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ActivityManager f2081c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        c f2082d;
        float f;
        float e = 2.0f;
        float g = 0.4f;
        float h = 0.33f;
        int i = 4194304;

        static {
            f2079a = Build.VERSION.SDK_INT < 26 ? 4 : 1;
        }

        public a(Context context) {
            this.f = f2079a;
            this.f2080b = context;
            this.f2081c = (ActivityManager) context.getSystemService("activity");
            this.f2082d = new b(context.getResources().getDisplayMetrics());
            if (Build.VERSION.SDK_INT < 26 || !i.e(this.f2081c)) {
                return;
            }
            this.f = 0.0f;
        }

        public i a() {
            return new i(this);
        }
    }

    private static final class b implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final DisplayMetrics f2083a;

        b(DisplayMetrics displayMetrics) {
            this.f2083a = displayMetrics;
        }

        @Override // com.bumptech.glide.load.n.b0.i.c
        public int a() {
            return this.f2083a.heightPixels;
        }

        @Override // com.bumptech.glide.load.n.b0.i.c
        public int b() {
            return this.f2083a.widthPixels;
        }
    }

    interface c {
        int a();

        int b();
    }

    i(a aVar) {
        this.f2077c = aVar.f2080b;
        int i = e(aVar.f2081c) ? aVar.i / 2 : aVar.i;
        this.f2078d = i;
        int iC = c(aVar.f2081c, aVar.g, aVar.h);
        float fB = aVar.f2082d.b() * aVar.f2082d.a() * 4;
        int iRound = Math.round(aVar.f * fB);
        int iRound2 = Math.round(fB * aVar.e);
        int i2 = iC - i;
        int i3 = iRound2 + iRound;
        if (i3 <= i2) {
            this.f2076b = iRound2;
            this.f2075a = iRound;
        } else {
            float f = i2;
            float f2 = aVar.f;
            float f3 = aVar.e;
            float f4 = f / (f2 + f3);
            this.f2076b = Math.round(f3 * f4);
            this.f2075a = Math.round(f4 * aVar.f);
        }
        if (Log.isLoggable("MemorySizeCalculator", 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Calculation complete, Calculated memory cache size: ");
            sb.append(f(this.f2076b));
            sb.append(", pool size: ");
            sb.append(f(this.f2075a));
            sb.append(", byte array size: ");
            sb.append(f(i));
            sb.append(", memory class limited? ");
            sb.append(i3 > iC);
            sb.append(", max size: ");
            sb.append(f(iC));
            sb.append(", memoryClass: ");
            sb.append(aVar.f2081c.getMemoryClass());
            sb.append(", isLowMemoryDevice: ");
            sb.append(e(aVar.f2081c));
            Log.d("MemorySizeCalculator", sb.toString());
        }
    }

    private static int c(ActivityManager activityManager, float f, float f2) {
        float memoryClass = activityManager.getMemoryClass() * 1024 * 1024;
        if (e(activityManager)) {
            f = f2;
        }
        return Math.round(memoryClass * f);
    }

    @TargetApi(19)
    static boolean e(ActivityManager activityManager) {
        if (Build.VERSION.SDK_INT >= 19) {
            return activityManager.isLowRamDevice();
        }
        return true;
    }

    private String f(int i) {
        return Formatter.formatFileSize(this.f2077c, i);
    }

    public int a() {
        return this.f2078d;
    }

    public int b() {
        return this.f2075a;
    }

    public int d() {
        return this.f2076b;
    }
}
