package com.android.systemui.shared.system;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.os.IBinder;
import com.android.systemui.shared.recents.model.ThumbnailData;

/* JADX INFO: loaded from: classes.dex */
public class TaskStackChangeListener {
    public void onActivityDismissingDockedStack() {
    }

    public void onActivityForcedResizable(String packageName, int taskId, int reason) {
    }

    public void onActivityLaunchOnSecondaryDisplayFailed() {
    }

    public void onActivityLaunchOnSecondaryDisplayRerouted() {
    }

    public void onActivityPinned(String packageName, int userId, int taskId, int stackId) {
    }

    public void onActivityRequestedOrientationChanged(int taskId, int requestedOrientation) {
    }

    public void onActivityRestartAttempt(ActivityManager.RunningTaskInfo task, boolean homeTaskVisible, boolean clearedTask, boolean wasVisible) {
    }

    public void onActivityRotation(int displayId) {
    }

    public void onActivityUnpinned() {
    }

    public void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo taskInfo) {
    }

    public void onRecentTaskListFrozenChanged(boolean frozen) {
    }

    public void onRecentTaskListUpdated() {
    }

    public void onSingleTaskDisplayDrawn(int displayId) {
    }

    public void onSingleTaskDisplayEmpty(int displayId) {
    }

    public void onSizeCompatModeActivityChanged(int displayId, IBinder activityToken) {
    }

    public void onTaskCreated(int taskId, ComponentName componentName) {
    }

    public void onTaskDescriptionChanged(ActivityManager.RunningTaskInfo taskInfo) {
    }

    public void onTaskDisplayChanged(int taskId, int newDisplayId) {
    }

    public void onTaskFocusChanged(int taskId, boolean focused) {
    }

    public void onTaskMovedToFront(int taskId) {
    }

    public void onTaskProfileLocked(int taskId, int userId) {
    }

    public void onTaskRemoved(int taskId) {
    }

    public void onTaskSnapshotChanged(int taskId, ThumbnailData snapshot) {
    }

    public void onTaskStackChanged() {
    }

    public void onTaskStackChangedBackground() {
    }

    public void onActivityLaunchOnSecondaryDisplayFailed(ActivityManager.RunningTaskInfo taskInfo) {
        onActivityLaunchOnSecondaryDisplayFailed();
    }

    public void onActivityLaunchOnSecondaryDisplayRerouted(ActivityManager.RunningTaskInfo taskInfo) {
        onActivityLaunchOnSecondaryDisplayRerouted();
    }

    public void onTaskMovedToFront(ActivityManager.RunningTaskInfo taskInfo) {
        onTaskMovedToFront(taskInfo.taskId);
    }
}
