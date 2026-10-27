package R7;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class m {
    public static final m l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final m f7541m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final m f7542n;
    public static final /* synthetic */ m[] o;

    static {
        m mVar = new m("RUNTIME", 0);
        l = mVar;
        m mVar2 = new m("BINARY", 1);
        f7541m = mVar2;
        m mVar3 = new m("SOURCE", 2);
        f7542n = mVar3;
        o = new m[]{mVar, mVar2, mVar3};
    }

    public static m valueOf(String str) {
        return (m) Enum.valueOf(m.class, str);
    }

    public static m[] values() {
        return (m[]) o.clone();
    }
}
