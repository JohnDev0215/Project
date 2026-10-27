package a.g.d;

import a.g.h.b;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import androidx.core.content.c.c;
import androidx.core.content.c.f;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"NewApi"})
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final j f279a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final a.d.e<String, Typeface> f280b;

    static {
        int i = Build.VERSION.SDK_INT;
        f279a = i >= 29 ? new i() : i >= 28 ? new h() : i >= 26 ? new g() : (i < 24 || !f.m()) ? i >= 21 ? new e() : new j() : new f();
        f280b = new a.d.e<>(16);
    }

    public static Typeface a(Context context, Typeface typeface, int i) {
        Typeface typefaceG;
        if (context != null) {
            return (Build.VERSION.SDK_INT >= 21 || (typefaceG = g(context, typeface, i)) == null) ? Typeface.create(typeface, i) : typefaceG;
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    public static Typeface b(Context context, CancellationSignal cancellationSignal, b.f[] fVarArr, int i) {
        return f279a.c(context, cancellationSignal, fVarArr, i);
    }

    public static Typeface c(Context context, c.a aVar, Resources resources, int i, int i2, f.a aVar2, Handler handler, boolean z) {
        Typeface typefaceB;
        if (aVar instanceof c.d) {
            c.d dVar = (c.d) aVar;
            boolean z2 = false;
            if (!z ? aVar2 == null : dVar.a() == 0) {
                z2 = true;
            }
            typefaceB = a.g.h.b.g(context, dVar.b(), aVar2, handler, z2, z ? dVar.c() : -1, i2);
        } else {
            typefaceB = f279a.b(context, (c.b) aVar, resources, i2);
            if (aVar2 != null) {
                if (typefaceB != null) {
                    aVar2.b(typefaceB, handler);
                } else {
                    aVar2.a(-3, handler);
                }
            }
        }
        if (typefaceB != null) {
            f280b.d(e(resources, i, i2), typefaceB);
        }
        return typefaceB;
    }

    public static Typeface d(Context context, Resources resources, int i, String str, int i2) {
        Typeface typefaceE = f279a.e(context, resources, i, str, i2);
        if (typefaceE != null) {
            f280b.d(e(resources, i, i2), typefaceE);
        }
        return typefaceE;
    }

    private static String e(Resources resources, int i, int i2) {
        return resources.getResourcePackageName(i) + "-" + i + "-" + i2;
    }

    public static Typeface f(Resources resources, int i, int i2) {
        return f280b.c(e(resources, i, i2));
    }

    private static Typeface g(Context context, Typeface typeface, int i) {
        j jVar = f279a;
        c.b bVarI = jVar.i(typeface);
        if (bVarI == null) {
            return null;
        }
        return jVar.b(context, bVarI, context.getResources(), i);
    }
}
