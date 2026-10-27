package org.chromium.chrome.browser.omnibox;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import defpackage.go2;
import defpackage.ro2;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.ui.base.LocalizationUtils;

/* JADX INFO: loaded from: classes2.dex */
public class LocationBarTablet extends LocationBarLayout implements ro2 {
    public float A;
    public float B;
    public final go2 C;
    public View r;
    public View s;
    public View t;
    public View[] u;
    public final Rect v;
    public final int w;
    public final int x;
    public final int y;
    public float z;

    public LocationBarTablet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.v = new Rect();
        this.w = getResources().getDimensionPixelOffset(R$dimen.toolbar_button_width) * 3;
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R$dimen.location_bar_icon_width);
        this.x = dimensionPixelOffset;
        this.y = dimensionPixelOffset;
        go2 go2Var = new go2(this);
        this.C = go2Var;
        go2Var.K(attributeSet, 0);
    }

    @Override // defpackage.ro2
    public final void e() {
        go2 go2Var = this.C;
        if (go2Var != null) {
            go2Var.J();
        }
    }

    @Deprecated
    public View getBookmarkButtonForAnimation() {
        return this.s;
    }

    public float getLensButtonAlpha() {
        return this.g.getAlpha();
    }

    @Deprecated
    public View getLensButtonForAnimation() {
        return this.g;
    }

    public float getMicButtonAlpha() {
        return this.f.getAlpha();
    }

    @Deprecated
    public View getMicButtonForAnimation() {
        return this.f;
    }

    @Deprecated
    public View getSaveOfflineButtonForAnimation() {
        return this.t;
    }

    public float getWidthChangeFraction() {
        return this.z;
    }

    @Override // org.chromium.chrome.browser.omnibox.LocationBarLayout, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.r = findViewById(R$id.location_bar_status_icon);
        this.s = findViewById(R$id.bookmark_button);
        this.t = findViewById(R$id.save_offline_button);
        LinearLayout linearLayout = this.p;
        boolean z = linearLayout.getLayoutDirection() == 1;
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.location_bar_url_action_padding);
        int paddingLeft = z ? dimensionPixelSize : linearLayout.getPaddingLeft();
        int paddingTop = linearLayout.getPaddingTop();
        if (z) {
            dimensionPixelSize = linearLayout.getRight();
        }
        linearLayout.setPadding(paddingLeft, paddingTop, dimensionPixelSize, linearLayout.getPaddingBottom());
        this.u = new View[]{this.h, this.e};
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.A = i;
        this.B = i3;
    }

    @Override // org.chromium.chrome.browser.omnibox.LocationBarLayout, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i, i2);
        if (getMeasuredWidth() != measuredWidth) {
            setUnfocusedWidth(getMeasuredWidth());
            super.onMeasure(i, i2);
        }
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        View[] viewArr = this.u;
        if (viewArr == null) {
            return true;
        }
        View view = null;
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        for (View view2 : viewArr) {
            if (view2.isShown()) {
                int width = view2.getWidth();
                int height = view2.getHeight();
                Rect rect = this.v;
                rect.set(0, 0, width, height);
                offsetDescendantRectToMyCoords(view2, rect);
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                float f4 = rect.left;
                float f5 = rect.right;
                float f6 = x < f4 ? f4 - x : x > f5 ? f5 - x : 0.0f;
                float f7 = rect.top;
                float f8 = rect.bottom;
                float f9 = y < f7 ? f7 - y : y > f8 ? f8 - y : 0.0f;
                float fAbs = Math.abs(f9) + Math.abs(f6);
                if (view == null || fAbs < f3) {
                    f = x + f6;
                    f2 = y + f9;
                    view = view2;
                    f3 = fAbs;
                }
            }
        }
        if (view == null) {
            return false;
        }
        motionEvent.setLocation(f, f2);
        return view.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        go2 go2Var = this.C;
        if (go2Var != null) {
            go2Var.L(i);
        }
    }

    public void setBookmarkButtonVisibility(boolean z) {
        this.s.setVisibility(z ? 0 : 8);
    }

    public void setWidthChangeAnimationFraction(float f) {
        this.z = f;
        float f2 = (this.w + 0) * f;
        if (LocalizationUtils.isLayoutRtl()) {
            setRight((int) (this.B + f2));
        } else {
            setLeft((int) (this.A - f2));
        }
        int i = (int) (this.x * f);
        if (this.g.getVisibility() == 0) {
            i += (int) (this.y * f);
        }
        int i2 = (int) f2;
        int layoutDirection = getLayoutDirection();
        ImageButton imageButton = this.e;
        if (layoutDirection == 1) {
            float f3 = i2;
            this.r.setTranslationX(f3);
            this.h.setTranslationX(f3);
            if (imageButton.getVisibility() == 0) {
                imageButton.setTranslationX(-i);
                return;
            }
            return;
        }
        if (this.t.getVisibility() == 0) {
            this.t.setTranslationX(i2);
        } else {
            this.f.setTranslationX(i2);
        }
        if (imageButton.getVisibility() == 0) {
            imageButton.setTranslationX(i2 + i);
        } else {
            this.s.setTranslationX(i2);
        }
    }
}
