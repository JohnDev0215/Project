package com.spotify.eventsender.config;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class MutableEventSenderConfig$Effect {
    public static final MutableEventSenderConfig$Effect c;
    public static final MutableEventSenderConfig$Effect e;
    public static final MutableEventSenderConfig$Effect k;
    public static final /* synthetic */ MutableEventSenderConfig$Effect[] l;

    static {
        MutableEventSenderConfig$Effect mutableEventSenderConfig$Effect = new MutableEventSenderConfig$Effect("RESET_EVENT_SCHEDULER", 0);
        c = mutableEventSenderConfig$Effect;
        MutableEventSenderConfig$Effect mutableEventSenderConfig$Effect2 = new MutableEventSenderConfig$Effect("RESET_EVENT_MONITOR", 1);
        e = mutableEventSenderConfig$Effect2;
        MutableEventSenderConfig$Effect mutableEventSenderConfig$Effect3 = new MutableEventSenderConfig$Effect("UPDATE_RATE_LIMITER", 2);
        k = mutableEventSenderConfig$Effect3;
        l = new MutableEventSenderConfig$Effect[]{mutableEventSenderConfig$Effect, mutableEventSenderConfig$Effect2, mutableEventSenderConfig$Effect3};
    }

    public static MutableEventSenderConfig$Effect valueOf(String str) {
        return (MutableEventSenderConfig$Effect) Enum.valueOf(MutableEventSenderConfig$Effect.class, str);
    }

    public static MutableEventSenderConfig$Effect[] values() {
        return (MutableEventSenderConfig$Effect[]) l.clone();
    }
}
