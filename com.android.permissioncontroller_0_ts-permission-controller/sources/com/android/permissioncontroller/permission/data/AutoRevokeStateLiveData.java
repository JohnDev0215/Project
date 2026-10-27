package com.android.permissioncontroller.permission.data;

import android.app.AppOpsManager;
import android.app.Application;
import android.os.UserHandle;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.permission.model.livedatatypes.AutoRevokeState;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import com.android.permissioncontroller.permission.service.ExemptServicesLiveData;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoRevokeStateLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u001f2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u001fB\u001f\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0019\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\b\u0010\u001b\u001a\u00020\u0017H\u0014J\b\u0010\u001c\u001a\u00020\u0017H\u0014J\u001c\u0010\u001d\u001a\u00020\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"Lcom/android/permissioncontroller/permission/data/AutoRevokeStateLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartAsyncMediatorLiveData;", "Lcom/android/permissioncontroller/permission/model/livedatatypes/AutoRevokeState;", "Landroid/app/AppOpsManager$OnOpChangedListener;", "app", "Landroid/app/Application;", "packageName", "", "user", "Landroid/os/UserHandle;", "(Landroid/app/Application;Ljava/lang/String;Landroid/os/UserHandle;)V", "appOpsManager", "Landroid/app/AppOpsManager;", "exemptServicesLiveData", "Lcom/android/permissioncontroller/permission/service/ExemptServicesLiveData;", "packageLiveData", "Lcom/android/permissioncontroller/permission/data/LightPackageInfoLiveData;", "packagePermsLiveData", "Lcom/android/permissioncontroller/permission/data/PackagePermissionsLiveData;", "permStateLiveDatas", "", "Lcom/android/permissioncontroller/permission/data/PermStateLiveData;", "loadDataAndPostValue", "", "job", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onActive", "onInactive", "onOpChanged", "op", "Companion", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class AutoRevokeStateLiveData extends SmartAsyncMediatorLiveData<AutoRevokeState> implements AppOpsManager.OnOpChangedListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Application app;
    private final AppOpsManager appOpsManager;
    private final ExemptServicesLiveData exemptServicesLiveData;
    private final LightPackageInfoLiveData packageLiveData;
    private final String packageName;
    private final PackagePermissionsLiveData packagePermsLiveData;
    private final Map<String, PermStateLiveData> permStateLiveDatas;
    private final UserHandle user;

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.data.AutoRevokeStateLiveData$loadDataAndPostValue$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AutoRevokeStateLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0096@"}, d2 = {"loadDataAndPostValue", "", "job", "Lkotlinx/coroutines/Job;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.data.AutoRevokeStateLiveData", f = "AutoRevokeStateLiveData.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, l = {86, 88}, m = "loadDataAndPostValue", n = {"this", "job", "groups", "getLiveData", "this", "job", "groups", "getLiveData", "revocable", "revocableGroups"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "Z$0", "L$4"})
    static final class C00321 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C00321(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AutoRevokeStateLiveData.this.loadDataAndPostValue(null, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private AutoRevokeStateLiveData(Application app, String packageName, UserHandle user) {
        this.app = app;
        this.packageName = packageName;
        this.user = user;
        this.packagePermsLiveData = (PackagePermissionsLiveData) DataRepositoryKt.get(PackagePermissionsLiveData.INSTANCE, this.packageName, this.user);
        this.packageLiveData = (LightPackageInfoLiveData) DataRepositoryKt.get(LightPackageInfoLiveData.INSTANCE, this.packageName, this.user);
        this.permStateLiveDatas = new LinkedHashMap();
        this.exemptServicesLiveData = (ExemptServicesLiveData) ExemptServicesLiveData.INSTANCE.get(this.user);
        Object systemService = this.app.getSystemService(AppOpsManager.class);
        if (systemService == null) {
            Intrinsics.throwNpe();
        }
        this.appOpsManager = (AppOpsManager) systemService;
        addSource(this.packagePermsLiveData, new Observer<Map<String, ? extends List<? extends String>>>() { // from class: com.android.permissioncontroller.permission.data.AutoRevokeStateLiveData.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Map<String, ? extends List<? extends String>> map) {
                onChanged2((Map<String, ? extends List<String>>) map);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(Map<String, ? extends List<String>> map) {
                AutoRevokeStateLiveData.this.updateIfActive();
            }
        });
        addSource(this.packageLiveData, new Observer<LightPackageInfo>() { // from class: com.android.permissioncontroller.permission.data.AutoRevokeStateLiveData.2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(LightPackageInfo it) {
                AutoRevokeStateLiveData.this.updateIfActive();
            }
        });
        addSource(this.exemptServicesLiveData, new Observer<Map<String, ? extends List<? extends String>>>() { // from class: com.android.permissioncontroller.permission.data.AutoRevokeStateLiveData.3
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Map<String, ? extends List<? extends String>> map) {
                onChanged2((Map<String, ? extends List<String>>) map);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(Map<String, ? extends List<String>> map) {
                AutoRevokeStateLiveData.this.updateIfActive();
            }
        });
    }

    public /* synthetic */ AutoRevokeStateLiveData(Application app, String packageName, UserHandle user, DefaultConstructorMarker $constructor_marker) {
        this(app, packageName, user);
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0252  */
    @Override // com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object loadDataAndPostValue(kotlinx.coroutines.Job r27, kotlin.coroutines.Continuation<? super kotlin.Unit> r28) {
        /*
            Method dump skipped, instruction units count: 641
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.permission.data.AutoRevokeStateLiveData.loadDataAndPostValue(kotlinx.coroutines.Job, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // android.app.AppOpsManager.OnOpChangedListener
    public void onOpChanged(String op, String packageName) {
        if (Intrinsics.areEqual(op, "android:auto_revoke_permissions_if_unused") && Intrinsics.areEqual(packageName, packageName)) {
            updateIfActive();
        }
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onActive() {
        super.onActive();
        this.appOpsManager.startWatchingMode("android:auto_revoke_permissions_if_unused", this.packageName, this);
        updateIfActive();
    }

    @Override // com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData, com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onInactive() {
        super.onInactive();
        this.appOpsManager.stopWatchingMode(this);
    }

    /* JADX INFO: compiled from: AutoRevokeStateLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0004\u0012\u00020\u00050\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\u00020\u00052\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002H\u0014¨\u0006\t"}, d2 = {"Lcom/android/permissioncontroller/permission/data/AutoRevokeStateLiveData$Companion;", "Lcom/android/permissioncontroller/permission/data/DataRepositoryForPackage;", "Lkotlin/Pair;", "", "Landroid/os/UserHandle;", "Lcom/android/permissioncontroller/permission/data/AutoRevokeStateLiveData;", "()V", "newValue", "key", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class Companion extends DataRepositoryForPackage<Pair<? extends String, ? extends UserHandle>, AutoRevokeStateLiveData> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.permissioncontroller.permission.data.DataRepository
        public AutoRevokeStateLiveData newValue(Pair<String, UserHandle> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
            Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
            return new AutoRevokeStateLiveData(permissionControllerApplication, key.getFirst(), key.getSecond(), null);
        }
    }
}
