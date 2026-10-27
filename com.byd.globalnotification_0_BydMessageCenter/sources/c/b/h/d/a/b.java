package c.b.h.d.a;

import c.b.c;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class b<T> extends c.b.h.d.a.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f2240b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final TimeUnit f2241c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c f2242d;

    public static final class a<T> extends AtomicReference<c.b.e.b> implements c.b.b<T>, c.b.e.b, Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c.b.b<? super T> f2243a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final long f2244b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final TimeUnit f2245c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final c.b f2246d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public c.b.e.b f2247e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public volatile boolean f2248f;
        public boolean g;

        public a(c.b.b<? super T> bVar, long j, TimeUnit timeUnit, c.b bVar2) {
            this.f2243a = bVar;
            this.f2244b = j;
            this.f2245c = timeUnit;
            this.f2246d = bVar2;
        }

        @Override // c.b.b
        public void a() {
            if (this.g) {
                return;
            }
            this.g = true;
            this.f2243a.a();
            this.f2246d.d();
        }

        @Override // c.b.b
        public void b(Throwable th) {
            if (this.g) {
                c.b.j.a.c(th);
                return;
            }
            this.g = true;
            this.f2243a.b(th);
            this.f2246d.d();
        }

        @Override // c.b.b
        public void c(T t) {
            c.b.e.b bVar;
            if (this.f2248f || this.g) {
                return;
            }
            this.f2248f = true;
            this.f2243a.c(t);
            c.b.e.b bVar2 = get();
            if (bVar2 != null) {
                bVar2.d();
            }
            c.b.e.b bVarB = this.f2246d.b(this, this.f2244b, this.f2245c);
            do {
                bVar = get();
                if (bVar == c.b.h.a.b.DISPOSED) {
                    if (bVarB != null) {
                        bVarB.d();
                        return;
                    }
                    return;
                }
            } while (!compareAndSet(bVar, bVarB));
        }

        @Override // c.b.e.b
        public void d() {
            this.f2247e.d();
            this.f2246d.d();
        }

        @Override // c.b.b
        public void e(c.b.e.b bVar) {
            if (c.b.h.a.b.a(this.f2247e, bVar)) {
                this.f2247e = bVar;
                this.f2243a.e(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f2248f = false;
        }
    }

    public b(c.b.a<T> aVar, long j, TimeUnit timeUnit, c cVar) {
        super(aVar);
        this.f2240b = j;
        this.f2241c = timeUnit;
        this.f2242d = cVar;
    }

    @Override // c.b.a
    public void c(c.b.b<? super T> bVar) {
        this.f2239a.b(new a(new c.b.i.a(bVar), this.f2240b, this.f2241c, this.f2242d.a()));
    }
}
