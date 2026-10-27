package h.z.a;

import h.t;

/* JADX INFO: compiled from: CallExecuteObservable.java */
/* JADX INFO: loaded from: classes.dex */
final class c<T> extends e.a.e<t<T>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final h.d<T> f5722a;

    /* JADX INFO: compiled from: CallExecuteObservable.java */
    private static final class a implements e.a.k.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final h.d<?> f5723b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private volatile boolean f5724c;

        a(h.d<?> dVar) {
            this.f5723b = dVar;
        }

        public boolean a() {
            return this.f5724c;
        }

        @Override // e.a.k.b
        public void d() {
            this.f5724c = true;
            this.f5723b.cancel();
        }
    }

    c(h.d<T> dVar) {
        this.f5722a = dVar;
    }

    @Override // e.a.e
    protected void h(e.a.g<? super t<T>> gVar) {
        boolean z;
        h.d<T> dVarClone = this.f5722a.clone();
        a aVar = new a(dVarClone);
        gVar.i(aVar);
        if (aVar.a()) {
            return;
        }
        try {
            t<T> tVarD = dVarClone.d();
            if (!aVar.a()) {
                gVar.h(tVarD);
            }
            if (aVar.a()) {
                return;
            }
            try {
                gVar.a();
            } catch (Throwable th) {
                th = th;
                z = true;
                e.a.l.b.b(th);
                if (z) {
                    e.a.p.a.o(th);
                    return;
                }
                if (aVar.a()) {
                    return;
                }
                try {
                    gVar.g(th);
                } catch (Throwable th2) {
                    e.a.l.b.b(th2);
                    e.a.p.a.o(new e.a.l.a(th, th2));
                }
            }
        } catch (Throwable th3) {
            th = th3;
            z = false;
        }
    }
}
