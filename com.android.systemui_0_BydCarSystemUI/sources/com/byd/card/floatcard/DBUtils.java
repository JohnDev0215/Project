package com.byd.card.floatcard;

import android.app.UiStyleConfig;
import android.content.Context;
import android.database.ContentObserver;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.Dependency;
import com.android.systemui.SystemUIApplication;
import com.byd.card.bean.AppInfo;
import com.byd.card.bean.Constant;
import com.byd.card.bean.Folder;
import com.byd.card.bean.ShortcutInfo;
import com.byd.card.db.AppInfoDao;
import com.byd.card.db.ApplicationDao;
import com.byd.card.db.CardDatabase;
import com.byd.card.drag.ApplicationInfo;
import com.byd.card.drag.MoreAppFolderInfo;
import com.byd.card.forbidden.ForbiddenManager;
import com.byd.card.provider.ShortcutProvider;
import com.byd.card.utils.AppUtils;
import com.byd.card.utils.BitmapUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DBUtils {
    private static final String TAG = "DBUtils";
    private static volatile DBUtils sInstance;

    private DBUtils() {
    }

    public static DBUtils getInstance() {
        if (sInstance == null) {
            synchronized (DBUtils.class) {
                if (sInstance == null) {
                    sInstance = new DBUtils();
                }
            }
        }
        return sInstance;
    }

    public void updateAllList(final List<ApplicationInfo> checkList, final List<ApplicationInfo> cancelList, final MoreAppFolderInfo folderInfo) {
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.DBUtils.1
            @Override // java.lang.Runnable
            public void run() {
                ApplicationDao workSpaceItemDao = CardDatabase.getInstance().getWorkSpaceItemDao();
                int size = checkList.size();
                if (size == 1) {
                    ApplicationInfo appByPackageName = workSpaceItemDao.getAppByPackageName(((ApplicationInfo) checkList.get(0)).getPackageName());
                    appByPackageName.folderPosition = 0;
                    appByPackageName.folderId = 0;
                    workSpaceItemDao.update(appByPackageName);
                } else if (size > 1) {
                    Iterator it = checkList.iterator();
                    int i = 0;
                    while (it.hasNext()) {
                        ApplicationInfo appByPackageName2 = workSpaceItemDao.getAppByPackageName(((ApplicationInfo) it.next()).getPackageName());
                        appByPackageName2.folderId = folderInfo.folderId;
                        appByPackageName2.folderPosition = i;
                        workSpaceItemDao.update(appByPackageName2);
                        i++;
                    }
                }
                if (cancelList == null) {
                    return;
                }
                int maxPosition = workSpaceItemDao.getMaxPosition();
                for (ApplicationInfo applicationInfo : cancelList) {
                    if (applicationInfo != null) {
                        ApplicationInfo appByPackageName3 = workSpaceItemDao.getAppByPackageName(applicationInfo.getPackageName());
                        appByPackageName3.folderPosition = 0;
                        appByPackageName3.folderId = 0;
                        maxPosition++;
                        appByPackageName3.position = maxPosition;
                        workSpaceItemDao.update(appByPackageName3);
                    }
                }
            }
        });
    }

    public void insertNewFolder(final MoreAppFolderInfo moreAppFolderInfo, final ApplicationInfo srcApp, final ApplicationInfo desApp) {
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.DBUtils.2
            @Override // java.lang.Runnable
            public void run() {
                if (moreAppFolderInfo == null) {
                    return;
                }
                CardDatabase.getInstance().getFolderDao().insertFolder(new Folder(Constant.FolderType.DEFAULT_FOLDER));
                ApplicationDao workSpaceItemDao = CardDatabase.getInstance().getWorkSpaceItemDao();
                ApplicationInfo appByPackageName = workSpaceItemDao.getAppByPackageName(srcApp.getPackageName());
                appByPackageName.folderId = moreAppFolderInfo.folderId;
                appByPackageName.position = srcApp.getPosition();
                appByPackageName.folderPosition = srcApp.folderPosition;
                workSpaceItemDao.update(appByPackageName);
                ApplicationInfo appByPackageName2 = workSpaceItemDao.getAppByPackageName(desApp.getPackageName());
                appByPackageName2.folderId = moreAppFolderInfo.folderId;
                appByPackageName2.position = desApp.getPosition();
                appByPackageName2.folderPosition = desApp.folderPosition;
                workSpaceItemDao.update(appByPackageName2);
            }
        });
    }

    public void updatePositionToDB(final List<ApplicationInfo> infos, final ApplicationInfo dragApp) {
        if (infos == null) {
            return;
        }
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.DBUtils.3
            @Override // java.lang.Runnable
            public void run() {
                for (int i = 0; i < infos.size(); i++) {
                    ApplicationInfo applicationInfo = (ApplicationInfo) infos.get(i);
                    if (applicationInfo == null) {
                        applicationInfo = dragApp;
                    }
                    if (applicationInfo != null) {
                        ApplicationDao workSpaceItemDao = CardDatabase.getInstance().getWorkSpaceItemDao();
                        if (applicationInfo instanceof MoreAppFolderInfo) {
                            List<ApplicationInfo> listByFolderId = workSpaceItemDao.getListByFolderId(applicationInfo.folderId);
                            if (listByFolderId != null) {
                                if (listByFolderId.size() == 1 && listByFolderId.get(0) != null) {
                                    ApplicationInfo applicationInfo2 = listByFolderId.get(0);
                                    applicationInfo2.folderId = 0;
                                    workSpaceItemDao.update(applicationInfo2);
                                } else {
                                    for (int i2 = 0; i2 < listByFolderId.size(); i2++) {
                                        ApplicationInfo applicationInfo3 = listByFolderId.get(i2);
                                        applicationInfo3.position = i;
                                        workSpaceItemDao.update(applicationInfo3);
                                    }
                                }
                            }
                        } else if (applicationInfo == dragApp) {
                            Log.i(DBUtils.TAG, "info == dragApp");
                            ApplicationInfo appByActivity = workSpaceItemDao.getAppByActivity(dragApp.activity);
                            if (appByActivity != null) {
                                appByActivity.position = i;
                                workSpaceItemDao.update(appByActivity);
                            }
                        } else {
                            ApplicationInfo appByActivity2 = workSpaceItemDao.getAppByActivity(applicationInfo.activity);
                            if (appByActivity2 != null) {
                                appByActivity2.position = i;
                                appByActivity2.folderId = 0;
                                appByActivity2.folderPosition = 0;
                                workSpaceItemDao.update(appByActivity2);
                            }
                        }
                    }
                }
            }
        });
    }

    public void updateFolderAndAppDB(final MoreAppFolderInfo folderInfo, final ApplicationInfo deleteApp) {
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.DBUtils.4
            @Override // java.lang.Runnable
            public void run() {
                if (deleteApp == null) {
                    BydLogHelper.logD("updateFolderAndAppDB deleteApp is null");
                    return;
                }
                Folder folderById = CardDatabase.getInstance().getFolderDao().getFolderById(folderInfo.folderId);
                ApplicationDao workSpaceItemDao = CardDatabase.getInstance().getWorkSpaceItemDao();
                ApplicationInfo appByActivity = workSpaceItemDao.getAppByActivity(deleteApp.getActivity());
                if (folderById != null && folderInfo.getIcons().size() == 1) {
                    ApplicationInfo appByActivity2 = workSpaceItemDao.getAppByActivity(folderInfo.getIcons().get(0).getActivity());
                    appByActivity2.folderId = 0;
                    appByActivity2.folderPosition = 0;
                    workSpaceItemDao.update(appByActivity2);
                }
                if (appByActivity == null) {
                    BydLogHelper.logD("updateFolderAndAppDB info is null");
                    return;
                }
                appByActivity.folderId = 0;
                appByActivity.folderPosition = 0;
                workSpaceItemDao.update(appByActivity);
            }
        });
    }

    public void removeFolder(final ApplicationInfo deleteApp) {
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.DBUtils.5
            @Override // java.lang.Runnable
            public void run() {
                if (deleteApp == null) {
                    BydLogHelper.logD("removeFolder deleteApp is null");
                    return;
                }
                ApplicationDao workSpaceItemDao = CardDatabase.getInstance().getWorkSpaceItemDao();
                ApplicationInfo appByActivity = workSpaceItemDao.getAppByActivity(deleteApp.getActivity());
                if (appByActivity == null) {
                    BydLogHelper.logD("updateFolderAndAppDB info is null");
                    return;
                }
                appByActivity.folderId = 0;
                appByActivity.folderPosition = 0;
                workSpaceItemDao.update(appByActivity);
            }
        });
    }

    /* JADX INFO: renamed from: com.byd.card.floatcard.DBUtils$6, reason: invalid class name */
    class AnonymousClass6 implements Runnable {
        final /* synthetic */ Context val$context;

        AnonymousClass6(final Context val$context) {
            this.val$context = val$context;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                DBUtils.this.judgeCountryCode();
                DBUtils.this.initDatabase(this.val$context);
            } catch (Exception e) {
                Log.w(DBUtils.TAG, "initDatabase exception : " + e.getMessage());
                DBUtils.this.resetData(this.val$context);
            }
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new AnonymousClass1());
        }

        /* JADX INFO: renamed from: com.byd.card.floatcard.DBUtils$6$1, reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            AnonymousClass1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    AnonymousClass6.this.val$context.getContentResolver().registerContentObserver(ShortcutProvider.getUri("shortcut_info"), true, new ContentObserver((Handler) Dependency.get(Dependency.MAIN_HANDLER)) { // from class: com.byd.card.floatcard.DBUtils.6.1.1
                        @Override // android.database.ContentObserver
                        public void onChange(boolean selfChange, Uri uri) {
                            Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.DBUtils.6.1.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    Log.d(DBUtils.TAG, "shortcut info change! synchronizeShortcutAndApplication!");
                                    DBUtils.this.synchronizeShortcutAndApplication(CardDatabase.getInstance().getWorkSpaceItemDao().getMaxPosition());
                                }
                            });
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(DBUtils.TAG, "initDatabaseAppInfo exception : " + e.getMessage());
                }
            }
        }
    }

    public void initDatabaseAppInfo(Context context) {
        Executors.MODEL_EXECUTOR.execute(new AnonymousClass6(context));
    }

    private void initCommonData(List<AppInfo> list) {
        Collections.sort(list, new Comparator<AppInfo>() { // from class: com.byd.card.floatcard.DBUtils.7
            @Override // java.util.Comparator
            public int compare(AppInfo o1, AppInfo o2) {
                return o1.name.compareTo(o2.name);
            }
        });
        int i = 0;
        while (true) {
            if (i >= list.size()) {
                break;
            }
            if (list.get(i) != null && "com.ts.carplay.app".equals(list.get(i).packageName)) {
                Collections.swap(list, 1, i);
                break;
            }
            i++;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= list.size()) {
                break;
            }
            if (list.get(i2) != null && "com.ts.androidauto.app".equals(list.get(i2).packageName)) {
                Collections.swap(list, 2, i2);
                break;
            }
            i2++;
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < list.size(); i3++) {
            arrayList.add(switchApplicationInfo(list.get(i3), i3));
        }
        CardDatabase.getInstance().getWorkSpaceItemDao().insertList(arrayList);
    }

    public static ApplicationInfo changeShortcutToApplication(ShortcutInfo shortcutInfo, ApplicationInfo applicationInfo) {
        applicationInfo.title = shortcutInfo.title;
        applicationInfo.type = 3;
        applicationInfo.setPackageName(shortcutInfo.getItemIdentify());
        applicationInfo.setActivity(shortcutInfo.getItemIdentify());
        applicationInfo.versionName = "1";
        applicationInfo.versionCode = 1;
        applicationInfo.iconBytes = shortcutInfo.icon;
        return applicationInfo;
    }

    public static ApplicationInfo changeShortcutToApplication(ShortcutInfo shortcutInfo, int position) {
        ApplicationInfo applicationInfo = new ApplicationInfo();
        changeShortcutToApplication(shortcutInfo, applicationInfo);
        applicationInfo.position = position;
        return applicationInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initDatabase(Context context) {
        List<AppInfo> appList = AppUtils.getAppList(context);
        List<AppInfo> allApp = CardDatabase.getInstance().getAppInfoDao().getAllApp();
        if (allApp == null || allApp.size() == 0) {
            CardDatabase.getInstance().getAppInfoDao().insertList(appList);
        } else {
            for (AppInfo appInfo : appList) {
                Log.i(TAG, "initDatabase appInfo = " + appInfo.packageName + ", appInfos contain " + allApp.contains(appInfo));
                if (!allApp.contains(appInfo)) {
                    CardDatabase.getInstance().getAppInfoDao().insert(appInfo);
                }
            }
            for (AppInfo appInfo2 : allApp) {
                Log.i(TAG, "initDatabase old appInfo = " + appInfo2.packageName + ", list contain " + appList.contains(appInfo2));
                if (!appList.contains(appInfo2)) {
                    CardDatabase.getInstance().getAppInfoDao().delete(appInfo2);
                }
            }
        }
        initMoreAppDataBase(appList);
    }

    public void updateAppName(final Context context) {
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.DBUtils.8
            @Override // java.lang.Runnable
            public void run() {
                AppInfoDao appInfoDao = CardDatabase.getInstance().getAppInfoDao();
                List<AppInfo> appList = AppUtils.getAppList(context);
                for (AppInfo appInfo : appList) {
                    AppInfo appInfoByPackage = appInfoDao.getAppInfoByPackage(appInfo.packageName);
                    if (appInfoByPackage != null) {
                        appInfoByPackage.name = appInfo.name;
                        appInfoByPackage.pinyin = appInfo.pinyin;
                        appInfoDao.update(appInfoByPackage);
                    }
                }
                ApplicationDao workSpaceItemDao = CardDatabase.getInstance().getWorkSpaceItemDao();
                for (AppInfo appInfo2 : appList) {
                    ApplicationInfo appByPackageName = workSpaceItemDao.getAppByPackageName(appInfo2.packageName);
                    if (appByPackageName != null) {
                        appByPackageName.title = appInfo2.name;
                        appByPackageName.pinyin = appInfo2.pinyin;
                        workSpaceItemDao.update(appByPackageName);
                    }
                }
            }
        });
    }

    private void initMoreAppDataBase(List<AppInfo> installList) {
        List<ApplicationInfo> allApps = CardDatabase.getInstance().getWorkSpaceItemDao().getAllApps();
        if (allApps != null && !allApps.isEmpty()) {
            List<String> workSpacePkgList = getWorkSpacePkgList(allApps);
            int size = workSpacePkgList.size();
            for (AppInfo appInfo : installList) {
                if (!workSpacePkgList.contains(appInfo.packageName)) {
                    if ("com.ts.carplay.app".equals(appInfo.packageName)) {
                        initCPAAWorkspace(appInfo.packageName);
                        CardDatabase.getInstance().getWorkSpaceItemDao().insert(switchApplicationInfo(appInfo, 1));
                    } else if ("com.ts.androidauto.app".equals(appInfo.packageName)) {
                        initCPAAWorkspace(appInfo.packageName);
                        CardDatabase.getInstance().getWorkSpaceItemDao().insert(switchApplicationInfo(appInfo, 2));
                    } else {
                        CardDatabase.getInstance().getWorkSpaceItemDao().insert(switchApplicationInfo(appInfo, size));
                    }
                    size++;
                }
            }
            List<String> installPkgList = getInstallPkgList(installList);
            for (ApplicationInfo applicationInfo : allApps) {
                Log.i(TAG, "old application = " + applicationInfo.mPackageName + ", installPkgList contain " + installPkgList.contains(applicationInfo.mPackageName));
                if (!installPkgList.contains(applicationInfo.mPackageName)) {
                    CardDatabase.getInstance().getWorkSpaceItemDao().delete(applicationInfo);
                }
            }
            synchronizeShortcutAndApplication(size);
            return;
        }
        initCommonData(installList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void synchronizeShortcutAndApplication(int index) {
        boolean z;
        List<ApplicationInfo> shortcutList = CardDatabase.getInstance().getWorkSpaceItemDao().getShortcutList();
        List<ShortcutInfo> allShortcut = CardDatabase.getInstance().getShortcutInfoDao().getAllShortcut();
        Log.d(TAG, "synchronizeShortcutAndApplication " + index + "  installedShortcut = " + shortcutList + " allShortcut = " + allShortcut);
        for (ShortcutInfo shortcutInfo : allShortcut) {
            Iterator<ApplicationInfo> it = shortcutList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                ApplicationInfo next = it.next();
                if (TextUtils.equals(shortcutInfo.getItemIdentify(), next.getPackageName())) {
                    CardDatabase.getInstance().getWorkSpaceItemDao().update(changeShortcutToApplication(shortcutInfo, next));
                    it.remove();
                    z = true;
                    break;
                }
            }
            if (!z) {
                int i = index + 1;
                CardDatabase.getInstance().getWorkSpaceItemDao().insert(changeShortcutToApplication(shortcutInfo, index));
                if (shortcutInfo.pGearFlag == 1) {
                    ForbiddenManager.addForbiddenAppListOnNonP(shortcutInfo.getItemIdentify(), 3);
                }
                index = i;
            }
        }
        if (shortcutList.size() > 0) {
            CardDatabase.getInstance().getWorkSpaceItemDao().deleteList(shortcutList);
            Iterator<ApplicationInfo> it2 = shortcutList.iterator();
            while (it2.hasNext()) {
                ForbiddenManager.removeForbiddenAppListOnNonP(it2.next().getPackageName());
            }
        }
    }

    private List<String> getWorkSpacePkgList(List<ApplicationInfo> applicationInfos) {
        ArrayList arrayList = new ArrayList();
        Iterator<ApplicationInfo> it = applicationInfos.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().mPackageName);
        }
        return arrayList;
    }

    private List<String> getInstallPkgList(List<AppInfo> appInfos) {
        ArrayList arrayList = new ArrayList();
        Iterator<AppInfo> it = appInfos.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().packageName);
        }
        return arrayList;
    }

    private ApplicationInfo switchApplicationInfo(AppInfo appInfo, int index) {
        ApplicationInfo applicationInfo = new ApplicationInfo();
        applicationInfo.title = appInfo.name;
        applicationInfo.activity = appInfo.activity;
        applicationInfo.setIconBytes(appInfo.icons);
        applicationInfo.setIcon(BitmapUtils.decodeBitmapFromResource(appInfo.icon));
        applicationInfo.mPackageName = appInfo.packageName;
        applicationInfo.versionCode = appInfo.versionCode;
        applicationInfo.versionName = appInfo.versionName;
        applicationInfo.folderId = 0;
        applicationInfo.position = index;
        return applicationInfo;
    }

    public void initCPAAWorkspace(String pkg) {
        List<ApplicationInfo> allApps = CardDatabase.getInstance().getWorkSpaceItemDao().getAllApps();
        if ("com.ts.carplay.app".equals(pkg)) {
            for (int i = 1; i < allApps.size(); i++) {
                ApplicationInfo applicationInfo = allApps.get(i);
                applicationInfo.position++;
                CardDatabase.getInstance().getWorkSpaceItemDao().update(applicationInfo);
            }
            return;
        }
        if ("com.ts.androidauto.app".equals(pkg)) {
            for (int i2 = 2; i2 < allApps.size(); i2++) {
                ApplicationInfo applicationInfo2 = allApps.get(i2);
                applicationInfo2.position++;
                CardDatabase.getInstance().getWorkSpaceItemDao().update(applicationInfo2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void judgeCountryCode() {
        String countryCode = SpUtils.getCountryCode();
        String countryCode2 = AppUtils.getCountryCode();
        Log.i(TAG, "judgeCountryCode code = " + countryCode2 + ", localCode = " + countryCode);
        if (!TextUtils.equals(countryCode, countryCode2)) {
            SpUtils.clearFloatCard();
            Log.i(TAG, "judgeCountryCode clear!");
            SpUtils.setCountryCode(countryCode2);
            CardDatabase.getInstance().getAppInfoDao().deleteList(CardDatabase.getInstance().getAppInfoDao().getAllApp());
            CardDatabase.getInstance().getWorkSpaceItemDao().deleteList(CardDatabase.getInstance().getWorkSpaceItemDao().getAllApps());
            CardDatabase.getInstance().getSearchHistoryDao().deleteAll(CardDatabase.getInstance().getSearchHistoryDao().getAllHistory());
        }
    }

    public void checkClearCardData() {
        String countryCode = SpUtils.getCountryCode();
        String countryCode2 = AppUtils.getCountryCode();
        boolean z = true;
        boolean z2 = !TextUtils.equals(countryCode, countryCode2);
        int uiStyle = SpUtils.getUiStyle();
        final int uiStyle2 = UiStyleConfig.getInstance(SystemUIApplication.getInstance().getApplicationContext()).getUiStyle();
        if (uiStyle != uiStyle2) {
            Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.-$$Lambda$DBUtils$hwF8SMvAhgHeBpX5_xNW-0PIRrw
                @Override // java.lang.Runnable
                public final void run() {
                    SpUtils.setUiStyle(uiStyle2);
                }
            });
            z2 = true;
        }
        int autoType = SpUtils.getAutoType();
        final int autoType2 = BYDAutoBodyworkDevice.getInstance(SystemUIApplication.getInstance().getApplicationContext()).getAutoType();
        if (autoType != autoType2) {
            Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.-$$Lambda$DBUtils$2dS6qwbjHQ2MDh8O5D8oVL3zG7k
                @Override // java.lang.Runnable
                public final void run() {
                    SpUtils.setAutoType(autoType2);
                }
            });
        } else {
            z = z2;
        }
        if (z) {
            Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.-$$Lambda$P6nEZUtdOmeCZL4P_SFKc6XbTsc
                @Override // java.lang.Runnable
                public final void run() {
                    SpUtils.clearFloatCard();
                }
            });
        }
        Log.i(TAG, "handleCard needClearData = " + z + " code = " + countryCode2 + ", localCode = " + countryCode + ", style = " + uiStyle2 + ", localStyle = " + uiStyle + ", autoType = " + autoType2 + ", localAutoType = " + autoType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetData(Context context) {
        SpUtils.clearFloatCard();
        context.getApplicationContext().deleteDatabase(CardDatabase.DB_NAME);
        initDatabase(context);
        Log.i(TAG, "resetData");
    }
}
