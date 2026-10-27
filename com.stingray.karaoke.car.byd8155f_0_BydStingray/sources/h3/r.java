package h3;

import B2.L;

/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L f15427a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f15428b;
    public boolean c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f15429e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f15430f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f15431g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f15432h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f15433i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f15434j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f15435k;
    public long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f15436m;

    public r(L l) {
        this.f15427a = l;
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
    public final void a(int i7) {
        long j3 = this.l;
        if (j3 == -9223372036854775807L) {
            return;
        }
        boolean z6 = this.f15436m;
        this.f15427a.b(j3, z6 ? 1 : 0, (int) (this.f15428b - this.f15435k), i7, null);
    }
}
