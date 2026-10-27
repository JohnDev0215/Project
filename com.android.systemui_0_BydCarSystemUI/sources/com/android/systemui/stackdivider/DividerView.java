package com.android.systemui.stackdivider;

import android.animation.AnimationHandler;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.Region;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceControl;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewRootImpl;
import android.view.ViewTreeObserver;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import com.android.internal.graphics.SfVsyncFrameCallbackProvider;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.policy.DividerSnapAlgorithm;
import com.android.internal.policy.DockedDividerUtils;
import com.android.systemui.Interpolators;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.stackdivider.view.DividerMoreButtonView;
import com.android.systemui.statusbar.FlingAnimationUtils;
import com.android.systemui.statusbar.wm.Util;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class DividerView extends FrameLayout implements View.OnTouchListener, ViewTreeObserver.OnComputeInternalInsetsListener {
    private static final float ADJUSTED_FOR_IME_SCALE = 0.5f;
    private static final boolean DEBUG = true;
    public static final int EXIT_ANIMATION_DIRECTION_LEFT = 2;
    public static final int EXIT_ANIMATION_DIRECTION_RIGHT = 1;
    public static final int INVALID_RECENTS_GROW_TARGET = -1;
    private static final int LOG_VALUE_RESIZE_50_50 = 0;
    private static final int LOG_VALUE_RESIZE_DOCKED_LARGER = 2;
    private static final int LOG_VALUE_RESIZE_DOCKED_SMALLER = 1;
    private static final int LOG_VALUE_UNDOCK_MAX_DOCKED = 0;
    private static final int LOG_VALUE_UNDOCK_MAX_OTHER = 1;
    private static final float MINIMIZE_DOCK_SCALE = 0.0f;
    private static final String TAG = "DividerView";
    private static final int TASK_POSITION_SAME = Integer.MAX_VALUE;
    static final long TOUCH_ANIMATION_DURATION = 150;
    static final long TOUCH_RELEASE_ANIMATION_DURATION = 200;
    private boolean mAdjustedForIme;
    private final AnimationHandler mAnimationHandler;
    private boolean mBackgroundLifted;
    private DividerCallbacks mCallback;
    private ValueAnimator mCurrentAnimator;
    private final Display mDefaultDisplay;
    private final Divider mDivider;
    private int mDividerInsets;
    private DividerMoreButtonView mDividerMoreButton;
    int mDividerPositionX;
    int mDividerPositionY;
    private int mDividerSize;
    private final DividerUtils mDividerUtils;
    private int mDockSide;
    private final Rect mDockedInsetRect;
    private final Rect mDockedRect;
    private boolean mDockedStackMinimized;
    private final Rect mDockedTaskRect;
    private boolean mEntranceAnimationRunning;
    private boolean mExitAnimationRunning;
    private int mExitStartPosition;
    boolean mFirstLayout;
    private FlingAnimationUtils mFlingAnimationUtils;
    private boolean mGrowRecents;
    private DividerHandleView mHandle;
    private final Handler mHandler;
    private boolean mHomeStackResizable;
    private boolean mIsInMinimizeInteraction;
    private final Rect mLastResizeRect;
    private int mLongPressEntraceAnimDuration;
    private boolean mMoving;
    private final Rect mOtherInsetRect;
    private final Rect mOtherRect;
    private final Rect mOtherTaskRect;
    private boolean mRemoved;
    private final Runnable mResetBackgroundRunnable;
    DividerSnapAlgorithm.SnapTarget mSnapTargetBeforeMinimized;
    private SplitDisplayLayout mSplitLayout;
    private int mStartPosition;
    private int mStartX;
    private int mStartY;
    private DividerState mState;
    private boolean mSurfaceHidden;
    private SplitScreenTaskOrganizer mTiles;
    private final Matrix mTmpMatrix;
    private final Rect mTmpRect;
    private final float[] mTmpValues;
    private int mTouchElevation;
    private int mTouchSlop;
    private final Runnable mUpdateEmbeddedMatrix;
    private VelocityTracker mVelocityTracker;
    private DividerWindowManager mWindowManager;
    private WindowManagerProxy mWindowManagerProxy;
    private static final PathInterpolator SLOWDOWN_INTERPOLATOR = new PathInterpolator(0.5f, 1.0f, 0.5f, 1.0f);
    private static final PathInterpolator DIM_INTERPOLATOR = new PathInterpolator(0.23f, 0.87f, 0.52f, -0.11f);
    private static final Interpolator IME_ADJUST_INTERPOLATOR = new PathInterpolator(0.2f, 0.0f, 0.1f, 1.0f);

    public interface DividerCallbacks {
        void growRecents();

        void onDraggingEnd();

        void onDraggingStart();
    }

    private static boolean dockSideBottomRight(int dockSide) {
        return dockSide == 4 || dockSide == 3;
    }

    private static boolean dockSideTopLeft(int dockSide) {
        return dockSide == 2 || dockSide == 1;
    }

    public /* synthetic */ void lambda$new$0$DividerView() {
        if (getViewRootImpl() == null) {
            return;
        }
        if (isHorizontalDivision()) {
            this.mTmpMatrix.setTranslate(0.0f, this.mDividerPositionY - this.mDividerInsets);
        } else {
            this.mTmpMatrix.setTranslate(this.mDividerPositionX - this.mDividerInsets, 0.0f);
        }
        this.mTmpMatrix.getValues(this.mTmpValues);
        try {
            getViewRootImpl().getAccessibilityEmbeddedConnection().setScreenMatrix(this.mTmpValues);
        } catch (RemoteException unused) {
        }
    }

    public DividerView(Context context) {
        this(context, null);
    }

    public DividerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DividerView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public DividerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mDockedRect = new Rect();
        this.mDockedTaskRect = new Rect();
        this.mOtherTaskRect = new Rect();
        this.mOtherRect = new Rect();
        this.mDockedInsetRect = new Rect();
        this.mOtherInsetRect = new Rect();
        this.mLastResizeRect = new Rect();
        this.mTmpRect = new Rect();
        AnimationHandler animationHandler = new AnimationHandler();
        this.mAnimationHandler = animationHandler;
        this.mFirstLayout = true;
        this.mTmpMatrix = new Matrix();
        this.mTmpValues = new float[9];
        this.mSurfaceHidden = false;
        this.mHandler = new Handler();
        this.mResetBackgroundRunnable = new Runnable() { // from class: com.android.systemui.stackdivider.DividerView.1
            @Override // java.lang.Runnable
            public void run() {
                DividerView.this.resetBackground();
            }
        };
        this.mUpdateEmbeddedMatrix = new Runnable() { // from class: com.android.systemui.stackdivider.-$$Lambda$DividerView$z2WUnJSNUXqhL903Uf3Y1PGqErg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$DividerView();
            }
        };
        this.mDefaultDisplay = ((DisplayManager) this.mContext.getSystemService("display")).getDisplay(0);
        animationHandler.setProvider(new SfVsyncFrameCallbackProvider());
        this.mDivider = (Divider) SysUiServiceProvider.getComponent(this.mContext, Divider.class);
        this.mDividerUtils = DividerUtils.getInstance();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        BydLogHelper.logD(TAG, "onFinishInflate");
        this.mHandle = (DividerHandleView) findViewById(R.id.docked_divider_handle);
        this.mDividerMoreButton = (DividerMoreButtonView) findViewById(R.id.divider_more_button);
        this.mHandle.setOnTouchListener(this);
        int dimensionPixelSize = getResources().getDimensionPixelSize(android.R.dimen.config_viewMaxFlingVelocity);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(android.R.dimen.config_viewConfigurationTouchSlop);
        this.mDividerInsets = dimensionPixelSize2;
        this.mDividerSize = dimensionPixelSize - (dimensionPixelSize2 * 2);
        BydLogHelper.logD("onFinishInflate dividerWindowWidth : " + dimensionPixelSize + " , mDividerInsets : " + this.mDividerInsets + " , mDividerSize : " + this.mDividerSize);
        this.mTouchElevation = getResources().getDimensionPixelSize(R.dimen.docked_stack_divider_lift_elevation);
        this.mLongPressEntraceAnimDuration = getResources().getInteger(R.integer.long_press_dock_anim_duration);
        this.mGrowRecents = getResources().getBoolean(R.bool.recents_grow_in_multiwindow);
        this.mTouchSlop = ViewConfiguration.get(this.mContext).getScaledTouchSlop();
        this.mFlingAnimationUtils = new FlingAnimationUtils(getResources().getDisplayMetrics(), 0.3f);
        int i = getResources().getConfiguration().orientation;
        getViewTreeObserver().addOnComputeInternalInsetsListener(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        BydLogHelper.logD("onAttachedToWindow");
        BydLogHelper.logD("onAttachedToWindow mDockSide : " + this.mDockSide + " , mIsInMinimizeInteraction : " + this.mIsInMinimizeInteraction);
        if (this.mDockSide != -1 && !this.mIsInMinimizeInteraction) {
            saveSnapTargetBeforeMinimized(this.mSnapTargetBeforeMinimized);
        }
        this.mFirstLayout = true;
    }

    void onDividerRemoved() {
        this.mRemoved = true;
        this.mCallback = null;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        BydLogHelper.logD(TAG, "onLayout mFirstLayout : " + this.mFirstLayout);
        if (this.mFirstLayout) {
            initializeSurfaceState();
            this.mFirstLayout = false;
        }
        if (changed) {
            notifySplitScreenBoundsChanged();
        }
    }

    public void injectDependencies(DividerWindowManager windowManager, DividerState dividerState, DividerCallbacks callback, SplitScreenTaskOrganizer tiles, SplitDisplayLayout sdl, WindowManagerProxy wmProxy) {
        BydLogHelper.logD(TAG, "injectDependencies");
        this.mWindowManager = windowManager;
        this.mState = dividerState;
        this.mCallback = callback;
        this.mTiles = tiles;
        this.mSplitLayout = sdl;
        this.mWindowManagerProxy = wmProxy;
        if (dividerState.mRatioPositionBeforeMinimized == 0.0f) {
            this.mSnapTargetBeforeMinimized = this.mSplitLayout.getSnapAlgorithm().getMiddleTarget();
        } else {
            repositionSnapTargetBeforeMinimized();
        }
    }

    public Rect getNonMinimizedSplitScreenSecondaryBounds() {
        this.mOtherTaskRect.set(this.mSplitLayout.mSecondary);
        return this.mOtherTaskRect;
    }

    private boolean inSplitMode() {
        BydLogHelper.logD(TAG, "inSplitMode : " + getVisibility());
        return getVisibility() == 0;
    }

    void setHidden(final boolean hidden) {
        BydLogHelper.logD(TAG, "setHidden hidden : " + hidden);
        if (this.mSurfaceHidden == hidden) {
            return;
        }
        this.mSurfaceHidden = hidden;
        post(new Runnable() { // from class: com.android.systemui.stackdivider.-$$Lambda$DividerView$aoLBAYQAKCIz2dnPAL_Kd1zF2S4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setHidden$1$DividerView(hidden);
            }
        });
    }

    public /* synthetic */ void lambda$setHidden$1$DividerView(boolean z) {
        SurfaceControl windowSurfaceControl = getWindowSurfaceControl();
        if (windowSurfaceControl == null) {
            return;
        }
        SurfaceControl.Transaction transaction = this.mTiles.getTransaction();
        if (z) {
            transaction.hide(windowSurfaceControl);
        } else {
            transaction.show(windowSurfaceControl);
        }
        transaction.apply();
        this.mTiles.releaseTransaction(transaction);
    }

    boolean isHidden() {
        return this.mSurfaceHidden;
    }

    public boolean startDragging(boolean animate, boolean touching) {
        BydLogHelper.logD(TAG, "startDragging animate : " + animate + " , touching : " + touching);
        cancelFlingAnimation();
        this.mDockSide = this.mSplitLayout.getPrimarySplitSide();
        BydLogHelper.logD("startDragging mDockSide : " + this.mDockSide);
        this.mWindowManagerProxy.setResizing(true);
        if (touching) {
            this.mWindowManager.setSlippery(false);
            liftBackground();
        }
        DividerCallbacks dividerCallbacks = this.mCallback;
        if (dividerCallbacks != null) {
            dividerCallbacks.onDraggingStart();
        }
        return inSplitMode();
    }

    public void stopDragging(int position, float velocity, boolean avoidDismissStart, boolean logMetrics) {
        BydLogHelper.logD(TAG, "stopDragging position : " + position + " , velocity : " + velocity + " , avoidDismissStart : " + avoidDismissStart + " , logMetrics : " + logMetrics);
        int i = this.mStartPosition;
        if (i != position || i != getSnapAlgorithm().getMiddleTarget().position) {
            fling(position, velocity, avoidDismissStart, logMetrics);
        }
        this.mWindowManager.setSlippery(true);
        releaseBackground();
    }

    public void stopDragging(int position, DividerSnapAlgorithm.SnapTarget target, long duration, long startDelay, long endDelay, Interpolator interpolator) {
        BydLogHelper.logD(TAG, "stopDragging position : " + position + " , target : " + target + " , duration : " + duration + " , startDelay : " + startDelay + " , interpolator : " + interpolator + " , endDelay : " + endDelay);
        flingTo(position, target, duration, startDelay, endDelay, interpolator);
        this.mWindowManager.setSlippery(true);
        releaseBackground();
    }

    private void stopDragging() {
        BydLogHelper.logD(TAG, "stopDragging");
        this.mWindowManager.setSlippery(true);
        releaseBackground();
    }

    private void updateDockSide() {
        BydLogHelper.logD(TAG, "updateDockSide mDockSide : " + this.mDockSide);
        this.mDockSide = this.mSplitLayout.getPrimarySplitSide();
        BydLogHelper.logD("updateDockSide mDockSide : " + this.mDockSide);
    }

    public DividerSnapAlgorithm getSnapAlgorithm() {
        BydLogHelper.logD(TAG, "getSnapAlgorithm mDockedStackMinimized : " + this.mDockedStackMinimized);
        return this.mDockedStackMinimized ? this.mSplitLayout.getMinimizedSnapAlgorithm(this.mHomeStackResizable) : this.mSplitLayout.getSnapAlgorithm();
    }

    public int getCurrentPosition() {
        BydLogHelper.logD(TAG, "getCurrentPosition");
        return isHorizontalDivision() ? this.mDividerPositionY : this.mDividerPositionX;
    }

    public boolean isMinimized() {
        return this.mDockedStackMinimized;
    }

    public boolean isDividerHandleViewMoving() {
        return this.mMoving;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00a5  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r6, android.view.MotionEvent r7) {
        /*
            Method dump skipped, instruction units count: 257
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.stackdivider.DividerView.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public void dismissDockedStack() {
        BydLogHelper.logD(TAG, "dismissDockedStack");
        this.mWindowManagerProxy.dismissOrMaximizeDocked(this.mTiles, this.mSplitLayout, true);
    }

    private void logResizeEvent(DividerSnapAlgorithm.SnapTarget snapTarget) {
        BydLogHelper.logD(TAG, "logResizeEvent snapTarget : " + snapTarget);
        if (snapTarget == this.mSplitLayout.getSnapAlgorithm().getDismissStartTarget()) {
            MetricsLogger.action(this.mContext, 390, dockSideTopLeft(this.mDockSide) ? 1 : 0);
            return;
        }
        if (snapTarget == this.mSplitLayout.getSnapAlgorithm().getDismissEndTarget()) {
            MetricsLogger.action(this.mContext, 390, dockSideBottomRight(this.mDockSide) ? 1 : 0);
            return;
        }
        if (snapTarget == this.mSplitLayout.getSnapAlgorithm().getMiddleTarget()) {
            MetricsLogger.action(this.mContext, 389, 0);
            return;
        }
        if (snapTarget == this.mSplitLayout.getSnapAlgorithm().getFirstSplitTarget()) {
            MetricsLogger.action(this.mContext, 389, dockSideTopLeft(this.mDockSide) ? 1 : 2);
        } else if (snapTarget == this.mSplitLayout.getSnapAlgorithm().getLastSplitTarget()) {
            MetricsLogger.action(this.mContext, 389, dockSideTopLeft(this.mDockSide) ? 2 : 1);
        }
    }

    private void convertToScreenCoordinates(MotionEvent event) {
        BydLogHelper.logD(TAG, "convertToScreenCoordinates event : " + event);
        event.setLocation(event.getRawX(), event.getRawY());
    }

    private void fling(int position, float velocity, boolean avoidDismissStart, boolean logMetrics) {
        BydLogHelper.logD(TAG, "fling position : " + position + " , velocity : " + velocity + " , avoidDismissStart : " + avoidDismissStart + " , logMetrics : " + logMetrics);
        DividerSnapAlgorithm snapAlgorithm = getSnapAlgorithm();
        DividerSnapAlgorithm.SnapTarget snapTargetCalculateSnapTarget = snapAlgorithm.calculateSnapTarget(position, velocity);
        if (avoidDismissStart && snapTargetCalculateSnapTarget == snapAlgorithm.getDismissStartTarget()) {
            snapTargetCalculateSnapTarget = snapAlgorithm.getFirstSplitTarget();
        }
        if (logMetrics) {
            logResizeEvent(snapTargetCalculateSnapTarget);
        }
        int dividerStatus = this.mDividerUtils.getDividerStatus();
        BydLogHelper.logD(TAG, "fling dividerStatus : " + dividerStatus);
        BydLogHelper.logD(TAG, "fling snapTarget.position : " + snapTargetCalculateSnapTarget.position);
        int[] windowWidthAndHeight = Util.getWindowWidthAndHeight(getContext());
        int navigationBarHeight = Util.getNavigationBarHeight(getContext());
        int statusBarHeight = Util.getStatusBarHeight(getContext());
        int i = windowWidthAndHeight[1];
        int i2 = windowWidthAndHeight[0];
        BydLogHelper.logD(TAG, "fling screenHeight : " + i + " , screenWidth : " + i2);
        BydLogHelper.logD(TAG, "fling navigationBarHeight : " + navigationBarHeight + " , statusBarHeight : " + statusBarHeight);
        BydLogHelper.logD(TAG, "fling isHorizontalDivision : " + this.mDividerUtils.isHorizontalDivision());
        if (this.mDividerUtils.isHorizontalDivision()) {
            if (snapTargetCalculateSnapTarget.position == i2) {
                if (dividerStatus == 2) {
                    this.mDivider.setGestureSlideExitOrientation(2);
                }
            } else if (snapTargetCalculateSnapTarget.position == -14 && dividerStatus == 2) {
                this.mDivider.setGestureSlideExitOrientation(1);
            }
        } else if (snapTargetCalculateSnapTarget.position == -14) {
            if (dividerStatus == 2) {
                this.mDivider.setGestureSlideExitOrientation(3);
            }
        } else if (snapTargetCalculateSnapTarget.position == i - navigationBarHeight && dividerStatus == 2) {
            this.mDivider.setGestureSlideExitOrientation(4);
        }
        ValueAnimator flingAnimator = getFlingAnimator(position, snapTargetCalculateSnapTarget, 0L);
        this.mFlingAnimationUtils.apply(flingAnimator, position, snapTargetCalculateSnapTarget.position, velocity);
        flingAnimator.start();
    }

    private void flingTo(int position, DividerSnapAlgorithm.SnapTarget target, long duration, long startDelay, long endDelay, Interpolator interpolator) {
        ValueAnimator flingAnimator = getFlingAnimator(position, target, endDelay);
        flingAnimator.setDuration(duration);
        flingAnimator.setStartDelay(startDelay);
        flingAnimator.setInterpolator(interpolator);
        flingAnimator.start();
    }

    private ValueAnimator getFlingAnimator(int position, final DividerSnapAlgorithm.SnapTarget snapTarget, final long endDelay) {
        if (this.mCurrentAnimator != null) {
            cancelFlingAnimation();
            updateDockSide();
        }
        final boolean z = snapTarget.flag == 0;
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(position, snapTarget.position);
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.stackdivider.DividerView.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                DividerView.this.resizeStackSurfaces(((Integer) animation.getAnimatedValue()).intValue(), (z && animation.getAnimatedFraction() == 1.0f) ? Integer.MAX_VALUE : snapTarget.taskPosition, snapTarget, null);
            }
        });
        valueAnimatorOfInt.addListener(new AnonymousClass3(endDelay, new Consumer() { // from class: com.android.systemui.stackdivider.-$$Lambda$DividerView$1fszFI0pBsLuZo-PiWnhUhHYMsc
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$getFlingAnimator$2$DividerView(snapTarget, (Boolean) obj);
            }
        }));
        valueAnimatorOfInt.setAnimationHandler(this.mAnimationHandler);
        this.mCurrentAnimator = valueAnimatorOfInt;
        return valueAnimatorOfInt;
    }

    public /* synthetic */ void lambda$getFlingAnimator$2$DividerView(DividerSnapAlgorithm.SnapTarget snapTarget, Boolean bool) {
        boolean z = this.mIsInMinimizeInteraction;
        if (!bool.booleanValue() && !this.mDockedStackMinimized && this.mIsInMinimizeInteraction) {
            this.mIsInMinimizeInteraction = false;
        }
        boolean zCommitSnapFlags = commitSnapFlags(snapTarget);
        this.mWindowManagerProxy.setResizing(false);
        updateDockSide();
        this.mCurrentAnimator = null;
        this.mEntranceAnimationRunning = false;
        this.mExitAnimationRunning = false;
        if (!zCommitSnapFlags && !z) {
            this.mWindowManagerProxy.applyResizeSplits(snapTarget.position, this.mSplitLayout);
        }
        DividerCallbacks dividerCallbacks = this.mCallback;
        if (dividerCallbacks != null) {
            dividerCallbacks.onDraggingEnd();
        }
        if (!this.mIsInMinimizeInteraction) {
            if (snapTarget.position < 0) {
                snapTarget = this.mSplitLayout.getSnapAlgorithm().getMiddleTarget();
            }
            DividerSnapAlgorithm snapAlgorithm = this.mSplitLayout.getSnapAlgorithm();
            if (snapTarget.position != snapAlgorithm.getDismissEndTarget().position && snapTarget.position != snapAlgorithm.getDismissStartTarget().position) {
                saveSnapTargetBeforeMinimized(snapTarget);
            }
        }
        notifySplitScreenBoundsChanged();
    }

    /* JADX INFO: renamed from: com.android.systemui.stackdivider.DividerView$3, reason: invalid class name */
    class AnonymousClass3 extends AnimatorListenerAdapter {
        private boolean mCancelled;
        final /* synthetic */ Consumer val$endAction;
        final /* synthetic */ long val$endDelay;

        AnonymousClass3(final long val$endDelay, final Consumer val$endAction) {
            this.val$endDelay = val$endDelay;
            this.val$endAction = val$endAction;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animation) {
            this.mCancelled = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animation) {
            long j = this.val$endDelay;
            if (j == 0) {
                boolean z = this.mCancelled;
                j = 0;
            }
            if (j == 0) {
                this.val$endAction.accept(Boolean.valueOf(this.mCancelled));
                return;
            }
            final Boolean boolValueOf = Boolean.valueOf(this.mCancelled);
            Handler handler = DividerView.this.mHandler;
            final Consumer consumer = this.val$endAction;
            handler.postDelayed(new Runnable() { // from class: com.android.systemui.stackdivider.-$$Lambda$DividerView$3$X59hbzs2j4KWvuPEtIqNNF78BHg
                @Override // java.lang.Runnable
                public final void run() {
                    consumer.accept(boolValueOf);
                }
            }, j);
        }
    }

    private void notifySplitScreenBoundsChanged() {
        BydLogHelper.logD(TAG, "notifySplitScreenBoundsChanged mSplitLayout.mPrimary : " + this.mSplitLayout.mPrimary + "\nmSplitLayout.mSecondary : " + this.mSplitLayout.mSecondary);
        if (this.mSplitLayout.mPrimary == null || this.mSplitLayout.mSecondary == null) {
            return;
        }
        this.mOtherTaskRect.set(this.mSplitLayout.mSecondary);
        BydLogHelper.logD(TAG, "notifySplitScreenBoundsChanged mHandle.getTop() : " + this.mHandle.getTop());
        this.mTmpRect.set(this.mHandle.getLeft(), this.mHandle.getTop(), this.mHandle.getRight(), this.mHandle.getBottom());
        if (isHorizontalDivision()) {
            this.mTmpRect.offsetTo(this.mHandle.getLeft(), this.mDividerPositionY);
        } else {
            this.mTmpRect.offsetTo(this.mDividerPositionX, this.mHandle.getTop());
        }
        this.mWindowManagerProxy.setTouchRegion(this.mTmpRect);
        this.mTmpRect.set(this.mSplitLayout.mDisplayLayout.stableInsets());
        int primarySplitSide = this.mSplitLayout.getPrimarySplitSide();
        if (primarySplitSide == 1) {
            this.mTmpRect.left = 0;
        } else if (primarySplitSide == 2) {
            this.mTmpRect.top = 0;
        } else if (primarySplitSide == 3) {
            this.mTmpRect.right = 0;
        }
        OverviewProxyService.mOverviewProxyService.notifySplitScreenBoundsChanged(this.mOtherTaskRect, this.mTmpRect);
    }

    private void cancelFlingAnimation() {
        BydLogHelper.logD(TAG, "cancelFlingAnimation mCurrentAnimator : " + this.mCurrentAnimator);
        ValueAnimator valueAnimator = this.mCurrentAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    private boolean commitSnapFlags(DividerSnapAlgorithm.SnapTarget target) {
        int i;
        int i2;
        BydLogHelper.logD(TAG, "commitSnapFlags target.flag : " + target.flag);
        if (target.flag == 0) {
            return false;
        }
        this.mWindowManagerProxy.dismissOrMaximizeDocked(this.mTiles, this.mSplitLayout, target.flag != 1 ? (i = this.mDockSide) == 3 || i == 4 : (i2 = this.mDockSide) == 1 || i2 == 2);
        SurfaceControl.Transaction transaction = this.mTiles.getTransaction();
        setResizeDimLayer(transaction, true, 0.0f);
        setResizeDimLayer(transaction, false, 0.0f);
        transaction.apply();
        this.mTiles.releaseTransaction(transaction);
        return true;
    }

    private void liftBackground() {
        BydLogHelper.logD(TAG, "liftBackground mBackgroundLifted : " + this.mBackgroundLifted);
        if (this.mBackgroundLifted) {
            return;
        }
        this.mBackgroundLifted = true;
    }

    private void releaseBackground() {
        BydLogHelper.logD(TAG, "releaseBackground mBackgroundLifted : " + this.mBackgroundLifted);
        if (this.mBackgroundLifted) {
            this.mBackgroundLifted = false;
        }
    }

    private void initializeSurfaceState() {
        Rect rect;
        BydLogHelper.logD(TAG, "initializeSurfaceState");
        int i = this.mSplitLayout.getSnapAlgorithm().getMiddleTarget().position;
        BydLogHelper.logD(TAG, "initializeSurfaceState midPos : " + i);
        this.mSplitLayout.resizeSplits(i);
        SurfaceControl.Transaction transaction = this.mTiles.getTransaction();
        BydLogHelper.logD(TAG, "initializeSurfaceState mDockedStackMinimized : " + this.mDockedStackMinimized);
        if (this.mDockedStackMinimized) {
            int i2 = this.mSplitLayout.getMinimizedSnapAlgorithm(this.mHomeStackResizable).getMiddleTarget().position;
            calculateBoundsForPosition(i2, this.mDockSide, this.mDockedRect);
            calculateBoundsForPosition(i2, DockedDividerUtils.invertDockSide(this.mDockSide), this.mOtherRect);
            this.mDividerPositionY = i2;
            this.mDividerPositionX = i2;
            BydLogHelper.logD(TAG, "resizeSplitSurfaces 111");
            resizeSplitSurfaces(transaction, this.mDockedRect, this.mSplitLayout.mPrimary, this.mOtherRect, this.mSplitLayout.mSecondary);
        } else {
            BydLogHelper.logD(TAG, "resizeSplitSurfaces 222");
            resizeSplitSurfaces(transaction, this.mSplitLayout.mPrimary, null, this.mSplitLayout.mSecondary, null);
        }
        setResizeDimLayer(transaction, true, 0.0f);
        setResizeDimLayer(transaction, false, 0.0f);
        transaction.apply();
        this.mTiles.releaseTransaction(transaction);
        if (isHorizontalDivision()) {
            rect = new Rect(0, this.mDividerInsets, this.mSplitLayout.mDisplayLayout.width(), this.mDividerInsets + this.mDividerSize);
        } else {
            int i3 = this.mDividerInsets;
            rect = new Rect(i3, 0, this.mDividerSize + i3, this.mSplitLayout.mDisplayLayout.height());
        }
        Region region = new Region(rect);
        region.union(new Rect(this.mHandle.getLeft(), this.mHandle.getTop(), this.mHandle.getRight(), this.mHandle.getBottom()));
        this.mWindowManager.setTouchRegion(region);
    }

    void setMinimizedDockStack(boolean minimized, boolean isHomeStackResizable, SurfaceControl.Transaction t) {
        BydLogHelper.logD(TAG, "setMinimizedDockStack minimized : " + minimized + "\nisHomeStackResizable : " + isHomeStackResizable + "\nt : " + t);
        this.mHomeStackResizable = isHomeStackResizable;
        updateDockSide();
        if (!minimized) {
            resetBackground();
        }
        if (this.mDockedStackMinimized != minimized) {
            this.mDockedStackMinimized = minimized;
            if (this.mSplitLayout.mDisplayLayout.rotation() != this.mDefaultDisplay.getRotation()) {
                repositionSnapTargetBeforeMinimized();
            }
            if (this.mIsInMinimizeInteraction == minimized && this.mCurrentAnimator == null) {
                return;
            }
            cancelFlingAnimation();
            if (minimized) {
                requestLayout();
                this.mIsInMinimizeInteraction = true;
                resizeStackSurfaces(this.mSplitLayout.getMinimizedSnapAlgorithm(this.mHomeStackResizable).getMiddleTarget(), t);
            } else {
                resizeStackSurfaces(this.mSnapTargetBeforeMinimized, t);
                this.mIsInMinimizeInteraction = false;
            }
        }
    }

    void enterSplitMode(boolean isHomeStackResizable) {
        BydLogHelper.logD(TAG, "enterSplitMode isHomeStackResizable : " + isHomeStackResizable);
        setHidden(false);
        DividerSnapAlgorithm.SnapTarget middleTarget = this.mSplitLayout.getMinimizedSnapAlgorithm(isHomeStackResizable).getMiddleTarget();
        if (this.mDockedStackMinimized) {
            int i = middleTarget.position;
            this.mDividerPositionX = i;
            this.mDividerPositionY = i;
        }
    }

    private SurfaceControl getWindowSurfaceControl() {
        BydLogHelper.logD(TAG, "getWindowSurfaceControl");
        ViewRootImpl viewRootImpl = getViewRootImpl();
        if (viewRootImpl == null) {
            return null;
        }
        SurfaceControl surfaceControl = viewRootImpl.getSurfaceControl();
        return (surfaceControl == null || !surfaceControl.isValid()) ? this.mWindowManager.mSystemWindows.getViewSurface(this) : surfaceControl;
    }

    void exitSplitMode() {
        BydLogHelper.logD(TAG, "exitSplitMode");
        SurfaceControl windowSurfaceControl = getWindowSurfaceControl();
        if (windowSurfaceControl == null) {
            return;
        }
        SurfaceControl.Transaction transaction = this.mTiles.getTransaction();
        transaction.hide(windowSurfaceControl);
        transaction.apply();
        this.mTiles.releaseTransaction(transaction);
        this.mWindowManagerProxy.applyResizeSplits(this.mSplitLayout.getSnapAlgorithm().getMiddleTarget().position, this.mSplitLayout);
    }

    private void saveSnapTargetBeforeMinimized(DividerSnapAlgorithm.SnapTarget target) {
        BydLogHelper.logD(TAG, "saveSnapTargetBeforeMinimized target : " + target.position);
        this.mSnapTargetBeforeMinimized = target;
        this.mState.mRatioPositionBeforeMinimized = target.position / (isHorizontalDivision() ? this.mSplitLayout.mDisplayLayout.height() : this.mSplitLayout.mDisplayLayout.width());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetBackground() {
        BydLogHelper.logD(TAG, "resetBackground");
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        BydLogHelper.logD(TAG, "onConfigurationChanged getWindowingMode : " + newConfig.windowConfiguration.getWindowingMode());
    }

    private void repositionSnapTargetBeforeMinimized() {
        this.mSnapTargetBeforeMinimized = this.mSplitLayout.getSnapAlgorithm().calculateNonDismissingSnapTarget((int) (this.mState.mRatioPositionBeforeMinimized * (isHorizontalDivision() ? this.mSplitLayout.mDisplayLayout.height() : this.mSplitLayout.mDisplayLayout.width())));
    }

    private int calculatePosition(int touchX, int touchY) {
        return isHorizontalDivision() ? calculateYPosition(touchY) : calculateXPosition(touchX);
    }

    public boolean isHorizontalDivision() {
        int i = getResources().getConfiguration().orientation;
        BydLogHelper.logD(TAG, "isHorizontalDivision orientation : " + i);
        return i != 2;
    }

    private int calculateXPosition(int touchX) {
        return (this.mStartPosition + touchX) - this.mStartX;
    }

    private int calculateYPosition(int touchY) {
        return (this.mStartPosition + touchY) - this.mStartY;
    }

    private void alignTopLeft(Rect containingRect, Rect rect) {
        BydLogHelper.logD(TAG, "alignTopLeft rect : " + rect);
        rect.set(containingRect.left, containingRect.top, containingRect.left + rect.width(), containingRect.top + rect.height());
    }

    private void alignBottomRight(Rect containingRect, Rect rect) {
        BydLogHelper.logD(TAG, "alignBottomRight rect : " + rect);
        rect.set(containingRect.right - rect.width(), containingRect.bottom - rect.height(), containingRect.right, containingRect.bottom);
    }

    public void calculateBoundsForPosition(int position, int dockSide, Rect outRect) {
        DockedDividerUtils.calculateBoundsForPosition(position, dockSide, outRect, this.mSplitLayout.mDisplayLayout.width(), this.mSplitLayout.mDisplayLayout.height(), this.mDividerSize);
    }

    private void resizeStackSurfaces(DividerSnapAlgorithm.SnapTarget taskSnapTarget, SurfaceControl.Transaction t) {
        BydLogHelper.logD(TAG, "resizeStackSurfaces 444");
        resizeStackSurfaces(taskSnapTarget.position, taskSnapTarget.position, taskSnapTarget, t);
    }

    private void resizeSplitSurfaces(SurfaceControl.Transaction t, Rect dockedRect, Rect dockedTaskRect, Rect otherRect, Rect otherTaskRect) {
        BydLogHelper.logD(TAG, "resizeSplitSurfaces");
        if (dockedTaskRect == null) {
            dockedTaskRect = dockedRect;
        }
        if (otherTaskRect == null) {
            otherTaskRect = otherRect;
        }
        this.mDividerPositionX = this.mSplitLayout.getPrimarySplitSide() == 3 ? otherRect.right : dockedRect.right;
        this.mDividerPositionY = dockedRect.bottom;
        BydLogHelper.logD(TAG, "Resizing split surfaces dockedRect : " + dockedRect + "\ndockedTaskRect : " + dockedTaskRect + "\notherRect : " + otherRect + "\notherTaskRect : " + otherTaskRect);
        t.setPosition(this.mTiles.mPrimarySurface, dockedTaskRect.left, dockedTaskRect.top);
        Rect rect = new Rect(dockedRect);
        rect.offsetTo(-Math.min(dockedTaskRect.left - dockedRect.left, 0), -Math.min(dockedTaskRect.top - dockedRect.top, 0));
        t.setWindowCrop(this.mTiles.mPrimarySurface, rect);
        t.setPosition(this.mTiles.mSecondarySurface, otherTaskRect.left, otherTaskRect.top);
        rect.set(otherRect);
        rect.offsetTo(-(otherTaskRect.left - otherRect.left), -(otherTaskRect.top - otherRect.top));
        t.setWindowCrop(this.mTiles.mSecondarySurface, rect);
        SurfaceControl windowSurfaceControl = getWindowSurfaceControl();
        if (windowSurfaceControl != null) {
            if (isHorizontalDivision()) {
                t.setPosition(windowSurfaceControl, 0.0f, this.mDividerPositionY - this.mDividerInsets);
            } else {
                t.setPosition(windowSurfaceControl, this.mDividerPositionX - this.mDividerInsets, 0.0f);
            }
        }
        if (getViewRootImpl() != null) {
            this.mHandler.removeCallbacks(this.mUpdateEmbeddedMatrix);
            this.mHandler.post(this.mUpdateEmbeddedMatrix);
        }
    }

    void setResizeDimLayer(SurfaceControl.Transaction t, boolean primary, float alpha) {
        BydLogHelper.logD(TAG, "setResizeDimLayer primary : " + primary + " , alpha : " + alpha);
        SurfaceControl surfaceControl = primary ? this.mTiles.mPrimaryDim : this.mTiles.mSecondaryDim;
        if (alpha <= 0.001f) {
            t.hide(surfaceControl);
        } else {
            t.setAlpha(surfaceControl, alpha);
            t.show(surfaceControl);
        }
    }

    void resizeStackSurfaces(int position, int taskPosition, DividerSnapAlgorithm.SnapTarget taskSnapTarget, SurfaceControl.Transaction transaction) {
        BydLogHelper.logD(TAG, "resizeStackSurfaces mRemoved : " + this.mRemoved);
        if (this.mRemoved) {
            return;
        }
        calculateBoundsForPosition(position, this.mDockSide, this.mDockedRect);
        calculateBoundsForPosition(position, DockedDividerUtils.invertDockSide(this.mDockSide), this.mOtherRect);
        if (!this.mDockedRect.equals(this.mLastResizeRect) || this.mEntranceAnimationRunning) {
            boolean z = transaction == null;
            SurfaceControl.Transaction transaction2 = z ? this.mTiles.getTransaction() : transaction;
            this.mLastResizeRect.set(this.mDockedRect);
            if (this.mIsInMinimizeInteraction) {
                calculateBoundsForPosition(this.mSnapTargetBeforeMinimized.position, this.mDockSide, this.mDockedTaskRect);
                calculateBoundsForPosition(this.mSnapTargetBeforeMinimized.position, DockedDividerUtils.invertDockSide(this.mDockSide), this.mOtherTaskRect);
                if (this.mDockSide == 3) {
                    this.mDockedTaskRect.offset((Math.max(position, -this.mDividerSize) - this.mDockedTaskRect.left) + this.mDividerSize, 0);
                }
                BydLogHelper.logD(TAG, "resizeStackSurfaces 444");
                resizeSplitSurfaces(transaction2, this.mDockedRect, this.mDockedTaskRect, this.mOtherRect, this.mOtherTaskRect);
                if (z) {
                    transaction2.apply();
                    this.mTiles.releaseTransaction(transaction2);
                    return;
                }
                return;
            }
            if (this.mEntranceAnimationRunning && taskPosition != Integer.MAX_VALUE) {
                calculateBoundsForPosition(taskPosition, this.mDockSide, this.mDockedTaskRect);
                if (this.mDockSide == 3) {
                    this.mDockedTaskRect.offset((Math.max(position, -this.mDividerSize) - this.mDockedTaskRect.left) + this.mDividerSize, 0);
                }
                calculateBoundsForPosition(taskPosition, DockedDividerUtils.invertDockSide(this.mDockSide), this.mOtherTaskRect);
                resizeSplitSurfaces(transaction2, this.mDockedRect, this.mDockedTaskRect, this.mOtherRect, this.mOtherTaskRect);
            } else if (this.mExitAnimationRunning && taskPosition != Integer.MAX_VALUE) {
                calculateBoundsForPosition(position, DockedDividerUtils.invertDockSide(this.mDockSide), this.mOtherRect);
                int iInvertDockSide = DockedDividerUtils.invertDockSide(this.mDockSide);
                int iRestrictDismissingTaskPosition = restrictDismissingTaskPosition(taskPosition, this.mDockSide, taskSnapTarget);
                int iRestrictDismissingTaskPosition2 = restrictDismissingTaskPosition(taskPosition, iInvertDockSide, taskSnapTarget);
                calculateBoundsForPosition(iRestrictDismissingTaskPosition, this.mDockSide, this.mDockedTaskRect);
                calculateBoundsForPosition(iRestrictDismissingTaskPosition2, iInvertDockSide, this.mOtherTaskRect);
                this.mTmpRect.set(0, 0, this.mSplitLayout.mDisplayLayout.width(), this.mSplitLayout.mDisplayLayout.height());
                alignTopLeft(this.mDockedRect, this.mDockedTaskRect);
                alignTopLeft(this.mOtherRect, this.mOtherTaskRect);
                this.mDockedInsetRect.set(this.mDockedTaskRect);
                this.mOtherInsetRect.set(this.mOtherTaskRect);
                if (dockSideTopLeft(this.mDockSide)) {
                    alignTopLeft(this.mTmpRect, this.mDockedInsetRect);
                    alignBottomRight(this.mTmpRect, this.mOtherInsetRect);
                } else {
                    alignBottomRight(this.mTmpRect, this.mDockedInsetRect);
                    alignTopLeft(this.mTmpRect, this.mOtherInsetRect);
                }
                applyDismissingParallax(this.mDockedTaskRect, this.mDockSide, taskSnapTarget, position, iRestrictDismissingTaskPosition);
                applyDismissingParallax(this.mOtherTaskRect, iInvertDockSide, taskSnapTarget, position, iRestrictDismissingTaskPosition2);
                BydLogHelper.logD(TAG, "resizeStackSurfaces 666");
                resizeSplitSurfaces(transaction2, this.mDockedRect, this.mDockedTaskRect, this.mOtherRect, this.mOtherTaskRect);
            } else if (taskPosition != Integer.MAX_VALUE) {
                calculateBoundsForPosition(position, DockedDividerUtils.invertDockSide(this.mDockSide), this.mOtherRect);
                int iInvertDockSide2 = DockedDividerUtils.invertDockSide(this.mDockSide);
                int iRestrictDismissingTaskPosition3 = restrictDismissingTaskPosition(taskPosition, this.mDockSide, taskSnapTarget);
                int iRestrictDismissingTaskPosition4 = restrictDismissingTaskPosition(taskPosition, iInvertDockSide2, taskSnapTarget);
                calculateBoundsForPosition(iRestrictDismissingTaskPosition3, this.mDockSide, this.mDockedTaskRect);
                calculateBoundsForPosition(iRestrictDismissingTaskPosition4, iInvertDockSide2, this.mOtherTaskRect);
                this.mTmpRect.set(0, 0, this.mSplitLayout.mDisplayLayout.width(), this.mSplitLayout.mDisplayLayout.height());
                alignTopLeft(this.mDockedRect, this.mDockedTaskRect);
                alignTopLeft(this.mOtherRect, this.mOtherTaskRect);
                this.mDockedInsetRect.set(this.mDockedTaskRect);
                this.mOtherInsetRect.set(this.mOtherTaskRect);
                if (dockSideTopLeft(this.mDockSide)) {
                    alignTopLeft(this.mTmpRect, this.mDockedInsetRect);
                    alignBottomRight(this.mTmpRect, this.mOtherInsetRect);
                } else {
                    alignBottomRight(this.mTmpRect, this.mDockedInsetRect);
                    alignTopLeft(this.mTmpRect, this.mOtherInsetRect);
                }
                applyDismissingParallax(this.mDockedTaskRect, this.mDockSide, taskSnapTarget, position, iRestrictDismissingTaskPosition3);
                applyDismissingParallax(this.mOtherTaskRect, iInvertDockSide2, taskSnapTarget, position, iRestrictDismissingTaskPosition4);
                BydLogHelper.logD(TAG, "resizeStackSurfaces 777");
                resizeSplitSurfaces(transaction2, this.mDockedRect, this.mDockedTaskRect, this.mOtherRect, this.mOtherTaskRect);
            } else {
                BydLogHelper.logD(TAG, "resizeStackSurfaces 888");
                resizeSplitSurfaces(transaction2, this.mDockedRect, null, this.mOtherRect, null);
            }
            boolean zIsHorizontalDivision = isHorizontalDivision();
            BydLogHelper.logD("resizeStackSurfaces position : " + position + " , isHorizontalDivision : " + zIsHorizontalDivision);
            if (zIsHorizontalDivision) {
                if (position == 950) {
                    setResizeDimLayer(transaction2, false, 0.0f);
                    setResizeDimLayer(transaction2, true, 0.0f);
                }
            } else if (position == 953) {
                setResizeDimLayer(transaction2, false, 0.0f);
                setResizeDimLayer(transaction2, true, 0.0f);
            }
            DividerSnapAlgorithm.SnapTarget closestDismissTarget = getSnapAlgorithm().getClosestDismissTarget(position);
            setResizeDimLayer(transaction2, isDismissTargetPrimary(closestDismissTarget), getDimFraction(position, closestDismissTarget));
            if (z) {
                transaction2.apply();
                this.mTiles.releaseTransaction(transaction2);
            }
        }
    }

    private float getDimFraction(int position, DividerSnapAlgorithm.SnapTarget dismissTarget) {
        BydLogHelper.logD(TAG, "getDimFraction dismissTarget : " + dismissTarget + "\nposition : " + position);
        if (this.mEntranceAnimationRunning) {
            return 0.0f;
        }
        return DIM_INTERPOLATOR.getInterpolation(Math.max(0.0f, Math.min(getSnapAlgorithm().calculateDismissingFraction(position), 1.0f)));
    }

    private int restrictDismissingTaskPosition(int taskPosition, int dockSide, DividerSnapAlgorithm.SnapTarget snapTarget) {
        BydLogHelper.logD(TAG, "restrictDismissingTaskPosition snapTarget : " + snapTarget + "\ndockSide : " + dockSide + "\ntaskPosition : " + taskPosition);
        if (snapTarget.flag == 1 && dockSideTopLeft(dockSide)) {
            return Math.max(this.mSplitLayout.getSnapAlgorithm().getFirstSplitTarget().position, this.mStartPosition);
        }
        return (snapTarget.flag == 2 && dockSideBottomRight(dockSide)) ? Math.min(this.mSplitLayout.getSnapAlgorithm().getLastSplitTarget().position, this.mStartPosition) : taskPosition;
    }

    private void applyDismissingParallax(Rect taskRect, int dockSide, DividerSnapAlgorithm.SnapTarget snapTarget, int position, int taskPosition) {
        DividerSnapAlgorithm.SnapTarget firstSplitTarget;
        BydLogHelper.logD(TAG, "applyDismissingParallax taskRect : " + taskRect + "\ndockSide : " + dockSide + "\nposition : " + position + "\ntaskPosition : " + taskPosition);
        float fMin = Math.min(1.0f, Math.max(0.0f, this.mSplitLayout.getSnapAlgorithm().calculateDismissingFraction(position)));
        DividerSnapAlgorithm.SnapTarget dismissEndTarget = null;
        if (position <= this.mSplitLayout.getSnapAlgorithm().getLastSplitTarget().position && dockSideTopLeft(dockSide)) {
            dismissEndTarget = this.mSplitLayout.getSnapAlgorithm().getDismissStartTarget();
            firstSplitTarget = this.mSplitLayout.getSnapAlgorithm().getFirstSplitTarget();
        } else if (position < this.mSplitLayout.getSnapAlgorithm().getLastSplitTarget().position || !dockSideBottomRight(dockSide)) {
            taskPosition = 0;
            firstSplitTarget = null;
        } else {
            dismissEndTarget = this.mSplitLayout.getSnapAlgorithm().getDismissEndTarget();
            DividerSnapAlgorithm.SnapTarget lastSplitTarget = this.mSplitLayout.getSnapAlgorithm().getLastSplitTarget();
            firstSplitTarget = lastSplitTarget;
            taskPosition = lastSplitTarget.position;
        }
        if (dismissEndTarget == null || fMin <= 0.0f || !isDismissing(firstSplitTarget, position, dockSide)) {
            return;
        }
        int iCalculateParallaxDismissingFraction = (int) (taskPosition + (calculateParallaxDismissingFraction(fMin, dockSide) * (dismissEndTarget.position - firstSplitTarget.position)));
        int iWidth = taskRect.width();
        int iHeight = taskRect.height();
        if (dockSide == 1) {
            taskRect.left = iCalculateParallaxDismissingFraction - iWidth;
            taskRect.right = iCalculateParallaxDismissingFraction;
            return;
        }
        if (dockSide == 2) {
            taskRect.top = iCalculateParallaxDismissingFraction - iHeight;
            taskRect.bottom = iCalculateParallaxDismissingFraction;
        } else if (dockSide == 3) {
            taskRect.left = this.mDividerSize + iCalculateParallaxDismissingFraction;
            taskRect.right = iCalculateParallaxDismissingFraction + iWidth + this.mDividerSize;
        } else {
            if (dockSide != 4) {
                return;
            }
            taskRect.top = this.mDividerSize + iCalculateParallaxDismissingFraction;
            taskRect.bottom = iCalculateParallaxDismissingFraction + iHeight + this.mDividerSize;
        }
    }

    private static float calculateParallaxDismissingFraction(float fraction, int dockSide) {
        float interpolation = SLOWDOWN_INTERPOLATOR.getInterpolation(fraction) / 3.5f;
        return dockSide == 2 ? interpolation / 2.0f : interpolation;
    }

    private static boolean isDismissing(DividerSnapAlgorithm.SnapTarget snapTarget, int position, int dockSide) {
        return (dockSide == 2 || dockSide == 1) ? position < snapTarget.position : position > snapTarget.position;
    }

    private boolean isDismissTargetPrimary(DividerSnapAlgorithm.SnapTarget dismissTarget) {
        if (dismissTarget.flag == 1 && dockSideTopLeft(this.mDockSide)) {
            return true;
        }
        return dismissTarget.flag == 2 && dockSideBottomRight(this.mDockSide);
    }

    public void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo inoutInfo) {
        BydLogHelper.logD(TAG, "onComputeInternalInsets inoutInfo : " + inoutInfo);
        inoutInfo.setTouchableInsets(3);
        inoutInfo.touchableRegion.set(this.mHandle.getLeft(), this.mHandle.getTop(), this.mHandle.getRight(), this.mHandle.getBottom());
    }

    void onDockedFirstAnimationFrame() {
        BydLogHelper.logD("onDockedFirstAnimationFrame");
        saveSnapTargetBeforeMinimized(this.mSplitLayout.getSnapAlgorithm().getMiddleTarget());
    }

    void onDockedTopTask() {
        this.mState.growAfterRecentsDrawn = false;
        this.mState.animateAfterRecentsDrawn = true;
        startDragging(false, false);
        updateDockSide();
        this.mEntranceAnimationRunning = true;
        BydLogHelper.logD(TAG, "resizeStackSurfaces 666");
        resizeStackSurfaces(calculatePositionForInsetBounds(), this.mSplitLayout.getSnapAlgorithm().getMiddleTarget().position, this.mSplitLayout.getSnapAlgorithm().getMiddleTarget(), null);
    }

    void onUndockingTask(int direction) {
        BydLogHelper.logD(TAG, "onUndockingTask direction : " + direction);
        if (inSplitMode()) {
            DividerSnapAlgorithm.SnapTarget dismissStartTarget = null;
            if (direction == 1) {
                dismissStartTarget = this.mSplitLayout.getSnapAlgorithm().getDismissEndTarget();
            } else if (direction == 2) {
                dismissStartTarget = this.mSplitLayout.getSnapAlgorithm().getDismissStartTarget();
            }
            DividerSnapAlgorithm.SnapTarget snapTarget = dismissStartTarget;
            if (snapTarget == null) {
                return;
            }
            startDragging(false, false);
            this.mExitAnimationRunning = true;
            this.mExitStartPosition = getCurrentPosition();
            BydLogHelper.logD(TAG, "onUndockingTask target : " + snapTarget.position);
            stopDragging(this.mExitStartPosition, snapTarget, 20L, 0L, 0L, Interpolators.FAST_OUT_SLOW_IN);
        }
    }

    private int calculatePositionForInsetBounds() {
        this.mSplitLayout.mDisplayLayout.getStableBounds(this.mTmpRect);
        return DockedDividerUtils.calculatePositionForBounds(this.mTmpRect, this.mDockSide, this.mDividerSize);
    }

    public void updateSplitBtnShowHide() {
        this.mDividerMoreButton.updateSplitBtnShowHide();
    }

    public void updateExitOrientation() {
        this.mDividerMoreButton.updateExitOrientation();
    }
}
