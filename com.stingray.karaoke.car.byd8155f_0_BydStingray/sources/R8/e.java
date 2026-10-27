package r8;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class e {
    public static final e l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final e f21881m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final e f21882n;
    public static final /* synthetic */ e[] o;

    static {
        e eVar = new e("CONFLICTS_ONLY", 0);
        l = eVar;
        e eVar2 = new e("SUCCESS_ONLY", 1);
        f21881m = eVar2;
        e eVar3 = new e("BOTH", 2);
        f21882n = eVar3;
        o = new e[]{eVar, eVar2, eVar3};
    }

    public static e valueOf(String str) {
        return (e) Enum.valueOf(e.class, str);
    }

    public static e[] values() {
        return (e[]) o.clone();
    }
}
