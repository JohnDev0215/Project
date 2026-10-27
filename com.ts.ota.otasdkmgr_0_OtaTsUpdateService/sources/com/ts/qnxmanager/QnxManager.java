package com.ts.qnxmanager;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import com.ts.lib.qnx.IQnx;
import com.ts.lib.qnx.IQnxCallBack;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class QnxManager implements IBinder.DeathRecipient {
    private static final int CONNECT_INTERVAL = 200;
    private static final int MSG_TYPE_MUSIC = 1;
    private static final int MSG_TYPE_NAVI = 3;
    private static final int MSG_TYPE_PHONE = 2;
    private static final String QNX_SERVICE = "QnxMessage";
    public static String TAG = "QNXManager";
    private static volatile QnxManager mImpl;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private boolean notifyDiLinkSendStatus;
    private IQnx mService = null;
    private final Object mLock = new Object();
    private IdentityHashMap<Integer, IQnxCallBack> mQnxCallbackMap = new IdentityHashMap<>();

    private QnxManager() {
        this.mHandlerThread = null;
        this.mHandler = null;
        Log.i(TAG, "This is QnxManager construct Method");
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        lambda$tryConnectService$0$QnxManager();
    }

    public static QnxManager getInstance() {
        Log.i(TAG, "This is QnxManager getInstance Method");
        if (mImpl == null) {
            synchronized (QnxManager.class) {
                if (mImpl == null) {
                    mImpl = new QnxManager();
                }
            }
        }
        return mImpl;
    }

    public boolean sendMessage(int msgType, int subType, String content, Bundle bundle) {
        if (msgType != 1 && msgType != 2 && msgType != 3) {
            Log.d(TAG, "sendMessage msgType = " + msgType + " subType = " + subType + " content = " + content + " bundle = " + bundle);
        } else {
            Log.d(TAG, "sendMessage msgType = " + msgType + " subType = " + subType + "no contains bundle");
        }
        try {
            if (this.mService == null) {
                Log.e(TAG, "mService is null.");
                return false;
            }
            boolean ret = this.mService.sendMessage(msgType, subType, content, bundle);
            return ret;
        } catch (RemoteException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean notifyDiLinkStarted() {
        Log.e(TAG, "notifyDilinkStarted to Qnx");
        try {
            if (this.mService == null) {
                Log.e(TAG, "mService is null.");
                this.notifyDiLinkSendStatus = true;
                return false;
            }
            boolean ret = this.mService.notifyDiLinkStarted();
            return ret;
        } catch (RemoteException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public Bundle receiveMessage(int msgType, int subType, String content, Bundle bundle) throws RemoteException {
        Bundle retBundle = null;
        try {
        } catch (RemoteException exception) {
            exception.printStackTrace();
        }
        if (this.mService == null) {
            Log.e(TAG, "mService is null.");
            return null;
        }
        retBundle = this.mService.receiveMessage(msgType, subType, content, bundle);
        if (bundle.isEmpty()) {
            return null;
        }
        return retBundle;
    }

    public boolean registerCallback(int msgType, int subType, IQnxCallBack callBack) {
        this.mQnxCallbackMap.put(new Integer(msgType), callBack);
        try {
            if (this.mService == null) {
                Log.e(TAG, "mService is null.");
                return false;
            }
            Log.e(TAG, "msgType :" + msgType + "IQnxCallBack :" + callBack);
            boolean ret = this.mService.registerCallback(msgType, subType, callBack);
            return ret;
        } catch (RemoteException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean unregisterCallback(int msgType, int subType, IQnxCallBack callBack) {
        boolean ret = false;
        try {
        } catch (RemoteException exception) {
            exception.printStackTrace();
        }
        if (this.mService == null) {
            Log.e(TAG, "mService is null.");
            return false;
        }
        Log.e(TAG, "unregisterCallback: start msgType: " + msgType + "callBack :" + callBack);
        ret = this.mService.unregisterCallback(msgType, subType, callBack);
        Iterator<Map.Entry<Integer, IQnxCallBack>> entries = this.mQnxCallbackMap.entrySet().iterator();
        while (true) {
            if (!entries.hasNext()) {
                break;
            }
            Map.Entry<Integer, IQnxCallBack> entry = entries.next();
            if (entry.getValue() == callBack) {
                Log.d(TAG, "unregisterCallback: getValue : " + entry.getValue() + "callback : " + callBack);
                Integer key = entry.getKey();
                this.mQnxCallbackMap.remove(key);
                break;
            }
        }
        return ret;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: tryConnectService, reason: merged with bridge method [inline-methods] */
    public void lambda$tryConnectService$0$QnxManager() {
        IBinder binder = ServiceManager.getService(QNX_SERVICE);
        if (binder == null) {
            Log.e(TAG, "tryConnectService -> reconnect");
            this.mHandler.postDelayed(new Runnable() { // from class: com.ts.qnxmanager.-$$Lambda$QnxManager$H5twYB61wb50c_gRbkfkEghhjao
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$tryConnectService$0$QnxManager();
                }
            }, 200L);
            return;
        }
        synchronized (this.mLock) {
            Log.e(TAG, "tryConnectService -> connected");
            IQnx iQnxAsInterface = IQnx.Stub.asInterface(binder);
            this.mService = iQnxAsInterface;
            if (iQnxAsInterface != null) {
                try {
                    iQnxAsInterface.asBinder().linkToDeath(this, 0);
                    if (this.notifyDiLinkSendStatus) {
                        this.mService.notifyDiLinkStarted();
                        this.notifyDiLinkSendStatus = false;
                    }
                    for (Map.Entry<Integer, IQnxCallBack> entry : this.mQnxCallbackMap.entrySet()) {
                        Log.e(TAG, "msgType :" + entry.getKey() + "IQnxCallBack :" + entry.getValue());
                        this.mService.registerCallback(entry.getKey().intValue(), 0, entry.getValue());
                    }
                } catch (RemoteException exception) {
                    this.mService = null;
                    exception.printStackTrace();
                }
            }
        }
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        Log.e(TAG, "remote service died, something is wrong!");
        try {
            if (this.mService != null) {
                this.mService.asBinder().unlinkToDeath(this, 0);
                this.mService = null;
            }
        } catch (Exception exception) {
            Log.e(TAG, "unlinkToDeath failed, msg:" + exception.getMessage());
        }
        lambda$tryConnectService$0$QnxManager();
    }
}
