package com.android.settingslib.media;

import android.app.Notification;
import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes6.dex */
public abstract class MediaManager {
    private static final String TAG = "MediaManager";
    protected Context mContext;
    protected Notification mNotification;
    protected final Collection<MediaDeviceCallback> mCallbacks = new CopyOnWriteArrayList();
    protected final List<MediaDevice> mMediaDevices = new ArrayList();

    public interface MediaDeviceCallback {
        void onConnectedDeviceChanged(String str);

        void onDeviceAdded(MediaDevice mediaDevice);

        void onDeviceAttributesChanged();

        void onDeviceListAdded(List<MediaDevice> list);

        void onDeviceListRemoved(List<MediaDevice> list);

        void onDeviceRemoved(MediaDevice mediaDevice);

        void onRequestFailed(int i);
    }

    public abstract void startScan();

    public abstract void stopScan();

    MediaManager(Context context, Notification notification) {
        this.mContext = context;
        this.mNotification = notification;
    }

    protected void registerCallback(MediaDeviceCallback callback) {
        if (!this.mCallbacks.contains(callback)) {
            this.mCallbacks.add(callback);
        }
    }

    protected void unregisterCallback(MediaDeviceCallback callback) {
        if (this.mCallbacks.contains(callback)) {
            this.mCallbacks.remove(callback);
        }
    }

    protected MediaDevice findMediaDevice(String id) {
        for (MediaDevice mediaDevice : this.mMediaDevices) {
            if (mediaDevice.getId().equals(id)) {
                return mediaDevice;
            }
        }
        Log.e(TAG, "findMediaDevice() can't found device");
        return null;
    }

    protected void dispatchDeviceAdded(MediaDevice mediaDevice) {
        for (MediaDeviceCallback callback : getCallbacks()) {
            callback.onDeviceAdded(mediaDevice);
        }
    }

    protected void dispatchDeviceRemoved(MediaDevice mediaDevice) {
        for (MediaDeviceCallback callback : getCallbacks()) {
            callback.onDeviceRemoved(mediaDevice);
        }
    }

    protected void dispatchDeviceListAdded() {
        for (MediaDeviceCallback callback : getCallbacks()) {
            callback.onDeviceListAdded(new ArrayList(this.mMediaDevices));
        }
    }

    protected void dispatchDeviceListRemoved(List<MediaDevice> devices) {
        for (MediaDeviceCallback callback : getCallbacks()) {
            callback.onDeviceListRemoved(devices);
        }
    }

    protected void dispatchConnectedDeviceChanged(String id) {
        for (MediaDeviceCallback callback : getCallbacks()) {
            callback.onConnectedDeviceChanged(id);
        }
    }

    protected void dispatchDataChanged() {
        for (MediaDeviceCallback callback : getCallbacks()) {
            callback.onDeviceAttributesChanged();
        }
    }

    protected void dispatchOnRequestFailed(int reason) {
        for (MediaDeviceCallback callback : getCallbacks()) {
            callback.onRequestFailed(reason);
        }
    }

    private Collection<MediaDeviceCallback> getCallbacks() {
        return new CopyOnWriteArrayList(this.mCallbacks);
    }
}
