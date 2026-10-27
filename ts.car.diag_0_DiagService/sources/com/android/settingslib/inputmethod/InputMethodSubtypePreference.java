package com.android.settingslib.inputmethod;

import android.content.Context;
import android.text.TextUtils;
import com.android.internal.annotations.VisibleForTesting;
import java.util.Locale;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class InputMethodSubtypePreference extends SwitchWithNoTextPreference {
    private final boolean X;

    @VisibleForTesting
    InputMethodSubtypePreference(Context context, String str, CharSequence charSequence, Locale locale, Locale locale2) {
        super(context);
        r0(false);
        l0(str);
        x0(charSequence);
        if (locale == null) {
            this.X = false;
            return;
        }
        boolean zEquals = locale.equals(locale2);
        this.X = zEquals;
        if (zEquals) {
            return;
        }
        TextUtils.equals(locale.getLanguage(), locale2.getLanguage());
    }
}
