package android.view;

import android.content.ClipData;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.util.MergedConfiguration;
import android.view.DisplayCutout;
import android.view.SurfaceControl;
import android.view.WindowManager;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class WindowlessWindowManager implements IWindowSession {
    private static final String TAG = "WindowlessWindowManager";
    private final Configuration mConfiguration;
    private final IBinder mHostInputToken;
    private final SurfaceControl mRootSurface;
    final HashMap<IBinder, State> mStateForWindow = new HashMap<>();
    final HashMap<IBinder, ResizeCompleteCallback> mResizeCompletionForWindow = new HashMap<>();
    private final SurfaceSession mSurfaceSession = new SurfaceSession();
    private int mForceHeight = -1;
    private int mForceWidth = -1;
    private final IWindowSession mRealWm = WindowManagerGlobal.getWindowSession();

    public interface ResizeCompleteCallback {
        void finished(SurfaceControl.Transaction transaction);
    }

    @Override // android.view.IWindowSession
    public int addToDisplayWithoutInputChannel(IWindow iWindow, int i, WindowManager.LayoutParams layoutParams, int i2, int i3, Rect rect, Rect rect2, InsetsState insetsState) {
        return 0;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return null;
    }

    @Override // android.view.IWindowSession
    public void cancelDragAndDrop(IBinder iBinder, boolean z) {
    }

    @Override // android.view.IWindowSession
    public void dragRecipientEntered(IWindow iWindow) {
    }

    @Override // android.view.IWindowSession
    public void dragRecipientExited(IWindow iWindow) {
    }

    @Override // android.view.IWindowSession
    public void finishMovingTask(IWindow iWindow) {
    }

    @Override // android.view.IWindowSession
    public void getDisplayFrame(IWindow iWindow, Rect rect) {
    }

    @Override // android.view.IWindowSession
    public boolean getInTouchMode() {
        return false;
    }

    @Override // android.view.IWindowSession
    public IWindowId getWindowId(IBinder iBinder) {
        return null;
    }

    @Override // android.view.IWindowSession
    public void grantInputChannel(int i, SurfaceControl surfaceControl, IWindow iWindow, IBinder iBinder, int i2, int i3, InputChannel inputChannel) {
    }

    @Override // android.view.IWindowSession
    public void insetsModified(IWindow iWindow, InsetsState insetsState) {
    }

    @Override // android.view.IWindowSession
    public void onRectangleOnScreenRequested(IBinder iBinder, Rect rect) {
    }

    @Override // android.view.IWindowSession
    public boolean outOfMemory(IWindow iWindow) {
        return false;
    }

    @Override // android.view.IWindowSession
    public IBinder performDrag(IWindow iWindow, int i, SurfaceControl surfaceControl, int i2, float f, float f2, float f3, float f4, ClipData clipData) {
        return null;
    }

    @Override // android.view.IWindowSession
    public boolean performHapticFeedback(int i, boolean z) {
        return false;
    }

    @Override // android.view.IWindowSession
    public void pokeDrawLock(IBinder iBinder) {
    }

    @Override // android.view.IWindowSession
    public void prepareToReplaceWindows(IBinder iBinder, boolean z) {
    }

    @Override // android.view.IWindowSession
    public void reparentDisplayContent(IWindow iWindow, SurfaceControl surfaceControl, int i) {
    }

    @Override // android.view.IWindowSession
    public void reportDropResult(IWindow iWindow, boolean z) {
    }

    @Override // android.view.IWindowSession
    public void reportSystemGestureExclusionChanged(IWindow iWindow, List<Rect> list) {
    }

    @Override // android.view.IWindowSession
    public Bundle sendWallpaperCommand(IBinder iBinder, String str, int i, int i2, int i3, Bundle bundle, boolean z) {
        return null;
    }

    @Override // android.view.IWindowSession
    public void setInTouchMode(boolean z) {
    }

    @Override // android.view.IWindowSession
    public void setInsets(IWindow iWindow, int i, Rect rect, Rect rect2, Region region) {
    }

    @Override // android.view.IWindowSession
    public void setShouldZoomOutWallpaper(IBinder iBinder, boolean z) {
    }

    @Override // android.view.IWindowSession
    public void setTransparentRegion(IWindow iWindow, Region region) {
    }

    @Override // android.view.IWindowSession
    public void setWallpaperDisplayOffset(IBinder iBinder, int i, int i2) {
    }

    @Override // android.view.IWindowSession
    public void setWallpaperPosition(IBinder iBinder, float f, float f2, float f3, float f4) {
    }

    @Override // android.view.IWindowSession
    public void setWallpaperZoomOut(IBinder iBinder, float f) {
    }

    @Override // android.view.IWindowSession
    public boolean startMovingTask(IWindow iWindow, float f, float f2) {
        return false;
    }

    @Override // android.view.IWindowSession
    public void updateDisplayContentLocation(IWindow iWindow, int i, int i2, int i3) {
    }

    @Override // android.view.IWindowSession
    public void updateInputChannel(IBinder iBinder, int i, SurfaceControl surfaceControl, int i2, Region region) {
    }

    @Override // android.view.IWindowSession
    public void updatePointerIcon(IWindow iWindow) {
    }

    @Override // android.view.IWindowSession
    public void updateTapExcludeRegion(IWindow iWindow, Region region) {
    }

    @Override // android.view.IWindowSession
    public void wallpaperCommandComplete(IBinder iBinder, Bundle bundle) {
    }

    @Override // android.view.IWindowSession
    public void wallpaperOffsetsComplete(IBinder iBinder) {
    }

    private class State {
        int mDisplayId;
        IBinder mInputChannelToken;
        Region mInputRegion;
        WindowManager.LayoutParams mParams;
        SurfaceControl mSurfaceControl;

        State(SurfaceControl surfaceControl, WindowManager.LayoutParams layoutParams, int i, IBinder iBinder) {
            WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams();
            this.mParams = layoutParams2;
            this.mSurfaceControl = surfaceControl;
            layoutParams2.copyFrom(layoutParams);
            this.mDisplayId = i;
            this.mInputChannelToken = iBinder;
        }
    }

    public WindowlessWindowManager(Configuration configuration, SurfaceControl surfaceControl, IBinder iBinder) {
        this.mRootSurface = surfaceControl;
        this.mConfiguration = new Configuration(configuration);
        this.mHostInputToken = iBinder;
    }

    protected void setConfiguration(Configuration configuration) {
        this.mConfiguration.setTo(configuration);
    }

    void setCompletionCallback(IBinder iBinder, ResizeCompleteCallback resizeCompleteCallback) {
        if (this.mResizeCompletionForWindow.get(iBinder) != null) {
            Log.w(TAG, "Unsupported overlapping resizes");
        }
        this.mResizeCompletionForWindow.put(iBinder, resizeCompleteCallback);
    }

    protected void setTouchRegion(IBinder iBinder, Region region) {
        synchronized (this) {
            State state = this.mStateForWindow.get(iBinder);
            if (state == null) {
                return;
            }
            if (Objects.equals(region, state.mInputRegion)) {
                return;
            }
            state.mInputRegion = region != null ? new Region(region) : null;
            if (state.mInputChannelToken != null) {
                try {
                    this.mRealWm.updateInputChannel(state.mInputChannelToken, state.mDisplayId, state.mSurfaceControl, state.mParams.flags, state.mInputRegion);
                } catch (RemoteException e) {
                    Log.e(TAG, "Failed to update surface input channel: ", e);
                }
            }
        }
    }

    @Override // android.view.IWindowSession
    public int addToDisplay(IWindow iWindow, int i, WindowManager.LayoutParams layoutParams, int i2, int i3, Rect rect, Rect rect2, Rect rect3, DisplayCutout.ParcelableWrapper parcelableWrapper, InputChannel inputChannel, InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr) {
        SurfaceControl surfaceControlBuild = new SurfaceControl.Builder(this.mSurfaceSession).setParent(this.mRootSurface).setFormat(layoutParams.format).setBufferSize(getSurfaceWidth(layoutParams), getSurfaceHeight(layoutParams)).setName(layoutParams.getTitle().toString()).setCallsite("WindowlessWindowManager.addToDisplay").build();
        if ((layoutParams.inputFeatures & 2) == 0) {
            try {
                this.mRealWm.grantInputChannel(i3, surfaceControlBuild, iWindow, this.mHostInputToken, layoutParams.flags, layoutParams.type, inputChannel);
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to grant input to surface: ", e);
            }
        }
        State state = new State(surfaceControlBuild, layoutParams, i3, inputChannel != null ? inputChannel.getToken() : null);
        synchronized (this) {
            this.mStateForWindow.put(iWindow.asBinder(), state);
        }
        return isInTouchMode() ? 3 : 2;
    }

    @Override // android.view.IWindowSession
    public int addToDisplayAsUser(IWindow iWindow, int i, WindowManager.LayoutParams layoutParams, int i2, int i3, int i4, Rect rect, Rect rect2, Rect rect3, DisplayCutout.ParcelableWrapper parcelableWrapper, InputChannel inputChannel, InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr) {
        return addToDisplay(iWindow, i, layoutParams, i2, i3, rect, rect2, rect3, parcelableWrapper, inputChannel, insetsState, insetsSourceControlArr);
    }

    @Override // android.view.IWindowSession
    public void remove(IWindow iWindow) throws RemoteException {
        State stateRemove;
        this.mRealWm.remove(iWindow);
        synchronized (this) {
            stateRemove = this.mStateForWindow.remove(iWindow.asBinder());
        }
        if (stateRemove == null) {
            throw new IllegalArgumentException("Invalid window token (never added or removed already)");
        }
        SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
        try {
            transaction.remove(stateRemove.mSurfaceControl).apply();
            transaction.close();
        } catch (Throwable th) {
            try {
                transaction.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private boolean isOpaque(WindowManager.LayoutParams layoutParams) {
        if ((layoutParams.surfaceInsets == null || layoutParams.surfaceInsets.left == 0) && layoutParams.surfaceInsets.top == 0 && layoutParams.surfaceInsets.right == 0 && layoutParams.surfaceInsets.bottom == 0) {
            return !PixelFormat.formatHasAlpha(layoutParams.format);
        }
        return false;
    }

    private boolean isInTouchMode() {
        try {
            return WindowManagerGlobal.getWindowSession().getInTouchMode();
        } catch (RemoteException e) {
            Log.e(TAG, "Unable to check if the window is in touch mode", e);
            return false;
        }
    }

    protected SurfaceControl getSurfaceControl(View view) {
        State state;
        ViewRootImpl viewRootImpl = view.getViewRootImpl();
        if (viewRootImpl == null || (state = this.mStateForWindow.get(viewRootImpl.mWindow.asBinder())) == null) {
            return null;
        }
        return state.mSurfaceControl;
    }

    @Override // android.view.IWindowSession
    public int relayout(IWindow iWindow, int i, WindowManager.LayoutParams layoutParams, int i2, int i3, int i4, int i5, long j, Rect rect, Rect rect2, Rect rect3, Rect rect4, Rect rect5, DisplayCutout.ParcelableWrapper parcelableWrapper, MergedConfiguration mergedConfiguration, SurfaceControl surfaceControl, InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr, Point point, SurfaceControl surfaceControl2) {
        State state;
        synchronized (this) {
            state = this.mStateForWindow.get(iWindow.asBinder());
        }
        if (state == null) {
            throw new IllegalArgumentException("Invalid window token (never added or removed already)");
        }
        SurfaceControl surfaceControl3 = state.mSurfaceControl;
        SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
        int iCopyFrom = layoutParams != null ? state.mParams.copyFrom(layoutParams) : 0;
        WindowManager.LayoutParams layoutParams2 = state.mParams;
        if (i4 == 0) {
            transaction.setBufferSize(surfaceControl3, getSurfaceWidth(layoutParams2), getSurfaceHeight(layoutParams2)).setOpaque(surfaceControl3, isOpaque(layoutParams2)).show(surfaceControl3).apply();
            surfaceControl.copyFrom(surfaceControl3, "WindowlessWindowManager.relayout");
        } else {
            transaction.hide(surfaceControl3).apply();
            surfaceControl.release();
        }
        rect.set(0, 0, layoutParams2.width, layoutParams2.height);
        Configuration configuration = this.mConfiguration;
        mergedConfiguration.setConfiguration(configuration, configuration);
        if ((iCopyFrom & 4) != 0 && state.mInputChannelToken != null) {
            try {
                this.mRealWm.updateInputChannel(state.mInputChannelToken, state.mDisplayId, surfaceControl3, layoutParams2.flags, state.mInputRegion);
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to update surface input channel: ", e);
            }
        }
        return isInTouchMode() ? 1 : 0;
    }

    @Override // android.view.IWindowSession
    public void finishDrawing(IWindow iWindow, SurfaceControl.Transaction transaction) {
        synchronized (this) {
            ResizeCompleteCallback resizeCompleteCallback = this.mResizeCompletionForWindow.get(iWindow.asBinder());
            if (resizeCompleteCallback == null) {
                transaction.apply();
            } else {
                resizeCompleteCallback.finished(transaction);
                this.mResizeCompletionForWindow.remove(iWindow.asBinder());
            }
        }
    }

    private int getSurfaceWidth(WindowManager.LayoutParams layoutParams) {
        Rect rect = layoutParams.surfaceInsets;
        return rect != null ? layoutParams.width + rect.left + rect.right : layoutParams.width;
    }

    private int getSurfaceHeight(WindowManager.LayoutParams layoutParams) {
        Rect rect = layoutParams.surfaceInsets;
        return rect != null ? layoutParams.height + rect.top + rect.bottom : layoutParams.height;
    }
}
