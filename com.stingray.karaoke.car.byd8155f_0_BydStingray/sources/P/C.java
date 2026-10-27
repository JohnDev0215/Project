package P;

import A.C0006c;
import E0.C0168s;
import E0.F;
import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import androidx.compose.ui.platform.AndroidComposeView;
import c9.C1045f;
import h2.AbstractC1343A;
import h2.AbstractC1347d;
import h2.C1344a;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import l3.C1764u;
import l5.C1778f;
import n0.C1891t;
import n7.AbstractC1998m;
import p0.C2071b;
import r9.H;
import u.C2472l;
import w8.C2607b;
import x.AbstractC2614C;
import x.AbstractC2631e;
import x.C0;
import x.C2629d;
import y0.AbstractC2737o;
import y0.C2729g;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class C implements l2.m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f5202a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f5203b;
    public Object c = AbstractC2631e.a(0.0f);
    public Object d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f5204e;

    /* JADX WARN: Multi-variable type inference failed */
    public C(InterfaceC2772a interfaceC2772a, boolean z6) {
        this.f5202a = z6;
        this.f5203b = (A7.o) interfaceC2772a;
    }

    public static int l(l2.k kVar, int i7) {
        int iHashCode = kVar.f18485b.hashCode() + (kVar.f18484a * 31);
        if (i7 >= 2) {
            return (iHashCode * 31) + kVar.f18486e.hashCode();
        }
        long jA = l2.n.a(kVar.f18486e);
        return (iHashCode * 31) + ((int) (jA ^ (jA >>> 32)));
    }

    public static l2.k o(int i7, DataInputStream dataInputStream) throws IOException {
        l2.o oVarA;
        int i10 = dataInputStream.readInt();
        String utf = dataInputStream.readUTF();
        if (i7 < 2) {
            long j3 = dataInputStream.readLong();
            l2.g gVar = new l2.g();
            gVar.a(Long.valueOf(j3), "exo_len");
            oVarA = l2.o.c.b(gVar);
        } else {
            oVarA = H0.b.a(dataInputStream);
        }
        return new l2.k(i10, utf, oVarA);
    }

    @Override // l2.m
    public boolean a() {
        M.t tVar = (M.t) this.d;
        return ((File) tVar.l).exists() || ((File) tVar.f4151m).exists();
    }

    @Override // l2.m
    public void b(HashMap map) throws Throwable {
        if (this.f5202a) {
            c(map);
        }
    }

    @Override // l2.m
    public void c(HashMap map) throws Throwable {
        DataOutputStream dataOutputStream;
        M.t tVar = (M.t) this.d;
        DataOutputStream dataOutputStream2 = null;
        try {
            C1344a c1344aO = tVar.O();
            l2.r rVar = (l2.r) this.f5204e;
            if (rVar == null) {
                this.f5204e = new l2.r(c1344aO);
            } else {
                rVar.b(c1344aO);
            }
            dataOutputStream = new DataOutputStream((l2.r) this.f5204e);
        } catch (Throwable th) {
            th = th;
        }
        try {
            dataOutputStream.writeInt(2);
            dataOutputStream.writeInt(0);
            dataOutputStream.writeInt(map.size());
            int iL = 0;
            for (l2.k kVar : map.values()) {
                dataOutputStream.writeInt(kVar.f18484a);
                dataOutputStream.writeUTF(kVar.f18485b);
                H0.b.c(kVar.f18486e, dataOutputStream);
                iL += l(kVar, 2);
            }
            dataOutputStream.writeInt(iL);
            dataOutputStream.close();
            ((File) tVar.f4151m).delete();
            int i7 = AbstractC1343A.f15195a;
            this.f5202a = false;
        } catch (Throwable th2) {
            th = th2;
            dataOutputStream2 = dataOutputStream;
            AbstractC1343A.g(dataOutputStream2);
            throw th;
        }
    }

    @Override // l2.m
    public void d(long j3) {
    }

    @Override // l2.m
    public void e(l2.k kVar) {
        this.f5202a = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00be  */
    @Override // l2.m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void f(java.util.HashMap r12, android.util.SparseArray r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 210
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: P.C.f(java.util.HashMap, android.util.SparseArray):void");
    }

    @Override // l2.m
    public void g(l2.k kVar, boolean z6) {
        this.f5202a = true;
    }

    @Override // l2.m
    public void h() {
        M.t tVar = (M.t) this.d;
        ((File) tVar.l).delete();
        ((File) tVar.f4151m).delete();
    }

    public void i() {
        synchronized (((C1045f) this.f5204e)) {
            if (this.f5202a) {
                return;
            }
            this.f5202a = true;
            d9.b.c((H) this.c);
            try {
                ((A2.c) this.f5203b).a();
            } catch (IOException unused) {
            }
        }
    }

    public void j(E0.H h10, float f10, long j3) {
        float fFloatValue = ((Number) ((C2629d) this.c).d()).floatValue();
        if (fFloatValue > 0.0f) {
            long jB = C1891t.b(fFloatValue, j3);
            if (!this.f5202a) {
                h10.Q(jB, f10, (120 & 4) != 0 ? h10.S() : 0L, 1.0f, (120 & 16) != 0 ? p0.g.f20641a : null, null, 3);
                return;
            }
            C2071b c2071b = h10.l;
            float fD = m0.f.d(c2071b.e());
            float fB = m0.f.b(c2071b.e());
            h3.y yVar = c2071b.f20638m;
            long jY = yVar.y();
            yVar.m().m();
            try {
                ((h3.y) ((C1764u) yVar.f15499m).f18608m).m().i(0.0f, 0.0f, fD, fB, 1);
                h10.Q(jB, f10, (120 & 4) != 0 ? h10.S() : 0L, 1.0f, (120 & 16) != 0 ? p0.g.f20641a : null, null, 3);
            } finally {
                AbstractC1347d.o(yVar, jY);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r5v4, types: [A7.o, z7.a] */
    public void k(C.j jVar, InterfaceC0725w interfaceC0725w) {
        boolean z6 = jVar instanceof C.h;
        ArrayList arrayList = (ArrayList) this.d;
        if (z6) {
            arrayList.add(jVar);
        } else if (jVar instanceof C.i) {
            arrayList.remove(((C.i) jVar).f831a);
        } else if (jVar instanceof C.d) {
            arrayList.add(jVar);
        } else if (jVar instanceof C.e) {
            arrayList.remove(((C.e) jVar).f826a);
        } else if (jVar instanceof C.b) {
            arrayList.add(jVar);
        } else if (jVar instanceof C.c) {
            arrayList.remove(((C.c) jVar).f825a);
        } else if (!(jVar instanceof C.a)) {
            return;
        } else {
            arrayList.remove(((C.a) jVar).f824a);
        }
        C.j jVar2 = (C.j) AbstractC1998m.i0(arrayList);
        if (A7.m.a((C.j) this.f5204e, jVar2)) {
            return;
        }
        if (jVar2 != null) {
            h hVar = (h) ((A7.o) this.f5203b).a();
            float f10 = z6 ? hVar.c : jVar instanceof C.d ? hVar.f5230b : jVar instanceof C.b ? hVar.f5229a : 0.0f;
            C0 c02 = u.f5257a;
            boolean z10 = jVar2 instanceof C.h;
            C0 c03 = u.f5257a;
            if (!z10 && ((jVar2 instanceof C.d) || (jVar2 instanceof C.b))) {
                c03 = new C0(45, AbstractC2614C.c, 2);
            }
            AbstractC0727y.w(interfaceC0725w, null, null, new A(this, f10, c03, null), 3);
        } else {
            C.j jVar3 = (C.j) this.f5204e;
            C0 c04 = u.f5257a;
            boolean z11 = jVar3 instanceof C.h;
            C0 c05 = u.f5257a;
            if (!z11 && !(jVar3 instanceof C.d) && (jVar3 instanceof C.b)) {
                c05 = new C0(150, AbstractC2614C.c, 2);
            }
            AbstractC0727y.w(interfaceC0725w, null, null, new B(this, c05, null), 3);
        }
        this.f5204e = jVar2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int m(C1778f c1778f, AndroidComposeView androidComposeView, boolean z6) {
        Object[] objArr;
        h3.y yVar;
        int i7;
        C0168s c0168s = (C0168s) this.f5204e;
        if (this.f5202a) {
            return 0;
        }
        try {
            this.f5202a = true;
            t4.e eVarF = ((C2607b) this.d).f(c1778f, androidComposeView);
            C2472l c2472l = (C2472l) eVarF.f22463n;
            int iG = c2472l.g();
            for (int i10 = 0; i10 < iG; i10++) {
                y0.q qVar = (y0.q) c2472l.h(i10);
                if (!qVar.d && !qVar.f24082h) {
                }
                objArr = false;
                break;
            }
            objArr = true;
            int iG2 = c2472l.g();
            int i11 = 0;
            while (true) {
                yVar = (h3.y) this.c;
                if (i11 >= iG2) {
                    break;
                }
                y0.q qVar2 = (y0.q) c2472l.h(i11);
                if (objArr != false || AbstractC2737o.a(qVar2)) {
                    ((F) this.f5203b).M(qVar2.c, (C0168s) this.f5204e, AbstractC2737o.e(qVar2.f24083i, 1), true);
                    if (!c0168s.isEmpty()) {
                        yVar.b(qVar2.f24077a, c0168s, AbstractC2737o.a(qVar2));
                        c0168s.clear();
                    }
                }
                i11++;
            }
            ((C0006c) yVar.f15500n).d();
            boolean zK = yVar.k(eVarF, z6);
            if (!eVarF.f22462m) {
                int iG3 = c2472l.g();
                for (int i12 = 0; i12 < iG3; i12++) {
                    y0.q qVar3 = (y0.q) c2472l.h(i12);
                    if (!m0.c.b(AbstractC2737o.i(qVar3, true), 0L) && qVar3.b()) {
                        i7 = 2;
                        break;
                    }
                }
            }
            i7 = 0;
            int i13 = (zK ? 1 : 0) | i7;
            this.f5202a = false;
            return i13;
        } catch (Throwable th) {
            this.f5202a = false;
            throw th;
        }
    }

    public void n() {
        if (this.f5202a) {
            return;
        }
        ((C2472l) ((C2607b) this.d).f23345m).a();
        h3.y yVar = (h3.y) this.c;
        V.d dVar = ((C0006c) yVar.f15500n).f158a;
        int i7 = dVar.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVar.l;
            int i10 = 0;
            do {
                ((C2729g) objArr[i10]).f();
                i10++;
            } while (i10 < i7);
        }
        ((C0006c) yVar.f15500n).f158a.g();
    }
}
