package androidx.work;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class NetworkType {
    public static final NetworkType c;
    public static final NetworkType e;
    public static final NetworkType k;
    public static final NetworkType l;
    public static final NetworkType m;
    public static final NetworkType n;
    public static final /* synthetic */ NetworkType[] o;

    static {
        NetworkType networkType = new NetworkType("NOT_REQUIRED", 0);
        c = networkType;
        NetworkType networkType2 = new NetworkType("CONNECTED", 1);
        e = networkType2;
        NetworkType networkType3 = new NetworkType("UNMETERED", 2);
        k = networkType3;
        NetworkType networkType4 = new NetworkType("NOT_ROAMING", 3);
        l = networkType4;
        NetworkType networkType5 = new NetworkType("METERED", 4);
        m = networkType5;
        NetworkType networkType6 = new NetworkType("TEMPORARILY_UNMETERED", 5);
        n = networkType6;
        o = new NetworkType[]{networkType, networkType2, networkType3, networkType4, networkType5, networkType6};
    }

    public static NetworkType valueOf(String str) {
        return (NetworkType) Enum.valueOf(NetworkType.class, str);
    }

    public static NetworkType[] values() {
        return (NetworkType[]) o.clone();
    }
}
