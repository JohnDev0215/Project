package androidx.constraintlayout.core;

import com.spotify.voiceassistants.playermodels.ContextResponseKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class SolverVariable$Type {
    public static final SolverVariable$Type c;
    public static final SolverVariable$Type e;
    public static final SolverVariable$Type k;
    public static final SolverVariable$Type l;
    public static final /* synthetic */ SolverVariable$Type[] m;

    static {
        SolverVariable$Type solverVariable$Type = new SolverVariable$Type("UNRESTRICTED", 0);
        c = solverVariable$Type;
        SolverVariable$Type solverVariable$Type2 = new SolverVariable$Type("CONSTANT", 1);
        SolverVariable$Type solverVariable$Type3 = new SolverVariable$Type("SLACK", 2);
        e = solverVariable$Type3;
        SolverVariable$Type solverVariable$Type4 = new SolverVariable$Type(ContextResponseKt.RESULT_ERROR, 3);
        k = solverVariable$Type4;
        SolverVariable$Type solverVariable$Type5 = new SolverVariable$Type("UNKNOWN", 4);
        l = solverVariable$Type5;
        m = new SolverVariable$Type[]{solverVariable$Type, solverVariable$Type2, solverVariable$Type3, solverVariable$Type4, solverVariable$Type5};
    }

    public static SolverVariable$Type valueOf(String str) {
        return (SolverVariable$Type) Enum.valueOf(SolverVariable$Type.class, str);
    }

    public static SolverVariable$Type[] values() {
        return (SolverVariable$Type[]) m.clone();
    }
}
