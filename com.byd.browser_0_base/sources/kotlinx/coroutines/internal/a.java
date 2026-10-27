package kotlinx.coroutines.internal;

import defpackage.d70;
import defpackage.i21;
import defpackage.k9;
import defpackage.sa2;
import defpackage.ts1;
import defpackage.y62;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: LockFreeLinkedList.kt */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static final /* synthetic */ AtomicReferenceFieldUpdater e = AtomicReferenceFieldUpdater.newUpdater(a.class, Object.class, "_next");
    public static final /* synthetic */ AtomicReferenceFieldUpdater f = AtomicReferenceFieldUpdater.newUpdater(a.class, Object.class, "_prev");
    public static final /* synthetic */ AtomicReferenceFieldUpdater g = AtomicReferenceFieldUpdater.newUpdater(a.class, Object.class, "_removedRef");
    volatile /* synthetic */ Object _next = this;
    volatile /* synthetic */ Object _prev = this;
    private volatile /* synthetic */ Object _removedRef = null;

    /* JADX INFO: renamed from: kotlinx.coroutines.internal.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LockFreeLinkedList.kt */
    public static abstract class AbstractC0060a extends k9<a> {
        public final a b;
        public a c;

        public AbstractC0060a(a aVar) {
            this.b = aVar;
        }

        @Override // defpackage.k9
        public final void b(a aVar, Object obj) {
            a aVar2 = aVar;
            boolean z = true;
            boolean z2 = obj == null;
            a aVar3 = this.b;
            a aVar4 = z2 ? aVar3 : this.c;
            if (aVar4 != null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = a.e;
                while (true) {
                    if (atomicReferenceFieldUpdater.compareAndSet(aVar2, this, aVar4)) {
                        break;
                    } else if (atomicReferenceFieldUpdater.get(aVar2) != this) {
                        z = false;
                        break;
                    }
                }
                if (z && z2) {
                    a aVar5 = this.c;
                    i21.b(aVar5);
                    aVar3.e(aVar5);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x003e, code lost:
    
        r7 = kotlinx.coroutines.internal.a.e;
        r4 = ((defpackage.sa2) r4).a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0048, code lost:
    
        if (r7.compareAndSet(r3, r1, r4) == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x004a, code lost:
    
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0050, code lost:
    
        if (r7.get(r3) == r1) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0052, code lost:
    
        if (r5 != false) goto L36;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final kotlinx.coroutines.internal.a d() {
        /*
            r9 = this;
        L0:
            java.lang.Object r0 = r9._prev
            kotlinx.coroutines.internal.a r0 = (kotlinx.coroutines.internal.a) r0
            r1 = r0
        L5:
            r2 = 0
            r3 = r2
        L7:
            java.lang.Object r4 = r1._next
            r5 = 0
            r6 = 1
            if (r4 != r9) goto L24
            if (r0 != r1) goto L10
            return r1
        L10:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r7 = kotlinx.coroutines.internal.a.f
        L12:
            boolean r2 = r7.compareAndSet(r9, r0, r1)
            if (r2 == 0) goto L1a
            r5 = r6
            goto L20
        L1a:
            java.lang.Object r2 = r7.get(r9)
            if (r2 == r0) goto L12
        L20:
            if (r5 != 0) goto L23
            goto L0
        L23:
            return r1
        L24:
            boolean r7 = r9.j()
            if (r7 == 0) goto L2b
            return r2
        L2b:
            if (r4 != 0) goto L2e
            return r1
        L2e:
            boolean r7 = r4 instanceof defpackage.ts1
            if (r7 == 0) goto L38
            ts1 r4 = (defpackage.ts1) r4
            r4.a(r1)
            goto L0
        L38:
            boolean r7 = r4 instanceof defpackage.sa2
            if (r7 == 0) goto L5c
            if (r3 == 0) goto L57
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r7 = kotlinx.coroutines.internal.a.e
            sa2 r4 = (defpackage.sa2) r4
            kotlinx.coroutines.internal.a r4 = r4.a
        L44:
            boolean r2 = r7.compareAndSet(r3, r1, r4)
            if (r2 == 0) goto L4c
            r5 = r6
            goto L52
        L4c:
            java.lang.Object r2 = r7.get(r3)
            if (r2 == r1) goto L44
        L52:
            if (r5 != 0) goto L55
            goto L0
        L55:
            r1 = r3
            goto L5
        L57:
            java.lang.Object r1 = r1._prev
            kotlinx.coroutines.internal.a r1 = (kotlinx.coroutines.internal.a) r1
            goto L7
        L5c:
            r3 = r4
            kotlinx.coroutines.internal.a r3 = (kotlinx.coroutines.internal.a) r3
            r8 = r3
            r3 = r1
            r1 = r8
            goto L7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.a.d():kotlinx.coroutines.internal.a");
    }

    public final void e(a aVar) {
        boolean z;
        do {
            a aVar2 = (a) aVar._prev;
            if (g() != aVar) {
                return;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f;
            while (true) {
                if (atomicReferenceFieldUpdater.compareAndSet(aVar, aVar2, this)) {
                    z = true;
                    break;
                } else if (atomicReferenceFieldUpdater.get(aVar) != aVar2) {
                    z = false;
                    break;
                }
            }
        } while (!z);
        if (j()) {
            aVar.d();
        }
    }

    public final Object g() {
        while (true) {
            Object obj = this._next;
            if (!(obj instanceof ts1)) {
                return obj;
            }
            ((ts1) obj).a(this);
        }
    }

    public final a h() {
        Object objG = g();
        sa2 sa2Var = objG instanceof sa2 ? (sa2) objG : null;
        a aVar = sa2Var != null ? sa2Var.a : null;
        return aVar == null ? (a) objG : aVar;
    }

    public final a i() {
        a aVarD = d();
        if (aVarD == null) {
            aVarD = (a) this._prev;
            while (aVarD.j()) {
                aVarD = (a) aVarD._prev;
            }
        }
        return aVarD;
    }

    public boolean j() {
        return g() instanceof sa2;
    }

    public final void k() {
        a aVar;
        boolean z;
        do {
            Object objG = g();
            if (objG instanceof sa2) {
                a aVar2 = ((sa2) objG).a;
                return;
            }
            if (objG == this) {
                return;
            }
            aVar = (a) objG;
            sa2 sa2Var = (sa2) aVar._removedRef;
            if (sa2Var == null) {
                sa2Var = new sa2(aVar);
                g.lazySet(aVar, sa2Var);
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = e;
            while (true) {
                if (atomicReferenceFieldUpdater.compareAndSet(this, objG, sa2Var)) {
                    z = true;
                    break;
                } else if (atomicReferenceFieldUpdater.get(this) != objG) {
                    z = false;
                    break;
                }
            }
        } while (!z);
        aVar.d();
    }

    public String toString() {
        return new y62(this) { // from class: kotlinx.coroutines.internal.a.b
        } + '@' + d70.d(this);
    }
}
