package com.android.settingslib.search;

import com.android.car.settings.accounts.AccountSettingsFragment;
import com.android.car.settings.applications.AppsAndNotificationsFragment;
import com.android.car.settings.applications.assist.ManageAssistFragment;
import com.android.car.settings.applications.specialaccess.SpecialAccessSettingsFragment;
import com.android.car.settings.bluetooth.BluetoothSettingsFragment;
import com.android.car.settings.datausage.DataUsageFragment;
import com.android.car.settings.datausage.DataWarningAndLimitFragment;
import com.android.car.settings.datetime.DatetimeSettingsFragment;
import com.android.car.settings.display.DisplaySettingsFragment;
import com.android.car.settings.inputmethod.KeyboardFragment;
import com.android.car.settings.language.LanguagesAndInputFragment;
import com.android.car.settings.location.LocationSettingsFragment;
import com.android.car.settings.network.MobileNetworkFragment;
import com.android.car.settings.network.MobileNetworkListFragment;
import com.android.car.settings.network.NetworkAndInternetFragment;
import com.android.car.settings.privacy.PrivacySettingsFragment;
import com.android.car.settings.security.SecuritySettingsFragment;
import com.android.car.settings.sound.SoundSettingsFragment;
import com.android.car.settings.storage.StorageSettingsFragment;
import com.android.car.settings.system.AboutSettingsFragment;
import com.android.car.settings.system.LegalInformationFragment;
import com.android.car.settings.system.ResetOptionsFragment;
import com.android.car.settings.system.SystemSettingsFragment;
import com.android.car.settings.tts.TextToSpeechOutputFragment;
import com.android.car.settings.units.UnitsSettingsFragment;
import com.android.car.settings.users.UsersListFragment;
import com.android.car.settings.wifi.WifiSettingsFragment;
import com.android.car.settings.wifi.WifiTetherFragment;
import com.android.car.settings.wifi.preferences.WifiPreferencesFragment;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes6.dex */
public class SearchIndexableResourcesBase implements SearchIndexableResources {
    private final Set<SearchIndexableData> mProviders = new HashSet();

    public SearchIndexableResourcesBase() {
        addIndex(new SearchIndexableData(AccountSettingsFragment.class, AccountSettingsFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(AppsAndNotificationsFragment.class, AppsAndNotificationsFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(ManageAssistFragment.class, ManageAssistFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(SpecialAccessSettingsFragment.class, SpecialAccessSettingsFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(BluetoothSettingsFragment.class, BluetoothSettingsFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(DataUsageFragment.class, DataUsageFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(DataWarningAndLimitFragment.class, DataWarningAndLimitFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(DatetimeSettingsFragment.class, DatetimeSettingsFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(DisplaySettingsFragment.class, DisplaySettingsFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(KeyboardFragment.class, KeyboardFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(LanguagesAndInputFragment.class, LanguagesAndInputFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(LocationSettingsFragment.class, LocationSettingsFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(MobileNetworkFragment.class, MobileNetworkFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(MobileNetworkListFragment.class, MobileNetworkListFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(NetworkAndInternetFragment.class, NetworkAndInternetFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(PrivacySettingsFragment.class, PrivacySettingsFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(SecuritySettingsFragment.class, SecuritySettingsFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(SoundSettingsFragment.class, SoundSettingsFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(StorageSettingsFragment.class, StorageSettingsFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(AboutSettingsFragment.class, AboutSettingsFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(LegalInformationFragment.class, LegalInformationFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(ResetOptionsFragment.class, ResetOptionsFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(SystemSettingsFragment.class, SystemSettingsFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(TextToSpeechOutputFragment.class, TextToSpeechOutputFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(UnitsSettingsFragment.class, UnitsSettingsFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(UsersListFragment.class, UsersListFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(WifiSettingsFragment.class, WifiSettingsFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(WifiTetherFragment.class, WifiTetherFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(WifiPreferencesFragment.class, WifiPreferencesFragment.SEARCH_INDEX_DATA_PROVIDER));
    }

    @Override // com.android.settingslib.search.SearchIndexableResources
    public void addIndex(SearchIndexableData indexClass) {
        this.mProviders.add(indexClass);
    }

    @Override // com.android.settingslib.search.SearchIndexableResources
    public Collection<SearchIndexableData> getProviderValues() {
        return this.mProviders;
    }
}
