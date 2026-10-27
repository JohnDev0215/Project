package com.android.inputmethod.compat;

import android.content.Context;
import android.os.UserManager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public final class UserManagerCompatUtils {
    public static final int LOCK_STATE_LOCKED = 2;
    public static final int LOCK_STATE_UNKNOWN = 0;
    public static final int LOCK_STATE_UNLOCKED = 1;
    private static final Method METHOD_isUserUnlocked;

    @Retention(RetentionPolicy.SOURCE)
    public @interface LockState {
    }

    static {
        if (BuildCompatUtils.EFFECTIVE_SDK_INT <= 23) {
            METHOD_isUserUnlocked = null;
        } else {
            METHOD_isUserUnlocked = CompatUtils.getMethod(UserManager.class, "isUserUnlocked", new Class[0]);
        }
    }

    private UserManagerCompatUtils() {
    }

    public static int getUserLockState(final Context context) {
        UserManager userManager;
        Boolean bool;
        Method method = METHOD_isUserUnlocked;
        if (method == null || (userManager = (UserManager) context.getSystemService(UserManager.class)) == null || (bool = (Boolean) CompatUtils.invoke(userManager, null, method, new Object[0])) == null) {
            return 0;
        }
        return bool.booleanValue() ? 1 : 2;
    }
}
