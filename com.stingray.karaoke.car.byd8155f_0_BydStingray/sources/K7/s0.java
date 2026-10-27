package K7;

import F8.C0277l;
import N1.C0420a;
import T.C0746i0;
import T7.C0793l;
import W7.AbstractC0839c;
import Z7.C0900c;
import c8.C1036a;
import c8.C1037b;
import c8.C1039d;
import g8.C1314d;
import h8.AbstractC1387d;
import h8.C1389f;
import h8.C1390g;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import n0.C1886o;
import n7.AbstractC1996k;
import n7.AbstractC1999n;
import o8.C2061f;
import w8.C2607b;

/* JADX INFO: loaded from: classes.dex */
public abstract class s0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap f3834a = new ConcurrentHashMap();

    public static final V7.f a(Class cls) {
        E8.l lVar;
        S7.b bVarH;
        S7.d dVarH;
        A7.m.f(cls, "<this>");
        ClassLoader classLoaderD = AbstractC0839c.d(cls);
        B0 b02 = new B0(classLoaderD);
        ConcurrentHashMap concurrentHashMap = f3834a;
        WeakReference weakReference = (WeakReference) concurrentHashMap.get(b02);
        if (weakReference != null) {
            V7.f fVar = (V7.f) weakReference.get();
            if (fVar != null) {
                return fVar;
            }
            concurrentHashMap.remove(b02, weakReference);
        }
        V7.b bVar = new V7.b(classLoaderD);
        ClassLoader classLoader = m7.q.class.getClassLoader();
        A7.m.e(classLoader, "Unit::class.java.classLoader");
        V7.b bVar2 = new V7.b(classLoader);
        V7.b bVar3 = new V7.b(classLoaderD);
        String str = "runtime module for " + classLoaderD;
        V7.e eVar = V7.e.f9551b;
        V7.e eVar2 = V7.e.c;
        A7.m.f(str, "moduleName");
        E8.l lVar2 = new E8.l("DeserializationComponentsForJava.ModuleData");
        P7.i iVar = new P7.i(lVar2);
        T7.z zVar = new T7.z(C2061f.g("<" + str + '>'), lVar2, iVar, 56);
        E8.n nVar = lVar2.f1860a;
        nVar.lock();
        try {
            if (iVar.f4797a == null) {
                iVar.f4797a = zVar;
                nVar.unlock();
                iVar.f5437f = new N7.l(zVar, 1);
                C1389f c1389f = new C1389f();
                C0420a c0420a = new C0420a();
                B8.t tVar = new B8.t(lVar2, zVar);
                C1390g c1390g = C1390g.c;
                a8.h hVar = a8.h.c;
                a8.h hVar2 = a8.h.f11213a;
                n7.u uVar = n7.u.l;
                C1886o c1886o = new C1886o(lVar2);
                Q7.O o = Q7.O.f7181n;
                Y7.a aVar = Y7.a.f10797a;
                N7.m mVar = new N7.m(zVar, tVar);
                Z7.t tVar2 = Z7.t.c;
                C0900c c0900c = new C0900c(tVar2);
                C1037b c1037b = C1037b.l;
                C1314d c1314d = new C1314d();
                Z7.m mVar2 = Z7.m.f10946a;
                G8.k.f2736b.getClass();
                G8.l lVar3 = G8.j.f2735b;
                B0 b03 = b02;
                ConcurrentHashMap concurrentHashMap2 = concurrentHashMap;
                C1039d c1039d = new C1039d(new C1036a(lVar2, bVar3, bVar, c1389f, hVar, eVar, hVar2, c1886o, eVar2, c0420a, c1390g, o, aVar, zVar, mVar, c0900c, c1314d, mVar2, c1037b, lVar3, tVar2, new C1390g()));
                n8.f fVar2 = n8.f.f20166g;
                A7.m.f(fVar2, "jvmMetadataVersion");
                M.t tVar3 = new M.t(bVar, c1389f);
                H0.b bVar4 = new H0.b(zVar, tVar, lVar2, bVar);
                bVar4.f2769r = fVar2;
                List listJ = AbstractC1387d.j(C0277l.f2472a);
                N7.i iVar2 = zVar.f8921p;
                P7.i iVar3 = iVar2 instanceof P7.i ? (P7.i) iVar2 : null;
                C1390g c1390g2 = C1390g.f15610b;
                if (iVar3 == null || (bVarH = iVar3.H()) == null) {
                    bVarH = S7.a.f8077b;
                }
                S7.b bVar5 = bVarH;
                if (iVar3 == null || (dVarH = iVar3.H()) == null) {
                    dVarH = S7.a.d;
                }
                B8.i iVar4 = new B8.i(lVar2, zVar, tVar3, bVar4, c1039d, eVar, c1390g2, uVar, tVar, bVar5, dVarH, n8.h.f20171a, lVar3, new C1886o(lVar2), listJ, 262144);
                c1389f.f15609a = iVar4;
                c0420a.l = new C2607b(c1039d, 0);
                P7.n nVarH = iVar.H();
                P7.n nVarH2 = iVar.H();
                C1886o c1886o2 = new C1886o(lVar2);
                A7.m.f(nVarH, "additionalClassPartsProvider");
                A7.m.f(nVarH2, "platformDependentDeclarationFilter");
                P7.o oVar = new P7.o(lVar2, bVar2, zVar);
                B2.O o4 = new B2.O(oVar, 3);
                C8.a aVar2 = C8.a.f1016m;
                oVar.c = new B8.i(lVar2, zVar, o4, new io.sentry.internal.debugmeta.c(zVar, tVar, aVar2), oVar, B8.l.f786a, B8.j.c, AbstractC1999n.D(new O7.a(lVar2, zVar), new P7.g(lVar2, zVar)), tVar, nVarH, nVarH2, aVar2.f465a, lVar3, c1886o2, null, 786432);
                zVar.f8924s = new C0746i0(AbstractC1996k.b0(new T7.z[]{zVar}));
                zVar.f8925t = new C0793l("CompositeProvider@RuntimeModuleData for " + zVar, AbstractC1999n.D(c1039d, oVar));
                V7.f fVar3 = new V7.f(iVar4, new l3.P(c1389f, bVar));
                while (true) {
                    B0 b04 = b03;
                    ConcurrentHashMap concurrentHashMap3 = concurrentHashMap2;
                    WeakReference weakReference2 = (WeakReference) concurrentHashMap3.putIfAbsent(b04, new WeakReference(fVar3));
                    if (weakReference2 == null) {
                        return fVar3;
                    }
                    V7.f fVar4 = (V7.f) weakReference2.get();
                    if (fVar4 != null) {
                        return fVar4;
                    }
                    concurrentHashMap3.remove(b04, weakReference2);
                    b03 = b04;
                    concurrentHashMap2 = concurrentHashMap3;
                }
            } else {
                lVar = lVar2;
                try {
                    throw new AssertionError("Built-ins module is already set: " + iVar.f4797a + " (attempting to reset to " + zVar + ")");
                } catch (Throwable th) {
                    th = th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            lVar = lVar2;
        }
        try {
            lVar.f1861b.getClass();
            throw th;
        } catch (Throwable th3) {
            nVar.unlock();
            throw th3;
        }
    }
}
