package U8;

import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: loaded from: classes.dex */
public final class t extends X8.r {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final l f9272p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ AtomicReferenceArray f9273q;

    public t(long j3, t tVar, l lVar, int i7) {
        super(j3, tVar, i7);
        this.f9272p = lVar;
        this.f9273q = new AtomicReferenceArray(n.f9252b * 2);
    }

    @Override // X8.r
    public final int g() {
        return n.f9252b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x005b, code lost:
    
        n(r7, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x005e, code lost:
    
        if (r1 == false) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0060, code lost:
    
        A7.m.c(r4);
        r6 = r4.f9248m;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0065, code lost:
    
        if (r6 == null) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0067, code lost:
    
        X8.a.a(r6, r0, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x006a, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:?, code lost:
    
        return;
     */
    @Override // X8.r
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void h(int r7, q7.InterfaceC2194h r8) throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            r6 = this;
            int r0 = U8.n.f9252b
            if (r7 < r0) goto L6
            r1 = 1
            goto L7
        L6:
            r1 = 0
        L7:
            if (r1 == 0) goto La
            int r7 = r7 - r0
        La:
            java.util.concurrent.atomic.AtomicReferenceArray r0 = r6.f9273q
            int r2 = r7 * 2
            java.lang.Object r0 = r0.get(r2)
        L12:
            java.lang.Object r2 = r6.l(r7)
            boolean r3 = r2 instanceof S8.v0
            U8.l r4 = r6.f9272p
            r5 = 0
            if (r3 != 0) goto L6b
            boolean r3 = r2 instanceof U8.B
            if (r3 == 0) goto L22
            goto L6b
        L22:
            B2.q r3 = U8.n.f9258j
            if (r2 == r3) goto L5b
            B2.q r3 = U8.n.f9259k
            if (r2 != r3) goto L2b
            goto L5b
        L2b:
            B2.q r3 = U8.n.f9255g
            if (r2 == r3) goto L12
            B2.q r3 = U8.n.f9254f
            if (r2 != r3) goto L34
            goto L12
        L34:
            B2.q r6 = U8.n.f9257i
            if (r2 == r6) goto L5a
            B2.q r6 = U8.n.d
            if (r2 != r6) goto L3d
            goto L5a
        L3d:
            B2.q r6 = U8.n.l
            if (r2 != r6) goto L42
            return
        L42:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "unexpected state: "
            r7.<init>(r8)
            r7.append(r2)
            java.lang.String r7 = r7.toString()
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        L5a:
            return
        L5b:
            r6.n(r7, r5)
            if (r1 == 0) goto L6a
            A7.m.c(r4)
            z7.k r6 = r4.f9248m
            if (r6 == 0) goto L6a
            X8.a.a(r6, r0, r8)
        L6a:
            return
        L6b:
            if (r1 == 0) goto L70
            B2.q r3 = U8.n.f9258j
            goto L72
        L70:
            B2.q r3 = U8.n.f9259k
        L72:
            boolean r2 = r6.k(r2, r7, r3)
            if (r2 == 0) goto L12
            r6.n(r7, r5)
            r2 = r1 ^ 1
            r6.m(r7, r2)
            if (r1 == 0) goto L8c
            A7.m.c(r4)
            z7.k r6 = r4.f9248m
            if (r6 == 0) goto L8c
            X8.a.a(r6, r0, r8)
        L8c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: U8.t.h(int, q7.h):void");
    }

    public final boolean k(Object obj, int i7, Object obj2) {
        AtomicReferenceArray atomicReferenceArray = this.f9273q;
        int i10 = (i7 * 2) + 1;
        while (!atomicReferenceArray.compareAndSet(i10, obj, obj2)) {
            if (atomicReferenceArray.get(i10) != obj) {
                return false;
            }
        }
        return true;
    }

    public final Object l(int i7) {
        return this.f9273q.get((i7 * 2) + 1);
    }

    public final void m(int i7, boolean z6) {
        if (z6) {
            l lVar = this.f9272p;
            A7.m.c(lVar);
            lVar.N((this.f10577n * ((long) n.f9252b)) + ((long) i7));
        }
        i();
    }

    public final void n(int i7, Object obj) {
        this.f9273q.set(i7 * 2, obj);
    }

    public final void o(int i7, B2.q qVar) {
        this.f9273q.set((i7 * 2) + 1, qVar);
    }
}
