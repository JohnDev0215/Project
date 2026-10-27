package android.window;

import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceControl;
import android.window.TaskEmbedder;

/* JADX INFO: loaded from: classes3.dex */
public class TaskOrganizerTaskEmbedder extends TaskEmbedder {
    private static final boolean DEBUG = false;
    private static final String TAG = "TaskOrgTaskEmbedder";
    private boolean mPendingNotifyBoundsChanged;
    private ActivityManager.RunningTaskInfo mTaskInfo;
    private SurfaceControl mTaskLeash;
    private TaskOrganizer mTaskOrganizer;
    private WindowContainerToken mTaskToken;

    @Override // android.window.TaskEmbedder
    public void performBackPress() {
    }

    public TaskOrganizerTaskEmbedder(Context context, TaskEmbedder.Host host) {
        super(context, host);
    }

    @Override // android.window.TaskEmbedder
    public boolean isInitialized() {
        return this.mTaskOrganizer != null;
    }

    @Override // android.window.TaskEmbedder
    public boolean onInitialize() {
        TaskOrganizerImpl taskOrganizerImpl = new TaskOrganizerImpl();
        this.mTaskOrganizer = taskOrganizerImpl;
        taskOrganizerImpl.registerOrganizer(6);
        this.mTaskOrganizer.setInterceptBackPressedOnTaskRoot(true);
        return super.onInitialize();
    }

    @Override // android.window.TaskEmbedder
    protected boolean onRelease() {
        if (!isInitialized()) {
            return false;
        }
        this.mTaskOrganizer.unregisterOrganizer();
        resetTaskInfo();
        return true;
    }

    @Override // android.window.TaskEmbedder
    public void start() {
        super.start();
        if (isInitialized() && this.mTaskToken != null) {
            WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
            windowContainerTransaction.setHidden(this.mTaskToken, false);
            WindowOrganizer.applyTransaction(windowContainerTransaction);
            if (this.mListener != null) {
                this.mListener.onTaskVisibilityChanged(getTaskId(), true);
            }
        }
    }

    @Override // android.window.TaskEmbedder
    public void stop() {
        super.stop();
        if (isInitialized() && this.mTaskToken != null) {
            WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
            windowContainerTransaction.setHidden(this.mTaskToken, true);
            WindowOrganizer.applyTransaction(windowContainerTransaction);
            if (this.mListener != null) {
                this.mListener.onTaskVisibilityChanged(getTaskId(), false);
            }
        }
    }

    @Override // android.window.TaskEmbedder
    public void notifyBoundsChanged() {
        super.notifyBoundsChanged();
        if (this.mTaskToken == null) {
            this.mPendingNotifyBoundsChanged = true;
            return;
        }
        this.mPendingNotifyBoundsChanged = false;
        Rect screenBounds = this.mHost.getScreenBounds();
        if (screenBounds.left < 0 || screenBounds.top < 0) {
            screenBounds.offsetTo(0, 0);
        }
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        windowContainerTransaction.setBounds(this.mTaskToken, screenBounds);
        WindowOrganizer.applyTransaction(windowContainerTransaction);
    }

    @Override // android.window.TaskEmbedder
    public int getId() {
        return getTaskId();
    }

    @Override // android.window.TaskEmbedder
    protected ActivityOptions prepareActivityOptions(ActivityOptions activityOptions) {
        ActivityOptions activityOptionsPrepareActivityOptions = super.prepareActivityOptions(activityOptions);
        activityOptionsPrepareActivityOptions.setLaunchWindowingMode(6);
        return activityOptionsPrepareActivityOptions;
    }

    private int getTaskId() {
        ActivityManager.RunningTaskInfo runningTaskInfo = this.mTaskInfo;
        if (runningTaskInfo != null) {
            return runningTaskInfo.taskId;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetTaskInfo() {
        this.mTaskInfo = null;
        this.mTaskToken = null;
        this.mTaskLeash = null;
    }

    private void log(String str) {
        Log.d(TAG, "[" + System.identityHashCode(this) + "] " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    class TaskOrganizerImpl extends TaskOrganizer {
        private TaskOrganizerImpl() {
        }

        @Override // android.window.TaskOrganizer
        public void onTaskAppeared(final ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl) {
            TaskOrganizerTaskEmbedder.this.mTaskInfo = runningTaskInfo;
            TaskOrganizerTaskEmbedder.this.mTaskToken = runningTaskInfo.token;
            TaskOrganizerTaskEmbedder.this.mTaskLeash = surfaceControl;
            TaskOrganizerTaskEmbedder.this.mTransaction.reparent(TaskOrganizerTaskEmbedder.this.mTaskLeash, TaskOrganizerTaskEmbedder.this.mSurfaceControl).show(TaskOrganizerTaskEmbedder.this.mTaskLeash).show(TaskOrganizerTaskEmbedder.this.mSurfaceControl).apply();
            if (TaskOrganizerTaskEmbedder.this.mPendingNotifyBoundsChanged) {
                TaskOrganizerTaskEmbedder.this.notifyBoundsChanged();
            }
            TaskOrganizerTaskEmbedder.this.mHost.post(new Runnable() { // from class: android.window.-$$Lambda$TaskOrganizerTaskEmbedder$TaskOrganizerImpl$F1C0p21ViXm95msPof8YuA58p7U
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onTaskAppeared$0$TaskOrganizerTaskEmbedder$TaskOrganizerImpl(runningTaskInfo);
                }
            });
            if (TaskOrganizerTaskEmbedder.this.mListener != null) {
                TaskOrganizerTaskEmbedder.this.mListener.onTaskCreated(runningTaskInfo.taskId, runningTaskInfo.baseActivity);
            }
        }

        public /* synthetic */ void lambda$onTaskAppeared$0$TaskOrganizerTaskEmbedder$TaskOrganizerImpl(ActivityManager.RunningTaskInfo runningTaskInfo) {
            TaskOrganizerTaskEmbedder.this.mHost.onTaskBackgroundColorChanged(TaskOrganizerTaskEmbedder.this, runningTaskInfo.taskDescription.getBackgroundColor());
        }

        @Override // android.window.TaskOrganizer
        public void onTaskInfoChanged(final ActivityManager.RunningTaskInfo runningTaskInfo) {
            TaskOrganizerTaskEmbedder.this.mTaskInfo.taskDescription = runningTaskInfo.taskDescription;
            TaskOrganizerTaskEmbedder.this.mHost.post(new Runnable() { // from class: android.window.-$$Lambda$TaskOrganizerTaskEmbedder$TaskOrganizerImpl$t5QVZpIME0kwJAY3szZJLY6NYMM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onTaskInfoChanged$1$TaskOrganizerTaskEmbedder$TaskOrganizerImpl(runningTaskInfo);
                }
            });
        }

        public /* synthetic */ void lambda$onTaskInfoChanged$1$TaskOrganizerTaskEmbedder$TaskOrganizerImpl(ActivityManager.RunningTaskInfo runningTaskInfo) {
            TaskOrganizerTaskEmbedder.this.mHost.onTaskBackgroundColorChanged(TaskOrganizerTaskEmbedder.this, runningTaskInfo.taskDescription.getBackgroundColor());
        }

        @Override // android.window.TaskOrganizer
        public void onTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) {
            if (TaskOrganizerTaskEmbedder.this.mTaskToken != null) {
                if (runningTaskInfo == null || TaskOrganizerTaskEmbedder.this.mTaskToken.asBinder().equals(runningTaskInfo.token.asBinder())) {
                    if (TaskOrganizerTaskEmbedder.this.mListener != null) {
                        TaskOrganizerTaskEmbedder.this.mListener.onTaskRemovalStarted(runningTaskInfo.taskId);
                    }
                    TaskOrganizerTaskEmbedder.this.resetTaskInfo();
                }
            }
        }

        @Override // android.window.TaskOrganizer
        public void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo runningTaskInfo) {
            if (TaskOrganizerTaskEmbedder.this.mListener != null) {
                TaskOrganizerTaskEmbedder.this.mListener.onBackPressedOnTaskRoot(runningTaskInfo.taskId);
            }
        }
    }
}
