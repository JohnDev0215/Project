package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.SolverVariable;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintAnchor {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConstraintWidget f1170b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Type f1171c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ConstraintAnchor f1172d;
    SolverVariable g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private HashSet<ConstraintAnchor> f1169a = null;
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
        static final /* synthetic */ int[] f1174a;

        static {
            int[] iArr = new int[Type.values().length];
            f1174a = iArr;
            try {
                iArr[Type.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1174a[Type.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1174a[Type.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1174a[Type.TOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1174a[Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1174a[Type.BASELINE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1174a[Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1174a[Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1174a[Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public ConstraintAnchor(ConstraintWidget constraintWidget, Type type) {
        this.f1170b = constraintWidget;
        this.f1171c = type;
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
        this.f1172d = constraintAnchor;
        if (constraintAnchor.f1169a == null) {
            constraintAnchor.f1169a = new HashSet<>();
        }
        this.f1172d.f1169a.add(this);
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
        if (this.f1170b.P() == 8) {
            return 0;
        }
        return (this.f <= -1 || (constraintAnchor = this.f1172d) == null || constraintAnchor.f1170b.P() != 8) ? this.e : this.f;
    }

    public final ConstraintAnchor d() {
        switch (a.f1174a[this.f1171c.ordinal()]) {
            case 1:
            case 6:
            case 7:
            case 8:
            case 9:
                return null;
            case 2:
                return this.f1170b.G;
            case 3:
                return this.f1170b.E;
            case 4:
                return this.f1170b.H;
            case 5:
                return this.f1170b.F;
            default:
                throw new AssertionError(this.f1171c.name());
        }
    }

    public ConstraintWidget e() {
        return this.f1170b;
    }

    public SolverVariable f() {
        return this.g;
    }

    public ConstraintAnchor g() {
        return this.f1172d;
    }

    public Type h() {
        return this.f1171c;
    }

    public boolean i() {
        HashSet<ConstraintAnchor> hashSet = this.f1169a;
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
        return this.f1172d != null;
    }

    public boolean k(ConstraintAnchor constraintAnchor) {
        if (constraintAnchor == null) {
            return false;
        }
        Type typeH = constraintAnchor.h();
        Type type = this.f1171c;
        if (typeH == type) {
            return type != Type.BASELINE || (constraintAnchor.e().T() && e().T());
        }
        switch (a.f1174a[type.ordinal()]) {
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
                throw new AssertionError(this.f1171c.name());
        }
    }

    public void l() {
        HashSet<ConstraintAnchor> hashSet;
        ConstraintAnchor constraintAnchor = this.f1172d;
        if (constraintAnchor != null && (hashSet = constraintAnchor.f1169a) != null) {
            hashSet.remove(this);
        }
        this.f1172d = null;
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
        return this.f1170b.s() + ":" + this.f1171c.toString();
    }
}
