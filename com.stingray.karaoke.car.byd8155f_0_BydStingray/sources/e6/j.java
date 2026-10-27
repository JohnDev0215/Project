package e6;

import S8.InterfaceC0725w;
import m7.q;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class j extends s7.i implements InterfaceC2785n {
    public k l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f14454m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f14455n;
    public final /* synthetic */ k o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ long f14456p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ String f14457q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ boolean f14458r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(k kVar, long j3, String str, boolean z6, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = kVar;
        this.f14456p = j3;
        this.f14457q = str;
        this.f14458r = z6;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new j(this.o, this.f14456p, this.f14457q, this.f14458r, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((j) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0081 A[Catch: all -> 0x012d, CancellationException -> 0x013e, TryCatch #2 {CancellationException -> 0x013e, all -> 0x012d, blocks: (B:8:0x0018, B:61:0x00ff, B:12:0x0025, B:24:0x0070, B:25:0x007b, B:27:0x0081, B:29:0x008c, B:32:0x0093, B:33:0x0097, B:34:0x0099, B:36:0x00a6, B:37:0x00a8, B:39:0x00b7, B:41:0x00bb, B:42:0x00bf, B:44:0x00c5, B:46:0x00cf, B:49:0x00d6, B:51:0x00da, B:53:0x00e2, B:55:0x00ea, B:57:0x00ee, B:21:0x004c), top: B:68:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00bb A[Catch: all -> 0x012d, CancellationException -> 0x013e, TryCatch #2 {CancellationException -> 0x013e, all -> 0x012d, blocks: (B:8:0x0018, B:61:0x00ff, B:12:0x0025, B:24:0x0070, B:25:0x007b, B:27:0x0081, B:29:0x008c, B:32:0x0093, B:33:0x0097, B:34:0x0099, B:36:0x00a6, B:37:0x00a8, B:39:0x00b7, B:41:0x00bb, B:42:0x00bf, B:44:0x00c5, B:46:0x00cf, B:49:0x00d6, B:51:0x00da, B:53:0x00e2, B:55:0x00ea, B:57:0x00ee, B:21:0x004c), top: B:68:0x000c }] */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 319
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: e6.j.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
