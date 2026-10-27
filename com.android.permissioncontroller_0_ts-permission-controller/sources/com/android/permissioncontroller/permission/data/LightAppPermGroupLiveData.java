package com.android.permissioncontroller.permission.data;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.UserHandle;
import android.permission.PermissionManager;
import android.util.Log;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.permission.model.livedatatypes.LightAppPermGroup;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPermInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPermission;
import com.android.permissioncontroller.permission.model.livedatatypes.PermGroup;
import com.android.permissioncontroller.permission.model.livedatatypes.PermState;
import com.android.permissioncontroller.permission.utils.LocationUtils;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LightAppPermGroupLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 &2\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\u00020\u0003:\u0001&B'\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ$\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001c2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001f0\u001eH\u0002J\b\u0010 \u001a\u00020!H\u0014J\b\u0010\"\u001a\u00020!H\u0014J\u0010\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020\u0013H\u0016J\b\u0010%\u001a\u00020!H\u0014R\u0019\u0010\f\u001a\n \r*\u0004\u0018\u00010\u00070\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/android/permissioncontroller/permission/data/LightAppPermGroupLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightAppPermGroup;", "Lcom/android/permissioncontroller/permission/utils/LocationUtils$LocationListener;", "app", "Landroid/app/Application;", "packageName", "", "permGroupName", "user", "Landroid/os/UserHandle;", "(Landroid/app/Application;Ljava/lang/String;Ljava/lang/String;Landroid/os/UserHandle;)V", "LOG_TAG", "kotlin.jvm.PlatformType", "getLOG_TAG", "()Ljava/lang/String;", "fgPermNamesLiveData", "Lcom/android/permissioncontroller/permission/data/ForegroundPermNamesLiveData;", "isSpecialLocation", "", "packageInfoLiveData", "Lcom/android/permissioncontroller/permission/data/LightPackageInfoLiveData;", "permGroupLiveData", "Lcom/android/permissioncontroller/permission/data/PermGroupLiveData;", "permStateLiveData", "Lcom/android/permissioncontroller/permission/data/PermStateLiveData;", "hasInstallToRuntimeSplit", "packageInfo", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;", "permissionMap", "", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermission;", "onActive", "", "onInactive", "onLocationStateChange", "enabled", "onUpdate", "Companion", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class LightAppPermGroupLiveData extends SmartUpdateMediatorLiveData<LightAppPermGroup> implements LocationUtils.LocationListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String LOG_TAG;
    private final Application app;
    private final ForegroundPermNamesLiveData fgPermNamesLiveData;
    private boolean isSpecialLocation;
    private final LightPackageInfoLiveData packageInfoLiveData;
    private final String packageName;
    private final PermGroupLiveData permGroupLiveData;
    private final String permGroupName;
    private final PermStateLiveData permStateLiveData;
    private final UserHandle user;

    private LightAppPermGroupLiveData(Application app, String packageName, String permGroupName, UserHandle user) {
        this.app = app;
        this.packageName = packageName;
        this.permGroupName = permGroupName;
        this.user = user;
        this.LOG_TAG = getClass().getSimpleName();
        this.permStateLiveData = (PermStateLiveData) DataRepositoryKt.get(PermStateLiveData.INSTANCE, this.packageName, this.permGroupName, this.user);
        this.permGroupLiveData = PermGroupLiveData.INSTANCE.get(this.permGroupName);
        this.packageInfoLiveData = (LightPackageInfoLiveData) DataRepositoryKt.get(LightPackageInfoLiveData.INSTANCE, this.packageName, this.user);
        this.fgPermNamesLiveData = ForegroundPermNamesLiveData.INSTANCE;
        this.isSpecialLocation = LocationUtils.isLocationGroupAndProvider(this.app, this.permGroupName, this.packageName) || LocationUtils.isLocationGroupAndControllerExtraPackage(this.app, this.permGroupName, this.packageName);
        addSource(this.fgPermNamesLiveData, new Observer<Map<String, ? extends List<? extends String>>>() { // from class: com.android.permissioncontroller.permission.data.LightAppPermGroupLiveData.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Map<String, ? extends List<? extends String>> map) {
                onChanged2((Map<String, ? extends List<String>>) map);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(Map<String, ? extends List<String>> map) {
                LightAppPermGroupLiveData.this.updateIfActive();
            }
        });
        final Triple key = new Triple(this.packageName, this.permGroupName, this.user);
        addSource(this.permStateLiveData, new Observer<Map<String, ? extends PermState>>() { // from class: com.android.permissioncontroller.permission.data.LightAppPermGroupLiveData.2
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Map<String, ? extends PermState> map) {
                onChanged2((Map<String, PermState>) map);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(Map<String, PermState> map) {
                if (map == null && LightAppPermGroupLiveData.this.permStateLiveData.getIsInitialized()) {
                    LightAppPermGroupLiveData.INSTANCE.invalidateSingle(key);
                    LightAppPermGroupLiveData.this.setValue(null);
                } else {
                    LightAppPermGroupLiveData.this.updateIfActive();
                }
            }
        });
        addSource(this.permGroupLiveData, new Observer<PermGroup>() { // from class: com.android.permissioncontroller.permission.data.LightAppPermGroupLiveData.3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(PermGroup permGroup) {
                if (permGroup == null && LightAppPermGroupLiveData.this.permGroupLiveData.getIsInitialized()) {
                    LightAppPermGroupLiveData.INSTANCE.invalidateSingle(key);
                    LightAppPermGroupLiveData.this.setValue(null);
                } else {
                    LightAppPermGroupLiveData.this.updateIfActive();
                }
            }
        });
        addSource(this.packageInfoLiveData, new Observer<LightPackageInfo>() { // from class: com.android.permissioncontroller.permission.data.LightAppPermGroupLiveData.4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(LightPackageInfo packageInfo) {
                if (packageInfo == null && LightAppPermGroupLiveData.this.packageInfoLiveData.getIsInitialized()) {
                    LightAppPermGroupLiveData.INSTANCE.invalidateSingle(key);
                    LightAppPermGroupLiveData.this.setValue(null);
                } else {
                    LightAppPermGroupLiveData.this.updateIfActive();
                }
            }
        });
    }

    public /* synthetic */ LightAppPermGroupLiveData(Application app, String packageName, String permGroupName, UserHandle user, DefaultConstructorMarker $constructor_marker) {
        this(app, packageName, permGroupName, user);
    }

    public final String getLOG_TAG() {
        return this.LOG_TAG;
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
    protected void onUpdate() throws PackageManager.NameNotFoundException {
        Boolean specialLocationGrant;
        Map<String, ? extends PermState> value = this.permStateLiveData.getValue();
        if (value == null) {
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(value, "permStateLiveData.value ?: return");
        PermGroup permGroup = this.permGroupLiveData.getValue();
        if (permGroup == null) {
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(permGroup, "permGroupLiveData.value ?: return");
        LightPackageInfo packageInfo = this.packageInfoLiveData.getValue();
        if (packageInfo == null) {
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(packageInfo, "packageInfoLiveData.value ?: return");
        Map<String, ? extends List<? extends String>> value2 = this.fgPermNamesLiveData.getValue();
        if (value2 == null) {
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(value2, "fgPermNamesLiveData.value ?: return");
        if (packageInfo.getTargetSdkVersion() < 23 && (!Intrinsics.areEqual(permGroup.getGroupInfo().getPackageName(), Utils.OS_PKG))) {
            setValue(new LightAppPermGroup(packageInfo, permGroup.getGroupInfo(), MapsKt.emptyMap()));
            return;
        }
        Map permissionMap = new LinkedHashMap();
        for (Map.Entry<String, ? extends PermState> entry : value.entrySet()) {
            String permName = entry.getKey();
            PermState permState = entry.getValue();
            LightPermInfo permInfo = permGroup.getPermissionInfos().get(permName);
            if (permInfo != null) {
                permissionMap.put(permName, new LightPermission(packageInfo, permInfo, permState, value2.get(permName)));
            }
        }
        Boolean specialLocationGrant2 = (Boolean) null;
        Context userContext = Utils.getUserContext(this.app, this.user);
        Intrinsics.checkExpressionValueIsNotNull(userContext, "Utils.getUserContext(app, user)");
        if (LocationUtils.isLocationGroupAndProvider(userContext, this.permGroupName, this.packageName)) {
            Boolean specialLocationGrant3 = Boolean.valueOf(LocationUtils.isLocationEnabled(userContext));
            specialLocationGrant = specialLocationGrant3;
        } else if (!LocationUtils.isLocationGroupAndControllerExtraPackage(this.app, this.permGroupName, this.packageName)) {
            specialLocationGrant = specialLocationGrant2;
        } else {
            Boolean specialLocationGrant4 = Boolean.valueOf(LocationUtils.isExtraLocationControllerPackageEnabled(userContext));
            specialLocationGrant = specialLocationGrant4;
        }
        boolean hasInstallToRuntimeSplit = hasInstallToRuntimeSplit(packageInfo, permissionMap);
        setValue(new LightAppPermGroup(packageInfo, permGroup.getGroupInfo(), permissionMap, hasInstallToRuntimeSplit, specialLocationGrant));
    }

    private final boolean hasInstallToRuntimeSplit(LightPackageInfo packageInfo, Map<String, LightPermission> permissionMap) {
        LightPermInfo newPerm;
        PermissionManager permissionManager = (PermissionManager) this.app.getSystemService(PermissionManager.class);
        if (permissionManager == null) {
            return false;
        }
        for (PermissionManager.SplitPermissionInfo spi : permissionManager.getSplitPermissions()) {
            Intrinsics.checkExpressionValueIsNotNull(spi, "spi");
            String splitPerm = spi.getSplitPermission();
            try {
                PermissionInfo pi = this.app.getPackageManager().getPermissionInfo(splitPerm, 0);
                Intrinsics.checkExpressionValueIsNotNull(pi, "pi");
                if (pi.getProtection() != 0) {
                    continue;
                } else {
                    List<String> newPerms = spi.getNewPermissions();
                    for (String permName : newPerms) {
                        LightPermission lightPermission = permissionMap.get(permName);
                        if (lightPermission != null && (newPerm = lightPermission.getPermInfo()) != null && newPerm.getProtection() == 1 && packageInfo.getTargetSdkVersion() < spi.getTargetSdk()) {
                            return true;
                        }
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.w(this.LOG_TAG, "No such permission: " + splitPerm, e);
            }
        }
        return false;
    }

    @Override // com.android.permissioncontroller.permission.utils.LocationUtils.LocationListener
    public void onLocationStateChange(boolean enabled) {
        updateIfActive();
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onActive() {
        super.onActive();
        if (this.isSpecialLocation) {
            LocationUtils.addLocationListener(this);
            updateIfActive();
        }
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData, androidx.lifecycle.MediatorLiveData, androidx.lifecycle.LiveData
    protected void onInactive() {
        super.onInactive();
        if (this.isSpecialLocation) {
            LocationUtils.removeLocationListener(this);
        }
    }

    /* JADX INFO: compiled from: LightAppPermGroupLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002 \u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0004\u0012\u00020\u00050\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0006J\"\u0010\u0007\u001a\u00020\u00052\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002H\u0014¨\u0006\t"}, d2 = {"Lcom/android/permissioncontroller/permission/data/LightAppPermGroupLiveData$Companion;", "Lcom/android/permissioncontroller/permission/data/DataRepositoryForPackage;", "Lkotlin/Triple;", "", "Landroid/os/UserHandle;", "Lcom/android/permissioncontroller/permission/data/LightAppPermGroupLiveData;", "()V", "newValue", "key", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class Companion extends DataRepositoryForPackage<Triple<? extends String, ? extends String, ? extends UserHandle>, LightAppPermGroupLiveData> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.permissioncontroller.permission.data.DataRepository
        public LightAppPermGroupLiveData newValue(Triple<String, String, UserHandle> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
            Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
            return new LightAppPermGroupLiveData(permissionControllerApplication, key.getFirst(), key.getSecond(), key.getThird(), null);
        }
    }
}
