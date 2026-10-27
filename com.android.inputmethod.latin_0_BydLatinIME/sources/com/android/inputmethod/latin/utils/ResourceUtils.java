package com.android.inputmethod.latin.utils;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.settings.SettingsValues;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.PatternSyntaxException;

/* JADX INFO: loaded from: classes2.dex */
public final class ResourceUtils {
    private static final String[] BUILD_KEYS_AND_VALUES;
    private static final String TAG = "ResourceUtils";
    public static final int UNDEFINED_DIMENSION = -1;
    public static final float UNDEFINED_RATIO = -1.0f;
    private static final HashMap<String, String> sBuildKeyValues;
    private static final String sBuildKeyValuesDebugString;
    private static final HashMap<String, String> sDeviceOverrideValueMap = new HashMap<>();

    public static boolean isValidDimensionPixelOffset(final int dimension) {
        return dimension >= 0;
    }

    public static boolean isValidDimensionPixelSize(final int dimension) {
        return dimension > 0;
    }

    public static boolean isValidFraction(final float fraction) {
        return fraction >= 0.0f;
    }

    private ResourceUtils() {
    }

    static {
        String[] strArr = {"HARDWARE", Build.HARDWARE, "MODEL", Build.MODEL, "BRAND", Build.BRAND, "MANUFACTURER", Build.MANUFACTURER};
        BUILD_KEYS_AND_VALUES = strArr;
        sBuildKeyValues = new HashMap<>();
        ArrayList arrayList = new ArrayList();
        int length = strArr.length / 2;
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            String[] strArr2 = BUILD_KEYS_AND_VALUES;
            String str = strArr2[i2];
            String str2 = strArr2[i2 + 1];
            sBuildKeyValues.put(str, str2);
            arrayList.add(str + '=' + str2);
        }
        sBuildKeyValuesDebugString = "[" + TextUtils.join(" ", arrayList) + "]";
    }

    public static String getDeviceOverrideValue(final Resources res, final int overrideResId, final String defaultValue) {
        String str = overrideResId + "-" + res.getConfiguration().orientation;
        HashMap<String, String> map = sDeviceOverrideValueMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        String strFindConstantForKeyValuePairs = findConstantForKeyValuePairs(sBuildKeyValues, res.getStringArray(overrideResId));
        if (strFindConstantForKeyValuePairs != null) {
            Log.i(TAG, "Find override value: resource=" + res.getResourceEntryName(overrideResId) + " build=" + sBuildKeyValuesDebugString + " override=" + strFindConstantForKeyValuePairs);
            map.put(str, strFindConstantForKeyValuePairs);
            return strFindConstantForKeyValuePairs;
        }
        map.put(str, defaultValue);
        return defaultValue;
    }

    static class DeviceOverridePatternSyntaxError extends Exception {
        public DeviceOverridePatternSyntaxError(final String message, final String expression) {
            this(message, expression, null);
        }

        public DeviceOverridePatternSyntaxError(final String message, final String expression, final Throwable throwable) {
            super(message + ": " + expression, throwable);
        }
    }

    static String findConstantForKeyValuePairs(final HashMap<String, String> keyValuePairs, final String[] conditionConstantArray) {
        String strSubstring = null;
        if (conditionConstantArray != null && keyValuePairs != null) {
            for (String str : conditionConstantArray) {
                int iIndexOf = str.indexOf(44);
                if (iIndexOf < 0) {
                    Log.w(TAG, "Array element has no comma: " + str);
                } else {
                    String strSubstring2 = str.substring(0, iIndexOf);
                    if (strSubstring2.isEmpty()) {
                        Log.w(TAG, "Array element has no condition: " + str);
                    } else {
                        try {
                            if (fulfillsCondition(keyValuePairs, strSubstring2) && strSubstring == null) {
                                strSubstring = str.substring(iIndexOf + 1);
                            }
                        } catch (DeviceOverridePatternSyntaxError e) {
                            Log.w(TAG, "Syntax error, ignored", e);
                        }
                    }
                }
            }
        }
        return strSubstring;
    }

    private static boolean fulfillsCondition(final HashMap<String, String> keyValuePairs, final String condition) throws DeviceOverridePatternSyntaxError {
        boolean z = true;
        for (String str : condition.split(":")) {
            int iIndexOf = str.indexOf(61);
            if (iIndexOf < 0) {
                throw new DeviceOverridePatternSyntaxError("Pattern has no '='", condition);
            }
            String str2 = keyValuePairs.get(str.substring(0, iIndexOf));
            if (str2 == null) {
                throw new DeviceOverridePatternSyntaxError("Unknown key", condition);
            }
            try {
                if (!str2.matches(str.substring(iIndexOf + 1))) {
                    z = false;
                }
            } catch (PatternSyntaxException e) {
                throw new DeviceOverridePatternSyntaxError("Syntax error", condition, e);
            }
        }
        return z;
    }

    public static int getDefaultKeyboardWidth(final Resources res) {
        return res.getDisplayMetrics().widthPixels;
    }

    public static int getKeyboardHeight(final Resources res, final SettingsValues settingsValues) {
        int defaultKeyboardHeight = getDefaultKeyboardHeight(res);
        return settingsValues.mHasKeyboardResize ? (int) (defaultKeyboardHeight * settingsValues.mKeyboardHeightScale) : defaultKeyboardHeight;
    }

    public static int getDefaultKeyboardHeight(final Resources res) {
        float dimension;
        DisplayMetrics displayMetrics = res.getDisplayMetrics();
        String deviceOverrideValue = getDeviceOverrideValue(res, R.array.keyboard_heights, null);
        if (TextUtils.isEmpty(deviceOverrideValue)) {
            dimension = res.getDimension(R.dimen.config_default_keyboard_height);
        } else {
            dimension = Float.parseFloat(deviceOverrideValue) * displayMetrics.density;
        }
        float fraction = res.getFraction(R.fraction.config_max_keyboard_height, displayMetrics.heightPixels, displayMetrics.heightPixels);
        float fraction2 = res.getFraction(R.fraction.config_min_keyboard_height, displayMetrics.heightPixels, displayMetrics.heightPixels);
        if (fraction2 < 0.0f) {
            fraction2 = -res.getFraction(R.fraction.config_min_keyboard_height, displayMetrics.widthPixels, displayMetrics.widthPixels);
        }
        return (int) Math.max(Math.min(dimension, fraction), fraction2);
    }

    public static float getFloatFromFraction(final Resources res, final int fractionResId) {
        return res.getFraction(fractionResId, 1, 1);
    }

    public static float getFraction(final TypedArray a, final int index, final float defValue) {
        TypedValue typedValuePeekValue = a.peekValue(index);
        return (typedValuePeekValue == null || !isFractionValue(typedValuePeekValue)) ? defValue : a.getFraction(index, 1, 1, defValue);
    }

    public static float getFraction(final TypedArray a, final int index) {
        return getFraction(a, index, -1.0f);
    }

    public static int getDimensionPixelSize(final TypedArray a, final int index) {
        TypedValue typedValuePeekValue = a.peekValue(index);
        if (typedValuePeekValue == null || !isDimensionValue(typedValuePeekValue)) {
            return -1;
        }
        return a.getDimensionPixelSize(index, -1);
    }

    public static float getDimensionOrFraction(final TypedArray a, final int index, final int base, final float defValue) {
        TypedValue typedValuePeekValue = a.peekValue(index);
        if (typedValuePeekValue == null) {
            return defValue;
        }
        if (isFractionValue(typedValuePeekValue)) {
            return a.getFraction(index, base, base, defValue);
        }
        return isDimensionValue(typedValuePeekValue) ? a.getDimension(index, defValue) : defValue;
    }

    public static int getEnumValue(final TypedArray a, final int index, final int defValue) {
        TypedValue typedValuePeekValue = a.peekValue(index);
        return (typedValuePeekValue != null && isIntegerValue(typedValuePeekValue)) ? a.getInt(index, defValue) : defValue;
    }

    public static boolean isFractionValue(final TypedValue v) {
        return v.type == 6;
    }

    public static boolean isDimensionValue(final TypedValue v) {
        return v.type == 5;
    }

    public static boolean isIntegerValue(final TypedValue v) {
        return v.type >= 16 && v.type <= 31;
    }

    public static boolean isStringValue(final TypedValue v) {
        return v.type == 3;
    }
}
