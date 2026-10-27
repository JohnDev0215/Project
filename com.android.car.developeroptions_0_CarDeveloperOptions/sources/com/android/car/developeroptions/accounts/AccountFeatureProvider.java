package com.android.car.developeroptions.accounts;

import android.accounts.Account;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public interface AccountFeatureProvider {
    String getAccountType();

    Account[] getAccounts(Context context);
}
