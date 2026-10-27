package h3;

import B2.C0076p;
import B2.L;
import B2.N;
import e2.C1207o;
import e2.C1208p;
import java.util.Arrays;

/* JADX INFO: renamed from: h3.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1355f implements InterfaceC1358i {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final byte[] f15318w = {73, 68, 51};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f15319a;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f15321e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f15322f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public L f15323g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public L f15324h;
    public boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f15328m;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f15330p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f15331q;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f15333s;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public L f15335u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public long f15336v;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final N f15320b = new N(7, new byte[7]);
    public final h2.t c = new h2.t(Arrays.copyOf(f15318w, 10));

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f15325i = 0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f15326j = 0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f15327k = 256;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f15329n = -1;
    public int o = -1;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public long f15332r = -9223372036854775807L;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f15334t = -9223372036854775807L;

    public C1355f(String str, int i7, boolean z6) {
        this.f15319a = z6;
        this.d = str;
        this.f15321e = i7;
    }

    @Override // h3.InterfaceC1358i
    public final void b() {
        this.f15334t = -9223372036854775807L;
        this.f15328m = false;
        this.f15325i = 0;
        this.f15326j = 0;
        this.f15327k = 256;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:121:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0308  */
    /* JADX WARN: Type inference failed for: r24v0, types: [h2.t] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [int] */
    /* JADX WARN: Type inference failed for: r2v47 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
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
    public final void c(h2.t r24) throws e2.M {
        /*
            Method dump skipped, instruction units count: 810
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h3.C1355f.c(h2.t):void");
    }

    @Override // h3.InterfaceC1358i
    public final void d(boolean z6) {
    }

    @Override // h3.InterfaceC1358i
    public final void e(long j3, int i7) {
        this.f15334t = j3;
    }

    @Override // h3.InterfaceC1358i
    public final void f(B2.t tVar, H h10) {
        h10.a();
        h10.b();
        this.f15322f = h10.f15293e;
        h10.b();
        L lU = tVar.u(h10.d, 1);
        this.f15323g = lU;
        this.f15335u = lU;
        if (!this.f15319a) {
            this.f15324h = new C0076p();
            return;
        }
        h10.a();
        h10.b();
        L lU2 = tVar.u(h10.d, 5);
        this.f15324h = lU2;
        C1207o c1207o = new C1207o();
        h10.b();
        c1207o.f14237a = h10.f15293e;
        c1207o.f14246m = e2.L.l("application/id3");
        lU2.d(new C1208p(c1207o));
    }
}
