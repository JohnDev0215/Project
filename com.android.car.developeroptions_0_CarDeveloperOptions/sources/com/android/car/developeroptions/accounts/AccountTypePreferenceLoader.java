package com.android.car.developeroptions.accounts;

import android.accounts.Account;
import android.accounts.AuthenticatorDescription;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.location.LocationSettings;
import com.android.car.developeroptions.utils.LocalClassLoaderContextThemeWrapper;
import com.android.settingslib.accounts.AuthenticatorHelper;
import com.android.settingslib.core.instrumentation.Instrumentable;

/* JADX INFO: loaded from: classes.dex */
public class AccountTypePreferenceLoader {
    private AuthenticatorHelper mAuthenticatorHelper;
    private PreferenceFragmentCompat mFragment;
    private UserHandle mUserHandle;

    public AccountTypePreferenceLoader(PreferenceFragmentCompat preferenceFragmentCompat, AuthenticatorHelper authenticatorHelper, UserHandle userHandle) {
        this.mFragment = preferenceFragmentCompat;
        this.mAuthenticatorHelper = authenticatorHelper;
        this.mUserHandle = userHandle;
    }

    public PreferenceScreen addPreferencesForType(String str, PreferenceScreen preferenceScreen) {
        AuthenticatorDescription accountTypeDescription;
        if (!this.mAuthenticatorHelper.containsAccountType(str)) {
            return null;
        }
        try {
            accountTypeDescription = this.mAuthenticatorHelper.getAccountTypeDescription(str);
            if (accountTypeDescription == null) {
                return null;
            }
            try {
                if (accountTypeDescription.accountPreferencesId == 0) {
                    return null;
                }
                Context contextCreatePackageContextAsUser = this.mFragment.getActivity().createPackageContextAsUser(accountTypeDescription.packageName, 0, this.mUserHandle);
                Resources.Theme themeNewTheme = this.mFragment.getResources().newTheme();
                themeNewTheme.applyStyle(R.style.Theme_SettingsBase, true);
                LocalClassLoaderContextThemeWrapper localClassLoaderContextThemeWrapper = new LocalClassLoaderContextThemeWrapper(getClass(), contextCreatePackageContextAsUser, 0);
                localClassLoaderContextThemeWrapper.getTheme().setTo(themeNewTheme);
                return this.mFragment.getPreferenceManager().inflateFromResource(localClassLoaderContextThemeWrapper, accountTypeDescription.accountPreferencesId, preferenceScreen);
            } catch (PackageManager.NameNotFoundException unused) {
                Log.w("AccountTypePrefLoader", "Couldn't load preferences.xml file from " + accountTypeDescription.packageName);
                return null;
            } catch (Resources.NotFoundException unused2) {
                Log.w("AccountTypePrefLoader", "Couldn't load preferences.xml file from " + accountTypeDescription.packageName);
                return null;
            }
        } catch (PackageManager.NameNotFoundException unused3) {
            accountTypeDescription = null;
        } catch (Resources.NotFoundException unused4) {
            accountTypeDescription = null;
        }
    }

    public void updatePreferenceIntents(PreferenceGroup preferenceGroup, final String str, Account account) {
        final PackageManager packageManager = this.mFragment.getActivity().getPackageManager();
        int i = 0;
        while (i < preferenceGroup.getPreferenceCount()) {
            Preference preference = preferenceGroup.getPreference(i);
            if (preference instanceof PreferenceGroup) {
                updatePreferenceIntents((PreferenceGroup) preference, str, account);
            }
            Intent intent = preference.getIntent();
            if (intent != null) {
                if (TextUtils.equals(intent.getAction(), "android.settings.LOCATION_SOURCE_SETTINGS")) {
                    preference.setOnPreferenceClickListener(new FragmentStarter(LocationSettings.class.getName(), R.string.location_settings_title));
                } else if (packageManager.resolveActivityAsUser(intent, 65536, this.mUserHandle.getIdentifier()) == null) {
                    preferenceGroup.removePreference(preference);
                } else {
                    intent.putExtra("account", account);
                    intent.setFlags(intent.getFlags() | 268435456);
                    preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.accounts.AccountTypePreferenceLoader.1
                        @Override // androidx.preference.Preference.OnPreferenceClickListener
                        public boolean onPreferenceClick(Preference preference2) {
                            Intent intent2 = preference2.getIntent();
                            if (AccountTypePreferenceLoader.this.isSafeIntent(packageManager, intent2, str)) {
                                AccountTypePreferenceLoader.this.mFragment.getActivity().startActivityAsUser(intent2, AccountTypePreferenceLoader.this.mUserHandle);
                                return true;
                            }
                            Log.e("AccountTypePrefLoader", "Refusing to launch authenticator intent becauseit exploits Settings permissions: " + intent2);
                            return true;
                        }
                    });
                }
            }
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSafeIntent(PackageManager packageManager, Intent intent, String str) {
        AuthenticatorDescription accountTypeDescription = this.mAuthenticatorHelper.getAccountTypeDescription(str);
        ResolveInfo resolveInfoResolveActivityAsUser = packageManager.resolveActivityAsUser(intent, 0, this.mUserHandle.getIdentifier());
        if (resolveInfoResolveActivityAsUser == null) {
            return false;
        }
        ActivityInfo activityInfo = resolveInfoResolveActivityAsUser.activityInfo;
        ApplicationInfo applicationInfo = activityInfo.applicationInfo;
        try {
            if (activityInfo.exported && (activityInfo.permission == null || packageManager.checkPermission(activityInfo.permission, accountTypeDescription.packageName) == 0)) {
                return true;
            }
            return applicationInfo.uid == packageManager.getApplicationInfo(accountTypeDescription.packageName, 0).uid;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("AccountTypePrefLoader", "Intent considered unsafe due to exception.", e);
            return false;
        }
    }

    private class FragmentStarter implements Preference.OnPreferenceClickListener {
        private final String mClass;
        private final int mTitleRes;

        public FragmentStarter(String str, int i) {
            this.mClass = str;
            this.mTitleRes = i;
        }

        @Override // androidx.preference.Preference.OnPreferenceClickListener
        public boolean onPreferenceClick(Preference preference) {
            int metricsCategory = AccountTypePreferenceLoader.this.mFragment instanceof Instrumentable ? ((Instrumentable) AccountTypePreferenceLoader.this.mFragment).getMetricsCategory() : 0;
            SubSettingLauncher subSettingLauncher = new SubSettingLauncher(preference.getContext());
            subSettingLauncher.setTitleRes(this.mTitleRes);
            subSettingLauncher.setDestination(this.mClass);
            subSettingLauncher.setSourceMetricsCategory(metricsCategory);
            subSettingLauncher.launch();
            if (!this.mClass.equals(LocationSettings.class.getName())) {
                return true;
            }
            AccountTypePreferenceLoader.this.mFragment.getActivity().sendBroadcast(new Intent("com.android.car.developeroptions.accounts.LAUNCHING_LOCATION_SETTINGS"), "android.permission.WRITE_SECURE_SETTINGS");
            return true;
        }
    }
}
