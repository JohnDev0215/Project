package H;

import A7.o;
import E0.d0;
import S8.InterfaceC0725w;
import m7.q;
import q7.InterfaceC2189c;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class f extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ i f2754m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ d0 f2755n;
    public final /* synthetic */ o o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public f(i iVar, d0 d0Var, InterfaceC2772a interfaceC2772a, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f2754m = iVar;
        this.f2755n = d0Var;
        this.o = (o) interfaceC2772a;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [A7.o, z7.a] */
    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new f(this.f2754m, this.f2755n, this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((f) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d4 A[RETURN] */
    /* JADX WARN: Type inference failed for: r6v0, types: [A7.o, z7.a] */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 214
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: H.f.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
