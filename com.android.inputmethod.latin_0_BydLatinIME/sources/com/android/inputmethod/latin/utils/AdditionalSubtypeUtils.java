package com.android.inputmethod.latin.utils;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.InputMethodSubtype;
import com.android.inputmethod.compat.InputMethodSubtypeCompatUtils;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.common.Constants;
import com.android.inputmethod.latin.common.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class AdditionalSubtypeUtils {
    private static final InputMethodSubtype[] EMPTY_SUBTYPE_ARRAY = new InputMethodSubtype[0];
    private static final int INDEX_OF_EXTRA_VALUE = 2;
    private static final int INDEX_OF_KEYBOARD_LAYOUT = 1;
    private static final int INDEX_OF_LOCALE = 0;
    private static final int LENGTH_WITHOUT_EXTRA_VALUE = 2;
    private static final int LENGTH_WITH_EXTRA_VALUE = 3;
    private static final String LOCALE_AND_LAYOUT_SEPARATOR = ":";
    private static final String PREF_SUBTYPE_SEPARATOR = ";";
    private static final String TAG = "AdditionalSubtypeUtils";

    private AdditionalSubtypeUtils() {
    }

    public static boolean isAdditionalSubtype(final InputMethodSubtype subtype) {
        return subtype.containsExtraValueKey(Constants.Subtype.ExtraValue.IS_ADDITIONAL_SUBTYPE);
    }

    private static InputMethodSubtype createAdditionalSubtypeInternal(final String localeString, final String keyboardLayoutSetName, final boolean isAsciiCapable, final boolean isEmojiCapable) {
        return InputMethodSubtypeCompatUtils.newInputMethodSubtype(SubtypeLocaleUtils.getSubtypeNameId(localeString, keyboardLayoutSetName), R.drawable.ic_ime_switcher_dark, localeString, Constants.Subtype.KEYBOARD_MODE, getPlatformVersionDependentExtraValue(localeString, keyboardLayoutSetName, isAsciiCapable, isEmojiCapable), false, false, getPlatformVersionIndependentSubtypeId(localeString, keyboardLayoutSetName));
    }

    public static InputMethodSubtype createDummyAdditionalSubtype(final String localeString, final String keyboardLayoutSetName) {
        return createAdditionalSubtypeInternal(localeString, keyboardLayoutSetName, false, false);
    }

    public static InputMethodSubtype createAsciiEmojiCapableAdditionalSubtype(final String localeString, final String keyboardLayoutSetName) {
        return createAdditionalSubtypeInternal(localeString, keyboardLayoutSetName, true, true);
    }

    public static String getPrefSubtype(final InputMethodSubtype subtype) {
        String locale = subtype.getLocale();
        String keyboardLayoutSetName = SubtypeLocaleUtils.getKeyboardLayoutSetName(subtype);
        String strRemoveFromCommaSplittableTextIfExists = StringUtils.removeFromCommaSplittableTextIfExists("KeyboardLayoutSet=" + keyboardLayoutSetName, StringUtils.removeFromCommaSplittableTextIfExists(Constants.Subtype.ExtraValue.IS_ADDITIONAL_SUBTYPE, subtype.getExtraValue()));
        String str = locale + ":" + keyboardLayoutSetName;
        return strRemoveFromCommaSplittableTextIfExists.isEmpty() ? str : str + ":" + strRemoveFromCommaSplittableTextIfExists;
    }

    public static InputMethodSubtype[] createAdditionalSubtypesArray(final String prefSubtypes) {
        if (TextUtils.isEmpty(prefSubtypes)) {
            return EMPTY_SUBTYPE_ARRAY;
        }
        String[] strArrSplit = prefSubtypes.split(PREF_SUBTYPE_SEPARATOR);
        ArrayList arrayList = new ArrayList(strArrSplit.length);
        for (String str : strArrSplit) {
            String[] strArrSplit2 = str.split(":");
            if (strArrSplit2.length != 2 && strArrSplit2.length != 3) {
                Log.w(TAG, "Unknown additional subtype specified: " + str + " in " + prefSubtypes);
            } else {
                InputMethodSubtype inputMethodSubtypeCreateAsciiEmojiCapableAdditionalSubtype = createAsciiEmojiCapableAdditionalSubtype(strArrSplit2[0], strArrSplit2[1]);
                if (inputMethodSubtypeCreateAsciiEmojiCapableAdditionalSubtype.getNameResId() != R.string.subtype_generic) {
                    arrayList.add(inputMethodSubtypeCreateAsciiEmojiCapableAdditionalSubtype);
                }
            }
        }
        return (InputMethodSubtype[]) arrayList.toArray(new InputMethodSubtype[arrayList.size()]);
    }

    public static String createPrefSubtypes(final InputMethodSubtype[] subtypes) {
        if (subtypes == null || subtypes.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (InputMethodSubtype inputMethodSubtype : subtypes) {
            if (sb.length() > 0) {
                sb.append(PREF_SUBTYPE_SEPARATOR);
            }
            sb.append(getPrefSubtype(inputMethodSubtype));
        }
        return sb.toString();
    }

    public static String createPrefSubtypes(final String[] prefSubtypes) {
        if (prefSubtypes == null || prefSubtypes.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : prefSubtypes) {
            if (sb.length() > 0) {
                sb.append(PREF_SUBTYPE_SEPARATOR);
            }
            sb.append(str);
        }
        return sb.toString();
    }

    private static String getPlatformVersionDependentExtraValue(final String localeString, final String keyboardLayoutSetName, final boolean isAsciiCapable, final boolean isEmojiCapable) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("KeyboardLayoutSet=" + keyboardLayoutSetName);
        if (isAsciiCapable) {
            arrayList.add(Constants.Subtype.ExtraValue.ASCII_CAPABLE);
        }
        if (Build.VERSION.SDK_INT >= 16 && SubtypeLocaleUtils.isExceptionalLocale(localeString)) {
            arrayList.add("UntranslatableReplacementStringInSubtypeName=" + SubtypeLocaleUtils.getKeyboardLayoutSetDisplayName(keyboardLayoutSetName));
        }
        if (isEmojiCapable && Build.VERSION.SDK_INT >= 19) {
            arrayList.add(Constants.Subtype.ExtraValue.EMOJI_CAPABLE);
        }
        arrayList.add(Constants.Subtype.ExtraValue.IS_ADDITIONAL_SUBTYPE);
        return TextUtils.join(",", arrayList);
    }

    private static int getPlatformVersionIndependentSubtypeId(final String localeString, final String keyboardLayoutSetName) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("KeyboardLayoutSet=" + keyboardLayoutSetName);
        arrayList.add(Constants.Subtype.ExtraValue.ASCII_CAPABLE);
        if (SubtypeLocaleUtils.isExceptionalLocale(localeString)) {
            arrayList.add("UntranslatableReplacementStringInSubtypeName=" + SubtypeLocaleUtils.getKeyboardLayoutSetDisplayName(keyboardLayoutSetName));
        }
        arrayList.add(Constants.Subtype.ExtraValue.EMOJI_CAPABLE);
        arrayList.add(Constants.Subtype.ExtraValue.IS_ADDITIONAL_SUBTYPE);
        return Arrays.hashCode(new Object[]{localeString, Constants.Subtype.KEYBOARD_MODE, TextUtils.join(",", arrayList), false, false});
    }
}
