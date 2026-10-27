package com.android.systemui.recents;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.android.systemui.shared.recents.model.Task;

/* JADX INFO: loaded from: classes.dex */
public class RecentTaskBean {
    private Drawable appLogo;
    private String appName;
    private Bitmap bitmap;
    private ComponentName componentName;
    private boolean isAddAnim;
    private boolean isShowLock;
    private boolean isShowSplit;
    private boolean lockAnim;
    private String processName;
    private boolean removeAnim;
    private ActivityManager.TaskDescription taskDescription;
    private int taskId;
    private Task.TaskKey taskKey;

    public boolean isAddAnim() {
        return this.isAddAnim;
    }

    public void setAddAnim(boolean addAnim) {
        this.isAddAnim = addAnim;
    }

    public boolean isLockAnim() {
        return this.lockAnim;
    }

    public void setLockAnim(boolean lockAnim) {
        this.lockAnim = lockAnim;
    }

    public boolean isRemoveAnim() {
        return this.removeAnim;
    }

    public void setRemoveAnim(boolean removeAnim) {
        this.removeAnim = removeAnim;
    }

    public Task.TaskKey getTaskKey() {
        return this.taskKey;
    }

    public void setTaskKey(Task.TaskKey taskKey) {
        this.taskKey = taskKey;
    }

    public ComponentName getComponentName() {
        return this.componentName;
    }

    public void setComponentName(ComponentName componentName) {
        this.componentName = componentName;
    }

    public int getTaskId() {
        return this.taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Drawable getAppLogo() {
        return this.appLogo;
    }

    public void setAppLogo(Drawable appLogo) {
        this.appLogo = appLogo;
    }

    public boolean isShowSplit() {
        return this.isShowSplit;
    }

    public void setShowSplit(boolean showSplit) {
        this.isShowSplit = showSplit;
    }

    public boolean isShowLock() {
        return this.isShowLock;
    }

    public void setShowLock(boolean showLock) {
        this.isShowLock = showLock;
    }

    public ActivityManager.TaskDescription getTaskDescription() {
        return this.taskDescription;
    }

    public void setTaskDescription(ActivityManager.TaskDescription taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getProcessName() {
        return this.processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
}
