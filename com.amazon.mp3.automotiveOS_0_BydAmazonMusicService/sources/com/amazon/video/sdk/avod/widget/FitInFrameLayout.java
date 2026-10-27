package com.amazon.video.sdk.avod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes5.dex */
public class FitInFrameLayout extends FrameLayout {

    private enum Alignment {
        START,
        CENTER,
        END
    }

    public FitInFrameLayout(Context context) {
        super(context);
    }

    public FitInFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.ViewGroup
    protected void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        measureChild(view, i, i3);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        layoutChildrenToFit(i, i2, i3, i4);
    }

    void layoutChildrenToFit(int i, int i2, int i3, int i4) {
        int paddingLeft = ((i3 - i) - getPaddingLeft()) - getPaddingRight();
        int iMax = Math.max(0, paddingLeft);
        int iMax2 = Math.max(0, ((i4 - i2) - getPaddingTop()) - getPaddingBottom());
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                layoutChildToFit(childAt, iMax, iMax2);
            }
        }
    }

    private void layoutChildToFit(@Nonnull View view, @Nonnegative int i, @Nonnegative int i2) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        int iMax = Math.max(0, view.getMeasuredWidth());
        int startOffset = getStartOffset(getAlignmentFromHorizGravity(layoutParams, getViewLayoutDirection()), layoutParams.leftMargin, layoutParams.rightMargin, i, iMax);
        int iMax2 = Math.max(0, view.getMeasuredHeight());
        int startOffset2 = getStartOffset(getAlignmentFromVertGravity(layoutParams), layoutParams.topMargin, layoutParams.bottomMargin, i2, iMax2);
        int paddingLeft = getPaddingLeft() + startOffset;
        int paddingTop = getPaddingTop() + startOffset2;
        view.layout(paddingLeft, paddingTop, Math.min(iMax + paddingLeft, getPaddingLeft() + i), Math.min(iMax2 + paddingTop, getPaddingTop() + i2));
    }

    private int getViewLayoutDirection() {
        return getLayoutDirection();
    }

    @Nonnull
    private static Alignment getAlignmentFromHorizGravity(@Nonnull FrameLayout.LayoutParams layoutParams, int i) {
        int absoluteGravity = Gravity.getAbsoluteGravity(layoutParams.gravity, i) & 7;
        if (absoluteGravity == 1) {
            return Alignment.CENTER;
        }
        if (absoluteGravity == 5) {
            return Alignment.END;
        }
        return Alignment.START;
    }

    private static Alignment getAlignmentFromVertGravity(@Nonnull FrameLayout.LayoutParams layoutParams) {
        int i = layoutParams.gravity & 112;
        if (i == 16) {
            return Alignment.CENTER;
        }
        if (i == 80) {
            return Alignment.END;
        }
        return Alignment.START;
    }

    @Nonnegative
    private static int getStartOffset(@Nonnull Alignment alignment, int i, int i2, @Nonnegative int i3, @Nonnegative int i4) {
        int rawStartOffset = getRawStartOffset(alignment, i, i2, i3, i4);
        if (rawStartOffset <= 0) {
            return 0;
        }
        return rawStartOffset + i4 > i3 ? Math.max(0, i3 - i4) : rawStartOffset;
    }

    /* JADX INFO: renamed from: com.amazon.video.sdk.avod.widget.FitInFrameLayout$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$video$sdk$avod$widget$FitInFrameLayout$Alignment;

        static {
            int[] iArr = new int[Alignment.values().length];
            $SwitchMap$com$amazon$video$sdk$avod$widget$FitInFrameLayout$Alignment = iArr;
            try {
                iArr[Alignment.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$video$sdk$avod$widget$FitInFrameLayout$Alignment[Alignment.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$video$sdk$avod$widget$FitInFrameLayout$Alignment[Alignment.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static int getRawStartOffset(@Nonnull Alignment alignment, int i, int i2, @Nonnegative int i3, @Nonnegative int i4) {
        int i5 = i3 - i2;
        int i6 = AnonymousClass1.$SwitchMap$com$amazon$video$sdk$avod$widget$FitInFrameLayout$Alignment[alignment.ordinal()];
        if (i6 == 1) {
            return i;
        }
        if (i6 == 2) {
            return i5 - i4;
        }
        if (i6 == 3) {
            return ((i + i5) - i4) / 2;
        }
        throw new IllegalStateException("Unexpected alignment: " + alignment);
    }
}
