package com.ts.qnxapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import com.ts.lib.qnx.IQnxCallBack;
import com.ts.qnxapp.fdbusClientForAndroid.AndroidFdbusClientManager;
import com.ts.qnxapp.fdbusClientForAndroid.AndroidFdbusServerManager;
import com.ts.qnxapp.fdbusClientForAndroid.FdbusUtil;
import com.ts.qnxapp.util.LogUtil;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class QnxMessageManager {
    private static final int MESSAGE_CALLBACK = 1;
    private static final String TAG = "QnxMessageManager";
    private static volatile QnxMessageManager mMessageManager;
    private Bundle mAdasBundle;
    private CallbackHandler mCallbackHandler;
    private HandlerThread mCallbackThread;
    private Bundle mChmidBundle;
    private Bundle mDayNightBundle;
    private AndroidFdbusClientManager mFdbusClientManager;
    private AndroidFdbusServerManager mFdbusServerManager;
    private MessageSenderHandler mHandler;
    private Bundle mInsCenterDisPlayBundle;
    private Bundle mNaviModeBundle;
    private Bundle mTemperatureBundle;
    private Bundle mTftTemperatureBundle;
    private Bundle mThemeBundle;
    private MessageSender mSender = null;
    private MessageReceive mReceive = null;
    private MessageReceiveHandler mReceiveHandler = null;
    private HashMap<Integer, RemoteCallbackList<IQnxCallBack>> mCallbacks = new HashMap<>();
    private final QnxMessageListener mListener = new QnxMessageListener() { // from class: com.ts.qnxapp.-$$Lambda$QnxMessageManager$Vx7hPd_0h4LNlNvqxkdWW55t-DI
        @Override // com.ts.qnxapp.QnxMessageListener
        public final void notifyListener(Bundle bundle) {
            this.f$0.lambda$new$0$QnxMessageManager(bundle);
        }
    };

    public static QnxMessageManager getInstance() {
        LogUtil.logE(TAG, "QnxMessageManager getInstance called");
        if (mMessageManager == null) {
            synchronized (QnxMessageManager.class) {
                if (mMessageManager == null) {
                    mMessageManager = new QnxMessageManager();
                }
            }
        }
        return mMessageManager;
    }

    public void init() {
        LogUtil.logE(TAG, "QnxMessageManager init called!");
        startCallbackHandleMessage();
        this.mReceiveHandler = new MessageReceiveHandler(this.mListener);
        MessageReceive messageReceive = new MessageReceive(this.mReceiveHandler);
        this.mReceive = messageReceive;
        messageReceive.startReceiveHandler();
        AndroidFdbusServerManager androidFdbusServerManager = AndroidFdbusServerManager.getInstance();
        this.mFdbusServerManager = androidFdbusServerManager;
        androidFdbusServerManager.initAll(this.mReceive);
        AndroidFdbusClientManager androidFdbusClientManager = AndroidFdbusClientManager.getInstance();
        this.mFdbusClientManager = androidFdbusClientManager;
        androidFdbusClientManager.initAll(this.mReceive);
        this.mHandler = new MessageSenderHandler(this.mFdbusClientManager, this.mFdbusServerManager);
        MessageSender messageSender = new MessageSender(this.mHandler);
        this.mSender = messageSender;
        messageSender.startMessageHandler();
    }

    private void startCallbackHandleMessage() {
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mCallbackThread = handlerThread;
        handlerThread.start();
        this.mCallbackHandler = new CallbackHandler(this.mCallbackThread.getLooper());
    }

    private final class CallbackHandler extends Handler {
        public CallbackHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                QnxMessageManager.this.callbackOnMessageChanged(msg.arg1, msg.arg2, (Bundle) msg.obj);
                return;
            }
            LogUtil.logI(QnxMessageManager.TAG, "Could not handleMessage: " + msg.what);
        }
    }

    public /* synthetic */ void lambda$new$0$QnxMessageManager(Bundle bundle) {
        if (bundle != null) {
            bundleToCallback(bundle);
            int msgType = bundle.getInt("msgType");
            int commandId = FdbusUtil.getInstance().commandTransact(msgType, 0);
            Message message = this.mCallbackHandler.obtainMessage(1);
            message.arg1 = msgType;
            message.arg2 = commandId;
            message.obj = bundle;
            this.mCallbackHandler.sendMessage(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackOnMessageChanged(int msgType, int commandId, Bundle bundle) {
        RemoteCallbackList<IQnxCallBack> list;
        synchronized (this.mCallbacks) {
            list = this.mCallbacks.get(Integer.valueOf(commandId));
        }
        if (list == null) {
            LogUtil.logE(TAG, "there is no callback : msgType:" + msgType);
            return;
        }
        int count = list.beginBroadcast();
        for (int i = 0; i < count; i++) {
            try {
                list.getBroadcastItem(i).onMessageChanged(bundle);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
        list.finishBroadcast();
    }

    private void bundleToCallback(Bundle bundle) {
        int msgType = bundle.getInt("msgType");
        if (msgType == 3 || msgType == 9) {
            registerCallbackCluster(bundle);
            return;
        }
        if (msgType == 10) {
            if (bundle.getInt("subtype") == 101) {
                this.mTemperatureBundle = bundle;
            } else if (bundle.getInt("subtype") == 102) {
                this.mTftTemperatureBundle = bundle;
            }
        }
    }

    private void registerCallbackCluster(Bundle bundle) {
        int subtype = bundle.getInt("subtype");
        if (subtype == 0) {
            this.mDayNightBundle = bundle;
            return;
        }
        if (subtype == 1) {
            this.mNaviModeBundle = bundle;
            return;
        }
        if (subtype == 2) {
            this.mInsCenterDisPlayBundle = bundle;
            return;
        }
        if (subtype == 3) {
            this.mAdasBundle = bundle;
        } else if (subtype == 4) {
            this.mThemeBundle = bundle;
        } else if (subtype == 6) {
            this.mChmidBundle = bundle;
        }
    }

    public boolean sendMessage(int msgType, int subType, String content, Bundle bundle) {
        IpcMessage ipcmessage = new IpcMessage();
        ipcmessage.subType = subType;
        ipcmessage.msgType = msgType;
        ipcmessage.content = content;
        ipcmessage.bundle = bundle;
        boolean ret = this.mSender.sendMessage(ipcmessage);
        return ret;
    }

    public boolean notifyDiLinkStarted() {
        IpcMessage ipcmessage = new IpcMessage();
        ipcmessage.subType = 1;
        ipcmessage.msgType = 6;
        ipcmessage.content = null;
        Bundle bundle = new Bundle();
        bundle.putInt("AndroidStatus", 1);
        ipcmessage.bundle = bundle;
        boolean ret = this.mFdbusServerManager.sendMessageToQnx(ipcmessage.msgType, ipcmessage.subType, ipcmessage.content, ipcmessage.bundle);
        LogUtil.logD(TAG, "notifyDiLinkStarted IpcMessage: " + ipcmessage + " result = " + ret);
        return ret;
    }

    public boolean registerCallback(int msgType, int subType, IQnxCallBack callback) {
        synchronized (this.mCallbacks) {
            LogUtil.logI(TAG, "register callback =" + msgType + ";" + subType + ";" + callback);
            int commandId = FdbusUtil.getInstance().commandTransact(msgType, subType);
            synchronized (this.mCallbacks) {
                RemoteCallbackList<IQnxCallBack> list = this.mCallbacks.get(Integer.valueOf(commandId));
                if (list == null) {
                    LogUtil.logI(TAG, "list is null,register");
                    RemoteCallbackList<IQnxCallBack> list2 = new RemoteCallbackList<>();
                    list2.register(callback);
                    this.mCallbacks.put(Integer.valueOf(commandId), list2);
                } else {
                    LogUtil.logI(TAG, "list is not null,register");
                    list.register(callback);
                }
                if (msgType == 10) {
                    this.mListener.notifyListener(this.mTemperatureBundle);
                    this.mListener.notifyListener(this.mTftTemperatureBundle);
                }
                if (msgType == 9) {
                    this.mListener.notifyListener(this.mDayNightBundle);
                    this.mListener.notifyListener(this.mInsCenterDisPlayBundle);
                    this.mListener.notifyListener(this.mAdasBundle);
                    this.mListener.notifyListener(this.mThemeBundle);
                    this.mListener.notifyListener(this.mChmidBundle);
                }
                if (msgType == 3) {
                    this.mListener.notifyListener(this.mNaviModeBundle);
                }
            }
        }
        return true;
    }

    public boolean unregisterCallback(int msgType, int subType, IQnxCallBack callback) {
        RemoteCallbackList<IQnxCallBack> list;
        int commandId = FdbusUtil.getInstance().commandTransact(msgType, subType);
        synchronized (this.mCallbacks) {
            list = this.mCallbacks.get(Integer.valueOf(commandId));
        }
        if (list != null) {
            LogUtil.logI(TAG, "unregister client callback, msgType:" + msgType + "; subType:" + subType + "; callback:" + callback);
            list.unregister(callback);
            return true;
        }
        LogUtil.logD(TAG, "There is no callback need to unregister, msgType:" + msgType + ";" + subType);
        return false;
    }
}
