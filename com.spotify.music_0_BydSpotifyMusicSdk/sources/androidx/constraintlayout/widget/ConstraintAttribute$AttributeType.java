package androidx.constraintlayout.widget;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class ConstraintAttribute$AttributeType {
    public static final ConstraintAttribute$AttributeType c;
    public static final ConstraintAttribute$AttributeType e;
    public static final ConstraintAttribute$AttributeType k;
    public static final ConstraintAttribute$AttributeType l;
    public static final ConstraintAttribute$AttributeType m;
    public static final ConstraintAttribute$AttributeType n;
    public static final ConstraintAttribute$AttributeType o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final ConstraintAttribute$AttributeType f15p;
    public static final /* synthetic */ ConstraintAttribute$AttributeType[] q;

    static {
        ConstraintAttribute$AttributeType constraintAttribute$AttributeType = new ConstraintAttribute$AttributeType("INT_TYPE", 0);
        c = constraintAttribute$AttributeType;
        ConstraintAttribute$AttributeType constraintAttribute$AttributeType2 = new ConstraintAttribute$AttributeType("FLOAT_TYPE", 1);
        e = constraintAttribute$AttributeType2;
        ConstraintAttribute$AttributeType constraintAttribute$AttributeType3 = new ConstraintAttribute$AttributeType("COLOR_TYPE", 2);
        k = constraintAttribute$AttributeType3;
        ConstraintAttribute$AttributeType constraintAttribute$AttributeType4 = new ConstraintAttribute$AttributeType("COLOR_DRAWABLE_TYPE", 3);
        l = constraintAttribute$AttributeType4;
        ConstraintAttribute$AttributeType constraintAttribute$AttributeType5 = new ConstraintAttribute$AttributeType("STRING_TYPE", 4);
        m = constraintAttribute$AttributeType5;
        ConstraintAttribute$AttributeType constraintAttribute$AttributeType6 = new ConstraintAttribute$AttributeType("BOOLEAN_TYPE", 5);
        n = constraintAttribute$AttributeType6;
        ConstraintAttribute$AttributeType constraintAttribute$AttributeType7 = new ConstraintAttribute$AttributeType("DIMENSION_TYPE", 6);
        o = constraintAttribute$AttributeType7;
        ConstraintAttribute$AttributeType constraintAttribute$AttributeType8 = new ConstraintAttribute$AttributeType("REFERENCE_TYPE", 7);
        f15p = constraintAttribute$AttributeType8;
        q = new ConstraintAttribute$AttributeType[]{constraintAttribute$AttributeType, constraintAttribute$AttributeType2, constraintAttribute$AttributeType3, constraintAttribute$AttributeType4, constraintAttribute$AttributeType5, constraintAttribute$AttributeType6, constraintAttribute$AttributeType7, constraintAttribute$AttributeType8};
    }

    public static ConstraintAttribute$AttributeType valueOf(String str) {
        return (ConstraintAttribute$AttributeType) Enum.valueOf(ConstraintAttribute$AttributeType.class, str);
    }

    public static ConstraintAttribute$AttributeType[] values() {
        return (ConstraintAttribute$AttributeType[]) q.clone();
    }
}
