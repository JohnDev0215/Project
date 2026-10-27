package com.android.permissioncontroller.permission.data;

import android.app.Application;
import android.os.UserHandle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.permission.model.livedatatypes.AppPermGroupUiInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.PermGroupPackagesUiInfo;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PermGroupsPackagesUiInfoLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u001b\u0012\u0017\u0012\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0002¢\u0006\u0002\b\u00050\u0001B!\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\n0\t¢\u0006\u0002\u0010\u000bJ(\u0010\u0011\u001a\u00020\u00122\u001e\u0010\u0013\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0004\u0012\u00020\u00160\u0002H\u0002J(\u0010\u0017\u001a\u00020\u00122\u001e\u0010\u0013\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0004\u0012\u00020\u00160\u0002H\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0019H\u0014R\u001c\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\nX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00100\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/android/permissioncontroller/permission/data/PermGroupsPackagesUiInfoLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "", "", "Lcom/android/permissioncontroller/permission/model/livedatatypes/PermGroupPackagesUiInfo;", "Lkotlin/jvm/JvmSuppressWildcards;", "app", "Landroid/app/Application;", "groupNamesLiveData", "Landroidx/lifecycle/LiveData;", "", "(Landroid/app/Application;Landroidx/lifecycle/LiveData;)V", "allPackageData", "", "groupNames", "permGroupPackagesLiveDatas", "Lcom/android/permissioncontroller/permission/data/SinglePermGroupPackagesUiInfoLiveData;", "getNonSystemGranted", "", "uiInfo", "Lkotlin/Pair;", "Landroid/os/UserHandle;", "Lcom/android/permissioncontroller/permission/model/livedatatypes/AppPermGroupUiInfo;", "getNonSystemTotal", "getPermGroupPackageLiveDatas", "", "onUpdate", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class PermGroupsPackagesUiInfoLiveData extends SmartUpdateMediatorLiveData<Map<String, PermGroupPackagesUiInfo>> {
    private final Map<String, PermGroupPackagesUiInfo> allPackageData;
    private final Application app;
    private List<String> groupNames;
    private final Map<String, SinglePermGroupPackagesUiInfoLiveData> permGroupPackagesLiveDatas;

    public PermGroupsPackagesUiInfoLiveData(Application app, LiveData<List<String>> groupNamesLiveData) {
        Intrinsics.checkParameterIsNotNull(app, "app");
        Intrinsics.checkParameterIsNotNull(groupNamesLiveData, "groupNamesLiveData");
        this.app = app;
        this.permGroupPackagesLiveDatas = new LinkedHashMap();
        this.allPackageData = new LinkedHashMap();
        addSource(groupNamesLiveData, new Observer<List<? extends String>>() { // from class: com.android.permissioncontroller.permission.data.PermGroupsPackagesUiInfoLiveData.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends String> list) {
                onChanged2((List<String>) list);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<String> list) {
                PermGroupsPackagesUiInfoLiveData.this.groupNames = list != null ? list : CollectionsKt.emptyList();
                PermGroupsPackagesUiInfoLiveData.this.updateIfActive();
                PermGroupsPackagesUiInfoLiveData.this.getPermGroupPackageLiveDatas();
            }
        });
    }

    public static final /* synthetic */ List access$getGroupNames$p(PermGroupsPackagesUiInfoLiveData $this) {
        List<String> list = $this.groupNames;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("groupNames");
        }
        return list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getPermGroupPackageLiveDatas() {
        Function1 getLiveData = new Function1<String, SinglePermGroupPackagesUiInfoLiveData>() { // from class: com.android.permissioncontroller.permission.data.PermGroupsPackagesUiInfoLiveData$getPermGroupPackageLiveDatas$getLiveData$1
            @Override // kotlin.jvm.functions.Function1
            public final SinglePermGroupPackagesUiInfoLiveData invoke(String groupName) {
                Intrinsics.checkParameterIsNotNull(groupName, "groupName");
                return SinglePermGroupPackagesUiInfoLiveData.INSTANCE.get(groupName);
            }
        };
        List<String> list = this.groupNames;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("groupNames");
        }
        SmartUpdateMediatorLiveData.setSourcesToDifference$default(this, list, this.permGroupPackagesLiveDatas, getLiveData, null, 8, null);
    }

    private final int getNonSystemTotal(Map<Pair<String, UserHandle>, AppPermGroupUiInfo> uiInfo) {
        int shownNonSystem = 0;
        Iterator<Map.Entry<Pair<String, UserHandle>, AppPermGroupUiInfo>> it = uiInfo.entrySet().iterator();
        while (it.hasNext()) {
            AppPermGroupUiInfo appPermGroup = it.next().getValue();
            if (appPermGroup.getShouldShow() && !appPermGroup.isSystem()) {
                shownNonSystem++;
            }
        }
        return shownNonSystem;
    }

    private final int getNonSystemGranted(Map<Pair<String, UserHandle>, AppPermGroupUiInfo> uiInfo) {
        int granted = 0;
        Iterator<Map.Entry<Pair<String, UserHandle>, AppPermGroupUiInfo>> it = uiInfo.entrySet().iterator();
        while (it.hasNext()) {
            AppPermGroupUiInfo appPermGroup = it.next().getValue();
            if (appPermGroup.getShouldShow() && !appPermGroup.isSystem() && appPermGroup.getPermGrantState() != AppPermGroupUiInfo.PermGrantState.PERMS_DENIED && appPermGroup.getPermGrantState() != AppPermGroupUiInfo.PermGrantState.PERMS_ASK) {
                granted++;
            }
        }
        return granted;
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
    protected void onUpdate() {
        boolean haveAllLiveDatas;
        SinglePermGroupPackagesUiInfoLiveData singlePermGroupPackagesUiInfoLiveData;
        Map<Pair<String, UserHandle>, AppPermGroupUiInfo> uiInfo;
        Iterable iterable = this.groupNames;
        if (iterable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("groupNames");
        }
        Iterable $this$all$iv = iterable;
        boolean z = false;
        if (!($this$all$iv instanceof Collection) || !((Collection) $this$all$iv).isEmpty()) {
            Iterator it = $this$all$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    String it2 = (String) element$iv;
                    if (!this.permGroupPackagesLiveDatas.containsKey(it2)) {
                        haveAllLiveDatas = false;
                        break;
                    }
                } else {
                    haveAllLiveDatas = true;
                    break;
                }
            }
        } else {
            haveAllLiveDatas = true;
        }
        Map<String, SinglePermGroupPackagesUiInfoLiveData> map = this.permGroupPackagesLiveDatas;
        if (!map.isEmpty()) {
            Iterator<Map.Entry<String, SinglePermGroupPackagesUiInfoLiveData>> it3 = map.entrySet().iterator();
            while (true) {
                if (it3.hasNext()) {
                    if (!it3.next().getValue().getIsInitialized()) {
                        break;
                    }
                } else {
                    z = true;
                    break;
                }
            }
        } else {
            z = true;
        }
        boolean allInitialized = z;
        List<String> list = this.groupNames;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("groupNames");
        }
        for (String groupName : list) {
            Map<String, PermGroupPackagesUiInfo> map2 = this.allPackageData;
            PermGroupPackagesUiInfo permGroupPackagesUiInfo = null;
            if (haveAllLiveDatas && allInitialized && (singlePermGroupPackagesUiInfoLiveData = this.permGroupPackagesLiveDatas.get(groupName)) != null && (uiInfo = (Map) singlePermGroupPackagesUiInfoLiveData.getValue()) != null) {
                Intrinsics.checkExpressionValueIsNotNull(uiInfo, "uiInfo");
                permGroupPackagesUiInfo = new PermGroupPackagesUiInfo(groupName, getNonSystemTotal(uiInfo), getNonSystemGranted(uiInfo));
            }
            map2.put(groupName, permGroupPackagesUiInfo);
        }
        setValue(MapsKt.toMap(this.allPackageData));
    }
}
