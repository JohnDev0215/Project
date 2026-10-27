package com.amazon.avod.media.framework.volume;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import androidx.mediarouter.media.SystemMediaRouteProvider;
import com.amazon.avod.util.DLog;
import com.visualon.OSMPUtils.voMimeTypes;

/* JADX INFO: loaded from: classes2.dex */
public class VolumeManager {
    private final AudioManager mAudioManager;
    private final Context mContext;
    private boolean mInitialized;
    private int mLastVolume;
    private VolumeChangeListener mVolumeChangeListener;
    final BroadcastReceiver mVolumeChangedReceiver;
    private final Object mVolumeMutex;

    public int getMinimumVolume() {
        return 0;
    }

    public VolumeManager(Context context) {
        this(context, (AudioManager) context.getSystemService(voMimeTypes.VOBASE_TYPE_AUDIO));
    }

    public VolumeManager(Context context, AudioManager audioManager) {
        this.mVolumeChangedReceiver = new BroadcastReceiver() { // from class: com.amazon.avod.media.framework.volume.VolumeManager.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                DLog.enterf("Intention action: %s", intent == null ? null : intent.getAction());
                VolumeManager.this.onVolumeChanged();
            }
        };
        this.mVolumeMutex = new Object();
        this.mContext = context;
        this.mAudioManager = audioManager;
    }

    public void startListening(VolumeChangeListener volumeChangeListener) {
        synchronized (this.mVolumeMutex) {
            if (this.mInitialized) {
                return;
            }
            this.mVolumeChangeListener = volumeChangeListener;
            this.mLastVolume = getCurrentVolume();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(SystemMediaRouteProvider.LegacyImpl.VolumeChangeReceiver.VOLUME_CHANGED_ACTION);
            intentFilter.addAction("android.media.RINGER_MODE_CHANGED");
            this.mContext.registerReceiver(this.mVolumeChangedReceiver, intentFilter);
            this.mInitialized = true;
        }
    }

    public void stopListening() {
        synchronized (this.mVolumeMutex) {
            this.mInitialized = false;
            this.mContext.unregisterReceiver(this.mVolumeChangedReceiver);
            this.mVolumeChangeListener = null;
        }
    }

    public void setVolume(int i) {
        this.mAudioManager.setStreamVolume(3, i, 0);
    }

    public int getCurrentVolume() {
        return this.mAudioManager.getStreamVolume(3);
    }

    public int getMaximumVolume() {
        return this.mAudioManager.getStreamMaxVolume(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onVolumeChanged() {
        synchronized (this.mVolumeMutex) {
            if (this.mVolumeChangeListener != null) {
                int currentVolume = getCurrentVolume();
                this.mVolumeChangeListener.onVolumeChange(this.mLastVolume, currentVolume);
                this.mLastVolume = currentVolume;
            }
        }
    }
}
