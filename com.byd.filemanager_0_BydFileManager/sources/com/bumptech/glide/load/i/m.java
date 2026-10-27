package com.bumptech.glide.load.i;

import java.util.Queue;

/* JADX INFO: compiled from: ModelCache.java */
/* JADX INFO: loaded from: classes.dex */
public class m<A, B> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.bumptech.glide.o.g<b<A>, B> f3215a;

    /* JADX INFO: compiled from: ModelCache.java */
    class a extends com.bumptech.glide.o.g<b<A>, B> {
        a(long j) {
            super(j);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bumptech.glide.o.g
        /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
        public void j(b<A> bVar, B b2) {
            bVar.c();
        }
    }

    /* JADX INFO: compiled from: ModelCache.java */
    static final class b<A> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final Queue<b<?>> f3217a = com.bumptech.glide.o.k.e(0);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f3218b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f3219c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private A f3220d;

        private b() {
        }

        static <A> b<A> a(A a2, int i, int i2) {
            b<A> bVar;
            Queue<b<?>> queue = f3217a;
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
            this.f3220d = a2;
            this.f3219c = i;
            this.f3218b = i2;
        }

        public void c() {
            Queue<b<?>> queue = f3217a;
            synchronized (queue) {
                queue.offer(this);
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.f3219c == bVar.f3219c && this.f3218b == bVar.f3218b && this.f3220d.equals(bVar.f3220d);
        }

        public int hashCode() {
            return (((this.f3218b * 31) + this.f3219c) * 31) + this.f3220d.hashCode();
        }
    }

    public m(long j) {
        this.f3215a = new a(j);
    }

    public B a(A a2, int i, int i2) {
        b<A> bVarA = b.a(a2, i, i2);
        B bG = this.f3215a.g(bVarA);
        bVarA.c();
        return bG;
    }

    public void b(A a2, int i, int i2, B b2) {
        this.f3215a.k(b.a(a2, i, i2), b2);
    }
}
