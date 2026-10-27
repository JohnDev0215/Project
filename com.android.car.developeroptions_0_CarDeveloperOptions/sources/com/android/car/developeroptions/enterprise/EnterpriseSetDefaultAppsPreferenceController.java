package com.android.car.developeroptions.enterprise;

import android.content.Context;
import android.os.UserHandle;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.applications.ApplicationFeatureProvider;
import com.android.car.developeroptions.applications.EnterpriseDefaultApps;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.users.UserFeatureProvider;
import com.android.settingslib.core.AbstractPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class EnterpriseSetDefaultAppsPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    private final ApplicationFeatureProvider mApplicationFeatureProvider;
    private final UserFeatureProvider mUserFeatureProvider;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "number_enterprise_set_default_apps";
    }

    public EnterpriseSetDefaultAppsPreferenceController(Context context) {
        super(context);
        FeatureFactory factory = FeatureFactory.getFactory(context);
        this.mApplicationFeatureProvider = factory.getApplicationFeatureProvider(context);
        this.mUserFeatureProvider = factory.getUserFeatureProvider(context);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        int numberOfEnterpriseSetDefaultApps = getNumberOfEnterpriseSetDefaultApps();
        preference.setSummary(this.mContext.getResources().getQuantityString(R.plurals.enterprise_privacy_number_packages, numberOfEnterpriseSetDefaultApps, Integer.valueOf(numberOfEnterpriseSetDefaultApps)));
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return getNumberOfEnterpriseSetDefaultApps() > 0;
    }

    private int getNumberOfEnterpriseSetDefaultApps() {
        int size = 0;
        for (UserHandle userHandle : this.mUserFeatureProvider.getUserProfiles()) {
            for (EnterpriseDefaultApps enterpriseDefaultApps : EnterpriseDefaultApps.values()) {
                size += this.mApplicationFeatureProvider.findPersistentPreferredActivities(userHandle.getIdentifier(), enterpriseDefaultApps.getIntents()).size();
            }
        }
        return size;
    }
}
