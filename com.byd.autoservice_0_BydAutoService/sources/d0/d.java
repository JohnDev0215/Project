package d0;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import c0.c;
import c0.f;
import i0.b;

/* JADX INFO: compiled from: TypefaceCompat.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"NewApi"})
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j f2656a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final s.e<String, Typeface> f2657b;

    static {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 29) {
            f2656a = new i();
        } else if (i3 >= 28) {
            f2656a = new h();
        } else if (i3 >= 26) {
            f2656a = new g();
        } else if (i3 >= 24 && f.m()) {
            f2656a = new f();
        } else if (i3 >= 21) {
            f2656a = new e();
        } else {
            f2656a = new j();
        }
        f2657b = new s.e<>(16);
    }

    public static Typeface a(Context context, Typeface typeface, int i3) {
        Typeface typefaceG;
        if (context != null) {
            return (Build.VERSION.SDK_INT >= 21 || (typefaceG = g(context, typeface, i3)) == null) ? Typeface.create(typeface, i3) : typefaceG;
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    public static Typeface b(Context context, CancellationSignal cancellationSignal, b.f[] fVarArr, int i3) {
        return f2656a.c(context, cancellationSignal, fVarArr, i3);
    }

    public static Typeface c(Context context, c.a aVar, Resources resources, int i3, int i4, f.a aVar2, Handler handler, boolean z3) {
        Typeface typefaceB;
        if (aVar instanceof c.d) {
            c.d dVar = (c.d) aVar;
            boolean z4 = false;
            if (!z3 ? aVar2 == null : dVar.a() == 0) {
                z4 = true;
            }
            typefaceB = i0.b.g(context, dVar.b(), aVar2, handler, z4, z3 ? dVar.c() : -1, i4);
        } else {
            typefaceB = f2656a.b(context, (c.b) aVar, resources, i4);
            if (aVar2 != null) {
                if (typefaceB != null) {
                    aVar2.b(typefaceB, handler);
                } else {
                    aVar2.a(-3, handler);
                }
            }
        }
        if (typefaceB != null) {
            f2657b.d(e(resources, i3, i4), typefaceB);
        }
        return typefaceB;
    }

    public static Typeface d(Context context, Resources resources, int i3, String str, int i4) {
        Typeface typefaceE = f2656a.e(context, resources, i3, str, i4);
        if (typefaceE != null) {
            f2657b.d(e(resources, i3, i4), typefaceE);
        }
        return typefaceE;
    }

    public static String e(Resources resources, int i3, int i4) {
        return resources.getResourcePackageName(i3) + "-" + i3 + "-" + i4;
    }

    public static Typeface f(Resources resources, int i3, int i4) {
        return f2657b.c(e(resources, i3, i4));
    }

    public static Typeface g(Context context, Typeface typeface, int i3) {
        j jVar = f2656a;
        c.b bVarI = jVar.i(typeface);
        if (bVarI == null) {
            return null;
        }
        return jVar.b(context, bVarI, context.getResources(), i3);
    }
}
