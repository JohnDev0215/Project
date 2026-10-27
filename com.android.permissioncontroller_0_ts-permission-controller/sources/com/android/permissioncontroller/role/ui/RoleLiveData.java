package com.android.permissioncontroller.role.ui;

import android.app.role.OnRoleHoldersChangedListener;
import android.app.role.RoleManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.UserHandle;
import android.util.Log;
import android.util.Pair;
import com.android.permissioncontroller.AsyncTaskLiveData;
import com.android.permissioncontroller.role.model.Role;
import com.android.permissioncontroller.role.utils.PackageUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class RoleLiveData extends AsyncTaskLiveData<List<Pair<ApplicationInfo, Boolean>>> implements OnRoleHoldersChangedListener {
    private static final String LOG_TAG = RoleLiveData.class.getSimpleName();
    private final Context mContext;
    private final Role mRole;
    private final UserHandle mUser;

    public RoleLiveData(Role role, UserHandle user, Context context) {
        this.mRole = role;
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
    public List<Pair<ApplicationInfo, Boolean>> loadValueInBackground() {
        RoleManager roleManager = (RoleManager) this.mContext.getSystemService(RoleManager.class);
        List<String> holderPackageNames = roleManager.getRoleHoldersAsUser(this.mRole.getName(), this.mUser);
        List<String> qualifyingPackageNames = this.mRole.getQualifyingPackagesAsUser(this.mUser, this.mContext);
        List<Pair<ApplicationInfo, Boolean>> qualifyingApplications = new ArrayList<>();
        int qualifyingPackageNamesSize = qualifyingPackageNames.size();
        for (int i = 0; i < qualifyingPackageNamesSize; i++) {
            String qualifyingPackageName = qualifyingPackageNames.get(i);
            ApplicationInfo qualifyingApplicationInfo = PackageUtils.getApplicationInfoAsUser(qualifyingPackageName, this.mUser, this.mContext);
            if (qualifyingApplicationInfo == null) {
                Log.w(LOG_TAG, "Cannot get ApplicationInfo for application, skipping: " + qualifyingPackageName);
            } else if (this.mRole.isApplicationVisibleAsUser(qualifyingApplicationInfo, this.mUser, this.mContext)) {
                boolean isHolderApplication = holderPackageNames.contains(qualifyingPackageName);
                qualifyingApplications.add(new Pair<>(qualifyingApplicationInfo, Boolean.valueOf(isHolderApplication)));
            }
        }
        return qualifyingApplications;
    }
}
