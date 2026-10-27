package com.android.car.developeroptions.datausage;

import android.content.Context;
import android.content.IntentFilter;
import android.net.INetworkStatsService;
import android.net.NetworkPolicyManager;
import android.net.NetworkTemplate;
import android.os.INetworkManagementService;
import android.os.ServiceManager;
import android.os.UserManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.datausage.TemplatePreference;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.NetworkPolicyEditor;

/* JADX INFO: loaded from: classes.dex */
public class BillingCyclePreferenceController extends BasePreferenceController {
    private int mSubscriptionId;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
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

    public BillingCyclePreferenceController(Context context, String str) {
        super(context, str);
    }

    public void init(int i) {
        this.mSubscriptionId = i;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        BillingCyclePreference billingCyclePreference = (BillingCyclePreference) preferenceScreen.findPreference(getPreferenceKey());
        TemplatePreference.NetworkServices networkServices = new TemplatePreference.NetworkServices();
        networkServices.mNetworkService = INetworkManagementService.Stub.asInterface(ServiceManager.getService("network_management"));
        INetworkStatsService.Stub.asInterface(ServiceManager.getService("netstats"));
        NetworkPolicyManager networkPolicyManager = (NetworkPolicyManager) this.mContext.getSystemService(NetworkPolicyManager.class);
        networkServices.mPolicyManager = networkPolicyManager;
        networkServices.mPolicyEditor = new NetworkPolicyEditor(networkPolicyManager);
        networkServices.mTelephonyManager = (TelephonyManager) this.mContext.getSystemService(TelephonyManager.class);
        networkServices.mSubscriptionManager = (SubscriptionManager) this.mContext.getSystemService(SubscriptionManager.class);
        networkServices.mUserManager = (UserManager) this.mContext.getSystemService(UserManager.class);
        billingCyclePreference.setTemplate(NetworkTemplate.normalize(NetworkTemplate.buildTemplateMobileAll(networkServices.mTelephonyManager.getSubscriberId(this.mSubscriptionId)), networkServices.mTelephonyManager.getMergedSubscriberIds()), this.mSubscriptionId, networkServices);
    }
}
