package com.android.inputmethod.latin.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import android.view.inputmethod.InputMethodSubtype;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.common.Constants;
import com.android.inputmethod.latin.common.LocaleUtils;
import com.android.inputmethod.latin.common.StringUtils;
import java.util.HashMap;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class SubtypeLocaleUtils {
    public static final String EMOJI = "emoji";
    public static final String NO_LANGUAGE = "zz";
    public static final String QWERTY = "qwerty";
    private static final String SUBTYPE_NAME_RESOURCE_GENERIC_PREFIX = "string/subtype_generic_";
    private static final String SUBTYPE_NAME_RESOURCE_IN_ROOT_LOCALE_PREFIX = "string/subtype_in_root_locale_";
    private static final String SUBTYPE_NAME_RESOURCE_NO_LANGUAGE_PREFIX = "string/subtype_no_language_";
    private static final String SUBTYPE_NAME_RESOURCE_PREFIX = "string/subtype_";
    private static final String SUBTYPE_NAME_RESOURCE_WITH_LAYOUT_PREFIX = "string/subtype_with_layout_";
    static final String TAG = "SubtypeLocaleUtils";
    public static final int UNKNOWN_KEYBOARD_LAYOUT = 2131625660;
    private static Resources sResources;
    private static final String RESOURCE_PACKAGE_NAME = R.class.getPackage().getName();
    private static volatile boolean sInitialized = false;
    private static final Object sInitializeLock = new Object();
    private static final HashMap<String, String> sKeyboardLayoutToDisplayNameMap = new HashMap<>();
    private static final HashMap<String, Integer> sKeyboardLayoutToNameIdsMap = new HashMap<>();
    private static final HashMap<String, Integer> sExceptionalLocaleDisplayedInRootLocale = new HashMap<>();
    private static final HashMap<String, Integer> sExceptionalLocaleToNameIdsMap = new HashMap<>();
    private static final HashMap<String, Integer> sExceptionalLocaleToWithLayoutNameIdsMap = new HashMap<>();
    private static final HashMap<String, String> sLocaleAndExtraValueToKeyboardLayoutSetMap = new HashMap<>();

    private SubtypeLocaleUtils() {
    }

    public static void init(final Context context) {
        synchronized (sInitializeLock) {
            if (!sInitialized) {
                initLocked(context);
                sInitialized = true;
            }
        }
    }

    private static void initLocked(final Context context) {
        Resources resources = context.getResources();
        sResources = resources;
        String[] stringArray = resources.getStringArray(R.array.predefined_layouts);
        String[] stringArray2 = resources.getStringArray(R.array.predefined_layout_display_names);
        int i = 0;
        for (int i2 = 0; i2 < stringArray.length; i2++) {
            String str = stringArray[i2];
            sKeyboardLayoutToDisplayNameMap.put(str, stringArray2[i2]);
            String str2 = SUBTYPE_NAME_RESOURCE_GENERIC_PREFIX + str;
            String str3 = RESOURCE_PACKAGE_NAME;
            int identifier = resources.getIdentifier(str2, null, str3);
            HashMap<String, Integer> map = sKeyboardLayoutToNameIdsMap;
            map.put(str, Integer.valueOf(identifier));
            map.put(getNoLanguageLayoutKey(str), Integer.valueOf(resources.getIdentifier(SUBTYPE_NAME_RESOURCE_NO_LANGUAGE_PREFIX + str, null, str3)));
        }
        for (String str4 : resources.getStringArray(R.array.subtype_locale_displayed_in_root_locale)) {
            sExceptionalLocaleDisplayedInRootLocale.put(str4, Integer.valueOf(resources.getIdentifier(SUBTYPE_NAME_RESOURCE_IN_ROOT_LOCALE_PREFIX + str4, null, RESOURCE_PACKAGE_NAME)));
        }
        for (String str5 : resources.getStringArray(R.array.subtype_locale_exception_keys)) {
            String str6 = SUBTYPE_NAME_RESOURCE_PREFIX + str5;
            String str7 = RESOURCE_PACKAGE_NAME;
            sExceptionalLocaleToNameIdsMap.put(str5, Integer.valueOf(resources.getIdentifier(str6, null, str7)));
            sExceptionalLocaleToWithLayoutNameIdsMap.put(str5, Integer.valueOf(resources.getIdentifier(SUBTYPE_NAME_RESOURCE_WITH_LAYOUT_PREFIX + str5, null, str7)));
        }
        String[] stringArray3 = resources.getStringArray(R.array.locale_and_extra_value_to_keyboard_layout_set_map);
        while (true) {
            int i3 = i + 1;
            if (i3 >= stringArray3.length) {
                return;
            }
            sLocaleAndExtraValueToKeyboardLayoutSetMap.put(stringArray3[i], stringArray3[i3]);
            i += 2;
        }
    }

    public static boolean isExceptionalLocale(final String localeString) {
        return sExceptionalLocaleToNameIdsMap.containsKey(localeString);
    }

    private static final String getNoLanguageLayoutKey(final String keyboardLayoutName) {
        return "zz_" + keyboardLayoutName;
    }

    public static int getSubtypeNameId(final String localeString, final String keyboardLayoutName) {
        if (Build.VERSION.SDK_INT >= 16 && isExceptionalLocale(localeString)) {
            return sExceptionalLocaleToWithLayoutNameIdsMap.get(localeString).intValue();
        }
        if (NO_LANGUAGE.equals(localeString)) {
            keyboardLayoutName = getNoLanguageLayoutKey(keyboardLayoutName);
        }
        Integer num = sKeyboardLayoutToNameIdsMap.get(keyboardLayoutName);
        return num == null ? R.string.subtype_generic : num.intValue();
    }

    @Nonnull
    public static Locale getDisplayLocaleOfSubtypeLocale(@Nonnull final String localeString) {
        if (NO_LANGUAGE.equals(localeString)) {
            return sResources.getConfiguration().locale;
        }
        if (sExceptionalLocaleDisplayedInRootLocale.containsKey(localeString)) {
            return Locale.ROOT;
        }
        return LocaleUtils.constructLocaleFromString(localeString);
    }

    public static String getSubtypeLocaleDisplayNameInSystemLocale(@Nonnull final String localeString) {
        return getSubtypeLocaleDisplayNameInternal(localeString, sResources.getConfiguration().locale);
    }

    @Nonnull
    public static String getSubtypeLocaleDisplayName(@Nonnull final String localeString) {
        return getSubtypeLocaleDisplayNameInternal(localeString, getDisplayLocaleOfSubtypeLocale(localeString));
    }

    @Nonnull
    public static String getSubtypeLanguageDisplayName(@Nonnull final String localeString) {
        Locale displayLocaleOfSubtypeLocale = getDisplayLocaleOfSubtypeLocale(localeString);
        if (!sExceptionalLocaleDisplayedInRootLocale.containsKey(localeString)) {
            localeString = LocaleUtils.constructLocaleFromString(localeString).getLanguage();
        }
        return getSubtypeLocaleDisplayNameInternal(localeString, displayLocaleOfSubtypeLocale);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0029  */
    @javax.annotation.Nonnull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String getSubtypeLocaleDisplayNameInternal(@javax.annotation.Nonnull final java.lang.String r2, @javax.annotation.Nonnull final java.util.Locale r3) {
        /*
            java.lang.String r0 = "zz"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L12
            android.content.res.Resources r2 = com.android.inputmethod.latin.utils.SubtypeLocaleUtils.sResources
            r3 = 2131625673(0x7f0e06c9, float:1.887856E38)
            java.lang.String r2 = r2.getString(r3)
            return r2
        L12:
            java.util.Locale r0 = java.util.Locale.ROOT
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L29
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = com.android.inputmethod.latin.utils.SubtypeLocaleUtils.sExceptionalLocaleDisplayedInRootLocale
            boolean r1 = r0.containsKey(r2)
            if (r1 == 0) goto L29
            java.lang.Object r0 = r0.get(r2)
            java.lang.Integer r0 = (java.lang.Integer) r0
            goto L39
        L29:
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = com.android.inputmethod.latin.utils.SubtypeLocaleUtils.sExceptionalLocaleToNameIdsMap
            boolean r1 = r0.containsKey(r2)
            if (r1 == 0) goto L38
            java.lang.Object r0 = r0.get(r2)
            java.lang.Integer r0 = (java.lang.Integer) r0
            goto L39
        L38:
            r0 = 0
        L39:
            if (r0 == 0) goto L49
            com.android.inputmethod.latin.utils.SubtypeLocaleUtils$1 r2 = new com.android.inputmethod.latin.utils.SubtypeLocaleUtils$1
            r2.<init>()
            android.content.res.Resources r0 = com.android.inputmethod.latin.utils.SubtypeLocaleUtils.sResources
            java.lang.Object r2 = r2.runInLocale(r0, r3)
            java.lang.String r2 = (java.lang.String) r2
            goto L51
        L49:
            java.util.Locale r2 = com.android.inputmethod.latin.common.LocaleUtils.constructLocaleFromString(r2)
            java.lang.String r2 = r2.getDisplayName(r3)
        L51:
            java.lang.String r2 = com.android.inputmethod.latin.common.StringUtils.capitalizeFirstCodePoint(r2, r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.inputmethod.latin.utils.SubtypeLocaleUtils.getSubtypeLocaleDisplayNameInternal(java.lang.String, java.util.Locale):java.lang.String");
    }

    @Nonnull
    private static String getReplacementString(@Nonnull final InputMethodSubtype subtype, @Nonnull final Locale displayLocale) {
        if (Build.VERSION.SDK_INT >= 16 && subtype.containsExtraValueKey(Constants.Subtype.ExtraValue.UNTRANSLATABLE_STRING_IN_SUBTYPE_NAME)) {
            return subtype.getExtraValueOf(Constants.Subtype.ExtraValue.UNTRANSLATABLE_STRING_IN_SUBTYPE_NAME);
        }
        return getSubtypeLocaleDisplayNameInternal(subtype.getLocale(), displayLocale);
    }

    @Nonnull
    public static String getSubtypeDisplayNameInSystemLocale(@Nonnull final InputMethodSubtype subtype) {
        return getSubtypeDisplayNameInternal(subtype, sResources.getConfiguration().locale);
    }

    @Nonnull
    public static String getSubtypeNameForLogging(@Nullable final InputMethodSubtype subtype) {
        return subtype == null ? "<null subtype>" : getSubtypeLocale(subtype) + "/" + getKeyboardLayoutSetName(subtype);
    }

    @Nonnull
    private static String getSubtypeDisplayNameInternal(@Nonnull final InputMethodSubtype subtype, @Nonnull final Locale displayLocale) {
        final String replacementString = getReplacementString(subtype, displayLocale);
        final int nameResId = subtype.getNameResId();
        return StringUtils.capitalizeFirstCodePoint(new RunInLocale<String>() { // from class: com.android.inputmethod.latin.utils.SubtypeLocaleUtils.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.android.inputmethod.latin.utils.RunInLocale
            public String job(final Resources res) {
                try {
                    return res.getString(nameResId, replacementString);
                } catch (Resources.NotFoundException unused) {
                    Log.w(SubtypeLocaleUtils.TAG, "Unknown subtype: mode=" + subtype.getMode() + " nameResId=" + subtype.getNameResId() + " locale=" + subtype.getLocale() + " extra=" + subtype.getExtraValue() + "\n" + DebugLogUtils.getStackTrace());
                    return "";
                }
            }
        }.runInLocale(sResources, displayLocale), displayLocale);
    }

    @Nonnull
    public static Locale getSubtypeLocale(@Nonnull final InputMethodSubtype subtype) {
        return LocaleUtils.constructLocaleFromString(subtype.getLocale());
    }

    @Nonnull
    public static String getKeyboardLayoutSetDisplayName(@Nonnull final InputMethodSubtype subtype) {
        return getKeyboardLayoutSetDisplayName(getKeyboardLayoutSetName(subtype));
    }

    @Nonnull
    public static String getKeyboardLayoutSetDisplayName(@Nonnull final String layoutName) {
        return sKeyboardLayoutToDisplayNameMap.get(layoutName);
    }

    @Nonnull
    public static String getKeyboardLayoutSetName(final InputMethodSubtype subtype) {
        String extraValueOf = subtype.getExtraValueOf(Constants.Subtype.ExtraValue.KEYBOARD_LAYOUT_SET);
        if (extraValueOf == null) {
            extraValueOf = sLocaleAndExtraValueToKeyboardLayoutSetMap.get(subtype.getLocale() + ":" + subtype.getExtraValue());
        }
        if (extraValueOf != null) {
            return extraValueOf;
        }
        Log.w(TAG, "KeyboardLayoutSet not found, use QWERTY: locale=" + subtype.getLocale() + " extraValue=" + subtype.getExtraValue());
        return QWERTY;
    }

    public static String getCombiningRulesExtraValue(final InputMethodSubtype subtype) {
        return subtype.getExtraValueOf(Constants.Subtype.ExtraValue.COMBINING_RULES);
    }
}
