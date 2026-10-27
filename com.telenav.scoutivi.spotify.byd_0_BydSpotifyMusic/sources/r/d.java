package r;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import o.i;
import s.o;

/* JADX INFO: compiled from: ConstraintAnchor.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f9194b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f9195c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final e f9196d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b f9197e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public d f9198f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    o.i f9201i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private HashSet<d> f9193a = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f9199g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    int f9200h = Integer.MIN_VALUE;

    /* JADX INFO: compiled from: ConstraintAnchor.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f9202a;

        static {
            int[] iArr = new int[b.values().length];
            f9202a = iArr;
            try {
                iArr[b.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9202a[b.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9202a[b.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9202a[b.TOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9202a[b.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9202a[b.BASELINE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9202a[b.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f9202a[b.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f9202a[b.NONE.ordinal()] = 9;
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
        this.f9196d = eVar;
        this.f9197e = bVar;
    }

    public boolean a(d dVar, int i7) {
        return b(dVar, i7, Integer.MIN_VALUE, false);
    }

    public boolean b(d dVar, int i7, int i8, boolean z6) {
        if (dVar == null) {
            q();
            return true;
        }
        if (!z6 && !p(dVar)) {
            return false;
        }
        this.f9198f = dVar;
        if (dVar.f9193a == null) {
            dVar.f9193a = new HashSet<>();
        }
        HashSet<d> hashSet = this.f9198f.f9193a;
        if (hashSet != null) {
            hashSet.add(this);
        }
        this.f9199g = i7;
        this.f9200h = i8;
        return true;
    }

    public void c(int i7, ArrayList<o> arrayList, o oVar) {
        HashSet<d> hashSet = this.f9193a;
        if (hashSet != null) {
            Iterator<d> it = hashSet.iterator();
            while (it.hasNext()) {
                s.i.a(it.next().f9196d, i7, arrayList, oVar);
            }
        }
    }

    public HashSet<d> d() {
        return this.f9193a;
    }

    public int e() {
        if (this.f9195c) {
            return this.f9194b;
        }
        return 0;
    }

    public int f() {
        d dVar;
        if (this.f9196d.X() == 8) {
            return 0;
        }
        return (this.f9200h == Integer.MIN_VALUE || (dVar = this.f9198f) == null || dVar.f9196d.X() != 8) ? this.f9199g : this.f9200h;
    }

    public final d g() {
        switch (a.f9202a[this.f9197e.ordinal()]) {
            case 1:
            case 6:
            case 7:
            case 8:
            case 9:
                return null;
            case 2:
                return this.f9196d.S;
            case 3:
                return this.f9196d.Q;
            case 4:
                return this.f9196d.T;
            case 5:
                return this.f9196d.R;
            default:
                throw new AssertionError(this.f9197e.name());
        }
    }

    public e h() {
        return this.f9196d;
    }

    public o.i i() {
        return this.f9201i;
    }

    public d j() {
        return this.f9198f;
    }

    public b k() {
        return this.f9197e;
    }

    public boolean l() {
        HashSet<d> hashSet = this.f9193a;
        if (hashSet == null) {
            return false;
        }
        Iterator<d> it = hashSet.iterator();
        while (it.hasNext()) {
            if (it.next().g().o()) {
                return true;
            }
        }
        return false;
    }

    public boolean m() {
        HashSet<d> hashSet = this.f9193a;
        return hashSet != null && hashSet.size() > 0;
    }

    public boolean n() {
        return this.f9195c;
    }

    public boolean o() {
        return this.f9198f != null;
    }

    public boolean p(d dVar) {
        if (dVar == null) {
            return false;
        }
        b bVarK = dVar.k();
        b bVar = this.f9197e;
        if (bVarK == bVar) {
            return bVar != b.BASELINE || (dVar.h().b0() && h().b0());
        }
        switch (a.f9202a[bVar.ordinal()]) {
            case 1:
                return (bVarK == b.BASELINE || bVarK == b.CENTER_X || bVarK == b.CENTER_Y) ? false : true;
            case 2:
            case 3:
                boolean z6 = bVarK == b.LEFT || bVarK == b.RIGHT;
                if (dVar.h() instanceof h) {
                    return z6 || bVarK == b.CENTER_X;
                }
                return z6;
            case 4:
            case 5:
                boolean z7 = bVarK == b.TOP || bVarK == b.BOTTOM;
                if (dVar.h() instanceof h) {
                    return z7 || bVarK == b.CENTER_Y;
                }
                return z7;
            case 6:
                return (bVarK == b.LEFT || bVarK == b.RIGHT) ? false : true;
            case 7:
            case 8:
            case 9:
                return false;
            default:
                throw new AssertionError(this.f9197e.name());
        }
    }

    public void q() {
        HashSet<d> hashSet;
        d dVar = this.f9198f;
        if (dVar != null && (hashSet = dVar.f9193a) != null) {
            hashSet.remove(this);
            if (this.f9198f.f9193a.size() == 0) {
                this.f9198f.f9193a = null;
            }
        }
        this.f9193a = null;
        this.f9198f = null;
        this.f9199g = 0;
        this.f9200h = Integer.MIN_VALUE;
        this.f9195c = false;
        this.f9194b = 0;
    }

    public void r() {
        this.f9195c = false;
        this.f9194b = 0;
    }

    public void s(o.c cVar) {
        o.i iVar = this.f9201i;
        if (iVar == null) {
            this.f9201i = new o.i(i.a.UNRESTRICTED, null);
        } else {
            iVar.e();
        }
    }

    public void t(int i7) {
        this.f9194b = i7;
        this.f9195c = true;
    }

    public String toString() {
        return this.f9196d.v() + ":" + this.f9197e.toString();
    }

    public void u(int i7) {
        if (o()) {
            this.f9200h = i7;
        }
    }
}
