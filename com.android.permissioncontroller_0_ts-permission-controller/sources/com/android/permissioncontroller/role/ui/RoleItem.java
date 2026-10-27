package com.android.permissioncontroller.role.ui;

import android.content.pm.ApplicationInfo;
import com.android.permissioncontroller.role.model.Role;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class RoleItem {
    private final List<ApplicationInfo> mHolderApplicationInfos;
    private final Role mRole;

    public RoleItem(Role role, List<ApplicationInfo> holderApplicationInfos) {
        this.mRole = role;
        this.mHolderApplicationInfos = holderApplicationInfos;
    }

    public Role getRole() {
        return this.mRole;
    }

    public List<ApplicationInfo> getHolderApplicationInfos() {
        return this.mHolderApplicationInfos;
    }
}
