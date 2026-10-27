package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.SolverVariable;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintAnchor {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConstraintWidget f651b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Type f652c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ConstraintAnchor f653d;
    SolverVariable g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private HashSet<ConstraintAnchor> f650a = null;
    public int e = 0;
    int f = -1;

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

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f655a;

        static {
            int[] iArr = new int[Type.values().length];
            f655a = iArr;
            try {
                iArr[Type.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f655a[Type.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f655a[Type.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f655a[Type.TOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f655a[Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f655a[Type.BASELINE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f655a[Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f655a[Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f655a[Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public ConstraintAnchor(ConstraintWidget constraintWidget, Type type) {
        this.f651b = constraintWidget;
        this.f652c = type;
    }

    public boolean a(ConstraintAnchor constraintAnchor, int i) {
        return b(constraintAnchor, i, -1, false);
    }

    public boolean b(ConstraintAnchor constraintAnchor, int i, int i2, boolean z) {
        if (constraintAnchor == null) {
            l();
            return true;
        }
        if (!z && !k(constraintAnchor)) {
            return false;
        }
        this.f653d = constraintAnchor;
        if (constraintAnchor.f650a == null) {
            constraintAnchor.f650a = new HashSet<>();
        }
        this.f653d.f650a.add(this);
        if (i > 0) {
            this.e = i;
        } else {
            this.e = 0;
        }
        this.f = i2;
        return true;
    }

    public int c() {
        ConstraintAnchor constraintAnchor;
        if (this.f651b.P() == 8) {
            return 0;
        }
        return (this.f <= -1 || (constraintAnchor = this.f653d) == null || constraintAnchor.f651b.P() != 8) ? this.e : this.f;
    }

    public final ConstraintAnchor d() {
        switch (a.f655a[this.f652c.ordinal()]) {
            case 1:
            case 6:
            case 7:
            case 8:
            case 9:
                return null;
            case 2:
                return this.f651b.G;
            case 3:
                return this.f651b.E;
            case 4:
                return this.f651b.H;
            case 5:
                return this.f651b.F;
            default:
                throw new AssertionError(this.f652c.name());
        }
    }

    public ConstraintWidget e() {
        return this.f651b;
    }

    public SolverVariable f() {
        return this.g;
    }

    public ConstraintAnchor g() {
        return this.f653d;
    }

    public Type h() {
        return this.f652c;
    }

    public boolean i() {
        HashSet<ConstraintAnchor> hashSet = this.f650a;
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
        return this.f653d != null;
    }

    public boolean k(ConstraintAnchor constraintAnchor) {
        if (constraintAnchor == null) {
            return false;
        }
        Type typeH = constraintAnchor.h();
        Type type = this.f652c;
        if (typeH == type) {
            return type != Type.BASELINE || (constraintAnchor.e().T() && e().T());
        }
        switch (a.f655a[type.ordinal()]) {
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
                throw new AssertionError(this.f652c.name());
        }
    }

    public void l() {
        HashSet<ConstraintAnchor> hashSet;
        ConstraintAnchor constraintAnchor = this.f653d;
        if (constraintAnchor != null && (hashSet = constraintAnchor.f650a) != null) {
            hashSet.remove(this);
        }
        this.f653d = null;
        this.e = 0;
        this.f = -1;
    }

    public void m(androidx.constraintlayout.solver.c cVar) {
        SolverVariable solverVariable = this.g;
        if (solverVariable == null) {
            this.g = new SolverVariable(SolverVariable.Type.UNRESTRICTED, null);
        } else {
            solverVariable.d();
        }
    }

    public void n(int i) {
        if (j()) {
            this.f = i;
        }
    }

    public String toString() {
        return this.f651b.s() + ":" + this.f652c.toString();
    }
}
