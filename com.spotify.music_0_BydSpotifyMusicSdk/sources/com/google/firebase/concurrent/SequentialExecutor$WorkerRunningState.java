package com.google.firebase.concurrent;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
final class SequentialExecutor$WorkerRunningState {
    public static final SequentialExecutor$WorkerRunningState c;
    public static final SequentialExecutor$WorkerRunningState e;
    public static final SequentialExecutor$WorkerRunningState k;
    public static final SequentialExecutor$WorkerRunningState l;
    public static final /* synthetic */ SequentialExecutor$WorkerRunningState[] m;

    static {
        SequentialExecutor$WorkerRunningState sequentialExecutor$WorkerRunningState = new SequentialExecutor$WorkerRunningState("IDLE", 0);
        c = sequentialExecutor$WorkerRunningState;
        SequentialExecutor$WorkerRunningState sequentialExecutor$WorkerRunningState2 = new SequentialExecutor$WorkerRunningState("QUEUING", 1);
        e = sequentialExecutor$WorkerRunningState2;
        SequentialExecutor$WorkerRunningState sequentialExecutor$WorkerRunningState3 = new SequentialExecutor$WorkerRunningState("QUEUED", 2);
        k = sequentialExecutor$WorkerRunningState3;
        SequentialExecutor$WorkerRunningState sequentialExecutor$WorkerRunningState4 = new SequentialExecutor$WorkerRunningState("RUNNING", 3);
        l = sequentialExecutor$WorkerRunningState4;
        m = new SequentialExecutor$WorkerRunningState[]{sequentialExecutor$WorkerRunningState, sequentialExecutor$WorkerRunningState2, sequentialExecutor$WorkerRunningState3, sequentialExecutor$WorkerRunningState4};
    }

    public static SequentialExecutor$WorkerRunningState valueOf(String str) {
        return (SequentialExecutor$WorkerRunningState) Enum.valueOf(SequentialExecutor$WorkerRunningState.class, str);
    }

    public static SequentialExecutor$WorkerRunningState[] values() {
        return (SequentialExecutor$WorkerRunningState[]) m.clone();
    }
}
