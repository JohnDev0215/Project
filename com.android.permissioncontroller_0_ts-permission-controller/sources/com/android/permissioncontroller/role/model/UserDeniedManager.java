package com.android.permissioncontroller.role.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArraySet;
import com.android.permissioncontroller.Constants;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: classes8.dex */
public class UserDeniedManager {
    private static UserDeniedManager sInstance;
    private final SharedPreferences mPreferences;

    public static UserDeniedManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new UserDeniedManager(context);
        }
        return sInstance;
    }

    private UserDeniedManager(Context context) {
        this.mPreferences = context.getApplicationContext().getSharedPreferences(Constants.REQUEST_ROLE_USER_DENIED_FILE, 0);
    }

    public boolean isDeniedOnce(String roleName, String packageName) {
        return isDenied(roleName, packageName, false);
    }

    public void setDeniedOnce(String roleName, String packageName) {
        setDenied(roleName, packageName, false, true);
    }

    public boolean isDeniedAlways(String roleName, String packageName) {
        return isDenied(roleName, packageName, true);
    }

    public void setDeniedAlways(String roleName, String packageName) {
        setDenied(roleName, packageName, true, true);
    }

    public void clearDenied(String roleName, String packageName) {
        setDenied(roleName, packageName, false, false);
        setDenied(roleName, packageName, true, false);
    }

    public void clearPackageDenied(String packageName) {
        this.mPreferences.edit().remove(getKey(packageName, false)).remove(getKey(packageName, true)).apply();
    }

    private static String getKey(String packageName, boolean always) {
        StringBuilder sb = new StringBuilder();
        sb.append(always ? Constants.REQUEST_ROLE_USER_DENIED_ALWAYS_KEY_PREFIX : Constants.REQUEST_ROLE_USER_DENIED_ONCE_KEY_PREFIX);
        sb.append(packageName);
        return sb.toString();
    }

    private boolean isDenied(String roleName, String packageName, boolean always) {
        String key = getKey(packageName, always);
        return this.mPreferences.getStringSet(key, Collections.emptySet()).contains(roleName);
    }

    private void setDenied(String roleName, String packageName, boolean always, boolean denied) {
        String key = getKey(packageName, always);
        Set<String> roleNames = this.mPreferences.getStringSet(key, Collections.emptySet());
        if (roleNames.contains(roleName) == denied) {
            return;
        }
        Set<String> roleNames2 = new ArraySet<>(roleNames);
        if (denied) {
            roleNames2.add(roleName);
        } else {
            roleNames2.remove(roleName);
        }
        if (roleName.isEmpty()) {
            this.mPreferences.edit().remove(key).apply();
        } else {
            this.mPreferences.edit().putStringSet(key, roleNames2).apply();
        }
    }
}
