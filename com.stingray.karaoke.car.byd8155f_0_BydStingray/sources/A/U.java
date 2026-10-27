package A;

import S8.InterfaceC0725w;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class U extends s7.i implements InterfaceC2785n {
    public A7.z l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public A7.z f114m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f115n;
    public /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ V f116p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public U(V v10, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f116p = v10;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        U u4 = new U(this.f116p, interfaceC2189c);
        u4.o = obj;
        return u4;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((U) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:34|35|(1:37)|14|57|38|(2:44|(2:46|(1:48)))(2:40|(1:42))) */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00c5, code lost:
    
        r1 = r5;
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00a9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b0 A[Catch: CancellationException -> 0x00c5, TryCatch #1 {CancellationException -> 0x00c5, blocks: (B:38:0x00aa, B:40:0x00b0, B:44:0x00c7, B:46:0x00cb), top: B:57:0x00aa }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c7 A[Catch: CancellationException -> 0x00c5, TryCatch #1 {CancellationException -> 0x00c5, blocks: (B:38:0x00aa, B:40:0x00b0, B:44:0x00c7, B:46:0x00cb), top: B:57:0x00aa }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00e6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00e7  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x0081 -> B:19:0x0056). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x00c2 -> B:19:0x0056). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x00c9 -> B:19:0x0056). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x00d6 -> B:19:0x0056). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:50:0x00e4 -> B:11:0x0027). Please report as a decompilation issue!!! */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 252
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: A.U.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
