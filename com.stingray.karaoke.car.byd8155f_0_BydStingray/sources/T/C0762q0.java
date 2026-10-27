package T;

import S8.InterfaceC0725w;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: T.q0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0762q0 extends s7.i implements InterfaceC2785n {
    public G2.a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f8327m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f8328n;
    public final /* synthetic */ C0767t0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C0765s0 f8329p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ T f8330q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0762q0(C0767t0 c0767t0, C0765s0 c0765s0, T t10, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = c0767t0;
        this.f8329p = c0765s0;
        this.f8330q = t10;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0762q0 c0762q0 = new C0762q0(this.o, this.f8329p, this.f8330q, interfaceC2189c);
        c0762q0.f8328n = obj;
        return c0762q0;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0762q0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0116 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x013c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.lang.Object, java.util.Collection] */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 365
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C0762q0.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
