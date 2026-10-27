package android.provider.settings.validators;

import android.media.AudioFormat;
import android.text.TextUtils;
import android.util.ArrayMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class GlobalSettingsValidators {
    public static final Map<String, Validator> VALIDATORS;

    static {
        ArrayMap arrayMap = new ArrayMap();
        VALIDATORS = arrayMap;
        arrayMap.put("apply_ramping_ringer", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("bugreport_in_power_menu", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("stay_on_while_plugged_in", new Validator() { // from class: android.provider.settings.validators.-$$Lambda$GlobalSettingsValidators$qXOzqY7wDYbiip8ksaf1T03MmuU
            @Override // android.provider.settings.validators.Validator
            public final boolean validate(String str) {
                return GlobalSettingsValidators.lambda$static$0(str);
            }
        });
        VALIDATORS.put("auto_time", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("auto_time_zone", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("power_sounds_enabled", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("dock_sounds_enabled", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("charging_sounds_enabled", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("usb_mass_storage_enabled", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("network_recommendations_enabled", new DiscreteValueValidator(new String[]{"-1", "0", "1"}));
        VALIDATORS.put("wifi_wakeup_enabled", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("wifi_networks_available_notification_on", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("use_open_wifi_package", new Validator() { // from class: android.provider.settings.validators.-$$Lambda$GlobalSettingsValidators$XTi8JZwURroFQ61w2NGlz5mS3i4
            @Override // android.provider.settings.validators.Validator
            public final boolean validate(String str) {
                return GlobalSettingsValidators.lambda$static$1(str);
            }
        });
        VALIDATORS.put("wifi_watchdog_poor_network_test_enabled", SettingsValidators.ANY_STRING_VALIDATOR);
        VALIDATORS.put("emergency_tone", new DiscreteValueValidator(new String[]{"0", "1", "2"}));
        VALIDATORS.put("call_auto_retry", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("dock_audio_media_enabled", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("enable_automatic_system_server_heap_dumps", new DiscreteValueValidator(new String[]{"0", "1"}));
        VALIDATORS.put("encoded_surround_output", new DiscreteValueValidator(new String[]{"0", "1", "2", "3"}));
        VALIDATORS.put("encoded_surround_output_enabled_formats", new Validator() { // from class: android.provider.settings.validators.-$$Lambda$GlobalSettingsValidators$1n-YYCSGddOfjxq0XnxulNG6W1Q
            @Override // android.provider.settings.validators.Validator
            public final boolean validate(String str) {
                return GlobalSettingsValidators.lambda$static$2(str);
            }
        });
        VALIDATORS.put("low_power_sticky_auto_disable_level", new InclusiveIntegerRangeValidator(0, 100));
        VALIDATORS.put("low_power_sticky_auto_disable_enabled", new DiscreteValueValidator(new String[]{"0", "1"}));
        VALIDATORS.put("low_power_trigger_level", SettingsValidators.PERCENTAGE_INTEGER_VALIDATOR);
        VALIDATORS.put("low_power_trigger_level_max", SettingsValidators.PERCENTAGE_INTEGER_VALIDATOR);
        VALIDATORS.put("automatic_power_save_mode", new DiscreteValueValidator(new String[]{"0", "1"}));
        VALIDATORS.put("dynamic_power_savings_disable_threshold", SettingsValidators.PERCENTAGE_INTEGER_VALIDATOR);
        VALIDATORS.put("bluetooth_on", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("private_dns_mode", SettingsValidators.ANY_STRING_VALIDATOR);
        VALIDATORS.put("private_dns_specifier", SettingsValidators.ANY_STRING_VALIDATOR);
        VALIDATORS.put("soft_ap_timeout_enabled", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("wifi_scan_throttle_enabled", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("app_auto_restriction_enabled", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("zen_duration", SettingsValidators.ANY_INTEGER_VALIDATOR);
        VALIDATORS.put("charging_vibration_enabled", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("device_provisioning_mobile_data", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("require_password_to_decrypt", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("device_demo_mode", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("aware_allowed", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("power_button_long_press", new InclusiveIntegerRangeValidator(0, 5));
        VALIDATORS.put("power_button_very_long_press", new InclusiveIntegerRangeValidator(0, 1));
        VALIDATORS.put("notification_bubbles", SettingsValidators.BOOLEAN_VALIDATOR);
        VALIDATORS.put("custom_bugreport_handler_app", SettingsValidators.ANY_STRING_VALIDATOR);
        VALIDATORS.put("custom_bugreport_handler_user", SettingsValidators.ANY_INTEGER_VALIDATOR);
        VALIDATORS.put("development_settings_enabled", SettingsValidators.BOOLEAN_VALIDATOR);
    }

    static /* synthetic */ boolean lambda$static$0(String str) {
        try {
            int i = Integer.parseInt(str);
            return i == 0 || i == 1 || i == 2 || i == 4 || i == 3 || i == 5 || i == 6 || i == 7;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    static /* synthetic */ boolean lambda$static$1(String str) {
        return str == null || SettingsValidators.PACKAGE_NAME_VALIDATOR.validate(str);
    }

    static /* synthetic */ boolean lambda$static$2(String str) {
        try {
            String[] strArrSplit = TextUtils.split(str, ",");
            int length = strArrSplit.length;
            int i = 0;
            while (true) {
                boolean z = true;
                if (i >= length) {
                    return true;
                }
                int iIntValue = Integer.valueOf(strArrSplit[i]).intValue();
                int[] iArr = AudioFormat.SURROUND_SOUND_ENCODING;
                int length2 = iArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length2) {
                        z = false;
                        break;
                    }
                    if (iArr[i2] == iIntValue) {
                        break;
                    }
                    i2++;
                }
                if (!z) {
                    return false;
                }
                i++;
            }
        } catch (NumberFormatException unused) {
            return false;
        }
    }
}
