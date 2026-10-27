package e.a.n.a;

/* JADX INFO: compiled from: EmptyDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public enum c implements e.a.n.c.a<Object> {
    INSTANCE,
    NEVER;

    @Override // e.a.n.c.c
    public Object b() {
        return null;
    }

    @Override // e.a.n.c.c
    public void clear() {
    }

    @Override // e.a.k.b
    public void d() {
    }

    @Override // e.a.n.c.c
    public boolean e(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // e.a.n.c.c
    public boolean isEmpty() {
        return true;
    }

    @Override // e.a.n.c.b
    public int j(int i2) {
        return i2 & 2;
    }
}
