package d.b.a.a.v;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import d.b.a.a.x.h;
import d.b.a.a.x.m;
import d.b.a.a.x.p;

/* JADX INFO: compiled from: RippleDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends Drawable implements p, androidx.core.graphics.drawable.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f4726b;

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public a mutate() {
        this.f4726b = new b(this.f4726b);
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        b bVar = this.f4726b;
        if (bVar.f4728b) {
            bVar.f4727a.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f4726b;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f4726b.f4727a.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f4726b.f4727a.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        boolean zOnStateChange = super.onStateChange(iArr);
        if (this.f4726b.f4727a.setState(iArr)) {
            zOnStateChange = true;
        }
        boolean zE = d.b.a.a.v.b.e(iArr);
        b bVar = this.f4726b;
        if (bVar.f4728b == zE) {
            return zOnStateChange;
        }
        bVar.f4728b = zE;
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f4726b.f4727a.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f4726b.f4727a.setColorFilter(colorFilter);
    }

    @Override // d.b.a.a.x.p
    public void setShapeAppearanceModel(m mVar) {
        this.f4726b.f4727a.setShapeAppearanceModel(mVar);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTint(int i2) {
        this.f4726b.f4727a.setTint(i2);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        this.f4726b.f4727a.setTintList(colorStateList);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        this.f4726b.f4727a.setTintMode(mode);
    }

    public a(m mVar) {
        this(new b(new h(mVar)));
    }

    /* JADX INFO: compiled from: RippleDrawableCompat.java */
    static final class b extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        h f4727a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f4728b;

        public b(h hVar) {
            this.f4727a = hVar;
            this.f4728b = false;
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
            this.f4727a = (h) bVar.f4727a.getConstantState().newDrawable();
            this.f4728b = bVar.f4728b;
        }
    }

    private a(b bVar) {
        this.f4726b = bVar;
    }
}
