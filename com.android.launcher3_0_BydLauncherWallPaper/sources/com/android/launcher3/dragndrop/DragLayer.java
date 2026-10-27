package com.android.launcher3.dragndrop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.TextView;
import com.android.launcher3.AbstractFloatingView;
import com.android.launcher3.CellLayout;
import com.android.launcher3.DropTargetBar;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.ShortcutAndWidgetContainer;
import com.android.launcher3.Workspace;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.compat.AccessibilityManagerCompat;
import com.android.launcher3.folder.Folder;
import com.android.launcher3.folder.FolderIcon;
import com.android.launcher3.graphics.RotationMode;
import com.android.launcher3.graphics.WorkspaceAndHotseatScrim;
import com.android.launcher3.keyboard.ViewGroupFocusHelper;
import com.android.launcher3.uioverrides.UiFactory;
import com.android.launcher3.views.BaseDragLayer;
import com.android.launcher3.views.Transposable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class DragLayer extends BaseDragLayer<Launcher> {
    private static final int ALPHA_CHANNEL_COUNT = 3;
    public static final int ALPHA_INDEX_LAUNCHER_LOAD = 1;
    public static final int ALPHA_INDEX_OVERLAY = 0;
    public static final int ALPHA_INDEX_TRANSITIONS = 2;
    public static final int ANIMATION_END_DISAPPEAR = 0;
    public static final int ANIMATION_END_REMAIN_VISIBLE = 2;
    View mAnchorView;
    int mAnchorViewInitialScrollX;
    private int mChildCountOnLastUpdate;
    private final TimeInterpolator mCubicEaseOutInterpolator;
    DragController mDragController;
    private ValueAnimator mDropAnim;
    DragView mDropView;
    private final ViewGroupFocusHelper mFocusIndicatorHelper;
    private boolean mHoverPointClosesFolder;
    private final WorkspaceAndHotseatScrim mScrim;
    private int mTopViewIndex;

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent ev) {
        return false;
    }

    public DragLayer(Context context, AttributeSet attrs) {
        super(context, attrs, 3);
        this.mDropAnim = null;
        this.mCubicEaseOutInterpolator = Interpolators.DEACCEL_1_5;
        this.mDropView = null;
        this.mAnchorViewInitialScrollX = 0;
        this.mAnchorView = null;
        this.mHoverPointClosesFolder = false;
        this.mChildCountOnLastUpdate = -1;
        setMotionEventSplittingEnabled(false);
        setChildrenDrawingOrderEnabled(true);
        this.mFocusIndicatorHelper = new ViewGroupFocusHelper(this);
        this.mScrim = new WorkspaceAndHotseatScrim(this);
    }

    public void setup(DragController dragController, Workspace workspace) {
        this.mDragController = dragController;
        this.mScrim.setWorkspace(workspace);
        recreateControllers();
    }

    public void recreateControllers() {
        this.mControllers = UiFactory.createTouchControllers((Launcher) this.mActivity);
    }

    public ViewGroupFocusHelper getFocusIndicatorHelper() {
        return this.mFocusIndicatorHelper;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        return this.mDragController.dispatchKeyEvent(event) || super.dispatchKeyEvent(event);
    }

    private boolean isEventOverAccessibleDropTargetBar(MotionEvent ev) {
        return isInAccessibleDrag() && isEventOverView(((Launcher) this.mActivity).getDropTargetBar(), ev);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptHoverEvent(MotionEvent ev) {
        if (this.mActivity != 0 && ((Launcher) this.mActivity).getWorkspace() != null) {
            AbstractFloatingView topOpenView = AbstractFloatingView.getTopOpenView(this.mActivity);
            if ((topOpenView instanceof Folder) && ((AccessibilityManager) getContext().getSystemService(Context.ACCESSIBILITY_SERVICE)).isTouchExplorationEnabled()) {
                Folder folder = (Folder) topOpenView;
                int action = ev.getAction();
                if (action == 7) {
                    boolean z = isEventOverView(topOpenView, ev) || isEventOverAccessibleDropTargetBar(ev);
                    if (!z && !this.mHoverPointClosesFolder) {
                        sendTapOutsideFolderAccessibilityEvent(folder.isEditingName());
                        this.mHoverPointClosesFolder = true;
                        return true;
                    }
                    if (!z) {
                        return true;
                    }
                    this.mHoverPointClosesFolder = false;
                } else if (action == 9) {
                    if (!(isEventOverView(topOpenView, ev) || isEventOverAccessibleDropTargetBar(ev))) {
                        sendTapOutsideFolderAccessibilityEvent(folder.isEditingName());
                        this.mHoverPointClosesFolder = true;
                        return true;
                    }
                    this.mHoverPointClosesFolder = false;
                }
            }
        }
        return false;
    }

    private void sendTapOutsideFolderAccessibilityEvent(boolean isEditingName) {
        AccessibilityManagerCompat.sendCustomAccessibilityEvent(this, 8, getContext().getString(isEditingName ? R.string.folder_tap_to_rename : R.string.folder_tap_to_close));
    }

    private boolean isInAccessibleDrag() {
        return ((Launcher) this.mActivity).getAccessibilityDelegate().isInAccessibleDrag();
    }

    @Override // com.android.launcher3.views.BaseDragLayer, android.view.ViewGroup
    public boolean onRequestSendAccessibilityEvent(View child, AccessibilityEvent event) {
        if (isInAccessibleDrag() && (child instanceof DropTargetBar)) {
            return true;
        }
        return super.onRequestSendAccessibilityEvent(child, event);
    }

    @Override // com.android.launcher3.views.BaseDragLayer, android.view.ViewGroup, android.view.View
    public void addChildrenForAccessibility(ArrayList<View> childrenForAccessibility) {
        AbstractFloatingView topOpenViewWithType = AbstractFloatingView.getTopOpenViewWithType(this.mActivity, 1727);
        if (topOpenViewWithType != null) {
            addAccessibleChildToList(topOpenViewWithType, childrenForAccessibility);
            if (isInAccessibleDrag()) {
                addAccessibleChildToList(((Launcher) this.mActivity).getDropTargetBar(), childrenForAccessibility);
                return;
            }
            return;
        }
        super.addChildrenForAccessibility(childrenForAccessibility);
    }

    @Override // com.android.launcher3.views.BaseDragLayer, android.view.ViewGroup, android.view.View
    public boolean dispatchUnhandledMove(View focused, int direction) {
        return super.dispatchUnhandledMove(focused, direction) || this.mDragController.dispatchUnhandledMove(focused, direction);
    }

    @Override // com.android.launcher3.views.BaseDragLayer, android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        ev.offsetLocation(getTranslationX(), 0.0f);
        try {
            return super.dispatchTouchEvent(ev);
        } finally {
            ev.offsetLocation(-getTranslationX(), 0.0f);
        }
    }

    public void animateViewIntoPosition(DragView dragView, final int[] pos, float alpha, float scaleX, float scaleY, int animationEndStyle, Runnable onFinishRunnable, int duration) {
        Rect rect = new Rect();
        getViewRectRelativeToSelf(dragView, rect);
        animateViewIntoPosition(dragView, rect.left, rect.top, pos[0], pos[1], alpha, 1.0f, 1.0f, scaleX, scaleY, onFinishRunnable, animationEndStyle, duration, null);
    }

    public void animateViewIntoPosition(DragView dragView, final View child, View anchorView) {
        animateViewIntoPosition(dragView, child, -1, anchorView);
    }

    public void animateViewIntoPosition(DragView dragView, final View child, int duration, View anchorView) {
        int iRound;
        int iRound2;
        int measuredWidth;
        float f;
        int i;
        ShortcutAndWidgetContainer shortcutAndWidgetContainer = (ShortcutAndWidgetContainer) child.getParent();
        CellLayout.LayoutParams layoutParams = (CellLayout.LayoutParams) child.getLayoutParams();
        shortcutAndWidgetContainer.measureChild(child);
        Rect rect = new Rect();
        getViewRectRelativeToSelf(dragView, rect);
        float scaleX = child.getScaleX();
        float f2 = 1.0f - scaleX;
        float[] fArr = {layoutParams.x + ((child.getMeasuredWidth() * f2) / 2.0f), layoutParams.y + ((child.getMeasuredHeight() * f2) / 2.0f)};
        float descendantCoordRelativeToSelf = getDescendantCoordRelativeToSelf((View) child.getParent(), fArr) * scaleX;
        int iRound3 = Math.round(fArr[0]);
        int iRound4 = Math.round(fArr[1]);
        if (child instanceof TextView) {
            float intrinsicIconScaleFactor = descendantCoordRelativeToSelf / dragView.getIntrinsicIconScaleFactor();
            int iRound5 = (int) ((iRound4 + Math.round(((TextView) child).getPaddingTop() * intrinsicIconScaleFactor)) - ((dragView.getMeasuredHeight() * (1.0f - intrinsicIconScaleFactor)) / 2.0f));
            if (dragView.getDragVisualizeOffset() != null) {
                iRound5 -= Math.round(dragView.getDragVisualizeOffset().y * intrinsicIconScaleFactor);
            }
            measuredWidth = iRound3 - ((dragView.getMeasuredWidth() - Math.round(descendantCoordRelativeToSelf * child.getMeasuredWidth())) / 2);
            i = iRound5;
            f = intrinsicIconScaleFactor;
        } else {
            if (child instanceof FolderIcon) {
                iRound = (int) (((int) ((iRound4 + Math.round((child.getPaddingTop() - dragView.getDragRegionTop()) * descendantCoordRelativeToSelf)) - ((dragView.getBlurSizeOutline() * descendantCoordRelativeToSelf) / 2.0f))) - (((1.0f - descendantCoordRelativeToSelf) * dragView.getMeasuredHeight()) / 2.0f));
                iRound2 = (dragView.getMeasuredWidth() - Math.round(child.getMeasuredWidth() * descendantCoordRelativeToSelf)) / 2;
            } else {
                iRound = iRound4 - (Math.round((dragView.getHeight() - child.getMeasuredHeight()) * descendantCoordRelativeToSelf) / 2);
                iRound2 = Math.round((dragView.getMeasuredWidth() - child.getMeasuredWidth()) * descendantCoordRelativeToSelf) / 2;
            }
            measuredWidth = iRound3 - iRound2;
            f = descendantCoordRelativeToSelf;
            i = iRound;
        }
        int i2 = rect.left;
        int i3 = rect.top;
        child.setVisibility(4);
        animateViewIntoPosition(dragView, i2, i3, measuredWidth, i, 1.0f, 1.0f, 1.0f, f, f, new Runnable() { // from class: com.android.launcher3.dragndrop.-$$Lambda$DragLayer$Ji7AgIK443SVeUgDtJy_k41-fOk
            @Override // java.lang.Runnable
            public final void run() {
                child.setVisibility(0);
            }
        }, 0, duration, anchorView);
    }

    public void animateViewIntoPosition(final DragView view, final int fromX, final int fromY, final int toX, final int toY, float finalAlpha, float initScaleX, float initScaleY, float finalScaleX, float finalScaleY, Runnable onCompleteRunnable, int animationEndStyle, int duration, View anchorView) {
        animateView(view, new Rect(fromX, fromY, view.getMeasuredWidth() + fromX, view.getMeasuredHeight() + fromY), new Rect(toX, toY, view.getMeasuredWidth() + toX, view.getMeasuredHeight() + toY), finalAlpha, initScaleX, initScaleY, finalScaleX, finalScaleY, duration, null, null, onCompleteRunnable, animationEndStyle, anchorView);
    }

    public void animateView(final DragView view, final Rect from, final Rect to, final float finalAlpha, final float initScaleX, final float initScaleY, final float finalScaleX, final float finalScaleY, int duration, final Interpolator motionInterpolator, final Interpolator alphaInterpolator, final Runnable onCompleteRunnable, final int animationEndStyle, View anchorView) {
        int iMax;
        float fHypot = (float) Math.hypot(to.left - from.left, to.top - from.top);
        Resources resources = getResources();
        float integer = resources.getInteger(R.integer.config_dropAnimMaxDist);
        if (duration < 0) {
            int integer2 = resources.getInteger(R.integer.config_dropAnimMaxDuration);
            if (fHypot < integer) {
                integer2 = (int) (integer2 * this.mCubicEaseOutInterpolator.getInterpolation(fHypot / integer));
            }
            iMax = Math.max(integer2, resources.getInteger(R.integer.config_dropAnimMinDuration));
        } else {
            iMax = duration;
        }
        TimeInterpolator timeInterpolator = (alphaInterpolator == null || motionInterpolator == null) ? this.mCubicEaseOutInterpolator : null;
        final float alpha = view.getAlpha();
        final float scaleX = view.getScaleX();
        animateView(view, new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.launcher3.dragndrop.DragLayer.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                float fFloatValue = ((Float) animation.getAnimatedValue()).floatValue();
                int measuredWidth = view.getMeasuredWidth();
                int measuredHeight = view.getMeasuredHeight();
                Interpolator interpolator = alphaInterpolator;
                float interpolation = interpolator == null ? fFloatValue : interpolator.getInterpolation(fFloatValue);
                Interpolator interpolator2 = motionInterpolator;
                float interpolation2 = interpolator2 == null ? fFloatValue : interpolator2.getInterpolation(fFloatValue);
                float f = initScaleX;
                float f2 = scaleX;
                float f3 = f * f2;
                float f4 = initScaleY * f2;
                float f5 = 1.0f - fFloatValue;
                float f6 = (finalScaleX * fFloatValue) + (f3 * f5);
                float f7 = (finalScaleY * fFloatValue) + (f5 * f4);
                float f8 = (finalAlpha * interpolation) + (alpha * (1.0f - interpolation));
                float f9 = from.left + (((f3 - 1.0f) * measuredWidth) / 2.0f);
                int iRound = (int) (from.top + (((f4 - 1.0f) * measuredHeight) / 2.0f) + Math.round((to.top - r4) * interpolation2));
                int iRound2 = (((int) (f9 + Math.round((to.left - f9) * interpolation2))) - DragLayer.this.mDropView.getScrollX()) + (DragLayer.this.mAnchorView == null ? 0 : (int) (DragLayer.this.mAnchorView.getScaleX() * (DragLayer.this.mAnchorViewInitialScrollX - DragLayer.this.mAnchorView.getScrollX())));
                int scrollY = iRound - DragLayer.this.mDropView.getScrollY();
                DragLayer.this.mDropView.setTranslationX(iRound2);
                DragLayer.this.mDropView.setTranslationY(scrollY);
                DragLayer.this.mDropView.setScaleX(f6);
                DragLayer.this.mDropView.setScaleY(f7);
                DragLayer.this.mDropView.setAlpha(f8);
            }
        }, iMax, timeInterpolator, onCompleteRunnable, animationEndStyle, anchorView);
    }

    public void animateView(final DragView view, ValueAnimator.AnimatorUpdateListener updateCb, int duration, TimeInterpolator interpolator, final Runnable onCompleteRunnable, final int animationEndStyle, View anchorView) {
        ValueAnimator valueAnimator = this.mDropAnim;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.mDropView = view;
        view.cancelAnimation();
        this.mDropView.requestLayout();
        if (anchorView != null) {
            this.mAnchorViewInitialScrollX = anchorView.getScrollX();
        }
        this.mAnchorView = anchorView;
        ValueAnimator valueAnimator2 = new ValueAnimator();
        this.mDropAnim = valueAnimator2;
        valueAnimator2.setInterpolator(interpolator);
        this.mDropAnim.setDuration(duration);
        this.mDropAnim.setFloatValues(0.0f, 1.0f);
        this.mDropAnim.addUpdateListener(updateCb);
        this.mDropAnim.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.dragndrop.DragLayer.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Runnable runnable = onCompleteRunnable;
                if (runnable != null) {
                    runnable.run();
                }
                if (animationEndStyle == 0) {
                    DragLayer.this.clearAnimatedView();
                }
                DragLayer.this.mDropAnim = null;
            }
        });
        this.mDropAnim.start();
    }

    public void clearAnimatedView() {
        ValueAnimator valueAnimator = this.mDropAnim;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.mDropAnim = null;
        DragView dragView = this.mDropView;
        if (dragView != null) {
            this.mDragController.onDeferredEndDrag(dragView);
        }
        this.mDropView = null;
        invalidate();
    }

    public View getAnimatedView() {
        return this.mDropView;
    }

    @Override // com.android.launcher3.InsettableFrameLayout, android.view.ViewGroup
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        updateChildIndices();
        UiFactory.onLauncherStateOrFocusChanged((Launcher) this.mActivity);
    }

    @Override // com.android.launcher3.views.BaseDragLayer, android.view.ViewGroup
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        updateChildIndices();
        UiFactory.onLauncherStateOrFocusChanged((Launcher) this.mActivity);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void bringChildToFront(View child) {
        super.bringChildToFront(child);
        updateChildIndices();
    }

    private void updateChildIndices() {
        this.mTopViewIndex = -1;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i) instanceof DragView) {
                this.mTopViewIndex = i;
            }
        }
        this.mChildCountOnLastUpdate = childCount;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int childCount, int i) {
        if (this.mChildCountOnLastUpdate != childCount) {
            updateChildIndices();
        }
        int i2 = this.mTopViewIndex;
        return i2 == -1 ? i : i == childCount + (-1) ? i2 : i < i2 ? i : i + 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        this.mScrim.draw(canvas);
        this.mFocusIndicatorHelper.draw(canvas);
        super.dispatchDraw(canvas);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mScrim.setSize(w, h);
    }

    @Override // com.android.launcher3.InsettableFrameLayout, com.android.launcher3.Insettable
    public void setInsets(Rect insets) {
        super.setInsets(insets);
        this.mScrim.onInsetsChanged(insets);
    }

    public WorkspaceAndHotseatScrim getScrim() {
        return this.mScrim;
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        RotationMode rotationMode = ((Launcher) this.mActivity).getRotationMode();
        int childCount = getChildCount();
        int i = 0;
        if (!rotationMode.isTransposed || View.MeasureSpec.getMode(widthMeasureSpec) != 1073741824 || View.MeasureSpec.getMode(heightMeasureSpec) != 1073741824) {
            while (i < childCount) {
                getChildAt(i).setRotation(rotationMode.surfaceRotation);
                i++;
            }
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        while (i < childCount) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                if (!(childAt instanceof Transposable)) {
                    measureChildWithMargins(childAt, widthMeasureSpec, 0, heightMeasureSpec, 0);
                } else {
                    measureChildWithMargins(childAt, heightMeasureSpec, 0, widthMeasureSpec, 0);
                    childAt.setPivotX(childAt.getMeasuredWidth() / 2);
                    childAt.setPivotY(childAt.getMeasuredHeight() / 2);
                    childAt.setRotation(rotationMode.surfaceRotation);
                }
            }
            i++;
        }
        setMeasuredDimension(View.MeasureSpec.getSize(widthMeasureSpec), View.MeasureSpec.getSize(heightMeasureSpec));
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00e4  */
    @Override // com.android.launcher3.views.BaseDragLayer, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onLayout(boolean r11, int r12, int r13, int r14, int r15) {
        /*
            Method dump skipped, instruction units count: 251
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.dragndrop.DragLayer.onLayout(boolean, int, int, int, int):void");
    }
}
