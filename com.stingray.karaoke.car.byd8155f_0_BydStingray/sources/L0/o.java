package L0;

import C0.c0;
import E0.AbstractC0156f;
import E0.F;
import E0.InterfaceC0163m;
import E0.d0;
import a.AbstractC0904a;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import n7.AbstractC1998m;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g0.n f3916a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f3917b;
    public final F c;
    public final j d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f3918e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public o f3919f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f3920g;

    public o(g0.n nVar, boolean z6, F f10, j jVar) {
        this.f3916a = nVar;
        this.f3917b = z6;
        this.c = f10;
        this.d = jVar;
        this.f3920g = f10.f1481m;
    }

    public static /* synthetic */ List h(o oVar, boolean z6, int i7) {
        boolean z10 = (i7 & 1) != 0 ? !oVar.f3917b : false;
        if ((i7 & 2) != 0) {
            z6 = false;
        }
        return oVar.g(z10, z6, false);
    }

    public final o a(g gVar, InterfaceC2782k interfaceC2782k) {
        j jVar = new j();
        jVar.f3909m = false;
        jVar.f3910n = false;
        interfaceC2782k.b(jVar);
        o oVar = new o(new m(interfaceC2782k), false, new F(this.f3920g + (gVar != null ? 1000000000 : 2000000000), true), jVar);
        oVar.f3918e = true;
        oVar.f3919f = this;
        return oVar;
    }

    public final void b(F f10, ArrayList arrayList, boolean z6) {
        V.d dVarK = f10.K();
        int i7 = dVarK.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVarK.l;
            int i10 = 0;
            do {
                F f11 = (F) objArr[i10];
                if (f11.V() && (z6 || !f11.f1478Q)) {
                    if (f11.f1469H.f(8)) {
                        arrayList.add(AbstractC0904a.p(f11, this.f3917b));
                    } else {
                        b(f11, arrayList, z6);
                    }
                }
                i10++;
            } while (i10 < i7);
        }
    }

    public final d0 c() {
        if (this.f3918e) {
            o oVarJ = j();
            if (oVarJ != null) {
                return oVarJ.c();
            }
            return null;
        }
        InterfaceC0163m interfaceC0163mG = AbstractC0904a.G(this.c);
        if (interfaceC0163mG == null) {
            interfaceC0163mG = this.f3916a;
        }
        return AbstractC0156f.r(interfaceC0163mG, 8);
    }

    public final void d(List list) {
        List listO = o(false, false);
        int size = listO.size();
        for (int i7 = 0; i7 < size; i7++) {
            o oVar = (o) listO.get(i7);
            if (oVar.l()) {
                list.add(oVar);
            } else if (!oVar.d.f3910n) {
                oVar.d(list);
            }
        }
    }

    public final m0.d e() {
        d0 d0VarC = c();
        if (d0VarC != null) {
            if (!d0VarC.Q0().f14695x) {
                d0VarC = null;
            }
            if (d0VarC != null) {
                return c0.h(d0VarC).o(d0VarC, true);
            }
        }
        return m0.d.f18881e;
    }

    public final m0.d f() {
        d0 d0VarC = c();
        if (d0VarC != null) {
            if (!d0VarC.Q0().f14695x) {
                d0VarC = null;
            }
            if (d0VarC != null) {
                return c0.f(d0VarC);
            }
        }
        return m0.d.f18881e;
    }

    public final List g(boolean z6, boolean z10, boolean z11) {
        if (!z6 && this.d.f3910n) {
            return n7.u.l;
        }
        if (!l()) {
            return o(z10, z11);
        }
        ArrayList arrayList = new ArrayList();
        d(arrayList);
        return arrayList;
    }

    public final j i() {
        boolean zL = l();
        j jVar = this.d;
        if (!zL) {
            return jVar;
        }
        j jVar2 = new j();
        jVar2.f3909m = jVar.f3909m;
        jVar2.f3910n = jVar.f3910n;
        jVar2.l.putAll(jVar.l);
        n(jVar2);
        return jVar2;
    }

    public final o j() {
        F fG;
        o oVar = this.f3919f;
        if (oVar != null) {
            return oVar;
        }
        F f10 = this.c;
        boolean z6 = this.f3917b;
        if (z6) {
            n nVar = n.f3915m;
            fG = f10.G();
            while (fG != null) {
                if (((Boolean) nVar.b(fG)).booleanValue()) {
                    break;
                }
                fG = fG.G();
            }
            fG = null;
        } else {
            fG = null;
        }
        if (fG == null) {
            F fG2 = f10.G();
            while (true) {
                if (fG2 == null) {
                    fG = null;
                    break;
                }
                if (fG2.f1469H.f(8)) {
                    fG = fG2;
                    break;
                }
                fG2 = fG2.G();
            }
        }
        if (fG == null) {
            return null;
        }
        return AbstractC0904a.p(fG, z6);
    }

    public final j k() {
        return this.d;
    }

    public final boolean l() {
        return this.f3917b && this.d.f3909m;
    }

    public final boolean m() {
        if (!this.f3918e && h(this, true, 4).isEmpty()) {
            F fG = this.c.G();
            while (true) {
                if (fG != null) {
                    j jVarQ = fG.q();
                    if (jVarQ != null && jVarQ.f3909m) {
                        break;
                    }
                    fG = fG.G();
                } else {
                    fG = null;
                    break;
                }
            }
            if (fG == null) {
                return true;
            }
        }
        return false;
    }

    public final void n(j jVar) {
        if (this.d.f3910n) {
            return;
        }
        List listO = o(false, false);
        int size = listO.size();
        for (int i7 = 0; i7 < size; i7++) {
            o oVar = (o) listO.get(i7);
            if (!oVar.l()) {
                for (Map.Entry entry : oVar.d.l.entrySet()) {
                    u uVar = (u) entry.getKey();
                    Object value = entry.getValue();
                    LinkedHashMap linkedHashMap = jVar.l;
                    Object obj = linkedHashMap.get(uVar);
                    A7.m.d(uVar, "null cannot be cast to non-null type androidx.compose.ui.semantics.SemanticsPropertyKey<kotlin.Any?>");
                    Object objInvoke = uVar.f3966b.invoke(obj, value);
                    if (objInvoke != null) {
                        linkedHashMap.put(uVar, objInvoke);
                    }
                }
                oVar.n(jVar);
            }
        }
    }

    public final List o(boolean z6, boolean z10) {
        if (this.f3918e) {
            return n7.u.l;
        }
        ArrayList arrayList = new ArrayList();
        b(this.c, arrayList, z10);
        if (z6) {
            u uVar = r.f3955s;
            j jVar = this.d;
            LinkedHashMap linkedHashMap = jVar.l;
            Object obj = linkedHashMap.get(uVar);
            if (obj == null) {
                obj = null;
            }
            g gVar = (g) obj;
            if (gVar != null && jVar.f3909m && !arrayList.isEmpty()) {
                arrayList.add(a(gVar, new A.F(gVar, 23)));
            }
            u uVar2 = r.f3941a;
            if (linkedHashMap.containsKey(uVar2) && !arrayList.isEmpty() && jVar.f3909m) {
                Object obj2 = linkedHashMap.get(uVar2);
                if (obj2 == null) {
                    obj2 = null;
                }
                List list = (List) obj2;
                String str = list != null ? (String) AbstractC1998m.a0(list) : null;
                if (str != null) {
                    arrayList.add(0, a(null, new l(str, 0)));
                }
            }
        }
        return arrayList;
    }
}
