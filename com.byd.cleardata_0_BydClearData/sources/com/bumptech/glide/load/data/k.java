package com.bumptech.glide.load.data;

import com.bumptech.glide.load.data.e;
import com.bumptech.glide.load.p.d.s;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class k implements e<InputStream> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final s f1986a;

    public static final class a implements e.a<InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final com.bumptech.glide.load.n.a0.b f1987a;

        public a(com.bumptech.glide.load.n.a0.b bVar) {
            this.f1987a = bVar;
        }

        @Override // com.bumptech.glide.load.data.e.a
        public Class<InputStream> a() {
            return InputStream.class;
        }

        @Override // com.bumptech.glide.load.data.e.a
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public e<InputStream> b(InputStream inputStream) {
            return new k(inputStream, this.f1987a);
        }
    }

    public k(InputStream inputStream, com.bumptech.glide.load.n.a0.b bVar) {
        s sVar = new s(inputStream, bVar);
        this.f1986a = sVar;
        sVar.mark(5242880);
    }

    @Override // com.bumptech.glide.load.data.e
    public void b() {
        this.f1986a.c();
    }

    public void c() {
        this.f1986a.b();
    }

    @Override // com.bumptech.glide.load.data.e
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public InputStream a() {
        this.f1986a.reset();
        return this.f1986a;
    }
}
