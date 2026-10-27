package com.android.permissioncontroller.permission.data;

import android.app.usage.UsageStats;
import android.os.UserHandle;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.permission.service.AutoRevokePermissions;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoRevokedPackagesLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002&\u0012\"\u0012 \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00060\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0014R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/android/permissioncontroller/permission/data/UnusedAutoRevokedPackagesLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "", "Lkotlin/Pair;", "", "Landroid/os/UserHandle;", "", "()V", "unusedThreshold", "", "usageStatsLiveData", "Lcom/android/permissioncontroller/permission/data/UsageStatsLiveData;", "onUpdate", "", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class UnusedAutoRevokedPackagesLiveData extends SmartUpdateMediatorLiveData<Map<Pair<? extends String, ? extends UserHandle>, ? extends Set<? extends String>>> {
    public static final UnusedAutoRevokedPackagesLiveData INSTANCE;
    private static final long unusedThreshold;
    private static final UsageStatsLiveData usageStatsLiveData;

    static {
        UnusedAutoRevokedPackagesLiveData unusedAutoRevokedPackagesLiveData = new UnusedAutoRevokedPackagesLiveData();
        INSTANCE = unusedAutoRevokedPackagesLiveData;
        PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
        Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
        unusedThreshold = AutoRevokePermissions.getUnusedThresholdMs(permissionControllerApplication);
        UsageStatsLiveData usageStatsLiveData2 = UsageStatsLiveData.INSTANCE.get(unusedThreshold);
        usageStatsLiveData = usageStatsLiveData2;
        unusedAutoRevokedPackagesLiveData.addSource(usageStatsLiveData2, new Observer<Map<UserHandle, ? extends List<? extends UsageStats>>>() { // from class: com.android.permissioncontroller.permission.data.UnusedAutoRevokedPackagesLiveData.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Map<UserHandle, ? extends List<? extends UsageStats>> map) {
                onChanged2((Map<UserHandle, ? extends List<UsageStats>>) map);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(Map<UserHandle, ? extends List<UsageStats>> map) {
                UnusedAutoRevokedPackagesLiveData.INSTANCE.updateIfActive();
            }
        });
        unusedAutoRevokedPackagesLiveData.addSource(AutoRevokedPackagesLiveData.INSTANCE, new Observer<Map<Pair<? extends String, ? extends UserHandle>, ? extends Set<? extends String>>>() { // from class: com.android.permissioncontroller.permission.data.UnusedAutoRevokedPackagesLiveData.2
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Map<Pair<? extends String, ? extends UserHandle>, ? extends Set<? extends String>> map) {
                onChanged2((Map<Pair<String, UserHandle>, ? extends Set<String>>) map);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(Map<Pair<String, UserHandle>, ? extends Set<String>> map) {
                UnusedAutoRevokedPackagesLiveData.INSTANCE.updateIfActive();
            }
        });
    }

    private UnusedAutoRevokedPackagesLiveData() {
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
    protected void onUpdate() {
        if (!usageStatsLiveData.getIsInitialized() || !AutoRevokedPackagesLiveData.INSTANCE.getIsInitialized()) {
            return;
        }
        Map<Pair<? extends String, ? extends UserHandle>, ? extends Set<? extends String>> value = AutoRevokedPackagesLiveData.INSTANCE.getValue();
        if (value == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(value, "AutoRevokedPackagesLiveData.value!!");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<Pair<? extends String, ? extends UserHandle>, ? extends Set<? extends String>> entry : value.entrySet()) {
            linkedHashMap.put(entry.getKey(), CollectionsKt.toSet(entry.getValue()));
        }
        long now = System.currentTimeMillis();
        Map<UserHandle, ? extends List<? extends UsageStats>> value2 = usageStatsLiveData.getValue();
        if (value2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(value2, "usageStatsLiveData.value!!");
        for (Map.Entry<UserHandle, ? extends List<? extends UsageStats>> entry2 : value2.entrySet()) {
            UserHandle user = entry2.getKey();
            for (UsageStats stat : entry2.getValue()) {
                Pair userPackage = TuplesKt.to(stat.getPackageName(), user);
                if (value.containsKey(userPackage) && now - stat.getLastTimeVisible() < unusedThreshold) {
                    linkedHashMap.remove(userPackage);
                }
            }
        }
        setValue(linkedHashMap);
    }
}
