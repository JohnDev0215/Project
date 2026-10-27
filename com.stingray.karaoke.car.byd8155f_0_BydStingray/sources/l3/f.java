package L3;

import h6.r;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class f {
    public static final f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final f f4003m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ f[] f4004n;

    static {
        f fVar = new f("FILL", 0);
        l = fVar;
        f fVar2 = new f("FIT", 1);
        f4003m = fVar2;
        f[] fVarArr = {fVar, fVar2};
        f4004n = fVarArr;
        r.e(fVarArr);
    }

    public static f valueOf(String str) {
        return (f) Enum.valueOf(f.class, str);
    }

    public static f[] values() {
        return (f[]) f4004n.clone();
    }
}
