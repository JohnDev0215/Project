package com.android.launcher3.model;

import android.appwidget.AppWidgetProviderInfo;
import android.content.Context;
import android.content.pm.LauncherActivityInfo;
import android.os.Process;
import android.os.UserHandle;
import com.android.launcher3.AllAppsList;
import com.android.launcher3.AppInfo;
import com.android.launcher3.InstallShortcutReceiver;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherAppWidgetInfo;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.compat.AppWidgetManagerCompat;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.compat.PackageInstallerCompat;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.icons.IconCache;
import com.android.launcher3.icons.LauncherActivtiyCachingLogic;
import com.android.launcher3.icons.LauncherIcons;
import com.android.launcher3.icons.cache.IconCacheUpdateHandler;
import com.android.launcher3.shortcuts.DeepShortcutManager;
import com.android.launcher3.util.LooperIdleLock;
import com.android.launcher3.util.TraceHelper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes4.dex */
public class LoaderTask implements Runnable {
    private static final String TAG = "LoaderTask";
    private final LauncherAppState mApp;
    private final AppWidgetManagerCompat mAppWidgetManager;
    private final AllAppsList mBgAllAppsList;
    private final BgDataModel mBgDataModel;
    private FirstScreenBroadcast mFirstScreenBroadcast;
    private final IconCache mIconCache;
    private final LauncherAppsCompat mLauncherApps;
    private final PackageInstallerCompat mPackageInstaller;
    private final LoaderResults mResults;
    private final DeepShortcutManager mShortcutManager;
    private boolean mStopped;
    private final UserManagerCompat mUserManager;

    public LoaderTask(LauncherAppState app, AllAppsList bgAllAppsList, BgDataModel dataModel, LoaderResults results) {
        this.mApp = app;
        this.mBgAllAppsList = bgAllAppsList;
        this.mBgDataModel = dataModel;
        this.mResults = results;
        this.mLauncherApps = LauncherAppsCompat.getInstance(app.getContext());
        this.mUserManager = UserManagerCompat.getInstance(app.getContext());
        this.mShortcutManager = DeepShortcutManager.getInstance(app.getContext());
        this.mPackageInstaller = PackageInstallerCompat.getInstance(app.getContext());
        this.mAppWidgetManager = AppWidgetManagerCompat.getInstance(app.getContext());
        this.mIconCache = app.getIconCache();
    }

    protected synchronized void waitForIdle() {
        LooperIdleLock looperIdleLockNewIdleLock = this.mResults.newIdleLock(this);
        while (!this.mStopped && looperIdleLockNewIdleLock.awaitLocked(1000L)) {
        }
    }

    private synchronized void verifyNotStopped() throws CancellationException {
        if (this.mStopped) {
            throw new CancellationException("Loader stopped");
        }
    }

    private void sendFirstScreenActiveInstallsBroadcast() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        synchronized (this.mBgDataModel) {
            arrayList2.addAll(this.mBgDataModel.workspaceItems);
            arrayList2.addAll(this.mBgDataModel.appWidgets);
        }
        LoaderResults.filterCurrentWorkspaceItems(this.mBgDataModel.collectWorkspaceScreens().get(0), arrayList2, arrayList, new ArrayList());
        this.mFirstScreenBroadcast.sendBroadcasts(this.mApp.getContext(), arrayList);
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this) {
            if (this.mStopped) {
                return;
            }
            TraceHelper.beginSection(TAG);
            try {
                LauncherModel.LoaderTransaction loaderTransactionBeginLoader = this.mApp.getModel().beginLoader(this);
                try {
                    TraceHelper.partitionSection(TAG, "step 1.1: loading workspace");
                    loadWorkspace();
                    verifyNotStopped();
                    TraceHelper.partitionSection(TAG, "step 1.2: bind workspace workspace");
                    this.mResults.bindWorkspace();
                    TraceHelper.partitionSection(TAG, "step 1.3: send first screen broadcast");
                    sendFirstScreenActiveInstallsBroadcast();
                    TraceHelper.partitionSection(TAG, "step 1 completed, wait for idle");
                    waitForIdle();
                    verifyNotStopped();
                    TraceHelper.partitionSection(TAG, "step 2.1: loading all apps");
                    List<LauncherActivityInfo> listLoadAllApps = loadAllApps();
                    verifyApplications();
                    TraceHelper.partitionSection(TAG, "step 2.2: Binding all apps");
                    verifyNotStopped();
                    this.mResults.bindAllApps();
                    verifyNotStopped();
                    TraceHelper.partitionSection(TAG, "step 2.3: Update icon cache");
                    IconCacheUpdateHandler updateHandler = this.mIconCache.getUpdateHandler();
                    setIgnorePackages(updateHandler);
                    LauncherActivtiyCachingLogic launcherActivtiyCachingLogic = new LauncherActivtiyCachingLogic(this.mApp.getIconCache());
                    final LauncherModel model = this.mApp.getModel();
                    Objects.requireNonNull(model);
                    updateHandler.updateIcons(listLoadAllApps, launcherActivtiyCachingLogic, new IconCacheUpdateHandler.OnUpdateCallback() { // from class: com.android.launcher3.model.-$$Lambda$tFi_8Vq3vx5dtJb4Ge3ehK3lz48
                        @Override // com.android.launcher3.icons.cache.IconCacheUpdateHandler.OnUpdateCallback
                        public final void onPackageIconsUpdated(HashSet hashSet, UserHandle userHandle) {
                            model.onPackageIconsUpdated(hashSet, userHandle);
                        }
                    });
                    TraceHelper.partitionSection(TAG, "step 2 completed, wait for idle");
                    waitForIdle();
                    verifyNotStopped();
                    TraceHelper.partitionSection(TAG, "step 3.1: loading deep shortcuts");
                    loadDeepShortcuts();
                    verifyNotStopped();
                    TraceHelper.partitionSection(TAG, "step 3.2: bind deep shortcuts");
                    this.mResults.bindDeepShortcuts();
                    TraceHelper.partitionSection(TAG, "step 3 completed, wait for idle");
                    waitForIdle();
                    verifyNotStopped();
                    verifyNotStopped();
                    TraceHelper.partitionSection(TAG, "step 5: Finish icon cache update");
                    updateHandler.finish();
                    loaderTransactionBeginLoader.commit();
                    if (loaderTransactionBeginLoader != null) {
                        loaderTransactionBeginLoader.close();
                    }
                } finally {
                }
            } catch (CancellationException unused) {
                TraceHelper.partitionSection(TAG, "Cancelled");
            }
            TraceHelper.endSection(TAG);
        }
    }

    private void verifyApplications() {
        Context context = this.mApp.getContext();
        String packageName = context.getPackageName();
        ArrayList arrayList = new ArrayList();
        Iterator<UserHandle> it = this.mUserManager.getUserProfiles().iterator();
        while (it.hasNext()) {
            List<LauncherActivityInfo> activityList = this.mLauncherApps.getActivityList(null, it.next());
            ArrayList arrayList2 = new ArrayList();
            synchronized (this) {
                for (LauncherActivityInfo launcherActivityInfo : activityList) {
                    if (!packageName.equals(launcherActivityInfo.getComponentName().getPackageName())) {
                        InstallShortcutReceiver.PendingInstallShortcutInfo pendingInstallShortcutInfo = new InstallShortcutReceiver.PendingInstallShortcutInfo(launcherActivityInfo, context);
                        arrayList2.add(pendingInstallShortcutInfo);
                        arrayList.add(pendingInstallShortcutInfo.getItemInfo());
                    }
                }
            }
            if (!arrayList2.isEmpty()) {
                this.mApp.getModel().addAndBindAddedWorkspaceItems(arrayList);
            }
        }
    }

    public synchronized void stopLocked() {
        this.mStopped = true;
        notify();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:46|47|(1:566)(7:51|(2:53|(20:470|59|(1:61)(1:62)|63|64|492|65|(1:67)(1:68)|69|70|(4:72|506|73|74)(1:77)|500|78|79|516|80|81|537|82|(16:(4:89|(1:92)|93|94)(9:98|99|100|(1:102)(1:104)|103|105|(1:(1:109)(1:(5:552|111|144|579|576)))(1:107)|(1:113)(1:114)|115)|95|539|116|(1:118)|119|120|480|121|122|484|123|(1:143)(2:128|(2:555|130)(5:569|(2:132|(1:136))|137|(1:139)|140))|144|579|576)(2:478|87))(2:568|(6:575|57|142|389|578|576)))(6:567|164|165|543|166|167)|141|142|389|578|576)|58|535|175|(2:556|177)(7:179|(1:181)(1:182)|183|(1:185)(1:187)|188|(2:197|(4:203|(11:529|205|206|533|207|(0)(1:210)|(1:245)(3:474|220|(1:222)(5:224|225|468|226|(8:228|229|230|231|232|466|247|(17:275|496|276|277|(1:279)|(2:490|281)|285|(1:287)(1:288)|289|(2:570|291)(2:293|(2:571|295)(9:297|298|(9:508|300|301|514|302|303|541|304|(4:306|494|307|(5:504|309|563|310|577)(18:574|313|314|482|315|316|486|317|318|488|319|320|522|321|472|322|(1:324)|325))(2:573|338))(6:572|349|350|(1:354)|357|(1:365))|(15:520|367|368|524|369|370|498|371|372|(1:379)(3:376|377|378)|380|(2:384|(1:386)(1:387))|388|389|578)(3:394|395|564)|396|410|411|389|578))|292|(0)(0)|396|410|411|389|578)(3:512|250|(20:531|252|(2:255|(1:257)(6:561|258|236|237|310|577))(1:254)|275|496|276|277|(0)|(0)|285|(0)(0)|289|(0)(0)|292|(0)(0)|396|410|411|389|578)(19:262|(1:264)(1:(3:267|268|269)(6:562|270|271|237|310|577))|265|496|276|277|(0)|(0)|285|(0)(0)|289|(0)(0)|292|(0)(0)|396|410|411|389|578)))(6:560|235|236|237|310|577)))|246|466|247|(17:275|496|276|277|(0)|(0)|285|(0)(0)|289|(0)(0)|292|(0)(0)|396|410|411|389|578)(0))(1:216)|217|(5:245|246|466|247|(0)(0))(0))(2:559|201))(2:190|(2:557|192)(2:193|(2:558|195)(0)))|576)|178|310|577|576) */
    /* JADX WARN: Can't wrap try/catch for region: R(11:529|205|206|533|207|(0)(1:210)|(1:245)(3:474|220|(1:222)(5:224|225|468|226|(8:228|229|230|231|232|466|247|(17:275|496|276|277|(1:279)|(2:490|281)|285|(1:287)(1:288)|289|(2:570|291)(2:293|(2:571|295)(9:297|298|(9:508|300|301|514|302|303|541|304|(4:306|494|307|(5:504|309|563|310|577)(18:574|313|314|482|315|316|486|317|318|488|319|320|522|321|472|322|(1:324)|325))(2:573|338))(6:572|349|350|(1:354)|357|(1:365))|(15:520|367|368|524|369|370|498|371|372|(1:379)(3:376|377|378)|380|(2:384|(1:386)(1:387))|388|389|578)(3:394|395|564)|396|410|411|389|578))|292|(0)(0)|396|410|411|389|578)(3:512|250|(20:531|252|(2:255|(1:257)(6:561|258|236|237|310|577))(1:254)|275|496|276|277|(0)|(0)|285|(0)(0)|289|(0)(0)|292|(0)(0)|396|410|411|389|578)(19:262|(1:264)(1:(3:267|268|269)(6:562|270|271|237|310|577))|265|496|276|277|(0)|(0)|285|(0)(0)|289|(0)(0)|292|(0)(0)|396|410|411|389|578)))(6:560|235|236|237|310|577)))|246|466|247|(17:275|496|276|277|(0)|(0)|285|(0)(0)|289|(0)(0)|292|(0)(0)|396|410|411|389|578)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(16:(4:89|(1:92)|93|94)(9:98|99|100|(1:102)(1:104)|103|105|(1:(1:109)(1:(5:552|111|144|579|576)))(1:107)|(1:113)(1:114)|115)|95|539|116|(1:118)|119|120|480|121|122|484|123|(1:143)(2:128|(2:555|130)(5:569|(2:132|(1:136))|137|(1:139)|140))|144|579|576) */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x03f1, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x03f2, code lost:
    
        r29 = r2;
        r34 = r7;
        r35 = r8;
        r20 = r9;
        r6 = r19;
        r9 = r21;
        r7 = r22;
        r13 = r28;
        r8 = r32;
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x0406, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x049d, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x049e, code lost:
    
        r29 = r2;
        r34 = r7;
        r35 = r8;
        r20 = r9;
        r9 = r21;
        r7 = r22;
        r13 = r28;
        r8 = r32;
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:400:0x086d, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:401:0x086e, code lost:
    
        r20 = r9;
        r6 = r19;
        r9 = r21;
        r7 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:403:0x0879, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:404:0x087a, code lost:
    
        r29 = r2;
        r34 = r7;
        r35 = r8;
        r20 = r9;
        r9 = r21;
        r7 = r22;
        r13 = r28;
        r8 = r32;
        r28 = r6;
        r21 = r10;
     */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0515 A[Catch: Exception -> 0x0879, all -> 0x09c3, TRY_ENTER, TRY_LEAVE, TryCatch #22 {all -> 0x09c3, blocks: (B:17:0x0085, B:18:0x00cb, B:20:0x00d3, B:22:0x00ff, B:24:0x0112, B:25:0x0116, B:27:0x011c, B:30:0x0131, B:32:0x014a, B:34:0x014e, B:36:0x0154, B:38:0x0158, B:46:0x0196, B:59:0x01d8, B:63:0x01df, B:65:0x01ee, B:69:0x01fb, B:73:0x0207, B:78:0x0216, B:80:0x021a, B:82:0x0220, B:87:0x0237, B:411:0x08b1, B:89:0x0257, B:92:0x0268, B:93:0x026a, B:116:0x0317, B:118:0x031d, B:119:0x0323, B:121:0x0328, B:123:0x0330, B:125:0x033e, B:128:0x0344, B:130:0x034a, B:132:0x0353, B:134:0x035f, B:136:0x0365, B:137:0x037f, B:139:0x0383, B:140:0x039e, B:143:0x03bc, B:98:0x0286, B:100:0x02c5, B:105:0x02de, B:115:0x0313, B:114:0x030f, B:109:0x02e7, B:111:0x02ef, B:104:0x02d1, B:164:0x044c, B:166:0x046e, B:167:0x0472, B:175:0x04b7, B:177:0x04bd, B:179:0x04d5, B:183:0x04e7, B:185:0x04ed, B:188:0x04f6, B:190:0x0500, B:192:0x0505, B:193:0x050b, B:195:0x050f, B:197:0x0515, B:199:0x051b, B:201:0x0520, B:203:0x0526, B:205:0x052c, B:207:0x0534, B:220:0x055c, B:222:0x0568, B:247:0x05d6, B:250:0x05de, B:252:0x05e2, B:255:0x0605, B:257:0x060b, B:258:0x0626, B:262:0x0641, B:264:0x0649, B:276:0x069b, B:281:0x06a4, B:285:0x06aa, B:289:0x06b3, B:291:0x06b7, B:367:0x07e4, B:369:0x07eb, B:371:0x07f2, B:372:0x07f6, B:374:0x07fd, B:376:0x0803, B:378:0x0807, B:380:0x080b, B:382:0x080f, B:384:0x0815, B:386:0x081d, B:387:0x0824, B:388:0x082f, B:394:0x0858, B:395:0x0862, B:293:0x06c5, B:295:0x06c9, B:297:0x06ce, B:300:0x06d3, B:302:0x06da, B:304:0x06de, B:307:0x06ec, B:309:0x06f4, B:313:0x071c, B:315:0x0720, B:317:0x0725, B:319:0x072b, B:321:0x0733, B:322:0x0737, B:324:0x0749, B:325:0x074f, B:338:0x076e, B:350:0x07a2, B:352:0x07ac, B:357:0x07b9, B:359:0x07bf, B:361:0x07c5, B:363:0x07d1, B:365:0x07dd, B:267:0x0651, B:269:0x066d, B:270:0x0671, B:224:0x056d, B:226:0x0571, B:228:0x0577, B:232:0x0587, B:235:0x0598, B:187:0x04f2), top: B:510:0x0085, outer: #31 }] */
    /* JADX WARN: Removed duplicated region for block: B:245:0x05ca  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x05dc A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0698  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x06a1  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x06b0  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x06b2  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x06c5 A[Catch: Exception -> 0x0865, all -> 0x09c3, TRY_ENTER, TRY_LEAVE, TryCatch #15 {Exception -> 0x0865, blocks: (B:276:0x069b, B:285:0x06aa, B:289:0x06b3, B:293:0x06c5, B:297:0x06ce), top: B:496:0x069b }] */
    /* JADX WARN: Removed duplicated region for block: B:394:0x0858 A[Catch: Exception -> 0x0863, all -> 0x09c3, TryCatch #16 {Exception -> 0x0863, blocks: (B:371:0x07f2, B:378:0x0807, B:380:0x080b, B:382:0x080f, B:384:0x0815, B:386:0x081d, B:387:0x0824, B:388:0x082f, B:394:0x0858, B:395:0x0862), top: B:498:0x07f2 }] */
    /* JADX WARN: Removed duplicated region for block: B:490:0x06a4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:520:0x07e4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:570:0x06b7 A[SYNTHETIC] */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void loadWorkspace() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 2513
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.model.LoaderTask.loadWorkspace():void");
    }

    static /* synthetic */ ItemInfoWithIcon lambda$loadWorkspace$0(LoaderCursor loaderCursor, WorkspaceItemInfo workspaceItemInfo, LauncherIcons launcherIcons) {
        if (loaderCursor.loadIcon(workspaceItemInfo, launcherIcons)) {
            return workspaceItemInfo;
        }
        return null;
    }

    private void setIgnorePackages(IconCacheUpdateHandler updateHandler) {
        HashSet hashSet = new HashSet();
        synchronized (this.mBgDataModel) {
            for (ItemInfo itemInfo : this.mBgDataModel.itemsIdMap) {
                if (itemInfo instanceof WorkspaceItemInfo) {
                    WorkspaceItemInfo workspaceItemInfo = (WorkspaceItemInfo) itemInfo;
                    if (workspaceItemInfo.isPromise() && workspaceItemInfo.getTargetComponent() != null) {
                        hashSet.add(workspaceItemInfo.getTargetComponent().getPackageName());
                    }
                } else if (itemInfo instanceof LauncherAppWidgetInfo) {
                    LauncherAppWidgetInfo launcherAppWidgetInfo = (LauncherAppWidgetInfo) itemInfo;
                    if (launcherAppWidgetInfo.hasRestoreFlag(2)) {
                        hashSet.add(launcherAppWidgetInfo.providerName.getPackageName());
                    }
                }
            }
        }
        updateHandler.setPackagesToIgnore(Process.myUserHandle(), hashSet);
    }

    private List<LauncherActivityInfo> loadAllApps() {
        List<UserHandle> userProfiles = this.mUserManager.getUserProfiles();
        ArrayList arrayList = new ArrayList();
        this.mBgAllAppsList.clear();
        for (UserHandle userHandle : userProfiles) {
            List<LauncherActivityInfo> activityList = this.mLauncherApps.getActivityList(null, userHandle);
            if (activityList == null || activityList.isEmpty()) {
                return arrayList;
            }
            boolean zIsQuietModeEnabled = this.mUserManager.isQuietModeEnabled(userHandle);
            for (int i = 0; i < activityList.size(); i++) {
                LauncherActivityInfo launcherActivityInfo = activityList.get(i);
                this.mBgAllAppsList.add(new AppInfo(launcherActivityInfo, userHandle, zIsQuietModeEnabled), launcherActivityInfo);
            }
            arrayList.addAll(activityList);
        }
        this.mBgAllAppsList.added = new ArrayList<>();
        return arrayList;
    }

    private void loadDeepShortcuts() {
        this.mBgDataModel.deepShortcutMap.clear();
        this.mBgDataModel.hasShortcutHostPermission = this.mShortcutManager.hasHostPermission();
        if (this.mBgDataModel.hasShortcutHostPermission) {
            for (UserHandle userHandle : this.mUserManager.getUserProfiles()) {
                if (this.mUserManager.isUserUnlocked(userHandle)) {
                    this.mBgDataModel.updateDeepShortcutCounts(null, userHandle, this.mShortcutManager.queryForAllShortcuts(userHandle));
                }
            }
        }
    }

    public static boolean isValidProvider(AppWidgetProviderInfo provider) {
        return (provider == null || provider.provider == null || provider.provider.getPackageName() == null) ? false : true;
    }
}
