package h3;

import B2.L;
import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L f15374a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f15375b;
    public boolean c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f15376e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f15377f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f15378g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f15379h;

    public m(L l) {
        this.f15374a = l;
    }

    public final void a(int i7, int i10, byte[] bArr) {
        if (this.c) {
            int i11 = this.f15377f;
            int i12 = (i7 + 1) - i11;
            if (i12 >= i10) {
                this.f15377f = (i10 - i7) + i11;
            } else {
                this.d = ((bArr[i12] & 192) >> 6) == 0;
                this.c = false;
            }
        }
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
    public final void b(int i7, long j3, boolean z6) {
        AbstractC1346c.j(this.f15379h != -9223372036854775807L);
        if (this.f15376e == 182 && z6 && this.f15375b) {
            this.f15374a.b(this.f15379h, this.d ? 1 : 0, (int) (j3 - this.f15378g), i7, null);
        }
        if (this.f15376e != 179) {
            this.f15378g = j3;
        }
    }
}
