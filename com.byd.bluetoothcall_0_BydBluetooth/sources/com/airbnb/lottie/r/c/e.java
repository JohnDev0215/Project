package com.airbnb.lottie.r.c;

import java.util.List;

/* JADX INFO: compiled from: IntegerKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends f<Integer> {
    public e(List<com.airbnb.lottie.v.a<Integer>> list) {
        super(list);
    }

    public int n() {
        return o(b(), d());
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
    int o(com.airbnb.lottie.v.a<Integer> aVar, float f) {
        Integer num;
        if (aVar.f3198b == null || aVar.f3199c == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        com.airbnb.lottie.v.c<A> cVar = this.f3115e;
        return (cVar == 0 || (num = (Integer) cVar.b(aVar.f3201e, aVar.f.floatValue(), aVar.f3198b, aVar.f3199c, f, e(), f())) == null) ? com.airbnb.lottie.u.g.k(aVar.g(), aVar.d(), f) : num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.airbnb.lottie.r.c.a
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public Integer i(com.airbnb.lottie.v.a<Integer> aVar, float f) {
        return Integer.valueOf(o(aVar, f));
    }
}
