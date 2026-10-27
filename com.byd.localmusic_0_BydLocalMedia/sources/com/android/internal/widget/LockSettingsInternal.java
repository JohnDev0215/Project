package com.android.internal.widget;

import android.app.admin.PasswordMetrics;
import com.android.internal.widget.LockPatternUtils;

/* JADX INFO: loaded from: classes4.dex */
public abstract class LockSettingsInternal {
    public abstract long addEscrowToken(byte[] bArr, int i, LockPatternUtils.EscrowTokenStateChangeCallback escrowTokenStateChangeCallback);

    public abstract boolean armRebootEscrow();

    public abstract void clearRebootEscrow();

    public abstract PasswordMetrics getUserPasswordMetrics(int i);

    public abstract boolean isEscrowTokenActive(long j, int i);

    public abstract void prepareRebootEscrow();

    public abstract void refreshStrongAuthTimeout(int i);

    public abstract boolean removeEscrowToken(long j, int i);

    public abstract boolean setLockCredentialWithToken(LockscreenCredential lockscreenCredential, long j, byte[] bArr, int i);

    public abstract void setRebootEscrowListener(RebootEscrowListener rebootEscrowListener);

    public abstract boolean unlockUserWithToken(long j, byte[] bArr, int i);
}
