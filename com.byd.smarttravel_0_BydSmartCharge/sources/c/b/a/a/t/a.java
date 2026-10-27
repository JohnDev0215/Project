package c.b.a.a.t;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import c.b.a.a.v.h;
import c.b.a.a.v.m;
import c.b.a.a.v.p;

/* JADX INFO: compiled from: RippleDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends Drawable implements p, b.g.c.l.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f1835b;

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public a mutate() {
        this.f1835b = new b(this.f1835b);
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        b bVar = this.f1835b;
        if (bVar.f1837b) {
            bVar.f1836a.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f1835b;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f1835b.f1836a.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f1835b.f1836a.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean zOnStateChange = super.onStateChange(iArr);
        if (this.f1835b.f1836a.setState(iArr)) {
            zOnStateChange = true;
        }
        boolean zE = c.b.a.a.t.b.e(iArr);
        b bVar = this.f1835b;
        if (bVar.f1837b == zE) {
            return zOnStateChange;
        }
        bVar.f1837b = zE;
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.f1835b.f1836a.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f1835b.f1836a.setColorFilter(colorFilter);
    }

    @Override // c.b.a.a.v.p
    public void setShapeAppearanceModel(m mVar) {
        this.f1835b.f1836a.setShapeAppearanceModel(mVar);
    }

    @Override // android.graphics.drawable.Drawable, b.g.c.l.b
    public void setTint(int i) {
        this.f1835b.f1836a.setTint(i);
    }

    @Override // android.graphics.drawable.Drawable, b.g.c.l.b
    public void setTintList(ColorStateList colorStateList) {
        this.f1835b.f1836a.setTintList(colorStateList);
    }

    @Override // android.graphics.drawable.Drawable, b.g.c.l.b
    public void setTintMode(PorterDuff.Mode mode) {
        this.f1835b.f1836a.setTintMode(mode);
    }

    public a(m mVar) {
        this(new b(new h(mVar)));
    }

    /* JADX INFO: compiled from: RippleDrawableCompat.java */
    public static final class b extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public h f1836a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1837b;

        public b(h hVar) {
            this.f1836a = hVar;
            this.f1837b = false;
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
            this.f1836a = (h) bVar.f1836a.getConstantState().newDrawable();
            this.f1837b = bVar.f1837b;
        }
    }

    public a(b bVar) {
        this.f1835b = bVar;
    }
}
