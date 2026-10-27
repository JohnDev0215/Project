package com.spotify.core.corefullsessionservice;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class CoreFullSessionService$CoreThreadPolicy {
    public static final CoreFullSessionService$CoreThreadPolicy c;
    public static final CoreFullSessionService$CoreThreadPolicy e;
    public static final /* synthetic */ CoreFullSessionService$CoreThreadPolicy[] k;

    static {
        CoreFullSessionService$CoreThreadPolicy coreFullSessionService$CoreThreadPolicy = new CoreFullSessionService$CoreThreadPolicy("RUN_ON_CORE_THREAD", 0);
        c = coreFullSessionService$CoreThreadPolicy;
        CoreFullSessionService$CoreThreadPolicy coreFullSessionService$CoreThreadPolicy2 = new CoreFullSessionService$CoreThreadPolicy("DO_NOT_RUN_ON_CORE_THREAD", 1);
        e = coreFullSessionService$CoreThreadPolicy2;
        k = new CoreFullSessionService$CoreThreadPolicy[]{coreFullSessionService$CoreThreadPolicy, coreFullSessionService$CoreThreadPolicy2};
    }

    public static CoreFullSessionService$CoreThreadPolicy valueOf(String str) {
        return (CoreFullSessionService$CoreThreadPolicy) Enum.valueOf(CoreFullSessionService$CoreThreadPolicy.class, str);
    }

    public static CoreFullSessionService$CoreThreadPolicy[] values() {
        return (CoreFullSessionService$CoreThreadPolicy[]) k.clone();
    }
}
