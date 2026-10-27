package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import p.dc1;
import p.f60;
import p.g32;
import p.oq1;
import p.tz2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static final AtomicReferenceFieldUpdater c = AtomicReferenceFieldUpdater.newUpdater(a.class, Object.class, "_next");
    public static final AtomicReferenceFieldUpdater e = AtomicReferenceFieldUpdater.newUpdater(a.class, Object.class, "_prev");
    public static final AtomicReferenceFieldUpdater k = AtomicReferenceFieldUpdater.newUpdater(a.class, Object.class, "_removedRef");
    private volatile Object _next = this;
    private volatile Object _prev = this;
    private volatile Object _removedRef;

    /* JADX WARN: Code restructure failed: missing block: B:25:0x003e, code lost:
    
        r6 = ((p.g32) r6).a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0046, code lost:
    
        if (r5.compareAndSet(r4, r3, r6) == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x004e, code lost:
    
        if (r5.get(r4) == r3) goto L51;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final kotlinx.coroutines.internal.a d() {
        /*
            r9 = this;
        L0:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = kotlinx.coroutines.internal.a.e
            java.lang.Object r1 = r0.get(r9)
            kotlinx.coroutines.internal.a r1 = (kotlinx.coroutines.internal.a) r1
            r2 = 0
            r3 = r1
        La:
            r4 = r2
        Lb:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = kotlinx.coroutines.internal.a.c
            java.lang.Object r6 = r5.get(r3)
            if (r6 != r9) goto L24
            if (r1 != r3) goto L16
            return r3
        L16:
            boolean r2 = r0.compareAndSet(r9, r1, r3)
            if (r2 == 0) goto L1d
            return r3
        L1d:
            java.lang.Object r2 = r0.get(r9)
            if (r2 == r1) goto L16
            goto L0
        L24:
            boolean r7 = r9.j()
            if (r7 == 0) goto L2b
            return r2
        L2b:
            if (r6 != 0) goto L2e
            return r3
        L2e:
            boolean r7 = r6 instanceof p.oq1
            if (r7 == 0) goto L38
            p.oq1 r6 = (p.oq1) r6
            r6.a(r3)
            goto L0
        L38:
            boolean r7 = r6 instanceof p.g32
            if (r7 == 0) goto L58
            if (r4 == 0) goto L51
            p.g32 r6 = (p.g32) r6
            kotlinx.coroutines.internal.a r6 = r6.a
        L42:
            boolean r7 = r5.compareAndSet(r4, r3, r6)
            if (r7 == 0) goto L4a
            r3 = r4
            goto La
        L4a:
            java.lang.Object r7 = r5.get(r4)
            if (r7 == r3) goto L42
            goto L0
        L51:
            java.lang.Object r3 = r0.get(r3)
            kotlinx.coroutines.internal.a r3 = (kotlinx.coroutines.internal.a) r3
            goto Lb
        L58:
            java.lang.String r4 = "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }"
            p.v03.g(r6, r4)
            r4 = r6
            kotlinx.coroutines.internal.a r4 = (kotlinx.coroutines.internal.a) r4
            r8 = r4
            r4 = r3
            r3 = r8
            goto Lb
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.a.d():kotlinx.coroutines.internal.a");
    }

    public final void f(a aVar) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = e;
            a aVar2 = (a) atomicReferenceFieldUpdater.get(aVar);
            if (h() != aVar) {
                return;
            }
            while (!atomicReferenceFieldUpdater.compareAndSet(aVar, aVar2, this)) {
                if (atomicReferenceFieldUpdater.get(aVar) != aVar2) {
                    break;
                }
            }
            if (j()) {
                aVar.d();
                return;
            }
            return;
        }
    }

    public final Object h() {
        while (true) {
            Object obj = c.get(this);
            if (!(obj instanceof oq1)) {
                return obj;
            }
            ((oq1) obj).a(this);
        }
    }

    public final a i() {
        a aVar;
        Object objH = h();
        tz2 tz2Var = dc1.a;
        g32 g32Var = objH instanceof g32 ? (g32) objH : null;
        if (g32Var != null && (aVar = g32Var.a) != null) {
            return aVar;
        }
        v03.g(objH, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        return (a) objH;
    }

    public boolean j() {
        return h() instanceof g32;
    }

    public String toString() {
        return new LockFreeLinkedListNode$toString$1(this, f60.class, "classSimpleName", "getClassSimpleName(Ljava/lang/Object;)Ljava/lang/String;", 1) + '@' + f60.s(this);
    }
}
