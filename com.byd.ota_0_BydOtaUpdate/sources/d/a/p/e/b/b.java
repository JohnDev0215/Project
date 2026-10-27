package d.a.p.e.b;

import d.a.j;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: ObservableDelay.java */
/* JADX INFO: loaded from: classes.dex */
public final class b<T> extends d.a.p.e.b.a<T, T> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f3191d;
    public final TimeUnit s;
    public final d.a.j x;
    public final boolean y;

    /* JADX INFO: compiled from: ObservableDelay.java */
    public static final class a<T> implements d.a.i<T>, d.a.m.b {
        public d.a.m.b E3;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final d.a.i<? super T> f3192c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final long f3193d;
        public final TimeUnit s;
        public final j.b x;
        public final boolean y;

        /* JADX INFO: renamed from: d.a.p.e.b.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableDelay.java */
        public class RunnableC0073a implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ Object f3194c;

            public RunnableC0073a(Object obj) {
                this.f3194c = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.f3192c.onNext((Object) this.f3194c);
            }
        }

        /* JADX INFO: renamed from: d.a.p.e.b.b$a$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableDelay.java */
        public class RunnableC0074b implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ Throwable f3196c;

            public RunnableC0074b(Throwable th) {
                this.f3196c = th;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    a.this.f3192c.onError(this.f3196c);
                } finally {
                    a.this.x.dispose();
                }
            }
        }

        /* JADX INFO: compiled from: ObservableDelay.java */
        public class c implements Runnable {
            public c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    a.this.f3192c.onComplete();
                } finally {
                    a.this.x.dispose();
                }
            }
        }

        public a(d.a.i<? super T> iVar, long j, TimeUnit timeUnit, j.b bVar, boolean z) {
            this.f3192c = iVar;
            this.f3193d = j;
            this.s = timeUnit;
            this.x = bVar;
            this.y = z;
        }

        @Override // d.a.m.b
        public void dispose() {
            this.x.dispose();
            this.E3.dispose();
        }

        @Override // d.a.m.b
        public boolean isDisposed() {
            return this.x.isDisposed();
        }

        @Override // d.a.i
        public void onComplete() {
            this.x.c(new c(), this.f3193d, this.s);
        }

        @Override // d.a.i
        public void onError(Throwable th) {
            this.x.c(new RunnableC0074b(th), this.y ? this.f3193d : 0L, this.s);
        }

        @Override // d.a.i
        public void onNext(T t) {
            this.x.c(new RunnableC0073a(t), this.f3193d, this.s);
        }

        @Override // d.a.i
        public void onSubscribe(d.a.m.b bVar) {
            if (DisposableHelper.validate(this.E3, bVar)) {
                this.E3 = bVar;
                this.f3192c.onSubscribe(this);
            }
        }
    }

    public b(d.a.h<T> hVar, long j, TimeUnit timeUnit, d.a.j jVar, boolean z) {
        super(hVar);
        this.f3191d = j;
        this.s = timeUnit;
        this.x = jVar;
        this.y = z;
    }

    @Override // d.a.g
    public void t(d.a.i<? super T> iVar) {
        this.f3190c.a(new a(this.y ? iVar : new d.a.q.a(iVar), this.f3191d, this.s, this.x.a(), this.y));
    }
}
