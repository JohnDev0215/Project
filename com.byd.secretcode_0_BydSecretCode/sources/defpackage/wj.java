package defpackage;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: RippleDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class wj extends Drawable implements pk, z7 {
    public b b;

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public wj mutate() {
        this.b = new b(this.b);
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        b bVar = this.b;
        if (bVar.b) {
            bVar.a.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.b;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.b.a.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.b.a.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean zOnStateChange = super.onStateChange(iArr);
        if (this.b.a.setState(iArr)) {
            zOnStateChange = true;
        }
        boolean zE = xj.e(iArr);
        b bVar = this.b;
        if (bVar.b == zE) {
            return zOnStateChange;
        }
        bVar.b = zE;
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.b.a.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.b.a.setColorFilter(colorFilter);
    }

    @Override // defpackage.pk
    public void setShapeAppearanceModel(mk mkVar) {
        this.b.a.setShapeAppearanceModel(mkVar);
    }

    @Override // android.graphics.drawable.Drawable, defpackage.z7
    public void setTint(int i) {
        this.b.a.setTint(i);
    }

    @Override // android.graphics.drawable.Drawable, defpackage.z7
    public void setTintList(ColorStateList colorStateList) {
        this.b.a.setTintList(colorStateList);
    }

    @Override // android.graphics.drawable.Drawable, defpackage.z7
    public void setTintMode(PorterDuff.Mode mode) {
        this.b.a.setTintMode(mode);
    }

    public wj(mk mkVar) {
        this(new b(new hk(mkVar)));
    }

    /* JADX INFO: compiled from: RippleDrawableCompat.java */
    public static final class b extends Drawable.ConstantState {
        public hk a;
        public boolean b;

        public b(hk hkVar) {
            this.a = hkVar;
            this.b = false;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public wj newDrawable() {
            return new wj(new b(this));
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        public b(b bVar) {
            this.a = (hk) bVar.a.getConstantState().newDrawable();
            this.b = bVar.b;
        }
    }

    public wj(b bVar) {
        this.b = bVar;
    }
}
