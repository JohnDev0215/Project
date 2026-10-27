package E4;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final a f1744m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final a f1745n;
    public static final a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ a[] f1746p;

    static {
        a aVar = new a("DarkPixel", 0);
        l = aVar;
        a aVar2 = new a("LightPixel", 1);
        f1744m = aVar2;
        a aVar3 = new a("Background", 2);
        f1745n = aVar3;
        a aVar4 = new a("Logo", 3);
        o = aVar4;
        f1746p = new a[]{aVar, aVar2, aVar3, aVar4, new a("VersionEye", 4)};
    }

    public static a valueOf(String str) {
        return (a) Enum.valueOf(a.class, str);
    }

    public static a[] values() {
        return (a[]) f1746p.clone();
    }
}
