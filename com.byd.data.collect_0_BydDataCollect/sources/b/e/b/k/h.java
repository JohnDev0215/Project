package b.e.b.k;

import b.e.b.k.d;
import b.e.b.k.e;
import java.util.HashMap;

/* JADX INFO: compiled from: Guideline.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends e {
    protected float G0 = -1.0f;
    protected int H0 = -1;
    protected int I0 = -1;
    private d J0 = this.F;
    private int K0 = 0;
    private int L0 = 0;

    /* JADX INFO: compiled from: Guideline.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2463a;

        static {
            int[] iArr = new int[d.b.values().length];
            f2463a = iArr;
            try {
                iArr[d.b.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2463a[d.b.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2463a[d.b.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2463a[d.b.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2463a[d.b.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2463a[d.b.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2463a[d.b.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f2463a[d.b.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f2463a[d.b.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public h() {
        this.N.clear();
        this.N.add(this.J0);
        int length = this.M.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.M[i2] = this.J0;
        }
    }

    @Override // b.e.b.k.e
    public void K0(b.e.b.d dVar) {
        if (H() == null) {
            return;
        }
        int iX = dVar.x(this.J0);
        if (this.K0 == 1) {
            G0(iX);
            H0(0);
            i0(H().w());
            F0(0);
            return;
        }
        G0(0);
        H0(iX);
        F0(H().Q());
        i0(0);
    }

    public int L0() {
        return this.K0;
    }

    public int M0() {
        return this.H0;
    }

    public int N0() {
        return this.I0;
    }

    public float O0() {
        return this.G0;
    }

    public void P0(int i2) {
        if (i2 > -1) {
            this.G0 = -1.0f;
            this.H0 = i2;
            this.I0 = -1;
        }
    }

    public void Q0(int i2) {
        if (i2 > -1) {
            this.G0 = -1.0f;
            this.H0 = -1;
            this.I0 = i2;
        }
    }

    public void R0(float f2) {
        if (f2 > -1.0f) {
            this.G0 = f2;
            this.H0 = -1;
            this.I0 = -1;
        }
    }

    public void S0(int i2) {
        if (this.K0 == i2) {
            return;
        }
        this.K0 = i2;
        this.N.clear();
        if (this.K0 == 1) {
            this.J0 = this.E;
        } else {
            this.J0 = this.F;
        }
        this.N.add(this.J0);
        int length = this.M.length;
        for (int i3 = 0; i3 < length; i3++) {
            this.M[i3] = this.J0;
        }
    }

    @Override // b.e.b.k.e
    public void f(b.e.b.d dVar) {
        f fVar = (f) H();
        if (fVar == null) {
            return;
        }
        d dVarN = fVar.n(d.b.LEFT);
        d dVarN2 = fVar.n(d.b.RIGHT);
        e eVar = this.Q;
        boolean z = eVar != null && eVar.P[0] == e.b.WRAP_CONTENT;
        if (this.K0 == 0) {
            dVarN = fVar.n(d.b.TOP);
            dVarN2 = fVar.n(d.b.BOTTOM);
            e eVar2 = this.Q;
            z = eVar2 != null && eVar2.P[1] == e.b.WRAP_CONTENT;
        }
        if (this.H0 != -1) {
            b.e.b.i iVarQ = dVar.q(this.J0);
            dVar.e(iVarQ, dVar.q(dVarN), this.H0, 8);
            if (z) {
                dVar.h(dVar.q(dVarN2), iVarQ, 0, 5);
                return;
            }
            return;
        }
        if (this.I0 == -1) {
            if (this.G0 != -1.0f) {
                dVar.d(b.e.b.d.s(dVar, dVar.q(this.J0), dVar.q(dVarN2), this.G0));
                return;
            }
            return;
        }
        b.e.b.i iVarQ2 = dVar.q(this.J0);
        b.e.b.i iVarQ3 = dVar.q(dVarN2);
        dVar.e(iVarQ2, iVarQ3, -this.I0, 8);
        if (z) {
            dVar.h(iVarQ2, dVar.q(dVarN), 0, 5);
            dVar.h(iVarQ3, iVarQ2, 0, 5);
        }
    }

    @Override // b.e.b.k.e
    public boolean g() {
        return true;
    }

    @Override // b.e.b.k.e
    public void l(e eVar, HashMap<e, e> map) {
        super.l(eVar, map);
        h hVar = (h) eVar;
        this.G0 = hVar.G0;
        this.H0 = hVar.H0;
        this.I0 = hVar.I0;
        S0(hVar.K0);
    }

    @Override // b.e.b.k.e
    public d n(d.b bVar) {
        switch (a.f2463a[bVar.ordinal()]) {
            case 1:
            case 2:
                if (this.K0 == 1) {
                    return this.J0;
                }
                break;
            case 3:
            case 4:
                if (this.K0 == 0) {
                    return this.J0;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return null;
        }
        throw new AssertionError(bVar.name());
    }
}
