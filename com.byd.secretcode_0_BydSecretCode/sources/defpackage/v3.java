package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import defpackage.z3;

/* JADX INFO: compiled from: CardViewBaseImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class v3 implements x3 {
    public final RectF a = new RectF();

    /* JADX INFO: compiled from: CardViewBaseImpl.java */
    public class a implements z3.a {
        public a() {
        }

        @Override // z3.a
        public void a(Canvas canvas, RectF rectF, float f, Paint paint) {
            float f2 = 2.0f * f;
            float fWidth = (rectF.width() - f2) - 1.0f;
            float fHeight = (rectF.height() - f2) - 1.0f;
            if (f >= 1.0f) {
                float f3 = f + 0.5f;
                float f4 = -f3;
                v3.this.a.set(f4, f4, f3, f3);
                int iSave = canvas.save();
                canvas.translate(rectF.left + f3, rectF.top + f3);
                canvas.drawArc(v3.this.a, 180.0f, 90.0f, true, paint);
                canvas.translate(fWidth, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(v3.this.a, 180.0f, 90.0f, true, paint);
                canvas.translate(fHeight, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(v3.this.a, 180.0f, 90.0f, true, paint);
                canvas.translate(fWidth, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(v3.this.a, 180.0f, 90.0f, true, paint);
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

    @Override // defpackage.x3
    public float a(w3 w3Var) {
        return q(w3Var).i();
    }

    @Override // defpackage.x3
    public float b(w3 w3Var) {
        return q(w3Var).g();
    }

    @Override // defpackage.x3
    public float c(w3 w3Var) {
        return q(w3Var).j();
    }

    @Override // defpackage.x3
    public float d(w3 w3Var) {
        return q(w3Var).k();
    }

    @Override // defpackage.x3
    public void e(w3 w3Var) {
    }

    @Override // defpackage.x3
    public ColorStateList f(w3 w3Var) {
        return q(w3Var).f();
    }

    @Override // defpackage.x3
    public void g(w3 w3Var, float f) {
        q(w3Var).r(f);
    }

    @Override // defpackage.x3
    public void h(w3 w3Var, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        z3 z3VarP = p(context, colorStateList, f, f2, f3);
        z3VarP.m(w3Var.g());
        w3Var.f(z3VarP);
        n(w3Var);
    }

    @Override // defpackage.x3
    public void i(w3 w3Var, float f) {
        q(w3Var).p(f);
        n(w3Var);
    }

    @Override // defpackage.x3
    public void j(w3 w3Var) {
        q(w3Var).m(w3Var.g());
        n(w3Var);
    }

    @Override // defpackage.x3
    public void k(w3 w3Var, ColorStateList colorStateList) {
        q(w3Var).o(colorStateList);
    }

    @Override // defpackage.x3
    public float l(w3 w3Var) {
        return q(w3Var).l();
    }

    @Override // defpackage.x3
    public void m(w3 w3Var, float f) {
        q(w3Var).q(f);
        n(w3Var);
    }

    @Override // defpackage.x3
    public void n(w3 w3Var) {
        Rect rect = new Rect();
        q(w3Var).h(rect);
        w3Var.e((int) Math.ceil(d(w3Var)), (int) Math.ceil(c(w3Var)));
        w3Var.a(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Override // defpackage.x3
    public void o() {
        z3.b = new a();
    }

    public final z3 p(Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        return new z3(context.getResources(), colorStateList, f, f2, f3);
    }

    public final z3 q(w3 w3Var) {
        return (z3) w3Var.d();
    }
}
