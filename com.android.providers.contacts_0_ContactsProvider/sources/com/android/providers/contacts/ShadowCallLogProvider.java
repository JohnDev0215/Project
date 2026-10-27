package com.android.providers.contacts;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class ShadowCallLogProvider extends CallLogProvider {
    @Override // com.android.providers.contacts.CallLogProvider
    protected boolean isShadow() {
        return true;
    }

    @Override // com.android.providers.contacts.CallLogProvider
    protected CallLogDatabaseHelper getDatabaseHelper(Context context) {
        return CallLogDatabaseHelper.getInstanceForShadow(context);
    }
}
