package com.ts.appservice.power;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import com.ts.lib.power.ICarPowerEventListener;
import com.ts.lib.power.data.CarPowerData;
import com.ts.lib.power.data.CarPowerSignalStatus;
import com.ts.lib.power.data.CarPowerWorkModeStatus;
import com.ts.lib.power.util.LogUtil;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class RemoteCallbackExecutor {
    private static final String TAG = RemoteCallbackExecutor.class.getSimpleName();
    private static final String THREAD_NAME = "CarPowerServiceCallBackThread";
    private MyHandler mHandler;
    private HandlerThread mHandlerThread;
    private RemoteCallbackList<ICarPowerEventListener> mListeners;

    public RemoteCallbackExecutor(RemoteCallbackList<ICarPowerEventListener> listeners) {
        LogUtil.info(TAG, "RemoteCallbackExecutor");
        this.mListeners = listeners;
        init();
    }

    private void init() {
        LogUtil.info(TAG, "init");
        HandlerThread handlerThread = new HandlerThread(THREAD_NAME);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new MyHandler(this.mHandlerThread.getLooper(), this);
    }

    public void quit() {
        LogUtil.info(TAG, "quit");
        MyHandler myHandler = this.mHandler;
        if (myHandler != null) {
            myHandler.removeCallbacksAndMessages(null);
        }
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
    }

    public void onCarPowerWorkModeChangeEvent(CarPowerWorkModeStatus status) {
        if (status == null) {
            LogUtil.error(TAG, "onCarPowerWorkModeChangeEvent status is null !");
            return;
        }
        LogUtil.info(TAG, "onTsCarPowerWorkModeChangeEvent status : " + status.getCarPowerWorkModeStatusEnum());
        Message message = this.mHandler.obtainMessage();
        message.what = 1;
        message.obj = status;
        this.mHandler.sendMessage(message);
    }

    public void onCarPowerSignalChangeEvent(CarPowerSignalStatus status) {
        if (status == null) {
            LogUtil.error(TAG, "onTsCarPowerSignalChangeEvent status is null !");
            return;
        }
        LogUtil.info(TAG, "onTsCarPowerSignalChangeEvent " + status.getCarPowerSignalStatusEnum());
        Message message = this.mHandler.obtainMessage();
        message.what = 2;
        message.obj = status;
        this.mHandler.sendMessage(message);
    }

    public void onCarPowerEvent(final int type, final int state, final CarPowerData data) {
        LogUtil.info(TAG, "onTsCarPowerEvent type : " + type + " state : " + state);
        this.mHandler.post(new Runnable() { // from class: com.ts.appservice.power.-$$Lambda$RemoteCallbackExecutor$a3rdn9O2CE_ZvOUVA2mNdCkaJW8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCarPowerEvent$0$RemoteCallbackExecutor(type, state, data);
            }
        });
    }

    public /* synthetic */ void lambda$onCarPowerEvent$0$RemoteCallbackExecutor(int type, int state, CarPowerData data) {
        int num = this.mListeners.beginBroadcast();
        for (int i = 0; i < num; i++) {
            try {
                this.mListeners.getBroadcastItem(i).onCarPowerEvent(type, state, data);
            } catch (RemoteException excp) {
                excp.printStackTrace();
            }
        }
        this.mListeners.finishBroadcast();
    }

    public void onCarPowerDisplayEvent(int type, int displayPanelId, int value) {
        LogUtil.info(TAG, "onCarPowerDisplayEvent displayPanelId : " + displayPanelId + " value : " + value + " type : " + type);
        Message message = this.mHandler.obtainMessage();
        message.what = 3;
        message.obj = Integer.valueOf(type);
        message.arg1 = displayPanelId;
        message.arg2 = value;
        this.mHandler.sendMessage(message);
    }

    private static class MyHandler extends Handler {
        private static final int WHAT_DISPLAY_CHANGE = 3;
        private static final int WHAT_SIGNAL_CHANGE = 2;
        private static final int WHAT_WORK_MODE_CHANGE = 1;
        private WeakReference<RemoteCallbackExecutor> mWeakReference;

        public MyHandler(Looper looper, RemoteCallbackExecutor remoteCallbackExecutor) {
            super(looper);
            this.mWeakReference = new WeakReference<>(remoteCallbackExecutor);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            RemoteCallbackExecutor callbackExecutor = this.mWeakReference.get();
            if (callbackExecutor == null) {
                return;
            }
            if (msg.what == 1) {
                CarPowerWorkModeStatus status = (CarPowerWorkModeStatus) msg.obj;
                int num = callbackExecutor.mListeners.beginBroadcast();
                for (int i = 0; i < num; i++) {
                    try {
                        callbackExecutor.mListeners.getBroadcastItem(i).onCarPowerWorkModeChangeEvent(status);
                    } catch (Exception excp) {
                        excp.printStackTrace();
                    }
                }
                callbackExecutor.mListeners.finishBroadcast();
                return;
            }
            if (msg.what == 2) {
                CarPowerSignalStatus status2 = (CarPowerSignalStatus) msg.obj;
                int num2 = callbackExecutor.mListeners.beginBroadcast();
                for (int i2 = 0; i2 < num2; i2++) {
                    try {
                        callbackExecutor.mListeners.getBroadcastItem(i2).onCarPowerSignalChangeEvent(status2);
                    } catch (Exception excp2) {
                        excp2.printStackTrace();
                    }
                }
                callbackExecutor.mListeners.finishBroadcast();
                return;
            }
            if (msg.what == 3) {
                int type = Integer.parseInt(String.valueOf(msg.obj));
                int displayPanelId = msg.arg1;
                int value = msg.arg2;
                int num3 = callbackExecutor.mListeners.beginBroadcast();
                for (int i3 = 0; i3 < num3; i3++) {
                    try {
                        callbackExecutor.mListeners.getBroadcastItem(i3).onCarPowerDisplayEvent(type, displayPanelId, value);
                    } catch (Exception excp3) {
                        excp3.printStackTrace();
                    }
                }
                callbackExecutor.mListeners.finishBroadcast();
            }
        }
    }
}
