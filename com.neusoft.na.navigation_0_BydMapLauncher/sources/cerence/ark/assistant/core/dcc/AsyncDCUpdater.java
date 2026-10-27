package cerence.ark.assistant.core.dcc;

import android.os.CancellationSignal;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import cerence.ark.assistant.api.dynamic.IDynamicContentProvider;
import cerence.ark.assistant.common.Logger;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AsyncDCUpdater implements IDCUpdater<List<String>> {
    private static final int FLAG_DESTROY = 31;
    private static final int FLAG_GET_CONTENT = 30;
    private static final String TAG = "AsyncDCUpdater";
    private CancellationSignal mCancellationSignal;
    private IDynamicContentProvider mDynamicContentProvider;
    private Handler mHandler;

    protected abstract List<String> getContent(String str, IDynamicContentProvider iDynamicContentProvider, CancellationSignal cancellationSignal);

    protected AsyncDCUpdater(IDynamicContentProvider iDynamicContentProvider) {
        this.mDynamicContentProvider = iDynamicContentProvider;
    }

    private void initThread(String str) {
        HandlerThread handlerThread = new HandlerThread(str);
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: cerence.ark.assistant.core.dcc.AsyncDCUpdater.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 30) {
                    AsyncDCUpdater.this.getContent(message);
                } else if (i != 31) {
                    Logger.error(AsyncDCUpdater.TAG, "unknown message: " + message.what);
                } else {
                    AsyncDCUpdater.this.destroyThread(false);
                }
            }
        };
    }

    @Override // cerence.ark.assistant.core.dcc.IDCUpdater
    public synchronized void getContent(String str, IDCCallback<List<String>> iDCCallback) {
        String str2;
        Handler handler = this.mHandler;
        if (handler == null) {
            if (TextUtils.isEmpty(str)) {
                str2 = "ca_dcu_" + SystemClock.uptimeMillis();
            } else {
                str2 = "ca_dcu_" + str;
            }
            initThread(str2);
        } else {
            handler.removeCallbacksAndMessages(null);
        }
        CancellationSignal cancellationSignal = this.mCancellationSignal;
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
        }
        this.mCancellationSignal = new CancellationSignal();
        Message message = new Message();
        message.what = 30;
        message.obj = new DccMessageWrapper(str, this.mCancellationSignal, iDCCallback);
        this.mHandler.sendMessage(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getContent(Message message) {
        IDynamicContentProvider iDynamicContentProvider = this.mDynamicContentProvider;
        if (iDynamicContentProvider == null) {
            Logger.info(TAG, "dynamicContentProvider is null");
            return;
        }
        DccMessageWrapper dccMessageWrapper = (DccMessageWrapper) message.obj;
        String contentType = dccMessageWrapper.getContentType();
        CancellationSignal cancellationSignal = dccMessageWrapper.getCancellationSignal();
        IDCCallback callback = dccMessageWrapper.getCallback();
        if (cancellationSignal.isCanceled()) {
            Logger.info(TAG, "This task was cancelled.");
            return;
        }
        List<String> content = getContent(contentType, iDynamicContentProvider, cancellationSignal);
        if (cancellationSignal.isCanceled()) {
            Logger.info(TAG, "getContent: This task was cancelled.");
        } else {
            callback.onCompleted(content);
            delayDestroy();
        }
    }

    @Override // cerence.ark.assistant.core.dcc.IDCUpdater
    public void destroy() {
        CancellationSignal cancellationSignal = this.mCancellationSignal;
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
        }
        this.mCancellationSignal = null;
        this.mDynamicContentProvider = null;
        destroyThread(true);
    }

    protected synchronized void delayDestroy() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeMessages(31);
            this.mHandler.sendEmptyMessageDelayed(31, 30000L);
        } else {
            Logger.info(TAG, "delayDestroy: The thread has been destroyed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void destroyThread(boolean z) {
        Handler handler = this.mHandler;
        if (handler == null) {
            Logger.info(TAG, "destroyThread: The thread has been destroyed");
            return;
        }
        if (z || !handler.hasMessages(30)) {
            Logger.info(TAG, "destroyThread: immediate = " + z);
            this.mHandler.removeCallbacksAndMessages(null);
            this.mHandler.getLooper().quit();
            this.mHandler = null;
        } else {
            Logger.info(TAG, "destroyThread: message queue has tasks");
        }
    }
}
