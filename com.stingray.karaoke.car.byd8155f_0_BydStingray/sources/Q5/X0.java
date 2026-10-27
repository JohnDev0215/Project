package Q5;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class X0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final X0 f6641m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final X0 f6642n;
    public static final X0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final X0 f6643p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final X0 f6644q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final /* synthetic */ X0[] f6645r;
    public final boolean l;

    static {
        X0 x02 = new X0("Back", 0, false);
        f6641m = x02;
        X0 x03 = new X0("Clear", 1, true);
        f6642n = x03;
        X0 x04 = new X0("Minimize", 2, false);
        o = x04;
        X0 x05 = new X0("SongQueue", 3, false);
        f6643p = x05;
        X0 x06 = new X0("Close", 4, false);
        f6644q = x06;
        X0[] x0Arr = {x02, x03, x04, x05, x06};
        f6645r = x0Arr;
        h6.r.e(x0Arr);
    }

    public X0(String str, int i7, boolean z6) {
        this.l = z6;
    }

    public static X0 valueOf(String str) {
        return (X0) Enum.valueOf(X0.class, str);
    }

    public static X0[] values() {
        return (X0[]) f6645r.clone();
    }
}
