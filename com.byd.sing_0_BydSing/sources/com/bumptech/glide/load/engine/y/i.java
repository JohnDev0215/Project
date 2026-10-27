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
    private final int f2889a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f2890b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f2891c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f2892d;

    /* JADX INFO: compiled from: MemorySizeCalculator.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final int f2893a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Context f2894b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ActivityManager f2895c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        c f2896d;
        float f;
        float e = 2.0f;
        float g = 0.4f;
        float h = 0.33f;
        int i = 4194304;

        static {
            f2893a = Build.VERSION.SDK_INT < 26 ? 4 : 1;
        }

        public a(Context context) {
            this.f = f2893a;
            this.f2894b = context;
            this.f2895c = (ActivityManager) context.getSystemService("activity");
            this.f2896d = new b(context.getResources().getDisplayMetrics());
            if (Build.VERSION.SDK_INT < 26 || !i.e(this.f2895c)) {
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
        private final DisplayMetrics f2897a;

        b(DisplayMetrics displayMetrics) {
            this.f2897a = displayMetrics;
        }

        @Override // com.bumptech.glide.load.engine.y.i.c
        public int a() {
            return this.f2897a.heightPixels;
        }

        @Override // com.bumptech.glide.load.engine.y.i.c
        public int b() {
            return this.f2897a.widthPixels;
        }
    }

    /* JADX INFO: compiled from: MemorySizeCalculator.java */
    interface c {
        int a();

        int b();
    }

    i(a aVar) {
        this.f2891c = aVar.f2894b;
        int i = e(aVar.f2895c) ? aVar.i / 2 : aVar.i;
        this.f2892d = i;
        int iC = c(aVar.f2895c, aVar.g, aVar.h);
        float fB = aVar.f2896d.b() * aVar.f2896d.a() * 4;
        int iRound = Math.round(aVar.f * fB);
        int iRound2 = Math.round(fB * aVar.e);
        int i2 = iC - i;
        int i3 = iRound2 + iRound;
        if (i3 <= i2) {
            this.f2890b = iRound2;
            this.f2889a = iRound;
        } else {
            float f = i2;
            float f2 = aVar.f;
            float f3 = aVar.e;
            float f4 = f / (f2 + f3);
            this.f2890b = Math.round(f3 * f4);
            this.f2889a = Math.round(f4 * aVar.f);
        }
        if (Log.isLoggable("MemorySizeCalculator", 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Calculation complete, Calculated memory cache size: ");
            sb.append(f(this.f2890b));
            sb.append(", pool size: ");
            sb.append(f(this.f2889a));
            sb.append(", byte array size: ");
            sb.append(f(i));
            sb.append(", memory class limited? ");
            sb.append(i3 > iC);
            sb.append(", max size: ");
            sb.append(f(iC));
            sb.append(", memoryClass: ");
            sb.append(aVar.f2895c.getMemoryClass());
            sb.append(", isLowMemoryDevice: ");
            sb.append(e(aVar.f2895c));
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
        return Formatter.formatFileSize(this.f2891c, i);
    }

    public int a() {
        return this.f2892d;
    }

    public int b() {
        return this.f2889a;
    }

    public int d() {
        return this.f2890b;
    }
}
