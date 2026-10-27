package androidx.work;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class WorkInfo$State {
    public static final WorkInfo$State c;
    public static final WorkInfo$State e;
    public static final WorkInfo$State k;
    public static final WorkInfo$State l;
    public static final WorkInfo$State m;
    public static final WorkInfo$State n;
    public static final /* synthetic */ WorkInfo$State[] o;

    static {
        WorkInfo$State workInfo$State = new WorkInfo$State("ENQUEUED", 0);
        c = workInfo$State;
        WorkInfo$State workInfo$State2 = new WorkInfo$State("RUNNING", 1);
        e = workInfo$State2;
        WorkInfo$State workInfo$State3 = new WorkInfo$State("SUCCEEDED", 2);
        k = workInfo$State3;
        WorkInfo$State workInfo$State4 = new WorkInfo$State("FAILED", 3);
        l = workInfo$State4;
        WorkInfo$State workInfo$State5 = new WorkInfo$State("BLOCKED", 4);
        m = workInfo$State5;
        WorkInfo$State workInfo$State6 = new WorkInfo$State("CANCELLED", 5);
        n = workInfo$State6;
        o = new WorkInfo$State[]{workInfo$State, workInfo$State2, workInfo$State3, workInfo$State4, workInfo$State5, workInfo$State6};
    }

    public static WorkInfo$State valueOf(String str) {
        return (WorkInfo$State) Enum.valueOf(WorkInfo$State.class, str);
    }

    public static WorkInfo$State[] values() {
        return (WorkInfo$State[]) o.clone();
    }

    public final boolean a() {
        return this == k || this == l || this == n;
    }
}
