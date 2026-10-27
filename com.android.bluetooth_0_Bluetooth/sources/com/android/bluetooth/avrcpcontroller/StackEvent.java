package com.android.bluetooth.avrcpcontroller;

/* JADX INFO: loaded from: classes.dex */
final class StackEvent {
    static final int EVENT_TYPE_CONNECTION_STATE_CHANGED = 1;
    static final int EVENT_TYPE_NONE = 0;
    static final int EVENT_TYPE_RC_FEATURES = 2;
    boolean mBrowsingConnected;
    int mFeatures;
    boolean mRemoteControlConnected;
    int mType;

    private StackEvent(int type) {
        this.mType = 0;
        this.mType = type;
    }

    public String toString() {
        int i = this.mType;
        if (i != 1) {
            if (i == 2) {
                return "EVENT_TYPE_RC_FEATURES";
            }
            return "Unknown";
        }
        return "EVENT_TYPE_CONNECTION_STATE_CHANGED " + this.mRemoteControlConnected;
    }

    static StackEvent connectionStateChanged(boolean remoteControlConnected, boolean browsingConnected) {
        StackEvent event = new StackEvent(1);
        event.mRemoteControlConnected = remoteControlConnected;
        event.mBrowsingConnected = browsingConnected;
        return event;
    }

    static StackEvent rcFeatures(int features) {
        StackEvent event = new StackEvent(2);
        event.mFeatures = features;
        return event;
    }
}
