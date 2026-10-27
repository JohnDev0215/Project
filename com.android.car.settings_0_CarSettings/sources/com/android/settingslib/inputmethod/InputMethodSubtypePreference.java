package com.android.settingslib.inputmethod;

import android.content.Context;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodSubtype;
import androidx.preference.Preference;
import java.text.Collator;
import java.util.Locale;

/* JADX INFO: loaded from: classes6.dex */
public class InputMethodSubtypePreference extends SwitchWithNoTextPreference {
    private final boolean mIsSystemLanguage;
    private final boolean mIsSystemLocale;

    public InputMethodSubtypePreference(Context context, InputMethodSubtype subtype, InputMethodInfo imi) {
        this(context, imi.getId() + subtype.hashCode(), InputMethodAndSubtypeUtil.getSubtypeLocaleNameAsSentence(subtype, context, imi), subtype.getLocaleObject(), context.getResources().getConfiguration().locale);
    }

    InputMethodSubtypePreference(Context context, String prefKey, CharSequence title, Locale subtypeLocale, Locale systemLocale) {
        super(context);
        setPersistent(false);
        setKey(prefKey);
        setTitle(title);
        if (subtypeLocale == null) {
            this.mIsSystemLocale = false;
            this.mIsSystemLanguage = false;
        } else {
            boolean zEquals = subtypeLocale.equals(systemLocale);
            this.mIsSystemLocale = zEquals;
            this.mIsSystemLanguage = zEquals || TextUtils.equals(subtypeLocale.getLanguage(), systemLocale.getLanguage());
        }
    }

    public int compareTo(Preference rhs, Collator collator) {
        if (this == rhs) {
            return 0;
        }
        if (rhs instanceof InputMethodSubtypePreference) {
            InputMethodSubtypePreference rhsPref = (InputMethodSubtypePreference) rhs;
            if (this.mIsSystemLocale && !rhsPref.mIsSystemLocale) {
                return -1;
            }
            if (!this.mIsSystemLocale && rhsPref.mIsSystemLocale) {
                return 1;
            }
            if (this.mIsSystemLanguage && !rhsPref.mIsSystemLanguage) {
                return -1;
            }
            if (!this.mIsSystemLanguage && rhsPref.mIsSystemLanguage) {
                return 1;
            }
            CharSequence title = getTitle();
            CharSequence rhsTitle = rhs.getTitle();
            boolean emptyTitle = TextUtils.isEmpty(title);
            boolean rhsEmptyTitle = TextUtils.isEmpty(rhsTitle);
            if (emptyTitle || rhsEmptyTitle) {
                return (emptyTitle ? -1 : 0) - (rhsEmptyTitle ? -1 : 0);
            }
            return collator.compare(title.toString(), rhsTitle.toString());
        }
        return super.compareTo(rhs);
    }
}
