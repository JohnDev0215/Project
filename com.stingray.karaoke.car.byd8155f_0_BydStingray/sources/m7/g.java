package m7;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class g {
    public static final g l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final g f19257m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ g[] f19258n;

    static {
        g gVar = new g("SYNCHRONIZED", 0);
        g gVar2 = new g("PUBLICATION", 1);
        l = gVar2;
        g gVar3 = new g("NONE", 2);
        f19257m = gVar3;
        g[] gVarArr = {gVar, gVar2, gVar3};
        f19258n = gVarArr;
        h6.r.e(gVarArr);
    }

    public static g valueOf(String str) {
        return (g) Enum.valueOf(g.class, str);
    }

    public static g[] values() {
        return (g[]) f19258n.clone();
    }
}
