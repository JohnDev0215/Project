package org.chromium.chrome.browser.omnibox;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import defpackage.cw;
import defpackage.et2;
import defpackage.jg2;
import defpackage.pr1;
import defpackage.wt2;
import defpackage.xt2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.omnibox.status.StatusView;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: loaded from: classes2.dex */
public class LocationBarLayout extends FrameLayout {
    public final ImageButton e;
    public final ImageButton f;
    public final ImageButton g;
    public final UrlBar h;
    public final View i;
    public final View j;
    public h k;
    public org.chromium.chrome.browser.omnibox.suggestions.c l;
    public f m;
    public et2 n;
    public boolean o;
    public final LinearLayout p;
    public final cw q;

    public LocationBarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$layout.location_bar);
        cw cwVar = new cw(this);
        this.q = cwVar;
        setTouchDelegate(cwVar);
    }

    private int getUrlContainerMarginEnd() {
        Iterator<View> it = getUrlContainerViewsForMargin().iterator();
        int endPaddingPixelSizeOnFocusDelta = 0;
        int marginEnd = 0;
        while (it.hasNext()) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) it.next().getLayoutParams();
            marginEnd += marginLayoutParams.getMarginEnd() + marginLayoutParams.getMarginStart() + marginLayoutParams.width;
        }
        LinearLayout linearLayout = this.p;
        if (linearLayout != null && linearLayout.getVisibility() == 0) {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) linearLayout.getLayoutParams();
            marginEnd += marginLayoutParams2.getMarginEnd() + marginLayoutParams2.getMarginStart();
        }
        if ((this.n.e.j.getIconVisibility() == 0) && this.n.f.d()) {
            endPaddingPixelSizeOnFocusDelta = getEndPaddingPixelSizeOnFocusDelta();
        }
        int i = marginEnd + endPaddingPixelSizeOnFocusDelta;
        return DeviceFormFactor.a(getContext()) ? i + getResources().getDimensionPixelSize(R$dimen.location_bar_url_action_padding) : i;
    }

    public void a(org.chromium.chrome.browser.omnibox.suggestions.c cVar, h hVar, et2 et2Var, f fVar, jg2 jg2Var) {
        this.l = cVar;
        this.k = hVar;
        this.n = et2Var;
        this.m = fVar;
    }

    public void c() {
        this.o = true;
    }

    public org.chromium.chrome.browser.omnibox.suggestions.c getAutocompleteCoordinator() {
        return this.l;
    }

    public int getEndPaddingPixelSizeOnFocusDelta() {
        return getResources().getDimensionPixelSize(R$dimen.location_bar_icon_end_padding_focused) - getResources().getDimensionPixelSize(R$dimen.location_bar_icon_end_padding);
    }

    public View getSecurityIconView() {
        return this.n.e.getSecurityView();
    }

    public et2 getStatusCoordinatorForTesting() {
        return this.n;
    }

    public List<View> getUrlContainerViewsForMargin() {
        ArrayList arrayList = new ArrayList();
        LinearLayout linearLayout = this.p;
        if (linearLayout == null) {
            return arrayList;
        }
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View childAt = linearLayout.getChildAt(i);
            if (childAt.getVisibility() != 8) {
                arrayList.add(childAt);
            }
        }
        return arrayList;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        setLayoutTransition(null);
        ((StatusView) findViewById(R$id.location_bar_status)).setCompositeTouchDelegate(this.q);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        UrlBar urlBar;
        int i3 = 0;
        int measuredWidth = 0;
        while (true) {
            int childCount = getChildCount();
            urlBar = this.h;
            if (i3 >= childCount) {
                break;
            }
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                if (layoutParams.getMarginStart() != measuredWidth) {
                    layoutParams.setMarginStart(measuredWidth);
                    childAt.setLayoutParams(layoutParams);
                }
                if (childAt == urlBar) {
                    break;
                }
                int i4 = layoutParams.width;
                int iMakeMeasureSpec = i4 == -2 ? View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), Integer.MIN_VALUE) : i4 == -1 ? View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED) : View.MeasureSpec.makeMeasureSpec(i4, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                int i5 = layoutParams.height;
                childAt.measure(iMakeMeasureSpec, i5 == -2 ? View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), Integer.MIN_VALUE) : i5 == -1 ? View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED) : View.MeasureSpec.makeMeasureSpec(i5, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
                measuredWidth = childAt.getMeasuredWidth() + measuredWidth;
            }
            i3++;
        }
        int urlContainerMarginEnd = getUrlContainerMarginEnd();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) urlBar.getLayoutParams();
        if (marginLayoutParams.getMarginEnd() != urlContainerMarginEnd) {
            marginLayoutParams.setMarginEnd(urlContainerMarginEnd);
            urlBar.setLayoutParams(marginLayoutParams);
        }
        super.onMeasure(i, i2);
    }

    public void setDeleteButtonTint(ColorStateList colorStateList) {
        this.e.setImageTintList(colorStateList);
    }

    public void setDeleteButtonVisibility(boolean z) {
        this.e.setVisibility(z ? 0 : 8);
    }

    public void setLensButtonTint(ColorStateList colorStateList) {
        this.g.setImageTintList(colorStateList);
    }

    public void setLensButtonVisibility(boolean z) {
        this.g.setVisibility(z ? 0 : 8);
    }

    public void setMicButtonDrawable(Drawable drawable) {
        this.f.setImageDrawable(drawable);
    }

    public void setMicButtonTint(ColorStateList colorStateList) {
        this.f.setImageTintList(colorStateList);
    }

    public void setMicButtonVisibility(boolean z) {
        this.f.setVisibility(z ? 0 : 8);
    }

    public void setStatusCoordinatorForTesting(et2 et2Var) {
        this.n = et2Var;
    }

    public void setStatusViewLeftSpacePercent(float f) {
        if (pr1.e(getContext())) {
            View view = this.i;
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = (int) (getResources().getDimensionPixelSize(R$dimen.location_bar_status_view_left_space_width) * f);
            view.setLayoutParams(layoutParams);
        }
    }

    public void setStatusViewRightSpacePercent(float f) {
        if (DeviceFormFactor.a(getContext())) {
            return;
        }
        View view = this.j;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (int) (getEndPaddingPixelSizeOnFocusDelta() * f);
        view.setLayoutParams(layoutParams);
    }

    public void setUnfocusedWidth(int i) {
        wt2 wt2Var = this.n.f;
        int i2 = (i - wt2Var.p) - wt2Var.q;
        boolean z = i >= wt2Var.r;
        if (z) {
            wt2Var.e.n(xt2.p, i2);
        }
        if (z != wt2Var.k) {
            wt2Var.k = z;
            wt2Var.h();
        }
    }

    public void setUrlActionContainerVisibility(int i) {
        this.p.setVisibility(i);
    }

    public void setUrlFocusChangePercent(float f) {
        setStatusViewLeftSpacePercent(f);
        setStatusViewRightSpacePercent(f);
    }

    public LocationBarLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(i, (ViewGroup) this, true);
        this.e = (ImageButton) findViewById(R$id.delete_button);
        this.h = (UrlBar) findViewById(R$id.url_bar);
        this.f = (ImageButton) findViewById(R$id.mic_button);
        this.g = (ImageButton) findViewById(R$id.lens_camera_button);
        this.p = (LinearLayout) findViewById(R$id.url_action_container);
        this.i = findViewById(R$id.location_bar_status_view_left_space);
        this.j = findViewById(R$id.location_bar_status_view_right_space);
    }

    public void b() {
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
    }
}
