package com.android.permissioncontroller.permission.data;

import android.app.Application;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.util.Log;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.permission.data.PackageBroadcastReceiver;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPermInfo;
import com.android.permissioncontroller.permission.utils.Utils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: LightPermInfoLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u001aB\u0017\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\r\u001a\u00020\fH\u0002J\u0019\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J\b\u0010\u0013\u001a\u00020\u000fH\u0014J\b\u0010\u0014\u001a\u00020\u000fH\u0014J\u0010\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0007H\u0016J\b\u0010\u0017\u001a\u00020\u000fH\u0002J\b\u0010\u0018\u001a\u00020\u000fH\u0002J\b\u0010\u0019\u001a\u00020\u000fH\u0016R\u0016\u0010\t\u001a\n \n*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"Lcom/android/permissioncontroller/permission/data/LightPermInfoLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartAsyncMediatorLiveData;", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermInfo;", "Lcom/android/permissioncontroller/permission/data/PackageBroadcastReceiver$PackageBroadcastListener;", "app", "Landroid/app/Application;", "permissionName", "", "(Landroid/app/Application;Ljava/lang/String;)V", "LOG_TAG", "kotlin.jvm.PlatformType", "isListeningForChanges", "", "isImmutable", "loadDataAndPostValue", "", "job", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onActive", "onInactive", "onPackageUpdate", "ignored", "startListeningForChanges", "stopListeningForChanges", "updateAsync", "Companion", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class LightPermInfoLiveData extends SmartAsyncMediatorLiveData<LightPermInfo> implements PackageBroadcastReceiver.PackageBroadcastListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String LOG_TAG;
    private final Application app;
    private boolean isListeningForChanges;
    private final String permissionName;

    private LightPermInfoLiveData(Application app, String permissionName) {
        this.app = app;
        this.permissionName = permissionName;
        this.LOG_TAG = LightPermInfoLiveData.class.getSimpleName();
    }

    public /* synthetic */ LightPermInfoLiveData(Application app, String permissionName, DefaultConstructorMarker $constructor_marker) {
        this(app, permissionName);
    }

    @Override // com.android.permissioncontroller.permission.data.PackageBroadcastReceiver.PackageBroadcastListener
    public void onPackageUpdate(String ignored) {
        Intrinsics.checkParameterIsNotNull(ignored, "ignored");
        updateAsync();
    }

    @Override // com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData
    public void updateAsync() {
        if (getValue() != null && isImmutable()) {
            return;
        }
        super.updateAsync();
    }

    @Override // com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData
    public Object loadDataAndPostValue(Job job, Continuation<? super Unit> continuation) {
        LightPermInfo newValue;
        if (job.isCancelled()) {
            return Unit.INSTANCE;
        }
        try {
            PermissionInfo permissionInfo = this.app.getPackageManager().getPermissionInfo(this.permissionName, 0);
            Intrinsics.checkExpressionValueIsNotNull(permissionInfo, "app.packageManager.getPe…onInfo(permissionName, 0)");
            newValue = new LightPermInfo(permissionInfo);
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(this.LOG_TAG, "Permission \"" + this.permissionName + "\" not found");
            INSTANCE.invalidateSingle(this.permissionName);
            newValue = null;
        }
        if (isImmutable()) {
            stopListeningForChanges();
        }
        postValue(newValue);
        return Unit.INSTANCE;
    }

    private final boolean isImmutable() {
        LightPermInfo it = getValue();
        if (it != null && Intrinsics.areEqual(it.getPackageName(), Utils.OS_PKG)) {
            return true;
        }
        return Utils.isRuntimePlatformPermission(this.permissionName);
    }

    private final void startListeningForChanges() {
        if (!this.isListeningForChanges && !isImmutable()) {
            this.isListeningForChanges = true;
            PackageBroadcastReceiver.INSTANCE.addAllCallback(this);
        }
    }

    private final void stopListeningForChanges() {
        if (this.isListeningForChanges) {
            PackageBroadcastReceiver.INSTANCE.removeAllCallback(this);
            this.isListeningForChanges = false;
        }
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onActive() {
        super.onActive();
        startListeningForChanges();
        updateAsync();
    }

    @Override // com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData, com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onInactive() {
        super.onInactive();
        stopListeningForChanges();
    }

    /* JADX INFO: compiled from: LightPermInfoLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0002H\u0014¨\u0006\u0007"}, d2 = {"Lcom/android/permissioncontroller/permission/data/LightPermInfoLiveData$Companion;", "Lcom/android/permissioncontroller/permission/data/DataRepositoryForPackage;", "", "Lcom/android/permissioncontroller/permission/data/LightPermInfoLiveData;", "()V", "newValue", "key", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class Companion extends DataRepositoryForPackage<String, LightPermInfoLiveData> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.permissioncontroller.permission.data.DataRepository
        public LightPermInfoLiveData newValue(String key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
            Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
            return new LightPermInfoLiveData(permissionControllerApplication, key, null);
        }
    }
}
