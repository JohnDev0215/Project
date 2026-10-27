package b.a.a.a.p;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import android.widget.FrameLayout;
import b.a.a.a.b0.n;
import b.a.a.a.k;

/* JADX INFO: loaded from: classes.dex */
public class a extends a.c.a.a implements Checkable, n {
    private static final int[] j = {R.attr.state_checkable};
    private static final int[] k = {R.attr.state_checked};
    private static final int[] l = {b.a.a.a.b.w};
    private static final int m = k.n;
    private final b n;
    private boolean o;
    private boolean p;
    private boolean q;
    private InterfaceC0051a r;

    /* JADX INFO: renamed from: b.a.a.a.p.a$a, reason: collision with other inner class name */
    public interface InterfaceC0051a {
        void a(a aVar, boolean z);
    }

    private void d() {
        if (Build.VERSION.SDK_INT > 26) {
            throw null;
        }
    }

    private RectF getBoundsAsRectF() {
        new RectF();
        throw null;
    }

    public boolean e() {
        if (this.n == null) {
            return false;
        }
        throw null;
    }

    public boolean f() {
        return this.q;
    }

    @Override // a.c.a.a
    public ColorStateList getCardBackgroundColor() {
        throw null;
    }

    public ColorStateList getCardForegroundColor() {
        throw null;
    }

    float getCardViewRadius() {
        return super.getRadius();
    }

    public Drawable getCheckedIcon() {
        throw null;
    }

    public ColorStateList getCheckedIconTint() {
        throw null;
    }

    @Override // a.c.a.a
    public int getContentPaddingBottom() {
        throw null;
    }

    @Override // a.c.a.a
    public int getContentPaddingLeft() {
        throw null;
    }

    @Override // a.c.a.a
    public int getContentPaddingRight() {
        throw null;
    }

    @Override // a.c.a.a
    public int getContentPaddingTop() {
        throw null;
    }

    public float getProgress() {
        throw null;
    }

    @Override // a.c.a.a
    public float getRadius() {
        throw null;
    }

    public ColorStateList getRippleColor() {
        throw null;
    }

    public b.a.a.a.b0.k getShapeAppearanceModel() {
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
        return this.p;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        throw null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 3);
        if (e()) {
            FrameLayout.mergeDrawableStates(iArrOnCreateDrawableState, j);
        }
        if (isChecked()) {
            FrameLayout.mergeDrawableStates(iArrOnCreateDrawableState, k);
        }
        if (f()) {
            FrameLayout.mergeDrawableStates(iArrOnCreateDrawableState, l);
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
        accessibilityNodeInfo.setCheckable(e());
        accessibilityNodeInfo.setClickable(isClickable());
        accessibilityNodeInfo.setChecked(isChecked());
    }

    @Override // a.c.a.a, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
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
        if (this.o) {
            throw null;
        }
    }

    void setBackgroundInternal(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    @Override // a.c.a.a
    public void setCardBackgroundColor(int i) {
        ColorStateList.valueOf(i);
        throw null;
    }

    @Override // a.c.a.a
    public void setCardBackgroundColor(ColorStateList colorStateList) {
        throw null;
    }

    @Override // a.c.a.a
    public void setCardElevation(float f) {
        super.setCardElevation(f);
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
        if (this.p != z) {
            toggle();
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        throw null;
    }

    public void setCheckedIconResource(int i) {
        a.a.k.a.a.d(getContext(), i);
        throw null;
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        throw null;
    }

    @Override // android.view.View
    public void setClickable(boolean z) {
        super.setClickable(z);
        if (this.n != null) {
            throw null;
        }
    }

    public void setDragged(boolean z) {
        if (this.q != z) {
            this.q = z;
            refreshDrawableState();
            d();
            invalidate();
        }
    }

    @Override // a.c.a.a
    public void setMaxCardElevation(float f) {
        super.setMaxCardElevation(f);
        throw null;
    }

    public void setOnCheckedChangeListener(InterfaceC0051a interfaceC0051a) {
        this.r = interfaceC0051a;
    }

    @Override // a.c.a.a
    public void setPreventCornerOverlap(boolean z) {
        super.setPreventCornerOverlap(z);
        throw null;
    }

    public void setProgress(float f) {
        throw null;
    }

    @Override // a.c.a.a
    public void setRadius(float f) {
        super.setRadius(f);
        throw null;
    }

    public void setRippleColor(ColorStateList colorStateList) {
        throw null;
    }

    public void setRippleColorResource(int i) {
        a.a.k.a.a.c(getContext(), i);
        throw null;
    }

    @Override // b.a.a.a.b0.n
    public void setShapeAppearanceModel(b.a.a.a.b0.k kVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(kVar.u(getBoundsAsRectF()));
        }
        throw null;
    }

    public void setStrokeColor(int i) {
        ColorStateList.valueOf(i);
        throw null;
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        throw null;
    }

    public void setStrokeWidth(int i) {
        throw null;
    }

    @Override // a.c.a.a
    public void setUseCompatPadding(boolean z) {
        super.setUseCompatPadding(z);
        throw null;
    }

    @Override // android.widget.Checkable
    public void toggle() {
        if (e() && isEnabled()) {
            this.p = !this.p;
            refreshDrawableState();
            d();
            InterfaceC0051a interfaceC0051a = this.r;
            if (interfaceC0051a != null) {
                interfaceC0051a.a(this, this.p);
            }
        }
    }
}
