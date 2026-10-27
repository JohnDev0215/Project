package a.e.b.k;

import a.e.b.i;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f205b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b f206c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public d f207d;
    a.e.b.i g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private HashSet<d> f204a = null;
    public int e = 0;
    int f = -1;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f208a;

        static {
            int[] iArr = new int[b.values().length];
            f208a = iArr;
            try {
                iArr[b.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f208a[b.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f208a[b.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f208a[b.TOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f208a[b.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f208a[b.BASELINE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f208a[b.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f208a[b.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f208a[b.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

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
        this.f205b = eVar;
        this.f206c = bVar;
    }

    public boolean a(d dVar, int i, int i2, boolean z) {
        if (dVar == null) {
            k();
            return true;
        }
        if (!z && !j(dVar)) {
            return false;
        }
        this.f207d = dVar;
        if (dVar.f204a == null) {
            dVar.f204a = new HashSet<>();
        }
        this.f207d.f204a.add(this);
        if (i > 0) {
            this.e = i;
        } else {
            this.e = 0;
        }
        this.f = i2;
        return true;
    }

    public int b() {
        d dVar;
        if (this.f205b.M() == 8) {
            return 0;
        }
        return (this.f <= -1 || (dVar = this.f207d) == null || dVar.f205b.M() != 8) ? this.e : this.f;
    }

    public final d c() {
        switch (a.f208a[this.f206c.ordinal()]) {
            case 1:
            case 6:
            case 7:
            case 8:
            case 9:
                return null;
            case 2:
                return this.f205b.E;
            case 3:
                return this.f205b.C;
            case 4:
                return this.f205b.F;
            case 5:
                return this.f205b.D;
            default:
                throw new AssertionError(this.f206c.name());
        }
    }

    public e d() {
        return this.f205b;
    }

    public a.e.b.i e() {
        return this.g;
    }

    public d f() {
        return this.f207d;
    }

    public b g() {
        return this.f206c;
    }

    public boolean h() {
        HashSet<d> hashSet = this.f204a;
        if (hashSet == null) {
            return false;
        }
        Iterator<d> it = hashSet.iterator();
        while (it.hasNext()) {
            if (it.next().c().i()) {
                return true;
            }
        }
        return false;
    }

    public boolean i() {
        return this.f207d != null;
    }

    public boolean j(d dVar) {
        if (dVar == null) {
            return false;
        }
        b bVarG = dVar.g();
        b bVar = this.f206c;
        if (bVarG == bVar) {
            return bVar != b.BASELINE || (dVar.d().Q() && d().Q());
        }
        switch (a.f208a[bVar.ordinal()]) {
            case 1:
                return (bVarG == b.BASELINE || bVarG == b.CENTER_X || bVarG == b.CENTER_Y) ? false : true;
            case 2:
            case 3:
                boolean z = bVarG == b.LEFT || bVarG == b.RIGHT;
                if (dVar.d() instanceof g) {
                    return z || bVarG == b.CENTER_X;
                }
                return z;
            case 4:
            case 5:
                boolean z2 = bVarG == b.TOP || bVarG == b.BOTTOM;
                if (dVar.d() instanceof g) {
                    return z2 || bVarG == b.CENTER_Y;
                }
                return z2;
            case 6:
            case 7:
            case 8:
            case 9:
                return false;
            default:
                throw new AssertionError(this.f206c.name());
        }
    }

    public void k() {
        HashSet<d> hashSet;
        d dVar = this.f207d;
        if (dVar != null && (hashSet = dVar.f204a) != null) {
            hashSet.remove(this);
        }
        this.f207d = null;
        this.e = 0;
        this.f = -1;
    }

    public void l(a.e.b.c cVar) {
        a.e.b.i iVar = this.g;
        if (iVar == null) {
            this.g = new a.e.b.i(i.a.UNRESTRICTED, null);
        } else {
            iVar.d();
        }
    }

    public String toString() {
        return this.f205b.p() + ":" + this.f206c.toString();
    }
}
