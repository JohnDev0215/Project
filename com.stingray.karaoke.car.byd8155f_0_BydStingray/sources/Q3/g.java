package Q3;

import a4.AbstractC0925f;
import a4.C0920a;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.MaskFilter;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class g implements e, R3.a, c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f6150a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final P3.a f6151b;
    public final X3.b c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f6152e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final R3.f f6153f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final R3.f f6154g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final O3.j f6155h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final R3.f f6156i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f6157j;

    public g(O3.j jVar, X3.b bVar, W3.l lVar) {
        Path path = new Path();
        this.f6150a = path;
        this.f6151b = new P3.a(1, 0);
        this.f6152e = new ArrayList();
        this.c = bVar;
        lVar.getClass();
        this.d = lVar.f9796e;
        this.f6155h = jVar;
        if (bVar.i() != null) {
            R3.f fVarT0 = ((V3.b) bVar.i().l).t0();
            this.f6156i = fVarT0;
            fVarT0.a(this);
            bVar.d(fVarT0);
        }
        V3.a aVar = lVar.c;
        if (aVar == null) {
            this.f6153f = null;
            this.f6154g = null;
            return;
        }
        V3.a aVar2 = lVar.d;
        path.setFillType(lVar.f9795b);
        R3.e eVarT0 = aVar.t0();
        this.f6153f = (R3.f) eVarT0;
        eVarT0.a(this);
        bVar.d(eVarT0);
        R3.e eVarT02 = aVar2.t0();
        this.f6154g = (R3.f) eVarT02;
        eVarT02.a(this);
        bVar.d(eVarT02);
    }

    @Override // Q3.e
    public final void a(RectF rectF, Matrix matrix, boolean z6) {
        Path path = this.f6150a;
        path.reset();
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.f6152e;
            if (i7 >= arrayList.size()) {
                path.computeBounds(rectF, false);
                rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
                return;
            } else {
                path.addPath(((l) arrayList.get(i7)).e(), matrix);
                i7++;
            }
        }
    }

    @Override // R3.a
    public final void b() {
        this.f6155h.invalidateSelf();
    }

    @Override // Q3.c
    public final void c(List list, List list2) {
        for (int i7 = 0; i7 < list2.size(); i7++) {
            c cVar = (c) list2.get(i7);
            if (cVar instanceof l) {
                this.f6152e.add((l) cVar);
            }
        }
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
    @Override // Q3.e
    public final void f(Canvas canvas, Matrix matrix, int i7, C0920a c0920a) {
        MaskFilter maskFilter;
        if (this.d) {
            return;
        }
        R3.f fVar = this.f6153f;
        float fIntValue = ((Integer) this.f6154g.d()).intValue() / 100.0f;
        int iC = (AbstractC0925f.c((int) (i7 * fIntValue)) << 24) | (fVar.k(fVar.c.t(), fVar.b()) & 16777215);
        P3.a aVar = this.f6151b;
        aVar.setColor(iC);
        R3.f fVar2 = this.f6156i;
        if (fVar2 != null) {
            float fFloatValue = ((Float) fVar2.d()).floatValue();
            if (fFloatValue == 0.0f) {
                aVar.setMaskFilter(null);
            } else if (fFloatValue != this.f6157j) {
                X3.b bVar = this.c;
                if (bVar.f9995A == fFloatValue) {
                    maskFilter = bVar.B;
                } else {
                    BlurMaskFilter blurMaskFilter = new BlurMaskFilter(fFloatValue / 2.0f, BlurMaskFilter.Blur.NORMAL);
                    bVar.B = blurMaskFilter;
                    bVar.f9995A = fFloatValue;
                    maskFilter = blurMaskFilter;
                }
                aVar.setMaskFilter(maskFilter);
            }
            this.f6157j = fFloatValue;
        }
        if (c0920a != null) {
            c0920a.a((int) (fIntValue * 255.0f), aVar);
        } else {
            aVar.clearShadowLayer();
        }
        Path path = this.f6150a;
        path.reset();
        int i10 = 0;
        while (true) {
            ArrayList arrayList = this.f6152e;
            if (i10 >= arrayList.size()) {
                canvas.drawPath(path, aVar);
                return;
            } else {
                path.addPath(((l) arrayList.get(i10)).e(), matrix);
                i10++;
            }
        }
    }
}
