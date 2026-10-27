package com.android.car.developeroptions.vpn2;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.IConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.security.KeyStore;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.RestrictedSettingsFragment;
import com.android.car.developeroptions.widget.GearPreference;
import com.android.internal.annotations.GuardedBy;
import com.android.internal.net.LegacyVpnInfo;
import com.android.internal.net.VpnConfig;
import com.android.internal.net.VpnProfile;
import com.android.internal.util.ArrayUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.google.android.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class VpnSettings extends RestrictedSettingsFragment implements Handler.Callback, Preference.OnPreferenceClickListener {
    private static final NetworkRequest VPN_REQUEST = new NetworkRequest.Builder().removeCapability(15).removeCapability(13).removeCapability(14).build();
    private Map<AppVpnInfo, AppPreference> mAppPreferences;
    private LegacyVpnInfo mConnectedLegacyVpn;
    private ConnectivityManager mConnectivityManager;
    private final IConnectivityManager mConnectivityService;
    private GearPreference.OnGearClickListener mGearListener;
    private final KeyStore mKeyStore;
    private Map<String, LegacyVpnPreference> mLegacyVpnPreferences;
    private ConnectivityManager.NetworkCallback mNetworkCallback;
    private boolean mUnavailable;

    @GuardedBy({"this"})
    private Handler mUpdater;
    private HandlerThread mUpdaterThread;
    private UserManager mUserManager;

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_vpn;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 100;
    }

    public VpnSettings() {
        super("no_config_vpn");
        this.mConnectivityService = IConnectivityManager.Stub.asInterface(ServiceManager.getService("connectivity"));
        this.mKeyStore = KeyStore.getInstance();
        this.mLegacyVpnPreferences = new ArrayMap();
        this.mAppPreferences = new ArrayMap();
        this.mGearListener = new GearPreference.OnGearClickListener() { // from class: com.android.car.developeroptions.vpn2.VpnSettings.1
            @Override // com.android.car.developeroptions.widget.GearPreference.OnGearClickListener
            public void onGearClick(GearPreference gearPreference) {
                if (gearPreference instanceof LegacyVpnPreference) {
                    ConfigDialogFragment.show(VpnSettings.this, ((LegacyVpnPreference) gearPreference).getProfile(), true, true);
                } else if (gearPreference instanceof AppPreference) {
                    AppManagementFragment.show(VpnSettings.this.getPrefContext(), (AppPreference) gearPreference, VpnSettings.this.getMetricsCategory());
                }
            }
        };
        this.mNetworkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.android.car.developeroptions.vpn2.VpnSettings.2
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(Network network) {
                if (VpnSettings.this.mUpdater != null) {
                    VpnSettings.this.mUpdater.sendEmptyMessage(0);
                }
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(Network network) {
                if (VpnSettings.this.mUpdater != null) {
                    VpnSettings.this.mUpdater.sendEmptyMessage(0);
                }
            }
        };
    }

    @Override // com.android.car.developeroptions.RestrictedSettingsFragment, com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mUserManager = (UserManager) getSystemService("user");
        this.mConnectivityManager = (ConnectivityManager) getSystemService("connectivity");
        boolean zIsUiRestricted = isUiRestricted();
        this.mUnavailable = zIsUiRestricted;
        setHasOptionsMenu(!zIsUiRestricted);
        addPreferencesFromResource(R.xml.vpn_settings2);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.vpn, menu);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        for (int i = 0; i < menu.size(); i++) {
            if (isUiRestrictedByOnlyAdmin()) {
                RestrictedLockUtilsInternal.setMenuItemAsDisabledByAdmin(getPrefContext(), menu.getItem(i), getRestrictionEnforcedAdmin());
            } else {
                menu.getItem(i).setEnabled(!this.mUnavailable);
            }
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.vpn_create) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            while (this.mLegacyVpnPreferences.containsKey(Long.toHexString(jCurrentTimeMillis))) {
                jCurrentTimeMillis++;
            }
            ConfigDialogFragment.show(this, new VpnProfile(Long.toHexString(jCurrentTimeMillis)), true, false);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // com.android.car.developeroptions.RestrictedSettingsFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        boolean zHasUserRestriction = this.mUserManager.hasUserRestriction("no_config_vpn");
        this.mUnavailable = zHasUserRestriction;
        if (zHasUserRestriction) {
            if (!isUiRestrictedByOnlyAdmin()) {
                getEmptyTextView().setText(R.string.vpn_settings_not_available);
            }
            getPreferenceScreen().removeAll();
            return;
        }
        setEmptyView(getEmptyTextView());
        getEmptyTextView().setText(R.string.vpn_no_vpns_added);
        this.mConnectivityManager.registerNetworkCallback(VPN_REQUEST, this.mNetworkCallback);
        HandlerThread handlerThread = new HandlerThread("Refresh VPN list in background");
        this.mUpdaterThread = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(this.mUpdaterThread.getLooper(), this);
        this.mUpdater = handler;
        handler.sendEmptyMessage(0);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        if (this.mUnavailable) {
            super.onPause();
            return;
        }
        this.mConnectivityManager.unregisterNetworkCallback(this.mNetworkCallback);
        synchronized (this) {
            this.mUpdater.removeCallbacksAndMessages(null);
            this.mUpdater = null;
            this.mUpdaterThread.quit();
            this.mUpdaterThread = null;
        }
        super.onPause();
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return true;
        }
        Context applicationContext = activity.getApplicationContext();
        List<VpnProfile> listLoadVpnProfiles = loadVpnProfiles(this.mKeyStore, new int[0]);
        List<AppVpnInfo> vpnApps = getVpnApps(applicationContext, true);
        Map<String, LegacyVpnInfo> connectedLegacyVpns = getConnectedLegacyVpns();
        Set<AppVpnInfo> connectedAppVpns = getConnectedAppVpns();
        Set<AppVpnInfo> alwaysOnAppVpnInfos = getAlwaysOnAppVpnInfos();
        String lockdownVpn = VpnUtils.getLockdownVpn();
        UpdatePreferences updatePreferences = new UpdatePreferences(this);
        updatePreferences.legacyVpns(listLoadVpnProfiles, connectedLegacyVpns, lockdownVpn);
        updatePreferences.appVpns(vpnApps, connectedAppVpns, alwaysOnAppVpnInfos);
        activity.runOnUiThread(updatePreferences);
        synchronized (this) {
            if (this.mUpdater != null) {
                this.mUpdater.removeMessages(0);
                this.mUpdater.sendEmptyMessageDelayed(0, 1000L);
            }
        }
        return true;
    }

    static class UpdatePreferences implements Runnable {
        private final VpnSettings mSettings;
        private List<VpnProfile> vpnProfiles = Collections.emptyList();
        private List<AppVpnInfo> vpnApps = Collections.emptyList();
        private Map<String, LegacyVpnInfo> connectedLegacyVpns = Collections.emptyMap();
        private Set<AppVpnInfo> connectedAppVpns = Collections.emptySet();
        private Set<AppVpnInfo> alwaysOnAppVpnInfos = Collections.emptySet();
        private String lockdownVpnKey = null;

        public UpdatePreferences(VpnSettings vpnSettings) {
            this.mSettings = vpnSettings;
        }

        public final UpdatePreferences legacyVpns(List<VpnProfile> list, Map<String, LegacyVpnInfo> map, String str) {
            this.vpnProfiles = list;
            this.connectedLegacyVpns = map;
            this.lockdownVpnKey = str;
            return this;
        }

        public final UpdatePreferences appVpns(List<AppVpnInfo> list, Set<AppVpnInfo> set, Set<AppVpnInfo> set2) {
            this.vpnApps = list;
            this.connectedAppVpns = set;
            this.alwaysOnAppVpnInfos = set2;
            return this;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mSettings.canAddPreferences()) {
                ArraySet arraySet = new ArraySet();
                Iterator<VpnProfile> it = this.vpnProfiles.iterator();
                while (true) {
                    boolean z = false;
                    if (!it.hasNext()) {
                        break;
                    }
                    VpnProfile next = it.next();
                    LegacyVpnPreference legacyVpnPreferenceFindOrCreatePreference = this.mSettings.findOrCreatePreference(next, true);
                    if (this.connectedLegacyVpns.containsKey(next.key)) {
                        legacyVpnPreferenceFindOrCreatePreference.setState(this.connectedLegacyVpns.get(next.key).state);
                    } else {
                        legacyVpnPreferenceFindOrCreatePreference.setState(ManageablePreference.STATE_NONE);
                    }
                    String str = this.lockdownVpnKey;
                    if (str != null && str.equals(next.key)) {
                        z = true;
                    }
                    legacyVpnPreferenceFindOrCreatePreference.setAlwaysOn(z);
                    arraySet.add(legacyVpnPreferenceFindOrCreatePreference);
                }
                for (LegacyVpnInfo legacyVpnInfo : this.connectedLegacyVpns.values()) {
                    LegacyVpnPreference legacyVpnPreferenceFindOrCreatePreference2 = this.mSettings.findOrCreatePreference(new VpnProfile(legacyVpnInfo.key), false);
                    legacyVpnPreferenceFindOrCreatePreference2.setState(legacyVpnInfo.state);
                    String str2 = this.lockdownVpnKey;
                    legacyVpnPreferenceFindOrCreatePreference2.setAlwaysOn(str2 != null && str2.equals(legacyVpnInfo.key));
                    arraySet.add(legacyVpnPreferenceFindOrCreatePreference2);
                }
                for (AppVpnInfo appVpnInfo : this.vpnApps) {
                    AppPreference appPreferenceFindOrCreatePreference = this.mSettings.findOrCreatePreference(appVpnInfo);
                    if (this.connectedAppVpns.contains(appVpnInfo)) {
                        appPreferenceFindOrCreatePreference.setState(3);
                    } else {
                        appPreferenceFindOrCreatePreference.setState(AppPreference.STATE_DISCONNECTED);
                    }
                    appPreferenceFindOrCreatePreference.setAlwaysOn(this.alwaysOnAppVpnInfos.contains(appVpnInfo));
                    arraySet.add(appPreferenceFindOrCreatePreference);
                }
                this.mSettings.setShownPreferences(arraySet);
            }
        }
    }

    public boolean canAddPreferences() {
        return isAdded();
    }

    public void setShownPreferences(Collection<Preference> collection) {
        this.mLegacyVpnPreferences.values().retainAll(collection);
        this.mAppPreferences.values().retainAll(collection);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        for (int preferenceCount = preferenceScreen.getPreferenceCount() - 1; preferenceCount >= 0; preferenceCount--) {
            Preference preference = preferenceScreen.getPreference(preferenceCount);
            if (collection.contains(preference)) {
                collection.remove(preference);
            } else {
                preferenceScreen.removePreference(preference);
            }
        }
        Iterator<Preference> it = collection.iterator();
        while (it.hasNext()) {
            preferenceScreen.addPreference(it.next());
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        if (preference instanceof LegacyVpnPreference) {
            VpnProfile profile = ((LegacyVpnPreference) preference).getProfile();
            LegacyVpnInfo legacyVpnInfo = this.mConnectedLegacyVpn;
            if (legacyVpnInfo != null && profile.key.equals(legacyVpnInfo.key)) {
                LegacyVpnInfo legacyVpnInfo2 = this.mConnectedLegacyVpn;
                if (legacyVpnInfo2.state == 3) {
                    try {
                        legacyVpnInfo2.intent.send();
                        return true;
                    } catch (Exception e) {
                        Log.w("VpnSettings", "Starting config intent failed", e);
                    }
                }
            }
            ConfigDialogFragment.show(this, profile, false, true);
            return true;
        }
        if (!(preference instanceof AppPreference)) {
            return false;
        }
        AppPreference appPreference = (AppPreference) preference;
        boolean z = appPreference.getState() == 3;
        if (!z) {
            try {
                UserHandle userHandleOf = UserHandle.of(appPreference.getUserId());
                Context contextCreatePackageContextAsUser = getActivity().createPackageContextAsUser(getActivity().getPackageName(), 0, userHandleOf);
                Intent launchIntentForPackage = contextCreatePackageContextAsUser.getPackageManager().getLaunchIntentForPackage(appPreference.getPackageName());
                if (launchIntentForPackage != null) {
                    contextCreatePackageContextAsUser.startActivityAsUser(launchIntentForPackage, userHandleOf);
                    return true;
                }
            } catch (PackageManager.NameNotFoundException e2) {
                Log.w("VpnSettings", "VPN provider does not exist: " + appPreference.getPackageName(), e2);
            }
        }
        AppDialogFragment.show(this, appPreference.getPackageInfo(), appPreference.getLabel(), false, z);
        return true;
    }

    public LegacyVpnPreference findOrCreatePreference(VpnProfile vpnProfile, boolean z) {
        boolean z2;
        LegacyVpnPreference legacyVpnPreference = this.mLegacyVpnPreferences.get(vpnProfile.key);
        if (legacyVpnPreference == null) {
            legacyVpnPreference = new LegacyVpnPreference(getPrefContext());
            legacyVpnPreference.setOnGearClickListener(this.mGearListener);
            legacyVpnPreference.setOnPreferenceClickListener(this);
            this.mLegacyVpnPreferences.put(vpnProfile.key, legacyVpnPreference);
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 || z) {
            legacyVpnPreference.setProfile(vpnProfile);
        }
        return legacyVpnPreference;
    }

    public AppPreference findOrCreatePreference(AppVpnInfo appVpnInfo) {
        AppPreference appPreference = this.mAppPreferences.get(appVpnInfo);
        if (appPreference != null) {
            return appPreference;
        }
        AppPreference appPreference2 = new AppPreference(getPrefContext(), appVpnInfo.userId, appVpnInfo.packageName);
        appPreference2.setOnGearClickListener(this.mGearListener);
        appPreference2.setOnPreferenceClickListener(this);
        this.mAppPreferences.put(appVpnInfo, appPreference2);
        return appPreference2;
    }

    private Map<String, LegacyVpnInfo> getConnectedLegacyVpns() {
        try {
            LegacyVpnInfo legacyVpnInfo = this.mConnectivityService.getLegacyVpnInfo(UserHandle.myUserId());
            this.mConnectedLegacyVpn = legacyVpnInfo;
            if (legacyVpnInfo != null) {
                return Collections.singletonMap(legacyVpnInfo.key, legacyVpnInfo);
            }
        } catch (RemoteException e) {
            Log.e("VpnSettings", "Failure updating VPN list with connected legacy VPNs", e);
        }
        return Collections.emptyMap();
    }

    private Set<AppVpnInfo> getConnectedAppVpns() {
        ArraySet arraySet = new ArraySet();
        try {
            for (UserHandle userHandle : this.mUserManager.getUserProfiles()) {
                VpnConfig vpnConfig = this.mConnectivityService.getVpnConfig(userHandle.getIdentifier());
                if (vpnConfig != null && !vpnConfig.legacy) {
                    arraySet.add(new AppVpnInfo(userHandle.getIdentifier(), vpnConfig.user));
                }
            }
        } catch (RemoteException e) {
            Log.e("VpnSettings", "Failure updating VPN list with connected app VPNs", e);
        }
        return arraySet;
    }

    private Set<AppVpnInfo> getAlwaysOnAppVpnInfos() {
        ArraySet arraySet = new ArraySet();
        Iterator<UserHandle> it = this.mUserManager.getUserProfiles().iterator();
        while (it.hasNext()) {
            int identifier = it.next().getIdentifier();
            String alwaysOnVpnPackageForUser = this.mConnectivityManager.getAlwaysOnVpnPackageForUser(identifier);
            if (alwaysOnVpnPackageForUser != null) {
                arraySet.add(new AppVpnInfo(identifier, alwaysOnVpnPackageForUser));
            }
        }
        return arraySet;
    }

    static List<AppVpnInfo> getVpnApps(Context context, boolean z) {
        Set setSingleton;
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        if (z) {
            setSingleton = new ArraySet();
            Iterator<UserHandle> it = UserManager.get(context).getUserProfiles().iterator();
            while (it.hasNext()) {
                setSingleton.add(Integer.valueOf(it.next().getIdentifier()));
            }
        } else {
            setSingleton = Collections.singleton(Integer.valueOf(UserHandle.myUserId()));
        }
        List<AppOpsManager.PackageOps> packagesForOps = ((AppOpsManager) context.getSystemService("appops")).getPackagesForOps(new int[]{47});
        if (packagesForOps != null) {
            for (AppOpsManager.PackageOps packageOps : packagesForOps) {
                int userId = UserHandle.getUserId(packageOps.getUid());
                if (setSingleton.contains(Integer.valueOf(userId))) {
                    boolean z2 = false;
                    for (AppOpsManager.OpEntry opEntry : packageOps.getOps()) {
                        if (opEntry.getOp() == 47 && opEntry.getMode() == 0) {
                            z2 = true;
                        }
                    }
                    if (z2) {
                        arrayListNewArrayList.add(new AppVpnInfo(userId, packageOps.getPackageName()));
                    }
                }
            }
        }
        Collections.sort(arrayListNewArrayList);
        return arrayListNewArrayList;
    }

    static List<VpnProfile> loadVpnProfiles(KeyStore keyStore, int... iArr) {
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        for (String str : keyStore.list("VPN_")) {
            VpnProfile vpnProfileDecode = VpnProfile.decode(str, keyStore.get("VPN_" + str));
            if (vpnProfileDecode != null && !ArrayUtils.contains(iArr, vpnProfileDecode.type)) {
                arrayListNewArrayList.add(vpnProfileDecode);
            }
        }
        return arrayListNewArrayList;
    }
}
