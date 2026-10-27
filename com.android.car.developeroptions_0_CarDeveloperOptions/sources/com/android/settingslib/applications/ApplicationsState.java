package com.android.settingslib.applications;

import android.R;
import android.app.ActivityManager;
import android.app.AppGlobals;
import android.app.Application;
import android.app.usage.StorageStats;
import android.app.usage.StorageStatsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.ModuleInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.format.Formatter;
import android.util.IconDrawableFactory;
import android.util.Log;
import android.util.SparseArray;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.android.car.developeroptions.intelligence.LogProto$SettingsLog;
import com.android.internal.util.ArrayUtils;
import com.android.settingslib.R$string;
import com.android.settingslib.Utils;
import com.android.settingslib.utils.ThreadUtils;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class ApplicationsState {
    public static final Comparator<AppEntry> ALPHA_COMPARATOR;
    public static final Comparator<AppEntry> EXTERNAL_SIZE_COMPARATOR;
    public static final AppFilter FILTER_ALL_ENABLED;
    public static final AppFilter FILTER_AUDIO;
    public static final AppFilter FILTER_DISABLED;
    public static final AppFilter FILTER_DOWNLOADED_AND_LAUNCHER;
    public static final AppFilter FILTER_DOWNLOADED_AND_LAUNCHER_AND_INSTANT;
    public static final AppFilter FILTER_EVERYTHING;
    public static final AppFilter FILTER_GAMES;
    public static final AppFilter FILTER_INSTANT;
    public static final AppFilter FILTER_MOVIES;
    public static final AppFilter FILTER_NOT_HIDE;
    public static final AppFilter FILTER_OTHER_APPS;
    public static final AppFilter FILTER_PERSONAL;
    public static final AppFilter FILTER_PHOTOS;
    public static final AppFilter FILTER_WITHOUT_DISABLED_UNTIL_USED;
    public static final AppFilter FILTER_WITH_DOMAIN_URLS;
    public static final AppFilter FILTER_WORK;
    public static final Comparator<AppEntry> INTERNAL_SIZE_COMPARATOR;
    public static final Comparator<AppEntry> SIZE_COMPARATOR;
    static ApplicationsState sInstance;
    private static final Object sLock = new Object();
    final int mAdminRetrieveFlags;
    final BackgroundHandler mBackgroundHandler;
    final Context mContext;
    String mCurComputingSizePkg;
    int mCurComputingSizeUserId;
    UUID mCurComputingSizeUuid;
    boolean mHaveDisabledApps;
    boolean mHaveInstantApps;
    final IPackageManager mIpm;
    PackageIntentReceiver mPackageIntentReceiver;
    final PackageManager mPm;
    boolean mResumed;
    final int mRetrieveFlags;
    boolean mSessionsChanged;
    final StorageStatsManager mStats;
    final HandlerThread mThread;
    final UserManager mUm;
    final ArrayList<Session> mSessions = new ArrayList<>();
    final ArrayList<Session> mRebuildingSessions = new ArrayList<>();
    private InterestingConfigChanges mInterestingConfigChanges = new InterestingConfigChanges();
    final SparseArray<HashMap<String, AppEntry>> mEntriesMap = new SparseArray<>();
    final ArrayList<AppEntry> mAppEntries = new ArrayList<>();
    List<ApplicationInfo> mApplications = new ArrayList();
    long mCurId = 1;
    final HashMap<String, Boolean> mSystemModules = new HashMap<>();
    final ArrayList<WeakReference<Session>> mActiveSessions = new ArrayList<>();
    final MainHandler mMainHandler = new MainHandler(Looper.getMainLooper());

    public interface Callbacks {
        void onAllSizesComputed();

        void onLauncherInfoChanged();

        void onLoadEntriesCompleted();

        void onPackageIconChanged();

        void onPackageListChanged();

        void onPackageSizeChanged(String str);

        void onRebuildComplete(ArrayList<AppEntry> arrayList);

        void onRunningStateChanged(boolean z);
    }

    public static class SizeInfo {
        public long cacheSize;
        public long codeSize;
        public long dataSize;
        public long externalCacheSize;
        public long externalCodeSize;
        public long externalDataSize;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean hasFlag(int i, int i2) {
        return (i & i2) != 0;
    }

    static {
        Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        ALPHA_COMPARATOR = new Comparator<AppEntry>() { // from class: com.android.settingslib.applications.ApplicationsState.1
            private final Collator sCollator = Collator.getInstance();

            @Override // java.util.Comparator
            public int compare(AppEntry appEntry, AppEntry appEntry2) {
                ApplicationInfo applicationInfo;
                int iCompare;
                int iCompare2 = this.sCollator.compare(appEntry.label, appEntry2.label);
                if (iCompare2 != 0) {
                    return iCompare2;
                }
                ApplicationInfo applicationInfo2 = appEntry.info;
                return (applicationInfo2 == null || (applicationInfo = appEntry2.info) == null || (iCompare = this.sCollator.compare(applicationInfo2.packageName, applicationInfo.packageName)) == 0) ? appEntry.info.uid - appEntry2.info.uid : iCompare;
            }
        };
        SIZE_COMPARATOR = new Comparator<AppEntry>() { // from class: com.android.settingslib.applications.ApplicationsState.2
            @Override // java.util.Comparator
            public int compare(AppEntry appEntry, AppEntry appEntry2) {
                long j = appEntry.size;
                long j2 = appEntry2.size;
                if (j < j2) {
                    return 1;
                }
                if (j > j2) {
                    return -1;
                }
                return ApplicationsState.ALPHA_COMPARATOR.compare(appEntry, appEntry2);
            }
        };
        INTERNAL_SIZE_COMPARATOR = new Comparator<AppEntry>() { // from class: com.android.settingslib.applications.ApplicationsState.3
            @Override // java.util.Comparator
            public int compare(AppEntry appEntry, AppEntry appEntry2) {
                long j = appEntry.internalSize;
                long j2 = appEntry2.internalSize;
                if (j < j2) {
                    return 1;
                }
                if (j > j2) {
                    return -1;
                }
                return ApplicationsState.ALPHA_COMPARATOR.compare(appEntry, appEntry2);
            }
        };
        EXTERNAL_SIZE_COMPARATOR = new Comparator<AppEntry>() { // from class: com.android.settingslib.applications.ApplicationsState.4
            @Override // java.util.Comparator
            public int compare(AppEntry appEntry, AppEntry appEntry2) {
                long j = appEntry.externalSize;
                long j2 = appEntry2.externalSize;
                if (j < j2) {
                    return 1;
                }
                if (j > j2) {
                    return -1;
                }
                return ApplicationsState.ALPHA_COMPARATOR.compare(appEntry, appEntry2);
            }
        };
        FILTER_PERSONAL = new AppFilter() { // from class: com.android.settingslib.applications.ApplicationsState.5
            private int mCurrentUser;

            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public void init() {
                this.mCurrentUser = ActivityManager.getCurrentUser();
            }

            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public boolean filterApp(AppEntry appEntry) {
                return UserHandle.getUserId(appEntry.info.uid) == this.mCurrentUser;
            }
        };
        FILTER_WITHOUT_DISABLED_UNTIL_USED = new AppFilter() { // from class: com.android.settingslib.applications.ApplicationsState.6
            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public void init() {
            }

            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public boolean filterApp(AppEntry appEntry) {
                return appEntry.info.enabledSetting != 4;
            }
        };
        FILTER_WORK = new AppFilter() { // from class: com.android.settingslib.applications.ApplicationsState.7
            private int mCurrentUser;

            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public void init() {
                this.mCurrentUser = ActivityManager.getCurrentUser();
            }

            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public boolean filterApp(AppEntry appEntry) {
                return UserHandle.getUserId(appEntry.info.uid) != this.mCurrentUser;
            }
        };
        FILTER_DOWNLOADED_AND_LAUNCHER = new AppFilter() { // from class: com.android.settingslib.applications.ApplicationsState.8
            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public void init() {
            }

            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public boolean filterApp(AppEntry appEntry) {
                if (AppUtils.isInstant(appEntry.info)) {
                    return false;
                }
                if (ApplicationsState.hasFlag(appEntry.info.flags, 128) || !ApplicationsState.hasFlag(appEntry.info.flags, 1) || appEntry.hasLauncherEntry) {
                    return true;
                }
                return ApplicationsState.hasFlag(appEntry.info.flags, 1) && appEntry.isHomeApp;
            }
        };
        FILTER_DOWNLOADED_AND_LAUNCHER_AND_INSTANT = new AppFilter() { // from class: com.android.settingslib.applications.ApplicationsState.9
            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public void init() {
            }

            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public boolean filterApp(AppEntry appEntry) {
                return AppUtils.isInstant(appEntry.info) || ApplicationsState.FILTER_DOWNLOADED_AND_LAUNCHER.filterApp(appEntry);
            }
        };
        FILTER_DISABLED = new AppFilter() { // from class: com.android.settingslib.applications.ApplicationsState.11
            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public void init() {
            }

            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public boolean filterApp(AppEntry appEntry) {
                ApplicationInfo applicationInfo = appEntry.info;
                return (applicationInfo.enabled || AppUtils.isInstant(applicationInfo)) ? false : true;
            }
        };
        FILTER_INSTANT = new AppFilter() { // from class: com.android.settingslib.applications.ApplicationsState.12
            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public void init() {
            }

            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public boolean filterApp(AppEntry appEntry) {
                return AppUtils.isInstant(appEntry.info);
            }
        };
        FILTER_ALL_ENABLED = new AppFilter() { // from class: com.android.settingslib.applications.ApplicationsState.13
            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public void init() {
            }

            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public boolean filterApp(AppEntry appEntry) {
                ApplicationInfo applicationInfo = appEntry.info;
                return applicationInfo.enabled && !AppUtils.isInstant(applicationInfo);
            }
        };
        FILTER_EVERYTHING = new AppFilter() { // from class: com.android.settingslib.applications.ApplicationsState.14
            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public boolean filterApp(AppEntry appEntry) {
                return true;
            }

            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public void init() {
            }
        };
        FILTER_WITH_DOMAIN_URLS = new AppFilter() { // from class: com.android.settingslib.applications.ApplicationsState.15
            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public void init() {
            }

            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public boolean filterApp(AppEntry appEntry) {
                return !AppUtils.isInstant(appEntry.info) && ApplicationsState.hasFlag(appEntry.info.privateFlags, 16);
            }
        };
        FILTER_NOT_HIDE = new AppFilter() { // from class: com.android.settingslib.applications.ApplicationsState.16
            private String[] mHidePackageNames;

            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public void init() {
            }

            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public void init(Context context) {
                this.mHidePackageNames = context.getResources().getStringArray(R.array.config_deviceTabletopRotations);
            }

            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public boolean filterApp(AppEntry appEntry) {
                if (!ArrayUtils.contains(this.mHidePackageNames, appEntry.info.packageName)) {
                    return true;
                }
                ApplicationInfo applicationInfo = appEntry.info;
                return applicationInfo.enabled && applicationInfo.enabledSetting != 4;
            }
        };
        FILTER_GAMES = new AppFilter() { // from class: com.android.settingslib.applications.ApplicationsState.17
            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public void init() {
            }

            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public boolean filterApp(AppEntry appEntry) {
                boolean z;
                synchronized (appEntry.info) {
                    z = ApplicationsState.hasFlag(appEntry.info.flags, 33554432) || appEntry.info.category == 0;
                }
                return z;
            }
        };
        FILTER_AUDIO = new AppFilter() { // from class: com.android.settingslib.applications.ApplicationsState.18
            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public void init() {
            }

            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public boolean filterApp(AppEntry appEntry) {
                boolean z;
                synchronized (appEntry) {
                    z = true;
                    if (appEntry.info.category != 1) {
                        z = false;
                    }
                }
                return z;
            }
        };
        FILTER_MOVIES = new AppFilter() { // from class: com.android.settingslib.applications.ApplicationsState.19
            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public void init() {
            }

            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public boolean filterApp(AppEntry appEntry) {
                boolean z;
                synchronized (appEntry) {
                    z = appEntry.info.category == 2;
                }
                return z;
            }
        };
        FILTER_PHOTOS = new AppFilter() { // from class: com.android.settingslib.applications.ApplicationsState.20
            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public void init() {
            }

            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public boolean filterApp(AppEntry appEntry) {
                boolean z;
                synchronized (appEntry) {
                    z = appEntry.info.category == 3;
                }
                return z;
            }
        };
        FILTER_OTHER_APPS = new AppFilter() { // from class: com.android.settingslib.applications.ApplicationsState.21
            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public void init() {
            }

            @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
            public boolean filterApp(AppEntry appEntry) {
                boolean z;
                synchronized (appEntry) {
                    z = ApplicationsState.FILTER_AUDIO.filterApp(appEntry) || ApplicationsState.FILTER_GAMES.filterApp(appEntry) || ApplicationsState.FILTER_MOVIES.filterApp(appEntry) || ApplicationsState.FILTER_PHOTOS.filterApp(appEntry);
                }
                return !z;
            }
        };
    }

    public static ApplicationsState getInstance(Application application) {
        return getInstance(application, AppGlobals.getPackageManager());
    }

    static ApplicationsState getInstance(Application application, IPackageManager iPackageManager) {
        ApplicationsState applicationsState;
        synchronized (sLock) {
            if (sInstance == null) {
                sInstance = new ApplicationsState(application, iPackageManager);
            }
            applicationsState = sInstance;
        }
        return applicationsState;
    }

    void setInterestingConfigChanges(InterestingConfigChanges interestingConfigChanges) {
        this.mInterestingConfigChanges = interestingConfigChanges;
    }

    private ApplicationsState(Application application, IPackageManager iPackageManager) {
        this.mContext = application;
        this.mPm = application.getPackageManager();
        IconDrawableFactory.newInstance(this.mContext);
        this.mIpm = iPackageManager;
        this.mUm = (UserManager) this.mContext.getSystemService(UserManager.class);
        this.mStats = (StorageStatsManager) this.mContext.getSystemService(StorageStatsManager.class);
        for (int i : this.mUm.getProfileIdsWithDisabled(UserHandle.myUserId())) {
            this.mEntriesMap.put(i, new HashMap<>());
        }
        HandlerThread handlerThread = new HandlerThread("ApplicationsState.Loader");
        this.mThread = handlerThread;
        handlerThread.start();
        this.mBackgroundHandler = new BackgroundHandler(this.mThread.getLooper());
        this.mAdminRetrieveFlags = 4227584;
        this.mRetrieveFlags = 33280;
        for (ModuleInfo moduleInfo : this.mPm.getInstalledModules(0)) {
            this.mSystemModules.put(moduleInfo.getPackageName(), Boolean.valueOf(moduleInfo.isHidden()));
        }
        synchronized (this.mEntriesMap) {
            try {
                this.mEntriesMap.wait(1L);
            } catch (InterruptedException unused) {
            }
        }
    }

    public Looper getBackgroundLooper() {
        return this.mThread.getLooper();
    }

    public Session newSession(Callbacks callbacks) {
        return newSession(callbacks, null);
    }

    public Session newSession(Callbacks callbacks, Lifecycle lifecycle) {
        Session session = new Session(callbacks, lifecycle);
        synchronized (this.mEntriesMap) {
            this.mSessions.add(session);
        }
        return session;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00d7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void doResumeIfNeededLocked() {
        /*
            Method dump skipped, instruction units count: 283
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.applications.ApplicationsState.doResumeIfNeededLocked():void");
    }

    private static boolean anyAppIsRemoved(List<ApplicationInfo> list, List<ApplicationInfo> list2) {
        HashSet hashSet;
        if (list.size() == 0) {
            return false;
        }
        if (list2.size() < list.size()) {
            return true;
        }
        HashMap map = new HashMap();
        for (ApplicationInfo applicationInfo : list2) {
            String strValueOf = String.valueOf(UserHandle.getUserId(applicationInfo.uid));
            HashSet hashSet2 = (HashSet) map.get(strValueOf);
            if (hashSet2 == null) {
                hashSet2 = new HashSet();
                map.put(strValueOf, hashSet2);
            }
            if (hasFlag(applicationInfo.flags, 8388608)) {
                hashSet2.add(applicationInfo.packageName);
            }
        }
        for (ApplicationInfo applicationInfo2 : list) {
            if (hasFlag(applicationInfo2.flags, 8388608) && ((hashSet = (HashSet) map.get(String.valueOf(UserHandle.getUserId(applicationInfo2.uid)))) == null || !hashSet.remove(applicationInfo2.packageName))) {
                return true;
            }
        }
        return false;
    }

    void clearEntries() {
        for (int i = 0; i < this.mEntriesMap.size(); i++) {
            this.mEntriesMap.valueAt(i).clear();
        }
        this.mAppEntries.clear();
    }

    public boolean haveDisabledApps() {
        return this.mHaveDisabledApps;
    }

    public boolean haveInstantApps() {
        return this.mHaveInstantApps;
    }

    boolean isHiddenModule(String str) {
        Boolean bool = this.mSystemModules.get(str);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    void doPauseIfNeededLocked() {
        if (this.mResumed) {
            for (int i = 0; i < this.mSessions.size(); i++) {
                if (this.mSessions.get(i).mResumed) {
                    return;
                }
            }
            doPauseLocked();
        }
    }

    void doPauseLocked() {
        this.mResumed = false;
        PackageIntentReceiver packageIntentReceiver = this.mPackageIntentReceiver;
        if (packageIntentReceiver != null) {
            packageIntentReceiver.unregisterReceiver();
            this.mPackageIntentReceiver = null;
        }
    }

    public AppEntry getEntry(String str, int i) {
        AppEntry entryLocked;
        synchronized (this.mEntriesMap) {
            entryLocked = this.mEntriesMap.get(i).get(str);
            if (entryLocked == null) {
                ApplicationInfo appInfoLocked = getAppInfoLocked(str, i);
                if (appInfoLocked == null) {
                    try {
                        appInfoLocked = this.mIpm.getApplicationInfo(str, 0, i);
                    } catch (RemoteException e) {
                        Log.w("ApplicationsState", "getEntry couldn't reach PackageManager", e);
                        return null;
                    }
                }
                if (appInfoLocked != null) {
                    entryLocked = getEntryLocked(appInfoLocked);
                }
            }
        }
        return entryLocked;
    }

    private ApplicationInfo getAppInfoLocked(String str, int i) {
        for (int i2 = 0; i2 < this.mApplications.size(); i2++) {
            ApplicationInfo applicationInfo = this.mApplications.get(i2);
            if (str.equals(applicationInfo.packageName) && i == UserHandle.getUserId(applicationInfo.uid)) {
                return applicationInfo;
            }
        }
        return null;
    }

    public void ensureIcon(AppEntry appEntry) {
        if (appEntry.icon != null) {
            return;
        }
        synchronized (appEntry) {
            appEntry.ensureIconLocked(this.mContext);
        }
    }

    public void requestSize(final String str, final int i) {
        synchronized (this.mEntriesMap) {
            final AppEntry appEntry = this.mEntriesMap.get(i).get(str);
            if (appEntry != null && hasFlag(appEntry.info.flags, 8388608)) {
                this.mBackgroundHandler.post(new Runnable() { // from class: com.android.settingslib.applications.-$$Lambda$ApplicationsState$LuXUFbWTiS5lu-nO9WUp0g2nHmU
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$requestSize$0$ApplicationsState(appEntry, str, i);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$requestSize$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$requestSize$0$ApplicationsState(AppEntry appEntry, String str, int i) {
        try {
            try {
                StorageStats storageStatsQueryStatsForPackage = this.mStats.queryStatsForPackage(appEntry.info.storageUuid, str, UserHandle.of(i));
                long cacheQuotaBytes = this.mStats.getCacheQuotaBytes(appEntry.info.storageUuid.toString(), appEntry.info.uid);
                PackageStats packageStats = new PackageStats(str, i);
                packageStats.codeSize = storageStatsQueryStatsForPackage.getCodeBytes();
                packageStats.dataSize = storageStatsQueryStatsForPackage.getDataBytes();
                packageStats.cacheSize = Math.min(storageStatsQueryStatsForPackage.getCacheBytes(), cacheQuotaBytes);
                this.mBackgroundHandler.mStatsObserver.onGetStatsCompleted(packageStats, true);
            } catch (PackageManager.NameNotFoundException | IOException e) {
                Log.w("ApplicationsState", "Failed to query stats: " + e);
                this.mBackgroundHandler.mStatsObserver.onGetStatsCompleted((PackageStats) null, false);
            }
        } catch (RemoteException unused) {
        }
    }

    int indexOfApplicationInfoLocked(String str, int i) {
        for (int size = this.mApplications.size() - 1; size >= 0; size--) {
            ApplicationInfo applicationInfo = this.mApplications.get(size);
            if (applicationInfo.packageName.equals(str) && UserHandle.getUserId(applicationInfo.uid) == i) {
                return size;
            }
        }
        return -1;
    }

    void addPackage(String str, int i) {
        try {
            synchronized (this.mEntriesMap) {
                if (this.mResumed) {
                    if (indexOfApplicationInfoLocked(str, i) >= 0) {
                        return;
                    }
                    ApplicationInfo applicationInfo = this.mIpm.getApplicationInfo(str, this.mUm.isUserAdmin(i) ? this.mAdminRetrieveFlags : this.mRetrieveFlags, i);
                    if (applicationInfo == null) {
                        return;
                    }
                    if (!applicationInfo.enabled) {
                        if (applicationInfo.enabledSetting != 3) {
                            return;
                        } else {
                            this.mHaveDisabledApps = true;
                        }
                    }
                    if (AppUtils.isInstant(applicationInfo)) {
                        this.mHaveInstantApps = true;
                    }
                    this.mApplications.add(applicationInfo);
                    if (!this.mBackgroundHandler.hasMessages(2)) {
                        this.mBackgroundHandler.sendEmptyMessage(2);
                    }
                    if (!this.mMainHandler.hasMessages(2)) {
                        this.mMainHandler.sendEmptyMessage(2);
                    }
                }
            }
        } catch (RemoteException unused) {
        }
    }

    public void removePackage(String str, int i) {
        synchronized (this.mEntriesMap) {
            int iIndexOfApplicationInfoLocked = indexOfApplicationInfoLocked(str, i);
            if (iIndexOfApplicationInfoLocked >= 0) {
                AppEntry appEntry = this.mEntriesMap.get(i).get(str);
                if (appEntry != null) {
                    this.mEntriesMap.get(i).remove(str);
                    this.mAppEntries.remove(appEntry);
                }
                ApplicationInfo applicationInfo = this.mApplications.get(iIndexOfApplicationInfoLocked);
                this.mApplications.remove(iIndexOfApplicationInfoLocked);
                if (!applicationInfo.enabled) {
                    this.mHaveDisabledApps = false;
                    Iterator<ApplicationInfo> it = this.mApplications.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        } else if (!it.next().enabled) {
                            this.mHaveDisabledApps = true;
                            break;
                        }
                    }
                }
                if (AppUtils.isInstant(applicationInfo)) {
                    this.mHaveInstantApps = false;
                    Iterator<ApplicationInfo> it2 = this.mApplications.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        } else if (AppUtils.isInstant(it2.next())) {
                            this.mHaveInstantApps = true;
                            break;
                        }
                    }
                }
                if (!this.mMainHandler.hasMessages(2)) {
                    this.mMainHandler.sendEmptyMessage(2);
                }
            }
        }
    }

    public void invalidatePackage(String str, int i) {
        removePackage(str, i);
        addPackage(str, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addUser(int i) {
        if (ArrayUtils.contains(this.mUm.getProfileIdsWithDisabled(UserHandle.myUserId()), i)) {
            synchronized (this.mEntriesMap) {
                this.mEntriesMap.put(i, new HashMap<>());
                if (this.mResumed) {
                    doPauseLocked();
                    doResumeIfNeededLocked();
                }
                if (!this.mMainHandler.hasMessages(2)) {
                    this.mMainHandler.sendEmptyMessage(2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeUser(int i) {
        synchronized (this.mEntriesMap) {
            HashMap<String, AppEntry> map = this.mEntriesMap.get(i);
            if (map != null) {
                for (AppEntry appEntry : map.values()) {
                    this.mAppEntries.remove(appEntry);
                    this.mApplications.remove(appEntry.info);
                }
                this.mEntriesMap.remove(i);
                if (!this.mMainHandler.hasMessages(2)) {
                    this.mMainHandler.sendEmptyMessage(2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AppEntry getEntryLocked(ApplicationInfo applicationInfo) {
        int userId = UserHandle.getUserId(applicationInfo.uid);
        AppEntry appEntry = this.mEntriesMap.get(userId).get(applicationInfo.packageName);
        if (appEntry == null) {
            if (isHiddenModule(applicationInfo.packageName)) {
                return null;
            }
            Context context = this.mContext;
            long j = this.mCurId;
            this.mCurId = 1 + j;
            AppEntry appEntry2 = new AppEntry(context, applicationInfo, j);
            this.mEntriesMap.get(userId).put(applicationInfo.packageName, appEntry2);
            this.mAppEntries.add(appEntry2);
            return appEntry2;
        }
        if (appEntry.info == applicationInfo) {
            return appEntry;
        }
        appEntry.info = applicationInfo;
        return appEntry;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getTotalInternalSize(PackageStats packageStats) {
        if (packageStats != null) {
            return (packageStats.codeSize + packageStats.dataSize) - packageStats.cacheSize;
        }
        return -2L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getTotalExternalSize(PackageStats packageStats) {
        if (packageStats != null) {
            return packageStats.externalCodeSize + packageStats.externalDataSize + packageStats.externalCacheSize + packageStats.externalMediaSize + packageStats.externalObbSize;
        }
        return -2L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getSizeStr(long j) {
        if (j >= 0) {
            return Formatter.formatFileSize(this.mContext, j);
        }
        return null;
    }

    void rebuildActiveSessions() {
        synchronized (this.mEntriesMap) {
            if (this.mSessionsChanged) {
                this.mActiveSessions.clear();
                for (int i = 0; i < this.mSessions.size(); i++) {
                    Session session = this.mSessions.get(i);
                    if (session.mResumed) {
                        this.mActiveSessions.add(new WeakReference<>(session));
                    }
                }
            }
        }
    }

    public class Session implements LifecycleObserver {
        final Callbacks mCallbacks;
        private final boolean mHasLifecycle;
        ArrayList<AppEntry> mLastAppList;
        boolean mRebuildAsync;
        Comparator<AppEntry> mRebuildComparator;
        AppFilter mRebuildFilter;
        boolean mRebuildForeground;
        boolean mRebuildRequested;
        boolean mResumed;
        final Object mRebuildSync = new Object();
        private int mFlags = 15;

        Session(Callbacks callbacks, Lifecycle lifecycle) {
            this.mCallbacks = callbacks;
            if (lifecycle != null) {
                lifecycle.addObserver(this);
                this.mHasLifecycle = true;
            } else {
                this.mHasLifecycle = false;
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public void onResume() {
            synchronized (ApplicationsState.this.mEntriesMap) {
                if (!this.mResumed) {
                    this.mResumed = true;
                    ApplicationsState.this.mSessionsChanged = true;
                    ApplicationsState.this.doPauseLocked();
                    ApplicationsState.this.doResumeIfNeededLocked();
                }
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        public void onPause() {
            synchronized (ApplicationsState.this.mEntriesMap) {
                if (this.mResumed) {
                    this.mResumed = false;
                    ApplicationsState.this.mSessionsChanged = true;
                    ApplicationsState.this.mBackgroundHandler.removeMessages(1, this);
                    ApplicationsState.this.doPauseIfNeededLocked();
                }
            }
        }

        public ArrayList<AppEntry> getAllApps() {
            ArrayList<AppEntry> arrayList;
            synchronized (ApplicationsState.this.mEntriesMap) {
                arrayList = new ArrayList<>(ApplicationsState.this.mAppEntries);
            }
            return arrayList;
        }

        public ArrayList<AppEntry> rebuild(AppFilter appFilter, Comparator<AppEntry> comparator) {
            return rebuild(appFilter, comparator, true);
        }

        public ArrayList<AppEntry> rebuild(AppFilter appFilter, Comparator<AppEntry> comparator, boolean z) {
            synchronized (this.mRebuildSync) {
                synchronized (ApplicationsState.this.mRebuildingSessions) {
                    ApplicationsState.this.mRebuildingSessions.add(this);
                    this.mRebuildRequested = true;
                    this.mRebuildAsync = true;
                    this.mRebuildFilter = appFilter;
                    this.mRebuildComparator = comparator;
                    this.mRebuildForeground = z;
                    if (!ApplicationsState.this.mBackgroundHandler.hasMessages(1)) {
                        ApplicationsState.this.mBackgroundHandler.sendMessage(ApplicationsState.this.mBackgroundHandler.obtainMessage(1));
                    }
                }
            }
            return null;
        }

        void handleRebuildList() {
            ArrayList<AppEntry> arrayList;
            if (this.mResumed) {
                synchronized (this.mRebuildSync) {
                    if (this.mRebuildRequested) {
                        AppFilter appFilter = this.mRebuildFilter;
                        Comparator<AppEntry> comparator = this.mRebuildComparator;
                        this.mRebuildRequested = false;
                        this.mRebuildFilter = null;
                        this.mRebuildComparator = null;
                        if (this.mRebuildForeground) {
                            Process.setThreadPriority(-2);
                            this.mRebuildForeground = false;
                        }
                        if (appFilter != null) {
                            appFilter.init(ApplicationsState.this.mContext);
                        }
                        synchronized (ApplicationsState.this.mEntriesMap) {
                            arrayList = new ArrayList(ApplicationsState.this.mAppEntries);
                        }
                        ArrayList<AppEntry> arrayList2 = new ArrayList<>();
                        for (AppEntry appEntry : arrayList) {
                            if (appEntry != null && (appFilter == null || appFilter.filterApp(appEntry))) {
                                synchronized (ApplicationsState.this.mEntriesMap) {
                                    if (comparator != null) {
                                        appEntry.ensureLabel(ApplicationsState.this.mContext);
                                    }
                                    arrayList2.add(appEntry);
                                }
                            }
                        }
                        if (comparator != null) {
                            synchronized (ApplicationsState.this.mEntriesMap) {
                                Collections.sort(arrayList2, comparator);
                            }
                        }
                        synchronized (this.mRebuildSync) {
                            if (!this.mRebuildRequested) {
                                this.mLastAppList = arrayList2;
                                if (!this.mRebuildAsync) {
                                    this.mRebuildSync.notifyAll();
                                } else if (!ApplicationsState.this.mMainHandler.hasMessages(1, this)) {
                                    ApplicationsState.this.mMainHandler.sendMessage(ApplicationsState.this.mMainHandler.obtainMessage(1, this));
                                }
                            }
                        }
                        Process.setThreadPriority(10);
                    }
                }
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void onDestroy() {
            if (!this.mHasLifecycle) {
                onPause();
            }
            synchronized (ApplicationsState.this.mEntriesMap) {
                ApplicationsState.this.mSessions.remove(this);
            }
        }
    }

    class MainHandler extends Handler {
        public MainHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ApplicationsState.this.rebuildActiveSessions();
            switch (message.what) {
                case 1:
                    Session session = (Session) message.obj;
                    Iterator<WeakReference<Session>> it = ApplicationsState.this.mActiveSessions.iterator();
                    while (it.hasNext()) {
                        Session session2 = it.next().get();
                        if (session2 != null && session2 == session) {
                            session.mCallbacks.onRebuildComplete(session.mLastAppList);
                        }
                    }
                    break;
                case 2:
                    Iterator<WeakReference<Session>> it2 = ApplicationsState.this.mActiveSessions.iterator();
                    while (it2.hasNext()) {
                        Session session3 = it2.next().get();
                        if (session3 != null) {
                            session3.mCallbacks.onPackageListChanged();
                        }
                    }
                    break;
                case 3:
                    Iterator<WeakReference<Session>> it3 = ApplicationsState.this.mActiveSessions.iterator();
                    while (it3.hasNext()) {
                        Session session4 = it3.next().get();
                        if (session4 != null) {
                            session4.mCallbacks.onPackageIconChanged();
                        }
                    }
                    break;
                case 4:
                    Iterator<WeakReference<Session>> it4 = ApplicationsState.this.mActiveSessions.iterator();
                    while (it4.hasNext()) {
                        Session session5 = it4.next().get();
                        if (session5 != null) {
                            session5.mCallbacks.onPackageSizeChanged((String) message.obj);
                        }
                    }
                    break;
                case 5:
                    Iterator<WeakReference<Session>> it5 = ApplicationsState.this.mActiveSessions.iterator();
                    while (it5.hasNext()) {
                        Session session6 = it5.next().get();
                        if (session6 != null) {
                            session6.mCallbacks.onAllSizesComputed();
                        }
                    }
                    break;
                case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                    Iterator<WeakReference<Session>> it6 = ApplicationsState.this.mActiveSessions.iterator();
                    while (it6.hasNext()) {
                        Session session7 = it6.next().get();
                        if (session7 != null) {
                            session7.mCallbacks.onRunningStateChanged(message.arg1 != 0);
                        }
                    }
                    break;
                case 7:
                    Iterator<WeakReference<Session>> it7 = ApplicationsState.this.mActiveSessions.iterator();
                    while (it7.hasNext()) {
                        Session session8 = it7.next().get();
                        if (session8 != null) {
                            session8.mCallbacks.onLauncherInfoChanged();
                        }
                    }
                    break;
                case 8:
                    Iterator<WeakReference<Session>> it8 = ApplicationsState.this.mActiveSessions.iterator();
                    while (it8.hasNext()) {
                        Session session9 = it8.next().get();
                        if (session9 != null) {
                            session9.mCallbacks.onLoadEntriesCompleted();
                        }
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class BackgroundHandler extends Handler {
        boolean mRunning;
        final IPackageStatsObserver.Stub mStatsObserver;

        BackgroundHandler(Looper looper) {
            super(looper);
            this.mStatsObserver = new IPackageStatsObserver.Stub() { // from class: com.android.settingslib.applications.ApplicationsState.BackgroundHandler.1
                public void onGetStatsCompleted(PackageStats packageStats, boolean z) {
                    boolean z2;
                    if (z) {
                        synchronized (ApplicationsState.this.mEntriesMap) {
                            HashMap<String, AppEntry> map = ApplicationsState.this.mEntriesMap.get(packageStats.userHandle);
                            if (map == null) {
                                return;
                            }
                            AppEntry appEntry = map.get(packageStats.packageName);
                            if (appEntry != null) {
                                synchronized (appEntry) {
                                    z2 = false;
                                    appEntry.sizeStale = false;
                                    appEntry.sizeLoadStart = 0L;
                                    long j = packageStats.externalCodeSize + packageStats.externalObbSize;
                                    long j2 = packageStats.externalDataSize + packageStats.externalMediaSize;
                                    long totalInternalSize = j + j2 + ApplicationsState.this.getTotalInternalSize(packageStats);
                                    if (appEntry.size != totalInternalSize || appEntry.cacheSize != packageStats.cacheSize || appEntry.codeSize != packageStats.codeSize || appEntry.dataSize != packageStats.dataSize || appEntry.externalCodeSize != j || appEntry.externalDataSize != j2 || appEntry.externalCacheSize != packageStats.externalCacheSize) {
                                        appEntry.size = totalInternalSize;
                                        appEntry.cacheSize = packageStats.cacheSize;
                                        appEntry.codeSize = packageStats.codeSize;
                                        appEntry.dataSize = packageStats.dataSize;
                                        appEntry.externalCodeSize = j;
                                        appEntry.externalDataSize = j2;
                                        appEntry.externalCacheSize = packageStats.externalCacheSize;
                                        appEntry.sizeStr = ApplicationsState.this.getSizeStr(totalInternalSize);
                                        long totalInternalSize2 = ApplicationsState.this.getTotalInternalSize(packageStats);
                                        appEntry.internalSize = totalInternalSize2;
                                        appEntry.internalSizeStr = ApplicationsState.this.getSizeStr(totalInternalSize2);
                                        long totalExternalSize = ApplicationsState.this.getTotalExternalSize(packageStats);
                                        appEntry.externalSize = totalExternalSize;
                                        appEntry.externalSizeStr = ApplicationsState.this.getSizeStr(totalExternalSize);
                                        z2 = true;
                                    }
                                }
                                if (z2) {
                                    ApplicationsState.this.mMainHandler.sendMessage(ApplicationsState.this.mMainHandler.obtainMessage(4, packageStats.packageName));
                                }
                            }
                            if (ApplicationsState.this.mCurComputingSizePkg != null && ApplicationsState.this.mCurComputingSizePkg.equals(packageStats.packageName) && ApplicationsState.this.mCurComputingSizeUserId == packageStats.userHandle) {
                                ApplicationsState.this.mCurComputingSizePkg = null;
                                BackgroundHandler.this.sendEmptyMessage(7);
                            }
                        }
                    }
                }
            };
        }

        /* JADX WARN: Removed duplicated region for block: B:165:0x0363  */
        @Override // android.os.Handler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void handleMessage(android.os.Message r19) {
            /*
                Method dump skipped, instruction units count: 924
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.applications.ApplicationsState.BackgroundHandler.handleMessage(android.os.Message):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$handleMessage$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$handleMessage$0$ApplicationsState$BackgroundHandler() {
            try {
                try {
                    StorageStats storageStatsQueryStatsForPackage = ApplicationsState.this.mStats.queryStatsForPackage(ApplicationsState.this.mCurComputingSizeUuid, ApplicationsState.this.mCurComputingSizePkg, UserHandle.of(ApplicationsState.this.mCurComputingSizeUserId));
                    PackageStats packageStats = new PackageStats(ApplicationsState.this.mCurComputingSizePkg, ApplicationsState.this.mCurComputingSizeUserId);
                    packageStats.codeSize = storageStatsQueryStatsForPackage.getCodeBytes();
                    packageStats.dataSize = storageStatsQueryStatsForPackage.getDataBytes();
                    packageStats.cacheSize = storageStatsQueryStatsForPackage.getCacheBytes();
                    this.mStatsObserver.onGetStatsCompleted(packageStats, true);
                } catch (PackageManager.NameNotFoundException | IOException e) {
                    Log.w("ApplicationsState", "Failed to query stats: " + e);
                    this.mStatsObserver.onGetStatsCompleted((PackageStats) null, false);
                }
            } catch (RemoteException unused) {
            }
        }

        private int getCombinedSessionFlags(List<Session> list) {
            int i;
            synchronized (ApplicationsState.this.mEntriesMap) {
                i = 0;
                Iterator<Session> it = list.iterator();
                while (it.hasNext()) {
                    i |= it.next().mFlags;
                }
            }
            return i;
        }
    }

    private class PackageIntentReceiver extends BroadcastReceiver {
        private PackageIntentReceiver() {
        }

        void registerReceiver() {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
            intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
            intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
            intentFilter.addDataScheme("package");
            ApplicationsState.this.mContext.registerReceiver(this, intentFilter);
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE");
            intentFilter2.addAction("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE");
            ApplicationsState.this.mContext.registerReceiver(this, intentFilter2);
            IntentFilter intentFilter3 = new IntentFilter();
            intentFilter3.addAction("android.intent.action.USER_ADDED");
            intentFilter3.addAction("android.intent.action.USER_REMOVED");
            ApplicationsState.this.mContext.registerReceiver(this, intentFilter3);
        }

        void unregisterReceiver() {
            ApplicationsState.this.mContext.unregisterReceiver(this);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            int i = 0;
            if ("android.intent.action.PACKAGE_ADDED".equals(action)) {
                String encodedSchemeSpecificPart = intent.getData().getEncodedSchemeSpecificPart();
                while (i < ApplicationsState.this.mEntriesMap.size()) {
                    ApplicationsState applicationsState = ApplicationsState.this;
                    applicationsState.addPackage(encodedSchemeSpecificPart, applicationsState.mEntriesMap.keyAt(i));
                    i++;
                }
                return;
            }
            if ("android.intent.action.PACKAGE_REMOVED".equals(action)) {
                String encodedSchemeSpecificPart2 = intent.getData().getEncodedSchemeSpecificPart();
                while (i < ApplicationsState.this.mEntriesMap.size()) {
                    ApplicationsState applicationsState2 = ApplicationsState.this;
                    applicationsState2.removePackage(encodedSchemeSpecificPart2, applicationsState2.mEntriesMap.keyAt(i));
                    i++;
                }
                return;
            }
            if ("android.intent.action.PACKAGE_CHANGED".equals(action)) {
                String encodedSchemeSpecificPart3 = intent.getData().getEncodedSchemeSpecificPart();
                while (i < ApplicationsState.this.mEntriesMap.size()) {
                    ApplicationsState applicationsState3 = ApplicationsState.this;
                    applicationsState3.invalidatePackage(encodedSchemeSpecificPart3, applicationsState3.mEntriesMap.keyAt(i));
                    i++;
                }
                return;
            }
            if ("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE".equals(action) || "android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE".equals(action)) {
                String[] stringArrayExtra = intent.getStringArrayExtra("android.intent.extra.changed_package_list");
                if (stringArrayExtra == null || stringArrayExtra.length == 0 || !"android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE".equals(action)) {
                    return;
                }
                for (String str : stringArrayExtra) {
                    for (int i2 = 0; i2 < ApplicationsState.this.mEntriesMap.size(); i2++) {
                        ApplicationsState applicationsState4 = ApplicationsState.this;
                        applicationsState4.invalidatePackage(str, applicationsState4.mEntriesMap.keyAt(i2));
                    }
                }
                return;
            }
            if ("android.intent.action.USER_ADDED".equals(action)) {
                ApplicationsState.this.addUser(intent.getIntExtra("android.intent.extra.user_handle", -10000));
            } else if ("android.intent.action.USER_REMOVED".equals(action)) {
                ApplicationsState.this.removeUser(intent.getIntExtra("android.intent.extra.user_handle", -10000));
            }
        }
    }

    public static class AppEntry extends SizeInfo {
        public final File apkFile;
        public long externalSize;
        public String externalSizeStr;
        public Object extraInfo;
        public boolean hasLauncherEntry;
        public Drawable icon;
        public final long id;
        public ApplicationInfo info;
        public long internalSize;
        public String internalSizeStr;
        public boolean isHomeApp;
        public String label;
        public String labelDescription;
        public boolean launcherEntryEnabled;
        public boolean mounted;
        public long sizeLoadStart;
        public String sizeStr;
        public long size = -1;
        public boolean sizeStale = true;

        public AppEntry(final Context context, ApplicationInfo applicationInfo, long j) {
            this.apkFile = new File(applicationInfo.sourceDir);
            this.id = j;
            this.info = applicationInfo;
            ensureLabel(context);
            ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.settingslib.applications.-$$Lambda$ApplicationsState$AppEntry$CHcrAV6RtpyvpsjQKTHb_OQPQ1I
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$new$0$ApplicationsState$AppEntry(context);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$new$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$new$0$ApplicationsState$AppEntry(Context context) {
            if (this.icon == null) {
                ensureIconLocked(context);
            }
            if (this.labelDescription == null) {
                ensureLabelDescriptionLocked(context);
            }
        }

        public void ensureLabel(Context context) {
            if (this.label == null || !this.mounted) {
                if (!this.apkFile.exists()) {
                    this.mounted = false;
                    this.label = this.info.packageName;
                } else {
                    this.mounted = true;
                    CharSequence charSequenceLoadLabel = this.info.loadLabel(context.getPackageManager());
                    this.label = charSequenceLoadLabel != null ? charSequenceLoadLabel.toString() : this.info.packageName;
                }
            }
        }

        boolean ensureIconLocked(Context context) {
            if (this.icon == null) {
                if (this.apkFile.exists()) {
                    this.icon = Utils.getBadgedIcon(context, this.info);
                    return true;
                }
                this.mounted = false;
                this.icon = context.getDrawable(R.drawable.pointer_wait_vector_59);
            } else if (!this.mounted && this.apkFile.exists()) {
                this.mounted = true;
                this.icon = Utils.getBadgedIcon(context, this.info);
                return true;
            }
            return false;
        }

        public void ensureLabelDescriptionLocked(Context context) {
            if (UserManager.get(context).isManagedProfile(UserHandle.getUserId(this.info.uid))) {
                this.labelDescription = context.getString(R$string.accessibility_work_profile_app_description, this.label);
            } else {
                this.labelDescription = this.label;
            }
        }
    }

    public interface AppFilter {
        boolean filterApp(AppEntry appEntry);

        void init();

        default void init(Context context) {
            init();
        }
    }

    public static class VolumeFilter implements AppFilter {
        private final String mVolumeUuid;

        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public void init() {
        }

        public VolumeFilter(String str) {
            this.mVolumeUuid = str;
        }

        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public boolean filterApp(AppEntry appEntry) {
            return Objects.equals(appEntry.info.volumeUuid, this.mVolumeUuid);
        }
    }

    public static class CompoundFilter implements AppFilter {
        private final AppFilter mFirstFilter;
        private final AppFilter mSecondFilter;

        public CompoundFilter(AppFilter appFilter, AppFilter appFilter2) {
            this.mFirstFilter = appFilter;
            this.mSecondFilter = appFilter2;
        }

        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public void init(Context context) {
            this.mFirstFilter.init(context);
            this.mSecondFilter.init(context);
        }

        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public void init() {
            this.mFirstFilter.init();
            this.mSecondFilter.init();
        }

        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public boolean filterApp(AppEntry appEntry) {
            return this.mFirstFilter.filterApp(appEntry) && this.mSecondFilter.filterApp(appEntry);
        }
    }
}
