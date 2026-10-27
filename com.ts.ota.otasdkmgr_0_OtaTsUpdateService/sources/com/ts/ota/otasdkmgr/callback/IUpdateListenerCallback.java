package com.ts.ota.otasdkmgr.callback;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.RemoteCallbackList;
import android.util.Log;
import com.byd.ota.master.interfaces.IUpdateListener;

/* JADX INFO: loaded from: classes.dex */
public class IUpdateListenerCallback implements IUpdateListener {
    private static final String TAG = "IUpdateListenerCallback";
    private static IUpdateListenerCallback sCallback;
    RemoteCallbackList<com.ts.ota.otasdkmgr.IUpdateListener> callBacks = new RemoteCallbackList<>();
    private final Handler mHandler;

    public static synchronized IUpdateListenerCallback getInstance() {
        if (sCallback == null) {
            sCallback = new IUpdateListenerCallback();
        }
        return sCallback;
    }

    private IUpdateListenerCallback() {
        HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper());
    }

    public void registerListener(com.ts.ota.otasdkmgr.IUpdateListener callBack) {
        this.callBacks.register(callBack);
    }

    public void unregisterListener(com.ts.ota.otasdkmgr.IUpdateListener callBack) {
        this.callBacks.unregister(callBack);
    }

    public void onUpdateSuccess() {
        Log.w(TAG, "onUpdateSuccess");
        this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.callback.IUpdateListenerCallback.1
            @Override // java.lang.Runnable
            public void run() {
                int size = IUpdateListenerCallback.this.callBacks.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((com.ts.ota.otasdkmgr.IUpdateListener) IUpdateListenerCallback.this.callBacks.getBroadcastItem(i)).onUpdateSuccess();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                IUpdateListenerCallback.this.callBacks.finishBroadcast();
            }
        });
    }

    public void onUpdateProgress(final int progress) {
        Log.w(TAG, "onUpdateProgress : progress " + progress);
        this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.callback.IUpdateListenerCallback.2
            @Override // java.lang.Runnable
            public void run() {
                int size = IUpdateListenerCallback.this.callBacks.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((com.ts.ota.otasdkmgr.IUpdateListener) IUpdateListenerCallback.this.callBacks.getBroadcastItem(i)).onUpdateProgress(progress);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                IUpdateListenerCallback.this.callBacks.finishBroadcast();
            }
        });
    }

    public void onUpdateFail(final int errorCode) {
        Log.w(TAG, "onUpdateFail : errorCode " + errorCode);
        this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.callback.IUpdateListenerCallback.3
            @Override // java.lang.Runnable
            public void run() {
                int size = IUpdateListenerCallback.this.callBacks.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((com.ts.ota.otasdkmgr.IUpdateListener) IUpdateListenerCallback.this.callBacks.getBroadcastItem(i)).onUpdateFail(errorCode);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                IUpdateListenerCallback.this.callBacks.finishBroadcast();
            }
        });
    }

    public void onDownloadBackupSuccess() {
        Log.w(TAG, "onDownloadBackupSuccess");
        this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.callback.IUpdateListenerCallback.4
            @Override // java.lang.Runnable
            public void run() {
                int size = IUpdateListenerCallback.this.callBacks.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((com.ts.ota.otasdkmgr.IUpdateListener) IUpdateListenerCallback.this.callBacks.getBroadcastItem(i)).onDownloadBackupSuccess();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                IUpdateListenerCallback.this.callBacks.finishBroadcast();
            }
        });
    }

    public void onDownloadBackupProgress(final int progress) {
        Log.w(TAG, "onDownloadBackupProgress : progress " + progress);
        this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.callback.IUpdateListenerCallback.5
            @Override // java.lang.Runnable
            public void run() {
                int size = IUpdateListenerCallback.this.callBacks.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((com.ts.ota.otasdkmgr.IUpdateListener) IUpdateListenerCallback.this.callBacks.getBroadcastItem(i)).onDownloadBackupProgress(progress);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                IUpdateListenerCallback.this.callBacks.finishBroadcast();
            }
        });
    }

    public void onDownloadBackupFail(final int errorCode) {
        Log.w(TAG, "onDownloadBackupFail : errorCode " + errorCode);
        this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.callback.IUpdateListenerCallback.6
            @Override // java.lang.Runnable
            public void run() {
                int size = IUpdateListenerCallback.this.callBacks.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((com.ts.ota.otasdkmgr.IUpdateListener) IUpdateListenerCallback.this.callBacks.getBroadcastItem(i)).onDownloadBackupFail(errorCode);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                IUpdateListenerCallback.this.callBacks.finishBroadcast();
            }
        });
    }

    public void onTboxUpdateResult(final int ret) {
        Log.w(TAG, "onTboxUpdateResult : ret " + ret);
        this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.callback.IUpdateListenerCallback.7
            @Override // java.lang.Runnable
            public void run() {
                int size = IUpdateListenerCallback.this.callBacks.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((com.ts.ota.otasdkmgr.IUpdateListener) IUpdateListenerCallback.this.callBacks.getBroadcastItem(i)).onTboxUpdateResult(ret);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                IUpdateListenerCallback.this.callBacks.finishBroadcast();
            }
        });
    }

    public void onTboxUpdateProgress(final int progress) {
        Log.w(TAG, "onTboxUpdateProgress : progress " + progress);
        this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.callback.IUpdateListenerCallback.8
            @Override // java.lang.Runnable
            public void run() {
                int size = IUpdateListenerCallback.this.callBacks.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((com.ts.ota.otasdkmgr.IUpdateListener) IUpdateListenerCallback.this.callBacks.getBroadcastItem(i)).onTboxUpdateProgress(progress);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                IUpdateListenerCallback.this.callBacks.finishBroadcast();
            }
        });
    }

    public void onTboxConnectState(final int state) {
        Log.w(TAG, "onTboxConnectState : state " + state);
        this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.callback.IUpdateListenerCallback.9
            @Override // java.lang.Runnable
            public void run() {
                int size = IUpdateListenerCallback.this.callBacks.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((com.ts.ota.otasdkmgr.IUpdateListener) IUpdateListenerCallback.this.callBacks.getBroadcastItem(i)).onTboxConnectState(state);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                IUpdateListenerCallback.this.callBacks.finishBroadcast();
            }
        });
    }

    public void onTboxExportLogProgress(final int progress) {
        Log.w(TAG, "onTboxExportLogProgress : progress " + progress);
        this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.callback.IUpdateListenerCallback.10
            @Override // java.lang.Runnable
            public void run() {
                int size = IUpdateListenerCallback.this.callBacks.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((com.ts.ota.otasdkmgr.IUpdateListener) IUpdateListenerCallback.this.callBacks.getBroadcastItem(i)).onTboxExportLogProgress(progress);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                IUpdateListenerCallback.this.callBacks.finishBroadcast();
            }
        });
    }

    public void onTboxExportLogResult(final int result) {
        Log.w(TAG, "onTboxExportLogResult : progress " + result);
        this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.callback.IUpdateListenerCallback.11
            @Override // java.lang.Runnable
            public void run() {
                int size = IUpdateListenerCallback.this.callBacks.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((com.ts.ota.otasdkmgr.IUpdateListener) IUpdateListenerCallback.this.callBacks.getBroadcastItem(i)).onTboxExportLogResult(result);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                IUpdateListenerCallback.this.callBacks.finishBroadcast();
            }
        });
    }

    public void onNotify(final Bundle bundle) {
        Log.w(TAG, "onNotify : bundle " + bundle);
        this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.callback.IUpdateListenerCallback.12
            @Override // java.lang.Runnable
            public void run() {
                int size = IUpdateListenerCallback.this.callBacks.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((com.ts.ota.otasdkmgr.IUpdateListener) IUpdateListenerCallback.this.callBacks.getBroadcastItem(i)).onNotify(bundle);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                IUpdateListenerCallback.this.callBacks.finishBroadcast();
            }
        });
    }
}
