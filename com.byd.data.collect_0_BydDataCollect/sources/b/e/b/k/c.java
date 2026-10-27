package b.e.b.k;

import b.e.b.k.e;
import java.util.ArrayList;

/* JADX INFO: compiled from: ChainHead.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected e f2413a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected e f2414b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected e f2415c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected e f2416d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected e f2417e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected e f2418f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected e f2419g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    protected ArrayList<e> f2420h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    protected int f2421i;
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

    public c(e eVar, int i2, boolean z) {
        this.q = false;
        this.f2413a = eVar;
        this.p = i2;
        this.q = z;
    }

    private void b() {
        int i2 = this.p * 2;
        e eVar = this.f2413a;
        this.o = true;
        e eVar2 = eVar;
        boolean z = false;
        while (!z) {
            this.f2421i++;
            e[] eVarArr = eVar.D0;
            int i3 = this.p;
            e eVar3 = null;
            eVarArr[i3] = null;
            eVar.C0[i3] = null;
            if (eVar.P() != 8) {
                this.l++;
                e.b bVarT = eVar.t(this.p);
                e.b bVar = e.b.MATCH_CONSTRAINT;
                if (bVarT != bVar) {
                    this.m += eVar.B(this.p);
                }
                int iC = this.m + eVar.M[i2].c();
                this.m = iC;
                int i4 = i2 + 1;
                this.m = iC + eVar.M[i4].c();
                int iC2 = this.n + eVar.M[i2].c();
                this.n = iC2;
                this.n = iC2 + eVar.M[i4].c();
                if (this.f2414b == null) {
                    this.f2414b = eVar;
                }
                this.f2416d = eVar;
                e.b[] bVarArr = eVar.P;
                int i5 = this.p;
                if (bVarArr[i5] == bVar) {
                    int[] iArr = eVar.o;
                    if (iArr[i5] == 0 || iArr[i5] == 3 || iArr[i5] == 2) {
                        this.j++;
                        float[] fArr = eVar.B0;
                        float f2 = fArr[i5];
                        if (f2 > 0.0f) {
                            this.k += fArr[i5];
                        }
                        if (c(eVar, i5)) {
                            if (f2 < 0.0f) {
                                this.r = true;
                            } else {
                                this.s = true;
                            }
                            if (this.f2420h == null) {
                                this.f2420h = new ArrayList<>();
                            }
                            this.f2420h.add(eVar);
                        }
                        if (this.f2418f == null) {
                            this.f2418f = eVar;
                        }
                        e eVar4 = this.f2419g;
                        if (eVar4 != null) {
                            eVar4.C0[this.p] = eVar;
                        }
                        this.f2419g = eVar;
                    }
                    if (this.p == 0) {
                        if (eVar.m != 0 || eVar.p != 0 || eVar.q != 0) {
                            this.o = false;
                        }
                    } else if (eVar.n != 0 || eVar.s != 0 || eVar.t != 0) {
                        this.o = false;
                    }
                    if (eVar.T != 0.0f) {
                        this.o = false;
                        this.u = true;
                    }
                }
            }
            if (eVar2 != eVar) {
                eVar2.D0[this.p] = eVar;
            }
            d dVar = eVar.M[i2 + 1].f2425d;
            if (dVar != null) {
                e eVar5 = dVar.f2423b;
                d[] dVarArr = eVar5.M;
                if (dVarArr[i2].f2425d != null && dVarArr[i2].f2425d.f2423b == eVar) {
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
        e eVar6 = this.f2414b;
        if (eVar6 != null) {
            this.m -= eVar6.M[i2].c();
        }
        e eVar7 = this.f2416d;
        if (eVar7 != null) {
            this.m -= eVar7.M[i2 + 1].c();
        }
        this.f2415c = eVar;
        if (this.p == 0 && this.q) {
            this.f2417e = eVar;
        } else {
            this.f2417e = this.f2413a;
        }
        this.t = this.s && this.r;
    }

    private static boolean c(e eVar, int i2) {
        if (eVar.P() != 8 && eVar.P[i2] == e.b.MATCH_CONSTRAINT) {
            int[] iArr = eVar.o;
            if (iArr[i2] == 0 || iArr[i2] == 3) {
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
