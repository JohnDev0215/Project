package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.SolverVariable;
import java.util.HashSet;
import java.util.Iterator;
import y.c;
import z.f;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintAnchor {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConstraintWidget f917b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Type f918c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ConstraintAnchor f919d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public SolverVariable f922g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashSet<ConstraintAnchor> f916a = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f920e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f921f = -1;

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
        public static final /* synthetic */ int[] f923a;

        static {
            int[] iArr = new int[Type.values().length];
            f923a = iArr;
            try {
                iArr[Type.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f923a[Type.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f923a[Type.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f923a[Type.TOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f923a[Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f923a[Type.BASELINE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f923a[Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f923a[Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f923a[Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public ConstraintAnchor(ConstraintWidget constraintWidget, Type type) {
        this.f917b = constraintWidget;
        this.f918c = type;
    }

    public boolean a(ConstraintAnchor constraintAnchor, int i8) {
        return b(constraintAnchor, i8, -1, false);
    }

    public boolean b(ConstraintAnchor constraintAnchor, int i8, int i9, boolean z7) {
        if (constraintAnchor == null) {
            l();
            return true;
        }
        if (!z7 && !k(constraintAnchor)) {
            return false;
        }
        this.f919d = constraintAnchor;
        if (constraintAnchor.f916a == null) {
            constraintAnchor.f916a = new HashSet<>();
        }
        this.f919d.f916a.add(this);
        if (i8 > 0) {
            this.f920e = i8;
        } else {
            this.f920e = 0;
        }
        this.f921f = i9;
        return true;
    }

    public int c() {
        ConstraintAnchor constraintAnchor;
        if (this.f917b.P() == 8) {
            return 0;
        }
        return (this.f921f <= -1 || (constraintAnchor = this.f919d) == null || constraintAnchor.f917b.P() != 8) ? this.f920e : this.f921f;
    }

    public final ConstraintAnchor d() {
        switch (a.f923a[this.f918c.ordinal()]) {
            case 1:
            case 6:
            case 7:
            case 8:
            case 9:
                return null;
            case 2:
                return this.f917b.G;
            case 3:
                return this.f917b.E;
            case 4:
                return this.f917b.H;
            case 5:
                return this.f917b.F;
            default:
                throw new AssertionError(this.f918c.name());
        }
    }

    public ConstraintWidget e() {
        return this.f917b;
    }

    public SolverVariable f() {
        return this.f922g;
    }

    public ConstraintAnchor g() {
        return this.f919d;
    }

    public Type h() {
        return this.f918c;
    }

    public boolean i() {
        HashSet<ConstraintAnchor> hashSet = this.f916a;
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
        return this.f919d != null;
    }

    public boolean k(ConstraintAnchor constraintAnchor) {
        if (constraintAnchor == null) {
            return false;
        }
        Type typeH = constraintAnchor.h();
        Type type = this.f918c;
        if (typeH == type) {
            return type != Type.BASELINE || (constraintAnchor.e().T() && e().T());
        }
        switch (a.f923a[type.ordinal()]) {
            case 1:
                return (typeH == Type.BASELINE || typeH == Type.CENTER_X || typeH == Type.CENTER_Y) ? false : true;
            case 2:
            case 3:
                boolean z7 = typeH == Type.LEFT || typeH == Type.RIGHT;
                if (constraintAnchor.e() instanceof f) {
                    return z7 || typeH == Type.CENTER_X;
                }
                return z7;
            case 4:
            case 5:
                boolean z8 = typeH == Type.TOP || typeH == Type.BOTTOM;
                if (constraintAnchor.e() instanceof f) {
                    return z8 || typeH == Type.CENTER_Y;
                }
                return z8;
            case 6:
            case 7:
            case 8:
            case 9:
                return false;
            default:
                throw new AssertionError(this.f918c.name());
        }
    }

    public void l() {
        HashSet<ConstraintAnchor> hashSet;
        ConstraintAnchor constraintAnchor = this.f919d;
        if (constraintAnchor != null && (hashSet = constraintAnchor.f916a) != null) {
            hashSet.remove(this);
        }
        this.f919d = null;
        this.f920e = 0;
        this.f921f = -1;
    }

    public void m(c cVar) {
        SolverVariable solverVariable = this.f922g;
        if (solverVariable == null) {
            this.f922g = new SolverVariable(SolverVariable.Type.UNRESTRICTED, null);
        } else {
            solverVariable.d();
        }
    }

    public void n(int i8) {
        if (j()) {
            this.f921f = i8;
        }
    }

    public String toString() {
        return this.f917b.s() + ":" + this.f918c.toString();
    }
}
