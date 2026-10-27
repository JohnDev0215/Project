package com.android.internal.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.Window;
import com.android.internal.R;
import com.android.internal.policy.DecorView;
import com.android.internal.policy.PhoneWindow;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class DecorCaptionView extends ViewGroup implements View.OnTouchListener, GestureDetector.OnGestureListener {
    private static final String TAG = "DecorCaptionView";
    private View mCaption;
    private boolean mCheckForDragging;
    private View mClickTarget;
    private View mClose;
    private final Rect mCloseRect;
    private View mContent;
    private int mDragSlop;
    private boolean mDragging;
    private GestureDetector mGestureDetector;
    private View mMaximize;
    private final Rect mMaximizeRect;
    private boolean mOverlayWithAppContent;
    private PhoneWindow mOwner;
    private int mRootScrollY;
    private boolean mShow;
    private ArrayList<View> mTouchDispatchList;
    private int mTouchDownX;
    private int mTouchDownY;

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public DecorCaptionView(Context context) {
        super(context);
        this.mOwner = null;
        this.mShow = false;
        this.mDragging = false;
        this.mOverlayWithAppContent = false;
        this.mTouchDispatchList = new ArrayList<>(2);
        this.mCloseRect = new Rect();
        this.mMaximizeRect = new Rect();
        init(context);
    }

    public DecorCaptionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOwner = null;
        this.mShow = false;
        this.mDragging = false;
        this.mOverlayWithAppContent = false;
        this.mTouchDispatchList = new ArrayList<>(2);
        this.mCloseRect = new Rect();
        this.mMaximizeRect = new Rect();
        init(context);
    }

    public DecorCaptionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mOwner = null;
        this.mShow = false;
        this.mDragging = false;
        this.mOverlayWithAppContent = false;
        this.mTouchDispatchList = new ArrayList<>(2);
        this.mCloseRect = new Rect();
        this.mMaximizeRect = new Rect();
        init(context);
    }

    private void init(Context context) {
        this.mDragSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mGestureDetector = new GestureDetector(context, this);
        setContentDescription(context.getString(R.string.accessibility_freeform_caption, context.getPackageManager().getApplicationLabel(context.getApplicationInfo())));
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mCaption = getChildAt(0);
    }

    public void setPhoneWindow(PhoneWindow phoneWindow, boolean z) {
        this.mOwner = phoneWindow;
        this.mShow = z;
        this.mOverlayWithAppContent = phoneWindow.isOverlayWithDecorCaptionEnabled();
        updateCaptionVisibility();
        this.mOwner.getDecorView().setOutlineProvider(ViewOutlineProvider.BOUNDS);
        this.mMaximize = findViewById(R.id.maximize_window);
        this.mClose = findViewById(R.id.close_window);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (this.mMaximizeRect.contains(x, y - this.mRootScrollY)) {
                this.mClickTarget = this.mMaximize;
            }
            if (this.mCloseRect.contains(x, y - this.mRootScrollY)) {
                this.mClickTarget = this.mClose;
            }
        }
        return this.mClickTarget != null;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mClickTarget == null) {
            return false;
        }
        this.mGestureDetector.onTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        if (action == 1 || action == 3) {
            this.mClickTarget = null;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0052  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r8, android.view.MotionEvent r9) {
        /*
            r7 = this;
            float r8 = r9.getX()
            int r8 = (int) r8
            float r0 = r9.getY()
            int r0 = (int) r0
            int r1 = r9.getActionIndex()
            int r1 = r9.getToolType(r1)
            r2 = 3
            r3 = 0
            r4 = 1
            if (r1 != r2) goto L19
            r1 = r4
            goto L1a
        L19:
            r1 = r3
        L1a:
            int r5 = r9.getButtonState()
            r5 = r5 & r4
            if (r5 == 0) goto L23
            r5 = r4
            goto L24
        L23:
            r5 = r3
        L24:
            int r6 = r9.getActionMasked()
            if (r6 == 0) goto L62
            if (r6 == r4) goto L52
            r5 = 2
            if (r6 == r5) goto L32
            if (r6 == r2) goto L52
            goto L71
        L32:
            boolean r2 = r7.mDragging
            if (r2 != 0) goto L71
            boolean r2 = r7.mCheckForDragging
            if (r2 == 0) goto L71
            if (r1 != 0) goto L42
            boolean r8 = r7.passedSlop(r8, r0)
            if (r8 == 0) goto L71
        L42:
            r7.mCheckForDragging = r3
            r7.mDragging = r4
            float r8 = r9.getRawX()
            float r9 = r9.getRawY()
            r7.startMovingTask(r8, r9)
            goto L71
        L52:
            boolean r8 = r7.mDragging
            if (r8 != 0) goto L57
            goto L71
        L57:
            if (r6 != r4) goto L5c
            r7.finishMovingTask()
        L5c:
            r7.mDragging = r3
            boolean r7 = r7.mCheckForDragging
            r7 = r7 ^ r4
            return r7
        L62:
            boolean r9 = r7.mShow
            if (r9 != 0) goto L67
            return r3
        L67:
            if (r1 == 0) goto L6b
            if (r5 == 0) goto L71
        L6b:
            r7.mCheckForDragging = r4
            r7.mTouchDownX = r8
            r7.mTouchDownY = r0
        L71:
            boolean r8 = r7.mDragging
            if (r8 != 0) goto L79
            boolean r7 = r7.mCheckForDragging
            if (r7 == 0) goto L7a
        L79:
            r3 = r4
        L7a:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.widget.DecorCaptionView.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    private boolean passedSlop(int i, int i2) {
        return Math.abs(i - this.mTouchDownX) > this.mDragSlop || Math.abs(i2 - this.mTouchDownY) > this.mDragSlop;
    }

    public void onConfigurationChanged(boolean z) {
        this.mShow = z;
        updateCaptionVisibility();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            throw new IllegalArgumentException("params " + layoutParams + " must subclass MarginLayoutParams");
        }
        if (i >= 2 || getChildCount() >= 2) {
            throw new IllegalStateException("DecorCaptionView can only handle 1 client view");
        }
        super.addView(view, 0, layoutParams);
        this.mContent = view;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int measuredHeight;
        if (this.mCaption.getVisibility() != 8) {
            measureChildWithMargins(this.mCaption, i, 0, i2, 0);
            measuredHeight = this.mCaption.getMeasuredHeight();
        } else {
            measuredHeight = 0;
        }
        int i3 = measuredHeight;
        View view = this.mContent;
        if (view != null) {
            if (this.mOverlayWithAppContent) {
                measureChildWithMargins(view, i, 0, i2, 0);
            } else {
                measureChildWithMargins(view, i, 0, i2, i3);
            }
        }
        setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int bottom;
        if (this.mCaption.getVisibility() != 8) {
            View view = this.mCaption;
            view.layout(0, 0, view.getMeasuredWidth(), this.mCaption.getMeasuredHeight());
            bottom = this.mCaption.getBottom() - this.mCaption.getTop();
            this.mMaximize.getHitRect(this.mMaximizeRect);
            this.mClose.getHitRect(this.mCloseRect);
        } else {
            this.mMaximizeRect.setEmpty();
            this.mCloseRect.setEmpty();
            bottom = 0;
        }
        View view2 = this.mContent;
        if (view2 != null) {
            if (this.mOverlayWithAppContent) {
                view2.layout(0, 0, view2.getMeasuredWidth(), this.mContent.getMeasuredHeight());
            } else {
                view2.layout(0, bottom, view2.getMeasuredWidth(), this.mContent.getMeasuredHeight() + bottom);
            }
        }
        ((DecorView) this.mOwner.getDecorView()).notifyCaptionHeightChanged();
        this.mOwner.notifyRestrictedCaptionAreaCallback(this.mMaximize.getLeft(), this.mMaximize.getTop(), this.mClose.getRight(), this.mClose.getBottom());
    }

    private void updateCaptionVisibility() {
        this.mCaption.setVisibility(this.mShow ? 0 : 8);
        this.mCaption.setOnTouchListener(this);
    }

    private void toggleFreeformWindowingMode() {
        Window.WindowControllerCallback windowControllerCallback = this.mOwner.getWindowControllerCallback();
        if (windowControllerCallback != null) {
            try {
                windowControllerCallback.toggleFreeformWindowingMode();
            } catch (RemoteException unused) {
                Log.e(TAG, "Cannot change task workspace.");
            }
        }
    }

    public boolean isCaptionShowing() {
        return this.mShow;
    }

    public int getCaptionHeight() {
        View view = this.mCaption;
        if (view != null) {
            return view.getHeight();
        }
        return 0;
    }

    public void removeContentView() {
        View view = this.mContent;
        if (view != null) {
            removeView(view);
            this.mContent = null;
        }
    }

    public View getCaption() {
        return this.mCaption;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -1);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ViewGroup.MarginLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        View view = this.mClickTarget;
        if (view == this.mMaximize) {
            toggleFreeformWindowingMode();
        } else if (view == this.mClose) {
            this.mOwner.dispatchOnWindowDismissed(true, false);
        }
        return true;
    }

    public void onRootViewScrollYChanged(int i) {
        View view = this.mCaption;
        if (view != null) {
            this.mRootScrollY = i;
            view.setTranslationY(i);
        }
    }
}
