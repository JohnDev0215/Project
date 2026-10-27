package android.view;

import android.app.ResourcesManager;
import android.content.Context;
import android.graphics.Insets;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.DisplayCutout;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
import com.android.internal.os.IResultReceiver;

/* JADX INFO: loaded from: classes3.dex */
public final class WindowManagerImpl implements WindowManager {
    public final Context mContext;
    private IBinder mDefaultToken;
    private final WindowManagerGlobal mGlobal;
    private final Window mParentWindow;

    public WindowManagerImpl(Context context) {
        this(context, null);
    }

    private WindowManagerImpl(Context context, Window window) {
        this.mGlobal = WindowManagerGlobal.getInstance();
        this.mContext = context;
        this.mParentWindow = window;
    }

    public WindowManagerImpl createLocalWindowManager(Window window) {
        return new WindowManagerImpl(this.mContext, window);
    }

    public WindowManagerImpl createPresentationWindowManager(Context context) {
        return new WindowManagerImpl(context, this.mParentWindow);
    }

    public void setDefaultToken(IBinder iBinder) {
        this.mDefaultToken = iBinder;
    }

    @Override // android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        applyDefaultToken(layoutParams);
        this.mGlobal.addView(view, layoutParams, this.mContext.getDisplayNoVerify(), this.mParentWindow, this.mContext.getUserId());
    }

    @Override // android.view.ViewManager
    public void updateViewLayout(View view, ViewGroup.LayoutParams layoutParams) {
        applyDefaultToken(layoutParams);
        this.mGlobal.updateViewLayout(view, layoutParams);
    }

    private void applyDefaultToken(ViewGroup.LayoutParams layoutParams) {
        if (this.mDefaultToken == null || this.mParentWindow != null) {
            return;
        }
        if (!(layoutParams instanceof WindowManager.LayoutParams)) {
            throw new IllegalArgumentException("Params must be WindowManager.LayoutParams");
        }
        WindowManager.LayoutParams layoutParams2 = (WindowManager.LayoutParams) layoutParams;
        if (layoutParams2.token == null) {
            layoutParams2.token = this.mDefaultToken;
        }
    }

    @Override // android.view.ViewManager
    public void removeView(View view) {
        this.mGlobal.removeView(view, false);
    }

    @Override // android.view.WindowManager
    public void removeViewImmediate(View view) {
        this.mGlobal.removeView(view, true);
    }

    @Override // android.view.WindowManager
    public void requestAppKeyboardShortcuts(final WindowManager.KeyboardShortcutsReceiver keyboardShortcutsReceiver, int i) {
        try {
            WindowManagerGlobal.getWindowManagerService().requestAppKeyboardShortcuts(new IResultReceiver.Stub() { // from class: android.view.WindowManagerImpl.1
                @Override // com.android.internal.os.IResultReceiver
                public void send(int i2, Bundle bundle) throws RemoteException {
                    keyboardShortcutsReceiver.onKeyboardShortcutsReceived(bundle.getParcelableArrayList(WindowManager.PARCEL_KEY_SHORTCUTS_ARRAY));
                }
            }, i);
        } catch (RemoteException unused) {
        }
    }

    @Override // android.view.WindowManager
    public Display getDefaultDisplay() {
        return this.mContext.getDisplayNoVerify();
    }

    @Override // android.view.WindowManager
    public Region getCurrentImeTouchRegion() {
        try {
            return WindowManagerGlobal.getWindowManagerService().getCurrentImeTouchRegion();
        } catch (RemoteException unused) {
            return null;
        }
    }

    @Override // android.view.WindowManager
    public void setShouldShowWithInsecureKeyguard(int i, boolean z) {
        try {
            WindowManagerGlobal.getWindowManagerService().setShouldShowWithInsecureKeyguard(i, z);
        } catch (RemoteException unused) {
        }
    }

    @Override // android.view.WindowManager
    public void setShouldShowSystemDecors(int i, boolean z) {
        try {
            WindowManagerGlobal.getWindowManagerService().setShouldShowSystemDecors(i, z);
        } catch (RemoteException unused) {
        }
    }

    @Override // android.view.WindowManager
    public boolean shouldShowSystemDecors(int i) {
        try {
            return WindowManagerGlobal.getWindowManagerService().shouldShowSystemDecors(i);
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // android.view.WindowManager
    public void setShouldShowIme(int i, boolean z) {
        try {
            WindowManagerGlobal.getWindowManagerService().setShouldShowIme(i, z);
        } catch (RemoteException unused) {
        }
    }

    @Override // android.view.WindowManager
    public boolean shouldShowIme(int i) {
        try {
            return WindowManagerGlobal.getWindowManagerService().shouldShowIme(i);
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // android.view.WindowManager
    public WindowMetrics getCurrentWindowMetrics() {
        Window window = this.mParentWindow;
        Rect currentBounds = getCurrentBounds(window != null ? window.getContext() : this.mContext);
        return new WindowMetrics(currentBounds, computeWindowInsets(currentBounds));
    }

    private static Rect getCurrentBounds(Context context) {
        Rect bounds;
        synchronized (ResourcesManager.getInstance()) {
            bounds = context.getResources().getConfiguration().windowConfiguration.getBounds();
        }
        return bounds;
    }

    @Override // android.view.WindowManager
    public WindowMetrics getMaximumWindowMetrics() {
        Rect maximumBounds = getMaximumBounds();
        return new WindowMetrics(maximumBounds, computeWindowInsets(maximumBounds));
    }

    private Rect getMaximumBounds() {
        Display displayNoVerify = this.mContext.getDisplayNoVerify();
        Point point = new Point();
        displayNoVerify.getRealSize(point);
        return new Rect(0, 0, point.x, point.y);
    }

    private WindowInsets computeWindowInsets(Rect rect) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = 65792;
        Window window = this.mParentWindow;
        layoutParams.token = window != null ? window.getContext().getActivityToken() : this.mContext.getActivityToken();
        layoutParams.systemUiVisibility = 1536;
        layoutParams.setFitInsetsTypes(0);
        layoutParams.setFitInsetsSides(0);
        return getWindowInsetsFromServer(layoutParams, rect);
    }

    private WindowInsets getWindowInsetsFromServer(WindowManager.LayoutParams layoutParams, Rect rect) {
        try {
            Rect rect2 = new Rect();
            Rect rect3 = new Rect();
            DisplayCutout.ParcelableWrapper parcelableWrapper = new DisplayCutout.ParcelableWrapper();
            InsetsState insetsState = new InsetsState();
            boolean windowInsets = WindowManagerGlobal.getWindowManagerService().getWindowInsets(layoutParams, this.mContext.getDisplayId(), rect2, rect3, parcelableWrapper, insetsState);
            boolean zIsScreenRound = this.mContext.getResources().getConfiguration().isScreenRound();
            if (ViewRootImpl.sNewInsetsMode == 2) {
                return insetsState.calculateInsets(rect, null, zIsScreenRound, windowInsets, parcelableWrapper.get(), 48, layoutParams.flags, 0, null);
            }
            return new WindowInsets.Builder().setAlwaysConsumeSystemBars(windowInsets).setRound(zIsScreenRound).setSystemWindowInsets(Insets.of(rect2)).setStableInsets(Insets.of(rect3)).setDisplayCutout(parcelableWrapper.get()).build();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
