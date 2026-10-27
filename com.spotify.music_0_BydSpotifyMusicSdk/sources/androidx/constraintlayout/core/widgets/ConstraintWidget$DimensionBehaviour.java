package androidx.constraintlayout.core.widgets;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class ConstraintWidget$DimensionBehaviour {
    public static final ConstraintWidget$DimensionBehaviour c;
    public static final ConstraintWidget$DimensionBehaviour e;
    public static final ConstraintWidget$DimensionBehaviour k;
    public static final ConstraintWidget$DimensionBehaviour l;
    public static final /* synthetic */ ConstraintWidget$DimensionBehaviour[] m;

    static {
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = new ConstraintWidget$DimensionBehaviour("FIXED", 0);
        c = constraintWidget$DimensionBehaviour;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = new ConstraintWidget$DimensionBehaviour("WRAP_CONTENT", 1);
        e = constraintWidget$DimensionBehaviour2;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = new ConstraintWidget$DimensionBehaviour("MATCH_CONSTRAINT", 2);
        k = constraintWidget$DimensionBehaviour3;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour4 = new ConstraintWidget$DimensionBehaviour("MATCH_PARENT", 3);
        l = constraintWidget$DimensionBehaviour4;
        m = new ConstraintWidget$DimensionBehaviour[]{constraintWidget$DimensionBehaviour, constraintWidget$DimensionBehaviour2, constraintWidget$DimensionBehaviour3, constraintWidget$DimensionBehaviour4};
    }

    public static ConstraintWidget$DimensionBehaviour valueOf(String str) {
        return (ConstraintWidget$DimensionBehaviour) Enum.valueOf(ConstraintWidget$DimensionBehaviour.class, str);
    }

    public static ConstraintWidget$DimensionBehaviour[] values() {
        return (ConstraintWidget$DimensionBehaviour[]) m.clone();
    }
}
