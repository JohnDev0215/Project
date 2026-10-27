package com.android.permissioncontroller.permission.service;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.UserHandle;
import android.permission.PermissionManager;
import android.permission.RuntimePermissionPresentationInfo;
import android.permission.RuntimePermissionUsageInfo;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.Xml;
import com.android.permissioncontroller.PermissionControllerProto;
import com.android.permissioncontroller.PermissionControllerStatsLog;
import com.android.permissioncontroller.permission.model.AppPermissionGroup;
import com.android.permissioncontroller.permission.model.AppPermissions;
import com.android.permissioncontroller.permission.model.Permission;
import com.android.permissioncontroller.permission.model.livedatatypes.AppPermGroupUiInfo;
import com.android.permissioncontroller.permission.ui.AutoGrantPermissionsNotifier;
import com.android.permissioncontroller.permission.utils.ArrayUtils;
import com.android.permissioncontroller.permission.utils.KotlinUtils;
import com.android.permissioncontroller.permission.utils.UserSensitiveFlagsUtils;
import com.android.permissioncontroller.permission.utils.Utils;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import kotlin.Pair;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes8.dex */
public final class PermissionControllerServiceImpl extends PermissionControllerLifecycleService {
    private static final String LOG_TAG = PermissionControllerServiceImpl.class.getSimpleName();
    private static final int MAX_RETRY_ATTEMPTS = 3;
    public static final String ONE_TIME_PERMISSION_REVOKED_REASON = "one-time permission revoked";
    private static final long RETRY_DELAY_MS = 500;
    private final PermissionControllerServiceModel mServiceModel = new PermissionControllerServiceModel(this);

    @Override // com.android.permissioncontroller.permission.service.PermissionControllerLifecycleService
    public boolean onUnbind(Intent intent) {
        this.mServiceModel.removeObservers();
        return super.onUnbind(intent);
    }

    public void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        try {
            PermissionControllerProto.PermissionControllerDumpProto dump = (PermissionControllerProto.PermissionControllerDumpProto) BuildersKt.runBlocking(GlobalScope.INSTANCE.getCoroutineContext(), new Function2() { // from class: com.android.permissioncontroller.permission.service.-$$Lambda$PermissionControllerServiceImpl$Mszl9xqF24cf5Z9A4stYxEmWfJo
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return this.f$0.lambda$dump$0$PermissionControllerServiceImpl((CoroutineScope) obj, (Continuation) obj2);
                }
            });
            if (ArrayUtils.contains(args, "--proto")) {
                try {
                    OutputStream out = new FileOutputStream(fd);
                    try {
                        dump.writeTo(out);
                        out.close();
                        return;
                    } finally {
                    }
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Cannot write dump", e);
                    return;
                }
            }
            writer.println(dump.toString());
            writer.flush();
        } catch (Exception e2) {
            Log.e(LOG_TAG, "Cannot produce dump", e2);
        }
    }

    public /* synthetic */ Object lambda$dump$0$PermissionControllerServiceImpl(CoroutineScope coroutineScope, Continuation continuation) {
        return this.mServiceModel.onDump(continuation);
    }

    private ArrayList<String> addSplitPermissions(List<String> perms, int targetSDK) {
        List<PermissionManager.SplitPermissionInfo> splitPerms = ((PermissionManager) getSystemService(PermissionManager.class)).getSplitPermissions();
        ArrayList<String> expandedPerms = new ArrayList<>(perms);
        int numReqPerms = perms.size();
        for (int reqPermNum = 0; reqPermNum < numReqPerms; reqPermNum++) {
            String reqPerm = perms.get(reqPermNum);
            int numSplitPerms = splitPerms.size();
            for (int splitPermNum = 0; splitPermNum < numSplitPerms; splitPermNum++) {
                PermissionManager.SplitPermissionInfo splitPerm = splitPerms.get(splitPermNum);
                if (targetSDK < splitPerm.getTargetSdk() && splitPerm.getSplitPermission().equals(reqPerm)) {
                    expandedPerms.addAll(splitPerm.getNewPermissions());
                }
            }
        }
        return expandedPerms;
    }

    private PackageInfo getPkgInfo(String pkg) {
        try {
            return getPackageManager().getPackageInfo(pkg, 4096);
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(LOG_TAG, pkg + " not found", e);
            return null;
        }
    }

    private ArrayList<AppPermissionGroup> getRevocableGroupsForPermissions(ArrayList<String> permissions, AppPermissions appPerms) {
        ArrayList<AppPermissionGroup> groupsToRevoke = new ArrayList<>();
        int numGroups = appPerms.getPermissionGroups().size();
        for (int groupNum = 0; groupNum < numGroups; groupNum++) {
            AppPermissionGroup group = appPerms.getPermissionGroups().get(groupNum);
            if (!group.isPolicyFixed() && !group.isSystemFixed()) {
                int numPerms = permissions.size();
                for (int permNum = 0; permNum < numPerms; permNum++) {
                    String reqPerm = permissions.get(permNum);
                    if (group.hasPermission(reqPerm)) {
                        groupsToRevoke.add(group);
                        AppPermissionGroup bgPerms = group.getBackgroundPermissions();
                        if (bgPerms != null) {
                            groupsToRevoke.add(bgPerms);
                        }
                    } else {
                        AppPermissionGroup bgPerms2 = group.getBackgroundPermissions();
                        if (bgPerms2 != null && bgPerms2.hasPermission(reqPerm)) {
                            groupsToRevoke.add(bgPerms2);
                        }
                    }
                }
            }
        }
        return groupsToRevoke;
    }

    private ArrayList<String> revokePermissionGroups(ArrayList<AppPermissionGroup> groupsToRevoke) {
        ArrayList<String> revokedPerms = new ArrayList<>();
        int numGroupsToRevoke = groupsToRevoke.size();
        for (int groupsToRevokeNum = 0; groupsToRevokeNum < numGroupsToRevoke; groupsToRevokeNum++) {
            AppPermissionGroup group = groupsToRevoke.get(groupsToRevokeNum);
            ArrayList<Permission> perms = group.getPermissions();
            group.unsetReviewRequired();
            int numPerms = perms.size();
            for (int permNum = 0; permNum < numPerms; permNum++) {
                Permission perm = perms.get(permNum);
                if (perm.isGrantedIncludingAppOp()) {
                    revokedPerms.add(perm.getName());
                }
            }
            group.revokeRuntimePermissions(false);
        }
        return revokedPerms;
    }

    public /* synthetic */ void lambda$onRevokeRuntimePermissions$1$PermissionControllerServiceImpl(Consumer callback, Map request, boolean doDryRun, int reason, String callerPackageName) {
        callback.accept(onRevokeRuntimePermissions(request, doDryRun, reason, callerPackageName));
    }

    public void onRevokeRuntimePermissions(final Map<String, List<String>> request, final boolean doDryRun, final int reason, final String callerPackageName, final Consumer<Map<String, List<String>>> callback) {
        AsyncTask.execute(new Runnable() { // from class: com.android.permissioncontroller.permission.service.-$$Lambda$PermissionControllerServiceImpl$mJVi7KMHKzz_KqFHZmvDi9WTJ68
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onRevokeRuntimePermissions$1$PermissionControllerServiceImpl(callback, request, doDryRun, reason, callerPackageName);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Map<String, List<String>> onRevokeRuntimePermissions(Map<String, List<String>> request, boolean doDryRun, int reason, String callerPackageName) {
        String[] pkgNames;
        int pkgNum;
        int numPkgNames;
        String[] pkgNames2;
        PackageInfo requestedPkgInfo;
        int i = 2;
        if (reason != 1 && reason != 2) {
            Log.e(LOG_TAG, "Invalid reason " + reason);
            return Collections.emptyMap();
        }
        PackageManager pm = getPackageManager();
        PackageInfo callerPkgInfo = getPkgInfo(callerPackageName);
        if (callerPkgInfo == null) {
            return Collections.emptyMap();
        }
        int callerTargetSdk = callerPkgInfo.applicationInfo.targetSdkVersion;
        Map<String, List<String>> actuallyRevokedPerms = new ArrayMap<>();
        ArrayList<AppPermissions> appsWithRevokedPerms = new ArrayList<>();
        for (Map.Entry<String, List<String>> appRequest : request.entrySet()) {
            PackageInfo requestedPkgInfo2 = getPkgInfo(appRequest.getKey());
            if (requestedPkgInfo2 != null && (pkgNames = pm.getPackagesForUid(requestedPkgInfo2.applicationInfo.uid)) != null) {
                int numPkgNames2 = pkgNames.length;
                int pkgNum2 = 0;
                while (pkgNum2 < numPkgNames2) {
                    String pkgName = pkgNames[pkgNum2];
                    PackageInfo pkgInfo = getPkgInfo(pkgName);
                    if (pkgInfo == null) {
                        pkgNum = pkgNum2;
                        numPkgNames = numPkgNames2;
                        pkgNames2 = pkgNames;
                        requestedPkgInfo = requestedPkgInfo2;
                    } else if (reason != i || callerPackageName.equals(pm.getInstallerPackageName(pkgName))) {
                        ArrayList<String> expandedPerms = addSplitPermissions(appRequest.getValue(), callerTargetSdk);
                        pkgNum = pkgNum2;
                        numPkgNames = numPkgNames2;
                        pkgNames2 = pkgNames;
                        requestedPkgInfo = requestedPkgInfo2;
                        AppPermissions appPerms = new AppPermissions(this, pkgInfo, false, true, null);
                        ArrayList<AppPermissionGroup> groupsToRevoke = getRevocableGroupsForPermissions(expandedPerms, appPerms);
                        ArrayList<String> revokedPerms = revokePermissionGroups(groupsToRevoke);
                        if (!revokedPerms.isEmpty()) {
                            actuallyRevokedPerms.put(pkgName, revokedPerms);
                            appsWithRevokedPerms.add(appPerms);
                        }
                    } else {
                        Log.i(LOG_TAG, "Ignoring " + pkgName + " as it is not installed by " + callerPackageName);
                        pkgNum = pkgNum2;
                        numPkgNames = numPkgNames2;
                        pkgNames2 = pkgNames;
                        requestedPkgInfo = requestedPkgInfo2;
                    }
                    pkgNum2 = pkgNum + 1;
                    pkgNames = pkgNames2;
                    requestedPkgInfo2 = requestedPkgInfo;
                    numPkgNames2 = numPkgNames;
                    i = 2;
                }
                i = 2;
            }
        }
        if (!doDryRun) {
            int numChangedApps = appsWithRevokedPerms.size();
            for (int i2 = 0; i2 < numChangedApps; i2++) {
                appsWithRevokedPerms.get(i2).persistChanges(true);
            }
        }
        return actuallyRevokedPerms;
    }

    public void onGetRuntimePermissionsBackup(final UserHandle user, final OutputStream backup, final Runnable callback) {
        AsyncTask.execute(new Runnable() { // from class: com.android.permissioncontroller.permission.service.-$$Lambda$PermissionControllerServiceImpl$Gqlc5fKNqGFTSl3PSPmoOvajUgE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onGetRuntimePermissionsBackup$2$PermissionControllerServiceImpl(user, backup, callback);
            }
        });
    }

    public /* synthetic */ void lambda$onGetRuntimePermissionsBackup$2$PermissionControllerServiceImpl(UserHandle user, OutputStream backup, Runnable callback) {
        onGetRuntimePermissionsBackup(user, backup);
        callback.run();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void onGetRuntimePermissionsBackup(UserHandle user, OutputStream backup) {
        BackupHelper backupHelper = new BackupHelper(this, user);
        try {
            XmlSerializer serializer = Xml.newSerializer();
            serializer.setOutput(backup, StandardCharsets.UTF_8.name());
            backupHelper.writeState(serializer);
            serializer.flush();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Unable to write permissions backup", e);
        }
    }

    public void onStageAndApplyRuntimePermissionsBackup(final UserHandle user, final InputStream backup, final Runnable callback) {
        AsyncTask.execute(new Runnable() { // from class: com.android.permissioncontroller.permission.service.-$$Lambda$PermissionControllerServiceImpl$zU7bsnd3mqdYpSKBkbfiPf1IUc0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onStageAndApplyRuntimePermissionsBackup$3$PermissionControllerServiceImpl(user, backup, callback);
            }
        });
    }

    public /* synthetic */ void lambda$onStageAndApplyRuntimePermissionsBackup$3$PermissionControllerServiceImpl(UserHandle user, InputStream backup, Runnable callback) {
        onRestoreRuntimePermissionsBackup(user, backup);
        callback.run();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void onRestoreRuntimePermissionsBackup(UserHandle user, InputStream backup) {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(backup, StandardCharsets.UTF_8.name());
            new BackupHelper(this, user).restoreState(parser);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception restoring permissions: " + e.getMessage());
        }
    }

    public /* synthetic */ void lambda$onApplyStagedRuntimePermissionBackup$4$PermissionControllerServiceImpl(Consumer callback, String packageName, UserHandle user) {
        callback.accept(Boolean.valueOf(onRestoreDelayedRuntimePermissionsBackup(packageName, user)));
    }

    public void onApplyStagedRuntimePermissionBackup(final String packageName, final UserHandle user, final Consumer<Boolean> callback) {
        AsyncTask.execute(new Runnable() { // from class: com.android.permissioncontroller.permission.service.-$$Lambda$PermissionControllerServiceImpl$mE7NqZpMEJTKa_y63m6czFzewlg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onApplyStagedRuntimePermissionBackup$4$PermissionControllerServiceImpl(callback, packageName, user);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean onRestoreDelayedRuntimePermissionsBackup(String packageName, UserHandle user) {
        try {
            return new BackupHelper(this, user).restoreDelayedState(packageName);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception restoring delayed permissions: " + e.getMessage());
            return false;
        }
    }

    public void onGetAppPermissions(String packageName, final Consumer<List<RuntimePermissionPresentationInfo>> callback) {
        this.mServiceModel.onGetAppPermissions(packageName, new androidx.core.util.Consumer() { // from class: com.android.permissioncontroller.permission.service.-$$Lambda$PermissionControllerServiceImpl$GsH48gENeShnQ6hGRZZ5DEysN5I
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$onGetAppPermissions$5$PermissionControllerServiceImpl(callback, (List) obj);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ void lambda$onGetAppPermissions$5$PermissionControllerServiceImpl(Consumer callback, List groupUiInfos) {
        ArrayList arrayList = new ArrayList();
        Iterator it = groupUiInfos.iterator();
        while (it.hasNext()) {
            Pair<String, AppPermGroupUiInfo> groupNameAndUiInfo = (Pair) it.next();
            String groupName = groupNameAndUiInfo.getFirst();
            AppPermGroupUiInfo uiInfo = groupNameAndUiInfo.getSecond();
            boolean isPlatform = Utils.getPlatformPermissionGroups().contains(groupName);
            CharSequence label = KotlinUtils.INSTANCE.getPermGroupLabel(this, groupName);
            RuntimePermissionPresentationInfo permission = new RuntimePermissionPresentationInfo(label, (uiInfo.getPermGrantState() == AppPermGroupUiInfo.PermGrantState.PERMS_DENIED || uiInfo.getPermGrantState() == AppPermGroupUiInfo.PermGrantState.PERMS_ASK) ? false : true, isPlatform);
            arrayList.add(permission);
        }
        callback.accept(arrayList);
    }

    public void onRevokeRuntimePermission(final String packageName, final String permissionName, final Runnable callback) {
        AsyncTask.execute(new Runnable() { // from class: com.android.permissioncontroller.permission.service.-$$Lambda$PermissionControllerServiceImpl$BoRHKLsk53NQLSoqnlFJ0B0Vje8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onRevokeRuntimePermission$6$PermissionControllerServiceImpl(packageName, permissionName, callback);
            }
        });
    }

    public /* synthetic */ void lambda$onRevokeRuntimePermission$6$PermissionControllerServiceImpl(String packageName, String permissionName, Runnable callback) {
        onRevokeRuntimePermission(packageName, permissionName);
        callback.run();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void onRevokeRuntimePermission(String packageName, String permissionName) {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(packageName, 4096);
            AppPermissions appPermissions = new AppPermissions(this, packageInfo, false, null);
            AppPermissionGroup appPermissionGroup = appPermissions.getGroupForPermission(permissionName);
            if (appPermissionGroup != null) {
                appPermissionGroup.revokeRuntimePermissions(false);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(LOG_TAG, "Error getting package:" + packageName, e);
        }
    }

    public void onCountPermissionApps(List<String> permissionNames, int flags, IntConsumer callback) {
        this.mServiceModel.onCountPermissionAppsLiveData(permissionNames, flags, callback);
    }

    @Deprecated
    public void onGetPermissionUsages(boolean countSystem, long numMillis, Consumer<List<RuntimePermissionUsageInfo>> callback) {
        callback.accept(null);
    }

    public /* synthetic */ void lambda$onSetRuntimePermissionGrantStateByDeviceAdmin$7$PermissionControllerServiceImpl(Consumer callback, String callerPackageName, String packageName, String unexpandedPermission, int grantState) {
        callback.accept(Boolean.valueOf(onSetRuntimePermissionGrantStateByDeviceAdmin(callerPackageName, packageName, unexpandedPermission, grantState)));
    }

    public void onSetRuntimePermissionGrantStateByDeviceAdmin(final String callerPackageName, final String packageName, final String unexpandedPermission, final int grantState, final Consumer<Boolean> callback) {
        AsyncTask.execute(new Runnable() { // from class: com.android.permissioncontroller.permission.service.-$$Lambda$PermissionControllerServiceImpl$tjPlA7RykY9FEl7k5KJev2d1SgA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onSetRuntimePermissionGrantStateByDeviceAdmin$7$PermissionControllerServiceImpl(callback, callerPackageName, packageName, unexpandedPermission, grantState);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean onSetRuntimePermissionGrantStateByDeviceAdmin(String callerPackageName, String packageName, String unexpandedPermission, int grantState) {
        int numPerms;
        PackageInfo callerPkgInfo = getPkgInfo(callerPackageName);
        if (callerPkgInfo != null) {
            PackageInfo pkgInfo = getPkgInfo(packageName);
            if (pkgInfo == null) {
                Log.w(LOG_TAG, "Cannot fix " + unexpandedPermission + " as " + packageName + " cannot be found");
                return false;
            }
            ArrayList<String> expandedPermissions = addSplitPermissions(Collections.singletonList(unexpandedPermission), callerPkgInfo.applicationInfo.targetSdkVersion);
            AppPermissions app = new AppPermissions(this, pkgInfo, false, true, null);
            AutoGrantPermissionsNotifier autoGrantPermissionsNotifier = new AutoGrantPermissionsNotifier(this, pkgInfo);
            int numPerms2 = expandedPermissions.size();
            int i = 0;
            while (i < numPerms2) {
                String permName = expandedPermissions.get(i);
                AppPermissionGroup group = app.getGroupForPermission(permName);
                if (group == null) {
                    numPerms = numPerms2;
                } else if (group.isSystemFixed()) {
                    numPerms = numPerms2;
                } else {
                    Permission perm = group.getPermission(permName);
                    if (perm == null) {
                        numPerms = numPerms2;
                    } else if (grantState == 0) {
                        numPerms = numPerms2;
                        perm.setPolicyFixed(false);
                    } else if (grantState == 1) {
                        numPerms = numPerms2;
                        perm.setPolicyFixed(true);
                        group.grantRuntimePermissions(false, false, new String[]{permName});
                        autoGrantPermissionsNotifier.onPermissionAutoGranted(permName);
                    } else if (grantState == 2) {
                        perm.setPolicyFixed(true);
                        numPerms = numPerms2;
                        group.revokeRuntimePermissions(false, new String[]{permName});
                    } else {
                        return false;
                    }
                }
                i++;
                numPerms2 = numPerms;
            }
            app.persistChanges(grantState == 2 || !callerPackageName.equals(packageName));
            autoGrantPermissionsNotifier.notifyOfAutoGrantPermissions(false);
            return true;
        }
        Log.w(LOG_TAG, "Cannot fix " + unexpandedPermission + " as admin " + callerPackageName + " cannot be found");
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onGrantOrUpgradeDefaultRuntimePermissions(final Runnable callback) {
        performDefaultPermissionGrants();
        RuntimePermissionsUpgradeController.INSTANCE.upgradeIfNeeded(this, new Runnable() { // from class: com.android.permissioncontroller.permission.service.-$$Lambda$PermissionControllerServiceImpl$Ujh6sVS_PGChOVCu9FJsrG5xvTg
            @Override // java.lang.Runnable
            public final void run() {
                callback.run();
            }
        });
    }

    private void performDefaultPermissionGrants() {
    }

    public void onUpdateUserSensitivePermissionFlags(int uid, Executor executor, Runnable callback) throws Exception {
        lambda$onUpdateUserSensistivePermissionFlagsWithRetry$11$PermissionControllerServiceImpl(uid, executor, callback, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onUpdateUserSensistivePermissionFlagsWithRetry, reason: merged with bridge method [inline-methods] */
    public void lambda$onUpdateUserSensistivePermissionFlagsWithRetry$11$PermissionControllerServiceImpl(final int uid, final Executor executor, final Runnable callback, int numAttempts) throws Exception {
        StringBuilder sb;
        if (uid == -1) {
            sb = new StringBuilder();
            sb.append("user ");
            sb.append(Process.myUserHandle().getIdentifier());
        } else {
            sb = new StringBuilder();
            sb.append("uid ");
            sb.append(uid);
        }
        String idString = sb.toString();
        try {
            Log.i(LOG_TAG, "Updating user sensitive for " + idString);
            if (uid == -1) {
                UserSensitiveFlagsUtils.updateUserSensitiveForUser(Process.myUserHandle(), new Runnable() { // from class: com.android.permissioncontroller.permission.service.-$$Lambda$PermissionControllerServiceImpl$-dBcGJHpuWO7Xp58tUz-7gkxSJk
                    @Override // java.lang.Runnable
                    public final void run() {
                        executor.execute(callback);
                    }
                });
            } else {
                UserSensitiveFlagsUtils.updateUserSensitiveForUid(uid, new Runnable() { // from class: com.android.permissioncontroller.permission.service.-$$Lambda$PermissionControllerServiceImpl$-aWI_XGBrblNIgk8F7TA2yeUffY
                    @Override // java.lang.Runnable
                    public final void run() {
                        executor.execute(callback);
                    }
                });
            }
        } catch (Exception e) {
            Log.w(LOG_TAG, "Failed to complete user sensitive update for " + idString + ", attempt number " + (numAttempts + 1) + " of 3", e);
            if (numAttempts == 3) {
                throw e;
            }
            final int attempts = numAttempts + 1;
            Handler h = new Handler(Looper.getMainLooper());
            h.postDelayed(new Runnable() { // from class: com.android.permissioncontroller.permission.service.-$$Lambda$PermissionControllerServiceImpl$2l-V8VXBCVRPaNCUF4I08gpAv9Y
                @Override // java.lang.Runnable
                public final void run() throws Exception {
                    this.f$0.lambda$onUpdateUserSensistivePermissionFlagsWithRetry$11$PermissionControllerServiceImpl(uid, executor, callback, attempts);
                }
            }, RETRY_DELAY_MS);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onOneTimePermissionSessionTimeout(String packageName) throws PackageManager.NameNotFoundException {
        boolean z;
        AppPermissionGroup group;
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(packageName, 4096);
            boolean z2 = false;
            int uid = pm.getPackageUid(packageName, 0);
            String[] permissions = packageInfo.requestedPermissions;
            if (permissions == null) {
                return;
            }
            Set<AppPermissionGroup> groups = new ArraySet<>();
            for (String permission : permissions) {
                AppPermissionGroup group2 = AppPermissionGroup.create(this, packageInfo, permission, true);
                if (group2 != null && group2.isOneTime()) {
                    groups.add(group2);
                }
            }
            long requestId = Utils.getValidSessionId();
            for (AppPermissionGroup group3 : groups) {
                if (!group3.areRuntimePermissionsGranted()) {
                    z = z2;
                    group = group3;
                } else {
                    group = group3;
                    logOneTimeSessionRevoke(packageName, uid, group3, requestId);
                    z = false;
                    group.revokeRuntimePermissions(false);
                }
                group.setUserSet(z);
                group.persistChanges(z, ONE_TIME_PERMISSION_REVOKED_REASON);
                z2 = z;
            }
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void logOneTimeSessionRevoke(String packageName, int uid, AppPermissionGroup group, long requestId) {
        for (Permission permission : group.getPermissions()) {
            if (permission.isGranted()) {
                String permName = permission.getName();
                Log.v(LOG_TAG, "Permission grant result requestId=" + requestId + " callingUid=" + uid + " callingPackage=" + packageName + " permission=" + permName + " isImplicit=false result=15");
                PermissionControllerStatsLog.write(PermissionControllerStatsLog.PERMISSION_GRANT_REQUEST_RESULT_REPORTED, requestId, uid, packageName, permName, false, 15);
            }
        }
    }
}
