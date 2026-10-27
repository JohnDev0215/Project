package u1;

import o1.d;
import u1.n;

/* JADX INFO: compiled from: UnitModelLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class v<Model> implements n<Model, Model> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final v<?> f9646a = new v<>();

    /* JADX INFO: compiled from: UnitModelLoader.java */
    public static class a<Model> implements o<Model, Model> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final a<?> f9647a = new a<>();

        @Deprecated
        public a() {
        }

        public static <T> a<T> a() {
            return (a<T>) f9647a;
        }

        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<Model, Model> c(r rVar) {
            return v.c();
        }
    }

    /* JADX INFO: compiled from: UnitModelLoader.java */
    private static class b<Model> implements o1.d<Model> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Model f9648e;

        b(Model model) {
            this.f9648e = model;
        }

        @Override // o1.d
        public Class<Model> a() {
            return (Class<Model>) this.f9648e.getClass();
        }

        @Override // o1.d
        public void b() {
        }

        @Override // o1.d
        public void c(com.bumptech.glide.g gVar, d.a<? super Model> aVar) {
            aVar.f(this.f9648e);
        }

        @Override // o1.d
        public void cancel() {
        }

        @Override // o1.d
        public n1.a e() {
            return n1.a.LOCAL;
        }
    }

    @Deprecated
    public v() {
    }

    public static <T> v<T> c() {
        return (v<T>) f9646a;
    }

    @Override // u1.n
    public boolean a(Model model) {
        return true;
    }

    @Override // u1.n
    public n.a<Model> b(Model model, int i7, int i8, n1.i iVar) {
        return new n.a<>(new j2.d(model), new b(model));
    }
}
