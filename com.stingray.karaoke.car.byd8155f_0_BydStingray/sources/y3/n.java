package Y3;

import android.graphics.Color;
import android.graphics.PointF;
import java.util.ArrayList;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public abstract class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final io.sentry.internal.debugmeta.c f10689a = io.sentry.internal.debugmeta.c.Q("x", "y");

    public static int a(Z3.a aVar) {
        aVar.b();
        int iQ = (int) (aVar.q() * 255.0d);
        int iQ2 = (int) (aVar.q() * 255.0d);
        int iQ3 = (int) (aVar.q() * 255.0d);
        while (aVar.m()) {
            aVar.I();
        }
        aVar.i();
        return Color.argb(255, iQ, iQ2, iQ3);
    }

    public static PointF b(Z3.a aVar, float f10) {
        int iC = AbstractC2641j.c(aVar.z());
        if (iC == 0) {
            aVar.b();
            float fQ = (float) aVar.q();
            float fQ2 = (float) aVar.q();
            while (aVar.z() != 2) {
                aVar.I();
            }
            aVar.i();
            return new PointF(fQ * f10, fQ2 * f10);
        }
        if (iC != 2) {
            if (iC != 6) {
                throw new IllegalArgumentException("Unknown point starts with ".concat(M5.b.v(aVar.z())));
            }
            float fQ3 = (float) aVar.q();
            float fQ4 = (float) aVar.q();
            while (aVar.m()) {
                aVar.I();
            }
            return new PointF(fQ3 * f10, fQ4 * f10);
        }
        aVar.g();
        float fD = 0.0f;
        float fD2 = 0.0f;
        while (aVar.m()) {
            int iF = aVar.F(f10689a);
            if (iF == 0) {
                fD = d(aVar);
            } else if (iF != 1) {
                aVar.G();
                aVar.I();
            } else {
                fD2 = d(aVar);
            }
        }
        aVar.j();
        return new PointF(fD * f10, fD2 * f10);
    }

    public static ArrayList c(Z3.a aVar, float f10) {
        ArrayList arrayList = new ArrayList();
        aVar.b();
        while (aVar.z() == 1) {
            aVar.b();
            arrayList.add(b(aVar, f10));
            aVar.i();
        }
        aVar.i();
        return arrayList;
    }

    public static float d(Z3.a aVar) {
        int iZ = aVar.z();
        int iC = AbstractC2641j.c(iZ);
        if (iC != 0) {
            if (iC == 6) {
                return (float) aVar.q();
            }
            throw new IllegalArgumentException("Unknown value for token of type ".concat(M5.b.v(iZ)));
        }
        aVar.b();
        float fQ = (float) aVar.q();
        while (aVar.m()) {
            aVar.I();
        }
        aVar.i();
        return fQ;
    }
}
