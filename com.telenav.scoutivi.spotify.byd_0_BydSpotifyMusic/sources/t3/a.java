package t3;

import h3.i;
import q4.e;
import q4.g;

/* JADX INFO: compiled from: RxExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final u3.b f9554a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final s3.a f9555b = new s3.a();

    /* JADX INFO: compiled from: RxExecutor.java */
    public interface b {
        void d();
    }

    /* JADX INFO: compiled from: RxExecutor.java */
    private static class c<T> extends c5.a<T> implements b {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private g<T> f9556g;

        @Override // q4.g
        public void a(Throwable th) {
            g<T> gVar = this.f9556g;
            if (gVar != null) {
                gVar.a(th);
            } else {
                i.b("RxExecutor").b("DisposableSingleObserverWrapper  onError singleObserver is null", new Object[0]);
            }
        }

        @Override // q4.g
        public void b(T t6) {
            g<T> gVar = this.f9556g;
            if (gVar != null) {
                gVar.b(t6);
            } else {
                i.b("RxExecutor").b("DisposableSingleObserverWrapper  onSuccess singleObserver is null", new Object[0]);
            }
        }

        @Override // t3.a.b
        public void d() {
            this.f9556g = null;
            i.b("RxExecutor").b("DisposableSingleObserverWrapper  destroy", new Object[0]);
        }

        @Override // c5.a
        protected void e() {
            g<T> gVar = this.f9556g;
            if (gVar != null) {
                gVar.c(this);
            } else {
                i.b("RxExecutor").b("DisposableSingleObserverWrapper  onStart singleObserver is null", new Object[0]);
            }
        }

        private c(g<T> gVar) {
            this.f9556g = gVar;
        }
    }

    public a(u3.b bVar) {
        this.f9554a = bVar;
    }

    private void a(t4.b bVar) {
        if (bVar.isDisposed()) {
            return;
        }
        this.f9555b.a(bVar);
        i.b("RxExecutor").i("addDisposable subscribes size : %s", Integer.valueOf(d()));
    }

    public void b() {
        this.f9555b.dispose();
    }

    public <T> void c(e<T> eVar, g<T> gVar) {
        a((c) eVar.h(this.f9554a.a()).f(this.f9554a.b()).i(new c(gVar)));
    }

    public int d() {
        if (this.f9555b.f() == null) {
            return 0;
        }
        return this.f9555b.f().size();
    }
}
