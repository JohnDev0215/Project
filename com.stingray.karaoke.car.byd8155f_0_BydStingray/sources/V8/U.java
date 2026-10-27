package V8;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class U {
    public static final U l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final U f9595m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final U f9596n;
    public static final /* synthetic */ U[] o;

    static {
        U u4 = new U("START", 0);
        l = u4;
        U u10 = new U("STOP", 1);
        f9595m = u10;
        U u11 = new U("STOP_AND_RESET_REPLAY_CACHE", 2);
        f9596n = u11;
        U[] uArr = {u4, u10, u11};
        o = uArr;
        h6.r.e(uArr);
    }

    public static U valueOf(String str) {
        return (U) Enum.valueOf(U.class, str);
    }

    public static U[] values() {
        return (U[]) o.clone();
    }
}
