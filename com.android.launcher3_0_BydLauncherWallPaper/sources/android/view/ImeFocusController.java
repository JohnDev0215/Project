package android.view;

import android.util.Log;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.android.internal.inputmethod.InputMethodDebug;

/* JADX INFO: loaded from: classes3.dex */
public final class ImeFocusController {
    private static final boolean DEBUG = true;
    private static final String TAG = "ImeFocusController";
    private InputMethodManagerDelegate mDelegate;
    private boolean mHasImeFocus = false;
    private View mNextServedView;
    private View mServedView;
    private final ViewRootImpl mViewRootImpl;

    public interface InputMethodManagerDelegate {
        void closeCurrentIme();

        void finishComposingText();

        void finishInput();

        boolean hasActiveConnection(View view);

        boolean isCurrentRootView(ViewRootImpl viewRootImpl);

        boolean isRestartOnNextWindowFocus(boolean z);

        void setCurrentRootView(ViewRootImpl viewRootImpl);

        boolean startInput(int i, View view, int i2, int i3, int i4);

        void startInputAsyncOnWindowFocusGain(View view, int i, int i2, boolean z);
    }

    ImeFocusController(ViewRootImpl viewRootImpl) {
        this.mViewRootImpl = viewRootImpl;
    }

    private InputMethodManagerDelegate getImmDelegate() {
        InputMethodManagerDelegate inputMethodManagerDelegate = this.mDelegate;
        if (inputMethodManagerDelegate != null) {
            return inputMethodManagerDelegate;
        }
        InputMethodManager.DelegateImpl delegate = ((InputMethodManager) this.mViewRootImpl.mContext.getSystemService(InputMethodManager.class)).getDelegate();
        this.mDelegate = delegate;
        return delegate;
    }

    void onMovedToDisplay() {
        this.mDelegate = null;
    }

    void onTraversal(boolean z, WindowManager.LayoutParams layoutParams) {
        boolean zUpdateImeFocusable = updateImeFocusable(layoutParams, false);
        if (!z || isInLocalFocusMode(layoutParams) || zUpdateImeFocusable == this.mHasImeFocus) {
            return;
        }
        this.mHasImeFocus = zUpdateImeFocusable;
        if (zUpdateImeFocusable) {
            onPreWindowFocus(true, layoutParams);
            onPostWindowFocus(this.mViewRootImpl.mView.findFocus(), true, layoutParams);
        }
    }

    void onPreWindowFocus(boolean z, WindowManager.LayoutParams layoutParams) {
        if (this.mHasImeFocus && !isInLocalFocusMode(layoutParams) && z) {
            getImmDelegate().setCurrentRootView(this.mViewRootImpl);
        }
    }

    boolean updateImeFocusable(WindowManager.LayoutParams layoutParams, boolean z) {
        boolean zMayUseInputMethod = WindowManager.LayoutParams.mayUseInputMethod(layoutParams.flags);
        if (z) {
            this.mHasImeFocus = zMayUseInputMethod;
        }
        return zMayUseInputMethod;
    }

    void onPostWindowFocus(View view, boolean z, WindowManager.LayoutParams layoutParams) {
        boolean z2;
        if (z && this.mHasImeFocus && !isInLocalFocusMode(layoutParams)) {
            Log.v(TAG, "onWindowFocus: " + view + " softInputMode=" + InputMethodDebug.softInputModeToString(layoutParams.softInputMode));
            InputMethodManagerDelegate immDelegate = getImmDelegate();
            if (immDelegate.isRestartOnNextWindowFocus(true)) {
                Log.v(TAG, "Restarting due to isRestartOnNextWindowFocus as true");
                z2 = true;
            } else {
                z2 = false;
            }
            if (view == null) {
                view = this.mViewRootImpl.mView;
            }
            onViewFocusChanged(view, true);
            immDelegate.startInputAsyncOnWindowFocusGain(view, layoutParams.softInputMode, layoutParams.flags, (!(this.mServedView == view) || immDelegate.hasActiveConnection(view)) ? z2 : true);
        }
    }

    public boolean checkFocus(boolean z, boolean z2) {
        InputMethodManagerDelegate immDelegate = getImmDelegate();
        if (!immDelegate.isCurrentRootView(this.mViewRootImpl) || (this.mServedView == this.mNextServedView && !z)) {
            return false;
        }
        StringBuilder sbAppend = new StringBuilder().append("checkFocus: view=").append(this.mServedView).append(" next=").append(this.mNextServedView).append(" force=").append(z).append(" package=");
        View view = this.mServedView;
        Log.v(TAG, sbAppend.append(view != null ? view.getContext().getPackageName() : "<none>").toString());
        View view2 = this.mNextServedView;
        if (view2 == null) {
            immDelegate.finishInput();
            immDelegate.closeCurrentIme();
            return false;
        }
        this.mServedView = view2;
        immDelegate.finishComposingText();
        if (!z2) {
            return true;
        }
        immDelegate.startInput(5, null, 0, 0, 0);
        return true;
    }

    void onViewFocusChanged(View view, boolean z) {
        if (view != null && !view.isTemporarilyDetached() && getImmDelegate().isCurrentRootView(view.getViewRootImpl()) && view.hasImeFocus() && view.hasWindowFocus()) {
            Log.d(TAG, "onViewFocusChanged, view=" + view + ", mServedView=" + this.mServedView);
            if (z) {
                this.mNextServedView = view;
            }
            this.mViewRootImpl.dispatchCheckFocus();
        }
    }

    void onViewDetachedFromWindow(View view) {
        if (getImmDelegate().isCurrentRootView(view.getViewRootImpl()) && this.mServedView == view) {
            this.mNextServedView = null;
            this.mViewRootImpl.dispatchCheckFocus();
        }
    }

    void onWindowDismissed() {
        InputMethodManagerDelegate immDelegate = getImmDelegate();
        if (immDelegate.isCurrentRootView(this.mViewRootImpl)) {
            if (this.mServedView != null) {
                immDelegate.finishInput();
            }
            immDelegate.setCurrentRootView(null);
            this.mHasImeFocus = false;
        }
    }

    private static boolean isInLocalFocusMode(WindowManager.LayoutParams layoutParams) {
        return (layoutParams.flags & 268435456) != 0;
    }

    int onProcessImeInputStage(Object obj, InputEvent inputEvent, WindowManager.LayoutParams layoutParams, InputMethodManager.FinishedInputEventCallback finishedInputEventCallback) {
        InputMethodManager inputMethodManager;
        if (!this.mHasImeFocus || isInLocalFocusMode(layoutParams) || (inputMethodManager = (InputMethodManager) this.mViewRootImpl.mContext.getSystemService(InputMethodManager.class)) == null) {
            return 0;
        }
        return inputMethodManager.dispatchInputEvent(inputEvent, obj, finishedInputEventCallback, this.mViewRootImpl.mHandler);
    }

    public View getServedView() {
        return this.mServedView;
    }

    public View getNextServedView() {
        return this.mNextServedView;
    }

    public void setServedView(View view) {
        this.mServedView = view;
    }

    public void setNextServedView(View view) {
        this.mNextServedView = view;
    }

    boolean hasImeFocus() {
        return this.mHasImeFocus;
    }
}
