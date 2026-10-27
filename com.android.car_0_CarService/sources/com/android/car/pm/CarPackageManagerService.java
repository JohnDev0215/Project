package com.android.car.pm;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.car.content.pm.AppBlockingPackageInfo;
import android.car.content.pm.CarAppBlockingPolicy;
import android.car.content.pm.ICarPackageManager;
import android.car.drivingstate.CarUxRestrictions;
import android.car.drivingstate.ICarUxRestrictionsChangeListener;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.hardware.display.DisplayManager;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.UserHandle;
import android.text.format.DateFormat;
import android.util.ArraySet;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.view.Display;
import android.view.DisplayAddress;
import com.android.car.CarLog;
import com.android.car.CarServiceBase;
import com.android.car.CarServiceUtils;
import com.android.car.CarUxRestrictionsManagerService;
import com.android.car.Manifest;
import com.android.car.R;
import com.android.car.SystemActivityMonitoringService;
import com.android.settingslib.accessibility.AccessibilityUtils;
import com.google.android.collect.Sets;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class CarPackageManagerService extends ICarPackageManager.Stub implements CarServiceBase {
    public static final String BLOCKING_INTENT_EXTRA_BLOCKED_ACTIVITY_NAME = "blocked_activity";
    public static final String BLOCKING_INTENT_EXTRA_BLOCKED_TASK_ID = "blocked_task_id";
    public static final String BLOCKING_INTENT_EXTRA_DISPLAY_ID = "display_id";
    public static final String BLOCKING_INTENT_EXTRA_IS_ROOT_ACTIVITY_DO = "is_root_activity_do";
    public static final String BLOCKING_INTENT_EXTRA_ROOT_ACTIVITY_NAME = "root_activity_name";
    private static final boolean DBG = false;
    private static final boolean DBG_POLICY_CHECK = false;
    private static final boolean DBG_POLICY_ENFORCEMENT = false;
    private static final boolean DBG_POLICY_SET = false;
    private static final int LOG_SIZE = 20;
    private static final String PACKAGE_ACTIVITY_DELIMITER = "/";
    private static final String PACKAGE_DELIMITER = ",";
    private final ComponentName mActivityBlockingActivity;
    private final ActivityLaunchListener mActivityLaunchListener;
    private final ActivityManager mActivityManager;
    private final List<String> mAllowedAppInstallSources;
    private final CarUxRestrictionsManagerService mCarUxRestrictionsService;
    private String mConfiguredAllowlist;
    private Map<String, Set<String>> mConfiguredAllowlistMap;
    private String mConfiguredBlocklist;
    private Map<String, Set<String>> mConfiguredBlocklistMap;
    private String mConfiguredSystemAllowlist;
    private final Context mContext;
    private final DisplayManager mDisplayManager;
    private boolean mEnableActivityBlocking;
    private final PackageHandler mHandler;
    private final PackageManager mPackageManager;
    private final PackageParsingEventReceiver mPackageParsingEventReceiver;
    private LinkedList<AppBlockingPolicyProxy> mProxies;
    private final SystemActivityMonitoringService mSystemActivityMonitoringService;
    private final VendorServiceController mVendorServiceController;
    private final HandlerThread mHandlerThread = CarServiceUtils.getHandlerThread(getClass().getSimpleName());
    private final Object mLock = new Object();
    private final LinkedList<String> mBlockedActivityLogs = new LinkedList<>();
    private final HashMap<String, ClientPolicy> mClientPolicies = new HashMap<>();
    private HashMap<String, AppBlockingPackageInfoWrapper> mActivityAllowlistMap = new HashMap<>();
    private HashSet<String> mActivityDenylistPackages = new HashSet<>();
    private final LinkedList<CarAppBlockingPolicy> mWaitingPolicies = new LinkedList<>();
    private final SparseArray<UxRestrictionsListener> mUxRestrictionsListeners = new SparseArray<>();
    private final Set<String> mPackageManagerActions = Sets.newArraySet(new String[]{"android.intent.action.PACKAGE_ADDED", "android.intent.action.PACKAGE_CHANGED", "android.intent.action.PACKAGE_REMOVED", "android.intent.action.PACKAGE_REPLACED"});

    public CarPackageManagerService(Context context, CarUxRestrictionsManagerService uxRestrictionsService, SystemActivityMonitoringService systemActivityMonitoringService) {
        this.mHandler = new PackageHandler(this.mHandlerThread.getLooper(), this);
        this.mActivityLaunchListener = new ActivityLaunchListener();
        this.mPackageParsingEventReceiver = new PackageParsingEventReceiver();
        this.mContext = context;
        this.mCarUxRestrictionsService = uxRestrictionsService;
        this.mSystemActivityMonitoringService = systemActivityMonitoringService;
        this.mPackageManager = context.getPackageManager();
        this.mActivityManager = (ActivityManager) this.mContext.getSystemService(ActivityManager.class);
        this.mDisplayManager = (DisplayManager) this.mContext.getSystemService(DisplayManager.class);
        Resources res = context.getResources();
        this.mEnableActivityBlocking = res.getBoolean(R.bool.enableActivityBlockingForSafety);
        String blockingActivity = res.getString(R.string.activityBlockingActivity);
        this.mActivityBlockingActivity = ComponentName.unflattenFromString(blockingActivity);
        this.mAllowedAppInstallSources = Arrays.asList(res.getStringArray(R.array.allowedAppInstallSources));
        this.mVendorServiceController = new VendorServiceController(this.mContext, this.mHandler.getLooper());
    }

    public void setAppBlockingPolicy(String packageName, CarAppBlockingPolicy policy, int flags) {
        doSetAppBlockingPolicy(packageName, policy, flags);
    }

    public void restartTask(int taskId) {
        if (this.mContext.checkCallingOrSelfPermission("android.permission.REAL_GET_TASKS") != 0) {
            throw new SecurityException("requires permission android.permission.REAL_GET_TASKS");
        }
        this.mSystemActivityMonitoringService.restartTask(taskId);
    }

    private void doSetAppBlockingPolicy(String packageName, CarAppBlockingPolicy policy, int flags) {
        if (this.mContext.checkCallingOrSelfPermission(Manifest.permission.CONTROL_APP_BLOCKING) != 0) {
            throw new SecurityException("requires permission android.car.permission.CONTROL_APP_BLOCKING");
        }
        CarServiceUtils.assertPackageName(this.mContext, packageName);
        if (policy == null) {
            throw new IllegalArgumentException("policy cannot be null");
        }
        if ((flags & 2) != 0 && (flags & 4) != 0) {
            throw new IllegalArgumentException("Cannot set both FLAG_SET_POLICY_ADD and FLAG_SET_POLICY_REMOVE flag");
        }
        synchronized (this.mLock) {
            if ((flags & 1) != 0) {
                this.mWaitingPolicies.add(policy);
            }
        }
        this.mHandler.requestUpdatingPolicy(packageName, policy, flags);
        if ((flags & 1) != 0) {
            synchronized (this.mLock) {
                while (this.mWaitingPolicies.contains(policy)) {
                    try {
                        try {
                            this.mLock.wait();
                        } catch (InterruptedException e) {
                            throw new IllegalStateException("Interrupted while waiting for policy completion", e);
                        }
                    } finally {
                    }
                }
            }
        }
    }

    public boolean isActivityDistractionOptimized(String packageName, String className) {
        assertPackageAndClassName(packageName, className);
        synchronized (this.mLock) {
            if (searchFromClientPolicyBlocklistsLocked(packageName)) {
                return false;
            }
            if (isActivityInClientPolicyAllowlistsLocked(packageName, className)) {
                return true;
            }
            boolean packageBlocked = this.mActivityDenylistPackages.contains(packageName);
            AppBlockingPackageInfoWrapper infoWrapper = this.mActivityAllowlistMap.get(packageName);
            if (!packageBlocked && infoWrapper == null) {
                updateActivityAllowlistAndDenylistMap(packageName);
                packageBlocked = this.mActivityDenylistPackages.contains(packageName);
                infoWrapper = this.mActivityAllowlistMap.get(packageName);
            }
            if (!packageBlocked && isActivityInMapAndMatching(infoWrapper, packageName, className)) {
                return true;
            }
            return false;
        }
    }

    public boolean isPendingIntentDistractionOptimized(PendingIntent pendingIntent) {
        ResolveInfo info = this.mPackageManager.resolveActivity(pendingIntent.getIntent(), 65536);
        if (info == null) {
            return false;
        }
        ActivityInfo activityInfo = info.activityInfo;
        return isActivityDistractionOptimized(activityInfo.packageName, activityInfo.name);
    }

    public boolean isServiceDistractionOptimized(String packageName, String className) {
        if (packageName == null) {
            throw new IllegalArgumentException("Package name null");
        }
        synchronized (this.mLock) {
            if (searchFromClientPolicyBlocklistsLocked(packageName)) {
                return false;
            }
            if (searchFromClientPolicyAllowlistsLocked(packageName)) {
                return true;
            }
            boolean packageBlocked = this.mActivityDenylistPackages.contains(packageName);
            AppBlockingPackageInfoWrapper infoWrapper = this.mActivityAllowlistMap.get(packageName);
            if (!packageBlocked && infoWrapper == null) {
                updateActivityAllowlistAndDenylistMap(packageName);
                packageBlocked = this.mActivityDenylistPackages.contains(packageName);
                infoWrapper = this.mActivityAllowlistMap.get(packageName);
            }
            if (!packageBlocked && infoWrapper != null && infoWrapper.info != null) {
                return true;
            }
            return false;
        }
    }

    public boolean isActivityBackedBySafeActivity(ComponentName activityName) {
        ActivityManager.StackInfo info = this.mSystemActivityMonitoringService.getFocusedStackForTopActivity(activityName);
        if (info != null && isUxRestrictedOnDisplay(info.displayId)) {
            if (info.taskNames.length <= 1) {
                return false;
            }
            ComponentName activityBehind = ComponentName.unflattenFromString(info.taskNames[info.taskNames.length - 2]);
            return isActivityDistractionOptimized(activityBehind.getPackageName(), activityBehind.getClassName());
        }
        return true;
    }

    public Looper getLooper() {
        return this.mHandlerThread.getLooper();
    }

    private void assertPackageAndClassName(String packageName, String className) {
        if (packageName == null) {
            throw new IllegalArgumentException("Package name null");
        }
        if (className == null) {
            throw new IllegalArgumentException("Class name null");
        }
    }

    private boolean searchFromClientPolicyBlocklistsLocked(String packageName) {
        for (ClientPolicy policy : this.mClientPolicies.values()) {
            AppBlockingPackageInfoWrapper wrapper = (AppBlockingPackageInfoWrapper) policy.mBlocklistsMap.get(packageName);
            if (wrapper != null && wrapper.isMatching && wrapper.info != null) {
                return true;
            }
        }
        return false;
    }

    private boolean searchFromClientPolicyAllowlistsLocked(String packageName) {
        for (ClientPolicy policy : this.mClientPolicies.values()) {
            AppBlockingPackageInfoWrapper wrapper = (AppBlockingPackageInfoWrapper) policy.mAllowlistsMap.get(packageName);
            if (wrapper != null && wrapper.isMatching && wrapper.info != null) {
                return true;
            }
        }
        return false;
    }

    private boolean isActivityInClientPolicyAllowlistsLocked(String packageName, String className) {
        for (ClientPolicy policy : this.mClientPolicies.values()) {
            if (isActivityInMapAndMatching((AppBlockingPackageInfoWrapper) policy.mAllowlistsMap.get(packageName), packageName, className)) {
                return true;
            }
        }
        return false;
    }

    private boolean isActivityInMapAndMatching(AppBlockingPackageInfoWrapper wrapper, String packageName, String className) {
        if (wrapper == null || !wrapper.isMatching) {
            return false;
        }
        return wrapper.info.isActivityCovered(className);
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
        synchronized (this.mLock) {
            this.mHandler.requestInit();
        }
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        synchronized (this.mLock) {
            this.mHandler.requestRelease();
            try {
                this.mLock.wait();
            } catch (InterruptedException e) {
                Log.e(CarLog.TAG_PACKAGE, "Interrupted wait during release");
                Thread.currentThread().interrupt();
            }
            this.mActivityAllowlistMap.clear();
            this.mActivityDenylistPackages.clear();
            this.mClientPolicies.clear();
            if (this.mProxies != null) {
                for (AppBlockingPolicyProxy proxy : this.mProxies) {
                    proxy.disconnect();
                }
                this.mProxies.clear();
            }
            this.mWaitingPolicies.clear();
            this.mLock.notifyAll();
        }
        this.mContext.unregisterReceiver(this.mPackageParsingEventReceiver);
        this.mSystemActivityMonitoringService.registerActivityLaunchListener(null);
        for (int i = 0; i < this.mUxRestrictionsListeners.size(); i++) {
            this.mCarUxRestrictionsService.unregisterUxRestrictionsChangeListener((UxRestrictionsListener) this.mUxRestrictionsListeners.valueAt(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doHandleInit() {
        startAppBlockingPolicies();
        IntentFilter pkgParseIntent = new IntentFilter();
        for (String action : this.mPackageManagerActions) {
            pkgParseIntent.addAction(action);
        }
        pkgParseIntent.addDataScheme("package");
        this.mContext.registerReceiverAsUser(this.mPackageParsingEventReceiver, UserHandle.ALL, pkgParseIntent, null, null);
        List<Display> physicalDisplays = getPhysicalDisplays();
        Display defaultDisplay = this.mDisplayManager.getDisplay(0);
        if (!physicalDisplays.contains(defaultDisplay)) {
            if (Log.isLoggable(CarLog.TAG_PACKAGE, 4)) {
                Log.i(CarLog.TAG_PACKAGE, "Adding default display to physical displays.");
            }
            physicalDisplays.add(defaultDisplay);
        }
        for (Display physicalDisplay : physicalDisplays) {
            int displayId = physicalDisplay.getDisplayId();
            ICarUxRestrictionsChangeListener uxRestrictionsListener = new UxRestrictionsListener(this.mCarUxRestrictionsService);
            this.mUxRestrictionsListeners.put(displayId, uxRestrictionsListener);
            this.mCarUxRestrictionsService.registerUxRestrictionsChangeListener(uxRestrictionsListener, displayId);
        }
        this.mVendorServiceController.init();
        this.mSystemActivityMonitoringService.registerActivityLaunchListener(this.mActivityLaunchListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doParseInstalledPackage(String packageName) {
        synchronized (this.mLock) {
            this.mActivityDenylistPackages.remove(packageName);
            this.mActivityAllowlistMap.remove(packageName);
        }
        updateActivityAllowlistAndDenylistMap(packageName);
        blockTopActivitiesIfNecessary();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doHandleRelease() {
        synchronized (this.mLock) {
            this.mVendorServiceController.release();
            this.mLock.notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doUpdatePolicy(String packageName, CarAppBlockingPolicy policy, int flags) {
        AppBlockingPackageInfoWrapper[] blocklistWrapper = verifyList(policy.blacklists);
        AppBlockingPackageInfoWrapper[] allowlistWrapper = verifyList(policy.whitelists);
        synchronized (this.mLock) {
            ClientPolicy clientPolicy = this.mClientPolicies.get(packageName);
            if (clientPolicy == null) {
                clientPolicy = new ClientPolicy();
                this.mClientPolicies.put(packageName, clientPolicy);
            }
            if ((flags & 2) == 0) {
                if ((flags & 4) != 0) {
                    clientPolicy.removeBlocklists(blocklistWrapper);
                    clientPolicy.removeAllowlists(allowlistWrapper);
                } else {
                    clientPolicy.replaceBlocklists(blocklistWrapper);
                    clientPolicy.replaceAllowlists(allowlistWrapper);
                }
            } else {
                clientPolicy.addToBlocklists(blocklistWrapper);
                clientPolicy.addToAllowlists(allowlistWrapper);
            }
            if ((flags & 1) != 0) {
                this.mWaitingPolicies.remove(policy);
                this.mLock.notifyAll();
            }
        }
        blockTopActivitiesIfNecessary();
    }

    private AppBlockingPackageInfoWrapper[] verifyList(AppBlockingPackageInfo[] list) {
        if (list == null) {
            return null;
        }
        LinkedList<AppBlockingPackageInfoWrapper> wrappers = new LinkedList<>();
        for (AppBlockingPackageInfo info : list) {
            if (info != null) {
                boolean isMatching = isInstalledPackageMatching(info);
                wrappers.add(new AppBlockingPackageInfoWrapper(info, isMatching));
            }
        }
        return (AppBlockingPackageInfoWrapper[]) wrappers.toArray(new AppBlockingPackageInfoWrapper[wrappers.size()]);
    }

    boolean isInstalledPackageMatching(AppBlockingPackageInfo info) {
        try {
            PackageInfo packageInfo = this.mPackageManager.getPackageInfo(info.packageName, 64);
            if (packageInfo == null) {
                return false;
            }
            if ((info.flags & 1) == 0 || (!packageInfo.applicationInfo.isSystemApp() && !packageInfo.applicationInfo.isUpdatedSystemApp())) {
                Signature[] signatures = packageInfo.signatures;
                if (!isAnySignatureMatching(signatures, info.signatures)) {
                    return false;
                }
            }
            int version = packageInfo.versionCode;
            return info.minRevisionCode == 0 ? info.maxRevisionCode == 0 || info.maxRevisionCode > version : info.maxRevisionCode == 0 ? info.minRevisionCode < version : info.minRevisionCode < version && info.maxRevisionCode > version;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    boolean isAnySignatureMatching(Signature[] fromPackage, Signature[] fromPolicy) {
        if (fromPackage == null || fromPolicy == null) {
            return false;
        }
        ArraySet<Signature> setFromPackage = new ArraySet<>();
        for (Signature sig : fromPackage) {
            setFromPackage.add(sig);
        }
        for (Signature sig2 : fromPolicy) {
            if (setFromPackage.contains(sig2)) {
                return true;
            }
        }
        return false;
    }

    private AppBlockingPackageInfoWrapper getPackageInfoWrapperForUser(String packageName, int userId, Map<String, Set<String>> configAllowlist, Map<String, Set<String>> configBlocklist) {
        int flags;
        String installerName;
        try {
            PackageInfo info = this.mPackageManager.getPackageInfoAsUser(packageName, 787009, userId);
            if (info == null || info.applicationInfo == null) {
                return null;
            }
            int flags2 = 0;
            Set<String> activities = new ArraySet<>();
            if (info.applicationInfo.isSystemApp() || info.applicationInfo.isUpdatedSystemApp()) {
                flags2 = 1;
            }
            Set<String> configActivitiesForPackage = configAllowlist.get(info.packageName);
            if (configActivitiesForPackage != null) {
                if (configActivitiesForPackage.isEmpty()) {
                    int flags3 = flags2 | 2;
                    List<String> activitiesForPackage = getActivitiesInPackage(info);
                    if (activitiesForPackage != null) {
                        activities.addAll(activitiesForPackage);
                    }
                    flags = flags3;
                } else {
                    activities.addAll(configActivitiesForPackage);
                    flags = flags2;
                }
            } else {
                flags = flags2;
            }
            if (!isDebugBuild() && !info.applicationInfo.isSystemApp() && !info.applicationInfo.isUpdatedSystemApp()) {
                try {
                    if (this.mAllowedAppInstallSources != null && ((installerName = this.mPackageManager.getInstallerPackageName(info.packageName)) == null || (installerName != null && !this.mAllowedAppInstallSources.contains(installerName)))) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(info.packageName);
                        sb.append(" not installed from permitted sources ");
                        sb.append(installerName == null ? "NULL" : installerName);
                        Log.w(CarLog.TAG_PACKAGE, sb.toString());
                        return null;
                    }
                } catch (IllegalArgumentException e) {
                    Log.w(CarLog.TAG_PACKAGE, info.packageName + " not installed!");
                    return null;
                }
            }
            try {
                String[] doActivities = CarAppMetadataReader.findDistractionOptimizedActivitiesAsUser(this.mContext, info.packageName, userId);
                if (doActivities != null) {
                    activities.addAll(Arrays.asList(doActivities));
                }
                if (activities.isEmpty()) {
                    return null;
                }
                if (configBlocklist.containsKey(info.packageName)) {
                    Set<String> configBlocklistActivities = configBlocklist.get(info.packageName);
                    if (configBlocklistActivities.isEmpty()) {
                        return null;
                    }
                    activities.removeAll(configBlocklistActivities);
                }
                Signature[] signatures = info.signatures;
                AppBlockingPackageInfo appBlockingInfo = new AppBlockingPackageInfo(info.packageName, 0, 0, flags, signatures, (String[]) activities.toArray(new String[activities.size()]));
                AppBlockingPackageInfoWrapper wrapper = new AppBlockingPackageInfoWrapper(appBlockingInfo, true);
                return wrapper;
            } catch (PackageManager.NameNotFoundException e2) {
                Log.w(CarLog.TAG_PACKAGE, "Error reading metadata: " + info.packageName);
                return null;
            }
        } catch (PackageManager.NameNotFoundException e3) {
            Log.w(CarLog.TAG_PACKAGE, packageName + " not installed! User Id: " + userId);
            return null;
        }
    }

    private void updateActivityAllowlistAndDenylistMap(String packageName) {
        int userId = ActivityManager.getCurrentUser();
        Log.i(CarLog.TAG_PACKAGE, "Updating allowlist and denylist mapping for package: " + packageName + " for UserId: " + userId);
        Map<String, Set<String>> configAllowlist = generateConfigAllowlist();
        Map<String, Set<String>> configBlocklist = generateConfigBlocklist();
        AppBlockingPackageInfoWrapper wrapper = getPackageInfoWrapperForUser(packageName, userId, configAllowlist, configBlocklist);
        if (wrapper == null && userId != 0) {
            Log.i(CarLog.TAG_PACKAGE, "Updating allowlist and denylist mapping for package: " + packageName + " for UserId: 0");
            wrapper = getPackageInfoWrapperForUser(packageName, 0, configAllowlist, configBlocklist);
        }
        synchronized (this.mLock) {
            if (wrapper != null) {
                Log.i(CarLog.TAG_PACKAGE, "Package: " + packageName + " added in allowlist.");
                this.mActivityAllowlistMap.put(packageName, wrapper);
            } else {
                Log.i(CarLog.TAG_PACKAGE, "Package: " + packageName + " added in denylist.");
                this.mActivityDenylistPackages.add(packageName);
            }
        }
    }

    private Map<String, Set<String>> generateConfigAllowlist() {
        synchronized (this.mLock) {
            if (this.mConfiguredAllowlistMap != null) {
                return this.mConfiguredAllowlistMap;
            }
            Map<String, Set<String>> configAllowlist = new HashMap<>();
            String string = this.mContext.getString(R.string.activityWhitelist);
            this.mConfiguredAllowlist = string;
            parseConfigList(string, configAllowlist);
            String string2 = this.mContext.getString(R.string.systemActivityWhitelist);
            this.mConfiguredSystemAllowlist = string2;
            parseConfigList(string2, configAllowlist);
            Set<String> defaultActivity = new ArraySet<>();
            if (this.mActivityBlockingActivity != null) {
                defaultActivity.add(this.mActivityBlockingActivity.getClassName());
                configAllowlist.put(this.mActivityBlockingActivity.getPackageName(), defaultActivity);
            }
            this.mConfiguredAllowlistMap = configAllowlist;
            return configAllowlist;
        }
    }

    private Map<String, Set<String>> generateConfigBlocklist() {
        synchronized (this.mLock) {
            if (this.mConfiguredBlocklistMap != null) {
                return this.mConfiguredBlocklistMap;
            }
            Map<String, Set<String>> configBlocklist = new HashMap<>();
            String string = this.mContext.getString(R.string.activityBlacklist);
            this.mConfiguredBlocklist = string;
            parseConfigList(string, configBlocklist);
            this.mConfiguredBlocklistMap = configBlocklist;
            return configBlocklist;
        }
    }

    private boolean isDebugBuild() {
        return Build.IS_USERDEBUG || Build.IS_ENG;
    }

    void parseConfigList(String configList, Map<String, Set<String>> packageToActivityMap) {
        if (configList == null) {
            return;
        }
        String[] entries = configList.split(PACKAGE_DELIMITER);
        for (String entry : entries) {
            String[] packageActivityPair = entry.split(PACKAGE_ACTIVITY_DELIMITER);
            Set<String> activities = packageToActivityMap.get(packageActivityPair[0]);
            boolean newPackage = false;
            if (activities == null) {
                activities = new ArraySet();
                newPackage = true;
                packageToActivityMap.put(packageActivityPair[0], activities);
            }
            if (packageActivityPair.length == 1) {
                activities.clear();
            } else if (packageActivityPair.length == 2 && (newPackage || activities.size() > 0)) {
                activities.add(packageActivityPair[1]);
            }
        }
    }

    private List<String> getActivitiesInPackage(PackageInfo info) {
        if (info == null || info.activities == null) {
            return null;
        }
        List<String> activityList = new ArrayList<>();
        for (ActivityInfo aInfo : info.activities) {
            activityList.add(aInfo.name);
        }
        return activityList;
    }

    public void startAppBlockingPolicies() {
        Intent policyIntent = new Intent();
        policyIntent.setAction("android.car.content.pm.CarAppBlockingPolicyService");
        List<ResolveInfo> policyInfos = this.mPackageManager.queryIntentServices(policyIntent, 0);
        if (policyInfos == null) {
            return;
        }
        LinkedList<AppBlockingPolicyProxy> proxies = new LinkedList<>();
        for (ResolveInfo resolveInfo : policyInfos) {
            ServiceInfo serviceInfo = resolveInfo.serviceInfo;
            if (serviceInfo != null && serviceInfo.isEnabled() && this.mPackageManager.checkPermission(Manifest.permission.CONTROL_APP_BLOCKING, serviceInfo.packageName) == 0) {
                Log.i(CarLog.TAG_PACKAGE, "found policy holding service:" + serviceInfo);
                AppBlockingPolicyProxy proxy = new AppBlockingPolicyProxy(this, this.mContext, serviceInfo);
                proxy.connect();
                proxies.add(proxy);
            }
        }
        synchronized (this.mLock) {
            this.mProxies = proxies;
        }
    }

    public void onPolicyConnectionAndSet(AppBlockingPolicyProxy proxy, CarAppBlockingPolicy policy) {
        doHandlePolicyConnection(proxy, policy);
    }

    public void onPolicyConnectionFailure(AppBlockingPolicyProxy proxy) {
        doHandlePolicyConnection(proxy, null);
    }

    private void doHandlePolicyConnection(AppBlockingPolicyProxy proxy, CarAppBlockingPolicy policy) {
        synchronized (this.mLock) {
            if (this.mProxies == null) {
                return;
            }
            this.mProxies.remove(proxy);
            if (this.mProxies.size() == 0) {
                this.mProxies = null;
            }
            if (policy != null) {
                try {
                    doSetAppBlockingPolicy(proxy.getPackageName(), policy, 0);
                } finally {
                    proxy.disconnect();
                }
            }
        }
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        synchronized (this.mLock) {
            writer.println("*CarPackageManagerService*");
            writer.println("mEnableActivityBlocking:" + this.mEnableActivityBlocking);
            List<String> restrictions = new ArrayList<>(this.mUxRestrictionsListeners.size());
            for (int i = 0; i < this.mUxRestrictionsListeners.size(); i++) {
                int displayId = this.mUxRestrictionsListeners.keyAt(i);
                UxRestrictionsListener listener = this.mUxRestrictionsListeners.valueAt(i);
                Object[] objArr = new Object[2];
                objArr[0] = Integer.valueOf(displayId);
                objArr[1] = listener.isRestricted() ? "restricted" : "unrestricted";
                restrictions.add(String.format("Display %d is %s", objArr));
            }
            writer.println("Display Restrictions:\n" + String.join("\n", restrictions));
            writer.println(" Blocked activity log:");
            writer.println(String.join("\n", this.mBlockedActivityLogs));
            writer.print(dumpPoliciesLocked(true));
        }
    }

    private String dumpPoliciesLocked(boolean dumpAll) {
        StringBuilder sb = new StringBuilder();
        if (dumpAll) {
            sb.append("**System allowlist**\n");
            for (AppBlockingPackageInfoWrapper wrapper : this.mActivityAllowlistMap.values()) {
                sb.append(wrapper.toString() + "\n");
            }
        }
        sb.append("**Client Policies**\n");
        for (Map.Entry<String, ClientPolicy> entry : this.mClientPolicies.entrySet()) {
            sb.append("Client:" + entry.getKey() + "\n");
            sb.append("  allowlists:\n");
            for (AppBlockingPackageInfoWrapper wrapper2 : entry.getValue().mAllowlistsMap.values()) {
                sb.append(wrapper2.toString() + "\n");
            }
            sb.append("  blocklists:\n");
            for (AppBlockingPackageInfoWrapper wrapper3 : entry.getValue().mBlocklistsMap.values()) {
                sb.append(wrapper3.toString() + "\n");
            }
        }
        sb.append("**Unprocessed policy services**\n");
        LinkedList<AppBlockingPolicyProxy> linkedList = this.mProxies;
        if (linkedList != null) {
            for (AppBlockingPolicyProxy proxy : linkedList) {
                sb.append(proxy.toString() + "\n");
            }
        }
        sb.append("**Allowlist string in resource**\n");
        sb.append(this.mConfiguredAllowlist + "\n");
        sb.append("**System allowlist string in resource**\n");
        sb.append(this.mConfiguredSystemAllowlist + "\n");
        sb.append("**Blocklist string in resource**\n");
        sb.append(this.mConfiguredBlocklist + "\n");
        sb.append("**Allowlist map from resource**\n");
        sb.append(this.mConfiguredAllowlistMap + "\n");
        sb.append("**Blocklist from resource**\n");
        sb.append(this.mConfiguredBlocklist + "\n");
        return sb.toString();
    }

    private List<Display> getPhysicalDisplays() {
        List<Display> displays = new ArrayList<>();
        for (Display display : this.mDisplayManager.getDisplays()) {
            if (display.getAddress() instanceof DisplayAddress.Physical) {
                displays.add(display);
            }
        }
        return displays;
    }

    private boolean isUxRestrictedOnDisplay(int displayId) {
        UxRestrictionsListener listenerForTopTaskDisplay;
        if (this.mUxRestrictionsListeners.indexOfKey(displayId) < 0) {
            listenerForTopTaskDisplay = this.mUxRestrictionsListeners.get(0);
            if (listenerForTopTaskDisplay == null) {
                Log.e(CarLog.TAG_PACKAGE, "Missing listener for default display.");
                return true;
            }
        } else {
            listenerForTopTaskDisplay = this.mUxRestrictionsListeners.get(displayId);
        }
        return listenerForTopTaskDisplay.isRestricted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void blockTopActivitiesIfNecessary() {
        List<SystemActivityMonitoringService.TopTaskInfoContainer> topTasks = this.mSystemActivityMonitoringService.getTopTasks();
        for (SystemActivityMonitoringService.TopTaskInfoContainer topTask : topTasks) {
            if (topTask == null) {
                Log.e(CarLog.TAG_PACKAGE, "Top tasks contains null.");
            } else {
                blockTopActivityIfNecessary(topTask);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void blockTopActivityIfNecessary(SystemActivityMonitoringService.TopTaskInfoContainer topTask) {
        if (isUxRestrictedOnDisplay(topTask.displayId)) {
            doBlockTopActivityIfNotAllowed(topTask);
        }
    }

    private void doBlockTopActivityIfNotAllowed(SystemActivityMonitoringService.TopTaskInfoContainer topTask) {
        if (topTask.topActivity == null) {
            return;
        }
        boolean allowed = isActivityDistractionOptimized(topTask.topActivity.getPackageName(), topTask.topActivity.getClassName());
        if (allowed) {
            return;
        }
        synchronized (this.mLock) {
            if (!this.mEnableActivityBlocking) {
                Log.d(CarLog.TAG_PACKAGE, "Current activity " + topTask.topActivity + " not allowed, blocking disabled. Number of tasks in stack:" + topTask.stackInfo.taskIds.length);
                return;
            }
            String taskRootActivity = null;
            int i = 0;
            while (true) {
                if (i >= topTask.stackInfo.taskIds.length) {
                    break;
                }
                if (topTask.stackInfo.taskIds[i] != topTask.taskId) {
                    i++;
                } else {
                    taskRootActivity = topTask.stackInfo.taskNames[i];
                    break;
                }
            }
            boolean isRootDO = false;
            if (taskRootActivity != null) {
                ComponentName componentName = ComponentName.unflattenFromString(taskRootActivity);
                isRootDO = isActivityDistractionOptimized(componentName.getPackageName(), componentName.getClassName());
            }
            Intent newActivityIntent = createBlockingActivityIntent(this.mActivityBlockingActivity, topTask.displayId, topTask.topActivity.flattenToShortString(), topTask.taskId, taskRootActivity, isRootDO);
            String log = "Starting blocking activity with intent: " + newActivityIntent.toUri(0);
            if (Log.isLoggable(CarLog.TAG_PACKAGE, 4)) {
                Log.i(CarLog.TAG_PACKAGE, log);
            }
            addLog(log);
            this.mSystemActivityMonitoringService.blockActivity(topTask, newActivityIntent);
        }
    }

    private static Intent createBlockingActivityIntent(ComponentName blockingActivity, int displayId, String blockedActivity, int blockedTaskId, String taskRootActivity, boolean isRootDo) {
        Intent newActivityIntent = new Intent();
        newActivityIntent.setFlags(134217728);
        newActivityIntent.setComponent(blockingActivity);
        newActivityIntent.putExtra(BLOCKING_INTENT_EXTRA_DISPLAY_ID, displayId);
        newActivityIntent.putExtra(BLOCKING_INTENT_EXTRA_BLOCKED_ACTIVITY_NAME, blockedActivity);
        newActivityIntent.putExtra(BLOCKING_INTENT_EXTRA_BLOCKED_TASK_ID, blockedTaskId);
        newActivityIntent.putExtra(BLOCKING_INTENT_EXTRA_ROOT_ACTIVITY_NAME, taskRootActivity);
        newActivityIntent.putExtra(BLOCKING_INTENT_EXTRA_IS_ROOT_ACTIVITY_DO, isRootDo);
        return newActivityIntent;
    }

    public void setEnableActivityBlocking(boolean enable) {
        if (!isDebugBuild()) {
            Log.e(CarLog.TAG_PACKAGE, "Cannot enable/disable activity blocking");
            return;
        }
        if (this.mPackageManager.checkSignatures(Process.myUid(), Binder.getCallingUid()) != 0) {
            throw new SecurityException("Caller " + this.mPackageManager.getNameForUid(Binder.getCallingUid()) + " does not have the right signature");
        }
        this.mCarUxRestrictionsService.setUxRChangeBroadcastEnabled(enable);
    }

    public String[] getDistractionOptimizedActivities(String pkgName) {
        try {
            return CarAppMetadataReader.findDistractionOptimizedActivitiesAsUser(this.mContext, pkgName, ActivityManager.getCurrentUser());
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    private void addLog(String log) {
        while (this.mBlockedActivityLogs.size() >= 20) {
            this.mBlockedActivityLogs.remove();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(CarLog.TAG_PACKAGE);
        stringBuffer.append(AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR);
        stringBuffer.append(DateFormat.format("MM-dd HH:mm:ss", System.currentTimeMillis()));
        stringBuffer.append(": ");
        StringBuffer sb = stringBuffer.append(log);
        this.mBlockedActivityLogs.add(sb.toString());
    }

    private static final class PackageHandler extends Handler {
        private static final int MSG_INIT = 0;
        private static final int MSG_PARSE_PKG = 1;
        private static final int MSG_RELEASE = 3;
        private static final int MSG_UPDATE_POLICY = 2;
        private static final String TAG = PackageHandler.class.getSimpleName();
        private final WeakReference<CarPackageManagerService> mService;

        private PackageHandler(Looper looper, CarPackageManagerService service) {
            super(looper);
            this.mService = new WeakReference<>(service);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void requestInit() {
            Message msg = obtainMessage(0);
            sendMessage(msg);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void requestRelease() {
            removeMessages(2);
            Message msg = obtainMessage(3);
            sendMessage(msg);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void requestUpdatingPolicy(String packageName, CarAppBlockingPolicy policy, int flags) {
            Pair<String, CarAppBlockingPolicy> pair = new Pair<>(packageName, policy);
            Message msg = obtainMessage(2, flags, 0, pair);
            sendMessage(msg);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void requestParsingInstalledPkg(String packageName) {
            Message msg = obtainMessage(1, packageName);
            sendMessage(msg);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            CarPackageManagerService service = this.mService.get();
            if (service == null) {
                Log.i(TAG, "handleMessage null service");
                return;
            }
            int i = msg.what;
            if (i == 0) {
                service.doHandleInit();
                return;
            }
            if (i == 1) {
                service.doParseInstalledPackage((String) msg.obj);
                return;
            }
            if (i == 2) {
                Pair<String, CarAppBlockingPolicy> pair = (Pair) msg.obj;
                service.doUpdatePolicy((String) pair.first, (CarAppBlockingPolicy) pair.second, msg.arg1);
            } else if (i == 3) {
                service.doHandleRelease();
            }
        }
    }

    private static class AppBlockingPackageInfoWrapper {
        private final AppBlockingPackageInfo info;
        private boolean isMatching;

        private AppBlockingPackageInfoWrapper(AppBlockingPackageInfo info, boolean isMatching) {
            this.info = info;
            this.isMatching = isMatching;
        }

        public String toString() {
            return "AppBlockingPackageInfoWrapper [info=" + this.info + ", isMatching=" + this.isMatching + "]";
        }
    }

    private static class ClientPolicy {
        private final HashMap<String, AppBlockingPackageInfoWrapper> mAllowlistsMap;
        private final HashMap<String, AppBlockingPackageInfoWrapper> mBlocklistsMap;

        private ClientPolicy() {
            this.mAllowlistsMap = new HashMap<>();
            this.mBlocklistsMap = new HashMap<>();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void replaceAllowlists(AppBlockingPackageInfoWrapper[] allowlists) {
            this.mAllowlistsMap.clear();
            addToAllowlists(allowlists);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addToAllowlists(AppBlockingPackageInfoWrapper[] allowlists) {
            if (allowlists == null) {
                return;
            }
            for (AppBlockingPackageInfoWrapper wrapper : allowlists) {
                if (wrapper != null) {
                    this.mAllowlistsMap.put(wrapper.info.packageName, wrapper);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeAllowlists(AppBlockingPackageInfoWrapper[] allowlists) {
            if (allowlists == null) {
                return;
            }
            for (AppBlockingPackageInfoWrapper wrapper : allowlists) {
                if (wrapper != null) {
                    this.mAllowlistsMap.remove(wrapper.info.packageName);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void replaceBlocklists(AppBlockingPackageInfoWrapper[] blocklists) {
            this.mBlocklistsMap.clear();
            addToBlocklists(blocklists);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addToBlocklists(AppBlockingPackageInfoWrapper[] blocklists) {
            if (blocklists == null) {
                return;
            }
            for (AppBlockingPackageInfoWrapper wrapper : blocklists) {
                if (wrapper != null) {
                    this.mBlocklistsMap.put(wrapper.info.packageName, wrapper);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeBlocklists(AppBlockingPackageInfoWrapper[] blocklists) {
            if (blocklists == null) {
                return;
            }
            for (AppBlockingPackageInfoWrapper wrapper : blocklists) {
                if (wrapper != null) {
                    this.mBlocklistsMap.remove(wrapper.info.packageName);
                }
            }
        }
    }

    private class ActivityLaunchListener implements SystemActivityMonitoringService.ActivityLaunchListener {
        private ActivityLaunchListener() {
        }

        @Override // com.android.car.SystemActivityMonitoringService.ActivityLaunchListener
        public void onActivityLaunch(SystemActivityMonitoringService.TopTaskInfoContainer topTask) {
            if (topTask != null) {
                CarPackageManagerService.this.blockTopActivityIfNecessary(topTask);
            } else {
                Log.e(CarLog.TAG_PACKAGE, "Received callback with null top task.");
            }
        }
    }

    private class UxRestrictionsListener extends ICarUxRestrictionsChangeListener.Stub {
        private CarUxRestrictions mCurrentUxRestrictions;
        private final CarUxRestrictionsManagerService uxRestrictionsService;

        public UxRestrictionsListener(CarUxRestrictionsManagerService service) {
            this.uxRestrictionsService = service;
        }

        public void onUxRestrictionsChanged(CarUxRestrictions restrictions) {
            synchronized (CarPackageManagerService.this.mLock) {
                this.mCurrentUxRestrictions = new CarUxRestrictions(restrictions);
            }
            checkIfTopActivityNeedsBlocking();
        }

        private void checkIfTopActivityNeedsBlocking() {
            boolean shouldCheck = false;
            synchronized (CarPackageManagerService.this.mLock) {
                if (this.mCurrentUxRestrictions != null && this.mCurrentUxRestrictions.isRequiresDistractionOptimization()) {
                    shouldCheck = true;
                }
            }
            if (shouldCheck) {
                CarPackageManagerService.this.blockTopActivitiesIfNecessary();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isRestricted() {
            synchronized (CarPackageManagerService.this.mLock) {
                if (this.mCurrentUxRestrictions == null) {
                    this.mCurrentUxRestrictions = this.uxRestrictionsService.getCurrentUxRestrictions();
                }
                if (this.mCurrentUxRestrictions != null) {
                    return this.mCurrentUxRestrictions.isRequiresDistractionOptimization();
                }
                return false;
            }
        }
    }

    private class PackageParsingEventReceiver extends BroadcastReceiver {
        private PackageParsingEventReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                return;
            }
            String action = intent.getAction();
            if (isPackageManagerAction(action)) {
                logEventChange(intent);
                String packageName = getPackageName(intent);
                CarPackageManagerService.this.mHandler.requestParsingInstalledPkg(packageName);
            }
        }

        private String getPackageName(Intent intent) {
            String dataString = intent.getDataString();
            if (dataString == null) {
                return null;
            }
            String scheme = intent.getScheme();
            if (!scheme.equals("package")) {
                return null;
            }
            String[] splitData = intent.getDataString().split(":");
            if (splitData.length < 2) {
                return null;
            }
            return splitData[1];
        }

        private boolean isPackageManagerAction(String action) {
            return CarPackageManagerService.this.mPackageManagerActions.contains(action);
        }

        private void logEventChange(Intent intent) {
        }
    }
}
