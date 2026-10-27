package com.bumptech.glide.load.j;

import java.util.Queue;

/* JADX INFO: compiled from: ModelCache.java */
/* JADX INFO: loaded from: classes.dex */
public class m<A, B> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.bumptech.glide.m.g<b<A>, B> f2991a;

    /* JADX INFO: compiled from: ModelCache.java */
    class a extends com.bumptech.glide.m.g<b<A>, B> {
        a(long j) {
            super(j);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bumptech.glide.m.g
        /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
        public void j(b<A> bVar, B b2) {
            bVar.c();
        }
    }

    /* JADX INFO: compiled from: ModelCache.java */
    static final class b<A> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final Queue<b<?>> f2992a = com.bumptech.glide.m.k.e(0);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2993b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f2994c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private A f2995d;

        private b() {
        }

        static <A> b<A> a(A a2, int i, int i2) {
            b<A> bVar;
            Queue<b<?>> queue = f2992a;
            synchronized (queue) {
                bVar = (b) queue.poll();
            }
            if (bVar == null) {
                bVar = new b<>();
            }
            bVar.b(a2, i, i2);
            return bVar;
        }

        private void b(A a2, int i, int i2) {
            this.f2995d = a2;
            this.f2994c = i;
            this.f2993b = i2;
        }

        public void c() {
            Queue<b<?>> queue = f2992a;
            synchronized (queue) {
                queue.offer(this);
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.f2994c == bVar.f2994c && this.f2993b == bVar.f2993b && this.f2995d.equals(bVar.f2995d);
        }

        public int hashCode() {
            return (((this.f2993b * 31) + this.f2994c) * 31) + this.f2995d.hashCode();
        }
    }

    public m(long j) {
        this.f2991a = new a(j);
    }

    public B a(A a2, int i, int i2) {
        b<A> bVarA = b.a(a2, i, i2);
        B bG = this.f2991a.g(bVarA);
        bVarA.c();
        return bG;
    }

    public void b(A a2, int i, int i2, B b2) {
        this.f2991a.k(b.a(a2, i, i2), b2);
    }
}
