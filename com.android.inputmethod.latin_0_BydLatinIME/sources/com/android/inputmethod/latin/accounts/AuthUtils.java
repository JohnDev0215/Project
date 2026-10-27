package com.android.inputmethod.latin.accounts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class AuthUtils {
    private final AccountManager mAccountManager;

    public AuthUtils(Context context) {
        this.mAccountManager = AccountManager.get(context);
    }

    public void invalidateAuthToken(final String accountType, final String authToken) {
        this.mAccountManager.invalidateAuthToken(accountType, authToken);
    }

    public AccountManagerFuture<Bundle> getAuthToken(final Account account, final String authTokenType, final Bundle options, final boolean notifyAuthFailure, final AccountManagerCallback<Bundle> callback, final Handler handler) {
        return this.mAccountManager.getAuthToken(account, authTokenType, options, notifyAuthFailure, callback, handler);
    }

    public String blockingGetAuthToken(final Account account, final String authTokenType, final boolean notifyAuthFailure) throws OperationCanceledException, IOException, AuthenticatorException {
        return this.mAccountManager.blockingGetAuthToken(account, authTokenType, notifyAuthFailure);
    }
}
