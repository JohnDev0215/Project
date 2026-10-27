package android.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.graphics.Insets;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.hardware.display.VirtualDisplay;
import android.os.UserHandle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DisplayInfo;
import android.view.IWindow;
import android.view.SurfaceControl;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.window.TaskEmbedder;
import android.window.TaskOrganizerTaskEmbedder;
import android.window.VirtualDisplayTaskEmbedder;
import dalvik.system.CloseGuard;

/* JADX INFO: loaded from: classes.dex */
public class ActivityView extends ViewGroup implements TaskEmbedder.Host {
    private static final String TAG = "ActivityView";
    private final CloseGuard mGuard;
    private boolean mOpened;
    private final Matrix mScreenSurfaceMatrix;
    private final SurfaceCallback mSurfaceCallback;
    private final SurfaceView mSurfaceView;
    private final Region mTapExcludeRegion;
    private TaskEmbedder mTaskEmbedder;
    private final int[] mTmpArray;
    private final Rect mTmpRect;
    private final SurfaceControl.Transaction mTmpTransaction;
    private final Point mWindowPosition;

    public static abstract class StateCallback {
        public abstract void onActivityViewDestroyed(ActivityView activityView);

        public abstract void onActivityViewReady(ActivityView activityView);

        public void onBackPressedOnTaskRoot(int i) {
        }

        public void onTaskCreated(int i, ComponentName componentName) {
        }

        public void onTaskMovedToFront(int i) {
        }

        public void onTaskRemovalStarted(int i) {
        }

        public void onTaskVisibilityChanged(int i, boolean z) {
        }
    }

    protected boolean useTaskOrganizer() {
        return false;
    }

    public ActivityView(Context context) {
        this(context, null);
    }

    public ActivityView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActivityView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, false);
    }

    public ActivityView(Context context, AttributeSet attributeSet, int i, boolean z) {
        this(context, attributeSet, i, z, false);
    }

    public ActivityView(Context context, AttributeSet attributeSet, int i, boolean z, boolean z2) {
        this(context, attributeSet, i, z, z2, false);
    }

    public ActivityView(Context context, AttributeSet attributeSet, int i, boolean z, boolean z2, boolean z3) {
        this(context, attributeSet, i, z, z2, z3, false);
    }

    public ActivityView(Context context, AttributeSet attributeSet, int i, boolean z, boolean z2, boolean z3, boolean z4) {
        super(context, attributeSet, i);
        this.mGuard = CloseGuard.get();
        this.mTmpTransaction = new SurfaceControl.Transaction();
        this.mWindowPosition = new Point();
        this.mTmpArray = new int[2];
        this.mTmpRect = new Rect();
        this.mScreenSurfaceMatrix = new Matrix();
        this.mTapExcludeRegion = new Region();
        if (useTaskOrganizer()) {
            this.mTaskEmbedder = new TaskOrganizerTaskEmbedder(context, this);
        } else {
            this.mTaskEmbedder = new VirtualDisplayTaskEmbedder(context, this, z, z2, z4);
        }
        SurfaceView surfaceView = new SurfaceView(context, null, 0, 0, z3);
        this.mSurfaceView = surfaceView;
        surfaceView.setAlpha(super.getAlpha());
        this.mSurfaceView.setUseAlpha();
        this.mSurfaceCallback = new SurfaceCallback();
        this.mSurfaceView.getHolder().addCallback(this.mSurfaceCallback);
        addView(this.mSurfaceView);
        this.mOpened = true;
        this.mGuard.open("release");
    }

    public void setCallback(StateCallback stateCallback) {
        if (stateCallback == null) {
            this.mTaskEmbedder.setListener(null);
        } else {
            this.mTaskEmbedder.setListener(new StateCallbackAdapter(stateCallback));
        }
    }

    public void setCornerRadius(float f) {
        this.mSurfaceView.setCornerRadius(f);
    }

    public float getCornerRadius() {
        return this.mSurfaceView.getCornerRadius();
    }

    public void setSurfaceClippingEnabled(boolean z) {
        this.mSurfaceView.setEnableSurfaceClipping(z);
    }

    public void setSurfaceClipBounds(Rect rect) {
        this.mSurfaceView.setClipBounds(rect);
    }

    public boolean getSurfaceClipBounds(Rect rect) {
        return this.mSurfaceView.getClipBounds(rect);
    }

    public void startShortcutActivity(ShortcutInfo shortcutInfo, ActivityOptions activityOptions, Rect rect) {
        this.mTaskEmbedder.startShortcutActivity(shortcutInfo, activityOptions, rect);
    }

    public void startActivity(Intent intent) {
        this.mTaskEmbedder.startActivity(intent);
    }

    public void startActivity(Intent intent, UserHandle userHandle) {
        this.mTaskEmbedder.startActivity(intent, userHandle);
    }

    public void startActivity(PendingIntent pendingIntent) {
        this.mTaskEmbedder.startActivity(pendingIntent);
    }

    public void startActivity(PendingIntent pendingIntent, Intent intent, ActivityOptions activityOptions) {
        this.mTaskEmbedder.startActivity(pendingIntent, intent, activityOptions);
    }

    public void release() {
        performRelease();
    }

    public void onLocationChanged() {
        this.mTaskEmbedder.notifyBoundsChanged();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.mSurfaceView.layout(0, 0, i3 - i, i4 - i2);
    }

    @Override // android.view.View
    public void setAlpha(float f) {
        super.setAlpha(f);
        SurfaceView surfaceView = this.mSurfaceView;
        if (surfaceView != null) {
            surfaceView.setAlpha(f);
        }
    }

    @Override // android.view.View
    public float getAlpha() {
        return this.mSurfaceView.getAlpha();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean gatherTransparentRegion(Region region) {
        return this.mTaskEmbedder.gatherTransparentRegion(region) || super.gatherTransparentRegion(region);
    }

    private class SurfaceCallback implements SurfaceHolder.Callback {
        private final DisplayInfo mTempDisplayInfo;
        private final DisplayMetrics mTempMetrics;

        private SurfaceCallback() {
            this.mTempDisplayInfo = new DisplayInfo();
            this.mTempMetrics = new DisplayMetrics();
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            if (!ActivityView.this.mTaskEmbedder.isInitialized()) {
                ActivityView activityView = ActivityView.this;
                activityView.initTaskEmbedder(activityView.mSurfaceView.getSurfaceControl());
            } else {
                ActivityView.this.mTmpTransaction.reparent(ActivityView.this.mTaskEmbedder.getSurfaceControl(), ActivityView.this.mSurfaceView.getSurfaceControl()).apply();
            }
            ActivityView.this.mTaskEmbedder.resizeTask(ActivityView.this.getWidth(), ActivityView.this.getHeight());
            ActivityView.this.mTaskEmbedder.start();
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            if (ActivityView.this.getVirtualDisplay().getDisplay().getDisplayInfo(this.mTempDisplayInfo)) {
                this.mTempDisplayInfo.getAppMetrics(this.mTempMetrics);
                if (i2 == this.mTempMetrics.widthPixels && i3 == this.mTempMetrics.heightPixels) {
                    return;
                }
                ActivityView.this.mTaskEmbedder.resizeTask(i2, i3);
                ActivityView.this.mTaskEmbedder.notifyBoundsChanged();
            }
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            ActivityView.this.mTaskEmbedder.stop();
        }
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.mSurfaceView.setVisibility(i);
    }

    public int getVirtualDisplayId() {
        return this.mTaskEmbedder.getDisplayId();
    }

    public VirtualDisplay getVirtualDisplay() {
        return this.mTaskEmbedder.getVirtualDisplay();
    }

    public void performBackPress() {
        this.mTaskEmbedder.performBackPress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean initTaskEmbedder(SurfaceControl surfaceControl) {
        if (this.mTaskEmbedder.initialize(surfaceControl)) {
            return true;
        }
        Log.e(TAG, "Failed to initialize ActivityView");
        return false;
    }

    private void performRelease() {
        if (this.mOpened) {
            this.mSurfaceView.getHolder().removeCallback(this.mSurfaceCallback);
            if (this.mTaskEmbedder.isInitialized()) {
                this.mTaskEmbedder.release();
            }
            this.mTaskEmbedder.setListener(null);
            this.mGuard.close();
            this.mOpened = false;
        }
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

    public void setForwardedInsets(Insets insets) {
        this.mTaskEmbedder.setForwardedInsets(insets);
    }

    @Override // android.window.TaskEmbedder.Host
    public void onTaskBackgroundColorChanged(TaskEmbedder taskEmbedder, int i) {
        SurfaceView surfaceView = this.mSurfaceView;
        if (surfaceView != null) {
            surfaceView.setResizeBackgroundColor(i);
        }
    }

    @Override // android.window.TaskEmbedder.Host
    public Region getTapExcludeRegion() {
        if (isAttachedToWindow() && canReceivePointerEvents()) {
            Point positionInWindow = getPositionInWindow();
            this.mTapExcludeRegion.set(positionInWindow.x, positionInWindow.y, positionInWindow.x + getWidth(), positionInWindow.y + getHeight());
            ViewParent parent = getParent();
            if (parent != null) {
                parent.subtractObscuredTouchableRegion(this.mTapExcludeRegion, this);
            }
        } else {
            this.mTapExcludeRegion.setEmpty();
        }
        return this.mTapExcludeRegion;
    }

    @Override // android.window.TaskEmbedder.Host
    public Matrix getScreenToTaskMatrix() {
        getLocationOnScreen(this.mTmpArray);
        this.mScreenSurfaceMatrix.set(getMatrix());
        Matrix matrix = this.mScreenSurfaceMatrix;
        int[] iArr = this.mTmpArray;
        matrix.postTranslate(iArr[0], iArr[1]);
        return this.mScreenSurfaceMatrix;
    }

    @Override // android.window.TaskEmbedder.Host
    public Point getPositionInWindow() {
        getLocationInWindow(this.mTmpArray);
        Point point = this.mWindowPosition;
        int[] iArr = this.mTmpArray;
        point.set(iArr[0], iArr[1]);
        return this.mWindowPosition;
    }

    @Override // android.window.TaskEmbedder.Host
    public Rect getScreenBounds() {
        getBoundsOnScreen(this.mTmpRect);
        return this.mTmpRect;
    }

    @Override // android.view.View, android.window.TaskEmbedder.Host
    public IWindow getWindow() {
        return super.getWindow();
    }

    @Override // android.view.View, android.window.TaskEmbedder.Host
    public boolean canReceivePointerEvents() {
        return super.canReceivePointerEvents();
    }

    private final class StateCallbackAdapter implements TaskEmbedder.Listener {
        private final StateCallback mCallback;

        private StateCallbackAdapter(StateCallback stateCallback) {
            this.mCallback = stateCallback;
        }

        @Override // android.window.TaskEmbedder.Listener
        public void onInitialized() {
            this.mCallback.onActivityViewReady(ActivityView.this);
        }

        @Override // android.window.TaskEmbedder.Listener
        public void onReleased() {
            this.mCallback.onActivityViewDestroyed(ActivityView.this);
        }

        @Override // android.window.TaskEmbedder.Listener
        public void onTaskCreated(int i, ComponentName componentName) {
            this.mCallback.onTaskCreated(i, componentName);
        }

        @Override // android.window.TaskEmbedder.Listener
        public void onTaskVisibilityChanged(int i, boolean z) {
            this.mCallback.onTaskVisibilityChanged(i, z);
        }

        @Override // android.window.TaskEmbedder.Listener
        public void onTaskMovedToFront(int i) {
            this.mCallback.onTaskMovedToFront(i);
        }

        @Override // android.window.TaskEmbedder.Listener
        public void onTaskRemovalStarted(int i) {
            this.mCallback.onTaskRemovalStarted(i);
        }

        @Override // android.window.TaskEmbedder.Listener
        public void onBackPressedOnTaskRoot(int i) {
            this.mCallback.onBackPressedOnTaskRoot(i);
        }
    }
}
