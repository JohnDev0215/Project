package p;

import androidx.constraintlayout.core.SolverVariable$Type;
import androidx.constraintlayout.core.widgets.ConstraintAnchor$Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class xx {
    public int b;
    public boolean c;
    public final ty d;
    public final ConstraintAnchor$Type e;
    public xx f;
    public ce2 i;
    public HashSet a = null;
    public int g = 0;
    public int h = Integer.MIN_VALUE;

    public xx(ty tyVar, ConstraintAnchor$Type constraintAnchor$Type) {
        this.d = tyVar;
        this.e = constraintAnchor$Type;
    }

    public final void a(xx xxVar, int i, int i2) {
        if (xxVar == null) {
            g();
            return;
        }
        this.f = xxVar;
        if (xxVar.a == null) {
            xxVar.a = new HashSet();
        }
        HashSet hashSet = this.f.a;
        if (hashSet != null) {
            hashSet.add(this);
        }
        this.g = i;
        this.h = i2;
    }

    public final void b(int i, iw2 iw2Var, ArrayList arrayList) {
        HashSet hashSet = this.a;
        if (hashSet != null) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                he1.q(((xx) it.next()).d, i, arrayList, iw2Var);
            }
        }
    }

    public final int c() {
        if (this.c) {
            return this.b;
        }
        return 0;
    }

    public final int d() {
        xx xxVar;
        if (this.d.g0 == 8) {
            return 0;
        }
        int i = this.h;
        return (i == Integer.MIN_VALUE || (xxVar = this.f) == null || xxVar.d.g0 != 8) ? this.g : i;
    }

    public final boolean e() {
        xx xxVar;
        HashSet<xx> hashSet = this.a;
        if (hashSet == null) {
            return false;
        }
        for (xx xxVar2 : hashSet) {
            ConstraintAnchor$Type constraintAnchor$Type = xxVar2.e;
            int iOrdinal = constraintAnchor$Type.ordinal();
            ty tyVar = xxVar2.d;
            switch (iOrdinal) {
                case 0:
                case 5:
                case 6:
                case 7:
                case 8:
                    xxVar = null;
                    break;
                case 1:
                    xxVar = tyVar.J;
                    break;
                case 2:
                    xxVar = tyVar.K;
                    break;
                case 3:
                    xxVar = tyVar.H;
                    break;
                case 4:
                    xxVar = tyVar.I;
                    break;
                default:
                    throw new AssertionError(constraintAnchor$Type.name());
            }
            if (xxVar.f()) {
                return true;
            }
        }
        return false;
    }

    public final boolean f() {
        return this.f != null;
    }

    public final void g() {
        HashSet hashSet;
        xx xxVar = this.f;
        if (xxVar != null && (hashSet = xxVar.a) != null) {
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

    public final void h() {
        ce2 ce2Var = this.i;
        if (ce2Var == null) {
            this.i = new ce2(SolverVariable$Type.c);
        } else {
            ce2Var.c();
        }
    }

    public final void i(int i) {
        this.b = i;
        this.c = true;
    }

    public final String toString() {
        return this.d.h0 + ":" + this.e.toString();
    }
}
