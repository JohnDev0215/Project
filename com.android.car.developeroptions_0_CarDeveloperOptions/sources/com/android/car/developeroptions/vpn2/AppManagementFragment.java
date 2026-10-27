package com.android.car.developeroptions.vpn2;

import android.app.AppOpsManager;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.IConnectivityManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.android.car.developeroptions.vpn2.AppDialogFragment;
import com.android.car.developeroptions.vpn2.ConfirmLockdownFragment;
import com.android.internal.net.VpnConfig;
import com.android.internal.util.ArrayUtils;
import com.android.settingslib.RestrictedPreference;
import com.android.settingslib.RestrictedSwitchPreference;

/* JADX INFO: loaded from: classes.dex */
public class AppManagementFragment extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener, ConfirmLockdownFragment.ConfirmLockdownListener {
    private ConnectivityManager mConnectivityManager;
    private IConnectivityManager mConnectivityService;
    private PackageInfo mPackageInfo;
    private PackageManager mPackageManager;
    private String mPackageName;
    private RestrictedSwitchPreference mPreferenceAlwaysOn;
    private RestrictedPreference mPreferenceForget;
    private RestrictedSwitchPreference mPreferenceLockdown;
    private Preference mPreferenceVersion;
    private String mVpnLabel;
    private final int mUserId = UserHandle.myUserId();
    private final AppDialogFragment.Listener mForgetVpnDialogFragmentListener = new AppDialogFragment.Listener() { // from class: com.android.car.developeroptions.vpn2.AppManagementFragment.1
        @Override // com.android.car.developeroptions.vpn2.AppDialogFragment.Listener
        public void onCancel() {
        }

        @Override // com.android.car.developeroptions.vpn2.AppDialogFragment.Listener
        public void onForget() {
            if (AppManagementFragment.this.isVpnAlwaysOn()) {
                AppManagementFragment.this.setAlwaysOnVpn(false, false);
            }
            AppManagementFragment.this.finish();
        }
    };

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 100;
    }

    public static void show(Context context, AppPreference appPreference, int i) {
        Bundle bundle = new Bundle();
        bundle.putString("package", appPreference.getPackageName());
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(context);
        subSettingLauncher.setDestination(AppManagementFragment.class.getName());
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.setTitleText(appPreference.getLabel());
        subSettingLauncher.setSourceMetricsCategory(i);
        subSettingLauncher.setUserHandle(new UserHandle(appPreference.getUserId()));
        subSettingLauncher.launch();
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.vpn_app_management);
        this.mPackageManager = getContext().getPackageManager();
        this.mConnectivityManager = (ConnectivityManager) getContext().getSystemService(ConnectivityManager.class);
        this.mConnectivityService = IConnectivityManager.Stub.asInterface(ServiceManager.getService("connectivity"));
        this.mPreferenceVersion = findPreference("version");
        this.mPreferenceAlwaysOn = (RestrictedSwitchPreference) findPreference("always_on_vpn");
        this.mPreferenceLockdown = (RestrictedSwitchPreference) findPreference("lockdown_vpn");
        this.mPreferenceForget = (RestrictedPreference) findPreference("forget_vpn");
        this.mPreferenceAlwaysOn.setOnPreferenceChangeListener(this);
        this.mPreferenceLockdown.setOnPreferenceChangeListener(this);
        this.mPreferenceForget.setOnPreferenceClickListener(this);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (loadInfo()) {
            this.mPreferenceVersion.setTitle(getPrefContext().getString(R.string.vpn_version, this.mPackageInfo.versionName));
            updateUI();
        } else {
            finish();
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();
        if (((key.hashCode() == -591389790 && key.equals("forget_vpn")) ? (byte) 0 : (byte) -1) == 0) {
            return onForgetVpnClick();
        }
        Log.w("AppManagementFragment", "unknown key is clicked: " + key);
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0029  */
    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onPreferenceChange(androidx.preference.Preference r6, java.lang.Object r7) {
        /*
            r5 = this;
            java.lang.String r0 = r6.getKey()
            int r1 = r0.hashCode()
            r2 = -2008102204(0xffffffff884ecac4, float:-6.222922E-34)
            r3 = 0
            r4 = 1
            if (r1 == r2) goto L1f
            r2 = -1808701950(0xffffffff94316602, float:-8.956334E-27)
            if (r1 == r2) goto L15
            goto L29
        L15:
            java.lang.String r1 = "lockdown_vpn"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L29
            r0 = r4
            goto L2a
        L1f:
            java.lang.String r1 = "always_on_vpn"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L29
            r0 = r3
            goto L2a
        L29:
            r0 = -1
        L2a:
            if (r0 == 0) goto L5a
            if (r0 == r4) goto L49
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "unknown key is clicked: "
            r5.append(r7)
            java.lang.String r6 = r6.getKey()
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            java.lang.String r6 = "AppManagementFragment"
            android.util.Log.w(r6, r5)
            return r3
        L49:
            com.android.settingslib.RestrictedSwitchPreference r6 = r5.mPreferenceAlwaysOn
            boolean r6 = r6.isChecked()
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            boolean r5 = r5.onAlwaysOnVpnClick(r6, r7)
            return r5
        L5a:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r6 = r7.booleanValue()
            com.android.settingslib.RestrictedSwitchPreference r7 = r5.mPreferenceLockdown
            boolean r7 = r7.isChecked()
            boolean r5 = r5.onAlwaysOnVpnClick(r6, r7)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.vpn2.AppManagementFragment.onPreferenceChange(androidx.preference.Preference, java.lang.Object):boolean");
    }

    private boolean onForgetVpnClick() {
        updateRestrictedViews();
        if (!this.mPreferenceForget.isEnabled()) {
            return false;
        }
        AppDialogFragment.show(this, this.mForgetVpnDialogFragmentListener, this.mPackageInfo, this.mVpnLabel, true, true);
        return true;
    }

    private boolean onAlwaysOnVpnClick(boolean z, boolean z2) {
        boolean zIsAnotherVpnActive = isAnotherVpnActive();
        boolean zIsAnyLockdownActive = VpnUtils.isAnyLockdownActive(getActivity());
        if (ConfirmLockdownFragment.shouldShow(zIsAnotherVpnActive, zIsAnyLockdownActive, z2)) {
            ConfirmLockdownFragment.show(this, zIsAnotherVpnActive, z, zIsAnyLockdownActive, z2, null);
            return false;
        }
        return setAlwaysOnVpnByUI(z, z2);
    }

    @Override // com.android.car.developeroptions.vpn2.ConfirmLockdownFragment.ConfirmLockdownListener
    public void onConfirmLockdown(Bundle bundle, boolean z, boolean z2) {
        setAlwaysOnVpnByUI(z, z2);
    }

    private boolean setAlwaysOnVpnByUI(boolean z, boolean z2) {
        updateRestrictedViews();
        if (!this.mPreferenceAlwaysOn.isEnabled()) {
            return false;
        }
        if (this.mUserId == 0) {
            VpnUtils.clearLockdownVpn(getContext());
        }
        boolean alwaysOnVpn = setAlwaysOnVpn(z, z2);
        if (z && (!alwaysOnVpn || !isVpnAlwaysOn())) {
            CannotConnectFragment.show(this, this.mVpnLabel);
        } else {
            updateUI();
        }
        return alwaysOnVpn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setAlwaysOnVpn(boolean z, boolean z2) {
        return this.mConnectivityManager.setAlwaysOnVpnPackageForUser(this.mUserId, z ? this.mPackageName : null, z2, null);
    }

    private void updateUI() {
        if (isAdded()) {
            boolean zIsVpnAlwaysOn = isVpnAlwaysOn();
            boolean z = zIsVpnAlwaysOn && VpnUtils.isAnyLockdownActive(getActivity());
            this.mPreferenceAlwaysOn.setChecked(zIsVpnAlwaysOn);
            this.mPreferenceLockdown.setChecked(z);
            updateRestrictedViews();
        }
    }

    private void updateRestrictedViews() {
        if (isAdded()) {
            this.mPreferenceAlwaysOn.checkRestrictionAndSetDisabled("no_config_vpn", this.mUserId);
            this.mPreferenceLockdown.checkRestrictionAndSetDisabled("no_config_vpn", this.mUserId);
            this.mPreferenceForget.checkRestrictionAndSetDisabled("no_config_vpn", this.mUserId);
            if (this.mConnectivityManager.isAlwaysOnVpnPackageSupportedForUser(this.mUserId, this.mPackageName)) {
                this.mPreferenceAlwaysOn.setSummary(R.string.vpn_always_on_summary);
                return;
            }
            this.mPreferenceAlwaysOn.setEnabled(false);
            this.mPreferenceLockdown.setEnabled(false);
            this.mPreferenceAlwaysOn.setSummary(R.string.vpn_always_on_summary_not_supported);
        }
    }

    private String getAlwaysOnVpnPackage() {
        return this.mConnectivityManager.getAlwaysOnVpnPackageForUser(this.mUserId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isVpnAlwaysOn() {
        return this.mPackageName.equals(getAlwaysOnVpnPackage());
    }

    private boolean loadInfo() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            Log.e("AppManagementFragment", "empty bundle");
            return false;
        }
        String string = arguments.getString("package");
        this.mPackageName = string;
        if (string == null) {
            Log.e("AppManagementFragment", "empty package name");
            return false;
        }
        try {
            this.mPackageInfo = this.mPackageManager.getPackageInfo(string, 0);
            this.mVpnLabel = VpnConfig.getVpnLabel(getPrefContext(), this.mPackageName).toString();
            if (this.mPackageInfo.applicationInfo == null) {
                Log.e("AppManagementFragment", "package does not include an application");
                return false;
            }
            if (appHasVpnPermission(getContext(), this.mPackageInfo.applicationInfo)) {
                return true;
            }
            Log.e("AppManagementFragment", "package didn't register VPN profile");
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("AppManagementFragment", "package not found", e);
            return false;
        }
    }

    static boolean appHasVpnPermission(Context context, ApplicationInfo applicationInfo) {
        return !ArrayUtils.isEmpty(((AppOpsManager) context.getSystemService("appops")).getOpsForPackage(applicationInfo.uid, applicationInfo.packageName, new int[]{47}));
    }

    private boolean isAnotherVpnActive() {
        try {
            VpnConfig vpnConfig = this.mConnectivityService.getVpnConfig(this.mUserId);
            if (vpnConfig != null) {
                return !TextUtils.equals(vpnConfig.user, this.mPackageName);
            }
            return false;
        } catch (RemoteException e) {
            Log.w("AppManagementFragment", "Failure to look up active VPN", e);
            return false;
        }
    }

    public static class CannotConnectFragment extends InstrumentedDialogFragment {
        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 547;
        }

        public static void show(AppManagementFragment appManagementFragment, String str) {
            if (appManagementFragment.getFragmentManager().findFragmentByTag("CannotConnect") == null) {
                Bundle bundle = new Bundle();
                bundle.putString("label", str);
                CannotConnectFragment cannotConnectFragment = new CannotConnectFragment();
                cannotConnectFragment.setArguments(bundle);
                cannotConnectFragment.show(appManagementFragment.getFragmentManager(), "CannotConnect");
            }
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            String string = getArguments().getString("label");
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(getActivity().getString(R.string.vpn_cant_connect_title, new Object[]{string}));
            builder.setMessage(getActivity().getString(R.string.vpn_cant_connect_message));
            builder.setPositiveButton(R.string.okay, (DialogInterface.OnClickListener) null);
            return builder.create();
        }
    }
}
