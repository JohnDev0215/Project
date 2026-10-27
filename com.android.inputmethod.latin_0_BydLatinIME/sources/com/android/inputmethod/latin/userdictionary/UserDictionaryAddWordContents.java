package com.android.inputmethod.latin.userdictionary;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.common.LocaleUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.TreeSet;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class UserDictionaryAddWordContents {
    static final int CODE_ALREADY_PRESENT = 2;
    static final int CODE_CANCEL = 1;
    static final int CODE_WORD_ADDED = 0;
    public static final String EXTRA_LOCALE = "locale";
    public static final String EXTRA_MODE = "mode";
    public static final String EXTRA_ORIGINAL_SHORTCUT = "originalShortcut";
    public static final String EXTRA_ORIGINAL_WORD = "originalWord";
    public static final String EXTRA_SHORTCUT = "shortcut";
    public static final String EXTRA_WORD = "word";
    private static final int FREQUENCY_FOR_USER_DICTIONARY_ADDS = 250;
    private static final String[] HAS_WORD_PROJECTION = {"word"};
    private static final String HAS_WORD_SELECTION_ALL_LOCALES = "word=? AND locale is null";
    private static final String HAS_WORD_SELECTION_ONE_LOCALE = "word=? AND locale=?";
    public static final int MODE_EDIT = 0;
    public static final int MODE_INSERT = 1;
    private String mLocale;
    private final int mMode;
    private final String mOldShortcut;
    private final String mOldWord;
    private String mSavedShortcut;
    private String mSavedWord;
    private final EditText mShortcutEditText;
    private final EditText mWordEditText;

    UserDictionaryAddWordContents(final View view, final Bundle args) {
        EditText editText = (EditText) view.findViewById(R.id.user_dictionary_add_word_text);
        this.mWordEditText = editText;
        EditText editText2 = (EditText) view.findViewById(R.id.user_dictionary_add_shortcut);
        this.mShortcutEditText = editText2;
        if (!UserDictionarySettings.IS_SHORTCUT_API_SUPPORTED) {
            editText2.setVisibility(8);
            view.findViewById(R.id.user_dictionary_add_shortcut_label).setVisibility(8);
        }
        String string = args.getString("word");
        if (string != null) {
            editText.setText(string);
            editText.setSelection(editText.getText().length());
        }
        if (UserDictionarySettings.IS_SHORTCUT_API_SUPPORTED) {
            String string2 = args.getString(EXTRA_SHORTCUT);
            if (string2 != null && editText2 != null) {
                editText2.setText(string2);
            }
            this.mOldShortcut = args.getString(EXTRA_SHORTCUT);
        } else {
            this.mOldShortcut = null;
        }
        this.mMode = args.getInt(EXTRA_MODE);
        this.mOldWord = args.getString("word");
        updateLocale(args.getString("locale"));
    }

    UserDictionaryAddWordContents(final View view, final UserDictionaryAddWordContents oldInstanceToBeEdited) {
        this.mWordEditText = (EditText) view.findViewById(R.id.user_dictionary_add_word_text);
        this.mShortcutEditText = (EditText) view.findViewById(R.id.user_dictionary_add_shortcut);
        this.mMode = 0;
        this.mOldWord = oldInstanceToBeEdited.mSavedWord;
        this.mOldShortcut = oldInstanceToBeEdited.mSavedShortcut;
        updateLocale(this.mLocale);
    }

    void updateLocale(final String locale) {
        if (locale == null) {
            locale = Locale.getDefault().toString();
        }
        this.mLocale = locale;
    }

    void saveStateIntoBundle(final Bundle outState) {
        outState.putString("word", this.mWordEditText.getText().toString());
        outState.putString(EXTRA_ORIGINAL_WORD, this.mOldWord);
        EditText editText = this.mShortcutEditText;
        if (editText != null) {
            outState.putString(EXTRA_SHORTCUT, editText.getText().toString());
        }
        String str = this.mOldShortcut;
        if (str != null) {
            outState.putString(EXTRA_ORIGINAL_SHORTCUT, str);
        }
        outState.putString("locale", this.mLocale);
    }

    void delete(final Context context) {
        if (this.mMode != 0 || TextUtils.isEmpty(this.mOldWord)) {
            return;
        }
        UserDictionarySettings.deleteWord(this.mOldWord, this.mOldShortcut, context.getContentResolver());
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    int apply(final android.content.Context r5, final android.os.Bundle r6) {
        /*
            r4 = this;
            if (r6 == 0) goto L5
            r4.saveStateIntoBundle(r6)
        L5:
            android.content.ContentResolver r6 = r5.getContentResolver()
            int r0 = r4.mMode
            if (r0 != 0) goto L1c
            java.lang.String r0 = r4.mOldWord
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L1c
            java.lang.String r0 = r4.mOldWord
            java.lang.String r1 = r4.mOldShortcut
            com.android.inputmethod.latin.userdictionary.UserDictionarySettings.deleteWord(r0, r1, r6)
        L1c:
            android.widget.EditText r0 = r4.mWordEditText
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            boolean r1 = com.android.inputmethod.latin.userdictionary.UserDictionarySettings.IS_SHORTCUT_API_SUPPORTED
            r2 = 0
            if (r1 != 0) goto L2d
        L2b:
            r1 = r2
            goto L41
        L2d:
            android.widget.EditText r1 = r4.mShortcutEditText
            if (r1 != 0) goto L32
            goto L2b
        L32:
            android.text.Editable r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 == 0) goto L41
            goto L2b
        L41:
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 == 0) goto L49
            r5 = 1
            return r5
        L49:
            r4.mSavedWord = r0
            r4.mSavedShortcut = r1
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 == 0) goto L5b
            boolean r3 = r4.hasWord(r0, r5)
            if (r3 == 0) goto L5b
            r5 = 2
            return r5
        L5b:
            com.android.inputmethod.latin.userdictionary.UserDictionarySettings.deleteWord(r0, r2, r6)
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 != 0) goto L67
            com.android.inputmethod.latin.userdictionary.UserDictionarySettings.deleteWord(r0, r1, r6)
        L67:
            java.lang.String r6 = r0.toString()
            r0 = 250(0xfa, float:3.5E-43)
            java.lang.String r3 = r4.mLocale
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L76
            goto L7c
        L76:
            java.lang.String r2 = r4.mLocale
            java.util.Locale r2 = com.android.inputmethod.latin.common.LocaleUtils.constructLocaleFromString(r2)
        L7c:
            com.android.inputmethod.compat.UserDictionaryCompatUtils.addWord(r5, r6, r0, r1, r2)
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.inputmethod.latin.userdictionary.UserDictionaryAddWordContents.apply(android.content.Context, android.os.Bundle):int");
    }

    private boolean hasWord(final String word, final Context context) {
        Cursor cursorQuery;
        if ("".equals(this.mLocale)) {
            cursorQuery = context.getContentResolver().query(UserDictionary.Words.CONTENT_URI, HAS_WORD_PROJECTION, HAS_WORD_SELECTION_ALL_LOCALES, new String[]{word}, null);
        } else {
            cursorQuery = context.getContentResolver().query(UserDictionary.Words.CONTENT_URI, HAS_WORD_PROJECTION, HAS_WORD_SELECTION_ONE_LOCALE, new String[]{word, this.mLocale}, null);
        }
        if (cursorQuery == null) {
            return false;
        }
        try {
            boolean z = cursorQuery.getCount() > 0;
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return z;
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    public static class LocaleRenderer {
        private final String mDescription;
        private final String mLocaleString;

        public LocaleRenderer(final Context context, @Nullable final String localeString) {
            this.mLocaleString = localeString;
            if (localeString == null) {
                this.mDescription = context.getString(R.string.user_dict_settings_more_languages);
            } else if ("".equals(localeString)) {
                this.mDescription = context.getString(R.string.user_dict_settings_all_languages);
            } else {
                this.mDescription = LocaleUtils.constructLocaleFromString(localeString).getDisplayName();
            }
        }

        public String toString() {
            return this.mDescription;
        }

        public String getLocaleString() {
            return this.mLocaleString;
        }

        public boolean isMoreLanguages() {
            return this.mLocaleString == null;
        }
    }

    private static void addLocaleDisplayNameToList(final Context context, final ArrayList<LocaleRenderer> list, final String locale) {
        if (locale != null) {
            list.add(new LocaleRenderer(context, locale));
        }
    }

    public ArrayList<LocaleRenderer> getLocalesList(final Activity activity) {
        TreeSet<String> userDictionaryLocalesSet = UserDictionaryList.getUserDictionaryLocalesSet(activity);
        userDictionaryLocalesSet.remove(this.mLocale);
        String string = Locale.getDefault().toString();
        userDictionaryLocalesSet.remove(string);
        userDictionaryLocalesSet.remove("");
        ArrayList<LocaleRenderer> arrayList = new ArrayList<>();
        addLocaleDisplayNameToList(activity, arrayList, this.mLocale);
        if (!string.equals(this.mLocale)) {
            addLocaleDisplayNameToList(activity, arrayList, string);
        }
        Iterator<String> it = userDictionaryLocalesSet.iterator();
        while (it.hasNext()) {
            addLocaleDisplayNameToList(activity, arrayList, it.next());
        }
        if (!"".equals(this.mLocale)) {
            addLocaleDisplayNameToList(activity, arrayList, "");
        }
        arrayList.add(new LocaleRenderer(activity, null));
        return arrayList;
    }

    public String getCurrentUserDictionaryLocale() {
        return this.mLocale;
    }
}
