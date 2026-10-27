package com.amazon.mp3.account.details;

/* JADX INFO: loaded from: classes3.dex */
public final class AccountDetails {
    private final String mAccountStatus;
    private final boolean mAccountVerified;
    private final boolean mPrimeMusicAccessible;

    public AccountDetails(String accountStatus, boolean primeMusicAccessible, boolean accountVerified) {
        this.mAccountStatus = accountStatus;
        this.mPrimeMusicAccessible = primeMusicAccessible;
        this.mAccountVerified = accountVerified;
    }

    public String getAccountStatus() {
        return this.mAccountStatus;
    }

    public boolean isPrimeMusicAccessible() {
        return this.mPrimeMusicAccessible;
    }

    public boolean getAccountVerified() {
        return this.mAccountVerified;
    }
}
