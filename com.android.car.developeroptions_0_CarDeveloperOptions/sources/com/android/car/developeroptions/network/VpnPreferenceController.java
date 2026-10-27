package com.android.car.developeroptions.network;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.net.ConnectivityManager;
import android.net.IConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.util.Log;
import android.util.SparseArray;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.internal.net.LegacyVpnInfo;
import com.android.internal.net.VpnConfig;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.android.settingslib.utils.ThreadUtils;

/* JADX INFO: loaded from: classes.dex */
public class VpnPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnResume, OnPause {
    private static final NetworkRequest REQUEST = new NetworkRequest.Builder().removeCapability(15).removeCapability(13).removeCapability(14).build();
    private final ConnectivityManager mConnectivityManager;
    private final IConnectivityManager mConnectivityManagerService;
    private final ConnectivityManager.NetworkCallback mNetworkCallback;
    private Preference mPreference;
    private final String mToggleable;
    private final UserManager mUserManager;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "vpn_settings";
    }

    public VpnPreferenceController(Context context) {
        super(context);
        this.mNetworkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.android.car.developeroptions.network.VpnPreferenceController.1
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(Network network) {
                VpnPreferenceController.this.updateSummary();
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(Network network) {
                VpnPreferenceController.this.updateSummary();
            }
        };
        this.mToggleable = Settings.Global.getString(context.getContentResolver(), "airplane_mode_toggleable_radios");
        this.mUserManager = (UserManager) context.getSystemService("user");
        this.mConnectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.mConnectivityManagerService = IConnectivityManager.Stub.asInterface(ServiceManager.getService("connectivity"));
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        Preference preference;
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference("vpn_settings");
        String str = this.mToggleable;
        if ((str == null || !str.contains("wifi")) && (preference = this.mPreference) != null) {
            preference.setDependency("airplane_mode");
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return !RestrictedLockUtilsInternal.hasBaseUserRestriction(this.mContext, "no_config_vpn", UserHandle.myUserId());
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnPause
    public void onPause() {
        if (isAvailable()) {
            this.mConnectivityManager.unregisterNetworkCallback(this.mNetworkCallback);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        if (isAvailable()) {
            this.mConnectivityManager.registerNetworkCallback(REQUEST, this.mNetworkCallback);
        }
    }

    void updateSummary() {
        int i;
        final String nameForVpnConfig;
        LegacyVpnInfo legacyVpnInfo;
        if (this.mPreference == null) {
            return;
        }
        SparseArray sparseArray = new SparseArray();
        try {
            for (UserInfo userInfo : this.mUserManager.getUsers()) {
                VpnConfig vpnConfig = this.mConnectivityManagerService.getVpnConfig(userInfo.id);
                if (vpnConfig != null && (!vpnConfig.legacy || ((legacyVpnInfo = this.mConnectivityManagerService.getLegacyVpnInfo(userInfo.id)) != null && legacyVpnInfo.state == 3))) {
                    sparseArray.put(userInfo.id, vpnConfig);
                }
            }
            UserInfo userInfo2 = this.mUserManager.getUserInfo(UserHandle.myUserId());
            if (userInfo2.isRestricted()) {
                i = userInfo2.restrictedProfileParentId;
            } else {
                i = userInfo2.id;
            }
            VpnConfig vpnConfig2 = (VpnConfig) sparseArray.get(i);
            if (vpnConfig2 == null) {
                nameForVpnConfig = this.mContext.getString(R.string.vpn_disconnected_summary);
            } else {
                nameForVpnConfig = getNameForVpnConfig(vpnConfig2, UserHandle.of(i));
            }
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.car.developeroptions.network.-$$Lambda$VpnPreferenceController$t50KKSW3JuVrxTlSmfPNygPqwpQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$updateSummary$0$VpnPreferenceController(nameForVpnConfig);
                }
            });
        } catch (RemoteException e) {
            Log.e("VpnPreferenceController", "Unable to list active VPNs", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateSummary$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$updateSummary$0$VpnPreferenceController(String str) {
        this.mPreference.setSummary(str);
    }

    String getNameForVpnConfig(VpnConfig vpnConfig, UserHandle userHandle) {
        if (vpnConfig.legacy) {
            return this.mContext.getString(R.string.wifi_display_status_connected);
        }
        String str = vpnConfig.user;
        try {
            return VpnConfig.getVpnLabel(this.mContext.createPackageContextAsUser(this.mContext.getPackageName(), 0, userHandle), str).toString();
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("VpnPreferenceController", "Package " + str + " is not present", e);
            return null;
        }
    }
}
