package c.b.a.a.g0;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import c.b.a.a.i0.h;
import c.b.a.a.i0.m;
import c.b.a.a.i0.p;

/* JADX INFO: compiled from: RippleDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends Drawable implements p, b.h.f.l.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f3831b;

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public a mutate() {
        this.f3831b = new b(this.f3831b);
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        b bVar = this.f3831b;
        if (bVar.f3833b) {
            bVar.f3832a.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f3831b;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f3831b.f3832a.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f3831b.f3832a.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean zOnStateChange = super.onStateChange(iArr);
        if (this.f3831b.f3832a.setState(iArr)) {
            zOnStateChange = true;
        }
        boolean zE = c.b.a.a.g0.b.e(iArr);
        b bVar = this.f3831b;
        if (bVar.f3833b == zE) {
            return zOnStateChange;
        }
        bVar.f3833b = zE;
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f3831b.f3832a.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f3831b.f3832a.setColorFilter(colorFilter);
    }

    @Override // c.b.a.a.i0.p
    public void setShapeAppearanceModel(m mVar) {
        this.f3831b.f3832a.setShapeAppearanceModel(mVar);
    }

    @Override // android.graphics.drawable.Drawable, b.h.f.l.b
    public void setTint(int i2) {
        this.f3831b.f3832a.setTint(i2);
    }

    @Override // android.graphics.drawable.Drawable, b.h.f.l.b
    public void setTintList(ColorStateList colorStateList) {
        this.f3831b.f3832a.setTintList(colorStateList);
    }

    @Override // android.graphics.drawable.Drawable, b.h.f.l.b
    public void setTintMode(PorterDuff.Mode mode) {
        this.f3831b.f3832a.setTintMode(mode);
    }

    public a(m mVar) {
        this(new b(new h(mVar)));
    }

    /* JADX INFO: compiled from: RippleDrawableCompat.java */
    public static final class b extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public h f3832a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f3833b;

        public b(h hVar) {
            this.f3832a = hVar;
            this.f3833b = false;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a newDrawable() {
            return new a(new b(this));
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        public b(b bVar) {
            this.f3832a = (h) bVar.f3832a.getConstantState().newDrawable();
            this.f3833b = bVar.f3833b;
        }
    }

    public a(b bVar) {
        this.f3831b = bVar;
    }
}
