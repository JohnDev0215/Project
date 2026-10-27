package com.android.permissioncontroller.permission.data;

import android.app.AppOpsManager;
import android.app.Application;
import android.os.UserHandle;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: FullStoragePermissionAppsLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001:\u0001\u0010B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\nH\u0014J\u0006\u0010\u000f\u001a\u00020\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lcom/android/permissioncontroller/permission/data/FullStoragePermissionAppsLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartAsyncMediatorLiveData;", "", "Lcom/android/permissioncontroller/permission/data/FullStoragePermissionAppsLiveData$FullStoragePackageState;", "()V", "app", "Landroid/app/Application;", "standardPermGroupsPackagesLiveData", "Lcom/android/permissioncontroller/permission/data/PermGroupsPackagesLiveData;", "loadDataAndPostValue", "", "job", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onActive", "recalculate", "FullStoragePackageState", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class FullStoragePermissionAppsLiveData extends SmartAsyncMediatorLiveData<List<? extends FullStoragePackageState>> {
    public static final FullStoragePermissionAppsLiveData INSTANCE;
    private static final Application app;
    private static final PermGroupsPackagesLiveData standardPermGroupsPackagesLiveData;

    static {
        FullStoragePermissionAppsLiveData fullStoragePermissionAppsLiveData = new FullStoragePermissionAppsLiveData();
        INSTANCE = fullStoragePermissionAppsLiveData;
        PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
        Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
        app = permissionControllerApplication;
        PermGroupsPackagesLiveData permGroupsPackagesLiveData = PermGroupsPackagesLiveData.INSTANCE.get(false);
        standardPermGroupsPackagesLiveData = permGroupsPackagesLiveData;
        fullStoragePermissionAppsLiveData.addSource(permGroupsPackagesLiveData, new Observer<Map<String, ? extends Set<? extends Pair<? extends String, ? extends UserHandle>>>>() { // from class: com.android.permissioncontroller.permission.data.FullStoragePermissionAppsLiveData.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Map<String, ? extends Set<? extends Pair<? extends String, ? extends UserHandle>>> map) {
                onChanged2((Map<String, ? extends Set<Pair<String, UserHandle>>>) map);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(Map<String, ? extends Set<Pair<String, UserHandle>>> map) {
                FullStoragePermissionAppsLiveData.INSTANCE.updateAsync();
            }
        });
        fullStoragePermissionAppsLiveData.addSource(AllPackageInfosLiveData.INSTANCE, new Observer<Map<UserHandle, ? extends List<? extends LightPackageInfo>>>() { // from class: com.android.permissioncontroller.permission.data.FullStoragePermissionAppsLiveData.2
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Map<UserHandle, ? extends List<? extends LightPackageInfo>> map) {
                onChanged2((Map<UserHandle, ? extends List<LightPackageInfo>>) map);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(Map<UserHandle, ? extends List<LightPackageInfo>> map) {
                FullStoragePermissionAppsLiveData.INSTANCE.updateAsync();
            }
        });
    }

    private FullStoragePermissionAppsLiveData() {
    }

    /* JADX INFO: compiled from: FullStoragePermissionAppsLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/android/permissioncontroller/permission/data/FullStoragePermissionAppsLiveData$FullStoragePackageState;", "", "packageName", "", "user", "Landroid/os/UserHandle;", "isLegacy", "", "isGranted", "(Ljava/lang/String;Landroid/os/UserHandle;ZZ)V", "()Z", "getPackageName", "()Ljava/lang/String;", "getUser", "()Landroid/os/UserHandle;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class FullStoragePackageState {
        private final boolean isGranted;
        private final boolean isLegacy;
        private final String packageName;
        private final UserHandle user;

        public static /* synthetic */ FullStoragePackageState copy$default(FullStoragePackageState fullStoragePackageState, String str, UserHandle userHandle, boolean z, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = fullStoragePackageState.packageName;
            }
            if ((i & 2) != 0) {
                userHandle = fullStoragePackageState.user;
            }
            if ((i & 4) != 0) {
                z = fullStoragePackageState.isLegacy;
            }
            if ((i & 8) != 0) {
                z2 = fullStoragePackageState.isGranted;
            }
            return fullStoragePackageState.copy(str, userHandle, z, z2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getPackageName() {
            return this.packageName;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final UserHandle getUser() {
            return this.user;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsLegacy() {
            return this.isLegacy;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsGranted() {
            return this.isGranted;
        }

        public final FullStoragePackageState copy(String packageName, UserHandle user, boolean isLegacy, boolean isGranted) {
            Intrinsics.checkParameterIsNotNull(packageName, "packageName");
            Intrinsics.checkParameterIsNotNull(user, "user");
            return new FullStoragePackageState(packageName, user, isLegacy, isGranted);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FullStoragePackageState)) {
                return false;
            }
            FullStoragePackageState fullStoragePackageState = (FullStoragePackageState) other;
            return Intrinsics.areEqual(this.packageName, fullStoragePackageState.packageName) && Intrinsics.areEqual(this.user, fullStoragePackageState.user) && this.isLegacy == fullStoragePackageState.isLegacy && this.isGranted == fullStoragePackageState.isGranted;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v3, types: [int] */
        /* JADX WARN: Type inference failed for: r1v5 */
        /* JADX WARN: Type inference failed for: r1v7 */
        /* JADX WARN: Type inference failed for: r2v1 */
        /* JADX WARN: Type inference failed for: r2v2, types: [int] */
        /* JADX WARN: Type inference failed for: r2v3 */
        public int hashCode() {
            String str = this.packageName;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            UserHandle userHandle = this.user;
            int iHashCode2 = (iHashCode + (userHandle != null ? userHandle.hashCode() : 0)) * 31;
            boolean z = this.isLegacy;
            ?? r1 = z;
            if (z) {
                r1 = 1;
            }
            int i = (iHashCode2 + r1) * 31;
            boolean z2 = this.isGranted;
            return i + (z2 ? 1 : z2);
        }

        public String toString() {
            return "FullStoragePackageState(packageName=" + this.packageName + ", user=" + this.user + ", isLegacy=" + this.isLegacy + ", isGranted=" + this.isGranted + ")";
        }

        public FullStoragePackageState(String packageName, UserHandle user, boolean isLegacy, boolean isGranted) {
            Intrinsics.checkParameterIsNotNull(packageName, "packageName");
            Intrinsics.checkParameterIsNotNull(user, "user");
            this.packageName = packageName;
            this.user = user;
            this.isLegacy = isLegacy;
            this.isGranted = isGranted;
        }

        public final String getPackageName() {
            return this.packageName;
        }

        public final UserHandle getUser() {
            return this.user;
        }

        public final boolean isLegacy() {
            return this.isLegacy;
        }

        public final boolean isGranted() {
            return this.isGranted;
        }
    }

    @Override // com.android.permissioncontroller.permission.data.SmartAsyncMediatorLiveData
    public Object loadDataAndPostValue(Job job, Continuation<? super Unit> continuation) {
        boolean z;
        Map<String, ? extends Set<? extends Pair<? extends String, ? extends UserHandle>>> value = standardPermGroupsPackagesLiveData.getValue();
        Object obj = value;
        if (value != null) {
            Set<? extends Pair<? extends String, ? extends UserHandle>> set = value.get("android.permission-group.STORAGE");
            obj = set;
            if (set != null) {
                AppOpsManager appOpsManager = (AppOpsManager) app.getSystemService(AppOpsManager.class);
                if (appOpsManager == null) {
                    return appOpsManager == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? appOpsManager : Unit.INSTANCE;
                }
                ArrayList arrayList = new ArrayList();
                Map<UserHandle, ? extends List<? extends LightPackageInfo>> value2 = AllPackageInfosLiveData.INSTANCE.getValue();
                if (value2 == null) {
                    value2 = MapsKt.emptyMap();
                }
                Intrinsics.checkExpressionValueIsNotNull(value2, "AllPackageInfosLiveData.value ?: emptyMap()");
                for (Map.Entry<UserHandle, ? extends List<? extends LightPackageInfo>> entry : value2.entrySet()) {
                    UserHandle key = entry.getKey();
                    List<? extends LightPackageInfo> value3 = entry.getValue();
                    ArrayList<LightPackageInfo> arrayList2 = new ArrayList();
                    Iterator<T> it = value3.iterator();
                    while (true) {
                        z = true;
                        if (!it.hasNext()) {
                            break;
                        }
                        Object next = it.next();
                        LightPackageInfo lightPackageInfo = (LightPackageInfo) next;
                        if (Boxing.boxBoolean(set.contains(TuplesKt.to(lightPackageInfo.getPackageName(), key)) || lightPackageInfo.getRequestedPermissions().contains("android.permission.MANAGE_EXTERNAL_STORAGE")).booleanValue()) {
                            arrayList2.add(next);
                        }
                    }
                    for (LightPackageInfo lightPackageInfo2 : arrayList2) {
                        int targetSdkVersion = lightPackageInfo2.getTargetSdkVersion();
                        if (targetSdkVersion < 28) {
                            arrayList.add(new FullStoragePackageState(lightPackageInfo2.getPackageName(), key, z, z));
                        } else if (targetSdkVersion <= 29 && appOpsManager.unsafeCheckOpNoThrow("android:legacy_storage", lightPackageInfo2.getUid(), lightPackageInfo2.getPackageName()) == 0) {
                            arrayList.add(new FullStoragePackageState(lightPackageInfo2.getPackageName(), key, z, z));
                        } else if (lightPackageInfo2.getRequestedPermissions().contains("android.permission.MANAGE_EXTERNAL_STORAGE")) {
                            int iUnsafeCheckOpNoThrow = appOpsManager.unsafeCheckOpNoThrow("android:manage_external_storage", lightPackageInfo2.getUid(), lightPackageInfo2.getPackageName());
                            arrayList.add(new FullStoragePackageState(lightPackageInfo2.getPackageName(), key, false, (iUnsafeCheckOpNoThrow == 0 || iUnsafeCheckOpNoThrow == 4 || (iUnsafeCheckOpNoThrow == 3 && lightPackageInfo2.getGrantedPermissions().contains("android.permission.MANAGE_EXTERNAL_STORAGE"))) ? z : false));
                        }
                        z = true;
                    }
                }
                postValue(arrayList);
                return Unit.INSTANCE;
            }
        }
        return obj == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? obj : Unit.INSTANCE;
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onActive() {
        super.onActive();
        updateAsync();
    }

    public final void recalculate() {
        updateAsync();
    }
}
