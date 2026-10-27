package android.window;

import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.ActivityTaskManager;
import android.app.TaskStackListener;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Insets;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Region;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.hardware.input.InputManager;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.IWindowManager;
import android.view.KeyEvent;
import android.view.WindowManagerGlobal;
import android.view.inputmethod.InputMethodManager;
import android.window.TaskEmbedder;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class VirtualDisplayTaskEmbedder extends TaskEmbedder {
    private static final String DISPLAY_NAME = "TaskVirtualDisplay";
    private static final String TAG = "VirDispTaskEmbedder";
    private int mDisplayDensityDpi;
    private Insets mForwardedInsets;
    private final boolean mSingleTaskInstance;
    private TaskStackListener mTaskStackListener;
    private DisplayMetrics mTmpDisplayMetrics;
    private final boolean mUsePublicVirtualDisplay;
    private final boolean mUseTrustedDisplay;
    private VirtualDisplay mVirtualDisplay;

    public VirtualDisplayTaskEmbedder(Context context, TaskEmbedder.Host host, boolean z, boolean z2, boolean z3) {
        super(context, host);
        this.mSingleTaskInstance = z;
        this.mUsePublicVirtualDisplay = z2;
        this.mUseTrustedDisplay = z3;
    }

    @Override // android.window.TaskEmbedder
    public boolean isInitialized() {
        return this.mVirtualDisplay != null;
    }

    @Override // android.window.TaskEmbedder
    public boolean onInitialize() {
        DisplayManager displayManager = (DisplayManager) this.mContext.getSystemService(DisplayManager.class);
        this.mDisplayDensityDpi = getBaseDisplayDensity();
        int i = this.mUsePublicVirtualDisplay ? 265 : 264;
        if (this.mUseTrustedDisplay) {
            i |= 1024;
        }
        VirtualDisplay virtualDisplayCreateVirtualDisplay = displayManager.createVirtualDisplay("TaskVirtualDisplay@" + System.identityHashCode(this), this.mHost.getWidth(), this.mHost.getHeight(), this.mDisplayDensityDpi, null, i);
        this.mVirtualDisplay = virtualDisplayCreateVirtualDisplay;
        if (virtualDisplayCreateVirtualDisplay == null) {
            Log.e(TAG, "Failed to initialize TaskEmbedder");
            return false;
        }
        try {
            int displayId = getDisplayId();
            IWindowManager windowManagerService = WindowManagerGlobal.getWindowManagerService();
            WindowManagerGlobal.getWindowSession().reparentDisplayContent(this.mHost.getWindow(), this.mSurfaceControl, displayId);
            windowManagerService.dontOverrideDisplayInfo(displayId);
            if (this.mSingleTaskInstance) {
                ((ActivityTaskManager) this.mContext.getSystemService(ActivityTaskManager.class)).setDisplayToSingleTaskInstance(displayId);
            }
            setForwardedInsets(this.mForwardedInsets);
            this.mTaskStackListener = new TaskStackListenerImpl();
            this.mActivityTaskManager.registerTaskStackListener(this.mTaskStackListener);
        } catch (RemoteException e) {
            e.rethrowAsRuntimeException();
        }
        return super.onInitialize();
    }

    @Override // android.window.TaskEmbedder
    protected boolean onRelease() {
        super.onRelease();
        clearActivityViewGeometryForIme();
        if (this.mTaskStackListener != null) {
            try {
                this.mActivityTaskManager.unregisterTaskStackListener(this.mTaskStackListener);
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to unregister task stack listener", e);
            }
            this.mTaskStackListener = null;
        }
        if (!isInitialized()) {
            return false;
        }
        this.mVirtualDisplay.release();
        this.mVirtualDisplay = null;
        return true;
    }

    @Override // android.window.TaskEmbedder
    public void start() {
        super.start();
        if (isInitialized()) {
            this.mVirtualDisplay.setDisplayState(true);
        }
    }

    @Override // android.window.TaskEmbedder
    public void stop() {
        super.stop();
        if (isInitialized()) {
            this.mVirtualDisplay.setDisplayState(false);
            clearActivityViewGeometryForIme();
        }
    }

    @Override // android.window.TaskEmbedder
    public void resizeTask(int i, int i2) {
        this.mDisplayDensityDpi = getBaseDisplayDensity();
        if (isInitialized()) {
            this.mVirtualDisplay.resize(i, i2, this.mDisplayDensityDpi);
        }
    }

    @Override // android.window.TaskEmbedder
    public void performBackPress() {
        if (isInitialized()) {
            int displayId = this.mVirtualDisplay.getDisplay().getDisplayId();
            InputManager inputManager = InputManager.getInstance();
            inputManager.injectInputEvent(createKeyEvent(0, 4, displayId), 0);
            inputManager.injectInputEvent(createKeyEvent(1, 4, displayId), 0);
        }
    }

    @Override // android.window.TaskEmbedder
    public boolean gatherTransparentRegion(Region region) {
        notifyBoundsChanged();
        return super.gatherTransparentRegion(region);
    }

    @Override // android.window.TaskEmbedder
    public int getId() {
        return getDisplayId();
    }

    @Override // android.window.TaskEmbedder
    public int getDisplayId() {
        if (isInitialized()) {
            return this.mVirtualDisplay.getDisplay().getDisplayId();
        }
        return -1;
    }

    @Override // android.window.TaskEmbedder
    public VirtualDisplay getVirtualDisplay() {
        if (isInitialized()) {
            return this.mVirtualDisplay;
        }
        return null;
    }

    @Override // android.window.TaskEmbedder
    protected ActivityOptions prepareActivityOptions(ActivityOptions activityOptions) {
        ActivityOptions activityOptionsPrepareActivityOptions = super.prepareActivityOptions(activityOptions);
        activityOptionsPrepareActivityOptions.setLaunchDisplayId(getDisplayId());
        return activityOptionsPrepareActivityOptions;
    }

    @Override // android.window.TaskEmbedder
    public void setForwardedInsets(Insets insets) {
        this.mForwardedInsets = insets;
        if (isInitialized()) {
            try {
                WindowManagerGlobal.getWindowManagerService().setForwardedInsets(getDisplayId(), this.mForwardedInsets);
            } catch (RemoteException e) {
                e.rethrowAsRuntimeException();
            }
        }
    }

    @Override // android.window.TaskEmbedder
    protected void updateLocationAndTapExcludeRegion() {
        super.updateLocationAndTapExcludeRegion();
        if (!isInitialized() || this.mHost.getWindow() == null) {
            return;
        }
        reportLocation(this.mHost.getScreenToTaskMatrix(), this.mHost.getPositionInWindow());
    }

    private void reportLocation(Matrix matrix, Point point) {
        try {
            int displayId = getDisplayId();
            ((InputMethodManager) this.mContext.getSystemService(InputMethodManager.class)).reportActivityView(displayId, matrix);
            WindowManagerGlobal.getWindowSession().updateDisplayContentLocation(this.mHost.getWindow(), point.x, point.y, displayId);
        } catch (RemoteException e) {
            e.rethrowAsRuntimeException();
        }
    }

    private void clearActivityViewGeometryForIme() {
        ((InputMethodManager) this.mContext.getSystemService(InputMethodManager.class)).reportActivityView(getDisplayId(), null);
    }

    private static KeyEvent createKeyEvent(int i, int i2, int i3) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        KeyEvent keyEvent = new KeyEvent(jUptimeMillis, jUptimeMillis, i, i2, 0, 0, -1, 0, 72, 257);
        keyEvent.setDisplayId(i3);
        return keyEvent;
    }

    private int getBaseDisplayDensity() {
        return this.mContext.getResources().getConfiguration().densityDpi;
    }

    /* JADX INFO: Access modifiers changed from: private */
    class TaskStackListenerImpl extends TaskStackListener {
        private TaskStackListenerImpl() {
        }

        @Override // android.app.TaskStackListener, android.app.ITaskStackListener
        public void onTaskDescriptionChanged(final ActivityManager.RunningTaskInfo runningTaskInfo) throws RemoteException {
            ActivityManager.StackInfo topMostStackInfo;
            if (VirtualDisplayTaskEmbedder.this.isInitialized() && runningTaskInfo.displayId == VirtualDisplayTaskEmbedder.this.getDisplayId() && (topMostStackInfo = getTopMostStackInfo()) != null && runningTaskInfo.taskId == topMostStackInfo.taskIds[topMostStackInfo.taskIds.length - 1]) {
                VirtualDisplayTaskEmbedder.this.mHost.post(new Runnable() { // from class: android.window.-$$Lambda$VirtualDisplayTaskEmbedder$TaskStackListenerImpl$T5XvoM0ShYGPCBOY7qLgIslsK9g
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onTaskDescriptionChanged$0$VirtualDisplayTaskEmbedder$TaskStackListenerImpl(runningTaskInfo);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onTaskDescriptionChanged$0$VirtualDisplayTaskEmbedder$TaskStackListenerImpl(ActivityManager.RunningTaskInfo runningTaskInfo) {
            VirtualDisplayTaskEmbedder.this.mHost.onTaskBackgroundColorChanged(VirtualDisplayTaskEmbedder.this, runningTaskInfo.taskDescription.getBackgroundColor());
        }

        @Override // android.app.TaskStackListener, android.app.ITaskStackListener
        public void onTaskMovedToFront(ActivityManager.RunningTaskInfo runningTaskInfo) throws RemoteException {
            ActivityManager.StackInfo topMostStackInfo;
            if (VirtualDisplayTaskEmbedder.this.isInitialized() && VirtualDisplayTaskEmbedder.this.mListener != null && runningTaskInfo.displayId == VirtualDisplayTaskEmbedder.this.getDisplayId() && (topMostStackInfo = getTopMostStackInfo()) != null && runningTaskInfo.taskId == topMostStackInfo.taskIds[topMostStackInfo.taskIds.length - 1]) {
                VirtualDisplayTaskEmbedder.this.mListener.onTaskMovedToFront(runningTaskInfo.taskId);
            }
        }

        @Override // android.app.TaskStackListener, android.app.ITaskStackListener
        public void onTaskCreated(int i, ComponentName componentName) throws RemoteException {
            ActivityManager.StackInfo topMostStackInfo;
            if (VirtualDisplayTaskEmbedder.this.mListener == null || !VirtualDisplayTaskEmbedder.this.isInitialized() || (topMostStackInfo = getTopMostStackInfo()) == null || i != topMostStackInfo.taskIds[topMostStackInfo.taskIds.length - 1]) {
                return;
            }
            VirtualDisplayTaskEmbedder.this.mListener.onTaskCreated(i, componentName);
        }

        @Override // android.app.TaskStackListener, android.app.ITaskStackListener
        public void onTaskRemovalStarted(ActivityManager.RunningTaskInfo runningTaskInfo) throws RemoteException {
            if (VirtualDisplayTaskEmbedder.this.mListener != null && VirtualDisplayTaskEmbedder.this.isInitialized() && runningTaskInfo.displayId == VirtualDisplayTaskEmbedder.this.getDisplayId()) {
                VirtualDisplayTaskEmbedder.this.mListener.onTaskRemovalStarted(runningTaskInfo.taskId);
            }
        }

        private ActivityManager.StackInfo getTopMostStackInfo() throws RemoteException {
            List<ActivityManager.StackInfo> allStackInfosOnDisplay = VirtualDisplayTaskEmbedder.this.mActivityTaskManager.getAllStackInfosOnDisplay(VirtualDisplayTaskEmbedder.this.getDisplayId());
            if (allStackInfosOnDisplay.isEmpty()) {
                return null;
            }
            return allStackInfosOnDisplay.get(0);
        }
    }
}
