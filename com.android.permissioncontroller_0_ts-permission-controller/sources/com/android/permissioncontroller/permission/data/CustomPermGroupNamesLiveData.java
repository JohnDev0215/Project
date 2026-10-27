package com.android.permissioncontroller.permission.data;

import android.app.Application;
import android.os.UserHandle;
import android.util.Log;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPermInfo;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomPermGroupNamesLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/android/permissioncontroller/permission/data/CustomPermGroupNamesLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "", "", "()V", "app", "Landroid/app/Application;", "packagesLiveData", "Lcom/android/permissioncontroller/permission/data/AllPackageInfosLiveData;", "onUpdate", "", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class CustomPermGroupNamesLiveData extends SmartUpdateMediatorLiveData<List<? extends String>> {
    public static final CustomPermGroupNamesLiveData INSTANCE;
    private static final Application app;
    private static final AllPackageInfosLiveData packagesLiveData;

    static {
        CustomPermGroupNamesLiveData customPermGroupNamesLiveData = new CustomPermGroupNamesLiveData();
        INSTANCE = customPermGroupNamesLiveData;
        PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
        Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
        app = permissionControllerApplication;
        packagesLiveData = AllPackageInfosLiveData.INSTANCE;
        Log.i("CustomPermGroupNames", "observing UserPackageInfoLiveData for all users in CustomPermGroupNamesLiveData");
        customPermGroupNamesLiveData.addSource(packagesLiveData, new Observer<Map<UserHandle, ? extends List<? extends LightPackageInfo>>>() { // from class: com.android.permissioncontroller.permission.data.CustomPermGroupNamesLiveData.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Map<UserHandle, ? extends List<? extends LightPackageInfo>> map) {
                onChanged2((Map<UserHandle, ? extends List<LightPackageInfo>>) map);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(Map<UserHandle, ? extends List<LightPackageInfo>> map) {
                CustomPermGroupNamesLiveData.INSTANCE.updateIfActive();
            }
        });
    }

    private CustomPermGroupNamesLiveData() {
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
    protected void onUpdate() {
        List<String> platformPermissionGroups = Utils.getPlatformPermissionGroups();
        ArrayList arrayList = new ArrayList();
        Map<UserHandle, ? extends List<? extends LightPackageInfo>> value = packagesLiveData.getValue();
        if (value == null) {
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(value, "packagesLiveData.value ?: return");
        Iterator<Map.Entry<UserHandle, ? extends List<? extends LightPackageInfo>>> it = value.entrySet().iterator();
        while (it.hasNext()) {
            for (LightPackageInfo packageInfo : it.next().getValue()) {
                for (LightPermInfo permission : packageInfo.getPermissions()) {
                    if (permission.getProtection() == 1 && (permission.getFlags() & BasicMeasure.EXACTLY) != 0 && !CollectionsKt.contains(arrayList, permission.getGroup()) && !platformPermissionGroups.contains(permission.getGroup()) && !arrayList.contains(permission.getName())) {
                        if (permission.getGroup() != null) {
                            arrayList.add(permission.getGroup());
                        } else {
                            arrayList.add(permission.getName());
                        }
                    }
                }
            }
        }
        setValue(arrayList);
    }
}
