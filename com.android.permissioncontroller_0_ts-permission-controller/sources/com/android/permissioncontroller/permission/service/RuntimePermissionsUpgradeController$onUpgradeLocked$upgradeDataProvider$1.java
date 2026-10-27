package com.android.permissioncontroller.permission.service;

import android.os.Process;
import android.os.UserHandle;
import android.util.Log;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.permission.data.DataRepositoryKt;
import com.android.permissioncontroller.permission.data.LightAppPermGroupLiveData;
import com.android.permissioncontroller.permission.data.LightPermInfoLiveData;
import com.android.permissioncontroller.permission.data.PreinstalledUserPackageInfosLiveData;
import com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData;
import com.android.permissioncontroller.permission.data.UserPackageInfosLiveData;
import com.android.permissioncontroller.permission.model.livedatatypes.LightAppPermGroup;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPermInfo;
import com.android.permissioncontroller.permission.service.RuntimePermissionsUpgradeController;
import com.android.permissioncontroller.permission.utils.LocationUtils;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RuntimePermissionsUpgradeController.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00009\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u000f\u001a\u00020\u0010H\u0014R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"com/android/permissioncontroller/permission/service/RuntimePermissionsUpgradeController$onUpgradeLocked$upgradeDataProvider$1", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "Lcom/android/permissioncontroller/permission/service/RuntimePermissionsUpgradeController$UpgradeData;", "permGroupProviders", "", "Lcom/android/permissioncontroller/permission/data/LightAppPermGroupLiveData;", "permGroupProvidersDone", "", "pkgInfoProvider", "Lcom/android/permissioncontroller/permission/data/UserPackageInfosLiveData;", "platformRuntimePermissionInfoProviders", "Lcom/android/permissioncontroller/permission/data/LightPermInfoLiveData;", "platformRuntimePermissionInfoProvidersDone", "preinstalledPkgInfoProvider", "Lcom/android/permissioncontroller/permission/data/PreinstalledUserPackageInfosLiveData;", "onUpdate", "", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class RuntimePermissionsUpgradeController$onUpgradeLocked$upgradeDataProvider$1 extends SmartUpdateMediatorLiveData<RuntimePermissionsUpgradeController.UpgradeData> {
    final /* synthetic */ boolean $needAccessMediaAppPermGroups;
    final /* synthetic */ boolean $needBackgroundAppPermGroups;
    private List<LightAppPermGroupLiveData> permGroupProviders;
    private final Set<LightAppPermGroupLiveData> permGroupProvidersDone;
    private final UserPackageInfosLiveData pkgInfoProvider;
    private final List<LightPermInfoLiveData> platformRuntimePermissionInfoProviders;
    private final Set<LightPermInfoLiveData> platformRuntimePermissionInfoProvidersDone;
    private final PreinstalledUserPackageInfosLiveData preinstalledPkgInfoProvider;

    /* JADX WARN: Multi-variable type inference failed */
    RuntimePermissionsUpgradeController$onUpgradeLocked$upgradeDataProvider$1(boolean $captured_local_variable$0, boolean $captured_local_variable$1) {
        this.$needBackgroundAppPermGroups = $captured_local_variable$0;
        this.$needAccessMediaAppPermGroups = $captured_local_variable$1;
        PreinstalledUserPackageInfosLiveData.Companion companion = PreinstalledUserPackageInfosLiveData.INSTANCE;
        UserHandle userHandleMyUserHandle = Process.myUserHandle();
        Intrinsics.checkExpressionValueIsNotNull(userHandleMyUserHandle, "myUserHandle()");
        this.preinstalledPkgInfoProvider = companion.get(userHandleMyUserHandle);
        this.platformRuntimePermissionInfoProviders = new ArrayList();
        this.platformRuntimePermissionInfoProvidersDone = new LinkedHashSet();
        UserPackageInfosLiveData.Companion companion2 = UserPackageInfosLiveData.INSTANCE;
        UserHandle userHandleMyUserHandle2 = Process.myUserHandle();
        Intrinsics.checkExpressionValueIsNotNull(userHandleMyUserHandle2, "myUserHandle()");
        this.pkgInfoProvider = companion2.get(userHandleMyUserHandle2);
        this.permGroupProvidersDone = new LinkedHashSet();
        StringBuilder sb = new StringBuilder();
        sb.append("observing UserPackageInfoLiveData for ");
        UserHandle userHandleMyUserHandle3 = Process.myUserHandle();
        Intrinsics.checkExpressionValueIsNotNull(userHandleMyUserHandle3, "myUserHandle()");
        sb.append(userHandleMyUserHandle3.getIdentifier());
        sb.append(" in RuntimePermissionsUpgradeController");
        Log.i("RuntimePermissions", sb.toString());
        addSource(this.pkgInfoProvider, new Observer<List<LightPackageInfo>>() { // from class: com.android.permissioncontroller.permission.service.RuntimePermissionsUpgradeController$onUpgradeLocked$upgradeDataProvider$1.1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(List<LightPackageInfo> list) {
                if (list != null) {
                    RuntimePermissionsUpgradeController$onUpgradeLocked$upgradeDataProvider$1 runtimePermissionsUpgradeController$onUpgradeLocked$upgradeDataProvider$1 = RuntimePermissionsUpgradeController$onUpgradeLocked$upgradeDataProvider$1.this;
                    runtimePermissionsUpgradeController$onUpgradeLocked$upgradeDataProvider$1.removeSource(runtimePermissionsUpgradeController$onUpgradeLocked$upgradeDataProvider$1.pkgInfoProvider);
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("observing ");
                    sb2.append("PreinstalledUserPackageInfoLiveData for ");
                    UserHandle userHandleMyUserHandle4 = Process.myUserHandle();
                    Intrinsics.checkExpressionValueIsNotNull(userHandleMyUserHandle4, "myUserHandle()");
                    sb2.append(userHandleMyUserHandle4.getIdentifier());
                    sb2.append(" in RuntimePermissionsUpgradeController");
                    Log.i("RuntimePermissions", sb2.toString());
                    RuntimePermissionsUpgradeController$onUpgradeLocked$upgradeDataProvider$1 runtimePermissionsUpgradeController$onUpgradeLocked$upgradeDataProvider$12 = RuntimePermissionsUpgradeController$onUpgradeLocked$upgradeDataProvider$1.this;
                    runtimePermissionsUpgradeController$onUpgradeLocked$upgradeDataProvider$12.addSource(runtimePermissionsUpgradeController$onUpgradeLocked$upgradeDataProvider$12.preinstalledPkgInfoProvider, new Observer<List<LightPackageInfo>>() { // from class: com.android.permissioncontroller.permission.service.RuntimePermissionsUpgradeController.onUpgradeLocked.upgradeDataProvider.1.1.1
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(List<LightPackageInfo> list2) {
                            if (list2 != null) {
                                RuntimePermissionsUpgradeController$onUpgradeLocked$upgradeDataProvider$1.this.removeSource(RuntimePermissionsUpgradeController$onUpgradeLocked$upgradeDataProvider$1.this.preinstalledPkgInfoProvider);
                                RuntimePermissionsUpgradeController$onUpgradeLocked$upgradeDataProvider$1.this.updateIfActive();
                            }
                        }
                    });
                }
            }
        });
        for (String platformRuntimePermission : Utils.getRuntimePlatformPermissionNames()) {
            LightPermInfoLiveData.Companion companion3 = LightPermInfoLiveData.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(platformRuntimePermission, "platformRuntimePermission");
            final LightPermInfoLiveData permProvider = (LightPermInfoLiveData) companion3.get(platformRuntimePermission);
            this.platformRuntimePermissionInfoProviders.add(permProvider);
            addSource(permProvider, new Observer<LightPermInfo>() { // from class: com.android.permissioncontroller.permission.service.RuntimePermissionsUpgradeController$onUpgradeLocked$upgradeDataProvider$1.2
                @Override // androidx.lifecycle.Observer
                public final void onChanged(LightPermInfo permInfo) {
                    if (permInfo != null) {
                        RuntimePermissionsUpgradeController$onUpgradeLocked$upgradeDataProvider$1.this.platformRuntimePermissionInfoProvidersDone.add(permProvider);
                        RuntimePermissionsUpgradeController$onUpgradeLocked$upgradeDataProvider$1.this.removeSource(permProvider);
                        RuntimePermissionsUpgradeController$onUpgradeLocked$upgradeDataProvider$1.this.updateIfActive();
                    }
                }
            });
        }
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
    protected void onUpdate() {
        List<LightAppPermGroupLiveData> list;
        if (this.permGroupProviders == null && this.pkgInfoProvider.getValue() != null) {
            this.permGroupProviders = new ArrayList();
            if (this.$needBackgroundAppPermGroups || this.$needAccessMediaAppPermGroups) {
                List<LightPackageInfo> value = this.pkgInfoProvider.getValue();
                if (value == null) {
                    Intrinsics.throwNpe();
                }
                for (LightPackageInfo lightPackageInfo : value) {
                    String packageName = lightPackageInfo.getPackageName();
                    boolean z = false;
                    boolean z2 = false;
                    for (Pair pair : CollectionsKt.zip(lightPackageInfo.component3(), lightPackageInfo.component4())) {
                        String str = (String) pair.component1();
                        int iIntValue = ((Number) pair.component2()).intValue();
                        if (this.$needBackgroundAppPermGroups && Intrinsics.areEqual(str, "android.permission.ACCESS_BACKGROUND_LOCATION")) {
                            List<LightAppPermGroupLiveData> list2 = this.permGroupProviders;
                            if (list2 == null) {
                                Intrinsics.throwNpe();
                            }
                            LightAppPermGroupLiveData.Companion companion = LightAppPermGroupLiveData.INSTANCE;
                            UserHandle userHandleMyUserHandle = Process.myUserHandle();
                            Intrinsics.checkExpressionValueIsNotNull(userHandleMyUserHandle, "myUserHandle()");
                            list2.add((LightAppPermGroupLiveData) DataRepositoryKt.get(companion, packageName, LocationUtils.LOCATION_PERMISSION, userHandleMyUserHandle));
                        }
                        if (this.$needAccessMediaAppPermGroups) {
                            if (Intrinsics.areEqual(str, "android.permission.ACCESS_MEDIA_LOCATION")) {
                                z = true;
                            }
                            if (Intrinsics.areEqual(str, "android.permission.READ_EXTERNAL_STORAGE") && (iIntValue & 2) != 0) {
                                z2 = true;
                            }
                        }
                    }
                    if (z && z2) {
                        List<LightAppPermGroupLiveData> list3 = this.permGroupProviders;
                        if (list3 == null) {
                            Intrinsics.throwNpe();
                        }
                        LightAppPermGroupLiveData.Companion companion2 = LightAppPermGroupLiveData.INSTANCE;
                        UserHandle userHandleMyUserHandle2 = Process.myUserHandle();
                        Intrinsics.checkExpressionValueIsNotNull(userHandleMyUserHandle2, "myUserHandle()");
                        list3.add((LightAppPermGroupLiveData) DataRepositoryKt.get(companion2, packageName, "android.permission-group.STORAGE", userHandleMyUserHandle2));
                    }
                }
            }
            List<LightAppPermGroupLiveData> list4 = this.permGroupProviders;
            if (list4 == null) {
                Intrinsics.throwNpe();
            }
            for (final LightAppPermGroupLiveData lightAppPermGroupLiveData : list4) {
                addSource(lightAppPermGroupLiveData, new Observer<LightAppPermGroup>() { // from class: com.android.permissioncontroller.permission.service.RuntimePermissionsUpgradeController$onUpgradeLocked$upgradeDataProvider$1$onUpdate$1
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(LightAppPermGroup group) {
                        if (group != null) {
                            this.this$0.permGroupProvidersDone.add(lightAppPermGroupLiveData);
                            this.this$0.removeSource(lightAppPermGroupLiveData);
                            this.this$0.updateIfActive();
                        }
                    }
                });
            }
            List<LightAppPermGroupLiveData> list5 = this.permGroupProviders;
            if (list5 == null) {
                Intrinsics.throwNpe();
            }
            if (list5.isEmpty()) {
                updateIfActive();
                return;
            }
            return;
        }
        if (this.permGroupProviders != null) {
            int size = this.permGroupProvidersDone.size();
            List<LightAppPermGroupLiveData> list6 = this.permGroupProviders;
            if (list6 == null) {
                Intrinsics.throwNpe();
            }
            if (size == list6.size() && this.preinstalledPkgInfoProvider.getValue() != null && this.platformRuntimePermissionInfoProviders.size() == this.platformRuntimePermissionInfoProvidersDone.size()) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                List<LightAppPermGroupLiveData> list7 = this.permGroupProviders;
                if (list7 == null) {
                    Intrinsics.throwNpe();
                }
                List<LightAppPermGroupLiveData> list8 = list7;
                ArrayList<LightAppPermGroup> arrayList3 = new ArrayList();
                Iterator<T> it = list8.iterator();
                while (it.hasNext()) {
                    LightAppPermGroup value2 = ((LightAppPermGroupLiveData) it.next()).getValue();
                    if (value2 != null) {
                        list = list8;
                        arrayList3.add(value2);
                    } else {
                        list = list8;
                    }
                    list8 = list;
                }
                for (LightAppPermGroup group : arrayList3) {
                    String permGroupName = group.getPermGroupName();
                    int iHashCode = permGroupName.hashCode();
                    if (iHashCode == 828638019) {
                        if (permGroupName.equals(LocationUtils.LOCATION_PERMISSION)) {
                            Intrinsics.checkExpressionValueIsNotNull(group, "group");
                            arrayList.add(group);
                        }
                    } else if (iHashCode == 852078861 && permGroupName.equals("android.permission-group.STORAGE")) {
                        Intrinsics.checkExpressionValueIsNotNull(group, "group");
                        arrayList2.add(group);
                    }
                }
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                Iterator<LightPermInfoLiveData> it2 = this.platformRuntimePermissionInfoProviders.iterator();
                while (it2.hasNext()) {
                    LightPermInfo value3 = it2.next().getValue();
                    if (value3 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(value3, "permInfoLiveDt.value!!");
                    if ((value3.getFlags() & 12) != 0) {
                        linkedHashSet.add(value3.getName());
                    }
                }
                List<LightPackageInfo> value4 = this.preinstalledPkgInfoProvider.getValue();
                if (value4 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(value4, "preinstalledPkgInfoProvider.value!!");
                List<LightPackageInfo> value5 = this.pkgInfoProvider.getValue();
                if (value5 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(value5, "pkgInfoProvider.value!!");
                setValue(new RuntimePermissionsUpgradeController.UpgradeData(value4, linkedHashSet, value5, arrayList, arrayList2));
            }
        }
    }
}
