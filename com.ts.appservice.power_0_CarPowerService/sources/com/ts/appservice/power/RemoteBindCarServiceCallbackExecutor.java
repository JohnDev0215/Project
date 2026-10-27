package com.ts.appservice.power;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import com.ts.lib.power.ICarPowerBindCarServiceListener;
import com.ts.lib.power.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class RemoteBindCarServiceCallbackExecutor {
    private static final String TAG = RemoteBindCarServiceCallbackExecutor.class.getSimpleName();
    private static final String THREAD_NAME = "CarPowerBindCarServiceThread";
    private boolean isConnect = false;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private RemoteCallbackList<ICarPowerBindCarServiceListener> mListeners;

    public boolean isConnect() {
        return this.isConnect;
    }

    public RemoteBindCarServiceCallbackExecutor(RemoteCallbackList<ICarPowerBindCarServiceListener> listeners) {
        LogUtil.info(TAG, "RemoteBindCarServiceCallbackExecutor");
        this.mListeners = listeners;
        init();
    }

    private void init() {
        LogUtil.info(TAG, "init");
        HandlerThread handlerThread = new HandlerThread(THREAD_NAME);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
    }

    public void quit() {
        LogUtil.info(TAG, "quit");
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
    }

    public void onCarPowerBindCarServiceState(final boolean state) {
        this.isConnect = state;
        LogUtil.info(TAG, "onTsCarPowerBindCarServiceState state : " + state);
        this.mHandler.post(new Runnable() { // from class: com.ts.appservice.power.-$$Lambda$RemoteBindCarServiceCallbackExecutor$NWRkWZfYtFC-IrGKh8JeW8qvPhc
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCarPowerBindCarServiceState$0$RemoteBindCarServiceCallbackExecutor(state);
            }
        });
    }

    public /* synthetic */ void lambda$onCarPowerBindCarServiceState$0$RemoteBindCarServiceCallbackExecutor(boolean state) {
        int num = this.mListeners.beginBroadcast();
        for (int i = 0; i < num; i++) {
            try {
                this.mListeners.getBroadcastItem(i).onCarPowerBindCarServiceState(state);
            } catch (RemoteException excp) {
                excp.printStackTrace();
            }
        }
        this.mListeners.finishBroadcast();
    }
}
