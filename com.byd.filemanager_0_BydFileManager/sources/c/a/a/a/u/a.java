package c.a.a.a.u;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import c.a.a.a.w.h;
import c.a.a.a.w.m;
import c.a.a.a.w.p;

/* JADX INFO: compiled from: RippleDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends Drawable implements p, androidx.core.graphics.drawable.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f2349b;

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public a mutate() {
        this.f2349b = new b(this.f2349b);
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        b bVar = this.f2349b;
        if (bVar.f2351b) {
            bVar.f2350a.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f2349b;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f2349b.f2350a.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f2349b.f2350a.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        boolean zOnStateChange = super.onStateChange(iArr);
        if (this.f2349b.f2350a.setState(iArr)) {
            zOnStateChange = true;
        }
        boolean zE = c.a.a.a.u.b.e(iArr);
        b bVar = this.f2349b;
        if (bVar.f2351b == zE) {
            return zOnStateChange;
        }
        bVar.f2351b = zE;
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.f2349b.f2350a.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f2349b.f2350a.setColorFilter(colorFilter);
    }

    @Override // c.a.a.a.w.p
    public void setShapeAppearanceModel(m mVar) {
        this.f2349b.f2350a.setShapeAppearanceModel(mVar);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTint(int i) {
        this.f2349b.f2350a.setTint(i);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        this.f2349b.f2350a.setTintList(colorStateList);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        this.f2349b.f2350a.setTintMode(mode);
    }

    public a(m mVar) {
        this(new b(new h(mVar)));
    }

    /* JADX INFO: compiled from: RippleDrawableCompat.java */
    static final class b extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        h f2350a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f2351b;

        public b(h hVar) {
            this.f2350a = hVar;
            this.f2351b = false;
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
            this.f2350a = (h) bVar.f2350a.getConstantState().newDrawable();
            this.f2351b = bVar.f2351b;
        }
    }

    private a(b bVar) {
        this.f2349b = bVar;
    }
}
