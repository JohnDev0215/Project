package K7;

/* JADX INFO: renamed from: K7.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0379c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final M.t f3777a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final M.t f3778b;
    public static final M.t c;
    public static final M.t d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final M.t f3779e;

    static {
        C0377b c0377b = C0377b.f3765q;
        int i7 = AbstractC0375a.f3761a;
        f3777a = new M.t(c0377b);
        f3778b = new M.t(C0377b.f3766r);
        c = new M.t(C0377b.f3763n);
        d = new M.t(C0377b.f3764p);
        f3779e = new M.t(C0377b.o);
    }

    public static final C0401z a(Class cls) {
        A7.m.f(cls, "jClass");
        Object objD = f3777a.D(cls);
        A7.m.d(objD, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KClassImpl<T of kotlin.reflect.jvm.internal.CachesKt.getOrCreateKotlinClass>");
        return (C0401z) objD;
    }
}
