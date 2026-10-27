package com.android.car.developeroptions.applications;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.content.pm.IPackageManager;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.UserInfo;
import android.location.LocationManager;
import android.os.RemoteException;
import android.os.UserManager;
import android.telecom.DefaultDialerManager;
import android.text.TextUtils;
import android.util.ArraySet;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.applications.ApplicationFeatureProvider;
import com.android.internal.telephony.SmsApplication;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ApplicationFeatureProviderImpl implements ApplicationFeatureProvider {
    private final Context mContext;
    private final DevicePolicyManager mDpm;
    private final PackageManager mPm;
    private final IPackageManager mPms;
    private final UserManager mUm;

    public ApplicationFeatureProviderImpl(Context context, PackageManager packageManager, IPackageManager iPackageManager, DevicePolicyManager devicePolicyManager) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mPm = packageManager;
        this.mPms = iPackageManager;
        this.mDpm = devicePolicyManager;
        this.mUm = UserManager.get(applicationContext);
    }

    @Override // com.android.car.developeroptions.applications.ApplicationFeatureProvider
    public void calculateNumberOfPolicyInstalledApps(boolean z, ApplicationFeatureProvider.NumberOfAppsCallback numberOfAppsCallback) {
        CurrentUserAndManagedProfilePolicyInstalledAppCounter currentUserAndManagedProfilePolicyInstalledAppCounter = new CurrentUserAndManagedProfilePolicyInstalledAppCounter(this.mContext, this.mPm, numberOfAppsCallback);
        if (z) {
            currentUserAndManagedProfilePolicyInstalledAppCounter.execute(new Void[0]);
        } else {
            currentUserAndManagedProfilePolicyInstalledAppCounter.executeInForeground();
        }
    }

    @Override // com.android.car.developeroptions.applications.ApplicationFeatureProvider
    public void listPolicyInstalledApps(ApplicationFeatureProvider.ListOfAppsCallback listOfAppsCallback) {
        new CurrentUserPolicyInstalledAppLister(this.mPm, this.mUm, listOfAppsCallback).execute(new Void[0]);
    }

    @Override // com.android.car.developeroptions.applications.ApplicationFeatureProvider
    public void calculateNumberOfAppsWithAdminGrantedPermissions(String[] strArr, boolean z, ApplicationFeatureProvider.NumberOfAppsCallback numberOfAppsCallback) {
        CurrentUserAndManagedProfileAppWithAdminGrantedPermissionsCounter currentUserAndManagedProfileAppWithAdminGrantedPermissionsCounter = new CurrentUserAndManagedProfileAppWithAdminGrantedPermissionsCounter(this.mContext, strArr, this.mPm, this.mPms, this.mDpm, numberOfAppsCallback);
        if (z) {
            currentUserAndManagedProfileAppWithAdminGrantedPermissionsCounter.execute(new Void[0]);
        } else {
            currentUserAndManagedProfileAppWithAdminGrantedPermissionsCounter.executeInForeground();
        }
    }

    @Override // com.android.car.developeroptions.applications.ApplicationFeatureProvider
    public void listAppsWithAdminGrantedPermissions(String[] strArr, ApplicationFeatureProvider.ListOfAppsCallback listOfAppsCallback) {
        new CurrentUserAppWithAdminGrantedPermissionsLister(strArr, this.mPm, this.mPms, this.mDpm, this.mUm, listOfAppsCallback).execute(new Void[0]);
    }

    @Override // com.android.car.developeroptions.applications.ApplicationFeatureProvider
    public List<UserAppInfo> findPersistentPreferredActivities(int i, Intent[] intentArr) {
        ArrayList arrayList = new ArrayList();
        ArraySet arraySet = new ArraySet();
        UserInfo userInfo = this.mUm.getUserInfo(i);
        for (Intent intent : intentArr) {
            try {
                ResolveInfo resolveInfoFindPersistentPreferredActivity = this.mPms.findPersistentPreferredActivity(intent, i);
                if (resolveInfoFindPersistentPreferredActivity != null) {
                    ComponentInfo componentInfo = null;
                    if (resolveInfoFindPersistentPreferredActivity.activityInfo != null) {
                        componentInfo = resolveInfoFindPersistentPreferredActivity.activityInfo;
                    } else if (resolveInfoFindPersistentPreferredActivity.serviceInfo != null) {
                        componentInfo = resolveInfoFindPersistentPreferredActivity.serviceInfo;
                    } else if (resolveInfoFindPersistentPreferredActivity.providerInfo != null) {
                        componentInfo = resolveInfoFindPersistentPreferredActivity.providerInfo;
                    }
                    if (componentInfo != null) {
                        UserAppInfo userAppInfo = new UserAppInfo(userInfo, componentInfo.applicationInfo);
                        if (arraySet.add(userAppInfo)) {
                            arrayList.add(userAppInfo);
                        }
                    }
                }
            } catch (RemoteException unused) {
            }
        }
        return arrayList;
    }

    @Override // com.android.car.developeroptions.applications.ApplicationFeatureProvider
    public Set<String> getKeepEnabledPackages() {
        ArraySet arraySet = new ArraySet();
        String defaultDialerApplication = DefaultDialerManager.getDefaultDialerApplication(this.mContext);
        if (!TextUtils.isEmpty(defaultDialerApplication)) {
            arraySet.add(defaultDialerApplication);
        }
        ComponentName defaultSmsApplication = SmsApplication.getDefaultSmsApplication(this.mContext, true);
        if (defaultSmsApplication != null) {
            arraySet.add(defaultSmsApplication.getPackageName());
        }
        arraySet.addAll(getEnabledPackageWhitelist());
        String extraLocationControllerPackage = ((LocationManager) this.mContext.getSystemService("location")).getExtraLocationControllerPackage();
        if (extraLocationControllerPackage != null) {
            arraySet.add(extraLocationControllerPackage);
        }
        return arraySet;
    }

    private Set<String> getEnabledPackageWhitelist() {
        ArraySet arraySet = new ArraySet();
        arraySet.add(this.mContext.getString(R.string.config_settingsintelligence_package_name));
        arraySet.add(this.mContext.getString(R.string.config_package_installer_package_name));
        return arraySet;
    }

    private static class CurrentUserAndManagedProfilePolicyInstalledAppCounter extends InstalledAppCounter {
        private ApplicationFeatureProvider.NumberOfAppsCallback mCallback;

        CurrentUserAndManagedProfilePolicyInstalledAppCounter(Context context, PackageManager packageManager, ApplicationFeatureProvider.NumberOfAppsCallback numberOfAppsCallback) {
            super(context, 1, packageManager);
            this.mCallback = numberOfAppsCallback;
        }

        @Override // com.android.car.developeroptions.applications.AppCounter
        protected void onCountComplete(int i) {
            this.mCallback.onNumberOfAppsResult(i);
        }
    }

    private static class CurrentUserAndManagedProfileAppWithAdminGrantedPermissionsCounter extends AppWithAdminGrantedPermissionsCounter {
        private ApplicationFeatureProvider.NumberOfAppsCallback mCallback;

        CurrentUserAndManagedProfileAppWithAdminGrantedPermissionsCounter(Context context, String[] strArr, PackageManager packageManager, IPackageManager iPackageManager, DevicePolicyManager devicePolicyManager, ApplicationFeatureProvider.NumberOfAppsCallback numberOfAppsCallback) {
            super(context, strArr, packageManager, iPackageManager, devicePolicyManager);
            this.mCallback = numberOfAppsCallback;
        }

        @Override // com.android.car.developeroptions.applications.AppCounter
        protected void onCountComplete(int i) {
            this.mCallback.onNumberOfAppsResult(i);
        }
    }

    private static class CurrentUserPolicyInstalledAppLister extends InstalledAppLister {
        private ApplicationFeatureProvider.ListOfAppsCallback mCallback;

        CurrentUserPolicyInstalledAppLister(PackageManager packageManager, UserManager userManager, ApplicationFeatureProvider.ListOfAppsCallback listOfAppsCallback) {
            super(packageManager, userManager);
            this.mCallback = listOfAppsCallback;
        }

        @Override // com.android.car.developeroptions.applications.AppLister
        protected void onAppListBuilt(List<UserAppInfo> list) {
            this.mCallback.onListOfAppsResult(list);
        }
    }

    private static class CurrentUserAppWithAdminGrantedPermissionsLister extends AppWithAdminGrantedPermissionsLister {
        private ApplicationFeatureProvider.ListOfAppsCallback mCallback;

        CurrentUserAppWithAdminGrantedPermissionsLister(String[] strArr, PackageManager packageManager, IPackageManager iPackageManager, DevicePolicyManager devicePolicyManager, UserManager userManager, ApplicationFeatureProvider.ListOfAppsCallback listOfAppsCallback) {
            super(strArr, packageManager, iPackageManager, devicePolicyManager, userManager);
            this.mCallback = listOfAppsCallback;
        }

        @Override // com.android.car.developeroptions.applications.AppLister
        protected void onAppListBuilt(List<UserAppInfo> list) {
            this.mCallback.onListOfAppsResult(list);
        }
    }
}
