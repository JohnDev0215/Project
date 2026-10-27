package com.android.car.developeroptions.applications.specialaccess.zenaccess;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.applications.AppInfoWithHeader;
import com.android.car.developeroptions.applications.specialaccess.zenaccess.ZenAccessSettingObserverMixin;

/* JADX INFO: loaded from: classes.dex */
public class ZenAccessDetails extends AppInfoWithHeader implements ZenAccessSettingObserverMixin.Listener {
    @Override // com.android.car.developeroptions.applications.AppInfoBase
    protected AlertDialog createDialog(int i, int i2) {
        return null;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1692;
    }

    @Override // com.android.car.developeroptions.applications.AppInfoBase, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.zen_access_permission_details);
        getSettingsLifecycle().addObserver(new ZenAccessSettingObserverMixin(getContext(), this));
    }

    @Override // com.android.car.developeroptions.applications.AppInfoBase
    protected boolean refreshUi() {
        Context context = getContext();
        if (!ZenAccessController.isSupported((ActivityManager) context.getSystemService(ActivityManager.class)) || !ZenAccessController.getPackagesRequestingNotificationPolicyAccess().contains(this.mPackageName)) {
            return false;
        }
        updatePreference(context, (SwitchPreference) findPreference("zen_access_switch"));
        return true;
    }

    public void updatePreference(Context context, SwitchPreference switchPreference) {
        final CharSequence charSequenceLoadLabel = this.mPackageInfo.applicationInfo.loadLabel(this.mPm);
        if (ZenAccessController.getAutoApprovedPackages(context).contains(this.mPackageName)) {
            switchPreference.setEnabled(false);
            switchPreference.setSummary(getString(R.string.zen_access_disabled_package_warning));
        } else {
            switchPreference.setChecked(ZenAccessController.hasAccess(context, this.mPackageName));
            switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.car.developeroptions.applications.specialaccess.zenaccess.-$$Lambda$ZenAccessDetails$T_jWxkn_LpS67YNjrVOTZX5m2-I
                @Override // androidx.preference.Preference.OnPreferenceChangeListener
                public final boolean onPreferenceChange(Preference preference, Object obj) {
                    return this.f$0.lambda$updatePreference$0$ZenAccessDetails(charSequenceLoadLabel, preference, obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updatePreference$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$updatePreference$0$ZenAccessDetails(CharSequence charSequence, Preference preference, Object obj) {
        if (((Boolean) obj).booleanValue()) {
            ScaryWarningDialogFragment scaryWarningDialogFragment = new ScaryWarningDialogFragment();
            scaryWarningDialogFragment.setPkgInfo(this.mPackageName, charSequence);
            scaryWarningDialogFragment.show(getFragmentManager(), "dialog");
            return false;
        }
        FriendlyWarningDialogFragment friendlyWarningDialogFragment = new FriendlyWarningDialogFragment();
        friendlyWarningDialogFragment.setPkgInfo(this.mPackageName, charSequence);
        friendlyWarningDialogFragment.show(getFragmentManager(), "dialog");
        return false;
    }

    @Override // com.android.car.developeroptions.applications.specialaccess.zenaccess.ZenAccessSettingObserverMixin.Listener
    public void onZenAccessPolicyChanged() {
        refreshUi();
    }
}
