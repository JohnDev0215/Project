package b.a.a.a.z;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import b.a.a.a.b0.g;
import b.a.a.a.b0.k;
import b.a.a.a.b0.n;

/* JADX INFO: loaded from: classes.dex */
public class a extends Drawable implements n, androidx.core.graphics.drawable.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f1787b;

    static final class b extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        g f1788a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f1789b;

        public b(g gVar) {
            this.f1788a = gVar;
            this.f1789b = false;
        }

        public b(b bVar) {
            this.f1788a = (g) bVar.f1788a.getConstantState().newDrawable();
            this.f1789b = bVar.f1789b;
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
    }

    public a(k kVar) {
        this(new b(new g(kVar)));
    }

    private a(b bVar) {
        this.f1787b = bVar;
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public a mutate() {
        this.f1787b = new b(this.f1787b);
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        b bVar = this.f1787b;
        if (bVar.f1789b) {
            bVar.f1788a.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f1787b;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f1787b.f1788a.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f1787b.f1788a.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        boolean zOnStateChange = super.onStateChange(iArr);
        if (this.f1787b.f1788a.setState(iArr)) {
            zOnStateChange = true;
        }
        boolean zE = b.a.a.a.z.b.e(iArr);
        b bVar = this.f1787b;
        if (bVar.f1789b == zE) {
            return zOnStateChange;
        }
        bVar.f1789b = zE;
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.f1787b.f1788a.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f1787b.f1788a.setColorFilter(colorFilter);
    }

    @Override // b.a.a.a.b0.n
    public void setShapeAppearanceModel(k kVar) {
        this.f1787b.f1788a.setShapeAppearanceModel(kVar);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTint(int i) {
        this.f1787b.f1788a.setTint(i);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        this.f1787b.f1788a.setTintList(colorStateList);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        this.f1787b.f1788a.setTintMode(mode);
    }
}
