package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.analyzer.n;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintAnchor {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f816b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f817c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ConstraintWidget f818d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Type f819e;
    public ConstraintAnchor f;
    SolverVariable i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private HashSet<ConstraintAnchor> f815a = null;
    public int g = 0;
    int h = Integer.MIN_VALUE;

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
        static final /* synthetic */ int[] f821a;

        static {
            int[] iArr = new int[Type.values().length];
            f821a = iArr;
            try {
                iArr[Type.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f821a[Type.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f821a[Type.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f821a[Type.TOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f821a[Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f821a[Type.BASELINE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f821a[Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f821a[Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f821a[Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public ConstraintAnchor(ConstraintWidget constraintWidget, Type type) {
        this.f818d = constraintWidget;
        this.f819e = type;
    }

    public boolean a(ConstraintAnchor constraintAnchor, int i) {
        return b(constraintAnchor, i, Integer.MIN_VALUE, false);
    }

    public boolean b(ConstraintAnchor constraintAnchor, int i, int i2, boolean z) {
        if (constraintAnchor == null) {
            q();
            return true;
        }
        if (!z && !p(constraintAnchor)) {
            return false;
        }
        this.f = constraintAnchor;
        if (constraintAnchor.f815a == null) {
            constraintAnchor.f815a = new HashSet<>();
        }
        HashSet<ConstraintAnchor> hashSet = this.f.f815a;
        if (hashSet != null) {
            hashSet.add(this);
        }
        this.g = i;
        this.h = i2;
        return true;
    }

    public void c(int i, ArrayList<n> arrayList, n nVar) {
        HashSet<ConstraintAnchor> hashSet = this.f815a;
        if (hashSet != null) {
            Iterator<ConstraintAnchor> it = hashSet.iterator();
            while (it.hasNext()) {
                androidx.constraintlayout.core.widgets.analyzer.h.a(it.next().f818d, i, arrayList, nVar);
            }
        }
    }

    public HashSet<ConstraintAnchor> d() {
        return this.f815a;
    }

    public int e() {
        if (this.f817c) {
            return this.f816b;
        }
        return 0;
    }

    public int f() {
        ConstraintAnchor constraintAnchor;
        if (this.f818d.X() == 8) {
            return 0;
        }
        return (this.h == Integer.MIN_VALUE || (constraintAnchor = this.f) == null || constraintAnchor.f818d.X() != 8) ? this.g : this.h;
    }

    public final ConstraintAnchor g() {
        switch (a.f821a[this.f819e.ordinal()]) {
            case 1:
            case 6:
            case 7:
            case 8:
            case 9:
                return null;
            case 2:
                return this.f818d.T;
            case 3:
                return this.f818d.R;
            case 4:
                return this.f818d.U;
            case 5:
                return this.f818d.S;
            default:
                throw new AssertionError(this.f819e.name());
        }
    }

    public ConstraintWidget h() {
        return this.f818d;
    }

    public SolverVariable i() {
        return this.i;
    }

    public ConstraintAnchor j() {
        return this.f;
    }

    public Type k() {
        return this.f819e;
    }

    public boolean l() {
        HashSet<ConstraintAnchor> hashSet = this.f815a;
        if (hashSet == null) {
            return false;
        }
        Iterator<ConstraintAnchor> it = hashSet.iterator();
        while (it.hasNext()) {
            if (it.next().g().o()) {
                return true;
            }
        }
        return false;
    }

    public boolean m() {
        HashSet<ConstraintAnchor> hashSet = this.f815a;
        return hashSet != null && hashSet.size() > 0;
    }

    public boolean n() {
        return this.f817c;
    }

    public boolean o() {
        return this.f != null;
    }

    public boolean p(ConstraintAnchor constraintAnchor) {
        if (constraintAnchor == null) {
            return false;
        }
        Type typeK = constraintAnchor.k();
        Type type = this.f819e;
        if (typeK == type) {
            return type != Type.BASELINE || (constraintAnchor.h().b0() && h().b0());
        }
        switch (a.f821a[type.ordinal()]) {
            case 1:
                return (typeK == Type.BASELINE || typeK == Type.CENTER_X || typeK == Type.CENTER_Y) ? false : true;
            case 2:
            case 3:
                boolean z = typeK == Type.LEFT || typeK == Type.RIGHT;
                if (constraintAnchor.h() instanceof f) {
                    return z || typeK == Type.CENTER_X;
                }
                return z;
            case 4:
            case 5:
                boolean z2 = typeK == Type.TOP || typeK == Type.BOTTOM;
                if (constraintAnchor.h() instanceof f) {
                    return z2 || typeK == Type.CENTER_Y;
                }
                return z2;
            case 6:
                return (typeK == Type.LEFT || typeK == Type.RIGHT) ? false : true;
            case 7:
            case 8:
            case 9:
                return false;
            default:
                throw new AssertionError(this.f819e.name());
        }
    }

    public void q() {
        HashSet<ConstraintAnchor> hashSet;
        ConstraintAnchor constraintAnchor = this.f;
        if (constraintAnchor != null && (hashSet = constraintAnchor.f815a) != null) {
            hashSet.remove(this);
            if (this.f.f815a.size() == 0) {
                this.f.f815a = null;
            }
        }
        this.f815a = null;
        this.f = null;
        this.g = 0;
        this.h = Integer.MIN_VALUE;
        this.f817c = false;
        this.f816b = 0;
    }

    public void r() {
        this.f817c = false;
        this.f816b = 0;
    }

    public void s(androidx.constraintlayout.core.c cVar) {
        SolverVariable solverVariable = this.i;
        if (solverVariable == null) {
            this.i = new SolverVariable(SolverVariable.Type.UNRESTRICTED, null);
        } else {
            solverVariable.h();
        }
    }

    public void t(int i) {
        this.f816b = i;
        this.f817c = true;
    }

    public String toString() {
        return this.f818d.v() + ":" + this.f819e.toString();
    }

    public void u(int i) {
        if (o()) {
            this.h = i;
        }
    }
}
