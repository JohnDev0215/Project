package com.android.car.developeroptions.accounts;

import android.accounts.Account;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class AccountFeatureProviderImpl implements AccountFeatureProvider {
    @Override // com.android.car.developeroptions.accounts.AccountFeatureProvider
    public String getAccountType() {
        return null;
    }

    @Override // com.android.car.developeroptions.accounts.AccountFeatureProvider
    public Account[] getAccounts(Context context) {
        return new Account[0];
    }
}
