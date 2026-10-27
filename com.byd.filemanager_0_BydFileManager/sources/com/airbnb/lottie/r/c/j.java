package com.airbnb.lottie.r.c;

import android.graphics.PointF;
import java.util.List;

/* JADX INFO: compiled from: PointKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends f<PointF> {
    private final PointF i;

    public j(List<com.airbnb.lottie.v.a<PointF>> list) {
        super(list);
        this.i = new PointF();
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
    public PointF i(com.airbnb.lottie.v.a<PointF> aVar, float f) {
        PointF pointF;
        PointF pointF2;
        PointF pointF3 = aVar.f2818b;
        if (pointF3 == null || (pointF = aVar.f2819c) == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        PointF pointF4 = pointF3;
        PointF pointF5 = pointF;
        com.airbnb.lottie.v.c<A> cVar = this.f2729e;
        if (cVar != 0 && (pointF2 = (PointF) cVar.b(aVar.f2821e, aVar.f.floatValue(), pointF4, pointF5, f, e(), f())) != null) {
            return pointF2;
        }
        PointF pointF6 = this.i;
        float f2 = pointF4.x;
        float f3 = f2 + ((pointF5.x - f2) * f);
        float f4 = pointF4.y;
        pointF6.set(f3, f4 + (f * (pointF5.y - f4)));
        return this.i;
    }
}
