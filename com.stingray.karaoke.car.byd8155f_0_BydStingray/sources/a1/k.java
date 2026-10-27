package a1;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class k {
    public static final k l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final k f11038m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ k[] f11039n;

    static {
        k kVar = new k("Ltr", 0);
        l = kVar;
        k kVar2 = new k("Rtl", 1);
        f11038m = kVar2;
        f11039n = new k[]{kVar, kVar2};
    }

    public static k valueOf(String str) {
        return (k) Enum.valueOf(k.class, str);
    }

    public static k[] values() {
        return (k[]) f11039n.clone();
    }
}
