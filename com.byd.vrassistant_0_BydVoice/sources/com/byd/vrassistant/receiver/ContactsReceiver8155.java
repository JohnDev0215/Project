package com.byd.vrassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cerence.ark.assistant.api.ArkAssistant;
import cerence.ark.assistant.api.dynamic.ContentType;
import com.byd.vrassistant.utils.RLog;

/* JADX INFO: loaded from: classes2.dex */
public class ContactsReceiver8155 extends BroadcastReceiver {
    private static final String TAG = "NEU_ContactsReceiver8155";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        RLog.i(TAG, "onReceive: updataContacts");
        ArkAssistant.get().notifyContentChanged(ContentType.CONTACT);
    }
}
