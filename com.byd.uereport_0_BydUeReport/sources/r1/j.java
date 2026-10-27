package r1;

import android.graphics.PointF;
import java.util.List;

/* JADX INFO: compiled from: PointKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends f<PointF> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final PointF f7765i;

    public j(List<b2.a<PointF>> list) {
        super(list);
        this.f7765i = new PointF();
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
    public PointF i(b2.a<PointF> aVar, float f8) {
        PointF pointF;
        PointF pointF2;
        PointF pointF3 = aVar.f2045b;
        if (pointF3 == null || (pointF = aVar.f2046c) == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        PointF pointF4 = pointF3;
        PointF pointF5 = pointF;
        b2.c<A> cVar = this.f7745e;
        if (cVar != 0 && (pointF2 = (PointF) cVar.b(aVar.f2048e, aVar.f2049f.floatValue(), pointF4, pointF5, f8, e(), f())) != null) {
            return pointF2;
        }
        PointF pointF6 = this.f7765i;
        float f9 = pointF4.x;
        float f10 = f9 + ((pointF5.x - f9) * f8);
        float f11 = pointF4.y;
        pointF6.set(f10, f11 + (f8 * (pointF5.y - f11)));
        return this.f7765i;
    }
}
