package defpackage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: ConstraintAnchor.java */
/* JADX INFO: loaded from: classes.dex */
public final class nx {
    public int b;
    public boolean c;
    public final qx d;
    public final a e;
    public nx f;
    public hq2 i;
    public HashSet<nx> a = null;
    public int g = 0;
    public int h = Integer.MIN_VALUE;

    /* JADX INFO: compiled from: ConstraintAnchor.java */
    public enum a {
        /* JADX INFO: Fake field, exist only in values array */
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

    public nx(qx qxVar, a aVar) {
        this.d = qxVar;
        this.e = aVar;
    }

    public final void a(nx nxVar, int i) {
        b(nxVar, i, Integer.MIN_VALUE, false);
    }

    public final boolean b(nx nxVar, int i, int i2, boolean z) {
        if (nxVar == null) {
            j();
            return true;
        }
        if (!z && !i(nxVar)) {
            return false;
        }
        this.f = nxVar;
        if (nxVar.a == null) {
            nxVar.a = new HashSet<>();
        }
        HashSet<nx> hashSet = this.f.a;
        if (hashSet != null) {
            hashSet.add(this);
        }
        this.g = i;
        this.h = i2;
        return true;
    }

    public final void c(int i, ft3 ft3Var, ArrayList arrayList) {
        HashSet<nx> hashSet = this.a;
        if (hashSet != null) {
            Iterator<nx> it = hashSet.iterator();
            while (it.hasNext()) {
                lt0.a(it.next().d, i, arrayList, ft3Var);
            }
        }
    }

    public final int d() {
        if (this.c) {
            return this.b;
        }
        return 0;
    }

    public final int e() {
        nx nxVar;
        if (this.d.j0 == 8) {
            return 0;
        }
        int i = this.h;
        return (i == Integer.MIN_VALUE || (nxVar = this.f) == null || nxVar.d.j0 != 8) ? this.g : i;
    }

    public final nx f() {
        a aVar = this.e;
        int iOrdinal = aVar.ordinal();
        qx qxVar = this.d;
        switch (iOrdinal) {
            case 0:
            case 5:
            case 6:
            case 7:
            case 8:
                return null;
            case 1:
                return qxVar.M;
            case 2:
                return qxVar.N;
            case 3:
                return qxVar.K;
            case 4:
                return qxVar.L;
            default:
                throw new AssertionError(aVar.name());
        }
    }

    public final boolean g() {
        HashSet<nx> hashSet = this.a;
        if (hashSet == null) {
            return false;
        }
        Iterator<nx> it = hashSet.iterator();
        while (it.hasNext()) {
            if (it.next().f().h()) {
                return true;
            }
        }
        return false;
    }

    public final boolean h() {
        return this.f != null;
    }

    public final boolean i(nx nxVar) {
        if (nxVar == null) {
            return false;
        }
        a aVar = a.BASELINE;
        a aVar2 = this.e;
        qx qxVar = nxVar.d;
        a aVar3 = nxVar.e;
        if (aVar3 == aVar2) {
            return aVar2 != aVar || (qxVar.F && this.d.F);
        }
        int iOrdinal = aVar2.ordinal();
        a aVar4 = a.CENTER_Y;
        a aVar5 = a.RIGHT;
        a aVar6 = a.CENTER_X;
        a aVar7 = a.LEFT;
        switch (iOrdinal) {
            case 0:
            case 7:
            case 8:
                return false;
            case 1:
            case 3:
                boolean z = aVar3 == aVar7 || aVar3 == aVar5;
                if (qxVar instanceof rt0) {
                    return z || aVar3 == aVar6;
                }
                return z;
            case 2:
            case 4:
                boolean z2 = aVar3 == a.TOP || aVar3 == a.BOTTOM;
                if (qxVar instanceof rt0) {
                    return z2 || aVar3 == aVar4;
                }
                return z2;
            case 5:
                return (aVar3 == aVar7 || aVar3 == aVar5) ? false : true;
            case 6:
                return (aVar3 == aVar || aVar3 == aVar6 || aVar3 == aVar4) ? false : true;
            default:
                throw new AssertionError(aVar2.name());
        }
    }

    public final void j() {
        HashSet<nx> hashSet;
        nx nxVar = this.f;
        if (nxVar != null && (hashSet = nxVar.a) != null) {
            hashSet.remove(this);
            if (this.f.a.size() == 0) {
                this.f.a = null;
            }
        }
        this.a = null;
        this.f = null;
        this.g = 0;
        this.h = Integer.MIN_VALUE;
        this.c = false;
        this.b = 0;
    }

    public final void k() {
        hq2 hq2Var = this.i;
        if (hq2Var == null) {
            this.i = new hq2(1);
        } else {
            hq2Var.f();
        }
    }

    public final void l(int i) {
        this.b = i;
        this.c = true;
    }

    public final String toString() {
        return this.d.l0 + ":" + this.e.toString();
    }
}
