package y1;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: KeyframeParser.java */
/* JADX INFO: loaded from: classes.dex */
public class q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static v.h<WeakReference<Interpolator>> f9211b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Interpolator f9210a = new LinearInterpolator();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static JsonReader.a f9212c = JsonReader.a.a("t", "s", "e", "o", "i", "h", "to", "ti");

    public static WeakReference<Interpolator> a(int i8) {
        WeakReference<Interpolator> weakReferenceE;
        synchronized (q.class) {
            weakReferenceE = e().e(i8);
        }
        return weakReferenceE;
    }

    public static <T> b2.a<T> b(JsonReader jsonReader, o1.e eVar, float f8, j0<T> j0Var, boolean z7) throws IOException {
        return z7 ? c(eVar, jsonReader, f8, j0Var) : d(jsonReader, f8, j0Var);
    }

    public static <T> b2.a<T> c(o1.e eVar, JsonReader jsonReader, float f8, j0<T> j0Var) throws IOException {
        Interpolator interpolator;
        T t7;
        Interpolator interpolatorA;
        jsonReader.i();
        boolean z7 = false;
        float fQ = 0.0f;
        PointF pointFE = null;
        PointF pointFE2 = null;
        T tA = null;
        T tA2 = null;
        PointF pointFE3 = null;
        PointF pointFE4 = null;
        while (jsonReader.n()) {
            switch (jsonReader.C(f9212c)) {
                case 0:
                    fQ = (float) jsonReader.q();
                    break;
                case 1:
                    tA2 = j0Var.a(jsonReader, f8);
                    break;
                case 2:
                    tA = j0Var.a(jsonReader, f8);
                    break;
                case 3:
                    pointFE = p.e(jsonReader, f8);
                    break;
                case 4:
                    pointFE2 = p.e(jsonReader, f8);
                    break;
                case 5:
                    z7 = jsonReader.r() == 1;
                    break;
                case 6:
                    pointFE4 = p.e(jsonReader, f8);
                    break;
                case 7:
                    pointFE3 = p.e(jsonReader, f8);
                    break;
                default:
                    jsonReader.F();
                    break;
            }
        }
        jsonReader.m();
        if (z7) {
            interpolator = f9210a;
            t7 = tA2;
        } else {
            if (pointFE == null || pointFE2 == null) {
                interpolator = f9210a;
            } else {
                float f9 = -f8;
                pointFE.x = a2.g.b(pointFE.x, f9, f8);
                pointFE.y = a2.g.b(pointFE.y, -100.0f, 100.0f);
                pointFE2.x = a2.g.b(pointFE2.x, f9, f8);
                float fB = a2.g.b(pointFE2.y, -100.0f, 100.0f);
                pointFE2.y = fB;
                int i8 = a2.h.i(pointFE.x, pointFE.y, pointFE2.x, fB);
                WeakReference<Interpolator> weakReferenceA = a(i8);
                Interpolator interpolator2 = weakReferenceA != null ? weakReferenceA.get() : null;
                if (weakReferenceA == null || interpolator2 == null) {
                    pointFE.x /= f8;
                    pointFE.y /= f8;
                    float f10 = pointFE2.x / f8;
                    pointFE2.x = f10;
                    float f11 = pointFE2.y / f8;
                    pointFE2.y = f11;
                    try {
                        interpolatorA = q0.b.a(pointFE.x, pointFE.y, f10, f11);
                    } catch (IllegalArgumentException e8) {
                        interpolatorA = e8.getMessage().equals("The Path cannot loop back on itself.") ? q0.b.a(Math.min(pointFE.x, 1.0f), pointFE.y, Math.max(pointFE2.x, 0.0f), pointFE2.y) : new LinearInterpolator();
                    }
                    interpolator2 = interpolatorA;
                    try {
                        f(i8, new WeakReference(interpolator2));
                    } catch (ArrayIndexOutOfBoundsException unused) {
                    }
                }
                interpolator = interpolator2;
            }
            t7 = tA;
        }
        b2.a<T> aVar = new b2.a<>(eVar, tA2, t7, interpolator, fQ, null);
        aVar.f2056m = pointFE4;
        aVar.f2057n = pointFE3;
        return aVar;
    }

    public static <T> b2.a<T> d(JsonReader jsonReader, float f8, j0<T> j0Var) throws IOException {
        return new b2.a<>(j0Var.a(jsonReader, f8));
    }

    public static v.h<WeakReference<Interpolator>> e() {
        if (f9211b == null) {
            f9211b = new v.h<>();
        }
        return f9211b;
    }

    public static void f(int i8, WeakReference<Interpolator> weakReference) {
        synchronized (q.class) {
            f9211b.j(i8, weakReference);
        }
    }
}
