package r1;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import java.util.List;

/* JADX INFO: compiled from: PathKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends f<PointF> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final PointF f7761i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final float[] f7762j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public h f7763k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public PathMeasure f7764l;

    public i(List<? extends b2.a<PointF>> list) {
        super(list);
        this.f7761i = new PointF();
        this.f7762j = new float[2];
        this.f7764l = new PathMeasure();
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
    @Override // r1.a
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public PointF i(b2.a<PointF> aVar, float f8) {
        PointF pointF;
        h hVar = (h) aVar;
        Path pathJ = hVar.j();
        if (pathJ == null) {
            return aVar.f2045b;
        }
        b2.c<A> cVar = this.f7745e;
        if (cVar != 0 && (pointF = (PointF) cVar.b(hVar.f2048e, hVar.f2049f.floatValue(), hVar.f2045b, hVar.f2046c, e(), f8, f())) != null) {
            return pointF;
        }
        if (this.f7763k != hVar) {
            this.f7764l.setPath(pathJ, false);
            this.f7763k = hVar;
        }
        PathMeasure pathMeasure = this.f7764l;
        pathMeasure.getPosTan(f8 * pathMeasure.getLength(), this.f7762j, null);
        PointF pointF2 = this.f7761i;
        float[] fArr = this.f7762j;
        pointF2.set(fArr[0], fArr[1]);
        return this.f7761i;
    }
}
