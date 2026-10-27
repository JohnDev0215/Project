package com.android.permissioncontroller.permission.data;

import android.app.Application;
import android.os.UserHandle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPermGroupInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPermInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.PermGroup;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PermGroupsPackagesLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00152&\u0012\"\u0012 \u0012\u0004\u0012\u00020\u0003\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u00050\u00040\u00020\u0001:\u0001\u0015B#\b\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000b0\n¢\u0006\u0002\u0010\fJ\b\u0010\u0013\u001a\u00020\u0014H\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/android/permissioncontroller/permission/data/PermGroupsPackagesLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "", "", "", "Lkotlin/Pair;", "Landroid/os/UserHandle;", "app", "Landroid/app/Application;", "groupNamesLiveData", "Landroidx/lifecycle/LiveData;", "", "(Landroid/app/Application;Landroidx/lifecycle/LiveData;)V", "groupNames", "packagesLiveData", "Lcom/android/permissioncontroller/permission/data/AllPackageInfosLiveData;", "permGroupLiveDatas", "", "Lcom/android/permissioncontroller/permission/data/PermGroupLiveData;", "onUpdate", "", "Companion", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class PermGroupsPackagesLiveData extends SmartUpdateMediatorLiveData<Map<String, ? extends Set<? extends Pair<? extends String, ? extends UserHandle>>>> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final PermGroupsPackagesLiveData customInstance;
    private static final PermGroupsPackagesLiveData standardInstance;
    private final Application app;
    private final AllPackageInfosLiveData packagesLiveData = AllPackageInfosLiveData.INSTANCE;
    private final Map<String, PermGroupLiveData> permGroupLiveDatas = new LinkedHashMap();
    private List<String> groupNames = CollectionsKt.emptyList();

    @JvmStatic
    public static final PermGroupsPackagesLiveData get(boolean z) {
        return INSTANCE.get(z);
    }

    private PermGroupsPackagesLiveData(Application app, LiveData<List<String>> liveData) {
        this.app = app;
        addSource(liveData, new Observer<List<? extends String>>() { // from class: com.android.permissioncontroller.permission.data.PermGroupsPackagesLiveData.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends String> list) {
                onChanged2((List<String>) list);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<String> list) {
                PermGroupsPackagesLiveData.this.groupNames = list != null ? list : CollectionsKt.emptyList();
                PermGroupsPackagesLiveData$1$getLiveData$1 permGroupsPackagesLiveData$1$getLiveData$1 = new Function1<String, PermGroupLiveData>() { // from class: com.android.permissioncontroller.permission.data.PermGroupsPackagesLiveData$1$getLiveData$1
                    @Override // kotlin.jvm.functions.Function1
                    public final PermGroupLiveData invoke(String groupName) {
                        Intrinsics.checkParameterIsNotNull(groupName, "groupName");
                        return PermGroupLiveData.INSTANCE.get(groupName);
                    }
                };
                PermGroupsPackagesLiveData permGroupsPackagesLiveData = PermGroupsPackagesLiveData.this;
                permGroupsPackagesLiveData.setSourcesToDifference(permGroupsPackagesLiveData.groupNames, PermGroupsPackagesLiveData.this.permGroupLiveDatas, permGroupsPackagesLiveData$1$getLiveData$1, new Function1<String, Unit>() { // from class: com.android.permissioncontroller.permission.data.PermGroupsPackagesLiveData.1.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(String str) {
                        invoke2(str);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(String it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        if (PermGroupsPackagesLiveData.this.packagesLiveData.getIsInitialized()) {
                            Map $this$all$iv = PermGroupsPackagesLiveData.this.permGroupLiveDatas;
                            boolean z = true;
                            if (!$this$all$iv.isEmpty()) {
                                Iterator it2 = $this$all$iv.entrySet().iterator();
                                while (true) {
                                    if (!it2.hasNext()) {
                                        break;
                                    }
                                    Map.Entry element$iv = (Map.Entry) it2.next();
                                    if (!((PermGroupLiveData) element$iv.getValue()).getIsInitialized()) {
                                        z = false;
                                        break;
                                    }
                                }
                            }
                            if (z) {
                                PermGroupsPackagesLiveData.this.updateIfActive();
                            }
                        }
                    }
                });
            }
        });
        addSource(this.packagesLiveData, new Observer<Map<UserHandle, ? extends List<? extends LightPackageInfo>>>() { // from class: com.android.permissioncontroller.permission.data.PermGroupsPackagesLiveData.2
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Map<UserHandle, ? extends List<? extends LightPackageInfo>> map) {
                onChanged2((Map<UserHandle, ? extends List<LightPackageInfo>>) map);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(Map<UserHandle, ? extends List<LightPackageInfo>> map) {
                Map $this$all$iv = PermGroupsPackagesLiveData.this.permGroupLiveDatas;
                boolean z = true;
                if (!$this$all$iv.isEmpty()) {
                    Iterator it = $this$all$iv.entrySet().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Map.Entry element$iv = (Map.Entry) it.next();
                        if (!((PermGroupLiveData) element$iv.getValue()).getIsInitialized()) {
                            z = false;
                            break;
                        }
                    }
                }
                if (z) {
                    PermGroupsPackagesLiveData.this.updateIfActive();
                }
            }
        });
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
    protected void onUpdate() {
        Map<UserHandle, ? extends List<? extends LightPackageInfo>> map;
        if (this.groupNames.isEmpty()) {
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List<PermGroup> permGroups = new ArrayList();
        for (String groupName : this.groupNames) {
            PermGroupLiveData permGroupLiveData = this.permGroupLiveDatas.get(groupName);
            PermGroup permGroup = permGroupLiveData != null ? permGroupLiveData.getValue() : null;
            if (permGroup != null && permGroup.getHasRuntimePermissions()) {
                permGroups.add(permGroup);
                linkedHashMap.put(groupName, new LinkedHashSet());
            }
        }
        Map<UserHandle, ? extends List<? extends LightPackageInfo>> value = this.packagesLiveData.getValue();
        if (value == null) {
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(value, "packagesLiveData.value ?: return");
        for (Map.Entry<UserHandle, ? extends List<? extends LightPackageInfo>> entry : value.entrySet()) {
            UserHandle userHandle = entry.getKey();
            for (LightPackageInfo packageInfo : entry.getValue()) {
                boolean z = true;
                boolean isPreMApp = packageInfo.getTargetSdkVersion() < 23;
                for (PermGroup permGroup2 : permGroups) {
                    LightPermGroupInfo groupInfo = permGroup2.getGroupInfo();
                    Map<String, LightPermInfo> mapComponent2 = permGroup2.component2();
                    if (!isPreMApp || !(Intrinsics.areEqual(groupInfo.getPackageName(), Utils.OS_PKG) ^ z)) {
                        for (String permissionName : packageInfo.getRequestedPermissions()) {
                            if (!mapComponent2.containsKey(permissionName)) {
                                map = value;
                            } else {
                                Set set = (Set) linkedHashMap.get(groupInfo.getName());
                                if (set != null) {
                                    map = value;
                                    set.add(TuplesKt.to(packageInfo.getPackageName(), userHandle));
                                } else {
                                    map = value;
                                }
                            }
                            value = map;
                        }
                    }
                    value = value;
                    z = true;
                }
            }
        }
        for (PermGroup permGroup3 : permGroups) {
            if (permGroup3.getGroupInfo().isSinglePermGroup() || Intrinsics.areEqual(permGroup3.getName(), "android.permission-group.UNDEFINED")) {
                Set groupPackages = (Set) linkedHashMap.get(permGroup3.getName());
                if (groupPackages != null && groupPackages.isEmpty()) {
                    linkedHashMap.remove(permGroup3.getName());
                }
            }
        }
        setValue(linkedHashMap);
    }

    /* JADX INFO: compiled from: PermGroupsPackagesLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/android/permissioncontroller/permission/data/PermGroupsPackagesLiveData$Companion;", "", "()V", "customInstance", "Lcom/android/permissioncontroller/permission/data/PermGroupsPackagesLiveData;", "standardInstance", "get", "customGroups", "", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public static /* synthetic */ PermGroupsPackagesLiveData get$default(Companion companion, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.get(z);
        }

        @JvmStatic
        public final PermGroupsPackagesLiveData get(boolean customGroups) {
            return customGroups ? PermGroupsPackagesLiveData.customInstance : PermGroupsPackagesLiveData.standardInstance;
        }
    }

    static {
        PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
        Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
        customInstance = new PermGroupsPackagesLiveData(permissionControllerApplication, CustomPermGroupNamesLiveData.INSTANCE);
        PermissionControllerApplication permissionControllerApplication2 = PermissionControllerApplication.get();
        Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication2, "PermissionControllerApplication.get()");
        standardInstance = new PermGroupsPackagesLiveData(permissionControllerApplication2, StandardPermGroupNamesLiveData.INSTANCE);
    }
}
