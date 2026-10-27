package com.android.bluetooth.a2dpsink;

import android.bluetooth.BluetoothDevice;

/* JADX INFO: loaded from: classes.dex */
final class StackEvent {
    static final int AUDIO_STATE_REMOTE_SUSPEND = 0;
    static final int AUDIO_STATE_STARTED = 2;
    static final int AUDIO_STATE_STOPPED = 1;
    static final int CONNECTION_STATE_CONNECTED = 2;
    static final int CONNECTION_STATE_CONNECTING = 1;
    static final int CONNECTION_STATE_DISCONNECTED = 0;
    static final int CONNECTION_STATE_DISCONNECTING = 3;
    static final int EVENT_TYPE_AUDIO_CONFIG_CHANGED = 3;
    static final int EVENT_TYPE_AUDIO_STATE_CHANGED = 2;
    static final int EVENT_TYPE_CONNECTION_STATE_CHANGED = 1;
    static final int EVENT_TYPE_NONE = 0;
    int mType;
    BluetoothDevice mDevice = null;
    int mState = 0;
    int mSampleRate = 0;
    int mChannelCount = 0;
    int mCodecIndex = 0;

    private StackEvent(int type) {
        this.mType = 0;
        this.mType = type;
    }

    public String toString() {
        int i = this.mType;
        if (i == 1) {
            return "EVENT_TYPE_CONNECTION_STATE_CHANGED " + this.mState;
        }
        if (i == 2) {
            return "EVENT_TYPE_AUDIO_STATE_CHANGED " + this.mState;
        }
        if (i == 3) {
            return "EVENT_TYPE_AUDIO_CONFIG_CHANGED " + this.mSampleRate + ":" + this.mChannelCount;
        }
        return "Unknown";
    }

    static StackEvent connectionStateChanged(BluetoothDevice device, int state) {
        StackEvent event = new StackEvent(1);
        event.mDevice = device;
        event.mState = state;
        return event;
    }

    static StackEvent audioStateChanged(BluetoothDevice device, int state) {
        StackEvent event = new StackEvent(2);
        event.mDevice = device;
        event.mState = state;
        return event;
    }

    static StackEvent audioConfigChanged(BluetoothDevice device, int sampleRate, int channelCount, int codecIndex) {
        StackEvent event = new StackEvent(3);
        event.mDevice = device;
        event.mSampleRate = sampleRate;
        event.mChannelCount = channelCount;
        if (codecIndex == 5) {
            event.mCodecIndex = 1000000;
        } else {
            event.mCodecIndex = codecIndex;
        }
        return event;
    }
}
