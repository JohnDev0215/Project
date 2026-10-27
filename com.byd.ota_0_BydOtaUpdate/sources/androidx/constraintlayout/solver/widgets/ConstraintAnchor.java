package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.SolverVariable;
import b.e.b.c;
import b.e.b.j.f;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintAnchor {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConstraintWidget f330b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Type f331c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ConstraintAnchor f332d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public SolverVariable f335g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashSet<ConstraintAnchor> f329a = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f333e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f334f = -1;

    public enum Type {
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

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f337a;

        static {
            int[] iArr = new int[Type.values().length];
            f337a = iArr;
            try {
                iArr[Type.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f337a[Type.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f337a[Type.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f337a[Type.TOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f337a[Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f337a[Type.BASELINE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f337a[Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f337a[Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f337a[Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public ConstraintAnchor(ConstraintWidget constraintWidget, Type type) {
        this.f330b = constraintWidget;
        this.f331c = type;
    }

    public boolean a(ConstraintAnchor constraintAnchor, int i2) {
        return b(constraintAnchor, i2, -1, false);
    }

    public boolean b(ConstraintAnchor constraintAnchor, int i2, int i3, boolean z) {
        if (constraintAnchor == null) {
            l();
            return true;
        }
        if (!z && !k(constraintAnchor)) {
            return false;
        }
        this.f332d = constraintAnchor;
        if (constraintAnchor.f329a == null) {
            constraintAnchor.f329a = new HashSet<>();
        }
        this.f332d.f329a.add(this);
        if (i2 > 0) {
            this.f333e = i2;
        } else {
            this.f333e = 0;
        }
        this.f334f = i3;
        return true;
    }

    public int c() {
        ConstraintAnchor constraintAnchor;
        if (this.f330b.P() == 8) {
            return 0;
        }
        return (this.f334f <= -1 || (constraintAnchor = this.f332d) == null || constraintAnchor.f330b.P() != 8) ? this.f333e : this.f334f;
    }

    public final ConstraintAnchor d() {
        switch (a.f337a[this.f331c.ordinal()]) {
            case 1:
            case 6:
            case 7:
            case 8:
            case 9:
                return null;
            case 2:
                return this.f330b.G;
            case 3:
                return this.f330b.E;
            case 4:
                return this.f330b.H;
            case 5:
                return this.f330b.F;
            default:
                throw new AssertionError(this.f331c.name());
        }
    }

    public ConstraintWidget e() {
        return this.f330b;
    }

    public SolverVariable f() {
        return this.f335g;
    }

    public ConstraintAnchor g() {
        return this.f332d;
    }

    public Type h() {
        return this.f331c;
    }

    public boolean i() {
        HashSet<ConstraintAnchor> hashSet = this.f329a;
        if (hashSet == null) {
            return false;
        }
        Iterator<ConstraintAnchor> it = hashSet.iterator();
        while (it.hasNext()) {
            if (it.next().d().j()) {
                return true;
            }
        }
        return false;
    }

    public boolean j() {
        return this.f332d != null;
    }

    public boolean k(ConstraintAnchor constraintAnchor) {
        if (constraintAnchor == null) {
            return false;
        }
        Type typeH = constraintAnchor.h();
        Type type = this.f331c;
        if (typeH == type) {
            return type != Type.BASELINE || (constraintAnchor.e().T() && e().T());
        }
        switch (a.f337a[type.ordinal()]) {
            case 1:
                return (typeH == Type.BASELINE || typeH == Type.CENTER_X || typeH == Type.CENTER_Y) ? false : true;
            case 2:
            case 3:
                boolean z = typeH == Type.LEFT || typeH == Type.RIGHT;
                if (constraintAnchor.e() instanceof f) {
                    return z || typeH == Type.CENTER_X;
                }
                return z;
            case 4:
            case 5:
                boolean z2 = typeH == Type.TOP || typeH == Type.BOTTOM;
                if (constraintAnchor.e() instanceof f) {
                    return z2 || typeH == Type.CENTER_Y;
                }
                return z2;
            case 6:
            case 7:
            case 8:
            case 9:
                return false;
            default:
                throw new AssertionError(this.f331c.name());
        }
    }

    public void l() {
        HashSet<ConstraintAnchor> hashSet;
        ConstraintAnchor constraintAnchor = this.f332d;
        if (constraintAnchor != null && (hashSet = constraintAnchor.f329a) != null) {
            hashSet.remove(this);
        }
        this.f332d = null;
        this.f333e = 0;
        this.f334f = -1;
    }

    public void m(c cVar) {
        SolverVariable solverVariable = this.f335g;
        if (solverVariable == null) {
            this.f335g = new SolverVariable(SolverVariable.Type.UNRESTRICTED, null);
        } else {
            solverVariable.d();
        }
    }

    public void n(int i2) {
        if (j()) {
            this.f334f = i2;
        }
    }

    public String toString() {
        return this.f330b.s() + ":" + this.f331c.toString();
    }
}
