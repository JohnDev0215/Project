package com.android.systemui.shared.recents.model;

import android.app.ActivityManager;
import android.app.TaskInfo;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.ViewDebug;
import com.android.systemui.shared.recents.utilities.Utilities;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class Task {
    public static final String TAG = "Task";

    @ViewDebug.ExportedProperty(category = "recents")
    public int colorBackground;

    @ViewDebug.ExportedProperty(category = "recents")
    public int colorPrimary;
    public Drawable icon;

    @ViewDebug.ExportedProperty(category = "recents")
    public boolean isDockable;

    @ViewDebug.ExportedProperty(category = "recents")
    @Deprecated
    public boolean isLaunchTarget;

    @ViewDebug.ExportedProperty(category = "recents")
    public boolean isLocked;

    @ViewDebug.ExportedProperty(category = "recents")
    @Deprecated
    public boolean isStackTask;

    @ViewDebug.ExportedProperty(category = "recents")
    @Deprecated
    public boolean isSystemApp;

    @ViewDebug.ExportedProperty(deepExport = true, prefix = "key_")
    public TaskKey key;

    @Deprecated
    private ArrayList<TaskCallbacks> mCallbacks;

    @ViewDebug.ExportedProperty(category = "recents")
    @Deprecated
    public int resizeMode;
    public ActivityManager.TaskDescription taskDescription;

    @Deprecated
    public int temporarySortIndexInStack;
    public ThumbnailData thumbnail;

    @ViewDebug.ExportedProperty(category = "recents")
    @Deprecated
    public String title;

    @ViewDebug.ExportedProperty(category = "recents")
    public String titleDescription;

    @ViewDebug.ExportedProperty(category = "recents")
    public ComponentName topActivity;

    @ViewDebug.ExportedProperty(category = "recents")
    @Deprecated
    public boolean useLightOnPrimaryColor;

    @Deprecated
    public interface TaskCallbacks {
        void onTaskDataLoaded(Task task, ThumbnailData thumbnailData);

        void onTaskDataUnloaded();

        void onTaskWindowingModeChanged();
    }

    public static class TaskKey {

        @ViewDebug.ExportedProperty(category = "recents")
        public final Intent baseIntent;

        @ViewDebug.ExportedProperty(category = "recents")
        public final int displayId;

        @ViewDebug.ExportedProperty(category = "recents")
        public final int id;

        @ViewDebug.ExportedProperty(category = "recents")
        public long lastActiveTime;
        private int mHashCode;
        public final ComponentName sourceComponent;

        @ViewDebug.ExportedProperty(category = "recents")
        public final int userId;

        @ViewDebug.ExportedProperty(category = "recents")
        public int windowingMode;

        public TaskKey(TaskInfo t) {
            ComponentName componentName;
            if (t.origActivity != null) {
                componentName = t.origActivity;
            } else {
                componentName = t.realActivity;
            }
            this.id = t.taskId;
            this.windowingMode = t.configuration.windowConfiguration.getWindowingMode();
            this.baseIntent = t.baseIntent;
            this.sourceComponent = componentName;
            this.userId = t.userId;
            this.lastActiveTime = t.lastActiveTime;
            this.displayId = t.displayId;
            updateHashCode();
        }

        public TaskKey(int id, int windowingMode, Intent intent, ComponentName sourceComponent, int userId, long lastActiveTime) {
            this.id = id;
            this.windowingMode = windowingMode;
            this.baseIntent = intent;
            this.sourceComponent = sourceComponent;
            this.userId = userId;
            this.lastActiveTime = lastActiveTime;
            this.displayId = 0;
            updateHashCode();
        }

        public TaskKey(int id, int windowingMode, Intent intent, ComponentName sourceComponent, int userId, long lastActiveTime, int displayId) {
            this.id = id;
            this.windowingMode = windowingMode;
            this.baseIntent = intent;
            this.sourceComponent = sourceComponent;
            this.userId = userId;
            this.lastActiveTime = lastActiveTime;
            this.displayId = displayId;
            updateHashCode();
        }

        public void setWindowingMode(int windowingMode) {
            this.windowingMode = windowingMode;
            updateHashCode();
        }

        public ComponentName getComponent() {
            return this.baseIntent.getComponent();
        }

        public String getPackageName() {
            if (this.baseIntent.getComponent() != null) {
                return this.baseIntent.getComponent().getPackageName();
            }
            return this.baseIntent.getPackage();
        }

        public boolean equals(Object o) {
            if (!(o instanceof TaskKey)) {
                return false;
            }
            TaskKey taskKey = (TaskKey) o;
            return this.id == taskKey.id && this.windowingMode == taskKey.windowingMode && this.userId == taskKey.userId;
        }

        public int hashCode() {
            return this.mHashCode;
        }

        public String toString() {
            return "id=" + this.id + " windowingMode=" + this.windowingMode + " user=" + this.userId + " lastActiveTime=" + this.lastActiveTime;
        }

        private void updateHashCode() {
            this.mHashCode = Objects.hash(Integer.valueOf(this.id), Integer.valueOf(this.windowingMode), Integer.valueOf(this.userId));
        }
    }

    public Task() {
        this.mCallbacks = new ArrayList<>();
    }

    public static Task from(TaskKey taskKey, TaskInfo taskInfo, boolean isLocked) {
        ActivityManager.TaskDescription taskDescription = taskInfo.taskDescription;
        return new Task(taskKey, taskDescription != null ? taskDescription.getPrimaryColor() : 0, taskDescription != null ? taskDescription.getBackgroundColor() : 0, taskInfo.supportsSplitScreenMultiWindow, isLocked, taskDescription, taskInfo.topActivity);
    }

    public Task(TaskKey key) {
        this.mCallbacks = new ArrayList<>();
        this.key = key;
        this.taskDescription = new ActivityManager.TaskDescription();
    }

    public Task(TaskKey key, int colorPrimary, int colorBackground, boolean isDockable, boolean isLocked, ActivityManager.TaskDescription taskDescription, ComponentName topActivity) {
        this.mCallbacks = new ArrayList<>();
        this.key = key;
        this.colorPrimary = colorPrimary;
        this.colorBackground = colorBackground;
        this.taskDescription = taskDescription;
        this.isDockable = isDockable;
        this.isLocked = isLocked;
        this.topActivity = topActivity;
    }

    @Deprecated
    public Task(TaskKey key, Drawable icon, ThumbnailData thumbnail, String title, String titleDescription, int colorPrimary, int colorBackground, boolean isLaunchTarget, boolean isStackTask, boolean isSystemApp, boolean isDockable, ActivityManager.TaskDescription taskDescription, int resizeMode, ComponentName topActivity, boolean isLocked) {
        this.mCallbacks = new ArrayList<>();
        this.key = key;
        this.icon = icon;
        this.thumbnail = thumbnail;
        this.title = title;
        this.titleDescription = titleDescription;
        this.colorPrimary = colorPrimary;
        this.colorBackground = colorBackground;
        this.useLightOnPrimaryColor = Utilities.computeContrastBetweenColors(colorPrimary, -1) > 3.0f;
        this.taskDescription = taskDescription;
        this.isLaunchTarget = isLaunchTarget;
        this.isStackTask = isStackTask;
        this.isSystemApp = isSystemApp;
        this.isDockable = isDockable;
        this.resizeMode = resizeMode;
        this.topActivity = topActivity;
        this.isLocked = isLocked;
    }

    @Deprecated
    public void copyFrom(Task o) {
        this.key = o.key;
        this.icon = o.icon;
        this.thumbnail = o.thumbnail;
        this.title = o.title;
        this.titleDescription = o.titleDescription;
        this.colorPrimary = o.colorPrimary;
        this.colorBackground = o.colorBackground;
        this.useLightOnPrimaryColor = o.useLightOnPrimaryColor;
        this.taskDescription = o.taskDescription;
        this.isLaunchTarget = o.isLaunchTarget;
        this.isStackTask = o.isStackTask;
        this.isSystemApp = o.isSystemApp;
        this.isDockable = o.isDockable;
        this.resizeMode = o.resizeMode;
        this.isLocked = o.isLocked;
        this.topActivity = o.topActivity;
    }

    @Deprecated
    public void addCallback(TaskCallbacks cb) {
        if (this.mCallbacks.contains(cb)) {
            return;
        }
        this.mCallbacks.add(cb);
    }

    @Deprecated
    public void removeCallback(TaskCallbacks cb) {
        this.mCallbacks.remove(cb);
    }

    @Deprecated
    public void setWindowingMode(int windowingMode) {
        this.key.setWindowingMode(windowingMode);
        int size = this.mCallbacks.size();
        for (int i = 0; i < size; i++) {
            this.mCallbacks.get(i).onTaskWindowingModeChanged();
        }
    }

    @Deprecated
    public void notifyTaskDataLoaded(ThumbnailData thumbnailData, Drawable applicationIcon) {
        this.icon = applicationIcon;
        this.thumbnail = thumbnailData;
        int size = this.mCallbacks.size();
        for (int i = 0; i < size; i++) {
            this.mCallbacks.get(i).onTaskDataLoaded(this, thumbnailData);
        }
    }

    @Deprecated
    public void notifyTaskDataUnloaded(Drawable defaultApplicationIcon) {
        this.icon = defaultApplicationIcon;
        this.thumbnail = null;
        for (int size = this.mCallbacks.size() - 1; size >= 0; size--) {
            this.mCallbacks.get(size).onTaskDataUnloaded();
        }
    }

    public ComponentName getTopComponent() {
        ComponentName componentName = this.topActivity;
        return componentName != null ? componentName : this.key.baseIntent.getComponent();
    }

    public boolean equals(Object o) {
        return this.key.equals(((Task) o).key);
    }

    public String toString() {
        return "[" + this.key.toString() + "] " + this.title;
    }

    public void dump(String prefix, PrintWriter writer) {
        writer.print(prefix);
        writer.print(this.key);
        if (!this.isDockable) {
            writer.print(" dockable=N");
        }
        if (this.isLaunchTarget) {
            writer.print(" launchTarget=Y");
        }
        if (this.isLocked) {
            writer.print(" locked=Y");
        }
        writer.print(" ");
        writer.print(this.title);
        writer.println();
    }
}
