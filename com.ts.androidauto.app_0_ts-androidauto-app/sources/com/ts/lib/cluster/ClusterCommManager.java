package com.ts.lib.cluster;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import com.ts.lib.cluster.common.IClusterCommCallback;
import com.ts.lib.cluster.common.IClusterCommService;
import com.ts.lib.cluster.utils.LogUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class ClusterCommManager implements IBinder.DeathRecipient {
    private static final int CONNECT_INTERVAL = 1000;
    private static final String SERVICE_CLUSTERCOMM = "cluster_comm";
    protected static final String SERVICE_NAME = "com.ts.appservice.cluster/.service.ClusterCoreService";
    private static final String TAG = "ClusterCommManager";
    private static volatile ClusterCommManager sInstance = null;
    private ClusterManagerServiceConnection mClusterManagerServiceConnection;
    private Context mContext;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private BroadcastReceiver mReceiver;
    private IClusterCommService mService = null;
    private final Object mServiceLock = new Object();
    private final Object mLocalCbLock = new Object();
    private HashMap mLocalCb = new HashMap();

    private ClusterCommManager(Context context) {
        this.mContext = null;
        this.mHandlerThread = null;
        this.mHandler = null;
        this.mReceiver = null;
        LogUtil.logD(TAG, "ClusterCommManager init");
        this.mContext = context;
        this.mReceiver = new BroadcastReceiver() { // from class: com.ts.lib.cluster.ClusterCommManager.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
            }
        };
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        tryConnectService();
    }

    public static synchronized ClusterCommManager getInstance(Context context) {
        if (sInstance == null) {
            if (context == null) {
                return null;
            }
            sInstance = new ClusterCommManager(context.getApplicationContext());
        }
        return sInstance;
    }

    public boolean isServiceReady() {
        boolean ret;
        synchronized (this.mServiceLock) {
            if (this.mService != null) {
                ret = true;
            } else {
                ret = false;
            }
        }
        return ret;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryConnectService() {
        if (this.mClusterManagerServiceConnection == null) {
            this.mClusterManagerServiceConnection = new ClusterManagerServiceConnection();
        }
        Intent intent = new Intent();
        ComponentName componentName = ComponentName.unflattenFromString(SERVICE_NAME);
        intent.setComponent(componentName);
        IBinder binder = this.mReceiver.peekService(this.mContext, intent);
        if (binder == null) {
            this.mContext.bindService(intent, this.mClusterManagerServiceConnection, 1);
        } else {
            this.mClusterManagerServiceConnection.onServiceConnected(componentName, binder);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reRegService() {
        Iterator iter;
        synchronized (this.mLocalCbLock) {
            iter = this.mLocalCb.entrySet().iterator();
        }
        if (iter != null) {
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                int commands = ((Integer) entry.getValue()).intValue();
                IClusterCommCallback callback = (IClusterCommCallback) entry.getKey();
                LogUtil.logD(TAG, "reRegService command: " + commands);
                try {
                    if (this.mService != null) {
                        this.mService.registerCallback(commands, callback);
                    }
                } catch (Exception ex) {
                    LogUtil.logW(TAG, "registerCallback got exception:" + ex);
                }
            }
            return;
        }
        LogUtil.logW(TAG, "iter is null!");
    }

    public boolean sendCommand(int commandId, Bundle bundle) {
        try {
            if (this.mService == null) {
                return false;
            }
            if (bundle == null) {
                bundle = new Bundle();
            }
            this.mService.sendCommand(commandId, bundle);
            return true;
        } catch (RemoteException ex) {
            LogUtil.logW(TAG, "sendCommand failed:" + ex);
            return false;
        }
    }

    public boolean registerCallback(int commandId, IClusterCommCallback callback) throws RemoteException {
        int newCmds;
        boolean ret = false;
        if (callback == null) {
            LogUtil.logW(TAG, "IClusterCommCallback cannot be null!");
            return false;
        }
        synchronized (this.mLocalCbLock) {
            if (this.mLocalCb.containsKey(callback)) {
                int commands = ((Integer) this.mLocalCb.get(callback)).intValue();
                int matchCmds = commands | commandId;
                if (matchCmds != commands) {
                    newCmds = (~commands) & matchCmds;
                    this.mLocalCb.put(callback, Integer.valueOf(matchCmds));
                } else {
                    return true;
                }
            } else {
                this.mLocalCb.put(callback, Integer.valueOf(commandId));
                newCmds = commandId;
            }
            try {
                if (this.mService != null) {
                    ret = this.mService.registerCallback(newCmds, callback);
                }
            } catch (Exception ex) {
                LogUtil.logW(TAG, "registerCallback got exception:" + ex);
            }
            return ret;
        }
    }

    public boolean unregisterCallback(int commandId, IClusterCommCallback callback) throws RemoteException {
        boolean ret = false;
        if (callback == null) {
            LogUtil.logW(TAG, "unregisterCallback, IClusterCommCallback cannot be null!");
            return false;
        }
        synchronized (this.mLocalCbLock) {
            if (!this.mLocalCb.containsKey(callback)) {
                throw new IllegalArgumentException("callback should add first!");
            }
            try {
                if (this.mService != null) {
                    ret = this.mService.unregisterCallback(commandId, callback);
                }
            } catch (Exception ex) {
                LogUtil.logW(TAG, "unregisterCallback got exception:" + ex);
            }
            int commands = ((Integer) this.mLocalCb.get(callback)).intValue();
            int newcmds = (~commandId) & commands;
            if (newcmds != 0) {
                this.mLocalCb.put(callback, Integer.valueOf(newcmds));
            } else {
                this.mLocalCb.remove(callback);
            }
        }
        return ret;
    }

    public Bundle requestCacheData(int commandId, int dataType) throws RemoteException {
        Bundle bundle;
        synchronized (this.mServiceLock) {
            bundle = null;
            try {
            } catch (Exception ex) {
                LogUtil.logW(TAG, "requestCacheData got exception:" + ex);
            }
            if (this.mService != null) {
                bundle = this.mService.requestCacheData(commandId, dataType);
            }
        }
        return bundle;
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        LogUtil.logW(TAG, "remote service died, something is wrong!");
        try {
            if (this.mService != null) {
                this.mService.asBinder().unlinkToDeath(this, 0);
                this.mService = null;
            }
        } catch (Exception exception) {
            LogUtil.logW(TAG, "unlinkToDeath failed, msg:" + exception.getMessage());
        }
        tryConnectService();
    }

    /* JADX INFO: Access modifiers changed from: private */
    class ClusterManagerServiceConnection implements ServiceConnection {
        private ClusterManagerServiceConnection() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.logD(ClusterCommManager.TAG, "ClusterManagerServiceConnection onServiceConnected()");
            ClusterCommManager.this.mService = IClusterCommService.Stub.asInterface(service);
            if (ClusterCommManager.this.mService != null) {
                try {
                    ClusterCommManager.this.mService.asBinder().linkToDeath(ClusterCommManager.this, 0);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
                ClusterCommManager.this.reRegService();
                return;
            }
            LogUtil.logW(ClusterCommManager.TAG, "ClusterManagerServiceConnection mService is null!");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.logW(ClusterCommManager.TAG, "ClusterManagerServiceConnection onServiceDisconnected()");
            ClusterCommManager.this.mService = null;
            LogUtil.logW(ClusterCommManager.TAG, "tryConnectService -> reconnect");
            ClusterCommManager.this.mHandler.postDelayed(new Runnable() { // from class: com.ts.lib.cluster.-$$Lambda$ClusterCommManager$ClusterManagerServiceConnection$chz0mmkXsXyqgqz347xvPhH_2S4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onServiceDisconnected$0$ClusterCommManager$ClusterManagerServiceConnection();
                }
            }, 1000L);
        }

        public /* synthetic */ void lambda$onServiceDisconnected$0$ClusterCommManager$ClusterManagerServiceConnection() {
            ClusterCommManager.this.tryConnectService();
        }
    }
}
