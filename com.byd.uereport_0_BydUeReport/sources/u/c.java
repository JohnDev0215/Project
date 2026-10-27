package u;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import u.g;

/* JADX INFO: compiled from: CardViewBaseImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RectF f8088a = new RectF();

    /* JADX INFO: compiled from: CardViewBaseImpl.java */
    public class a implements g.a {
        public a() {
        }

        @Override // u.g.a
        public void a(Canvas canvas, RectF rectF, float f8, Paint paint) {
            float f9 = 2.0f * f8;
            float fWidth = (rectF.width() - f9) - 1.0f;
            float fHeight = (rectF.height() - f9) - 1.0f;
            if (f8 >= 1.0f) {
                float f10 = f8 + 0.5f;
                float f11 = -f10;
                c.this.f8088a.set(f11, f11, f10, f10);
                int iSave = canvas.save();
                canvas.translate(rectF.left + f10, rectF.top + f10);
                canvas.drawArc(c.this.f8088a, 180.0f, 90.0f, true, paint);
                canvas.translate(fWidth, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(c.this.f8088a, 180.0f, 90.0f, true, paint);
                canvas.translate(fHeight, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(c.this.f8088a, 180.0f, 90.0f, true, paint);
                canvas.translate(fWidth, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(c.this.f8088a, 180.0f, 90.0f, true, paint);
                canvas.restoreToCount(iSave);
                float f12 = (rectF.left + f10) - 1.0f;
                float f13 = rectF.top;
                canvas.drawRect(f12, f13, (rectF.right - f10) + 1.0f, f13 + f10, paint);
                float f14 = (rectF.left + f10) - 1.0f;
                float f15 = rectF.bottom;
                canvas.drawRect(f14, f15 - f10, (rectF.right - f10) + 1.0f, f15, paint);
            }
            canvas.drawRect(rectF.left, rectF.top + f8, rectF.right, rectF.bottom - f8, paint);
        }
    }

    @Override // u.e
    public float a(d dVar) {
        return q(dVar).i();
    }

    @Override // u.e
    public ColorStateList b(d dVar) {
        return q(dVar).f();
    }

    @Override // u.e
    public void c(d dVar, Context context, ColorStateList colorStateList, float f8, float f9, float f10) {
        g gVarP = p(context, colorStateList, f8, f9, f10);
        gVarP.m(dVar.e());
        dVar.d(gVarP);
        f(dVar);
    }

    @Override // u.e
    public void d(d dVar, float f8) {
        q(dVar).p(f8);
        f(dVar);
    }

    @Override // u.e
    public float e(d dVar) {
        return q(dVar).l();
    }

    @Override // u.e
    public void f(d dVar) {
        Rect rect = new Rect();
        q(dVar).h(rect);
        dVar.c((int) Math.ceil(j(dVar)), (int) Math.ceil(i(dVar)));
        dVar.a(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Override // u.e
    public void g() {
        g.f8102b = new a();
    }

    @Override // u.e
    public float h(d dVar) {
        return q(dVar).g();
    }

    @Override // u.e
    public float i(d dVar) {
        return q(dVar).j();
    }

    @Override // u.e
    public float j(d dVar) {
        return q(dVar).k();
    }

    @Override // u.e
    public void k(d dVar) {
    }

    @Override // u.e
    public void l(d dVar, float f8) {
        q(dVar).r(f8);
    }

    @Override // u.e
    public void m(d dVar) {
        q(dVar).m(dVar.e());
        f(dVar);
    }

    @Override // u.e
    public void n(d dVar, ColorStateList colorStateList) {
        q(dVar).o(colorStateList);
    }

    @Override // u.e
    public void o(d dVar, float f8) {
        q(dVar).q(f8);
        f(dVar);
    }

    public final g p(Context context, ColorStateList colorStateList, float f8, float f9, float f10) {
        return new g(context.getResources(), colorStateList, f8, f9, f10);
    }

    public final g q(d dVar) {
        return (g) dVar.g();
    }
}
