package com.android.providers.contacts;

import android.util.EventLog;

/* JADX INFO: loaded from: classes.dex */
public class EventLogTags {
    public static void writeContactsUpgradeReceiver(long j) {
        EventLog.writeEvent(4100, j);
    }
}
