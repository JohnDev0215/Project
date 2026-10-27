package Q;

import S8.InterfaceC0725w;
import q7.InterfaceC2189c;
import x.C2629d;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0506g extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C2629d f5885m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f5886n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C0508h f5887p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ C.j f5888q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0506g(C2629d c2629d, float f10, boolean z6, C0508h c0508h, C.j jVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f5885m = c2629d;
        this.f5886n = f10;
        this.o = z6;
        this.f5887p = c0508h;
        this.f5888q = jVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0506g(this.f5885m, this.f5886n, this.o, this.f5887p, this.f5888q, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0506g) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x008d A[PHI: r3
  0x008d: PHI (r3v2 x.C0) = (r3v1 x.C0), (r3v1 x.C0), (r3v1 x.C0), (r3v3 x.C0), (r3v3 x.C0), (r3v3 x.C0), (r3v3 x.C0) binds: [B:44:0x00a6, B:47:0x00ab, B:53:0x00b7, B:30:0x008b, B:33:0x0091, B:36:0x0096, B:39:0x009b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00dd A[RETURN] */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: Q.C0506g.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
