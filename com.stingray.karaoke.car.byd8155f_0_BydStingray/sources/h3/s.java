package h3;

import B2.L;
import h2.AbstractC1343A;
import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final class s implements InterfaceC1358i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y f15437a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f15438b;
    public L c;
    public r d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f15439e;
    public long l;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean[] f15440f = new boolean[3];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final x f15441g = new x(32);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final x f15442h = new x(33);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final x f15443i = new x(34);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final x f15444j = new x(39);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final x f15445k = new x(40);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f15446m = -9223372036854775807L;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final h2.t f15447n = new h2.t();

    public s(y yVar) {
        this.f15437a = yVar;
    }

    public final void a(int i7, int i10, byte[] bArr) {
        r rVar = this.d;
        if (rVar.f15430f) {
            int i11 = rVar.d;
            int i12 = (i7 + 2) - i11;
            if (i12 < i10) {
                rVar.f15431g = (bArr[i12] & 128) != 0;
                rVar.f15430f = false;
            } else {
                rVar.d = (i10 - i7) + i11;
            }
        }
        if (!this.f15439e) {
            this.f15441g.a(i7, i10, bArr);
            this.f15442h.a(i7, i10, bArr);
            this.f15443i.a(i7, i10, bArr);
        }
        this.f15444j.a(i7, i10, bArr);
        this.f15445k.a(i7, i10, bArr);
    }

    @Override // h3.InterfaceC1358i
    public final void b() {
        this.l = 0L;
        this.f15446m = -9223372036854775807L;
        i2.n.a(this.f15440f);
        this.f15441g.g();
        this.f15442h.g();
        this.f15443i.g();
        this.f15444j.g();
        this.f15445k.g();
        ((i2.q) this.f15437a.o).c(0);
        r rVar = this.d;
        if (rVar != null) {
            rVar.f15430f = false;
            rVar.f15431g = false;
            rVar.f15432h = false;
            rVar.f15433i = false;
            rVar.f15434j = false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:106:0x025a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x024b  */
    /* JADX WARN: Type inference failed for: r11v2, types: [h3.x] */
    /* JADX WARN: Type inference failed for: r1v3, types: [h3.x] */
    /* JADX WARN: Type inference failed for: r1v4, types: [h3.x] */
    /* JADX WARN: Type inference failed for: r27v0 */
    /* JADX WARN: Type inference failed for: r27v1 */
    /* JADX WARN: Type inference failed for: r27v2 */
    /* JADX WARN: Type inference failed for: r27v3 */
    /* JADX WARN: Type inference failed for: r27v4 */
    /* JADX WARN: Type inference failed for: r27v5 */
    /* JADX WARN: Type inference failed for: r28v0 */
    /* JADX WARN: Type inference failed for: r28v1 */
    /* JADX WARN: Type inference failed for: r28v2 */
    /* JADX WARN: Type inference failed for: r28v3 */
    /* JADX WARN: Type inference failed for: r28v4 */
    /* JADX WARN: Type inference failed for: r28v5 */
    /* JADX WARN: Type inference failed for: r29v0 */
    /* JADX WARN: Type inference failed for: r29v1 */
    /* JADX WARN: Type inference failed for: r29v2 */
    /* JADX WARN: Type inference failed for: r29v3 */
    /* JADX WARN: Type inference failed for: r29v4 */
    /* JADX WARN: Type inference failed for: r29v5 */
    /* JADX WARN: Type inference failed for: r2v3, types: [h3.x] */
    /* JADX WARN: Type inference failed for: r2v5, types: [h3.x] */
    /* JADX WARN: Type inference failed for: r3v13, types: [h3.x] */
    /* JADX WARN: Type inference failed for: r3v14, types: [h3.x] */
    /* JADX WARN: Type inference failed for: r3v15, types: [h3.x] */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6, types: [int] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // h3.InterfaceC1358i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(h2.t r38) {
        /*
            Method dump skipped, instruction units count: 624
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h3.s.c(h2.t):void");
    }

    @Override // h3.InterfaceC1358i
    public final void d(boolean z6) {
        AbstractC1346c.k(this.c);
        int i7 = AbstractC1343A.f15195a;
        if (z6) {
            ((i2.q) this.f15437a.o).c(0);
            r rVar = this.d;
            long j3 = this.l;
            rVar.f15436m = rVar.c;
            rVar.a((int) (j3 - rVar.f15428b));
            rVar.f15435k = rVar.f15428b;
            rVar.f15428b = j3;
            rVar.a(0);
            rVar.f15433i = false;
        }
    }

    @Override // h3.InterfaceC1358i
    public final void e(long j3, int i7) {
        this.f15446m = j3;
    }

    @Override // h3.InterfaceC1358i
    public final void f(B2.t tVar, H h10) {
        h10.a();
        h10.b();
        this.f15438b = h10.f15293e;
        h10.b();
        L lU = tVar.u(h10.d, 2);
        this.c = lU;
        this.d = new r(lU);
        this.f15437a.h(tVar, h10);
    }
}
