package com.byd.vrdownload;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.byd.vrdownload.utils.LogUtils;
import com.liulishuo.filedownloader.FileDownloadConnectListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadProperties;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

/* JADX INFO: loaded from: classes.dex */
public class DownloadService extends Service {
    private static final String TAG = "DownloadService";
    IDownloadServiceHandler handler;
    private FileDownloadConnectListener listener;
    Context mContext;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtils.i("wens", "DownloadService  onCreate");
        FileDownloadHelper.holdContext(getApplicationContext());
        try {
            FileDownloadUtils.setMinProgressStep(FileDownloadProperties.getImpl().downloadMinProgressStep);
            FileDownloadUtils.setMinProgressTime(FileDownloadProperties.getImpl().downloadMinProgressTime);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (!FileDownloader.getImpl().isServiceConnected()) {
            FileDownloader.getImpl().bindService();
            registerServiceConnectionListener();
        }
        App app = App.CONTEXT;
        this.mContext = app;
        if (app == null) {
            LogUtils.e(TAG, "onCreate mContext is null ");
        } else {
            LogUtils.e(TAG, "onCreate: ");
        }
        Context context = this.mContext;
        if (context != null) {
            this.handler = DownloadServiceHandler.getInstance(context);
        }
        try {
            this.handler.getAttributeProfile();
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.handler.onBind(intent);
    }

    public void registerServiceConnectionListener() {
        if (this.listener != null) {
            FileDownloader.getImpl().removeServiceConnectListener(this.listener);
        }
        this.listener = new FileDownloadConnectListener() { // from class: com.byd.vrdownload.DownloadService.1
            @Override // com.liulishuo.filedownloader.FileDownloadConnectListener
            public void connected() {
            }

            @Override // com.liulishuo.filedownloader.FileDownloadConnectListener
            public void disconnected() {
            }
        };
        FileDownloader.getImpl().addServiceConnectListener(this.listener);
    }

    private void unregisterServiceConnectionListener() {
        FileDownloader.getImpl().removeServiceConnectListener(this.listener);
        this.listener = null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        this.handler.onStartCommand(intent, i, i2);
        Log.i(TAG, "DownloadService onStartCommand called!");
        ((NotificationManager) getSystemService("notification")).createNotificationChannel(new NotificationChannel(getApplication().getPackageName(), getApplication().getPackageName(), 2));
        startForeground(96, new Notification.Builder(this, getApplication().getPackageName()).setSmallIcon(R.mipmap.ic_launcher).build());
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        stopForeground(true);
        unregisterServiceConnectionListener();
        super.onDestroy();
    }
}
