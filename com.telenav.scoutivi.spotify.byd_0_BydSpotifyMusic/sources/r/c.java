package r;

import java.util.ArrayList;
import r.e;

/* JADX INFO: compiled from: ChainHead.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected e f9171a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected e f9172b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected e f9173c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected e f9174d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected e f9175e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected e f9176f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected e f9177g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    protected ArrayList<e> f9178h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    protected int f9179i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    protected int f9180j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    protected float f9181k = 0.0f;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    int f9182l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    int f9183m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    int f9184n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    boolean f9185o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f9186p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f9187q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    protected boolean f9188r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    protected boolean f9189s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    protected boolean f9190t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    protected boolean f9191u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private boolean f9192v;

    public c(e eVar, int i7, boolean z6) {
        this.f9171a = eVar;
        this.f9186p = i7;
        this.f9187q = z6;
    }

    private void b() {
        int i7 = this.f9186p * 2;
        e eVar = this.f9171a;
        this.f9185o = true;
        e eVar2 = eVar;
        boolean z6 = false;
        while (!z6) {
            this.f9179i++;
            e[] eVarArr = eVar.O0;
            int i8 = this.f9186p;
            e eVar3 = null;
            eVarArr[i8] = null;
            eVar.N0[i8] = null;
            if (eVar.X() != 8) {
                this.f9182l++;
                e.b bVarW = eVar.w(this.f9186p);
                e.b bVar = e.b.MATCH_CONSTRAINT;
                if (bVarW != bVar) {
                    this.f9183m += eVar.G(this.f9186p);
                }
                int iF = this.f9183m + eVar.Y[i7].f();
                this.f9183m = iF;
                int i9 = i7 + 1;
                this.f9183m = iF + eVar.Y[i9].f();
                int iF2 = this.f9184n + eVar.Y[i7].f();
                this.f9184n = iF2;
                this.f9184n = iF2 + eVar.Y[i9].f();
                if (this.f9172b == null) {
                    this.f9172b = eVar;
                }
                this.f9174d = eVar;
                e.b[] bVarArr = eVar.f9207b0;
                int i10 = this.f9186p;
                if (bVarArr[i10] == bVar) {
                    int[] iArr = eVar.f9252y;
                    if (iArr[i10] == 0 || iArr[i10] == 3 || iArr[i10] == 2) {
                        this.f9180j++;
                        float[] fArr = eVar.M0;
                        float f7 = fArr[i10];
                        if (f7 > 0.0f) {
                            this.f9181k += fArr[i10];
                        }
                        if (c(eVar, i10)) {
                            if (f7 < 0.0f) {
                                this.f9188r = true;
                            } else {
                                this.f9189s = true;
                            }
                            if (this.f9178h == null) {
                                this.f9178h = new ArrayList<>();
                            }
                            this.f9178h.add(eVar);
                        }
                        if (this.f9176f == null) {
                            this.f9176f = eVar;
                        }
                        e eVar4 = this.f9177g;
                        if (eVar4 != null) {
                            eVar4.N0[this.f9186p] = eVar;
                        }
                        this.f9177g = eVar;
                    }
                    if (this.f9186p == 0) {
                        if (eVar.f9248w != 0 || eVar.f9254z != 0 || eVar.A != 0) {
                            this.f9185o = false;
                        }
                    } else if (eVar.f9250x != 0 || eVar.C != 0 || eVar.D != 0) {
                        this.f9185o = false;
                    }
                    if (eVar.f9215f0 != 0.0f) {
                        this.f9185o = false;
                        this.f9191u = true;
                    }
                }
            }
            if (eVar2 != eVar) {
                eVar2.O0[this.f9186p] = eVar;
            }
            d dVar = eVar.Y[i7 + 1].f9198f;
            if (dVar != null) {
                e eVar5 = dVar.f9196d;
                d[] dVarArr = eVar5.Y;
                if (dVarArr[i7].f9198f != null && dVarArr[i7].f9198f.f9196d == eVar) {
                    eVar3 = eVar5;
                }
            }
            if (eVar3 == null) {
                eVar3 = eVar;
                z6 = true;
            }
            eVar2 = eVar;
            eVar = eVar3;
        }
        e eVar6 = this.f9172b;
        if (eVar6 != null) {
            this.f9183m -= eVar6.Y[i7].f();
        }
        e eVar7 = this.f9174d;
        if (eVar7 != null) {
            this.f9183m -= eVar7.Y[i7 + 1].f();
        }
        this.f9173c = eVar;
        if (this.f9186p == 0 && this.f9187q) {
            this.f9175e = eVar;
        } else {
            this.f9175e = this.f9171a;
        }
        this.f9190t = this.f9189s && this.f9188r;
    }

    private static boolean c(e eVar, int i7) {
        if (eVar.X() != 8 && eVar.f9207b0[i7] == e.b.MATCH_CONSTRAINT) {
            int[] iArr = eVar.f9252y;
            if (iArr[i7] == 0 || iArr[i7] == 3) {
                return true;
            }
        }
        return false;
    }

    public void a() {
        if (!this.f9192v) {
            b();
        }
        this.f9192v = true;
    }
}
