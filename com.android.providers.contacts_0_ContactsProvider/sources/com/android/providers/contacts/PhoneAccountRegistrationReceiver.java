package com.android.providers.contacts;

import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.telecom.PhoneAccountHandle;

/* JADX INFO: loaded from: classes.dex */
public class PhoneAccountRegistrationReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.telecom.action.PHONE_ACCOUNT_REGISTERED".equals(intent.getAction())) {
            PhoneAccountHandle phoneAccountHandle = (PhoneAccountHandle) intent.getParcelableExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE");
            ContentProvider contentProviderCoerceToLocalContentProvider = ContentProvider.coerceToLocalContentProvider(context.getContentResolver().acquireProvider("call_log"));
            if (contentProviderCoerceToLocalContentProvider instanceof CallLogProvider) {
                ((CallLogProvider) contentProviderCoerceToLocalContentProvider).adjustForNewPhoneAccount(phoneAccountHandle);
            }
        }
    }
}
