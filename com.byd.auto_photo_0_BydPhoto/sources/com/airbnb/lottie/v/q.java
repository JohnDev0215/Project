package com.airbnb.lottie.v;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.airbnb.lottie.v.k0.c;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
class q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static SparseArrayCompat<WeakReference<Interpolator>> f951b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Interpolator f950a = new LinearInterpolator();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static c.a f952c = c.a.a("t", "s", "e", "o", "i", "h", "to", "ti");

    q() {
    }

    @Nullable
    private static WeakReference<Interpolator> a(int i) {
        WeakReference<Interpolator> weakReference;
        synchronized (q.class) {
            weakReference = e().get(i);
        }
        return weakReference;
    }

    static <T> com.airbnb.lottie.x.a<T> b(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar, float f, j0<T> j0Var, boolean z) {
        return z ? c(dVar, cVar, f, j0Var) : d(cVar, f, j0Var);
    }

    private static <T> com.airbnb.lottie.x.a<T> c(com.airbnb.lottie.d dVar, com.airbnb.lottie.v.k0.c cVar, float f, j0<T> j0Var) {
        Interpolator interpolator;
        cVar.l();
        PointF pointFE = null;
        PointF pointFE2 = null;
        T tA = null;
        T tA2 = null;
        PointF pointFE3 = null;
        PointF pointFE4 = null;
        float fR = 0.0f;
        while (true) {
            boolean z = false;
            while (cVar.p()) {
                switch (cVar.y(f952c)) {
                    case 0:
                        fR = (float) cVar.r();
                        break;
                    case 1:
                        tA = j0Var.a(cVar, f);
                        break;
                    case 2:
                        tA2 = j0Var.a(cVar, f);
                        break;
                    case 3:
                        pointFE = p.e(cVar, f);
                        break;
                    case 4:
                        pointFE2 = p.e(cVar, f);
                        break;
                    case 5:
                        if (cVar.s() == 1) {
                            z = true;
                        }
                        break;
                    case 6:
                        pointFE3 = p.e(cVar, f);
                        break;
                    case 7:
                        pointFE4 = p.e(cVar, f);
                        break;
                    default:
                        cVar.A();
                        break;
                }
            }
            cVar.n();
            if (z) {
                interpolator = f950a;
                tA2 = tA;
            } else if (pointFE == null || pointFE2 == null) {
                interpolator = f950a;
            } else {
                float f2 = -f;
                pointFE.x = com.airbnb.lottie.w.g.b(pointFE.x, f2, f);
                pointFE.y = com.airbnb.lottie.w.g.b(pointFE.y, -100.0f, 100.0f);
                pointFE2.x = com.airbnb.lottie.w.g.b(pointFE2.x, f2, f);
                float fB = com.airbnb.lottie.w.g.b(pointFE2.y, -100.0f, 100.0f);
                pointFE2.y = fB;
                int i = com.airbnb.lottie.w.h.i(pointFE.x, pointFE.y, pointFE2.x, fB);
                WeakReference<Interpolator> weakReferenceA = a(i);
                Interpolator interpolatorCreate = weakReferenceA != null ? weakReferenceA.get() : null;
                if (weakReferenceA == null || interpolatorCreate == null) {
                    interpolatorCreate = PathInterpolatorCompat.create(pointFE.x / f, pointFE.y / f, pointFE2.x / f, pointFE2.y / f);
                    try {
                        f(i, new WeakReference(interpolatorCreate));
                    } catch (ArrayIndexOutOfBoundsException unused) {
                    }
                }
                interpolator = interpolatorCreate;
            }
            com.airbnb.lottie.x.a<T> aVar = new com.airbnb.lottie.x.a<>(dVar, tA, tA2, interpolator, fR, null);
            aVar.m = pointFE3;
            aVar.n = pointFE4;
            return aVar;
        }
    }

    private static <T> com.airbnb.lottie.x.a<T> d(com.airbnb.lottie.v.k0.c cVar, float f, j0<T> j0Var) {
        return new com.airbnb.lottie.x.a<>(j0Var.a(cVar, f));
    }

    private static SparseArrayCompat<WeakReference<Interpolator>> e() {
        if (f951b == null) {
            f951b = new SparseArrayCompat<>();
        }
        return f951b;
    }

    private static void f(int i, WeakReference<Interpolator> weakReference) {
        synchronized (q.class) {
            f951b.put(i, weakReference);
        }
    }
}
