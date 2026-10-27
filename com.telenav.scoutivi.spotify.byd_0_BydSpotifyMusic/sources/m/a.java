package m;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: compiled from: CardView.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends FrameLayout {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final int[] f8005l = {R.attr.colorBackground};

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static final d f8006m;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f8007e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f8008g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    int f8009h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    int f8010i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    final Rect f8011j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final c f8012k;

    static {
        b bVar = new b();
        f8006m = bVar;
        bVar.l();
    }

    public ColorStateList getCardBackgroundColor() {
        return f8006m.e(this.f8012k);
    }

    public float getCardElevation() {
        return f8006m.g(this.f8012k);
    }

    public int getContentPaddingBottom() {
        return this.f8011j.bottom;
    }

    public int getContentPaddingLeft() {
        return this.f8011j.left;
    }

    public int getContentPaddingRight() {
        return this.f8011j.right;
    }

    public int getContentPaddingTop() {
        return this.f8011j.top;
    }

    public float getMaxCardElevation() {
        return f8006m.a(this.f8012k);
    }

    public boolean getPreventCornerOverlap() {
        return this.f8008g;
    }

    public float getRadius() {
        return f8006m.b(this.f8012k);
    }

    public boolean getUseCompatPadding() {
        return this.f8007e;
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i7, int i8) {
        if (f8006m instanceof b) {
            super.onMeasure(i7, i8);
            return;
        }
        int mode = View.MeasureSpec.getMode(i7);
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            i7 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(r0.d(this.f8012k)), View.MeasureSpec.getSize(i7)), mode);
        }
        int mode2 = View.MeasureSpec.getMode(i8);
        if (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) {
            i8 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(r0.f(this.f8012k)), View.MeasureSpec.getSize(i8)), mode2);
        }
        super.onMeasure(i7, i8);
    }

    public void setCardBackgroundColor(int i7) {
        f8006m.i(this.f8012k, ColorStateList.valueOf(i7));
    }

    public void setCardElevation(float f7) {
        f8006m.h(this.f8012k, f7);
    }

    public void setMaxCardElevation(float f7) {
        f8006m.m(this.f8012k, f7);
    }

    @Override // android.view.View
    public void setMinimumHeight(int i7) {
        this.f8010i = i7;
        super.setMinimumHeight(i7);
    }

    @Override // android.view.View
    public void setMinimumWidth(int i7) {
        this.f8009h = i7;
        super.setMinimumWidth(i7);
    }

    @Override // android.view.View
    public void setPadding(int i7, int i8, int i9, int i10) {
    }

    @Override // android.view.View
    public void setPaddingRelative(int i7, int i8, int i9, int i10) {
    }

    public void setPreventCornerOverlap(boolean z6) {
        if (z6 != this.f8008g) {
            this.f8008g = z6;
            f8006m.k(this.f8012k);
        }
    }

    public void setRadius(float f7) {
        f8006m.c(this.f8012k, f7);
    }

    public void setUseCompatPadding(boolean z6) {
        if (this.f8007e != z6) {
            this.f8007e = z6;
            f8006m.j(this.f8012k);
        }
    }

    public void setCardBackgroundColor(ColorStateList colorStateList) {
        f8006m.i(this.f8012k, colorStateList);
    }
}
