package android.view;

import android.graphics.Rect;
import android.view.SurfaceControl;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
public class InsetsSourceConsumer {
    private static final String TAG = "InsetsSourceConsumer";
    protected final InsetsController mController;
    private boolean mHasWindowFocus;
    private boolean mIsAnimationPending;
    private Rect mPendingFrame;
    private Rect mPendingVisibleFrame;
    protected boolean mRequestedVisible;
    private InsetsSourceControl mSourceControl;
    protected final InsetsState mState;
    private final Supplier<SurfaceControl.Transaction> mTransactionSupplier;
    protected final int mType;

    @Retention(RetentionPolicy.SOURCE)
    @interface ShowResult {
        public static final int IME_SHOW_DELAYED = 1;
        public static final int IME_SHOW_FAILED = 2;
        public static final int SHOW_IMMEDIATELY = 0;
    }

    void notifyHidden() {
    }

    public void onPerceptible(boolean z) {
    }

    public void removeSurface() {
    }

    public int requestShow(boolean z) {
        return 0;
    }

    public InsetsSourceConsumer(int i, InsetsState insetsState, Supplier<SurfaceControl.Transaction> supplier, InsetsController insetsController) {
        this.mType = i;
        this.mState = insetsState;
        this.mTransactionSupplier = supplier;
        this.mController = insetsController;
        this.mRequestedVisible = InsetsState.getDefaultVisibility(i);
    }

    public void setControl(InsetsSourceControl insetsSourceControl, int[] iArr, int[] iArr2) {
        InsetsSourceControl insetsSourceControl2 = this.mSourceControl;
        if (insetsSourceControl2 == insetsSourceControl) {
            return;
        }
        SurfaceControl leash = insetsSourceControl2 != null ? insetsSourceControl2.getLeash() : null;
        InsetsSourceControl insetsSourceControl3 = this.mSourceControl;
        this.mSourceControl = insetsSourceControl;
        if (insetsSourceControl == null) {
            this.mController.notifyControlRevoked(this);
            InsetsSource source = this.mState.getSource(this.mType);
            boolean sourceOrDefaultVisibility = this.mController.getLastDispatchedState().getSourceOrDefaultVisibility(this.mType);
            if (source.isVisible() != sourceOrDefaultVisibility) {
                source.setVisible(sourceOrDefaultVisibility);
                this.mController.notifyVisibilityChanged();
            }
            applyLocalVisibilityOverride();
        } else {
            boolean zIsRequestedVisibleAwaitingControl = isRequestedVisibleAwaitingControl();
            boolean z = zIsRequestedVisibleAwaitingControl != this.mState.getSource(this.mType).isVisible();
            if (insetsSourceControl.getLeash() != null && (z || this.mIsAnimationPending)) {
                if (zIsRequestedVisibleAwaitingControl) {
                    iArr[0] = iArr[0] | InsetsState.toPublicType(getType());
                } else {
                    iArr2[0] = iArr2[0] | InsetsState.toPublicType(getType());
                }
                this.mIsAnimationPending = false;
            } else {
                if (z) {
                    this.mIsAnimationPending = true;
                }
                if (applyLocalVisibilityOverride()) {
                    this.mController.notifyVisibilityChanged();
                }
                SurfaceControl leash2 = this.mSourceControl.getLeash();
                if (leash == null || leash2 == null || !leash.isSameSurface(leash2)) {
                    applyHiddenToControl();
                }
                if (!zIsRequestedVisibleAwaitingControl && !this.mIsAnimationPending) {
                    removeSurface();
                }
            }
        }
        if (insetsSourceControl3 != null) {
            insetsSourceControl3.release($$Lambda$Rl1VZmNJ0VZDLK0BAbaVGis0rrA.INSTANCE);
        }
    }

    public InsetsSourceControl getControl() {
        return this.mSourceControl;
    }

    protected boolean isRequestedVisibleAwaitingControl() {
        return isRequestedVisible();
    }

    int getType() {
        return this.mType;
    }

    public void show(boolean z) {
        setRequestedVisible(true);
    }

    public void hide() {
        setRequestedVisible(false);
    }

    void hide(boolean z, int i) {
        hide();
    }

    public void onWindowFocusGained() {
        this.mHasWindowFocus = true;
    }

    public void onWindowFocusLost() {
        this.mHasWindowFocus = false;
    }

    boolean hasWindowFocus() {
        return this.mHasWindowFocus;
    }

    boolean applyLocalVisibilityOverride() {
        InsetsSource insetsSourcePeekSource = this.mState.peekSource(this.mType);
        boolean zIsVisible = insetsSourcePeekSource != null ? insetsSourcePeekSource.isVisible() : InsetsState.getDefaultVisibility(this.mType);
        boolean z = this.mSourceControl != null;
        this.mController.updateCompatSysUiVisibility(this.mType, (z || insetsSourcePeekSource == null) ? this.mRequestedVisible : zIsVisible, z);
        if (!z || zIsVisible == this.mRequestedVisible) {
            return false;
        }
        this.mState.getSource(this.mType).setVisible(this.mRequestedVisible);
        return true;
    }

    public boolean isRequestedVisible() {
        return this.mRequestedVisible;
    }

    public void updateSource(InsetsSource insetsSource, int i) {
        InsetsSource insetsSourcePeekSource = this.mState.peekSource(this.mType);
        if (insetsSourcePeekSource == null || i == -1 || insetsSourcePeekSource.getFrame().equals(insetsSource.getFrame())) {
            this.mPendingFrame = null;
            this.mPendingVisibleFrame = null;
            this.mState.addSource(insetsSource);
        } else {
            InsetsSource insetsSource2 = new InsetsSource(insetsSource);
            this.mPendingFrame = new Rect(insetsSource2.getFrame());
            this.mPendingVisibleFrame = insetsSource2.getVisibleFrame() != null ? new Rect(insetsSource2.getVisibleFrame()) : null;
            insetsSource2.setFrame(insetsSourcePeekSource.getFrame());
            insetsSource2.setVisibleFrame(insetsSourcePeekSource.getVisibleFrame());
            this.mState.addSource(insetsSource2);
        }
    }

    public boolean notifyAnimationFinished() {
        if (this.mPendingFrame == null) {
            return false;
        }
        InsetsSource source = this.mState.getSource(this.mType);
        source.setFrame(this.mPendingFrame);
        source.setVisibleFrame(this.mPendingVisibleFrame);
        this.mPendingFrame = null;
        this.mPendingVisibleFrame = null;
        return true;
    }

    protected void setRequestedVisible(boolean z) {
        if (this.mRequestedVisible != z) {
            this.mRequestedVisible = z;
            this.mIsAnimationPending = false;
        }
        if (applyLocalVisibilityOverride()) {
            this.mController.notifyVisibilityChanged();
        }
    }

    private void applyHiddenToControl() {
        InsetsSourceControl insetsSourceControl = this.mSourceControl;
        if (insetsSourceControl == null || insetsSourceControl.getLeash() == null) {
            return;
        }
        SurfaceControl.Transaction transaction = this.mTransactionSupplier.get();
        if (this.mRequestedVisible) {
            transaction.show(this.mSourceControl.getLeash());
        } else {
            transaction.hide(this.mSourceControl.getLeash());
        }
        transaction.apply();
        onPerceptible(this.mRequestedVisible);
    }
}
