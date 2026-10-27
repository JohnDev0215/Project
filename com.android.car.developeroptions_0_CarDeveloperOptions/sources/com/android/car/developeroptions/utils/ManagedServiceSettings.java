package com.android.car.developeroptions.utils;

import android.app.Dialog;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.IconDrawableFactory;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.android.car.developeroptions.utils.ManagedServiceSettings;
import com.android.car.developeroptions.widget.AppSwitchPreference;
import com.android.car.developeroptions.widget.EmptyTextSettings;
import com.android.settingslib.applications.ServiceListing;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class ManagedServiceSettings extends EmptyTextSettings {
    private final Config mConfig = getConfig();
    protected Context mContext;
    private DevicePolicyManager mDpm;
    private IconDrawableFactory mIconDrawableFactory;
    private PackageManager mPm;
    private ServiceListing mServiceListing;

    protected abstract Config getConfig();

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        this.mContext = activity;
        this.mPm = activity.getPackageManager();
        this.mDpm = (DevicePolicyManager) this.mContext.getSystemService("device_policy");
        this.mIconDrawableFactory = IconDrawableFactory.newInstance(this.mContext);
        ServiceListing.Builder builder = new ServiceListing.Builder(this.mContext);
        builder.setPermission(this.mConfig.permission);
        builder.setIntentAction(this.mConfig.intentAction);
        builder.setNoun(this.mConfig.noun);
        builder.setSetting(this.mConfig.setting);
        builder.setTag(this.mConfig.tag);
        ServiceListing serviceListingBuild = builder.build();
        this.mServiceListing = serviceListingBuild;
        serviceListingBuild.addCallback(new ServiceListing.Callback() { // from class: com.android.car.developeroptions.utils.-$$Lambda$ManagedServiceSettings$WMnlizfnIYA8ltUfbUZijJaG0DA
            @Override // com.android.settingslib.applications.ServiceListing.Callback
            public final void onServicesReloaded(List list) {
                this.f$0.updateList(list);
            }
        });
        setPreferenceScreen(getPreferenceManager().createPreferenceScreen(this.mContext));
    }

    @Override // com.android.car.developeroptions.widget.EmptyTextSettings, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setEmptyText(this.mConfig.emptyText);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mServiceListing.reload();
        this.mServiceListing.setListening(true);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mServiceListing.setListening(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateList(List<ServiceInfo> list) {
        int managedProfileId = Utils.getManagedProfileId((UserManager) this.mContext.getSystemService("user"), UserHandle.myUserId());
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        preferenceScreen.removeAll();
        list.sort(new PackageItemInfo.DisplayNameComparator(this.mPm));
        for (ServiceInfo serviceInfo : list) {
            final ComponentName componentName = new ComponentName(serviceInfo.packageName, serviceInfo.name);
            final CharSequence charSequenceLoadLabel = null;
            try {
                charSequenceLoadLabel = this.mPm.getApplicationInfoAsUser(serviceInfo.packageName, 0, UserHandle.myUserId()).loadLabel(this.mPm);
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("ManagedServiceSettings", "can't find package name", e);
            }
            String string = serviceInfo.loadLabel(this.mPm).toString();
            AppSwitchPreference appSwitchPreference = new AppSwitchPreference(getPrefContext());
            appSwitchPreference.setPersistent(false);
            IconDrawableFactory iconDrawableFactory = this.mIconDrawableFactory;
            ApplicationInfo applicationInfo = serviceInfo.applicationInfo;
            appSwitchPreference.setIcon(iconDrawableFactory.getBadgedIcon(serviceInfo, applicationInfo, UserHandle.getUserId(applicationInfo.uid)));
            if (charSequenceLoadLabel != null && !charSequenceLoadLabel.equals(string)) {
                appSwitchPreference.setTitle(charSequenceLoadLabel);
                appSwitchPreference.setSummary(string);
            } else {
                appSwitchPreference.setTitle(string);
            }
            appSwitchPreference.setKey(componentName.flattenToString());
            appSwitchPreference.setChecked(isServiceEnabled(componentName));
            if (managedProfileId != -10000 && !this.mDpm.isNotificationListenerServicePermitted(serviceInfo.packageName, managedProfileId)) {
                appSwitchPreference.setSummary(R.string.work_profile_notification_access_blocked_summary);
            }
            appSwitchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.car.developeroptions.utils.-$$Lambda$ManagedServiceSettings$qqO1510yI-oMNT6inM_2f_xxIFc
                @Override // androidx.preference.Preference.OnPreferenceChangeListener
                public final boolean onPreferenceChange(Preference preference, Object obj) {
                    return this.f$0.lambda$updateList$0$ManagedServiceSettings(charSequenceLoadLabel, componentName, preference, obj);
                }
            });
            appSwitchPreference.setKey(componentName.flattenToString());
            preferenceScreen.addPreference(appSwitchPreference);
        }
        highlightPreferenceIfNeeded();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateList$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$updateList$0$ManagedServiceSettings(CharSequence charSequence, ComponentName componentName, Preference preference, Object obj) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        if (charSequence != null) {
            return setEnabled(componentName, charSequence.toString(), zBooleanValue);
        }
        return setEnabled(componentName, null, zBooleanValue);
    }

    protected boolean isServiceEnabled(ComponentName componentName) {
        return this.mServiceListing.isEnabled(componentName);
    }

    protected boolean setEnabled(ComponentName componentName, String str, boolean z) {
        if (!z) {
            this.mServiceListing.setEnabled(componentName, false);
            return true;
        }
        if (this.mServiceListing.isEnabled(componentName)) {
            return true;
        }
        ScaryWarningDialogFragment scaryWarningDialogFragment = new ScaryWarningDialogFragment();
        scaryWarningDialogFragment.setServiceInfo(componentName, str, this);
        scaryWarningDialogFragment.show(getFragmentManager(), "dialog");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void enable(ComponentName componentName) {
        this.mServiceListing.setEnabled(componentName, true);
    }

    public static class ScaryWarningDialogFragment extends InstrumentedDialogFragment {
        static /* synthetic */ void lambda$onCreateDialog$1(DialogInterface dialogInterface, int i) {
        }

        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 557;
        }

        public ScaryWarningDialogFragment setServiceInfo(ComponentName componentName, String str, Fragment fragment) {
            Bundle bundle = new Bundle();
            bundle.putString("c", componentName.flattenToString());
            bundle.putString("l", str);
            setArguments(bundle);
            setTargetFragment(fragment, 0);
            return this;
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            Bundle arguments = getArguments();
            String string = arguments.getString("l");
            final ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(arguments.getString("c"));
            final ManagedServiceSettings managedServiceSettings = (ManagedServiceSettings) getTargetFragment();
            String string2 = getResources().getString(managedServiceSettings.mConfig.warningDialogTitle, string);
            String string3 = getResources().getString(managedServiceSettings.mConfig.warningDialogSummary, string);
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(string3);
            builder.setTitle(string2);
            builder.setCancelable(true);
            builder.setPositiveButton(R.string.allow, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.utils.-$$Lambda$ManagedServiceSettings$ScaryWarningDialogFragment$CkTLl1nxvq7yRVxOFphgtpEvRcU
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    managedServiceSettings.enable(componentNameUnflattenFromString);
                }
            });
            builder.setNegativeButton(R.string.deny, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.utils.-$$Lambda$ManagedServiceSettings$ScaryWarningDialogFragment$ZwC_e09jp380Fw4twLQqL-RNjbE
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    ManagedServiceSettings.ScaryWarningDialogFragment.lambda$onCreateDialog$1(dialogInterface, i);
                }
            });
            return builder.create();
        }
    }

    public static class Config {
        public final String configIntentAction;
        public final int emptyText;
        public final String intentAction;
        public final String noun;
        public final String permission;
        public final String setting;
        public final String tag;
        public final int warningDialogSummary;
        public final int warningDialogTitle;

        private Config(String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, int i3) {
            this.tag = str;
            this.setting = str2;
            this.intentAction = str3;
            this.permission = str5;
            this.noun = str6;
            this.warningDialogTitle = i;
            this.warningDialogSummary = i2;
            this.emptyText = i3;
            this.configIntentAction = str4;
        }

        public static class Builder {
            private String mConfigIntentAction;
            private int mEmptyText;
            private String mIntentAction;
            private String mNoun;
            private String mPermission;
            private String mSetting;
            private String mTag;
            private int mWarningDialogSummary;
            private int mWarningDialogTitle;

            public Builder setTag(String str) {
                this.mTag = str;
                return this;
            }

            public Builder setSetting(String str) {
                this.mSetting = str;
                return this;
            }

            public Builder setIntentAction(String str) {
                this.mIntentAction = str;
                return this;
            }

            public Builder setConfigurationIntentAction(String str) {
                this.mConfigIntentAction = str;
                return this;
            }

            public Builder setPermission(String str) {
                this.mPermission = str;
                return this;
            }

            public Builder setNoun(String str) {
                this.mNoun = str;
                return this;
            }

            public Builder setWarningDialogTitle(int i) {
                this.mWarningDialogTitle = i;
                return this;
            }

            public Builder setWarningDialogSummary(int i) {
                this.mWarningDialogSummary = i;
                return this;
            }

            public Builder setEmptyText(int i) {
                this.mEmptyText = i;
                return this;
            }

            public Config build() {
                return new Config(this.mTag, this.mSetting, this.mIntentAction, this.mConfigIntentAction, this.mPermission, this.mNoun, this.mWarningDialogTitle, this.mWarningDialogSummary, this.mEmptyText);
            }
        }
    }
}
