package android.provider.settings.validators;

import android.compat.annotation.UnsupportedAppUsage;
import android.content.ComponentName;
import android.util.ArrayMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class SystemSettingsValidators {

    @UnsupportedAppUsage
    public static final Map<String, Validator> VALIDATORS;

    static {
        ArrayMap arrayMap = new ArrayMap();
        VALIDATORS = arrayMap;
        arrayMap.put("stay_on_while_plugged_in", new Validator() { // from class: android.provider.settings.validators.-$$Lambda$SystemSettingsValidators$7r948OHjLc3Uq8jvnjXLuZiFeyg
            @Override // android.provider.settings.validators.Validator
            public final boolean validate(String str) {
                return SystemSettingsValidators.lambda$static$0(str);
            }
        });
        VALIDATORS.put("end_button_behavior", new InclusiveIntegerRangeValidator(0, 3));
        VALIDATORS.put("wifi_use_static_ip", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("bluetooth_discoverability", new InclusiveIntegerRangeValidator(0, 2));
        VALIDATORS.put("bluetooth_discoverability_timeout", SettingsValidators.NON_NEGATIVE_INTEGER_VALIDATOR);
        VALIDATORS.put("next_alarm_formatted", new Validator() { // from class: android.provider.settings.validators.SystemSettingsValidators.1
            @Override // android.provider.settings.validators.Validator
            public boolean validate(String str) {
                return str == null || str.length() < 1000;
            }
        });
        VALIDATORS.put("font_scale", new InclusiveFloatRangeValidator(0.25f, 5.0f));
        VALIDATORS.put("dim_screen", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("display_color_mode", new Validator() { // from class: android.provider.settings.validators.SystemSettingsValidators.2
            @Override // android.provider.settings.validators.Validator
            public boolean validate(String str) {
                try {
                    int i = Integer.parseInt(str);
                    return (i >= 0 && i <= 3) || (i >= 256 && i <= 511);
                } catch (NullPointerException | NumberFormatException unused) {
                    return false;
                }
            }
        });
        VALIDATORS.put("screen_off_timeout", SettingsValidators.NON_NEGATIVE_INTEGER_VALIDATOR);
        VALIDATORS.put("screen_brightness_for_vr", new InclusiveIntegerRangeValidator(0, 255));
        VALIDATORS.put("screen_brightness_mode", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("adaptive_sleep", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("mode_ringer_streams_affected", SettingsValidators.NON_NEGATIVE_INTEGER_VALIDATOR);
        VALIDATORS.put("mute_streams_affected", SettingsValidators.NON_NEGATIVE_INTEGER_VALIDATOR);
        VALIDATORS.put("vibrate_on", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("notification_vibration_intensity", SettingsValidators.VIBRATION_INTENSITY_VALIDATOR);
        VALIDATORS.put("ring_vibration_intensity", SettingsValidators.VIBRATION_INTENSITY_VALIDATOR);
        VALIDATORS.put("haptic_feedback_intensity", SettingsValidators.VIBRATION_INTENSITY_VALIDATOR);
        VALIDATORS.put("ringtone", SettingsValidators.URI_VALIDATOR);
        VALIDATORS.put("notification_sound", SettingsValidators.URI_VALIDATOR);
        VALIDATORS.put("alarm_alert", SettingsValidators.URI_VALIDATOR);
        VALIDATORS.put("auto_replace", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("auto_caps", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("auto_punctuate", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("show_password", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("auto_time", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("auto_time_zone", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("SHOW_GTALK_SERVICE_STATUS", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("wallpaper_activity", new Validator() { // from class: android.provider.settings.validators.SystemSettingsValidators.3
            @Override // android.provider.settings.validators.Validator
            public boolean validate(String str) {
                return (str == null || str.length() <= 1000) && ComponentName.unflattenFromString(str) != null;
            }
        });
        VALIDATORS.put("time_12_24", new DiscreteValueValidator(new String[]{"12", "24", null}));
        VALIDATORS.put("date_format", SettingsValidators.DATE_FORMAT_VALIDATOR);
        VALIDATORS.put("setup_wizard_has_run", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("accelerometer_rotation", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("user_rotation", new InclusiveIntegerRangeValidator(0, 3));
        VALIDATORS.put("dtmf_tone", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("sound_effects_enabled", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("haptic_feedback_enabled", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("power_sounds_enabled", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("dock_sounds_enabled", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("show_web_suggestions", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("wifi_use_static_ip", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("advanced_settings", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("screen_auto_brightness_adj", new InclusiveFloatRangeValidator(-1.0f, 1.0f));
        VALIDATORS.put("vibrate_input_devices", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("master_mono", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("master_balance", new InclusiveFloatRangeValidator(-1.0f, 1.0f));
        VALIDATORS.put("notifications_use_ring_volume", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("vibrate_in_silent", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("media_button_receiver", SettingsValidators.COMPONENT_NAME_VALIDATOR);
        VALIDATORS.put("hide_rotation_lock_toggle_for_accessibility", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("vibrate_when_ringing", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("dtmf_tone_type", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("hearing_aid", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("tty_mode", new InclusiveIntegerRangeValidator(0, 3));
        VALIDATORS.put("notification_light_pulse", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("pointer_location", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("show_touches", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("window_orientation_listener_log", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("lockscreen_sounds_enabled", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("lockscreen.disabled", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("sip_receive_calls", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("sip_call_options", new DiscreteValueValidator(new String[]{"SIP_ALWAYS", "SIP_ADDRESS_ONLY"}));
        VALIDATORS.put("SIP_ALWAYS", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("SIP_ADDRESS_ONLY", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("SIP_ASK_ME_EACH_TIME", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("pointer_speed", new InclusiveFloatRangeValidator(-7.0f, 7.0f));
        VALIDATORS.put("lock_to_app_enabled", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("egg_mode", new Validator() { // from class: android.provider.settings.validators.SystemSettingsValidators.4
            @Override // android.provider.settings.validators.Validator
            public boolean validate(String str) {
                try {
                    return Long.parseLong(str) >= 0;
                } catch (NumberFormatException unused) {
                    return false;
                }
            }
        });
        VALIDATORS.put("wifi_static_ip", SettingsValidators.LENIENT_IP_ADDRESS_VALIDATOR);
        VALIDATORS.put("wifi_static_gateway", SettingsValidators.LENIENT_IP_ADDRESS_VALIDATOR);
        VALIDATORS.put("wifi_static_netmask", SettingsValidators.LENIENT_IP_ADDRESS_VALIDATOR);
        VALIDATORS.put("wifi_static_dns1", SettingsValidators.LENIENT_IP_ADDRESS_VALIDATOR);
        VALIDATORS.put("wifi_static_dns2", SettingsValidators.LENIENT_IP_ADDRESS_VALIDATOR);
        VALIDATORS.put("status_bar_show_battery_percent", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("notification_light_pulse", SettingsValidators.BOOLEAN_VALIDATOR);
    }

    static /* synthetic */ boolean lambda$static$0(String str) {
        try {
            int i = Integer.parseInt(str);
            return i == 0 || i == 1 || i == 2 || i == 4 || i == 3 || i == 5 || i == 6 || i == 7;
        } catch (NumberFormatException unused) {
            return false;
        }
    }
}
