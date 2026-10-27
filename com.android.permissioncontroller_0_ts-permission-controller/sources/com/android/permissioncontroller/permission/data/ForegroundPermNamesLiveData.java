package com.android.permissioncontroller.permission.data;

import androidx.lifecycle.Observer;
import com.android.permissioncontroller.permission.data.PermGroupLiveData;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPermInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.PermGroup;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: ForegroundPermNamesLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00040\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0014¨\u0006\b"}, d2 = {"Lcom/android/permissioncontroller/permission/data/ForegroundPermNamesLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "", "", "", "()V", "onUpdate", "", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class ForegroundPermNamesLiveData extends SmartUpdateMediatorLiveData<Map<String, ? extends List<? extends String>>> {
    public static final ForegroundPermNamesLiveData INSTANCE;

    static {
        ForegroundPermNamesLiveData foregroundPermNamesLiveData = new ForegroundPermNamesLiveData();
        INSTANCE = foregroundPermNamesLiveData;
        foregroundPermNamesLiveData.onUpdate();
    }

    private ForegroundPermNamesLiveData() {
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
    protected void onUpdate() {
        Iterable systemGroups = Utils.getPlatformPermissionGroups();
        Intrinsics.checkExpressionValueIsNotNull(systemGroups, "systemGroups");
        Iterable $this$map$iv = systemGroups;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            PermGroupLiveData.Companion companion = PermGroupLiveData.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            destination$iv$iv.add(companion.get(it));
        }
        final List<PermGroupLiveData> groupLiveDatas = (List) destination$iv$iv;
        final Map permMap = new LinkedHashMap();
        final Ref.IntRef numLiveDatasSeen = new Ref.IntRef();
        numLiveDatasSeen.element = 0;
        for (final PermGroupLiveData groupLiveData : groupLiveDatas) {
            addSource(groupLiveData, new Observer<PermGroup>() { // from class: com.android.permissioncontroller.permission.data.ForegroundPermNamesLiveData.onUpdate.1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(PermGroup permGroup) {
                    ArrayList answer$iv;
                    if (permGroup == null) {
                        if (groupLiveData.getIsInitialized()) {
                            numLiveDatasSeen.element++;
                            return;
                        }
                        return;
                    }
                    for (LightPermInfo permInfo : permGroup.getPermissionInfos().values()) {
                        String backgroundPerm = permInfo.getBackgroundPermission();
                        if (backgroundPerm != null) {
                            Map $this$getOrPut$iv = permMap;
                            Object value$iv = $this$getOrPut$iv.get(backgroundPerm);
                            if (value$iv == null) {
                                answer$iv = new ArrayList();
                                $this$getOrPut$iv.put(backgroundPerm, answer$iv);
                            } else {
                                answer$iv = value$iv;
                            }
                            List foregroundPerms = (List) answer$iv;
                            foregroundPerms.add(permInfo.getName());
                        }
                    }
                    numLiveDatasSeen.element++;
                    if (numLiveDatasSeen.element == groupLiveDatas.size()) {
                        ForegroundPermNamesLiveData.INSTANCE.setValue(permMap);
                    }
                }
            });
        }
    }
}
