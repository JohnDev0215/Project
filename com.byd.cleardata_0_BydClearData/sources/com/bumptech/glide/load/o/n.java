package com.bumptech.glide.load.o;

import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface n<Model, Data> {

    public static class a<Data> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final com.bumptech.glide.load.g f2250a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final List<com.bumptech.glide.load.g> f2251b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final com.bumptech.glide.load.data.d<Data> f2252c;

        public a(com.bumptech.glide.load.g gVar, com.bumptech.glide.load.data.d<Data> dVar) {
            this(gVar, Collections.emptyList(), dVar);
        }

        public a(com.bumptech.glide.load.g gVar, List<com.bumptech.glide.load.g> list, com.bumptech.glide.load.data.d<Data> dVar) {
            this.f2250a = (com.bumptech.glide.load.g) com.bumptech.glide.t.j.d(gVar);
            this.f2251b = (List) com.bumptech.glide.t.j.d(list);
            this.f2252c = (com.bumptech.glide.load.data.d) com.bumptech.glide.t.j.d(dVar);
        }
    }

    a<Data> a(Model model, int i, int i2, com.bumptech.glide.load.i iVar);

    boolean b(Model model);
}
