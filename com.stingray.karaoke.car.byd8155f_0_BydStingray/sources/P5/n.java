package P5;

import q7.InterfaceC2189c;
import y0.C2722B;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class n extends s7.i implements InterfaceC2785n {
    public z l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f5376m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f5377n;
    public final /* synthetic */ p o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(p pVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = pVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        n nVar = new n(this.o, interfaceC2189c);
        nVar.f5377n = obj;
        return nVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((n) create((C2722B) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x006b  */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 204
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: P5.n.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
