package N1;

import V8.InterfaceC0818g;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: N1.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0444z extends s7.i implements InterfaceC2785n {
    public C0423d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4563m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f4564n;
    public final /* synthetic */ Q o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0444z(Q q4, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = q4;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0444z c0444z = new C0444z(this.o, interfaceC2189c);
        c0444z.f4564n = obj;
        return c0444z;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0444z) create((InterfaceC0818g) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00bf  */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 209
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: N1.C0444z.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
