package com.android.permissioncontroller.role.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;
import android.os.UserHandle;
import android.os.UserManager;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes8.dex */
public class UserUtils {
    private UserUtils() {
    }

    public static boolean isWorkProfile(UserHandle user, Context context) {
        UserManager userManager = (UserManager) context.getSystemService(UserManager.class);
        return userManager.isManagedProfile(user.getIdentifier());
    }

    public static UserHandle getWorkProfile(Context context) {
        UserManager userManager = (UserManager) context.getSystemService(UserManager.class);
        List<UserHandle> profiles = userManager.getUserProfiles();
        UserHandle user = Process.myUserHandle();
        int profilesSize = profiles.size();
        for (int i = 0; i < profilesSize; i++) {
            UserHandle profile = profiles.get(i);
            if (!Objects.equals(profile, user) && userManager.isManagedProfile(profile.getIdentifier())) {
                return profile;
            }
        }
        return null;
    }

    public static Context getUserContext(Context context, UserHandle user) {
        if (Process.myUserHandle().equals(user)) {
            return context;
        }
        try {
            return context.createPackageContextAsUser(context.getPackageName(), 0, user);
        } catch (PackageManager.NameNotFoundException doesNotHappen) {
            throw new IllegalStateException(doesNotHappen);
        }
    }
}
