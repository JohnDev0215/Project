package L;

import q7.InterfaceC2189c;
import s7.h;
import y0.EnumC2731i;
import y0.q;
import y0.z;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class a extends h implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public q f3863m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public EnumC2731i f3864n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public /* synthetic */ Object f3865p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ d f3866q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(d dVar, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f3866q = dVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        a aVar = new a(this.f3866q, interfaceC2189c);
        aVar.f3865p = obj;
        return aVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((a) create((z) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Path cross not found for [B:58:0x0117, B:56:0x0106], limit reached: 90 */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0158 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x018a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0185 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00fe A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r16v0 */
    /* JADX WARN: Type inference failed for: r6v22, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r8v11, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r8v12, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x00c6 -> B:41:0x00c9). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:67:0x0156 -> B:69:0x0159). Please report as a decompilation issue!!! */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 405
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: L.a.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
