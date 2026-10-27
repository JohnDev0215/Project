package com.android.car.settings.inputmethod;

import android.app.admin.DevicePolicyManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import com.android.settingslib.inputmethod.InputMethodAndSubtypeUtil;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes5.dex */
public final class InputMethodUtil {
    public static final String GOOGLE_VOICE_TYPING = "com.google.android.carassistant";
    public static final char INPUT_METHOD_DELIMITER = ':';
    public static final TextUtils.SimpleStringSplitter sInputMethodSplitter = new TextUtils.SimpleStringSplitter(':');
    static final Drawable NO_ICON = new ColorDrawable(0);

    private InputMethodUtil() {
    }

    public static List<InputMethodInfo> getPermittedAndEnabledInputMethodList(InputMethodManager imm, DevicePolicyManager dpm) {
        List<InputMethodInfo> inputMethodInfos = imm.getEnabledInputMethodList();
        if (inputMethodInfos != null) {
            final List<String> permittedList = dpm.getPermittedInputMethodsForCurrentUser();
            return (List) inputMethodInfos.stream().filter(new Predicate() { // from class: com.android.car.settings.inputmethod.-$$Lambda$InputMethodUtil$PYAYMws0fJKJSpKxxKC6dUwE00E
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return InputMethodUtil.lambda$getPermittedAndEnabledInputMethodList$0(permittedList, (InputMethodInfo) obj);
                }
            }).collect(Collectors.toList());
        }
        return inputMethodInfos;
    }

    static /* synthetic */ boolean lambda$getPermittedAndEnabledInputMethodList$0(List permittedList, InputMethodInfo info) {
        boolean isAllowedByOrganization = permittedList == null || permittedList.contains(info.getPackageName());
        boolean isGoogleVoiceTyping = info.getPackageName().equals(GOOGLE_VOICE_TYPING);
        return isAllowedByOrganization && !isGoogleVoiceTyping;
    }

    public static Drawable getPackageIcon(PackageManager packageManager, InputMethodInfo inputMethodInfo) {
        try {
            Drawable icon = packageManager.getApplicationIcon(inputMethodInfo.getPackageName());
            return icon;
        } catch (PackageManager.NameNotFoundException e) {
            Drawable icon2 = NO_ICON;
            return icon2;
        }
    }

    public static String getPackageLabel(PackageManager packageManager, InputMethodInfo inputMethodInfo) {
        return inputMethodInfo.loadLabel(packageManager).toString();
    }

    public static String getSummaryString(Context context, InputMethodManager inputMethodManager, InputMethodInfo inputMethodInfo) {
        List<InputMethodSubtype> subtypes = inputMethodManager.getEnabledInputMethodSubtypeList(inputMethodInfo, true);
        return InputMethodAndSubtypeUtil.getSubtypeLocaleNameListAsSentence(subtypes, context, inputMethodInfo);
    }

    public static boolean isInputMethodEnabled(ContentResolver resolver, InputMethodInfo inputMethodInfo) {
        sInputMethodSplitter.setString(getEnabledInputMethodsConcatenatedIds(resolver));
        while (sInputMethodSplitter.hasNext()) {
            String inputMethodId = sInputMethodSplitter.next();
            if (inputMethodId.equals(inputMethodInfo.getId())) {
                return true;
            }
        }
        return false;
    }

    public static void enableInputMethod(ContentResolver resolver, InputMethodInfo inputMethodInfo) {
        if (isInputMethodEnabled(resolver, inputMethodInfo)) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(getEnabledInputMethodsConcatenatedIds(resolver));
        if (!builder.toString().isEmpty()) {
            builder.append(':');
        }
        builder.append(inputMethodInfo.getId());
        setEnabledInputMethodsConcatenatedIds(resolver, builder.toString());
    }

    public static void disableInputMethod(Context context, InputMethodManager inputMethodManager, InputMethodInfo inputMethodInfo) {
        List<InputMethodInfo> enabledInputMethodInfos = inputMethodManager.getEnabledInputMethodList();
        StringBuilder builder = new StringBuilder();
        boolean foundAnotherEnabledDefaultInputMethod = false;
        boolean isSystemDefault = isDefaultInputMethod(context.getContentResolver(), inputMethodInfo);
        for (InputMethodInfo enabledInputMethodInfo : enabledInputMethodInfos) {
            if (!enabledInputMethodInfo.getId().equals(inputMethodInfo.getId())) {
                if (builder.length() > 0) {
                    builder.append(':');
                }
                builder.append(enabledInputMethodInfo.getId());
                if (isSystemDefault && enabledInputMethodInfo.isDefault(context)) {
                    foundAnotherEnabledDefaultInputMethod = true;
                    setDefaultInputMethodId(context.getContentResolver(), enabledInputMethodInfo.getId());
                }
            }
        }
        if (isSystemDefault && !foundAnotherEnabledDefaultInputMethod) {
            return;
        }
        setEnabledInputMethodsConcatenatedIds(context.getContentResolver(), builder.toString());
    }

    private static String getEnabledInputMethodsConcatenatedIds(ContentResolver resolver) {
        return Settings.Secure.getString(resolver, "enabled_input_methods");
    }

    private static String getDefaultInputMethodId(ContentResolver resolver) {
        return Settings.Secure.getString(resolver, "default_input_method");
    }

    private static boolean isDefaultInputMethod(ContentResolver resolver, InputMethodInfo inputMethodInfo) {
        return inputMethodInfo.getId().equals(getDefaultInputMethodId(resolver));
    }

    private static void setEnabledInputMethodsConcatenatedIds(ContentResolver resolver, String enabledInputMethodIds) {
        Settings.Secure.putString(resolver, "enabled_input_methods", enabledInputMethodIds);
    }

    private static void setDefaultInputMethodId(ContentResolver resolver, String defaultInputMethodId) {
        Settings.Secure.putString(resolver, "default_input_method", defaultInputMethodId);
    }
}
