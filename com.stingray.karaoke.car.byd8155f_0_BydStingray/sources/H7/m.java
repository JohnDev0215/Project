package H7;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class m {
    public static final m l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final m f2852m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final m f2853n;
    public static final /* synthetic */ m[] o;

    static {
        m mVar = new m("INSTANCE", 0);
        l = mVar;
        m mVar2 = new m("EXTENSION_RECEIVER", 1);
        f2852m = mVar2;
        m mVar3 = new m("VALUE", 2);
        f2853n = mVar3;
        m[] mVarArr = {mVar, mVar2, mVar3};
        o = mVarArr;
        h6.r.e(mVarArr);
    }

    public static m valueOf(String str) {
        return (m) Enum.valueOf(m.class, str);
    }

    public static m[] values() {
        return (m[]) o.clone();
    }
}
