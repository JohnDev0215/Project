package Z2;

import T.C0746i0;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.ArrayDeque;

/* JADX INFO: loaded from: classes.dex */
public abstract class h implements Y2.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayDeque f10880a = new ArrayDeque();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayDeque f10881b;
    public final ArrayDeque c;
    public g d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f10882e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f10883f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f10884g;

    public h() {
        for (int i7 = 0; i7 < 10; i7++) {
            this.f10880a.add(new g(1));
        }
        this.f10881b = new ArrayDeque();
        for (int i10 = 0; i10 < 2; i10++) {
            ArrayDeque arrayDeque = this.f10881b;
            G2.a aVar = new G2.a(this, 5);
            Y2.c cVar = new Y2.c();
            cVar.f10636s = aVar;
            arrayDeque.add(cVar);
        }
        this.c = new ArrayDeque();
        this.f10884g = -9223372036854775807L;
    }

    @Override // m2.d
    public void a() {
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002c  */
    @Override // m2.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(Y2.i r7) {
        /*
            r6 = this;
            Z2.g r0 = r6.d
            if (r7 != r0) goto L6
            r0 = 1
            goto L7
        L6:
            r0 = 0
        L7:
            h2.AbstractC1346c.c(r0)
            Z2.g r7 = (Z2.g) r7
            long r0 = r7.f18957r
            r2 = -9223372036854775808
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 == 0) goto L2c
            long r2 = r6.f10884g
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 == 0) goto L2c
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 >= 0) goto L2c
            r7.f()
            java.util.ArrayDeque r0 = r6.f10880a
            r0.add(r7)
            goto L3a
        L2c:
            long r0 = r6.f10883f
            r2 = 1
            long r2 = r2 + r0
            r6.f10883f = r2
            r7.f10879v = r0
            java.util.ArrayDeque r0 = r6.c
            r0.add(r7)
        L3a:
            r7 = 0
            r6.d = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Z2.h.b(Y2.i):void");
    }

    @Override // m2.d
    public final void c(long j3) {
        this.f10884g = j3;
    }

    @Override // Y2.e
    public final void d(long j3) {
        this.f10882e = j3;
    }

    @Override // m2.d
    public final Object f() {
        AbstractC1346c.j(this.d == null);
        ArrayDeque arrayDeque = this.f10880a;
        if (arrayDeque.isEmpty()) {
            return null;
        }
        g gVar = (g) arrayDeque.pollFirst();
        this.d = gVar;
        return gVar;
    }

    @Override // m2.d
    public void flush() {
        ArrayDeque arrayDeque;
        this.f10883f = 0L;
        this.f10882e = 0L;
        while (true) {
            ArrayDeque arrayDeque2 = this.c;
            boolean zIsEmpty = arrayDeque2.isEmpty();
            arrayDeque = this.f10880a;
            if (zIsEmpty) {
                break;
            }
            g gVar = (g) arrayDeque2.poll();
            int i7 = AbstractC1343A.f15195a;
            gVar.f();
            arrayDeque.add(gVar);
        }
        g gVar2 = this.d;
        if (gVar2 != null) {
            gVar2.f();
            arrayDeque.add(gVar2);
            this.d = null;
        }
    }

    public abstract C0746i0 g();

    public abstract void h(g gVar);

    /* JADX WARN: Code restructure failed: missing block: B:18:0x006a, code lost:
    
        return null;
     */
    @Override // m2.d
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public Y2.c e() {
        /*
            r7 = this;
            java.util.ArrayDeque r0 = r7.f10881b
            boolean r1 = r0.isEmpty()
            r2 = 0
            if (r1 == 0) goto La
            return r2
        La:
            java.util.ArrayDeque r1 = r7.c
            boolean r3 = r1.isEmpty()
            if (r3 != 0) goto L6a
            java.lang.Object r3 = r1.peek()
            Z2.g r3 = (Z2.g) r3
            int r4 = h2.AbstractC1343A.f15195a
            long r3 = r3.f18957r
            long r5 = r7.f10882e
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 > 0) goto L6a
            java.lang.Object r1 = r1.poll()
            Z2.g r1 = (Z2.g) r1
            r3 = 4
            boolean r4 = r1.e(r3)
            java.util.ArrayDeque r5 = r7.f10880a
            if (r4 == 0) goto L41
            java.lang.Object r7 = r0.pollFirst()
            Y2.c r7 = (Y2.c) r7
            r7.b(r3)
            r1.f()
            r5.add(r1)
            return r7
        L41:
            r7.h(r1)
            boolean r3 = r7.j()
            if (r3 == 0) goto L63
            T.i0 r7 = r7.g()
            java.lang.Object r0 = r0.pollFirst()
            Y2.c r0 = (Y2.c) r0
            long r2 = r1.f18957r
            r0.f18960n = r2
            r0.f10633p = r7
            r0.f10634q = r2
            r1.f()
            r5.add(r1)
            return r0
        L63:
            r1.f()
            r5.add(r1)
            goto La
        L6a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: Z2.h.e():Y2.c");
    }

    public abstract boolean j();
}
