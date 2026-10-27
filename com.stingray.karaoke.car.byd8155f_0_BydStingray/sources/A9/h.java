package A9;

import h6.r;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class h {
    public static final h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final h f484m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final h f485n;
    public static final h o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final h f486p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final /* synthetic */ h[] f487q;

    static {
        h hVar = new h("Success", 0);
        l = hVar;
        h hVar2 = new h("Informational", 1);
        f484m = hVar2;
        h hVar3 = new h("Redirection", 2);
        f485n = hVar3;
        h hVar4 = new h("ClientError", 3);
        o = hVar4;
        h hVar5 = new h("ServerError", 4);
        f486p = hVar5;
        h[] hVarArr = {hVar, hVar2, hVar3, hVar4, hVar5};
        f487q = hVarArr;
        r.e(hVarArr);
    }

    public static h valueOf(String str) {
        return (h) Enum.valueOf(h.class, str);
    }

    public static h[] values() {
        return (h[]) f487q.clone();
    }
}
