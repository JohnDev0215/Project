package T0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class A {
    public static final A l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final A f8408m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final A f8409n;
    public static final A o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ A[] f8410p;

    static {
        A a10 = new A("StartInput", 0);
        l = a10;
        A a11 = new A("StopInput", 1);
        f8408m = a11;
        A a12 = new A("ShowKeyboard", 2);
        f8409n = a12;
        A a13 = new A("HideKeyboard", 3);
        o = a13;
        f8410p = new A[]{a10, a11, a12, a13};
    }

    public static A valueOf(String str) {
        return (A) Enum.valueOf(A.class, str);
    }

    public static A[] values() {
        return (A[]) f8410p.clone();
    }
}
