package com.android.permissioncontroller.permission.data;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserHandle;
import android.os.UserManager;
import com.android.permissioncontroller.PermissionControllerApplication;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UsersLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000+\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\t\bÆ\u0002\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\fH\u0014J\b\u0010\u000e\u001a\u00020\fH\u0014R\u0018\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u00068\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u000f"}, d2 = {"Lcom/android/permissioncontroller/permission/data/UsersLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "", "Landroid/os/UserHandle;", "()V", "app", "Lcom/android/permissioncontroller/PermissionControllerApplication;", "kotlin.jvm.PlatformType", "mUserMonitor", "com/android/permissioncontroller/permission/data/UsersLiveData$mUserMonitor$1", "Lcom/android/permissioncontroller/permission/data/UsersLiveData$mUserMonitor$1;", "onActive", "", "onInactive", "onUpdate", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class UsersLiveData extends SmartUpdateMediatorLiveData<List<? extends UserHandle>> {
    public static final UsersLiveData INSTANCE = new UsersLiveData();
    private static final PermissionControllerApplication app = PermissionControllerApplication.get();
    private static final UsersLiveData$mUserMonitor$1 mUserMonitor = new BroadcastReceiver() { // from class: com.android.permissioncontroller.permission.data.UsersLiveData$mUserMonitor$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(intent, "intent");
            UsersLiveData.INSTANCE.onUpdate();
        }
    };

    private UsersLiveData() {
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
    protected void onUpdate() {
        Object systemService = app.getSystemService(UserManager.class);
        if (systemService == null) {
            Intrinsics.throwNpe();
        }
        setValue(((UserManager) systemService).getUserProfiles());
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onActive() {
        onUpdate();
        IntentFilter userChangeFilter = new IntentFilter();
        userChangeFilter.addAction("android.intent.action.USER_ADDED");
        userChangeFilter.addAction("android.intent.action.USER_REMOVED");
        app.registerReceiver(mUserMonitor, userChangeFilter);
        super.onActive();
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onInactive() {
        app.unregisterReceiver(mUserMonitor);
        super.onInactive();
    }
}
