package c.a.b;

/* JADX INFO: compiled from: CryptoException.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends RuntimeException {
    private static final long serialVersionUID = 8068509879445395353L;

    public b(Throwable th) {
        super(c.a.a.c.a.a(th), th);
    }

    public b(String str, Object... objArr) {
        super(c.a.a.f.b.f(str, objArr));
    }
}
