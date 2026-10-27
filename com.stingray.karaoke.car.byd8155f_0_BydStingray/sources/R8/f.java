package r8;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class f {
    public static final f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final f f21883m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final f f21884n;
    public static final /* synthetic */ f[] o;

    static {
        f fVar = new f("OVERRIDABLE", 0);
        l = fVar;
        f fVar2 = new f("CONFLICT", 1);
        f fVar3 = new f("INCOMPATIBLE", 2);
        f21883m = fVar3;
        f fVar4 = new f("UNKNOWN", 3);
        f21884n = fVar4;
        o = new f[]{fVar, fVar2, fVar3, fVar4};
    }

    public static f valueOf(String str) {
        return (f) Enum.valueOf(f.class, str);
    }

    public static f[] values() {
        return (f[]) o.clone();
    }
}
