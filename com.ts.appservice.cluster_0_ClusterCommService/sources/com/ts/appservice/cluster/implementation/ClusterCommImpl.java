package com.ts.appservice.cluster.implementation;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.provider.Settings;
import com.ts.lib.cluster.adapter.ClusterInteractImpl;
import com.ts.lib.cluster.common.IClusterCommCallback;
import com.ts.lib.cluster.common.data.Constants;
import com.ts.lib.cluster.reflector.ModuleFactory;
import com.ts.lib.cluster.utils.LogUtil;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class ClusterCommImpl {
    private static final int MESSAGE_DELAY_TIME = 100;
    private static final int MESSAGE_SEND_COMMAND = 1;
    private static final int MESSAGE_START_LOADING = 0;
    private static final int SUB_TYPE_NAVI_SEND_NAVI_STATE_2_AVM = 103;
    private static final String TAG = "ClusterCommImpl";
    private ClusterImplHandler mClusterImplHandler;
    private Context mContext;
    private HashMap<Integer, RemoteCallbackList<IClusterCommCallback>> mCallbacks = new HashMap<>();
    private HandlerThread mClusterThread = new HandlerThread(TAG);

    public ClusterCommImpl(Context context) {
        this.mContext = context;
        startClusterThread();
    }

    private void startClusterThread() {
        this.mClusterThread.start();
        ClusterImplHandler clusterImplHandler = new ClusterImplHandler(this.mClusterThread.getLooper());
        this.mClusterImplHandler = clusterImplHandler;
        Message msg = Message.obtain(clusterImplHandler, 0);
        this.mClusterImplHandler.sendMessageDelayed(msg, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadInternalModule() {
        loadClusterInterfaceMoudle();
    }

    private void loadClusterInterfaceMoudle() {
        ModuleFactory module = null;
        try {
            module = (ModuleFactory) ClusterInteractImpl.class.newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
            LogUtil.logW(TAG, "load  ModuleFactory failed");
        } catch (Throwable ta) {
            ta.printStackTrace();
            LogUtil.logW(TAG, "load  ModuleFactory failed");
        }
        if (module != null) {
            module.start(this.mContext);
        }
    }

    public Bundle requestCacheData(int commandId, int dataType) {
        if (!Constants.Command.isAvailableCmd(commandId)) {
            LogUtil.logW(TAG, "wrong commandId: " + commandId);
            return null;
        }
        return null;
    }

    public boolean sendCommand(int commandId, Bundle bundle) {
        Message message = Message.obtain(this.mClusterImplHandler, 1);
        message.arg1 = commandId;
        message.setData(bundle);
        message.sendToTarget();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean notifyListener(int commandId, Bundle bundle) {
        if (!Constants.Command.isAvailableCmd(commandId)) {
            LogUtil.logW(TAG, "commandId not found" + commandId);
            return false;
        }
        synchronized (this.mCallbacks) {
            RemoteCallbackList<IClusterCommCallback> list = this.mCallbacks.get(Integer.valueOf(commandId));
            if (list == null || list.getRegisteredCallbackCount() == 0) {
                LogUtil.logD(TAG, "no callbacks. commandId:" + commandId);
                return true;
            }
            try {
                try {
                    int count = list.beginBroadcast();
                    for (int i = 0; i < count; i++) {
                        list.getBroadcastItem(i).onReceiveCommand(bundle);
                    }
                    return true;
                } finally {
                    try {
                        list.finishBroadcast();
                    } catch (Exception exception) {
                        LogUtil.logW(TAG, exception.getMessage());
                    }
                }
            } catch (Exception exception2) {
                LogUtil.logW(TAG, exception2.getMessage());
                try {
                    list.finishBroadcast();
                } catch (Exception exception3) {
                    LogUtil.logW(TAG, exception3.getMessage());
                }
                return false;
            }
        }
    }

    public boolean registerClient(int commandIds, IClusterCommCallback callback) {
        int validCmd = commandIds & 127;
        if (validCmd == 0) {
            LogUtil.logW(TAG, "no valid commands");
            return false;
        }
        int[] list = Constants.Command.getCmdList();
        int index = 0;
        while (validCmd > 0) {
            if ((list[index] & validCmd) == list[index]) {
                if (!addListener(list[index], callback)) {
                    return false;
                }
                validCmd -= list[index];
            }
            index++;
        }
        return true;
    }

    private boolean addListener(int commandId, IClusterCommCallback callback) {
        try {
            synchronized (this.mCallbacks) {
                LogUtil.logD(TAG, "add Listener, commandId:" + commandId);
                RemoteCallbackList<IClusterCommCallback> list = this.mCallbacks.get(Integer.valueOf(commandId));
                if (list == null) {
                    list = new RemoteCallbackList<>();
                    this.mCallbacks.put(Integer.valueOf(commandId), list);
                }
                list.register(callback);
            }
            return true;
        } catch (Exception exception) {
            LogUtil.logW(TAG, exception.getMessage());
            return false;
        }
    }

    public boolean unregisterClient(int commandId, IClusterCommCallback callback) {
        try {
            synchronized (this.mCallbacks) {
                if (this.mCallbacks.get(Integer.valueOf(commandId)) != null) {
                    LogUtil.logD(TAG, "unregister client callback, commandId:" + commandId);
                    this.mCallbacks.get(Integer.valueOf(commandId)).unregister(callback);
                }
            }
            return true;
        } catch (Exception exception) {
            LogUtil.logW(TAG, exception.getMessage());
            return false;
        }
    }

    private final class ClusterImplHandler extends Handler {
        public ClusterImplHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            LogUtil.logD(ClusterCommImpl.TAG, "handleMessage: " + msg.what);
            int i = msg.what;
            if (i == 0) {
                ClusterCommImpl.this.loadInternalModule();
                return;
            }
            if (i == 1) {
                int commandId = msg.arg1;
                Bundle bundle = msg.getData();
                ClusterCommImpl.this.checkData(bundle);
                if (!ClusterCommImpl.this.notifyListener(commandId, bundle)) {
                    LogUtil.logW(ClusterCommImpl.TAG, "send message failure");
                    return;
                }
                return;
            }
            LogUtil.logW(ClusterCommImpl.TAG, "Could not handleMessage: " + msg.what);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkData(Bundle bundle) {
        if (bundle == null) {
            LogUtil.logE(TAG, "checkData bundle is null");
            return;
        }
        LogUtil.logD(TAG, "checkData dataType = " + bundle.getInt("DataType"));
        if (SUB_TYPE_NAVI_SEND_NAVI_STATE_2_AVM == bundle.getInt("DataType")) {
            ContentResolver resolver = this.mContext.getContentResolver();
            int mapType = bundle.getInt("mapType", -1);
            int naviState = bundle.getInt("naviState", -1);
            int isForeground = bundle.getInt("isForeground", -1);
            String mapSource = bundle.getString("mapSource", "");
            Settings.System.putInt(resolver, "mapType", mapType);
            Settings.System.putInt(resolver, "naviState", naviState);
            Settings.System.putInt(resolver, "isForeground", isForeground);
            Settings.System.putString(resolver, "mapSource", mapSource);
            LogUtil.logI(TAG, "SET_NAVI_STATE_2_AVM Success");
        }
    }

    public void quitSafty() {
        this.mClusterThread.quitSafely();
    }
}
