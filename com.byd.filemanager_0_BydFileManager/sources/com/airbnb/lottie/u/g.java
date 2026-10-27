package com.airbnb.lottie.u;

import android.graphics.Path;
import android.graphics.PointF;
import com.airbnb.lottie.r.b.k;
import java.util.List;

/* JADX INFO: compiled from: MiscUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static PointF f2811a = new PointF();

    public static PointF a(PointF pointF, PointF pointF2) {
        return new PointF(pointF.x + pointF2.x, pointF.y + pointF2.y);
    }

    public static float b(float f, float f2, float f3) {
        return Math.max(f2, Math.min(f3, f));
    }

    public static int c(int i, int i2, int i3) {
        return Math.max(i2, Math.min(i3, i));
    }

    public static boolean d(float f, float f2, float f3) {
        return f >= f2 && f <= f3;
    }

    private static int e(int i, int i2) {
        int i3 = i / i2;
        return (((i ^ i2) >= 0) || i % i2 == 0) ? i3 : i3 - 1;
    }

    static int f(float f, float f2) {
        return g((int) f, (int) f2);
    }

    private static int g(int i, int i2) {
        return i - (i2 * e(i, i2));
    }

    public static void h(com.airbnb.lottie.model.content.h hVar, Path path) {
        path.reset();
        PointF pointFB = hVar.b();
        path.moveTo(pointFB.x, pointFB.y);
        f2811a.set(pointFB.x, pointFB.y);
        for (int i = 0; i < hVar.a().size(); i++) {
            com.airbnb.lottie.model.a aVar = hVar.a().get(i);
            PointF pointFA = aVar.a();
            PointF pointFB2 = aVar.b();
            PointF pointFC = aVar.c();
            if (pointFA.equals(f2811a) && pointFB2.equals(pointFC)) {
                path.lineTo(pointFC.x, pointFC.y);
            } else {
                path.cubicTo(pointFA.x, pointFA.y, pointFB2.x, pointFB2.y, pointFC.x, pointFC.y);
            }
            f2811a.set(pointFC.x, pointFC.y);
        }
        if (hVar.d()) {
            path.close();
        }
    }

    public static double i(double d2, double d3, double d4) {
        return d2 + (d4 * (d3 - d2));
    }

    public static float j(float f, float f2, float f3) {
        return f + (f3 * (f2 - f));
    }

    public static int k(int i, int i2, float f) {
        return (int) (i + (f * (i2 - i)));
    }

    public static void l(com.airbnb.lottie.model.d dVar, int i, List<com.airbnb.lottie.model.d> list, com.airbnb.lottie.model.d dVar2, k kVar) {
        if (dVar.c(kVar.i(), i)) {
            list.add(dVar2.a(kVar.i()).i(kVar));
        }
    }
}
