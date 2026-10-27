package com.android.services.telephony;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Vibrator;
import android.provider.Settings;

/* JADX INFO: loaded from: classes.dex */
class EmergencyTonePlayer {
    private static final long[] VIBRATE_PATTERN = {1000, 1000};
    private static final AudioAttributes VIBRATION_ATTRIBUTES = new AudioAttributes.Builder().setContentType(1).setUsage(2).build();
    private final AudioManager mAudioManager;
    private final Context mContext;
    private boolean mIsVibrating = false;
    private int mSavedInCallVolume;
    private ToneGenerator mToneGenerator;
    private final Vibrator mVibrator;

    EmergencyTonePlayer(Context context) {
        this.mContext = context;
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.mVibrator = (Vibrator) context.getSystemService("vibrator");
    }

    public void start() {
        int toneSetting = getToneSetting();
        if (toneSetting == 1) {
            startAlert();
        } else {
            if (toneSetting != 2) {
                return;
            }
            startVibrate();
        }
    }

    public void stop() {
        stopVibrate();
        stopAlert();
    }

    private void startVibrate() {
        int ringerMode = this.mAudioManager.getRingerMode();
        if (ringerMode == 0) {
            Log.i(this, "startVibrate: skipping vibrate tone due to ringer mode %d", Integer.valueOf(ringerMode));
        } else {
            if (this.mIsVibrating) {
                return;
            }
            this.mVibrator.vibrate(VIBRATE_PATTERN, 0, VIBRATION_ATTRIBUTES);
            this.mIsVibrating = true;
        }
    }

    private void stopVibrate() {
        if (this.mIsVibrating) {
            this.mVibrator.cancel();
            this.mIsVibrating = false;
        }
    }

    private void startAlert() {
        int ringerMode = this.mAudioManager.getRingerMode();
        if (ringerMode != 2) {
            Log.i(this, "startAlert: skipping emergency tone due to ringer mode %d", Integer.valueOf(ringerMode));
            return;
        }
        if (this.mToneGenerator == null) {
            this.mToneGenerator = new ToneGenerator(0, 100);
            this.mSavedInCallVolume = this.mAudioManager.getStreamVolume(0);
            AudioManager audioManager = this.mAudioManager;
            audioManager.setStreamVolume(0, audioManager.getStreamMaxVolume(0), 0);
            this.mToneGenerator.startTone(92);
        }
    }

    private void stopAlert() {
        ToneGenerator toneGenerator = this.mToneGenerator;
        if (toneGenerator != null) {
            toneGenerator.stopTone();
            this.mToneGenerator.release();
            this.mToneGenerator = null;
            this.mAudioManager.setStreamVolume(0, this.mSavedInCallVolume, 0);
            this.mSavedInCallVolume = 0;
        }
    }

    private int getToneSetting() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "emergency_tone", 0);
    }
}
