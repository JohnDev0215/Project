package L3;

import h6.r;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static final d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final d f4001m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final d f4002n;
    public static final /* synthetic */ d[] o;

    static {
        d dVar = new d("EXACT", 0);
        l = dVar;
        d dVar2 = new d("INEXACT", 1);
        f4001m = dVar2;
        d dVar3 = new d("AUTOMATIC", 2);
        f4002n = dVar3;
        d[] dVarArr = {dVar, dVar2, dVar3};
        o = dVarArr;
        r.e(dVarArr);
    }

    public static d valueOf(String str) {
        return (d) Enum.valueOf(d.class, str);
    }

    public static d[] values() {
        return (d[]) o.clone();
    }
}
