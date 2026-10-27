package com.android.permissioncontroller.permission.data;

import android.app.AppOpsManager;
import android.app.Application;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.permission.data.DataRepository;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppOpLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0010B'\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0002\u0010\tJ\b\u0010\u000e\u001a\u00020\u000fH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/android/permissioncontroller/permission/data/AppOpLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "", "app", "Landroid/app/Application;", "packageName", "", "op", "uid", "(Landroid/app/Application;Ljava/lang/String;Ljava/lang/String;I)V", "appOpsManager", "Landroid/app/AppOpsManager;", "getAppOpsManager", "()Landroid/app/AppOpsManager;", "onUpdate", "", "Companion", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class AppOpLiveData extends SmartUpdateMediatorLiveData<Integer> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Application app;
    private final AppOpsManager appOpsManager;
    private final String op;
    private final String packageName;
    private final int uid;

    private AppOpLiveData(Application app, String packageName, String op, int uid) {
        this.app = app;
        this.packageName = packageName;
        this.op = op;
        this.uid = uid;
        Object systemService = app.getSystemService(AppOpsManager.class);
        if (systemService == null) {
            Intrinsics.throwNpe();
        }
        this.appOpsManager = (AppOpsManager) systemService;
    }

    public /* synthetic */ AppOpLiveData(Application app, String packageName, String op, int uid, DefaultConstructorMarker $constructor_marker) {
        this(app, packageName, op, uid);
    }

    public final AppOpsManager getAppOpsManager() {
        return this.appOpsManager;
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
    protected void onUpdate() {
        setValue(Integer.valueOf(this.appOpsManager.unsafeCheckOpNoThrow(this.op, this.uid, this.packageName)));
    }

    /* JADX INFO: compiled from: AppOpLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002 \u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0004\u0012\u00020\u00050\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0006J\"\u0010\u0007\u001a\u00020\u00052\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002H\u0014¨\u0006\t"}, d2 = {"Lcom/android/permissioncontroller/permission/data/AppOpLiveData$Companion;", "Lcom/android/permissioncontroller/permission/data/DataRepository;", "Lkotlin/Triple;", "", "", "Lcom/android/permissioncontroller/permission/data/AppOpLiveData;", "()V", "newValue", "key", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class Companion extends DataRepository<Triple<? extends String, ? extends String, ? extends Integer>, AppOpLiveData> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @Override // com.android.permissioncontroller.permission.data.DataRepository
        public /* bridge */ /* synthetic */ DataRepository.InactiveTimekeeper newValue(Triple<? extends String, ? extends String, ? extends Integer> triple) {
            return newValue2((Triple<String, String, Integer>) triple);
        }

        /* JADX INFO: renamed from: newValue, reason: avoid collision after fix types in other method */
        protected AppOpLiveData newValue2(Triple<String, String, Integer> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
            Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
            return new AppOpLiveData(permissionControllerApplication, key.getFirst(), key.getSecond(), key.getThird().intValue(), null);
        }
    }
}
