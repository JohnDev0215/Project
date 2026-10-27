package org.chromium.content.browser.input;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content.browser.ContainerViewObserver;
import org.chromium.content.browser.ContentViewCore;
import org.chromium.content.browser.PositionObserver;
import org.chromium.content.browser.ViewPositionObserver;
import org.chromium.content_public.browser.GestureStateListener;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class PopupTouchHandleDrawable extends View {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int FADE_IN_DURATION_MS = 200;
    private static final int MOVING_FADE_IN_DELAY_MS = 300;
    private float mAlpha;
    private boolean mAttachedToWindow;
    private final PopupWindow mContainer;
    private ContentViewCore mContentViewCore;
    private Runnable mDeferredHandleFadeInRunnable;
    private boolean mDelayVisibilityUpdateWAR;
    private Drawable mDrawable;
    private long mFadeStartTime;
    private boolean mFocused;
    private final GestureStateListener mGestureStateListener;
    private boolean mHasPendingInvalidate;
    private Runnable mInvalidationRunnable;
    private boolean mMirrorHorizontal;
    private boolean mMirrorVertical;
    private int mOrientation;
    private final PositionObserver.Listener mParentPositionListener;
    private PositionObserver mParentPositionObserver;
    private int mParentPositionX;
    private int mParentPositionY;
    private final ContainerViewObserver mParentViewObserver;
    private int mPositionX;
    private int mPositionY;
    private boolean mScrolling;
    private final int[] mTempScreenCoords;
    private boolean mTemporarilyHidden;
    private long mTemporarilyHiddenExpireTime;
    private Runnable mTemporarilyHiddenExpiredRunnable;
    private boolean mVisible;
    private boolean mWasShowingAllowed;

    private PopupTouchHandleDrawable(ContentViewCore contentViewCore) {
        super(contentViewCore.getContainerView().getContext());
        this.mTempScreenCoords = new int[2];
        this.mOrientation = 3;
        this.mContentViewCore = contentViewCore;
        PopupWindow popupWindow = new PopupWindow(this.mContentViewCore.getWindowAndroid().getContext().get(), (AttributeSet) null, R.attr.textSelectHandleWindowStyle);
        this.mContainer = popupWindow;
        popupWindow.setSplitTouchEnabled(true);
        popupWindow.setClippingEnabled(false);
        popupWindow.setAnimationStyle(0);
        setWindowLayoutType(popupWindow, 1002);
        popupWindow.setWidth(-2);
        popupWindow.setHeight(-2);
        this.mAlpha = 1.0f;
        this.mVisible = getVisibility() == 0;
        this.mFocused = this.mContentViewCore.getContainerView().hasWindowFocus();
        this.mParentPositionObserver = new ViewPositionObserver(this.mContentViewCore.getContainerView());
        this.mParentPositionListener = new PositionObserver.Listener() { // from class: org.chromium.content.browser.input.PopupTouchHandleDrawable.1
            @Override // org.chromium.content.browser.PositionObserver.Listener
            public void onPositionChanged(int i, int i2) {
                PopupTouchHandleDrawable.this.updateParentPosition(i, i2);
            }
        };
        ContainerViewObserver containerViewObserver = new ContainerViewObserver() { // from class: org.chromium.content.browser.input.PopupTouchHandleDrawable.2
            @Override // org.chromium.content.browser.ContainerViewObserver
            public void onContainerViewChanged(ViewGroup viewGroup) {
                PopupTouchHandleDrawable.this.mParentPositionObserver.clearListener();
                PopupTouchHandleDrawable.this.mParentPositionObserver = new ViewPositionObserver(viewGroup);
                if (PopupTouchHandleDrawable.this.mContainer.isShowing()) {
                    PopupTouchHandleDrawable.this.mParentPositionObserver.addListener(PopupTouchHandleDrawable.this.mParentPositionListener);
                }
            }
        };
        this.mParentViewObserver = containerViewObserver;
        GestureStateListener gestureStateListener = new GestureStateListener() { // from class: org.chromium.content.browser.input.PopupTouchHandleDrawable.3
            @Override // org.chromium.content_public.browser.GestureStateListener
            public void onScrollStarted(int i, int i2) {
                PopupTouchHandleDrawable.this.setIsScrolling(true);
            }

            @Override // org.chromium.content_public.browser.GestureStateListener
            public void onScrollEnded(int i, int i2) {
                PopupTouchHandleDrawable.this.setIsScrolling(false);
            }

            @Override // org.chromium.content_public.browser.GestureStateListener
            public void onFlingStartGesture(int i, int i2, int i3, int i4) {
                PopupTouchHandleDrawable.this.setIsScrolling(false);
            }

            @Override // org.chromium.content_public.browser.GestureStateListener
            public void onScrollOffsetOrExtentChanged(int i, int i2) {
                PopupTouchHandleDrawable.this.temporarilyHide();
            }

            @Override // org.chromium.content_public.browser.GestureStateListener
            public void onWindowFocusChanged(boolean z) {
                PopupTouchHandleDrawable.this.setIsFocused(z);
            }

            @Override // org.chromium.content_public.browser.GestureStateListener
            public void onDestroyed() {
                PopupTouchHandleDrawable.this.destroy();
            }
        };
        this.mGestureStateListener = gestureStateListener;
        this.mContentViewCore.addGestureStateListener(gestureStateListener);
        this.mContentViewCore.addContainerViewObserver(containerViewObserver);
    }

    private static void setWindowLayoutType(PopupWindow popupWindow, int i) {
        popupWindow.setWindowLayoutType(i);
    }

    private static Drawable getHandleDrawable(Context context, int i) {
        if (i == 0) {
            return HandleViewResources.getLeftHandleDrawable(context);
        }
        if (i == 1) {
            return HandleViewResources.getCenterHandleDrawable(context);
        }
        if (i == 2) {
            return HandleViewResources.getRightHandleDrawable(context);
        }
        return HandleViewResources.getCenterHandleDrawable(context);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ContentViewCore contentViewCore = this.mContentViewCore;
        if (contentViewCore == null) {
            return false;
        }
        contentViewCore.getContainerView().getLocationOnScreen(this.mTempScreenCoords);
        float rawX = (motionEvent.getRawX() - motionEvent.getX()) - this.mTempScreenCoords[0];
        float rawY = (motionEvent.getRawY() - motionEvent.getY()) - this.mTempScreenCoords[1];
        MotionEvent motionEventObtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
        motionEventObtainNoHistory.offsetLocation(rawX, rawY);
        boolean zOnTouchHandleEvent = this.mContentViewCore.onTouchHandleEvent(motionEventObtainNoHistory);
        motionEventObtainNoHistory.recycle();
        return zOnTouchHandleEvent;
    }

    private static PopupTouchHandleDrawable create(ContentViewCore contentViewCore) {
        return new PopupTouchHandleDrawable(contentViewCore);
    }

    private void setOrientation(int i, boolean z, boolean z2) {
        boolean z3 = true;
        boolean z4 = this.mOrientation != i;
        if (this.mMirrorHorizontal == z2 && this.mMirrorVertical == z) {
            z3 = false;
        }
        this.mOrientation = i;
        this.mMirrorHorizontal = z2;
        this.mMirrorVertical = z;
        if (z4) {
            this.mDrawable = getHandleDrawable(getContext(), this.mOrientation);
        }
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            drawable.setAlpha((int) (this.mAlpha * 255.0f));
        }
        if (z4 || z3) {
            scheduleInvalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateParentPosition(int i, int i2) {
        if (this.mParentPositionX == i && this.mParentPositionY == i2) {
            return;
        }
        this.mParentPositionX = i;
        this.mParentPositionY = i2;
        temporarilyHide();
    }

    private int getContainerPositionX() {
        return this.mParentPositionX + this.mPositionX;
    }

    private int getContainerPositionY() {
        return this.mParentPositionY + this.mPositionY;
    }

    private void updatePosition() {
        this.mContainer.update(getContainerPositionX(), getContainerPositionY(), getRight() - getLeft(), getBottom() - getTop());
    }

    private boolean isShowingAllowed() {
        return this.mAttachedToWindow && this.mVisible && this.mFocused && !this.mScrolling && !this.mTemporarilyHidden;
    }

    private void updateVisibility() {
        int i = isShowingAllowed() ? 0 : 4;
        if (i == 0 && getVisibility() != 0 && !this.mDelayVisibilityUpdateWAR) {
            this.mDelayVisibilityUpdateWAR = true;
            scheduleInvalidate();
        } else {
            this.mDelayVisibilityUpdateWAR = false;
            setVisibility(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsScrolling(boolean z) {
        if (this.mScrolling == z) {
            return;
        }
        this.mScrolling = z;
        onVisibilityInputChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsFocused(boolean z) {
        if (this.mFocused == z) {
            return;
        }
        this.mFocused = z;
        onVisibilityInputChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTemporarilyHidden(boolean z) {
        if (this.mTemporarilyHidden == z) {
            return;
        }
        this.mTemporarilyHidden = z;
        if (z) {
            if (this.mTemporarilyHiddenExpiredRunnable == null) {
                this.mTemporarilyHiddenExpiredRunnable = new Runnable() { // from class: org.chromium.content.browser.input.PopupTouchHandleDrawable.4
                    @Override // java.lang.Runnable
                    public void run() {
                        PopupTouchHandleDrawable.this.setTemporarilyHidden(false);
                    }
                };
            }
            removeCallbacks(this.mTemporarilyHiddenExpiredRunnable);
            postDelayed(this.mTemporarilyHiddenExpiredRunnable, Math.max(0L, this.mTemporarilyHiddenExpireTime - SystemClock.uptimeMillis()));
        } else {
            Runnable runnable = this.mTemporarilyHiddenExpiredRunnable;
            if (runnable != null) {
                removeCallbacks(runnable);
            }
        }
        onVisibilityInputChanged();
    }

    private void onVisibilityInputChanged() {
        boolean zIsShowingAllowed;
        if (this.mContainer.isShowing() && this.mWasShowingAllowed != (zIsShowingAllowed = isShowingAllowed())) {
            this.mWasShowingAllowed = zIsShowingAllowed;
            cancelFadeIn();
            if (zIsShowingAllowed) {
                if (this.mDeferredHandleFadeInRunnable == null) {
                    this.mDeferredHandleFadeInRunnable = new Runnable() { // from class: org.chromium.content.browser.input.PopupTouchHandleDrawable.5
                        @Override // java.lang.Runnable
                        public void run() {
                            PopupTouchHandleDrawable.this.beginFadeIn();
                        }
                    };
                }
                postOnAnimation(this.mDeferredHandleFadeInRunnable);
                return;
            }
            updateVisibility();
        }
    }

    private void updateAlpha() {
        if (this.mAlpha == 1.0f) {
            return;
        }
        float fMin = Math.min(1.0f, (AnimationUtils.currentAnimationTimeMillis() - this.mFadeStartTime) / 200.0f);
        this.mAlpha = fMin;
        this.mDrawable.setAlpha((int) (fMin * 255.0f));
        scheduleInvalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void temporarilyHide() {
        if (this.mContainer.isShowing()) {
            this.mTemporarilyHiddenExpireTime = SystemClock.uptimeMillis() + 300;
            setTemporarilyHidden(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doInvalidate() {
        if (this.mContainer.isShowing()) {
            updateVisibility();
            updatePosition();
            invalidate();
        }
    }

    private void scheduleInvalidate() {
        if (this.mInvalidationRunnable == null) {
            this.mInvalidationRunnable = new Runnable() { // from class: org.chromium.content.browser.input.PopupTouchHandleDrawable.6
                @Override // java.lang.Runnable
                public void run() {
                    PopupTouchHandleDrawable.this.mHasPendingInvalidate = false;
                    PopupTouchHandleDrawable.this.doInvalidate();
                }
            };
        }
        if (this.mHasPendingInvalidate) {
            return;
        }
        this.mHasPendingInvalidate = true;
        postOnAnimation(this.mInvalidationRunnable);
    }

    private void cancelFadeIn() {
        Runnable runnable = this.mDeferredHandleFadeInRunnable;
        if (runnable == null) {
            return;
        }
        removeCallbacks(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void beginFadeIn() {
        if (getVisibility() == 0) {
            return;
        }
        this.mAlpha = 0.0f;
        this.mFadeStartTime = AnimationUtils.currentAnimationTimeMillis();
        doInvalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        Drawable drawable = this.mDrawable;
        if (drawable == null) {
            setMeasuredDimension(0, 0);
        } else {
            setMeasuredDimension(drawable.getIntrinsicWidth(), this.mDrawable.getIntrinsicHeight());
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mDrawable == null) {
            return;
        }
        boolean z = this.mMirrorHorizontal || this.mMirrorVertical;
        if (z) {
            canvas.save();
            canvas.scale(this.mMirrorHorizontal ? -1.0f : 1.0f, this.mMirrorVertical ? -1.0f : 1.0f, getWidth() / 2.0f, getHeight() / 2.0f);
        }
        updateAlpha();
        this.mDrawable.setBounds(0, 0, getRight() - getLeft(), getBottom() - getTop());
        this.mDrawable.draw(canvas);
        if (z) {
            canvas.restore();
        }
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
        onVisibilityInputChanged();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttachedToWindow = false;
        onVisibilityInputChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroy() {
        if (this.mContentViewCore == null) {
            return;
        }
        hide();
        this.mContentViewCore.removeGestureStateListener(this.mGestureStateListener);
        this.mContentViewCore.removeContainerViewObserver(this.mParentViewObserver);
        this.mContentViewCore = null;
    }

    private void show() {
        if (this.mContentViewCore == null || this.mContainer.isShowing()) {
            return;
        }
        updateParentPosition(this.mParentPositionObserver.getPositionX(), this.mParentPositionObserver.getPositionY());
        this.mParentPositionObserver.addListener(this.mParentPositionListener);
        this.mContainer.setContentView(this);
        try {
            this.mContainer.showAtLocation(this.mContentViewCore.getContainerView(), 0, getContainerPositionX(), getContainerPositionY());
        } catch (WindowManager.BadTokenException unused) {
            hide();
        }
    }

    private void hide() {
        this.mTemporarilyHiddenExpireTime = 0L;
        setTemporarilyHidden(false);
        this.mAlpha = 1.0f;
        if (this.mContainer.isShowing()) {
            try {
                this.mContainer.dismiss();
            } catch (IllegalArgumentException unused) {
            }
        }
        this.mParentPositionObserver.clearListener();
    }

    private void setOrigin(float f, float f2) {
        if (this.mPositionX == f && this.mPositionY == f2) {
            return;
        }
        this.mPositionX = (int) f;
        this.mPositionY = (int) f2;
        if (getVisibility() == 0) {
            scheduleInvalidate();
        }
    }

    private void setVisible(boolean z) {
        if (this.mVisible == z) {
            return;
        }
        this.mVisible = z;
        onVisibilityInputChanged();
    }

    private int getPositionX() {
        return this.mPositionX;
    }

    private float getHandleHorizontalPaddingRatio() {
        return HandleViewResources.getHandleHorizontalPaddingRatio();
    }

    private int getPositionY() {
        return this.mPositionY;
    }

    private int getVisibleWidth() {
        Drawable drawable = this.mDrawable;
        if (drawable == null) {
            return 0;
        }
        return drawable.getIntrinsicWidth();
    }

    private int getVisibleHeight() {
        Drawable drawable = this.mDrawable;
        if (drawable == null) {
            return 0;
        }
        return drawable.getIntrinsicHeight();
    }
}
