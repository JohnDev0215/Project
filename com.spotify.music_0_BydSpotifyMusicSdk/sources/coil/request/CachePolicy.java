package coil.request;

import kotlin.enums.a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class CachePolicy {
    public static final CachePolicy c;
    public static final CachePolicy e;
    public static final /* synthetic */ CachePolicy[] k;
    private final boolean readEnabled;
    private final boolean writeEnabled;

    static {
        CachePolicy cachePolicy = new CachePolicy("ENABLED", 0, true, true);
        c = cachePolicy;
        CachePolicy cachePolicy2 = new CachePolicy("READ_ONLY", 1, true, false);
        CachePolicy cachePolicy3 = new CachePolicy("WRITE_ONLY", 2, false, true);
        CachePolicy cachePolicy4 = new CachePolicy("DISABLED", 3, false, false);
        e = cachePolicy4;
        CachePolicy[] cachePolicyArr = {cachePolicy, cachePolicy2, cachePolicy3, cachePolicy4};
        k = cachePolicyArr;
        a.a(cachePolicyArr);
    }

    public CachePolicy(String str, int i, boolean z, boolean z2) {
        this.readEnabled = z;
        this.writeEnabled = z2;
    }

    public static CachePolicy valueOf(String str) {
        return (CachePolicy) Enum.valueOf(CachePolicy.class, str);
    }

    public static CachePolicy[] values() {
        return (CachePolicy[]) k.clone();
    }

    public final boolean a() {
        return this.readEnabled;
    }

    public final boolean b() {
        return this.writeEnabled;
    }
}
