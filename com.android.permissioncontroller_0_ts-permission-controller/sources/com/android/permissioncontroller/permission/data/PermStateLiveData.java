package com.android.permissioncontroller.permission.data;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.permission.data.PermissionListenerMultiplexer;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPermInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.PermGroup;
import com.android.permissioncontroller.permission.model.livedatatypes.PermState;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: PermStateLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 #2\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u00012\u00020\u0005:\u0001#B'\b\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0010H\u0002J\u0019\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001eH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\b\u0010 \u001a\u00020\u001aH\u0014J\b\u0010!\u001a\u00020\u001aH\u0014J\b\u0010\"\u001a\u00020\u001aH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0017R\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0017R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, d2 = {"Lcom/android/permissioncontroller/permission/data/PermStateLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartAsyncMediatorLiveData;", "", "", "Lcom/android/permissioncontroller/permission/model/livedatatypes/PermState;", "Lcom/android/permissioncontroller/permission/data/PermissionListenerMultiplexer$PermissionChangeCallback;", "app", "Landroid/app/Application;", "packageName", "permGroupName", "user", "Landroid/os/UserHandle;", "(Landroid/app/Application;Ljava/lang/String;Ljava/lang/String;Landroid/os/UserHandle;)V", "context", "Landroid/content/Context;", "currentPackageInfo", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;", "groupLiveData", "Lcom/android/permissioncontroller/permission/data/PermGroupLiveData;", "packageInfoLiveData", "Lcom/android/permissioncontroller/permission/data/LightPackageInfoLiveData;", "registeredUid", "", "Ljava/lang/Integer;", "uid", "checkForUidUpdate", "", "packageInfo", "loadDataAndPostValue", "job", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onActive", "onInactive", "onPermissionChange", "Companion", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class PermStateLiveData extends SmartAsyncMediatorLiveData<Map<String, ? extends PermState>> implements PermissionListenerMultiplexer.PermissionChangeCallback {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Application app;
    private final Context context;
    private LightPackageInfo currentPackageInfo;
    private final PermGroupLiveData groupLiveData;
    private final LightPackageInfoLiveData packageInfoLiveData;
    private final String packageName;
    private final String permGroupName;
    private Integer registeredUid;
    private Integer uid;
    private final UserHandle user;

    private PermStateLiveData(Application app, String packageName, String permGroupName, UserHandle user) throws PackageManager.NameNotFoundException {
        this.app = app;
        this.packageName = packageName;
        this.permGroupName = permGroupName;
        this.user = user;
        Context userContext = Utils.getUserContext(app, user);
        Intrinsics.checkExpressionValueIsNotNull(userContext, "Utils.getUserContext(app, user)");
        this.context = userContext;
        this.packageInfoLiveData = (LightPackageInfoLiveData) DataRepositoryKt.get(LightPackageInfoLiveData.INSTANCE, this.packageName, this.user);
        this.groupLiveData = PermGroupLiveData.INSTANCE.get(this.permGroupName);
        addSource(this.packageInfoLiveData, new Observer<LightPackageInfo>() { // from class: com.android.permissioncontroller.permission.data.PermStateLiveData.1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(LightPackageInfo it) {
                PermStateLiveData.this.checkForUidUpdate(it);
                PermStateLiveData.this.currentPackageInfo = it;
                PermStateLiveData.this.updateAsync();
            }
        });
        addSource(this.groupLiveData, new Observer<PermGroup>() { // from class: com.android.permissioncontroller.permission.data.PermStateLiveData.2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(PermGroup it) {
                PermStateLiveData.this.updateAsync();
            }
        });
    }

    public /* synthetic */ PermStateLiveData(Application app, String packageName, String permGroupName, UserHandle user, DefaultConstructorMarker $constructor_marker) {
        this(app, packageName, permGroupName, user);
    }

    @Override // com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData
    public Object loadDataAndPostValue(Job job, Continuation<? super Unit> continuation) {
        if (!this.packageInfoLiveData.getIsInitialized() || !this.groupLiveData.getIsInitialized()) {
            return Unit.INSTANCE;
        }
        LightPackageInfo packageInfo = this.currentPackageInfo;
        PermGroup permissionGroup = this.groupLiveData.getValue();
        if (packageInfo == null || permissionGroup == null) {
            INSTANCE.invalidateSingle(new Triple(this.packageName, this.permGroupName, this.user));
            postValue(null);
            return Unit.INSTANCE;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int index = 0;
        for (String permissionName : packageInfo.getRequestedPermissions()) {
            LightPermInfo permInfo = permissionGroup.getPermissionInfos().get(permissionName);
            if (permInfo != null) {
                int packageFlags = packageInfo.getRequestedPermissionsFlags().get(index).intValue();
                int permFlags = this.context.getPackageManager().getPermissionFlags(permInfo.getName(), this.packageName, this.user);
                boolean granted = (packageFlags & 2) != 0 && (permFlags & 8) == 0;
                if (job.isCancelled()) {
                    return Unit.INSTANCE;
                }
                linkedHashMap.put(permissionName, new PermState(permFlags, granted));
            }
            index++;
        }
        postValue(linkedHashMap);
        return Unit.INSTANCE;
    }

    @Override // com.android.permissioncontroller.permission.data.PermissionListenerMultiplexer.PermissionChangeCallback
    public void onPermissionChange() {
        updateAsync();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkForUidUpdate(LightPackageInfo packageInfo) {
        if (packageInfo == null) {
            Integer num = this.registeredUid;
            if (num != null) {
                int it = num.intValue();
                PermissionListenerMultiplexer.INSTANCE.removeCallback(it, this);
                return;
            }
            return;
        }
        this.uid = Integer.valueOf(packageInfo.getUid());
        if ((!Intrinsics.areEqual(r0, this.registeredUid)) && hasActiveObservers()) {
            PermissionListenerMultiplexer.INSTANCE.addOrReplaceCallback(this.registeredUid, packageInfo.getUid(), this);
            this.registeredUid = this.uid;
        }
    }

    @Override // com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData, com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onInactive() {
        super.onInactive();
        Integer num = this.registeredUid;
        if (num != null) {
            int it = num.intValue();
            PermissionListenerMultiplexer.INSTANCE.removeCallback(it, this);
            this.registeredUid = (Integer) null;
        }
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onActive() {
        super.onActive();
        Integer num = this.uid;
        if (num != null) {
            int it = num.intValue();
            PermissionListenerMultiplexer.INSTANCE.addCallback(it, this);
            this.registeredUid = this.uid;
        }
        updateAsync();
    }

    /* JADX INFO: compiled from: PermStateLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002 \u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0004\u0012\u00020\u00050\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0006J\"\u0010\u0007\u001a\u00020\u00052\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002H\u0014¨\u0006\t"}, d2 = {"Lcom/android/permissioncontroller/permission/data/PermStateLiveData$Companion;", "Lcom/android/permissioncontroller/permission/data/DataRepositoryForPackage;", "Lkotlin/Triple;", "", "Landroid/os/UserHandle;", "Lcom/android/permissioncontroller/permission/data/PermStateLiveData;", "()V", "newValue", "key", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class Companion extends DataRepositoryForPackage<Triple<? extends String, ? extends String, ? extends UserHandle>, PermStateLiveData> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.permissioncontroller.permission.data.DataRepository
        public PermStateLiveData newValue(Triple<String, String, UserHandle> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
            Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
            return new PermStateLiveData(permissionControllerApplication, key.getFirst(), key.getSecond(), key.getThird(), null);
        }
    }
}
