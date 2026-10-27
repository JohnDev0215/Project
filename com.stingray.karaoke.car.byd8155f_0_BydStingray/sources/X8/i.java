package X8;

import S8.AbstractC0727y;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public class i {
    public static final /* synthetic */ AtomicReferenceFieldUpdater l = AtomicReferenceFieldUpdater.newUpdater(i.class, Object.class, "_next$volatile");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f10564m = AtomicReferenceFieldUpdater.newUpdater(i.class, Object.class, "_prev$volatile");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f10565n = AtomicReferenceFieldUpdater.newUpdater(i.class, Object.class, "_removedRef$volatile");
    private volatile /* synthetic */ Object _next$volatile = this;
    private volatile /* synthetic */ Object _prev$volatile = this;
    private volatile /* synthetic */ Object _removedRef$volatile;

    public final boolean e(i iVar, int i7) {
        while (true) {
            i iVarF = f();
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f10564m;
            if (iVarF == null) {
                Object obj = atomicReferenceFieldUpdater.get(this);
                while (true) {
                    iVarF = (i) obj;
                    if (!iVarF.i()) {
                        break;
                    }
                    obj = atomicReferenceFieldUpdater.get(iVarF);
                }
            }
            if (iVarF instanceof h) {
                return (((h) iVarF).o & i7) == 0 && iVarF.e(iVar, i7);
            }
            atomicReferenceFieldUpdater.set(iVar, iVarF);
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = l;
            atomicReferenceFieldUpdater2.set(iVar, this);
            while (!atomicReferenceFieldUpdater2.compareAndSet(iVarF, this, iVar)) {
                if (atomicReferenceFieldUpdater2.get(iVarF) != this) {
                    break;
                }
            }
            iVar.g(this);
            return true;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0031, code lost:
    
        r6 = ((X8.o) r6).f10576a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0039, code lost:
    
        if (r5.compareAndSet(r4, r3, r6) == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0041, code lost:
    
        if (r5.get(r4) == r3) goto L43;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final X8.i f() {
        /*
            r9 = this;
        L0:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = X8.i.f10564m
            java.lang.Object r1 = r0.get(r9)
            X8.i r1 = (X8.i) r1
            r2 = 0
            r3 = r1
        La:
            r4 = r2
        Lb:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = X8.i.l
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
            boolean r7 = r9.i()
            if (r7 == 0) goto L2b
            return r2
        L2b:
            boolean r7 = r6 instanceof X8.o
            if (r7 == 0) goto L4b
            if (r4 == 0) goto L44
            X8.o r6 = (X8.o) r6
            X8.i r6 = r6.f10576a
        L35:
            boolean r7 = r5.compareAndSet(r4, r3, r6)
            if (r7 == 0) goto L3d
            r3 = r4
            goto La
        L3d:
            java.lang.Object r7 = r5.get(r4)
            if (r7 == r3) goto L35
            goto L0
        L44:
            java.lang.Object r3 = r0.get(r3)
            X8.i r3 = (X8.i) r3
            goto Lb
        L4b:
            java.lang.String r4 = "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode"
            A7.m.d(r6, r4)
            r4 = r6
            X8.i r4 = (X8.i) r4
            r8 = r4
            r4 = r3
            r3 = r8
            goto Lb
        */
        throw new UnsupportedOperationException("Method not decompiled: X8.i.f():X8.i");
    }

    public final void g(i iVar) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f10564m;
            i iVar2 = (i) atomicReferenceFieldUpdater.get(iVar);
            if (l.get(this) != iVar) {
                return;
            }
            while (!atomicReferenceFieldUpdater.compareAndSet(iVar, iVar2, this)) {
                if (atomicReferenceFieldUpdater.get(iVar) != iVar2) {
                    break;
                }
            }
            if (i()) {
                iVar.f();
                return;
            }
            return;
        }
    }

    public final i h() {
        i iVar;
        Object obj = l.get(this);
        o oVar = obj instanceof o ? (o) obj : null;
        if (oVar != null && (iVar = oVar.f10576a) != null) {
            return iVar;
        }
        A7.m.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode");
        return (i) obj;
    }

    public boolean i() {
        return l.get(this) instanceof o;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i7 = 3;
        sb.append(new E.m(1, i7, AbstractC0727y.class, this, "classSimpleName", "getClassSimpleName(Ljava/lang/Object;)Ljava/lang/String;"));
        sb.append('@');
        sb.append(AbstractC0727y.n(this));
        return sb.toString();
    }
}
