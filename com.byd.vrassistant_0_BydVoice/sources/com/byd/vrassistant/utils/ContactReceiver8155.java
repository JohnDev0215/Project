package com.byd.vrassistant.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cerence.ark.assistant.api.ArkAssistant;
import cerence.ark.assistant.api.dynamic.ContentType;

/* JADX INFO: loaded from: classes2.dex */
public class ContactReceiver8155 extends BroadcastReceiver {
    private static final String TAG = TagUtil.getTagName(ContactReceiver8155.class);

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        RLog.i(TAG, "onReceive: updataContacts");
        ArkAssistant.get().notifyContentChanged(ContentType.CONTACT);
    }
}
