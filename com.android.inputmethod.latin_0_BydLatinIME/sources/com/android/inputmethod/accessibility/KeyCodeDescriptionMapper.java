package com.android.inputmethod.accessibility;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseIntArray;
import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.keyboard.Keyboard;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.common.StringUtils;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
final class KeyCodeDescriptionMapper {
    private static final int OBSCURED_KEY_RES_ID = 2131624292;
    private static final String SPOKEN_EMOJI_RESOURCE_NAME_FORMAT = "spoken_emoji_%04X";
    private static final String SPOKEN_EMOTICON_CODE_POINT_FORMAT = "_%02X";
    private static final String SPOKEN_EMOTICON_RESOURCE_NAME_PREFIX = "spoken_emoticon";
    private static final String SPOKEN_LETTER_RESOURCE_NAME_FORMAT = "spoken_accented_letter_%04X";
    private static final String SPOKEN_SYMBOL_RESOURCE_NAME_FORMAT = "spoken_symbol_%04X";
    private static final String TAG = "KeyCodeDescriptionMapper";
    private static final KeyCodeDescriptionMapper sInstance = new KeyCodeDescriptionMapper();
    private final SparseIntArray mKeyCodeMap;

    public static KeyCodeDescriptionMapper getInstance() {
        return sInstance;
    }

    private KeyCodeDescriptionMapper() {
        SparseIntArray sparseIntArray = new SparseIntArray();
        this.mKeyCodeMap = sparseIntArray;
        sparseIntArray.put(32, R.string.spoken_description_space);
        sparseIntArray.put(-5, R.string.spoken_description_delete);
        sparseIntArray.put(10, R.string.spoken_description_return);
        sparseIntArray.put(-6, R.string.spoken_description_settings);
        sparseIntArray.put(-1, R.string.spoken_description_shift);
        sparseIntArray.put(-7, R.string.spoken_description_mic);
        sparseIntArray.put(-3, R.string.spoken_description_to_symbol);
        sparseIntArray.put(9, R.string.spoken_description_tab);
        sparseIntArray.put(-10, R.string.spoken_description_language_switch);
        sparseIntArray.put(-8, R.string.spoken_description_action_next);
        sparseIntArray.put(-9, R.string.spoken_description_action_previous);
        sparseIntArray.put(-11, R.string.spoken_description_emoji);
        sparseIntArray.put(73, R.string.spoken_letter_0049);
        sparseIntArray.put(304, R.string.spoken_letter_0130);
    }

    public String getDescriptionForKey(final Context context, final Keyboard keyboard, final Key key, final boolean shouldObscure) {
        String descriptionForSwitchAlphaSymbol;
        int code = key.getCode();
        if (code == -3 && (descriptionForSwitchAlphaSymbol = getDescriptionForSwitchAlphaSymbol(context, keyboard)) != null) {
            return descriptionForSwitchAlphaSymbol;
        }
        if (code == -1) {
            return getDescriptionForShiftKey(context, keyboard);
        }
        if (code == 10) {
            return getDescriptionForActionKey(context, keyboard, key);
        }
        if (code == -4) {
            String outputText = key.getOutputText();
            String spokenEmoticonDescription = getSpokenEmoticonDescription(context, outputText);
            return TextUtils.isEmpty(spokenEmoticonDescription) ? outputText : spokenEmoticonDescription;
        }
        if (code == -15) {
            return null;
        }
        boolean z = Character.isDefined(code) && !Character.isISOControl(code);
        if (shouldObscure && z) {
            return context.getString(R.string.spoken_description_dot);
        }
        String descriptionForCodePoint = getDescriptionForCodePoint(context, code);
        if (descriptionForCodePoint != null) {
            return descriptionForCodePoint;
        }
        if (!TextUtils.isEmpty(key.getLabel())) {
            return key.getLabel();
        }
        return context.getString(R.string.spoken_description_unknown);
    }

    private static String getDescriptionForSwitchAlphaSymbol(final Context context, final Keyboard keyboard) {
        int i = keyboard.mId.mElementId;
        int i2 = R.string.spoken_description_to_symbol;
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 7:
                break;
            case 5:
            case 6:
                i2 = R.string.spoken_description_to_alpha;
                break;
            case 8:
                i2 = R.string.spoken_description_to_numeric;
                break;
            default:
                Log.e(TAG, "Missing description for keyboard element ID:" + i);
                return null;
        }
        return context.getString(i2);
    }

    private static String getDescriptionForShiftKey(final Context context, final Keyboard keyboard) {
        int i;
        switch (keyboard.mId.mElementId) {
            case 1:
            case 2:
                i = R.string.spoken_description_shift_shifted;
                break;
            case 3:
            case 4:
                i = R.string.spoken_description_caps_lock;
                break;
            case 5:
                i = R.string.spoken_description_symbols_shift;
                break;
            case 6:
                i = R.string.spoken_description_symbols_shift_shifted;
                break;
            default:
                i = R.string.spoken_description_shift;
                break;
        }
        return context.getString(i);
    }

    private static String getDescriptionForActionKey(final Context context, final Keyboard keyboard, final Key key) {
        int i;
        int iImeAction = keyboard.mId.imeAction();
        if (!TextUtils.isEmpty(key.getLabel())) {
            return key.getLabel().trim();
        }
        switch (iImeAction) {
            case 2:
                i = R.string.label_go_key;
                break;
            case 3:
                i = R.string.spoken_description_search;
                break;
            case 4:
                i = R.string.label_send_key;
                break;
            case 5:
                i = R.string.label_next_key;
                break;
            case 6:
                i = R.string.label_done_key;
                break;
            case 7:
                i = R.string.label_previous_key;
                break;
            default:
                i = R.string.spoken_description_return;
                break;
        }
        return context.getString(i);
    }

    public String getDescriptionForCodePoint(final Context context, final int codePoint) {
        int iIndexOfKey = this.mKeyCodeMap.indexOfKey(codePoint);
        if (iIndexOfKey >= 0) {
            return context.getString(this.mKeyCodeMap.valueAt(iIndexOfKey));
        }
        String spokenAccentedLetterDescription = getSpokenAccentedLetterDescription(context, codePoint);
        if (spokenAccentedLetterDescription != null) {
            return spokenAccentedLetterDescription;
        }
        String spokenSymbolDescription = getSpokenSymbolDescription(context, codePoint);
        if (spokenSymbolDescription != null) {
            return spokenSymbolDescription;
        }
        String spokenEmojiDescription = getSpokenEmojiDescription(context, codePoint);
        if (spokenEmojiDescription != null) {
            return spokenEmojiDescription;
        }
        if (!Character.isDefined(codePoint) || Character.isISOControl(codePoint)) {
            return null;
        }
        return StringUtils.newSingleCodePointString(codePoint);
    }

    private String getSpokenAccentedLetterDescription(final Context context, final int code) {
        int spokenDescriptionId;
        boolean zIsUpperCase = Character.isUpperCase(code);
        if (zIsUpperCase) {
            code = Character.toLowerCase(code);
        }
        int iIndexOfKey = this.mKeyCodeMap.indexOfKey(code);
        if (iIndexOfKey >= 0) {
            spokenDescriptionId = this.mKeyCodeMap.valueAt(iIndexOfKey);
        } else {
            spokenDescriptionId = getSpokenDescriptionId(context, code, SPOKEN_LETTER_RESOURCE_NAME_FORMAT);
        }
        if (spokenDescriptionId == 0) {
            return null;
        }
        String string = context.getString(spokenDescriptionId);
        return zIsUpperCase ? context.getString(R.string.spoken_description_upper_case, string) : string;
    }

    private String getSpokenSymbolDescription(final Context context, final int code) {
        int spokenDescriptionId = getSpokenDescriptionId(context, code, SPOKEN_SYMBOL_RESOURCE_NAME_FORMAT);
        if (spokenDescriptionId == 0) {
            return null;
        }
        String string = context.getString(spokenDescriptionId);
        return !TextUtils.isEmpty(string) ? string : context.getString(R.string.spoken_symbol_unknown);
    }

    private String getSpokenEmojiDescription(final Context context, final int code) {
        int spokenDescriptionId = getSpokenDescriptionId(context, code, SPOKEN_EMOJI_RESOURCE_NAME_FORMAT);
        if (spokenDescriptionId == 0) {
            return null;
        }
        String string = context.getString(spokenDescriptionId);
        return !TextUtils.isEmpty(string) ? string : context.getString(R.string.spoken_emoji_unknown);
    }

    private int getSpokenDescriptionId(final Context context, final int code, final String resourceNameFormat) {
        String str = String.format(Locale.ROOT, resourceNameFormat, Integer.valueOf(code));
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier(str, "string", resources.getResourcePackageName(R.string.spoken_description_unknown));
        if (identifier != 0) {
            this.mKeyCodeMap.append(code, identifier);
        }
        return identifier;
    }

    private static String getSpokenEmoticonDescription(final Context context, final String outputText) {
        StringBuilder sb = new StringBuilder(SPOKEN_EMOTICON_RESOURCE_NAME_PREFIX);
        int length = outputText.length();
        int iOffsetByCodePoints = 0;
        while (iOffsetByCodePoints < length) {
            sb.append(String.format(Locale.ROOT, SPOKEN_EMOTICON_CODE_POINT_FORMAT, Integer.valueOf(outputText.codePointAt(iOffsetByCodePoints))));
            iOffsetByCodePoints = outputText.offsetByCodePoints(iOffsetByCodePoints, 1);
        }
        String string = sb.toString();
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier(string, "string", resources.getResourcePackageName(R.string.spoken_description_unknown));
        if (identifier == 0) {
            return null;
        }
        return resources.getString(identifier);
    }
}
