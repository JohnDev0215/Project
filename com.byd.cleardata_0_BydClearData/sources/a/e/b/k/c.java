package a.e.b.k;

import a.e.b.k.e;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected e f200a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected e f201b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected e f202c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected e f203d;
    protected e e;
    protected e f;
    protected e g;
    protected ArrayList<e> h;
    protected int i;
    protected int j;
    protected float k = 0.0f;
    int l;
    int m;
    int n;
    boolean o;
    private int p;
    private boolean q;
    protected boolean r;
    protected boolean s;
    protected boolean t;
    protected boolean u;
    private boolean v;

    public c(e eVar, int i, boolean z) {
        this.q = false;
        this.f200a = eVar;
        this.p = i;
        this.q = z;
    }

    private void b() {
        int i = this.p * 2;
        e eVar = this.f200a;
        this.o = true;
        e eVar2 = eVar;
        boolean z = false;
        while (!z) {
            this.i++;
            e[] eVarArr = eVar.t0;
            int i2 = this.p;
            e eVar3 = null;
            eVarArr[i2] = null;
            eVar.s0[i2] = null;
            if (eVar.M() != 8) {
                this.l++;
                e.b bVarQ = eVar.q(this.p);
                e.b bVar = e.b.MATCH_CONSTRAINT;
                if (bVarQ != bVar) {
                    this.m += eVar.y(this.p);
                }
                int iB = this.m + eVar.K[i].b();
                this.m = iB;
                int i3 = i + 1;
                this.m = iB + eVar.K[i3].b();
                int iB2 = this.n + eVar.K[i].b();
                this.n = iB2;
                this.n = iB2 + eVar.K[i3].b();
                if (this.f201b == null) {
                    this.f201b = eVar;
                }
                this.f203d = eVar;
                e.b[] bVarArr = eVar.N;
                int i4 = this.p;
                if (bVarArr[i4] == bVar) {
                    int[] iArr = eVar.o;
                    if (iArr[i4] == 0 || iArr[i4] == 3 || iArr[i4] == 2) {
                        this.j++;
                        float[] fArr = eVar.r0;
                        float f = fArr[i4];
                        if (f > 0.0f) {
                            this.k += fArr[i4];
                        }
                        if (c(eVar, i4)) {
                            if (f < 0.0f) {
                                this.r = true;
                            } else {
                                this.s = true;
                            }
                            if (this.h == null) {
                                this.h = new ArrayList<>();
                            }
                            this.h.add(eVar);
                        }
                        if (this.f == null) {
                            this.f = eVar;
                        }
                        e eVar4 = this.g;
                        if (eVar4 != null) {
                            eVar4.s0[this.p] = eVar;
                        }
                        this.g = eVar;
                    }
                    if (this.p != 0 ? !(eVar.n == 0 && eVar.s == 0 && eVar.t == 0) : !(eVar.m == 0 && eVar.p == 0 && eVar.q == 0)) {
                        this.o = false;
                    }
                    if (eVar.R != 0.0f) {
                        this.o = false;
                        this.u = true;
                    }
                }
            }
            if (eVar2 != eVar) {
                eVar2.t0[this.p] = eVar;
            }
            d dVar = eVar.K[i + 1].f207d;
            if (dVar != null) {
                e eVar5 = dVar.f205b;
                d[] dVarArr = eVar5.K;
                if (dVarArr[i].f207d != null && dVarArr[i].f207d.f205b == eVar) {
                    eVar3 = eVar5;
                }
            }
            if (eVar3 == null) {
                eVar3 = eVar;
                z = true;
            }
            eVar2 = eVar;
            eVar = eVar3;
        }
        e eVar6 = this.f201b;
        if (eVar6 != null) {
            this.m -= eVar6.K[i].b();
        }
        e eVar7 = this.f203d;
        if (eVar7 != null) {
            this.m -= eVar7.K[i + 1].b();
        }
        this.f202c = eVar;
        if (this.p == 0 && this.q) {
            this.e = eVar;
        } else {
            this.e = this.f200a;
        }
        this.t = this.s && this.r;
    }

    private static boolean c(e eVar, int i) {
        if (eVar.M() != 8 && eVar.N[i] == e.b.MATCH_CONSTRAINT) {
            int[] iArr = eVar.o;
            if (iArr[i] == 0 || iArr[i] == 3) {
                return true;
            }
        }
        return false;
    }

    public void a() {
        if (!this.v) {
            b();
        }
        this.v = true;
    }
}
