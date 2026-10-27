package A;

import q7.InterfaceC2189c;
import y0.C2730h;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class D extends s7.h implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C2730h f9m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f10n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public /* synthetic */ Object f11p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ A7.z f12q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ A7.z f13r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public D(A7.z zVar, A7.z zVar2, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f12q = zVar;
        this.f13r = zVar2;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        D d = new D(this.f12q, this.f13r, interfaceC2189c);
        d.f11p = obj;
        return d;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((D) create((y0.z) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0092, code lost:
    
        r2 = r3;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00c3 A[EDGE_INSN: B:66:0x00c3->B:41:0x00c3 BREAK  A[LOOP:0: B:36:0x00b0->B:40:0x00c0], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0069 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r5v9, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r9v1, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x00a4 -> B:35:0x00a7). Please report as a decompilation issue!!! */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r17) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 294
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: A.D.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
