package android.window;

import android.app.ActivityOptions;
import android.app.ActivityTaskManager;
import android.app.IActivityTaskManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.content.pm.ShortcutInfo;
import android.graphics.Insets;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.hardware.display.VirtualDisplay;
import android.os.RemoteException;
import android.os.UserHandle;
import android.view.IWindow;
import android.view.SurfaceControl;
import android.view.WindowManagerGlobal;
import dalvik.system.CloseGuard;

/* JADX INFO: loaded from: classes3.dex */
public abstract class TaskEmbedder {
    private static final String TAG = "TaskEmbedder";
    protected final Context mContext;
    protected Host mHost;
    protected Listener mListener;
    protected boolean mOpened;
    protected SurfaceControl mSurfaceControl;
    protected SurfaceControl.Transaction mTransaction;
    protected IActivityTaskManager mActivityTaskManager = ActivityTaskManager.getService();
    private final CloseGuard mGuard = CloseGuard.get();

    public interface Host {
        boolean canReceivePointerEvents();

        int getHeight();

        Point getPositionInWindow();

        Rect getScreenBounds();

        Matrix getScreenToTaskMatrix();

        Region getTapExcludeRegion();

        int getWidth();

        IWindow getWindow();

        void onTaskBackgroundColorChanged(TaskEmbedder taskEmbedder, int i);

        boolean post(Runnable runnable);
    }

    public interface Listener {
        default void onBackPressedOnTaskRoot(int i) {
        }

        default void onInitialized() {
        }

        default void onReleased() {
        }

        default void onTaskCreated(int i, ComponentName componentName) {
        }

        default void onTaskMovedToFront(int i) {
        }

        default void onTaskRemovalStarted(int i) {
        }

        default void onTaskVisibilityChanged(int i, boolean z) {
        }
    }

    public boolean gatherTransparentRegion(Region region) {
        return false;
    }

    public int getDisplayId() {
        return -1;
    }

    public abstract int getId();

    public VirtualDisplay getVirtualDisplay() {
        return null;
    }

    public abstract boolean isInitialized();

    public abstract void performBackPress();

    public void resizeTask(int i, int i2) {
    }

    public void setForwardedInsets(Insets insets) {
    }

    public TaskEmbedder(Context context, Host host) {
        this.mContext = context;
        this.mHost = host;
    }

    public boolean initialize(SurfaceControl surfaceControl) {
        if (isInitialized()) {
            throw new IllegalStateException("Trying to initialize for the second time.");
        }
        this.mTransaction = new SurfaceControl.Transaction();
        this.mSurfaceControl = new SurfaceControl.Builder().setContainerLayer().setParent(surfaceControl).setName("TaskEmbedder - " + Integer.toHexString(System.identityHashCode(this))).setCallsite("TaskEmbedder.initialize").build();
        if (!onInitialize()) {
            return false;
        }
        if (this.mListener != null && isInitialized()) {
            this.mListener.onInitialized();
        }
        this.mOpened = true;
        this.mGuard.open("release");
        this.mTransaction.show(getSurfaceControl()).apply();
        return true;
    }

    public boolean onInitialize() {
        updateLocationAndTapExcludeRegion();
        return true;
    }

    protected boolean onRelease() {
        clearTapExcludeRegion();
        return true;
    }

    public void start() {
        updateLocationAndTapExcludeRegion();
    }

    public void stop() {
        clearTapExcludeRegion();
    }

    public void notifyBoundsChanged() {
        updateLocationAndTapExcludeRegion();
    }

    public SurfaceControl getSurfaceControl() {
        return this.mSurfaceControl;
    }

    protected void updateLocationAndTapExcludeRegion() {
        if (!isInitialized() || this.mHost.getWindow() == null) {
            return;
        }
        applyTapExcludeRegion(this.mHost.getWindow(), this.mHost.getTapExcludeRegion());
    }

    private void applyTapExcludeRegion(IWindow iWindow, Region region) {
        try {
            WindowManagerGlobal.getWindowSession().updateTapExcludeRegion(iWindow, region);
        } catch (RemoteException e) {
            e.rethrowAsRuntimeException();
        }
    }

    private void clearTapExcludeRegion() {
        if (!isInitialized() || this.mHost.getWindow() == null) {
            return;
        }
        applyTapExcludeRegion(this.mHost.getWindow(), null);
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
        if (listener == null || !isInitialized()) {
            return;
        }
        this.mListener.onInitialized();
    }

    public void startActivity(Intent intent) {
        this.mContext.startActivity(intent, prepareActivityOptions(null).toBundle());
    }

    public void startActivity(Intent intent, UserHandle userHandle) {
        this.mContext.startActivityAsUser(intent, prepareActivityOptions(null).toBundle(), userHandle);
    }

    public void startActivity(PendingIntent pendingIntent) {
        try {
            pendingIntent.send(null, 0, null, null, null, null, prepareActivityOptions(null).toBundle());
        } catch (PendingIntent.CanceledException e) {
            throw new RuntimeException(e);
        }
    }

    public void startActivity(PendingIntent pendingIntent, Intent intent, ActivityOptions activityOptions) {
        prepareActivityOptions(activityOptions);
        try {
            pendingIntent.send(this.mContext, 0, intent, null, null, null, activityOptions.toBundle());
        } catch (PendingIntent.CanceledException e) {
            throw new RuntimeException(e);
        }
    }

    public void startShortcutActivity(ShortcutInfo shortcutInfo, ActivityOptions activityOptions, Rect rect) {
        LauncherApps launcherApps = (LauncherApps) this.mContext.getSystemService(Context.LAUNCHER_APPS_SERVICE);
        prepareActivityOptions(activityOptions);
        launcherApps.startShortcut(shortcutInfo, rect, activityOptions.toBundle());
    }

    protected ActivityOptions prepareActivityOptions(ActivityOptions activityOptions) {
        if (isInitialized()) {
            return activityOptions == null ? ActivityOptions.makeBasic() : activityOptions;
        }
        throw new IllegalStateException("Trying to start activity before ActivityView is ready.");
    }

    public void release() {
        if (!isInitialized()) {
            throw new IllegalStateException("Trying to release container that is not initialized.");
        }
        performRelease();
    }

    private boolean performRelease() {
        if (!this.mOpened) {
            return false;
        }
        this.mTransaction.reparent(this.mSurfaceControl, null).apply();
        this.mSurfaceControl.release();
        boolean zOnRelease = onRelease();
        Listener listener = this.mListener;
        if (listener != null && zOnRelease) {
            listener.onReleased();
        }
        this.mOpened = false;
        this.mGuard.close();
        return true;
    }

    protected void finalize() throws Throwable {
        try {
            if (this.mGuard != null) {
                this.mGuard.warnIfOpen();
                performRelease();
            }
        } finally {
            super.finalize();
        }
    }
}
