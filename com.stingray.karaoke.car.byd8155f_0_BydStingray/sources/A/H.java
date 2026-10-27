package A;

import q7.InterfaceC2189c;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class H extends s7.h implements InterfaceC2785n {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final /* synthetic */ A7.o f26A;
    public final /* synthetic */ A7.o B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final /* synthetic */ A7.o f27C;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f28m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f29n;
    public Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public A7.y f30p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public s1 f31q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public y0.q f32r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f33s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f34t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f35u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public /* synthetic */ Object f36v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ A7.o f37w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final /* synthetic */ A7.y f38x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ EnumC0017h0 f39y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final /* synthetic */ A7.o f40z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public H(InterfaceC2772a interfaceC2772a, A7.y yVar, EnumC0017h0 enumC0017h0, InterfaceC2786o interfaceC2786o, InterfaceC2785n interfaceC2785n, InterfaceC2772a interfaceC2772a2, InterfaceC2782k interfaceC2782k, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f37w = (A7.o) interfaceC2772a;
        this.f38x = yVar;
        this.f39y = enumC0017h0;
        this.f40z = (A7.o) interfaceC2786o;
        this.f26A = (A7.o) interfaceC2785n;
        this.B = (A7.o) interfaceC2772a2;
        this.f27C = (A7.o) interfaceC2782k;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [A7.o, z7.a] */
    /* JADX WARN: Type inference failed for: r4v0, types: [A7.o, z7.o] */
    /* JADX WARN: Type inference failed for: r5v0, types: [A7.o, z7.n] */
    /* JADX WARN: Type inference failed for: r6v0, types: [A7.o, z7.a] */
    /* JADX WARN: Type inference failed for: r7v0, types: [A7.o, z7.k] */
    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        ?? r62 = this.B;
        ?? r72 = this.f27C;
        H h10 = new H(this.f37w, this.f38x, this.f39y, this.f40z, this.f26A, r62, r72, interfaceC2189c);
        h10.f36v = obj;
        return h10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((H) create((y0.z) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:154:0x03cd, code lost:
    
        if ((r1 != null ? r1 == A.EnumC0017h0.l ? m0.c.e(r7) : m0.c.d(r7) : m0.c.c(r7)) == 0.0f) goto L155;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Path cross not found for [B:48:0x0199, B:50:0x01a3], limit reached: 172 */
    /* JADX WARN: Path cross not found for [B:53:0x01aa, B:48:0x0199], limit reached: 172 */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x02fe  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x034d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0384  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0387  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x03d6  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x03e5  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x03e8  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x03f4  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0379 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:176:0x018d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0159 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01d6  */
    /* JADX WARN: Type inference failed for: r0v3, types: [A7.o, z7.n] */
    /* JADX WARN: Type inference failed for: r0v41, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r0v53, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v54 */
    /* JADX WARN: Type inference failed for: r0v56, types: [A7.o, z7.k] */
    /* JADX WARN: Type inference failed for: r0v57, types: [A7.o, z7.a] */
    /* JADX WARN: Type inference failed for: r0v58, types: [z7.n] */
    /* JADX WARN: Type inference failed for: r0v61 */
    /* JADX WARN: Type inference failed for: r0v62 */
    /* JADX WARN: Type inference failed for: r0v63 */
    /* JADX WARN: Type inference failed for: r0v64 */
    /* JADX WARN: Type inference failed for: r0v65 */
    /* JADX WARN: Type inference failed for: r0v66 */
    /* JADX WARN: Type inference failed for: r10v14, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r15v0, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r1v2, types: [A7.o, z7.o] */
    /* JADX WARN: Type inference failed for: r2v4, types: [A7.o, z7.a] */
    /* JADX WARN: Type inference failed for: r6v12, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:102:0x02c7 -> B:104:0x02ca). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:120:0x034b -> B:122:0x034e). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x0109 -> B:107:0x02d3). Please report as a decompilation issue!!! */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r25) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1041
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: A.H.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
