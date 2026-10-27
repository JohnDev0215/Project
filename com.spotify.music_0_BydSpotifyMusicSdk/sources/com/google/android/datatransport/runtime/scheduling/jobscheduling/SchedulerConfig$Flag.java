package com.google.android.datatransport.runtime.scheduling.jobscheduling;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class SchedulerConfig$Flag {
    public static final SchedulerConfig$Flag c;
    public static final SchedulerConfig$Flag e;
    public static final SchedulerConfig$Flag k;
    public static final /* synthetic */ SchedulerConfig$Flag[] l;

    static {
        SchedulerConfig$Flag schedulerConfig$Flag = new SchedulerConfig$Flag("NETWORK_UNMETERED", 0);
        c = schedulerConfig$Flag;
        SchedulerConfig$Flag schedulerConfig$Flag2 = new SchedulerConfig$Flag("DEVICE_IDLE", 1);
        e = schedulerConfig$Flag2;
        SchedulerConfig$Flag schedulerConfig$Flag3 = new SchedulerConfig$Flag("DEVICE_CHARGING", 2);
        k = schedulerConfig$Flag3;
        l = new SchedulerConfig$Flag[]{schedulerConfig$Flag, schedulerConfig$Flag2, schedulerConfig$Flag3};
    }

    public static SchedulerConfig$Flag valueOf(String str) {
        return (SchedulerConfig$Flag) Enum.valueOf(SchedulerConfig$Flag.class, str);
    }

    public static SchedulerConfig$Flag[] values() {
        return (SchedulerConfig$Flag[]) l.clone();
    }
}
