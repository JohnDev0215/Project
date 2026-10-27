package com.android.settingslib.inputmethod;

import android.content.Context;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodSubtype;
import androidx.preference.Preference;
import com.android.internal.annotations.VisibleForTesting;
import java.text.Collator;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class InputMethodSubtypePreference extends SwitchWithNoTextPreference {
    private final boolean mIsSystemLanguage;
    private final boolean mIsSystemLocale;

    public InputMethodSubtypePreference(Context context, InputMethodSubtype inputMethodSubtype, InputMethodInfo inputMethodInfo) {
        this(context, inputMethodInfo.getId() + inputMethodSubtype.hashCode(), InputMethodAndSubtypeUtil.getSubtypeLocaleNameAsSentence(inputMethodSubtype, context, inputMethodInfo), inputMethodSubtype.getLocaleObject(), context.getResources().getConfiguration().locale);
    }

    @VisibleForTesting
    InputMethodSubtypePreference(Context context, String str, CharSequence charSequence, Locale locale, Locale locale2) {
        super(context);
        setPersistent(false);
        setKey(str);
        setTitle(charSequence);
        if (locale == null) {
            this.mIsSystemLocale = false;
            this.mIsSystemLanguage = false;
        } else {
            boolean zEquals = locale.equals(locale2);
            this.mIsSystemLocale = zEquals;
            this.mIsSystemLanguage = zEquals || TextUtils.equals(locale.getLanguage(), locale2.getLanguage());
        }
    }

    public int compareTo(Preference preference, Collator collator) {
        if (this == preference) {
            return 0;
        }
        if (preference instanceof InputMethodSubtypePreference) {
            InputMethodSubtypePreference inputMethodSubtypePreference = (InputMethodSubtypePreference) preference;
            if (this.mIsSystemLocale && !inputMethodSubtypePreference.mIsSystemLocale) {
                return -1;
            }
            if (!this.mIsSystemLocale && inputMethodSubtypePreference.mIsSystemLocale) {
                return 1;
            }
            if (this.mIsSystemLanguage && !inputMethodSubtypePreference.mIsSystemLanguage) {
                return -1;
            }
            if (!this.mIsSystemLanguage && inputMethodSubtypePreference.mIsSystemLanguage) {
                return 1;
            }
            CharSequence title = getTitle();
            CharSequence title2 = preference.getTitle();
            boolean zIsEmpty = TextUtils.isEmpty(title);
            boolean zIsEmpty2 = TextUtils.isEmpty(title2);
            if (zIsEmpty || zIsEmpty2) {
                return (zIsEmpty ? -1 : 0) - (zIsEmpty2 ? -1 : 0);
            }
            return collator.compare(title.toString(), title2.toString());
        }
        return super.compareTo(preference);
    }
}
