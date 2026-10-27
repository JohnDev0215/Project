package b.g.c;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import b.g.b.c.c;
import b.g.b.c.f;
import b.g.g.b;

/* JADX INFO: compiled from: TypefaceCompat.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"NewApi"})
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j f986a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b.e.e<String, Typeface> f987b;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            f986a = new i();
        } else if (i >= 28) {
            f986a = new h();
        } else if (i >= 26) {
            f986a = new g();
        } else if (i >= 24 && f.m()) {
            f986a = new f();
        } else if (i >= 21) {
            f986a = new e();
        } else {
            f986a = new j();
        }
        f987b = new b.e.e<>(16);
    }

    public static Typeface a(Context context, Typeface typeface, int i) {
        Typeface typefaceG;
        if (context != null) {
            return (Build.VERSION.SDK_INT >= 21 || (typefaceG = g(context, typeface, i)) == null) ? Typeface.create(typeface, i) : typefaceG;
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    public static Typeface b(Context context, CancellationSignal cancellationSignal, b.f[] fVarArr, int i) {
        return f986a.c(context, cancellationSignal, fVarArr, i);
    }

    public static Typeface c(Context context, c.a aVar, Resources resources, int i, int i2, f.a aVar2, Handler handler, boolean z) {
        Typeface typefaceB;
        if (aVar instanceof c.d) {
            c.d dVar = (c.d) aVar;
            boolean z2 = false;
            if (!z ? aVar2 == null : dVar.a() == 0) {
                z2 = true;
            }
            typefaceB = b.g.g.b.g(context, dVar.b(), aVar2, handler, z2, z ? dVar.c() : -1, i2);
        } else {
            typefaceB = f986a.b(context, (c.b) aVar, resources, i2);
            if (aVar2 != null) {
                if (typefaceB != null) {
                    aVar2.b(typefaceB, handler);
                } else {
                    aVar2.a(-3, handler);
                }
            }
        }
        if (typefaceB != null) {
            f987b.d(e(resources, i, i2), typefaceB);
        }
        return typefaceB;
    }

    public static Typeface d(Context context, Resources resources, int i, String str, int i2) {
        Typeface typefaceE = f986a.e(context, resources, i, str, i2);
        if (typefaceE != null) {
            f987b.d(e(resources, i, i2), typefaceE);
        }
        return typefaceE;
    }

    public static String e(Resources resources, int i, int i2) {
        return resources.getResourcePackageName(i) + "-" + i + "-" + i2;
    }

    public static Typeface f(Resources resources, int i, int i2) {
        return f987b.c(e(resources, i, i2));
    }

    public static Typeface g(Context context, Typeface typeface, int i) {
        j jVar = f986a;
        c.b bVarI = jVar.i(typeface);
        if (bVarI == null) {
            return null;
        }
        return jVar.b(context, bVarI, context.getResources(), i);
    }
}
