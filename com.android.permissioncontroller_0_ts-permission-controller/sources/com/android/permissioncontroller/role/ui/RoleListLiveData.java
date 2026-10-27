package com.android.permissioncontroller.role.ui;

import android.app.role.OnRoleHoldersChangedListener;
import android.app.role.RoleManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.UserHandle;
import android.util.ArrayMap;
import android.util.Log;
import com.android.permissioncontroller.AsyncTaskLiveData;
import com.android.permissioncontroller.role.model.Role;
import com.android.permissioncontroller.role.model.Roles;
import com.android.permissioncontroller.role.utils.PackageUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class RoleListLiveData extends AsyncTaskLiveData<List<RoleItem>> implements OnRoleHoldersChangedListener {
    private static final String LOG_TAG = RoleListLiveData.class.getSimpleName();
    private final Context mContext;
    private final boolean mExclusive;
    private final UserHandle mUser;

    public RoleListLiveData(boolean exclusive, UserHandle user, Context context) {
        this.mExclusive = exclusive;
        this.mUser = user;
        this.mContext = context;
    }

    @Override // androidx.lifecycle.LiveData
    protected void onActive() {
        loadValue();
        RoleManager roleManager = (RoleManager) this.mContext.getSystemService(RoleManager.class);
        roleManager.addOnRoleHoldersChangedListenerAsUser(this.mContext.getMainExecutor(), this, this.mUser);
    }

    @Override // androidx.lifecycle.LiveData
    protected void onInactive() {
        RoleManager roleManager = (RoleManager) this.mContext.getSystemService(RoleManager.class);
        roleManager.removeOnRoleHoldersChangedListenerAsUser(this, this.mUser);
    }

    public void onRoleHoldersChanged(String roleName, UserHandle user) {
        loadValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.permissioncontroller.AsyncTaskLiveData
    public List<RoleItem> loadValueInBackground() {
        ArrayMap<String, Role> roles = Roles.get(this.mContext);
        List<RoleItem> roleItems = new ArrayList<>();
        RoleManager roleManager = (RoleManager) this.mContext.getSystemService(RoleManager.class);
        int rolesSize = roles.size();
        for (int rolesIndex = 0; rolesIndex < rolesSize; rolesIndex++) {
            Role role = roles.valueAt(rolesIndex);
            if (role.isExclusive() == this.mExclusive && role.isAvailableAsUser(this.mUser, this.mContext) && role.isVisibleAsUser(this.mUser, this.mContext) && (!this.mExclusive || !role.getQualifyingPackagesAsUser(this.mUser, this.mContext).isEmpty())) {
                List<ApplicationInfo> holderApplicationInfos = new ArrayList<>();
                List<String> holderPackageNames = roleManager.getRoleHoldersAsUser(role.getName(), this.mUser);
                int holderPackageNamesSize = holderPackageNames.size();
                for (int holderPackageNamesIndex = 0; holderPackageNamesIndex < holderPackageNamesSize; holderPackageNamesIndex++) {
                    String holderPackageName = holderPackageNames.get(holderPackageNamesIndex);
                    ApplicationInfo holderApplicationInfo = PackageUtils.getApplicationInfoAsUser(holderPackageName, this.mUser, this.mContext);
                    if (holderApplicationInfo == null) {
                        Log.w(LOG_TAG, "Cannot get ApplicationInfo for application, package name: " + holderPackageName + ", user id: " + this.mUser.getIdentifier());
                    } else {
                        holderApplicationInfos.add(holderApplicationInfo);
                    }
                }
                roleItems.add(new RoleItem(role, holderApplicationInfos));
            }
        }
        return roleItems;
    }
}
