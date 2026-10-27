package com.android.car.developeroptions.network;

import android.content.Context;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class NetworkResetPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    private final NetworkResetRestrictionChecker mRestrictionChecker;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "network_reset_pref";
    }

    public NetworkResetPreferenceController(Context context) {
        super(context);
        this.mRestrictionChecker = new NetworkResetRestrictionChecker(context);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return !this.mRestrictionChecker.hasUserRestriction();
    }
}
