package com.android.permissioncontroller.role.ui;

import android.app.role.RoleManager;
import android.content.Context;
import android.os.UserHandle;
import android.util.Log;
import androidx.lifecycle.LiveData;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes8.dex */
public class ManageRoleHolderStateLiveData extends LiveData<Integer> {
    private static final boolean DEBUG = false;
    private static final String LOG_TAG = ManageRoleHolderStateLiveData.class.getSimpleName();
    public static final int STATE_FAILURE = 3;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SUCCESS = 2;
    public static final int STATE_WORKING = 1;
    private boolean mLastAdd;
    private int mLastFlags;
    private String mLastPackageName;
    private UserHandle mLastUser;

    public ManageRoleHolderStateLiveData() {
        setValue(0);
    }

    public void setRoleHolderAsUser(final String roleName, final String packageName, final boolean add, int flags, UserHandle user, Context context) {
        if (getValue().intValue() != 0) {
            Log.e(LOG_TAG, "Already (tried) managing role holders, requested role: " + roleName + ", requested package: " + packageName);
            return;
        }
        this.mLastPackageName = packageName;
        this.mLastAdd = add;
        this.mLastFlags = flags;
        this.mLastUser = user;
        setValue(1);
        RoleManager roleManager = (RoleManager) context.getSystemService(RoleManager.class);
        Executor executor = context.getMainExecutor();
        Consumer<Boolean> callback = new Consumer() { // from class: com.android.permissioncontroller.role.ui.-$$Lambda$ManageRoleHolderStateLiveData$OyDr247JBhMipVJmmb2sBSQC0dw
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$setRoleHolderAsUser$0$ManageRoleHolderStateLiveData(add, roleName, packageName, (Boolean) obj);
            }
        };
        if (add) {
            roleManager.addRoleHolderAsUser(roleName, packageName, flags, user, executor, callback);
        } else {
            roleManager.removeRoleHolderAsUser(roleName, packageName, flags, user, executor, callback);
        }
    }

    public /* synthetic */ void lambda$setRoleHolderAsUser$0$ManageRoleHolderStateLiveData(boolean add, String roleName, String packageName, Boolean successful) {
        if (successful.booleanValue()) {
            setValue(2);
        } else {
            setValue(3);
        }
    }

    public void clearRoleHoldersAsUser(final String roleName, int flags, UserHandle user, Context context) {
        if (getValue().intValue() != 0) {
            Log.e(LOG_TAG, "Already (tried) managing role holders, requested role: " + roleName);
            return;
        }
        this.mLastPackageName = null;
        this.mLastAdd = false;
        this.mLastFlags = flags;
        this.mLastUser = user;
        setValue(1);
        RoleManager roleManager = (RoleManager) context.getSystemService(RoleManager.class);
        Executor executor = context.getMainExecutor();
        Consumer<Boolean> callback = new Consumer() { // from class: com.android.permissioncontroller.role.ui.-$$Lambda$ManageRoleHolderStateLiveData$9kRoNGbHu7ebBeLeMzeV7YVxW0I
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$clearRoleHoldersAsUser$1$ManageRoleHolderStateLiveData(roleName, (Boolean) obj);
            }
        };
        roleManager.clearRoleHoldersAsUser(roleName, flags, user, executor, callback);
    }

    public /* synthetic */ void lambda$clearRoleHoldersAsUser$1$ManageRoleHolderStateLiveData(String roleName, Boolean successful) {
        if (successful.booleanValue()) {
            setValue(2);
        } else {
            setValue(3);
        }
    }

    public String getLastPackageName() {
        return this.mLastPackageName;
    }

    public boolean isLastAdd() {
        return this.mLastAdd;
    }

    public int getLastFlags() {
        return this.mLastFlags;
    }

    public UserHandle getLastUser() {
        return this.mLastUser;
    }

    public void resetState() {
        int state = getValue().intValue();
        if (state != 2 && state != 3) {
            Log.e(LOG_TAG, "Trying to reset state when the current state is not STATE_SUCCESS or STATE_FAILURE");
            return;
        }
        this.mLastPackageName = null;
        this.mLastAdd = false;
        this.mLastFlags = 0;
        this.mLastUser = null;
        setValue(0);
    }
}
