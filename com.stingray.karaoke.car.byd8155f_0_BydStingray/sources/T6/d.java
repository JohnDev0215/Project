package T6;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static final d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final d f8748m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ d[] f8749n;

    static {
        d dVar = new d("BULLET", 0);
        l = dVar;
        d dVar2 = new d("ORDERED", 1);
        f8748m = dVar2;
        f8749n = new d[]{dVar, dVar2};
    }

    public static d valueOf(String str) {
        return (d) Enum.valueOf(d.class, str);
    }

    public static d[] values() {
        return (d[]) f8749n.clone();
    }
}
