package m7;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static final b l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ b[] f19253m;

    static {
        b bVar = new b("WARNING", 0);
        l = bVar;
        b[] bVarArr = {bVar, new b("ERROR", 1), new b("HIDDEN", 2)};
        f19253m = bVarArr;
        h6.r.e(bVarArr);
    }

    public static b valueOf(String str) {
        return (b) Enum.valueOf(b.class, str);
    }

    public static b[] values() {
        return (b[]) f19253m.clone();
    }
}
