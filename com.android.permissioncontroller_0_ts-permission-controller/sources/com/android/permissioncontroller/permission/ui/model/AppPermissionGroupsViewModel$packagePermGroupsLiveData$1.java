package com.android.permissioncontroller.permission.ui.model;

import androidx.lifecycle.Observer;
import com.android.permissioncontroller.permission.data.AppPermGroupUiInfoLiveData;
import com.android.permissioncontroller.permission.data.DataRepositoryKt;
import com.android.permissioncontroller.permission.data.FullStoragePermissionAppsLiveData;
import com.android.permissioncontroller.permission.data.PackagePermissionsLiveData;
import com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData;
import com.android.permissioncontroller.permission.model.livedatatypes.AppPermGroupUiInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.AutoRevokeState;
import com.android.permissioncontroller.permission.ui.Category;
import com.android.permissioncontroller.permission.ui.model.AppPermissionGroupsViewModel;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppPermissionGroupsViewModel.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000?\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u001f\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0002¢\u0006\u0002\b\u00060\u0001J\b\u0010\u000f\u001a\u00020\u0010H\u0014R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"com/android/permissioncontroller/permission/ui/model/AppPermissionGroupsViewModel$packagePermGroupsLiveData$1", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "", "Lcom/android/permissioncontroller/permission/ui/Category;", "", "Lcom/android/permissioncontroller/permission/ui/model/AppPermissionGroupsViewModel$GroupUiInfo;", "Lkotlin/jvm/JvmSuppressWildcards;", "appPermGroupUiInfoLiveDatas", "", "", "Lcom/android/permissioncontroller/permission/data/AppPermGroupUiInfoLiveData;", "fullStoragePermsLiveData", "Lcom/android/permissioncontroller/permission/data/FullStoragePermissionAppsLiveData;", "packagePermsLiveData", "Lcom/android/permissioncontroller/permission/data/PackagePermissionsLiveData;", "onUpdate", "", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class AppPermissionGroupsViewModel$packagePermGroupsLiveData$1 extends SmartUpdateMediatorLiveData<Map<Category, List<AppPermissionGroupsViewModel.GroupUiInfo>>> {
    private final Map<String, AppPermGroupUiInfoLiveData> appPermGroupUiInfoLiveDatas = new LinkedHashMap();
    private final FullStoragePermissionAppsLiveData fullStoragePermsLiveData = FullStoragePermissionAppsLiveData.INSTANCE;
    private final PackagePermissionsLiveData packagePermsLiveData;
    final /* synthetic */ AppPermissionGroupsViewModel this$0;

    AppPermissionGroupsViewModel$packagePermGroupsLiveData$1(AppPermissionGroupsViewModel $outer) {
        this.this$0 = $outer;
        this.packagePermsLiveData = (PackagePermissionsLiveData) DataRepositoryKt.get(PackagePermissionsLiveData.INSTANCE, $outer.packageName, $outer.user);
        addSource(this.packagePermsLiveData, new Observer<Map<String, ? extends List<? extends String>>>() { // from class: com.android.permissioncontroller.permission.ui.model.AppPermissionGroupsViewModel$packagePermGroupsLiveData$1.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Map<String, ? extends List<? extends String>> map) {
                onChanged2((Map<String, ? extends List<String>>) map);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(Map<String, ? extends List<String>> map) {
                AppPermissionGroupsViewModel$packagePermGroupsLiveData$1.this.updateIfActive();
            }
        });
        addSource(this.fullStoragePermsLiveData, new Observer<List<? extends FullStoragePermissionAppsLiveData.FullStoragePackageState>>() { // from class: com.android.permissioncontroller.permission.ui.model.AppPermissionGroupsViewModel$packagePermGroupsLiveData$1.2
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends FullStoragePermissionAppsLiveData.FullStoragePackageState> list) {
                onChanged2((List<FullStoragePermissionAppsLiveData.FullStoragePackageState>) list);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<FullStoragePermissionAppsLiveData.FullStoragePackageState> list) {
                AppPermissionGroupsViewModel$packagePermGroupsLiveData$1.this.updateIfActive();
            }
        });
        addSource($outer.getAutoRevokeLiveData(), new Observer<AutoRevokeState>() { // from class: com.android.permissioncontroller.permission.ui.model.AppPermissionGroupsViewModel$packagePermGroupsLiveData$1.3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(AutoRevokeState it) {
                AppPermissionGroupsViewModel$packagePermGroupsLiveData$1 appPermissionGroupsViewModel$packagePermGroupsLiveData$1 = AppPermissionGroupsViewModel$packagePermGroupsLiveData$1.this;
                appPermissionGroupsViewModel$packagePermGroupsLiveData$1.removeSource(appPermissionGroupsViewModel$packagePermGroupsLiveData$1.this$0.getAutoRevokeLiveData());
                AppPermissionGroupsViewModel$packagePermGroupsLiveData$1.this.updateIfActive();
            }
        });
        updateIfActive();
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
    protected void onUpdate() {
        List<String> groups;
        boolean hasFullStorage;
        AppPermGroupUiInfo uiInfo;
        Iterable iterableKeySet;
        Map<String, ? extends List<? extends String>> value = this.packagePermsLiveData.getValue();
        if (value == null || (iterableKeySet = value.keySet()) == null) {
            groups = null;
        } else {
            Iterable $this$filter$iv = iterableKeySet;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                String it = (String) element$iv$iv;
                if (!Intrinsics.areEqual(it, PackagePermissionsLiveData.NON_RUNTIME_NORMAL_PERMS)) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            groups = (List) destination$iv$iv;
        }
        if (groups == null && this.packagePermsLiveData.getIsInitialized()) {
            setValue(null);
            return;
        }
        if (groups != null) {
            if ((!groups.contains("android.permission-group.STORAGE") || this.fullStoragePermsLiveData.getIsInitialized()) && this.this$0.getAutoRevokeLiveData().getIsInitialized()) {
                Iterable iterable = (List) this.fullStoragePermsLiveData.getValue();
                boolean z = false;
                if (iterable != null) {
                    Iterable $this$any$iv = iterable;
                    if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                        Iterator it2 = $this$any$iv.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                hasFullStorage = false;
                                break;
                            }
                            Object element$iv = it2.next();
                            FullStoragePermissionAppsLiveData.FullStoragePackageState pkg = (FullStoragePermissionAppsLiveData.FullStoragePackageState) element$iv;
                            if (Intrinsics.areEqual(pkg.getPackageName(), this.this$0.packageName) && Intrinsics.areEqual(pkg.getUser(), this.this$0.user) && pkg.isGranted()) {
                                hasFullStorage = true;
                                break;
                            }
                        }
                    } else {
                        hasFullStorage = false;
                    }
                } else {
                    hasFullStorage = false;
                }
                SmartUpdateMediatorLiveData.setSourcesToDifference$default(this, groups, this.appPermGroupUiInfoLiveDatas, new Function1<String, AppPermGroupUiInfoLiveData>() { // from class: com.android.permissioncontroller.permission.ui.model.AppPermissionGroupsViewModel$packagePermGroupsLiveData$1$onUpdate$getLiveData$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final AppPermGroupUiInfoLiveData invoke(String groupName) {
                        Intrinsics.checkParameterIsNotNull(groupName, "groupName");
                        return (AppPermGroupUiInfoLiveData) DataRepositoryKt.get(AppPermGroupUiInfoLiveData.INSTANCE, this.this$0.this$0.packageName, groupName, this.this$0.this$0.user);
                    }
                }, null, 8, null);
                Map<String, AppPermGroupUiInfoLiveData> map = this.appPermGroupUiInfoLiveDatas;
                if (!map.isEmpty()) {
                    Iterator<Map.Entry<String, AppPermGroupUiInfoLiveData>> it3 = map.entrySet().iterator();
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
                if (z) {
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.put(Category.ALLOWED, new ArrayList());
                    linkedHashMap.put(Category.ASK, new ArrayList());
                    linkedHashMap.put(Category.DENIED, new ArrayList());
                    for (String groupName : groups) {
                        boolean isSystem = Utils.getPlatformPermissionGroups().contains(groupName);
                        AppPermGroupUiInfoLiveData appPermGroupUiInfoLiveData = this.appPermGroupUiInfoLiveDatas.get(groupName);
                        if (appPermGroupUiInfoLiveData != null && (uiInfo = appPermGroupUiInfoLiveData.getValue()) != null) {
                            int i = AppPermissionGroupsViewModel.WhenMappings.$EnumSwitchMapping$0[uiInfo.getPermGrantState().ordinal()];
                            if (i == 1) {
                                AppPermissionGroupsViewModel.PermSubtitle subtitle = AppPermissionGroupsViewModel.PermSubtitle.NONE;
                                if (Intrinsics.areEqual(groupName, "android.permission-group.STORAGE")) {
                                    subtitle = hasFullStorage ? AppPermissionGroupsViewModel.PermSubtitle.ALL_FILES : AppPermissionGroupsViewModel.PermSubtitle.MEDIA_ONLY;
                                }
                                Object obj = linkedHashMap.get(Category.ALLOWED);
                                if (obj == null) {
                                    Intrinsics.throwNpe();
                                }
                                ((List) obj).add(new AppPermissionGroupsViewModel.GroupUiInfo(groupName, isSystem, subtitle));
                            } else if (i == 2) {
                                Object obj2 = linkedHashMap.get(Category.ALLOWED);
                                if (obj2 == null) {
                                    Intrinsics.throwNpe();
                                }
                                ((List) obj2).add(new AppPermissionGroupsViewModel.GroupUiInfo(groupName, isSystem));
                            } else if (i == 3) {
                                Object obj3 = linkedHashMap.get(Category.ALLOWED);
                                if (obj3 == null) {
                                    Intrinsics.throwNpe();
                                }
                                ((List) obj3).add(new AppPermissionGroupsViewModel.GroupUiInfo(groupName, isSystem, AppPermissionGroupsViewModel.PermSubtitle.FOREGROUND_ONLY));
                            } else if (i == 4) {
                                Object obj4 = linkedHashMap.get(Category.DENIED);
                                if (obj4 == null) {
                                    Intrinsics.throwNpe();
                                }
                                ((List) obj4).add(new AppPermissionGroupsViewModel.GroupUiInfo(groupName, isSystem));
                            } else {
                                if (i != 5) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                Object obj5 = linkedHashMap.get(Category.ASK);
                                if (obj5 == null) {
                                    Intrinsics.throwNpe();
                                }
                                ((List) obj5).add(new AppPermissionGroupsViewModel.GroupUiInfo(groupName, isSystem));
                            }
                        }
                    }
                    setValue(linkedHashMap);
                }
            }
        }
    }
}
