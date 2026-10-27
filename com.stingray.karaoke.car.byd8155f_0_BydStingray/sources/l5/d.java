package L5;

import S8.InterfaceC0725w;
import m7.q;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class d extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ i l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f4020m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ F6.b f4021n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(i iVar, String str, F6.b bVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = iVar;
        this.f4020m = str;
        this.f4021n = bVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new d(this.l, this.f4020m, this.f4021n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        d dVar = (d) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        q qVar = q.f19267a;
        dVar.invokeSuspend(qVar);
        return qVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:216:0x05cb  */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r30) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1846
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: L5.d.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
