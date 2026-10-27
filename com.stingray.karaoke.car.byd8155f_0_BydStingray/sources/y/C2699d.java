package y;

import A.C0023k0;
import S8.InterfaceC0725w;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: y.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2699d extends s7.i implements InterfaceC2785n {
    public boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f23892m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f23893n;
    public final /* synthetic */ C0023k0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ long f23894p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ C.k f23895q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ AbstractC2705j f23896r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2699d(C0023k0 c0023k0, long j3, C.k kVar, AbstractC2705j abstractC2705j, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = c0023k0;
        this.f23894p = j3;
        this.f23895q = kVar;
        this.f23896r = abstractC2705j;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C2699d c2699d = new C2699d(this.o, this.f23894p, this.f23895q, this.f23896r, interfaceC2189c);
        c2699d.f23893n = obj;
        return c2699d;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C2699d) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ac A[RETURN] */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 207
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: y.C2699d.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
