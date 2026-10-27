package com.android.car.developeroptions.password;

import com.android.car.developeroptions.password.ConfirmLockPassword;

/* JADX INFO: renamed from: com.android.car.developeroptions.password.-$$Lambda$ConfirmLockPassword$ConfirmLockPasswordFragment$YlJJyZAp5Sij75QLwkn6i6AcNF0, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$ConfirmLockPassword$ConfirmLockPasswordFragment$YlJJyZAp5Sij75QLwkn6i6AcNF0 implements Runnable {
    public final /* synthetic */ ConfirmLockPassword.ConfirmLockPasswordFragment f$0;

    public /* synthetic */ $$Lambda$ConfirmLockPassword$ConfirmLockPasswordFragment$YlJJyZAp5Sij75QLwkn6i6AcNF0(ConfirmLockPassword.ConfirmLockPasswordFragment confirmLockPasswordFragment) {
        this.f$0 = confirmLockPasswordFragment;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.updatePasswordEntry();
    }
}
