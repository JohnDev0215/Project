package spi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import spi.m5;
import spi.n5;
import spi.ti;

/* JADX INFO: loaded from: classes.dex */
public final class wr implements m5.a<Object> {
    public final /* synthetic */ ti.a a;
    public final /* synthetic */ xr b;

    public wr(xr xrVar, ti.a aVar) {
        this.b = xrVar;
        this.a = aVar;
    }

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
    @Override // spi.m5.a
    public final void c(@NonNull Exception exc) {
        xr xrVar = this.b;
        ti.a<?> aVar = this.a;
        ti.a<?> aVar2 = xrVar.f;
        if (aVar2 != null && aVar2 == aVar) {
            xr xrVar2 = this.b;
            ti.a aVar3 = this.a;
            n5.a aVar4 = xrVar2.b;
            ig igVar = xrVar2.g;
            m5<Data> m5Var = aVar3.c;
            aVar4.c(igVar, exc, m5Var, m5Var.e());
        }
    }

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
    @Override // spi.m5.a
    public final void f(@Nullable Object obj) {
        xr xrVar = this.b;
        ti.a<?> aVar = this.a;
        ti.a<?> aVar2 = xrVar.f;
        if (aVar2 != null && aVar2 == aVar) {
            xr xrVar2 = this.b;
            ti.a aVar3 = this.a;
            i6 i6Var = xrVar2.a.p;
            if (obj != null && i6Var.c(aVar3.c.e())) {
                xrVar2.e = obj;
                xrVar2.b.d();
            } else {
                n5.a aVar4 = xrVar2.b;
                ig igVar = aVar3.a;
                m5<Data> m5Var = aVar3.c;
                aVar4.a(igVar, obj, m5Var, m5Var.e(), xrVar2.g);
            }
        }
    }
}
