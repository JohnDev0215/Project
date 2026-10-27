package com.bumptech.glide.load.o;

import com.bumptech.glide.load.data.d;
import com.bumptech.glide.load.o.n;

/* JADX INFO: loaded from: classes.dex */
public class v<Model> implements n<Model, Model> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final v<?> f2277a = new v<>();

    public static class a<Model> implements o<Model, Model> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final a<?> f2278a = new a<>();

        @Deprecated
        public a() {
        }

        public static <T> a<T> a() {
            return (a<T>) f2278a;
        }

        @Override // com.bumptech.glide.load.o.o
        public n<Model, Model> b(r rVar) {
            return v.c();
        }
    }

    private static class b<Model> implements com.bumptech.glide.load.data.d<Model> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Model f2279b;

        b(Model model) {
            this.f2279b = model;
        }

        @Override // com.bumptech.glide.load.data.d
        public Class<Model> a() {
            return (Class<Model>) this.f2279b.getClass();
        }

        @Override // com.bumptech.glide.load.data.d
        public void b() {
        }

        @Override // com.bumptech.glide.load.data.d
        public com.bumptech.glide.load.a c() {
            return com.bumptech.glide.load.a.LOCAL;
        }

        @Override // com.bumptech.glide.load.data.d
        public void cancel() {
        }

        @Override // com.bumptech.glide.load.data.d
        public void e(com.bumptech.glide.g gVar, d.a<? super Model> aVar) {
            aVar.f(this.f2279b);
        }
    }

    @Deprecated
    public v() {
    }

    public static <T> v<T> c() {
        return (v<T>) f2277a;
    }

    @Override // com.bumptech.glide.load.o.n
    public n.a<Model> a(Model model, int i, int i2, com.bumptech.glide.load.i iVar) {
        return new n.a<>(new com.bumptech.glide.s.b(model), new b(model));
    }

    @Override // com.bumptech.glide.load.o.n
    public boolean b(Model model) {
        return true;
    }
}
