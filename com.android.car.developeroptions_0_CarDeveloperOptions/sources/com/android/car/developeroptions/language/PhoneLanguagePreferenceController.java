package com.android.car.developeroptions.language;

import android.content.Context;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.localepicker.LocaleListEditor;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PhoneLanguagePreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "phone_language";
    }

    public PhoneLanguagePreferenceController(Context context) {
        super(context);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.mContext.getResources().getBoolean(R.bool.config_show_phone_language) && this.mContext.getAssets().getLocales().length > 1;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        if (preference == null) {
            return;
        }
        preference.setSummary(FeatureFactory.getFactory(this.mContext).getLocaleFeatureProvider().getLocaleNames());
    }

    @Override // com.android.car.developeroptions.core.PreferenceControllerMixin
    public void updateNonIndexableKeys(List<String> list) {
        list.add(getPreferenceKey());
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!"phone_language".equals(preference.getKey())) {
            return false;
        }
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(LocaleListEditor.class.getName());
        subSettingLauncher.setSourceMetricsCategory(750);
        subSettingLauncher.setTitleRes(R.string.language_picker_title);
        subSettingLauncher.launch();
        return true;
    }
}
