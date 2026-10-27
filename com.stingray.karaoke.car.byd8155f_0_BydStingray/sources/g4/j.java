package g4;

import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import l3.P;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f14904a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f14905b;
    public final s4.a c;
    public final P d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f14906e;

    public j(Class cls, Class cls2, Class cls3, List list, s4.a aVar, P p10) {
        this.f14904a = cls;
        this.f14905b = list;
        this.c = aVar;
        this.d = p10;
        this.f14906e = "Failed DecodePath{" + cls.getSimpleName() + "->" + cls2.getSimpleName() + "->" + cls3.getSimpleName() + "}";
    }

    public final x a(int i7, int i10, C1.j jVar, com.bumptech.glide.load.data.g gVar, e4.h hVar) {
        x xVarA;
        e4.l lVar;
        int iT;
        boolean z6;
        boolean z10;
        Object eVar;
        P p10 = this.d;
        List list = (List) p10.i();
        try {
            x xVarB = b(gVar, i7, i10, hVar, list);
            p10.B(list);
            i iVar = (i) jVar.f944m;
            iVar.getClass();
            Class<?> cls = xVarB.get().getClass();
            int i11 = jVar.l;
            h hVar2 = iVar.l;
            e4.k kVarN = null;
            if (i11 != 4) {
                e4.l lVarE = hVar2.e(cls);
                lVar = lVarE;
                xVarA = lVarE.a(iVar.f14896s, xVarB, iVar.f14900w, iVar.f14901x);
            } else {
                xVarA = xVarB;
                lVar = null;
            }
            if (!xVarB.equals(xVarA)) {
                xVarB.e();
            }
            if (hVar2.c.a().d.n(xVarA.d()) != null) {
                com.bumptech.glide.h hVarA = hVar2.c.a();
                hVarA.getClass();
                kVarN = hVarA.d.n(xVarA.d());
                if (kVarN == null) {
                    throw new com.bumptech.glide.g(xVarA.d());
                }
                iT = kVarN.t(iVar.f14903z);
            } else {
                iT = 3;
            }
            e4.k kVar = kVarN;
            e4.e eVar2 = iVar.f14880F;
            ArrayList arrayListB = hVar2.b();
            int size = arrayListB.size();
            int i12 = 0;
            while (true) {
                if (i12 >= size) {
                    z6 = false;
                    break;
                }
                if (((k4.p) arrayListB.get(i12)).f18205a.equals(eVar2)) {
                    z6 = true;
                    break;
                }
                i12++;
            }
            switch (iVar.f14902y.f14908a) {
                default:
                    z10 = true;
                    if (((z6 || i11 != 3) && i11 != 1) || iT != 2) {
                    }
                case 0:
                case 1:
                    z10 = false;
                    break;
            }
            if (z10) {
                if (kVar == null) {
                    throw new com.bumptech.glide.g(xVarA.get().getClass());
                }
                int iC = AbstractC2641j.c(iT);
                if (iC == 0) {
                    eVar = new e(iVar.f14880F, iVar.f14897t);
                } else {
                    if (iC != 1) {
                        throw new IllegalArgumentException("Unknown strategy: ".concat(iT != 1 ? iT != 2 ? iT != 3 ? "null" : "NONE" : "TRANSFORMED" : "SOURCE"));
                    }
                    eVar = new z(hVar2.c.f12676a, iVar.f14880F, iVar.f14897t, iVar.f14900w, iVar.f14901x, lVar, cls, iVar.f14903z);
                }
                w wVar = (w) w.f14958p.i();
                wVar.o = false;
                wVar.f14960n = true;
                wVar.f14959m = xVarA;
                P p11 = iVar.f14894q;
                p11.f18537m = eVar;
                p11.f18538n = kVar;
                p11.o = wVar;
                xVarA = wVar;
            }
            return this.c.f(xVarA, hVar);
        } catch (Throwable th) {
            p10.B(list);
            throw th;
        }
    }

    public final x b(com.bumptech.glide.load.data.g gVar, int i7, int i10, e4.h hVar, List list) throws t {
        List list2 = this.f14905b;
        int size = list2.size();
        x xVarA = null;
        for (int i11 = 0; i11 < size; i11++) {
            e4.j jVar = (e4.j) list2.get(i11);
            try {
                if (jVar.b(gVar.a(), hVar)) {
                    xVarA = jVar.a(gVar.a(), i7, i10, hVar);
                }
            } catch (IOException | OutOfMemoryError | RuntimeException e10) {
                if (Log.isLoggable("DecodePath", 2)) {
                    Log.v("DecodePath", "Failed to decode data for " + jVar, e10);
                }
                list.add(e10);
            }
            if (xVarA != null) {
                break;
            }
        }
        if (xVarA != null) {
            return xVarA;
        }
        throw new t(this.f14906e, new ArrayList(list));
    }

    public final String toString() {
        return "DecodePath{ dataClass=" + this.f14904a + ", decoders=" + this.f14905b + ", transcoder=" + this.c + '}';
    }
}
