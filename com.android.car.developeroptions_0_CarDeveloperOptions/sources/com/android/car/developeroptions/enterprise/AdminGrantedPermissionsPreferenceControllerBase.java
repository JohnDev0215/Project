package com.android.car.developeroptions.enterprise;

import android.content.Context;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.applications.ApplicationFeatureProvider;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.core.AbstractPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public abstract class AdminGrantedPermissionsPreferenceControllerBase extends AbstractPreferenceController implements PreferenceControllerMixin {
    private final boolean mAsync;
    private final ApplicationFeatureProvider mFeatureProvider;
    private boolean mHasApps;
    private final String[] mPermissions;

    public AdminGrantedPermissionsPreferenceControllerBase(Context context, boolean z, String[] strArr) {
        super(context);
        this.mPermissions = strArr;
        this.mFeatureProvider = FeatureFactory.getFactory(context).getApplicationFeatureProvider(context);
        this.mAsync = z;
        this.mHasApps = false;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(final Preference preference) {
        this.mFeatureProvider.calculateNumberOfAppsWithAdminGrantedPermissions(this.mPermissions, true, new ApplicationFeatureProvider.NumberOfAppsCallback() { // from class: com.android.car.developeroptions.enterprise.-$$Lambda$AdminGrantedPermissionsPreferenceControllerBase$HjP0QnANIGl2_ocdEQ6JQCmv3zY
            @Override // com.android.car.developeroptions.applications.ApplicationFeatureProvider.NumberOfAppsCallback
            public final void onNumberOfAppsResult(int i) {
                this.f$0.lambda$updateState$0$AdminGrantedPermissionsPreferenceControllerBase(preference, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateState$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$updateState$0$AdminGrantedPermissionsPreferenceControllerBase(Preference preference, int i) {
        if (i == 0) {
            this.mHasApps = false;
        } else {
            preference.setSummary(this.mContext.getResources().getQuantityString(R.plurals.enterprise_privacy_number_packages_lower_bound, i, Integer.valueOf(i)));
            this.mHasApps = true;
        }
        preference.setVisible(this.mHasApps);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        if (this.mAsync) {
            return true;
        }
        final Boolean[] boolArr = {null};
        this.mFeatureProvider.calculateNumberOfAppsWithAdminGrantedPermissions(this.mPermissions, false, new ApplicationFeatureProvider.NumberOfAppsCallback() { // from class: com.android.car.developeroptions.enterprise.-$$Lambda$AdminGrantedPermissionsPreferenceControllerBase$RZL0skq1vtNDpGSvQDzQr6bh5q4
            @Override // com.android.car.developeroptions.applications.ApplicationFeatureProvider.NumberOfAppsCallback
            public final void onNumberOfAppsResult(int i) {
                AdminGrantedPermissionsPreferenceControllerBase.lambda$isAvailable$1(boolArr, i);
            }
        });
        boolean zBooleanValue = boolArr[0].booleanValue();
        this.mHasApps = zBooleanValue;
        return zBooleanValue;
    }

    static /* synthetic */ void lambda$isAvailable$1(Boolean[] boolArr, int i) {
        boolArr[0] = Boolean.valueOf(i > 0);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (getPreferenceKey().equals(preference.getKey()) && this.mHasApps) {
            return super.handlePreferenceTreeClick(preference);
        }
        return false;
    }
}
