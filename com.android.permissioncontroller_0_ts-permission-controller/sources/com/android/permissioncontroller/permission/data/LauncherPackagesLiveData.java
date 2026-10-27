package com.android.permissioncontroller.permission.data;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.permission.data.PackageBroadcastReceiver;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: LauncherPackagesLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00012\u00020\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0005J\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\nH\u0014J\b\u0010\u000f\u001a\u00020\nH\u0014J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0003H\u0016R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lcom/android/permissioncontroller/permission/data/LauncherPackagesLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartAsyncMediatorLiveData;", "", "", "Lcom/android/permissioncontroller/permission/data/PackageBroadcastReceiver$PackageBroadcastListener;", "()V", "LAUNCHER_INTENT", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "loadDataAndPostValue", "", "job", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onActive", "onInactive", "onPackageUpdate", "packageName", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class LauncherPackagesLiveData extends SmartAsyncMediatorLiveData<Set<? extends String>> implements PackageBroadcastReceiver.PackageBroadcastListener {
    public static final LauncherPackagesLiveData INSTANCE = new LauncherPackagesLiveData();
    private static final Intent LAUNCHER_INTENT = new Intent("android.intent.action.MAIN", (Uri) null).addCategory("android.intent.category.LAUNCHER");

    private LauncherPackagesLiveData() {
    }

    @Override // com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData
    public Object loadDataAndPostValue(Job job, Continuation<? super Unit> continuation) {
        Log.i("LancherPackagesLiveData", "updating LauncherPackageLiveData");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
        Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
        for (ResolveInfo info : permissionControllerApplication.getPackageManager().queryIntentActivities(LAUNCHER_INTENT, 786432)) {
            String str = info.activityInfo.packageName;
            Intrinsics.checkExpressionValueIsNotNull(str, "info.activityInfo.packageName");
            linkedHashSet.add(str);
        }
        postValue(linkedHashSet);
        return Unit.INSTANCE;
    }

    @Override // com.android.permissioncontroller.permission.data.PackageBroadcastReceiver.PackageBroadcastListener
    public void onPackageUpdate(String packageName) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        updateIfActive();
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onActive() {
        super.onActive();
        updateIfActive();
        PackageBroadcastReceiver.INSTANCE.addAllCallback(this);
    }

    @Override // com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData, com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onInactive() {
        super.onInactive();
        PackageBroadcastReceiver.INSTANCE.removeAllCallback(this);
    }
}
