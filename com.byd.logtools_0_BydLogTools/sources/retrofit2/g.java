package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import retrofit2.e;

/* JADX INFO: compiled from: CompletableFutureCallAdapterFactory.java */
/* JADX INFO: loaded from: classes.dex */
@IgnoreJRERequirement
final class g extends e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final e.a f5201a = new g();

    /* JADX INFO: compiled from: CompletableFutureCallAdapterFactory.java */
    @IgnoreJRERequirement
    private static final class a<R> implements e<R, CompletableFuture<R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Type f5202a;

        /* JADX INFO: renamed from: retrofit2.g$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: CompletableFutureCallAdapterFactory.java */
        @IgnoreJRERequirement
        private class C0098a implements f<R> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final CompletableFuture<R> f5203a;

            public C0098a(CompletableFuture<R> completableFuture) {
                this.f5203a = completableFuture;
            }

            @Override // retrofit2.f
            public void a(d<R> dVar, Throwable th) {
                this.f5203a.completeExceptionally(th);
            }

            @Override // retrofit2.f
            public void b(d<R> dVar, r<R> rVar) {
                if (rVar.d()) {
                    this.f5203a.complete(rVar.a());
                } else {
                    this.f5203a.completeExceptionally(new HttpException(rVar));
                }
            }
        }

        a(Type type) {
            this.f5202a = type;
        }

        @Override // retrofit2.e
        public Type a() {
            return this.f5202a;
        }

        @Override // retrofit2.e
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public CompletableFuture<R> b(d<R> dVar) {
            b bVar = new b(dVar);
            dVar.e(new C0098a(bVar));
            return bVar;
        }
    }

    /* JADX INFO: compiled from: CompletableFutureCallAdapterFactory.java */
    @IgnoreJRERequirement
    private static final class b<T> extends CompletableFuture<T> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final d<?> f5205b;

        b(d<?> dVar) {
            this.f5205b = dVar;
        }

        @Override // java.util.concurrent.CompletableFuture, java.util.concurrent.Future
        public boolean cancel(boolean z) {
            if (z) {
                this.f5205b.cancel();
            }
            return super.cancel(z);
        }
    }

    /* JADX INFO: compiled from: CompletableFutureCallAdapterFactory.java */
    @IgnoreJRERequirement
    private static final class c<R> implements e<R, CompletableFuture<r<R>>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Type f5206a;

        /* JADX INFO: compiled from: CompletableFutureCallAdapterFactory.java */
        @IgnoreJRERequirement
        private class a implements f<R> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final CompletableFuture<r<R>> f5207a;

            public a(CompletableFuture<r<R>> completableFuture) {
                this.f5207a = completableFuture;
            }

            @Override // retrofit2.f
            public void a(d<R> dVar, Throwable th) {
                this.f5207a.completeExceptionally(th);
            }

            @Override // retrofit2.f
            public void b(d<R> dVar, r<R> rVar) {
                this.f5207a.complete(rVar);
            }
        }

        c(Type type) {
            this.f5206a = type;
        }

        @Override // retrofit2.e
        public Type a() {
            return this.f5206a;
        }

        @Override // retrofit2.e
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public CompletableFuture<r<R>> b(d<R> dVar) {
            b bVar = new b(dVar);
            dVar.e(new a(bVar));
            return bVar;
        }
    }

    g() {
    }

    @Override // retrofit2.e.a
    @Nullable
    public e<?, ?> a(Type type, Annotation[] annotationArr, s sVar) {
        if (e.a.c(type) != CompletableFuture.class) {
            return null;
        }
        if (!(type instanceof ParameterizedType)) {
            throw new IllegalStateException("CompletableFuture return type must be parameterized as CompletableFuture<Foo> or CompletableFuture<? extends Foo>");
        }
        Type typeB = e.a.b(0, (ParameterizedType) type);
        if (e.a.c(typeB) != r.class) {
            return new a(typeB);
        }
        if (typeB instanceof ParameterizedType) {
            return new c(e.a.b(0, (ParameterizedType) typeB));
        }
        throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
    }
}
