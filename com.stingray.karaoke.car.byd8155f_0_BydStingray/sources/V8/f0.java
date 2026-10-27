package V8;

import S8.C0712i;
import U8.EnumC0810a;
import W8.AbstractC0842b;
import W8.AbstractC0843c;
import W8.AbstractC0844d;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;

/* JADX INFO: loaded from: classes.dex */
public final class f0 extends AbstractC0842b implements L, InterfaceC0817f, W8.w {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f9621q = AtomicReferenceFieldUpdater.newUpdater(f0.class, Object.class, "_state$volatile");
    private volatile /* synthetic */ Object _state$volatile;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f9622p;

    public f0(Object obj) {
        this._state$volatile = obj;
    }

    @Override // W8.w
    public final InterfaceC0817f a(InterfaceC2194h interfaceC2194h, int i7, EnumC0810a enumC0810a) {
        return (((i7 < 0 || i7 >= 2) && i7 != -2) || enumC0810a != EnumC0810a.f9223m) ? AbstractC0828q.l(this, interfaceC2194h, i7, enumC0810a) : this;
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x007b, code lost:
    
        if (r12 != r1) goto L31;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Path cross not found for [B:39:0x0091, B:41:0x0097], limit reached: 70 */
    /* JADX WARN: Path cross not found for [B:41:0x0097, B:39:0x0091], limit reached: 70 */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0083 A[Catch: all -> 0x0038, TryCatch #0 {all -> 0x0038, blocks: (B:14:0x0034, B:31:0x007b, B:33:0x0083, B:36:0x008a, B:37:0x008e, B:39:0x0091, B:49:0x00b2, B:52:0x00c2, B:53:0x00dc, B:59:0x00ee, B:56:0x00e5, B:58:0x00eb, B:41:0x0097, B:45:0x009e, B:21:0x004d), top: B:66:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0091 A[Catch: all -> 0x0038, TryCatch #0 {all -> 0x0038, blocks: (B:14:0x0034, B:31:0x007b, B:33:0x0083, B:36:0x008a, B:37:0x008e, B:39:0x0091, B:49:0x00b2, B:52:0x00c2, B:53:0x00dc, B:59:0x00ee, B:56:0x00e5, B:58:0x00eb, B:41:0x0097, B:45:0x009e, B:21:0x004d), top: B:66:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00c2 A[Catch: all -> 0x0038, TryCatch #0 {all -> 0x0038, blocks: (B:14:0x0034, B:31:0x007b, B:33:0x0083, B:36:0x008a, B:37:0x008e, B:39:0x0091, B:49:0x00b2, B:52:0x00c2, B:53:0x00dc, B:59:0x00ee, B:56:0x00e5, B:58:0x00eb, B:41:0x0097, B:45:0x009e, B:21:0x004d), top: B:66:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00f9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10, types: [V8.g0] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v2, types: [W8.d] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6, types: [V8.g0] */
    /* JADX WARN: Type inference failed for: r2v7, types: [V8.g0] */
    /* JADX WARN: Type inference failed for: r2v8, types: [V8.g0] */
    /* JADX WARN: Type inference failed for: r8v1, types: [W8.b] */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4, types: [V8.f0] */
    /* JADX WARN: Type inference failed for: r8v5, types: [V8.f0, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v7, types: [V8.f0] */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:51:0x00c1 -> B:31:0x007b). Please report as a decompilation issue!!! */
    @Override // V8.InterfaceC0817f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(V8.InterfaceC0818g r14, q7.InterfaceC2189c r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: V8.f0.b(V8.g, q7.c):java.lang.Object");
    }

    @Override // W8.AbstractC0842b
    public final AbstractC0844d d() {
        return new g0();
    }

    @Override // W8.AbstractC0842b
    public final AbstractC0844d[] e() {
        return new g0[2];
    }

    @Override // V8.InterfaceC0818g
    public final Object f(Object obj, InterfaceC2189c interfaceC2189c) {
        l(obj);
        return m7.q.f19267a;
    }

    @Override // V8.d0
    public final Object getValue() {
        B2.q qVar = AbstractC0843c.f9911b;
        Object obj = f9621q.get(this);
        if (obj == qVar) {
            return null;
        }
        return obj;
    }

    @Override // V8.K
    public final void h() {
        throw new UnsupportedOperationException("MutableStateFlow.resetReplayCache is not supported");
    }

    @Override // V8.K
    public final boolean j(Object obj) {
        l(obj);
        return true;
    }

    public final boolean k(Object obj, Object obj2) {
        B2.q qVar = AbstractC0843c.f9911b;
        if (obj == null) {
            obj = qVar;
        }
        if (obj2 == null) {
            obj2 = qVar;
        }
        return m(obj, obj2);
    }

    public final void l(Object obj) {
        if (obj == null) {
            obj = AbstractC0843c.f9911b;
        }
        m(null, obj);
    }

    public final boolean m(Object obj, Object obj2) {
        int i7;
        AbstractC0844d[] abstractC0844dArr;
        B2.q qVar;
        synchronized (this) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f9621q;
            Object obj3 = atomicReferenceFieldUpdater.get(this);
            if (obj != null && !A7.m.a(obj3, obj)) {
                return false;
            }
            if (A7.m.a(obj3, obj2)) {
                return true;
            }
            atomicReferenceFieldUpdater.set(this, obj2);
            int i10 = this.f9622p;
            if ((i10 & 1) != 0) {
                this.f9622p = i10 + 2;
                return true;
            }
            int i11 = i10 + 1;
            this.f9622p = i11;
            AbstractC0844d[] abstractC0844dArr2 = this.l;
            while (true) {
                g0[] g0VarArr = (g0[]) abstractC0844dArr2;
                if (g0VarArr != null) {
                    for (g0 g0Var : g0VarArr) {
                        if (g0Var != null) {
                            AtomicReference atomicReference = g0Var.f9623a;
                            while (true) {
                                Object obj4 = atomicReference.get();
                                if (obj4 != null && obj4 != (qVar = AbstractC0828q.d)) {
                                    B2.q qVar2 = AbstractC0828q.c;
                                    if (obj4 != qVar2) {
                                        while (!atomicReference.compareAndSet(obj4, qVar2)) {
                                            if (atomicReference.get() != obj4) {
                                                break;
                                            }
                                        }
                                        ((C0712i) obj4).resumeWith(m7.q.f19267a);
                                        break;
                                    }
                                    while (!atomicReference.compareAndSet(obj4, qVar)) {
                                        if (atomicReference.get() != obj4) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                synchronized (this) {
                    i7 = this.f9622p;
                    if (i7 == i11) {
                        this.f9622p = i11 + 1;
                        return true;
                    }
                    abstractC0844dArr = this.l;
                }
                abstractC0844dArr2 = abstractC0844dArr;
                i11 = i7;
            }
        }
    }
}
