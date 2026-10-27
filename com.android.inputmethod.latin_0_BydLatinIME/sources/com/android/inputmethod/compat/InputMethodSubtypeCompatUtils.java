package com.android.inputmethod.compat;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.InputMethodSubtype;
import com.android.inputmethod.latin.RichInputMethodSubtype;
import com.android.inputmethod.latin.common.Constants;
import com.android.inputmethod.latin.common.LocaleUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class InputMethodSubtypeCompatUtils {
    private static final Constructor<?> CONSTRUCTOR_INPUT_METHOD_SUBTYPE;
    private static final Method GET_LANGUAGE_TAG;
    private static final Method METHOD_isAsciiCapable;
    private static final String TAG = "InputMethodSubtypeCompatUtils";

    static {
        String simpleName = InputMethodSubtypeCompatUtils.class.getSimpleName();
        Constructor<?> constructor = CompatUtils.getConstructor(InputMethodSubtype.class, Integer.TYPE, Integer.TYPE, String.class, String.class, String.class, Boolean.TYPE, Boolean.TYPE, Integer.TYPE);
        CONSTRUCTOR_INPUT_METHOD_SUBTYPE = constructor;
        if (Build.VERSION.SDK_INT >= 17 && constructor == null) {
            Log.w(simpleName, "Warning!!! Constructor is not defined.");
        }
        METHOD_isAsciiCapable = CompatUtils.getMethod(InputMethodSubtype.class, "isAsciiCapable", new Class[0]);
        GET_LANGUAGE_TAG = CompatUtils.getMethod(InputMethodSubtype.class, "getLanguageTag", new Class[0]);
    }

    private InputMethodSubtypeCompatUtils() {
    }

    @Nonnull
    public static InputMethodSubtype newInputMethodSubtype(int nameId, int iconId, String locale, String mode, String extraValue, boolean isAuxiliary, boolean overridesImplicitlyEnabledSubtype, int id) {
        Constructor<?> constructor = CONSTRUCTOR_INPUT_METHOD_SUBTYPE;
        if (constructor == null || Build.VERSION.SDK_INT < 17) {
            return new InputMethodSubtype(nameId, iconId, locale, mode, extraValue, isAuxiliary, overridesImplicitlyEnabledSubtype);
        }
        return (InputMethodSubtype) CompatUtils.newInstance(constructor, Integer.valueOf(nameId), Integer.valueOf(iconId), locale, mode, extraValue, Boolean.valueOf(isAuxiliary), Boolean.valueOf(overridesImplicitlyEnabledSubtype), Integer.valueOf(id));
    }

    public static boolean isAsciiCapable(final RichInputMethodSubtype subtype) {
        return isAsciiCapable(subtype.getRawSubtype());
    }

    public static boolean isAsciiCapable(final InputMethodSubtype subtype) {
        return isAsciiCapableWithAPI(subtype) || subtype.containsExtraValueKey(Constants.Subtype.ExtraValue.ASCII_CAPABLE);
    }

    public static Locale getLocaleObject(final InputMethodSubtype subtype) {
        if (Build.VERSION.SDK_INT >= 21) {
            String str = (String) CompatUtils.invoke(subtype, null, GET_LANGUAGE_TAG, new Object[0]);
            if (!TextUtils.isEmpty(str)) {
                return Locale.forLanguageTag(str);
            }
        }
        return LocaleUtils.constructLocaleFromString(subtype.getLocale());
    }

    public static boolean isAsciiCapableWithAPI(final InputMethodSubtype subtype) {
        return ((Boolean) CompatUtils.invoke(subtype, false, METHOD_isAsciiCapable, new Object[0])).booleanValue();
    }
}
