package com.android.permissioncontroller.permission.ui.model;

import android.os.UserHandle;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.permission.data.PermGroupsPackagesLiveData;
import com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: ManageCustomPermissionsViewModel.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/model/UsedCustomPermGroupNamesLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "", "", "()V", "onUpdate", "", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class UsedCustomPermGroupNamesLiveData extends SmartUpdateMediatorLiveData<List<? extends String>> {
    public UsedCustomPermGroupNamesLiveData() {
        addSource(PermGroupsPackagesLiveData.INSTANCE.get(true), new Observer<Map<String, ? extends Set<? extends Pair<? extends String, ? extends UserHandle>>>>() { // from class: com.android.permissioncontroller.permission.ui.model.UsedCustomPermGroupNamesLiveData.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Map<String, ? extends Set<? extends Pair<? extends String, ? extends UserHandle>>> map) {
                onChanged2((Map<String, ? extends Set<Pair<String, UserHandle>>>) map);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(Map<String, ? extends Set<Pair<String, UserHandle>>> map) {
                UsedCustomPermGroupNamesLiveData.this.setValue(CollectionsKt.toList(map.keySet()));
            }
        });
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
    protected void onUpdate() {
    }
}
