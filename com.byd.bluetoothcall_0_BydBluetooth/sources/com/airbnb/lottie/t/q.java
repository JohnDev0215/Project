package com.airbnb.lottie.t;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: KeyframeParser.java */
/* JADX INFO: loaded from: classes.dex */
class q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static b.d.h<WeakReference<Interpolator>> f3169b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Interpolator f3168a = new LinearInterpolator();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static JsonReader.a f3170c = JsonReader.a.a("t", "s", "e", "o", "i", "h", "to", "ti");

    q() {
    }

    private static WeakReference<Interpolator> a(int i) {
        WeakReference<Interpolator> weakReferenceE;
        synchronized (q.class) {
            weakReferenceE = e().e(i);
        }
        return weakReferenceE;
    }

    static <T> com.airbnb.lottie.v.a<T> b(JsonReader jsonReader, com.airbnb.lottie.d dVar, float f, j0<T> j0Var, boolean z) throws IOException {
        return z ? c(dVar, jsonReader, f, j0Var) : d(jsonReader, f, j0Var);
    }

    private static <T> com.airbnb.lottie.v.a<T> c(com.airbnb.lottie.d dVar, JsonReader jsonReader, float f, j0<T> j0Var) throws IOException {
        Interpolator interpolator;
        jsonReader.m();
        PointF pointFE = null;
        PointF pointFE2 = null;
        T tA = null;
        T tA2 = null;
        PointF pointFE3 = null;
        PointF pointFE4 = null;
        float fS = 0.0f;
        while (true) {
            boolean z = false;
            while (jsonReader.q()) {
                switch (jsonReader.z(f3170c)) {
                    case 0:
                        fS = (float) jsonReader.s();
                        break;
                    case 1:
                        tA = j0Var.a(jsonReader, f);
                        break;
                    case 2:
                        tA2 = j0Var.a(jsonReader, f);
                        break;
                    case 3:
                        pointFE = p.e(jsonReader, f);
                        break;
                    case 4:
                        pointFE2 = p.e(jsonReader, f);
                        break;
                    case 5:
                        if (jsonReader.t() == 1) {
                            z = true;
                        }
                        break;
                    case 6:
                        pointFE3 = p.e(jsonReader, f);
                        break;
                    case 7:
                        pointFE4 = p.e(jsonReader, f);
                        break;
                    default:
                        jsonReader.B();
                        break;
                }
            }
            jsonReader.o();
            if (z) {
                interpolator = f3168a;
                tA2 = tA;
            } else if (pointFE == null || pointFE2 == null) {
                interpolator = f3168a;
            } else {
                float f2 = -f;
                pointFE.x = com.airbnb.lottie.u.g.b(pointFE.x, f2, f);
                pointFE.y = com.airbnb.lottie.u.g.b(pointFE.y, -100.0f, 100.0f);
                pointFE2.x = com.airbnb.lottie.u.g.b(pointFE2.x, f2, f);
                float fB = com.airbnb.lottie.u.g.b(pointFE2.y, -100.0f, 100.0f);
                pointFE2.y = fB;
                int i = com.airbnb.lottie.u.h.i(pointFE.x, pointFE.y, pointFE2.x, fB);
                WeakReference<Interpolator> weakReferenceA = a(i);
                Interpolator interpolatorA = weakReferenceA != null ? weakReferenceA.get() : null;
                if (weakReferenceA == null || interpolatorA == null) {
                    interpolatorA = b.g.k.d0.b.a(pointFE.x / f, pointFE.y / f, pointFE2.x / f, pointFE2.y / f);
                    try {
                        f(i, new WeakReference(interpolatorA));
                    } catch (ArrayIndexOutOfBoundsException unused) {
                    }
                }
                interpolator = interpolatorA;
            }
            com.airbnb.lottie.v.a<T> aVar = new com.airbnb.lottie.v.a<>(dVar, tA, tA2, interpolator, fS, null);
            aVar.m = pointFE3;
            aVar.n = pointFE4;
            return aVar;
        }
    }

    private static <T> com.airbnb.lottie.v.a<T> d(JsonReader jsonReader, float f, j0<T> j0Var) throws IOException {
        return new com.airbnb.lottie.v.a<>(j0Var.a(jsonReader, f));
    }

    private static b.d.h<WeakReference<Interpolator>> e() {
        if (f3169b == null) {
            f3169b = new b.d.h<>();
        }
        return f3169b;
    }

    private static void f(int i, WeakReference<Interpolator> weakReference) {
        synchronized (q.class) {
            f3169b.j(i, weakReference);
        }
    }
}
