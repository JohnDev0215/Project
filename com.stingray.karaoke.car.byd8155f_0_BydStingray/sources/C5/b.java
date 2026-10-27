package C5;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static final b l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ b[] f1002m;

    /* JADX INFO: Fake field, exist only in values array */
    b EF0;

    static {
        b bVar = new b("browser", 0);
        b bVar2 = new b("mobile", 1);
        l = bVar2;
        f1002m = new b[]{bVar, bVar2, new b("server", 2)};
    }

    public static b valueOf(String str) {
        return (b) Enum.valueOf(b.class, str);
    }

    public static b[] values() {
        return (b[]) f1002m.clone();
    }
}
