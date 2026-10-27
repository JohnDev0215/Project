package com.bumptech.glide.load.j.y;

import com.bumptech.glide.load.i.j;
import com.bumptech.glide.load.j.g;
import com.bumptech.glide.load.j.m;
import com.bumptech.glide.load.j.n;
import com.bumptech.glide.load.j.o;
import com.bumptech.glide.load.j.r;
import java.io.InputStream;

/* JADX INFO: compiled from: HttpGlideUrlLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements n<g, InputStream> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final com.bumptech.glide.load.d<Integer> f3033a = com.bumptech.glide.load.d.f("com.bumptech.glide.load.model.stream.HttpGlideUrlLoader.Timeout", 2500);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final m<g, g> f3034b;

    /* JADX INFO: renamed from: com.bumptech.glide.load.j.y.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HttpGlideUrlLoader.java */
    public static class C0098a implements o<g, InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final m<g, g> f3035a = new m<>(500);

        @Override // com.bumptech.glide.load.j.o
        public n<g, InputStream> b(r rVar) {
            return new a(this.f3035a);
        }
    }

    public a(m<g, g> mVar) {
        this.f3034b = mVar;
    }

    @Override // com.bumptech.glide.load.j.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<InputStream> a(g gVar, int i, int i2, com.bumptech.glide.load.e eVar) {
        m<g, g> mVar = this.f3034b;
        if (mVar != null) {
            g gVarA = mVar.a(gVar, 0, 0);
            if (gVarA == null) {
                this.f3034b.b(gVar, 0, 0, gVar);
            } else {
                gVar = gVarA;
            }
        }
        return new n.a<>(gVar, new j(gVar, ((Integer) eVar.c(f3033a)).intValue()));
    }

    @Override // com.bumptech.glide.load.j.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(g gVar) {
        return true;
    }
}
