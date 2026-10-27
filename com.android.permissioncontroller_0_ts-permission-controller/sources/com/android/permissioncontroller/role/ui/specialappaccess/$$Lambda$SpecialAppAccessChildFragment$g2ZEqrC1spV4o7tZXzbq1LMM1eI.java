package com.android.permissioncontroller.role.ui.specialappaccess;

import com.android.permissioncontroller.role.ui.ManageRoleHolderStateLiveData;
import com.android.permissioncontroller.role.ui.specialappaccess.SpecialAppAccessViewModel;

/* JADX INFO: renamed from: com.android.permissioncontroller.role.ui.specialappaccess.-$$Lambda$SpecialAppAccessChildFragment$g2ZEqrC1spV4o7tZXzbq1LMM1eI, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes8.dex */
public final /* synthetic */ class $$Lambda$SpecialAppAccessChildFragment$g2ZEqrC1spV4o7tZXzbq1LMM1eI implements SpecialAppAccessViewModel.ManageRoleHolderStateObserver {
    public final /* synthetic */ SpecialAppAccessChildFragment f$0;

    public /* synthetic */ $$Lambda$SpecialAppAccessChildFragment$g2ZEqrC1spV4o7tZXzbq1LMM1eI(SpecialAppAccessChildFragment specialAppAccessChildFragment) {
        this.f$0 = specialAppAccessChildFragment;
    }

    @Override // com.android.permissioncontroller.role.ui.specialappaccess.SpecialAppAccessViewModel.ManageRoleHolderStateObserver
    public final void onManageRoleHolderStateChanged(ManageRoleHolderStateLiveData manageRoleHolderStateLiveData, int i) {
        this.f$0.onManageRoleHolderStateChanged(manageRoleHolderStateLiveData, i);
    }
}
