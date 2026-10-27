package h3;

import B2.L;
import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final class k implements InterfaceC1358i {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final double[] f15357q = {23.976023976023978d, 24.0d, 25.0d, 29.97002997002997d, 30.0d, 50.0d, 59.94005994005994d, 60.0d};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f15358a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public L f15359b;
    public final M.t c;
    public final h2.t d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final x f15360e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean[] f15361f = new boolean[4];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final j f15362g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f15363h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f15364i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f15365j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f15366k;
    public long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f15367m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f15368n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f15369p;

    public k(M.t tVar) {
        this.c = tVar;
        j jVar = new j();
        jVar.d = new byte[128];
        this.f15362g = jVar;
        if (tVar != null) {
            this.f15360e = new x(178);
            this.d = new h2.t();
        } else {
            this.f15360e = null;
            this.d = null;
        }
        this.l = -9223372036854775807L;
        this.f15368n = -9223372036854775807L;
    }

    @Override // h3.InterfaceC1358i
    public final void b() {
        i2.n.a(this.f15361f);
        j jVar = this.f15362g;
        jVar.f15355a = false;
        jVar.f15356b = 0;
        jVar.c = 0;
        x xVar = this.f15360e;
        if (xVar != null) {
            xVar.g();
        }
        this.f15363h = 0L;
        this.f15364i = false;
        this.l = -9223372036854775807L;
        this.f15368n = -9223372036854775807L;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0194  */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [int] */
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
    public final void c(h2.t r27) {
        /*
            Method dump skipped, instruction units count: 530
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h3.k.c(h2.t):void");
    }

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
    public final void d(boolean z6) {
        AbstractC1346c.k(this.f15359b);
        if (z6) {
            boolean z10 = this.o;
            this.f15359b.b(this.f15368n, z10 ? 1 : 0, (int) (this.f15363h - this.f15367m), 0, null);
        }
    }

    @Override // h3.InterfaceC1358i
    public final void e(long j3, int i7) {
        this.l = j3;
    }

    @Override // h3.InterfaceC1358i
    public final void f(B2.t tVar, H h10) {
        h10.a();
        h10.b();
        this.f15358a = h10.f15293e;
        h10.b();
        this.f15359b = tVar.u(h10.d, 2);
        M.t tVar2 = this.c;
        if (tVar2 != null) {
            tVar2.y(tVar, h10);
        }
    }
}
