package com.android.packageinstaller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.packageinstaller.EventResultPersister;

/* JADX INFO: loaded from: classes2.dex */
public class InstallEventReceiver extends BroadcastReceiver {
    private static final Object sLock = new Object();
    private static EventResultPersister sReceiver;

    private static EventResultPersister getReceiver(Context context) {
        synchronized (sLock) {
            if (sReceiver == null) {
                sReceiver = new EventResultPersister(TemporaryFileManager.getInstallStateFile(context));
            }
        }
        return sReceiver;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) throws Throwable {
        getReceiver(context).onEventReceived(context, intent);
    }

    static int addObserver(Context context, int id, EventResultPersister.EventResultObserver observer) throws EventResultPersister.OutOfIdsException {
        return getReceiver(context).addObserver(id, observer);
    }

    static void removeObserver(Context context, int id) {
        getReceiver(context).removeObserver(id);
    }
}
