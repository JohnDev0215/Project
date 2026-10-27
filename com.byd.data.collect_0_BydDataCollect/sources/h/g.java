package h;

import h.e;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

/* JADX INFO: compiled from: CompletableFutureCallAdapterFactory.java */
/* JADX INFO: loaded from: classes.dex */
@IgnoreJRERequirement
final class g extends e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final e.a f5550a = new g();

    /* JADX INFO: compiled from: CompletableFutureCallAdapterFactory.java */
    @IgnoreJRERequirement
    private static final class a<R> implements e<R, CompletableFuture<R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Type f5551a;

        /* JADX INFO: renamed from: h.g$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: CompletableFutureCallAdapterFactory.java */
        class C0105a extends CompletableFuture<R> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ d f5552b;

            C0105a(d dVar) {
                this.f5552b = dVar;
            }

            @Override // java.util.concurrent.CompletableFuture, java.util.concurrent.Future
            public boolean cancel(boolean z) {
                if (z) {
                    this.f5552b.cancel();
                }
                return super.cancel(z);
            }
        }

        /* JADX INFO: compiled from: CompletableFutureCallAdapterFactory.java */
        class b implements f<R> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ CompletableFuture f5554b;

            b(CompletableFuture completableFuture) {
                this.f5554b = completableFuture;
            }

            @Override // h.f
            public void A(d<R> dVar, t<R> tVar) {
                if (tVar.d()) {
                    this.f5554b.complete(tVar.a());
                } else {
                    this.f5554b.completeExceptionally(new j(tVar));
                }
            }

            @Override // h.f
            public void k(d<R> dVar, Throwable th) {
                this.f5554b.completeExceptionally(th);
            }
        }

        a(Type type) {
            this.f5551a = type;
        }

        @Override // h.e
        public Type a() {
            return this.f5551a;
        }

        @Override // h.e
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public CompletableFuture<R> b(d<R> dVar) {
            C0105a c0105a = new C0105a(dVar);
            dVar.C(new b(c0105a));
            return c0105a;
        }
    }

    /* JADX INFO: compiled from: CompletableFutureCallAdapterFactory.java */
    @IgnoreJRERequirement
    private static final class b<R> implements e<R, CompletableFuture<t<R>>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Type f5556a;

        /* JADX INFO: compiled from: CompletableFutureCallAdapterFactory.java */
        class a extends CompletableFuture<t<R>> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ d f5557b;

            a(d dVar) {
                this.f5557b = dVar;
            }

            @Override // java.util.concurrent.CompletableFuture, java.util.concurrent.Future
            public boolean cancel(boolean z) {
                if (z) {
                    this.f5557b.cancel();
                }
                return super.cancel(z);
            }
        }

        /* JADX INFO: renamed from: h.g$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: CompletableFutureCallAdapterFactory.java */
        class C0106b implements f<R> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ CompletableFuture f5559b;

            C0106b(CompletableFuture completableFuture) {
                this.f5559b = completableFuture;
            }

            @Override // h.f
            public void A(d<R> dVar, t<R> tVar) {
                this.f5559b.complete(tVar);
            }

            @Override // h.f
            public void k(d<R> dVar, Throwable th) {
                this.f5559b.completeExceptionally(th);
            }
        }

        b(Type type) {
            this.f5556a = type;
        }

        @Override // h.e
        public Type a() {
            return this.f5556a;
        }

        @Override // h.e
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public CompletableFuture<t<R>> b(d<R> dVar) {
            a aVar = new a(dVar);
            dVar.C(new C0106b(aVar));
            return aVar;
        }
    }

    g() {
    }

    @Override // h.e.a
    @Nullable
    public e<?, ?> a(Type type, Annotation[] annotationArr, u uVar) {
        if (e.a.c(type) != CompletableFuture.class) {
            return null;
        }
        if (!(type instanceof ParameterizedType)) {
            throw new IllegalStateException("CompletableFuture return type must be parameterized as CompletableFuture<Foo> or CompletableFuture<? extends Foo>");
        }
        Type typeB = e.a.b(0, (ParameterizedType) type);
        if (e.a.c(typeB) != t.class) {
            return new a(typeB);
        }
        if (typeB instanceof ParameterizedType) {
            return new b(e.a.b(0, (ParameterizedType) typeB));
        }
        throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
    }
}
