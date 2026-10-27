package Y3;

import a4.AbstractC0927h;
import b4.C0995a;
import java.util.ArrayList;

/* JADX INFO: renamed from: Y3.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0894a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final io.sentry.internal.debugmeta.c f10662a = io.sentry.internal.debugmeta.c.Q("k", "x", "y");

    public static A7.D a(Z3.b bVar, O3.a aVar) {
        ArrayList arrayList = new ArrayList();
        if (bVar.z() == 1) {
            bVar.b();
            while (bVar.m()) {
                arrayList.add(new R3.j(aVar, o.b(bVar, aVar, AbstractC0927h.c(), f.f10672p, bVar.z() == 3, false)));
            }
            bVar.i();
            p.b(arrayList);
        } else {
            arrayList.add(new C0995a(n.b(bVar, AbstractC0927h.c())));
        }
        return new A7.D(arrayList);
    }

    public static V3.e b(Z3.b bVar, O3.a aVar) {
        bVar.g();
        A7.D dA = null;
        V3.b bVarN = null;
        boolean z6 = false;
        V3.b bVarN2 = null;
        while (bVar.z() != 4) {
            int iF = bVar.F(f10662a);
            if (iF == 0) {
                dA = a(bVar, aVar);
            } else if (iF != 1) {
                if (iF != 2) {
                    bVar.G();
                    bVar.I();
                } else if (bVar.z() == 6) {
                    bVar.I();
                    z6 = true;
                } else {
                    bVarN = P8.n.N(bVar, aVar, true);
                }
            } else if (bVar.z() == 6) {
                bVar.I();
                z6 = true;
            } else {
                bVarN2 = P8.n.N(bVar, aVar, true);
            }
        }
        bVar.j();
        if (z6) {
            aVar.a("Lottie doesn't support expressions.");
        }
        return dA != null ? dA : new V3.c(bVarN2, bVarN);
    }
}
