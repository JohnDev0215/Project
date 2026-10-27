package com.bumptech.glide.load.o.y;

import com.bumptech.glide.load.data.j;
import com.bumptech.glide.load.h;
import com.bumptech.glide.load.i;
import com.bumptech.glide.load.o.g;
import com.bumptech.glide.load.o.m;
import com.bumptech.glide.load.o.n;
import com.bumptech.glide.load.o.o;
import com.bumptech.glide.load.o.r;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class a implements n<g, InputStream> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h<Integer> f2287a = h.f("com.bumptech.glide.load.model.stream.HttpGlideUrlLoader.Timeout", 2500);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final m<g, g> f2288b;

    /* JADX INFO: renamed from: com.bumptech.glide.load.o.y.a$a, reason: collision with other inner class name */
    public static class C0078a implements o<g, InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final m<g, g> f2289a = new m<>(500);

        @Override // com.bumptech.glide.load.o.o
        public n<g, InputStream> b(r rVar) {
            return new a(this.f2289a);
        }
    }

    public a(m<g, g> mVar) {
        this.f2288b = mVar;
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<InputStream> a(g gVar, int i, int i2, i iVar) {
        m<g, g> mVar = this.f2288b;
        if (mVar != null) {
            g gVarA = mVar.a(gVar, 0, 0);
            if (gVarA == null) {
                this.f2288b.b(gVar, 0, 0, gVar);
            } else {
                gVar = gVarA;
            }
        }
        return new n.a<>(gVar, new j(gVar, ((Integer) iVar.c(f2287a)).intValue()));
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(g gVar) {
        return true;
    }
}
