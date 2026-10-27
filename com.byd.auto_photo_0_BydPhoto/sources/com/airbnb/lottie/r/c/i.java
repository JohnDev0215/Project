package com.airbnb.lottie.r.c;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class i extends f<PointF> {
    private final PointF l;
    private final float[] m;
    private h n;
    private PathMeasure o;

    public i(List<? extends com.airbnb.lottie.x.a<PointF>> list) {
        super(list);
        this.l = new PointF();
        this.m = new float[2];
        this.o = new PathMeasure();
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
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public PointF i(com.airbnb.lottie.x.a<PointF> aVar, float f) {
        PointF pointF;
        h hVar = (h) aVar;
        Path pathJ = hVar.j();
        if (pathJ == null) {
            return aVar.f976b;
        }
        com.airbnb.lottie.x.c<A> cVar = this.e;
        if (cVar != 0 && (pointF = (PointF) cVar.b(hVar.e, hVar.f.floatValue(), hVar.f976b, hVar.f977c, e(), f, f())) != null) {
            return pointF;
        }
        if (this.n != hVar) {
            this.o.setPath(pathJ, false);
            this.n = hVar;
        }
        PathMeasure pathMeasure = this.o;
        pathMeasure.getPosTan(f * pathMeasure.getLength(), this.m, null);
        PointF pointF2 = this.l;
        float[] fArr = this.m;
        pointF2.set(fArr[0], fArr[1]);
        return this.l;
    }
}
