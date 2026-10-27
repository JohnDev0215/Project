package com.airbnb.lottie.r.c;

import android.graphics.PointF;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class j extends f<PointF> {
    private final PointF l;

    public j(List<com.airbnb.lottie.x.a<PointF>> list) {
        super(list);
        this.l = new PointF();
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
    public PointF i(com.airbnb.lottie.x.a<PointF> aVar, float f) {
        PointF pointF;
        PointF pointF2;
        PointF pointF3 = aVar.f976b;
        if (pointF3 == null || (pointF = aVar.f977c) == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        PointF pointF4 = pointF3;
        PointF pointF5 = pointF;
        com.airbnb.lottie.x.c<A> cVar = this.e;
        if (cVar != 0 && (pointF2 = (PointF) cVar.b(aVar.e, aVar.f.floatValue(), pointF4, pointF5, f, e(), f())) != null) {
            return pointF2;
        }
        PointF pointF6 = this.l;
        float f2 = pointF4.x;
        float f3 = f2 + ((pointF5.x - f2) * f);
        float f4 = pointF4.y;
        pointF6.set(f3, f4 + (f * (pointF5.y - f4)));
        return this.l;
    }
}
