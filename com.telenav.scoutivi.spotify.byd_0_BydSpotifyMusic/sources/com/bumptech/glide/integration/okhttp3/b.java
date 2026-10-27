package com.bumptech.glide.integration.okhttp3;

import c6.e;
import c6.x;
import java.io.InputStream;
import n1.i;
import u1.g;
import u1.n;
import u1.o;
import u1.r;

/* JADX INFO: compiled from: OkHttpUrlLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements n<g, InputStream> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final e.a f3993a;

    /* JADX INFO: compiled from: OkHttpUrlLoader.java */
    public static class a implements o<g, InputStream> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static volatile e.a f3994b;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final e.a f3995a;

        public a() {
            this(a());
        }

        private static e.a a() {
            if (f3994b == null) {
                synchronized (a.class) {
                    if (f3994b == null) {
                        f3994b = new x();
                    }
                }
            }
            return f3994b;
        }

        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<g, InputStream> c(r rVar) {
            return new b(this.f3995a);
        }

        public a(e.a aVar) {
            this.f3995a = aVar;
        }
    }

    public b(e.a aVar) {
        this.f3993a = aVar;
    }

    @Override // u1.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<InputStream> b(g gVar, int i7, int i8, i iVar) {
        return new n.a<>(gVar, new m1.a(this.f3993a, gVar));
    }

    @Override // u1.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean a(g gVar) {
        return true;
    }
}
