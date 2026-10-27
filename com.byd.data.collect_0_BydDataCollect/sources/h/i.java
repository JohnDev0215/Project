package h;

import f.e0;
import h.e;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: DefaultCallAdapterFactory.java */
/* JADX INFO: loaded from: classes.dex */
final class i extends e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    private final Executor f5561a;

    /* JADX INFO: compiled from: DefaultCallAdapterFactory.java */
    class a implements e<Object, d<?>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Type f5562a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Executor f5563b;

        a(Type type, Executor executor) {
            this.f5562a = type;
            this.f5563b = executor;
        }

        @Override // h.e
        public Type a() {
            return this.f5562a;
        }

        @Override // h.e
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public d<Object> b(d<Object> dVar) {
            Executor executor = this.f5563b;
            return executor == null ? dVar : new b(executor, dVar);
        }
    }

    /* JADX INFO: compiled from: DefaultCallAdapterFactory.java */
    static final class b<T> implements d<T> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Executor f5565b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final d<T> f5566c;

        /* JADX INFO: compiled from: DefaultCallAdapterFactory.java */
        class a implements f<T> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ f f5567b;

            a(f fVar) {
                this.f5567b = fVar;
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public /* synthetic */ void b(f fVar, Throwable th) {
                fVar.k(b.this, th);
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public /* synthetic */ void d(f fVar, t tVar) {
                if (b.this.f5566c.isCanceled()) {
                    fVar.k(b.this, new IOException("Canceled"));
                } else {
                    fVar.A(b.this, tVar);
                }
            }

            @Override // h.f
            public void A(d<T> dVar, final t<T> tVar) {
                Executor executor = b.this.f5565b;
                final f fVar = this.f5567b;
                executor.execute(new Runnable() { // from class: h.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f5540b.d(fVar, tVar);
                    }
                });
            }

            @Override // h.f
            public void k(d<T> dVar, final Throwable th) {
                Executor executor = b.this.f5565b;
                final f fVar = this.f5567b;
                executor.execute(new Runnable() { // from class: h.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f5537b.b(fVar, th);
                    }
                });
            }
        }

        b(Executor executor, d<T> dVar) {
            this.f5565b = executor;
            this.f5566c = dVar;
        }

        @Override // h.d
        public void C(f<T> fVar) {
            Objects.requireNonNull(fVar, "callback == null");
            this.f5566c.C(new a(fVar));
        }

        @Override // h.d
        public e0 a() {
            return this.f5566c.a();
        }

        @Override // h.d
        public void cancel() {
            this.f5566c.cancel();
        }

        @Override // h.d
        public t<T> d() {
            return this.f5566c.d();
        }

        @Override // h.d
        public boolean isCanceled() {
            return this.f5566c.isCanceled();
        }

        @Override // h.d
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public d<T> clone() {
            return new b(this.f5565b, this.f5566c.clone());
        }
    }

    i(@Nullable Executor executor) {
        this.f5561a = executor;
    }

    @Override // h.e.a
    @Nullable
    public e<?, ?> a(Type type, Annotation[] annotationArr, u uVar) {
        if (e.a.c(type) != d.class) {
            return null;
        }
        if (type instanceof ParameterizedType) {
            return new a(y.g(0, (ParameterizedType) type), y.l(annotationArr, w.class) ? null : this.f5561a);
        }
        throw new IllegalArgumentException("Call return type must be parameterized as Call<Foo> or Call<? extends Foo>");
    }
}
