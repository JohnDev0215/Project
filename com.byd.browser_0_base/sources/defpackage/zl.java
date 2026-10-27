package defpackage;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: CancellableContinuationImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public final class zl<T> extends va0<T> implements b00, u00 {
    public static final /* synthetic */ AtomicIntegerFieldUpdater i = AtomicIntegerFieldUpdater.newUpdater(zl.class, "_decision");
    public static final /* synthetic */ AtomicReferenceFieldUpdater j = AtomicReferenceFieldUpdater.newUpdater(zl.class, Object.class, "_state");
    private volatile /* synthetic */ int _decision;
    private volatile /* synthetic */ Object _state;
    public zn1 h;

    @Override // defpackage.u00
    public final u00 a() {
        return null;
    }

    @Override // defpackage.va0
    public final void b(Object obj, CancellationException cancellationException) throws IllegalAccessException, InvocationTargetException {
        while (true) {
            Object obj2 = this._state;
            if (obj2 instanceof ao1) {
                throw new IllegalStateException("Not completed".toString());
            }
            if (obj2 instanceof rv) {
                return;
            }
            boolean z = false;
            if (obj2 instanceof pv) {
                pv pvVar = (pv) obj2;
                if (!(!(pvVar.e != null))) {
                    throw new IllegalStateException("Must be called at most once".toString());
                }
                pv pvVar2 = new pv(pvVar.a, pvVar.b, pvVar.c, pvVar.d, cancellationException);
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = j;
                while (true) {
                    if (atomicReferenceFieldUpdater.compareAndSet(this, obj2, pvVar2)) {
                        z = true;
                        break;
                    } else if (atomicReferenceFieldUpdater.get(this) != obj2) {
                        break;
                    }
                }
                if (z) {
                    wl wlVar = pvVar.b;
                    if (wlVar != null) {
                        try {
                            wlVar.a(cancellationException);
                        } catch (Throwable th) {
                            a8.H(null, new uv(i21.g(this, "Exception in invokeOnCancellation handler for "), th));
                        }
                    }
                    jr0<Throwable, vi3> jr0Var = pvVar.c;
                    if (jr0Var == null) {
                        return;
                    }
                    try {
                        jr0Var.c(cancellationException);
                        return;
                    } catch (Throwable th2) {
                        a8.H(null, new uv(i21.g(this, "Exception in resume onCancellation handler for "), th2));
                        return;
                    }
                }
            } else {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = j;
                pv pvVar3 = new pv(obj2, (wl) null, (jr0) null, cancellationException, 14);
                while (true) {
                    if (atomicReferenceFieldUpdater2.compareAndSet(this, obj2, pvVar3)) {
                        z = true;
                        break;
                    } else if (atomicReferenceFieldUpdater2.get(this) != obj2) {
                        break;
                    }
                }
                if (z) {
                    return;
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0086, code lost:
    
        if (r14 == false) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x008a, code lost:
    
        if (r0 != 4) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x008d, code lost:
    
        r8 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x008e, code lost:
    
        r14 = h();
        r0 = e(r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0096, code lost:
    
        if (r0 == null) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0098, code lost:
    
        defpackage.a8.v(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x009c, code lost:
    
        f(r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x009f, code lost:
    
        if (r8 == false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00a1, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00a2, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:?, code lost:
    
        return;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0042  */
    @Override // defpackage.b00
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Throwable r0 = defpackage.vb2.a(r14)
            if (r0 != 0) goto L7
            goto Lc
        L7:
            rv r14 = new rv
            r14.<init>(r0)
        Lc:
            int r0 = r13.g
        Le:
            r4 = 0
            java.lang.Object r7 = r13._state
            boolean r1 = r7 instanceof defpackage.ao1
            r8 = 1
            r9 = 0
            if (r1 == 0) goto La4
            r1 = r7
            ao1 r1 = (defpackage.ao1) r1
            boolean r2 = r14 instanceof defpackage.rv
            r10 = 0
            r11 = 2
            if (r2 == 0) goto L21
            goto L42
        L21:
            if (r0 == r8) goto L28
            if (r0 != r11) goto L26
            goto L28
        L26:
            r2 = r9
            goto L29
        L28:
            r2 = r8
        L29:
            if (r2 != 0) goto L2c
            goto L42
        L2c:
            boolean r2 = r1 instanceof defpackage.wl
            if (r2 == 0) goto L42
            pv r12 = new pv
            if (r2 == 0) goto L38
            wl r1 = (defpackage.wl) r1
            r3 = r1
            goto L39
        L38:
            r3 = r10
        L39:
            r5 = 0
            r6 = 16
            r1 = r12
            r2 = r14
            r1.<init>(r2, r3, r4, r5, r6)
            goto L43
        L42:
            r12 = r14
        L43:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = defpackage.zl.j
        L45:
            boolean r2 = r1.compareAndSet(r13, r7, r12)
            if (r2 == 0) goto L4d
            r1 = r8
            goto L54
        L4d:
            java.lang.Object r2 = r1.get(r13)
            if (r2 == r7) goto L45
            r1 = r9
        L54:
            if (r1 != 0) goto L57
            goto Le
        L57:
            int r14 = r13.g
            if (r14 != r11) goto L5d
            r14 = r8
            goto L5e
        L5d:
            r14 = r9
        L5e:
            if (r14 != 0) goto La3
            zn1 r14 = r13.h
            if (r14 != 0) goto L65
            goto L69
        L65:
            zn1 r14 = defpackage.zn1.e
            r13.h = r14
        L69:
            int r14 = r13._decision
            if (r14 == 0) goto L7d
            if (r14 != r8) goto L71
            r14 = r9
            goto L86
        L71:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "Already resumed"
            java.lang.String r14 = r14.toString()
            r13.<init>(r14)
            throw r13
        L7d:
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r14 = defpackage.zl.i
            boolean r14 = r14.compareAndSet(r13, r9, r11)
            if (r14 == 0) goto L69
            r14 = r8
        L86:
            if (r14 == 0) goto L89
            goto Lb5
        L89:
            r14 = 4
            if (r0 != r14) goto L8d
            goto L8e
        L8d:
            r8 = r9
        L8e:
            java.lang.Object r14 = r13.h()
            java.lang.Throwable r0 = r13.e(r14)
            if (r0 == 0) goto L9c
            defpackage.a8.v(r0)
            goto L9f
        L9c:
            r13.f(r14)
        L9f:
            if (r8 == 0) goto La2
            throw r10
        La2:
            throw r10
        La3:
            throw r10
        La4:
            boolean r13 = r7 instanceof defpackage.bm
            if (r13 == 0) goto Lb6
            bm r7 = (defpackage.bm) r7
            r7.getClass()
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r13 = defpackage.bm.c
            boolean r13 = r13.compareAndSet(r7, r9, r8)
            if (r13 == 0) goto Lb6
        Lb5:
            return
        Lb6:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "Already resumed, but proposed with update "
            java.lang.String r14 = defpackage.i21.g(r14, r0)
            java.lang.String r14 = r14.toString()
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.zl.c(java.lang.Object):void");
    }

    @Override // defpackage.va0
    public final b00<T> d() {
        return null;
    }

    @Override // defpackage.va0
    public final Throwable e(Object obj) {
        Throwable thE = super.e(obj);
        if (thE == null) {
            return null;
        }
        return thE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.va0
    public final <T> T f(Object obj) {
        return obj instanceof pv ? (T) ((pv) obj).a : obj;
    }

    @Override // defpackage.b00
    public final m00 getContext() {
        return null;
    }

    @Override // defpackage.va0
    public final Object h() {
        return this._state;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("CancellableContinuation(");
        sb.append(d70.h(null));
        sb.append("){");
        Object obj = this._state;
        sb.append(obj instanceof ao1 ? "Active" : obj instanceof bm ? "Cancelled" : "Completed");
        sb.append("}@");
        sb.append(d70.d(this));
        return sb.toString();
    }
}
