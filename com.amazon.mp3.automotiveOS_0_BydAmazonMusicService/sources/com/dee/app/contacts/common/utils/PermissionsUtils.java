package com.dee.app.contacts.common.utils;

import android.content.Context;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.dee.app.contacts.interfaces.models.data.enums.PermissionAuthStatus;
import com.dee.app.contacts.interfaces.models.data.enums.PermissionRequestInfo;

/* JADX INFO: loaded from: classes5.dex */
public class PermissionsUtils {
    private final ActivityLifecycleUtil mActivityLifecycleUtil;
    private Context mContext;
    private static SparseArray<Object> requestListeners = new SparseArray<>();
    private static final ContactsLogger LOG = ContactsLogger.getLogger("TAG", PermissionsUtils.class);

    public PermissionsUtils(ActivityLifecycleUtil activityLifecycleUtil, Context context) {
        this.mActivityLifecycleUtil = activityLifecycleUtil;
        this.mContext = context;
    }

    public PermissionAuthStatus getPermissionAuthStatus(@Nullable PermissionRequestInfo permissionRequestInfo) {
        boolean z = false;
        for (String str : permissionRequestInfo.permissions) {
            if (ContextCompat.checkSelfPermission(this.mContext, str) != 0) {
                z = true;
            }
        }
        if (z) {
            return PermissionAuthStatus.DENIED;
        }
        return PermissionAuthStatus.GRANTED;
    }
}
