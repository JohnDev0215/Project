package kotlinx.coroutines;

/* JADX INFO: compiled from: Yield.kt */
/* JADX INFO: loaded from: classes.dex */
public final class m2 {
    public static final void a(k5.g gVar) {
        l1 l1Var = (l1) gVar.get(l1.f7703d);
        if (l1Var != null && !l1Var.isActive()) {
            throw l1Var.getCancellationException();
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
    public static final Object b(k5.d<? super g5.r> dVar) {
        Object objD;
        k5.g context = dVar.getContext();
        a(context);
        k5.d dVarC = l5.c.c(dVar);
        if (!(dVarC instanceof kotlinx.coroutines.internal.h)) {
            dVarC = null;
        }
        kotlinx.coroutines.internal.h hVar = (kotlinx.coroutines.internal.h) dVarC;
        if (hVar != null) {
            if (hVar.f7644l.c0(context)) {
                hVar.m(context, g5.r.f6563a);
            } else {
                l2 l2Var = new l2();
                k5.g gVarPlus = context.plus(l2Var);
                objD = g5.r.f6563a;
                hVar.m(gVarPlus, objD);
                if (!l2Var.f7706e || kotlinx.coroutines.internal.i.d(hVar)) {
                    objD = l5.d.d();
                }
            }
            objD = l5.d.d();
        } else {
            objD = g5.r.f6563a;
        }
        if (objD == l5.d.d()) {
            kotlin.coroutines.jvm.internal.h.c(dVar);
        }
        return objD == l5.d.d() ? objD : g5.r.f6563a;
    }
}
