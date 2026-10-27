package z;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import e0.f;
import y.a;
import y.b;

/* JADX INFO: compiled from: TypefaceCompat.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"NewApi"})
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final h f10026a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final n.e<String, Typeface> f10027b;

    /* JADX INFO: compiled from: TypefaceCompat.java */
    public static class a extends f.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private b.a f10028a;

        public a(b.a aVar) {
            this.f10028a = aVar;
        }

        @Override // e0.f.c
        public void a(int i7) {
            b.a aVar = this.f10028a;
            if (aVar != null) {
                aVar.d(i7);
            }
        }

        @Override // e0.f.c
        public void b(Typeface typeface) {
            b.a aVar = this.f10028a;
            if (aVar != null) {
                aVar.e(typeface);
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 29) {
            f10026a = new g();
        } else {
            f10026a = new f();
        }
        f10027b = new n.e<>(16);
    }

    public static Typeface a(Context context, Typeface typeface, int i7) {
        if (context != null) {
            return Typeface.create(typeface, i7);
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    public static Typeface b(Context context, CancellationSignal cancellationSignal, f.b[] bVarArr, int i7) {
        return f10026a.b(context, cancellationSignal, bVarArr, i7);
    }

    public static Typeface c(Context context, a.InterfaceC0173a interfaceC0173a, Resources resources, int i7, int i8, b.a aVar, Handler handler, boolean z6) {
        Typeface typefaceA;
        if (interfaceC0173a instanceof a.d) {
            a.d dVar = (a.d) interfaceC0173a;
            Typeface typefaceG = g(dVar.c());
            if (typefaceG != null) {
                if (aVar != null) {
                    aVar.b(typefaceG, handler);
                }
                return typefaceG;
            }
            boolean z7 = !z6 ? aVar != null : dVar.a() != 0;
            int iD = z6 ? dVar.d() : -1;
            typefaceA = e0.f.a(context, dVar.b(), i8, z7, iD, b.a.c(handler), new a(aVar));
        } else {
            typefaceA = f10026a.a(context, (a.b) interfaceC0173a, resources, i8);
            if (aVar != null) {
                if (typefaceA != null) {
                    aVar.b(typefaceA, handler);
                } else {
                    aVar.a(-3, handler);
                }
            }
        }
        if (typefaceA != null) {
            f10027b.d(e(resources, i7, i8), typefaceA);
        }
        return typefaceA;
    }

    public static Typeface d(Context context, Resources resources, int i7, String str, int i8) {
        Typeface typefaceC = f10026a.c(context, resources, i7, str, i8);
        if (typefaceC != null) {
            f10027b.d(e(resources, i7, i8), typefaceC);
        }
        return typefaceC;
    }

    private static String e(Resources resources, int i7, int i8) {
        return resources.getResourcePackageName(i7) + "-" + i7 + "-" + i8;
    }

    public static Typeface f(Resources resources, int i7, int i8) {
        return f10027b.c(e(resources, i7, i8));
    }

    private static Typeface g(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        Typeface typefaceCreate = Typeface.create(str, 0);
        Typeface typefaceCreate2 = Typeface.create(Typeface.DEFAULT, 0);
        if (typefaceCreate == null || typefaceCreate.equals(typefaceCreate2)) {
            return null;
        }
        return typefaceCreate;
    }
}
