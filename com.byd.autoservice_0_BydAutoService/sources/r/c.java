package r;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import r.g;

/* JADX INFO: compiled from: CardViewBaseImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RectF f4643a = new RectF();

    /* JADX INFO: compiled from: CardViewBaseImpl.java */
    public class a implements g.a {
        public a() {
        }

        @Override // r.g.a
        public void a(Canvas canvas, RectF rectF, float f3, Paint paint) {
            float f4 = 2.0f * f3;
            float fWidth = (rectF.width() - f4) - 1.0f;
            float fHeight = (rectF.height() - f4) - 1.0f;
            if (f3 >= 1.0f) {
                float f5 = f3 + 0.5f;
                float f6 = -f5;
                c.this.f4643a.set(f6, f6, f5, f5);
                int iSave = canvas.save();
                canvas.translate(rectF.left + f5, rectF.top + f5);
                canvas.drawArc(c.this.f4643a, 180.0f, 90.0f, true, paint);
                canvas.translate(fWidth, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(c.this.f4643a, 180.0f, 90.0f, true, paint);
                canvas.translate(fHeight, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(c.this.f4643a, 180.0f, 90.0f, true, paint);
                canvas.translate(fWidth, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(c.this.f4643a, 180.0f, 90.0f, true, paint);
                canvas.restoreToCount(iSave);
                float f7 = (rectF.left + f5) - 1.0f;
                float f8 = rectF.top;
                canvas.drawRect(f7, f8, (rectF.right - f5) + 1.0f, f8 + f5, paint);
                float f9 = (rectF.left + f5) - 1.0f;
                float f10 = rectF.bottom;
                canvas.drawRect(f9, f10 - f5, (rectF.right - f5) + 1.0f, f10, paint);
            }
            canvas.drawRect(rectF.left, rectF.top + f3, rectF.right, rectF.bottom - f3, paint);
        }
    }

    @Override // r.e
    public float a(d dVar) {
        return q(dVar).i();
    }

    @Override // r.e
    public float b(d dVar) {
        return q(dVar).g();
    }

    @Override // r.e
    public float c(d dVar) {
        return q(dVar).j();
    }

    @Override // r.e
    public float d(d dVar) {
        return q(dVar).k();
    }

    @Override // r.e
    public void e(d dVar) {
    }

    @Override // r.e
    public ColorStateList f(d dVar) {
        return q(dVar).f();
    }

    @Override // r.e
    public void g(d dVar, float f3) {
        q(dVar).r(f3);
    }

    @Override // r.e
    public void h(d dVar, Context context, ColorStateList colorStateList, float f3, float f4, float f5) {
        g gVarP = p(context, colorStateList, f3, f4, f5);
        gVarP.m(dVar.g());
        dVar.f(gVarP);
        n(dVar);
    }

    @Override // r.e
    public void i(d dVar, float f3) {
        q(dVar).p(f3);
        n(dVar);
    }

    @Override // r.e
    public void j(d dVar) {
        q(dVar).m(dVar.g());
        n(dVar);
    }

    @Override // r.e
    public void k(d dVar, ColorStateList colorStateList) {
        q(dVar).o(colorStateList);
    }

    @Override // r.e
    public float l(d dVar) {
        return q(dVar).l();
    }

    @Override // r.e
    public void m(d dVar, float f3) {
        q(dVar).q(f3);
        n(dVar);
    }

    @Override // r.e
    public void n(d dVar) {
        Rect rect = new Rect();
        q(dVar).h(rect);
        dVar.e((int) Math.ceil(d(dVar)), (int) Math.ceil(c(dVar)));
        dVar.a(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Override // r.e
    public void o() {
        g.f4657b = new a();
    }

    public final g p(Context context, ColorStateList colorStateList, float f3, float f4, float f5) {
        return new g(context.getResources(), colorStateList, f3, f4, f5);
    }

    public final g q(d dVar) {
        return (g) dVar.d();
    }
}
