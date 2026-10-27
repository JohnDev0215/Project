package com.android.inputmethod.latin.userdictionary;

import android.content.Context;
import android.text.TextUtils;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.common.LocaleUtils;

/* JADX INFO: loaded from: classes2.dex */
public class UserDictionarySettingsUtils {
    public static String getLocaleDisplayName(Context context, String localeStr) {
        if (TextUtils.isEmpty(localeStr)) {
            return context.getResources().getString(R.string.user_dict_settings_all_languages);
        }
        return LocaleUtils.constructLocaleFromString(localeStr).getDisplayName(context.getResources().getConfiguration().locale);
    }
}
