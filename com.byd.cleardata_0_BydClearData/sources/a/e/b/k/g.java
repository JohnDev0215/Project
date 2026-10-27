package a.e.b.k;

import a.e.b.k.d;
import a.e.b.k.e;

/* JADX INFO: loaded from: classes.dex */
public class g extends e {
    protected float w0 = -1.0f;
    protected int x0 = -1;
    protected int y0 = -1;
    private d z0 = this.D;
    private int A0 = 0;
    private int B0 = 0;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f221a;

        static {
            int[] iArr = new int[d.b.values().length];
            f221a = iArr;
            try {
                iArr[d.b.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f221a[d.b.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f221a[d.b.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f221a[d.b.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f221a[d.b.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f221a[d.b.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f221a[d.b.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f221a[d.b.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f221a[d.b.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public g() {
        this.L.clear();
        this.L.add(this.z0);
        int length = this.K.length;
        for (int i = 0; i < length; i++) {
            this.K[i] = this.z0;
        }
    }

    @Override // a.e.b.k.e
    public void G0(a.e.b.d dVar) {
        if (E() == null) {
            return;
        }
        int iX = dVar.x(this.z0);
        if (this.A0 == 1) {
            C0(iX);
            D0(0);
            e0(E().t());
            B0(0);
            return;
        }
        C0(0);
        D0(iX);
        B0(E().N());
        e0(0);
    }

    public int H0() {
        return this.A0;
    }

    public int I0() {
        return this.x0;
    }

    public int J0() {
        return this.y0;
    }

    public float K0() {
        return this.w0;
    }

    public void L0(int i) {
        if (i > -1) {
            this.w0 = -1.0f;
            this.x0 = i;
            this.y0 = -1;
        }
    }

    public void M0(int i) {
        if (i > -1) {
            this.w0 = -1.0f;
            this.x0 = -1;
            this.y0 = i;
        }
    }

    public void N0(float f) {
        if (f > -1.0f) {
            this.w0 = f;
            this.x0 = -1;
            this.y0 = -1;
        }
    }

    public void O0(int i) {
        if (this.A0 == i) {
            return;
        }
        this.A0 = i;
        this.L.clear();
        this.z0 = this.A0 == 1 ? this.C : this.D;
        this.L.add(this.z0);
        int length = this.K.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.K[i2] = this.z0;
        }
    }

    @Override // a.e.b.k.e
    public void f(a.e.b.d dVar) {
        f fVar = (f) E();
        if (fVar == null) {
            return;
        }
        d dVarK = fVar.k(d.b.LEFT);
        d dVarK2 = fVar.k(d.b.RIGHT);
        e eVar = this.O;
        boolean z = eVar != null && eVar.N[0] == e.b.WRAP_CONTENT;
        if (this.A0 == 0) {
            dVarK = fVar.k(d.b.TOP);
            dVarK2 = fVar.k(d.b.BOTTOM);
            e eVar2 = this.O;
            z = eVar2 != null && eVar2.N[1] == e.b.WRAP_CONTENT;
        }
        if (this.x0 != -1) {
            a.e.b.i iVarQ = dVar.q(this.z0);
            dVar.e(iVarQ, dVar.q(dVarK), this.x0, 8);
            if (z) {
                dVar.h(dVar.q(dVarK2), iVarQ, 0, 5);
                return;
            }
            return;
        }
        if (this.y0 == -1) {
            if (this.w0 != -1.0f) {
                dVar.d(a.e.b.d.s(dVar, dVar.q(this.z0), dVar.q(dVarK2), this.w0));
                return;
            }
            return;
        }
        a.e.b.i iVarQ2 = dVar.q(this.z0);
        a.e.b.i iVarQ3 = dVar.q(dVarK2);
        dVar.e(iVarQ2, iVarQ3, -this.y0, 8);
        if (z) {
            dVar.h(iVarQ2, dVar.q(dVarK), 0, 5);
            dVar.h(iVarQ3, iVarQ2, 0, 5);
        }
    }

    @Override // a.e.b.k.e
    public boolean g() {
        return true;
    }

    @Override // a.e.b.k.e
    public d k(d.b bVar) {
        switch (a.f221a[bVar.ordinal()]) {
            case 1:
            case 2:
                if (this.A0 == 1) {
                    return this.z0;
                }
                break;
            case 3:
            case 4:
                if (this.A0 == 0) {
                    return this.z0;
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
