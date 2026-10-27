package com.android.car.developeroptions.accessibility;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.widget.Switch;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.widget.SeekBarPreference;
import com.android.car.developeroptions.widget.SwitchBar;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ToggleAutoclickPreferenceFragment extends ToggleFeaturePreferenceFragment implements SwitchBar.OnSwitchChangeListener, Preference.OnPreferenceChangeListener {
    private SeekBarPreference mDelay;
    private static final int[] mAutoclickPreferenceSummaries = {R.plurals.accessibilty_autoclick_preference_subtitle_extremely_short_delay, R.plurals.accessibilty_autoclick_preference_subtitle_very_short_delay, R.plurals.accessibilty_autoclick_preference_subtitle_short_delay, R.plurals.accessibilty_autoclick_preference_subtitle_long_delay, R.plurals.accessibilty_autoclick_preference_subtitle_very_long_delay};
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.accessibility.ToggleAutoclickPreferenceFragment.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.accessibility_autoclick_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };

    private int seekBarProgressToDelay(int i) {
        return (i * 100) + 200;
    }

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_autoclick;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 335;
    }

    @Override // com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.accessibility_autoclick_settings;
    }

    static CharSequence getAutoclickPreferenceSummary(Resources resources, int i) {
        return resources.getQuantityString(mAutoclickPreferenceSummaries[getAutoclickPreferenceSummaryIndex(i)], i, Integer.valueOf(i));
    }

    private static int getAutoclickPreferenceSummaryIndex(int i) {
        int[] iArr = mAutoclickPreferenceSummaries;
        if (i <= 200) {
            return 0;
        }
        if (i >= 1000) {
            return iArr.length - 1;
        }
        return (i - 200) / (800 / (iArr.length - 1));
    }

    protected void onPreferenceToggled(String str, boolean z) {
        Settings.Secure.putInt(getContentResolver(), str, z ? 1 : 0);
        this.mDelay.setEnabled(z);
    }

    @Override // com.android.car.developeroptions.accessibility.ToggleFeaturePreferenceFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i = Settings.Secure.getInt(getContentResolver(), "accessibility_autoclick_delay", 600);
        SeekBarPreference seekBarPreference = (SeekBarPreference) findPreference("autoclick_delay");
        this.mDelay = seekBarPreference;
        seekBarPreference.setMax(delayToSeekBarProgress(1000));
        this.mDelay.setProgress(delayToSeekBarProgress(i));
        this.mDelay.setOnPreferenceChangeListener(this);
    }

    @Override // com.android.car.developeroptions.accessibility.ToggleFeaturePreferenceFragment
    protected void onInstallSwitchBarToggleSwitch() {
        super.onInstallSwitchBarToggleSwitch();
        int i = Settings.Secure.getInt(getContentResolver(), "accessibility_autoclick_enabled", 0);
        this.mSwitchBar.setCheckedInternal(i == 1);
        this.mSwitchBar.addOnSwitchChangeListener(this);
        this.mDelay.setEnabled(i == 1);
    }

    @Override // com.android.car.developeroptions.accessibility.ToggleFeaturePreferenceFragment
    protected void onRemoveSwitchBarToggleSwitch() {
        super.onRemoveSwitchBarToggleSwitch();
        this.mSwitchBar.removeOnSwitchChangeListener(this);
    }

    @Override // com.android.car.developeroptions.widget.SwitchBar.OnSwitchChangeListener
    public void onSwitchChanged(Switch r1, boolean z) {
        onPreferenceToggled("accessibility_autoclick_enabled", z);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference != this.mDelay || !(obj instanceof Integer)) {
            return false;
        }
        Settings.Secure.putInt(getContentResolver(), "accessibility_autoclick_delay", seekBarProgressToDelay(((Integer) obj).intValue()));
        return true;
    }

    private int delayToSeekBarProgress(int i) {
        return (i - 200) / 100;
    }
}
