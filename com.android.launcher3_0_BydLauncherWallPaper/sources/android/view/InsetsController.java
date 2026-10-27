package android.view;

import android.animation.AnimationHandler;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.IBinder;
import android.os.Trace;
import android.util.ArraySet;
import android.util.Pair;
import android.util.SparseArray;
import android.view.InsetsController;
import android.view.SurfaceControl;
import android.view.SyncRtSurfaceTransactionApplier;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.WindowInsetsController;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.view.inputmethod.InputMethodManager;
import com.android.internal.graphics.SfVsyncFrameCallbackProvider;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
public class InsetsController implements WindowInsetsController, InsetsAnimationControlCallbacks {
    private static final int ANIMATION_DURATION_HIDE_MS = 340;
    private static final int ANIMATION_DURATION_SHOW_MS = 275;
    private static final int ANIMATION_DURATION_SYNC_IME_MS = 285;
    private static final int ANIMATION_DURATION_UNSYNC_IME_MS = 200;
    public static final int ANIMATION_TYPE_HIDE = 1;
    public static final int ANIMATION_TYPE_NONE = -1;
    public static final int ANIMATION_TYPE_SHOW = 0;
    public static final int ANIMATION_TYPE_USER = 2;
    static final boolean DEBUG = false;
    public static final int LAYOUT_INSETS_DURING_ANIMATION_HIDDEN = 1;
    public static final int LAYOUT_INSETS_DURING_ANIMATION_SHOWN = 0;
    private static final int PENDING_CONTROL_TIMEOUT_MS = 2000;
    private static final String TAG = "InsetsController";
    static final boolean WARN = false;
    private final Runnable mAnimCallback;
    private boolean mAnimCallbackScheduled;
    private boolean mAnimationsDisabled;
    private int mCaptionInsetsHeight;
    private final BiFunction<InsetsController, Integer, InsetsSourceConsumer> mConsumerCreator;
    private final ArrayList<WindowInsetsController.OnControllableInsetsChangedListener> mControllableInsetsChangedListeners;
    private int mDisabledUserAnimationInsetsTypes;
    private final Rect mFrame;
    private final Handler mHandler;
    private final Host mHost;
    private Runnable mInvokeControllableInsetsChangedListeners;
    private final InsetsState mLastDispatchedState;
    private DisplayCutout mLastDisplayCutout;
    private WindowInsets mLastInsets;
    private int mLastLegacySoftInputMode;
    private int mLastLegacySystemUiFlags;
    private int mLastLegacyWindowFlags;
    private int mLastStartedAnimTypes;
    private Runnable mPendingControlTimeout;
    private PendingControlRequest mPendingImeControlRequest;
    private final InsetsState mRequestedState;
    private final ArrayList<RunningAnimation> mRunningAnimations;
    private final SparseArray<InsetsSourceConsumer> mSourceConsumers;
    private boolean mStartingAnimation;
    private final InsetsState mState;
    private final SparseArray<InsetsSourceControl> mTmpControlArray;
    private final ArrayList<InsetsAnimationControlImpl> mTmpFinishedControls;
    private final ArrayList<WindowInsetsAnimation> mTmpRunningAnims;
    private int mTypesBeingCancelled;
    private final List<WindowInsetsAnimation> mUnmodifiableTmpRunningAnims;
    public static final Interpolator SYSTEM_BARS_INTERPOLATOR = new PathInterpolator(0.4f, 0.0f, 0.2f, 1.0f);
    private static final Interpolator SYNC_IME_INTERPOLATOR = new PathInterpolator(0.2f, 0.0f, 0.0f, 1.0f);
    private static final Interpolator LINEAR_OUT_SLOW_IN_INTERPOLATOR = new PathInterpolator(0.0f, 0.0f, 0.2f, 1.0f);
    private static final Interpolator FAST_OUT_LINEAR_IN_INTERPOLATOR = new PathInterpolator(0.4f, 0.0f, 1.0f, 1.0f);
    private static TypeEvaluator<Insets> sEvaluator = new TypeEvaluator() { // from class: android.view.-$$Lambda$InsetsController$gQpwnpbNeWh8bng_RJJ0-xELjO8
        @Override // android.animation.TypeEvaluator
        public final Object evaluate(float f, Object obj, Object obj2) {
            Insets insets = (Insets) obj;
            Insets insets2 = (Insets) obj2;
            return Insets.of((int) (insets.left + ((insets2.left - insets.left) * f)), (int) (insets.top + ((insets2.top - insets.top) * f)), (int) (insets.right + ((insets2.right - insets.right) * f)), (int) (insets.bottom + (f * (insets2.bottom - insets.bottom))));
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    @interface AnimationType {
    }

    public interface Host {
        void addOnPreDrawRunnable(Runnable runnable);

        void applySurfaceParams(SyncRtSurfaceTransactionApplier.SurfaceParams... surfaceParamsArr);

        int dipToPx(int i);

        void dispatchWindowInsetsAnimationEnd(WindowInsetsAnimation windowInsetsAnimation);

        void dispatchWindowInsetsAnimationPrepare(WindowInsetsAnimation windowInsetsAnimation);

        WindowInsets dispatchWindowInsetsAnimationProgress(WindowInsets windowInsets, List<WindowInsetsAnimation> list);

        WindowInsetsAnimation.Bounds dispatchWindowInsetsAnimationStart(WindowInsetsAnimation windowInsetsAnimation, WindowInsetsAnimation.Bounds bounds);

        Handler getHandler();

        InputMethodManager getInputMethodManager();

        String getRootViewTitle();

        int getSystemBarsAppearance();

        int getSystemBarsBehavior();

        IBinder getWindowToken();

        boolean hasAnimationCallbacks();

        void notifyInsetsChanged();

        void onInsetsModified(InsetsState insetsState);

        void postInsetsAnimationCallback(Runnable runnable);

        void releaseSurfaceControlFromRt(SurfaceControl surfaceControl);

        void setSystemBarsAppearance(int i, int i2);

        void setSystemBarsBehavior(int i);

        void updateCompatSysUiVisibility(int i, boolean z, boolean z2);
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface LayoutInsetsDuringAnimation {
    }

    public static class InternalAnimationControlListener implements WindowInsetsAnimationControlListener {
        protected static final int FLOATING_IME_BOTTOM_INSET = -80;
        private ValueAnimator mAnimator;
        private WindowInsetsAnimationController mController;
        private final boolean mDisable;
        private final int mFloatingImeBottomInset;
        private final boolean mHasAnimationCallbacks;
        private final int mRequestedTypes;
        private final boolean mShow;
        private ThreadLocal<AnimationHandler> mSfAnimationHandlerThreadLocal = new ThreadLocal<AnimationHandler>() { // from class: android.view.InsetsController.InternalAnimationControlListener.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.lang.ThreadLocal
            public AnimationHandler initialValue() {
                AnimationHandler animationHandler = new AnimationHandler();
                animationHandler.setProvider(new SfVsyncFrameCallbackProvider());
                return animationHandler;
            }
        };
        private final long mDurationMs = calculateDurationMs();

        static /* synthetic */ float lambda$getAlphaInterpolator$1(float f) {
            return 1.0f;
        }

        static /* synthetic */ float lambda$getAlphaInterpolator$3(float f) {
            return 1.0f;
        }

        @Override // android.view.WindowInsetsAnimationControlListener
        public void onFinished(WindowInsetsAnimationController windowInsetsAnimationController) {
        }

        public InternalAnimationControlListener(boolean z, boolean z2, int i, boolean z3, int i2) {
            this.mShow = z;
            this.mHasAnimationCallbacks = z2;
            this.mRequestedTypes = i;
            this.mDisable = z3;
            this.mFloatingImeBottomInset = i2;
        }

        @Override // android.view.WindowInsetsAnimationControlListener
        public void onReady(final WindowInsetsAnimationController windowInsetsAnimationController, int i) {
            this.mController = windowInsetsAnimationController;
            if (this.mDisable) {
                onAnimationFinish();
                return;
            }
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.mAnimator = valueAnimatorOfFloat;
            valueAnimatorOfFloat.setDuration(this.mDurationMs);
            this.mAnimator.setInterpolator(new LinearInterpolator());
            Insets hiddenStateInsets = windowInsetsAnimationController.getHiddenStateInsets();
            if (windowInsetsAnimationController.hasZeroInsetsIme()) {
                hiddenStateInsets = Insets.of(hiddenStateInsets.left, hiddenStateInsets.top, hiddenStateInsets.right, this.mFloatingImeBottomInset);
            }
            final Insets shownStateInsets = this.mShow ? hiddenStateInsets : windowInsetsAnimationController.getShownStateInsets();
            if (this.mShow) {
                hiddenStateInsets = windowInsetsAnimationController.getShownStateInsets();
            }
            final Insets insets = hiddenStateInsets;
            final Interpolator interpolator = getInterpolator();
            final Interpolator alphaInterpolator = getAlphaInterpolator();
            this.mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: android.view.-$$Lambda$InsetsController$InternalAnimationControlListener$bKruiaAMbuvF0a4_SD_7QYgJtWE
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.f$0.lambda$onReady$0$InsetsController$InternalAnimationControlListener(interpolator, windowInsetsAnimationController, shownStateInsets, insets, alphaInterpolator, valueAnimator);
                }
            });
            this.mAnimator.addListener(new AnimatorListenerAdapter() { // from class: android.view.InsetsController.InternalAnimationControlListener.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    InternalAnimationControlListener.this.onAnimationFinish();
                }
            });
            if (!this.mHasAnimationCallbacks) {
                this.mAnimator.setAnimationHandler(this.mSfAnimationHandlerThreadLocal.get());
            }
            this.mAnimator.start();
        }

        public /* synthetic */ void lambda$onReady$0$InsetsController$InternalAnimationControlListener(Interpolator interpolator, WindowInsetsAnimationController windowInsetsAnimationController, Insets insets, Insets insets2, Interpolator interpolator2, ValueAnimator valueAnimator) {
            float animatedFraction = valueAnimator.getAnimatedFraction();
            windowInsetsAnimationController.setInsetsAndAlpha((Insets) InsetsController.sEvaluator.evaluate(interpolator.getInterpolation(animatedFraction), insets, insets2), interpolator2.getInterpolation(this.mShow ? animatedFraction : 1.0f - animatedFraction), animatedFraction);
        }

        @Override // android.view.WindowInsetsAnimationControlListener
        public void onCancelled(WindowInsetsAnimationController windowInsetsAnimationController) {
            ValueAnimator valueAnimator = this.mAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
        }

        Interpolator getInterpolator() {
            if ((this.mRequestedTypes & WindowInsets.Type.ime()) != 0) {
                if (this.mHasAnimationCallbacks) {
                    return InsetsController.SYNC_IME_INTERPOLATOR;
                }
                return this.mShow ? InsetsController.LINEAR_OUT_SLOW_IN_INTERPOLATOR : InsetsController.FAST_OUT_LINEAR_IN_INTERPOLATOR;
            }
            return InsetsController.SYSTEM_BARS_INTERPOLATOR;
        }

        Interpolator getAlphaInterpolator() {
            if ((this.mRequestedTypes & WindowInsets.Type.ime()) != 0) {
                if (this.mHasAnimationCallbacks) {
                    return new Interpolator() { // from class: android.view.-$$Lambda$InsetsController$InternalAnimationControlListener$ALOvF92s1zkmX7KsgDz4ivQGpgY
                        @Override // android.animation.TimeInterpolator
                        public final float getInterpolation(float f) {
                            return InsetsController.InternalAnimationControlListener.lambda$getAlphaInterpolator$1(f);
                        }
                    };
                }
                if (!this.mShow) {
                    return InsetsController.FAST_OUT_LINEAR_IN_INTERPOLATOR;
                }
                return new Interpolator() { // from class: android.view.-$$Lambda$InsetsController$InternalAnimationControlListener$6rpKSB7EM4J2ybeh_5Rqfor6JNE
                    @Override // android.animation.TimeInterpolator
                    public final float getInterpolation(float f) {
                        return Math.min(1.0f, f * 2.0f);
                    }
                };
            }
            return new Interpolator() { // from class: android.view.-$$Lambda$InsetsController$InternalAnimationControlListener$3Vo5-k4-rtpsQhnW4tAy2g3k8Rs
                @Override // android.animation.TimeInterpolator
                public final float getInterpolation(float f) {
                    return InsetsController.InternalAnimationControlListener.lambda$getAlphaInterpolator$3(f);
                }
            };
        }

        protected void onAnimationFinish() {
            this.mController.finish(this.mShow);
        }

        public long getDurationMs() {
            return this.mDurationMs;
        }

        private long calculateDurationMs() {
            return (this.mRequestedTypes & WindowInsets.Type.ime()) != 0 ? this.mHasAnimationCallbacks ? 285L : 200L : this.mShow ? 275L : 340L;
        }
    }

    private static class RunningAnimation {
        final InsetsAnimationControlRunner runner;
        boolean startDispatched;
        final int type;

        RunningAnimation(InsetsAnimationControlRunner insetsAnimationControlRunner, int i) {
            this.runner = insetsAnimationControlRunner;
            this.type = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class PendingControlRequest {
        final int animationType;
        final CancellationSignal cancellationSignal;
        final long durationMs;
        final Interpolator interpolator;
        final int layoutInsetsDuringAnimation;
        final WindowInsetsAnimationControlListener listener;
        final int types;
        final boolean useInsetsAnimationThread;

        PendingControlRequest(int i, WindowInsetsAnimationControlListener windowInsetsAnimationControlListener, long j, Interpolator interpolator, int i2, int i3, CancellationSignal cancellationSignal, boolean z) {
            this.types = i;
            this.listener = windowInsetsAnimationControlListener;
            this.durationMs = j;
            this.interpolator = interpolator;
            this.animationType = i2;
            this.layoutInsetsDuringAnimation = i3;
            this.cancellationSignal = cancellationSignal;
            this.useInsetsAnimationThread = z;
        }
    }

    public InsetsController(Host host) {
        this(host, new BiFunction() { // from class: android.view.-$$Lambda$InsetsController$LvCBbImH6DQWA648Yq_JZtmCRe8
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return InsetsController.lambda$new$1((InsetsController) obj, (Integer) obj2);
            }
        }, host.getHandler());
    }

    static /* synthetic */ InsetsSourceConsumer lambda$new$1(InsetsController insetsController, Integer num) {
        if (num.intValue() == 17) {
            return new ImeInsetsSourceConsumer(insetsController.mState, new Supplier() { // from class: android.view.-$$Lambda$kYkFpS7OGAAOrVusdqwxwzBS7mY
                @Override // java.util.function.Supplier
                public final Object get() {
                    return new SurfaceControl.Transaction();
                }
            }, insetsController);
        }
        return new InsetsSourceConsumer(num.intValue(), insetsController.mState, new Supplier() { // from class: android.view.-$$Lambda$kYkFpS7OGAAOrVusdqwxwzBS7mY
            @Override // java.util.function.Supplier
            public final Object get() {
                return new SurfaceControl.Transaction();
            }
        }, insetsController);
    }

    public InsetsController(Host host, BiFunction<InsetsController, Integer, InsetsSourceConsumer> biFunction, Handler handler) {
        this.mState = new InsetsState();
        this.mLastDispatchedState = new InsetsState();
        this.mRequestedState = new InsetsState();
        this.mFrame = new Rect();
        this.mSourceConsumers = new SparseArray<>();
        this.mTmpControlArray = new SparseArray<>();
        this.mRunningAnimations = new ArrayList<>();
        ArrayList<WindowInsetsAnimation> arrayList = new ArrayList<>();
        this.mTmpRunningAnims = arrayList;
        this.mUnmodifiableTmpRunningAnims = Collections.unmodifiableList(arrayList);
        this.mTmpFinishedControls = new ArrayList<>();
        this.mCaptionInsetsHeight = 0;
        this.mPendingControlTimeout = new Runnable() { // from class: android.view.-$$Lambda$InsetsController$PRU-rByC-T6Cyol8QANPZ-P3LX8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.abortPendingImeControlRequest();
            }
        };
        this.mControllableInsetsChangedListeners = new ArrayList<>();
        this.mInvokeControllableInsetsChangedListeners = new Runnable() { // from class: android.view.-$$Lambda$InsetsController$ePJ4opFbfn8Q38r5Oo7RWmAEQ48
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.invokeControllableInsetsChangedListeners();
            }
        };
        this.mHost = host;
        this.mConsumerCreator = biFunction;
        this.mHandler = handler;
        this.mAnimCallback = new Runnable() { // from class: android.view.-$$Lambda$InsetsController$tE6Yg0Ym91d8arsYT4-ND9x8Y7E
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$2$InsetsController();
            }
        };
    }

    public /* synthetic */ void lambda$new$2$InsetsController() {
        this.mAnimCallbackScheduled = false;
        if (this.mRunningAnimations.isEmpty()) {
            return;
        }
        this.mTmpFinishedControls.clear();
        this.mTmpRunningAnims.clear();
        InsetsState insetsState = new InsetsState(this.mState, true);
        for (int size = this.mRunningAnimations.size() - 1; size >= 0; size--) {
            RunningAnimation runningAnimation = this.mRunningAnimations.get(size);
            InsetsAnimationControlRunner insetsAnimationControlRunner = runningAnimation.runner;
            if (insetsAnimationControlRunner instanceof InsetsAnimationControlImpl) {
                InsetsAnimationControlImpl insetsAnimationControlImpl = (InsetsAnimationControlImpl) insetsAnimationControlRunner;
                if (runningAnimation.startDispatched) {
                    this.mTmpRunningAnims.add(insetsAnimationControlImpl.getAnimation());
                }
                if (insetsAnimationControlImpl.applyChangeInsets(insetsState)) {
                    this.mTmpFinishedControls.add(insetsAnimationControlImpl);
                }
            }
        }
        this.mHost.dispatchWindowInsetsAnimationProgress(insetsState.calculateInsets(this.mFrame, this.mState, this.mLastInsets.isRound(), this.mLastInsets.shouldAlwaysConsumeSystemBars(), this.mLastDisplayCutout, this.mLastLegacySoftInputMode, this.mLastLegacyWindowFlags, this.mLastLegacySystemUiFlags, null), this.mUnmodifiableTmpRunningAnims);
        for (int size2 = this.mTmpFinishedControls.size() - 1; size2 >= 0; size2--) {
            dispatchAnimationEnd(this.mTmpFinishedControls.get(size2).getAnimation());
        }
    }

    public void onFrameChanged(Rect rect) {
        if (this.mFrame.equals(rect)) {
            return;
        }
        this.mHost.notifyInsetsChanged();
        this.mFrame.set(rect);
    }

    @Override // android.view.WindowInsetsController
    public InsetsState getState() {
        return this.mState;
    }

    @Override // android.view.WindowInsetsController
    public boolean isRequestedVisible(int i) {
        return getSourceConsumer(i).isRequestedVisible();
    }

    public InsetsState getLastDispatchedState() {
        return this.mLastDispatchedState;
    }

    public boolean onStateChanged(InsetsState insetsState) {
        if (!((this.mState.equals(insetsState, true, false) && captionInsetsUnchanged()) ? false : true) && this.mLastDispatchedState.equals(insetsState)) {
            return false;
        }
        this.mLastDispatchedState.set(insetsState, true);
        InsetsState insetsState2 = new InsetsState(this.mState, true);
        updateState(insetsState);
        applyLocalVisibilityOverride();
        if (!this.mState.equals(insetsState2, true, true)) {
            this.mHost.notifyInsetsChanged();
        }
        if (!this.mState.equals(this.mLastDispatchedState, true, true)) {
            updateRequestedState();
        }
        return true;
    }

    private void updateState(InsetsState insetsState) {
        this.mState.setDisplayFrame(insetsState.getDisplayFrame());
        final int[] iArr = {0};
        int i = 0;
        for (int i2 = 0; i2 < 20; i2++) {
            InsetsSource insetsSourcePeekSource = insetsState.peekSource(i2);
            if (insetsSourcePeekSource != null) {
                int animationType = getAnimationType(i2);
                if (!insetsSourcePeekSource.isUserControllable()) {
                    int publicType = InsetsState.toPublicType(i2);
                    i |= publicType;
                    if (animationType == 2) {
                        animationType = -1;
                        iArr[0] = iArr[0] | publicType;
                    }
                }
                getSourceConsumer(i2).updateSource(insetsSourcePeekSource, animationType);
            }
        }
        for (int i3 = 0; i3 < 20; i3++) {
            if (this.mState.peekSource(i3) != null && insetsState.peekSource(i3) == null) {
                this.mState.removeSource(i3);
            }
        }
        if (this.mCaptionInsetsHeight != 0) {
            this.mState.getSource(2).setFrame(new Rect(this.mFrame.left, this.mFrame.top, this.mFrame.right, this.mFrame.top + this.mCaptionInsetsHeight));
        }
        updateDisabledUserAnimationTypes(i);
        if (iArr[0] != 0) {
            this.mHandler.post(new Runnable() { // from class: android.view.-$$Lambda$InsetsController$-JAc4vLPL-V2Y9AhiGZ6TWoP4WA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$updateState$3$InsetsController(iArr);
                }
            });
        }
    }

    public /* synthetic */ void lambda$updateState$3$InsetsController(int[] iArr) {
        show(iArr[0]);
    }

    private void updateDisabledUserAnimationTypes(int i) {
        int i2 = this.mDisabledUserAnimationInsetsTypes ^ i;
        if (i2 != 0) {
            int size = this.mSourceConsumers.size() - 1;
            while (true) {
                if (size < 0) {
                    break;
                }
                InsetsSourceConsumer insetsSourceConsumerValueAt = this.mSourceConsumers.valueAt(size);
                if (insetsSourceConsumerValueAt.getControl() != null && (InsetsState.toPublicType(insetsSourceConsumerValueAt.getType()) & i2) != 0) {
                    this.mHandler.removeCallbacks(this.mInvokeControllableInsetsChangedListeners);
                    this.mHandler.post(this.mInvokeControllableInsetsChangedListeners);
                    break;
                }
                size--;
            }
            this.mDisabledUserAnimationInsetsTypes = i;
        }
    }

    private boolean captionInsetsUnchanged() {
        if (this.mState.peekSource(2) == null && this.mCaptionInsetsHeight == 0) {
            return true;
        }
        return this.mState.peekSource(2) != null && this.mCaptionInsetsHeight == this.mState.peekSource(2).getFrame().height();
    }

    public WindowInsets calculateInsets(boolean z, boolean z2, DisplayCutout displayCutout, int i, int i2, int i3) {
        this.mLastLegacySoftInputMode = i;
        this.mLastLegacyWindowFlags = i2;
        this.mLastLegacySystemUiFlags = i3;
        this.mLastDisplayCutout = displayCutout;
        WindowInsets windowInsetsCalculateInsets = this.mState.calculateInsets(this.mFrame, null, z, z2, displayCutout, i, i2, i3, null);
        this.mLastInsets = windowInsetsCalculateInsets;
        return windowInsetsCalculateInsets;
    }

    public Rect calculateVisibleInsets(int i) {
        return this.mState.calculateVisibleInsets(this.mFrame, i);
    }

    public void onControlsChanged(InsetsSourceControl[] insetsSourceControlArr) {
        if (insetsSourceControlArr != null) {
            for (InsetsSourceControl insetsSourceControl : insetsSourceControlArr) {
                if (insetsSourceControl != null) {
                    this.mTmpControlArray.put(insetsSourceControl.getType(), insetsSourceControl);
                }
            }
        }
        int[] iArr = new int[1];
        int[] iArr2 = new int[1];
        for (int size = this.mSourceConsumers.size() - 1; size >= 0; size--) {
            InsetsSourceConsumer insetsSourceConsumerValueAt = this.mSourceConsumers.valueAt(size);
            insetsSourceConsumerValueAt.setControl(this.mTmpControlArray.get(insetsSourceConsumerValueAt.getType()), iArr, iArr2);
        }
        boolean z = false;
        for (int size2 = this.mTmpControlArray.size() - 1; size2 >= 0; size2--) {
            InsetsSourceControl insetsSourceControlValueAt = this.mTmpControlArray.valueAt(size2);
            int type = insetsSourceControlValueAt.getType();
            InsetsSourceConsumer sourceConsumer = getSourceConsumer(type);
            sourceConsumer.setControl(insetsSourceControlValueAt, iArr, iArr2);
            if (!z) {
                boolean zIsRequestedVisible = sourceConsumer.isRequestedVisible();
                z = (zIsRequestedVisible != this.mRequestedState.getSourceOrDefaultVisibility(type)) || (type == 17 && zIsRequestedVisible);
            }
        }
        this.mTmpControlArray.clear();
        int iInvokeControllableInsetsChangedListeners = invokeControllableInsetsChangedListeners();
        int i = iArr[0];
        int i2 = ~iInvokeControllableInsetsChangedListeners;
        iArr[0] = i & i2;
        iArr2[0] = i2 & iArr2[0];
        if (iArr[0] != 0) {
            applyAnimation(iArr[0], true, false);
        }
        if (iArr2[0] != 0) {
            applyAnimation(iArr2[0], false, false);
        }
        if (z) {
            updateRequestedState();
        }
    }

    @Override // android.view.WindowInsetsController
    public void show(int i) {
        show(i, false);
    }

    public void show(int i, boolean z) {
        PendingControlRequest pendingControlRequest;
        if (z && (pendingControlRequest = this.mPendingImeControlRequest) != null) {
            this.mPendingImeControlRequest = null;
            this.mHandler.removeCallbacks(this.mPendingControlTimeout);
            controlAnimationUnchecked(pendingControlRequest.types, pendingControlRequest.cancellationSignal, pendingControlRequest.listener, this.mFrame, true, pendingControlRequest.durationMs, pendingControlRequest.interpolator, pendingControlRequest.animationType, pendingControlRequest.layoutInsetsDuringAnimation, pendingControlRequest.useInsetsAnimationThread);
            return;
        }
        int publicType = 0;
        ArraySet<Integer> internalType = InsetsState.toInternalType(i);
        for (int size = internalType.size() - 1; size >= 0; size--) {
            int iIntValue = internalType.valueAt(size).intValue();
            int animationType = getAnimationType(iIntValue);
            InsetsSourceConsumer sourceConsumer = getSourceConsumer(iIntValue);
            if ((!sourceConsumer.isRequestedVisible() || animationType != -1) && animationType != 0 && (!z || animationType != 2)) {
                publicType |= InsetsState.toPublicType(sourceConsumer.getType());
            }
        }
        applyAnimation(publicType, true, z);
    }

    @Override // android.view.WindowInsetsController
    public void hide(int i) {
        hide(i, false);
    }

    void hide(int i, boolean z) {
        ArraySet<Integer> internalType = InsetsState.toInternalType(i);
        int publicType = 0;
        for (int size = internalType.size() - 1; size >= 0; size--) {
            int iIntValue = internalType.valueAt(size).intValue();
            int animationType = getAnimationType(iIntValue);
            InsetsSourceConsumer sourceConsumer = getSourceConsumer(iIntValue);
            if ((sourceConsumer.isRequestedVisible() || animationType != -1) && animationType != 1) {
                publicType |= InsetsState.toPublicType(sourceConsumer.getType());
            }
        }
        applyAnimation(publicType, false, z);
    }

    @Override // android.view.WindowInsetsController
    public void controlWindowInsetsAnimation(int i, long j, Interpolator interpolator, CancellationSignal cancellationSignal, WindowInsetsAnimationControlListener windowInsetsAnimationControlListener) {
        controlWindowInsetsAnimation(i, cancellationSignal, windowInsetsAnimationControlListener, false, j, interpolator, 2);
    }

    private void controlWindowInsetsAnimation(int i, CancellationSignal cancellationSignal, WindowInsetsAnimationControlListener windowInsetsAnimationControlListener, boolean z, long j, Interpolator interpolator, int i2) {
        if ((this.mState.calculateUncontrollableInsetsFromFrame(this.mFrame) & i) != 0) {
            windowInsetsAnimationControlListener.onCancelled(null);
        } else {
            controlAnimationUnchecked(i, cancellationSignal, windowInsetsAnimationControlListener, this.mFrame, z, j, interpolator, i2, getLayoutInsetsDuringAnimationMode(i), false);
        }
    }

    private void controlAnimationUnchecked(int i, CancellationSignal cancellationSignal, WindowInsetsAnimationControlListener windowInsetsAnimationControlListener, Rect rect, boolean z, long j, Interpolator interpolator, int i2, int i3, boolean z2) {
        int i4;
        final InsetsAnimationControlRunner insetsAnimationControlImpl;
        if ((i & this.mTypesBeingCancelled) != 0) {
            throw new IllegalStateException("Cannot start a new insets animation of " + WindowInsets.Type.toString(i) + " while an existing " + WindowInsets.Type.toString(this.mTypesBeingCancelled) + " is being cancelled.");
        }
        if (i2 == 2) {
            int i5 = this.mDisabledUserAnimationInsetsTypes;
            int i6 = i & i5;
            int i7 = i & (~i5);
            if (z && (i6 & WindowInsets.Type.ime()) != 0 && !this.mState.getSource(17).isVisible()) {
                getSourceConsumer(17).hide(true, i2);
            }
            i4 = i7;
        } else {
            i4 = i;
        }
        if (i4 == 0) {
            windowInsetsAnimationControlListener.onCancelled(null);
            return;
        }
        cancelExistingControllers(i4);
        this.mLastStartedAnimTypes |= i4;
        ArraySet<Integer> internalType = InsetsState.toInternalType(i4);
        SparseArray<InsetsSourceControl> sparseArray = new SparseArray<>();
        Pair<Integer, Boolean> pairCollectSourceControls = collectSourceControls(z, internalType, sparseArray, i2);
        int iIntValue = pairCollectSourceControls.first.intValue();
        if (!pairCollectSourceControls.second.booleanValue()) {
            abortPendingImeControlRequest();
            final PendingControlRequest pendingControlRequest = new PendingControlRequest(i4, windowInsetsAnimationControlListener, j, interpolator, i2, i3, cancellationSignal, z2);
            this.mPendingImeControlRequest = pendingControlRequest;
            this.mHandler.postDelayed(this.mPendingControlTimeout, 2000L);
            if (cancellationSignal != null) {
                cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: android.view.-$$Lambda$InsetsController$rqx4q3E7acbyAUsGdEitPH37Ykw
                    @Override // android.os.CancellationSignal.OnCancelListener
                    public final void onCancel() {
                        this.f$0.lambda$controlAnimationUnchecked$4$InsetsController(pendingControlRequest);
                    }
                });
                return;
            }
            return;
        }
        if (iIntValue == 0) {
            windowInsetsAnimationControlListener.onCancelled(null);
            return;
        }
        if (z2) {
            insetsAnimationControlImpl = new InsetsAnimationThreadControlRunner(sparseArray, rect, this.mState, windowInsetsAnimationControlListener, iIntValue, this, j, interpolator, i2, this.mHost.getHandler());
        } else {
            insetsAnimationControlImpl = new InsetsAnimationControlImpl(sparseArray, rect, this.mState, windowInsetsAnimationControlListener, iIntValue, this, j, interpolator, i2);
        }
        this.mRunningAnimations.add(new RunningAnimation(insetsAnimationControlImpl, i2));
        if (cancellationSignal != null) {
            cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: android.view.-$$Lambda$InsetsController$hfI8-qXjGpNJX3L5MWnCJr7YrNU
                @Override // android.os.CancellationSignal.OnCancelListener
                public final void onCancel() {
                    this.f$0.lambda$controlAnimationUnchecked$5$InsetsController(insetsAnimationControlImpl);
                }
            });
        }
        if (i3 == 0) {
            showDirectly(i4);
        } else {
            hideDirectly(i4, false, i2);
        }
    }

    public /* synthetic */ void lambda$controlAnimationUnchecked$4$InsetsController(PendingControlRequest pendingControlRequest) {
        if (this.mPendingImeControlRequest == pendingControlRequest) {
            abortPendingImeControlRequest();
        }
    }

    public /* synthetic */ void lambda$controlAnimationUnchecked$5$InsetsController(InsetsAnimationControlRunner insetsAnimationControlRunner) {
        cancelAnimation(insetsAnimationControlRunner, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0061 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.util.Pair<java.lang.Integer, java.lang.Boolean> collectSourceControls(boolean r10, android.util.ArraySet<java.lang.Integer> r11, android.util.SparseArray<android.view.InsetsSourceControl> r12, int r13) {
        /*
            r9 = this;
            int r0 = r11.size()
            r1 = 1
            int r0 = r0 - r1
            r2 = 0
            r4 = r1
            r3 = r2
        L9:
            if (r0 < 0) goto L64
            java.lang.Object r5 = r11.valueAt(r0)
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            android.view.InsetsSourceConsumer r5 = r9.getSourceConsumer(r5)
            if (r13 == 0) goto L21
            r6 = 2
            if (r13 != r6) goto L1f
            goto L21
        L1f:
            r6 = r2
            goto L22
        L21:
            r6 = r1
        L22:
            if (r6 == 0) goto L31
            int r6 = r5.requestShow(r10)
            if (r6 == 0) goto L36
            if (r6 == r1) goto L2e
            r6 = r2
            goto L37
        L2e:
            r4 = r2
            r6 = r4
            goto L37
        L31:
            if (r10 != 0) goto L36
            r5.notifyHidden()
        L36:
            r6 = r1
        L37:
            if (r6 != 0) goto L3a
            goto L61
        L3a:
            android.view.InsetsSourceControl r6 = r5.getControl()
            if (r6 == 0) goto L56
            int r7 = r5.getType()
            android.view.InsetsSourceControl r8 = new android.view.InsetsSourceControl
            r8.<init>(r6)
            r12.put(r7, r8)
            int r5 = r5.getType()
            int r5 = android.view.InsetsState.toPublicType(r5)
            r3 = r3 | r5
            goto L61
        L56:
            if (r13 != 0) goto L5c
            r5.show(r10)
            goto L61
        L5c:
            if (r13 != r1) goto L61
            r5.hide()
        L61:
            int r0 = r0 + (-1)
            goto L9
        L64:
            android.util.Pair r9 = new android.util.Pair
            java.lang.Integer r10 = java.lang.Integer.valueOf(r3)
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r4)
            r9.<init>(r10, r11)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.InsetsController.collectSourceControls(boolean, android.util.ArraySet, android.util.SparseArray, int):android.util.Pair");
    }

    private int getLayoutInsetsDuringAnimationMode(int i) {
        ArraySet<Integer> internalType = InsetsState.toInternalType(i);
        for (int size = internalType.size() - 1; size >= 0; size--) {
            InsetsSourceConsumer insetsSourceConsumer = this.mSourceConsumers.get(internalType.valueAt(size).intValue());
            if (insetsSourceConsumer != null && !insetsSourceConsumer.isRequestedVisible()) {
                return 0;
            }
        }
        return 1;
    }

    private void cancelExistingControllers(int i) {
        int i2 = this.mTypesBeingCancelled;
        this.mTypesBeingCancelled = i2 | i;
        try {
            for (int size = this.mRunningAnimations.size() - 1; size >= 0; size--) {
                InsetsAnimationControlRunner insetsAnimationControlRunner = this.mRunningAnimations.get(size).runner;
                if ((insetsAnimationControlRunner.getTypes() & i) != 0) {
                    cancelAnimation(insetsAnimationControlRunner, true);
                }
            }
            if ((i & WindowInsets.Type.ime()) != 0) {
                abortPendingImeControlRequest();
            }
        } finally {
            this.mTypesBeingCancelled = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void abortPendingImeControlRequest() {
        PendingControlRequest pendingControlRequest = this.mPendingImeControlRequest;
        if (pendingControlRequest != null) {
            pendingControlRequest.listener.onCancelled(null);
            this.mPendingImeControlRequest = null;
            this.mHandler.removeCallbacks(this.mPendingControlTimeout);
        }
    }

    @Override // android.view.InsetsAnimationControlCallbacks
    public void notifyFinished(InsetsAnimationControlRunner insetsAnimationControlRunner, boolean z) {
        cancelAnimation(insetsAnimationControlRunner, false);
        if (z) {
            showDirectly(insetsAnimationControlRunner.getTypes());
        } else {
            hideDirectly(insetsAnimationControlRunner.getTypes(), true, insetsAnimationControlRunner.getAnimationType());
        }
    }

    @Override // android.view.InsetsAnimationControlCallbacks
    public void applySurfaceParams(SyncRtSurfaceTransactionApplier.SurfaceParams... surfaceParamsArr) {
        this.mHost.applySurfaceParams(surfaceParamsArr);
    }

    void notifyControlRevoked(InsetsSourceConsumer insetsSourceConsumer) {
        for (int size = this.mRunningAnimations.size() - 1; size >= 0; size--) {
            InsetsAnimationControlRunner insetsAnimationControlRunner = this.mRunningAnimations.get(size).runner;
            if ((insetsAnimationControlRunner.getTypes() & InsetsState.toPublicType(insetsSourceConsumer.getType())) != 0) {
                cancelAnimation(insetsAnimationControlRunner, true);
            }
        }
        if (insetsSourceConsumer.getType() == 17) {
            abortPendingImeControlRequest();
        }
    }

    private void cancelAnimation(InsetsAnimationControlRunner insetsAnimationControlRunner, boolean z) {
        if (z) {
            insetsAnimationControlRunner.cancel();
        }
        boolean zNotifyAnimationFinished = false;
        int size = this.mRunningAnimations.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            }
            RunningAnimation runningAnimation = this.mRunningAnimations.get(size);
            if (runningAnimation.runner == insetsAnimationControlRunner) {
                this.mRunningAnimations.remove(size);
                ArraySet<Integer> internalType = InsetsState.toInternalType(insetsAnimationControlRunner.getTypes());
                for (int size2 = internalType.size() - 1; size2 >= 0; size2--) {
                    zNotifyAnimationFinished |= getSourceConsumer(internalType.valueAt(size2).intValue()).notifyAnimationFinished();
                }
                if (z && runningAnimation.startDispatched) {
                    dispatchAnimationEnd(runningAnimation.runner.getAnimation());
                }
            } else {
                size--;
            }
        }
        if (zNotifyAnimationFinished) {
            this.mHost.notifyInsetsChanged();
            updateRequestedState();
        }
    }

    private void applyLocalVisibilityOverride() {
        for (int size = this.mSourceConsumers.size() - 1; size >= 0; size--) {
            this.mSourceConsumers.valueAt(size).applyLocalVisibilityOverride();
        }
    }

    public InsetsSourceConsumer getSourceConsumer(int i) {
        InsetsSourceConsumer insetsSourceConsumer = this.mSourceConsumers.get(i);
        if (insetsSourceConsumer != null) {
            return insetsSourceConsumer;
        }
        InsetsSourceConsumer insetsSourceConsumerApply = this.mConsumerCreator.apply(this, Integer.valueOf(i));
        this.mSourceConsumers.put(i, insetsSourceConsumerApply);
        return insetsSourceConsumerApply;
    }

    public void notifyVisibilityChanged() {
        this.mHost.notifyInsetsChanged();
        updateRequestedState();
    }

    public void updateCompatSysUiVisibility(int i, boolean z, boolean z2) {
        this.mHost.updateCompatSysUiVisibility(i, z, z2);
    }

    public void onWindowFocusGained() {
        getSourceConsumer(17).onWindowFocusGained();
    }

    public void onWindowFocusLost() {
        getSourceConsumer(17).onWindowFocusLost();
    }

    public void applyImeVisibility(boolean z) {
        if (z) {
            show(8, true);
        } else {
            hide(8);
        }
    }

    public int getAnimationType(int i) {
        for (int size = this.mRunningAnimations.size() - 1; size >= 0; size--) {
            if (this.mRunningAnimations.get(size).runner.controlsInternalType(i)) {
                return this.mRunningAnimations.get(size).type;
            }
        }
        return -1;
    }

    private void updateRequestedState() {
        boolean z = false;
        for (int size = this.mSourceConsumers.size() - 1; size >= 0; size--) {
            InsetsSourceConsumer insetsSourceConsumerValueAt = this.mSourceConsumers.valueAt(size);
            int type = insetsSourceConsumerValueAt.getType();
            if (type != 2 && insetsSourceConsumerValueAt.getControl() != null) {
                InsetsSource source = this.mState.getSource(type);
                if (!source.equals(this.mRequestedState.peekSource(type))) {
                    this.mRequestedState.addSource(new InsetsSource(source));
                    z = true;
                }
                if (!source.equals(this.mLastDispatchedState.peekSource(type))) {
                    z = true;
                }
            }
        }
        if (z) {
            this.mHost.onInsetsModified(this.mRequestedState);
        }
    }

    public void applyAnimation(int i, boolean z, boolean z2) {
        if (i == 0) {
            return;
        }
        boolean zHasAnimationCallbacks = this.mHost.hasAnimationCallbacks();
        InternalAnimationControlListener internalAnimationControlListener = new InternalAnimationControlListener(z, zHasAnimationCallbacks, i, this.mAnimationsDisabled, this.mHost.dipToPx(-80));
        controlAnimationUnchecked(i, null, internalAnimationControlListener, this.mState.getDisplayFrame(), z2, internalAnimationControlListener.getDurationMs(), internalAnimationControlListener.getInterpolator(), !z ? 1 : 0, !z ? 1 : 0, !zHasAnimationCallbacks);
    }

    private void hideDirectly(int i, boolean z, int i2) {
        ArraySet<Integer> internalType = InsetsState.toInternalType(i);
        for (int size = internalType.size() - 1; size >= 0; size--) {
            getSourceConsumer(internalType.valueAt(size).intValue()).hide(z, i2);
        }
    }

    private void showDirectly(int i) {
        ArraySet<Integer> internalType = InsetsState.toInternalType(i);
        for (int size = internalType.size() - 1; size >= 0; size--) {
            getSourceConsumer(internalType.valueAt(size).intValue()).show(false);
        }
    }

    public void cancelExistingAnimations() {
        cancelExistingControllers(WindowInsets.Type.all());
    }

    void dump(String str, PrintWriter printWriter) {
        printWriter.println(str);
        printWriter.println("InsetsController:");
        this.mState.dump(str + "  ", printWriter);
    }

    @Override // android.view.InsetsAnimationControlCallbacks
    public void startAnimation(final InsetsAnimationControlImpl insetsAnimationControlImpl, final WindowInsetsAnimationControlListener windowInsetsAnimationControlListener, final int i, final WindowInsetsAnimation windowInsetsAnimation, final WindowInsetsAnimation.Bounds bounds) {
        this.mHost.dispatchWindowInsetsAnimationPrepare(windowInsetsAnimation);
        this.mHost.addOnPreDrawRunnable(new Runnable() { // from class: android.view.-$$Lambda$InsetsController$y0mb3h0YfRYryI_Yhm9CpakGasA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$startAnimation$6$InsetsController(insetsAnimationControlImpl, i, windowInsetsAnimation, bounds, windowInsetsAnimationControlListener);
            }
        });
    }

    public /* synthetic */ void lambda$startAnimation$6$InsetsController(InsetsAnimationControlImpl insetsAnimationControlImpl, int i, WindowInsetsAnimation windowInsetsAnimation, WindowInsetsAnimation.Bounds bounds, WindowInsetsAnimationControlListener windowInsetsAnimationControlListener) {
        if (insetsAnimationControlImpl.isCancelled()) {
            return;
        }
        Trace.asyncTraceBegin(8L, "InsetsAnimation: " + WindowInsets.Type.toString(i), i);
        for (int size = this.mRunningAnimations.size() - 1; size >= 0; size--) {
            RunningAnimation runningAnimation = this.mRunningAnimations.get(size);
            if (runningAnimation.runner == insetsAnimationControlImpl) {
                runningAnimation.startDispatched = true;
            }
        }
        this.mHost.dispatchWindowInsetsAnimationStart(windowInsetsAnimation, bounds);
        this.mStartingAnimation = true;
        insetsAnimationControlImpl.mReadyDispatched = true;
        windowInsetsAnimationControlListener.onReady(insetsAnimationControlImpl, i);
        this.mStartingAnimation = false;
    }

    public void dispatchAnimationEnd(WindowInsetsAnimation windowInsetsAnimation) {
        Trace.asyncTraceEnd(8L, "InsetsAnimation: " + WindowInsets.Type.toString(windowInsetsAnimation.getTypeMask()), windowInsetsAnimation.getTypeMask());
        this.mHost.dispatchWindowInsetsAnimationEnd(windowInsetsAnimation);
    }

    @Override // android.view.InsetsAnimationControlCallbacks
    public void scheduleApplyChangeInsets(InsetsAnimationControlRunner insetsAnimationControlRunner) {
        if (this.mStartingAnimation || insetsAnimationControlRunner.getAnimationType() == 2) {
            this.mAnimCallback.run();
            this.mAnimCallbackScheduled = false;
        } else {
            if (this.mAnimCallbackScheduled) {
                return;
            }
            this.mHost.postInsetsAnimationCallback(this.mAnimCallback);
            this.mAnimCallbackScheduled = true;
        }
    }

    @Override // android.view.WindowInsetsController
    public void setSystemBarsAppearance(int i, int i2) {
        this.mHost.setSystemBarsAppearance(i, i2);
    }

    @Override // android.view.WindowInsetsController
    public int getSystemBarsAppearance() {
        return this.mHost.getSystemBarsAppearance();
    }

    @Override // android.view.WindowInsetsController
    public void setCaptionInsetsHeight(int i) {
        this.mCaptionInsetsHeight = i;
    }

    @Override // android.view.WindowInsetsController
    public void setSystemBarsBehavior(int i) {
        this.mHost.setSystemBarsBehavior(i);
    }

    @Override // android.view.WindowInsetsController
    public int getSystemBarsBehavior() {
        return this.mHost.getSystemBarsBehavior();
    }

    @Override // android.view.WindowInsetsController
    public void setAnimationsDisabled(boolean z) {
        this.mAnimationsDisabled = z;
    }

    private int calculateControllableTypes() {
        int publicType = 0;
        for (int size = this.mSourceConsumers.size() - 1; size >= 0; size--) {
            InsetsSourceConsumer insetsSourceConsumerValueAt = this.mSourceConsumers.valueAt(size);
            InsetsSource insetsSourcePeekSource = this.mState.peekSource(insetsSourceConsumerValueAt.mType);
            if (insetsSourceConsumerValueAt.getControl() != null && insetsSourcePeekSource != null && insetsSourcePeekSource.isUserControllable()) {
                publicType |= InsetsState.toPublicType(insetsSourceConsumerValueAt.mType);
            }
        }
        return (~this.mState.calculateUncontrollableInsetsFromFrame(this.mFrame)) & publicType;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int invokeControllableInsetsChangedListeners() {
        this.mHandler.removeCallbacks(this.mInvokeControllableInsetsChangedListeners);
        this.mLastStartedAnimTypes = 0;
        int iCalculateControllableTypes = calculateControllableTypes();
        int size = this.mControllableInsetsChangedListeners.size();
        for (int i = 0; i < size; i++) {
            this.mControllableInsetsChangedListeners.get(i).onControllableInsetsChanged(this, iCalculateControllableTypes);
        }
        return this.mLastStartedAnimTypes;
    }

    @Override // android.view.WindowInsetsController
    public void addOnControllableInsetsChangedListener(WindowInsetsController.OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        Objects.requireNonNull(onControllableInsetsChangedListener);
        this.mControllableInsetsChangedListeners.add(onControllableInsetsChangedListener);
        onControllableInsetsChangedListener.onControllableInsetsChanged(this, calculateControllableTypes());
    }

    @Override // android.view.WindowInsetsController
    public void removeOnControllableInsetsChangedListener(WindowInsetsController.OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        Objects.requireNonNull(onControllableInsetsChangedListener);
        this.mControllableInsetsChangedListeners.remove(onControllableInsetsChangedListener);
    }

    @Override // android.view.InsetsAnimationControlCallbacks
    public void releaseSurfaceControlFromRt(SurfaceControl surfaceControl) {
        this.mHost.releaseSurfaceControlFromRt(surfaceControl);
    }

    @Override // android.view.InsetsAnimationControlCallbacks
    public void reportPerceptible(int i, boolean z) {
        ArraySet<Integer> internalType = InsetsState.toInternalType(i);
        int size = this.mSourceConsumers.size();
        for (int i2 = 0; i2 < size; i2++) {
            InsetsSourceConsumer insetsSourceConsumerValueAt = this.mSourceConsumers.valueAt(i2);
            if (internalType.contains(Integer.valueOf(insetsSourceConsumerValueAt.getType()))) {
                insetsSourceConsumerValueAt.onPerceptible(z);
            }
        }
    }

    Host getHost() {
        return this.mHost;
    }
}
