package P5;

import q7.InterfaceC2189c;
import y0.C2730h;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class e extends s7.h implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C2730h f5349m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f5350n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public /* synthetic */ Object f5351p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ A7.z f5352q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ A7.z f5353r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(A7.z zVar, A7.z zVar2, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f5352q = zVar;
        this.f5353r = zVar2;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        e eVar = new e(this.f5352q, this.f5353r, interfaceC2189c);
        eVar.f5351p = obj;
        return eVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((e) create((y0.z) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0092, code lost:
    
        r2 = 1;
     */
    /* JADX WARN: Path cross not found for [B:50:0x00f3, B:53:0x00f9], limit reached: 86 */
    /* JADX WARN: Removed duplicated region for block: B:12:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00c3 A[EDGE_INSN: B:78:0x00c3->B:41:0x00c3 BREAK  A[LOOP:0: B:36:0x00b0->B:40:0x00c0], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00ec A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0069 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r5v9, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.lang.Object, java.util.List] */
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
            Method dump skipped, instruction units count: 339
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: P5.e.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
