package android.view;

import android.graphics.HardwareRenderer;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.InsetsController;
import android.view.SurfaceControl;
import android.view.SyncRtSurfaceTransactionApplier;
import android.view.ViewTreeObserver;
import android.view.WindowInsetsAnimation;
import android.view.inputmethod.InputMethodManager;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ViewRootInsetsControllerHost implements InsetsController.Host {
    private final String TAG = "VRInsetsControllerHost";
    private SyncRtSurfaceTransactionApplier mApplier;
    private final ViewRootImpl mViewRoot;

    public ViewRootInsetsControllerHost(ViewRootImpl viewRootImpl) {
        this.mViewRoot = viewRootImpl;
    }

    @Override // android.view.InsetsController.Host
    public Handler getHandler() {
        return this.mViewRoot.mHandler;
    }

    @Override // android.view.InsetsController.Host
    public void notifyInsetsChanged() {
        this.mViewRoot.notifyInsetsChanged();
    }

    @Override // android.view.InsetsController.Host
    public void addOnPreDrawRunnable(final Runnable runnable) {
        if (this.mViewRoot.mView == null) {
            return;
        }
        this.mViewRoot.mView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: android.view.ViewRootInsetsControllerHost.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                ViewRootInsetsControllerHost.this.mViewRoot.mView.getViewTreeObserver().removeOnPreDrawListener(this);
                runnable.run();
                return true;
            }
        });
        this.mViewRoot.mView.invalidate();
    }

    @Override // android.view.InsetsController.Host
    public void dispatchWindowInsetsAnimationPrepare(WindowInsetsAnimation windowInsetsAnimation) {
        if (this.mViewRoot.mView == null) {
            return;
        }
        this.mViewRoot.mView.dispatchWindowInsetsAnimationPrepare(windowInsetsAnimation);
    }

    @Override // android.view.InsetsController.Host
    public WindowInsetsAnimation.Bounds dispatchWindowInsetsAnimationStart(WindowInsetsAnimation windowInsetsAnimation, WindowInsetsAnimation.Bounds bounds) {
        if (this.mViewRoot.mView == null) {
            return null;
        }
        return this.mViewRoot.mView.dispatchWindowInsetsAnimationStart(windowInsetsAnimation, bounds);
    }

    @Override // android.view.InsetsController.Host
    public WindowInsets dispatchWindowInsetsAnimationProgress(WindowInsets windowInsets, List<WindowInsetsAnimation> list) {
        if (this.mViewRoot.mView == null) {
            return null;
        }
        return this.mViewRoot.mView.dispatchWindowInsetsAnimationProgress(windowInsets, list);
    }

    @Override // android.view.InsetsController.Host
    public void dispatchWindowInsetsAnimationEnd(WindowInsetsAnimation windowInsetsAnimation) {
        this.mViewRoot.mView.dispatchWindowInsetsAnimationEnd(windowInsetsAnimation);
    }

    @Override // android.view.InsetsController.Host
    public void applySurfaceParams(SyncRtSurfaceTransactionApplier.SurfaceParams... surfaceParamsArr) {
        if (this.mViewRoot.mView == null) {
            throw new IllegalStateException("View of the ViewRootImpl is not initiated.");
        }
        if (this.mApplier == null) {
            this.mApplier = new SyncRtSurfaceTransactionApplier(this.mViewRoot.mView);
        }
        if (this.mViewRoot.mView.isHardwareAccelerated()) {
            this.mApplier.scheduleApply(surfaceParamsArr);
        } else {
            this.mApplier.applyParams(new SurfaceControl.Transaction(), -1L, surfaceParamsArr);
        }
    }

    @Override // android.view.InsetsController.Host
    public void postInsetsAnimationCallback(Runnable runnable) {
        this.mViewRoot.mChoreographer.postCallback(2, runnable, null);
    }

    @Override // android.view.InsetsController.Host
    public void updateCompatSysUiVisibility(int i, boolean z, boolean z2) {
        this.mViewRoot.updateCompatSysUiVisibility(i, z, z2);
    }

    @Override // android.view.InsetsController.Host
    public void onInsetsModified(InsetsState insetsState) {
        try {
            this.mViewRoot.mWindowSession.insetsModified(this.mViewRoot.mWindow, insetsState);
        } catch (RemoteException e) {
            Log.e("VRInsetsControllerHost", "Failed to call insetsModified", e);
        }
    }

    @Override // android.view.InsetsController.Host
    public boolean hasAnimationCallbacks() {
        if (this.mViewRoot.mView == null) {
            return false;
        }
        return this.mViewRoot.mView.hasWindowInsetsAnimationCallback();
    }

    @Override // android.view.InsetsController.Host
    public void setSystemBarsAppearance(int i, int i2) {
        this.mViewRoot.mWindowAttributes.privateFlags |= 67108864;
        InsetsFlags insetsFlags = this.mViewRoot.mWindowAttributes.insetsFlags;
        if (insetsFlags.appearance != i) {
            insetsFlags.appearance = (i & i2) | (insetsFlags.appearance & (~i2));
            this.mViewRoot.mWindowAttributesChanged = true;
            this.mViewRoot.scheduleTraversals();
        }
    }

    @Override // android.view.InsetsController.Host
    public int getSystemBarsAppearance() {
        if ((this.mViewRoot.mWindowAttributes.privateFlags & 67108864) == 0) {
            return 0;
        }
        return this.mViewRoot.mWindowAttributes.insetsFlags.appearance;
    }

    @Override // android.view.InsetsController.Host
    public void setSystemBarsBehavior(int i) {
        this.mViewRoot.mWindowAttributes.privateFlags |= 134217728;
        if (this.mViewRoot.mWindowAttributes.insetsFlags.behavior != i) {
            this.mViewRoot.mWindowAttributes.insetsFlags.behavior = i;
            this.mViewRoot.mWindowAttributesChanged = true;
            this.mViewRoot.scheduleTraversals();
        }
    }

    @Override // android.view.InsetsController.Host
    public int getSystemBarsBehavior() {
        if ((this.mViewRoot.mWindowAttributes.privateFlags & 134217728) == 0) {
            return 0;
        }
        return this.mViewRoot.mWindowAttributes.insetsFlags.behavior;
    }

    @Override // android.view.InsetsController.Host
    public void releaseSurfaceControlFromRt(final SurfaceControl surfaceControl) {
        if (this.mViewRoot.mView != null && this.mViewRoot.mView.isHardwareAccelerated()) {
            this.mViewRoot.registerRtFrameCallback(new HardwareRenderer.FrameDrawingCallback() { // from class: android.view.-$$Lambda$ViewRootInsetsControllerHost$3v_KSpxQXUSxSM3D_S6T5-aZKoQ
                @Override // android.graphics.HardwareRenderer.FrameDrawingCallback
                public final void onFrameDraw(long j) {
                    surfaceControl.release();
                }
            });
            this.mViewRoot.mView.invalidate();
        } else {
            surfaceControl.release();
        }
    }

    @Override // android.view.InsetsController.Host
    public InputMethodManager getInputMethodManager() {
        return (InputMethodManager) this.mViewRoot.mContext.getSystemService(InputMethodManager.class);
    }

    @Override // android.view.InsetsController.Host
    public String getRootViewTitle() {
        ViewRootImpl viewRootImpl = this.mViewRoot;
        if (viewRootImpl == null) {
            return null;
        }
        return viewRootImpl.getTitle().toString();
    }

    @Override // android.view.InsetsController.Host
    public int dipToPx(int i) {
        ViewRootImpl viewRootImpl = this.mViewRoot;
        if (viewRootImpl != null) {
            return viewRootImpl.dipToPx(i);
        }
        return 0;
    }

    @Override // android.view.InsetsController.Host
    public IBinder getWindowToken() {
        View view;
        ViewRootImpl viewRootImpl = this.mViewRoot;
        if (viewRootImpl == null || (view = viewRootImpl.getView()) == null) {
            return null;
        }
        return view.getWindowToken();
    }
}
