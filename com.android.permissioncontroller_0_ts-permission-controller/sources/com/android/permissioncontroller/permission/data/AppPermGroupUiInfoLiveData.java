package com.android.permissioncontroller.permission.data;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import androidx.lifecycle.Observer;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.permission.model.livedatatypes.AppPermGroupUiInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPermGroupInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.LightPermInfo;
import com.android.permissioncontroller.permission.model.livedatatypes.PermGroup;
import com.android.permissioncontroller.permission.model.livedatatypes.PermState;
import com.android.permissioncontroller.permission.utils.LocationUtils;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppPermGroupUiInfoLiveData.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000 -2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001-B'\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ@\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001b0\u001a2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001d0\u001aH\u0002J0\u0010\u001e\u001a\u00020\u001f2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001d0\u001a2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001b0\u001aH\u0002J\u000f\u0010 \u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0002\u0010!J&\u0010\"\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001b0$H\u0002J\u001c\u0010%\u001a\u00020\r2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001d0\u001aH\u0002J\b\u0010&\u001a\u00020'H\u0014J\b\u0010(\u001a\u00020'H\u0014J\u0010\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020\rH\u0016J\b\u0010+\u001a\u00020'H\u0014J\b\u0010,\u001a\u00020\rH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/android/permissioncontroller/permission/data/AppPermGroupUiInfoLiveData;", "Lcom/android/permissioncontroller/permission/data/SmartUpdateMediatorLiveData;", "Lcom/android/permissioncontroller/permission/model/livedatatypes/AppPermGroupUiInfo;", "Lcom/android/permissioncontroller/permission/utils/LocationUtils$LocationListener;", "app", "Landroid/app/Application;", "packageName", "", "permGroupName", "user", "Landroid/os/UserHandle;", "(Landroid/app/Application;Ljava/lang/String;Ljava/lang/String;Landroid/os/UserHandle;)V", "isSpecialLocation", "", "packageInfoLiveData", "Lcom/android/permissioncontroller/permission/data/LightPackageInfoLiveData;", "permGroupLiveData", "Lcom/android/permissioncontroller/permission/data/PermGroupLiveData;", "permissionStateLiveData", "Lcom/android/permissioncontroller/permission/data/PermStateLiveData;", "getAppPermGroupUiInfo", "packageInfo", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;", "groupInfo", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermGroupInfo;", "allPermInfos", "", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermInfo;", "permissionState", "Lcom/android/permissioncontroller/permission/model/livedatatypes/PermState;", "getGrantedIncludingBackground", "Lcom/android/permissioncontroller/permission/model/livedatatypes/AppPermGroupUiInfo$PermGrantState;", "getIsSpecialLocationState", "()Ljava/lang/Boolean;", "isGrantableAndNotLegacyPlatform", "permissionInfos", "", "isUserSensitive", "onActive", "", "onInactive", "onLocationStateChange", "enabled", "onUpdate", "shouldShowAsForegroundGroup", "Companion", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class AppPermGroupUiInfoLiveData extends SmartUpdateMediatorLiveData<AppPermGroupUiInfo> implements LocationUtils.LocationListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Application app;
    private boolean isSpecialLocation;
    private final LightPackageInfoLiveData packageInfoLiveData;
    private final String packageName;
    private final PermGroupLiveData permGroupLiveData;
    private final String permGroupName;
    private final PermStateLiveData permissionStateLiveData;
    private final UserHandle user;

    private AppPermGroupUiInfoLiveData(Application app, String packageName, String permGroupName, UserHandle user) {
        this.app = app;
        this.packageName = packageName;
        this.permGroupName = permGroupName;
        this.user = user;
        this.packageInfoLiveData = (LightPackageInfoLiveData) DataRepositoryKt.get(LightPackageInfoLiveData.INSTANCE, this.packageName, this.user);
        this.permGroupLiveData = PermGroupLiveData.INSTANCE.get(this.permGroupName);
        this.permissionStateLiveData = (PermStateLiveData) DataRepositoryKt.get(PermStateLiveData.INSTANCE, this.packageName, this.permGroupName, this.user);
        this.isSpecialLocation = LocationUtils.isLocationGroupAndProvider(this.app, this.permGroupName, this.packageName) || LocationUtils.isLocationGroupAndControllerExtraPackage(this.app, this.permGroupName, this.packageName);
        addSource(this.packageInfoLiveData, new Observer<LightPackageInfo>() { // from class: com.android.permissioncontroller.permission.data.AppPermGroupUiInfoLiveData.1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(LightPackageInfo it) {
                AppPermGroupUiInfoLiveData.this.updateIfActive();
            }
        });
        addSource(this.permGroupLiveData, new Observer<PermGroup>() { // from class: com.android.permissioncontroller.permission.data.AppPermGroupUiInfoLiveData.2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(PermGroup it) {
                AppPermGroupUiInfoLiveData.this.updateIfActive();
            }
        });
        addSource(this.permissionStateLiveData, new Observer<Map<String, ? extends PermState>>() { // from class: com.android.permissioncontroller.permission.data.AppPermGroupUiInfoLiveData.3
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Map<String, ? extends PermState> map) {
                onChanged2((Map<String, PermState>) map);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(Map<String, PermState> map) {
                AppPermGroupUiInfoLiveData.this.updateIfActive();
            }
        });
    }

    public /* synthetic */ AppPermGroupUiInfoLiveData(Application app, String packageName, String permGroupName, UserHandle user, DefaultConstructorMarker $constructor_marker) {
        this(app, packageName, permGroupName, user);
    }

    @Override // com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData
    protected void onUpdate() {
        LightPackageInfo packageInfo = this.packageInfoLiveData.getValue();
        PermGroup permissionGroup = this.permGroupLiveData.getValue();
        Map<String, PermState> map = (Map) this.permissionStateLiveData.getValue();
        if (packageInfo == null || permissionGroup == null || map == null) {
            if (this.packageInfoLiveData.getIsInitialized() && this.permGroupLiveData.getIsInitialized() && this.permissionStateLiveData.getIsInitialized()) {
                INSTANCE.invalidateSingle(new Triple(this.packageName, this.permGroupName, this.user));
                setValue(null);
                return;
            }
            return;
        }
        setValue(getAppPermGroupUiInfo(packageInfo, permissionGroup.getGroupInfo(), permissionGroup.getPermissionInfos(), map));
    }

    private final AppPermGroupUiInfo getAppPermGroupUiInfo(LightPackageInfo packageInfo, LightPermGroupInfo groupInfo, Map<String, LightPermInfo> allPermInfos, Map<String, PermState> permissionState) throws PackageManager.NameNotFoundException {
        Map destination$iv$iv = new LinkedHashMap();
        for (Map.Entry<String, LightPermInfo> entry : allPermInfos.entrySet()) {
            if (permissionState.containsKey(entry.getKey())) {
                destination$iv$iv.put(entry.getKey(), entry.getValue());
            }
        }
        boolean shouldShow = packageInfo.getEnabled() && isGrantableAndNotLegacyPlatform(packageInfo, groupInfo, destination$iv$iv.values());
        boolean isSystemApp = true ^ isUserSensitive(permissionState);
        AppPermGroupUiInfo.PermGrantState isGranted = getGrantedIncludingBackground(permissionState, allPermInfos);
        return new AppPermGroupUiInfo(shouldShow, isGranted, isSystemApp);
    }

    private final boolean isGrantableAndNotLegacyPlatform(LightPackageInfo packageInfo, LightPermGroupInfo groupInfo, Collection<LightPermInfo> permissionInfos) {
        boolean hasPreRuntime = false;
        Iterator<LightPermInfo> it = permissionInfos.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            LightPermInfo permissionInfo = it.next();
            if ((permissionInfo.getProtectionFlags() & 8192) == 0) {
                hasPreRuntime = true;
                break;
            }
        }
        boolean isGrantingAllowed = !packageInfo.isInstantApp() && (packageInfo.getTargetSdkVersion() >= 23 || hasPreRuntime);
        if (isGrantingAllowed) {
            return !Intrinsics.areEqual(groupInfo.getPackageName(), Utils.OS_PKG) || Utils.isModernPermissionGroup(groupInfo.getName());
        }
        return false;
    }

    private final boolean isUserSensitive(Map<String, PermState> permissionState) {
        if (!Utils.isModernPermissionGroup(this.permGroupName)) {
            return true;
        }
        for (String permissionName : permissionState.keySet()) {
            PermState permState = permissionState.get(permissionName);
            if (permState == null) {
                return true;
            }
            int flags = permState.getPermFlags();
            PermState permState2 = permissionState.get(permissionName);
            if (permState2 == null) {
                return true;
            }
            boolean granted = permState2.getGranted();
            if ((granted && (flags & 256) != 0) || (!granted && (flags & 512) != 0)) {
                return true;
            }
        }
        return false;
    }

    private final AppPermGroupUiInfo.PermGrantState getGrantedIncludingBackground(Map<String, PermState> permissionState, Map<String, LightPermInfo> allPermInfos) throws PackageManager.NameNotFoundException {
        Boolean specialLocationState = getIsSpecialLocationState();
        boolean hasPermWithBackground = false;
        boolean isUserFixed = false;
        boolean isOneTime = false;
        Iterator<Map.Entry<String, PermState>> it = permissionState.entrySet().iterator();
        while (true) {
            boolean zBooleanValue = true;
            if (it.hasNext()) {
                Map.Entry<String, PermState> next = it.next();
                String permName = next.getKey();
                PermState permState = next.getValue();
                LightPermInfo permInfo = allPermInfos.get(permName);
                if (permInfo != null) {
                    String backgroundPerm = permInfo.getBackgroundPermission();
                    if (backgroundPerm != null) {
                        hasPermWithBackground = true;
                        PermState permState2 = permissionState.get(backgroundPerm);
                        if (permState2 != null && permState2.getGranted() && (!Intrinsics.areEqual((Object) specialLocationState, (Object) false))) {
                            return AppPermGroupUiInfo.PermGrantState.PERMS_ALLOWED_ALWAYS;
                        }
                    }
                    isUserFixed = isUserFixed || (permState.getPermFlags() & 2) != 0;
                    if (!isOneTime && (permState.getPermFlags() & 65536) == 0) {
                        zBooleanValue = false;
                    }
                    isOneTime = zBooleanValue;
                }
            } else {
                if (specialLocationState == null) {
                    if (!permissionState.isEmpty()) {
                        Iterator<Map.Entry<String, PermState>> it2 = permissionState.entrySet().iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                if (it2.next().getValue().getGranted()) {
                                    break;
                                }
                            } else {
                                zBooleanValue = false;
                                break;
                            }
                        }
                    } else {
                        zBooleanValue = false;
                    }
                } else {
                    zBooleanValue = specialLocationState.booleanValue();
                }
                boolean anyAllowed = zBooleanValue;
                if (anyAllowed && (hasPermWithBackground || shouldShowAsForegroundGroup())) {
                    if (isOneTime) {
                        return AppPermGroupUiInfo.PermGrantState.PERMS_ASK;
                    }
                    if (Utils.couldHaveForegroundCapabilities(Utils.getUserContext(this.app, this.user), this.packageName) || Utils.isEmergencyApp(Utils.getUserContext(this.app, this.user), this.packageName)) {
                        return AppPermGroupUiInfo.PermGrantState.PERMS_ALLOWED_ALWAYS;
                    }
                    return AppPermGroupUiInfo.PermGrantState.PERMS_ALLOWED_FOREGROUND_ONLY;
                }
                if (anyAllowed) {
                    if (isOneTime) {
                        return AppPermGroupUiInfo.PermGrantState.PERMS_ASK;
                    }
                    return AppPermGroupUiInfo.PermGrantState.PERMS_ALLOWED;
                }
                if (isUserFixed) {
                    return AppPermGroupUiInfo.PermGrantState.PERMS_DENIED;
                }
                if (isOneTime) {
                    return AppPermGroupUiInfo.PermGrantState.PERMS_ASK;
                }
                return AppPermGroupUiInfo.PermGrantState.PERMS_DENIED;
            }
        }
    }

    private final Boolean getIsSpecialLocationState() throws PackageManager.NameNotFoundException {
        if (!this.isSpecialLocation) {
            return null;
        }
        Context userContext = Utils.getUserContext(this.app, this.user);
        Intrinsics.checkExpressionValueIsNotNull(userContext, "Utils.getUserContext(app, user)");
        if (LocationUtils.isLocationGroupAndProvider(userContext, this.permGroupName, this.packageName)) {
            return Boolean.valueOf(LocationUtils.isLocationEnabled(userContext));
        }
        if (LocationUtils.isLocationGroupAndControllerExtraPackage(userContext, this.permGroupName, this.packageName)) {
            return Boolean.valueOf(LocationUtils.isExtraLocationControllerPackageEnabled(userContext));
        }
        return null;
    }

    private final boolean shouldShowAsForegroundGroup() {
        return this.permGroupName.equals("android.permission-group.CAMERA") || this.permGroupName.equals("android.permission-group.MICROPHONE");
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

    /* JADX INFO: compiled from: AppPermGroupUiInfoLiveData.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002 \u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0004\u0012\u00020\u00050\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0006J\"\u0010\u0007\u001a\u00020\u00052\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002H\u0014¨\u0006\t"}, d2 = {"Lcom/android/permissioncontroller/permission/data/AppPermGroupUiInfoLiveData$Companion;", "Lcom/android/permissioncontroller/permission/data/DataRepositoryForPackage;", "Lkotlin/Triple;", "", "Landroid/os/UserHandle;", "Lcom/android/permissioncontroller/permission/data/AppPermGroupUiInfoLiveData;", "()V", "newValue", "key", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class Companion extends DataRepositoryForPackage<Triple<? extends String, ? extends String, ? extends UserHandle>, AppPermGroupUiInfoLiveData> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.permissioncontroller.permission.data.DataRepository
        public AppPermGroupUiInfoLiveData newValue(Triple<String, String, UserHandle> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            PermissionControllerApplication permissionControllerApplication = PermissionControllerApplication.get();
            Intrinsics.checkExpressionValueIsNotNull(permissionControllerApplication, "PermissionControllerApplication.get()");
            return new AppPermGroupUiInfoLiveData(permissionControllerApplication, key.getFirst(), key.getSecond(), key.getThird(), null);
        }
    }
}
