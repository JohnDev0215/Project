package com.android.providers.contacts;

import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public class LocaleChangeReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ContentProvider contentProviderCoerceToLocalContentProvider = ContentProvider.coerceToLocalContentProvider(context.getContentResolver().acquireProvider("com.android.contacts"));
        if (contentProviderCoerceToLocalContentProvider instanceof ContactsProvider2) {
            ((ContactsProvider2) contentProviderCoerceToLocalContentProvider).onLocaleChanged();
        }
    }
}
