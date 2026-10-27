package c3;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import e3.g;
import e3.k;
import e3.n;

/* JADX INFO: compiled from: RippleDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends Drawable implements n {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private b f3586e;

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public a mutate() {
        this.f3586e = new b(this.f3586e);
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        b bVar = this.f3586e;
        if (bVar.f3588b) {
            bVar.f3587a.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f3586e;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f3586e.f3587a.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f3586e.f3587a.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        boolean zOnStateChange = super.onStateChange(iArr);
        if (this.f3586e.f3587a.setState(iArr)) {
            zOnStateChange = true;
        }
        boolean zE = c3.b.e(iArr);
        b bVar = this.f3586e;
        if (bVar.f3588b == zE) {
            return zOnStateChange;
        }
        bVar.f3588b = zE;
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i7) {
        this.f3586e.f3587a.setAlpha(i7);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f3586e.f3587a.setColorFilter(colorFilter);
    }

    @Override // e3.n
    public void setShapeAppearanceModel(k kVar) {
        this.f3586e.f3587a.setShapeAppearanceModel(kVar);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i7) {
        this.f3586e.f3587a.setTint(i7);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.f3586e.f3587a.setTintList(colorStateList);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        this.f3586e.f3587a.setTintMode(mode);
    }

    public a(k kVar) {
        this(new b(new g(kVar)));
    }

    /* JADX INFO: compiled from: RippleDrawableCompat.java */
    static final class b extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        g f3587a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f3588b;

        public b(g gVar) {
            this.f3587a = gVar;
            this.f3588b = false;
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
            this.f3587a = (g) bVar.f3587a.getConstantState().newDrawable();
            this.f3588b = bVar.f3588b;
        }
    }

    private a(b bVar) {
        this.f3586e = bVar;
    }
}
