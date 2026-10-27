package K3;

import h6.r;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final b f3510n;
    public static final b o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ b[] f3511p;
    public final boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f3512m;

    static {
        b bVar = new b("ENABLED", 0, true, true);
        f3510n = bVar;
        b bVar2 = new b("READ_ONLY", 1, true, false);
        b bVar3 = new b("WRITE_ONLY", 2, false, true);
        b bVar4 = new b("DISABLED", 3, false, false);
        o = bVar4;
        b[] bVarArr = {bVar, bVar2, bVar3, bVar4};
        f3511p = bVarArr;
        r.e(bVarArr);
    }

    public b(String str, int i7, boolean z6, boolean z10) {
        this.l = z6;
        this.f3512m = z10;
    }

    public static b valueOf(String str) {
        return (b) Enum.valueOf(b.class, str);
    }

    public static b[] values() {
        return (b[]) f3511p.clone();
    }
}
