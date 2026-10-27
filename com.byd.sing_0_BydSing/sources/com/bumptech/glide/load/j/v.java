package com.bumptech.glide.load.j;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.i.d;
import com.bumptech.glide.load.j.n;

/* JADX INFO: compiled from: UnitModelLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class v<Model> implements n<Model, Model> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final v<?> f3023a = new v<>();

    /* JADX INFO: compiled from: UnitModelLoader.java */
    public static class a<Model> implements o<Model, Model> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final a<?> f3024a = new a<>();

        @Deprecated
        public a() {
        }

        public static <T> a<T> a() {
            return (a<T>) f3024a;
        }

        @Override // com.bumptech.glide.load.j.o
        public n<Model, Model> b(r rVar) {
            return v.c();
        }
    }

    /* JADX INFO: compiled from: UnitModelLoader.java */
    private static class b<Model> implements com.bumptech.glide.load.i.d<Model> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Model f3025b;

        b(Model model) {
            this.f3025b = model;
        }

        @Override // com.bumptech.glide.load.i.d
        public Class<Model> a() {
            return (Class<Model>) this.f3025b.getClass();
        }

        @Override // com.bumptech.glide.load.i.d
        public void b() {
        }

        @Override // com.bumptech.glide.load.i.d
        public void cancel() {
        }

        @Override // com.bumptech.glide.load.i.d
        public DataSource e() {
            return DataSource.LOCAL;
        }

        @Override // com.bumptech.glide.load.i.d
        public void f(Priority priority, d.a<? super Model> aVar) {
            aVar.d(this.f3025b);
        }
    }

    @Deprecated
    public v() {
    }

    public static <T> v<T> c() {
        return (v<T>) f3023a;
    }

    @Override // com.bumptech.glide.load.j.n
    public n.a<Model> a(Model model, int i, int i2, com.bumptech.glide.load.e eVar) {
        return new n.a<>(new com.bumptech.glide.l.b(model), new b(model));
    }

    @Override // com.bumptech.glide.load.j.n
    public boolean b(Model model) {
        return true;
    }
}
