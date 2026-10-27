package d.b.a.a.u;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import d.b.a.a.w.h;
import d.b.a.a.w.m;
import d.b.a.a.w.p;

/* JADX INFO: compiled from: RippleDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends Drawable implements p, androidx.core.graphics.drawable.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f3985b;

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public a mutate() {
        this.f3985b = new b(this.f3985b);
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        b bVar = this.f3985b;
        if (bVar.f3987b) {
            bVar.f3986a.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f3985b;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f3985b.f3986a.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f3985b.f3986a.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        boolean zOnStateChange = super.onStateChange(iArr);
        if (this.f3985b.f3986a.setState(iArr)) {
            zOnStateChange = true;
        }
        boolean zE = d.b.a.a.u.b.e(iArr);
        b bVar = this.f3985b;
        if (bVar.f3987b == zE) {
            return zOnStateChange;
        }
        bVar.f3987b = zE;
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.f3985b.f3986a.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f3985b.f3986a.setColorFilter(colorFilter);
    }

    @Override // d.b.a.a.w.p
    public void setShapeAppearanceModel(m mVar) {
        this.f3985b.f3986a.setShapeAppearanceModel(mVar);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTint(int i) {
        this.f3985b.f3986a.setTint(i);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        this.f3985b.f3986a.setTintList(colorStateList);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        this.f3985b.f3986a.setTintMode(mode);
    }

    public a(m mVar) {
        this(new b(new h(mVar)));
    }

    /* JADX INFO: compiled from: RippleDrawableCompat.java */
    static final class b extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        h f3986a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f3987b;

        public b(h hVar) {
            this.f3986a = hVar;
            this.f3987b = false;
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
            this.f3986a = (h) bVar.f3986a.getConstantState().newDrawable();
            this.f3987b = bVar.f3987b;
        }
    }

    private a(b bVar) {
        this.f3985b = bVar;
    }
}
