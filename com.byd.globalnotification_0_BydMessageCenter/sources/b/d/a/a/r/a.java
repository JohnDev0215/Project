package b.d.a.a.r;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import android.widget.FrameLayout;
import b.d.a.a.b;
import b.d.a.a.e0.j;
import b.d.a.a.e0.n;

/* JADX INFO: loaded from: classes.dex */
public class a extends a.d.a.a implements Checkable, n {
    public static final int[] g = {R.attr.state_checkable};
    public static final int[] h = {R.attr.state_checked};
    public static final int[] i = {b.state_dragged};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2050d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2051e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public InterfaceC0041a f2052f;

    /* JADX INFO: renamed from: b.d.a.a.r.a$a, reason: collision with other inner class name */
    public interface InterfaceC0041a {
    }

    private RectF getBoundsAsRectF() {
        new RectF();
        throw null;
    }

    @Override // a.d.a.a
    public ColorStateList getCardBackgroundColor() {
        throw null;
    }

    public ColorStateList getCardForegroundColor() {
        throw null;
    }

    public float getCardViewRadius() {
        return super.getRadius();
    }

    public Drawable getCheckedIcon() {
        throw null;
    }

    public int getCheckedIconMargin() {
        throw null;
    }

    public int getCheckedIconSize() {
        throw null;
    }

    public ColorStateList getCheckedIconTint() {
        throw null;
    }

    @Override // a.d.a.a
    public int getContentPaddingBottom() {
        throw null;
    }

    @Override // a.d.a.a
    public int getContentPaddingLeft() {
        throw null;
    }

    @Override // a.d.a.a
    public int getContentPaddingRight() {
        throw null;
    }

    @Override // a.d.a.a
    public int getContentPaddingTop() {
        throw null;
    }

    public float getProgress() {
        throw null;
    }

    @Override // a.d.a.a
    public float getRadius() {
        throw null;
    }

    public ColorStateList getRippleColor() {
        throw null;
    }

    public j getShapeAppearanceModel() {
        throw null;
    }

    @Deprecated
    public int getStrokeColor() {
        throw null;
    }

    public ColorStateList getStrokeColorStateList() {
        throw null;
    }

    public int getStrokeWidth() {
        throw null;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.f2050d;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        throw null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i2) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i2 + 3);
        if (isChecked()) {
            FrameLayout.mergeDrawableStates(iArrOnCreateDrawableState, h);
        }
        if (this.f2051e) {
            FrameLayout.mergeDrawableStates(iArrOnCreateDrawableState, i);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.cardview.widget.CardView");
        accessibilityEvent.setChecked(isChecked());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.cardview.widget.CardView");
        accessibilityNodeInfo.setCheckable(false);
        accessibilityNodeInfo.setClickable(isClickable());
        accessibilityNodeInfo.setChecked(isChecked());
    }

    @Override // a.d.a.a, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        getMeasuredWidth();
        getMeasuredHeight();
        throw null;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
    }

    public void setBackgroundInternal(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    @Override // a.d.a.a
    public void setCardBackgroundColor(int i2) {
        ColorStateList.valueOf(i2);
        throw null;
    }

    @Override // a.d.a.a
    public void setCardBackgroundColor(ColorStateList colorStateList) {
        throw null;
    }

    @Override // a.d.a.a
    public void setCardElevation(float f2) {
        super.setCardElevation(f2);
        throw null;
    }

    public void setCardForegroundColor(ColorStateList colorStateList) {
        throw null;
    }

    public void setCheckable(boolean z) {
        throw null;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.f2050d != z) {
            toggle();
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        throw null;
    }

    public void setCheckedIconMargin(int i2) {
        throw null;
    }

    public void setCheckedIconMarginResource(int i2) {
        if (i2 == -1) {
            return;
        }
        getResources().getDimensionPixelSize(i2);
        throw null;
    }

    public void setCheckedIconResource(int i2) {
        a.b.l.a.a.b(getContext(), i2);
        throw null;
    }

    public void setCheckedIconSize(int i2) {
        throw null;
    }

    public void setCheckedIconSizeResource(int i2) {
        if (i2 == 0) {
            return;
        }
        getResources().getDimensionPixelSize(i2);
        throw null;
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        throw null;
    }

    @Override // android.view.View
    public void setClickable(boolean z) {
        super.setClickable(z);
    }

    public void setDragged(boolean z) {
        if (this.f2051e == z) {
            return;
        }
        this.f2051e = z;
        refreshDrawableState();
        throw null;
    }

    @Override // a.d.a.a
    public void setMaxCardElevation(float f2) {
        super.setMaxCardElevation(f2);
        throw null;
    }

    public void setOnCheckedChangeListener(InterfaceC0041a interfaceC0041a) {
        this.f2052f = interfaceC0041a;
    }

    @Override // a.d.a.a
    public void setPreventCornerOverlap(boolean z) {
        super.setPreventCornerOverlap(z);
        throw null;
    }

    public void setProgress(float f2) {
        throw null;
    }

    @Override // a.d.a.a
    public void setRadius(float f2) {
        super.setRadius(f2);
        throw null;
    }

    public void setRippleColor(ColorStateList colorStateList) {
        throw null;
    }

    public void setRippleColorResource(int i2) {
        a.b.l.a.a.a(getContext(), i2);
        throw null;
    }

    @Override // b.d.a.a.e0.n
    public void setShapeAppearanceModel(j jVar) {
        setClipToOutline(jVar.d(getBoundsAsRectF()));
        throw null;
    }

    public void setStrokeColor(int i2) {
        ColorStateList.valueOf(i2);
        throw null;
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        throw null;
    }

    public void setStrokeWidth(int i2) {
        throw null;
    }

    @Override // a.d.a.a
    public void setUseCompatPadding(boolean z) {
        super.setUseCompatPadding(z);
        throw null;
    }

    @Override // android.widget.Checkable
    public void toggle() {
    }
}
