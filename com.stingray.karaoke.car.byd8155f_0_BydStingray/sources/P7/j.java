package P7;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class j {
    public static final j l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final j f5439m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final j f5440n;
    public static final j o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ j[] f5441p;

    static {
        j jVar = new j("HIDDEN", 0);
        l = jVar;
        j jVar2 = new j("VISIBLE", 1);
        f5439m = jVar2;
        j jVar3 = new j("NOT_CONSIDERED", 2);
        f5440n = jVar3;
        j jVar4 = new j("DROP", 3);
        o = jVar4;
        f5441p = new j[]{jVar, jVar2, jVar3, jVar4};
    }

    public static j valueOf(String str) {
        return (j) Enum.valueOf(j.class, str);
    }

    public static j[] values() {
        return (j[]) f5441p.clone();
    }
}
