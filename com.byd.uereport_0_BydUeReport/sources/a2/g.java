package a2;

import android.graphics.Path;
import android.graphics.PointF;
import java.util.List;
import q1.k;

/* JADX INFO: compiled from: MiscUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static PointF f69a = new PointF();

    public static PointF a(PointF pointF, PointF pointF2) {
        return new PointF(pointF.x + pointF2.x, pointF.y + pointF2.y);
    }

    public static float b(float f8, float f9, float f10) {
        return Math.max(f9, Math.min(f10, f8));
    }

    public static int c(int i8, int i9, int i10) {
        return Math.max(i9, Math.min(i10, i8));
    }

    public static boolean d(float f8, float f9, float f10) {
        return f8 >= f9 && f8 <= f10;
    }

    public static int e(int i8, int i9) {
        int i10 = i8 / i9;
        return (((i8 ^ i9) >= 0) || i8 % i9 == 0) ? i10 : i10 - 1;
    }

    public static int f(float f8, float f9) {
        return g((int) f8, (int) f9);
    }

    public static int g(int i8, int i9) {
        return i8 - (i9 * e(i8, i9));
    }

    public static void h(v1.h hVar, Path path) {
        path.reset();
        PointF pointFB = hVar.b();
        path.moveTo(pointFB.x, pointFB.y);
        f69a.set(pointFB.x, pointFB.y);
        for (int i8 = 0; i8 < hVar.a().size(); i8++) {
            t1.a aVar = hVar.a().get(i8);
            PointF pointFA = aVar.a();
            PointF pointFB2 = aVar.b();
            PointF pointFC = aVar.c();
            if (pointFA.equals(f69a) && pointFB2.equals(pointFC)) {
                path.lineTo(pointFC.x, pointFC.y);
            } else {
                path.cubicTo(pointFA.x, pointFA.y, pointFB2.x, pointFB2.y, pointFC.x, pointFC.y);
            }
            f69a.set(pointFC.x, pointFC.y);
        }
        if (hVar.d()) {
            path.close();
        }
    }

    public static double i(double d8, double d9, double d10) {
        return d8 + (d10 * (d9 - d8));
    }

    public static float j(float f8, float f9, float f10) {
        return f8 + (f10 * (f9 - f8));
    }

    public static int k(int i8, int i9, float f8) {
        return (int) (i8 + (f8 * (i9 - i8)));
    }

    public static void l(t1.d dVar, int i8, List<t1.d> list, t1.d dVar2, k kVar) {
        if (dVar.c(kVar.h(), i8)) {
            list.add(dVar2.a(kVar.h()).i(kVar));
        }
    }
}
