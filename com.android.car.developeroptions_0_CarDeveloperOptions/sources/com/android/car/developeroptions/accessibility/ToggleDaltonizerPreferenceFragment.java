package com.android.car.developeroptions.accessibility;

import android.content.Context;
import android.hardware.display.ColorDisplayManager;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.widget.Switch;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.widget.SwitchBar;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ToggleDaltonizerPreferenceFragment extends ToggleFeaturePreferenceFragment implements Preference.OnPreferenceChangeListener, SwitchBar.OnSwitchChangeListener {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.accessibility.ToggleDaltonizerPreferenceFragment.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.accessibility_daltonizer_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    private ListPreference mType;

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_color_correction;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 5;
    }

    @Override // com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.accessibility_daltonizer_settings;
    }

    @Override // com.android.car.developeroptions.accessibility.ToggleFeaturePreferenceFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mType = (ListPreference) findPreference("type");
        findPreference("daltonizer_footer").setVisible(!ColorDisplayManager.isColorTransformAccelerated(getActivity()));
        initPreferences();
    }

    protected void onPreferenceToggled(String str, boolean z) {
        Settings.Secure.putInt(getContentResolver(), "accessibility_display_daltonizer_enabled", z ? 1 : 0);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference != this.mType) {
            return true;
        }
        Settings.Secure.putInt(getContentResolver(), "accessibility_display_daltonizer", Integer.parseInt((String) obj));
        preference.setSummary("%s");
        return true;
    }

    @Override // com.android.car.developeroptions.accessibility.ToggleFeaturePreferenceFragment
    protected void onInstallSwitchBarToggleSwitch() {
        super.onInstallSwitchBarToggleSwitch();
        this.mSwitchBar.setCheckedInternal(Settings.Secure.getInt(getContentResolver(), "accessibility_display_daltonizer_enabled", 0) == 1);
        this.mSwitchBar.addOnSwitchChangeListener(this);
    }

    @Override // com.android.car.developeroptions.accessibility.ToggleFeaturePreferenceFragment
    protected void onRemoveSwitchBarToggleSwitch() {
        super.onRemoveSwitchBarToggleSwitch();
        this.mSwitchBar.removeOnSwitchChangeListener(this);
    }

    @Override // com.android.car.developeroptions.accessibility.ToggleFeaturePreferenceFragment
    protected void updateSwitchBarText(SwitchBar switchBar) {
        switchBar.setSwitchBarText(R.string.accessibility_daltonizer_master_switch_title, R.string.accessibility_daltonizer_master_switch_title);
    }

    private void initPreferences() {
        String string = Integer.toString(Settings.Secure.getInt(getContentResolver(), "accessibility_display_daltonizer", 12));
        this.mType.setValue(string);
        this.mType.setOnPreferenceChangeListener(this);
        if (this.mType.findIndexOfValue(string) < 0) {
            this.mType.setSummary(getString(R.string.daltonizer_type_overridden, getString(R.string.simulate_color_space)));
        }
    }

    @Override // com.android.car.developeroptions.widget.SwitchBar.OnSwitchChangeListener
    public void onSwitchChanged(Switch r1, boolean z) {
        onPreferenceToggled(this.mPreferenceKey, z);
    }
}
