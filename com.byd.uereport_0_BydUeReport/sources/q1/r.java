package q1;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import com.airbnb.lottie.model.content.ShapeStroke;

/* JADX INFO: compiled from: StrokeContent.java */
/* JADX INFO: loaded from: classes.dex */
public class r extends a {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final w1.a f7363o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final String f7364p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f7365q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final r1.a<Integer, Integer> f7366r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public r1.a<ColorFilter, ColorFilter> f7367s;

    public r(o1.g gVar, w1.a aVar, ShapeStroke shapeStroke) {
        super(gVar, aVar, shapeStroke.b().toPaintCap(), shapeStroke.e().toPaintJoin(), shapeStroke.g(), shapeStroke.i(), shapeStroke.j(), shapeStroke.f(), shapeStroke.d());
        this.f7363o = aVar;
        this.f7364p = shapeStroke.h();
        this.f7365q = shapeStroke.k();
        r1.a<Integer, Integer> aVarA = shapeStroke.c().a();
        this.f7366r = aVarA;
        aVarA.a(this);
        aVar.k(aVarA);
    }

    @Override // q1.a, q1.e
    public void g(Canvas canvas, Matrix matrix, int i8) {
        if (this.f7365q) {
            return;
        }
        this.f7247i.setColor(((r1.b) this.f7366r).o());
        r1.a<ColorFilter, ColorFilter> aVar = this.f7367s;
        if (aVar != null) {
            this.f7247i.setColorFilter(aVar.h());
        }
        super.g(canvas, matrix, i8);
    }

    @Override // q1.c
    public String h() {
        return this.f7364p;
    }

    @Override // q1.a, t1.e
    public <T> void i(T t7, b2.c<T> cVar) {
        super.i(t7, cVar);
        if (t7 == o1.l.f6829b) {
            this.f7366r.m(cVar);
            return;
        }
        if (t7 == o1.l.C) {
            r1.a<ColorFilter, ColorFilter> aVar = this.f7367s;
            if (aVar != null) {
                this.f7363o.E(aVar);
            }
            if (cVar == null) {
                this.f7367s = null;
                return;
            }
            r1.p pVar = new r1.p(cVar);
            this.f7367s = pVar;
            pVar.a(this);
            this.f7363o.k(this.f7366r);
        }
    }
}
