package com.amazon.mp3.account.map;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.credentials.MapAccount;
import com.amazon.mp3.util.Log;
import com.amazon.music.push.PushMessagingClient;

/* JADX INFO: loaded from: classes3.dex */
public class AccountDeregistration {
    private static final String TAG = "AccountDeregistration";

    public static void deregister(final Context context, final Callback callback) {
        Callback callback2 = new Callback() { // from class: com.amazon.mp3.account.map.AccountDeregistration.1
            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle) {
                Callback callback3 = callback;
                if (callback3 != null) {
                    callback3.onError(bundle);
                }
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle) {
                Callback callback3 = callback;
                if (callback3 != null) {
                    callback3.onSuccess(bundle);
                }
            }
        };
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
        MAPAccountManager manager = MapAccount.getManager(context);
        String accountId = accountCredentialStorage.getAccountId();
        if (TextUtils.isEmpty(accountId)) {
            accountId = manager.getAccount();
        }
        manager.deregisterAccount(accountId, callback2);
        Log.warning(TAG, "deregistering account");
        new PushMessagingClient().setSignedIn(context.getApplicationContext(), false);
    }
}
