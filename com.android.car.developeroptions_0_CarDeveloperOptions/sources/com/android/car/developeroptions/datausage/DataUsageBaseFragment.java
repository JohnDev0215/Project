package com.android.car.developeroptions.datausage;

import android.content.Context;
import android.net.INetworkStatsService;
import android.net.NetworkPolicy;
import android.net.NetworkPolicyManager;
import android.os.Bundle;
import android.os.INetworkManagementService;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.datausage.TemplatePreference;
import com.android.settingslib.NetworkPolicyEditor;

/* JADX INFO: loaded from: classes.dex */
public abstract class DataUsageBaseFragment extends DashboardFragment {
    protected final TemplatePreference.NetworkServices services = new TemplatePreference.NetworkServices();

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context context = getContext();
        this.services.mNetworkService = INetworkManagementService.Stub.asInterface(ServiceManager.getService("network_management"));
        this.services.mStatsService = INetworkStatsService.Stub.asInterface(ServiceManager.getService("netstats"));
        this.services.mPolicyManager = (NetworkPolicyManager) context.getSystemService("netpolicy");
        TemplatePreference.NetworkServices networkServices = this.services;
        networkServices.mPolicyEditor = new NetworkPolicyEditor(networkServices.mPolicyManager);
        this.services.mTelephonyManager = TelephonyManager.from(context);
        this.services.mSubscriptionManager = SubscriptionManager.from(context);
        this.services.mUserManager = UserManager.get(context);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.services.mPolicyEditor.read();
    }

    protected boolean isAdmin() {
        return this.services.mUserManager.isAdminUser();
    }

    protected boolean isMobileDataAvailable(int i) {
        return this.services.mSubscriptionManager.getActiveSubscriptionInfo(i) != null;
    }

    protected boolean isNetworkPolicyModifiable(NetworkPolicy networkPolicy, int i) {
        return networkPolicy != null && isBandwidthControlEnabled() && this.services.mUserManager.isAdminUser() && isDataEnabled(i);
    }

    private boolean isDataEnabled(int i) {
        if (i == -1) {
            return true;
        }
        return this.services.mTelephonyManager.getDataEnabled(i);
    }

    protected boolean isBandwidthControlEnabled() {
        try {
            return this.services.mNetworkService.isBandwidthControlEnabled();
        } catch (RemoteException e) {
            Log.w("DataUsageBase", "problem talking with INetworkManagementService: ", e);
            return false;
        }
    }
}
