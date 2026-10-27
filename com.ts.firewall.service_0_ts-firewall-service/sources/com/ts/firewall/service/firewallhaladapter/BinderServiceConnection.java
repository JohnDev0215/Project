package com.ts.firewall.service.firewallhaladapter;

import android.hidl.base.V1_0.IBase;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IHwBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.ts.firewall.service.utils.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class BinderServiceConnection {
    private static final String HANDLER_THREAD_NAME = "com.ts.car.firewall.handlerthread";
    private static final int MSG_RECONNECT_SERVICE = 1;
    private static final int RECONNECT_DELAY = 1000;
    private MyHandler mHandler;
    private HandlerThread mHandlerThread;
    private IBase mHwBinder;
    private ServiceAction mServiceAction;
    private ConnectionState mConnectionState = ConnectionState.STATE_NOT_INIT;
    private IHwBinder.DeathRecipient mHwDeathRecipient = new IHwBinder.DeathRecipient() { // from class: com.ts.firewall.service.firewallhaladapter.BinderServiceConnection.1
        public void serviceDied(long l) {
            BinderServiceConnection.this.callbackServiceConnectState(false);
            BinderServiceConnection.this.onBinderDied();
        }
    };

    public enum ConnectionState {
        STATE_NOT_INIT,
        STATE_INIT,
        STATE_UNINIT
    }

    public BinderServiceConnection(ServiceAction action) {
        if (action == null) {
            LogUtil.error("mServiceAction cannot be null!");
            return;
        }
        this.mServiceAction = action;
        HandlerThread handlerThread = new HandlerThread(HANDLER_THREAD_NAME);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new MyHandler(this.mHandlerThread.getLooper());
    }

    public synchronized boolean init() {
        if (this.mConnectionState != ConnectionState.STATE_INIT) {
            this.mConnectionState = ConnectionState.STATE_INIT;
            if (this.mServiceAction == null) {
                LogUtil.error("mServiceAction cannot be null!");
                return false;
            }
            boolean res = this.mServiceAction.connectService();
            if (!res) {
                LogUtil.warn("connectService error");
                reConnectServiceDelayed();
            }
        }
        return true;
    }

    public synchronized void unInit() {
        if (this.mConnectionState == ConnectionState.STATE_INIT) {
            this.mHandler.removeMessages(1);
            this.mConnectionState = ConnectionState.STATE_UNINIT;
            if (this.mHwBinder != null) {
                try {
                    this.mHwBinder.unlinkToDeath(this.mHwDeathRecipient);
                } catch (RemoteException ex) {
                    LogUtil.wtf(ex);
                }
                this.mHwBinder = null;
            }
            this.mServiceAction.releaseService();
        }
    }

    protected void onGetBinder(IBase hwBinder) {
        LogUtil.debug("onGetBinder, mConnectionState :" + this.mConnectionState);
        if (this.mConnectionState != ConnectionState.STATE_INIT) {
            LogUtil.debug("onGetBinder, mConnectionState is not STATE_INIT:" + this.mConnectionState + ", abandon.");
            return;
        }
        if (hwBinder == null) {
            LogUtil.warn("onGetBinder binder is null!");
            reConnectServiceDelayed();
            return;
        }
        try {
            this.mHwBinder = hwBinder;
            hwBinder.linkToDeath(this.mHwDeathRecipient, 0L);
            callbackServiceConnectState(true);
        } catch (RemoteException ex) {
            LogUtil.warn("onGetBinder get exception", ex);
            reConnectServiceDelayed();
        }
    }

    protected synchronized void onBinderDied() {
        LogUtil.warn("onBinderDied");
        if (this.mHwBinder != null) {
            try {
                this.mHwBinder.unlinkToDeath(this.mHwDeathRecipient);
                this.mHwBinder = null;
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            callbackServiceConnectState(false);
            reConnectServiceDelayed();
        } else {
            callbackServiceConnectState(false);
            reConnectServiceDelayed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackServiceConnectState(boolean isConnected) {
        if (this.mServiceAction == null) {
            LogUtil.warn("mServiceAction is null!");
        }
    }

    public class MyHandler extends Handler {
        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                boolean res = BinderServiceConnection.this.mServiceAction.connectService();
                if (!res) {
                    LogUtil.warn("reconnectService error");
                    BinderServiceConnection.this.reConnectServiceDelayed();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void reConnectServiceDelayed() {
        this.mHandler.removeMessages(1);
        if (this.mConnectionState == ConnectionState.STATE_INIT) {
            this.mHandler.sendEmptyMessageDelayed(1, 1000L);
        }
    }
}
