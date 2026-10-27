package com.android.car.settings.location;

import android.content.Context;
import androidx.preference.Preference;
import com.android.car.ui.preference.CarUiPreference;
import com.android.settingslib.location.InjectedSetting;
import com.android.settingslib.location.SettingsInjector;

/* JADX INFO: loaded from: classes5.dex */
public class CarLocationSettingsInjector extends SettingsInjector {
    public CarLocationSettingsInjector(Context context) {
        super(context);
    }

    @Override // com.android.settingslib.location.SettingsInjector
    protected Preference createPreference(Context prefContext, InjectedSetting setting) {
        return new CarUiPreference(prefContext);
    }
}
