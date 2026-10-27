package r;

import java.util.HashMap;
import r.d;
import r.e;

/* JADX INFO: compiled from: Guideline.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends e {
    protected float U0 = -1.0f;
    protected int V0 = -1;
    protected int W0 = -1;
    protected boolean X0 = true;
    private d Y0 = this.R;
    private int Z0 = 0;

    /* JADX INFO: renamed from: a1, reason: collision with root package name */
    private int f9314a1 = 0;

    /* JADX INFO: renamed from: b1, reason: collision with root package name */
    private boolean f9315b1;

    /* JADX INFO: compiled from: Guideline.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f9316a;

        static {
            int[] iArr = new int[d.b.values().length];
            f9316a = iArr;
            try {
                iArr[d.b.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9316a[d.b.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9316a[d.b.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9316a[d.b.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9316a[d.b.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9316a[d.b.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9316a[d.b.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f9316a[d.b.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f9316a[d.b.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public h() {
        this.Z.clear();
        this.Z.add(this.Y0);
        int length = this.Y.length;
        for (int i7 = 0; i7 < length; i7++) {
            this.Y[i7] = this.Y0;
        }
    }

    public void A1(int i7) {
        if (i7 > -1) {
            this.U0 = -1.0f;
            this.V0 = i7;
            this.W0 = -1;
        }
    }

    public void B1(int i7) {
        if (i7 > -1) {
            this.U0 = -1.0f;
            this.V0 = -1;
            this.W0 = i7;
        }
    }

    public void C1(float f7) {
        if (f7 > -1.0f) {
            this.U0 = f7;
            this.V0 = -1;
            this.W0 = -1;
        }
    }

    public void D1(int i7) {
        if (this.Z0 == i7) {
            return;
        }
        this.Z0 = i7;
        this.Z.clear();
        if (this.Z0 == 1) {
            this.Y0 = this.Q;
        } else {
            this.Y0 = this.R;
        }
        this.Z.add(this.Y0);
        int length = this.Y.length;
        for (int i8 = 0; i8 < length; i8++) {
            this.Y[i8] = this.Y0;
        }
    }

    @Override // r.e
    public void g(o.d dVar, boolean z6) {
        f fVar = (f) M();
        if (fVar == null) {
            return;
        }
        d dVarQ = fVar.q(d.b.LEFT);
        d dVarQ2 = fVar.q(d.b.RIGHT);
        e eVar = this.f9209c0;
        boolean z7 = eVar != null && eVar.f9207b0[0] == e.b.WRAP_CONTENT;
        if (this.Z0 == 0) {
            dVarQ = fVar.q(d.b.TOP);
            dVarQ2 = fVar.q(d.b.BOTTOM);
            e eVar2 = this.f9209c0;
            z7 = eVar2 != null && eVar2.f9207b0[1] == e.b.WRAP_CONTENT;
        }
        if (this.f9315b1 && this.Y0.n()) {
            o.i iVarQ = dVar.q(this.Y0);
            dVar.f(iVarQ, this.Y0.e());
            if (this.V0 != -1) {
                if (z7) {
                    dVar.h(dVar.q(dVarQ2), iVarQ, 0, 5);
                }
            } else if (this.W0 != -1 && z7) {
                o.i iVarQ2 = dVar.q(dVarQ2);
                dVar.h(iVarQ, dVar.q(dVarQ), 0, 5);
                dVar.h(iVarQ2, iVarQ, 0, 5);
            }
            this.f9315b1 = false;
            return;
        }
        if (this.V0 != -1) {
            o.i iVarQ3 = dVar.q(this.Y0);
            dVar.e(iVarQ3, dVar.q(dVarQ), this.V0, 8);
            if (z7) {
                dVar.h(dVar.q(dVarQ2), iVarQ3, 0, 5);
                return;
            }
            return;
        }
        if (this.W0 == -1) {
            if (this.U0 != -1.0f) {
                dVar.d(o.d.s(dVar, dVar.q(this.Y0), dVar.q(dVarQ2), this.U0));
                return;
            }
            return;
        }
        o.i iVarQ4 = dVar.q(this.Y0);
        o.i iVarQ5 = dVar.q(dVarQ2);
        dVar.e(iVarQ4, iVarQ5, -this.W0, 8);
        if (z7) {
            dVar.h(iVarQ4, dVar.q(dVarQ), 0, 5);
            dVar.h(iVarQ5, iVarQ4, 0, 5);
        }
    }

    @Override // r.e
    public boolean h() {
        return true;
    }

    @Override // r.e
    public void n(e eVar, HashMap<e, e> map) {
        super.n(eVar, map);
        h hVar = (h) eVar;
        this.U0 = hVar.U0;
        this.V0 = hVar.V0;
        this.W0 = hVar.W0;
        this.X0 = hVar.X0;
        D1(hVar.Z0);
    }

    @Override // r.e
    public boolean p0() {
        return this.f9315b1;
    }

    @Override // r.e
    public d q(d.b bVar) {
        int i7 = a.f9316a[bVar.ordinal()];
        if (i7 == 1 || i7 == 2) {
            if (this.Z0 == 1) {
                return this.Y0;
            }
            return null;
        }
        if ((i7 == 3 || i7 == 4) && this.Z0 == 0) {
            return this.Y0;
        }
        return null;
    }

    @Override // r.e
    public boolean q0() {
        return this.f9315b1;
    }

    @Override // r.e
    public void t1(o.d dVar, boolean z6) {
        if (M() == null) {
            return;
        }
        int iX = dVar.x(this.Y0);
        if (this.Z0 == 1) {
            p1(iX);
            q1(0);
            O0(M().z());
            n1(0);
            return;
        }
        p1(0);
        q1(iX);
        n1(M().Y());
        O0(0);
    }

    public d u1() {
        return this.Y0;
    }

    public int v1() {
        return this.Z0;
    }

    public int w1() {
        return this.V0;
    }

    public int x1() {
        return this.W0;
    }

    public float y1() {
        return this.U0;
    }

    public void z1(int i7) {
        this.Y0.t(i7);
        this.f9315b1 = true;
    }
}
