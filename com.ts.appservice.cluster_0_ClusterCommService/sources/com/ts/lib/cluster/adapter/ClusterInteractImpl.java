package com.ts.lib.cluster.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.ts.lib.cluster.ClusterCommManager;
import com.ts.lib.cluster.adapter.someip.SomeIpClusterAdapter;
import com.ts.lib.cluster.common.IClusterCommCallback;
import com.ts.lib.cluster.reflector.ModuleFactory;
import com.ts.lib.cluster.utils.LogUtil;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ClusterInteractImpl extends ModuleFactory {
    public static final boolean IS_SOMEIP = true;
    private static final String TAG = "ClusterInteractImpl";
    private ClusterAdapter mClusterAdapter;
    private ClusterCommManager mClusterCommManager;
    private Context mContext;
    private DataHandler mHandler;
    private SomeIpClusterAdapter mSomeIpClusterAdapter;
    private HandlerThread mThread;
    private boolean mCommConnected = false;
    private ArrayList<Bundle> mPendingData = new ArrayList<>();
    private int mRegCmds = 0;
    private IClusterCommCallback.Stub mClusterCommCallback = new IClusterCommCallback.Stub() { // from class: com.ts.lib.cluster.adapter.ClusterInteractImpl.1
        public void onReceiveCommand(Bundle bundle) {
            Message msg = Message.obtain(ClusterInteractImpl.this.mHandler, 4);
            msg.setData(bundle);
            msg.sendToTarget();
        }
    };

    public void start(Context context) {
        LogUtil.logD(TAG, "start()");
        this.mContext = context;
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mThread = handlerThread;
        handlerThread.start();
        DataHandler dataHandler = new DataHandler(this.mThread.getLooper());
        this.mHandler = dataHandler;
        dataHandler.sendEmptyMessage(0);
    }

    public void stop() {
        LogUtil.logI(TAG, "ClusterInteractImpl stop called");
        this.mHandler.removeCallbacksAndMessages(null);
        this.mThread.quitSafely();
    }

    public Bundle getData(int commandId, int type) {
        return null;
    }

    private void unregisterService(int commandId) {
        ClusterCommManager clusterCommManager = this.mClusterCommManager;
        if (clusterCommManager != null) {
            clusterCommManager.unregisterCallback(commandId, this.mClusterCommCallback);
        }
    }

    private boolean sendCommand(int commandId, Bundle bundle) {
        ClusterCommManager clusterCommManager = this.mClusterCommManager;
        if (clusterCommManager != null) {
            return clusterCommManager.sendCommand(commandId, bundle);
        }
        LogUtil.logD(TAG, "sendCommand failure: " + commandId);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processPendingData() {
        if (this.mPendingData.size() > 0) {
            LogUtil.logD(TAG, "process pending data");
            for (Bundle bundle : this.mPendingData) {
                if (!this.mSomeIpClusterAdapter.handleDataToCluster(bundle)) {
                    LogUtil.logE(TAG, "pending data transfer failure");
                }
            }
            this.mPendingData.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean registerClusterService(int cmds) {
        boolean ret = false;
        ClusterCommManager clusterCommManager = this.mClusterCommManager;
        if (clusterCommManager != null) {
            ret = clusterCommManager.registerCallback(cmds, this.mClusterCommCallback);
            if (!ret) {
                this.mHandler.sendEmptyMessageDelayed(1, 500L);
            }
        } else {
            LogUtil.logE(TAG, "getInstance failure ");
        }
        return ret;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean tryConnectRemoteCluster() {
        boolean ret = this.mSomeIpClusterAdapter.initAdapterService();
        if (!ret) {
            this.mHandler.sendEmptyMessageDelayed(2, 500L);
        }
        return ret;
    }

    private final class DataHandler extends Handler {
        public DataHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            LogUtil.logD(ClusterInteractImpl.TAG, "handleMessage: " + msg.what);
            int i = msg.what;
            if (i == 0) {
                ClusterInteractImpl clusterInteractImpl = ClusterInteractImpl.this;
                clusterInteractImpl.mClusterCommManager = ClusterCommManager.getInstance(clusterInteractImpl.mContext);
                ClusterInteractImpl.this.mClusterAdapter = new ClusterAdapter(ClusterInteractImpl.this.mHandler, ClusterInteractImpl.this.mContext);
                ClusterInteractImpl.this.mSomeIpClusterAdapter = new SomeIpClusterAdapter(ClusterInteractImpl.this.mHandler, ClusterInteractImpl.this.mContext);
                ClusterInteractImpl clusterInteractImpl2 = ClusterInteractImpl.this;
                clusterInteractImpl2.mRegCmds = clusterInteractImpl2.mSomeIpClusterAdapter.getRegCmds();
                ClusterInteractImpl clusterInteractImpl3 = ClusterInteractImpl.this;
                if (clusterInteractImpl3.registerClusterService(clusterInteractImpl3.mRegCmds)) {
                    ClusterInteractImpl clusterInteractImpl4 = ClusterInteractImpl.this;
                    clusterInteractImpl4.mCommConnected = clusterInteractImpl4.tryConnectRemoteCluster();
                    return;
                }
                return;
            }
            if (i == 1) {
                ClusterInteractImpl clusterInteractImpl5 = ClusterInteractImpl.this;
                if (clusterInteractImpl5.registerClusterService(clusterInteractImpl5.mRegCmds)) {
                    ClusterInteractImpl clusterInteractImpl6 = ClusterInteractImpl.this;
                    clusterInteractImpl6.mCommConnected = clusterInteractImpl6.tryConnectRemoteCluster();
                    if (ClusterInteractImpl.this.mCommConnected) {
                        ClusterInteractImpl.this.processPendingData();
                        return;
                    }
                    return;
                }
                return;
            }
            if (i == 2) {
                ClusterInteractImpl clusterInteractImpl7 = ClusterInteractImpl.this;
                clusterInteractImpl7.mCommConnected = clusterInteractImpl7.tryConnectRemoteCluster();
                if (ClusterInteractImpl.this.mCommConnected) {
                    ClusterInteractImpl.this.processPendingData();
                    return;
                }
                return;
            }
            if (i != 4) {
                if (i == 5) {
                    Bundle bundle = msg.getData();
                    if (ClusterInteractImpl.this.mClusterCommManager != null) {
                        ClusterInteractImpl.this.mClusterCommManager.sendCommand(32, bundle);
                        return;
                    } else {
                        LogUtil.logE(ClusterInteractImpl.TAG, "send data failure ");
                        return;
                    }
                }
                return;
            }
            Bundle bundle2 = msg.getData();
            if (!ClusterInteractImpl.this.mCommConnected) {
                ClusterInteractImpl.this.mPendingData.add(bundle2);
            } else if (!ClusterInteractImpl.this.mSomeIpClusterAdapter.handleDataToCluster(bundle2)) {
                LogUtil.logE(ClusterInteractImpl.TAG, "transfer failure");
            }
        }
    }
}
