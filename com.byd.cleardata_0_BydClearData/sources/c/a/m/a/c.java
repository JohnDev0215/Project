package c.a.m.a;

import c.a.g;

/* JADX INFO: loaded from: classes.dex */
public enum c implements c.a.m.c.a<Object> {
    INSTANCE,
    NEVER;

    public static void a(Throwable th, g<?> gVar) {
        gVar.h(INSTANCE);
        gVar.b(th);
    }

    @Override // c.a.m.c.c
    public void clear() {
    }

    @Override // c.a.m.c.c
    public Object d() {
        return null;
    }

    @Override // c.a.j.b
    public void e() {
    }

    @Override // c.a.m.c.c
    public boolean f(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // c.a.j.b
    public boolean g() {
        return this == INSTANCE;
    }

    @Override // c.a.m.c.b
    public int i(int i) {
        return i & 2;
    }

    @Override // c.a.m.c.c
    public boolean isEmpty() {
        return true;
    }
}
