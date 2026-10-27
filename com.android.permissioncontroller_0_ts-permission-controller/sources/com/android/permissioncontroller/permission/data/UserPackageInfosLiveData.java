package com.android.permissioncontroller.permission.data;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.UserHandle;
import android.util.Log;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.permission.data.PackageBroadcastReceiver;
import com.android.permissioncontroller.permission.data.PermissionListenerMultiplexer;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: UserPackageInfosLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u001f2\u0013\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00040\u00012\u00020\u00052\u00020\u0006:\u0001\u001fB\u0017\b\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0019\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\b\u0010\u0017\u001a\u00020\u0013H\u0014J\b\u0010\u0018\u001a\u00020\u0013H\u0014J\u0010\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0013H\u0016J\u0018\u0010\u001d\u001a\u00020\u00132\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"Lcom/android/permissioncontroller/permission/data/UserPackageInfosLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartAsyncMediatorLiveData;", "", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;", "Lkotlin/jvm/JvmSuppressWildcards;", "Lcom/android/permissioncontroller/permission/data/PackageBroadcastReceiver$PackageBroadcastListener;", "Lcom/android/permissioncontroller/permission/data/PermissionListenerMultiplexer$PermissionChangeCallback;", "app", "Landroid/app/Application;", "user", "Landroid/os/UserHandle;", "(Landroid/app/Application;Landroid/os/UserHandle;)V", "permChangeStale", "", "getPermChangeStale", "()Z", "setPermChangeStale", "(Z)V", "loadDataAndPostValue", "", "job", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onActive", "onInactive", "onPackageUpdate", "packageName", "", "onPermissionChange", "setValue", "newValue", "Companion", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class UserPackageInfosLiveData extends SmartAsyncMediatorLiveData<List<LightPackageInfo>> implements PackageBroadcastReceiver.PackageBroadcastListener, PermissionListenerMultiplexer.PermissionChangeCallback {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Application app;
    private boolean permChangeStale;
    private final UserHandle user;

    private UserPackageInfosLiveData(Application app, UserHandle user) {
        this.app = app;
        this.user = user;
    }

    public /* synthetic */ UserPackageInfosLiveData(Application app, UserHandle user, DefaultConstructorMarker $constructor_marker) {
        this(app, user);
    }

    public final boolean getPermChangeStale() {
        return this.permChangeStale;
    }

    public final void setPermChangeStale(boolean z) {
        this.permChangeStale = z;
    }

    @Override // com.android.permissioncontroller.permission.data.PackageBroadcastReceiver.PackageBroadcastListener
    public void onPackageUpdate(String packageName) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        updateAsync();
    }

    @Override // com.android.permissioncontroller.permission.data.PermissionListenerMultiplexer.PermissionChangeCallback
    public void onPermissionChange() {
        this.permChangeStale = true;
        List<LightPackageInfo> value = getValue();
        if (value == null) {
            value = CollectionsKt.emptyList();
        }
        for (LightPackageInfo packageInfo : value) {
            PermissionListenerMultiplexer.INSTANCE.removeCallback(packageInfo.getUid(), this);
        }
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
    public void setValue(List<LightPackageInfo> newValue) {
        if (!Intrinsics.areEqual(newValue, getValue())) {
            List<LightPackageInfo> value = getValue();
            if (value == null) {
                value = CollectionsKt.emptyList();
            }
            for (LightPackageInfo packageInfo : value) {
                PermissionListenerMultiplexer.INSTANCE.removeCallback(packageInfo.getUid(), this);
            }
            for (LightPackageInfo packageInfo2 : newValue != null ? newValue : CollectionsKt.emptyList()) {
                PermissionListenerMultiplexer.INSTANCE.addCallback(packageInfo2.getUid(), this);
            }
        }
        super.setValue(newValue);
        this.permChangeStale = false;
    }

    @Override // com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData
    public Object loadDataAndPostValue(Job job, Continuation<? super Unit> continuation) {
        if (job.isCancelled()) {
            return Unit.INSTANCE;
        }
        Log.i("UserPackageInfos", "updating UserPackageInfosLiveData for user " + this.user.getIdentifier());
        Context applicationContext = this.app.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "app.applicationContext");
        Iterable packageInfos = applicationContext.getPackageManager().getInstalledPackagesAsUser(135168, this.user.getIdentifier());
        Intrinsics.checkExpressionValueIsNotNull(packageInfos, "packageInfos");
        Iterable $this$map$iv = packageInfos;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            PackageInfo packageInfo = (PackageInfo) item$iv$iv;
            Intrinsics.checkExpressionValueIsNotNull(packageInfo, "packageInfo");
            destination$iv$iv.add(new LightPackageInfo(packageInfo));
        }
        postValue((List) destination$iv$iv);
        return Unit.INSTANCE;
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onActive() {
        super.onActive();
        PackageBroadcastReceiver.INSTANCE.addAllCallback(this);
        List<LightPackageInfo> value = getValue();
        if (value == null) {
            value = CollectionsKt.emptyList();
        }
        for (LightPackageInfo packageInfo : value) {
            PermissionListenerMultiplexer.INSTANCE.addCallback(packageInfo.getUid(), this);
        }
        updateAsync();
    }

    @Override // com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData, com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onInactive() {
        super.onInactive();
        List<LightPackageInfo> value = getValue();
        if (value == null) {
            value = CollectionsKt.emptyList();
        }
        for (LightPackageInfo packageInfo : value) {
            PermissionListenerMultiplexer.INSTANCE.removeCallback(packageInfo.getUid(), this);
        }
        PackageBroadcastReceiver.INSTANCE.removeAllCallback(this);
    }

    /* JADX INFO: compiled from: UserPackageInfosLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0002H\u0014¨\u0006\u0007"}, d2 = {"Lcom/android/permissioncontroller/permission/data/UserPackageInfosLiveData$Companion;", "Lcom/android/permissioncontroller/permission/data/DataRepository;", "Landroid/os/UserHandle;", "Lcom/android/permissioncontroller/permission/data/UserPackageInfosLiveData;", "()V", "newValue", "key", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class Companion extends DataRepository<UserHandle, UserPackageInfosLiveData> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.permissioncontroller.permission.data.DataRepository
        public UserPackageInfosLiveData newValue(UserHandle key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
            Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
            return new UserPackageInfosLiveData(permissionControllerApplication, key, null);
        }
    }
}
