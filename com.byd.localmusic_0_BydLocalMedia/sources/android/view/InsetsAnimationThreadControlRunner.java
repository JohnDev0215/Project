package android.view;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Trace;
import android.util.SparseArray;
import android.view.SurfaceControl;
import android.view.SyncRtSurfaceTransactionApplier;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.animation.Interpolator;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class InsetsAnimationThreadControlRunner implements InsetsAnimationControlRunner {
    private static final String TAG = "InsetsAnimThreadRunner";
    private final InsetsAnimationControlImpl mControl;
    private final Handler mMainThreadHandler;
    private final InsetsAnimationControlCallbacks mOuterCallbacks;
    private final InsetsState mState = new InsetsState();
    private final InsetsAnimationControlCallbacks mCallbacks = new AnonymousClass1();

    /* JADX INFO: renamed from: android.view.InsetsAnimationThreadControlRunner$1, reason: invalid class name */
    class AnonymousClass1 implements InsetsAnimationControlCallbacks {
        private final float[] mTmpFloat9 = new float[9];

        @Override // android.view.InsetsAnimationControlCallbacks
        public void startAnimation(InsetsAnimationControlImpl insetsAnimationControlImpl, WindowInsetsAnimationControlListener windowInsetsAnimationControlListener, int i, WindowInsetsAnimation windowInsetsAnimation, WindowInsetsAnimation.Bounds bounds) {
        }

        AnonymousClass1() {
        }

        @Override // android.view.InsetsAnimationControlCallbacks
        public void scheduleApplyChangeInsets(InsetsAnimationControlRunner insetsAnimationControlRunner) {
            InsetsAnimationThreadControlRunner.this.mControl.applyChangeInsets(InsetsAnimationThreadControlRunner.this.mState);
        }

        @Override // android.view.InsetsAnimationControlCallbacks
        public void notifyFinished(InsetsAnimationControlRunner insetsAnimationControlRunner, final boolean z) {
            Trace.asyncTraceEnd(8L, "InsetsAsyncAnimation: " + WindowInsets.Type.toString(insetsAnimationControlRunner.getTypes()), insetsAnimationControlRunner.getTypes());
            InsetsAnimationThreadControlRunner insetsAnimationThreadControlRunner = InsetsAnimationThreadControlRunner.this;
            insetsAnimationThreadControlRunner.releaseControls(insetsAnimationThreadControlRunner.mControl.getControls());
            InsetsAnimationThreadControlRunner.this.mMainThreadHandler.post(new Runnable() { // from class: android.view.-$$Lambda$InsetsAnimationThreadControlRunner$1$HYaS-j4hzpYaXxnEg1yPA7mlZPo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$notifyFinished$0$InsetsAnimationThreadControlRunner$1(z);
                }
            });
        }

        public /* synthetic */ void lambda$notifyFinished$0$InsetsAnimationThreadControlRunner$1(boolean z) {
            InsetsAnimationThreadControlRunner.this.mOuterCallbacks.notifyFinished(InsetsAnimationThreadControlRunner.this, z);
        }

        @Override // android.view.InsetsAnimationControlCallbacks
        public void applySurfaceParams(SyncRtSurfaceTransactionApplier.SurfaceParams... surfaceParamsArr) {
            SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
            for (int length = surfaceParamsArr.length - 1; length >= 0; length--) {
                SyncRtSurfaceTransactionApplier.applyParams(transaction, surfaceParamsArr[length], this.mTmpFloat9);
            }
            transaction.apply();
            transaction.close();
        }

        @Override // android.view.InsetsAnimationControlCallbacks
        public void releaseSurfaceControlFromRt(SurfaceControl surfaceControl) {
            surfaceControl.release();
        }

        public /* synthetic */ void lambda$reportPerceptible$1$InsetsAnimationThreadControlRunner$1(int i, boolean z) {
            InsetsAnimationThreadControlRunner.this.mOuterCallbacks.reportPerceptible(i, z);
        }

        @Override // android.view.InsetsAnimationControlCallbacks
        public void reportPerceptible(final int i, final boolean z) {
            InsetsAnimationThreadControlRunner.this.mMainThreadHandler.post(new Runnable() { // from class: android.view.-$$Lambda$InsetsAnimationThreadControlRunner$1$P1j8tXGxG0HNP_tlmNjLKchwSD0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$reportPerceptible$1$InsetsAnimationThreadControlRunner$1(i, z);
                }
            });
        }
    }

    public InsetsAnimationThreadControlRunner(SparseArray<InsetsSourceControl> sparseArray, Rect rect, InsetsState insetsState, final WindowInsetsAnimationControlListener windowInsetsAnimationControlListener, final int i, InsetsAnimationControlCallbacks insetsAnimationControlCallbacks, long j, Interpolator interpolator, int i2, Handler handler) {
        this.mMainThreadHandler = handler;
        this.mOuterCallbacks = insetsAnimationControlCallbacks;
        this.mControl = new InsetsAnimationControlImpl(sparseArray, rect, insetsState, windowInsetsAnimationControlListener, i, this.mCallbacks, j, interpolator, i2);
        InsetsAnimationThread.getHandler().post(new Runnable() { // from class: android.view.-$$Lambda$InsetsAnimationThreadControlRunner$QoTV7aDlMBWllgklmGJ_FE7p30o
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$InsetsAnimationThreadControlRunner(i, windowInsetsAnimationControlListener);
            }
        });
    }

    public /* synthetic */ void lambda$new$0$InsetsAnimationThreadControlRunner(int i, WindowInsetsAnimationControlListener windowInsetsAnimationControlListener) {
        if (this.mControl.isCancelled()) {
            return;
        }
        Trace.asyncTraceBegin(8L, "InsetsAsyncAnimation: " + WindowInsets.Type.toString(i), i);
        windowInsetsAnimationControlListener.onReady(this.mControl, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseControls(SparseArray<InsetsSourceControl> sparseArray) {
        for (int size = sparseArray.size() - 1; size >= 0; size--) {
            sparseArray.valueAt(size).release($$Lambda$Rl1VZmNJ0VZDLK0BAbaVGis0rrA.INSTANCE);
        }
    }

    @Override // android.view.InsetsAnimationControlRunner
    public int getTypes() {
        return this.mControl.getTypes();
    }

    @Override // android.view.InsetsAnimationControlRunner
    public void cancel() {
        Handler handler = InsetsAnimationThread.getHandler();
        final InsetsAnimationControlImpl insetsAnimationControlImpl = this.mControl;
        Objects.requireNonNull(insetsAnimationControlImpl);
        handler.post(new Runnable() { // from class: android.view.-$$Lambda$kl-1KEyg7FwPQcg9XfREJM1iCiM
            @Override // java.lang.Runnable
            public final void run() {
                insetsAnimationControlImpl.cancel();
            }
        });
    }

    @Override // android.view.InsetsAnimationControlRunner
    public WindowInsetsAnimation getAnimation() {
        return this.mControl.getAnimation();
    }

    @Override // android.view.InsetsAnimationControlRunner
    public int getAnimationType() {
        return this.mControl.getAnimationType();
    }
}
