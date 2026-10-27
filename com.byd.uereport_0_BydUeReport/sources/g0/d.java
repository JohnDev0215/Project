package g0;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import f0.c;
import f0.f;
import l0.b;

/* JADX INFO: compiled from: TypefaceCompat.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"NewApi"})
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j f4990a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final v.e<String, Typeface> f4991b;

    static {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 29) {
            f4990a = new i();
        } else if (i8 >= 28) {
            f4990a = new h();
        } else if (i8 >= 26) {
            f4990a = new g();
        } else if (i8 >= 24 && f.m()) {
            f4990a = new f();
        } else if (i8 >= 21) {
            f4990a = new e();
        } else {
            f4990a = new j();
        }
        f4991b = new v.e<>(16);
    }

    public static Typeface a(Context context, Typeface typeface, int i8) {
        Typeface typefaceG;
        if (context != null) {
            return (Build.VERSION.SDK_INT >= 21 || (typefaceG = g(context, typeface, i8)) == null) ? Typeface.create(typeface, i8) : typefaceG;
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    public static Typeface b(Context context, CancellationSignal cancellationSignal, b.f[] fVarArr, int i8) {
        return f4990a.c(context, cancellationSignal, fVarArr, i8);
    }

    public static Typeface c(Context context, c.a aVar, Resources resources, int i8, int i9, f.a aVar2, Handler handler, boolean z7) {
        Typeface typefaceB;
        if (aVar instanceof c.d) {
            c.d dVar = (c.d) aVar;
            boolean z8 = false;
            if (!z7 ? aVar2 == null : dVar.a() == 0) {
                z8 = true;
            }
            typefaceB = l0.b.g(context, dVar.b(), aVar2, handler, z8, z7 ? dVar.c() : -1, i9);
        } else {
            typefaceB = f4990a.b(context, (c.b) aVar, resources, i9);
            if (aVar2 != null) {
                if (typefaceB != null) {
                    aVar2.b(typefaceB, handler);
                } else {
                    aVar2.a(-3, handler);
                }
            }
        }
        if (typefaceB != null) {
            f4991b.d(e(resources, i8, i9), typefaceB);
        }
        return typefaceB;
    }

    public static Typeface d(Context context, Resources resources, int i8, String str, int i9) {
        Typeface typefaceE = f4990a.e(context, resources, i8, str, i9);
        if (typefaceE != null) {
            f4991b.d(e(resources, i8, i9), typefaceE);
        }
        return typefaceE;
    }

    public static String e(Resources resources, int i8, int i9) {
        return resources.getResourcePackageName(i8) + "-" + i8 + "-" + i9;
    }

    public static Typeface f(Resources resources, int i8, int i9) {
        return f4991b.c(e(resources, i8, i9));
    }

    public static Typeface g(Context context, Typeface typeface, int i8) {
        j jVar = f4990a;
        c.b bVarI = jVar.i(typeface);
        if (bVarI == null) {
            return null;
        }
        return jVar.b(context, bVarI, context.getResources(), i8);
    }
}
