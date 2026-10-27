package R;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class A {
    public static final A l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final A f7227m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final A f7228n;
    public static final /* synthetic */ A[] o;

    static {
        A a10 = new A("Focused", 0);
        l = a10;
        A a11 = new A("UnfocusedEmpty", 1);
        f7227m = a11;
        A a12 = new A("UnfocusedNotEmpty", 2);
        f7228n = a12;
        o = new A[]{a10, a11, a12};
    }

    public static A valueOf(String str) {
        return (A) Enum.valueOf(A.class, str);
    }

    public static A[] values() {
        return (A[]) o.clone();
    }
}
