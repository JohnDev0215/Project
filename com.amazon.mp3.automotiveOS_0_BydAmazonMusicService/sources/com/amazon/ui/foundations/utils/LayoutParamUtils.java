package com.amazon.ui.foundations.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LayoutParamUtils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J_\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u0010J \u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002¨\u0006\u0013"}, d2 = {"Lcom/amazon/ui/foundations/utils/LayoutParamUtils;", "", "()V", "setLayoutMargins", "", "view", "Landroid/view/View;", "width", "", "height", "overrideParams", "", "left", "top", "right", "bottom", "(Landroid/view/View;IILjava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "setupLayoutParams", "Landroid/view/ViewParent;", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LayoutParamUtils {
    public static final LayoutParamUtils INSTANCE = new LayoutParamUtils();

    private LayoutParamUtils() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setLayoutMargins(View view, int width, int height, Boolean overrideParams, Integer left, Integer top, Integer right, Integer bottom) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (view.getLayoutParams() == null || Intrinsics.areEqual(overrideParams, Boolean.TRUE)) {
            if (view instanceof ViewParent) {
                setupLayoutParams((ViewParent) view, width, height);
            } else {
                ViewParent parent = view.getParent();
                Intrinsics.checkNotNullExpressionValue(parent, "view.parent");
                setupLayoutParams(parent, width, height);
            }
        } else {
            view.getLayoutParams().height = height;
            view.getLayoutParams().width = width;
        }
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            if (left != null) {
                int iIntValue = left.intValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                if (layoutParams == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
                }
                ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = iIntValue;
            }
            if (top != null) {
                int iIntValue2 = top.intValue();
                ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
                if (layoutParams2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
                }
                ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin = iIntValue2;
            }
            if (right != null) {
                int iIntValue3 = right.intValue();
                ViewGroup.LayoutParams layoutParams3 = view.getLayoutParams();
                if (layoutParams3 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
                }
                ((ViewGroup.MarginLayoutParams) layoutParams3).rightMargin = iIntValue3;
            }
            if (bottom == null) {
                return;
            }
            int iIntValue4 = bottom.intValue();
            ViewGroup.LayoutParams layoutParams4 = view.getLayoutParams();
            if (layoutParams4 == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            }
            ((ViewGroup.MarginLayoutParams) layoutParams4).bottomMargin = iIntValue4;
        }
    }

    private final void setupLayoutParams(ViewParent view, int width, int height) {
        if (view instanceof ConstraintLayout) {
            ((ConstraintLayout) view).setLayoutParams(new ConstraintLayout.LayoutParams(width, height));
            return;
        }
        if (view instanceof LinearLayout) {
            ((LinearLayout) view).setLayoutParams(new LinearLayout.LayoutParams(width, height));
        } else if (view instanceof RelativeLayout) {
            ((RelativeLayout) view).setLayoutParams(new RelativeLayout.LayoutParams(width, height));
        } else if (view instanceof ViewGroup) {
            ((ViewGroup) view).setLayoutParams(new ViewGroup.MarginLayoutParams(width, height));
        }
    }
}
