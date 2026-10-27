package Y3;

import b4.C0995a;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final io.sentry.internal.debugmeta.c f10692a = io.sentry.internal.debugmeta.c.Q("k");

    public static ArrayList a(Z3.a aVar, O3.a aVar2, float f10, D d, boolean z6) {
        ArrayList arrayList = new ArrayList();
        if (aVar.z() == 6) {
            aVar2.a("Lottie doesn't support expressions.");
            return arrayList;
        }
        aVar.g();
        while (aVar.m()) {
            if (aVar.F(f10692a) != 0) {
                aVar.I();
            } else if (aVar.z() == 1) {
                aVar.b();
                if (aVar.z() == 7) {
                    arrayList.add(o.b(aVar, aVar2, f10, d, false, z6));
                } else {
                    while (aVar.m()) {
                        arrayList.add(o.b(aVar, aVar2, f10, d, true, z6));
                    }
                }
                aVar.i();
            } else {
                arrayList.add(o.b(aVar, aVar2, f10, d, false, z6));
            }
        }
        aVar.j();
        b(arrayList);
        return arrayList;
    }

    public static void b(ArrayList arrayList) {
        int i7;
        Object obj;
        int size = arrayList.size();
        int i10 = 0;
        while (true) {
            i7 = size - 1;
            if (i10 >= i7) {
                break;
            }
            C0995a c0995a = (C0995a) arrayList.get(i10);
            i10++;
            C0995a c0995a2 = (C0995a) arrayList.get(i10);
            c0995a.f12216h = Float.valueOf(c0995a2.f12215g);
            if (c0995a.c == null && (obj = c0995a2.f12212b) != null) {
                c0995a.c = obj;
                if (c0995a instanceof R3.j) {
                    ((R3.j) c0995a).d();
                }
            }
        }
        C0995a c0995a3 = (C0995a) arrayList.get(i7);
        if ((c0995a3.f12212b == null || c0995a3.c == null) && arrayList.size() > 1) {
            arrayList.remove(c0995a3);
        }
    }
}
