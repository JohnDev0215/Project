package S3;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class l {
    public static final l l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ l[] f7952m;

    static {
        l lVar = new l("Immediately", 0);
        l = lVar;
        l[] lVarArr = {lVar, new l("OnIterationFinish", 1)};
        f7952m = lVarArr;
        h6.r.e(lVarArr);
    }

    public static l valueOf(String str) {
        return (l) Enum.valueOf(l.class, str);
    }

    public static l[] values() {
        return (l[]) f7952m.clone();
    }
}
