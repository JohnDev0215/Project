package com.android.launcher3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.compat.PackageInstallerCompat;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.icons.IconCache;
import com.android.launcher3.icons.LauncherIcons;
import com.android.launcher3.model.AddWorkspaceItemsTask;
import com.android.launcher3.model.BaseModelUpdateTask;
import com.android.launcher3.model.BgDataModel;
import com.android.launcher3.model.CacheDataUpdatedTask;
import com.android.launcher3.model.LoaderResults;
import com.android.launcher3.model.LoaderTask;
import com.android.launcher3.model.ModelWriter;
import com.android.launcher3.model.PackageInstallStateChangedTask;
import com.android.launcher3.model.PackageUpdatedTask;
import com.android.launcher3.model.ShortcutsChangedTask;
import com.android.launcher3.model.UserLockStateChangedTask;
import com.android.launcher3.shortcuts.DeepShortcutManager;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.IntArray;
import com.android.launcher3.util.ItemInfoMatcher;
import com.android.launcher3.util.PackageUserKey;
import com.android.launcher3.util.Preconditions;
import com.android.launcher3.util.ViewOnDrawExecutor;
import com.android.launcher3.widget.WidgetListRowEntry;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes4.dex */
public class LauncherModel extends BroadcastReceiver implements LauncherAppsCompat.OnAppsChangedCallbackCompat {
    private static final boolean DEBUG_RECEIVER = false;
    static final String TAG = "Launcher.Model";
    private static final Looper mWorkerLooper;
    static final BgDataModel sBgDataModel;
    static final Handler sWorker;
    static final HandlerThread sWorkerThread;
    final LauncherAppState mApp;
    private final AllAppsList mBgAllAppsList;
    WeakReference<Callbacks> mCallbacks;
    boolean mIsLoaderTaskRunning;
    LoaderTask mLoaderTask;
    private boolean mModelLoaded;
    private final MainThreadExecutor mUiExecutor = new MainThreadExecutor();
    final Object mLock = new Object();
    private final Runnable mShortcutPermissionCheckRunnable = new Runnable() { // from class: com.android.launcher3.LauncherModel.1
        @Override // java.lang.Runnable
        public void run() {
            if (!LauncherModel.this.mModelLoaded || DeepShortcutManager.getInstance(LauncherModel.this.mApp.getContext()).hasHostPermission() == LauncherModel.sBgDataModel.hasShortcutHostPermission) {
                return;
            }
            LauncherModel.this.forceReload();
        }
    };

    public interface CallbackTask {
        void execute(Callbacks callbacks);
    }

    public interface Callbacks {
        void bindAllApplications(ArrayList<AppInfo> apps);

        void bindAllWidgets(ArrayList<WidgetListRowEntry> widgets);

        void bindAppInfosRemoved(ArrayList<AppInfo> appInfos);

        void bindAppsAdded(IntArray newScreens, ArrayList<ItemInfo> addNotAnimated, ArrayList<ItemInfo> addAnimated);

        void bindAppsAddedOrUpdated(ArrayList<AppInfo> apps);

        void bindDeepShortcutMap(HashMap<ComponentKey, Integer> deepShortcutMap);

        void bindItems(List<ItemInfo> shortcuts, boolean forceAnimateIcons);

        void bindPromiseAppProgressUpdated(PromiseAppInfo app);

        void bindRestoreItemsChange(HashSet<ItemInfo> updates);

        void bindScreens(IntArray orderedScreenIds);

        void bindWidgetsRestored(ArrayList<LauncherAppWidgetInfo> widgets);

        void bindWorkspaceComponentsRemoved(ItemInfoMatcher matcher);

        void bindWorkspaceItemsChanged(ArrayList<WorkspaceItemInfo> updated);

        void clearPendingBinds();

        void executeOnNextDraw(ViewOnDrawExecutor executor);

        void finishBindingItems(int pageBoundFirst);

        void finishFirstPageBind(ViewOnDrawExecutor executor);

        int getCurrentWorkspaceScreen();

        void onPageBoundSynchronously(int page);

        void preAddApps();

        void rebindModel();

        void startBinding();
    }

    public interface ModelUpdateTask extends Runnable {
        void init(LauncherAppState app, LauncherModel model, BgDataModel dataModel, AllAppsList allAppsList, Executor uiExecutor);
    }

    static {
        HandlerThread handlerThread = new HandlerThread("launcher-loader");
        sWorkerThread = handlerThread;
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        mWorkerLooper = looper;
        sWorker = new Handler(looper);
        sBgDataModel = new BgDataModel();
    }

    public boolean isModelLoaded() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mModelLoaded && this.mLoaderTask == null;
        }
        return z;
    }

    LauncherModel(LauncherAppState app, IconCache iconCache, AppFilter appFilter) {
        this.mApp = app;
        this.mBgAllAppsList = new AllAppsList(iconCache, appFilter);
    }

    public void setPackageState(PackageInstallerCompat.PackageInstallInfo installInfo) {
        enqueueModelUpdateTask(new PackageInstallStateChangedTask(installInfo));
    }

    public void updateSessionDisplayInfo(final String packageName) {
        HashSet hashSet = new HashSet();
        hashSet.add(packageName);
        enqueueModelUpdateTask(new CacheDataUpdatedTask(2, Process.myUserHandle(), hashSet));
    }

    public void addAndBindAddedWorkspaceItems(List<Pair<ItemInfo, Object>> itemList) {
        Callbacks callback = getCallback();
        if (callback != null) {
            callback.preAddApps();
        }
        enqueueModelUpdateTask(new AddWorkspaceItemsTask(itemList));
    }

    public ModelWriter getWriter(boolean hasVerticalHotseat, boolean verifyChanges) {
        return new ModelWriter(this.mApp.getContext(), this, sBgDataModel, hasVerticalHotseat, verifyChanges);
    }

    public void initialize(Callbacks callbacks) {
        synchronized (this.mLock) {
            Preconditions.assertUIThread();
            this.mCallbacks = new WeakReference<>(callbacks);
        }
    }

    @Override // com.android.launcher3.compat.LauncherAppsCompat.OnAppsChangedCallbackCompat
    public void onPackageChanged(String packageName, UserHandle user) {
        enqueueModelUpdateTask(new PackageUpdatedTask(2, user, packageName));
    }

    @Override // com.android.launcher3.compat.LauncherAppsCompat.OnAppsChangedCallbackCompat
    public void onPackageRemoved(String packageName, UserHandle user) {
        onPackagesRemoved(user, packageName);
    }

    public void onPackagesRemoved(UserHandle user, String... packages) {
        enqueueModelUpdateTask(new PackageUpdatedTask(3, user, packages));
    }

    @Override // com.android.launcher3.compat.LauncherAppsCompat.OnAppsChangedCallbackCompat
    public void onPackageAdded(String packageName, UserHandle user) {
        enqueueModelUpdateTask(new PackageUpdatedTask(1, user, packageName));
    }

    @Override // com.android.launcher3.compat.LauncherAppsCompat.OnAppsChangedCallbackCompat
    public void onPackagesAvailable(String[] packageNames, UserHandle user, boolean replacing) {
        enqueueModelUpdateTask(new PackageUpdatedTask(2, user, packageNames));
    }

    @Override // com.android.launcher3.compat.LauncherAppsCompat.OnAppsChangedCallbackCompat
    public void onPackagesUnavailable(String[] packageNames, UserHandle user, boolean replacing) {
        if (replacing) {
            return;
        }
        enqueueModelUpdateTask(new PackageUpdatedTask(4, user, packageNames));
    }

    @Override // com.android.launcher3.compat.LauncherAppsCompat.OnAppsChangedCallbackCompat
    public void onPackagesSuspended(String[] packageNames, UserHandle user) {
        enqueueModelUpdateTask(new PackageUpdatedTask(5, user, packageNames));
    }

    @Override // com.android.launcher3.compat.LauncherAppsCompat.OnAppsChangedCallbackCompat
    public void onPackagesUnsuspended(String[] packageNames, UserHandle user) {
        enqueueModelUpdateTask(new PackageUpdatedTask(6, user, packageNames));
    }

    @Override // com.android.launcher3.compat.LauncherAppsCompat.OnAppsChangedCallbackCompat
    public void onShortcutsChanged(String packageName, List<ShortcutInfo> shortcuts, UserHandle user) {
        enqueueModelUpdateTask(new ShortcutsChangedTask(packageName, shortcuts, user, true));
    }

    public void updatePinnedShortcuts(String packageName, List<ShortcutInfo> shortcuts, UserHandle user) {
        enqueueModelUpdateTask(new ShortcutsChangedTask(packageName, shortcuts, user, false));
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        UserHandle userHandle;
        String action = intent.getAction();
        if (Intent.ACTION_LOCALE_CHANGED.equals(action)) {
            forceReload();
            return;
        }
        if (Intent.ACTION_MANAGED_PROFILE_ADDED.equals(action) || Intent.ACTION_MANAGED_PROFILE_REMOVED.equals(action)) {
            UserManagerCompat.getInstance(context).enableAndResetCache();
            forceReload();
            return;
        }
        if ((Intent.ACTION_MANAGED_PROFILE_AVAILABLE.equals(action) || Intent.ACTION_MANAGED_PROFILE_UNAVAILABLE.equals(action) || Intent.ACTION_MANAGED_PROFILE_UNLOCKED.equals(action)) && (userHandle = (UserHandle) intent.getParcelableExtra(Intent.EXTRA_USER)) != null) {
            if (Intent.ACTION_MANAGED_PROFILE_AVAILABLE.equals(action) || Intent.ACTION_MANAGED_PROFILE_UNAVAILABLE.equals(action)) {
                enqueueModelUpdateTask(new PackageUpdatedTask(7, userHandle, new String[0]));
            }
            if (Intent.ACTION_MANAGED_PROFILE_UNAVAILABLE.equals(action) || Intent.ACTION_MANAGED_PROFILE_UNLOCKED.equals(action)) {
                enqueueModelUpdateTask(new UserLockStateChangedTask(userHandle));
            }
        }
    }

    public void forceReload() {
        forceReload(-1);
    }

    public void forceReload(int synchronousBindPage) {
        synchronized (this.mLock) {
            stopLoader();
            this.mModelLoaded = false;
        }
        Callbacks callback = getCallback();
        if (callback != null) {
            if (synchronousBindPage < 0) {
                synchronousBindPage = callback.getCurrentWorkspaceScreen();
            }
            startLoader(synchronousBindPage);
        }
    }

    public boolean isCurrentCallbacks(Callbacks callbacks) {
        WeakReference<Callbacks> weakReference = this.mCallbacks;
        return weakReference != null && weakReference.get() == callbacks;
    }

    public boolean startLoader(int synchronousBindPage) {
        InstallShortcutReceiver.enableInstallQueue(2);
        synchronized (this.mLock) {
            WeakReference<Callbacks> weakReference = this.mCallbacks;
            if (weakReference != null && weakReference.get() != null) {
                final Callbacks callbacks = this.mCallbacks.get();
                MainThreadExecutor mainThreadExecutor = this.mUiExecutor;
                Objects.requireNonNull(callbacks);
                mainThreadExecutor.execute(new Runnable() { // from class: com.android.launcher3.-$$Lambda$VaXVAZ4E07z_RM8X0Qu_qWqY98E
                    @Override // java.lang.Runnable
                    public final void run() {
                        callbacks.clearPendingBinds();
                    }
                });
                stopLoader();
                LoaderResults loaderResults = new LoaderResults(this.mApp, sBgDataModel, this.mBgAllAppsList, synchronousBindPage, this.mCallbacks);
                if (this.mModelLoaded && !this.mIsLoaderTaskRunning) {
                    loaderResults.bindWorkspace();
                    loaderResults.bindAllApps();
                    loaderResults.bindDeepShortcuts();
                    loaderResults.bindWidgets();
                    return true;
                }
                startLoaderForResults(loaderResults);
            }
            return false;
        }
    }

    public void stopLoader() {
        synchronized (this.mLock) {
            LoaderTask loaderTask = this.mLoaderTask;
            this.mLoaderTask = null;
            if (loaderTask != null) {
                loaderTask.stopLocked();
            }
        }
    }

    public void startLoaderForResults(LoaderResults results) {
        synchronized (this.mLock) {
            stopLoader();
            LoaderTask loaderTask = new LoaderTask(this.mApp, this.mBgAllAppsList, sBgDataModel, results);
            this.mLoaderTask = loaderTask;
            sWorker.post(loaderTask);
        }
    }

    public void startLoaderForResultsIfNotLoaded(LoaderResults results) {
        synchronized (this.mLock) {
            if (!isModelLoaded()) {
                Log.d(TAG, "Workspace not loaded, loading now");
                startLoaderForResults(results);
            }
        }
    }

    public void onInstallSessionCreated(final PackageInstallerCompat.PackageInstallInfo sessionInfo) {
        enqueueModelUpdateTask(new BaseModelUpdateTask() { // from class: com.android.launcher3.LauncherModel.2
            @Override // com.android.launcher3.model.BaseModelUpdateTask
            public void execute(LauncherAppState app, BgDataModel dataModel, AllAppsList apps) {
                apps.addPromiseApp(app.getContext(), sessionInfo);
                if (apps.added.isEmpty()) {
                    return;
                }
                final ArrayList arrayList = new ArrayList(apps.added);
                apps.added.clear();
                scheduleCallbackTask(new CallbackTask() { // from class: com.android.launcher3.LauncherModel.2.1
                    @Override // com.android.launcher3.LauncherModel.CallbackTask
                    public void execute(Callbacks callbacks) {
                        callbacks.bindAppsAddedOrUpdated(arrayList);
                    }
                });
            }
        });
    }

    public class LoaderTransaction implements AutoCloseable {
        private final LoaderTask mTask;

        private LoaderTransaction(LoaderTask task) throws CancellationException {
            synchronized (LauncherModel.this.mLock) {
                if (LauncherModel.this.mLoaderTask != task) {
                    throw new CancellationException("Loader already stopped");
                }
                this.mTask = task;
                LauncherModel.this.mIsLoaderTaskRunning = true;
                LauncherModel.this.mModelLoaded = false;
            }
        }

        public void commit() {
            synchronized (LauncherModel.this.mLock) {
                LauncherModel.this.mModelLoaded = true;
            }
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            synchronized (LauncherModel.this.mLock) {
                if (LauncherModel.this.mLoaderTask == this.mTask) {
                    LauncherModel.this.mLoaderTask = null;
                }
                LauncherModel.this.mIsLoaderTaskRunning = false;
            }
        }
    }

    public LoaderTransaction beginLoader(LoaderTask task) throws CancellationException {
        return new LoaderTransaction(task);
    }

    public void refreshShortcutsIfRequired() {
        Handler handler = sWorker;
        handler.removeCallbacks(this.mShortcutPermissionCheckRunnable);
        handler.post(this.mShortcutPermissionCheckRunnable);
    }

    public void onPackageIconsUpdated(HashSet<String> updatedPackages, UserHandle user) {
        enqueueModelUpdateTask(new CacheDataUpdatedTask(1, user, updatedPackages));
    }

    public void onWidgetLabelsUpdated(final HashSet<String> updatedPackages, final UserHandle user) {
        enqueueModelUpdateTask(new BaseModelUpdateTask() { // from class: com.android.launcher3.LauncherModel.3
            @Override // com.android.launcher3.model.BaseModelUpdateTask
            public void execute(LauncherAppState app, BgDataModel dataModel, AllAppsList apps) {
                dataModel.widgetsModel.onPackageIconsUpdated(updatedPackages, user, app);
                bindUpdatedWidgets(dataModel);
            }
        });
    }

    public void enqueueModelUpdateTask(ModelUpdateTask task) {
        task.init(this.mApp, this, sBgDataModel, this.mBgAllAppsList, this.mUiExecutor);
        if (sWorkerThread.getThreadId() == Process.myTid()) {
            task.run();
        } else {
            sWorker.post(task);
        }
    }

    public void updateAndBindWorkspaceItem(final WorkspaceItemInfo si, final ShortcutInfo info) {
        updateAndBindWorkspaceItem(new Supplier() { // from class: com.android.launcher3.-$$Lambda$LauncherModel$RU5SrLL19JPTpJ6a2Kxqe8vZCqA
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.f$0.lambda$updateAndBindWorkspaceItem$0$LauncherModel(si, info);
            }
        });
    }

    public /* synthetic */ WorkspaceItemInfo lambda$updateAndBindWorkspaceItem$0$LauncherModel(WorkspaceItemInfo workspaceItemInfo, ShortcutInfo shortcutInfo) {
        workspaceItemInfo.updateFromDeepShortcutInfo(shortcutInfo, this.mApp.getContext());
        LauncherIcons launcherIconsObtain = LauncherIcons.obtain(this.mApp.getContext());
        workspaceItemInfo.applyFrom(launcherIconsObtain.createShortcutIcon(shortcutInfo));
        launcherIconsObtain.recycle();
        return workspaceItemInfo;
    }

    public void updateAndBindWorkspaceItem(final Supplier<WorkspaceItemInfo> itemProvider) {
        enqueueModelUpdateTask(new BaseModelUpdateTask() { // from class: com.android.launcher3.LauncherModel.4
            @Override // com.android.launcher3.model.BaseModelUpdateTask
            public void execute(LauncherAppState app, BgDataModel dataModel, AllAppsList apps) {
                WorkspaceItemInfo workspaceItemInfo = (WorkspaceItemInfo) itemProvider.get();
                getModelWriter().updateItemInDatabase(workspaceItemInfo);
                ArrayList<WorkspaceItemInfo> arrayList = new ArrayList<>();
                arrayList.add(workspaceItemInfo);
                bindUpdatedWorkspaceItems(arrayList);
            }
        });
    }

    public void refreshAndBindWidgetsAndShortcuts(final PackageUserKey packageUser) {
        enqueueModelUpdateTask(new BaseModelUpdateTask() { // from class: com.android.launcher3.LauncherModel.5
            @Override // com.android.launcher3.model.BaseModelUpdateTask
            public void execute(LauncherAppState app, BgDataModel dataModel, AllAppsList apps) {
                dataModel.widgetsModel.update(app, packageUser);
                bindUpdatedWidgets(dataModel);
            }
        });
    }

    public void dumpState(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        if (args.length > 0 && TextUtils.equals(args[0], "--all")) {
            writer.println(prefix + "All apps list: size=" + this.mBgAllAppsList.data.size());
            for (AppInfo appInfo : this.mBgAllAppsList.data) {
                writer.println(prefix + "   title=\"" + ((Object) appInfo.title) + "\" iconBitmap=" + appInfo.iconBitmap + " componentName=" + appInfo.componentName.getPackageName());
            }
        }
        sBgDataModel.dump(prefix, fd, writer, args);
    }

    public Callbacks getCallback() {
        WeakReference<Callbacks> weakReference = this.mCallbacks;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public static Looper getWorkerLooper() {
        return mWorkerLooper;
    }

    public static void setWorkerPriority(final int priority) {
        Process.setThreadPriority(sWorkerThread.getThreadId(), priority);
    }

    public ArrayList<ItemInfo> getAllWorkspaceItems() {
        return new ArrayList<>(sBgDataModel.workspaceItems);
    }
}
