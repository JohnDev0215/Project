package com.android.permissioncontroller.permission.ui.model;

import android.os.UserHandle;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.permission.data.PermGroupsPackagesLiveData;
import com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;

/* JADX INFO: compiled from: ManageStandardPermissionsViewModel.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/NumCustomPermGroupsWithPackagesLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "", "()V", "customPermGroupPackages", "Lcom/android/permissioncontroller/permission/data/PermGroupsPackagesLiveData;", "onUpdate", "", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class NumCustomPermGroupsWithPackagesLiveData extends SmartUpdateMediatorLiveData<Integer> {
    private final PermGroupsPackagesLiveData customPermGroupPackages;

    public NumCustomPermGroupsWithPackagesLiveData() {
        PermGroupsPackagesLiveData permGroupsPackagesLiveData = PermGroupsPackagesLiveData.INSTANCE.get(true);
        this.customPermGroupPackages = permGroupsPackagesLiveData;
        addSource(permGroupsPackagesLiveData, new Observer<Map<String, ? extends Set<? extends Pair<? extends String, ? extends UserHandle>>>>() { // from class: com.android.permissioncontroller.permission.ui.model.NumCustomPermGroupsWithPackagesLiveData.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Map<String, ? extends Set<? extends Pair<? extends String, ? extends UserHandle>>> map) {
                onChanged2((Map<String, ? extends Set<Pair<String, UserHandle>>>) map);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(Map<String, ? extends Set<Pair<String, UserHandle>>> map) {
                NumCustomPermGroupsWithPackagesLiveData.this.updateIfActive();
            }
        });
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
    protected void onUpdate() {
        Map<String, ? extends Set<? extends Pair<? extends String, ? extends UserHandle>>> value = this.customPermGroupPackages.getValue();
        setValue(Integer.valueOf(value != null ? value.size() : 0));
    }
}
