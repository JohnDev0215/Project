package u2;

import d5.AbstractC1095q;
import d5.C1078G;
import e2.C1207o;
import e2.C1208p;
import h2.AbstractC1346c;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import n0.C1886o;

/* JADX INFO: loaded from: classes.dex */
public final class K implements InterfaceC2490A, InterfaceC2522z {
    public final InterfaceC2490A[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final IdentityHashMap f22675m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C1886o f22676n;
    public final ArrayList o = new ArrayList();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final HashMap f22677p = new HashMap();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public InterfaceC2522z f22678q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public n0 f22679r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public InterfaceC2490A[] f22680s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public C2509l f22681t;

    public K(C1886o c1886o, long[] jArr, InterfaceC2490A... interfaceC2490AArr) {
        this.f22676n = c1886o;
        this.l = interfaceC2490AArr;
        c1886o.getClass();
        C1078G c1078g = d5.I.f13384m;
        d5.d0 d0Var = d5.d0.f13415p;
        this.f22681t = new C2509l(d0Var, d0Var);
        this.f22675m = new IdentityHashMap();
        this.f22680s = new InterfaceC2490A[0];
        for (int i7 = 0; i7 < interfaceC2490AArr.length; i7++) {
            long j3 = jArr[i7];
            if (j3 != 0) {
                this.l[i7] = new m0(interfaceC2490AArr[i7], j3);
            }
        }
    }

    @Override // u2.InterfaceC2490A
    public final long a(long j3, n2.b0 b0Var) {
        InterfaceC2490A[] interfaceC2490AArr = this.f22680s;
        return (interfaceC2490AArr.length > 0 ? interfaceC2490AArr[0] : this.l[0]).a(j3, b0Var);
    }

    @Override // u2.InterfaceC2490A
    public final void b(InterfaceC2522z interfaceC2522z, long j3) {
        this.f22678q = interfaceC2522z;
        ArrayList arrayList = this.o;
        InterfaceC2490A[] interfaceC2490AArr = this.l;
        Collections.addAll(arrayList, interfaceC2490AArr);
        for (InterfaceC2490A interfaceC2490A : interfaceC2490AArr) {
            interfaceC2490A.b(this, j3);
        }
    }

    @Override // u2.InterfaceC2522z
    public final void c(InterfaceC2490A interfaceC2490A) {
        ArrayList arrayList = this.o;
        arrayList.remove(interfaceC2490A);
        if (arrayList.isEmpty()) {
            InterfaceC2490A[] interfaceC2490AArr = this.l;
            int i7 = 0;
            for (InterfaceC2490A interfaceC2490A2 : interfaceC2490AArr) {
                i7 += interfaceC2490A2.j().f22881a;
            }
            e2.g0[] g0VarArr = new e2.g0[i7];
            int i10 = 0;
            for (int i11 = 0; i11 < interfaceC2490AArr.length; i11++) {
                n0 n0VarJ = interfaceC2490AArr[i11].j();
                int i12 = n0VarJ.f22881a;
                int i13 = 0;
                while (i13 < i12) {
                    e2.g0 g0VarA = n0VarJ.a(i13);
                    int i14 = g0VarA.f14104a;
                    C1208p[] c1208pArr = new C1208p[i14];
                    for (int i15 = 0; i15 < i14; i15++) {
                        C1208p c1208p = g0VarA.d[i15];
                        C1207o c1207oA = c1208p.a();
                        StringBuilder sbM = android.hardware.bydauto.ac.tsimpl.a.m(i11, ":");
                        String str = c1208p.f14306a;
                        if (str == null) {
                            str = "";
                        }
                        sbM.append(str);
                        c1207oA.f14237a = sbM.toString();
                        c1208pArr[i15] = new C1208p(c1207oA);
                    }
                    StringBuilder sbM2 = android.hardware.bydauto.ac.tsimpl.a.m(i11, ":");
                    sbM2.append(g0VarA.f14105b);
                    e2.g0 g0Var = new e2.g0(sbM2.toString(), c1208pArr);
                    this.f22677p.put(g0Var, g0VarA);
                    g0VarArr[i10] = g0Var;
                    i13++;
                    i10++;
                }
            }
            this.f22679r = new n0(g0VarArr);
            InterfaceC2522z interfaceC2522z = this.f22678q;
            interfaceC2522z.getClass();
            interfaceC2522z.c(this);
        }
    }

    @Override // u2.b0
    public final boolean d() {
        return this.f22681t.d();
    }

    @Override // u2.InterfaceC2522z
    public final void e(b0 b0Var) {
        InterfaceC2522z interfaceC2522z = this.f22678q;
        interfaceC2522z.getClass();
        interfaceC2522z.e(this);
    }

    @Override // u2.b0
    public final long g() {
        return this.f22681t.g();
    }

    @Override // u2.InterfaceC2490A
    public final long h() {
        long j3 = -9223372036854775807L;
        for (InterfaceC2490A interfaceC2490A : this.f22680s) {
            long jH = interfaceC2490A.h();
            if (jH == -9223372036854775807L) {
                if (j3 != -9223372036854775807L && interfaceC2490A.o(j3) != j3) {
                    throw new IllegalStateException("Unexpected child seekToUs result.");
                }
            } else if (j3 == -9223372036854775807L) {
                for (InterfaceC2490A interfaceC2490A2 : this.f22680s) {
                    if (interfaceC2490A2 == interfaceC2490A) {
                        break;
                    }
                    if (interfaceC2490A2.o(jH) != jH) {
                        throw new IllegalStateException("Unexpected child seekToUs result.");
                    }
                }
                j3 = jH;
            } else if (jH != j3) {
                throw new IllegalStateException("Conflicting discontinuities.");
            }
        }
        return j3;
    }

    @Override // u2.b0
    public final boolean i(n2.G g7) {
        ArrayList arrayList = this.o;
        if (arrayList.isEmpty()) {
            return this.f22681t.i(g7);
        }
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((InterfaceC2490A) arrayList.get(i7)).i(g7);
        }
        return false;
    }

    @Override // u2.InterfaceC2490A
    public final n0 j() {
        n0 n0Var = this.f22679r;
        n0Var.getClass();
        return n0Var;
    }

    @Override // u2.InterfaceC2490A
    public final long k(w2.q[] qVarArr, boolean[] zArr, a0[] a0VarArr, boolean[] zArr2, long j3) {
        IdentityHashMap identityHashMap;
        ArrayList arrayList;
        int[] iArr = new int[qVarArr.length];
        int[] iArr2 = new int[qVarArr.length];
        int i7 = 0;
        int i10 = 0;
        while (true) {
            int length = qVarArr.length;
            identityHashMap = this.f22675m;
            if (i10 >= length) {
                break;
            }
            a0 a0Var = a0VarArr[i10];
            Integer num = a0Var == null ? null : (Integer) identityHashMap.get(a0Var);
            iArr[i10] = num == null ? -1 : num.intValue();
            w2.q qVar = qVarArr[i10];
            if (qVar != null) {
                String str = qVar.g().f14105b;
                iArr2[i10] = Integer.parseInt(str.substring(0, str.indexOf(":")));
            } else {
                iArr2[i10] = -1;
            }
            i10++;
        }
        identityHashMap.clear();
        int length2 = qVarArr.length;
        a0[] a0VarArr2 = new a0[length2];
        a0[] a0VarArr3 = new a0[qVarArr.length];
        w2.q[] qVarArr2 = new w2.q[qVarArr.length];
        InterfaceC2490A[] interfaceC2490AArr = this.l;
        ArrayList arrayList2 = new ArrayList(interfaceC2490AArr.length);
        long j10 = j3;
        int i11 = 0;
        while (i11 < interfaceC2490AArr.length) {
            int i12 = i7;
            while (i12 < qVarArr.length) {
                a0VarArr3[i12] = iArr[i12] == i11 ? a0VarArr[i12] : null;
                if (iArr2[i12] == i11) {
                    w2.q qVar2 = qVarArr[i12];
                    qVar2.getClass();
                    arrayList = arrayList2;
                    e2.g0 g0Var = (e2.g0) this.f22677p.get(qVar2.g());
                    g0Var.getClass();
                    qVarArr2[i12] = new J(qVar2, g0Var);
                } else {
                    arrayList = arrayList2;
                    qVarArr2[i12] = null;
                }
                i12++;
                arrayList2 = arrayList;
            }
            ArrayList arrayList3 = arrayList2;
            int i13 = i11;
            InterfaceC2490A[] interfaceC2490AArr2 = interfaceC2490AArr;
            w2.q[] qVarArr3 = qVarArr2;
            long jK = interfaceC2490AArr[i11].k(qVarArr2, zArr, a0VarArr3, zArr2, j10);
            if (i13 == 0) {
                j10 = jK;
            } else if (jK != j10) {
                throw new IllegalStateException("Children enabled at different positions.");
            }
            boolean z6 = false;
            for (int i14 = 0; i14 < qVarArr.length; i14++) {
                if (iArr2[i14] == i13) {
                    a0 a0Var2 = a0VarArr3[i14];
                    a0Var2.getClass();
                    a0VarArr2[i14] = a0VarArr3[i14];
                    identityHashMap.put(a0Var2, Integer.valueOf(i13));
                    z6 = true;
                } else if (iArr[i14] == i13) {
                    AbstractC1346c.j(a0VarArr3[i14] == null);
                }
            }
            if (z6) {
                arrayList3.add(interfaceC2490AArr2[i13]);
            }
            i11 = i13 + 1;
            arrayList2 = arrayList3;
            interfaceC2490AArr = interfaceC2490AArr2;
            qVarArr2 = qVarArr3;
            i7 = 0;
        }
        int i15 = i7;
        ArrayList arrayList4 = arrayList2;
        System.arraycopy(a0VarArr2, i15, a0VarArr, i15, length2);
        this.f22680s = (InterfaceC2490A[]) arrayList4.toArray(new InterfaceC2490A[i15]);
        AbstractList abstractListX = AbstractC1095q.x(new I(0), arrayList4);
        this.f22676n.getClass();
        this.f22681t = new C2509l(arrayList4, abstractListX);
        return j10;
    }

    @Override // u2.b0
    public final long l() {
        return this.f22681t.l();
    }

    @Override // u2.InterfaceC2490A
    public final void m() {
        for (InterfaceC2490A interfaceC2490A : this.l) {
            interfaceC2490A.m();
        }
    }

    @Override // u2.InterfaceC2490A
    public final long o(long j3) {
        long jO = this.f22680s[0].o(j3);
        int i7 = 1;
        while (true) {
            InterfaceC2490A[] interfaceC2490AArr = this.f22680s;
            if (i7 >= interfaceC2490AArr.length) {
                return jO;
            }
            if (interfaceC2490AArr[i7].o(jO) != jO) {
                throw new IllegalStateException("Unexpected child seekToUs result.");
            }
            i7++;
        }
    }

    @Override // u2.InterfaceC2490A
    public final void p(long j3) {
        for (InterfaceC2490A interfaceC2490A : this.f22680s) {
            interfaceC2490A.p(j3);
        }
    }

    @Override // u2.b0
    public final void q(long j3) {
        this.f22681t.q(j3);
    }
}
