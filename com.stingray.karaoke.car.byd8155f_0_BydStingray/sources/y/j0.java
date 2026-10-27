package y;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class j0 {
    public static final j0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final j0 f23942m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ j0[] f23943n;

    static {
        j0 j0Var = new j0("Default", 0);
        l = j0Var;
        j0 j0Var2 = new j0("UserInput", 1);
        f23942m = j0Var2;
        f23943n = new j0[]{j0Var, j0Var2, new j0("PreventUserInput", 2)};
    }

    public static j0 valueOf(String str) {
        return (j0) Enum.valueOf(j0.class, str);
    }

    public static j0[] values() {
        return (j0[]) f23943n.clone();
    }
}
