package e0;

import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: e0.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1170j extends s7.h implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int[] f13708m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f13709n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f13710p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public /* synthetic */ Object f13711q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ C1171k f13712r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1170j(C1171k c1171k, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f13712r = c1171k;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C1170j c1170j = new C1170j(this.f13712r, interfaceC2189c);
        c1170j.f13711q = obj;
        return c1170j;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C1170j) create((P8.l) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ac  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0080 -> B:27:0x0098). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x00cd -> B:39:0x00ce). Please report as a decompilation issue!!! */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 212
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: e0.C1170j.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
