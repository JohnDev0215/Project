package android.content.pm;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.IDexModuleRegisterCallback;
import android.content.pm.IPackageDataObserver;
import android.content.pm.IPackageDeleteObserver;
import android.content.pm.IPackageDeleteObserver2;
import android.content.pm.IPackageInstaller;
import android.content.pm.IPackageManager;
import android.content.pm.IPackageMoveObserver;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.dex.IArtManager;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/* JADX INFO: loaded from: classes.dex */
public interface IPackageManager extends IInterface {

    public static class Default implements IPackageManager {
        @Override // android.content.pm.IPackageManager
        public boolean activitySupportsIntent(ComponentName componentName, Intent intent, String str) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public void addCrossProfileIntentFilter(IntentFilter intentFilter, String str, int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public boolean addPermission(PermissionInfo permissionInfo) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public boolean addPermissionAsync(PermissionInfo permissionInfo) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public void addPersistentPreferredActivity(IntentFilter intentFilter, ComponentName componentName, int i) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void addPreferredActivity(IntentFilter intentFilter, int i, ComponentName[] componentNameArr, ComponentName componentName, int i2) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public boolean canForwardTo(Intent intent, String str, int i, int i2) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public boolean canRequestPackageInstalls(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public String[] canonicalToCurrentPackageNames(String[] strArr) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public void checkPackageStartable(String str, int i) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public int checkPermission(String str, String str2, int i) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IPackageManager
        public int checkSignatures(String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IPackageManager
        public int checkUidPermission(String str, int i) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IPackageManager
        public int checkUidSignatures(int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IPackageManager
        public void clearApplicationProfileData(String str) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void clearApplicationUserData(String str, IPackageDataObserver iPackageDataObserver, int i) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void clearCrossProfileIntentFilters(int i, String str) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void clearPackagePersistentPreferredActivities(String str, int i) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void clearPackagePreferredActivities(String str) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public boolean compileLayouts(String str) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public String[] currentToCanonicalPackageNames(String[] strArr) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public void deleteApplicationCacheFiles(String str, IPackageDataObserver iPackageDataObserver) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void deleteApplicationCacheFilesAsUser(String str, int i, IPackageDataObserver iPackageDataObserver) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void deleteExistingPackageAsUser(VersionedPackage versionedPackage, IPackageDeleteObserver2 iPackageDeleteObserver2, int i) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void deletePackageAsUser(String str, int i, IPackageDeleteObserver iPackageDeleteObserver, int i2, int i3) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void deletePackageVersioned(VersionedPackage versionedPackage, IPackageDeleteObserver2 iPackageDeleteObserver2, int i, int i2) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void deletePreloadsFileCache() throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void dumpProfiles(String str) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void enterSafeMode() throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void extendVerificationTimeout(int i, int i2, long j) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public ResolveInfo findPersistentPreferredActivity(Intent intent, int i) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public void finishPackageInstall(int i, boolean z) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void flushPackageRestrictionsAsUser(int i) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void forceDexOpt(String str) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void freeStorage(String str, long j, int i, IntentSender intentSender) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void freeStorageAndNotify(String str, long j, int i, IPackageDataObserver iPackageDataObserver) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public ActivityInfo getActivityInfo(ComponentName componentName, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ParceledListSlice getAllIntentFilters(String str) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public List<String> getAllPackages() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public String[] getAppOpPermissionPackages(String str) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public String getAppPredictionServicePackageName() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public int getApplicationEnabledSetting(String str, int i) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IPackageManager
        public boolean getApplicationHiddenSettingAsUser(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public ApplicationInfo getApplicationInfo(String str, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public IArtManager getArtManager() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public String getAttentionServicePackageName() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public boolean getBlockUninstallForUser(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public ChangedPackages getChangedPackages(int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public int getComponentEnabledSetting(ComponentName componentName, int i) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IPackageManager
        public String getContentCaptureServicePackageName() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ParceledListSlice getDeclaredSharedLibraries(String str, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public byte[] getDefaultAppsBackup(int i) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public String getDefaultTextClassifierPackageName() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public int getFlagsForUid(int i) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IPackageManager
        public CharSequence getHarmfulAppWarning(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ComponentName getHomeActivities(List<ResolveInfo> list) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public String getIncidentReportApproverPackageName() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public int getInstallLocation() throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IPackageManager
        public int getInstallReason(String str, int i) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IPackageManager
        public InstallSourceInfo getInstallSourceInfo(String str) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ParceledListSlice getInstalledApplications(int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public List<ModuleInfo> getInstalledModules(int i) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ParceledListSlice getInstalledPackages(int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public String getInstallerPackageName(String str) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public String getInstantAppAndroidId(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public byte[] getInstantAppCookie(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public Bitmap getInstantAppIcon(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ComponentName getInstantAppInstallerComponent() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ComponentName getInstantAppResolverComponent() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ComponentName getInstantAppResolverSettingsComponent() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ParceledListSlice getInstantApps(int i) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public InstrumentationInfo getInstrumentationInfo(ComponentName componentName, int i) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public byte[] getIntentFilterVerificationBackup(int i) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ParceledListSlice getIntentFilterVerifications(String str) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public int getIntentVerificationStatus(String str, int i) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IPackageManager
        public KeySet getKeySetByAlias(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ResolveInfo getLastChosenActivity(Intent intent, String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public List<String> getMimeGroup(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ModuleInfo getModuleInfo(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public int getMoveStatus(int i) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IPackageManager
        public String getNameForUid(int i) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public String[] getNamesForUids(int[] iArr) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public int[] getPackageGids(String str, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public PackageInfo getPackageInfo(String str, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public PackageInfo getPackageInfoVersioned(VersionedPackage versionedPackage, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public IPackageInstaller getPackageInstaller() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public void getPackageSizeInfo(String str, int i, IPackageStatsObserver iPackageStatsObserver) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public int getPackageUid(String str, int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IPackageManager
        public String[] getPackagesForUid(int i) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ParceledListSlice getPackagesHoldingPermissions(String[] strArr, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public String getPermissionControllerPackageName() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public PermissionGroupInfo getPermissionGroupInfo(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ParceledListSlice getPersistentApplications(int i) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public int getPreferredActivities(List<IntentFilter> list, List<ComponentName> list2, String str) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IPackageManager
        public byte[] getPreferredActivityBackup(int i) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public int getPrivateFlagsForUid(int i) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IPackageManager
        public ProviderInfo getProviderInfo(ComponentName componentName, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ActivityInfo getReceiverInfo(ComponentName componentName, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public int getRuntimePermissionsVersion(int i) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IPackageManager
        public ServiceInfo getServiceInfo(ComponentName componentName, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public String getServicesSystemSharedLibraryPackageName() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public String getSetupWizardPackageName() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ParceledListSlice getSharedLibraries(String str, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public String getSharedSystemSharedLibraryPackageName() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public KeySet getSigningKeySet(String str) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public Bundle getSuspendedPackageAppExtras(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ParceledListSlice getSystemAvailableFeatures() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public String getSystemCaptionsServicePackageName() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public String[] getSystemSharedLibraryNames() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public String getSystemTextClassifierPackageName() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public int getUidForSharedUser(String str) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IPackageManager
        public String[] getUnsuspendablePackagesForUser(String[] strArr, int i) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public VerifierDeviceIdentity getVerifierDeviceIdentity() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public String getWellbeingPackageName() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public void grantImplicitAccess(int i, String str) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void grantRuntimePermission(String str, String str2, int i) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public boolean hasSigningCertificate(String str, byte[] bArr, int i) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public boolean hasSystemFeature(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public boolean hasSystemUidErrors() throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public boolean hasUidSigningCertificate(int i, byte[] bArr, int i2) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public int installExistingPackageAsUser(String str, int i, int i2, int i3, List<String> list) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IPackageManager
        public boolean isAutoRevokeWhitelisted(String str) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public boolean isDeviceUpgrading() throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public boolean isFirstBoot() throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public boolean isInstantApp(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public boolean isOnlyCoreApps() throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public boolean isPackageAvailable(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public boolean isPackageDeviceAdminOnAnyUser(String str) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public boolean isPackageSignedByKeySet(String str, KeySet keySet) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public boolean isPackageSignedByKeySetExactly(String str, KeySet keySet) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public boolean isPackageStateProtected(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public boolean isPackageSuspendedForUser(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public boolean isProtectedBroadcast(String str) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public boolean isSafeMode() throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public boolean isStorageLow() throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public boolean isUidPrivileged(int i) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public void logAppProcessStartIfNeeded(String str, int i, String str2, String str3, int i2) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public int movePackage(String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IPackageManager
        public int movePrimaryStorage(String str) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IPackageManager
        public void notifyDexLoad(String str, Map<String, String> map, String str2) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void notifyPackageUse(String str, int i) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void notifyPackagesReplacedReceived(String[] strArr) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void overrideLabelAndIcon(ComponentName componentName, String str, int i, int i2) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public boolean performDexOptMode(String str, boolean z, String str2, boolean z2, boolean z3, String str3) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public boolean performDexOptSecondary(String str, String str2, boolean z) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public void performFstrimIfNeeded() throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public ParceledListSlice queryContentProviders(String str, int i, int i2, String str2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ParceledListSlice queryInstrumentation(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ParceledListSlice queryIntentActivities(Intent intent, String str, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ParceledListSlice queryIntentActivityOptions(ComponentName componentName, Intent[] intentArr, String[] strArr, Intent intent, String str, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ParceledListSlice queryIntentContentProviders(Intent intent, String str, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ParceledListSlice queryIntentReceivers(Intent intent, String str, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ParceledListSlice queryIntentServices(Intent intent, String str, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public void querySyncProviders(List<String> list, List<ProviderInfo> list2) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void reconcileSecondaryDexFiles(String str) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void registerDexModule(String str, String str2, boolean z, IDexModuleRegisterCallback iDexModuleRegisterCallback) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void registerMoveCallback(IPackageMoveObserver iPackageMoveObserver) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void removePermission(String str) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void replacePreferredActivity(IntentFilter intentFilter, int i, ComponentName[] componentNameArr, ComponentName componentName, int i2) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void resetApplicationPreferences(int i) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public ProviderInfo resolveContentProvider(String str, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ResolveInfo resolveIntent(Intent intent, String str, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public ResolveInfo resolveService(Intent intent, String str, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public void restoreDefaultApps(byte[] bArr, int i) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void restoreIntentFilterVerification(byte[] bArr, int i) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void restoreLabelAndIcon(ComponentName componentName, int i) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void restorePreferredActivities(byte[] bArr, int i) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public boolean runBackgroundDexoptJob(List<String> list) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public void sendDeviceCustomizationReadyBroadcast() throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void setApplicationCategoryHint(String str, int i, String str2) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void setApplicationEnabledSetting(String str, int i, int i2, int i3, String str2) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public boolean setApplicationHiddenSettingAsUser(String str, boolean z, int i) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public boolean setBlockUninstallForUser(String str, boolean z, int i) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public void setComponentEnabledSetting(ComponentName componentName, int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public String[] setDistractingPackageRestrictionsAsUser(String[] strArr, int i, int i2) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public void setHarmfulAppWarning(String str, CharSequence charSequence, int i) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void setHomeActivity(ComponentName componentName, int i) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public boolean setInstallLocation(int i) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public void setInstallerPackageName(String str, String str2) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public boolean setInstantAppCookie(String str, byte[] bArr, int i) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public void setLastChosenActivity(Intent intent, String str, int i, IntentFilter intentFilter, int i2, ComponentName componentName) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void setMimeGroup(String str, String str2, List<String> list) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void setPackageStoppedState(String str, boolean z, int i) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public String[] setPackagesSuspendedAsUser(String[] strArr, boolean z, PersistableBundle persistableBundle, PersistableBundle persistableBundle2, SuspendDialogInfo suspendDialogInfo, String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManager
        public boolean setRequiredForSystemUser(String str, boolean z) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public void setRuntimePermissionsVersion(int i, int i2) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void setSystemAppHiddenUntilInstalled(String str, boolean z) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public boolean setSystemAppInstallState(String str, boolean z, int i) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public void setUpdateAvailable(String str, boolean z) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void systemReady() throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void unregisterMoveCallback(IPackageMoveObserver iPackageMoveObserver) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public boolean updateIntentVerificationStatus(String str, int i, int i2) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.IPackageManager
        public void updatePackagesIfNeeded() throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void verifyIntentFilter(int i, int i2, List<String> list) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManager
        public void verifyPendingInstall(int i, int i2) throws RemoteException {
        }
    }

    boolean activitySupportsIntent(ComponentName componentName, Intent intent, String str) throws RemoteException;

    void addCrossProfileIntentFilter(IntentFilter intentFilter, String str, int i, int i2, int i3) throws RemoteException;

    boolean addPermission(PermissionInfo permissionInfo) throws RemoteException;

    boolean addPermissionAsync(PermissionInfo permissionInfo) throws RemoteException;

    void addPersistentPreferredActivity(IntentFilter intentFilter, ComponentName componentName, int i) throws RemoteException;

    void addPreferredActivity(IntentFilter intentFilter, int i, ComponentName[] componentNameArr, ComponentName componentName, int i2) throws RemoteException;

    boolean canForwardTo(Intent intent, String str, int i, int i2) throws RemoteException;

    boolean canRequestPackageInstalls(String str, int i) throws RemoteException;

    String[] canonicalToCurrentPackageNames(String[] strArr) throws RemoteException;

    void checkPackageStartable(String str, int i) throws RemoteException;

    int checkPermission(String str, String str2, int i) throws RemoteException;

    int checkSignatures(String str, String str2) throws RemoteException;

    int checkUidPermission(String str, int i) throws RemoteException;

    int checkUidSignatures(int i, int i2) throws RemoteException;

    void clearApplicationProfileData(String str) throws RemoteException;

    void clearApplicationUserData(String str, IPackageDataObserver iPackageDataObserver, int i) throws RemoteException;

    void clearCrossProfileIntentFilters(int i, String str) throws RemoteException;

    void clearPackagePersistentPreferredActivities(String str, int i) throws RemoteException;

    void clearPackagePreferredActivities(String str) throws RemoteException;

    boolean compileLayouts(String str) throws RemoteException;

    String[] currentToCanonicalPackageNames(String[] strArr) throws RemoteException;

    void deleteApplicationCacheFiles(String str, IPackageDataObserver iPackageDataObserver) throws RemoteException;

    void deleteApplicationCacheFilesAsUser(String str, int i, IPackageDataObserver iPackageDataObserver) throws RemoteException;

    void deleteExistingPackageAsUser(VersionedPackage versionedPackage, IPackageDeleteObserver2 iPackageDeleteObserver2, int i) throws RemoteException;

    void deletePackageAsUser(String str, int i, IPackageDeleteObserver iPackageDeleteObserver, int i2, int i3) throws RemoteException;

    void deletePackageVersioned(VersionedPackage versionedPackage, IPackageDeleteObserver2 iPackageDeleteObserver2, int i, int i2) throws RemoteException;

    void deletePreloadsFileCache() throws RemoteException;

    void dumpProfiles(String str) throws RemoteException;

    void enterSafeMode() throws RemoteException;

    void extendVerificationTimeout(int i, int i2, long j) throws RemoteException;

    ResolveInfo findPersistentPreferredActivity(Intent intent, int i) throws RemoteException;

    void finishPackageInstall(int i, boolean z) throws RemoteException;

    void flushPackageRestrictionsAsUser(int i) throws RemoteException;

    void forceDexOpt(String str) throws RemoteException;

    void freeStorage(String str, long j, int i, IntentSender intentSender) throws RemoteException;

    void freeStorageAndNotify(String str, long j, int i, IPackageDataObserver iPackageDataObserver) throws RemoteException;

    ActivityInfo getActivityInfo(ComponentName componentName, int i, int i2) throws RemoteException;

    ParceledListSlice getAllIntentFilters(String str) throws RemoteException;

    List<String> getAllPackages() throws RemoteException;

    String[] getAppOpPermissionPackages(String str) throws RemoteException;

    String getAppPredictionServicePackageName() throws RemoteException;

    int getApplicationEnabledSetting(String str, int i) throws RemoteException;

    boolean getApplicationHiddenSettingAsUser(String str, int i) throws RemoteException;

    ApplicationInfo getApplicationInfo(String str, int i, int i2) throws RemoteException;

    IArtManager getArtManager() throws RemoteException;

    String getAttentionServicePackageName() throws RemoteException;

    boolean getBlockUninstallForUser(String str, int i) throws RemoteException;

    ChangedPackages getChangedPackages(int i, int i2) throws RemoteException;

    int getComponentEnabledSetting(ComponentName componentName, int i) throws RemoteException;

    String getContentCaptureServicePackageName() throws RemoteException;

    ParceledListSlice getDeclaredSharedLibraries(String str, int i, int i2) throws RemoteException;

    byte[] getDefaultAppsBackup(int i) throws RemoteException;

    String getDefaultTextClassifierPackageName() throws RemoteException;

    int getFlagsForUid(int i) throws RemoteException;

    CharSequence getHarmfulAppWarning(String str, int i) throws RemoteException;

    ComponentName getHomeActivities(List<ResolveInfo> list) throws RemoteException;

    String getIncidentReportApproverPackageName() throws RemoteException;

    int getInstallLocation() throws RemoteException;

    int getInstallReason(String str, int i) throws RemoteException;

    InstallSourceInfo getInstallSourceInfo(String str) throws RemoteException;

    ParceledListSlice getInstalledApplications(int i, int i2) throws RemoteException;

    List<ModuleInfo> getInstalledModules(int i) throws RemoteException;

    ParceledListSlice getInstalledPackages(int i, int i2) throws RemoteException;

    String getInstallerPackageName(String str) throws RemoteException;

    String getInstantAppAndroidId(String str, int i) throws RemoteException;

    byte[] getInstantAppCookie(String str, int i) throws RemoteException;

    Bitmap getInstantAppIcon(String str, int i) throws RemoteException;

    ComponentName getInstantAppInstallerComponent() throws RemoteException;

    ComponentName getInstantAppResolverComponent() throws RemoteException;

    ComponentName getInstantAppResolverSettingsComponent() throws RemoteException;

    ParceledListSlice getInstantApps(int i) throws RemoteException;

    InstrumentationInfo getInstrumentationInfo(ComponentName componentName, int i) throws RemoteException;

    byte[] getIntentFilterVerificationBackup(int i) throws RemoteException;

    ParceledListSlice getIntentFilterVerifications(String str) throws RemoteException;

    int getIntentVerificationStatus(String str, int i) throws RemoteException;

    KeySet getKeySetByAlias(String str, String str2) throws RemoteException;

    ResolveInfo getLastChosenActivity(Intent intent, String str, int i) throws RemoteException;

    List<String> getMimeGroup(String str, String str2) throws RemoteException;

    ModuleInfo getModuleInfo(String str, int i) throws RemoteException;

    int getMoveStatus(int i) throws RemoteException;

    String getNameForUid(int i) throws RemoteException;

    String[] getNamesForUids(int[] iArr) throws RemoteException;

    int[] getPackageGids(String str, int i, int i2) throws RemoteException;

    PackageInfo getPackageInfo(String str, int i, int i2) throws RemoteException;

    PackageInfo getPackageInfoVersioned(VersionedPackage versionedPackage, int i, int i2) throws RemoteException;

    IPackageInstaller getPackageInstaller() throws RemoteException;

    void getPackageSizeInfo(String str, int i, IPackageStatsObserver iPackageStatsObserver) throws RemoteException;

    int getPackageUid(String str, int i, int i2) throws RemoteException;

    String[] getPackagesForUid(int i) throws RemoteException;

    ParceledListSlice getPackagesHoldingPermissions(String[] strArr, int i, int i2) throws RemoteException;

    String getPermissionControllerPackageName() throws RemoteException;

    PermissionGroupInfo getPermissionGroupInfo(String str, int i) throws RemoteException;

    ParceledListSlice getPersistentApplications(int i) throws RemoteException;

    int getPreferredActivities(List<IntentFilter> list, List<ComponentName> list2, String str) throws RemoteException;

    byte[] getPreferredActivityBackup(int i) throws RemoteException;

    int getPrivateFlagsForUid(int i) throws RemoteException;

    ProviderInfo getProviderInfo(ComponentName componentName, int i, int i2) throws RemoteException;

    ActivityInfo getReceiverInfo(ComponentName componentName, int i, int i2) throws RemoteException;

    int getRuntimePermissionsVersion(int i) throws RemoteException;

    ServiceInfo getServiceInfo(ComponentName componentName, int i, int i2) throws RemoteException;

    String getServicesSystemSharedLibraryPackageName() throws RemoteException;

    String getSetupWizardPackageName() throws RemoteException;

    ParceledListSlice getSharedLibraries(String str, int i, int i2) throws RemoteException;

    String getSharedSystemSharedLibraryPackageName() throws RemoteException;

    KeySet getSigningKeySet(String str) throws RemoteException;

    Bundle getSuspendedPackageAppExtras(String str, int i) throws RemoteException;

    ParceledListSlice getSystemAvailableFeatures() throws RemoteException;

    String getSystemCaptionsServicePackageName() throws RemoteException;

    String[] getSystemSharedLibraryNames() throws RemoteException;

    String getSystemTextClassifierPackageName() throws RemoteException;

    int getUidForSharedUser(String str) throws RemoteException;

    String[] getUnsuspendablePackagesForUser(String[] strArr, int i) throws RemoteException;

    VerifierDeviceIdentity getVerifierDeviceIdentity() throws RemoteException;

    String getWellbeingPackageName() throws RemoteException;

    void grantImplicitAccess(int i, String str) throws RemoteException;

    void grantRuntimePermission(String str, String str2, int i) throws RemoteException;

    boolean hasSigningCertificate(String str, byte[] bArr, int i) throws RemoteException;

    boolean hasSystemFeature(String str, int i) throws RemoteException;

    boolean hasSystemUidErrors() throws RemoteException;

    boolean hasUidSigningCertificate(int i, byte[] bArr, int i2) throws RemoteException;

    int installExistingPackageAsUser(String str, int i, int i2, int i3, List<String> list) throws RemoteException;

    boolean isAutoRevokeWhitelisted(String str) throws RemoteException;

    boolean isDeviceUpgrading() throws RemoteException;

    boolean isFirstBoot() throws RemoteException;

    boolean isInstantApp(String str, int i) throws RemoteException;

    boolean isOnlyCoreApps() throws RemoteException;

    boolean isPackageAvailable(String str, int i) throws RemoteException;

    boolean isPackageDeviceAdminOnAnyUser(String str) throws RemoteException;

    boolean isPackageSignedByKeySet(String str, KeySet keySet) throws RemoteException;

    boolean isPackageSignedByKeySetExactly(String str, KeySet keySet) throws RemoteException;

    boolean isPackageStateProtected(String str, int i) throws RemoteException;

    boolean isPackageSuspendedForUser(String str, int i) throws RemoteException;

    boolean isProtectedBroadcast(String str) throws RemoteException;

    boolean isSafeMode() throws RemoteException;

    boolean isStorageLow() throws RemoteException;

    boolean isUidPrivileged(int i) throws RemoteException;

    void logAppProcessStartIfNeeded(String str, int i, String str2, String str3, int i2) throws RemoteException;

    int movePackage(String str, String str2) throws RemoteException;

    int movePrimaryStorage(String str) throws RemoteException;

    void notifyDexLoad(String str, Map<String, String> map, String str2) throws RemoteException;

    void notifyPackageUse(String str, int i) throws RemoteException;

    void notifyPackagesReplacedReceived(String[] strArr) throws RemoteException;

    void overrideLabelAndIcon(ComponentName componentName, String str, int i, int i2) throws RemoteException;

    boolean performDexOptMode(String str, boolean z, String str2, boolean z2, boolean z3, String str3) throws RemoteException;

    boolean performDexOptSecondary(String str, String str2, boolean z) throws RemoteException;

    void performFstrimIfNeeded() throws RemoteException;

    ParceledListSlice queryContentProviders(String str, int i, int i2, String str2) throws RemoteException;

    ParceledListSlice queryInstrumentation(String str, int i) throws RemoteException;

    ParceledListSlice queryIntentActivities(Intent intent, String str, int i, int i2) throws RemoteException;

    ParceledListSlice queryIntentActivityOptions(ComponentName componentName, Intent[] intentArr, String[] strArr, Intent intent, String str, int i, int i2) throws RemoteException;

    ParceledListSlice queryIntentContentProviders(Intent intent, String str, int i, int i2) throws RemoteException;

    ParceledListSlice queryIntentReceivers(Intent intent, String str, int i, int i2) throws RemoteException;

    ParceledListSlice queryIntentServices(Intent intent, String str, int i, int i2) throws RemoteException;

    void querySyncProviders(List<String> list, List<ProviderInfo> list2) throws RemoteException;

    void reconcileSecondaryDexFiles(String str) throws RemoteException;

    void registerDexModule(String str, String str2, boolean z, IDexModuleRegisterCallback iDexModuleRegisterCallback) throws RemoteException;

    void registerMoveCallback(IPackageMoveObserver iPackageMoveObserver) throws RemoteException;

    void removePermission(String str) throws RemoteException;

    void replacePreferredActivity(IntentFilter intentFilter, int i, ComponentName[] componentNameArr, ComponentName componentName, int i2) throws RemoteException;

    void resetApplicationPreferences(int i) throws RemoteException;

    ProviderInfo resolveContentProvider(String str, int i, int i2) throws RemoteException;

    ResolveInfo resolveIntent(Intent intent, String str, int i, int i2) throws RemoteException;

    ResolveInfo resolveService(Intent intent, String str, int i, int i2) throws RemoteException;

    void restoreDefaultApps(byte[] bArr, int i) throws RemoteException;

    void restoreIntentFilterVerification(byte[] bArr, int i) throws RemoteException;

    void restoreLabelAndIcon(ComponentName componentName, int i) throws RemoteException;

    void restorePreferredActivities(byte[] bArr, int i) throws RemoteException;

    boolean runBackgroundDexoptJob(List<String> list) throws RemoteException;

    void sendDeviceCustomizationReadyBroadcast() throws RemoteException;

    void setApplicationCategoryHint(String str, int i, String str2) throws RemoteException;

    void setApplicationEnabledSetting(String str, int i, int i2, int i3, String str2) throws RemoteException;

    boolean setApplicationHiddenSettingAsUser(String str, boolean z, int i) throws RemoteException;

    boolean setBlockUninstallForUser(String str, boolean z, int i) throws RemoteException;

    void setComponentEnabledSetting(ComponentName componentName, int i, int i2, int i3) throws RemoteException;

    String[] setDistractingPackageRestrictionsAsUser(String[] strArr, int i, int i2) throws RemoteException;

    void setHarmfulAppWarning(String str, CharSequence charSequence, int i) throws RemoteException;

    void setHomeActivity(ComponentName componentName, int i) throws RemoteException;

    boolean setInstallLocation(int i) throws RemoteException;

    void setInstallerPackageName(String str, String str2) throws RemoteException;

    boolean setInstantAppCookie(String str, byte[] bArr, int i) throws RemoteException;

    void setLastChosenActivity(Intent intent, String str, int i, IntentFilter intentFilter, int i2, ComponentName componentName) throws RemoteException;

    void setMimeGroup(String str, String str2, List<String> list) throws RemoteException;

    void setPackageStoppedState(String str, boolean z, int i) throws RemoteException;

    String[] setPackagesSuspendedAsUser(String[] strArr, boolean z, PersistableBundle persistableBundle, PersistableBundle persistableBundle2, SuspendDialogInfo suspendDialogInfo, String str, int i) throws RemoteException;

    boolean setRequiredForSystemUser(String str, boolean z) throws RemoteException;

    void setRuntimePermissionsVersion(int i, int i2) throws RemoteException;

    void setSystemAppHiddenUntilInstalled(String str, boolean z) throws RemoteException;

    boolean setSystemAppInstallState(String str, boolean z, int i) throws RemoteException;

    void setUpdateAvailable(String str, boolean z) throws RemoteException;

    void systemReady() throws RemoteException;

    void unregisterMoveCallback(IPackageMoveObserver iPackageMoveObserver) throws RemoteException;

    boolean updateIntentVerificationStatus(String str, int i, int i2) throws RemoteException;

    void updatePackagesIfNeeded() throws RemoteException;

    void verifyIntentFilter(int i, int i2, List<String> list) throws RemoteException;

    void verifyPendingInstall(int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IPackageManager {
        private static final String DESCRIPTOR = "android.content.pm.IPackageManager";
        static final int TRANSACTION_activitySupportsIntent = 11;
        static final int TRANSACTION_addCrossProfileIntentFilter = 61;
        static final int TRANSACTION_addPermission = 186;
        static final int TRANSACTION_addPermissionAsync = 187;
        static final int TRANSACTION_addPersistentPreferredActivity = 59;
        static final int TRANSACTION_addPreferredActivity = 55;
        static final int TRANSACTION_canForwardTo = 28;
        static final int TRANSACTION_canRequestPackageInstalls = 157;
        static final int TRANSACTION_canonicalToCurrentPackageNames = 8;
        static final int TRANSACTION_checkPackageStartable = 1;
        static final int TRANSACTION_checkPermission = 189;
        static final int TRANSACTION_checkSignatures = 16;
        static final int TRANSACTION_checkUidPermission = 191;
        static final int TRANSACTION_checkUidSignatures = 17;
        static final int TRANSACTION_clearApplicationProfileData = 90;
        static final int TRANSACTION_clearApplicationUserData = 89;
        static final int TRANSACTION_clearCrossProfileIntentFilters = 62;
        static final int TRANSACTION_clearPackagePersistentPreferredActivities = 60;
        static final int TRANSACTION_clearPackagePreferredActivities = 57;
        static final int TRANSACTION_compileLayouts = 106;
        static final int TRANSACTION_currentToCanonicalPackageNames = 7;
        static final int TRANSACTION_deleteApplicationCacheFiles = 87;
        static final int TRANSACTION_deleteApplicationCacheFilesAsUser = 88;
        static final int TRANSACTION_deleteExistingPackageAsUser = 49;
        static final int TRANSACTION_deletePackageAsUser = 47;
        static final int TRANSACTION_deletePackageVersioned = 48;
        static final int TRANSACTION_deletePreloadsFileCache = 158;
        static final int TRANSACTION_dumpProfiles = 107;
        static final int TRANSACTION_enterSafeMode = 95;
        static final int TRANSACTION_extendVerificationTimeout = 120;
        static final int TRANSACTION_findPersistentPreferredActivity = 27;
        static final int TRANSACTION_finishPackageInstall = 44;
        static final int TRANSACTION_flushPackageRestrictionsAsUser = 83;
        static final int TRANSACTION_forceDexOpt = 108;
        static final int TRANSACTION_freeStorage = 86;
        static final int TRANSACTION_freeStorageAndNotify = 85;
        static final int TRANSACTION_getActivityInfo = 10;
        static final int TRANSACTION_getAllIntentFilters = 125;
        static final int TRANSACTION_getAllPackages = 18;
        static final int TRANSACTION_getAppOpPermissionPackages = 184;
        static final int TRANSACTION_getAppPredictionServicePackageName = 172;
        static final int TRANSACTION_getApplicationEnabledSetting = 81;
        static final int TRANSACTION_getApplicationHiddenSettingAsUser = 132;
        static final int TRANSACTION_getApplicationInfo = 9;
        static final int TRANSACTION_getArtManager = 163;
        static final int TRANSACTION_getAttentionServicePackageName = 170;
        static final int TRANSACTION_getBlockUninstallForUser = 137;
        static final int TRANSACTION_getChangedPackages = 152;
        static final int TRANSACTION_getComponentEnabledSetting = 79;
        static final int TRANSACTION_getContentCaptureServicePackageName = 176;
        static final int TRANSACTION_getDeclaredSharedLibraries = 156;
        static final int TRANSACTION_getDefaultAppsBackup = 70;
        static final int TRANSACTION_getDefaultTextClassifierPackageName = 168;
        static final int TRANSACTION_getFlagsForUid = 23;
        static final int TRANSACTION_getHarmfulAppWarning = 165;
        static final int TRANSACTION_getHomeActivities = 74;
        static final int TRANSACTION_getIncidentReportApproverPackageName = 175;
        static final int TRANSACTION_getInstallLocation = 117;
        static final int TRANSACTION_getInstallReason = 154;
        static final int TRANSACTION_getInstallSourceInfo = 51;
        static final int TRANSACTION_getInstalledApplications = 37;
        static final int TRANSACTION_getInstalledModules = 179;
        static final int TRANSACTION_getInstalledPackages = 35;
        static final int TRANSACTION_getInstallerPackageName = 50;
        static final int TRANSACTION_getInstantAppAndroidId = 162;
        static final int TRANSACTION_getInstantAppCookie = 144;
        static final int TRANSACTION_getInstantAppIcon = 146;
        static final int TRANSACTION_getInstantAppInstallerComponent = 161;
        static final int TRANSACTION_getInstantAppResolverComponent = 159;
        static final int TRANSACTION_getInstantAppResolverSettingsComponent = 160;
        static final int TRANSACTION_getInstantApps = 143;
        static final int TRANSACTION_getInstrumentationInfo = 42;
        static final int TRANSACTION_getIntentFilterVerificationBackup = 72;
        static final int TRANSACTION_getIntentFilterVerifications = 124;
        static final int TRANSACTION_getIntentVerificationStatus = 122;
        static final int TRANSACTION_getKeySetByAlias = 138;
        static final int TRANSACTION_getLastChosenActivity = 53;
        static final int TRANSACTION_getMimeGroup = 193;
        static final int TRANSACTION_getModuleInfo = 180;
        static final int TRANSACTION_getMoveStatus = 111;
        static final int TRANSACTION_getNameForUid = 20;
        static final int TRANSACTION_getNamesForUids = 21;
        static final int TRANSACTION_getPackageGids = 6;
        static final int TRANSACTION_getPackageInfo = 3;
        static final int TRANSACTION_getPackageInfoVersioned = 4;
        static final int TRANSACTION_getPackageInstaller = 135;
        static final int TRANSACTION_getPackageSizeInfo = 91;
        static final int TRANSACTION_getPackageUid = 5;
        static final int TRANSACTION_getPackagesForUid = 19;
        static final int TRANSACTION_getPackagesHoldingPermissions = 36;
        static final int TRANSACTION_getPermissionControllerPackageName = 142;
        static final int TRANSACTION_getPermissionGroupInfo = 185;
        static final int TRANSACTION_getPersistentApplications = 38;
        static final int TRANSACTION_getPreferredActivities = 58;
        static final int TRANSACTION_getPreferredActivityBackup = 68;
        static final int TRANSACTION_getPrivateFlagsForUid = 24;
        static final int TRANSACTION_getProviderInfo = 14;
        static final int TRANSACTION_getReceiverInfo = 12;
        static final int TRANSACTION_getRuntimePermissionsVersion = 181;
        static final int TRANSACTION_getServiceInfo = 13;
        static final int TRANSACTION_getServicesSystemSharedLibraryPackageName = 150;
        static final int TRANSACTION_getSetupWizardPackageName = 174;
        static final int TRANSACTION_getSharedLibraries = 155;
        static final int TRANSACTION_getSharedSystemSharedLibraryPackageName = 151;
        static final int TRANSACTION_getSigningKeySet = 139;
        static final int TRANSACTION_getSuspendedPackageAppExtras = 67;
        static final int TRANSACTION_getSystemAvailableFeatures = 93;
        static final int TRANSACTION_getSystemCaptionsServicePackageName = 173;
        static final int TRANSACTION_getSystemSharedLibraryNames = 92;
        static final int TRANSACTION_getSystemTextClassifierPackageName = 169;
        static final int TRANSACTION_getUidForSharedUser = 22;
        static final int TRANSACTION_getUnsuspendablePackagesForUser = 65;
        static final int TRANSACTION_getVerifierDeviceIdentity = 126;
        static final int TRANSACTION_getWellbeingPackageName = 171;
        static final int TRANSACTION_grantImplicitAccess = 195;
        static final int TRANSACTION_grantRuntimePermission = 190;
        static final int TRANSACTION_hasSigningCertificate = 166;
        static final int TRANSACTION_hasSystemFeature = 94;
        static final int TRANSACTION_hasSystemUidErrors = 98;
        static final int TRANSACTION_hasUidSigningCertificate = 167;
        static final int TRANSACTION_installExistingPackageAsUser = 118;
        static final int TRANSACTION_isAutoRevokeWhitelisted = 194;
        static final int TRANSACTION_isDeviceUpgrading = 129;
        static final int TRANSACTION_isFirstBoot = 127;
        static final int TRANSACTION_isInstantApp = 147;
        static final int TRANSACTION_isOnlyCoreApps = 128;
        static final int TRANSACTION_isPackageAvailable = 2;
        static final int TRANSACTION_isPackageDeviceAdminOnAnyUser = 153;
        static final int TRANSACTION_isPackageSignedByKeySet = 140;
        static final int TRANSACTION_isPackageSignedByKeySetExactly = 141;
        static final int TRANSACTION_isPackageStateProtected = 177;
        static final int TRANSACTION_isPackageSuspendedForUser = 66;
        static final int TRANSACTION_isProtectedBroadcast = 15;
        static final int TRANSACTION_isSafeMode = 96;
        static final int TRANSACTION_isStorageLow = 130;
        static final int TRANSACTION_isUidPrivileged = 25;
        static final int TRANSACTION_logAppProcessStartIfNeeded = 82;
        static final int TRANSACTION_movePackage = 114;
        static final int TRANSACTION_movePrimaryStorage = 115;
        static final int TRANSACTION_notifyDexLoad = 102;
        static final int TRANSACTION_notifyPackageUse = 101;
        static final int TRANSACTION_notifyPackagesReplacedReceived = 183;
        static final int TRANSACTION_overrideLabelAndIcon = 76;
        static final int TRANSACTION_performDexOptMode = 104;
        static final int TRANSACTION_performDexOptSecondary = 105;
        static final int TRANSACTION_performFstrimIfNeeded = 99;
        static final int TRANSACTION_queryContentProviders = 41;
        static final int TRANSACTION_queryInstrumentation = 43;
        static final int TRANSACTION_queryIntentActivities = 29;
        static final int TRANSACTION_queryIntentActivityOptions = 30;
        static final int TRANSACTION_queryIntentContentProviders = 34;
        static final int TRANSACTION_queryIntentReceivers = 31;
        static final int TRANSACTION_queryIntentServices = 33;
        static final int TRANSACTION_querySyncProviders = 40;
        static final int TRANSACTION_reconcileSecondaryDexFiles = 110;
        static final int TRANSACTION_registerDexModule = 103;
        static final int TRANSACTION_registerMoveCallback = 112;
        static final int TRANSACTION_removePermission = 188;
        static final int TRANSACTION_replacePreferredActivity = 56;
        static final int TRANSACTION_resetApplicationPreferences = 52;
        static final int TRANSACTION_resolveContentProvider = 39;
        static final int TRANSACTION_resolveIntent = 26;
        static final int TRANSACTION_resolveService = 32;
        static final int TRANSACTION_restoreDefaultApps = 71;
        static final int TRANSACTION_restoreIntentFilterVerification = 73;
        static final int TRANSACTION_restoreLabelAndIcon = 77;
        static final int TRANSACTION_restorePreferredActivities = 69;
        static final int TRANSACTION_runBackgroundDexoptJob = 109;
        static final int TRANSACTION_sendDeviceCustomizationReadyBroadcast = 178;
        static final int TRANSACTION_setApplicationCategoryHint = 46;
        static final int TRANSACTION_setApplicationEnabledSetting = 80;
        static final int TRANSACTION_setApplicationHiddenSettingAsUser = 131;
        static final int TRANSACTION_setBlockUninstallForUser = 136;
        static final int TRANSACTION_setComponentEnabledSetting = 78;
        static final int TRANSACTION_setDistractingPackageRestrictionsAsUser = 63;
        static final int TRANSACTION_setHarmfulAppWarning = 164;
        static final int TRANSACTION_setHomeActivity = 75;
        static final int TRANSACTION_setInstallLocation = 116;
        static final int TRANSACTION_setInstallerPackageName = 45;
        static final int TRANSACTION_setInstantAppCookie = 145;
        static final int TRANSACTION_setLastChosenActivity = 54;
        static final int TRANSACTION_setMimeGroup = 192;
        static final int TRANSACTION_setPackageStoppedState = 84;
        static final int TRANSACTION_setPackagesSuspendedAsUser = 64;
        static final int TRANSACTION_setRequiredForSystemUser = 148;
        static final int TRANSACTION_setRuntimePermissionsVersion = 182;
        static final int TRANSACTION_setSystemAppHiddenUntilInstalled = 133;
        static final int TRANSACTION_setSystemAppInstallState = 134;
        static final int TRANSACTION_setUpdateAvailable = 149;
        static final int TRANSACTION_systemReady = 97;
        static final int TRANSACTION_unregisterMoveCallback = 113;
        static final int TRANSACTION_updateIntentVerificationStatus = 123;
        static final int TRANSACTION_updatePackagesIfNeeded = 100;
        static final int TRANSACTION_verifyIntentFilter = 121;
        static final int TRANSACTION_verifyPendingInstall = 119;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "checkPackageStartable";
                case 2:
                    return "isPackageAvailable";
                case 3:
                    return "getPackageInfo";
                case 4:
                    return "getPackageInfoVersioned";
                case 5:
                    return "getPackageUid";
                case 6:
                    return "getPackageGids";
                case 7:
                    return "currentToCanonicalPackageNames";
                case 8:
                    return "canonicalToCurrentPackageNames";
                case 9:
                    return "getApplicationInfo";
                case 10:
                    return "getActivityInfo";
                case 11:
                    return "activitySupportsIntent";
                case 12:
                    return "getReceiverInfo";
                case 13:
                    return "getServiceInfo";
                case 14:
                    return "getProviderInfo";
                case 15:
                    return "isProtectedBroadcast";
                case 16:
                    return "checkSignatures";
                case 17:
                    return "checkUidSignatures";
                case 18:
                    return "getAllPackages";
                case 19:
                    return "getPackagesForUid";
                case 20:
                    return "getNameForUid";
                case 21:
                    return "getNamesForUids";
                case 22:
                    return "getUidForSharedUser";
                case 23:
                    return "getFlagsForUid";
                case 24:
                    return "getPrivateFlagsForUid";
                case 25:
                    return "isUidPrivileged";
                case 26:
                    return "resolveIntent";
                case 27:
                    return "findPersistentPreferredActivity";
                case 28:
                    return "canForwardTo";
                case 29:
                    return "queryIntentActivities";
                case 30:
                    return "queryIntentActivityOptions";
                case 31:
                    return "queryIntentReceivers";
                case 32:
                    return "resolveService";
                case 33:
                    return "queryIntentServices";
                case 34:
                    return "queryIntentContentProviders";
                case 35:
                    return "getInstalledPackages";
                case 36:
                    return "getPackagesHoldingPermissions";
                case 37:
                    return "getInstalledApplications";
                case 38:
                    return "getPersistentApplications";
                case 39:
                    return "resolveContentProvider";
                case 40:
                    return "querySyncProviders";
                case 41:
                    return "queryContentProviders";
                case 42:
                    return "getInstrumentationInfo";
                case 43:
                    return "queryInstrumentation";
                case 44:
                    return "finishPackageInstall";
                case 45:
                    return "setInstallerPackageName";
                case 46:
                    return "setApplicationCategoryHint";
                case 47:
                    return "deletePackageAsUser";
                case 48:
                    return "deletePackageVersioned";
                case 49:
                    return "deleteExistingPackageAsUser";
                case 50:
                    return "getInstallerPackageName";
                case 51:
                    return "getInstallSourceInfo";
                case 52:
                    return "resetApplicationPreferences";
                case 53:
                    return "getLastChosenActivity";
                case 54:
                    return "setLastChosenActivity";
                case 55:
                    return "addPreferredActivity";
                case 56:
                    return "replacePreferredActivity";
                case 57:
                    return "clearPackagePreferredActivities";
                case 58:
                    return "getPreferredActivities";
                case 59:
                    return "addPersistentPreferredActivity";
                case 60:
                    return "clearPackagePersistentPreferredActivities";
                case 61:
                    return "addCrossProfileIntentFilter";
                case 62:
                    return "clearCrossProfileIntentFilters";
                case 63:
                    return "setDistractingPackageRestrictionsAsUser";
                case 64:
                    return "setPackagesSuspendedAsUser";
                case 65:
                    return "getUnsuspendablePackagesForUser";
                case 66:
                    return "isPackageSuspendedForUser";
                case 67:
                    return "getSuspendedPackageAppExtras";
                case 68:
                    return "getPreferredActivityBackup";
                case 69:
                    return "restorePreferredActivities";
                case 70:
                    return "getDefaultAppsBackup";
                case 71:
                    return "restoreDefaultApps";
                case 72:
                    return "getIntentFilterVerificationBackup";
                case 73:
                    return "restoreIntentFilterVerification";
                case 74:
                    return "getHomeActivities";
                case 75:
                    return "setHomeActivity";
                case 76:
                    return "overrideLabelAndIcon";
                case 77:
                    return "restoreLabelAndIcon";
                case 78:
                    return "setComponentEnabledSetting";
                case 79:
                    return "getComponentEnabledSetting";
                case 80:
                    return "setApplicationEnabledSetting";
                case 81:
                    return "getApplicationEnabledSetting";
                case 82:
                    return "logAppProcessStartIfNeeded";
                case 83:
                    return "flushPackageRestrictionsAsUser";
                case 84:
                    return "setPackageStoppedState";
                case 85:
                    return "freeStorageAndNotify";
                case 86:
                    return "freeStorage";
                case 87:
                    return "deleteApplicationCacheFiles";
                case 88:
                    return "deleteApplicationCacheFilesAsUser";
                case 89:
                    return "clearApplicationUserData";
                case 90:
                    return "clearApplicationProfileData";
                case 91:
                    return "getPackageSizeInfo";
                case 92:
                    return "getSystemSharedLibraryNames";
                case 93:
                    return "getSystemAvailableFeatures";
                case 94:
                    return "hasSystemFeature";
                case 95:
                    return "enterSafeMode";
                case 96:
                    return "isSafeMode";
                case 97:
                    return "systemReady";
                case 98:
                    return "hasSystemUidErrors";
                case 99:
                    return "performFstrimIfNeeded";
                case 100:
                    return "updatePackagesIfNeeded";
                case 101:
                    return "notifyPackageUse";
                case 102:
                    return "notifyDexLoad";
                case 103:
                    return "registerDexModule";
                case 104:
                    return "performDexOptMode";
                case 105:
                    return "performDexOptSecondary";
                case 106:
                    return "compileLayouts";
                case 107:
                    return "dumpProfiles";
                case 108:
                    return "forceDexOpt";
                case 109:
                    return "runBackgroundDexoptJob";
                case 110:
                    return "reconcileSecondaryDexFiles";
                case 111:
                    return "getMoveStatus";
                case 112:
                    return "registerMoveCallback";
                case 113:
                    return "unregisterMoveCallback";
                case 114:
                    return "movePackage";
                case 115:
                    return "movePrimaryStorage";
                case 116:
                    return "setInstallLocation";
                case 117:
                    return "getInstallLocation";
                case 118:
                    return "installExistingPackageAsUser";
                case 119:
                    return "verifyPendingInstall";
                case 120:
                    return "extendVerificationTimeout";
                case 121:
                    return "verifyIntentFilter";
                case 122:
                    return "getIntentVerificationStatus";
                case 123:
                    return "updateIntentVerificationStatus";
                case 124:
                    return "getIntentFilterVerifications";
                case 125:
                    return "getAllIntentFilters";
                case 126:
                    return "getVerifierDeviceIdentity";
                case 127:
                    return "isFirstBoot";
                case 128:
                    return "isOnlyCoreApps";
                case 129:
                    return "isDeviceUpgrading";
                case 130:
                    return "isStorageLow";
                case 131:
                    return "setApplicationHiddenSettingAsUser";
                case 132:
                    return "getApplicationHiddenSettingAsUser";
                case 133:
                    return "setSystemAppHiddenUntilInstalled";
                case 134:
                    return "setSystemAppInstallState";
                case 135:
                    return "getPackageInstaller";
                case 136:
                    return "setBlockUninstallForUser";
                case 137:
                    return "getBlockUninstallForUser";
                case 138:
                    return "getKeySetByAlias";
                case 139:
                    return "getSigningKeySet";
                case 140:
                    return "isPackageSignedByKeySet";
                case 141:
                    return "isPackageSignedByKeySetExactly";
                case 142:
                    return "getPermissionControllerPackageName";
                case 143:
                    return "getInstantApps";
                case 144:
                    return "getInstantAppCookie";
                case 145:
                    return "setInstantAppCookie";
                case 146:
                    return "getInstantAppIcon";
                case 147:
                    return "isInstantApp";
                case 148:
                    return "setRequiredForSystemUser";
                case 149:
                    return "setUpdateAvailable";
                case 150:
                    return "getServicesSystemSharedLibraryPackageName";
                case 151:
                    return "getSharedSystemSharedLibraryPackageName";
                case 152:
                    return "getChangedPackages";
                case 153:
                    return "isPackageDeviceAdminOnAnyUser";
                case 154:
                    return "getInstallReason";
                case 155:
                    return "getSharedLibraries";
                case 156:
                    return "getDeclaredSharedLibraries";
                case 157:
                    return "canRequestPackageInstalls";
                case 158:
                    return "deletePreloadsFileCache";
                case 159:
                    return "getInstantAppResolverComponent";
                case 160:
                    return "getInstantAppResolverSettingsComponent";
                case 161:
                    return "getInstantAppInstallerComponent";
                case 162:
                    return "getInstantAppAndroidId";
                case 163:
                    return "getArtManager";
                case 164:
                    return "setHarmfulAppWarning";
                case 165:
                    return "getHarmfulAppWarning";
                case 166:
                    return "hasSigningCertificate";
                case 167:
                    return "hasUidSigningCertificate";
                case 168:
                    return "getDefaultTextClassifierPackageName";
                case 169:
                    return "getSystemTextClassifierPackageName";
                case 170:
                    return "getAttentionServicePackageName";
                case 171:
                    return "getWellbeingPackageName";
                case 172:
                    return "getAppPredictionServicePackageName";
                case 173:
                    return "getSystemCaptionsServicePackageName";
                case 174:
                    return "getSetupWizardPackageName";
                case 175:
                    return "getIncidentReportApproverPackageName";
                case 176:
                    return "getContentCaptureServicePackageName";
                case 177:
                    return "isPackageStateProtected";
                case 178:
                    return "sendDeviceCustomizationReadyBroadcast";
                case 179:
                    return "getInstalledModules";
                case 180:
                    return "getModuleInfo";
                case 181:
                    return "getRuntimePermissionsVersion";
                case 182:
                    return "setRuntimePermissionsVersion";
                case 183:
                    return "notifyPackagesReplacedReceived";
                case 184:
                    return "getAppOpPermissionPackages";
                case 185:
                    return "getPermissionGroupInfo";
                case 186:
                    return "addPermission";
                case 187:
                    return "addPermissionAsync";
                case 188:
                    return "removePermission";
                case 189:
                    return "checkPermission";
                case 190:
                    return "grantRuntimePermission";
                case 191:
                    return "checkUidPermission";
                case 192:
                    return "setMimeGroup";
                case 193:
                    return "getMimeGroup";
                case 194:
                    return "isAutoRevokeWhitelisted";
                case 195:
                    return "grantImplicitAccess";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPackageManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IPackageManager)) {
                return (IPackageManager) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, final Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    checkPackageStartable(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsPackageAvailable = isPackageAvailable(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsPackageAvailable ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    PackageInfo packageInfo = getPackageInfo(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (packageInfo != null) {
                        parcel2.writeInt(1);
                        packageInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    PackageInfo packageInfoVersioned = getPackageInfoVersioned(parcel.readInt() != 0 ? VersionedPackage.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (packageInfoVersioned != null) {
                        parcel2.writeInt(1);
                        packageInfoVersioned.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int packageUid = getPackageUid(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(packageUid);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] packageGids = getPackageGids(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeIntArray(packageGids);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] strArrCurrentToCanonicalPackageNames = currentToCanonicalPackageNames(parcel.createStringArray());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(strArrCurrentToCanonicalPackageNames);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] strArrCanonicalToCurrentPackageNames = canonicalToCurrentPackageNames(parcel.createStringArray());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(strArrCanonicalToCurrentPackageNames);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    ApplicationInfo applicationInfo = getApplicationInfo(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (applicationInfo != null) {
                        parcel2.writeInt(1);
                        applicationInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    ActivityInfo activityInfo = getActivityInfo(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (activityInfo != null) {
                        parcel2.writeInt(1);
                        activityInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zActivitySupportsIntent = activitySupportsIntent(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zActivitySupportsIntent ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    ActivityInfo receiverInfo = getReceiverInfo(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (receiverInfo != null) {
                        parcel2.writeInt(1);
                        receiverInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    ServiceInfo serviceInfo = getServiceInfo(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (serviceInfo != null) {
                        parcel2.writeInt(1);
                        serviceInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    ProviderInfo providerInfo = getProviderInfo(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (providerInfo != null) {
                        parcel2.writeInt(1);
                        providerInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsProtectedBroadcast = isProtectedBroadcast(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsProtectedBroadcast ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCheckSignatures = checkSignatures(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iCheckSignatures);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCheckUidSignatures = checkUidSignatures(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iCheckUidSignatures);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> allPackages = getAllPackages();
                    parcel2.writeNoException();
                    parcel2.writeStringList(allPackages);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] packagesForUid = getPackagesForUid(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(packagesForUid);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    String nameForUid = getNameForUid(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(nameForUid);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] namesForUids = getNamesForUids(parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(namesForUids);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    int uidForSharedUser = getUidForSharedUser(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(uidForSharedUser);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    int flagsForUid = getFlagsForUid(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(flagsForUid);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    int privateFlagsForUid = getPrivateFlagsForUid(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(privateFlagsForUid);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsUidPrivileged = isUidPrivileged(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsUidPrivileged ? 1 : 0);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    ResolveInfo resolveInfoResolveIntent = resolveIntent(parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (resolveInfoResolveIntent != null) {
                        parcel2.writeInt(1);
                        resolveInfoResolveIntent.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    ResolveInfo resolveInfoFindPersistentPreferredActivity = findPersistentPreferredActivity(parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    if (resolveInfoFindPersistentPreferredActivity != null) {
                        parcel2.writeInt(1);
                        resolveInfoFindPersistentPreferredActivity.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCanForwardTo = canForwardTo(parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCanForwardTo ? 1 : 0);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice parceledListSliceQueryIntentActivities = queryIntentActivities(parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (parceledListSliceQueryIntentActivities != null) {
                        parcel2.writeInt(1);
                        parceledListSliceQueryIntentActivities.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice parceledListSliceQueryIntentActivityOptions = queryIntentActivityOptions(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, (Intent[]) parcel.createTypedArray(Intent.CREATOR), parcel.createStringArray(), parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (parceledListSliceQueryIntentActivityOptions != null) {
                        parcel2.writeInt(1);
                        parceledListSliceQueryIntentActivityOptions.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice parceledListSliceQueryIntentReceivers = queryIntentReceivers(parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (parceledListSliceQueryIntentReceivers != null) {
                        parcel2.writeInt(1);
                        parceledListSliceQueryIntentReceivers.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    ResolveInfo resolveInfoResolveService = resolveService(parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (resolveInfoResolveService != null) {
                        parcel2.writeInt(1);
                        resolveInfoResolveService.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice parceledListSliceQueryIntentServices = queryIntentServices(parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (parceledListSliceQueryIntentServices != null) {
                        parcel2.writeInt(1);
                        parceledListSliceQueryIntentServices.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice parceledListSliceQueryIntentContentProviders = queryIntentContentProviders(parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (parceledListSliceQueryIntentContentProviders != null) {
                        parcel2.writeInt(1);
                        parceledListSliceQueryIntentContentProviders.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice installedPackages = getInstalledPackages(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (installedPackages != null) {
                        parcel2.writeInt(1);
                        installedPackages.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice packagesHoldingPermissions = getPackagesHoldingPermissions(parcel.createStringArray(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (packagesHoldingPermissions != null) {
                        parcel2.writeInt(1);
                        packagesHoldingPermissions.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice installedApplications = getInstalledApplications(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (installedApplications != null) {
                        parcel2.writeInt(1);
                        installedApplications.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice persistentApplications = getPersistentApplications(parcel.readInt());
                    parcel2.writeNoException();
                    if (persistentApplications != null) {
                        parcel2.writeInt(1);
                        persistentApplications.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    ProviderInfo providerInfoResolveContentProvider = resolveContentProvider(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (providerInfoResolveContentProvider != null) {
                        parcel2.writeInt(1);
                        providerInfoResolveContentProvider.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    ArrayList<String> arrayListCreateStringArrayList = parcel.createStringArrayList();
                    ArrayList arrayListCreateTypedArrayList = parcel.createTypedArrayList(ProviderInfo.CREATOR);
                    querySyncProviders(arrayListCreateStringArrayList, arrayListCreateTypedArrayList);
                    parcel2.writeNoException();
                    parcel2.writeStringList(arrayListCreateStringArrayList);
                    parcel2.writeTypedList(arrayListCreateTypedArrayList);
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice parceledListSliceQueryContentProviders = queryContentProviders(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    if (parceledListSliceQueryContentProviders != null) {
                        parcel2.writeInt(1);
                        parceledListSliceQueryContentProviders.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    InstrumentationInfo instrumentationInfo = getInstrumentationInfo(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    if (instrumentationInfo != null) {
                        parcel2.writeInt(1);
                        instrumentationInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice parceledListSliceQueryInstrumentation = queryInstrumentation(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (parceledListSliceQueryInstrumentation != null) {
                        parcel2.writeInt(1);
                        parceledListSliceQueryInstrumentation.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    finishPackageInstall(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInstallerPackageName(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    setApplicationCategoryHint(parcel.readString(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    deletePackageAsUser(parcel.readString(), parcel.readInt(), IPackageDeleteObserver.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    deletePackageVersioned(parcel.readInt() != 0 ? VersionedPackage.CREATOR.createFromParcel(parcel) : null, IPackageDeleteObserver2.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteExistingPackageAsUser(parcel.readInt() != 0 ? VersionedPackage.CREATOR.createFromParcel(parcel) : null, IPackageDeleteObserver2.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    String installerPackageName = getInstallerPackageName(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(installerPackageName);
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    InstallSourceInfo installSourceInfo = getInstallSourceInfo(parcel.readString());
                    parcel2.writeNoException();
                    if (installSourceInfo != null) {
                        parcel2.writeInt(1);
                        installSourceInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 52:
                    parcel.enforceInterface(DESCRIPTOR);
                    resetApplicationPreferences(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 53:
                    parcel.enforceInterface(DESCRIPTOR);
                    ResolveInfo lastChosenActivity = getLastChosenActivity(parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (lastChosenActivity != null) {
                        parcel2.writeInt(1);
                        lastChosenActivity.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 54:
                    parcel.enforceInterface(DESCRIPTOR);
                    setLastChosenActivity(parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? IntentFilter.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 55:
                    parcel.enforceInterface(DESCRIPTOR);
                    addPreferredActivity(parcel.readInt() != 0 ? IntentFilter.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), (ComponentName[]) parcel.createTypedArray(ComponentName.CREATOR), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 56:
                    parcel.enforceInterface(DESCRIPTOR);
                    replacePreferredActivity(parcel.readInt() != 0 ? IntentFilter.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), (ComponentName[]) parcel.createTypedArray(ComponentName.CREATOR), parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 57:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearPackagePreferredActivities(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 58:
                    parcel.enforceInterface(DESCRIPTOR);
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    int preferredActivities = getPreferredActivities(arrayList, arrayList2, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(preferredActivities);
                    parcel2.writeTypedList(arrayList);
                    parcel2.writeTypedList(arrayList2);
                    return true;
                case 59:
                    parcel.enforceInterface(DESCRIPTOR);
                    addPersistentPreferredActivity(parcel.readInt() != 0 ? IntentFilter.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 60:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearPackagePersistentPreferredActivities(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 61:
                    parcel.enforceInterface(DESCRIPTOR);
                    addCrossProfileIntentFilter(parcel.readInt() != 0 ? IntentFilter.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 62:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearCrossProfileIntentFilters(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 63:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] distractingPackageRestrictionsAsUser = setDistractingPackageRestrictionsAsUser(parcel.createStringArray(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(distractingPackageRestrictionsAsUser);
                    return true;
                case 64:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] packagesSuspendedAsUser = setPackagesSuspendedAsUser(parcel.createStringArray(), parcel.readInt() != 0, parcel.readInt() != 0 ? PersistableBundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? PersistableBundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? SuspendDialogInfo.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(packagesSuspendedAsUser);
                    return true;
                case 65:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] unsuspendablePackagesForUser = getUnsuspendablePackagesForUser(parcel.createStringArray(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(unsuspendablePackagesForUser);
                    return true;
                case 66:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsPackageSuspendedForUser = isPackageSuspendedForUser(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsPackageSuspendedForUser ? 1 : 0);
                    return true;
                case 67:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle suspendedPackageAppExtras = getSuspendedPackageAppExtras(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (suspendedPackageAppExtras != null) {
                        parcel2.writeInt(1);
                        suspendedPackageAppExtras.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 68:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] preferredActivityBackup = getPreferredActivityBackup(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeByteArray(preferredActivityBackup);
                    return true;
                case 69:
                    parcel.enforceInterface(DESCRIPTOR);
                    restorePreferredActivities(parcel.createByteArray(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 70:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] defaultAppsBackup = getDefaultAppsBackup(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeByteArray(defaultAppsBackup);
                    return true;
                case 71:
                    parcel.enforceInterface(DESCRIPTOR);
                    restoreDefaultApps(parcel.createByteArray(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 72:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] intentFilterVerificationBackup = getIntentFilterVerificationBackup(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeByteArray(intentFilterVerificationBackup);
                    return true;
                case 73:
                    parcel.enforceInterface(DESCRIPTOR);
                    restoreIntentFilterVerification(parcel.createByteArray(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 74:
                    parcel.enforceInterface(DESCRIPTOR);
                    ArrayList arrayList3 = new ArrayList();
                    ComponentName homeActivities = getHomeActivities(arrayList3);
                    parcel2.writeNoException();
                    if (homeActivities != null) {
                        parcel2.writeInt(1);
                        homeActivities.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    parcel2.writeTypedList(arrayList3);
                    return true;
                case 75:
                    parcel.enforceInterface(DESCRIPTOR);
                    setHomeActivity(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 76:
                    parcel.enforceInterface(DESCRIPTOR);
                    overrideLabelAndIcon(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 77:
                    parcel.enforceInterface(DESCRIPTOR);
                    restoreLabelAndIcon(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 78:
                    parcel.enforceInterface(DESCRIPTOR);
                    setComponentEnabledSetting(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 79:
                    parcel.enforceInterface(DESCRIPTOR);
                    int componentEnabledSetting = getComponentEnabledSetting(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(componentEnabledSetting);
                    return true;
                case 80:
                    parcel.enforceInterface(DESCRIPTOR);
                    setApplicationEnabledSetting(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 81:
                    parcel.enforceInterface(DESCRIPTOR);
                    int applicationEnabledSetting = getApplicationEnabledSetting(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(applicationEnabledSetting);
                    return true;
                case 82:
                    parcel.enforceInterface(DESCRIPTOR);
                    logAppProcessStartIfNeeded(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 83:
                    parcel.enforceInterface(DESCRIPTOR);
                    flushPackageRestrictionsAsUser(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 84:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPackageStoppedState(parcel.readString(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 85:
                    parcel.enforceInterface(DESCRIPTOR);
                    freeStorageAndNotify(parcel.readString(), parcel.readLong(), parcel.readInt(), IPackageDataObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 86:
                    parcel.enforceInterface(DESCRIPTOR);
                    freeStorage(parcel.readString(), parcel.readLong(), parcel.readInt(), parcel.readInt() != 0 ? IntentSender.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 87:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteApplicationCacheFiles(parcel.readString(), IPackageDataObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 88:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteApplicationCacheFilesAsUser(parcel.readString(), parcel.readInt(), IPackageDataObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 89:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearApplicationUserData(parcel.readString(), IPackageDataObserver.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 90:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearApplicationProfileData(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 91:
                    parcel.enforceInterface(DESCRIPTOR);
                    getPackageSizeInfo(parcel.readString(), parcel.readInt(), IPackageStatsObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 92:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] systemSharedLibraryNames = getSystemSharedLibraryNames();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(systemSharedLibraryNames);
                    return true;
                case 93:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice systemAvailableFeatures = getSystemAvailableFeatures();
                    parcel2.writeNoException();
                    if (systemAvailableFeatures != null) {
                        parcel2.writeInt(1);
                        systemAvailableFeatures.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 94:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasSystemFeature = hasSystemFeature(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasSystemFeature ? 1 : 0);
                    return true;
                case 95:
                    parcel.enforceInterface(DESCRIPTOR);
                    enterSafeMode();
                    parcel2.writeNoException();
                    return true;
                case 96:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSafeMode = isSafeMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSafeMode ? 1 : 0);
                    return true;
                case 97:
                    parcel.enforceInterface(DESCRIPTOR);
                    systemReady();
                    parcel2.writeNoException();
                    return true;
                case 98:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasSystemUidErrors = hasSystemUidErrors();
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasSystemUidErrors ? 1 : 0);
                    return true;
                case 99:
                    parcel.enforceInterface(DESCRIPTOR);
                    performFstrimIfNeeded();
                    parcel2.writeNoException();
                    return true;
                case 100:
                    parcel.enforceInterface(DESCRIPTOR);
                    updatePackagesIfNeeded();
                    parcel2.writeNoException();
                    return true;
                case 101:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyPackageUse(parcel.readString(), parcel.readInt());
                    return true;
                case 102:
                    parcel.enforceInterface(DESCRIPTOR);
                    String string = parcel.readString();
                    int i3 = parcel.readInt();
                    final HashMap map = i3 >= 0 ? new HashMap() : null;
                    IntStream.range(0, i3).forEach(new IntConsumer() { // from class: android.content.pm.-$$Lambda$IPackageManager$Stub$ZVp6oEh-Gn_bn8lM7TgSgpaGriw
                        @Override // java.util.function.IntConsumer
                        public final void accept(int i4) {
                            Parcel parcel3 = parcel;
                            map.put(parcel3.readString(), parcel3.readString());
                        }
                    });
                    notifyDexLoad(string, map, parcel.readString());
                    return true;
                case 103:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerDexModule(parcel.readString(), parcel.readString(), parcel.readInt() != 0, IDexModuleRegisterCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 104:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zPerformDexOptMode = performDexOptMode(parcel.readString(), parcel.readInt() != 0, parcel.readString(), parcel.readInt() != 0, parcel.readInt() != 0, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zPerformDexOptMode ? 1 : 0);
                    return true;
                case 105:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zPerformDexOptSecondary = performDexOptSecondary(parcel.readString(), parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zPerformDexOptSecondary ? 1 : 0);
                    return true;
                case 106:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCompileLayouts = compileLayouts(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCompileLayouts ? 1 : 0);
                    return true;
                case 107:
                    parcel.enforceInterface(DESCRIPTOR);
                    dumpProfiles(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 108:
                    parcel.enforceInterface(DESCRIPTOR);
                    forceDexOpt(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 109:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRunBackgroundDexoptJob = runBackgroundDexoptJob(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRunBackgroundDexoptJob ? 1 : 0);
                    return true;
                case 110:
                    parcel.enforceInterface(DESCRIPTOR);
                    reconcileSecondaryDexFiles(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 111:
                    parcel.enforceInterface(DESCRIPTOR);
                    int moveStatus = getMoveStatus(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(moveStatus);
                    return true;
                case 112:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerMoveCallback(IPackageMoveObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 113:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterMoveCallback(IPackageMoveObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 114:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iMovePackage = movePackage(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iMovePackage);
                    return true;
                case 115:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iMovePrimaryStorage = movePrimaryStorage(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iMovePrimaryStorage);
                    return true;
                case 116:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean installLocation = setInstallLocation(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(installLocation ? 1 : 0);
                    return true;
                case 117:
                    parcel.enforceInterface(DESCRIPTOR);
                    int installLocation2 = getInstallLocation();
                    parcel2.writeNoException();
                    parcel2.writeInt(installLocation2);
                    return true;
                case 118:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iInstallExistingPackageAsUser = installExistingPackageAsUser(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(iInstallExistingPackageAsUser);
                    return true;
                case 119:
                    parcel.enforceInterface(DESCRIPTOR);
                    verifyPendingInstall(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 120:
                    parcel.enforceInterface(DESCRIPTOR);
                    extendVerificationTimeout(parcel.readInt(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 121:
                    parcel.enforceInterface(DESCRIPTOR);
                    verifyIntentFilter(parcel.readInt(), parcel.readInt(), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 122:
                    parcel.enforceInterface(DESCRIPTOR);
                    int intentVerificationStatus = getIntentVerificationStatus(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(intentVerificationStatus);
                    return true;
                case 123:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUpdateIntentVerificationStatus = updateIntentVerificationStatus(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zUpdateIntentVerificationStatus ? 1 : 0);
                    return true;
                case 124:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice intentFilterVerifications = getIntentFilterVerifications(parcel.readString());
                    parcel2.writeNoException();
                    if (intentFilterVerifications != null) {
                        parcel2.writeInt(1);
                        intentFilterVerifications.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 125:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice allIntentFilters = getAllIntentFilters(parcel.readString());
                    parcel2.writeNoException();
                    if (allIntentFilters != null) {
                        parcel2.writeInt(1);
                        allIntentFilters.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 126:
                    parcel.enforceInterface(DESCRIPTOR);
                    VerifierDeviceIdentity verifierDeviceIdentity = getVerifierDeviceIdentity();
                    parcel2.writeNoException();
                    if (verifierDeviceIdentity != null) {
                        parcel2.writeInt(1);
                        verifierDeviceIdentity.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 127:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsFirstBoot = isFirstBoot();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsFirstBoot ? 1 : 0);
                    return true;
                case 128:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsOnlyCoreApps = isOnlyCoreApps();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsOnlyCoreApps ? 1 : 0);
                    return true;
                case 129:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsDeviceUpgrading = isDeviceUpgrading();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsDeviceUpgrading ? 1 : 0);
                    return true;
                case 130:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsStorageLow = isStorageLow();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsStorageLow ? 1 : 0);
                    return true;
                case 131:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean applicationHiddenSettingAsUser = setApplicationHiddenSettingAsUser(parcel.readString(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(applicationHiddenSettingAsUser ? 1 : 0);
                    return true;
                case 132:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean applicationHiddenSettingAsUser2 = getApplicationHiddenSettingAsUser(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(applicationHiddenSettingAsUser2 ? 1 : 0);
                    return true;
                case 133:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSystemAppHiddenUntilInstalled(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 134:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean systemAppInstallState = setSystemAppInstallState(parcel.readString(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(systemAppInstallState ? 1 : 0);
                    return true;
                case 135:
                    parcel.enforceInterface(DESCRIPTOR);
                    IPackageInstaller packageInstaller = getPackageInstaller();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(packageInstaller != null ? packageInstaller.asBinder() : null);
                    return true;
                case 136:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean blockUninstallForUser = setBlockUninstallForUser(parcel.readString(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(blockUninstallForUser ? 1 : 0);
                    return true;
                case 137:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean blockUninstallForUser2 = getBlockUninstallForUser(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(blockUninstallForUser2 ? 1 : 0);
                    return true;
                case 138:
                    parcel.enforceInterface(DESCRIPTOR);
                    KeySet keySetByAlias = getKeySetByAlias(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (keySetByAlias != null) {
                        parcel2.writeInt(1);
                        keySetByAlias.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 139:
                    parcel.enforceInterface(DESCRIPTOR);
                    KeySet signingKeySet = getSigningKeySet(parcel.readString());
                    parcel2.writeNoException();
                    if (signingKeySet != null) {
                        parcel2.writeInt(1);
                        signingKeySet.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 140:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsPackageSignedByKeySet = isPackageSignedByKeySet(parcel.readString(), parcel.readInt() != 0 ? KeySet.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsPackageSignedByKeySet ? 1 : 0);
                    return true;
                case 141:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsPackageSignedByKeySetExactly = isPackageSignedByKeySetExactly(parcel.readString(), parcel.readInt() != 0 ? KeySet.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsPackageSignedByKeySetExactly ? 1 : 0);
                    return true;
                case 142:
                    parcel.enforceInterface(DESCRIPTOR);
                    String permissionControllerPackageName = getPermissionControllerPackageName();
                    parcel2.writeNoException();
                    parcel2.writeString(permissionControllerPackageName);
                    return true;
                case 143:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice instantApps = getInstantApps(parcel.readInt());
                    parcel2.writeNoException();
                    if (instantApps != null) {
                        parcel2.writeInt(1);
                        instantApps.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 144:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] instantAppCookie = getInstantAppCookie(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeByteArray(instantAppCookie);
                    return true;
                case 145:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean instantAppCookie2 = setInstantAppCookie(parcel.readString(), parcel.createByteArray(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(instantAppCookie2 ? 1 : 0);
                    return true;
                case 146:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bitmap instantAppIcon = getInstantAppIcon(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (instantAppIcon != null) {
                        parcel2.writeInt(1);
                        instantAppIcon.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 147:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsInstantApp = isInstantApp(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsInstantApp ? 1 : 0);
                    return true;
                case 148:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean requiredForSystemUser = setRequiredForSystemUser(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(requiredForSystemUser ? 1 : 0);
                    return true;
                case 149:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUpdateAvailable(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 150:
                    parcel.enforceInterface(DESCRIPTOR);
                    String servicesSystemSharedLibraryPackageName = getServicesSystemSharedLibraryPackageName();
                    parcel2.writeNoException();
                    parcel2.writeString(servicesSystemSharedLibraryPackageName);
                    return true;
                case 151:
                    parcel.enforceInterface(DESCRIPTOR);
                    String sharedSystemSharedLibraryPackageName = getSharedSystemSharedLibraryPackageName();
                    parcel2.writeNoException();
                    parcel2.writeString(sharedSystemSharedLibraryPackageName);
                    return true;
                case 152:
                    parcel.enforceInterface(DESCRIPTOR);
                    ChangedPackages changedPackages = getChangedPackages(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (changedPackages != null) {
                        parcel2.writeInt(1);
                        changedPackages.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 153:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsPackageDeviceAdminOnAnyUser = isPackageDeviceAdminOnAnyUser(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsPackageDeviceAdminOnAnyUser ? 1 : 0);
                    return true;
                case 154:
                    parcel.enforceInterface(DESCRIPTOR);
                    int installReason = getInstallReason(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(installReason);
                    return true;
                case 155:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice sharedLibraries = getSharedLibraries(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (sharedLibraries != null) {
                        parcel2.writeInt(1);
                        sharedLibraries.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 156:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice declaredSharedLibraries = getDeclaredSharedLibraries(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (declaredSharedLibraries != null) {
                        parcel2.writeInt(1);
                        declaredSharedLibraries.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 157:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCanRequestPackageInstalls = canRequestPackageInstalls(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCanRequestPackageInstalls ? 1 : 0);
                    return true;
                case 158:
                    parcel.enforceInterface(DESCRIPTOR);
                    deletePreloadsFileCache();
                    parcel2.writeNoException();
                    return true;
                case 159:
                    parcel.enforceInterface(DESCRIPTOR);
                    ComponentName instantAppResolverComponent = getInstantAppResolverComponent();
                    parcel2.writeNoException();
                    if (instantAppResolverComponent != null) {
                        parcel2.writeInt(1);
                        instantAppResolverComponent.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 160:
                    parcel.enforceInterface(DESCRIPTOR);
                    ComponentName instantAppResolverSettingsComponent = getInstantAppResolverSettingsComponent();
                    parcel2.writeNoException();
                    if (instantAppResolverSettingsComponent != null) {
                        parcel2.writeInt(1);
                        instantAppResolverSettingsComponent.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 161:
                    parcel.enforceInterface(DESCRIPTOR);
                    ComponentName instantAppInstallerComponent = getInstantAppInstallerComponent();
                    parcel2.writeNoException();
                    if (instantAppInstallerComponent != null) {
                        parcel2.writeInt(1);
                        instantAppInstallerComponent.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 162:
                    parcel.enforceInterface(DESCRIPTOR);
                    String instantAppAndroidId = getInstantAppAndroidId(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(instantAppAndroidId);
                    return true;
                case 163:
                    parcel.enforceInterface(DESCRIPTOR);
                    IArtManager artManager = getArtManager();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(artManager != null ? artManager.asBinder() : null);
                    return true;
                case 164:
                    parcel.enforceInterface(DESCRIPTOR);
                    setHarmfulAppWarning(parcel.readString(), parcel.readInt() != 0 ? TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 165:
                    parcel.enforceInterface(DESCRIPTOR);
                    CharSequence harmfulAppWarning = getHarmfulAppWarning(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (harmfulAppWarning != null) {
                        parcel2.writeInt(1);
                        TextUtils.writeToParcel(harmfulAppWarning, parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 166:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasSigningCertificate = hasSigningCertificate(parcel.readString(), parcel.createByteArray(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasSigningCertificate ? 1 : 0);
                    return true;
                case 167:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasUidSigningCertificate = hasUidSigningCertificate(parcel.readInt(), parcel.createByteArray(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasUidSigningCertificate ? 1 : 0);
                    return true;
                case 168:
                    parcel.enforceInterface(DESCRIPTOR);
                    String defaultTextClassifierPackageName = getDefaultTextClassifierPackageName();
                    parcel2.writeNoException();
                    parcel2.writeString(defaultTextClassifierPackageName);
                    return true;
                case 169:
                    parcel.enforceInterface(DESCRIPTOR);
                    String systemTextClassifierPackageName = getSystemTextClassifierPackageName();
                    parcel2.writeNoException();
                    parcel2.writeString(systemTextClassifierPackageName);
                    return true;
                case 170:
                    parcel.enforceInterface(DESCRIPTOR);
                    String attentionServicePackageName = getAttentionServicePackageName();
                    parcel2.writeNoException();
                    parcel2.writeString(attentionServicePackageName);
                    return true;
                case 171:
                    parcel.enforceInterface(DESCRIPTOR);
                    String wellbeingPackageName = getWellbeingPackageName();
                    parcel2.writeNoException();
                    parcel2.writeString(wellbeingPackageName);
                    return true;
                case 172:
                    parcel.enforceInterface(DESCRIPTOR);
                    String appPredictionServicePackageName = getAppPredictionServicePackageName();
                    parcel2.writeNoException();
                    parcel2.writeString(appPredictionServicePackageName);
                    return true;
                case 173:
                    parcel.enforceInterface(DESCRIPTOR);
                    String systemCaptionsServicePackageName = getSystemCaptionsServicePackageName();
                    parcel2.writeNoException();
                    parcel2.writeString(systemCaptionsServicePackageName);
                    return true;
                case 174:
                    parcel.enforceInterface(DESCRIPTOR);
                    String setupWizardPackageName = getSetupWizardPackageName();
                    parcel2.writeNoException();
                    parcel2.writeString(setupWizardPackageName);
                    return true;
                case 175:
                    parcel.enforceInterface(DESCRIPTOR);
                    String incidentReportApproverPackageName = getIncidentReportApproverPackageName();
                    parcel2.writeNoException();
                    parcel2.writeString(incidentReportApproverPackageName);
                    return true;
                case 176:
                    parcel.enforceInterface(DESCRIPTOR);
                    String contentCaptureServicePackageName = getContentCaptureServicePackageName();
                    parcel2.writeNoException();
                    parcel2.writeString(contentCaptureServicePackageName);
                    return true;
                case 177:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsPackageStateProtected = isPackageStateProtected(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsPackageStateProtected ? 1 : 0);
                    return true;
                case 178:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendDeviceCustomizationReadyBroadcast();
                    parcel2.writeNoException();
                    return true;
                case 179:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<ModuleInfo> installedModules = getInstalledModules(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(installedModules);
                    return true;
                case 180:
                    parcel.enforceInterface(DESCRIPTOR);
                    ModuleInfo moduleInfo = getModuleInfo(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (moduleInfo != null) {
                        parcel2.writeInt(1);
                        moduleInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 181:
                    parcel.enforceInterface(DESCRIPTOR);
                    int runtimePermissionsVersion = getRuntimePermissionsVersion(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(runtimePermissionsVersion);
                    return true;
                case 182:
                    parcel.enforceInterface(DESCRIPTOR);
                    setRuntimePermissionsVersion(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 183:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyPackagesReplacedReceived(parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                case 184:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] appOpPermissionPackages = getAppOpPermissionPackages(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(appOpPermissionPackages);
                    return true;
                case 185:
                    parcel.enforceInterface(DESCRIPTOR);
                    PermissionGroupInfo permissionGroupInfo = getPermissionGroupInfo(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (permissionGroupInfo != null) {
                        parcel2.writeInt(1);
                        permissionGroupInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 186:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAddPermission = addPermission(parcel.readInt() != 0 ? PermissionInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddPermission ? 1 : 0);
                    return true;
                case 187:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAddPermissionAsync = addPermissionAsync(parcel.readInt() != 0 ? PermissionInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddPermissionAsync ? 1 : 0);
                    return true;
                case 188:
                    parcel.enforceInterface(DESCRIPTOR);
                    removePermission(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 189:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCheckPermission = checkPermission(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iCheckPermission);
                    return true;
                case 190:
                    parcel.enforceInterface(DESCRIPTOR);
                    grantRuntimePermission(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 191:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCheckUidPermission = checkUidPermission(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iCheckUidPermission);
                    return true;
                case 192:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMimeGroup(parcel.readString(), parcel.readString(), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 193:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> mimeGroup = getMimeGroup(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStringList(mimeGroup);
                    return true;
                case 194:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsAutoRevokeWhitelisted = isAutoRevokeWhitelisted(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsAutoRevokeWhitelisted ? 1 : 0);
                    return true;
                case 195:
                    parcel.enforceInterface(DESCRIPTOR);
                    grantImplicitAccess(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        static class Proxy implements IPackageManager {
            public static IPackageManager sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.content.pm.IPackageManager
            public void checkPackageStartable(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().checkPackageStartable(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean isPackageAvailable(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPackageAvailable(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public PackageInfo getPackageInfo(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPackageInfo(str, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? PackageInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public PackageInfo getPackageInfoVersioned(VersionedPackage versionedPackage, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (versionedPackage != null) {
                        parcelObtain.writeInt(1);
                        versionedPackage.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPackageInfoVersioned(versionedPackage, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? PackageInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public int getPackageUid(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPackageUid(str, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public int[] getPackageGids(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPackageGids(str, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String[] currentToCanonicalPackageNames(String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().currentToCanonicalPackageNames(strArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String[] canonicalToCurrentPackageNames(String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canonicalToCurrentPackageNames(strArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ApplicationInfo getApplicationInfo(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getApplicationInfo(str, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ApplicationInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ActivityInfo getActivityInfo(ComponentName componentName, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActivityInfo(componentName, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ActivityInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean activitySupportsIntent(ComponentName componentName, Intent intent, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().activitySupportsIntent(componentName, intent, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ActivityInfo getReceiverInfo(ComponentName componentName, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getReceiverInfo(componentName, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ActivityInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ServiceInfo getServiceInfo(ComponentName componentName, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getServiceInfo(componentName, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ServiceInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ProviderInfo getProviderInfo(ComponentName componentName, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProviderInfo(componentName, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ProviderInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean isProtectedBroadcast(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isProtectedBroadcast(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public int checkSignatures(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkSignatures(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public int checkUidSignatures(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkUidSignatures(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public List<String> getAllPackages() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllPackages();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String[] getPackagesForUid(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPackagesForUid(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String getNameForUid(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNameForUid(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String[] getNamesForUids(int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNamesForUids(iArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public int getUidForSharedUser(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUidForSharedUser(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public int getFlagsForUid(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFlagsForUid(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public int getPrivateFlagsForUid(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPrivateFlagsForUid(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean isUidPrivileged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUidPrivileged(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ResolveInfo resolveIntent(Intent intent, String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().resolveIntent(intent, str, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ResolveInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ResolveInfo findPersistentPreferredActivity(Intent intent, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().findPersistentPreferredActivity(intent, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ResolveInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean canForwardTo(Intent intent, String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canForwardTo(intent, str, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ParceledListSlice queryIntentActivities(Intent intent, String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryIntentActivities(intent, str, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ParceledListSlice queryIntentActivityOptions(ComponentName componentName, Intent[] intentArr, String[] strArr, Intent intent, String str, int i, int i2) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeTypedArray(intentArr, 0);
                    parcelObtain.writeStringArray(strArr);
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        ParceledListSlice parceledListSliceQueryIntentActivityOptions = Stub.getDefaultImpl().queryIntentActivityOptions(componentName, intentArr, strArr, intent, str, i, i2);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return parceledListSliceQueryIntentActivityOptions;
                    }
                    parcelObtain2.readException();
                    ParceledListSlice parceledListSliceCreateFromParcel = parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return parceledListSliceCreateFromParcel;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.content.pm.IPackageManager
            public ParceledListSlice queryIntentReceivers(Intent intent, String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryIntentReceivers(intent, str, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ResolveInfo resolveService(Intent intent, String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().resolveService(intent, str, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ResolveInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ParceledListSlice queryIntentServices(Intent intent, String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryIntentServices(intent, str, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ParceledListSlice queryIntentContentProviders(Intent intent, String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryIntentContentProviders(intent, str, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ParceledListSlice getInstalledPackages(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstalledPackages(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ParceledListSlice getPackagesHoldingPermissions(String[] strArr, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPackagesHoldingPermissions(strArr, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ParceledListSlice getInstalledApplications(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstalledApplications(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ParceledListSlice getPersistentApplications(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPersistentApplications(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ProviderInfo resolveContentProvider(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().resolveContentProvider(str, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ProviderInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void querySyncProviders(List<String> list, List<ProviderInfo> list2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    parcelObtain.writeTypedList(list2);
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().querySyncProviders(list, list2);
                        return;
                    }
                    parcelObtain2.readException();
                    parcelObtain2.readStringList(list);
                    parcelObtain2.readTypedList(list2, ProviderInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ParceledListSlice queryContentProviders(String str, int i, int i2, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryContentProviders(str, i, i2, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public InstrumentationInfo getInstrumentationInfo(ComponentName componentName, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstrumentationInfo(componentName, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? InstrumentationInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ParceledListSlice queryInstrumentation(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(43, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryInstrumentation(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void finishPackageInstall(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(44, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().finishPackageInstall(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void setInstallerPackageName(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(45, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInstallerPackageName(str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void setApplicationCategoryHint(String str, int i, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(46, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setApplicationCategoryHint(str, i, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void deletePackageAsUser(String str, int i, IPackageDeleteObserver iPackageDeleteObserver, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iPackageDeleteObserver != null ? iPackageDeleteObserver.asBinder() : null);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(47, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deletePackageAsUser(str, i, iPackageDeleteObserver, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void deletePackageVersioned(VersionedPackage versionedPackage, IPackageDeleteObserver2 iPackageDeleteObserver2, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (versionedPackage != null) {
                        parcelObtain.writeInt(1);
                        versionedPackage.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iPackageDeleteObserver2 != null ? iPackageDeleteObserver2.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(48, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deletePackageVersioned(versionedPackage, iPackageDeleteObserver2, i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void deleteExistingPackageAsUser(VersionedPackage versionedPackage, IPackageDeleteObserver2 iPackageDeleteObserver2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (versionedPackage != null) {
                        parcelObtain.writeInt(1);
                        versionedPackage.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iPackageDeleteObserver2 != null ? iPackageDeleteObserver2.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(49, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteExistingPackageAsUser(versionedPackage, iPackageDeleteObserver2, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String getInstallerPackageName(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(50, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstallerPackageName(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public InstallSourceInfo getInstallSourceInfo(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(51, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstallSourceInfo(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? InstallSourceInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void resetApplicationPreferences(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(52, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resetApplicationPreferences(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ResolveInfo getLastChosenActivity(Intent intent, String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(53, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLastChosenActivity(intent, str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ResolveInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void setLastChosenActivity(Intent intent, String str, int i, IntentFilter intentFilter, int i2, ComponentName componentName) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (intentFilter != null) {
                        parcelObtain.writeInt(1);
                        intentFilter.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(54, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLastChosenActivity(intent, str, i, intentFilter, i2, componentName);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.content.pm.IPackageManager
            public void addPreferredActivity(IntentFilter intentFilter, int i, ComponentName[] componentNameArr, ComponentName componentName, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intentFilter != null) {
                        parcelObtain.writeInt(1);
                        intentFilter.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedArray(componentNameArr, 0);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(55, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addPreferredActivity(intentFilter, i, componentNameArr, componentName, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void replacePreferredActivity(IntentFilter intentFilter, int i, ComponentName[] componentNameArr, ComponentName componentName, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intentFilter != null) {
                        parcelObtain.writeInt(1);
                        intentFilter.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedArray(componentNameArr, 0);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(56, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().replacePreferredActivity(intentFilter, i, componentNameArr, componentName, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void clearPackagePreferredActivities(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(57, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearPackagePreferredActivities(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public int getPreferredActivities(List<IntentFilter> list, List<ComponentName> list2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(58, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPreferredActivities(list, list2, str);
                    }
                    parcelObtain2.readException();
                    int i = parcelObtain2.readInt();
                    parcelObtain2.readTypedList(list, IntentFilter.CREATOR);
                    parcelObtain2.readTypedList(list2, ComponentName.CREATOR);
                    return i;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void addPersistentPreferredActivity(IntentFilter intentFilter, ComponentName componentName, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intentFilter != null) {
                        parcelObtain.writeInt(1);
                        intentFilter.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(59, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addPersistentPreferredActivity(intentFilter, componentName, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void clearPackagePersistentPreferredActivities(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(60, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearPackagePersistentPreferredActivities(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void addCrossProfileIntentFilter(IntentFilter intentFilter, String str, int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intentFilter != null) {
                        parcelObtain.writeInt(1);
                        intentFilter.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(61, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addCrossProfileIntentFilter(intentFilter, str, i, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void clearCrossProfileIntentFilters(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(62, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearCrossProfileIntentFilters(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String[] setDistractingPackageRestrictionsAsUser(String[] strArr, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(63, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDistractingPackageRestrictionsAsUser(strArr, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String[] setPackagesSuspendedAsUser(String[] strArr, boolean z, PersistableBundle persistableBundle, PersistableBundle persistableBundle2, SuspendDialogInfo suspendDialogInfo, String str, int i) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (persistableBundle != null) {
                        parcelObtain.writeInt(1);
                        persistableBundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (persistableBundle2 != null) {
                        parcelObtain.writeInt(1);
                        persistableBundle2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (suspendDialogInfo != null) {
                        parcelObtain.writeInt(1);
                        suspendDialogInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(64, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        String[] packagesSuspendedAsUser = Stub.getDefaultImpl().setPackagesSuspendedAsUser(strArr, z, persistableBundle, persistableBundle2, suspendDialogInfo, str, i);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return packagesSuspendedAsUser;
                    }
                    parcelObtain2.readException();
                    String[] strArrCreateStringArray = parcelObtain2.createStringArray();
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return strArrCreateStringArray;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.content.pm.IPackageManager
            public String[] getUnsuspendablePackagesForUser(String[] strArr, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(65, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUnsuspendablePackagesForUser(strArr, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean isPackageSuspendedForUser(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(66, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPackageSuspendedForUser(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public Bundle getSuspendedPackageAppExtras(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(67, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSuspendedPackageAppExtras(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public byte[] getPreferredActivityBackup(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(68, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPreferredActivityBackup(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void restorePreferredActivities(byte[] bArr, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(69, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().restorePreferredActivities(bArr, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public byte[] getDefaultAppsBackup(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(70, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultAppsBackup(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void restoreDefaultApps(byte[] bArr, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(71, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().restoreDefaultApps(bArr, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public byte[] getIntentFilterVerificationBackup(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(72, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIntentFilterVerificationBackup(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void restoreIntentFilterVerification(byte[] bArr, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(73, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().restoreIntentFilterVerification(bArr, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ComponentName getHomeActivities(List<ResolveInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(74, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHomeActivities(list);
                    }
                    parcelObtain2.readException();
                    ComponentName componentNameCreateFromParcel = parcelObtain2.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcelObtain2) : null;
                    parcelObtain2.readTypedList(list, ResolveInfo.CREATOR);
                    return componentNameCreateFromParcel;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void setHomeActivity(ComponentName componentName, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(75, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setHomeActivity(componentName, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void overrideLabelAndIcon(ComponentName componentName, String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(76, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().overrideLabelAndIcon(componentName, str, i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void restoreLabelAndIcon(ComponentName componentName, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(77, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().restoreLabelAndIcon(componentName, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void setComponentEnabledSetting(ComponentName componentName, int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(78, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setComponentEnabledSetting(componentName, i, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public int getComponentEnabledSetting(ComponentName componentName, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(79, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getComponentEnabledSetting(componentName, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void setApplicationEnabledSetting(String str, int i, int i2, int i3, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(80, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setApplicationEnabledSetting(str, i, i2, i3, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public int getApplicationEnabledSetting(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(81, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getApplicationEnabledSetting(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void logAppProcessStartIfNeeded(String str, int i, String str2, String str3, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(82, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().logAppProcessStartIfNeeded(str, i, str2, str3, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void flushPackageRestrictionsAsUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(83, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().flushPackageRestrictionsAsUser(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void setPackageStoppedState(String str, boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(84, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPackageStoppedState(str, z, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void freeStorageAndNotify(String str, long j, int i, IPackageDataObserver iPackageDataObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iPackageDataObserver != null ? iPackageDataObserver.asBinder() : null);
                    if (!this.mRemote.transact(85, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().freeStorageAndNotify(str, j, i, iPackageDataObserver);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void freeStorage(String str, long j, int i, IntentSender intentSender) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    if (intentSender != null) {
                        parcelObtain.writeInt(1);
                        intentSender.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(86, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().freeStorage(str, j, i, intentSender);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void deleteApplicationCacheFiles(String str, IPackageDataObserver iPackageDataObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iPackageDataObserver != null ? iPackageDataObserver.asBinder() : null);
                    if (!this.mRemote.transact(87, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteApplicationCacheFiles(str, iPackageDataObserver);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void deleteApplicationCacheFilesAsUser(String str, int i, IPackageDataObserver iPackageDataObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iPackageDataObserver != null ? iPackageDataObserver.asBinder() : null);
                    if (!this.mRemote.transact(88, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteApplicationCacheFilesAsUser(str, i, iPackageDataObserver);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void clearApplicationUserData(String str, IPackageDataObserver iPackageDataObserver, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iPackageDataObserver != null ? iPackageDataObserver.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(89, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearApplicationUserData(str, iPackageDataObserver, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void clearApplicationProfileData(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(90, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearApplicationProfileData(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void getPackageSizeInfo(String str, int i, IPackageStatsObserver iPackageStatsObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iPackageStatsObserver != null ? iPackageStatsObserver.asBinder() : null);
                    if (!this.mRemote.transact(91, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getPackageSizeInfo(str, i, iPackageStatsObserver);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String[] getSystemSharedLibraryNames() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(92, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemSharedLibraryNames();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ParceledListSlice getSystemAvailableFeatures() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(93, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemAvailableFeatures();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean hasSystemFeature(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(94, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasSystemFeature(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void enterSafeMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(95, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enterSafeMode();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean isSafeMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(96, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSafeMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void systemReady() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(97, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().systemReady();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean hasSystemUidErrors() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(98, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasSystemUidErrors();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void performFstrimIfNeeded() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(99, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().performFstrimIfNeeded();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void updatePackagesIfNeeded() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(100, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updatePackagesIfNeeded();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void notifyPackageUse(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(101, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyPackageUse(str, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void notifyDexLoad(String str, Map<String, String> map, String str2) throws RemoteException {
                final Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (map == null) {
                        parcelObtain.writeInt(-1);
                    } else {
                        parcelObtain.writeInt(map.size());
                        map.forEach(new BiConsumer() { // from class: android.content.pm.-$$Lambda$IPackageManager$Stub$Proxy$X2I1qlX4SiKMZSjDTNzS_nTibbo
                            @Override // java.util.function.BiConsumer
                            public final void accept(Object obj, Object obj2) {
                                IPackageManager.Stub.Proxy.lambda$notifyDexLoad$0(parcelObtain, (String) obj, (String) obj2);
                            }
                        });
                    }
                    parcelObtain.writeString(str2);
                    if (this.mRemote.transact(102, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyDexLoad(str, map, str2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            static /* synthetic */ void lambda$notifyDexLoad$0(Parcel parcel, String str, String str2) {
                parcel.writeString(str);
                parcel.writeString(str2);
            }

            @Override // android.content.pm.IPackageManager
            public void registerDexModule(String str, String str2, boolean z, IDexModuleRegisterCallback iDexModuleRegisterCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeStrongBinder(iDexModuleRegisterCallback != null ? iDexModuleRegisterCallback.asBinder() : null);
                    if (this.mRemote.transact(103, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().registerDexModule(str, str2, z, iDexModuleRegisterCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean performDexOptMode(String str, boolean z, String str2, boolean z2, boolean z3, String str3) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    parcelObtain.writeInt(z3 ? 1 : 0);
                    parcelObtain.writeString(str3);
                    try {
                        if (!this.mRemote.transact(104, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            boolean zPerformDexOptMode = Stub.getDefaultImpl().performDexOptMode(str, z, str2, z2, z3, str3);
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                            return zPerformDexOptMode;
                        }
                        parcelObtain2.readException();
                        boolean z4 = parcelObtain2.readInt() != 0;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return z4;
                    } catch (Throwable th) {
                        th = th;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean performDexOptSecondary(String str, String str2, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(105, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().performDexOptSecondary(str, str2, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean compileLayouts(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(106, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().compileLayouts(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void dumpProfiles(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(107, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dumpProfiles(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void forceDexOpt(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(108, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().forceDexOpt(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean runBackgroundDexoptJob(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(109, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().runBackgroundDexoptJob(list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void reconcileSecondaryDexFiles(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(110, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reconcileSecondaryDexFiles(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public int getMoveStatus(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(111, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMoveStatus(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void registerMoveCallback(IPackageMoveObserver iPackageMoveObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iPackageMoveObserver != null ? iPackageMoveObserver.asBinder() : null);
                    if (!this.mRemote.transact(112, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerMoveCallback(iPackageMoveObserver);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void unregisterMoveCallback(IPackageMoveObserver iPackageMoveObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iPackageMoveObserver != null ? iPackageMoveObserver.asBinder() : null);
                    if (!this.mRemote.transact(113, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterMoveCallback(iPackageMoveObserver);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public int movePackage(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(114, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().movePackage(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public int movePrimaryStorage(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(115, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().movePrimaryStorage(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean setInstallLocation(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(116, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setInstallLocation(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public int getInstallLocation() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(117, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstallLocation();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public int installExistingPackageAsUser(String str, int i, int i2, int i3, List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(118, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().installExistingPackageAsUser(str, i, i2, i3, list);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void verifyPendingInstall(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(119, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().verifyPendingInstall(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void extendVerificationTimeout(int i, int i2, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(120, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().extendVerificationTimeout(i, i2, j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void verifyIntentFilter(int i, int i2, List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(121, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().verifyIntentFilter(i, i2, list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public int getIntentVerificationStatus(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(122, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIntentVerificationStatus(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean updateIntentVerificationStatus(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(123, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateIntentVerificationStatus(str, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ParceledListSlice getIntentFilterVerifications(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(124, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIntentFilterVerifications(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ParceledListSlice getAllIntentFilters(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(125, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllIntentFilters(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public VerifierDeviceIdentity getVerifierDeviceIdentity() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(126, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVerifierDeviceIdentity();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? VerifierDeviceIdentity.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean isFirstBoot() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(127, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isFirstBoot();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean isOnlyCoreApps() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(128, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isOnlyCoreApps();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean isDeviceUpgrading() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(129, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDeviceUpgrading();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean isStorageLow() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(130, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isStorageLow();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean setApplicationHiddenSettingAsUser(String str, boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(131, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setApplicationHiddenSettingAsUser(str, z, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean getApplicationHiddenSettingAsUser(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(132, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getApplicationHiddenSettingAsUser(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void setSystemAppHiddenUntilInstalled(String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(133, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSystemAppHiddenUntilInstalled(str, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean setSystemAppInstallState(String str, boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(134, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSystemAppInstallState(str, z, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public IPackageInstaller getPackageInstaller() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(135, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPackageInstaller();
                    }
                    parcelObtain2.readException();
                    return IPackageInstaller.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean setBlockUninstallForUser(String str, boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(136, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setBlockUninstallForUser(str, z, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean getBlockUninstallForUser(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(137, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBlockUninstallForUser(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public KeySet getKeySetByAlias(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(138, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getKeySetByAlias(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? KeySet.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public KeySet getSigningKeySet(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(139, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSigningKeySet(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? KeySet.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean isPackageSignedByKeySet(String str, KeySet keySet) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (keySet != null) {
                        parcelObtain.writeInt(1);
                        keySet.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(140, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPackageSignedByKeySet(str, keySet);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean isPackageSignedByKeySetExactly(String str, KeySet keySet) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (keySet != null) {
                        parcelObtain.writeInt(1);
                        keySet.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(141, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPackageSignedByKeySetExactly(str, keySet);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String getPermissionControllerPackageName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(142, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPermissionControllerPackageName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ParceledListSlice getInstantApps(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(143, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstantApps(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public byte[] getInstantAppCookie(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(144, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstantAppCookie(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean setInstantAppCookie(String str, byte[] bArr, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(145, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setInstantAppCookie(str, bArr, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public Bitmap getInstantAppIcon(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(146, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstantAppIcon(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Bitmap.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean isInstantApp(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(147, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInstantApp(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean setRequiredForSystemUser(String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(148, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setRequiredForSystemUser(str, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void setUpdateAvailable(String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(149, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUpdateAvailable(str, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String getServicesSystemSharedLibraryPackageName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(150, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getServicesSystemSharedLibraryPackageName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String getSharedSystemSharedLibraryPackageName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(151, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSharedSystemSharedLibraryPackageName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ChangedPackages getChangedPackages(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(152, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getChangedPackages(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ChangedPackages.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean isPackageDeviceAdminOnAnyUser(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(153, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPackageDeviceAdminOnAnyUser(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public int getInstallReason(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(154, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstallReason(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ParceledListSlice getSharedLibraries(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(155, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSharedLibraries(str, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ParceledListSlice getDeclaredSharedLibraries(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(156, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeclaredSharedLibraries(str, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean canRequestPackageInstalls(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(157, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canRequestPackageInstalls(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void deletePreloadsFileCache() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(158, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deletePreloadsFileCache();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ComponentName getInstantAppResolverComponent() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(159, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstantAppResolverComponent();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ComponentName getInstantAppResolverSettingsComponent() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(160, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstantAppResolverSettingsComponent();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ComponentName getInstantAppInstallerComponent() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(161, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstantAppInstallerComponent();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String getInstantAppAndroidId(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(162, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstantAppAndroidId(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public IArtManager getArtManager() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(163, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getArtManager();
                    }
                    parcelObtain2.readException();
                    return IArtManager.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void setHarmfulAppWarning(String str, CharSequence charSequence, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (charSequence != null) {
                        parcelObtain.writeInt(1);
                        TextUtils.writeToParcel(charSequence, parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(164, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setHarmfulAppWarning(str, charSequence, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public CharSequence getHarmfulAppWarning(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(165, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHarmfulAppWarning(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean hasSigningCertificate(String str, byte[] bArr, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(166, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasSigningCertificate(str, bArr, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean hasUidSigningCertificate(int i, byte[] bArr, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(167, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasUidSigningCertificate(i, bArr, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String getDefaultTextClassifierPackageName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(168, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultTextClassifierPackageName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String getSystemTextClassifierPackageName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(169, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemTextClassifierPackageName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String getAttentionServicePackageName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(170, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAttentionServicePackageName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String getWellbeingPackageName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(171, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWellbeingPackageName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String getAppPredictionServicePackageName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(172, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppPredictionServicePackageName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String getSystemCaptionsServicePackageName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(173, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemCaptionsServicePackageName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String getSetupWizardPackageName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(174, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSetupWizardPackageName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String getIncidentReportApproverPackageName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(175, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIncidentReportApproverPackageName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String getContentCaptureServicePackageName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(176, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getContentCaptureServicePackageName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean isPackageStateProtected(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(177, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPackageStateProtected(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void sendDeviceCustomizationReadyBroadcast() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(178, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendDeviceCustomizationReadyBroadcast();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public List<ModuleInfo> getInstalledModules(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(179, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstalledModules(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(ModuleInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public ModuleInfo getModuleInfo(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(180, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getModuleInfo(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ModuleInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public int getRuntimePermissionsVersion(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(181, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRuntimePermissionsVersion(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void setRuntimePermissionsVersion(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(182, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRuntimePermissionsVersion(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void notifyPackagesReplacedReceived(String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(183, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyPackagesReplacedReceived(strArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public String[] getAppOpPermissionPackages(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(184, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppOpPermissionPackages(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public PermissionGroupInfo getPermissionGroupInfo(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(185, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPermissionGroupInfo(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? PermissionGroupInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean addPermission(PermissionInfo permissionInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (permissionInfo != null) {
                        parcelObtain.writeInt(1);
                        permissionInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(186, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addPermission(permissionInfo);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean addPermissionAsync(PermissionInfo permissionInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (permissionInfo != null) {
                        parcelObtain.writeInt(1);
                        permissionInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(187, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addPermissionAsync(permissionInfo);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void removePermission(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(188, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removePermission(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public int checkPermission(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(189, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkPermission(str, str2, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void grantRuntimePermission(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(190, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().grantRuntimePermission(str, str2, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public int checkUidPermission(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(191, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkUidPermission(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void setMimeGroup(String str, String str2, List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(192, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMimeGroup(str, str2, list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public List<String> getMimeGroup(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(193, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMimeGroup(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public boolean isAutoRevokeWhitelisted(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(194, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAutoRevokeWhitelisted(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManager
            public void grantImplicitAccess(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(195, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().grantImplicitAccess(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPackageManager iPackageManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iPackageManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPackageManager;
            return true;
        }

        public static IPackageManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
