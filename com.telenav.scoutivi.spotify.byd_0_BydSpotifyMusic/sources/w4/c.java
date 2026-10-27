package w4;

import q4.g;

/* JADX INFO: compiled from: EmptyDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public enum c implements t4.b {
    INSTANCE,
    NEVER;

    public static void complete(q4.c<?> cVar) {
        cVar.c(INSTANCE);
        cVar.b();
    }

    public static void error(Throwable th, q4.c<?> cVar) {
        cVar.c(INSTANCE);
        cVar.a(th);
    }

    public void clear() {
    }

    @Override // t4.b
    public void dispose() {
    }

    @Override // t4.b
    public boolean isDisposed() {
        return this == INSTANCE;
    }

    public boolean isEmpty() {
        return true;
    }

    public boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public Object poll() {
        return null;
    }

    public int requestFusion(int i7) {
        return i7 & 2;
    }

    public boolean offer(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public static void complete(q4.b<?> bVar) {
        bVar.c(INSTANCE);
        bVar.b();
    }

    public static void error(Throwable th, q4.a aVar) {
        aVar.c(INSTANCE);
        aVar.a(th);
    }

    public static void complete(q4.a aVar) {
        aVar.c(INSTANCE);
        aVar.b();
    }

    public static void error(Throwable th, g<?> gVar) {
        gVar.c(INSTANCE);
        gVar.a(th);
    }

    public static void error(Throwable th, q4.b<?> bVar) {
        bVar.c(INSTANCE);
        bVar.a(th);
    }
}
