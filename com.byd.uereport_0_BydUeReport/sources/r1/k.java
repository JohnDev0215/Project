package r1;

import java.util.List;

/* JADX INFO: compiled from: ScaleKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends f<b2.d> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final b2.d f7766i;

    public k(List<b2.a<b2.d>> list) {
        super(list);
        this.f7766i = new b2.d();
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
    @Override // r1.a
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public b2.d i(b2.a<b2.d> aVar, float f8) {
        b2.d dVar;
        b2.d dVar2;
        b2.d dVar3 = aVar.f2045b;
        if (dVar3 == null || (dVar = aVar.f2046c) == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        b2.d dVar4 = dVar3;
        b2.d dVar5 = dVar;
        b2.c<A> cVar = this.f7745e;
        if (cVar != 0 && (dVar2 = (b2.d) cVar.b(aVar.f2048e, aVar.f2049f.floatValue(), dVar4, dVar5, f8, e(), f())) != null) {
            return dVar2;
        }
        this.f7766i.d(a2.g.j(dVar4.b(), dVar5.b(), f8), a2.g.j(dVar4.c(), dVar5.c(), f8));
        return this.f7766i;
    }
}
