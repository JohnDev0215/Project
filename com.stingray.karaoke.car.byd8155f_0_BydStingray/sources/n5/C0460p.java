package N5;

import com.stingray.shared.logs.segment.ActionOwner;
import q7.InterfaceC2189c;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: N5.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0460p extends s7.i implements InterfaceC2782k {
    public A7.v l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4730m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Z f4731n;
    public final /* synthetic */ ActionOwner o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0460p(Z z6, ActionOwner actionOwner, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.f4731n = z6;
        this.o = actionOwner;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return ((C0460p) create((InterfaceC2189c) obj)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new C0460p(this.f4731n, this.o, interfaceC2189c);
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00ea A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0179 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01f2  */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 502
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: N5.C0460p.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
