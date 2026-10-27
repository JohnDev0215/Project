package com.android.launcher3.compat;

import android.content.Context;
import android.os.Process;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.ArrayMap;
import android.util.LongSparseArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class UserManagerCompatVNMr1 extends UserManagerCompat {
    protected final UserManager mUserManager;
    protected ArrayMap<UserHandle, Long> mUserToSerialMap;
    protected LongSparseArray<UserHandle> mUsers;

    @Override // com.android.launcher3.compat.UserManagerCompat
    public boolean requestQuietModeEnabled(boolean enableQuietMode, UserHandle user) {
        return false;
    }

    UserManagerCompatVNMr1(Context context) {
        this.mUserManager = (UserManager) context.getSystemService("user");
    }

    @Override // com.android.launcher3.compat.UserManagerCompat
    public boolean isQuietModeEnabled(UserHandle user) {
        return this.mUserManager.isQuietModeEnabled(user);
    }

    @Override // com.android.launcher3.compat.UserManagerCompat
    public boolean isUserUnlocked(UserHandle user) {
        return this.mUserManager.isUserUnlocked(user);
    }

    @Override // com.android.launcher3.compat.UserManagerCompat
    public boolean isAnyProfileQuietModeEnabled() {
        for (UserHandle userHandle : getUserProfiles()) {
            if (!Process.myUserHandle().equals(userHandle) && isQuietModeEnabled(userHandle)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.launcher3.compat.UserManagerCompat
    public long getSerialNumberForUser(UserHandle user) {
        synchronized (this) {
            ArrayMap<UserHandle, Long> arrayMap = this.mUserToSerialMap;
            if (arrayMap != null) {
                Long l = arrayMap.get(user);
                return l == null ? 0L : l.longValue();
            }
            return this.mUserManager.getSerialNumberForUser(user);
        }
    }

    @Override // com.android.launcher3.compat.UserManagerCompat
    public UserHandle getUserForSerialNumber(long serialNumber) {
        synchronized (this) {
            LongSparseArray<UserHandle> longSparseArray = this.mUsers;
            if (longSparseArray != null) {
                return longSparseArray.get(serialNumber);
            }
            return this.mUserManager.getUserForSerialNumber(serialNumber);
        }
    }

    @Override // com.android.launcher3.compat.UserManagerCompat
    public boolean isDemoUser() {
        return this.mUserManager.isDemoUser();
    }

    @Override // com.android.launcher3.compat.UserManagerCompat
    public void enableAndResetCache() {
        synchronized (this) {
            this.mUsers = new LongSparseArray<>();
            this.mUserToSerialMap = new ArrayMap<>();
            List<UserHandle> userProfiles = this.mUserManager.getUserProfiles();
            if (userProfiles != null) {
                for (UserHandle userHandle : userProfiles) {
                    long serialNumberForUser = this.mUserManager.getSerialNumberForUser(userHandle);
                    this.mUsers.put(serialNumberForUser, userHandle);
                    this.mUserToSerialMap.put(userHandle, Long.valueOf(serialNumberForUser));
                }
            }
        }
    }

    @Override // com.android.launcher3.compat.UserManagerCompat
    public List<UserHandle> getUserProfiles() {
        synchronized (this) {
            if (this.mUsers != null) {
                return new ArrayList(this.mUserToSerialMap.keySet());
            }
            List<UserHandle> userProfiles = this.mUserManager.getUserProfiles();
            return userProfiles == null ? Collections.emptyList() : userProfiles;
        }
    }

    @Override // com.android.launcher3.compat.UserManagerCompat
    public boolean hasWorkProfile() {
        synchronized (this) {
            LongSparseArray<UserHandle> longSparseArray = this.mUsers;
            if (longSparseArray != null) {
                return longSparseArray.size() > 1;
            }
            return getUserProfiles().size() > 1;
        }
    }
}
