package n2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import n2.b;

/* JADX INFO: compiled from: ShimmerFrameLayout.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends FrameLayout {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Paint f8542e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final c f8543g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f8544h;

    public d(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8542e = new Paint();
        this.f8543g = new c();
        this.f8544h = true;
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        setWillNotDraw(false);
        this.f8543g.setCallback(this);
        if (attributeSet == null) {
            b(new b.a().a());
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.ShimmerFrameLayout, 0, 0);
        try {
            int i7 = a.ShimmerFrameLayout_shimmer_colored;
            b(((typedArrayObtainStyledAttributes.hasValue(i7) && typedArrayObtainStyledAttributes.getBoolean(i7, false)) ? new b.c() : new b.a()).c(typedArrayObtainStyledAttributes).a());
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public d b(b bVar) {
        this.f8543g.d(bVar);
        if (bVar == null || !bVar.f8527o) {
            setLayerType(0, null);
        } else {
            setLayerType(2, this.f8542e);
        }
        return this;
    }

    public void c() {
        this.f8543g.e();
    }

    public void d() {
        this.f8543g.f();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.f8544h) {
            this.f8543g.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f8543g.b();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        d();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        this.f8543g.setBounds(0, 0, getWidth(), getHeight());
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f8543g;
    }
}
