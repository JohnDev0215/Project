package com.android.inputmethod.latin.userdictionary;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceGroup;
import android.provider.UserDictionary;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.common.LocaleUtils;
import java.util.Iterator;
import java.util.Locale;
import java.util.TreeSet;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class UserDictionaryList extends PreferenceFragment {
    public static final String USER_DICTIONARY_SETTINGS_INTENT_ACTION = "android.settings.USER_DICTIONARY_SETTINGS";

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setPreferenceScreen(getPreferenceManager().createPreferenceScreen(getActivity()));
    }

    public static TreeSet<String> getUserDictionaryLocalesSet(final Activity activity) {
        Cursor cursorQuery = activity.getContentResolver().query(UserDictionary.Words.CONTENT_URI, new String[]{"locale"}, null, null, null);
        TreeSet<String> treeSet = new TreeSet<>();
        if (cursorQuery == null) {
            return null;
        }
        try {
            if (cursorQuery.moveToFirst()) {
                int columnIndex = cursorQuery.getColumnIndex("locale");
                do {
                    String string = cursorQuery.getString(columnIndex);
                    if (string == null) {
                        string = "";
                    }
                    treeSet.add(string);
                } while (cursorQuery.moveToNext());
            }
            cursorQuery.close();
            if (!UserDictionarySettings.IS_SHORTCUT_API_SUPPORTED) {
                treeSet.add("");
            }
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
            Iterator<InputMethodInfo> it = inputMethodManager.getEnabledInputMethodList().iterator();
            while (it.hasNext()) {
                Iterator<InputMethodSubtype> it2 = inputMethodManager.getEnabledInputMethodSubtypeList(it.next(), true).iterator();
                while (it2.hasNext()) {
                    String locale = it2.next().getLocale();
                    if (!TextUtils.isEmpty(locale)) {
                        treeSet.add(locale);
                    }
                }
            }
            if (!treeSet.contains(Locale.getDefault().getLanguage().toString())) {
                treeSet.add(Locale.getDefault().toString());
            }
            return treeSet;
        } catch (Throwable th) {
            cursorQuery.close();
            throw th;
        }
    }

    protected void createUserDictSettings(final PreferenceGroup userDictGroup) {
        Activity activity = getActivity();
        userDictGroup.removeAll();
        TreeSet<String> userDictionaryLocalesSet = getUserDictionaryLocalesSet(activity);
        if (userDictionaryLocalesSet.size() > 1) {
            userDictionaryLocalesSet.add("");
        }
        if (userDictionaryLocalesSet.isEmpty()) {
            userDictGroup.addPreference(createUserDictionaryPreference(null));
            return;
        }
        Iterator<String> it = userDictionaryLocalesSet.iterator();
        while (it.hasNext()) {
            userDictGroup.addPreference(createUserDictionaryPreference(it.next()));
        }
    }

    protected Preference createUserDictionaryPreference(@Nullable final String localeString) {
        Preference preference = new Preference(getActivity());
        Intent intent = new Intent(USER_DICTIONARY_SETTINGS_INTENT_ACTION);
        if (localeString == null) {
            preference.setTitle(Locale.getDefault().getDisplayName());
        } else {
            if (localeString.isEmpty()) {
                preference.setTitle(getString(R.string.user_dict_settings_all_languages));
            } else {
                preference.setTitle(LocaleUtils.constructLocaleFromString(localeString).getDisplayName());
            }
            intent.putExtra("locale", localeString);
            preference.getExtras().putString("locale", localeString);
        }
        preference.setIntent(intent);
        preference.setFragment(UserDictionarySettings.class.getName());
        return preference;
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        createUserDictSettings(getPreferenceScreen());
    }
}
