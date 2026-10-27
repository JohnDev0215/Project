package com.android.server.telecom;

import android.content.Context;
import android.media.AudioManager;
import android.provider.DeviceConfig;
import android.provider.Settings;
import com.android.internal.annotations.VisibleForTesting;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public class SystemSettingsUtil {
    public boolean isTheaterModeOn(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "theater_mode_on", 0) == 1;
    }

    public boolean canVibrateWhenRinging(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "vibrate_when_ringing", 0) != 0;
    }

    public boolean isEnhancedCallBlockingEnabled(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "debug.enable_enhanced_calling", 0) != 0;
    }

    public boolean setEnhancedCallBlockingEnabled(Context context, boolean z) {
        return Settings.System.putInt(context.getContentResolver(), "debug.enable_enhanced_calling", z ? 1 : 0);
    }

    public boolean applyRampingRinger(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "apply_ramping_ringer", 0) == 1;
    }

    public boolean enableAudioCoupledVibrationForRampingRinger() {
        return DeviceConfig.getBoolean("telephony", "ramping_ringer_audio_coupled_vibration_enabled", false);
    }

    public boolean isHapticPlaybackSupported(Context context) {
        return AudioManager.isHapticPlaybackSupported();
    }
}
