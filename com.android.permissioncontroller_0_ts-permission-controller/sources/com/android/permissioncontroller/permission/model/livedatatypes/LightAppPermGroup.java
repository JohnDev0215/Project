package com.android.permissioncontroller.permission.model.livedatatypes;

import android.os.UserHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LightAppPermGroup.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b$\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001MB+\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\u0010\nB;\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0011J\t\u0010A\u001a\u00020\u0003HÆ\u0003J\t\u0010B\u001a\u00020\u0005HÆ\u0003J\u0015\u0010C\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007HÆ\u0003J\t\u0010D\u001a\u00020\u000fHÆ\u0003J\u0010\u0010E\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u00109JN\u0010F\u001a\u00020\u00002\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00052\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000fHÆ\u0001¢\u0006\u0002\u0010GJ\u0013\u0010H\u001a\u00020\u000f2\b\u0010I\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010J\u001a\u00020KHÖ\u0001J\t\u0010L\u001a\u00020\bHÖ\u0001R\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\b0\u00198F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001bR\u0011\u0010 \u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\"R\u0011\u0010$\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\"R\u0011\u0010&\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\"R\u0011\u0010'\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\"R\u0011\u0010(\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\"R\u0011\u0010)\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\"R\u0011\u0010*\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\"R\u0011\u0010+\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\"R\u0011\u0010,\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\"R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0011\u0010/\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0011\u00104\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b5\u00101R\u001d\u00106\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u0013R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u000f¢\u0006\n\n\u0002\u0010:\u001a\u0004\b8\u00109R\u0011\u0010;\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\"R\u0011\u0010=\u001a\u00020>¢\u0006\b\n\u0000\u001a\u0004\b?\u0010@¨\u0006N"}, d2 = {"Lcom/android/permissioncontroller/permission/model/livedatatypes/LightAppPermGroup;", "", "pI", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;", "pGI", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermGroupInfo;", "perms", "", "", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermission;", "(Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermGroupInfo;Ljava/util/Map;)V", "packageInfo", "permGroupInfo", "allPermissions", "hasInstallToRuntimeSplit", "", "specialLocationGrant", "(Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermGroupInfo;Ljava/util/Map;ZLjava/lang/Boolean;)V", "getAllPermissions", "()Ljava/util/Map;", "background", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightAppPermGroup$AppPermSubGroup;", "getBackground", "()Lcom/android/permissioncontroller/permission/model/livedatatypes/LightAppPermGroup$AppPermSubGroup;", "backgroundPermNames", "", "getBackgroundPermNames", "()Ljava/util/List;", "foreground", "getForeground", "foregroundPermNames", "getForegroundPermNames", "hasBackgroundGroup", "getHasBackgroundGroup", "()Z", "getHasInstallToRuntimeSplit", "hasPermWithBackgroundMode", "getHasPermWithBackgroundMode", "isBackgroundFixed", "isForegroundFixed", "isGrantedByDefault", "isGrantedByRole", "isOneTime", "isPolicyFullyFixed", "isUserSensitive", "getPackageInfo", "()Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;", "packageName", "getPackageName", "()Ljava/lang/String;", "getPermGroupInfo", "()Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermGroupInfo;", "permGroupName", "getPermGroupName", "permissions", "getPermissions", "getSpecialLocationGrant", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "supportsRuntimePerms", "getSupportsRuntimePerms", "userHandle", "Landroid/os/UserHandle;", "getUserHandle", "()Landroid/os/UserHandle;", "component1", "component2", "component3", "component4", "component5", "copy", "(Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermGroupInfo;Ljava/util/Map;ZLjava/lang/Boolean;)Lcom/android/permissioncontroller/permission/model/livedatatypes/LightAppPermGroup;", "equals", "other", "hashCode", "", "toString", "AppPermSubGroup", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final /* data */ class LightAppPermGroup {
    private final Map<String, LightPermission> allPermissions;
    private final AppPermSubGroup background;
    private final List<String> backgroundPermNames;
    private final AppPermSubGroup foreground;
    private final boolean hasBackgroundGroup;
    private final boolean hasInstallToRuntimeSplit;
    private final boolean hasPermWithBackgroundMode;
    private final boolean isBackgroundFixed;
    private final boolean isForegroundFixed;
    private final boolean isGrantedByDefault;
    private final boolean isGrantedByRole;
    private final boolean isOneTime;
    private final boolean isPolicyFullyFixed;
    private final boolean isUserSensitive;
    private final LightPackageInfo packageInfo;
    private final String packageName;
    private final LightPermGroupInfo permGroupInfo;
    private final String permGroupName;
    private final Map<String, LightPermission> permissions;
    private final Boolean specialLocationGrant;
    private final boolean supportsRuntimePerms;
    private final UserHandle userHandle;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LightAppPermGroup copy$default(LightAppPermGroup lightAppPermGroup, LightPackageInfo lightPackageInfo, LightPermGroupInfo lightPermGroupInfo, Map map, boolean z, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            lightPackageInfo = lightAppPermGroup.packageInfo;
        }
        if ((i & 2) != 0) {
            lightPermGroupInfo = lightAppPermGroup.permGroupInfo;
        }
        LightPermGroupInfo lightPermGroupInfo2 = lightPermGroupInfo;
        if ((i & 4) != 0) {
            map = lightAppPermGroup.allPermissions;
        }
        Map map2 = map;
        if ((i & 8) != 0) {
            z = lightAppPermGroup.hasInstallToRuntimeSplit;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            bool = lightAppPermGroup.specialLocationGrant;
        }
        return lightAppPermGroup.copy(lightPackageInfo, lightPermGroupInfo2, map2, z2, bool);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final LightPackageInfo getPackageInfo() {
        return this.packageInfo;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final LightPermGroupInfo getPermGroupInfo() {
        return this.permGroupInfo;
    }

    public final Map<String, LightPermission> component3() {
        return this.allPermissions;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getHasInstallToRuntimeSplit() {
        return this.hasInstallToRuntimeSplit;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Boolean getSpecialLocationGrant() {
        return this.specialLocationGrant;
    }

    public final LightAppPermGroup copy(LightPackageInfo packageInfo, LightPermGroupInfo permGroupInfo, Map<String, LightPermission> allPermissions, boolean hasInstallToRuntimeSplit, Boolean specialLocationGrant) {
        Intrinsics.checkParameterIsNotNull(packageInfo, "packageInfo");
        Intrinsics.checkParameterIsNotNull(permGroupInfo, "permGroupInfo");
        Intrinsics.checkParameterIsNotNull(allPermissions, "allPermissions");
        return new LightAppPermGroup(packageInfo, permGroupInfo, allPermissions, hasInstallToRuntimeSplit, specialLocationGrant);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LightAppPermGroup)) {
            return false;
        }
        LightAppPermGroup lightAppPermGroup = (LightAppPermGroup) other;
        return Intrinsics.areEqual(this.packageInfo, lightAppPermGroup.packageInfo) && Intrinsics.areEqual(this.permGroupInfo, lightAppPermGroup.permGroupInfo) && Intrinsics.areEqual(this.allPermissions, lightAppPermGroup.allPermissions) && this.hasInstallToRuntimeSplit == lightAppPermGroup.hasInstallToRuntimeSplit && Intrinsics.areEqual(this.specialLocationGrant, lightAppPermGroup.specialLocationGrant);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v7, types: [int] */
    /* JADX WARN: Type inference failed for: r2v9 */
    public int hashCode() {
        LightPackageInfo lightPackageInfo = this.packageInfo;
        int iHashCode = (lightPackageInfo != null ? lightPackageInfo.hashCode() : 0) * 31;
        LightPermGroupInfo lightPermGroupInfo = this.permGroupInfo;
        int iHashCode2 = (iHashCode + (lightPermGroupInfo != null ? lightPermGroupInfo.hashCode() : 0)) * 31;
        Map<String, LightPermission> map = this.allPermissions;
        int iHashCode3 = (iHashCode2 + (map != null ? map.hashCode() : 0)) * 31;
        boolean z = this.hasInstallToRuntimeSplit;
        ?? r2 = z;
        if (z) {
            r2 = 1;
        }
        int i = (iHashCode3 + r2) * 31;
        Boolean bool = this.specialLocationGrant;
        return i + (bool != null ? bool.hashCode() : 0);
    }

    public String toString() {
        return "LightAppPermGroup(packageInfo=" + this.packageInfo + ", permGroupInfo=" + this.permGroupInfo + ", allPermissions=" + this.allPermissions + ", hasInstallToRuntimeSplit=" + this.hasInstallToRuntimeSplit + ", specialLocationGrant=" + this.specialLocationGrant + ")";
    }

    public LightAppPermGroup(LightPackageInfo packageInfo, LightPermGroupInfo permGroupInfo, Map<String, LightPermission> allPermissions, boolean hasInstallToRuntimeSplit, Boolean specialLocationGrant) {
        boolean z;
        boolean z2;
        boolean z3;
        Intrinsics.checkParameterIsNotNull(packageInfo, "packageInfo");
        Intrinsics.checkParameterIsNotNull(permGroupInfo, "permGroupInfo");
        Intrinsics.checkParameterIsNotNull(allPermissions, "allPermissions");
        this.packageInfo = packageInfo;
        this.permGroupInfo = permGroupInfo;
        this.allPermissions = allPermissions;
        this.hasInstallToRuntimeSplit = hasInstallToRuntimeSplit;
        this.specialLocationGrant = specialLocationGrant;
        Map<String, LightPermission> map = this.allPermissions;
        Map destination$iv$iv = new LinkedHashMap();
        for (Map.Entry<String, LightPermission> entry : map.entrySet()) {
            LightPermission permission = entry.getValue();
            if (!permission.getIsRestricted()) {
                destination$iv$iv.put(entry.getKey(), entry.getValue());
            }
        }
        this.permissions = destination$iv$iv;
        this.packageName = this.packageInfo.getPackageName();
        this.permGroupName = this.permGroupInfo.getName();
        UserHandle userHandleForUid = UserHandle.getUserHandleForUid(this.packageInfo.getUid());
        Intrinsics.checkExpressionValueIsNotNull(userHandleForUid, "UserHandle.getUserHandleForUid(packageInfo.uid)");
        this.userHandle = userHandleForUid;
        Map<String, LightPermission> map2 = this.permissions;
        Collection destination$iv$iv2 = new ArrayList();
        Iterator<Map.Entry<String, LightPermission>> it = map2.entrySet().iterator();
        while (it.hasNext()) {
            String backgroundPermission = it.next().getValue().getBackgroundPermission();
            if (backgroundPermission != null) {
                destination$iv$iv2.add(backgroundPermission);
            }
        }
        this.backgroundPermNames = (List) destination$iv$iv2;
        Map<String, LightPermission> map3 = this.permissions;
        Map destination$iv$iv3 = new LinkedHashMap();
        for (Map.Entry<String, LightPermission> entry2 : map3.entrySet()) {
            if (getForegroundPermNames().contains(entry2.getKey())) {
                destination$iv$iv3.put(entry2.getKey(), entry2.getValue());
            }
        }
        this.foreground = new AppPermSubGroup(destination$iv$iv3, this.specialLocationGrant);
        Map<String, LightPermission> map4 = this.permissions;
        Map destination$iv$iv4 = new LinkedHashMap();
        for (Map.Entry<String, LightPermission> entry3 : map4.entrySet()) {
            if (this.backgroundPermNames.contains(entry3.getKey())) {
                destination$iv$iv4.put(entry3.getKey(), entry3.getValue());
            }
        }
        this.background = new AppPermSubGroup(destination$iv$iv4, this.specialLocationGrant);
        this.hasPermWithBackgroundMode = !this.backgroundPermNames.isEmpty();
        Iterable $this$any$iv = this.backgroundPermNames;
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it2 = $this$any$iv.iterator();
            while (true) {
                if (it2.hasNext()) {
                    Object element$iv = it2.next();
                    String it3 = (String) element$iv;
                    if (this.permissions.containsKey(it3)) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
        } else {
            z = false;
        }
        this.hasBackgroundGroup = z;
        this.isPolicyFullyFixed = this.foreground.getIsPolicyFixed() && (!this.hasBackgroundGroup || this.background.getIsPolicyFixed());
        this.isBackgroundFixed = this.background.getIsPolicyFixed() || this.background.getIsSystemFixed();
        this.isForegroundFixed = this.foreground.getIsPolicyFixed() || this.foreground.getIsSystemFixed();
        this.supportsRuntimePerms = this.packageInfo.getTargetSdkVersion() >= 23;
        Map<String, LightPermission> map5 = this.permissions;
        if (!map5.isEmpty()) {
            Iterator<Map.Entry<String, LightPermission>> it4 = map5.entrySet().iterator();
            while (true) {
                if (it4.hasNext()) {
                    if (it4.next().getValue().getIsOneTime()) {
                        z2 = true;
                        break;
                    }
                } else {
                    z2 = false;
                    break;
                }
            }
        } else {
            z2 = false;
        }
        this.isOneTime = z2;
        this.isGrantedByDefault = this.foreground.getIsGrantedByDefault() || this.background.getIsGrantedByDefault();
        this.isGrantedByRole = this.foreground.getIsGrantedByRole() || this.background.getIsGrantedByRole();
        Map<String, LightPermission> map6 = this.permissions;
        if (!map6.isEmpty()) {
            Iterator<Map.Entry<String, LightPermission>> it5 = map6.entrySet().iterator();
            while (true) {
                if (it5.hasNext()) {
                    if (it5.next().getValue().getIsUserSensitive()) {
                        z3 = true;
                        break;
                    }
                } else {
                    z3 = false;
                    break;
                }
            }
        } else {
            z3 = false;
        }
        this.isUserSensitive = z3;
    }

    public final LightPackageInfo getPackageInfo() {
        return this.packageInfo;
    }

    public final LightPermGroupInfo getPermGroupInfo() {
        return this.permGroupInfo;
    }

    public final Map<String, LightPermission> getAllPermissions() {
        return this.allPermissions;
    }

    public final boolean getHasInstallToRuntimeSplit() {
        return this.hasInstallToRuntimeSplit;
    }

    public final Boolean getSpecialLocationGrant() {
        return this.specialLocationGrant;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LightAppPermGroup(LightPackageInfo pI, LightPermGroupInfo pGI, Map<String, LightPermission> perms) {
        this(pI, pGI, perms, false, null);
        Intrinsics.checkParameterIsNotNull(pI, "pI");
        Intrinsics.checkParameterIsNotNull(pGI, "pGI");
        Intrinsics.checkParameterIsNotNull(perms, "perms");
    }

    public final Map<String, LightPermission> getPermissions() {
        return this.permissions;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getPermGroupName() {
        return this.permGroupName;
    }

    public final UserHandle getUserHandle() {
        return this.userHandle;
    }

    public final List<String> getBackgroundPermNames() {
        return this.backgroundPermNames;
    }

    public final List<String> getForegroundPermNames() {
        Map<String, LightPermission> map = this.permissions;
        Collection destination$iv$iv = new ArrayList();
        Iterator<Map.Entry<String, LightPermission>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            String name = it.next().getKey();
            String str = !this.backgroundPermNames.contains(name) ? name : null;
            if (str != null) {
                destination$iv$iv.add(str);
            }
        }
        return (List) destination$iv$iv;
    }

    public final AppPermSubGroup getForeground() {
        return this.foreground;
    }

    public final AppPermSubGroup getBackground() {
        return this.background;
    }

    public final boolean getHasPermWithBackgroundMode() {
        return this.hasPermWithBackgroundMode;
    }

    public final boolean getHasBackgroundGroup() {
        return this.hasBackgroundGroup;
    }

    /* JADX INFO: renamed from: isPolicyFullyFixed, reason: from getter */
    public final boolean getIsPolicyFullyFixed() {
        return this.isPolicyFullyFixed;
    }

    /* JADX INFO: renamed from: isBackgroundFixed, reason: from getter */
    public final boolean getIsBackgroundFixed() {
        return this.isBackgroundFixed;
    }

    /* JADX INFO: renamed from: isForegroundFixed, reason: from getter */
    public final boolean getIsForegroundFixed() {
        return this.isForegroundFixed;
    }

    public final boolean getSupportsRuntimePerms() {
        return this.supportsRuntimePerms;
    }

    /* JADX INFO: renamed from: isOneTime, reason: from getter */
    public final boolean getIsOneTime() {
        return this.isOneTime;
    }

    /* JADX INFO: renamed from: isGrantedByDefault, reason: from getter */
    public final boolean getIsGrantedByDefault() {
        return this.isGrantedByDefault;
    }

    /* JADX INFO: renamed from: isGrantedByRole, reason: from getter */
    public final boolean getIsGrantedByRole() {
        return this.isGrantedByRole;
    }

    /* JADX INFO: renamed from: isUserSensitive, reason: from getter */
    public final boolean getIsUserSensitive() {
        return this.isUserSensitive;
    }

    /* JADX INFO: compiled from: LightAppPermGroup.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\b\u0000\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0015\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÂ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÂ\u0003¢\u0006\u0002\u0010\u0014J0\u0010\u0015\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\r\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u000e\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nR\u0011\u0010\u000f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\nR\u0011\u0010\u0010\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\nR\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011¨\u0006\u001c"}, d2 = {"Lcom/android/permissioncontroller/permission/model/livedatatypes/LightAppPermGroup$AppPermSubGroup;", "", "permissions", "", "", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermission;", "specialLocationGrant", "", "(Ljava/util/Map;Ljava/lang/Boolean;)V", "isGranted", "()Z", "isGrantedByDefault", "isGrantedByRole", "isPolicyFixed", "isSystemFixed", "isUserFixed", "isUserSet", "Ljava/lang/Boolean;", "component1", "component2", "()Ljava/lang/Boolean;", "copy", "(Ljava/util/Map;Ljava/lang/Boolean;)Lcom/android/permissioncontroller/permission/model/livedatatypes/LightAppPermGroup$AppPermSubGroup;", "equals", "other", "hashCode", "", "toString", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class AppPermSubGroup {
        private final boolean isGranted;
        private final boolean isGrantedByDefault;
        private final boolean isGrantedByRole;
        private final boolean isPolicyFixed;
        private final boolean isSystemFixed;
        private final boolean isUserFixed;
        private final boolean isUserSet;
        private final Map<String, LightPermission> permissions;
        private final Boolean specialLocationGrant;

        private final Map<String, LightPermission> component1() {
            return this.permissions;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        private final Boolean getSpecialLocationGrant() {
            return this.specialLocationGrant;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ AppPermSubGroup copy$default(AppPermSubGroup appPermSubGroup, Map map, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                map = appPermSubGroup.permissions;
            }
            if ((i & 2) != 0) {
                bool = appPermSubGroup.specialLocationGrant;
            }
            return appPermSubGroup.copy(map, bool);
        }

        public final AppPermSubGroup copy(Map<String, LightPermission> permissions, Boolean specialLocationGrant) {
            Intrinsics.checkParameterIsNotNull(permissions, "permissions");
            return new AppPermSubGroup(permissions, specialLocationGrant);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AppPermSubGroup)) {
                return false;
            }
            AppPermSubGroup appPermSubGroup = (AppPermSubGroup) other;
            return Intrinsics.areEqual(this.permissions, appPermSubGroup.permissions) && Intrinsics.areEqual(this.specialLocationGrant, appPermSubGroup.specialLocationGrant);
        }

        public int hashCode() {
            Map<String, LightPermission> map = this.permissions;
            int iHashCode = (map != null ? map.hashCode() : 0) * 31;
            Boolean bool = this.specialLocationGrant;
            return iHashCode + (bool != null ? bool.hashCode() : 0);
        }

        public String toString() {
            return "AppPermSubGroup(permissions=" + this.permissions + ", specialLocationGrant=" + this.specialLocationGrant + ")";
        }

        public AppPermSubGroup(Map<String, LightPermission> permissions, Boolean specialLocationGrant) {
            boolean zBooleanValue;
            boolean z;
            boolean z2;
            boolean z3;
            boolean z4;
            boolean z5;
            Intrinsics.checkParameterIsNotNull(permissions, "permissions");
            this.permissions = permissions;
            this.specialLocationGrant = specialLocationGrant;
            boolean z6 = true;
            if (specialLocationGrant == null) {
                Map<String, LightPermission> map = this.permissions;
                if (!map.isEmpty()) {
                    Iterator<Map.Entry<String, LightPermission>> it = map.entrySet().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (it.next().getValue().isGrantedIncludingAppOp()) {
                                zBooleanValue = true;
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
                zBooleanValue = specialLocationGrant.booleanValue();
            }
            this.isGranted = zBooleanValue;
            Map<String, LightPermission> map2 = this.permissions;
            if (!map2.isEmpty()) {
                Iterator<Map.Entry<String, LightPermission>> it2 = map2.entrySet().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (it2.next().getValue().getIsGrantedByDefault()) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
            } else {
                z = false;
            }
            this.isGrantedByDefault = z;
            Map<String, LightPermission> map3 = this.permissions;
            if (!map3.isEmpty()) {
                Iterator<Map.Entry<String, LightPermission>> it3 = map3.entrySet().iterator();
                while (true) {
                    if (it3.hasNext()) {
                        if (it3.next().getValue().getIsPolicyFixed()) {
                            z2 = true;
                            break;
                        }
                    } else {
                        z2 = false;
                        break;
                    }
                }
            } else {
                z2 = false;
            }
            this.isPolicyFixed = z2;
            Map<String, LightPermission> map4 = this.permissions;
            if (!map4.isEmpty()) {
                Iterator<Map.Entry<String, LightPermission>> it4 = map4.entrySet().iterator();
                while (true) {
                    if (it4.hasNext()) {
                        if (it4.next().getValue().getIsSystemFixed()) {
                            z3 = true;
                            break;
                        }
                    } else {
                        z3 = false;
                        break;
                    }
                }
            } else {
                z3 = false;
            }
            this.isSystemFixed = z3;
            Map<String, LightPermission> map5 = this.permissions;
            if (!map5.isEmpty()) {
                Iterator<Map.Entry<String, LightPermission>> it5 = map5.entrySet().iterator();
                while (true) {
                    if (it5.hasNext()) {
                        if (it5.next().getValue().getIsUserFixed()) {
                            z4 = true;
                            break;
                        }
                    } else {
                        z4 = false;
                        break;
                    }
                }
            } else {
                z4 = false;
            }
            this.isUserFixed = z4;
            Map<String, LightPermission> map6 = this.permissions;
            if (!map6.isEmpty()) {
                Iterator<Map.Entry<String, LightPermission>> it6 = map6.entrySet().iterator();
                while (true) {
                    if (it6.hasNext()) {
                        if (it6.next().getValue().getIsUserSet()) {
                            z5 = true;
                            break;
                        }
                    } else {
                        z5 = false;
                        break;
                    }
                }
            } else {
                z5 = false;
            }
            this.isUserSet = z5;
            Map<String, LightPermission> map7 = this.permissions;
            if (!map7.isEmpty()) {
                Iterator<Map.Entry<String, LightPermission>> it7 = map7.entrySet().iterator();
                while (true) {
                    if (it7.hasNext()) {
                        if (it7.next().getValue().getIsGrantedByRole()) {
                            break;
                        }
                    } else {
                        z6 = false;
                        break;
                    }
                }
            } else {
                z6 = false;
            }
            this.isGrantedByRole = z6;
        }

        /* JADX INFO: renamed from: isGranted, reason: from getter */
        public final boolean getIsGranted() {
            return this.isGranted;
        }

        /* JADX INFO: renamed from: isGrantedByDefault, reason: from getter */
        public final boolean getIsGrantedByDefault() {
            return this.isGrantedByDefault;
        }

        /* JADX INFO: renamed from: isPolicyFixed, reason: from getter */
        public final boolean getIsPolicyFixed() {
            return this.isPolicyFixed;
        }

        /* JADX INFO: renamed from: isSystemFixed, reason: from getter */
        public final boolean getIsSystemFixed() {
            return this.isSystemFixed;
        }

        /* JADX INFO: renamed from: isUserFixed, reason: from getter */
        public final boolean getIsUserFixed() {
            return this.isUserFixed;
        }

        /* JADX INFO: renamed from: isUserSet, reason: from getter */
        public final boolean getIsUserSet() {
            return this.isUserSet;
        }

        /* JADX INFO: renamed from: isGrantedByRole, reason: from getter */
        public final boolean getIsGrantedByRole() {
            return this.isGrantedByRole;
        }
    }
}
