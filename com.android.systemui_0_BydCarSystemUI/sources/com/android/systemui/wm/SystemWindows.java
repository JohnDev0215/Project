package com.android.systemui.wm;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.MergedConfiguration;
import android.util.Slog;
import android.util.SparseArray;
import android.view.DisplayCutout;
import android.view.DragEvent;
import android.view.IScrollCaptureController;
import android.view.IWindow;
import android.view.IWindowManager;
import android.view.IWindowSession;
import android.view.IWindowSessionCallback;
import android.view.InsetsSourceControl;
import android.view.InsetsState;
import android.view.SurfaceControl;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowlessWindowManager;
import com.android.internal.os.IResultReceiver;
import com.android.systemui.wm.DisplayController;
import java.util.HashMap;
import javax.inject.Inject;
import javax.inject.Singleton;

/* JADX INFO: loaded from: classes.dex */
@Singleton
public class SystemWindows {
    private static final String TAG = "SystemWindows";
    Context mContext;
    DisplayController mDisplayController;
    private final DisplayController.OnDisplaysChangedListener mDisplayListener;
    IWindowSession mSession;
    IWindowManager mWmService;
    private final SparseArray<PerDisplay> mPerDisplay = new SparseArray<>();
    final HashMap<View, SurfaceControlViewHost> mViewRoots = new HashMap<>();

    @Inject
    public SystemWindows(Context context, DisplayController displayController, IWindowManager wmService) {
        DisplayController.OnDisplaysChangedListener onDisplaysChangedListener = new DisplayController.OnDisplaysChangedListener() { // from class: com.android.systemui.wm.SystemWindows.1
            @Override // com.android.systemui.wm.DisplayController.OnDisplaysChangedListener
            public void onDisplayAdded(int displayId) {
            }

            @Override // com.android.systemui.wm.DisplayController.OnDisplaysChangedListener
            public void onDisplayRemoved(int displayId) {
            }

            @Override // com.android.systemui.wm.DisplayController.OnDisplaysChangedListener
            public void onDisplayConfigurationChanged(int displayId, Configuration newConfig) {
                PerDisplay perDisplay = (PerDisplay) SystemWindows.this.mPerDisplay.get(displayId);
                if (perDisplay == null) {
                    return;
                }
                perDisplay.updateConfiguration(newConfig);
            }
        };
        this.mDisplayListener = onDisplaysChangedListener;
        this.mContext = context;
        this.mWmService = wmService;
        this.mDisplayController = displayController;
        displayController.addDisplayWindowListener(onDisplaysChangedListener);
        try {
            this.mSession = wmService.openSession(new IWindowSessionCallback.Stub() { // from class: com.android.systemui.wm.SystemWindows.2
                public void onAnimatorScaleChanged(float scale) {
                }
            });
        } catch (RemoteException e) {
            Slog.e(TAG, "Unable to create layer", e);
        }
    }

    public void addView(View view, WindowManager.LayoutParams attrs, int displayId, int windowType) {
        PerDisplay perDisplay = this.mPerDisplay.get(displayId);
        if (perDisplay == null) {
            perDisplay = new PerDisplay(displayId);
            this.mPerDisplay.put(displayId, perDisplay);
        }
        perDisplay.addView(view, attrs, windowType);
    }

    public void removeView(View view) {
        this.mViewRoots.remove(view).release();
    }

    public void updateViewLayout(View view, ViewGroup.LayoutParams params) {
        SurfaceControlViewHost surfaceControlViewHost = this.mViewRoots.get(view);
        if (surfaceControlViewHost == null || !(params instanceof WindowManager.LayoutParams)) {
            return;
        }
        view.setLayoutParams(params);
        surfaceControlViewHost.relayout((WindowManager.LayoutParams) params);
    }

    public void setTouchableRegion(View view, Region region) {
        SurfaceControlViewHost surfaceControlViewHost = this.mViewRoots.get(view);
        if (surfaceControlViewHost == null) {
            return;
        }
        WindowlessWindowManager windowlessWM = surfaceControlViewHost.getWindowlessWM();
        if (windowlessWM instanceof SysUiWindowManager) {
            ((SysUiWindowManager) windowlessWM).setTouchableRegionForWindow(view, region);
        }
    }

    public void addRoot(int displayId, int windowType) {
        PerDisplay perDisplay = this.mPerDisplay.get(displayId);
        if (perDisplay == null) {
            perDisplay = new PerDisplay(displayId);
            this.mPerDisplay.put(displayId, perDisplay);
        }
        perDisplay.addRoot(windowType);
    }

    IWindow getWindow(int displayId, int windowType) {
        PerDisplay perDisplay = this.mPerDisplay.get(displayId);
        if (perDisplay == null) {
            return null;
        }
        return perDisplay.getWindow(windowType);
    }

    public SurfaceControl getViewSurface(View rootView) {
        for (int i = 0; i < this.mPerDisplay.size(); i++) {
            for (int i2 = 0; i2 < this.mPerDisplay.valueAt(i).mWwms.size(); i2++) {
                SurfaceControl surfaceControlForWindow = ((SysUiWindowManager) this.mPerDisplay.valueAt(i).mWwms.valueAt(i2)).getSurfaceControlForWindow(rootView);
                if (surfaceControlForWindow != null) {
                    return surfaceControlForWindow;
                }
            }
        }
        return null;
    }

    private class PerDisplay {
        final int mDisplayId;
        private final SparseArray<SysUiWindowManager> mWwms = new SparseArray<>();

        PerDisplay(int displayId) {
            this.mDisplayId = displayId;
        }

        public void addView(View view, WindowManager.LayoutParams attrs, int windowType) {
            SysUiWindowManager sysUiWindowManagerAddRoot = addRoot(windowType);
            if (sysUiWindowManagerAddRoot == null) {
                Slog.e(SystemWindows.TAG, "Unable to create systemui root");
                return;
            }
            SurfaceControlViewHost surfaceControlViewHost = new SurfaceControlViewHost(SystemWindows.this.mContext, SystemWindows.this.mDisplayController.getDisplay(this.mDisplayId), sysUiWindowManagerAddRoot, true);
            attrs.flags |= 16777216;
            surfaceControlViewHost.setView(view, attrs);
            SystemWindows.this.mViewRoots.put(view, surfaceControlViewHost);
            try {
                SystemWindows.this.mWmService.setShellRootAccessibilityWindow(this.mDisplayId, windowType, surfaceControlViewHost.getWindowToken());
            } catch (RemoteException e) {
                Slog.e(SystemWindows.TAG, "Error setting accessibility window for " + this.mDisplayId + ":" + windowType, e);
            }
        }

        SysUiWindowManager addRoot(int windowType) {
            SurfaceControl surfaceControlAddShellRoot;
            SysUiWindowManager sysUiWindowManager = this.mWwms.get(windowType);
            if (sysUiWindowManager != null) {
                return sysUiWindowManager;
            }
            ContainerWindow containerWindow = SystemWindows.this.new ContainerWindow();
            try {
                surfaceControlAddShellRoot = SystemWindows.this.mWmService.addShellRoot(this.mDisplayId, containerWindow, windowType);
            } catch (RemoteException unused) {
                surfaceControlAddShellRoot = null;
            }
            if (surfaceControlAddShellRoot == null) {
                Slog.e(SystemWindows.TAG, "Unable to get root surfacecontrol for systemui");
                return null;
            }
            SysUiWindowManager sysUiWindowManager2 = SystemWindows.this.new SysUiWindowManager(this.mDisplayId, SystemWindows.this.mDisplayController.getDisplayContext(this.mDisplayId), surfaceControlAddShellRoot, containerWindow);
            this.mWwms.put(windowType, sysUiWindowManager2);
            return sysUiWindowManager2;
        }

        IWindow getWindow(int windowType) {
            SysUiWindowManager sysUiWindowManager = this.mWwms.get(windowType);
            if (sysUiWindowManager == null) {
                return null;
            }
            return sysUiWindowManager.mContainerWindow;
        }

        void updateConfiguration(Configuration configuration) {
            for (int i = 0; i < this.mWwms.size(); i++) {
                this.mWwms.valueAt(i).updateConfiguration(configuration);
            }
        }
    }

    public class SysUiWindowManager extends WindowlessWindowManager {
        ContainerWindow mContainerWindow;
        final int mDisplayId;

        public SysUiWindowManager(int displayId, Context ctx, SurfaceControl rootSurface, ContainerWindow container) {
            super(ctx.getResources().getConfiguration(), rootSurface, (IBinder) null);
            this.mContainerWindow = container;
            this.mDisplayId = displayId;
        }

        public int relayout(IWindow window, int seq, WindowManager.LayoutParams attrs, int requestedWidth, int requestedHeight, int viewVisibility, int flags, long frameNumber, Rect outFrame, Rect outOverscanInsets, Rect outContentInsets, Rect outVisibleInsets, Rect outStableInsets, DisplayCutout.ParcelableWrapper cutout, MergedConfiguration mergedConfiguration, SurfaceControl outSurfaceControl, InsetsState outInsetsState, InsetsSourceControl[] outActiveControls, Point outSurfaceSize, SurfaceControl outBLASTSurfaceControl) {
            int iRelayout = super.relayout(window, seq, attrs, requestedWidth, requestedHeight, viewVisibility, flags, frameNumber, outFrame, outOverscanInsets, outContentInsets, outVisibleInsets, outStableInsets, cutout, mergedConfiguration, outSurfaceControl, outInsetsState, outActiveControls, outSurfaceSize, outBLASTSurfaceControl);
            if (iRelayout != 0) {
                return iRelayout;
            }
            outStableInsets.set(SystemWindows.this.mDisplayController.getDisplayLayout(this.mDisplayId).stableInsets());
            return 0;
        }

        void updateConfiguration(Configuration configuration) {
            setConfiguration(configuration);
        }

        SurfaceControl getSurfaceControlForWindow(View rootView) {
            return getSurfaceControl(rootView);
        }

        void setTouchableRegionForWindow(View rootView, Region region) {
            IBinder windowToken = rootView.getWindowToken();
            if (windowToken == null) {
                return;
            }
            setTouchRegion(windowToken, region);
        }
    }

    class ContainerWindow extends IWindow.Stub {
        public void closeSystemDialogs(String reason) {
        }

        public void dispatchAppVisibility(boolean visible) {
        }

        public void dispatchDragEvent(DragEvent event) {
        }

        public void dispatchGetNewSurface() {
        }

        public void dispatchPointerCaptureChanged(boolean hasCapture) {
        }

        public void dispatchSystemUiVisibilityChanged(int seq, int globalVisibility, int localValue, int localChanges) {
        }

        public void dispatchWallpaperCommand(String action, int x, int y, int z, Bundle extras, boolean sync) {
        }

        public void dispatchWallpaperOffsets(float x, float y, float xStep, float yStep, float zoom, boolean sync) {
        }

        public void dispatchWindowShown() {
        }

        public void executeCommand(String command, String parameters, ParcelFileDescriptor out) {
        }

        public void hideInsets(int types, boolean fromIme) {
        }

        public void insetsChanged(InsetsState insetsState) {
        }

        public void insetsControlChanged(InsetsState insetsState, InsetsSourceControl[] activeControls) {
        }

        public void locationInParentDisplayChanged(Point offset) {
        }

        public void moved(int newX, int newY) {
        }

        public void requestAppKeyboardShortcuts(IResultReceiver receiver, int deviceId) {
        }

        public void resized(Rect frame, Rect contentInsets, Rect visibleInsets, Rect stableInsets, boolean reportDraw, MergedConfiguration newMergedConfiguration, Rect backDropFrame, boolean forceLayout, boolean alwaysConsumeSystemBars, int displayId, DisplayCutout.ParcelableWrapper displayCutout) {
        }

        public void showInsets(int types, boolean fromIme) {
        }

        public void updatePointerIcon(float x, float y) {
        }

        public void windowFocusChanged(boolean hasFocus, boolean inTouchMode) {
        }

        ContainerWindow() {
        }

        public void requestScrollCapture(IScrollCaptureController controller) {
            try {
                controller.onClientUnavailable();
            } catch (RemoteException unused) {
            }
        }
    }
}
