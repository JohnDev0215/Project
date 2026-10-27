package Z7;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public class E {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final E f10909m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final E f10910n;
    public static final E o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final D f10911p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final /* synthetic */ E[] f10912q;
    public final Object l;

    static {
        E e10 = new E(null, "NULL", 0);
        f10909m = e10;
        E e11 = new E(-1, "INDEX", 1);
        f10910n = e11;
        E e12 = new E(Boolean.FALSE, "FALSE", 2);
        o = e12;
        D d = new D(null, "MAP_GET_OR_DEFAULT", 3);
        f10911p = d;
        f10912q = new E[]{e10, e11, e12, d};
    }

    public E(Object obj, String str, int i7) {
        this.l = obj;
    }

    public static E valueOf(String str) {
        return (E) Enum.valueOf(E.class, str);
    }

    public static E[] values() {
        return (E[]) f10912q.clone();
    }
}
