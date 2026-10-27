package androidx.constraintlayout.core.widgets;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class ConstraintAnchor$Type {
    public static final ConstraintAnchor$Type c;
    public static final ConstraintAnchor$Type e;
    public static final ConstraintAnchor$Type k;
    public static final ConstraintAnchor$Type l;
    public static final ConstraintAnchor$Type m;
    public static final ConstraintAnchor$Type n;
    public static final ConstraintAnchor$Type o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final ConstraintAnchor$Type f12p;
    public static final /* synthetic */ ConstraintAnchor$Type[] q;

    /* JADX INFO: Fake field, exist only in values array */
    ConstraintAnchor$Type EF0;

    static {
        ConstraintAnchor$Type constraintAnchor$Type = new ConstraintAnchor$Type("NONE", 0);
        ConstraintAnchor$Type constraintAnchor$Type2 = new ConstraintAnchor$Type("LEFT", 1);
        c = constraintAnchor$Type2;
        ConstraintAnchor$Type constraintAnchor$Type3 = new ConstraintAnchor$Type("TOP", 2);
        e = constraintAnchor$Type3;
        ConstraintAnchor$Type constraintAnchor$Type4 = new ConstraintAnchor$Type("RIGHT", 3);
        k = constraintAnchor$Type4;
        ConstraintAnchor$Type constraintAnchor$Type5 = new ConstraintAnchor$Type("BOTTOM", 4);
        l = constraintAnchor$Type5;
        ConstraintAnchor$Type constraintAnchor$Type6 = new ConstraintAnchor$Type("BASELINE", 5);
        m = constraintAnchor$Type6;
        ConstraintAnchor$Type constraintAnchor$Type7 = new ConstraintAnchor$Type("CENTER", 6);
        n = constraintAnchor$Type7;
        ConstraintAnchor$Type constraintAnchor$Type8 = new ConstraintAnchor$Type("CENTER_X", 7);
        o = constraintAnchor$Type8;
        ConstraintAnchor$Type constraintAnchor$Type9 = new ConstraintAnchor$Type("CENTER_Y", 8);
        f12p = constraintAnchor$Type9;
        q = new ConstraintAnchor$Type[]{constraintAnchor$Type, constraintAnchor$Type2, constraintAnchor$Type3, constraintAnchor$Type4, constraintAnchor$Type5, constraintAnchor$Type6, constraintAnchor$Type7, constraintAnchor$Type8, constraintAnchor$Type9};
    }

    public static ConstraintAnchor$Type valueOf(String str) {
        return (ConstraintAnchor$Type) Enum.valueOf(ConstraintAnchor$Type.class, str);
    }

    public static ConstraintAnchor$Type[] values() {
        return (ConstraintAnchor$Type[]) q.clone();
    }
}
