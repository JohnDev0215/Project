package Y3;

import a4.AbstractC0925f;
import android.graphics.PointF;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public final class x implements D {
    public static final x l = new x();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final io.sentry.internal.debugmeta.c f10702m = io.sentry.internal.debugmeta.c.Q("c", "v", "i", "o");

    @Override // Y3.D
    public final Object d(Z3.a aVar, float f10) {
        if (aVar.z() == 1) {
            aVar.b();
        }
        aVar.g();
        ArrayList arrayListC = null;
        ArrayList arrayListC2 = null;
        ArrayList arrayListC3 = null;
        boolean zO = false;
        while (aVar.m()) {
            int iF = aVar.F(f10702m);
            if (iF == 0) {
                zO = aVar.o();
            } else if (iF == 1) {
                arrayListC = n.c(aVar, f10);
            } else if (iF == 2) {
                arrayListC2 = n.c(aVar, f10);
            } else if (iF != 3) {
                aVar.G();
                aVar.I();
            } else {
                arrayListC3 = n.c(aVar, f10);
            }
        }
        aVar.j();
        if (aVar.z() == 2) {
            aVar.i();
        }
        if (arrayListC == null || arrayListC2 == null || arrayListC3 == null) {
            throw new IllegalArgumentException("Shape data was missing information.");
        }
        if (arrayListC.isEmpty()) {
            return new W3.k(new PointF(), false, Collections.emptyList());
        }
        int size = arrayListC.size();
        PointF pointF = (PointF) arrayListC.get(0);
        ArrayList arrayList = new ArrayList(size);
        for (int i7 = 1; i7 < size; i7++) {
            PointF pointF2 = (PointF) arrayListC.get(i7);
            int i10 = i7 - 1;
            arrayList.add(new U3.a(AbstractC0925f.a((PointF) arrayListC.get(i10), (PointF) arrayListC3.get(i10)), AbstractC0925f.a(pointF2, (PointF) arrayListC2.get(i7)), pointF2));
        }
        if (zO) {
            PointF pointF3 = (PointF) arrayListC.get(0);
            int i11 = size - 1;
            arrayList.add(new U3.a(AbstractC0925f.a((PointF) arrayListC.get(i11), (PointF) arrayListC3.get(i11)), AbstractC0925f.a(pointF3, (PointF) arrayListC2.get(0)), pointF3));
        }
        return new W3.k(pointF, zO, arrayList);
    }
}
