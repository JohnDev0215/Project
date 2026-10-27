package com.android.inputmethod.latin;

import android.content.Context;
import android.media.AudioManager;
import android.os.Vibrator;
import android.view.View;
import com.android.inputmethod.latin.settings.SettingsValues;

/* JADX INFO: loaded from: classes2.dex */
public final class AudioAndHapticFeedbackManager {
    private static final AudioAndHapticFeedbackManager sInstance = new AudioAndHapticFeedbackManager();
    private AudioManager mAudioManager;
    private Context mContext;
    private SettingsValues mSettingsValues;
    private boolean mSoundOn;
    private Vibrator mVibrator;

    public static AudioAndHapticFeedbackManager getInstance() {
        return sInstance;
    }

    private AudioAndHapticFeedbackManager() {
    }

    public static void init(final Context context) {
        sInstance.initInternal(context);
    }

    private void initInternal(final Context context) {
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.mVibrator = (Vibrator) context.getSystemService("vibrator");
        this.mContext = context;
    }

    public void performHapticAndAudioFeedback(final int code, final View viewToPerformHapticFeedbackOn) {
        performHapticFeedback(viewToPerformHapticFeedbackOn);
        performAudioFeedback(code);
    }

    public boolean hasVibrator() {
        Vibrator vibrator = this.mVibrator;
        return vibrator != null && vibrator.hasVibrator();
    }

    public void vibrate(final long milliseconds) {
        Vibrator vibrator = this.mVibrator;
        if (vibrator == null) {
            return;
        }
        vibrator.vibrate(milliseconds);
    }

    private boolean reevaluateIfSoundIsOn() {
        AudioManager audioManager;
        SettingsValues settingsValues = this.mSettingsValues;
        return settingsValues != null && settingsValues.mSoundOn && (audioManager = this.mAudioManager) != null && audioManager.getRingerMode() == 2;
    }

    public void performAudioFeedback(final int code) {
        AudioManager audioManager = this.mAudioManager;
        if (audioManager != null && this.mSoundOn) {
            audioManager.playSoundEffect(0);
        }
    }

    public void performHapticFeedback(final View viewToPerformHapticFeedbackOn) {
        if (this.mSettingsValues.mVibrateOn) {
            if (this.mSettingsValues.mKeypressVibrationDuration >= 0) {
                vibrate(this.mSettingsValues.mKeypressVibrationDuration);
            } else if (viewToPerformHapticFeedbackOn != null) {
                viewToPerformHapticFeedbackOn.performHapticFeedback(3, 2);
            }
        }
    }

    public void onSettingsChanged(final SettingsValues settingsValues) {
        this.mSettingsValues = settingsValues;
        this.mSoundOn = reevaluateIfSoundIsOn();
    }

    public void onRingerModeChanged() {
        this.mSoundOn = reevaluateIfSoundIsOn();
    }
}
