package com.android.launcher3;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.appwidget.AppWidgetHostView;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.android.launcher3.CellLayout;
import com.android.launcher3.accessibility.DragViewStateAnnouncer;
import com.android.launcher3.dragndrop.DragLayer;
import com.android.launcher3.util.FocusLogic;
import com.android.launcher3.views.BaseDragLayer;
import com.android.launcher3.widget.LauncherAppWidgetHostView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class AppWidgetResizeFrame extends AbstractFloatingView implements View.OnKeyListener {
    private static final float DIMMED_HANDLE_ALPHA = 0.0f;
    private static final int HANDLE_COUNT = 4;
    private static final int INDEX_BOTTOM = 3;
    private static final int INDEX_LEFT = 0;
    private static final int INDEX_RIGHT = 2;
    private static final int INDEX_TOP = 1;
    private static final float RESIZE_THRESHOLD = 0.66f;
    private static final int SNAP_DURATION = 150;
    private static Point[] sCellSize;
    private static final Rect sTmpRect = new Rect();
    private final int mBackgroundPadding;
    private final IntRange mBaselineX;
    private final IntRange mBaselineY;
    private boolean mBottomBorderActive;
    private int mBottomTouchRegionAdjustment;
    private CellLayout mCellLayout;
    private int mDeltaX;
    private int mDeltaXAddOn;
    private final IntRange mDeltaXRange;
    private int mDeltaY;
    private int mDeltaYAddOn;
    private final IntRange mDeltaYRange;
    private final int[] mDirectionVector;
    private final View[] mDragHandles;
    private DragLayer mDragLayer;
    private final FirstFrameAnimatorHelper mFirstFrameAnimatorHelper;
    private final int[] mLastDirectionVector;
    private final Launcher mLauncher;
    private boolean mLeftBorderActive;
    private int mMinHSpan;
    private int mMinVSpan;
    private int mResizeMode;
    private boolean mRightBorderActive;
    private int mRunningHInc;
    private int mRunningVInc;
    private final DragViewStateAnnouncer mStateAnnouncer;
    private final List<Rect> mSystemGestureExclusionRects;
    private final IntRange mTempRange1;
    private final IntRange mTempRange2;
    private boolean mTopBorderActive;
    private int mTopTouchRegionAdjustment;
    private final int mTouchTargetWidth;
    private Rect mWidgetPadding;
    private LauncherAppWidgetHostView mWidgetView;
    private int mXDown;
    private int mYDown;

    @Override // com.android.launcher3.AbstractFloatingView
    protected boolean isOfType(int type) {
        return (type & 8) != 0;
    }

    @Override // com.android.launcher3.AbstractFloatingView
    public void logActionCommand(int command) {
    }

    public AppWidgetResizeFrame(Context context) {
        this(context, null);
    }

    public AppWidgetResizeFrame(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppWidgetResizeFrame(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mDragHandles = new View[4];
        this.mSystemGestureExclusionRects = new ArrayList(4);
        this.mDirectionVector = new int[2];
        this.mLastDirectionVector = new int[2];
        this.mTempRange1 = new IntRange();
        this.mTempRange2 = new IntRange();
        this.mDeltaXRange = new IntRange();
        this.mBaselineX = new IntRange();
        this.mDeltaYRange = new IntRange();
        this.mBaselineY = new IntRange();
        this.mTopTouchRegionAdjustment = 0;
        this.mBottomTouchRegionAdjustment = 0;
        this.mLauncher = Launcher.getLauncher(context);
        this.mStateAnnouncer = DragViewStateAnnouncer.createFor(this);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.resize_frame_background_padding);
        this.mBackgroundPadding = dimensionPixelSize;
        this.mTouchTargetWidth = dimensionPixelSize * 2;
        this.mFirstFrameAnimatorHelper = new FirstFrameAnimatorHelper(this);
        for (int i = 0; i < 4; i++) {
            this.mSystemGestureExclusionRects.add(new Rect());
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        ViewGroup viewGroup = (ViewGroup) getChildAt(0);
        for (int i = 0; i < 4; i++) {
            this.mDragHandles[i] = viewGroup.getChildAt(i);
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (Utilities.ATLEAST_Q) {
            for (int i = 0; i < 4; i++) {
                View view = this.mDragHandles[i];
                this.mSystemGestureExclusionRects.get(i).set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
            setSystemGestureExclusionRects(this.mSystemGestureExclusionRects);
        }
    }

    public static void showForWidget(LauncherAppWidgetHostView widget, CellLayout cellLayout) {
        Launcher launcher = Launcher.getLauncher(cellLayout.getContext());
        AbstractFloatingView.closeAllOpenViews(launcher);
        DragLayer dragLayer = launcher.getDragLayer();
        AppWidgetResizeFrame appWidgetResizeFrame = (AppWidgetResizeFrame) launcher.getLayoutInflater().inflate(R.layout.app_widget_resize_frame, (ViewGroup) dragLayer, false);
        appWidgetResizeFrame.setupForWidget(widget, cellLayout, dragLayer);
        ((BaseDragLayer.LayoutParams) appWidgetResizeFrame.getLayoutParams()).customPosition = true;
        dragLayer.addView(appWidgetResizeFrame);
        appWidgetResizeFrame.mIsOpen = true;
        appWidgetResizeFrame.snapToWidget(false);
    }

    private void setupForWidget(LauncherAppWidgetHostView widgetView, CellLayout cellLayout, DragLayer dragLayer) {
        this.mCellLayout = cellLayout;
        this.mWidgetView = widgetView;
        LauncherAppWidgetProviderInfo launcherAppWidgetProviderInfo = (LauncherAppWidgetProviderInfo) widgetView.getAppWidgetInfo();
        this.mResizeMode = launcherAppWidgetProviderInfo.resizeMode;
        this.mDragLayer = dragLayer;
        this.mMinHSpan = launcherAppWidgetProviderInfo.minSpanX;
        this.mMinVSpan = launcherAppWidgetProviderInfo.minSpanY;
        this.mWidgetPadding = AppWidgetHostView.getDefaultPaddingForWidget(getContext(), widgetView.getAppWidgetInfo().provider, null);
        int i = this.mResizeMode;
        if (i == 1) {
            this.mDragHandles[1].setVisibility(8);
            this.mDragHandles[3].setVisibility(8);
        } else if (i == 2) {
            this.mDragHandles[0].setVisibility(8);
            this.mDragHandles[2].setVisibility(8);
        }
        this.mCellLayout.markCellsAsUnoccupiedForView(this.mWidgetView);
        setOnKeyListener(this);
    }

    public boolean beginResizeIfPointInRegion(int x, int y) {
        int i = this.mResizeMode;
        boolean z = (i & 1) != 0;
        boolean z2 = (i & 2) != 0;
        this.mLeftBorderActive = x < this.mTouchTargetWidth && z;
        int width = getWidth();
        int i2 = this.mTouchTargetWidth;
        this.mRightBorderActive = x > width - i2 && z;
        this.mTopBorderActive = y < i2 + this.mTopTouchRegionAdjustment && z2;
        boolean z3 = y > (getHeight() - this.mTouchTargetWidth) + this.mBottomTouchRegionAdjustment && z2;
        this.mBottomBorderActive = z3;
        boolean z4 = this.mLeftBorderActive;
        boolean z5 = z4 || this.mRightBorderActive || this.mTopBorderActive || z3;
        if (z5) {
            this.mDragHandles[0].setAlpha(z4 ? 1.0f : 0.0f);
            this.mDragHandles[2].setAlpha(this.mRightBorderActive ? 1.0f : 0.0f);
            this.mDragHandles[1].setAlpha(this.mTopBorderActive ? 1.0f : 0.0f);
            this.mDragHandles[3].setAlpha(this.mBottomBorderActive ? 1.0f : 0.0f);
        }
        if (this.mLeftBorderActive) {
            this.mDeltaXRange.set(-getLeft(), getWidth() - (this.mTouchTargetWidth * 2));
        } else if (this.mRightBorderActive) {
            this.mDeltaXRange.set((this.mTouchTargetWidth * 2) - getWidth(), this.mDragLayer.getWidth() - getRight());
        } else {
            this.mDeltaXRange.set(0, 0);
        }
        this.mBaselineX.set(getLeft(), getRight());
        if (this.mTopBorderActive) {
            this.mDeltaYRange.set(-getTop(), getHeight() - (this.mTouchTargetWidth * 2));
        } else if (this.mBottomBorderActive) {
            this.mDeltaYRange.set((this.mTouchTargetWidth * 2) - getHeight(), this.mDragLayer.getHeight() - getBottom());
        } else {
            this.mDeltaYRange.set(0, 0);
        }
        this.mBaselineY.set(getTop(), getBottom());
        return z5;
    }

    public void visualizeResizeForDelta(int deltaX, int deltaY) {
        this.mDeltaX = this.mDeltaXRange.clamp(deltaX);
        this.mDeltaY = this.mDeltaYRange.clamp(deltaY);
        BaseDragLayer.LayoutParams layoutParams = (BaseDragLayer.LayoutParams) getLayoutParams();
        int iClamp = this.mDeltaXRange.clamp(deltaX);
        this.mDeltaX = iClamp;
        this.mBaselineX.applyDelta(this.mLeftBorderActive, this.mRightBorderActive, iClamp, this.mTempRange1);
        layoutParams.x = this.mTempRange1.start;
        layoutParams.width = this.mTempRange1.size();
        int iClamp2 = this.mDeltaYRange.clamp(deltaY);
        this.mDeltaY = iClamp2;
        this.mBaselineY.applyDelta(this.mTopBorderActive, this.mBottomBorderActive, iClamp2, this.mTempRange1);
        layoutParams.y = this.mTempRange1.start;
        layoutParams.height = this.mTempRange1.size();
        resizeWidgetIfNeeded(false);
        Rect rect = sTmpRect;
        getSnappedRectRelativeToDragLayer(rect);
        if (this.mLeftBorderActive) {
            layoutParams.width = (rect.width() + rect.left) - layoutParams.x;
        }
        if (this.mTopBorderActive) {
            layoutParams.height = (rect.height() + rect.top) - layoutParams.y;
        }
        if (this.mRightBorderActive) {
            layoutParams.x = rect.left;
        }
        if (this.mBottomBorderActive) {
            layoutParams.y = rect.top;
        }
        requestLayout();
    }

    private static int getSpanIncrement(float deltaFrac) {
        if (Math.abs(deltaFrac) > RESIZE_THRESHOLD) {
            return Math.round(deltaFrac);
        }
        return 0;
    }

    private void resizeWidgetIfNeeded(boolean onDismiss) {
        float cellWidth = this.mCellLayout.getCellWidth();
        float cellHeight = this.mCellLayout.getCellHeight();
        int spanIncrement = getSpanIncrement(((this.mDeltaX + this.mDeltaXAddOn) / cellWidth) - this.mRunningHInc);
        int spanIncrement2 = getSpanIncrement(((this.mDeltaY + this.mDeltaYAddOn) / cellHeight) - this.mRunningVInc);
        if (!onDismiss && spanIncrement == 0 && spanIncrement2 == 0) {
            return;
        }
        int[] iArr = this.mDirectionVector;
        iArr[0] = 0;
        iArr[1] = 0;
        CellLayout.LayoutParams layoutParams = (CellLayout.LayoutParams) this.mWidgetView.getLayoutParams();
        int i = layoutParams.cellHSpan;
        int i2 = layoutParams.cellVSpan;
        int i3 = layoutParams.useTmpCoords ? layoutParams.tmpCellX : layoutParams.cellX;
        int i4 = layoutParams.useTmpCoords ? layoutParams.tmpCellY : layoutParams.cellY;
        this.mTempRange1.set(i3, i + i3);
        int iApplyDeltaAndBound = this.mTempRange1.applyDeltaAndBound(this.mLeftBorderActive, this.mRightBorderActive, spanIncrement, this.mMinHSpan, this.mCellLayout.getCountX(), this.mTempRange2);
        int i5 = this.mTempRange2.start;
        int size = this.mTempRange2.size();
        if (iApplyDeltaAndBound != 0) {
            this.mDirectionVector[0] = this.mLeftBorderActive ? -1 : 1;
        }
        this.mTempRange1.set(i4, i2 + i4);
        int iApplyDeltaAndBound2 = this.mTempRange1.applyDeltaAndBound(this.mTopBorderActive, this.mBottomBorderActive, spanIncrement2, this.mMinVSpan, this.mCellLayout.getCountY(), this.mTempRange2);
        int i6 = this.mTempRange2.start;
        int size2 = this.mTempRange2.size();
        if (iApplyDeltaAndBound2 != 0) {
            this.mDirectionVector[1] = this.mTopBorderActive ? -1 : 1;
        }
        if (!onDismiss && iApplyDeltaAndBound2 == 0 && iApplyDeltaAndBound == 0) {
            return;
        }
        if (onDismiss) {
            int[] iArr2 = this.mDirectionVector;
            int[] iArr3 = this.mLastDirectionVector;
            iArr2[0] = iArr3[0];
            iArr2[1] = iArr3[1];
        } else {
            int[] iArr4 = this.mLastDirectionVector;
            int[] iArr5 = this.mDirectionVector;
            iArr4[0] = iArr5[0];
            iArr4[1] = iArr5[1];
        }
        if (this.mCellLayout.createAreaForResize(i5, i6, size, size2, this.mWidgetView, this.mDirectionVector, onDismiss)) {
            if (this.mStateAnnouncer != null && (layoutParams.cellHSpan != size || layoutParams.cellVSpan != size2)) {
                this.mStateAnnouncer.announce(this.mLauncher.getString(R.string.widget_resized, Integer.valueOf(size), Integer.valueOf(size2)));
            }
            layoutParams.tmpCellX = i5;
            layoutParams.tmpCellY = i6;
            layoutParams.cellHSpan = size;
            layoutParams.cellVSpan = size2;
            this.mRunningVInc += iApplyDeltaAndBound2;
            this.mRunningHInc += iApplyDeltaAndBound;
            if (!onDismiss) {
                updateWidgetSizeRanges(this.mWidgetView, this.mLauncher, size, size2);
            }
        }
        this.mWidgetView.requestLayout();
    }

    static void updateWidgetSizeRanges(AppWidgetHostView widgetView, Launcher launcher, int spanX, int spanY) {
        Rect rect = sTmpRect;
        getWidgetSizeRanges(launcher, spanX, spanY, rect);
        widgetView.updateAppWidgetSize(null, rect.left, rect.top, rect.right, rect.bottom);
    }

    public static Rect getWidgetSizeRanges(Context context, int spanX, int spanY, Rect rect) {
        if (sCellSize == null) {
            InvariantDeviceProfile idp = LauncherAppState.getIDP(context);
            Point[] pointArr = new Point[2];
            sCellSize = pointArr;
            pointArr[0] = idp.landscapeProfile.getCellSize();
            sCellSize[1] = idp.portraitProfile.getCellSize();
        }
        if (rect == null) {
            rect = new Rect();
        }
        float f = context.getResources().getDisplayMetrics().density;
        rect.set((int) ((spanX * sCellSize[1].x) / f), (int) ((sCellSize[0].y * spanY) / f), (int) ((sCellSize[0].x * spanX) / f), (int) ((spanY * sCellSize[1].y) / f));
        return rect;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        resizeWidgetIfNeeded(true);
    }

    private void onTouchUp() {
        int cellWidth = this.mCellLayout.getCellWidth();
        int cellHeight = this.mCellLayout.getCellHeight();
        this.mDeltaXAddOn = this.mRunningHInc * cellWidth;
        this.mDeltaYAddOn = this.mRunningVInc * cellHeight;
        this.mDeltaX = 0;
        this.mDeltaY = 0;
        post(new Runnable() { // from class: com.android.launcher3.-$$Lambda$AppWidgetResizeFrame$2dpbP3v7fzJ3vlks66e7YFeB3PU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onTouchUp$0$AppWidgetResizeFrame();
            }
        });
    }

    public /* synthetic */ void lambda$onTouchUp$0$AppWidgetResizeFrame() {
        snapToWidget(true);
    }

    private void getSnappedRectRelativeToDragLayer(Rect out) {
        float scaleToFit = this.mWidgetView.getScaleToFit();
        this.mDragLayer.getViewRectRelativeToSelf(this.mWidgetView, out);
        int iWidth = (this.mBackgroundPadding * 2) + ((int) (((out.width() - this.mWidgetPadding.left) - this.mWidgetPadding.right) * scaleToFit));
        int iHeight = (this.mBackgroundPadding * 2) + ((int) (((out.height() - this.mWidgetPadding.top) - this.mWidgetPadding.bottom) * scaleToFit));
        int i = (int) ((out.left - this.mBackgroundPadding) + (this.mWidgetPadding.left * scaleToFit));
        out.left = i;
        out.top = (int) ((out.top - this.mBackgroundPadding) + (scaleToFit * this.mWidgetPadding.top));
        out.right = out.left + iWidth;
        out.bottom = out.top + iHeight;
    }

    private void snapToWidget(boolean animate) {
        Rect rect = sTmpRect;
        getSnappedRectRelativeToDragLayer(rect);
        int iWidth = rect.width();
        int iHeight = rect.height();
        int i = rect.left;
        int i2 = rect.top;
        if (i2 < 0) {
            this.mTopTouchRegionAdjustment = -i2;
        } else {
            this.mTopTouchRegionAdjustment = 0;
        }
        int i3 = i2 + iHeight;
        if (i3 > this.mDragLayer.getHeight()) {
            this.mBottomTouchRegionAdjustment = -(i3 - this.mDragLayer.getHeight());
        } else {
            this.mBottomTouchRegionAdjustment = 0;
        }
        BaseDragLayer.LayoutParams layoutParams = (BaseDragLayer.LayoutParams) getLayoutParams();
        if (!animate) {
            layoutParams.width = iWidth;
            layoutParams.height = iHeight;
            layoutParams.x = i;
            layoutParams.y = i2;
            for (int i4 = 0; i4 < 4; i4++) {
                this.mDragHandles[i4].setAlpha(1.0f);
            }
            requestLayout();
        } else {
            ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(layoutParams, PropertyValuesHolder.ofInt(LauncherAnimUtils.LAYOUT_WIDTH, layoutParams.width, iWidth), PropertyValuesHolder.ofInt(LauncherAnimUtils.LAYOUT_HEIGHT, layoutParams.height, iHeight), PropertyValuesHolder.ofInt(BaseDragLayer.LAYOUT_X, layoutParams.x, i), PropertyValuesHolder.ofInt(BaseDragLayer.LAYOUT_Y, layoutParams.y, i2));
            ((ObjectAnimator) this.mFirstFrameAnimatorHelper.addTo(objectAnimatorOfPropertyValuesHolder)).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.launcher3.-$$Lambda$AppWidgetResizeFrame$VAQasP1VNdVCCJR86XWip3QqAXM
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.f$0.lambda$snapToWidget$1$AppWidgetResizeFrame(valueAnimator);
                }
            });
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(objectAnimatorOfPropertyValuesHolder);
            for (int i5 = 0; i5 < 4; i5++) {
                animatorSet.play(this.mFirstFrameAnimatorHelper.addTo(ObjectAnimator.ofFloat(this.mDragHandles[i5], ALPHA, 1.0f)));
            }
            animatorSet.setDuration(150L);
            animatorSet.start();
        }
        setFocusableInTouchMode(true);
        requestFocus();
    }

    public /* synthetic */ void lambda$snapToWidget$1$AppWidgetResizeFrame(ValueAnimator valueAnimator) {
        requestLayout();
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (!FocusLogic.shouldConsume(keyCode)) {
            return false;
        }
        close(false);
        this.mWidgetView.requestFocus();
        return true;
    }

    private boolean handleTouchDown(MotionEvent ev) {
        Rect rect = new Rect();
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        getHitRect(rect);
        if (!rect.contains(x, y) || !beginResizeIfPointInRegion(x - getLeft(), y - getTop())) {
            return false;
        }
        this.mXDown = x;
        this.mYDown = y;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0024  */
    @Override // com.android.launcher3.AbstractFloatingView, com.android.launcher3.util.TouchController
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onControllerTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            int r0 = r5.getAction()
            float r1 = r5.getX()
            int r1 = (int) r1
            float r2 = r5.getY()
            int r2 = (int) r2
            if (r0 == 0) goto L36
            r5 = 1
            if (r0 == r5) goto L24
            r3 = 2
            if (r0 == r3) goto L1a
            r3 = 3
            if (r0 == r3) goto L24
            goto L35
        L1a:
            int r0 = r4.mXDown
            int r1 = r1 - r0
            int r0 = r4.mYDown
            int r2 = r2 - r0
            r4.visualizeResizeForDelta(r1, r2)
            goto L35
        L24:
            int r0 = r4.mXDown
            int r1 = r1 - r0
            int r0 = r4.mYDown
            int r2 = r2 - r0
            r4.visualizeResizeForDelta(r1, r2)
            r4.onTouchUp()
            r0 = 0
            r4.mYDown = r0
            r4.mXDown = r0
        L35:
            return r5
        L36:
            boolean r4 = r4.handleTouchDown(r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.AppWidgetResizeFrame.onControllerTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // com.android.launcher3.util.TouchController
    public boolean onControllerInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 0 && handleTouchDown(ev)) {
            return true;
        }
        close(false);
        return false;
    }

    @Override // com.android.launcher3.AbstractFloatingView
    protected void handleClose(boolean animate) {
        this.mDragLayer.removeView(this);
    }

    private static class IntRange {
        public int end;
        public int start;

        private IntRange() {
        }

        public int clamp(int value) {
            return Utilities.boundToRange(value, this.start, this.end);
        }

        public void set(int s, int e) {
            this.start = s;
            this.end = e;
        }

        public int size() {
            return this.end - this.start;
        }

        public void applyDelta(boolean moveStart, boolean moveEnd, int delta, IntRange out) {
            out.start = moveStart ? this.start + delta : this.start;
            int i = this.end;
            if (moveEnd) {
                i += delta;
            }
            out.end = i;
        }

        public int applyDeltaAndBound(boolean moveStart, boolean moveEnd, int delta, int minSize, int maxEnd, IntRange out) {
            applyDelta(moveStart, moveEnd, delta, out);
            if (out.start < 0) {
                out.start = 0;
            }
            if (out.end > maxEnd) {
                out.end = maxEnd;
            }
            if (out.size() < minSize) {
                if (moveStart) {
                    out.start = out.end - minSize;
                } else if (moveEnd) {
                    out.end = out.start + minSize;
                }
            }
            return moveEnd ? out.size() - size() : size() - out.size();
        }
    }
}
