package a.c.a;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class a extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f117b = {R.attr.colorBackground};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final f f118c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f119d;
    private boolean e;
    int f;
    int g;
    final Rect h;
    private final e i;

    static {
        int i = Build.VERSION.SDK_INT;
        f118c = i >= 21 ? new c() : i >= 17 ? new b() : new d();
        f118c.m();
    }

    public ColorStateList getCardBackgroundColor() {
        return f118c.f(this.i);
    }

    public float getCardElevation() {
        return f118c.k(this.i);
    }

    public int getContentPaddingBottom() {
        return this.h.bottom;
    }

    public int getContentPaddingLeft() {
        return this.h.left;
    }

    public int getContentPaddingRight() {
        return this.h.right;
    }

    public int getContentPaddingTop() {
        return this.h.top;
    }

    public float getMaxCardElevation() {
        return f118c.a(this.i);
    }

    public boolean getPreventCornerOverlap() {
        return this.e;
    }

    public float getRadius() {
        return f118c.b(this.i);
    }

    public boolean getUseCompatPadding() {
        return this.f119d;
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (!(f118c instanceof c)) {
            int mode = View.MeasureSpec.getMode(i);
            if (mode == Integer.MIN_VALUE || mode == 1073741824) {
                i = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(r0.d(this.i)), View.MeasureSpec.getSize(i)), mode);
            }
            int mode2 = View.MeasureSpec.getMode(i2);
            if (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) {
                i2 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(r0.c(this.i)), View.MeasureSpec.getSize(i2)), mode2);
            }
        }
        super.onMeasure(i, i2);
    }

    public void setCardBackgroundColor(int i) {
        f118c.j(this.i, ColorStateList.valueOf(i));
    }

    public void setCardBackgroundColor(ColorStateList colorStateList) {
        f118c.j(this.i, colorStateList);
    }

    public void setCardElevation(float f) {
        f118c.g(this.i, f);
    }

    public void setMaxCardElevation(float f) {
        f118c.l(this.i, f);
    }

    @Override // android.view.View
    public void setMinimumHeight(int i) {
        this.g = i;
        super.setMinimumHeight(i);
    }

    @Override // android.view.View
    public void setMinimumWidth(int i) {
        this.f = i;
        super.setMinimumWidth(i);
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
    }

    @Override // android.view.View
    public void setPaddingRelative(int i, int i2, int i3, int i4) {
    }

    public void setPreventCornerOverlap(boolean z) {
        if (z != this.e) {
            this.e = z;
            f118c.i(this.i);
        }
    }

    public void setRadius(float f) {
        f118c.h(this.i, f);
    }

    public void setUseCompatPadding(boolean z) {
        if (this.f119d != z) {
            this.f119d = z;
            f118c.e(this.i);
        }
    }
}
