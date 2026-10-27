package u1;

import java.util.Queue;

/* JADX INFO: compiled from: ModelCache.java */
/* JADX INFO: loaded from: classes.dex */
public class m<A, B> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final k2.g<b<A>, B> f9607a;

    /* JADX INFO: compiled from: ModelCache.java */
    class a extends k2.g<b<A>, B> {
        a(long j7) {
            super(j7);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // k2.g
        /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
        public void j(b<A> bVar, B b7) {
            bVar.c();
        }
    }

    /* JADX INFO: compiled from: ModelCache.java */
    static final class b<A> {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static final Queue<b<?>> f9609d = k2.k.e(0);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f9610a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f9611b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private A f9612c;

        private b() {
        }

        static <A> b<A> a(A a7, int i7, int i8) {
            b<A> bVar;
            Queue<b<?>> queue = f9609d;
            synchronized (queue) {
                bVar = (b) queue.poll();
            }
            if (bVar == null) {
                bVar = new b<>();
            }
            bVar.b(a7, i7, i8);
            return bVar;
        }

        private void b(A a7, int i7, int i8) {
            this.f9612c = a7;
            this.f9611b = i7;
            this.f9610a = i8;
        }

        public void c() {
            Queue<b<?>> queue = f9609d;
            synchronized (queue) {
                queue.offer(this);
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.f9611b == bVar.f9611b && this.f9610a == bVar.f9610a && this.f9612c.equals(bVar.f9612c);
        }

        public int hashCode() {
            return (((this.f9610a * 31) + this.f9611b) * 31) + this.f9612c.hashCode();
        }
    }

    public m(long j7) {
        this.f9607a = new a(j7);
    }

    public B a(A a7, int i7, int i8) {
        b<A> bVarA = b.a(a7, i7, i8);
        B bG = this.f9607a.g(bVarA);
        bVarA.c();
        return bG;
    }

    public void b(A a7, int i7, int i8, B b7) {
        this.f9607a.k(b.a(a7, i7, i8), b7);
    }
}
