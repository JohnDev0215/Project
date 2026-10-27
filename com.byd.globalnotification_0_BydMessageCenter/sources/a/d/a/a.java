package a.d.a;

import android.content.res.ColorStateList;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class a extends FrameLayout {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final d f438c = new b();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f439a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f440b;

    public ColorStateList getCardBackgroundColor() {
        throw null;
    }

    public float getCardElevation() {
        throw null;
    }

    public int getContentPaddingBottom() {
        throw null;
    }

    public int getContentPaddingLeft() {
        throw null;
    }

    public int getContentPaddingRight() {
        throw null;
    }

    public int getContentPaddingTop() {
        throw null;
    }

    public float getMaxCardElevation() {
        return ((b) f438c).b(null);
    }

    public boolean getPreventCornerOverlap() {
        return this.f440b;
    }

    public float getRadius() {
        return ((b) f438c).c(null);
    }

    public boolean getUseCompatPadding() {
        return this.f439a;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public void setCardBackgroundColor(int i) {
        ((b) f438c).d(null, ColorStateList.valueOf(i));
        throw null;
    }

    public void setCardBackgroundColor(ColorStateList colorStateList) {
        ((b) f438c).d(null, colorStateList);
        throw null;
    }

    public void setCardElevation(float f2) {
        throw null;
    }

    public void setMaxCardElevation(float f2) {
        ((b) f438c).e(null, f2);
    }

    @Override // android.view.View
    public void setMinimumHeight(int i) {
        super.setMinimumHeight(i);
    }

    @Override // android.view.View
    public void setMinimumWidth(int i) {
        super.setMinimumWidth(i);
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
    }

    @Override // android.view.View
    public void setPaddingRelative(int i, int i2, int i3, int i4) {
    }

    public void setPreventCornerOverlap(boolean z) {
        if (z == this.f440b) {
            return;
        }
        this.f440b = z;
        throw null;
    }

    public void setRadius(float f2) {
        throw null;
    }

    public void setUseCompatPadding(boolean z) {
        if (this.f439a == z) {
            return;
        }
        this.f439a = z;
        throw null;
    }
}
