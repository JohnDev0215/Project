package com.byd.vrdownload;

import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IDownloadServiceHandler {
    void getAttributeProfile() throws RemoteException;

    void notifyPermissionResult(boolean z);

    IBinder onBind(Intent intent);

    void onDestroy();

    void onStartCommand(Intent intent, int i, int i2);
}
