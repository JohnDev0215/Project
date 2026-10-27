package android.hardware.display;

import android.content.Context;
import android.os.Build;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import com.android.internal.R;

/* JADX INFO: loaded from: classes2.dex */
public class AmbientDisplayConfiguration {
    private final boolean mAlwaysOnByDefault;
    private final Context mContext;

    public AmbientDisplayConfiguration(Context context) {
        this.mContext = context;
        this.mAlwaysOnByDefault = context.getResources().getBoolean(R.bool.config_dozeAlwaysOnEnabled);
    }

    public boolean enabled(int i) {
        return pulseOnNotificationEnabled(i) || pulseOnLongPressEnabled(i) || alwaysOnEnabled(i) || wakeLockScreenGestureEnabled(i) || wakeDisplayGestureEnabled(i) || pickupGestureEnabled(i) || tapGestureEnabled(i) || doubleTapGestureEnabled(i);
    }

    public boolean pulseOnNotificationEnabled(int i) {
        return boolSettingDefaultOn(Settings.Secure.DOZE_ENABLED, i) && pulseOnNotificationAvailable();
    }

    public boolean pulseOnNotificationAvailable() {
        return ambientDisplayAvailable();
    }

    public boolean pickupGestureEnabled(int i) {
        return boolSettingDefaultOn(Settings.Secure.DOZE_PICK_UP_GESTURE, i) && dozePickupSensorAvailable();
    }

    public boolean dozePickupSensorAvailable() {
        return this.mContext.getResources().getBoolean(R.bool.config_dozePulsePickup);
    }

    public boolean tapGestureEnabled(int i) {
        return boolSettingDefaultOn(Settings.Secure.DOZE_TAP_SCREEN_GESTURE, i) && tapSensorAvailable();
    }

    public boolean tapSensorAvailable() {
        return !TextUtils.isEmpty(tapSensorType());
    }

    public boolean doubleTapGestureEnabled(int i) {
        return boolSettingDefaultOn(Settings.Secure.DOZE_DOUBLE_TAP_GESTURE, i) && doubleTapSensorAvailable();
    }

    public boolean doubleTapSensorAvailable() {
        return !TextUtils.isEmpty(doubleTapSensorType());
    }

    public boolean wakeScreenGestureAvailable() {
        return this.mContext.getResources().getBoolean(R.bool.config_dozeWakeLockScreenSensorAvailable);
    }

    public boolean wakeLockScreenGestureEnabled(int i) {
        return boolSettingDefaultOn(Settings.Secure.DOZE_WAKE_LOCK_SCREEN_GESTURE, i) && wakeScreenGestureAvailable();
    }

    public boolean wakeDisplayGestureEnabled(int i) {
        return boolSettingDefaultOn(Settings.Secure.DOZE_WAKE_DISPLAY_GESTURE, i) && wakeScreenGestureAvailable();
    }

    public long getWakeLockScreenDebounce() {
        return this.mContext.getResources().getInteger(R.integer.config_dozeWakeLockScreenDebounce);
    }

    public String doubleTapSensorType() {
        return this.mContext.getResources().getString(R.string.config_dozeDoubleTapSensorType);
    }

    public String tapSensorType() {
        return this.mContext.getResources().getString(R.string.config_dozeTapSensorType);
    }

    public String longPressSensorType() {
        return this.mContext.getResources().getString(R.string.config_dozeLongPressSensorType);
    }

    public boolean pulseOnLongPressEnabled(int i) {
        return pulseOnLongPressAvailable() && boolSettingDefaultOff(Settings.Secure.DOZE_PULSE_ON_LONG_PRESS, i);
    }

    private boolean pulseOnLongPressAvailable() {
        return !TextUtils.isEmpty(longPressSensorType());
    }

    public boolean alwaysOnEnabled(int i) {
        return boolSetting(Settings.Secure.DOZE_ALWAYS_ON, i, this.mAlwaysOnByDefault ? 1 : 0) && alwaysOnAvailable() && !accessibilityInversionEnabled(i);
    }

    public boolean alwaysOnAvailable() {
        return (alwaysOnDisplayDebuggingEnabled() || alwaysOnDisplayAvailable()) && ambientDisplayAvailable();
    }

    public boolean alwaysOnAvailableForUser(int i) {
        return alwaysOnAvailable() && !accessibilityInversionEnabled(i);
    }

    public String ambientDisplayComponent() {
        return this.mContext.getResources().getString(R.string.config_dozeComponent);
    }

    public boolean accessibilityInversionEnabled(int i) {
        return boolSettingDefaultOff(Settings.Secure.ACCESSIBILITY_DISPLAY_INVERSION_ENABLED, i);
    }

    public boolean ambientDisplayAvailable() {
        return !TextUtils.isEmpty(ambientDisplayComponent());
    }

    public boolean dozeSuppressed(int i) {
        return boolSettingDefaultOff(Settings.Secure.SUPPRESS_DOZE, i);
    }

    private boolean alwaysOnDisplayAvailable() {
        return this.mContext.getResources().getBoolean(R.bool.config_dozeAlwaysOnDisplayAvailable);
    }

    private boolean alwaysOnDisplayDebuggingEnabled() {
        return SystemProperties.getBoolean("debug.doze.aod", false) && Build.IS_DEBUGGABLE;
    }

    private boolean boolSettingDefaultOn(String str, int i) {
        return boolSetting(str, i, 1);
    }

    private boolean boolSettingDefaultOff(String str, int i) {
        return boolSetting(str, i, 0);
    }

    private boolean boolSetting(String str, int i, int i2) {
        return Settings.Secure.getIntForUser(this.mContext.getContentResolver(), str, i2, i) != 0;
    }
}
