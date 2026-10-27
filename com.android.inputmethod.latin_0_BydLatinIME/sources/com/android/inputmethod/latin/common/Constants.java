package com.android.inputmethod.latin.common;

import android.hardware.bydauto.properties.CarSettings;
import com.android.inputmethod.latin.userdictionary.UserDictionaryAddWordContents;
import com.android.inputmethod.latin.utils.SubtypeLocaleUtils;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class Constants {
    public static final int CODE_ACTION_NEXT = -8;
    public static final int CODE_ACTION_PREVIOUS = -9;
    public static final int CODE_ALPHA_FROM_EMOJI = -14;
    public static final int CODE_BACKSLASH = 92;
    public static final int CODE_CAPSLOCK = -2;
    public static final int CODE_CIRCUMFLEX_ACCENT = 94;
    public static final int CODE_CLOSING_ANGLE_BRACKET = 62;
    public static final int CODE_CLOSING_CURLY_BRACKET = 125;
    public static final int CODE_CLOSING_PARENTHESIS = 41;
    public static final int CODE_CLOSING_SQUARE_BRACKET = 93;
    public static final int CODE_COMMA = 44;
    public static final int CODE_COMMERCIAL_AT = 64;
    public static final int CODE_DASH = 45;
    public static final int CODE_DELETE = -5;
    public static final int CODE_DOUBLE_QUOTE = 34;
    public static final int CODE_EMOJI = -11;
    public static final int CODE_ENTER = 10;
    public static final int CODE_GRAVE_ACCENT = 96;
    public static final int CODE_INVERTED_EXCLAMATION_MARK = 161;
    public static final int CODE_INVERTED_QUESTION_MARK = 191;
    public static final int CODE_LANGUAGE_SWITCH = -10;
    public static final int CODE_OUTPUT_TEXT = -4;
    public static final int CODE_PERCENT = 37;
    public static final int CODE_PERIOD = 46;
    public static final int CODE_PLUS = 43;
    public static final int CODE_SETTINGS = -6;
    public static final int CODE_SHIFT = -1;
    public static final int CODE_SHIFT_ENTER = -12;
    public static final int CODE_SHORTCUT = -7;
    public static final int CODE_SINGLE_QUOTE = 39;
    public static final int CODE_SLASH = 47;
    public static final int CODE_SPACE = 32;
    public static final int CODE_SWITCH_ALPHA_SYMBOL = -3;
    public static final int CODE_SYMBOL_SHIFT = -13;
    public static final int CODE_TAB = 9;
    public static final int CODE_TILDE = 126;
    public static final int CODE_UNSPECIFIED = -15;
    public static final int CODE_VERTICAL_BAR = 124;
    public static final int CUSTOM_CODE_SHOW_INPUT_METHOD_PICKER = 1;
    public static final int DECODER_MAX_SCORE = 1000000000;
    public static final int DECODER_SCORE_SCALAR = 1000000;
    public static final int DEFAULT_GESTURE_POINTS_CAPACITY = 128;
    public static final int DELETE_ACCELERATE_AT = 20;
    public static final int EDITOR_CONTENTS_CACHE_SIZE = 1024;
    public static final int EVENT_BACKSPACE = 1;
    public static final int EVENT_REJECTION = 2;
    public static final int EVENT_REVERT = 3;
    public static final int EXTERNAL_KEYBOARD_COORDINATE = -4;
    public static final int GET_SUGGESTED_WORDS_TIMEOUT = 200;
    public static final int LONG_PRESS_MILLISECONDS = 200;
    public static final int MAX_CHARACTERS_FOR_RECAPITALIZATION = 102400;
    public static final int MAX_IME_DECODER_RESULTS = 20;
    public static final int NOT_A_CODE = -1;
    public static final int NOT_A_COORDINATE = -1;
    public static final int NOT_A_CURSOR_POSITION = -1;
    public static final String REGEXP_PERIOD = "\\.";
    public static final int SCREEN_METRICS_LARGE_PHONE = 1;
    public static final int SCREEN_METRICS_LARGE_TABLET = 2;
    public static final int SCREEN_METRICS_SMALL_PHONE = 0;
    public static final int SCREEN_METRICS_SMALL_TABLET = 3;
    public static final String STRING_SPACE = " ";
    public static final int SUGGESTION_STRIP_COORDINATE = -2;
    public static final String WORD_SEPARATOR = " ";

    public static final class Color {
        public static final int ALPHA_OPAQUE = 255;
    }

    public static boolean isLetterCode(final int code) {
        return code >= 32;
    }

    public static boolean isPhone(final int screenMetrics) {
        return screenMetrics == 0 || screenMetrics == 1;
    }

    public static boolean isTablet(final int screenMetrics) {
        return screenMetrics == 3 || screenMetrics == 2;
    }

    public static boolean isValidCoordinate(final int coordinate) {
        return coordinate >= 0;
    }

    public static final class ImeOption {
        public static final String FORCE_ASCII = "forceAscii";
        public static final String NO_FLOATING_GESTURE_PREVIEW = "noGestureFloatingPreview";
        public static final String NO_MICROPHONE = "noMicrophoneKey";
        public static final String NO_MICROPHONE_COMPAT = "nm";
        public static final String NO_SETTINGS_KEY = "noSettingsKey";

        private ImeOption() {
        }
    }

    public static final class Subtype {
        public static final String KEYBOARD_MODE = "keyboard";

        public static final class ExtraValue {
            public static final String ASCII_CAPABLE = "AsciiCapable";
            public static final String COMBINING_RULES = "CombiningRules";
            public static final String EMOJI_CAPABLE = "EmojiCapable";
            public static final String ENABLED_WHEN_DEFAULT_IS_NOT_ASCII_CAPABLE = "EnabledWhenDefaultIsNotAsciiCapable";
            public static final String IS_ADDITIONAL_SUBTYPE = "isAdditionalSubtype";
            public static final String KEYBOARD_LAYOUT_SET = "KeyboardLayoutSet";
            public static final String REQ_NETWORK_CONNECTIVITY = "requireNetworkConnectivity";
            public static final String UNTRANSLATABLE_STRING_IN_SUBTYPE_NAME = "UntranslatableReplacementStringInSubtypeName";

            private ExtraValue() {
            }
        }

        private Subtype() {
        }
    }

    public static final class TextUtils {
        public static final int CAP_MODE_OFF = 0;

        private TextUtils() {
        }
    }

    @Nonnull
    public static String printableCode(final int code) {
        if (code == -15) {
            return "unspec";
        }
        if (code == -14) {
            return "alpha";
        }
        if (code == 9) {
            return "tab";
        }
        if (code == 10) {
            return "enter";
        }
        if (code == 32) {
            return "space";
        }
        switch (code) {
            case CODE_SHIFT_ENTER /* -12 */:
                return "shiftEnter";
            case CODE_EMOJI /* -11 */:
                return SubtypeLocaleUtils.EMOJI;
            case CODE_LANGUAGE_SWITCH /* -10 */:
                return "languageSwitch";
            case -9:
                return "actionPrevious";
            case CODE_ACTION_NEXT /* -8 */:
                return "actionNext";
            case CODE_SHORTCUT /* -7 */:
                return UserDictionaryAddWordContents.EXTRA_SHORTCUT;
            case CODE_SETTINGS /* -6 */:
                return CarSettings.UserTableData.TABLE_NAME_SETTINGS;
            case CODE_DELETE /* -5 */:
                return "delete";
            case -4:
                return "text";
            case -3:
                return "symbol";
            case -2:
                return "capslock";
            case -1:
                return "shift";
            default:
                return code < 32 ? String.format("\\u%02X", Integer.valueOf(code)) : code < 256 ? String.format("%c", Integer.valueOf(code)) : code < 65536 ? String.format("\\u%04X", Integer.valueOf(code)) : String.format("\\U%05X", Integer.valueOf(code));
        }
    }

    @Nonnull
    public static String printableCodes(@Nonnull final int[] codes) {
        StringBuilder sb = new StringBuilder();
        int length = codes.length;
        int i = 0;
        boolean z = false;
        while (i < length) {
            int i2 = codes[i];
            if (i2 == -1) {
                break;
            }
            if (z) {
                sb.append(", ");
            }
            sb.append(printableCode(i2));
            i++;
            z = true;
        }
        return "[" + ((Object) sb) + "]";
    }

    private Constants() {
    }
}
