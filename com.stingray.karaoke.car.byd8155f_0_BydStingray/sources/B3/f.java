package B3;

import h6.r;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class f {
    public static final f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final f f624m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final f f625n;
    public static final f o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ f[] f626p;

    static {
        f fVar = new f("MEMORY_CACHE", 0);
        l = fVar;
        f fVar2 = new f("MEMORY", 1);
        f624m = fVar2;
        f fVar3 = new f("DISK", 2);
        f625n = fVar3;
        f fVar4 = new f("NETWORK", 3);
        o = fVar4;
        f[] fVarArr = {fVar, fVar2, fVar3, fVar4};
        f626p = fVarArr;
        r.e(fVarArr);
    }

    public static f valueOf(String str) {
        return (f) Enum.valueOf(f.class, str);
    }

    public static f[] values() {
        return (f[]) f626p.clone();
    }
}
