package com.bumptech.glide.load.engine.y;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;

/* JADX INFO: compiled from: MemorySizeCalculator.java */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f3141a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f3142b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f3143c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f3144d;

    /* JADX INFO: compiled from: MemorySizeCalculator.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final int f3145a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Context f3146b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ActivityManager f3147c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        c f3148d;
        float f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        float f3149e = 2.0f;
        float g = 0.4f;
        float h = 0.33f;
        int i = 4194304;

        static {
            f3145a = Build.VERSION.SDK_INT < 26 ? 4 : 1;
        }

        public a(Context context) {
            this.f = f3145a;
            this.f3146b = context;
            this.f3147c = (ActivityManager) context.getSystemService("activity");
            this.f3148d = new b(context.getResources().getDisplayMetrics());
            if (Build.VERSION.SDK_INT < 26 || !i.e(this.f3147c)) {
                return;
            }
            this.f = 0.0f;
        }

        public i a() {
            return new i(this);
        }
    }

    /* JADX INFO: compiled from: MemorySizeCalculator.java */
    private static final class b implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final DisplayMetrics f3150a;

        b(DisplayMetrics displayMetrics) {
            this.f3150a = displayMetrics;
        }

        @Override // com.bumptech.glide.load.engine.y.i.c
        public int a() {
            return this.f3150a.heightPixels;
        }

        @Override // com.bumptech.glide.load.engine.y.i.c
        public int b() {
            return this.f3150a.widthPixels;
        }
    }

    /* JADX INFO: compiled from: MemorySizeCalculator.java */
    interface c {
        int a();

        int b();
    }

    i(a aVar) {
        this.f3143c = aVar.f3146b;
        int i = e(aVar.f3147c) ? aVar.i / 2 : aVar.i;
        this.f3144d = i;
        int iC = c(aVar.f3147c, aVar.g, aVar.h);
        float fB = aVar.f3148d.b() * aVar.f3148d.a() * 4;
        int iRound = Math.round(aVar.f * fB);
        int iRound2 = Math.round(fB * aVar.f3149e);
        int i2 = iC - i;
        int i3 = iRound2 + iRound;
        if (i3 <= i2) {
            this.f3142b = iRound2;
            this.f3141a = iRound;
        } else {
            float f = i2;
            float f2 = aVar.f;
            float f3 = aVar.f3149e;
            float f4 = f / (f2 + f3);
            this.f3142b = Math.round(f3 * f4);
            this.f3141a = Math.round(f4 * aVar.f);
        }
        if (Log.isLoggable("MemorySizeCalculator", 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Calculation complete, Calculated memory cache size: ");
            sb.append(f(this.f3142b));
            sb.append(", pool size: ");
            sb.append(f(this.f3141a));
            sb.append(", byte array size: ");
            sb.append(f(i));
            sb.append(", memory class limited? ");
            sb.append(i3 > iC);
            sb.append(", max size: ");
            sb.append(f(iC));
            sb.append(", memoryClass: ");
            sb.append(aVar.f3147c.getMemoryClass());
            sb.append(", isLowMemoryDevice: ");
            sb.append(e(aVar.f3147c));
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
        return Formatter.formatFileSize(this.f3143c, i);
    }

    public int a() {
        return this.f3144d;
    }

    public int b() {
        return this.f3141a;
    }

    public int d() {
        return this.f3142b;
    }
}
