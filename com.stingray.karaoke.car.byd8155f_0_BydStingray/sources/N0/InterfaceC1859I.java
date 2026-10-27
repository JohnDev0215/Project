package n0;

import android.graphics.Path;
import android.graphics.RectF;
import m0.AbstractC1792a;
import x.AbstractC2641j;

/* JADX INFO: renamed from: n0.I, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public interface InterfaceC1859I {
    static void a(InterfaceC1859I interfaceC1859I, m0.e eVar) {
        Path.Direction direction;
        C1880i c1880i = (C1880i) interfaceC1859I;
        if (c1880i.f19437b == null) {
            c1880i.f19437b = new RectF();
        }
        RectF rectF = c1880i.f19437b;
        A7.m.c(rectF);
        float f10 = eVar.d;
        rectF.set(eVar.f18884a, eVar.f18885b, eVar.c, f10);
        if (c1880i.c == null) {
            c1880i.c = new float[8];
        }
        float[] fArr = c1880i.c;
        A7.m.c(fArr);
        long j3 = eVar.f18886e;
        fArr[0] = AbstractC1792a.b(j3);
        fArr[1] = AbstractC1792a.c(j3);
        long j10 = eVar.f18887f;
        fArr[2] = AbstractC1792a.b(j10);
        fArr[3] = AbstractC1792a.c(j10);
        long j11 = eVar.f18888g;
        fArr[4] = AbstractC1792a.b(j11);
        fArr[5] = AbstractC1792a.c(j11);
        long j12 = eVar.f18889h;
        fArr[6] = AbstractC1792a.b(j12);
        fArr[7] = AbstractC1792a.c(j12);
        RectF rectF2 = c1880i.f19437b;
        A7.m.c(rectF2);
        float[] fArr2 = c1880i.c;
        A7.m.c(fArr2);
        int iC = AbstractC2641j.c(1);
        if (iC == 0) {
            direction = Path.Direction.CCW;
        } else {
            if (iC != 1) {
                throw new A9.k();
            }
            direction = Path.Direction.CW;
        }
        c1880i.f19436a.addRoundRect(rectF2, fArr2, direction);
    }

    static void b(InterfaceC1859I interfaceC1859I, m0.d dVar) {
        Path.Direction direction;
        C1880i c1880i = (C1880i) interfaceC1859I;
        float f10 = dVar.f18882a;
        if (!Float.isNaN(f10)) {
            float f11 = dVar.f18883b;
            if (!Float.isNaN(f11)) {
                float f12 = dVar.c;
                if (!Float.isNaN(f12)) {
                    float f13 = dVar.d;
                    if (!Float.isNaN(f13)) {
                        if (c1880i.f19437b == null) {
                            c1880i.f19437b = new RectF();
                        }
                        RectF rectF = c1880i.f19437b;
                        A7.m.c(rectF);
                        rectF.set(f10, f11, f12, f13);
                        RectF rectF2 = c1880i.f19437b;
                        A7.m.c(rectF2);
                        int iC = AbstractC2641j.c(1);
                        if (iC == 0) {
                            direction = Path.Direction.CCW;
                        } else {
                            if (iC != 1) {
                                throw new A9.k();
                            }
                            direction = Path.Direction.CW;
                        }
                        c1880i.f19436a.addRect(rectF2, direction);
                        return;
                    }
                }
            }
        }
        throw new IllegalStateException("Invalid rectangle, make sure no value is NaN");
    }
}
