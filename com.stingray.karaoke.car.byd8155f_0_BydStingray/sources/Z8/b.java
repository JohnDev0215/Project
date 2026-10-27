package Z8;

import h6.r;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static final b l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final b f10997m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final b f10998n;
    public static final b o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final b f10999p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final /* synthetic */ b[] f11000q;

    static {
        b bVar = new b("CPU_ACQUIRED", 0);
        l = bVar;
        b bVar2 = new b("BLOCKING", 1);
        f10997m = bVar2;
        b bVar3 = new b("PARKING", 2);
        f10998n = bVar3;
        b bVar4 = new b("DORMANT", 3);
        o = bVar4;
        b bVar5 = new b("TERMINATED", 4);
        f10999p = bVar5;
        b[] bVarArr = {bVar, bVar2, bVar3, bVar4, bVar5};
        f11000q = bVarArr;
        r.e(bVarArr);
    }

    public static b valueOf(String str) {
        return (b) Enum.valueOf(b.class, str);
    }

    public static b[] values() {
        return (b[]) f11000q.clone();
    }
}
