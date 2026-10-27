package com.android.inputmethod.latin.accounts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.inputmethod.latin.settings.LocalSettingsConstants;

/* JADX INFO: loaded from: classes2.dex */
public class AccountsChangedReceiver extends BroadcastReceiver {
    static final String TAG = "AccountsChangedReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (!"android.accounts.LOGIN_ACCOUNTS_CHANGED".equals(intent.getAction())) {
            Log.w(TAG, "Received unknown broadcast: " + intent);
            return;
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        removeUnknownAccountFromPreference(defaultSharedPreferences, getAccountsForLogin(context), defaultSharedPreferences.getString(LocalSettingsConstants.PREF_ACCOUNT_NAME, null));
    }

    protected String[] getAccountsForLogin(Context context) {
        return LoginAccountUtils.getAccountsForLogin(context);
    }

    private static void removeUnknownAccountFromPreference(final SharedPreferences prefs, final String[] accounts, final String currentAccount) {
        if (currentAccount == null) {
            return;
        }
        for (String str : accounts) {
            if (TextUtils.equals(currentAccount, str)) {
                return;
            }
        }
        Log.i(TAG, "The current account was removed from the system: " + currentAccount);
        prefs.edit().remove(LocalSettingsConstants.PREF_ACCOUNT_NAME).apply();
    }
}
