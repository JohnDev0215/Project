package defpackage;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import defpackage.vj2;

/* JADX INFO: compiled from: BorderDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public final class th extends Drawable {
    public final Paint b;
    public float h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public uj2 o;
    public ColorStateList p;
    public final vj2 a = vj2.a.a;
    public final Path c = new Path();
    public final Rect d = new Rect();
    public final RectF e = new RectF();
    public final RectF f = new RectF();
    public final a g = new a();
    public boolean n = true;

    /* JADX INFO: compiled from: BorderDrawable.java */
    public class a extends Drawable.ConstantState {
        public a() {
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable() {
            return th.this;
        }
    }

    public th(uj2 uj2Var) {
        this.o = uj2Var;
        Paint paint = new Paint(1);
        this.b = paint;
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        boolean z = this.n;
        Paint paint = this.b;
        Rect rect = this.d;
        if (z) {
            copyBounds(rect);
            float fHeight = this.h / rect.height();
            paint.setShader(new LinearGradient(0.0f, rect.top, 0.0f, rect.bottom, new int[]{gv.f(this.i, this.m), gv.f(this.j, this.m), gv.f(gv.g(this.j, 0), this.m), gv.f(gv.g(this.l, 0), this.m), gv.f(this.l, this.m), gv.f(this.k, this.m)}, new float[]{0.0f, fHeight, 0.5f, 0.5f, 1.0f - fHeight, 1.0f}, Shader.TileMode.CLAMP));
            this.n = false;
        }
        float strokeWidth = paint.getStrokeWidth() / 2.0f;
        copyBounds(rect);
        RectF rectF = this.e;
        rectF.set(rect);
        l00 l00Var = this.o.e;
        RectF rectF2 = this.f;
        rectF2.set(getBounds());
        float fMin = Math.min(l00Var.a(rectF2), rectF.width() / 2.0f);
        uj2 uj2Var = this.o;
        rectF2.set(getBounds());
        if (uj2Var.e(rectF2)) {
            rectF.inset(strokeWidth, strokeWidth);
            canvas.drawRoundRect(rectF, fMin, fMin, paint);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        return this.g;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return this.h > 0.0f ? -3 : -2;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public final void getOutline(Outline outline) {
        uj2 uj2Var = this.o;
        RectF rectF = this.f;
        rectF.set(getBounds());
        if (uj2Var.e(rectF)) {
            l00 l00Var = this.o.e;
            rectF.set(getBounds());
            outline.setRoundRect(getBounds(), l00Var.a(rectF));
            return;
        }
        Rect rect = this.d;
        copyBounds(rect);
        RectF rectF2 = this.e;
        rectF2.set(rect);
        uj2 uj2Var2 = this.o;
        Path path = this.c;
        this.a.a(uj2Var2, 1.0f, rectF2, null, path);
        if (path.isConvex()) {
            outline.setConvexPath(path);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean getPadding(Rect rect) {
        uj2 uj2Var = this.o;
        RectF rectF = this.f;
        rectF.set(getBounds());
        if (!uj2Var.e(rectF)) {
            return true;
        }
        int iRound = Math.round(this.h);
        rect.set(iRound, iRound, iRound, iRound);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        ColorStateList colorStateList = this.p;
        return (colorStateList != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        this.n = true;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        int colorForState;
        ColorStateList colorStateList = this.p;
        if (colorStateList != null && (colorForState = colorStateList.getColorForState(iArr, this.m)) != this.m) {
            this.n = true;
            this.m = colorForState;
        }
        if (this.n) {
            invalidateSelf();
        }
        return this.n;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.b.setAlpha(i);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.b.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
