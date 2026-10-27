package android.app;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.window.WindowContainerToken;

/* JADX INFO: loaded from: classes.dex */
public class TaskInfo {
    private static final String TAG = "TaskInfo";
    public ComponentName baseActivity;
    public Intent baseIntent;
    public final Configuration configuration = new Configuration();
    public int displayId;
    public boolean isResizeable;
    public boolean isRunning;
    public long lastActiveTime;
    public int numActivities;
    public ComponentName origActivity;
    public PictureInPictureParams pictureInPictureParams;
    public ComponentName realActivity;
    public int requestedOrientation;
    public int resizeMode;
    public int stackId;
    public boolean supportsSplitScreenMultiWindow;
    public ActivityManager.TaskDescription taskDescription;
    public int taskId;
    public WindowContainerToken token;
    public ComponentName topActivity;
    public ActivityInfo topActivityInfo;
    public int topActivityType;
    public int userId;

    TaskInfo() {
    }

    private TaskInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    public ActivityManager.TaskSnapshot getTaskSnapshot(boolean z) {
        try {
            return ActivityManager.getService().getTaskSnapshot(this.taskId, z);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get task snapshot, taskId=" + this.taskId, e);
            return null;
        }
    }

    public WindowContainerToken getToken() {
        return this.token;
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    void readFromParcel(Parcel parcel) {
        this.userId = parcel.readInt();
        this.stackId = parcel.readInt();
        this.taskId = parcel.readInt();
        this.displayId = parcel.readInt();
        this.isRunning = parcel.readBoolean();
        this.baseIntent = parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null;
        this.baseActivity = ComponentName.readFromParcel(parcel);
        this.topActivity = ComponentName.readFromParcel(parcel);
        this.origActivity = ComponentName.readFromParcel(parcel);
        this.realActivity = ComponentName.readFromParcel(parcel);
        this.numActivities = parcel.readInt();
        this.lastActiveTime = parcel.readLong();
        this.taskDescription = parcel.readInt() != 0 ? ActivityManager.TaskDescription.CREATOR.createFromParcel(parcel) : null;
        this.supportsSplitScreenMultiWindow = parcel.readBoolean();
        this.resizeMode = parcel.readInt();
        this.configuration.readFromParcel(parcel);
        this.token = WindowContainerToken.CREATOR.createFromParcel(parcel);
        this.topActivityType = parcel.readInt();
        this.pictureInPictureParams = parcel.readInt() != 0 ? PictureInPictureParams.CREATOR.createFromParcel(parcel) : null;
        this.topActivityInfo = parcel.readInt() != 0 ? ActivityInfo.CREATOR.createFromParcel(parcel) : null;
        this.isResizeable = parcel.readBoolean();
        this.requestedOrientation = parcel.readInt();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.userId);
        parcel.writeInt(this.stackId);
        parcel.writeInt(this.taskId);
        parcel.writeInt(this.displayId);
        parcel.writeBoolean(this.isRunning);
        if (this.baseIntent != null) {
            parcel.writeInt(1);
            this.baseIntent.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        ComponentName.writeToParcel(this.baseActivity, parcel);
        ComponentName.writeToParcel(this.topActivity, parcel);
        ComponentName.writeToParcel(this.origActivity, parcel);
        ComponentName.writeToParcel(this.realActivity, parcel);
        parcel.writeInt(this.numActivities);
        parcel.writeLong(this.lastActiveTime);
        if (this.taskDescription != null) {
            parcel.writeInt(1);
            this.taskDescription.writeToParcel(parcel, i);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeBoolean(this.supportsSplitScreenMultiWindow);
        parcel.writeInt(this.resizeMode);
        this.configuration.writeToParcel(parcel, i);
        this.token.writeToParcel(parcel, i);
        parcel.writeInt(this.topActivityType);
        if (this.pictureInPictureParams == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            this.pictureInPictureParams.writeToParcel(parcel, i);
        }
        if (this.topActivityInfo == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            this.topActivityInfo.writeToParcel(parcel, i);
        }
        parcel.writeBoolean(this.isResizeable);
        parcel.writeInt(this.requestedOrientation);
    }

    public String toString() {
        return "TaskInfo{userId=" + this.userId + " stackId=" + this.stackId + " taskId=" + this.taskId + " displayId=" + this.displayId + " isRunning=" + this.isRunning + " baseIntent=" + this.baseIntent + " baseActivity=" + this.baseActivity + " topActivity=" + this.topActivity + " origActivity=" + this.origActivity + " realActivity=" + this.realActivity + " numActivities=" + this.numActivities + " lastActiveTime=" + this.lastActiveTime + " supportsSplitScreenMultiWindow=" + this.supportsSplitScreenMultiWindow + " resizeMode=" + this.resizeMode + " isResizeable=" + this.isResizeable + " token=" + this.token + " topActivityType=" + this.topActivityType + " pictureInPictureParams=" + this.pictureInPictureParams + " topActivityInfo=" + this.topActivityInfo + " requestedOrientation=" + this.requestedOrientation;
    }
}
