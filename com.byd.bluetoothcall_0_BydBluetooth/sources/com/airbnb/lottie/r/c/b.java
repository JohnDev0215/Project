package com.airbnb.lottie.r.c;

import java.util.List;

/* JADX INFO: compiled from: ColorKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends f<Integer> {
    public b(List<com.airbnb.lottie.v.a<Integer>> list) {
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
    public int o(com.airbnb.lottie.v.a<Integer> aVar, float f) {
        Integer num;
        Integer num2 = aVar.f3198b;
        if (num2 == null || aVar.f3199c == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        int iIntValue = num2.intValue();
        int iIntValue2 = aVar.f3199c.intValue();
        com.airbnb.lottie.v.c<A> cVar = this.f3115e;
        return (cVar == 0 || (num = (Integer) cVar.b(aVar.f3201e, aVar.f.floatValue(), Integer.valueOf(iIntValue), Integer.valueOf(iIntValue2), f, e(), f())) == null) ? com.airbnb.lottie.u.b.c(com.airbnb.lottie.u.g.b(f, 0.0f, 1.0f), iIntValue, iIntValue2) : num.intValue();
    }

    @Override // com.airbnb.lottie.r.c.a
    /* JADX INFO: renamed from: p */
    public Integer i(com.airbnb.lottie.v.a<Integer> aVar, float f) {
        return Integer.valueOf(o(aVar, f));
    }
}
