package b.e.b.k;

import b.e.b.i;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: ConstraintAnchor.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f2423b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b f2424c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public d f2425d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    b.e.b.i f2428g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private HashSet<d> f2422a = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2426e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f2427f = -1;

    /* JADX INFO: compiled from: ConstraintAnchor.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2429a;

        static {
            int[] iArr = new int[b.values().length];
            f2429a = iArr;
            try {
                iArr[b.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2429a[b.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2429a[b.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2429a[b.TOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2429a[b.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2429a[b.BASELINE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2429a[b.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f2429a[b.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f2429a[b.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    /* JADX INFO: compiled from: ConstraintAnchor.java */
    public enum b {
        NONE,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        BASELINE,
        CENTER,
        CENTER_X,
        CENTER_Y
    }

    public d(e eVar, b bVar) {
        this.f2423b = eVar;
        this.f2424c = bVar;
    }

    public boolean a(d dVar, int i2) {
        return b(dVar, i2, -1, false);
    }

    public boolean b(d dVar, int i2, int i3, boolean z) {
        if (dVar == null) {
            l();
            return true;
        }
        if (!z && !k(dVar)) {
            return false;
        }
        this.f2425d = dVar;
        if (dVar.f2422a == null) {
            dVar.f2422a = new HashSet<>();
        }
        this.f2425d.f2422a.add(this);
        if (i2 > 0) {
            this.f2426e = i2;
        } else {
            this.f2426e = 0;
        }
        this.f2427f = i3;
        return true;
    }

    public int c() {
        d dVar;
        if (this.f2423b.P() == 8) {
            return 0;
        }
        return (this.f2427f <= -1 || (dVar = this.f2425d) == null || dVar.f2423b.P() != 8) ? this.f2426e : this.f2427f;
    }

    public final d d() {
        switch (a.f2429a[this.f2424c.ordinal()]) {
            case 1:
            case 6:
            case 7:
            case 8:
            case 9:
                return null;
            case 2:
                return this.f2423b.G;
            case 3:
                return this.f2423b.E;
            case 4:
                return this.f2423b.H;
            case 5:
                return this.f2423b.F;
            default:
                throw new AssertionError(this.f2424c.name());
        }
    }

    public e e() {
        return this.f2423b;
    }

    public b.e.b.i f() {
        return this.f2428g;
    }

    public d g() {
        return this.f2425d;
    }

    public b h() {
        return this.f2424c;
    }

    public boolean i() {
        HashSet<d> hashSet = this.f2422a;
        if (hashSet == null) {
            return false;
        }
        Iterator<d> it = hashSet.iterator();
        while (it.hasNext()) {
            if (it.next().d().j()) {
                return true;
            }
        }
        return false;
    }

    public boolean j() {
        return this.f2425d != null;
    }

    public boolean k(d dVar) {
        if (dVar == null) {
            return false;
        }
        b bVarH = dVar.h();
        b bVar = this.f2424c;
        if (bVarH == bVar) {
            return bVar != b.BASELINE || (dVar.e().T() && e().T());
        }
        switch (a.f2429a[bVar.ordinal()]) {
            case 1:
                return (bVarH == b.BASELINE || bVarH == b.CENTER_X || bVarH == b.CENTER_Y) ? false : true;
            case 2:
            case 3:
                boolean z = bVarH == b.LEFT || bVarH == b.RIGHT;
                if (dVar.e() instanceof h) {
                    return z || bVarH == b.CENTER_X;
                }
                return z;
            case 4:
            case 5:
                boolean z2 = bVarH == b.TOP || bVarH == b.BOTTOM;
                if (dVar.e() instanceof h) {
                    return z2 || bVarH == b.CENTER_Y;
                }
                return z2;
            case 6:
            case 7:
            case 8:
            case 9:
                return false;
            default:
                throw new AssertionError(this.f2424c.name());
        }
    }

    public void l() {
        HashSet<d> hashSet;
        d dVar = this.f2425d;
        if (dVar != null && (hashSet = dVar.f2422a) != null) {
            hashSet.remove(this);
        }
        this.f2425d = null;
        this.f2426e = 0;
        this.f2427f = -1;
    }

    public void m(b.e.b.c cVar) {
        b.e.b.i iVar = this.f2428g;
        if (iVar == null) {
            this.f2428g = new b.e.b.i(i.a.UNRESTRICTED, null);
        } else {
            iVar.d();
        }
    }

    public void n(int i2) {
        if (j()) {
            this.f2427f = i2;
        }
    }

    public String toString() {
        return this.f2423b.s() + ":" + this.f2424c.toString();
    }
}
