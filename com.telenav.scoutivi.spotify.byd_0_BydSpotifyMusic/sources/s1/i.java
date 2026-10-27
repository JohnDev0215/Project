package s1;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;

/* JADX INFO: compiled from: MemorySizeCalculator.java */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f9479a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f9480b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f9481c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f9482d;

    /* JADX INFO: compiled from: MemorySizeCalculator.java */
    public static final class a {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        static final int f9483i = 1;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Context f9484a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ActivityManager f9485b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c f9486c;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        float f9488e;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f9487d = 2.0f;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        float f9489f = 0.4f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        float f9490g = 0.33f;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        int f9491h = 4194304;

        public a(Context context) {
            this.f9488e = f9483i;
            this.f9484a = context;
            this.f9485b = (ActivityManager) context.getSystemService("activity");
            this.f9486c = new b(context.getResources().getDisplayMetrics());
            if (i.e(this.f9485b)) {
                this.f9488e = 0.0f;
            }
        }

        public i a() {
            return new i(this);
        }

        public a b(float f7) {
            k2.j.a(f7 >= 0.0f, "Bitmap pool screens must be greater than or equal to 0");
            this.f9488e = f7;
            return this;
        }

        public a c(float f7) {
            k2.j.a(f7 >= 0.0f, "Memory cache screens must be greater than or equal to 0");
            this.f9487d = f7;
            return this;
        }
    }

    /* JADX INFO: compiled from: MemorySizeCalculator.java */
    private static final class b implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final DisplayMetrics f9492a;

        b(DisplayMetrics displayMetrics) {
            this.f9492a = displayMetrics;
        }

        @Override // s1.i.c
        public int a() {
            return this.f9492a.heightPixels;
        }

        @Override // s1.i.c
        public int b() {
            return this.f9492a.widthPixels;
        }
    }

    /* JADX INFO: compiled from: MemorySizeCalculator.java */
    interface c {
        int a();

        int b();
    }

    i(a aVar) {
        this.f9481c = aVar.f9484a;
        int i7 = e(aVar.f9485b) ? aVar.f9491h / 2 : aVar.f9491h;
        this.f9482d = i7;
        int iC = c(aVar.f9485b, aVar.f9489f, aVar.f9490g);
        float fB = aVar.f9486c.b() * aVar.f9486c.a() * 4;
        int iRound = Math.round(aVar.f9488e * fB);
        int iRound2 = Math.round(fB * aVar.f9487d);
        int i8 = iC - i7;
        int i9 = iRound2 + iRound;
        if (i9 <= i8) {
            this.f9480b = iRound2;
            this.f9479a = iRound;
        } else {
            float f7 = i8;
            float f8 = aVar.f9488e;
            float f9 = aVar.f9487d;
            float f10 = f7 / (f8 + f9);
            this.f9480b = Math.round(f9 * f10);
            this.f9479a = Math.round(f10 * aVar.f9488e);
        }
        if (Log.isLoggable("MemorySizeCalculator", 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Calculation complete, Calculated memory cache size: ");
            sb.append(f(this.f9480b));
            sb.append(", pool size: ");
            sb.append(f(this.f9479a));
            sb.append(", byte array size: ");
            sb.append(f(i7));
            sb.append(", memory class limited? ");
            sb.append(i9 > iC);
            sb.append(", max size: ");
            sb.append(f(iC));
            sb.append(", memoryClass: ");
            sb.append(aVar.f9485b.getMemoryClass());
            sb.append(", isLowMemoryDevice: ");
            sb.append(e(aVar.f9485b));
            Log.d("MemorySizeCalculator", sb.toString());
        }
    }

    private static int c(ActivityManager activityManager, float f7, float f8) {
        float memoryClass = activityManager.getMemoryClass() * 1024 * 1024;
        if (e(activityManager)) {
            f7 = f8;
        }
        return Math.round(memoryClass * f7);
    }

    @TargetApi(19)
    static boolean e(ActivityManager activityManager) {
        return activityManager.isLowRamDevice();
    }

    private String f(int i7) {
        return Formatter.formatFileSize(this.f9481c, i7);
    }

    public int a() {
        return this.f9482d;
    }

    public int b() {
        return this.f9479a;
    }

    public int d() {
        return this.f9480b;
    }
}
