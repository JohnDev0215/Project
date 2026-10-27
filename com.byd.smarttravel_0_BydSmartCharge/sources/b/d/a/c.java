package b.d.a;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import b.d.a.g;

/* JADX INFO: compiled from: CardViewBaseImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RectF f868a = new RectF();

    /* JADX INFO: compiled from: CardViewBaseImpl.java */
    public class a implements g.a {
        public a() {
        }

        @Override // b.d.a.g.a
        public void a(Canvas canvas, RectF rectF, float f, Paint paint) {
            float f2 = 2.0f * f;
            float fWidth = (rectF.width() - f2) - 1.0f;
            float fHeight = (rectF.height() - f2) - 1.0f;
            if (f >= 1.0f) {
                float f3 = f + 0.5f;
                float f4 = -f3;
                c.this.f868a.set(f4, f4, f3, f3);
                int iSave = canvas.save();
                canvas.translate(rectF.left + f3, rectF.top + f3);
                canvas.drawArc(c.this.f868a, 180.0f, 90.0f, true, paint);
                canvas.translate(fWidth, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(c.this.f868a, 180.0f, 90.0f, true, paint);
                canvas.translate(fHeight, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(c.this.f868a, 180.0f, 90.0f, true, paint);
                canvas.translate(fWidth, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(c.this.f868a, 180.0f, 90.0f, true, paint);
                canvas.restoreToCount(iSave);
                float f5 = (rectF.left + f3) - 1.0f;
                float f6 = rectF.top;
                canvas.drawRect(f5, f6, (rectF.right - f3) + 1.0f, f6 + f3, paint);
                float f7 = (rectF.left + f3) - 1.0f;
                float f8 = rectF.bottom;
                canvas.drawRect(f7, f8 - f3, (rectF.right - f3) + 1.0f, f8, paint);
            }
            canvas.drawRect(rectF.left, rectF.top + f, rectF.right, rectF.bottom - f, paint);
        }
    }

    @Override // b.d.a.e
    public float a(d dVar) {
        return q(dVar).i();
    }

    @Override // b.d.a.e
    public float b(d dVar) {
        return q(dVar).g();
    }

    @Override // b.d.a.e
    public float c(d dVar) {
        return q(dVar).j();
    }

    @Override // b.d.a.e
    public float d(d dVar) {
        return q(dVar).k();
    }

    @Override // b.d.a.e
    public void e(d dVar) {
    }

    @Override // b.d.a.e
    public ColorStateList f(d dVar) {
        return q(dVar).f();
    }

    @Override // b.d.a.e
    public void g(d dVar, float f) {
        q(dVar).r(f);
    }

    @Override // b.d.a.e
    public void h(d dVar, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        g gVarP = p(context, colorStateList, f, f2, f3);
        gVarP.m(dVar.g());
        dVar.f(gVarP);
        n(dVar);
    }

    @Override // b.d.a.e
    public void i(d dVar, float f) {
        q(dVar).p(f);
        n(dVar);
    }

    @Override // b.d.a.e
    public void j(d dVar) {
        q(dVar).m(dVar.g());
        n(dVar);
    }

    @Override // b.d.a.e
    public void k(d dVar, ColorStateList colorStateList) {
        q(dVar).o(colorStateList);
    }

    @Override // b.d.a.e
    public float l(d dVar) {
        return q(dVar).l();
    }

    @Override // b.d.a.e
    public void m(d dVar, float f) {
        q(dVar).q(f);
        n(dVar);
    }

    @Override // b.d.a.e
    public void n(d dVar) {
        Rect rect = new Rect();
        q(dVar).h(rect);
        dVar.e((int) Math.ceil(d(dVar)), (int) Math.ceil(c(dVar)));
        dVar.a(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Override // b.d.a.e
    public void o() {
        g.f875b = new a();
    }

    public final g p(Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        return new g(context.getResources(), colorStateList, f, f2, f3);
    }

    public final g q(d dVar) {
        return (g) dVar.d();
    }
}
