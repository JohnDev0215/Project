package com.android.systemui.statusbar.phone;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.app.IProcessObserver;
import android.app.TaskStackListener;
import android.content.ComponentName;
import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.byd.card.utils.AppUtils;

/* JADX INFO: loaded from: classes.dex */
public class StatusBarIconController {
    private Context mContext;
    private TopActivityChangeListener mListener;
    private ProcessObserver mProcessObserver;
    private TaskListener mTaskListener;
    private String TAG = StatusBarIconController.class.getSimpleName();
    private StatusBean mStatusBean = new StatusBean();
    private IActivityManager mAm = ActivityManager.getService();

    public interface TopActivityChangeListener {
        void onTopActivityChanged(ComponentName componentName, int displayId);
    }

    public StatusBean getStatusBean() {
        return this.mStatusBean;
    }

    public void setListener(TopActivityChangeListener mListener) {
        this.mListener = mListener;
    }

    public StatusBarIconController(Context context) {
        this.mContext = context;
        registerTopActivityChangeListener();
    }

    public final void registerTopActivityChangeListener() {
        this.mTaskListener = new TaskListener();
        this.mProcessObserver = new ProcessObserver();
        try {
            this.mAm.registerTaskStackListener(this.mTaskListener);
            this.mAm.registerProcessObserver(this.mProcessObserver);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void unregisterTopActivityChangeListener() {
        try {
            this.mAm.unregisterTaskStackListener(this.mTaskListener);
            this.mAm.unregisterProcessObserver(this.mProcessObserver);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private class TaskListener extends TaskStackListener {
        private TaskListener() {
        }

        public void onTaskStackChanged() {
            StatusBarIconController.this.changeIcon();
        }
    }

    private class ProcessObserver extends IProcessObserver.Stub {
        public void onForegroundActivitiesChanged(int pid, int uid, boolean foregroundActivities) {
        }

        public void onForegroundServicesChanged(int pid, int uid, int fgServiceTypes) {
        }

        public void onProcessDied(int pid, int uid) {
        }

        private ProcessObserver() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeIcon() {
        if (this.mListener != null) {
            ComponentName componentName = null;
            int i = 0;
            ActivityManager.RunningTaskInfo runningTask = AppUtils.getRunningTask(this.mContext);
            if (runningTask == null) {
                Log.d(this.TAG, "changeIcon taskInfo is null");
            } else {
                componentName = runningTask.topActivity;
                i = runningTask.displayId;
            }
            this.mListener.onTopActivityChanged(componentName, i);
        }
    }

    public boolean needReverse() {
        ActivityManager.RunningTaskInfo runningTask = AppUtils.getRunningTask(this.mContext);
        if (runningTask == null) {
            Log.d(this.TAG, "needReverse taskInfo is null --- false");
            return false;
        }
        if (TextUtils.equals("com.android.launcher3", runningTask.topActivity.getPackageName())) {
            Log.d(this.TAG, "needReverse --- true");
            return true;
        }
        Log.d(this.TAG, "needReverse --- false");
        return false;
    }
}
