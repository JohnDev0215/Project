package com.android.car.developeroptions.inputmethod;

import android.content.Context;
import android.text.TextUtils;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;

/* JADX INFO: loaded from: classes.dex */
public class UserDictionarySettingsUtils {
    public static String getLocaleDisplayName(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return context.getResources().getString(R.string.user_dict_settings_all_languages);
        }
        return Utils.createLocaleFromString(str).getDisplayName(context.getResources().getConfiguration().locale);
    }
}
