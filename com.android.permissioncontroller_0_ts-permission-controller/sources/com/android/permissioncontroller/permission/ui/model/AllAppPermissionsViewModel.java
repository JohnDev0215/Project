package com.android.permissioncontroller.permission.ui.model;

import android.os.UserHandle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.android.permissioncontroller.permission.data.DataRepositoryKt;
import com.android.permissioncontroller.permission.data.PackagePermissionsLiveData;
import com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AllAppPermissionsViewModel.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\fB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/AllAppPermissionsViewModel;", "Landroidx/lifecycle/ViewModel;", "packageName", "", "user", "Landroid/os/UserHandle;", "filterGroup", "(Ljava/lang/String;Landroid/os/UserHandle;Ljava/lang/String;)V", "allPackagePermissionsLiveData", "Lcom/android/permissioncontroller/permission/ui/model/AllAppPermissionsViewModel$AllPackagePermissionsLiveData;", "getAllPackagePermissionsLiveData", "()Lcom/android/permissioncontroller/permission/ui/model/AllAppPermissionsViewModel$AllPackagePermissionsLiveData;", "AllPackagePermissionsLiveData", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class AllAppPermissionsViewModel extends ViewModel {
    private final AllPackagePermissionsLiveData allPackagePermissionsLiveData;

    public AllAppPermissionsViewModel(String packageName, UserHandle user, String filterGroup) {
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(user, "user");
        this.allPackagePermissionsLiveData = new AllPackagePermissionsLiveData(packageName, user, filterGroup);
    }

    public final AllPackagePermissionsLiveData getAllPackagePermissionsLiveData() {
        return this.allPackagePermissionsLiveData;
    }

    /* JADX INFO: compiled from: AllAppPermissionsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u001f\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00040\u0002¢\u0006\u0002\b\u00050\u0001B\u001f\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nJ\b\u0010\r\u001a\u00020\u000eH\u0014R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/AllAppPermissionsViewModel$AllPackagePermissionsLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "", "", "", "Lkotlin/jvm/JvmSuppressWildcards;", "packageName", "user", "Landroid/os/UserHandle;", "filterGroup", "(Ljava/lang/String;Landroid/os/UserHandle;Ljava/lang/String;)V", "packagePermsLiveData", "Lcom/android/permissioncontroller/permission/data/PackagePermissionsLiveData;", "onUpdate", "", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class AllPackagePermissionsLiveData extends SmartUpdateMediatorLiveData<Map<String, List<String>>> {
        private final String filterGroup;
        private final PackagePermissionsLiveData packagePermsLiveData;

        public AllPackagePermissionsLiveData(String packageName, UserHandle user, String filterGroup) {
            Intrinsics.checkParameterIsNotNull(packageName, "packageName");
            Intrinsics.checkParameterIsNotNull(user, "user");
            this.filterGroup = filterGroup;
            PackagePermissionsLiveData packagePermissionsLiveData = (PackagePermissionsLiveData) DataRepositoryKt.get(PackagePermissionsLiveData.INSTANCE, packageName, user);
            this.packagePermsLiveData = packagePermissionsLiveData;
            addSource(packagePermissionsLiveData, new Observer<Map<String, ? extends List<? extends String>>>() { // from class: com.android.permissioncontroller.permission.ui.model.AllAppPermissionsViewModel.AllPackagePermissionsLiveData.1
                @Override // androidx.lifecycle.Observer
                public /* bridge */ /* synthetic */ void onChanged(Map<String, ? extends List<? extends String>> map) {
                    onChanged2((Map<String, ? extends List<String>>) map);
                }

                /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
                public final void onChanged2(Map<String, ? extends List<String>> map) {
                    AllPackagePermissionsLiveData.this.updateIfActive();
                }
            });
            updateIfActive();
        }

        @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
        protected void onUpdate() {
            Map<String, ? extends List<? extends String>> value = this.packagePermsLiveData.getValue();
            if (value == null) {
                setValue(null);
                return;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry<String, ? extends List<? extends String>> entry : value.entrySet()) {
                if (this.filterGroup == null || Intrinsics.areEqual(entry.getKey(), this.filterGroup)) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            }
            setValue(linkedHashMap);
        }
    }
}
