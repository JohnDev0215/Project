package com.android.launcher3.model;

import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherActivityInfo;
import android.os.UserHandle;
import com.android.launcher3.AllAppsList;
import com.android.launcher3.AppInfo;
import com.android.launcher3.InstallShortcutReceiver;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.util.PackageManagerHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class PackageUpdatedTask extends BaseModelUpdateTask {
    private static final boolean DEBUG = false;
    public static final int OP_ADD = 1;
    public static final int OP_NONE = 0;
    public static final int OP_REMOVE = 3;
    public static final int OP_SUSPEND = 5;
    public static final int OP_UNAVAILABLE = 4;
    public static final int OP_UNSUSPEND = 6;
    public static final int OP_UPDATE = 2;
    public static final int OP_USER_AVAILABILITY_CHANGE = 7;
    private static final String TAG = "PackageUpdatedTask";
    private final int mOp;
    private final String[] mPackages;
    private final UserHandle mUser;

    public PackageUpdatedTask(int op, UserHandle user, String... packages) {
        this.mOp = op;
        this.mUser = user;
        this.mPackages = packages;
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x02b0 A[Catch: all -> 0x0402, TryCatch #0 {, blocks: (B:58:0x016a, B:59:0x0170, B:61:0x0176, B:63:0x0186, B:65:0x0192, B:67:0x0198, B:69:0x01a2, B:71:0x01b1, B:73:0x01b7, B:75:0x01bd, B:77:0x01c3, B:79:0x01d3, B:83:0x01e3, B:86:0x01eb, B:88:0x01f0, B:96:0x023f, B:99:0x0248, B:118:0x02b0, B:120:0x02b4, B:121:0x02bd, B:130:0x02e4, B:128:0x02df, B:102:0x0250, B:104:0x0256, B:108:0x0272, B:109:0x0297, B:91:0x0217, B:92:0x0225, B:94:0x0233, B:112:0x02a0, B:114:0x02a6, B:132:0x02ee, B:135:0x02fb, B:137:0x0307, B:139:0x030e, B:141:0x031a, B:146:0x034b), top: B:185:0x016a }] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x02d8 A[PHI: r10 r21 r22 r23 r24
  0x02d8: PHI (r10v10 boolean) = (r10v9 boolean), (r10v16 boolean) binds: [B:124:0x02cf, B:122:0x02c9] A[DONT_GENERATE, DONT_INLINE]
  0x02d8: PHI (r21v1 boolean) = (r21v0 boolean), (r21v6 boolean) binds: [B:124:0x02cf, B:122:0x02c9] A[DONT_GENERATE, DONT_INLINE]
  0x02d8: PHI (r22v5 com.android.launcher3.util.ItemInfoMatcher) = (r22v4 com.android.launcher3.util.ItemInfoMatcher), (r22v7 com.android.launcher3.util.ItemInfoMatcher) binds: [B:124:0x02cf, B:122:0x02c9] A[DONT_GENERATE, DONT_INLINE]
  0x02d8: PHI (r23v6 java.lang.Boolean) = (r23v5 java.lang.Boolean), (r23v9 java.lang.Boolean) binds: [B:124:0x02cf, B:122:0x02c9] A[DONT_GENERATE, DONT_INLINE]
  0x02d8: PHI (r24v3 java.util.ArrayList) = (r24v2 java.util.ArrayList), (r24v7 java.util.ArrayList) binds: [B:124:0x02cf, B:122:0x02c9] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x02dd A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x02df A[Catch: all -> 0x0402, TryCatch #0 {, blocks: (B:58:0x016a, B:59:0x0170, B:61:0x0176, B:63:0x0186, B:65:0x0192, B:67:0x0198, B:69:0x01a2, B:71:0x01b1, B:73:0x01b7, B:75:0x01bd, B:77:0x01c3, B:79:0x01d3, B:83:0x01e3, B:86:0x01eb, B:88:0x01f0, B:96:0x023f, B:99:0x0248, B:118:0x02b0, B:120:0x02b4, B:121:0x02bd, B:130:0x02e4, B:128:0x02df, B:102:0x0250, B:104:0x0256, B:108:0x0272, B:109:0x0297, B:91:0x0217, B:92:0x0225, B:94:0x0233, B:112:0x02a0, B:114:0x02a6, B:132:0x02ee, B:135:0x02fb, B:137:0x0307, B:139:0x030e, B:141:0x031a, B:146:0x034b), top: B:185:0x016a }] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x02e4 A[Catch: all -> 0x0402, TryCatch #0 {, blocks: (B:58:0x016a, B:59:0x0170, B:61:0x0176, B:63:0x0186, B:65:0x0192, B:67:0x0198, B:69:0x01a2, B:71:0x01b1, B:73:0x01b7, B:75:0x01bd, B:77:0x01c3, B:79:0x01d3, B:83:0x01e3, B:86:0x01eb, B:88:0x01f0, B:96:0x023f, B:99:0x0248, B:118:0x02b0, B:120:0x02b4, B:121:0x02bd, B:130:0x02e4, B:128:0x02df, B:102:0x0250, B:104:0x0256, B:108:0x0272, B:109:0x0297, B:91:0x0217, B:92:0x0225, B:94:0x0233, B:112:0x02a0, B:114:0x02a6, B:132:0x02ee, B:135:0x02fb, B:137:0x0307, B:139:0x030e, B:141:0x031a, B:146:0x034b), top: B:185:0x016a }] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0333 A[PHI: r20 r22 r23 r24
  0x0333: PHI (r20v5 java.lang.String[]) = 
  (r20v2 java.lang.String[])
  (r20v2 java.lang.String[])
  (r20v2 java.lang.String[])
  (r20v2 java.lang.String[])
  (r20v2 java.lang.String[])
  (r20v6 java.lang.String[])
  (r20v6 java.lang.String[])
 binds: [B:133:0x02f7, B:134:0x02f9, B:136:0x0305, B:138:0x030c, B:140:0x0318, B:129:0x02e2, B:130:0x02e4] A[DONT_GENERATE, DONT_INLINE]
  0x0333: PHI (r22v3 com.android.launcher3.util.ItemInfoMatcher) = 
  (r22v0 com.android.launcher3.util.ItemInfoMatcher)
  (r22v0 com.android.launcher3.util.ItemInfoMatcher)
  (r22v0 com.android.launcher3.util.ItemInfoMatcher)
  (r22v0 com.android.launcher3.util.ItemInfoMatcher)
  (r22v0 com.android.launcher3.util.ItemInfoMatcher)
  (r22v6 com.android.launcher3.util.ItemInfoMatcher)
  (r22v6 com.android.launcher3.util.ItemInfoMatcher)
 binds: [B:133:0x02f7, B:134:0x02f9, B:136:0x0305, B:138:0x030c, B:140:0x0318, B:129:0x02e2, B:130:0x02e4] A[DONT_GENERATE, DONT_INLINE]
  0x0333: PHI (r23v4 java.lang.Boolean) = 
  (r23v1 java.lang.Boolean)
  (r23v1 java.lang.Boolean)
  (r23v1 java.lang.Boolean)
  (r23v1 java.lang.Boolean)
  (r23v1 java.lang.Boolean)
  (r23v7 java.lang.Boolean)
  (r23v7 java.lang.Boolean)
 binds: [B:133:0x02f7, B:134:0x02f9, B:136:0x0305, B:138:0x030c, B:140:0x0318, B:129:0x02e2, B:130:0x02e4] A[DONT_GENERATE, DONT_INLINE]
  0x0333: PHI (r24v1 java.util.ArrayList) = 
  (r24v0 java.util.ArrayList)
  (r24v0 java.util.ArrayList)
  (r24v0 java.util.ArrayList)
  (r24v0 java.util.ArrayList)
  (r24v0 java.util.ArrayList)
  (r24v4 java.util.ArrayList)
  (r24v4 java.util.ArrayList)
 binds: [B:133:0x02f7, B:134:0x02f9, B:136:0x0305, B:138:0x030c, B:140:0x0318, B:129:0x02e2, B:130:0x02e4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:169:0x03aa  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01b6  */
    @Override // com.android.launcher3.model.BaseModelUpdateTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute(com.android.launcher3.LauncherAppState r26, com.android.launcher3.model.BgDataModel r27, com.android.launcher3.AllAppsList r28) {
        /*
            Method dump skipped, instruction units count: 1048
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.model.PackageUpdatedTask.execute(com.android.launcher3.LauncherAppState, com.android.launcher3.model.BgDataModel, com.android.launcher3.AllAppsList):void");
    }

    private void updateToWorkSpace(Context context, LauncherAppState app, AllAppsList appsList) {
        ArrayList arrayList = new ArrayList();
        List<UserHandle> userProfiles = UserManagerCompat.getInstance(context).getUserProfiles();
        ArrayList arrayList2 = new ArrayList();
        Iterator<UserHandle> it = userProfiles.iterator();
        while (it.hasNext()) {
            List<LauncherActivityInfo> activityList = LauncherAppsCompat.getInstance(context).getActivityList(null, it.next());
            synchronized (this) {
                for (LauncherActivityInfo launcherActivityInfo : activityList) {
                    Iterator<AppInfo> it2 = appsList.added.iterator();
                    while (it2.hasNext()) {
                        if (launcherActivityInfo.getComponentName().equals(it2.next().componentName)) {
                            InstallShortcutReceiver.PendingInstallShortcutInfo pendingInstallShortcutInfo = new InstallShortcutReceiver.PendingInstallShortcutInfo(launcherActivityInfo, context);
                            arrayList2.add(pendingInstallShortcutInfo);
                            arrayList.add(pendingInstallShortcutInfo.getItemInfo());
                        }
                    }
                }
            }
        }
        if (arrayList2.isEmpty()) {
            return;
        }
        app.getModel().addAndBindAddedWorkspaceItems(arrayList);
    }

    private boolean updateWorkspaceItemIntent(Context context, WorkspaceItemInfo si, String packageName) {
        Intent appLaunchIntent = new PackageManagerHelper(context).getAppLaunchIntent(packageName, this.mUser);
        if (appLaunchIntent == null) {
            return false;
        }
        si.intent = appLaunchIntent;
        si.status = 0;
        return true;
    }
}
