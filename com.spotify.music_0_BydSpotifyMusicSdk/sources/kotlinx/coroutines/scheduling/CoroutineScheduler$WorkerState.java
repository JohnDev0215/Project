package kotlinx.coroutines.scheduling;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class CoroutineScheduler$WorkerState {
    public static final CoroutineScheduler$WorkerState c;
    public static final CoroutineScheduler$WorkerState e;
    public static final CoroutineScheduler$WorkerState k;
    public static final CoroutineScheduler$WorkerState l;
    public static final CoroutineScheduler$WorkerState m;
    public static final /* synthetic */ CoroutineScheduler$WorkerState[] n;

    static {
        CoroutineScheduler$WorkerState coroutineScheduler$WorkerState = new CoroutineScheduler$WorkerState("CPU_ACQUIRED", 0);
        c = coroutineScheduler$WorkerState;
        CoroutineScheduler$WorkerState coroutineScheduler$WorkerState2 = new CoroutineScheduler$WorkerState("BLOCKING", 1);
        e = coroutineScheduler$WorkerState2;
        CoroutineScheduler$WorkerState coroutineScheduler$WorkerState3 = new CoroutineScheduler$WorkerState("PARKING", 2);
        k = coroutineScheduler$WorkerState3;
        CoroutineScheduler$WorkerState coroutineScheduler$WorkerState4 = new CoroutineScheduler$WorkerState("DORMANT", 3);
        l = coroutineScheduler$WorkerState4;
        CoroutineScheduler$WorkerState coroutineScheduler$WorkerState5 = new CoroutineScheduler$WorkerState("TERMINATED", 4);
        m = coroutineScheduler$WorkerState5;
        n = new CoroutineScheduler$WorkerState[]{coroutineScheduler$WorkerState, coroutineScheduler$WorkerState2, coroutineScheduler$WorkerState3, coroutineScheduler$WorkerState4, coroutineScheduler$WorkerState5};
    }

    public static CoroutineScheduler$WorkerState valueOf(String str) {
        return (CoroutineScheduler$WorkerState) Enum.valueOf(CoroutineScheduler$WorkerState.class, str);
    }

    public static CoroutineScheduler$WorkerState[] values() {
        return (CoroutineScheduler$WorkerState[]) n.clone();
    }
}
