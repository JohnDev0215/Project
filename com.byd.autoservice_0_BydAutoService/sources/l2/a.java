package l2;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import n2.h;
import n2.m;
import n2.p;

/* JADX INFO: compiled from: RippleDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends Drawable implements p, e0.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f3714b;

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public a mutate() {
        this.f3714b = new b(this.f3714b);
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        b bVar = this.f3714b;
        if (bVar.f3716b) {
            bVar.f3715a.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f3714b;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f3714b.f3715a.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f3714b.f3715a.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean zOnStateChange = super.onStateChange(iArr);
        if (this.f3714b.f3715a.setState(iArr)) {
            zOnStateChange = true;
        }
        boolean zE = l2.b.e(iArr);
        b bVar = this.f3714b;
        if (bVar.f3716b == zE) {
            return zOnStateChange;
        }
        bVar.f3716b = zE;
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i3) {
        this.f3714b.f3715a.setAlpha(i3);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f3714b.f3715a.setColorFilter(colorFilter);
    }

    @Override // n2.p
    public void setShapeAppearanceModel(m mVar) {
        this.f3714b.f3715a.setShapeAppearanceModel(mVar);
    }

    @Override // android.graphics.drawable.Drawable, e0.b
    public void setTint(int i3) {
        this.f3714b.f3715a.setTint(i3);
    }

    @Override // android.graphics.drawable.Drawable, e0.b
    public void setTintList(ColorStateList colorStateList) {
        this.f3714b.f3715a.setTintList(colorStateList);
    }

    @Override // android.graphics.drawable.Drawable, e0.b
    public void setTintMode(PorterDuff.Mode mode) {
        this.f3714b.f3715a.setTintMode(mode);
    }

    public a(m mVar) {
        this(new b(new h(mVar)));
    }

    /* JADX INFO: compiled from: RippleDrawableCompat.java */
    public static final class b extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public h f3715a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f3716b;

        public b(h hVar) {
            this.f3715a = hVar;
            this.f3716b = false;
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
            this.f3715a = (h) bVar.f3715a.getConstantState().newDrawable();
            this.f3716b = bVar.f3716b;
        }
    }

    public a(b bVar) {
        this.f3714b = bVar;
    }
}
