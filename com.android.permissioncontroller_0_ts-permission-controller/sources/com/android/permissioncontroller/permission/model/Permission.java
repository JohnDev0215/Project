package com.android.permissioncontroller.permission.model;

import android.content.pm.PermissionInfo;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes8.dex */
public final class Permission {
    private final String mAppOp;
    private boolean mAppOpAllowed;
    private Permission mBackgroundPermission;
    private final String mBackgroundPermissionName;
    private int mFlags;
    private ArrayList<Permission> mForegroundPermissions;
    private boolean mGranted;
    private boolean mIsEphemeral;
    private boolean mIsRuntimeOnly;
    private final String mName;
    private final PermissionInfo mPermissionInfo;
    private boolean mWhitelisted;

    public Permission(String name, PermissionInfo permissionInfo, boolean granted, String appOp, boolean appOpAllowed, int flags) {
        this.mPermissionInfo = permissionInfo;
        this.mName = name;
        this.mBackgroundPermissionName = permissionInfo.backgroundPermission;
        this.mGranted = granted;
        this.mAppOp = appOp;
        this.mAppOpAllowed = appOpAllowed;
        this.mFlags = flags;
        this.mIsEphemeral = (permissionInfo.protectionLevel & 4096) != 0;
        this.mIsRuntimeOnly = (permissionInfo.protectionLevel & 8192) != 0;
    }

    public void addForegroundPermissions(Permission foregroundPermission) {
        if (this.mForegroundPermissions == null) {
            this.mForegroundPermissions = new ArrayList<>(1);
        }
        this.mForegroundPermissions.add(foregroundPermission);
    }

    public void setBackgroundPermission(Permission backgroundPermission) {
        this.mBackgroundPermission = backgroundPermission;
    }

    public PermissionInfo getPermissionInfo() {
        return this.mPermissionInfo;
    }

    public String getName() {
        return this.mName;
    }

    public String getAppOp() {
        return this.mAppOp;
    }

    public int getFlags() {
        return this.mFlags;
    }

    boolean isHardRestricted() {
        return (this.mPermissionInfo.flags & 4) != 0;
    }

    boolean isSoftRestricted() {
        return (this.mPermissionInfo.flags & 8) != 0;
    }

    public boolean affectsAppOp() {
        return this.mAppOp != null || isBackgroundPermission();
    }

    public boolean isGranted() {
        return this.mGranted;
    }

    public boolean isGrantedIncludingAppOp() {
        return this.mGranted && (!affectsAppOp() || isAppOpAllowed()) && !isReviewRequired();
    }

    public boolean isReviewRequired() {
        return (this.mFlags & 64) != 0;
    }

    public void unsetReviewRequired() {
        this.mFlags &= -65;
    }

    public void setGranted(boolean mGranted) {
        this.mGranted = mGranted;
    }

    public boolean isAppOpAllowed() {
        return this.mAppOpAllowed;
    }

    public boolean isUserFixed() {
        return (this.mFlags & 2) != 0;
    }

    public void setUserFixed(boolean userFixed) {
        if (userFixed) {
            this.mFlags |= 2;
        } else {
            this.mFlags &= -3;
        }
    }

    public void setOneTime(boolean oneTime) {
        if (oneTime) {
            this.mFlags |= 65536;
        } else {
            this.mFlags &= -65537;
        }
    }

    public boolean isSystemFixed() {
        return (this.mFlags & 16) != 0;
    }

    public boolean isPolicyFixed() {
        return (this.mFlags & 4) != 0;
    }

    public boolean isUserSet() {
        return (this.mFlags & 1) != 0;
    }

    public boolean isGrantedByDefault() {
        return (this.mFlags & 32) != 0;
    }

    public boolean isUserSensitive() {
        return isGrantedIncludingAppOp() ? (this.mFlags & 256) != 0 : (this.mFlags & 512) != 0;
    }

    public String getBackgroundPermissionName() {
        return this.mBackgroundPermissionName;
    }

    public Permission getBackgroundPermission() {
        return this.mBackgroundPermission;
    }

    public ArrayList<Permission> getForegroundPermissions() {
        return this.mForegroundPermissions;
    }

    public boolean hasBackgroundPermission() {
        return this.mBackgroundPermissionName != null;
    }

    public boolean isBackgroundPermission() {
        return this.mForegroundPermissions != null;
    }

    public boolean isOneTime() {
        return (this.mFlags & 65536) != 0;
    }

    public void setUserSet(boolean userSet) {
        if (userSet) {
            this.mFlags |= 1;
        } else {
            this.mFlags &= -2;
        }
    }

    public void setPolicyFixed(boolean policyFixed) {
        if (policyFixed) {
            this.mFlags |= 4;
        } else {
            this.mFlags &= -5;
        }
    }

    public boolean isRevokedCompat() {
        return (this.mFlags & 8) != 0;
    }

    public void setRevokedCompat(boolean revokedCompat) {
        if (revokedCompat) {
            this.mFlags |= 8;
        } else {
            this.mFlags &= -9;
        }
    }

    public void setAppOpAllowed(boolean mAppOpAllowed) {
        this.mAppOpAllowed = mAppOpAllowed;
    }

    public boolean isEphemeral() {
        return this.mIsEphemeral;
    }

    public boolean isRuntimeOnly() {
        return this.mIsRuntimeOnly;
    }

    public boolean isGrantingAllowed(boolean isEphemeralApp, boolean supportsRuntimePermissions) {
        return (!isEphemeralApp || isEphemeral()) && (supportsRuntimePermissions || !isRuntimeOnly());
    }

    public boolean equals(Object o) {
        if (!(o instanceof Permission)) {
            return false;
        }
        Permission other = (Permission) o;
        if (!Objects.equals(getName(), other.getName()) || getFlags() != other.getFlags() || isGranted() != other.isGranted()) {
            return false;
        }
        if (getBackgroundPermission() != null && other.getBackgroundPermission() != null) {
            if (!Objects.equals(getBackgroundPermissionName(), other.getBackgroundPermissionName())) {
                return false;
            }
        } else if (getBackgroundPermission() != other.getBackgroundPermission()) {
            return false;
        }
        if (getForegroundPermissions() != null && other.getForegroundPermissions() != null) {
            ArrayList<Permission> others = other.getForegroundPermissions();
            if (getForegroundPermissions().size() != others.size()) {
                return false;
            }
            for (int i = 0; i < others.size(); i++) {
                if (!getForegroundPermissions().get(i).getName().equals(others.get(i).getName())) {
                    return false;
                }
            }
        } else if (getForegroundPermissions() != null || other.getForegroundPermissions() != null) {
            return false;
        }
        return Objects.equals(getAppOp(), other.getAppOp()) && isAppOpAllowed() == other.isAppOpAllowed();
    }

    public int hashCode() {
        ArrayList<String> linkedPermissionNames = new ArrayList<>();
        Permission permission = this.mBackgroundPermission;
        if (permission != null) {
            linkedPermissionNames.add(permission.getName());
        }
        ArrayList<Permission> arrayList = this.mForegroundPermissions;
        if (arrayList != null) {
            for (Permission linkedPermission : arrayList) {
                if (linkedPermission != null) {
                    linkedPermissionNames.add(linkedPermission.getName());
                }
            }
        }
        return Objects.hash(this.mName, Integer.valueOf(this.mFlags), Boolean.valueOf(this.mGranted), this.mAppOp, Boolean.valueOf(this.mAppOpAllowed), linkedPermissionNames);
    }
}
