package com.spotify.core.corelimitedsessionservice;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class CoreLimitedSessionService$CoreThreadPolicy {
    public static final CoreLimitedSessionService$CoreThreadPolicy c;
    public static final CoreLimitedSessionService$CoreThreadPolicy e;
    public static final /* synthetic */ CoreLimitedSessionService$CoreThreadPolicy[] k;

    static {
        CoreLimitedSessionService$CoreThreadPolicy coreLimitedSessionService$CoreThreadPolicy = new CoreLimitedSessionService$CoreThreadPolicy("RUN_ON_CORE_THREAD", 0);
        c = coreLimitedSessionService$CoreThreadPolicy;
        CoreLimitedSessionService$CoreThreadPolicy coreLimitedSessionService$CoreThreadPolicy2 = new CoreLimitedSessionService$CoreThreadPolicy("DO_NOT_RUN_ON_CORE_THREAD", 1);
        e = coreLimitedSessionService$CoreThreadPolicy2;
        k = new CoreLimitedSessionService$CoreThreadPolicy[]{coreLimitedSessionService$CoreThreadPolicy, coreLimitedSessionService$CoreThreadPolicy2};
    }

    public static CoreLimitedSessionService$CoreThreadPolicy valueOf(String str) {
        return (CoreLimitedSessionService$CoreThreadPolicy) Enum.valueOf(CoreLimitedSessionService$CoreThreadPolicy.class, str);
    }

    public static CoreLimitedSessionService$CoreThreadPolicy[] values() {
        return (CoreLimitedSessionService$CoreThreadPolicy[]) k.clone();
    }
}
