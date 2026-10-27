package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.SolverVariable;
import java.util.HashSet;
import java.util.Iterator;
import v.c;
import w.f;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintAnchor {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConstraintWidget f826b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Type f827c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ConstraintAnchor f828d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public SolverVariable f831g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashSet<ConstraintAnchor> f825a = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f829e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f830f = -1;

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
        public static final /* synthetic */ int[] f832a;

        static {
            int[] iArr = new int[Type.values().length];
            f832a = iArr;
            try {
                iArr[Type.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f832a[Type.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f832a[Type.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f832a[Type.TOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f832a[Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f832a[Type.BASELINE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f832a[Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f832a[Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f832a[Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public ConstraintAnchor(ConstraintWidget constraintWidget, Type type) {
        this.f826b = constraintWidget;
        this.f827c = type;
    }

    public boolean a(ConstraintAnchor constraintAnchor, int i3) {
        return b(constraintAnchor, i3, -1, false);
    }

    public boolean b(ConstraintAnchor constraintAnchor, int i3, int i4, boolean z3) {
        if (constraintAnchor == null) {
            l();
            return true;
        }
        if (!z3 && !k(constraintAnchor)) {
            return false;
        }
        this.f828d = constraintAnchor;
        if (constraintAnchor.f825a == null) {
            constraintAnchor.f825a = new HashSet<>();
        }
        this.f828d.f825a.add(this);
        if (i3 > 0) {
            this.f829e = i3;
        } else {
            this.f829e = 0;
        }
        this.f830f = i4;
        return true;
    }

    public int c() {
        ConstraintAnchor constraintAnchor;
        if (this.f826b.P() == 8) {
            return 0;
        }
        return (this.f830f <= -1 || (constraintAnchor = this.f828d) == null || constraintAnchor.f826b.P() != 8) ? this.f829e : this.f830f;
    }

    public final ConstraintAnchor d() {
        switch (a.f832a[this.f827c.ordinal()]) {
            case 1:
            case 6:
            case 7:
            case 8:
            case 9:
                return null;
            case 2:
                return this.f826b.G;
            case 3:
                return this.f826b.E;
            case 4:
                return this.f826b.H;
            case 5:
                return this.f826b.F;
            default:
                throw new AssertionError(this.f827c.name());
        }
    }

    public ConstraintWidget e() {
        return this.f826b;
    }

    public SolverVariable f() {
        return this.f831g;
    }

    public ConstraintAnchor g() {
        return this.f828d;
    }

    public Type h() {
        return this.f827c;
    }

    public boolean i() {
        HashSet<ConstraintAnchor> hashSet = this.f825a;
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
        return this.f828d != null;
    }

    public boolean k(ConstraintAnchor constraintAnchor) {
        if (constraintAnchor == null) {
            return false;
        }
        Type typeH = constraintAnchor.h();
        Type type = this.f827c;
        if (typeH == type) {
            return type != Type.BASELINE || (constraintAnchor.e().T() && e().T());
        }
        switch (a.f832a[type.ordinal()]) {
            case 1:
                return (typeH == Type.BASELINE || typeH == Type.CENTER_X || typeH == Type.CENTER_Y) ? false : true;
            case 2:
            case 3:
                boolean z3 = typeH == Type.LEFT || typeH == Type.RIGHT;
                if (constraintAnchor.e() instanceof f) {
                    return z3 || typeH == Type.CENTER_X;
                }
                return z3;
            case 4:
            case 5:
                boolean z4 = typeH == Type.TOP || typeH == Type.BOTTOM;
                if (constraintAnchor.e() instanceof f) {
                    return z4 || typeH == Type.CENTER_Y;
                }
                return z4;
            case 6:
            case 7:
            case 8:
            case 9:
                return false;
            default:
                throw new AssertionError(this.f827c.name());
        }
    }

    public void l() {
        HashSet<ConstraintAnchor> hashSet;
        ConstraintAnchor constraintAnchor = this.f828d;
        if (constraintAnchor != null && (hashSet = constraintAnchor.f825a) != null) {
            hashSet.remove(this);
        }
        this.f828d = null;
        this.f829e = 0;
        this.f830f = -1;
    }

    public void m(c cVar) {
        SolverVariable solverVariable = this.f831g;
        if (solverVariable == null) {
            this.f831g = new SolverVariable(SolverVariable.Type.UNRESTRICTED, null);
        } else {
            solverVariable.d();
        }
    }

    public void n(int i3) {
        if (j()) {
            this.f830f = i3;
        }
    }

    public String toString() {
        return this.f826b.s() + ":" + this.f827c.toString();
    }
}
