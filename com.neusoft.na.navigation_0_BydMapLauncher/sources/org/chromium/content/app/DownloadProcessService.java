package org.chromium.content.app;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content.R;
import org.chromium.content.browser.ChildProcessConstants;
import org.chromium.content.common.IChildProcessCallback;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class DownloadProcessService extends ChildProcessService {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "DownloadProcess";
    private IChildProcessCallback mCallback;
    private long mClientContext;
    private int mDownloadCount;

    @Override // org.chromium.content.app.ChildProcessService, android.app.Service
    public void onCreate() {
        super.onCreate();
        startForeground(R.id.download_service_notification, new Notification());
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        initializeParams(intent);
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return 1;
        }
        this.mCallback = IChildProcessCallback.Stub.asInterface(extras.getBinder(ChildProcessConstants.EXTRA_CHILD_PROCESS_CALLBACK));
        getServiceInfo(extras);
        return 1;
    }

    private void onDownloadStarted(boolean z, int i) {
        IChildProcessCallback iChildProcessCallback = this.mCallback;
        if (iChildProcessCallback != null) {
            try {
                iChildProcessCallback.onDownloadStarted(z, i);
            } catch (RemoteException e) {
                Log.e(TAG, "Unable to callback the browser process.", e);
            }
        }
        if (z) {
            this.mDownloadCount++;
        }
    }

    private void onDownloadCompleted(boolean z) {
        int i = this.mDownloadCount - 1;
        this.mDownloadCount = i;
        if (i == 0) {
            stopSelf();
        }
    }
}
