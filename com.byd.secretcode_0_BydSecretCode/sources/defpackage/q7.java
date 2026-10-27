package defpackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import defpackage.i7;
import defpackage.k8;
import defpackage.l7;

/* JADX INFO: compiled from: TypefaceCompat.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"NewApi"})
public class q7 {
    public static final w7 a;
    public static final e4<String, Typeface> b;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            a = new v7();
        } else if (i >= 28) {
            a = new u7();
        } else if (i >= 26) {
            a = new t7();
        } else if (i >= 24 && s7.m()) {
            a = new s7();
        } else if (i >= 21) {
            a = new r7();
        } else {
            a = new w7();
        }
        b = new e4<>(16);
    }

    public static Typeface a(Context context, Typeface typeface, int i) {
        Typeface typefaceG;
        if (context != null) {
            return (Build.VERSION.SDK_INT >= 21 || (typefaceG = g(context, typeface, i)) == null) ? Typeface.create(typeface, i) : typefaceG;
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    public static Typeface b(Context context, CancellationSignal cancellationSignal, k8.f[] fVarArr, int i) {
        return a.c(context, cancellationSignal, fVarArr, i);
    }

    public static Typeface c(Context context, i7.a aVar, Resources resources, int i, int i2, l7.a aVar2, Handler handler, boolean z) {
        Typeface typefaceB;
        if (aVar instanceof i7.d) {
            i7.d dVar = (i7.d) aVar;
            boolean z2 = false;
            if (!z ? aVar2 == null : dVar.a() == 0) {
                z2 = true;
            }
            typefaceB = k8.g(context, dVar.b(), aVar2, handler, z2, z ? dVar.c() : -1, i2);
        } else {
            typefaceB = a.b(context, (i7.b) aVar, resources, i2);
            if (aVar2 != null) {
                if (typefaceB != null) {
                    aVar2.b(typefaceB, handler);
                } else {
                    aVar2.a(-3, handler);
                }
            }
        }
        if (typefaceB != null) {
            b.d(e(resources, i, i2), typefaceB);
        }
        return typefaceB;
    }

    public static Typeface d(Context context, Resources resources, int i, String str, int i2) {
        Typeface typefaceE = a.e(context, resources, i, str, i2);
        if (typefaceE != null) {
            b.d(e(resources, i, i2), typefaceE);
        }
        return typefaceE;
    }

    public static String e(Resources resources, int i, int i2) {
        return resources.getResourcePackageName(i) + "-" + i + "-" + i2;
    }

    public static Typeface f(Resources resources, int i, int i2) {
        return b.c(e(resources, i, i2));
    }

    public static Typeface g(Context context, Typeface typeface, int i) {
        w7 w7Var = a;
        i7.b bVarI = w7Var.i(typeface);
        if (bVarI == null) {
            return null;
        }
        return w7Var.b(context, bVarI, context.getResources(), i);
    }
}
