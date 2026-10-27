package A9;

import h6.r;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class g {
    public static final g l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final g f481m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final g f482n;
    public static final g o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ g[] f483p;

    static {
        g gVar = new g("GET", 0);
        l = gVar;
        g gVar2 = new g("DELETE", 1);
        f481m = gVar2;
        g gVar3 = new g("HEAD", 2);
        g gVar4 = new g("OPTIONS", 3);
        g gVar5 = new g("PATCH", 4);
        g gVar6 = new g("POST", 5);
        f482n = gVar6;
        g gVar7 = new g("PUT", 6);
        o = gVar7;
        g[] gVarArr = {gVar, gVar2, gVar3, gVar4, gVar5, gVar6, gVar7};
        f483p = gVarArr;
        r.e(gVarArr);
    }

    public static g valueOf(String str) {
        return (g) Enum.valueOf(g.class, str);
    }

    public static g[] values() {
        return (g[]) f483p.clone();
    }
}
