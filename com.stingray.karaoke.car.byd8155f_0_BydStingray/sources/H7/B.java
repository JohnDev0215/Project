package H7;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class B {
    public static final B l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final B f2841m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final B f2842n;
    public static final /* synthetic */ B[] o;

    static {
        B b10 = new B("INVARIANT", 0);
        l = b10;
        B b11 = new B("IN", 1);
        f2841m = b11;
        B b12 = new B("OUT", 2);
        f2842n = b12;
        B[] bArr = {b10, b11, b12};
        o = bArr;
        h6.r.e(bArr);
    }

    public static B valueOf(String str) {
        return (B) Enum.valueOf(B.class, str);
    }

    public static B[] values() {
        return (B[]) o.clone();
    }
}
