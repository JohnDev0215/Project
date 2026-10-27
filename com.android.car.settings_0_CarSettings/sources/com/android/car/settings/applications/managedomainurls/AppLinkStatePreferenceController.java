package com.android.car.settings.applications.managedomainurls;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.preference.ListPreference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;

/* JADX INFO: loaded from: classes5.dex */
public class AppLinkStatePreferenceController extends AppLaunchSettingsBasePreferenceController<ListPreference> {
    private static final Logger LOG = new Logger((Class<?>) AppLinkStatePreferenceController.class);
    private boolean mHasDomainUrls;
    private final PackageManager mPm;

    public AppLinkStatePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mPm = context.getPackageManager();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<ListPreference> getPreferenceType() {
        return ListPreference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        this.mHasDomainUrls = (getAppEntry().info.privateFlags & 16) != 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(ListPreference preference) {
        if (isBrowserApp()) {
            preference.setEnabled(false);
            return;
        }
        preference.setEnabled(this.mHasDomainUrls);
        preference.setEntries(new CharSequence[]{getContext().getString(R.string.app_link_open_always), getContext().getString(R.string.app_link_open_ask), getContext().getString(R.string.app_link_open_never)});
        preference.setEntryValues(new CharSequence[]{Integer.toString(2), Integer.toString(4), Integer.toString(3)});
        if (this.mHasDomainUrls) {
            int state = this.mPm.getIntentVerificationStatusAsUser(getPackageName(), getCurrentUserId());
            preference.setValueIndex(linkStateToIndex(state));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceChanged(ListPreference preference, Object newValue) {
        if (isBrowserApp()) {
            return false;
        }
        int newState = Integer.parseInt((String) newValue);
        int priorState = this.mPm.getIntentVerificationStatusAsUser(getPackageName(), getCurrentUserId());
        if (priorState == newState) {
            return false;
        }
        boolean success = this.mPm.updateIntentVerificationStatusAsUser(getPackageName(), newState, getCurrentUserId());
        if (success) {
            int updatedState = this.mPm.getIntentVerificationStatusAsUser(getPackageName(), getCurrentUserId());
            return newState == updatedState;
        }
        LOG.e("Couldn't update intent verification status!");
        return success;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private int linkStateToIndex(int state) {
        if (state == 2) {
            return ((ListPreference) getPreference()).findIndexOfValue(Integer.toString(2));
        }
        if (state == 3) {
            return ((ListPreference) getPreference()).findIndexOfValue(Integer.toString(3));
        }
        return ((ListPreference) getPreference()).findIndexOfValue(Integer.toString(4));
    }
}
