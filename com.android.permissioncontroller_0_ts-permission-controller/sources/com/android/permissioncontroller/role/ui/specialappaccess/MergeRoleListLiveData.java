package com.android.permissioncontroller.role.ui.specialappaccess;

import android.util.ArrayMap;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.role.ui.RoleItem;
import com.android.permissioncontroller.role.ui.RoleListLiveData;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class MergeRoleListLiveData extends MediatorLiveData<List<RoleItem>> {
    private final RoleListLiveData[] mLiveDatas;

    public MergeRoleListLiveData(RoleListLiveData... liveDatas) {
        this.mLiveDatas = liveDatas;
        int liveDatasLength = liveDatas.length;
        for (int i = 0; i < liveDatasLength; i++) {
            RoleListLiveData liveData = this.mLiveDatas[i];
            addSource(liveData, new Observer() { // from class: com.android.permissioncontroller.role.ui.specialappaccess.-$$Lambda$MergeRoleListLiveData$8_PLPxhsDUB4gXbQo0omyUMMYEU
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    this.f$0.lambda$new$0$MergeRoleListLiveData((List) obj);
                }
            });
        }
    }

    public /* synthetic */ void lambda$new$0$MergeRoleListLiveData(List roleItems) {
        onRoleListChanged();
    }

    private void onRoleListChanged() {
        ArrayMap<String, RoleItem> mergedRoleItemMap = new ArrayMap<>();
        int liveDatasLength = this.mLiveDatas.length;
        for (int liveDatasIndex = 0; liveDatasIndex < liveDatasLength; liveDatasIndex++) {
            RoleListLiveData liveData = this.mLiveDatas[liveDatasIndex];
            List<RoleItem> roleItems = liveData.getValue();
            if (roleItems == null) {
                return;
            }
            int roleItemsSize = roleItems.size();
            for (int roleItemsIndex = 0; roleItemsIndex < roleItemsSize; roleItemsIndex++) {
                RoleItem roleItem = roleItems.get(roleItemsIndex);
                String roleName = roleItem.getRole().getName();
                RoleItem mergedRoleItem = mergedRoleItemMap.get(roleName);
                if (mergedRoleItem == null) {
                    mergedRoleItemMap.put(roleName, new RoleItem(roleItem.getRole(), new ArrayList(roleItem.getHolderApplicationInfos())));
                } else {
                    mergedRoleItem.getHolderApplicationInfos().addAll(roleItem.getHolderApplicationInfos());
                }
            }
        }
        setValue(new ArrayList(mergedRoleItemMap.values()));
    }
}
