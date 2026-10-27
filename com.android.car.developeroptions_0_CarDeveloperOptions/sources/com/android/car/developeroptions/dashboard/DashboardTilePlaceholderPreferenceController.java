package com.android.car.developeroptions.dashboard;

import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;

/* JADX INFO: loaded from: classes.dex */
class DashboardTilePlaceholderPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    private int mOrder;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "dashboard_tile_placeholder";
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return false;
    }

    public DashboardTilePlaceholderPreferenceController(Context context) {
        super(context);
        this.mOrder = Integer.MAX_VALUE;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        Preference preferenceFindPreference = preferenceScreen.findPreference(getPreferenceKey());
        if (preferenceFindPreference != null) {
            this.mOrder = preferenceFindPreference.getOrder();
            preferenceScreen.removePreference(preferenceFindPreference);
        }
    }

    public int getOrder() {
        return this.mOrder;
    }
}
