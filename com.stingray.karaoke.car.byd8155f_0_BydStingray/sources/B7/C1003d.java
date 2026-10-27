package b7;

import N1.C0420a;
import c7.InterfaceC1035a;
import h5.t;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: renamed from: b7.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1003d implements InterfaceC1035a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f12281a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f12282b;
    public final /* synthetic */ Object c;

    public /* synthetic */ C1003d(t tVar, i iVar, int i7) {
        this.f12281a = i7;
        this.c = tVar;
        this.f12282b = iVar;
    }

    @Override // c7.InterfaceC1035a
    public final void q(Object... objArr) {
        int i7 = 3;
        int i10 = 1;
        Object obj = this.c;
        Object obj2 = this.f12282b;
        switch (this.f12281a) {
            case 0:
                Logger logger = i.f12288D;
                i iVar = (i) obj2;
                iVar.getClass();
                i.f12288D.fine("open");
                iVar.a1();
                iVar.f12290C = 3;
                iVar.N0("open", new Object[0]);
                h hVar = iVar.f12301y;
                LinkedList linkedList = iVar.f12299w;
                C1002c c1002c = new C1002c(iVar, i10);
                hVar.T0("data", c1002c);
                linkedList.add(new j(hVar, "data", c1002c));
                C1002c c1002c2 = new C1002c(iVar, 2);
                hVar.T0("error", c1002c2);
                linkedList.add(new j(hVar, "error", c1002c2));
                C1002c c1002c3 = new C1002c(iVar, i7);
                hVar.T0("close", c1002c3);
                linkedList.add(new j(hVar, "close", c1002c3));
                iVar.f12289A.f16570n = new g(iVar);
                C0420a c0420a = (C0420a) ((t) obj).f15562m;
                if (c0420a != null) {
                    c0420a.h(null);
                }
                break;
            case 1:
                Object obj3 = objArr.length > 0 ? objArr[0] : null;
                i.f12288D.fine("connect_error");
                i iVar2 = (i) obj2;
                iVar2.a1();
                iVar2.f12290C = 1;
                iVar2.N0("error", obj3);
                t tVar = (t) obj;
                if (((C0420a) tVar.f15562m) != null) {
                    ((C0420a) tVar.f15562m).h(new I7.a("Connection error", obj3 instanceof Exception ? (Exception) obj3 : null));
                    break;
                } else if (!iVar2.f12292p && iVar2.f12291n && iVar2.f12295s.d == 0) {
                    iVar2.c1();
                    break;
                }
                break;
            default:
                d7.o oVar = (d7.o) objArr[0];
                d7.o[] oVarArr = (d7.o[]) obj2;
                d7.o oVar2 = oVarArr[0];
                if (oVar2 != null && !oVar.o.equals(oVar2.o)) {
                    Logger logger2 = d7.l.f13510M;
                    if (logger2.isLoggable(Level.FINE)) {
                        logger2.fine("'" + oVar.o + "' works - aborting '" + oVarArr[0].o + "'");
                    }
                    ((d7.e) obj).q(new Object[0]);
                    break;
                }
                break;
        }
    }

    public C1003d(d7.o[] oVarArr, d7.e eVar) {
        this.f12281a = 2;
        this.f12282b = oVarArr;
        this.c = eVar;
    }
}
