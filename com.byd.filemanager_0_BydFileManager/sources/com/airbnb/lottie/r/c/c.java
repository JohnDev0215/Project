package com.airbnb.lottie.r.c;

import java.util.List;

/* JADX INFO: compiled from: FloatKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends f<Float> {
    public c(List<com.airbnb.lottie.v.a<Float>> list) {
        super(list);
    }

    public float o() {
        return p(b(), d());
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
    float p(com.airbnb.lottie.v.a<Float> aVar, float f) {
        Float f2;
        if (aVar.f2818b == null || aVar.f2819c == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        com.airbnb.lottie.v.c<A> cVar = this.f2729e;
        return (cVar == 0 || (f2 = (Float) cVar.b(aVar.f2821e, aVar.f.floatValue(), aVar.f2818b, aVar.f2819c, f, e(), f())) == null) ? com.airbnb.lottie.u.g.j(aVar.f(), aVar.c(), f) : f2.floatValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.airbnb.lottie.r.c.a
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public Float i(com.airbnb.lottie.v.a<Float> aVar, float f) {
        return Float.valueOf(p(aVar, f));
    }
}
