package com.airbnb.lottie.r.c;

import java.util.List;

/* JADX INFO: compiled from: ScaleKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends f<com.airbnb.lottie.v.d> {
    private final com.airbnb.lottie.v.d i;

    public k(List<com.airbnb.lottie.v.a<com.airbnb.lottie.v.d>> list) {
        super(list);
        this.i = new com.airbnb.lottie.v.d();
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
    @Override // com.airbnb.lottie.r.c.a
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public com.airbnb.lottie.v.d i(com.airbnb.lottie.v.a<com.airbnb.lottie.v.d> aVar, float f) {
        com.airbnb.lottie.v.d dVar;
        com.airbnb.lottie.v.d dVar2;
        com.airbnb.lottie.v.d dVar3 = aVar.f2818b;
        if (dVar3 == null || (dVar = aVar.f2819c) == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        com.airbnb.lottie.v.d dVar4 = dVar3;
        com.airbnb.lottie.v.d dVar5 = dVar;
        com.airbnb.lottie.v.c<A> cVar = this.f2729e;
        if (cVar != 0 && (dVar2 = (com.airbnb.lottie.v.d) cVar.b(aVar.f2821e, aVar.f.floatValue(), dVar4, dVar5, f, e(), f())) != null) {
            return dVar2;
        }
        this.i.d(com.airbnb.lottie.u.g.j(dVar4.b(), dVar5.b(), f), com.airbnb.lottie.u.g.j(dVar4.c(), dVar5.c(), f));
        return this.i;
    }
}
