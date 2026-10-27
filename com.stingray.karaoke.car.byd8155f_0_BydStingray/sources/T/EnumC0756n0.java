package T;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: T.n0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC0756n0 {
    public static final EnumC0756n0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final EnumC0756n0 f8276m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final EnumC0756n0 f8277n;
    public static final EnumC0756n0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final EnumC0756n0 f8278p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final EnumC0756n0 f8279q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final /* synthetic */ EnumC0756n0[] f8280r;

    static {
        EnumC0756n0 enumC0756n0 = new EnumC0756n0("ShutDown", 0);
        l = enumC0756n0;
        EnumC0756n0 enumC0756n02 = new EnumC0756n0("ShuttingDown", 1);
        f8276m = enumC0756n02;
        EnumC0756n0 enumC0756n03 = new EnumC0756n0("Inactive", 2);
        f8277n = enumC0756n03;
        EnumC0756n0 enumC0756n04 = new EnumC0756n0("InactivePendingWork", 3);
        o = enumC0756n04;
        EnumC0756n0 enumC0756n05 = new EnumC0756n0("Idle", 4);
        f8278p = enumC0756n05;
        EnumC0756n0 enumC0756n06 = new EnumC0756n0("PendingWork", 5);
        f8279q = enumC0756n06;
        f8280r = new EnumC0756n0[]{enumC0756n0, enumC0756n02, enumC0756n03, enumC0756n04, enumC0756n05, enumC0756n06};
    }

    public static EnumC0756n0 valueOf(String str) {
        return (EnumC0756n0) Enum.valueOf(EnumC0756n0.class, str);
    }

    public static EnumC0756n0[] values() {
        return (EnumC0756n0[]) f8280r.clone();
    }
}
