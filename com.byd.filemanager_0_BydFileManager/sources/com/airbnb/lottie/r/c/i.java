package com.airbnb.lottie.r.c;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import java.util.List;

/* JADX INFO: compiled from: PathKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends f<PointF> {
    private final PointF i;
    private final float[] j;
    private h k;
    private PathMeasure l;

    public i(List<? extends com.airbnb.lottie.v.a<PointF>> list) {
        super(list);
        this.i = new PointF();
        this.j = new float[2];
        this.l = new PathMeasure();
    }

    /* JADX WARN: Multi-variable type inference failed */
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
        h hVar = (h) aVar;
        Path pathJ = hVar.j();
        if (pathJ == null) {
            return aVar.f2818b;
        }
        com.airbnb.lottie.v.c<A> cVar = this.f2729e;
        if (cVar != 0 && (pointF = (PointF) cVar.b(hVar.f2821e, hVar.f.floatValue(), hVar.f2818b, hVar.f2819c, e(), f, f())) != null) {
            return pointF;
        }
        if (this.k != hVar) {
            this.l.setPath(pathJ, false);
            this.k = hVar;
        }
        PathMeasure pathMeasure = this.l;
        pathMeasure.getPosTan(f * pathMeasure.getLength(), this.j, null);
        PointF pointF2 = this.i;
        float[] fArr = this.j;
        pointF2.set(fArr[0], fArr[1]);
        return this.i;
    }
}
