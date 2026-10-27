package com.bumptech.glide.load.i.y;

import com.bumptech.glide.load.data.j;
import com.bumptech.glide.load.i.g;
import com.bumptech.glide.load.i.m;
import com.bumptech.glide.load.i.n;
import com.bumptech.glide.load.i.o;
import com.bumptech.glide.load.i.r;
import java.io.InputStream;

/* JADX INFO: compiled from: HttpGlideUrlLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements n<g, InputStream> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final com.bumptech.glide.load.d<Integer> f3260a = com.bumptech.glide.load.d.f("com.bumptech.glide.load.model.stream.HttpGlideUrlLoader.Timeout", 2500);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final m<g, g> f3261b;

    /* JADX INFO: renamed from: com.bumptech.glide.load.i.y.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HttpGlideUrlLoader.java */
    public static class C0074a implements o<g, InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final m<g, g> f3262a = new m<>(500);

        @Override // com.bumptech.glide.load.i.o
        public n<g, InputStream> b(r rVar) {
            return new a(this.f3262a);
        }
    }

    public a(m<g, g> mVar) {
        this.f3261b = mVar;
    }

    @Override // com.bumptech.glide.load.i.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<InputStream> a(g gVar, int i, int i2, com.bumptech.glide.load.e eVar) {
        m<g, g> mVar = this.f3261b;
        if (mVar != null) {
            g gVarA = mVar.a(gVar, 0, 0);
            if (gVarA == null) {
                this.f3261b.b(gVar, 0, 0, gVar);
            } else {
                gVar = gVarA;
            }
        }
        return new n.a<>(gVar, new j(gVar, ((Integer) eVar.c(f3260a)).intValue()));
    }

    @Override // com.bumptech.glide.load.i.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(g gVar) {
        return true;
    }
}
