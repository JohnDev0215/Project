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
    private static b.d.h<WeakReference<Interpolator>> f2789b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Interpolator f2788a = new LinearInterpolator();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static JsonReader.a f2790c = JsonReader.a.a("t", "s", "e", "o", "i", "h", "to", "ti");

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
        T t;
        Interpolator interpolatorA;
        jsonReader.l();
        boolean z = false;
        float fR = 0.0f;
        PointF pointFE = null;
        PointF pointFE2 = null;
        T tA = null;
        T tA2 = null;
        PointF pointFE3 = null;
        PointF pointFE4 = null;
        while (jsonReader.p()) {
            switch (jsonReader.y(f2790c)) {
                case 0:
                    fR = (float) jsonReader.r();
                    break;
                case 1:
                    tA2 = j0Var.a(jsonReader, f);
                    break;
                case 2:
                    tA = j0Var.a(jsonReader, f);
                    break;
                case 3:
                    pointFE = p.e(jsonReader, f);
                    break;
                case 4:
                    pointFE2 = p.e(jsonReader, f);
                    break;
                case 5:
                    z = jsonReader.s() == 1;
                    break;
                case 6:
                    pointFE4 = p.e(jsonReader, f);
                    break;
                case 7:
                    pointFE3 = p.e(jsonReader, f);
                    break;
                default:
                    jsonReader.A();
                    break;
            }
        }
        jsonReader.n();
        if (z) {
            interpolator = f2788a;
            t = tA2;
        } else {
            if (pointFE == null || pointFE2 == null) {
                interpolator = f2788a;
            } else {
                float f2 = -f;
                pointFE.x = com.airbnb.lottie.u.g.b(pointFE.x, f2, f);
                pointFE.y = com.airbnb.lottie.u.g.b(pointFE.y, -100.0f, 100.0f);
                pointFE2.x = com.airbnb.lottie.u.g.b(pointFE2.x, f2, f);
                float fB = com.airbnb.lottie.u.g.b(pointFE2.y, -100.0f, 100.0f);
                pointFE2.y = fB;
                int i = com.airbnb.lottie.u.h.i(pointFE.x, pointFE.y, pointFE2.x, fB);
                WeakReference<Interpolator> weakReferenceA = a(i);
                Interpolator interpolator2 = weakReferenceA != null ? weakReferenceA.get() : null;
                if (weakReferenceA == null || interpolator2 == null) {
                    pointFE.x /= f;
                    pointFE.y /= f;
                    float f3 = pointFE2.x / f;
                    pointFE2.x = f3;
                    float f4 = pointFE2.y / f;
                    pointFE2.y = f4;
                    try {
                        interpolatorA = b.g.k.d0.b.a(pointFE.x, pointFE.y, f3, f4);
                    } catch (IllegalArgumentException e2) {
                        interpolatorA = e2.getMessage().equals("The Path cannot loop back on itself.") ? b.g.k.d0.b.a(Math.min(pointFE.x, 1.0f), pointFE.y, Math.max(pointFE2.x, 0.0f), pointFE2.y) : new LinearInterpolator();
                    }
                    interpolator2 = interpolatorA;
                    try {
                        f(i, new WeakReference(interpolator2));
                    } catch (ArrayIndexOutOfBoundsException unused) {
                    }
                }
                interpolator = interpolator2;
            }
            t = tA;
        }
        com.airbnb.lottie.v.a<T> aVar = new com.airbnb.lottie.v.a<>(dVar, tA2, t, interpolator, fR, null);
        aVar.m = pointFE4;
        aVar.n = pointFE3;
        return aVar;
    }

    private static <T> com.airbnb.lottie.v.a<T> d(JsonReader jsonReader, float f, j0<T> j0Var) throws IOException {
        return new com.airbnb.lottie.v.a<>(j0Var.a(jsonReader, f));
    }

    private static b.d.h<WeakReference<Interpolator>> e() {
        if (f2789b == null) {
            f2789b = new b.d.h<>();
        }
        return f2789b;
    }

    private static void f(int i, WeakReference<Interpolator> weakReference) {
        synchronized (q.class) {
            f2789b.j(i, weakReference);
        }
    }
}
