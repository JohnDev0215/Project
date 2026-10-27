package r;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import r.e;
import s.b;

/* JADX INFO: compiled from: ConstraintWidgetContainer.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends n {
    private int X0;

    /* JADX INFO: renamed from: b1, reason: collision with root package name */
    int f9260b1;

    /* JADX INFO: renamed from: c1, reason: collision with root package name */
    int f9261c1;

    /* JADX INFO: renamed from: d1, reason: collision with root package name */
    int f9262d1;

    /* JADX INFO: renamed from: e1, reason: collision with root package name */
    int f9263e1;
    s.b V0 = new s.b(this);
    public s.e W0 = new s.e(this);
    protected b.InterfaceC0143b Y0 = null;
    private boolean Z0 = false;

    /* JADX INFO: renamed from: a1, reason: collision with root package name */
    protected o.d f9259a1 = new o.d();

    /* JADX INFO: renamed from: f1, reason: collision with root package name */
    public int f9264f1 = 0;

    /* JADX INFO: renamed from: g1, reason: collision with root package name */
    public int f9265g1 = 0;

    /* JADX INFO: renamed from: h1, reason: collision with root package name */
    c[] f9266h1 = new c[4];

    /* JADX INFO: renamed from: i1, reason: collision with root package name */
    c[] f9267i1 = new c[4];

    /* JADX INFO: renamed from: j1, reason: collision with root package name */
    public boolean f9268j1 = false;

    /* JADX INFO: renamed from: k1, reason: collision with root package name */
    public boolean f9269k1 = false;

    /* JADX INFO: renamed from: l1, reason: collision with root package name */
    public boolean f9270l1 = false;

    /* JADX INFO: renamed from: m1, reason: collision with root package name */
    public int f9271m1 = 0;

    /* JADX INFO: renamed from: n1, reason: collision with root package name */
    public int f9272n1 = 0;

    /* JADX INFO: renamed from: o1, reason: collision with root package name */
    private int f9273o1 = 257;

    /* JADX INFO: renamed from: p1, reason: collision with root package name */
    public boolean f9274p1 = false;

    /* JADX INFO: renamed from: q1, reason: collision with root package name */
    private boolean f9275q1 = false;

    /* JADX INFO: renamed from: r1, reason: collision with root package name */
    private boolean f9276r1 = false;

    /* JADX INFO: renamed from: s1, reason: collision with root package name */
    int f9277s1 = 0;

    /* JADX INFO: renamed from: t1, reason: collision with root package name */
    private WeakReference<d> f9278t1 = null;

    /* JADX INFO: renamed from: u1, reason: collision with root package name */
    private WeakReference<d> f9279u1 = null;

    /* JADX INFO: renamed from: v1, reason: collision with root package name */
    private WeakReference<d> f9280v1 = null;

    /* JADX INFO: renamed from: w1, reason: collision with root package name */
    private WeakReference<d> f9281w1 = null;

    /* JADX INFO: renamed from: x1, reason: collision with root package name */
    HashSet<e> f9282x1 = new HashSet<>();

    /* JADX INFO: renamed from: y1, reason: collision with root package name */
    public b.a f9283y1 = new b.a();

    private void A1(e eVar) {
        int i7 = this.f9264f1 + 1;
        c[] cVarArr = this.f9267i1;
        if (i7 >= cVarArr.length) {
            this.f9267i1 = (c[]) Arrays.copyOf(cVarArr, cVarArr.length * 2);
        }
        this.f9267i1[this.f9264f1] = new c(eVar, 0, S1());
        this.f9264f1++;
    }

    private void D1(d dVar, o.i iVar) {
        this.f9259a1.h(iVar, this.f9259a1.q(dVar), 0, 5);
    }

    private void E1(d dVar, o.i iVar) {
        this.f9259a1.h(this.f9259a1.q(dVar), iVar, 0, 5);
    }

    private void F1(e eVar) {
        int i7 = this.f9265g1 + 1;
        c[] cVarArr = this.f9266h1;
        if (i7 >= cVarArr.length) {
            this.f9266h1 = (c[]) Arrays.copyOf(cVarArr, cVarArr.length * 2);
        }
        this.f9266h1[this.f9265g1] = new c(eVar, 1, S1());
        this.f9265g1++;
    }

    public static boolean V1(int i7, e eVar, b.InterfaceC0143b interfaceC0143b, b.a aVar, int i8) {
        int i9;
        int i10;
        if (interfaceC0143b == null) {
            return false;
        }
        if (eVar.X() == 8 || (eVar instanceof h) || (eVar instanceof a)) {
            aVar.f9390e = 0;
            aVar.f9391f = 0;
            return false;
        }
        aVar.f9386a = eVar.C();
        aVar.f9387b = eVar.V();
        aVar.f9388c = eVar.Y();
        aVar.f9389d = eVar.z();
        aVar.f9394i = false;
        aVar.f9395j = i8;
        e.b bVar = aVar.f9386a;
        e.b bVar2 = e.b.MATCH_CONSTRAINT;
        boolean z6 = bVar == bVar2;
        boolean z7 = aVar.f9387b == bVar2;
        boolean z8 = z6 && eVar.f9215f0 > 0.0f;
        boolean z9 = z7 && eVar.f9215f0 > 0.0f;
        if (z6 && eVar.c0(0) && eVar.f9248w == 0 && !z8) {
            aVar.f9386a = e.b.WRAP_CONTENT;
            if (z7 && eVar.f9250x == 0) {
                aVar.f9386a = e.b.FIXED;
            }
            z6 = false;
        }
        if (z7 && eVar.c0(1) && eVar.f9250x == 0 && !z9) {
            aVar.f9387b = e.b.WRAP_CONTENT;
            if (z6 && eVar.f9248w == 0) {
                aVar.f9387b = e.b.FIXED;
            }
            z7 = false;
        }
        if (eVar.p0()) {
            aVar.f9386a = e.b.FIXED;
            z6 = false;
        }
        if (eVar.q0()) {
            aVar.f9387b = e.b.FIXED;
            z7 = false;
        }
        if (z8) {
            if (eVar.f9252y[0] == 4) {
                aVar.f9386a = e.b.FIXED;
            } else if (!z7) {
                e.b bVar3 = aVar.f9387b;
                e.b bVar4 = e.b.FIXED;
                if (bVar3 == bVar4) {
                    i10 = aVar.f9389d;
                } else {
                    aVar.f9386a = e.b.WRAP_CONTENT;
                    interfaceC0143b.a(eVar, aVar);
                    i10 = aVar.f9391f;
                }
                aVar.f9386a = bVar4;
                aVar.f9388c = (int) (eVar.x() * i10);
            }
        }
        if (z9) {
            if (eVar.f9252y[1] == 4) {
                aVar.f9387b = e.b.FIXED;
            } else if (!z6) {
                e.b bVar5 = aVar.f9386a;
                e.b bVar6 = e.b.FIXED;
                if (bVar5 == bVar6) {
                    i9 = aVar.f9388c;
                } else {
                    aVar.f9387b = e.b.WRAP_CONTENT;
                    interfaceC0143b.a(eVar, aVar);
                    i9 = aVar.f9390e;
                }
                aVar.f9387b = bVar6;
                if (eVar.y() == -1) {
                    aVar.f9389d = (int) (i9 / eVar.x());
                } else {
                    aVar.f9389d = (int) (eVar.x() * i9);
                }
            }
        }
        interfaceC0143b.a(eVar, aVar);
        eVar.n1(aVar.f9390e);
        eVar.O0(aVar.f9391f);
        eVar.N0(aVar.f9393h);
        eVar.D0(aVar.f9392g);
        aVar.f9395j = b.a.f9383k;
        return aVar.f9394i;
    }

    private void X1() {
        this.f9264f1 = 0;
        this.f9265g1 = 0;
    }

    public void B1(d dVar) {
        WeakReference<d> weakReference = this.f9281w1;
        if (weakReference == null || weakReference.get() == null || dVar.e() > this.f9281w1.get().e()) {
            this.f9281w1 = new WeakReference<>(dVar);
        }
    }

    public void C1(d dVar) {
        WeakReference<d> weakReference = this.f9279u1;
        if (weakReference == null || weakReference.get() == null || dVar.e() > this.f9279u1.get().e()) {
            this.f9279u1 = new WeakReference<>(dVar);
        }
    }

    void G1(d dVar) {
        WeakReference<d> weakReference = this.f9280v1;
        if (weakReference == null || weakReference.get() == null || dVar.e() > this.f9280v1.get().e()) {
            this.f9280v1 = new WeakReference<>(dVar);
        }
    }

    void H1(d dVar) {
        WeakReference<d> weakReference = this.f9278t1;
        if (weakReference == null || weakReference.get() == null || dVar.e() > this.f9278t1.get().e()) {
            this.f9278t1 = new WeakReference<>(dVar);
        }
    }

    public boolean I1(boolean z6) {
        return this.W0.f(z6);
    }

    public boolean J1(boolean z6) {
        return this.W0.g(z6);
    }

    public boolean K1(boolean z6, int i7) {
        return this.W0.h(z6, i7);
    }

    public b.InterfaceC0143b L1() {
        return this.Y0;
    }

    public int M1() {
        return this.f9273o1;
    }

    public o.d N1() {
        return this.f9259a1;
    }

    public boolean O1() {
        return false;
    }

    public void P1() {
        this.W0.j();
    }

    @Override // r.e
    public void Q(StringBuilder sb) {
        sb.append(this.f9232o + ":{\n");
        sb.append("  actualWidth:" + this.f9211d0);
        sb.append("\n");
        sb.append("  actualHeight:" + this.f9213e0);
        sb.append("\n");
        Iterator<e> it = u1().iterator();
        while (it.hasNext()) {
            it.next().Q(sb);
            sb.append(",\n");
        }
        sb.append("}");
    }

    public void Q1() {
        this.W0.k();
    }

    public boolean R1() {
        return this.f9276r1;
    }

    public boolean S1() {
        return this.Z0;
    }

    public boolean T1() {
        return this.f9275q1;
    }

    public long U1(int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15) {
        this.f9260b1 = i14;
        this.f9261c1 = i15;
        return this.V0.d(this, i7, i14, i15, i8, i9, i10, i11, i12, i13);
    }

    public boolean W1(int i7) {
        return (this.f9273o1 & i7) == i7;
    }

    public void Y1(b.InterfaceC0143b interfaceC0143b) {
        this.Y0 = interfaceC0143b;
        this.W0.n(interfaceC0143b);
    }

    public void Z1(int i7) {
        this.f9273o1 = i7;
        o.d.f8569r = W1(512);
    }

    public void a2(int i7) {
        this.X0 = i7;
    }

    public void b2(boolean z6) {
        this.Z0 = z6;
    }

    public boolean c2(o.d dVar, boolean[] zArr) {
        zArr[2] = false;
        boolean zW1 = W1(64);
        t1(dVar, zW1);
        int size = this.U0.size();
        boolean z6 = false;
        for (int i7 = 0; i7 < size; i7++) {
            e eVar = this.U0.get(i7);
            eVar.t1(dVar, zW1);
            if (eVar.e0()) {
                z6 = true;
            }
        }
        return z6;
    }

    public void d2() {
        this.V0.e(this);
    }

    @Override // r.e
    public void s1(boolean z6, boolean z7) {
        super.s1(z6, z7);
        int size = this.U0.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.U0.get(i7).s1(z6, z7);
        }
    }

    @Override // r.n, r.e
    public void v0() {
        this.f9259a1.D();
        this.f9260b1 = 0;
        this.f9262d1 = 0;
        this.f9261c1 = 0;
        this.f9263e1 = 0;
        this.f9274p1 = false;
        super.v0();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0309 A[PHI: r2 r16
  0x0309: PHI (r2v15 ??) = (r2v14 ??), (r2v19 ??), (r2v19 ??), (r2v19 ??) binds: [B:139:0x02ca, B:147:0x02ef, B:148:0x02f1, B:150:0x02f7] A[DONT_GENERATE, DONT_INLINE]
  0x0309: PHI (r16v4 boolean) = (r16v3 boolean), (r16v5 boolean), (r16v5 boolean), (r16v5 boolean) binds: [B:139:0x02ca, B:147:0x02ef, B:148:0x02f1, B:150:0x02f7] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r13v11 */
    /* JADX WARN: Type inference failed for: r13v12 */
    /* JADX WARN: Type inference failed for: r13v13 */
    /* JADX WARN: Type inference failed for: r13v14 */
    /* JADX WARN: Type inference failed for: r13v15 */
    /* JADX WARN: Type inference failed for: r13v17 */
    /* JADX WARN: Type inference failed for: r13v18 */
    /* JADX WARN: Type inference failed for: r13v19 */
    /* JADX WARN: Type inference failed for: r13v20 */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r13v8 */
    /* JADX WARN: Type inference failed for: r13v9 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v39 */
    /* JADX WARN: Type inference failed for: r2v40 */
    /* JADX WARN: Type inference failed for: r2v41 */
    /* JADX WARN: Type inference failed for: r2v42 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v6 */
    @Override // r.n
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void v1() {
        /*
            Method dump skipped, instruction units count: 815
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: r.f.v1():void");
    }

    void y1(e eVar, int i7) {
        if (i7 == 0) {
            A1(eVar);
        } else if (i7 == 1) {
            F1(eVar);
        }
    }

    public boolean z1(o.d dVar) {
        boolean zW1 = W1(64);
        g(dVar, zW1);
        int size = this.U0.size();
        boolean z6 = false;
        for (int i7 = 0; i7 < size; i7++) {
            e eVar = this.U0.get(i7);
            eVar.V0(0, false);
            eVar.V0(1, false);
            if (eVar instanceof a) {
                z6 = true;
            }
        }
        if (z6) {
            for (int i8 = 0; i8 < size; i8++) {
                e eVar2 = this.U0.get(i8);
                if (eVar2 instanceof a) {
                    ((a) eVar2).B1();
                }
            }
        }
        this.f9282x1.clear();
        for (int i9 = 0; i9 < size; i9++) {
            e eVar3 = this.U0.get(i9);
            if (eVar3.f()) {
                if (eVar3 instanceof m) {
                    this.f9282x1.add(eVar3);
                } else {
                    eVar3.g(dVar, zW1);
                }
            }
        }
        while (this.f9282x1.size() > 0) {
            int size2 = this.f9282x1.size();
            Iterator<e> it = this.f9282x1.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                m mVar = (m) it.next();
                if (mVar.x1(this.f9282x1)) {
                    mVar.g(dVar, zW1);
                    this.f9282x1.remove(mVar);
                    break;
                }
            }
            if (size2 == this.f9282x1.size()) {
                Iterator<e> it2 = this.f9282x1.iterator();
                while (it2.hasNext()) {
                    it2.next().g(dVar, zW1);
                }
                this.f9282x1.clear();
            }
        }
        if (o.d.f8569r) {
            HashSet<e> hashSet = new HashSet<>();
            for (int i10 = 0; i10 < size; i10++) {
                e eVar4 = this.U0.get(i10);
                if (!eVar4.f()) {
                    hashSet.add(eVar4);
                }
            }
            e(this, dVar, hashSet, C() == e.b.WRAP_CONTENT ? 0 : 1, false);
            for (e eVar5 : hashSet) {
                k.a(this, dVar, eVar5);
                eVar5.g(dVar, zW1);
            }
        } else {
            for (int i11 = 0; i11 < size; i11++) {
                e eVar6 = this.U0.get(i11);
                if (eVar6 instanceof f) {
                    e.b[] bVarArr = eVar6.f9207b0;
                    e.b bVar = bVarArr[0];
                    e.b bVar2 = bVarArr[1];
                    e.b bVar3 = e.b.WRAP_CONTENT;
                    if (bVar == bVar3) {
                        eVar6.S0(e.b.FIXED);
                    }
                    if (bVar2 == bVar3) {
                        eVar6.j1(e.b.FIXED);
                    }
                    eVar6.g(dVar, zW1);
                    if (bVar == bVar3) {
                        eVar6.S0(bVar);
                    }
                    if (bVar2 == bVar3) {
                        eVar6.j1(bVar2);
                    }
                } else {
                    k.a(this, dVar, eVar6);
                    if (!eVar6.f()) {
                        eVar6.g(dVar, zW1);
                    }
                }
            }
        }
        if (this.f9264f1 > 0) {
            b.b(this, dVar, null, 0);
        }
        if (this.f9265g1 > 0) {
            b.b(this, dVar, null, 1);
        }
        return true;
    }
}
