package r1;

import java.util.List;

/* JADX INFO: compiled from: ColorKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends f<Integer> {
    public b(List<b2.a<Integer>> list) {
        super(list);
    }

    public int o() {
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
    public int p(b2.a<Integer> aVar, float f8) {
        Integer num;
        Integer num2 = aVar.f2045b;
        if (num2 == null || aVar.f2046c == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        int iIntValue = num2.intValue();
        int iIntValue2 = aVar.f2046c.intValue();
        b2.c<A> cVar = this.f7745e;
        return (cVar == 0 || (num = (Integer) cVar.b(aVar.f2048e, aVar.f2049f.floatValue(), Integer.valueOf(iIntValue), Integer.valueOf(iIntValue2), f8, e(), f())) == null) ? a2.b.c(a2.g.b(f8, 0.0f, 1.0f), iIntValue, iIntValue2) : num.intValue();
    }

    @Override // r1.a
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public Integer i(b2.a<Integer> aVar, float f8) {
        return Integer.valueOf(p(aVar, f8));
    }
}
