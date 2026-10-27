package com.android.permissioncontroller.permission.service;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.os.UserHandle;
import android.os.UserManager;
import com.android.permissioncontroller.DumpableLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoRevokePermissions.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/android/permissioncontroller/permission/service/AutoRevokeOnBootReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class AutoRevokeOnBootReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) throws Throwable {
        Intrinsics.checkParameterIsNotNull(context, "context");
        AutoRevokePermissions.getFirstBootTime(context);
        DumpableLog.i$default(DumpableLog.INSTANCE, "AutoRevokePermissions", "scheduleAutoRevokePermissions with frequency " + AutoRevokePermissions.getCheckFrequencyMs(context) + "ms and threshold " + AutoRevokePermissions.getUnusedThresholdMs(context) + "ms", null, 4, null);
        Object systemService = context.getSystemService((Class<Object>) UserManager.class);
        if (systemService == null) {
            Intrinsics.throwNpe();
        }
        UserManager userManager = (UserManager) systemService;
        if (userManager.isProfile()) {
            DumpableLog dumpableLog = DumpableLog.INSTANCE;
            StringBuilder sb = new StringBuilder();
            sb.append("user ");
            UserHandle userHandleMyUserHandle = Process.myUserHandle();
            Intrinsics.checkExpressionValueIsNotNull(userHandleMyUserHandle, "myUserHandle()");
            sb.append(userHandleMyUserHandle.getIdentifier());
            sb.append(" is a profile. Not ");
            sb.append("running Auto Revoke.");
            DumpableLog.i$default(dumpableLog, "AutoRevokePermissions", sb.toString(), null, 4, null);
            return;
        }
        DumpableLog dumpableLog2 = DumpableLog.INSTANCE;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("user ");
        UserHandle userHandleMyUserHandle2 = Process.myUserHandle();
        Intrinsics.checkExpressionValueIsNotNull(userHandleMyUserHandle2, "myUserHandle()");
        sb2.append(userHandleMyUserHandle2.getIdentifier());
        sb2.append(" is a profile owner. ");
        sb2.append("Running Auto Revoke.");
        DumpableLog.i$default(dumpableLog2, "AutoRevokePermissions", sb2.toString(), null, 4, null);
        AutoRevokePermissions.SKIP_NEXT_RUN = true;
        JobInfo jobInfo = new JobInfo.Builder(2, new ComponentName(context, (Class<?>) AutoRevokeService.class)).setPeriodic(AutoRevokePermissions.getCheckFrequencyMs(context)).build();
        Object systemService2 = context.getSystemService((Class<Object>) JobScheduler.class);
        if (systemService2 == null) {
            Intrinsics.throwNpe();
        }
        int status = ((JobScheduler) systemService2).schedule(jobInfo);
        if (status != 1) {
            DumpableLog.e$default(DumpableLog.INSTANCE, "AutoRevokePermissions", "Could not schedule " + AutoRevokeService.class.getSimpleName() + ": " + status, null, 4, null);
        }
    }
}
