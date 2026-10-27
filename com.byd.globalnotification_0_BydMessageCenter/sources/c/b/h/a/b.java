package c.b.h.a;

/* JADX INFO: loaded from: classes.dex */
public enum b implements c.b.e.b {
    DISPOSED;

    public static boolean a(c.b.e.b bVar, c.b.e.b bVar2) {
        if (bVar2 == null) {
            c.b.j.a.c(new NullPointerException("next is null"));
            return false;
        }
        if (bVar == null) {
            return true;
        }
        bVar2.d();
        c.b.j.a.c(new c.b.f.c("Disposable already set!"));
        return false;
    }

    @Override // c.b.e.b
    public void d() {
    }
}
