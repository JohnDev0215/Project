package N5;

import com.stingray.shared.logs.segment.ActionOwner;
import q7.InterfaceC2189c;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class r extends s7.i implements InterfaceC2782k {
    public long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4756m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f4757n;
    public final /* synthetic */ Z o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ long f4758p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ ActionOwner f4759q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(Z z6, long j3, ActionOwner actionOwner, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.o = z6;
        this.f4758p = j3;
        this.f4759q = actionOwner;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return ((r) create((InterfaceC2189c) obj)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new r(this.o, this.f4758p, this.f4759q, interfaceC2189c);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: N5.r.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
