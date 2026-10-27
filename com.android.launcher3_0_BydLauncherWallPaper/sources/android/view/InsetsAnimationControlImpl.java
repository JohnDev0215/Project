package android.view;

import android.graphics.Insets;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.ArraySet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.SparseSetArray;
import android.view.SyncRtSurfaceTransactionApplier;
import android.view.WindowInsetsAnimation;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
public class InsetsAnimationControlImpl implements WindowInsetsAnimationController, InsetsAnimationControlRunner {
    private static final String TAG = "InsetsAnimationCtrlImpl";
    private final WindowInsetsAnimation mAnimation;
    private final int mAnimationType;
    private boolean mCancelled;
    private final InsetsAnimationControlCallbacks mController;
    private final SparseArray<InsetsSourceControl> mControls;
    private float mCurrentAlpha;
    private Insets mCurrentInsets;
    private boolean mFinished;
    private final boolean mHasZeroInsetsIme;
    private final Insets mHiddenInsets;
    private final InsetsState mInitialInsetsState;
    private final WindowInsetsAnimationControlListener mListener;
    private float mPendingAlpha;
    private float mPendingFraction;
    private Insets mPendingInsets;
    private Boolean mPerceptible;
    public boolean mReadyDispatched;
    private final Insets mShownInsets;
    private boolean mShownOnFinish;
    private final SparseSetArray<InsetsSourceControl> mSideSourceMap;
    private final Rect mTmpFrame = new Rect();
    private final Matrix mTmpMatrix;
    private final SparseIntArray mTypeSideMap;
    private final int mTypes;

    private static float sanitize(float f) {
        if (f >= 1.0f) {
            return 1.0f;
        }
        if (f <= 0.0f) {
            return 0.0f;
        }
        return f;
    }

    public InsetsAnimationControlImpl(SparseArray<InsetsSourceControl> sparseArray, Rect rect, InsetsState insetsState, WindowInsetsAnimationControlListener windowInsetsAnimationControlListener, int i, InsetsAnimationControlCallbacks insetsAnimationControlCallbacks, long j, Interpolator interpolator, int i2) {
        SparseIntArray sparseIntArray = new SparseIntArray();
        this.mTypeSideMap = sparseIntArray;
        SparseSetArray<InsetsSourceControl> sparseSetArray = new SparseSetArray<>();
        this.mSideSourceMap = sparseSetArray;
        this.mTmpMatrix = new Matrix();
        this.mCurrentAlpha = 1.0f;
        this.mPendingAlpha = 1.0f;
        this.mControls = sparseArray;
        this.mListener = windowInsetsAnimationControlListener;
        this.mTypes = i;
        this.mController = insetsAnimationControlCallbacks;
        InsetsState insetsState2 = new InsetsState(insetsState, true);
        this.mInitialInsetsState = insetsState2;
        Insets insetsFromState = getInsetsFromState(insetsState2, rect, null);
        this.mCurrentInsets = insetsFromState;
        this.mPendingInsets = insetsFromState;
        Insets insetsCalculateInsets = calculateInsets(insetsState2, rect, sparseArray, false, null);
        this.mHiddenInsets = insetsCalculateInsets;
        Insets insetsCalculateInsets2 = calculateInsets(insetsState2, rect, sparseArray, true, sparseIntArray);
        this.mShownInsets = insetsCalculateInsets2;
        boolean z = insetsCalculateInsets2.bottom == 0 && controlsInternalType(17);
        this.mHasZeroInsetsIme = z;
        if (z) {
            sparseIntArray.put(17, 3);
        }
        buildTypeSourcesMap(sparseIntArray, sparseSetArray, sparseArray);
        WindowInsetsAnimation windowInsetsAnimation = new WindowInsetsAnimation(i, interpolator, j);
        this.mAnimation = windowInsetsAnimation;
        windowInsetsAnimation.setAlpha(getCurrentAlpha());
        this.mAnimationType = i2;
        insetsAnimationControlCallbacks.startAnimation(this, windowInsetsAnimationControlListener, i, windowInsetsAnimation, new WindowInsetsAnimation.Bounds(insetsCalculateInsets, insetsCalculateInsets2));
    }

    private boolean calculatePerceptible(Insets insets, float f) {
        return insets.left * 100 >= (this.mShownInsets.left - this.mHiddenInsets.left) * 5 && insets.top * 100 >= (this.mShownInsets.top - this.mHiddenInsets.top) * 5 && insets.right * 100 >= (this.mShownInsets.right - this.mHiddenInsets.right) * 5 && insets.bottom * 100 >= (this.mShownInsets.bottom - this.mHiddenInsets.bottom) * 5 && f >= 0.5f;
    }

    @Override // android.view.WindowInsetsAnimationController
    public boolean hasZeroInsetsIme() {
        return this.mHasZeroInsetsIme;
    }

    @Override // android.view.WindowInsetsAnimationController
    public Insets getHiddenStateInsets() {
        return this.mHiddenInsets;
    }

    @Override // android.view.WindowInsetsAnimationController
    public Insets getShownStateInsets() {
        return this.mShownInsets;
    }

    @Override // android.view.WindowInsetsAnimationController
    public Insets getCurrentInsets() {
        return this.mCurrentInsets;
    }

    @Override // android.view.WindowInsetsAnimationController
    public float getCurrentAlpha() {
        return this.mCurrentAlpha;
    }

    @Override // android.view.WindowInsetsAnimationController, android.view.InsetsAnimationControlRunner
    public int getTypes() {
        return this.mTypes;
    }

    @Override // android.view.InsetsAnimationControlRunner
    public int getAnimationType() {
        return this.mAnimationType;
    }

    @Override // android.view.WindowInsetsAnimationController
    public void setInsetsAndAlpha(Insets insets, float f, float f2) {
        setInsetsAndAlpha(insets, f, f2, false);
    }

    private void setInsetsAndAlpha(Insets insets, float f, float f2, boolean z) {
        if (!z && this.mFinished) {
            throw new IllegalStateException("Can't change insets on an animation that is finished.");
        }
        if (this.mCancelled) {
            throw new IllegalStateException("Can't change insets on an animation that is cancelled.");
        }
        this.mPendingFraction = sanitize(f2);
        this.mPendingInsets = sanitize(insets);
        this.mPendingAlpha = sanitize(f);
        this.mController.scheduleApplyChangeInsets(this);
        boolean zCalculatePerceptible = calculatePerceptible(this.mPendingInsets, this.mPendingAlpha);
        Boolean bool = this.mPerceptible;
        if (bool == null || zCalculatePerceptible != bool.booleanValue()) {
            this.mController.reportPerceptible(this.mTypes, zCalculatePerceptible);
            this.mPerceptible = Boolean.valueOf(zCalculatePerceptible);
        }
    }

    public boolean applyChangeInsets(InsetsState insetsState) {
        if (this.mCancelled) {
            return false;
        }
        Insets insetsSubtract = Insets.subtract(this.mShownInsets, this.mPendingInsets);
        ArrayList<SyncRtSurfaceTransactionApplier.SurfaceParams> arrayList = new ArrayList<>();
        updateLeashesForSide(0, insetsSubtract.left, this.mShownInsets.left, this.mPendingInsets.left, arrayList, insetsState, Float.valueOf(this.mPendingAlpha));
        updateLeashesForSide(1, insetsSubtract.top, this.mShownInsets.top, this.mPendingInsets.top, arrayList, insetsState, Float.valueOf(this.mPendingAlpha));
        updateLeashesForSide(2, insetsSubtract.right, this.mShownInsets.right, this.mPendingInsets.right, arrayList, insetsState, Float.valueOf(this.mPendingAlpha));
        updateLeashesForSide(3, insetsSubtract.bottom, this.mShownInsets.bottom, this.mPendingInsets.bottom, arrayList, insetsState, Float.valueOf(this.mPendingAlpha));
        this.mController.applySurfaceParams((SyncRtSurfaceTransactionApplier.SurfaceParams[]) arrayList.toArray(new SyncRtSurfaceTransactionApplier.SurfaceParams[arrayList.size()]));
        this.mCurrentInsets = this.mPendingInsets;
        this.mAnimation.setFraction(this.mPendingFraction);
        float f = this.mPendingAlpha;
        this.mCurrentAlpha = f;
        this.mAnimation.setAlpha(f);
        if (this.mFinished) {
            this.mController.notifyFinished(this, this.mShownOnFinish);
            releaseLeashes();
        }
        return this.mFinished;
    }

    private void releaseLeashes() {
        for (int size = this.mControls.size() - 1; size >= 0; size--) {
            InsetsSourceControl insetsSourceControlValueAt = this.mControls.valueAt(size);
            if (insetsSourceControlValueAt != null) {
                final InsetsAnimationControlCallbacks insetsAnimationControlCallbacks = this.mController;
                Objects.requireNonNull(insetsAnimationControlCallbacks);
                insetsSourceControlValueAt.release(new Consumer() { // from class: android.view.-$$Lambda$ETB8tv2IYR-HsOb03MQU_AcJplA
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        insetsAnimationControlCallbacks.releaseSurfaceControlFromRt((SurfaceControl) obj);
                    }
                });
            }
        }
    }

    @Override // android.view.WindowInsetsAnimationController
    public void finish(boolean z) {
        if (this.mCancelled || this.mFinished) {
            return;
        }
        this.mShownOnFinish = z;
        this.mFinished = true;
        setInsetsAndAlpha(z ? this.mShownInsets : this.mHiddenInsets, 1.0f, 1.0f, true);
        this.mListener.onFinished(this);
    }

    @Override // android.view.WindowInsetsAnimationController
    public float getCurrentFraction() {
        return this.mAnimation.getFraction();
    }

    @Override // android.view.InsetsAnimationControlRunner
    public void cancel() {
        if (this.mFinished) {
            return;
        }
        this.mCancelled = true;
        this.mListener.onCancelled(this.mReadyDispatched ? this : null);
        releaseLeashes();
    }

    @Override // android.view.WindowInsetsAnimationController
    public boolean isFinished() {
        return this.mFinished;
    }

    @Override // android.view.WindowInsetsAnimationController
    public boolean isCancelled() {
        return this.mCancelled;
    }

    @Override // android.view.InsetsAnimationControlRunner
    public WindowInsetsAnimation getAnimation() {
        return this.mAnimation;
    }

    WindowInsetsAnimationControlListener getListener() {
        return this.mListener;
    }

    SparseArray<InsetsSourceControl> getControls() {
        return this.mControls;
    }

    private Insets calculateInsets(InsetsState insetsState, Rect rect, SparseArray<InsetsSourceControl> sparseArray, boolean z, SparseIntArray sparseIntArray) {
        for (int size = sparseArray.size() - 1; size >= 0; size--) {
            if (sparseArray.valueAt(size) != null) {
                insetsState.getSource(sparseArray.valueAt(size).getType()).setVisible(z);
            }
        }
        return getInsetsFromState(insetsState, rect, sparseIntArray);
    }

    private Insets getInsetsFromState(InsetsState insetsState, Rect rect, SparseIntArray sparseIntArray) {
        return insetsState.calculateInsets(rect, null, false, false, null, 16, 0, 0, sparseIntArray).getInsets(this.mTypes);
    }

    private Insets sanitize(Insets insets) {
        if (insets == null) {
            insets = getCurrentInsets();
        }
        return hasZeroInsetsIme() ? insets : Insets.max(Insets.min(insets, this.mShownInsets), this.mHiddenInsets);
    }

    private void updateLeashesForSide(int i, int i2, int i3, int i4, ArrayList<SyncRtSurfaceTransactionApplier.SurfaceParams> arrayList, InsetsState insetsState, Float f) {
        ArraySet<InsetsSourceControl> arraySet = this.mSideSourceMap.get(i);
        if (arraySet == null) {
            return;
        }
        for (int size = arraySet.size() - 1; size >= 0; size--) {
            InsetsSourceControl insetsSourceControlValueAt = arraySet.valueAt(size);
            InsetsSource source = this.mInitialInsetsState.getSource(insetsSourceControlValueAt.getType());
            SurfaceControl leash = insetsSourceControlValueAt.getLeash();
            this.mTmpMatrix.setTranslate(insetsSourceControlValueAt.getSurfacePosition().x, insetsSourceControlValueAt.getSurfacePosition().y);
            this.mTmpFrame.set(source.getFrame());
            addTranslationToMatrix(i, i2, this.mTmpMatrix, this.mTmpFrame);
            boolean z = false;
            if (!this.mHasZeroInsetsIme || i != 3 ? i4 != 0 : !(this.mAnimationType != 0 && this.mFinished)) {
                z = true;
            }
            insetsState.getSource(source.getType()).setVisible(z);
            insetsState.getSource(source.getType()).setFrame(this.mTmpFrame);
            if (leash != null) {
                arrayList.add(new SyncRtSurfaceTransactionApplier.SurfaceParams.Builder(leash).withAlpha(f.floatValue()).withMatrix(this.mTmpMatrix).withVisibility(z).build());
            }
        }
    }

    private void addTranslationToMatrix(int i, int i2, Matrix matrix, Rect rect) {
        if (i == 0) {
            int i3 = -i2;
            matrix.postTranslate(i3, 0.0f);
            rect.offset(i3, 0);
        } else if (i == 1) {
            int i4 = -i2;
            matrix.postTranslate(0.0f, i4);
            rect.offset(0, i4);
        } else if (i == 2) {
            matrix.postTranslate(i2, 0.0f);
            rect.offset(i2, 0);
        } else {
            if (i != 3) {
                return;
            }
            matrix.postTranslate(0.0f, i2);
            rect.offset(0, i2);
        }
    }

    private static void buildTypeSourcesMap(SparseIntArray sparseIntArray, SparseSetArray<InsetsSourceControl> sparseSetArray, SparseArray<InsetsSourceControl> sparseArray) {
        for (int size = sparseIntArray.size() - 1; size >= 0; size--) {
            int iKeyAt = sparseIntArray.keyAt(size);
            int iValueAt = sparseIntArray.valueAt(size);
            InsetsSourceControl insetsSourceControl = sparseArray.get(iKeyAt);
            if (insetsSourceControl != null) {
                sparseSetArray.add(iValueAt, insetsSourceControl);
            }
        }
    }
}
