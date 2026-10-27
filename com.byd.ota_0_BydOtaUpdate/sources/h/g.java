package h;

import h.c;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: ExecutorCallAdapterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class g extends c.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Executor f5755a;

    /* JADX INFO: compiled from: ExecutorCallAdapterFactory.java */
    public class a implements c<Object, h.b<?>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Type f5756a;

        public a(Type type) {
            this.f5756a = type;
        }

        @Override // h.c
        public Type a() {
            return this.f5756a;
        }

        @Override // h.c
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public h.b<Object> b(h.b<Object> bVar) {
            return new b(g.this.f5755a, bVar);
        }
    }

    /* JADX INFO: compiled from: ExecutorCallAdapterFactory.java */
    public static final class b<T> implements h.b<T> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Executor f5758c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final h.b<T> f5759d;

        /* JADX INFO: compiled from: ExecutorCallAdapterFactory.java */
        public class a implements d<T> {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ d f5760c;

            /* JADX INFO: renamed from: h.g$b$a$a, reason: collision with other inner class name */
            /* JADX INFO: compiled from: ExecutorCallAdapterFactory.java */
            public class RunnableC0099a implements Runnable {

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                public final /* synthetic */ l f5762c;

                public RunnableC0099a(l lVar) {
                    this.f5762c = lVar;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (b.this.f5759d.d()) {
                        a aVar = a.this;
                        aVar.f5760c.a(b.this, new IOException("Canceled"));
                    } else {
                        a aVar2 = a.this;
                        aVar2.f5760c.b(b.this, this.f5762c);
                    }
                }
            }

            /* JADX INFO: renamed from: h.g$b$a$b, reason: collision with other inner class name */
            /* JADX INFO: compiled from: ExecutorCallAdapterFactory.java */
            public class RunnableC0100b implements Runnable {

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                public final /* synthetic */ Throwable f5764c;

                public RunnableC0100b(Throwable th) {
                    this.f5764c = th;
                }

                @Override // java.lang.Runnable
                public void run() {
                    a aVar = a.this;
                    aVar.f5760c.a(b.this, this.f5764c);
                }
            }

            public a(d dVar) {
                this.f5760c = dVar;
            }

            @Override // h.d
            public void a(h.b<T> bVar, Throwable th) {
                b.this.f5758c.execute(new RunnableC0100b(th));
            }

            @Override // h.d
            public void b(h.b<T> bVar, l<T> lVar) {
                b.this.f5758c.execute(new RunnableC0099a(lVar));
            }
        }

        public b(Executor executor, h.b<T> bVar) {
            this.f5758c = executor;
            this.f5759d = bVar;
        }

        @Override // h.b
        public l<T> c() throws IOException {
            return this.f5759d.c();
        }

        @Override // h.b
        public void cancel() {
            this.f5759d.cancel();
        }

        @Override // h.b
        public boolean d() {
            return this.f5759d.d();
        }

        @Override // h.b
        public void v(d<T> dVar) {
            o.b(dVar, "callback == null");
            this.f5759d.v(new a(dVar));
        }

        @Override // h.b
        /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
        public h.b<T> m0clone() {
            return new b(this.f5758c, this.f5759d.m0clone());
        }
    }

    public g(Executor executor) {
        this.f5755a = executor;
    }

    @Override // h.c.a
    public c<?, ?> a(Type type, Annotation[] annotationArr, m mVar) {
        if (c.a.c(type) != h.b.class) {
            return null;
        }
        return new a(o.g(type));
    }
}
