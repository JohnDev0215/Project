package com.android.inputmethod.latin.userdictionary;

import android.R;
import android.app.ListFragment;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.provider.UserDictionary;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AlphabetIndexer;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class UserDictionarySettings extends ListFragment {
    private static final String[] ADAPTER_FROM;
    private static final String[] ADAPTER_FROM_SHORTCUT_SUPPORTED;
    private static final String[] ADAPTER_FROM_SHORTCUT_UNSUPPORTED;
    private static final int[] ADAPTER_TO;
    private static final int[] ADAPTER_TO_SHORTCUT_SUPPORTED;
    private static final int[] ADAPTER_TO_SHORTCUT_UNSUPPORTED;
    private static final String DELETE_SELECTION_SHORTCUT_UNSUPPORTED = "word=?";
    private static final String DELETE_SELECTION_WITHOUT_SHORTCUT = "word=? AND shortcut is null OR shortcut=''";
    private static final String DELETE_SELECTION_WITH_SHORTCUT = "word=? AND shortcut=?";
    private static final int INDEX_SHORTCUT = 2;
    public static final boolean IS_SHORTCUT_API_SUPPORTED;
    private static final int OPTIONS_MENU_ADD = 1;
    private static final String[] QUERY_PROJECTION;
    private static final String[] QUERY_PROJECTION_SHORTCUT_SUPPORTED;
    private static final String[] QUERY_PROJECTION_SHORTCUT_UNSUPPORTED;
    private static final String QUERY_SELECTION = "locale=?";
    private static final String QUERY_SELECTION_ALL_LOCALES = "locale is null";
    private Cursor mCursor;
    protected String mLocale;

    static {
        boolean z = Build.VERSION.SDK_INT >= 16;
        IS_SHORTCUT_API_SUPPORTED = z;
        String[] strArr = {"_id", "word"};
        QUERY_PROJECTION_SHORTCUT_UNSUPPORTED = strArr;
        String[] strArr2 = {"_id", "word", UserDictionaryAddWordContents.EXTRA_SHORTCUT};
        QUERY_PROJECTION_SHORTCUT_SUPPORTED = strArr2;
        if (z) {
            strArr = strArr2;
        }
        QUERY_PROJECTION = strArr;
        String[] strArr3 = {"word"};
        ADAPTER_FROM_SHORTCUT_UNSUPPORTED = strArr3;
        String[] strArr4 = {"word", UserDictionaryAddWordContents.EXTRA_SHORTCUT};
        ADAPTER_FROM_SHORTCUT_SUPPORTED = strArr4;
        if (z) {
            strArr3 = strArr4;
        }
        ADAPTER_FROM = strArr3;
        int[] iArr = {R.id.text1};
        ADAPTER_TO_SHORTCUT_UNSUPPORTED = iArr;
        int[] iArr2 = {R.id.text1, R.id.text2};
        ADAPTER_TO_SHORTCUT_SUPPORTED = iArr2;
        if (z) {
            iArr = iArr2;
        }
        ADAPTER_TO = iArr;
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getActionBar().setTitle(com.android.inputmethod.latin.R.string.edit_personal_dictionary);
    }

    @Override // android.app.ListFragment, android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(com.android.inputmethod.latin.R.layout.user_dictionary_preference_list_fragment, container, false);
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        String str = null;
        String stringExtra = intent == null ? null : intent.getStringExtra("locale");
        Bundle arguments = getArguments();
        String string = arguments == null ? null : arguments.getString("locale");
        if (string != null) {
            str = string;
        } else if (stringExtra != null) {
            str = stringExtra;
        }
        this.mLocale = str;
        this.mCursor = createCursor(str);
        TextView textView = (TextView) getView().findViewById(R.id.empty);
        textView.setText(com.android.inputmethod.latin.R.string.user_dict_settings_empty_text);
        ListView listView = getListView();
        listView.setAdapter(createAdapter());
        listView.setFastScrollEnabled(true);
        listView.setEmptyView(textView);
        setHasOptionsMenu(true);
        getActivity().getActionBar().setSubtitle(UserDictionarySettingsUtils.getLocaleDisplayName(getActivity(), this.mLocale));
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        ListAdapter adapter = getListView().getAdapter();
        if (adapter == null || !(adapter instanceof MyAdapter)) {
            return;
        }
        ((MyAdapter) adapter).notifyDataSetChanged();
    }

    private Cursor createCursor(final String locale) {
        if ("".equals(locale)) {
            return getActivity().managedQuery(UserDictionary.Words.CONTENT_URI, QUERY_PROJECTION, QUERY_SELECTION_ALL_LOCALES, null, "UPPER(word)");
        }
        if (locale == null) {
            locale = Locale.getDefault().toString();
        }
        return getActivity().managedQuery(UserDictionary.Words.CONTENT_URI, QUERY_PROJECTION, QUERY_SELECTION, new String[]{locale}, "UPPER(word)");
    }

    private ListAdapter createAdapter() {
        return new MyAdapter(getActivity(), com.android.inputmethod.latin.R.layout.user_dictionary_item, this.mCursor, ADAPTER_FROM, ADAPTER_TO);
    }

    @Override // android.app.ListFragment
    public void onListItemClick(ListView l, View v, int position, long id) {
        String word = getWord(position);
        String shortcut = getShortcut(position);
        if (word != null) {
            showAddOrEditDialog(word, shortcut);
        }
    }

    @Override // android.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!IS_SHORTCUT_API_SUPPORTED) {
            Locale locale = getResources().getConfiguration().locale;
            if (!TextUtils.isEmpty(this.mLocale) && !this.mLocale.equals(locale.toString())) {
                return;
            }
        }
        menu.add(0, 1, 0, com.android.inputmethod.latin.R.string.user_dict_settings_add_menu_title).setIcon(com.android.inputmethod.latin.R.drawable.ic_menu_add).setShowAsAction(5);
    }

    @Override // android.app.Fragment
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 1) {
            return false;
        }
        showAddOrEditDialog(null, null);
        return true;
    }

    private void showAddOrEditDialog(final String editingWord, final String editingShortcut) {
        Bundle bundle = new Bundle();
        bundle.putInt(UserDictionaryAddWordContents.EXTRA_MODE, editingWord == null ? 1 : 0);
        bundle.putString("word", editingWord);
        bundle.putString(UserDictionaryAddWordContents.EXTRA_SHORTCUT, editingShortcut);
        bundle.putString("locale", this.mLocale);
        ((PreferenceActivity) getActivity()).startPreferencePanel(UserDictionaryAddWordFragment.class.getName(), bundle, com.android.inputmethod.latin.R.string.user_dict_settings_add_dialog_title, null, null, 0);
    }

    private String getWord(final int position) {
        Cursor cursor = this.mCursor;
        if (cursor == null) {
            return null;
        }
        cursor.moveToPosition(position);
        if (this.mCursor.isAfterLast()) {
            return null;
        }
        Cursor cursor2 = this.mCursor;
        return cursor2.getString(cursor2.getColumnIndexOrThrow("word"));
    }

    private String getShortcut(final int position) {
        Cursor cursor;
        if (!IS_SHORTCUT_API_SUPPORTED || (cursor = this.mCursor) == null) {
            return null;
        }
        cursor.moveToPosition(position);
        if (this.mCursor.isAfterLast()) {
            return null;
        }
        Cursor cursor2 = this.mCursor;
        return cursor2.getString(cursor2.getColumnIndexOrThrow(UserDictionaryAddWordContents.EXTRA_SHORTCUT));
    }

    public static void deleteWord(final String word, final String shortcut, final ContentResolver resolver) {
        if (!IS_SHORTCUT_API_SUPPORTED) {
            resolver.delete(UserDictionary.Words.CONTENT_URI, DELETE_SELECTION_SHORTCUT_UNSUPPORTED, new String[]{word});
        } else if (TextUtils.isEmpty(shortcut)) {
            resolver.delete(UserDictionary.Words.CONTENT_URI, DELETE_SELECTION_WITHOUT_SHORTCUT, new String[]{word});
        } else {
            resolver.delete(UserDictionary.Words.CONTENT_URI, DELETE_SELECTION_WITH_SHORTCUT, new String[]{word, shortcut});
        }
    }

    private static class MyAdapter extends SimpleCursorAdapter implements SectionIndexer {
        private AlphabetIndexer mIndexer;
        private SimpleCursorAdapter.ViewBinder mViewBinder;

        public MyAdapter(final Context context, final int layout, final Cursor c, final String[] from, final int[] to) {
            super(context, layout, c, from, to, 0);
            this.mViewBinder = new SimpleCursorAdapter.ViewBinder() { // from class: com.android.inputmethod.latin.userdictionary.UserDictionarySettings.MyAdapter.1
                @Override // android.widget.SimpleCursorAdapter.ViewBinder
                public boolean setViewValue(final View v, final Cursor c2, final int columnIndex) {
                    if (!UserDictionarySettings.IS_SHORTCUT_API_SUPPORTED || columnIndex != 2) {
                        return false;
                    }
                    String string = c2.getString(2);
                    if (TextUtils.isEmpty(string)) {
                        v.setVisibility(8);
                    } else {
                        ((TextView) v).setText(string);
                        v.setVisibility(0);
                    }
                    v.invalidate();
                    return true;
                }
            };
            if (c != null) {
                this.mIndexer = new AlphabetIndexer(c, c.getColumnIndexOrThrow("word"), context.getString(com.android.inputmethod.latin.R.string.user_dict_fast_scroll_alphabet));
            }
            setViewBinder(this.mViewBinder);
        }

        @Override // android.widget.SectionIndexer
        public int getPositionForSection(final int section) {
            AlphabetIndexer alphabetIndexer = this.mIndexer;
            if (alphabetIndexer == null) {
                return 0;
            }
            return alphabetIndexer.getPositionForSection(section);
        }

        @Override // android.widget.SectionIndexer
        public int getSectionForPosition(final int position) {
            AlphabetIndexer alphabetIndexer = this.mIndexer;
            if (alphabetIndexer == null) {
                return 0;
            }
            return alphabetIndexer.getSectionForPosition(position);
        }

        @Override // android.widget.SectionIndexer
        public Object[] getSections() {
            AlphabetIndexer alphabetIndexer = this.mIndexer;
            if (alphabetIndexer == null) {
                return null;
            }
            return alphabetIndexer.getSections();
        }
    }
}
