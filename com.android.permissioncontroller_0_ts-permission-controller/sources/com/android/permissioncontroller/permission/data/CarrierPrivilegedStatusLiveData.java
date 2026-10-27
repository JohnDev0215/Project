package com.android.permissioncontroller.permission.data;

import android.app.Application;
import android.telephony.TelephonyManager;
import com.android.permissioncontroller.PermissionControllerApplication;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarrierPrivilegedStatusLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000eB\u0017\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Lcom/android/permissioncontroller/permission/data/CarrierPrivilegedStatusLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "", "app", "Landroid/app/Application;", "packageName", "", "(Landroid/app/Application;Ljava/lang/String;)V", "telephonyManager", "Landroid/telephony/TelephonyManager;", "getTelephonyManager", "()Landroid/telephony/TelephonyManager;", "onUpdate", "", "Companion", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class CarrierPrivilegedStatusLiveData extends SmartUpdateMediatorLiveData<Integer> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Application app;
    private final String packageName;
    private final TelephonyManager telephonyManager;

    private CarrierPrivilegedStatusLiveData(Application app, String packageName) {
        this.app = app;
        this.packageName = packageName;
        Object systemService = app.getSystemService(TelephonyManager.class);
        if (systemService == null) {
            Intrinsics.throwNpe();
        }
        this.telephonyManager = (TelephonyManager) systemService;
    }

    public /* synthetic */ CarrierPrivilegedStatusLiveData(Application app, String packageName, DefaultConstructorMarker $constructor_marker) {
        this(app, packageName);
    }

    public final TelephonyManager getTelephonyManager() {
        return this.telephonyManager;
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
    protected void onUpdate() {
        setValue(Integer.valueOf(this.telephonyManager.checkCarrierPrivilegesForPackageAnyPhone(this.packageName)));
    }

    /* JADX INFO: compiled from: CarrierPrivilegedStatusLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0002H\u0014¨\u0006\u0007"}, d2 = {"Lcom/android/permissioncontroller/permission/data/CarrierPrivilegedStatusLiveData$Companion;", "Lcom/android/permissioncontroller/permission/data/DataRepository;", "", "Lcom/android/permissioncontroller/permission/data/CarrierPrivilegedStatusLiveData;", "()V", "newValue", "key", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class Companion extends DataRepository<String, CarrierPrivilegedStatusLiveData> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.permissioncontroller.permission.data.DataRepository
        public CarrierPrivilegedStatusLiveData newValue(String key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
            Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
            return new CarrierPrivilegedStatusLiveData(permissionControllerApplication, key, null);
        }
    }
}
