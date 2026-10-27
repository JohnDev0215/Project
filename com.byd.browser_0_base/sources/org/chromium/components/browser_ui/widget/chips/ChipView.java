package org.chromium.components.browser_ui.widget.chips;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import defpackage.bc2;
import defpackage.bz;
import defpackage.c6;
import defpackage.in3;
import defpackage.nn3;
import defpackage.om3;
import defpackage.z92;
import java.util.WeakHashMap;
import org.chromium.components.browser_ui.widget.R$attr;
import org.chromium.components.browser_ui.widget.R$color;
import org.chromium.components.browser_ui.widget.R$dimen;
import org.chromium.components.browser_ui.widget.R$style;
import org.chromium.components.browser_ui.widget.R$styleable;
import org.chromium.ui.widget.ChromeImageView;
import org.chromium.ui.widget.LoadingView;

/* JADX INFO: loaded from: classes2.dex */
public class ChipView extends LinearLayout {
    public final bc2 e;
    public final AppCompatTextView f;
    public final ChromeImageView g;
    public final LoadingView h;
    public final int i;
    public final int j;
    public AppCompatTextView k;
    public int l;

    public ChipView(Context context, int i) {
        this(new ContextThemeWrapper(context, i), null, R$attr.chipStyle, 0);
    }

    private void setTint(boolean z) {
        AppCompatTextView appCompatTextView = this.f;
        ColorStateList textColors = appCompatTextView.getTextColors();
        ChromeImageView chromeImageView = this.g;
        if (textColors == null || !z) {
            chromeImageView.setImageTintList(null);
        } else {
            chromeImageView.setImageTintList(appCompatTextView.getTextColors());
        }
    }

    public final void a(int i, boolean z) {
        ChromeImageView chromeImageView = this.g;
        if (i == -1) {
            chromeImageView.setVisibility(8);
            return;
        }
        chromeImageView.setVisibility(0);
        chromeImageView.setImageResource(i);
        setTint(z);
    }

    public int getCornerRadius() {
        return this.j;
    }

    public TextView getPrimaryTextView() {
        return this.f;
    }

    public TextView getSecondaryTextView() {
        if (this.k == null) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(new ContextThemeWrapper(getContext(), R$style.ChipTextView));
            this.k = appCompatTextView;
            c6.f(appCompatTextView, this.i);
            this.k.setSelected(isSelected());
            this.k.setEnabled(isEnabled());
            addView(this.k);
        }
        return this.k;
    }

    public z92 getStartIconViewRect() {
        return new nn3(this.g);
    }

    @Override // android.view.View
    public final boolean isFocused() {
        return super.isFocused() || (isSelected() && !isInTouchMode());
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int i3 = this.l;
        if (measuredWidth > i3) {
            int paddingLeft = (i3 - getPaddingLeft()) - getPaddingRight();
            ChromeImageView chromeImageView = this.g;
            int measuredWidth2 = 0;
            int measuredWidth3 = paddingLeft - ((chromeImageView == null || chromeImageView.getVisibility() == 8) ? 0 : chromeImageView.getMeasuredWidth());
            AppCompatTextView appCompatTextView = this.k;
            if (appCompatTextView != null && appCompatTextView.getVisibility() != 8) {
                measuredWidth2 = this.k.getMeasuredWidth();
            }
            int i4 = measuredWidth3 - measuredWidth2;
            AppCompatTextView appCompatTextView2 = this.f;
            if (i4 > 0) {
                appCompatTextView2.setMaxWidth(i4);
                appCompatTextView2.setEllipsize(TextUtils.TruncateAt.END);
            } else {
                AppCompatTextView appCompatTextView3 = this.k;
                if (appCompatTextView3 == null || appCompatTextView3.getVisibility() == 8) {
                    return;
                } else {
                    appCompatTextView2.setVisibility(8);
                }
            }
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(this.l, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), i2);
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.e.c.setColor(i);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        bc2 bc2Var = this.e;
        if (colorStateList == bc2Var.a) {
            return;
        }
        bc2Var.a = colorStateList;
        bc2Var.c.setColor(colorStateList);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        getPrimaryTextView().setEnabled(z);
        this.g.setEnabled(z);
        AppCompatTextView appCompatTextView = this.k;
        if (appCompatTextView != null) {
            appCompatTextView.setEnabled(z);
        }
    }

    public void setMaxWidth(int i) {
        this.l = i;
    }

    public void setRemoveIconClickListener(View.OnClickListener onClickListener) {
        throw null;
    }

    public ChipView(Context context, AttributeSet attributeSet) {
        this(new ContextThemeWrapper(context, R$style.SuggestionChipThemeOverlay), attributeSet, R$attr.chipStyle, 0);
    }

    public ChipView(Context context, AttributeSet attributeSet, int i, int i2) {
        int dimensionPixelSize;
        int dimensionPixelSize2;
        super(context, attributeSet, i, i2);
        this.l = Integer.MAX_VALUE;
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ChipView, i, i2);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ChipView_extendLateralPadding, false);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ChipView_reduceEndPadding, false);
        if (z) {
            dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.chip_element_extended_leading_padding);
        } else {
            dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.chip_element_leading_padding);
        }
        if (z2) {
            dimensionPixelSize2 = getResources().getDimensionPixelSize(R$dimen.chip_reduced_end_padding);
        } else {
            dimensionPixelSize2 = getResources().getDimensionPixelSize(R$dimen.chip_end_padding);
        }
        if (z) {
            getResources().getDimensionPixelSize(R$dimen.chip_end_icon_extended_margin_start);
        } else {
            getResources().getDimensionPixelSize(R$dimen.chip_end_icon_margin_start);
        }
        if (z) {
            getResources().getDimensionPixelSize(R$dimen.chip_extended_end_padding_with_end_icon);
        } else {
            getResources().getDimensionPixelSize(R$dimen.chip_end_padding_with_end_icon);
        }
        int i3 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ChipView_solidColorChip, false) ? R$dimen.chip_solid_border_width : R$dimen.chip_border_width;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.ChipView_chipColor, R$color.chip_background_color);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R$styleable.ChipView_chipStateLayerColor, R$color.chip_state_layer_color);
        int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(R$styleable.ChipView_rippleColor, R$color.chip_ripple_color);
        int resourceId4 = typedArrayObtainStyledAttributes.getResourceId(R$styleable.ChipView_chipStrokeColor, R$color.chip_stroke_color);
        int dimensionPixelSize3 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ChipView_cornerRadius, getContext().getResources().getDimensionPixelSize(R$dimen.chip_corner_radius));
        this.j = dimensionPixelSize3;
        int i4 = R$styleable.ChipView_iconWidth;
        Resources resources = getResources();
        int i5 = R$dimen.chip_icon_size;
        int dimensionPixelSize4 = typedArrayObtainStyledAttributes.getDimensionPixelSize(i4, resources.getDimensionPixelSize(i5));
        int dimensionPixelSize5 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ChipView_iconHeight, getResources().getDimensionPixelSize(i5));
        boolean z3 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ChipView_useRoundedIcon, false);
        int i6 = R$styleable.ChipView_primaryTextAppearance;
        int i7 = R$style.TextAppearance_ChipText;
        int resourceId5 = typedArrayObtainStyledAttributes.getResourceId(i6, i7);
        int i8 = dimensionPixelSize;
        typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ChipView_endIconWidth, getResources().getDimensionPixelSize(i5));
        typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ChipView_endIconHeight, getResources().getDimensionPixelSize(i5));
        this.i = typedArrayObtainStyledAttributes.getResourceId(R$styleable.ChipView_secondaryTextAppearance, i7);
        int dimensionPixelSize6 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ChipView_verticalInset, getResources().getDimensionPixelSize(R$dimen.chip_bg_vertical_inset));
        boolean z4 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ChipView_allowMultipleLines, false);
        int dimensionPixelSize7 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ChipView_multiLineVerticalPadding, getResources().getDimensionPixelSize(R$dimen.chip_text_multiline_vertical_padding));
        boolean z5 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ChipView_textAlignStart, false);
        boolean z6 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ChipView_reduceTextStartPadding, false);
        typedArrayObtainStyledAttributes.recycle();
        ChromeImageView chromeImageView = new ChromeImageView(getContext());
        this.g = chromeImageView;
        chromeImageView.setLayoutParams(new LinearLayout.LayoutParams(dimensionPixelSize4, dimensionPixelSize5));
        addView(chromeImageView);
        int dimensionPixelOffset = z3 ? (getResources().getDimensionPixelOffset(R$dimen.chip_default_height) - dimensionPixelSize5) / 2 : i8;
        int dimensionPixelSize8 = getResources().getDimensionPixelSize(R$dimen.chip_loading_view_size);
        int i9 = (dimensionPixelSize5 - dimensionPixelSize8) / 2;
        int i10 = (dimensionPixelSize4 - dimensionPixelSize8) / 2;
        LoadingView loadingView = new LoadingView(getContext());
        this.h = loadingView;
        loadingView.setVisibility(8);
        loadingView.setIndeterminateTintList(ColorStateList.valueOf(getContext().getColor(R$color.default_icon_color_accent1_baseline)));
        loadingView.setPaddingRelative(i10, i9, i10, i9);
        addView(loadingView, new LinearLayout.LayoutParams(dimensionPixelSize4, dimensionPixelSize5));
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.e.i(this, dimensionPixelOffset, 0, dimensionPixelSize2, 0);
        AppCompatTextView appCompatTextView = new AppCompatTextView(new ContextThemeWrapper(getContext(), R$style.ChipTextView));
        this.f = appCompatTextView;
        appCompatTextView.setTextAppearance(appCompatTextView.getContext(), resourceId5);
        if (z4) {
            appCompatTextView.setMaxLines(2);
            appCompatTextView.setPaddingRelative(appCompatTextView.getPaddingStart(), dimensionPixelSize7, appCompatTextView.getPaddingEnd(), dimensionPixelSize7);
        }
        if (z5) {
            appCompatTextView.setTextAlignment(5);
        }
        if (z6) {
            appCompatTextView.setPaddingRelative(getResources().getDimensionPixelSize(R$dimen.chip_text_reduced_leading_padding), appCompatTextView.getPaddingTop(), appCompatTextView.getPaddingEnd(), appCompatTextView.getPaddingBottom());
        }
        addView(appCompatTextView);
        float f = dimensionPixelSize3;
        bc2 bc2Var = new bc2(this, resourceId, resourceId3, new float[]{f, f, f, f, f, f, f, f}, resourceId4, i3, dimensionPixelSize6);
        ColorStateList colorStateListB = bz.b(getContext(), resourceId2);
        if (colorStateListB != bc2Var.b) {
            bc2Var.b = colorStateListB;
            bc2Var.d.setColor(colorStateListB);
        }
        this.e = bc2Var;
        a(-1, false);
    }
}
