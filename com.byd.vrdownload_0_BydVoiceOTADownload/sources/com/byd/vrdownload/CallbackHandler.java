package com.byd.vrdownload;

import android.os.RemoteCallbackList;
import android.os.RemoteException;
import com.byd.vrdownload.i.IDownloadCallback;
import com.byd.vrdownload.model.AidlResult;
import com.byd.vrdownload.model.DownloadTask;
import com.byd.vrdownload.model.LanguageOTAResult;
import com.byd.vrdownload.utils.LogUtils;
import com.liulishuo.filedownloader.util.FileDownloadLog;

/* JADX INFO: loaded from: classes.dex */
public class CallbackHandler {
    private static final String TAG = "CallbackHandler";
    private final RemoteCallbackList<IDownloadCallback> callbackList;

    public CallbackHandler(RemoteCallbackList<IDownloadCallback> remoteCallbackList) {
        this.callbackList = remoteCallbackList;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0052 A[Catch: all -> 0x0065, TRY_LEAVE, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x0026, B:21:0x003d, B:26:0x0052, B:29:0x0058, B:24:0x004a, B:22:0x0044, B:23:0x0049, B:6:0x000b, B:9:0x001a, B:20:0x0036), top: B:38:0x0001, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0058 A[Catch: all -> 0x0065, TRY_ENTER, TRY_LEAVE, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x0026, B:21:0x003d, B:26:0x0052, B:29:0x0058, B:24:0x004a, B:22:0x0044, B:23:0x0049, B:6:0x000b, B:9:0x001a, B:20:0x0036), top: B:38:0x0001, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized com.byd.vrdownload.model.AidlResult checkSpaceQuota(long r6) throws android.os.RemoteException {
        /*
            r5 = this;
            monitor-enter(r5)
            android.os.RemoteCallbackList<com.byd.vrdownload.i.IDownloadCallback> r0 = r5.callbackList     // Catch: java.lang.Throwable -> L65
            int r0 = r0.beginBroadcast()     // Catch: java.lang.Throwable -> L65
            r1 = 0
            r2 = r1
        L9:
            if (r2 >= r0) goto L4a
            android.os.RemoteCallbackList<com.byd.vrdownload.i.IDownloadCallback> r3 = r5.callbackList     // Catch: java.lang.Throwable -> L32 android.os.RemoteException -> L34
            java.lang.Object r3 = r3.getBroadcastCookie(r2)     // Catch: java.lang.Throwable -> L32 android.os.RemoteException -> L34
            java.lang.String r4 = "setting"
            boolean r3 = r3.equals(r4)     // Catch: java.lang.Throwable -> L32 android.os.RemoteException -> L34
            if (r3 == 0) goto L2f
            r0 = 1
            android.os.RemoteCallbackList<com.byd.vrdownload.i.IDownloadCallback> r3 = r5.callbackList     // Catch: android.os.RemoteException -> L2d java.lang.Throwable -> L32
            android.os.IInterface r2 = r3.getBroadcastItem(r2)     // Catch: android.os.RemoteException -> L2d java.lang.Throwable -> L32
            com.byd.vrdownload.i.IDownloadCallback r2 = (com.byd.vrdownload.i.IDownloadCallback) r2     // Catch: android.os.RemoteException -> L2d java.lang.Throwable -> L32
            com.byd.vrdownload.model.AidlResult r6 = r2.checkSpaceQuota(r6)     // Catch: android.os.RemoteException -> L2d java.lang.Throwable -> L32
            android.os.RemoteCallbackList<com.byd.vrdownload.i.IDownloadCallback> r7 = r5.callbackList     // Catch: java.lang.Throwable -> L65
            r7.finishBroadcast()     // Catch: java.lang.Throwable -> L65
            monitor-exit(r5)
            return r6
        L2d:
            r6 = move-exception
            goto L36
        L2f:
            int r2 = r2 + 1
            goto L9
        L32:
            r6 = move-exception
            goto L44
        L34:
            r6 = move-exception
            r0 = r1
        L36:
            java.lang.String r7 = "callback error"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L32
            com.liulishuo.filedownloader.util.FileDownloadLog.e(r5, r6, r7, r1)     // Catch: java.lang.Throwable -> L32
            android.os.RemoteCallbackList<com.byd.vrdownload.i.IDownloadCallback> r7 = r5.callbackList     // Catch: java.lang.Throwable -> L65
            r7.finishBroadcast()     // Catch: java.lang.Throwable -> L65
            r1 = r0
            goto L50
        L44:
            android.os.RemoteCallbackList<com.byd.vrdownload.i.IDownloadCallback> r7 = r5.callbackList     // Catch: java.lang.Throwable -> L65
            r7.finishBroadcast()     // Catch: java.lang.Throwable -> L65
            throw r6     // Catch: java.lang.Throwable -> L65
        L4a:
            android.os.RemoteCallbackList<com.byd.vrdownload.i.IDownloadCallback> r6 = r5.callbackList     // Catch: java.lang.Throwable -> L65
            r6.finishBroadcast()     // Catch: java.lang.Throwable -> L65
            r6 = 0
        L50:
            if (r1 == 0) goto L58
            com.byd.vrdownload.model.AidlResult r6 = com.byd.vrdownload.model.AidlResult.fail(r6)     // Catch: java.lang.Throwable -> L65
            monitor-exit(r5)
            return r6
        L58:
            java.lang.Exception r6 = new java.lang.Exception     // Catch: java.lang.Throwable -> L65
            java.lang.String r7 = "客户端未连接"
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L65
            com.byd.vrdownload.model.AidlResult r6 = com.byd.vrdownload.model.AidlResult.fail(r6)     // Catch: java.lang.Throwable -> L65
            monitor-exit(r5)
            return r6
        L65:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrdownload.CallbackHandler.checkSpaceQuota(long):com.byd.vrdownload.model.AidlResult");
    }

    public synchronized void onStartDownloadResult(AidlResult aidlResult) {
        RemoteCallbackList<IDownloadCallback> remoteCallbackList;
        LogUtils.i(TAG, "onStartDownloadResult: " + aidlResult);
        int iBeginBroadcast = this.callbackList.beginBroadcast();
        for (int i = 0; i < iBeginBroadcast; i++) {
            try {
                try {
                    ((IDownloadCallback) this.callbackList.getBroadcastItem(i)).onStartDownloadResult(aidlResult);
                } catch (RemoteException e) {
                    LogUtils.e(TAG, "callback error" + e.getMessage());
                    remoteCallbackList = this.callbackList;
                    remoteCallbackList.finishBroadcast();
                }
            } catch (Throwable th) {
                this.callbackList.finishBroadcast();
                throw th;
            }
        }
        remoteCallbackList = this.callbackList;
        remoteCallbackList.finishBroadcast();
    }

    public synchronized void getLanguageOTA(LanguageOTAResult languageOTAResult) {
        RemoteCallbackList<IDownloadCallback> remoteCallbackList;
        LogUtils.i(TAG, "sendLanguageListToClient: " + languageOTAResult);
        int iBeginBroadcast = this.callbackList.beginBroadcast();
        for (int i = 0; i < iBeginBroadcast; i++) {
            try {
                try {
                    ((IDownloadCallback) this.callbackList.getBroadcastItem(i)).getLanguageOTA(languageOTAResult);
                } catch (RemoteException e) {
                    LogUtils.e(TAG, "callback error" + e.getMessage());
                    remoteCallbackList = this.callbackList;
                    remoteCallbackList.finishBroadcast();
                }
            } catch (Throwable th) {
                this.callbackList.finishBroadcast();
                throw th;
            }
        }
        remoteCallbackList = this.callbackList;
        remoteCallbackList.finishBroadcast();
    }

    public synchronized void onDownload(DownloadTask downloadTask) {
        RemoteCallbackList<IDownloadCallback> remoteCallbackList;
        int iBeginBroadcast = this.callbackList.beginBroadcast();
        for (int i = 0; i < iBeginBroadcast; i++) {
            try {
                try {
                    ((IDownloadCallback) this.callbackList.getBroadcastItem(i)).onDownload(downloadTask);
                } catch (RemoteException e) {
                    LogUtils.e(TAG, "callback error" + e.getMessage());
                    remoteCallbackList = this.callbackList;
                    remoteCallbackList.finishBroadcast();
                }
            } catch (Throwable th) {
                this.callbackList.finishBroadcast();
                throw th;
            }
        }
        remoteCallbackList = this.callbackList;
        remoteCallbackList.finishBroadcast();
    }

    public synchronized void onDownloadProgress(DownloadTask downloadTask, long j, long j2) {
        RemoteCallbackList<IDownloadCallback> remoteCallbackList;
        int iBeginBroadcast = this.callbackList.beginBroadcast();
        for (int i = 0; i < iBeginBroadcast; i++) {
            try {
                try {
                    ((IDownloadCallback) this.callbackList.getBroadcastItem(i)).onDownloadProgress(downloadTask, j, j2);
                } catch (RemoteException e) {
                    LogUtils.e(TAG, "onDownloadProgress error" + e.getMessage());
                    remoteCallbackList = this.callbackList;
                    remoteCallbackList.finishBroadcast();
                }
            } catch (Throwable th) {
                this.callbackList.finishBroadcast();
                throw th;
            }
        }
        remoteCallbackList = this.callbackList;
        remoteCallbackList.finishBroadcast();
    }

    public synchronized void deleteLanguage(String str) {
        RemoteCallbackList<IDownloadCallback> remoteCallbackList;
        int iBeginBroadcast = this.callbackList.beginBroadcast();
        for (int i = 0; i < iBeginBroadcast; i++) {
            try {
                try {
                    ((IDownloadCallback) this.callbackList.getBroadcastItem(i)).deleteLanguage(str);
                } catch (RemoteException e) {
                    LogUtils.e(TAG, "onDownloadProgress error" + e.getMessage());
                    remoteCallbackList = this.callbackList;
                    remoteCallbackList.finishBroadcast();
                }
            } catch (Throwable th) {
                this.callbackList.finishBroadcast();
                throw th;
            }
        }
        remoteCallbackList = this.callbackList;
        remoteCallbackList.finishBroadcast();
    }

    public synchronized void installLanguage(String str) {
        RemoteCallbackList<IDownloadCallback> remoteCallbackList;
        int iBeginBroadcast = this.callbackList.beginBroadcast();
        for (int i = 0; i < iBeginBroadcast; i++) {
            try {
                try {
                    ((IDownloadCallback) this.callbackList.getBroadcastItem(i)).installLanguage(str);
                } catch (RemoteException e) {
                    FileDownloadLog.e(this, e, "callback error", new Object[0]);
                    remoteCallbackList = this.callbackList;
                    remoteCallbackList.finishBroadcast();
                }
            } catch (Throwable th) {
                this.callbackList.finishBroadcast();
                throw th;
            }
        }
        remoteCallbackList = this.callbackList;
        remoteCallbackList.finishBroadcast();
    }

    public synchronized void onLanguageChanged(String str, byte b) {
    }
}
