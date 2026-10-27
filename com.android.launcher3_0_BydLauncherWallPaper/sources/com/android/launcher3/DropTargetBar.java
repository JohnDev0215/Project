package com.android.launcher3;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import com.android.launcher3.DropTarget;
import com.android.launcher3.anim.AlphaUpdateListener;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.dragndrop.DragController;
import com.android.launcher3.dragndrop.DragOptions;

/* JADX INFO: loaded from: classes4.dex */
public class DropTargetBar extends FrameLayout implements DragController.DragListener, Insettable {
    protected static final int DEFAULT_DRAG_FADE_DURATION = 175;
    protected static final TimeInterpolator DEFAULT_INTERPOLATOR = Interpolators.ACCEL;
    private ViewPropertyAnimator mCurrentAnimation;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    protected boolean mDeferOnDragEnd;
    private ButtonDropTarget[] mDropTargets;
    private final Runnable mFadeAnimationEndRunnable;
    private boolean mIsVertical;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    protected boolean mVisible;

    public /* synthetic */ void lambda$new$0$DropTargetBar() {
        AlphaUpdateListener.updateVisibility(this);
    }

    public DropTargetBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mFadeAnimationEndRunnable = new Runnable() { // from class: com.android.launcher3.-$$Lambda$DropTargetBar$96wMaBsk_jQeMPQBF6ozCI7qvNw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$DropTargetBar();
            }
        };
        this.mVisible = false;
        this.mIsVertical = true;
    }

    public DropTargetBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mFadeAnimationEndRunnable = new Runnable() { // from class: com.android.launcher3.-$$Lambda$DropTargetBar$96wMaBsk_jQeMPQBF6ozCI7qvNw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$DropTargetBar();
            }
        };
        this.mVisible = false;
        this.mIsVertical = true;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mDropTargets = new ButtonDropTarget[getChildCount()];
        int i = 0;
        while (true) {
            ButtonDropTarget[] buttonDropTargetArr = this.mDropTargets;
            if (i >= buttonDropTargetArr.length) {
                return;
            }
            buttonDropTargetArr[i] = (ButtonDropTarget) getChildAt(i);
            this.mDropTargets[i].setDropTargetBar(this);
            i++;
        }
    }

    @Override // com.android.launcher3.Insettable
    public void setInsets(Rect insets) {
        int i;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        DeviceProfile deviceProfile = Launcher.getLauncher(getContext()).getDeviceProfile();
        this.mIsVertical = deviceProfile.isVerticalBarLayout();
        layoutParams.leftMargin = insets.left;
        layoutParams.topMargin = insets.top;
        layoutParams.bottomMargin = insets.bottom;
        layoutParams.rightMargin = insets.right;
        int i2 = 1;
        if (deviceProfile.isVerticalBarLayout()) {
            layoutParams.width = deviceProfile.dropTargetBarSizePx;
            layoutParams.height = deviceProfile.availableHeightPx - (deviceProfile.edgeMarginPx * 2);
            layoutParams.gravity = deviceProfile.isSeascape() ? 5 : 3;
            if (!deviceProfile.isSeascape()) {
                i2 = 2;
            }
        } else {
            if (deviceProfile.isTablet) {
                i = (((deviceProfile.widthPx - (deviceProfile.edgeMarginPx * 2)) - (deviceProfile.inv.numColumns * deviceProfile.cellWidthPx)) / ((deviceProfile.inv.numColumns + 1) * 2)) + deviceProfile.edgeMarginPx;
            } else {
                i = deviceProfile.desiredWorkspaceLeftRightMarginPx - deviceProfile.inv.defaultWidgetPadding.right;
            }
            layoutParams.width = deviceProfile.availableWidthPx - (i * 2);
            layoutParams.topMargin += deviceProfile.edgeMarginPx;
            layoutParams.height = deviceProfile.dropTargetBarSizePx;
            layoutParams.gravity = 49;
            i2 = 0;
        }
        setLayoutParams(layoutParams);
        for (ButtonDropTarget buttonDropTarget : this.mDropTargets) {
            buttonDropTarget.setToolTipLocation(i2);
        }
    }

    public void setup(DragController dragController) {
        dragController.addDragListener(this);
        int i = 0;
        while (true) {
            ButtonDropTarget[] buttonDropTargetArr = this.mDropTargets;
            if (i >= buttonDropTargetArr.length) {
                return;
            }
            dragController.addDragListener(buttonDropTargetArr[i]);
            dragController.addDropTarget(this.mDropTargets[i]);
            i++;
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        if (this.mIsVertical) {
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size, 1073741824);
            int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE);
            for (ButtonDropTarget buttonDropTarget : this.mDropTargets) {
                if (buttonDropTarget.getVisibility() != 8) {
                    buttonDropTarget.setTextVisible(false);
                    buttonDropTarget.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
                }
            }
        } else {
            int visibleButtonsCount = size / getVisibleButtonsCount();
            boolean z = true;
            for (ButtonDropTarget buttonDropTarget2 : this.mDropTargets) {
                if (buttonDropTarget2.getVisibility() != 8) {
                    z = z && !buttonDropTarget2.isTextTruncated(visibleButtonsCount);
                }
            }
            int iMakeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(visibleButtonsCount, Integer.MIN_VALUE);
            int iMakeMeasureSpec4 = View.MeasureSpec.makeMeasureSpec(size2, 1073741824);
            for (ButtonDropTarget buttonDropTarget3 : this.mDropTargets) {
                if (buttonDropTarget3.getVisibility() != 8) {
                    buttonDropTarget3.setTextVisible(z);
                    buttonDropTarget3.measure(iMakeMeasureSpec3, iMakeMeasureSpec4);
                }
            }
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (this.mIsVertical) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.drop_target_vertical_gap);
            int i = dimensionPixelSize;
            for (ButtonDropTarget buttonDropTarget : this.mDropTargets) {
                if (buttonDropTarget.getVisibility() != 8) {
                    int measuredHeight = buttonDropTarget.getMeasuredHeight() + i;
                    buttonDropTarget.layout(0, i, buttonDropTarget.getMeasuredWidth(), measuredHeight);
                    i = measuredHeight + dimensionPixelSize;
                }
            }
            return;
        }
        int visibleButtonsCount = (right - left) / getVisibleButtonsCount();
        int i2 = visibleButtonsCount / 2;
        for (ButtonDropTarget buttonDropTarget2 : this.mDropTargets) {
            if (buttonDropTarget2.getVisibility() != 8) {
                int measuredWidth = buttonDropTarget2.getMeasuredWidth() / 2;
                buttonDropTarget2.layout(i2 - measuredWidth, 0, measuredWidth + i2, buttonDropTarget2.getMeasuredHeight());
                i2 += visibleButtonsCount;
            }
        }
    }

    private int getVisibleButtonsCount() {
        int i = 0;
        for (ButtonDropTarget buttonDropTarget : this.mDropTargets) {
            if (buttonDropTarget.getVisibility() != 8) {
                i++;
            }
        }
        return i;
    }

    public void animateToVisibility(boolean isVisible) {
        if (this.mVisible != isVisible) {
            this.mVisible = isVisible;
            ViewPropertyAnimator viewPropertyAnimator = this.mCurrentAnimation;
            if (viewPropertyAnimator != null) {
                viewPropertyAnimator.cancel();
                this.mCurrentAnimation = null;
            }
            float f = this.mVisible ? 1.0f : 0.0f;
            if (Float.compare(getAlpha(), f) != 0) {
                setVisibility(0);
                this.mCurrentAnimation = animate().alpha(f).setInterpolator(DEFAULT_INTERPOLATOR).setDuration(175L).withEndAction(this.mFadeAnimationEndRunnable);
            }
        }
    }

    @Override // com.android.launcher3.dragndrop.DragController.DragListener
    public void onDragStart(DropTarget.DragObject dragObject, DragOptions options) {
        animateToVisibility(false);
    }

    protected void deferOnDragEnd() {
        this.mDeferOnDragEnd = true;
    }

    @Override // com.android.launcher3.dragndrop.DragController.DragListener
    public void onDragEnd() {
        if (!this.mDeferOnDragEnd) {
            animateToVisibility(false);
        } else {
            this.mDeferOnDragEnd = false;
        }
    }

    public ButtonDropTarget[] getDropTargets() {
        return this.mDropTargets;
    }
}
