package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.cardview.widget.g;

/* JADX INFO: compiled from: CardViewBaseImpl.java */
/* JADX INFO: loaded from: classes.dex */
class c implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final RectF f766a = new RectF();

    /* JADX INFO: compiled from: CardViewBaseImpl.java */
    class a implements g.a {
        a() {
        }

        @Override // androidx.cardview.widget.g.a
        public void a(Canvas canvas, RectF rectF, float f2, Paint paint) {
            float f3 = 2.0f * f2;
            float fWidth = (rectF.width() - f3) - 1.0f;
            float fHeight = (rectF.height() - f3) - 1.0f;
            if (f2 >= 1.0f) {
                float f4 = f2 + 0.5f;
                float f5 = -f4;
                c.this.f766a.set(f5, f5, f4, f4);
                int iSave = canvas.save();
                canvas.translate(rectF.left + f4, rectF.top + f4);
                canvas.drawArc(c.this.f766a, 180.0f, 90.0f, true, paint);
                canvas.translate(fWidth, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(c.this.f766a, 180.0f, 90.0f, true, paint);
                canvas.translate(fHeight, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(c.this.f766a, 180.0f, 90.0f, true, paint);
                canvas.translate(fWidth, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(c.this.f766a, 180.0f, 90.0f, true, paint);
                canvas.restoreToCount(iSave);
                float f6 = (rectF.left + f4) - 1.0f;
                float f7 = rectF.top;
                canvas.drawRect(f6, f7, (rectF.right - f4) + 1.0f, f7 + f4, paint);
                float f8 = (rectF.left + f4) - 1.0f;
                float f9 = rectF.bottom;
                canvas.drawRect(f8, f9 - f4, (rectF.right - f4) + 1.0f, f9, paint);
            }
            canvas.drawRect(rectF.left, rectF.top + f2, rectF.right, rectF.bottom - f2, paint);
        }
    }

    c() {
    }

    private g p(Context context, ColorStateList colorStateList, float f2, float f3, float f4) {
        return new g(context.getResources(), colorStateList, f2, f3, f4);
    }

    private g q(d dVar) {
        return (g) dVar.d();
    }

    @Override // androidx.cardview.widget.e
    public float a(d dVar) {
        return q(dVar).i();
    }

    @Override // androidx.cardview.widget.e
    public float b(d dVar) {
        return q(dVar).g();
    }

    @Override // androidx.cardview.widget.e
    public float c(d dVar) {
        return q(dVar).j();
    }

    @Override // androidx.cardview.widget.e
    public float d(d dVar) {
        return q(dVar).k();
    }

    @Override // androidx.cardview.widget.e
    public void e(d dVar) {
    }

    @Override // androidx.cardview.widget.e
    public ColorStateList f(d dVar) {
        return q(dVar).f();
    }

    @Override // androidx.cardview.widget.e
    public void g(d dVar, float f2) {
        q(dVar).r(f2);
    }

    @Override // androidx.cardview.widget.e
    public void h(d dVar, Context context, ColorStateList colorStateList, float f2, float f3, float f4) {
        g gVarP = p(context, colorStateList, f2, f3, f4);
        gVarP.m(dVar.g());
        dVar.f(gVarP);
        n(dVar);
    }

    @Override // androidx.cardview.widget.e
    public void i(d dVar, float f2) {
        q(dVar).p(f2);
        n(dVar);
    }

    @Override // androidx.cardview.widget.e
    public void j(d dVar) {
        q(dVar).m(dVar.g());
        n(dVar);
    }

    @Override // androidx.cardview.widget.e
    public void k(d dVar, ColorStateList colorStateList) {
        q(dVar).o(colorStateList);
    }

    @Override // androidx.cardview.widget.e
    public float l(d dVar) {
        return q(dVar).l();
    }

    @Override // androidx.cardview.widget.e
    public void m(d dVar, float f2) {
        q(dVar).q(f2);
        n(dVar);
    }

    @Override // androidx.cardview.widget.e
    public void n(d dVar) {
        Rect rect = new Rect();
        q(dVar).h(rect);
        dVar.e((int) Math.ceil(d(dVar)), (int) Math.ceil(c(dVar)));
        dVar.a(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Override // androidx.cardview.widget.e
    public void o() {
        g.f778b = new a();
    }
}
