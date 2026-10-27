package com.bumptech.glide.load.i;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.d;
import com.bumptech.glide.load.i.n;

/* JADX INFO: compiled from: UnitModelLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class v<Model> implements n<Model, Model> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final v<?> f3250a = new v<>();

    /* JADX INFO: compiled from: UnitModelLoader.java */
    public static class a<Model> implements o<Model, Model> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final a<?> f3251a = new a<>();

        @Deprecated
        public a() {
        }

        public static <T> a<T> a() {
            return (a<T>) f3251a;
        }

        @Override // com.bumptech.glide.load.i.o
        public n<Model, Model> b(r rVar) {
            return v.c();
        }
    }

    /* JADX INFO: compiled from: UnitModelLoader.java */
    private static class b<Model> implements com.bumptech.glide.load.data.d<Model> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Model f3252b;

        b(Model model) {
            this.f3252b = model;
        }

        @Override // com.bumptech.glide.load.data.d
        public Class<Model> a() {
            return (Class<Model>) this.f3252b.getClass();
        }

        @Override // com.bumptech.glide.load.data.d
        public void b() {
        }

        @Override // com.bumptech.glide.load.data.d
        public DataSource c() {
            return DataSource.LOCAL;
        }

        @Override // com.bumptech.glide.load.data.d
        public void cancel() {
        }

        @Override // com.bumptech.glide.load.data.d
        public void e(Priority priority, d.a<? super Model> aVar) {
            aVar.f(this.f3252b);
        }
    }

    @Deprecated
    public v() {
    }

    public static <T> v<T> c() {
        return (v<T>) f3250a;
    }

    @Override // com.bumptech.glide.load.i.n
    public n.a<Model> a(Model model, int i, int i2, com.bumptech.glide.load.e eVar) {
        return new n.a<>(new com.bumptech.glide.n.b(model), new b(model));
    }

    @Override // com.bumptech.glide.load.i.n
    public boolean b(Model model) {
        return true;
    }
}
