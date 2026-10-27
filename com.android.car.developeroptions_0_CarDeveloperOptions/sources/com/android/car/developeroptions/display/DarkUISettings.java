package com.android.car.developeroptions.display;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.SearchIndexableResource;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.widget.RadioButtonPickerFragment;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import com.android.settingslib.widget.CandidateInfo;
import com.android.settingslib.widget.FooterPreference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DarkUISettings extends RadioButtonPickerFragment {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.display.DarkUISettings.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.dark_ui_settings;
            return Arrays.asList(searchIndexableResource);
        }
    };
    private DarkUISettingsRadioButtonsController mController;
    private Preference mFooter;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1698;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.dark_ui_settings;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        setIllustration(0, 0);
        FooterPreference footerPreference = new FooterPreference(context);
        this.mFooter = footerPreference;
        footerPreference.setIcon(android.R.color.transparent);
        this.mController = new DarkUISettingsRadioButtonsController(context, this.mFooter);
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected List<? extends CandidateInfo> getCandidates() {
        Context context = getContext();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DarkUISettingsCandidateInfo(DarkUISettingsRadioButtonsController.modeToDescription(context, 2), null, "key_dark_ui_settings_dark", true));
        arrayList.add(new DarkUISettingsCandidateInfo(DarkUISettingsRadioButtonsController.modeToDescription(context, 1), null, "key_dark_ui_settings_light", true));
        return arrayList;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected void addStaticPreferences(PreferenceScreen preferenceScreen) {
        preferenceScreen.addPreference(this.mFooter);
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected String getDefaultKey() {
        return this.mController.getDefaultKey();
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected boolean setDefaultKey(String str) {
        return this.mController.setDefaultKey(str);
    }

    static class DarkUISettingsCandidateInfo extends CandidateInfo {
        private final String mKey;
        private final CharSequence mLabel;

        @Override // com.android.settingslib.widget.CandidateInfo
        public Drawable loadIcon() {
            return null;
        }

        DarkUISettingsCandidateInfo(CharSequence charSequence, CharSequence charSequence2, String str, boolean z) {
            super(z);
            this.mLabel = charSequence;
            this.mKey = str;
        }

        @Override // com.android.settingslib.widget.CandidateInfo
        public CharSequence loadLabel() {
            return this.mLabel;
        }

        @Override // com.android.settingslib.widget.CandidateInfo
        public String getKey() {
            return this.mKey;
        }
    }
}
