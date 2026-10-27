package com.android.permissioncontroller.permission.model.livedatatypes;

import com.android.permissioncontroller.permission.utils.SoftRestrictedPermissionPolicy;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LightPermission.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b)\b\u0086\b\u0018\u00002\u00020\u0001B/\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0002\u0010\u000bB5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0002\u0010\u0010J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0005HÆ\u0003J\t\u00100\u001a\u00020\rHÆ\u0003J\t\u00101\u001a\u00020\u000fHÆ\u0003J\u0011\u00102\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0003JC\u00103\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0001J\u0013\u00104\u001a\u00020\r2\b\u00105\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00106\u001a\u00020\u000fHÖ\u0001J\b\u00107\u001a\u00020\nH\u0016R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\u001b\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\u001c\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0011\u0010\u001d\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0019R\u0011\u0010\u001e\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0011\u0010\u001f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0011\u0010 \u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u0011\u0010!\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u0011\u0010\"\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0019R\u0011\u0010#\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019R\u0011\u0010$\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0011\u0010%\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0019R\u0011\u0010&\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019R\u0011\u0010'\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0019R\u0011\u0010(\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-¨\u00068"}, d2 = {"Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermission;", "", "pkgInfo", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;", "permInfo", "Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermInfo;", "permState", "Lcom/android/permissioncontroller/permission/model/livedatatypes/PermState;", "foregroundPerms", "", "", "(Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermInfo;Lcom/android/permissioncontroller/permission/model/livedatatypes/PermState;Ljava/util/List;)V", "isGrantedIncludingAppOp", "", "flags", "", "(Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermInfo;ZILjava/util/List;)V", "backgroundPermission", "getBackgroundPermission", "()Ljava/lang/String;", "getFlags", "()I", "getForegroundPerms", "()Ljava/util/List;", "isAutoRevoked", "()Z", "isBackgroundPermission", "isCompatRevoked", "isGrantedByDefault", "isGrantedByRole", "isInstantPerm", "isOneTime", "isPolicyFixed", "isRestricted", "isReviewRequired", "isRuntimeOnly", "isSystemFixed", "isUserFixed", "isUserSensitive", "isUserSet", "name", "getName", "getPermInfo", "()Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPermInfo;", "getPkgInfo", "()Lcom/android/permissioncontroller/permission/model/livedatatypes/LightPackageInfo;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final /* data */ class LightPermission {
    private final String backgroundPermission;
    private final int flags;
    private final List<String> foregroundPerms;
    private final boolean isAutoRevoked;
    private final boolean isBackgroundPermission;
    private final boolean isCompatRevoked;
    private final boolean isGrantedByDefault;
    private final boolean isGrantedByRole;
    private final boolean isGrantedIncludingAppOp;
    private final boolean isInstantPerm;
    private final boolean isOneTime;
    private final boolean isPolicyFixed;
    private final boolean isRestricted;
    private final boolean isReviewRequired;
    private final boolean isRuntimeOnly;
    private final boolean isSystemFixed;
    private final boolean isUserFixed;
    private final boolean isUserSensitive;
    private final boolean isUserSet;
    private final String name;
    private final LightPermInfo permInfo;
    private final LightPackageInfo pkgInfo;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LightPermission copy$default(LightPermission lightPermission, LightPackageInfo lightPackageInfo, LightPermInfo lightPermInfo, boolean z, int i, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            lightPackageInfo = lightPermission.pkgInfo;
        }
        if ((i2 & 2) != 0) {
            lightPermInfo = lightPermission.permInfo;
        }
        LightPermInfo lightPermInfo2 = lightPermInfo;
        if ((i2 & 4) != 0) {
            z = lightPermission.isGrantedIncludingAppOp;
        }
        boolean z2 = z;
        if ((i2 & 8) != 0) {
            i = lightPermission.flags;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            list = lightPermission.foregroundPerms;
        }
        return lightPermission.copy(lightPackageInfo, lightPermInfo2, z2, i3, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final LightPackageInfo getPkgInfo() {
        return this.pkgInfo;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final LightPermInfo getPermInfo() {
        return this.permInfo;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsGrantedIncludingAppOp() {
        return this.isGrantedIncludingAppOp;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getFlags() {
        return this.flags;
    }

    public final List<String> component5() {
        return this.foregroundPerms;
    }

    public final LightPermission copy(LightPackageInfo pkgInfo, LightPermInfo permInfo, boolean isGrantedIncludingAppOp, int flags, List<String> foregroundPerms) {
        Intrinsics.checkParameterIsNotNull(pkgInfo, "pkgInfo");
        Intrinsics.checkParameterIsNotNull(permInfo, "permInfo");
        return new LightPermission(pkgInfo, permInfo, isGrantedIncludingAppOp, flags, foregroundPerms);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LightPermission)) {
            return false;
        }
        LightPermission lightPermission = (LightPermission) other;
        return Intrinsics.areEqual(this.pkgInfo, lightPermission.pkgInfo) && Intrinsics.areEqual(this.permInfo, lightPermission.permInfo) && this.isGrantedIncludingAppOp == lightPermission.isGrantedIncludingAppOp && this.flags == lightPermission.flags && Intrinsics.areEqual(this.foregroundPerms, lightPermission.foregroundPerms);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v4, types: [int] */
    /* JADX WARN: Type inference failed for: r2v8 */
    public int hashCode() {
        LightPackageInfo lightPackageInfo = this.pkgInfo;
        int iHashCode = (lightPackageInfo != null ? lightPackageInfo.hashCode() : 0) * 31;
        LightPermInfo lightPermInfo = this.permInfo;
        int iHashCode2 = (iHashCode + (lightPermInfo != null ? lightPermInfo.hashCode() : 0)) * 31;
        boolean z = this.isGrantedIncludingAppOp;
        ?? r2 = z;
        if (z) {
            r2 = 1;
        }
        int iHashCode3 = (((iHashCode2 + r2) * 31) + Integer.hashCode(this.flags)) * 31;
        List<String> list = this.foregroundPerms;
        return iHashCode3 + (list != null ? list.hashCode() : 0);
    }

    public LightPermission(LightPackageInfo pkgInfo, LightPermInfo permInfo, boolean isGrantedIncludingAppOp, int flags, List<String> list) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(pkgInfo, "pkgInfo");
        Intrinsics.checkParameterIsNotNull(permInfo, "permInfo");
        this.pkgInfo = pkgInfo;
        this.permInfo = permInfo;
        this.isGrantedIncludingAppOp = isGrantedIncludingAppOp;
        this.flags = flags;
        this.foregroundPerms = list;
        this.name = permInfo.getName();
        this.backgroundPermission = this.permInfo.getBackgroundPermission();
        this.isBackgroundPermission = this.foregroundPerms != null ? !r0.isEmpty() : false;
        this.isPolicyFixed = (this.flags & 4) != 0;
        this.isSystemFixed = (this.flags & 16) != 0;
        this.isUserFixed = (this.flags & 2) != 0;
        this.isUserSet = (this.flags & 1) != 0;
        this.isCompatRevoked = (this.flags & 8) != 0;
        this.isReviewRequired = (this.flags & 64) != 0;
        this.isOneTime = (this.flags & 65536) != 0;
        this.isInstantPerm = (this.permInfo.getProtectionFlags() & 4096) != 0;
        this.isRuntimeOnly = (this.permInfo.getProtectionFlags() & 8192) != 0;
        this.isGrantedByDefault = (this.flags & 32) != 0;
        this.isGrantedByRole = (this.flags & 32768) != 0;
        this.isUserSensitive = !Utils.isRuntimePlatformPermission(this.permInfo.getName()) || (this.isGrantedIncludingAppOp && (this.flags & 256) != 0) || !(this.isGrantedIncludingAppOp || (this.flags & 512) == 0);
        if ((this.permInfo.getFlags() & 4) != 0) {
            z = (this.flags & Utils.FLAGS_PERMISSION_RESTRICTION_ANY_EXEMPT) == 0;
        } else {
            z = ((this.permInfo.getFlags() & 8) == 0 || SoftRestrictedPermissionPolicy.shouldShow(this.pkgInfo, this.permInfo.getName(), this.flags)) ? false : true;
        }
        this.isRestricted = z;
        this.isAutoRevoked = (this.flags & 131072) != 0;
    }

    public final LightPackageInfo getPkgInfo() {
        return this.pkgInfo;
    }

    public final LightPermInfo getPermInfo() {
        return this.permInfo;
    }

    public final boolean isGrantedIncludingAppOp() {
        return this.isGrantedIncludingAppOp;
    }

    public final int getFlags() {
        return this.flags;
    }

    public final List<String> getForegroundPerms() {
        return this.foregroundPerms;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LightPermission(LightPackageInfo pkgInfo, LightPermInfo permInfo, PermState permState, List<String> list) {
        this(pkgInfo, permInfo, permState.getGranted(), permState.getPermFlags(), list);
        Intrinsics.checkParameterIsNotNull(pkgInfo, "pkgInfo");
        Intrinsics.checkParameterIsNotNull(permInfo, "permInfo");
        Intrinsics.checkParameterIsNotNull(permState, "permState");
    }

    public final String getName() {
        return this.name;
    }

    public final String getBackgroundPermission() {
        return this.backgroundPermission;
    }

    /* JADX INFO: renamed from: isBackgroundPermission, reason: from getter */
    public final boolean getIsBackgroundPermission() {
        return this.isBackgroundPermission;
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

    /* JADX INFO: renamed from: isCompatRevoked, reason: from getter */
    public final boolean getIsCompatRevoked() {
        return this.isCompatRevoked;
    }

    /* JADX INFO: renamed from: isReviewRequired, reason: from getter */
    public final boolean getIsReviewRequired() {
        return this.isReviewRequired;
    }

    /* JADX INFO: renamed from: isOneTime, reason: from getter */
    public final boolean getIsOneTime() {
        return this.isOneTime;
    }

    /* JADX INFO: renamed from: isInstantPerm, reason: from getter */
    public final boolean getIsInstantPerm() {
        return this.isInstantPerm;
    }

    /* JADX INFO: renamed from: isRuntimeOnly, reason: from getter */
    public final boolean getIsRuntimeOnly() {
        return this.isRuntimeOnly;
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

    /* JADX INFO: renamed from: isRestricted, reason: from getter */
    public final boolean getIsRestricted() {
        return this.isRestricted;
    }

    /* JADX INFO: renamed from: isAutoRevoked, reason: from getter */
    public final boolean getIsAutoRevoked() {
        return this.isAutoRevoked;
    }

    public String toString() {
        StringBuilder $this$buildString = new StringBuilder();
        $this$buildString.append(this.name);
        $this$buildString.append(this.isGrantedIncludingAppOp ? ", Granted" : ", NotGranted");
        if (this.isPolicyFixed) {
            $this$buildString.append(", PolicyFixed");
        }
        if (this.isSystemFixed) {
            $this$buildString.append(", SystemFixed");
        }
        if (this.isUserFixed) {
            $this$buildString.append(", UserFixed");
        }
        if (this.isUserSet) {
            $this$buildString.append(", UserSet");
        }
        if (this.isCompatRevoked) {
            $this$buildString.append(", CompatRevoked");
        }
        if (this.isReviewRequired) {
            $this$buildString.append(", ReviewRequired");
        }
        if (this.isOneTime) {
            $this$buildString.append(", OneTime");
        }
        if (this.isGrantedByDefault) {
            $this$buildString.append(", GrantedByDefault");
        }
        if (this.isGrantedByRole) {
            $this$buildString.append(", GrantedByRole");
        }
        if (this.isUserSensitive) {
            $this$buildString.append(", UserSensitive");
        }
        if (this.isRestricted) {
            $this$buildString.append(", Restricted");
        }
        if (this.isAutoRevoked) {
            $this$buildString.append(", AutoRevoked");
        }
        String string = $this$buildString.toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
