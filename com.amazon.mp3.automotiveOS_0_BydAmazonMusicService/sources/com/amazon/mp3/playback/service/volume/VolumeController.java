package com.amazon.mp3.playback.service.volume;

import android.media.AudioManager;
import com.amazon.mp3.AmazonApplication;
import com.visualon.OSMPUtils.voMimeTypes;

/* JADX INFO: loaded from: classes3.dex */
public final class VolumeController {
    private static VolumeController sInstance;
    private AudioManager mAudioManager = (AudioManager) AmazonApplication.getContext().getSystemService(voMimeTypes.VOBASE_TYPE_AUDIO);
    private MuteMode mMuteMode;
    private int mVolumeBeforeMute;

    public enum MuteMode {
        MUTE_ON,
        MUTE_OFF
    }

    public static synchronized VolumeController get() {
        if (sInstance == null) {
            sInstance = new VolumeController();
        }
        return sInstance;
    }

    private VolumeController() {
    }

    public void setVolume(int newVolume) {
        if (newVolume <= 0 && getMuteMode() == MuteMode.MUTE_OFF) {
            setMuteMode(MuteMode.MUTE_ON);
        } else {
            setMuteMode(MuteMode.MUTE_OFF);
        }
        this.mAudioManager.setStreamVolume(3, newVolume, 1);
    }

    public MuteMode getMuteMode() {
        return this.mMuteMode;
    }

    public void setMuteMode(MuteMode newMode) {
        this.mMuteMode = newMode;
    }

    public int getVolumeBeforeMute() {
        return this.mVolumeBeforeMute;
    }

    public void setVolumeBeforeMute(int newVolume) {
        this.mVolumeBeforeMute = newVolume;
    }

    public int getCurrentVolume() {
        return this.mAudioManager.getStreamVolume(3);
    }

    public int getMaximumVolume() {
        return this.mAudioManager.getStreamMaxVolume(3);
    }
}
