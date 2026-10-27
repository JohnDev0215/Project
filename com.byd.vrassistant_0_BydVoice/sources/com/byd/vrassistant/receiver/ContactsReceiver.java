package com.byd.vrassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cerence.ark.assistant.api.ArkAssistant;
import cerence.ark.assistant.api.dynamic.ContentType;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.TagUtil;

/* JADX INFO: loaded from: classes2.dex */
public class ContactsReceiver extends BroadcastReceiver {
    private static final String TAG = TagUtil.getTagName(ContactsReceiver.class);

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        RLog.i(TAG, "onReceive: updataContacts");
        ArkAssistant.get().notifyContentChanged(ContentType.CONTACT);
    }
}
