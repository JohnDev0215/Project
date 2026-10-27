package android.provider.settings.validators;

import android.content.ComponentName;
import android.net.Uri;
import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SettingsValidators {
    public static final Validator BOOLEAN_VALIDATOR = new DiscreteValueValidator(new String[]{"0", "1"});
    public static final Validator ANY_STRING_VALIDATOR = new Validator() { // from class: android.provider.settings.validators.SettingsValidators.1
        @Override // android.provider.settings.validators.Validator
        public boolean validate(String str) {
            return true;
        }
    };
    public static final Validator NON_NEGATIVE_INTEGER_VALIDATOR = new Validator() { // from class: android.provider.settings.validators.SettingsValidators.2
        @Override // android.provider.settings.validators.Validator
        public boolean validate(String str) {
            try {
                return Integer.parseInt(str) >= 0;
            } catch (NumberFormatException unused) {
                return false;
            }
        }
    };
    public static final Validator ANY_INTEGER_VALIDATOR = new Validator() { // from class: android.provider.settings.validators.SettingsValidators.3
        @Override // android.provider.settings.validators.Validator
        public boolean validate(String str) {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException unused) {
                return false;
            }
        }
    };
    public static final Validator URI_VALIDATOR = new Validator() { // from class: android.provider.settings.validators.SettingsValidators.4
        @Override // android.provider.settings.validators.Validator
        public boolean validate(String str) {
            try {
                Uri.decode(str);
                return true;
            } catch (IllegalArgumentException unused) {
                return false;
            }
        }
    };
    public static final Validator COMPONENT_NAME_VALIDATOR = new Validator() { // from class: android.provider.settings.validators.SettingsValidators.5
        @Override // android.provider.settings.validators.Validator
        public boolean validate(String str) {
            return (str == null || ComponentName.unflattenFromString(str) == null) ? false : true;
        }
    };
    public static final Validator NULLABLE_COMPONENT_NAME_VALIDATOR = new Validator() { // from class: android.provider.settings.validators.SettingsValidators.6
        @Override // android.provider.settings.validators.Validator
        public boolean validate(String str) {
            return str == null || SettingsValidators.COMPONENT_NAME_VALIDATOR.validate(str);
        }
    };
    public static final Validator PACKAGE_NAME_VALIDATOR = new Validator() { // from class: android.provider.settings.validators.SettingsValidators.7
        @Override // android.provider.settings.validators.Validator
        public boolean validate(String str) {
            return str != null && isStringPackageName(str);
        }

        private boolean isStringPackageName(String str) {
            if (str == null) {
                return false;
            }
            boolean zIsSubpartValidForPackageName = true;
            for (String str2 : str.split("\\.")) {
                zIsSubpartValidForPackageName &= isSubpartValidForPackageName(str2);
                if (!zIsSubpartValidForPackageName) {
                    break;
                }
            }
            return zIsSubpartValidForPackageName;
        }

        private boolean isSubpartValidForPackageName(String str) {
            if (str.length() == 0) {
                return false;
            }
            boolean zIsLetter = Character.isLetter(str.charAt(0));
            for (int i = 1; i < str.length(); i++) {
                zIsLetter &= Character.isLetterOrDigit(str.charAt(i)) || str.charAt(i) == '_';
                if (!zIsLetter) {
                    break;
                }
            }
            return zIsLetter;
        }
    };
    public static final Validator LENIENT_IP_ADDRESS_VALIDATOR = new Validator() { // from class: android.provider.settings.validators.SettingsValidators.8
        @Override // android.provider.settings.validators.Validator
        public boolean validate(String str) {
            return str != null && str.length() <= 45;
        }
    };
    public static final Validator LOCALE_VALIDATOR = new Validator() { // from class: android.provider.settings.validators.SettingsValidators.9
        @Override // android.provider.settings.validators.Validator
        public boolean validate(String str) {
            if (str == null) {
                return false;
            }
            for (Locale locale : Locale.getAvailableLocales()) {
                if (str.equals(locale.toString())) {
                    return true;
                }
            }
            return false;
        }
    };
    public static final Validator JSON_OBJECT_VALIDATOR = new Validator() { // from class: android.provider.settings.validators.-$$Lambda$SettingsValidators$A1HSi7P_WP7aLQXuQSZ-XLTj14c
        @Override // android.provider.settings.validators.Validator
        public final boolean validate(String str) {
            return SettingsValidators.lambda$static$0(str);
        }
    };
    public static final Validator TTS_LIST_VALIDATOR = new TTSListValidator();
    public static final Validator TILE_LIST_VALIDATOR = new TileListValidator();
    static final Validator DATE_FORMAT_VALIDATOR = new Validator() { // from class: android.provider.settings.validators.-$$Lambda$SettingsValidators$p4ATyLm0WuWQfaVzBDPwS7U11Tc
        @Override // android.provider.settings.validators.Validator
        public final boolean validate(String str) {
            return SettingsValidators.lambda$static$1(str);
        }
    };
    static final Validator COLON_SEPARATED_COMPONENT_LIST_VALIDATOR = new ComponentNameListValidator(":");
    static final Validator COLON_SEPARATED_PACKAGE_LIST_VALIDATOR = new PackageNameListValidator(":");
    static final Validator COMMA_SEPARATED_COMPONENT_LIST_VALIDATOR = new ComponentNameListValidator(",");
    static final Validator PERCENTAGE_INTEGER_VALIDATOR = new InclusiveIntegerRangeValidator(0, 100);
    static final Validator VIBRATION_INTENSITY_VALIDATOR = new InclusiveIntegerRangeValidator(0, 3);
    static final Validator ACCESSIBILITY_SHORTCUT_TARGET_LIST_VALIDATOR = new AccessibilityShortcutTargetListValidator();
    static final Validator NONE_NEGATIVE_LONG_VALIDATOR = new Validator() { // from class: android.provider.settings.validators.SettingsValidators.10
        @Override // android.provider.settings.validators.Validator
        public boolean validate(String str) {
            try {
                return Long.parseLong(str) >= 0;
            } catch (NumberFormatException unused) {
                return false;
            }
        }
    };

    static /* synthetic */ boolean lambda$static$0(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            new JSONObject(str);
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }

    static /* synthetic */ boolean lambda$static$1(String str) {
        try {
            new SimpleDateFormat(str);
            return true;
        } catch (IllegalArgumentException | NullPointerException unused) {
            return false;
        }
    }
}
