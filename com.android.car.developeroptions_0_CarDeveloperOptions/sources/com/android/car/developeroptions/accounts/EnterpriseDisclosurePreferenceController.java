package com.android.car.developeroptions.accounts;

import android.content.Context;
import android.content.IntentFilter;
import androidx.preference.Preference;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.enterprise.EnterprisePrivacyFeatureProvider;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class EnterpriseDisclosurePreferenceController extends BasePreferenceController {
    private final EnterprisePrivacyFeatureProvider mFeatureProvider;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public EnterpriseDisclosurePreferenceController(Context context, String str) {
        super(context, str);
        this.mFeatureProvider = FeatureFactory.getFactory(this.mContext).getEnterprisePrivacyFeatureProvider(this.mContext);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return getDisclosure() == null ? 3 : 0;
    }

    CharSequence getDisclosure() {
        return this.mFeatureProvider.getDeviceOwnerDisclosure();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        CharSequence disclosure = getDisclosure();
        if (disclosure == null) {
            return;
        }
        preference.setTitle(disclosure);
    }
}
