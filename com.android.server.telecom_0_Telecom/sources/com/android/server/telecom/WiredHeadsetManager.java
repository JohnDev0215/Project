package com.android.server.telecom;

import android.content.Context;
import android.media.AudioDeviceCallback;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.telecom.Log;
import com.android.internal.annotations.VisibleForTesting;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public class WiredHeadsetManager {
    private final AudioManager mAudioManager;
    private final Set<Listener> mListeners = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9f, 1));
    private boolean mIsPluggedIn = isWiredHeadsetPluggedIn();

    @VisibleForTesting
    public interface Listener {
        void onWiredHeadsetPluggedInChanged(boolean z, boolean z2);
    }

    private class WiredHeadsetCallback extends AudioDeviceCallback {
        private WiredHeadsetCallback() {
        }

        @Override // android.media.AudioDeviceCallback
        public void onAudioDevicesAdded(AudioDeviceInfo[] audioDeviceInfoArr) {
            Log.startSession("WHC.oADA");
            try {
                updateHeadsetStatus();
            } finally {
                Log.endSession();
            }
        }

        @Override // android.media.AudioDeviceCallback
        public void onAudioDevicesRemoved(AudioDeviceInfo[] audioDeviceInfoArr) {
            Log.startSession("WHC.oADR");
            try {
                updateHeadsetStatus();
            } finally {
                Log.endSession();
            }
        }

        private void updateHeadsetStatus() {
            boolean zIsWiredHeadsetPluggedIn = WiredHeadsetManager.this.isWiredHeadsetPluggedIn();
            Log.i(WiredHeadsetManager.this, "ACTION_HEADSET_PLUG event, plugged in: %b, ", new Object[]{Boolean.valueOf(zIsWiredHeadsetPluggedIn)});
            WiredHeadsetManager.this.onHeadsetPluggedInChanged(zIsWiredHeadsetPluggedIn);
        }
    }

    public WiredHeadsetManager(Context context) {
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.mAudioManager.registerAudioDeviceCallback(new WiredHeadsetCallback(), null);
    }

    @VisibleForTesting
    public void addListener(Listener listener) {
        this.mListeners.add(listener);
    }

    @VisibleForTesting
    public boolean isPluggedIn() {
        return this.mIsPluggedIn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isWiredHeadsetPluggedIn() {
        boolean z = false;
        for (AudioDeviceInfo audioDeviceInfo : this.mAudioManager.getDevices(3)) {
            int type = audioDeviceInfo.getType();
            if (type == 3 || type == 4 || type == 11 || type == 22) {
                z = true;
            }
            if (z) {
                break;
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onHeadsetPluggedInChanged(boolean z) {
        boolean z2 = this.mIsPluggedIn;
        if (z2 != z) {
            Log.v(this, "onHeadsetPluggedInChanged, mIsPluggedIn: %b -> %b", new Object[]{Boolean.valueOf(z2), Boolean.valueOf(z)});
            boolean z3 = this.mIsPluggedIn;
            this.mIsPluggedIn = z;
            Iterator<Listener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onWiredHeadsetPluggedInChanged(z3, this.mIsPluggedIn);
            }
        }
    }
}
