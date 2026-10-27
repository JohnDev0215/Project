package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import okhttp3.Request;
import retrofit2.e;

/* JADX INFO: compiled from: DefaultCallAdapterFactory.java */
/* JADX INFO: loaded from: classes.dex */
final class i extends e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    private final Executor f5209a;

    /* JADX INFO: compiled from: DefaultCallAdapterFactory.java */
    class a implements e<Object, d<?>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Type f5210a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Executor f5211b;

        a(Type type, Executor executor) {
            this.f5210a = type;
            this.f5211b = executor;
        }

        @Override // retrofit2.e
        public Type a() {
            return this.f5210a;
        }

        @Override // retrofit2.e
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public d<Object> b(d<Object> dVar) {
            Executor executor = this.f5211b;
            return executor == null ? dVar : new b(executor, dVar);
        }
    }

    /* JADX INFO: compiled from: DefaultCallAdapterFactory.java */
    static final class b<T> implements d<T> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Executor f5213b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final d<T> f5214c;

        /* JADX INFO: compiled from: DefaultCallAdapterFactory.java */
        class a implements f<T> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ f f5215a;

            a(f fVar) {
                this.f5215a = fVar;
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public /* synthetic */ void d(f fVar, Throwable th) {
                fVar.a(b.this, th);
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
            public /* synthetic */ void f(f fVar, r rVar) {
                if (b.this.f5214c.isCanceled()) {
                    fVar.a(b.this, new IOException("Canceled"));
                } else {
                    fVar.b(b.this, rVar);
                }
            }

            @Override // retrofit2.f
            public void a(d<T> dVar, final Throwable th) {
                Executor executor = b.this.f5213b;
                final f fVar = this.f5215a;
                executor.execute(new Runnable() { // from class: retrofit2.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f5188b.d(fVar, th);
                    }
                });
            }

            @Override // retrofit2.f
            public void b(d<T> dVar, final r<T> rVar) {
                Executor executor = b.this.f5213b;
                final f fVar = this.f5215a;
                executor.execute(new Runnable() { // from class: retrofit2.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f5191b.f(fVar, rVar);
                    }
                });
            }
        }

        b(Executor executor, d<T> dVar) {
            this.f5213b = executor;
            this.f5214c = dVar;
        }

        @Override // retrofit2.d
        public void cancel() {
            this.f5214c.cancel();
        }

        @Override // retrofit2.d
        public void e(f<T> fVar) {
            Objects.requireNonNull(fVar, "callback == null");
            this.f5214c.e(new a(fVar));
        }

        @Override // retrofit2.d
        public boolean isCanceled() {
            return this.f5214c.isCanceled();
        }

        @Override // retrofit2.d
        public Request request() {
            return this.f5214c.request();
        }

        @Override // retrofit2.d
        public d<T> clone() {
            return new b(this.f5213b, this.f5214c.clone());
        }
    }

    i(@Nullable Executor executor) {
        this.f5209a = executor;
    }

    @Override // retrofit2.e.a
    @Nullable
    public e<?, ?> a(Type type, Annotation[] annotationArr, s sVar) {
        if (e.a.c(type) != d.class) {
            return null;
        }
        if (type instanceof ParameterizedType) {
            return new a(w.g(0, (ParameterizedType) type), w.l(annotationArr, u.class) ? null : this.f5209a);
        }
        throw new IllegalArgumentException("Call return type must be parameterized as Call<Foo> or Call<? extends Foo>");
    }
}
