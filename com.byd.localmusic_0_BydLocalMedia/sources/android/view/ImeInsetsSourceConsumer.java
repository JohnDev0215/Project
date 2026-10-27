package android.view;

import android.os.IBinder;
import android.os.Parcel;
import android.text.TextUtils;
import android.view.SurfaceControl;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import java.util.Arrays;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
public final class ImeInsetsSourceConsumer extends InsetsSourceConsumer {
    private EditorInfo mFocusedEditor;
    private boolean mIsRequestedVisibleAwaitingControl;
    private EditorInfo mPreRenderedEditor;
    private boolean mShowOnNextImeRender;

    public ImeInsetsSourceConsumer(InsetsState insetsState, Supplier<SurfaceControl.Transaction> supplier, InsetsController insetsController) {
        super(17, insetsState, supplier, insetsController);
    }

    public void onPreRendered(EditorInfo editorInfo) {
        this.mPreRenderedEditor = editorInfo;
        if (this.mShowOnNextImeRender) {
            this.mShowOnNextImeRender = false;
            if (isServedEditorRendered()) {
                applyImeVisibility(true);
            }
        }
    }

    public void onServedEditorChanged(EditorInfo editorInfo) {
        if (isDummyOrEmptyEditor(editorInfo)) {
            this.mShowOnNextImeRender = false;
        }
        this.mFocusedEditor = editorInfo;
    }

    public void applyImeVisibility(boolean z) {
        this.mController.applyImeVisibility(z);
    }

    @Override // android.view.InsetsSourceConsumer
    public void onWindowFocusGained() {
        super.onWindowFocusGained();
        getImm().registerImeConsumer(this);
    }

    @Override // android.view.InsetsSourceConsumer
    public void onWindowFocusLost() {
        super.onWindowFocusLost();
        getImm().unregisterImeConsumer(this);
        this.mIsRequestedVisibleAwaitingControl = false;
    }

    @Override // android.view.InsetsSourceConsumer
    void hide(boolean z, int i) {
        super.hide();
        if (z) {
            notifyHidden();
            removeSurface();
        }
    }

    @Override // android.view.InsetsSourceConsumer
    public int requestShow(boolean z) {
        if (getControl() == null) {
            this.mIsRequestedVisibleAwaitingControl = true;
        }
        if (z) {
            return 0;
        }
        if (!this.mState.getSource(getType()).isVisible() || getControl() == null) {
            return getImm().requestImeShow(this.mController.getHost().getWindowToken()) ? 1 : 2;
        }
        return 0;
    }

    @Override // android.view.InsetsSourceConsumer
    void notifyHidden() {
        getImm().notifyImeHidden(this.mController.getHost().getWindowToken());
    }

    @Override // android.view.InsetsSourceConsumer
    public void removeSurface() {
        IBinder windowToken = this.mController.getHost().getWindowToken();
        if (windowToken != null) {
            getImm().removeImeSurface(windowToken);
        }
    }

    @Override // android.view.InsetsSourceConsumer
    public void setControl(InsetsSourceControl insetsSourceControl, int[] iArr, int[] iArr2) {
        super.setControl(insetsSourceControl, iArr, iArr2);
        if (insetsSourceControl != null || this.mIsRequestedVisibleAwaitingControl) {
            return;
        }
        hide();
        removeSurface();
    }

    @Override // android.view.InsetsSourceConsumer
    protected boolean isRequestedVisibleAwaitingControl() {
        return this.mIsRequestedVisibleAwaitingControl || isRequestedVisible();
    }

    @Override // android.view.InsetsSourceConsumer
    public void onPerceptible(boolean z) {
        super.onPerceptible(z);
        IBinder windowToken = this.mController.getHost().getWindowToken();
        if (windowToken != null) {
            getImm().reportPerceptible(windowToken, z);
        }
    }

    private boolean isDummyOrEmptyEditor(EditorInfo editorInfo) {
        return editorInfo == null || (editorInfo.fieldId <= 0 && editorInfo.inputType <= 0);
    }

    private boolean isServedEditorRendered() {
        EditorInfo editorInfo = this.mFocusedEditor;
        if (editorInfo == null || this.mPreRenderedEditor == null || isDummyOrEmptyEditor(editorInfo) || isDummyOrEmptyEditor(this.mPreRenderedEditor)) {
            return false;
        }
        return areEditorsSimilar(this.mFocusedEditor, this.mPreRenderedEditor);
    }

    public static boolean areEditorsSimilar(EditorInfo editorInfo, EditorInfo editorInfo2) {
        if (!(editorInfo.imeOptions == editorInfo2.imeOptions && editorInfo.inputType == editorInfo2.inputType && TextUtils.equals(editorInfo.packageName, editorInfo2.packageName)) || !(editorInfo.privateImeOptions != null ? editorInfo.privateImeOptions.equals(editorInfo2.privateImeOptions) : true)) {
            return false;
        }
        if ((editorInfo.extras == null && editorInfo2.extras == null) || editorInfo.extras == editorInfo2.extras) {
            return true;
        }
        if ((editorInfo.extras == null && editorInfo2.extras != null) || (editorInfo.extras == null && editorInfo2.extras != null)) {
            return false;
        }
        if (editorInfo.extras.hashCode() == editorInfo2.extras.hashCode() || editorInfo.extras.equals(editorInfo)) {
            return true;
        }
        if (editorInfo.extras.size() != editorInfo2.extras.size()) {
            return false;
        }
        if (editorInfo.extras.toString().equals(editorInfo2.extras.toString())) {
            return true;
        }
        Parcel parcelObtain = Parcel.obtain();
        editorInfo.extras.writeToParcel(parcelObtain, 0);
        parcelObtain.setDataPosition(0);
        Parcel parcelObtain2 = Parcel.obtain();
        editorInfo2.extras.writeToParcel(parcelObtain2, 0);
        parcelObtain2.setDataPosition(0);
        return Arrays.equals(parcelObtain.createByteArray(), parcelObtain2.createByteArray());
    }

    private InputMethodManager getImm() {
        return this.mController.getHost().getInputMethodManager();
    }
}
