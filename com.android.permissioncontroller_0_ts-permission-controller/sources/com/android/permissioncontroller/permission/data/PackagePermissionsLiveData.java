package com.android.permissioncontroller.permission.data;

import android.app.Application;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.UserHandle;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PackagePermissionsLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0004\u0018\u00010\u00020\u0001:\u0001\u000fB\u001f\b\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\r\u001a\u00020\u000eH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/android/permissioncontroller/permission/data/PackagePermissionsLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "", "", "", "app", "Landroid/app/Application;", "packageName", "user", "Landroid/os/UserHandle;", "(Landroid/app/Application;Ljava/lang/String;Landroid/os/UserHandle;)V", "packageInfoLiveData", "Lcom/android/permissioncontroller/permission/data/LightPackageInfoLiveData;", "onUpdate", "", "Companion", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class PackagePermissionsLiveData extends SmartUpdateMediatorLiveData<Map<String, ? extends List<? extends String>>> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String NON_RUNTIME_NORMAL_PERMS = "nonRuntimeNormalPerms";
    private final Application app;
    private final LightPackageInfoLiveData packageInfoLiveData;

    private PackagePermissionsLiveData(Application app, final String packageName, final UserHandle user) {
        this.app = app;
        LightPackageInfoLiveData lightPackageInfoLiveData = (LightPackageInfoLiveData) DataRepositoryKt.get(LightPackageInfoLiveData.INSTANCE, packageName, user);
        this.packageInfoLiveData = lightPackageInfoLiveData;
        addSource(lightPackageInfoLiveData, new Observer<LightPackageInfo>() { // from class: com.android.permissioncontroller.permission.data.PackagePermissionsLiveData.1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(LightPackageInfo it) {
                if (PackagePermissionsLiveData.this.packageInfoLiveData.getIsInitialized() && PackagePermissionsLiveData.this.packageInfoLiveData.getValue() == null) {
                    PackagePermissionsLiveData.INSTANCE.invalidateSingle(TuplesKt.to(packageName, user));
                    PackagePermissionsLiveData.this.setValue(null);
                } else {
                    PackagePermissionsLiveData.this.updateIfActive();
                }
            }
        });
    }

    public /* synthetic */ PackagePermissionsLiveData(Application app, String packageName, UserHandle user, DefaultConstructorMarker $constructor_marker) {
        this(app, packageName, user);
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
    protected void onUpdate() {
        ArrayList answer$iv;
        LightPackageInfo packageInfo = this.packageInfoLiveData.getValue();
        if (packageInfo == null) {
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(packageInfo, "packageInfoLiveData.value ?: return");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String permName : packageInfo.getRequestedPermissions()) {
            try {
                PermissionInfo permInfo = this.app.getPackageManager().getPermissionInfo(permName, 0);
                if ((permInfo.flags & BasicMeasure.EXACTLY) != 0 && (permInfo.flags & 2) == 0) {
                    if (packageInfo.isInstantApp()) {
                        Intrinsics.checkExpressionValueIsNotNull(permInfo, "permInfo");
                        if ((permInfo.getProtectionFlags() & 4096) == 0) {
                        }
                    }
                    if (packageInfo.getTargetSdkVersion() < 23) {
                        Intrinsics.checkExpressionValueIsNotNull(permInfo, "permInfo");
                        if ((permInfo.getProtectionFlags() & 8192) != 0) {
                        }
                    }
                    Intrinsics.checkExpressionValueIsNotNull(permInfo, "permInfo");
                    if (permInfo.getProtection() != 1) {
                        if (permInfo.getProtection() == 0) {
                            Object value$iv = linkedHashMap.get(NON_RUNTIME_NORMAL_PERMS);
                            if (value$iv == null) {
                                answer$iv = new ArrayList();
                                linkedHashMap.put(NON_RUNTIME_NORMAL_PERMS, answer$iv);
                            } else {
                                answer$iv = value$iv;
                            }
                            Object key$iv = answer$iv;
                            List otherPermsList = (List) key$iv;
                            String str = permInfo.name;
                            Intrinsics.checkExpressionValueIsNotNull(str, "permInfo.name");
                            otherPermsList.add(str);
                        }
                    } else {
                        String groupName = Utils.getGroupOfPermission(permInfo);
                        if (groupName == null) {
                            groupName = permInfo.name;
                        }
                        if (!linkedHashMap.containsKey(groupName)) {
                            Intrinsics.checkExpressionValueIsNotNull(groupName, "groupName");
                            linkedHashMap.put(groupName, new ArrayList());
                        }
                        List list = (List) linkedHashMap.get(groupName);
                        if (list != null) {
                            String str2 = permInfo.name;
                            Intrinsics.checkExpressionValueIsNotNull(str2, "permInfo.name");
                            list.add(str2);
                        }
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
        setValue(linkedHashMap);
    }

    /* JADX INFO: compiled from: PackagePermissionsLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0004\u0012\u00020\u00050\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0006J\u001c\u0010\b\u001a\u00020\u00052\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002H\u0014R\u000e\u0010\u0007\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/android/permissioncontroller/permission/data/PackagePermissionsLiveData$Companion;", "Lcom/android/permissioncontroller/permission/data/DataRepositoryForPackage;", "Lkotlin/Pair;", "", "Landroid/os/UserHandle;", "Lcom/android/permissioncontroller/permission/data/PackagePermissionsLiveData;", "()V", "NON_RUNTIME_NORMAL_PERMS", "newValue", "key", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class Companion extends DataRepositoryForPackage<Pair<? extends String, ? extends UserHandle>, PackagePermissionsLiveData> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.permissioncontroller.permission.data.DataRepository
        public PackagePermissionsLiveData newValue(Pair<String, UserHandle> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
            Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
            return new PackagePermissionsLiveData(permissionControllerApplication, key.getFirst(), key.getSecond(), null);
        }
    }
}
