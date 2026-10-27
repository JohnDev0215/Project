package b.h.f;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import b.h.e.c.c;
import b.h.e.c.f;
import b.h.j.b;

/* JADX INFO: compiled from: TypefaceCompat.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"NewApi"})
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j f2440a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b.e.e<String, Typeface> f2441b;

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            f2440a = new i();
        } else if (i2 >= 28) {
            f2440a = new h();
        } else if (i2 >= 26) {
            f2440a = new g();
        } else if (i2 >= 24 && f.m()) {
            f2440a = new f();
        } else if (i2 >= 21) {
            f2440a = new e();
        } else {
            f2440a = new j();
        }
        f2441b = new b.e.e<>(16);
    }

    public static Typeface a(Context context, Typeface typeface, int i2) {
        Typeface typefaceG;
        if (context != null) {
            return (Build.VERSION.SDK_INT >= 21 || (typefaceG = g(context, typeface, i2)) == null) ? Typeface.create(typeface, i2) : typefaceG;
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    public static Typeface b(Context context, CancellationSignal cancellationSignal, b.f[] fVarArr, int i2) {
        return f2440a.c(context, cancellationSignal, fVarArr, i2);
    }

    public static Typeface c(Context context, c.a aVar, Resources resources, int i2, int i3, f.a aVar2, Handler handler, boolean z) {
        Typeface typefaceB;
        if (aVar instanceof c.d) {
            c.d dVar = (c.d) aVar;
            boolean z2 = false;
            if (!z ? aVar2 == null : dVar.a() == 0) {
                z2 = true;
            }
            typefaceB = b.h.j.b.g(context, dVar.b(), aVar2, handler, z2, z ? dVar.c() : -1, i3);
        } else {
            typefaceB = f2440a.b(context, (c.b) aVar, resources, i3);
            if (aVar2 != null) {
                if (typefaceB != null) {
                    aVar2.b(typefaceB, handler);
                } else {
                    aVar2.a(-3, handler);
                }
            }
        }
        if (typefaceB != null) {
            f2441b.d(e(resources, i2, i3), typefaceB);
        }
        return typefaceB;
    }

    public static Typeface d(Context context, Resources resources, int i2, String str, int i3) {
        Typeface typefaceE = f2440a.e(context, resources, i2, str, i3);
        if (typefaceE != null) {
            f2441b.d(e(resources, i2, i3), typefaceE);
        }
        return typefaceE;
    }

    public static String e(Resources resources, int i2, int i3) {
        return resources.getResourcePackageName(i2) + "-" + i2 + "-" + i3;
    }

    public static Typeface f(Resources resources, int i2, int i3) {
        return f2441b.c(e(resources, i2, i3));
    }

    public static Typeface g(Context context, Typeface typeface, int i2) {
        j jVar = f2440a;
        c.b bVarI = jVar.i(typeface);
        if (bVarI == null) {
            return null;
        }
        return jVar.b(context, bVarI, context.getResources(), i2);
    }
}
