package com.airbnb.lottie.r.c;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class k extends f<com.airbnb.lottie.x.d> {
    private final com.airbnb.lottie.x.d l;

    public k(List<com.airbnb.lottie.x.a<com.airbnb.lottie.x.d>> list) {
        super(list);
        this.l = new com.airbnb.lottie.x.d();
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
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public com.airbnb.lottie.x.d i(com.airbnb.lottie.x.a<com.airbnb.lottie.x.d> aVar, float f) {
        com.airbnb.lottie.x.d dVar;
        com.airbnb.lottie.x.d dVar2;
        com.airbnb.lottie.x.d dVar3 = aVar.f976b;
        if (dVar3 == null || (dVar = aVar.f977c) == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        com.airbnb.lottie.x.d dVar4 = dVar3;
        com.airbnb.lottie.x.d dVar5 = dVar;
        com.airbnb.lottie.x.c<A> cVar = this.e;
        if (cVar != 0 && (dVar2 = (com.airbnb.lottie.x.d) cVar.b(aVar.e, aVar.f.floatValue(), dVar4, dVar5, f, e(), f())) != null) {
            return dVar2;
        }
        this.l.d(com.airbnb.lottie.w.g.j(dVar4.b(), dVar5.b(), f), com.airbnb.lottie.w.g.j(dVar4.c(), dVar5.c(), f));
        return this.l;
    }
}
