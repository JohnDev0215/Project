package c6;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import e6.h;
import e6.m;
import e6.p;

/* JADX INFO: compiled from: RippleDrawableCompat.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends Drawable implements p, h0.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b f2325d;

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public a mutate() {
        this.f2325d = new b(this.f2325d);
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        b bVar = this.f2325d;
        if (bVar.f2327b) {
            bVar.f2326a.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f2325d;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f2325d.f2326a.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f2325d.f2326a.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean zOnStateChange = super.onStateChange(iArr);
        if (this.f2325d.f2326a.setState(iArr)) {
            zOnStateChange = true;
        }
        boolean zE = c6.b.e(iArr);
        b bVar = this.f2325d;
        if (bVar.f2327b == zE) {
            return zOnStateChange;
        }
        bVar.f2327b = zE;
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i8) {
        this.f2325d.f2326a.setAlpha(i8);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f2325d.f2326a.setColorFilter(colorFilter);
    }

    @Override // e6.p
    public void setShapeAppearanceModel(m mVar) {
        this.f2325d.f2326a.setShapeAppearanceModel(mVar);
    }

    @Override // android.graphics.drawable.Drawable, h0.b
    public void setTint(int i8) {
        this.f2325d.f2326a.setTint(i8);
    }

    @Override // android.graphics.drawable.Drawable, h0.b
    public void setTintList(ColorStateList colorStateList) {
        this.f2325d.f2326a.setTintList(colorStateList);
    }

    @Override // android.graphics.drawable.Drawable, h0.b
    public void setTintMode(PorterDuff.Mode mode) {
        this.f2325d.f2326a.setTintMode(mode);
    }

    public a(m mVar) {
        this(new b(new h(mVar)));
    }

    /* JADX INFO: compiled from: RippleDrawableCompat.java */
    public static final class b extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public h f2326a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f2327b;

        public b(h hVar) {
            this.f2326a = hVar;
            this.f2327b = false;
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
            this.f2326a = (h) bVar.f2326a.getConstantState().newDrawable();
            this.f2327b = bVar.f2327b;
        }
    }

    public a(b bVar) {
        this.f2325d = bVar;
    }
}
