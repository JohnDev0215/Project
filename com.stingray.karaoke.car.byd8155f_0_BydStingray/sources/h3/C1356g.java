package h3;

import B2.C0061a;
import B2.L;
import e2.C1207o;
import e2.C1208p;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: h3.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1356g implements InterfaceC1358i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h2.t f15337a;
    public final String c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f15339e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public L f15340f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f15342h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f15343i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long f15344j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public C1208p f15345k;
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f15346m;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f15341g = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f15348p = -9223372036854775807L;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AtomicInteger f15338b = new AtomicInteger();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f15347n = -1;
    public int o = -1;

    public C1356g(String str, int i7, int i10) {
        this.f15337a = new h2.t(new byte[i10]);
        this.c = str;
        this.d = i7;
    }

    public final boolean a(h2.t tVar, byte[] bArr, int i7) {
        int iMin = Math.min(tVar.a(), i7 - this.f15342h);
        tVar.f(this.f15342h, iMin, bArr);
        int i10 = this.f15342h + iMin;
        this.f15342h = i10;
        return i10 == i7;
    }

    @Override // h3.InterfaceC1358i
    public final void b() {
        this.f15341g = 0;
        this.f15342h = 0;
        this.f15343i = 0;
        this.f15348p = -9223372036854775807L;
        this.f15338b.set(0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:181:0x046d  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0476  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x04a0  */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r15v0, types: [B2.L] */
    /* JADX WARN: Type inference failed for: r18v1 */
    /* JADX WARN: Type inference failed for: r18v2, types: [int] */
    /* JADX WARN: Type inference failed for: r18v3 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
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
    public final void c(h2.t r38) throws e2.M {
        /*
            Method dump skipped, instruction units count: 1402
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h3.C1356g.c(h2.t):void");
    }

    @Override // h3.InterfaceC1358i
    public final void d(boolean z6) {
    }

    @Override // h3.InterfaceC1358i
    public final void e(long j3, int i7) {
        this.f15348p = j3;
    }

    @Override // h3.InterfaceC1358i
    public final void f(B2.t tVar, H h10) {
        h10.a();
        h10.b();
        this.f15339e = h10.f15293e;
        h10.b();
        this.f15340f = tVar.u(h10.d, 1);
    }

    public final void g(C0061a c0061a) {
        int i7;
        int i10 = c0061a.f529b;
        if (i10 == -2147483647 || (i7 = c0061a.c) == -1) {
            return;
        }
        C1208p c1208p = this.f15345k;
        String str = c0061a.f528a;
        if (c1208p != null && i7 == c1208p.f14295C && i10 == c1208p.f14296D && str.equals(c1208p.f14316n)) {
            return;
        }
        C1208p c1208p2 = this.f15345k;
        C1207o c1207o = c1208p2 == null ? new C1207o() : c1208p2.a();
        c1207o.f14237a = this.f15339e;
        c1207o.f14246m = e2.L.l(str);
        c1207o.B = i7;
        c1207o.f14228C = i10;
        c1207o.d = this.c;
        c1207o.f14240f = this.d;
        C1208p c1208p3 = new C1208p(c1207o);
        this.f15345k = c1208p3;
        this.f15340f.d(c1208p3);
    }
}
