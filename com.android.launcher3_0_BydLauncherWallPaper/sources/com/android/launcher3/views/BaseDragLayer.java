package com.android.launcher3.views;

import android.content.Context;
import android.graphics.Insets;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import com.android.launcher3.AbstractFloatingView;
import com.android.launcher3.InsettableFrameLayout;
import com.android.launcher3.Utilities;
import com.android.launcher3.testing.TestProtocol;
import com.android.launcher3.util.MultiValueAlpha;
import com.android.launcher3.util.TouchController;
import com.android.launcher3.views.ActivityContext;
import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BaseDragLayer<T extends Context & ActivityContext> extends InsettableFrameLayout {
    public static final Property<LayoutParams, Integer> LAYOUT_X = new Property<LayoutParams, Integer>(Integer.TYPE, "x") { // from class: com.android.launcher3.views.BaseDragLayer.1
        @Override // android.util.Property
        public Integer get(LayoutParams lp) {
            return Integer.valueOf(lp.x);
        }

        @Override // android.util.Property
        public void set(LayoutParams lp, Integer x) {
            lp.x = x.intValue();
        }
    };
    public static final Property<LayoutParams, Integer> LAYOUT_Y = new Property<LayoutParams, Integer>(Integer.TYPE, "y") { // from class: com.android.launcher3.views.BaseDragLayer.2
        @Override // android.util.Property
        public Integer get(LayoutParams lp) {
            return Integer.valueOf(lp.y);
        }

        @Override // android.util.Property
        public void set(LayoutParams lp, Integer y) {
            lp.y = y.intValue();
        }
    };
    private static final int TOUCH_DISPATCHING_GESTURE = 2;
    private static final int TOUCH_DISPATCHING_PROXY = 4;
    private static final int TOUCH_DISPATCHING_VIEW = 1;
    protected TouchController mActiveController;
    protected final T mActivity;
    protected TouchController[] mControllers;
    protected final Rect mHitRect;
    private final MultiValueAlpha mMultiValueAlpha;
    protected TouchController mProxyTouchController;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private final RectF mSystemGestureRegion;
    protected final float[] mTmpRectPoints;
    protected final float[] mTmpXY;
    private TouchCompleteListener mTouchCompleteListener;
    private int mTouchDispatchState;

    public interface TouchCompleteListener {
        void onTouchComplete();
    }

    public BaseDragLayer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.mTmpXY = new float[2];
        this.mTmpRectPoints = new float[4];
        this.mHitRect = new Rect();
        this.mSystemGestureRegion = new RectF();
        this.mTouchDispatchState = 0;
        this.mActivity = (T) ((Context) ActivityContext.lookupContext(context));
        this.mMultiValueAlpha = new MultiValueAlpha(this, i);
    }

    public boolean isEventOverView(View view, MotionEvent ev) {
        getDescendantRectRelativeToSelf(view, this.mHitRect);
        return this.mHitRect.contains((int) ev.getX(), (int) ev.getY());
    }

    public boolean isEventOverView(View view, MotionEvent ev, View evView) {
        int[] iArr = {(int) ev.getX(), (int) ev.getY()};
        getDescendantCoordRelativeToSelf(evView, iArr);
        getDescendantRectRelativeToSelf(view, this.mHitRect);
        return this.mHitRect.contains(iArr[0], iArr[1]);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == 1 || action == 3) {
            TouchCompleteListener touchCompleteListener = this.mTouchCompleteListener;
            if (touchCompleteListener != null) {
                touchCompleteListener.onTouchComplete();
            }
            this.mTouchCompleteListener = null;
        } else if (action == 0) {
            this.mActivity.finishAutoCancelActionMode();
        }
        return findActiveController(ev);
    }

    private TouchController findControllerToHandleTouch(MotionEvent ev) {
        if (Utilities.shouldDisableGestures(ev)) {
            return null;
        }
        AbstractFloatingView topOpenView = AbstractFloatingView.getTopOpenView(this.mActivity);
        if (topOpenView != null && topOpenView.onControllerInterceptTouchEvent(ev)) {
            return topOpenView;
        }
        for (TouchController touchController : this.mControllers) {
            if (touchController.onControllerInterceptTouchEvent(ev)) {
                return touchController;
            }
        }
        return null;
    }

    protected boolean findActiveController(MotionEvent ev) {
        this.mActiveController = null;
        if ((this.mTouchDispatchState & 6) != 0) {
            return false;
        }
        TouchController touchControllerFindControllerToHandleTouch = findControllerToHandleTouch(ev);
        this.mActiveController = touchControllerFindControllerToHandleTouch;
        return touchControllerFindControllerToHandleTouch != null;
    }

    @Override // android.view.ViewGroup
    public boolean onRequestSendAccessibilityEvent(View child, AccessibilityEvent event) {
        AbstractFloatingView topOpenViewWithType = AbstractFloatingView.getTopOpenViewWithType(this.mActivity, 1727);
        if (topOpenViewWithType == null) {
            return super.onRequestSendAccessibilityEvent(child, event);
        }
        if (child == topOpenViewWithType) {
            return super.onRequestSendAccessibilityEvent(child, event);
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addChildrenForAccessibility(ArrayList<View> childrenForAccessibility) {
        AbstractFloatingView topOpenViewWithType = AbstractFloatingView.getTopOpenViewWithType(this.mActivity, 1727);
        if (topOpenViewWithType != null) {
            addAccessibleChildToList(topOpenViewWithType, childrenForAccessibility);
        } else {
            super.addChildrenForAccessibility(childrenForAccessibility);
        }
    }

    protected void addAccessibleChildToList(View child, ArrayList<View> outList) {
        if (child.isImportantForAccessibility()) {
            outList.add(child);
        } else {
            child.addChildrenForAccessibility(outList);
        }
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        if (child instanceof AbstractFloatingView) {
            final AbstractFloatingView abstractFloatingView = (AbstractFloatingView) child;
            if (abstractFloatingView.isOpen()) {
                postDelayed(new Runnable() { // from class: com.android.launcher3.views.-$$Lambda$BaseDragLayer$DT5IlsVGAEzrSsz77IA-FwsF-5Q
                    @Override // java.lang.Runnable
                    public final void run() {
                        abstractFloatingView.close(false);
                    }
                }, 16L);
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        if (TestProtocol.sDebugTracing) {
            Log.d(TestProtocol.NO_DRAG_TAG, "onTouchEvent " + ev);
        }
        int action = ev.getAction();
        if (action == 1 || action == 3) {
            TouchCompleteListener touchCompleteListener = this.mTouchCompleteListener;
            if (touchCompleteListener != null) {
                touchCompleteListener.onTouchComplete();
            }
            this.mTouchCompleteListener = null;
        }
        if (this.mActiveController != null) {
            if (TestProtocol.sDebugTracing) {
                Log.d(TestProtocol.NO_DRAG_TAG, "onTouchEvent 1");
            }
            return this.mActiveController.onControllerTouchEvent(ev);
        }
        return findActiveController(ev);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (TestProtocol.sDebugTracing) {
            Log.d(TestProtocol.NO_START_TAG, "BaseDragLayer.dispatchTouchEvent " + ev);
        }
        int action = ev.getAction();
        if (action == 0) {
            float x = ev.getX();
            float y = ev.getY();
            this.mTouchDispatchState |= 1;
            if (y < this.mSystemGestureRegion.top || x < this.mSystemGestureRegion.left || x > getWidth() - this.mSystemGestureRegion.right || y > getHeight() - this.mSystemGestureRegion.bottom) {
                this.mTouchDispatchState |= 2;
            } else {
                this.mTouchDispatchState &= -3;
            }
        } else if (action == 1 || action == 3) {
            int i = this.mTouchDispatchState & (-3);
            this.mTouchDispatchState = i;
            this.mTouchDispatchState = i & (-2);
        }
        super.dispatchTouchEvent(ev);
        return true;
    }

    public boolean prepareProxyEventStarting() {
        this.mProxyTouchController = null;
        int i = this.mTouchDispatchState;
        if ((i & 1) != 0 && this.mActiveController != null) {
            this.mTouchDispatchState = i & (-5);
            return false;
        }
        this.mTouchDispatchState = i | 4;
        return true;
    }

    public boolean proxyTouchEvent(MotionEvent ev) {
        boolean zOnControllerTouchEvent;
        TouchController touchController = this.mProxyTouchController;
        if (touchController != null) {
            zOnControllerTouchEvent = touchController.onControllerTouchEvent(ev);
        } else {
            TouchController touchControllerFindControllerToHandleTouch = findControllerToHandleTouch(ev);
            this.mProxyTouchController = touchControllerFindControllerToHandleTouch;
            zOnControllerTouchEvent = touchControllerFindControllerToHandleTouch != null;
        }
        int action = ev.getAction();
        if (action == 1 || action == 3) {
            this.mProxyTouchController = null;
            this.mTouchDispatchState &= -5;
        }
        return zOnControllerTouchEvent;
    }

    public float getDescendantRectRelativeToSelf(View descendant, Rect r) {
        float[] fArr = this.mTmpRectPoints;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        fArr[2] = descendant.getWidth();
        this.mTmpRectPoints[3] = descendant.getHeight();
        float descendantCoordRelativeToSelf = getDescendantCoordRelativeToSelf(descendant, this.mTmpRectPoints);
        float[] fArr2 = this.mTmpRectPoints;
        r.left = Math.round(Math.min(fArr2[0], fArr2[2]));
        float[] fArr3 = this.mTmpRectPoints;
        r.top = Math.round(Math.min(fArr3[1], fArr3[3]));
        float[] fArr4 = this.mTmpRectPoints;
        r.right = Math.round(Math.max(fArr4[0], fArr4[2]));
        float[] fArr5 = this.mTmpRectPoints;
        r.bottom = Math.round(Math.max(fArr5[1], fArr5[3]));
        return descendantCoordRelativeToSelf;
    }

    public float getLocationInDragLayer(View child, int[] loc) {
        loc[0] = 0;
        loc[1] = 0;
        return getDescendantCoordRelativeToSelf(child, loc);
    }

    public float getDescendantCoordRelativeToSelf(View descendant, int[] coord) {
        float[] fArr = this.mTmpXY;
        fArr[0] = coord[0];
        fArr[1] = coord[1];
        float descendantCoordRelativeToSelf = getDescendantCoordRelativeToSelf(descendant, fArr);
        Utilities.roundArray(this.mTmpXY, coord);
        return descendantCoordRelativeToSelf;
    }

    public float getDescendantCoordRelativeToSelf(View descendant, float[] coord) {
        return getDescendantCoordRelativeToSelf(descendant, coord, false);
    }

    public float getDescendantCoordRelativeToSelf(View descendant, float[] coord, boolean includeRootScroll) {
        return Utilities.getDescendantCoordRelativeToAncestor(descendant, this, coord, includeRootScroll);
    }

    public void mapCoordInSelfToDescendant(View descendant, float[] coord) {
        Utilities.mapCoordInSelfToDescendant(descendant, this, coord);
    }

    public void mapCoordInSelfToDescendant(View descendant, int[] coord) {
        float[] fArr = this.mTmpXY;
        fArr[0] = coord[0];
        fArr[1] = coord[1];
        Utilities.mapCoordInSelfToDescendant(descendant, this, fArr);
        Utilities.roundArray(this.mTmpXY, coord);
    }

    public void getViewRectRelativeToSelf(View v, Rect r) {
        int[] iArr = new int[2];
        getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        v.getLocationInWindow(iArr);
        int i3 = iArr[0] - i;
        int i4 = iArr[1] - i2;
        r.set(i3, i4, v.getMeasuredWidth() + i3, v.getMeasuredHeight() + i4);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchUnhandledMove(View focused, int direction) {
        return AbstractFloatingView.getTopOpenView(this.mActivity) != null;
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        AbstractFloatingView topOpenView = AbstractFloatingView.getTopOpenView(this.mActivity);
        if (topOpenView != null) {
            return topOpenView.requestFocus(direction, previouslyFocusedRect);
        }
        return super.onRequestFocusInDescendants(direction, previouslyFocusedRect);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> views, int direction, int focusableMode) {
        AbstractFloatingView topOpenView = AbstractFloatingView.getTopOpenView(this.mActivity);
        if (topOpenView != null) {
            topOpenView.addFocusables(views, direction);
        } else {
            super.addFocusables(views, direction, focusableMode);
        }
    }

    public void setTouchCompleteListener(TouchCompleteListener listener) {
        this.mTouchCompleteListener = listener;
    }

    @Override // com.android.launcher3.InsettableFrameLayout, android.widget.FrameLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.launcher3.InsettableFrameLayout, android.widget.FrameLayout, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // com.android.launcher3.InsettableFrameLayout, android.widget.FrameLayout, android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.launcher3.InsettableFrameLayout, android.widget.FrameLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    public MultiValueAlpha.AlphaProperty getAlphaProperty(int index) {
        return this.mMultiValueAlpha.getProperty(index);
    }

    public void dump(String prefix, PrintWriter writer) {
        writer.println(prefix + "DragLayer");
        if (this.mActiveController != null) {
            writer.println(prefix + "\tactiveController: " + this.mActiveController);
            this.mActiveController.dump(prefix + "\t", writer);
        }
        writer.println(prefix + "\tdragLayerAlpha : " + this.mMultiValueAlpha);
    }

    public static class LayoutParams extends InsettableFrameLayout.LayoutParams {
        public boolean customPosition;
        public int x;
        public int y;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            this.customPosition = false;
        }

        public LayoutParams(int width, int height) {
            super(width, height);
            this.customPosition = false;
        }

        public LayoutParams(ViewGroup.LayoutParams lp) {
            super(lp);
            this.customPosition = false;
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            if (layoutParams instanceof LayoutParams) {
                LayoutParams layoutParams2 = (LayoutParams) layoutParams;
                if (layoutParams2.customPosition) {
                    childAt.layout(layoutParams2.x, layoutParams2.y, layoutParams2.x + layoutParams2.width, layoutParams2.y + layoutParams2.height);
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public WindowInsets dispatchApplyWindowInsets(WindowInsets insets) {
        if (Utilities.ATLEAST_Q) {
            Insets mandatorySystemGestureInsets = insets.getMandatorySystemGestureInsets();
            this.mSystemGestureRegion.set(mandatorySystemGestureInsets.left, mandatorySystemGestureInsets.top, mandatorySystemGestureInsets.right, mandatorySystemGestureInsets.bottom);
        }
        return super.dispatchApplyWindowInsets(insets);
    }
}
