package com.android.inputmethod.dictionarypack;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import com.android.inputmethod.dictionarypack.UpdateHandler;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.common.LocaleUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes2.dex */
public final class DictionarySettingsFragment extends PreferenceFragment implements UpdateHandler.UpdateEventListener {
    private static final String DICT_LIST_ID = "list";
    public static final String DICT_SETTINGS_FRAGMENT_CLIENT_ID_ARGUMENT = "clientId";
    private static final int MENU_UPDATE_NOW = 1;
    private static final String TAG = "DictionarySettingsFragment";
    private boolean mChangedSettings;
    private String mClientId;
    private ConnectivityManager mConnectivityManager;
    private View mLoadingView;
    private MenuItem mUpdateNowMenu;
    private DictionaryListInterfaceState mDictionaryListInterfaceState = new DictionaryListInterfaceState();
    private TreeMap<String, WordListPreference> mCurrentPreferenceMap = new TreeMap<>();
    private final BroadcastReceiver mConnectivityChangedReceiver = new BroadcastReceiver() { // from class: com.android.inputmethod.dictionarypack.DictionarySettingsFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, final Intent intent) {
            DictionarySettingsFragment.this.refreshNetworkState();
        }
    };

    @Override // com.android.inputmethod.dictionarypack.UpdateHandler.UpdateEventListener
    public void updateCycleCompleted() {
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        this.mLoadingView = inflater.inflate(R.layout.loading_page, container, true).findViewById(R.id.loading_container);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity activity = getActivity();
        this.mClientId = activity.getIntent().getStringExtra(DICT_SETTINGS_FRAGMENT_CLIENT_ID_ARGUMENT);
        this.mConnectivityManager = (ConnectivityManager) activity.getSystemService("connectivity");
        addPreferencesFromResource(R.xml.dictionary_settings);
        refreshInterface();
        setHasOptionsMenu(true);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [com.android.inputmethod.dictionarypack.DictionarySettingsFragment$2] */
    @Override // android.app.Fragment
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        new AsyncTask<Void, Void, String>() { // from class: com.android.inputmethod.dictionarypack.DictionarySettingsFragment.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public String doInBackground(Void... params) {
                return MetadataDbHelper.getMetadataUriAsString(DictionarySettingsFragment.this.getActivity(), DictionarySettingsFragment.this.mClientId);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(String metadataUri) {
                if (TextUtils.isEmpty(metadataUri)) {
                    return;
                }
                if (DictionarySettingsFragment.this.mUpdateNowMenu == null) {
                    DictionarySettingsFragment.this.mUpdateNowMenu = menu.add(0, 1, 0, R.string.check_for_updates_now);
                    DictionarySettingsFragment.this.mUpdateNowMenu.setShowAsAction(1);
                }
                DictionarySettingsFragment.this.refreshNetworkState();
            }
        }.execute(new Void[0]);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.inputmethod.dictionarypack.DictionarySettingsFragment$3] */
    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mChangedSettings = false;
        UpdateHandler.registerUpdateEventListener(this);
        final Activity activity = getActivity();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        getActivity().registerReceiver(this.mConnectivityChangedReceiver, intentFilter);
        refreshNetworkState();
        new Thread("onResume") { // from class: com.android.inputmethod.dictionarypack.DictionarySettingsFragment.3
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                if (MetadataDbHelper.isClientKnown(activity, DictionarySettingsFragment.this.mClientId)) {
                    return;
                }
                Log.i(DictionarySettingsFragment.TAG, "Unknown dictionary pack client: " + DictionarySettingsFragment.this.mClientId + ". Requesting info.");
                Intent intent = new Intent(DictionaryPackConstants.UNKNOWN_DICTIONARY_PROVIDER_CLIENT);
                intent.putExtra(DictionaryPackConstants.DICTIONARY_PROVIDER_CLIENT_EXTRA, DictionarySettingsFragment.this.mClientId);
                activity.sendBroadcast(intent);
            }
        }.start();
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        Activity activity = getActivity();
        UpdateHandler.unregisterUpdateEventListener(this);
        activity.unregisterReceiver(this.mConnectivityChangedReceiver);
        if (this.mChangedSettings) {
            activity.sendBroadcast(new Intent(DictionaryPackConstants.NEW_DICTIONARY_INTENT_ACTION));
            this.mChangedSettings = false;
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [com.android.inputmethod.dictionarypack.DictionarySettingsFragment$4] */
    @Override // com.android.inputmethod.dictionarypack.UpdateHandler.UpdateEventListener
    public void downloadedMetadata(final boolean succeeded) {
        stopLoadingAnimation();
        if (succeeded) {
            new Thread("refreshInterface") { // from class: com.android.inputmethod.dictionarypack.DictionarySettingsFragment.4
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    DictionarySettingsFragment.this.refreshInterface();
                }
            }.start();
        }
    }

    @Override // com.android.inputmethod.dictionarypack.UpdateHandler.UpdateEventListener
    public void wordListDownloadFinished(final String wordListId, final boolean succeeded) {
        Activity activity;
        if (findWordListPreference(wordListId) == null || (activity = getActivity()) == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.android.inputmethod.dictionarypack.DictionarySettingsFragment.5
            @Override // java.lang.Runnable
            public void run() {
                DictionarySettingsFragment.this.refreshInterface();
            }
        });
    }

    private WordListPreference findWordListPreference(final String id) {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen == null) {
            Log.e(TAG, "Could not find the preference group");
            return null;
        }
        for (int preferenceCount = preferenceScreen.getPreferenceCount() - 1; preferenceCount >= 0; preferenceCount--) {
            Preference preference = preferenceScreen.getPreference(preferenceCount);
            if (preference instanceof WordListPreference) {
                WordListPreference wordListPreference = (WordListPreference) preference;
                if (id.equals(wordListPreference.mWordlistId)) {
                    return wordListPreference;
                }
            }
        }
        Log.e(TAG, "Could not find the preference for a word list id " + id);
        return null;
    }

    void refreshNetworkState() {
        NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
        boolean zIsConnected = activeNetworkInfo == null ? false : activeNetworkInfo.isConnected();
        MenuItem menuItem = this.mUpdateNowMenu;
        if (menuItem != null) {
            menuItem.setEnabled(zIsConnected);
        }
    }

    void refreshInterface() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        final PreferenceScreen preferenceScreen = getPreferenceScreen();
        final Collection<? extends Preference> collectionCreateInstalledDictSettingsCollection = createInstalledDictSettingsCollection(this.mClientId);
        activity.runOnUiThread(new Runnable() { // from class: com.android.inputmethod.dictionarypack.DictionarySettingsFragment.6
            @Override // java.lang.Runnable
            public void run() {
                DictionarySettingsFragment.this.refreshNetworkState();
                DictionarySettingsFragment.removeAnyDictSettings(preferenceScreen);
                int i = 0;
                for (Preference preference : collectionCreateInstalledDictSettingsCollection) {
                    preference.setOrder(i);
                    preferenceScreen.addPreference(preference);
                    i++;
                }
            }
        });
    }

    private static Preference createErrorMessage(final Activity activity, final int messageResource) {
        Preference preference = new Preference(activity);
        preference.setTitle(messageResource);
        preference.setEnabled(false);
        return preference;
    }

    static void removeAnyDictSettings(final PreferenceGroup prefGroup) {
        for (int preferenceCount = prefGroup.getPreferenceCount() - 1; preferenceCount >= 0; preferenceCount--) {
            prefGroup.removePreference(prefGroup.getPreference(preferenceCount));
        }
    }

    private Collection<? extends Preference> createInstalledDictSettingsCollection(final String clientId) {
        String str;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        String str2 = ".";
        Uri uriBuild = new Uri.Builder().scheme("content").authority(getString(R.string.authority)).appendPath(clientId).appendPath(DICT_LIST_ID).appendQueryParameter(DictionaryProvider.QUERY_PARAMETER_PROTOCOL_VERSION, "2").build();
        Activity activity = getActivity();
        Cursor cursorQuery = activity == null ? null : activity.getContentResolver().query(uriBuild, null, null, null, null);
        if (cursorQuery == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(createErrorMessage(activity, R.string.cannot_connect_to_dict_service));
            return arrayList;
        }
        try {
            if (!cursorQuery.moveToFirst()) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(createErrorMessage(activity, R.string.no_dictionaries_available));
                return arrayList2;
            }
            String string = Locale.getDefault().toString();
            TreeMap<String, WordListPreference> treeMap = new TreeMap<>();
            int columnIndex = cursorQuery.getColumnIndex(MetadataDbHelper.WORDLISTID_COLUMN);
            int columnIndex2 = cursorQuery.getColumnIndex("version");
            int columnIndex3 = cursorQuery.getColumnIndex("locale");
            int columnIndex4 = cursorQuery.getColumnIndex("description");
            int columnIndex5 = cursorQuery.getColumnIndex("status");
            int columnIndex6 = cursorQuery.getColumnIndex(MetadataDbHelper.FILESIZE_COLUMN);
            while (true) {
                String string2 = cursorQuery.getString(columnIndex);
                int i6 = cursorQuery.getInt(columnIndex2);
                String string3 = cursorQuery.getString(columnIndex3);
                Locale locale = new Locale(string3);
                int i7 = columnIndex3;
                String string4 = cursorQuery.getString(columnIndex4);
                int i8 = columnIndex2;
                int i9 = cursorQuery.getInt(columnIndex5);
                String matchLevelSortedString = LocaleUtils.getMatchLevelSortedString(LocaleUtils.getMatchLevel(string, string3));
                int i10 = cursorQuery.getInt(columnIndex6);
                String str3 = string;
                String str4 = matchLevelSortedString + str2 + string4 + str2 + string2;
                WordListPreference wordListPreference = treeMap.get(str4);
                if (wordListPreference == null || wordListPreference.hasPriorityOver(i9)) {
                    WordListPreference wordListPreference2 = this.mCurrentPreferenceMap.get(str4);
                    if (wordListPreference2 != null) {
                        str = str2;
                        if (wordListPreference2.mVersion == i6 && wordListPreference2.hasStatus(i9) && wordListPreference2.mLocale.equals(locale)) {
                            i4 = i7;
                            i = columnIndex6;
                            i2 = columnIndex5;
                            i3 = columnIndex4;
                            i5 = columnIndex;
                        }
                        treeMap.put(str4, wordListPreference2);
                    } else {
                        str = str2;
                    }
                    i = columnIndex6;
                    i2 = columnIndex5;
                    i3 = columnIndex4;
                    i4 = i7;
                    i5 = columnIndex;
                    wordListPreference2 = new WordListPreference(activity, this.mDictionaryListInterfaceState, this.mClientId, string2, i6, locale, string4, i9, i10);
                    treeMap.put(str4, wordListPreference2);
                } else {
                    i4 = i7;
                    str = str2;
                    i = columnIndex6;
                    i2 = columnIndex5;
                    i3 = columnIndex4;
                    i5 = columnIndex;
                }
                if (!cursorQuery.moveToNext()) {
                    this.mCurrentPreferenceMap = treeMap;
                    return treeMap.values();
                }
                columnIndex2 = i8;
                string = str3;
                str2 = str;
                columnIndex5 = i2;
                columnIndex6 = i;
                columnIndex3 = i4;
                columnIndex4 = i3;
                columnIndex = i5;
            }
        } finally {
            cursorQuery.close();
        }
    }

    @Override // android.app.Fragment
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() != 1) {
            return false;
        }
        if (8 == this.mLoadingView.getVisibility()) {
            startRefresh();
        } else {
            cancelRefresh();
        }
        return true;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.android.inputmethod.dictionarypack.DictionarySettingsFragment$7] */
    private void startRefresh() {
        startLoadingAnimation();
        this.mChangedSettings = true;
        UpdateHandler.registerUpdateEventListener(this);
        final Activity activity = getActivity();
        new Thread("updateByHand") { // from class: com.android.inputmethod.dictionarypack.DictionarySettingsFragment.7
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                if (UpdateHandler.tryUpdate(activity)) {
                    return;
                }
                DictionarySettingsFragment.this.stopLoadingAnimation();
            }
        }.start();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.android.inputmethod.dictionarypack.DictionarySettingsFragment$8] */
    private void cancelRefresh() {
        UpdateHandler.unregisterUpdateEventListener(this);
        final Activity activity = getActivity();
        new Thread("cancelByHand") { // from class: com.android.inputmethod.dictionarypack.DictionarySettingsFragment.8
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                UpdateHandler.cancelUpdate(activity, DictionarySettingsFragment.this.mClientId);
                DictionarySettingsFragment.this.stopLoadingAnimation();
            }
        }.start();
    }

    private void startLoadingAnimation() {
        this.mLoadingView.setVisibility(0);
        getView().setVisibility(8);
        MenuItem menuItem = this.mUpdateNowMenu;
        if (menuItem != null) {
            menuItem.setTitle(R.string.cancel);
        }
    }

    void stopLoadingAnimation() {
        final View view = getView();
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        final View view2 = this.mLoadingView;
        final MenuItem menuItem = this.mUpdateNowMenu;
        activity.runOnUiThread(new Runnable() { // from class: com.android.inputmethod.dictionarypack.DictionarySettingsFragment.9
            @Override // java.lang.Runnable
            public void run() {
                view2.setVisibility(8);
                view.setVisibility(0);
                view2.startAnimation(AnimationUtils.loadAnimation(activity, android.R.anim.fade_out));
                view.startAnimation(AnimationUtils.loadAnimation(activity, android.R.anim.fade_in));
                MenuItem menuItem2 = menuItem;
                if (menuItem2 != null) {
                    menuItem2.setTitle(R.string.check_for_updates_now);
                }
            }
        });
    }
}
