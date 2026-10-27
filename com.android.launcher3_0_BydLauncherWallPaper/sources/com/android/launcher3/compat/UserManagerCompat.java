package com.android.launcher3.compat;

import android.content.Context;
import android.os.UserHandle;
import com.android.launcher3.Utilities;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class UserManagerCompat {
    private static UserManagerCompat sInstance;
    private static final Object sInstanceLock = new Object();

    public abstract void enableAndResetCache();

    public abstract long getSerialNumberForUser(UserHandle user);

    public abstract UserHandle getUserForSerialNumber(long serialNumber);

    public abstract List<UserHandle> getUserProfiles();

    public abstract boolean hasWorkProfile();

    public abstract boolean isAnyProfileQuietModeEnabled();

    public abstract boolean isDemoUser();

    public abstract boolean isQuietModeEnabled(UserHandle user);

    public abstract boolean isUserUnlocked(UserHandle user);

    public abstract boolean requestQuietModeEnabled(boolean enableQuietMode, UserHandle user);

    protected UserManagerCompat() {
    }

    public static UserManagerCompat getInstance(Context context) {
        UserManagerCompat userManagerCompat;
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                if (Utilities.ATLEAST_P) {
                    sInstance = new UserManagerCompatVP(context.getApplicationContext());
                } else {
                    sInstance = new UserManagerCompatVNMr1(context.getApplicationContext());
                }
            }
            userManagerCompat = sInstance;
        }
        return userManagerCompat;
    }
}
