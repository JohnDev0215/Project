package com.bumptech.glide.load.j;

import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: ModelLoader.java */
/* JADX INFO: loaded from: classes.dex */
public interface n<Model, Data> {

    /* JADX INFO: compiled from: ModelLoader.java */
    public static class a<Data> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final com.bumptech.glide.load.c f2996a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final List<com.bumptech.glide.load.c> f2997b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final com.bumptech.glide.load.i.d<Data> f2998c;

        public a(com.bumptech.glide.load.c cVar, com.bumptech.glide.load.i.d<Data> dVar) {
            this(cVar, Collections.emptyList(), dVar);
        }

        public a(com.bumptech.glide.load.c cVar, List<com.bumptech.glide.load.c> list, com.bumptech.glide.load.i.d<Data> dVar) {
            this.f2996a = (com.bumptech.glide.load.c) com.bumptech.glide.m.j.d(cVar);
            this.f2997b = (List) com.bumptech.glide.m.j.d(list);
            this.f2998c = (com.bumptech.glide.load.i.d) com.bumptech.glide.m.j.d(dVar);
        }
    }

    a<Data> a(Model model, int i, int i2, com.bumptech.glide.load.e eVar);

    boolean b(Model model);
}
