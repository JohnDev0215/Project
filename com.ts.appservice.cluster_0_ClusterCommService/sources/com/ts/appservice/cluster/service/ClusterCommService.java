package com.ts.appservice.cluster.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import com.ts.appservice.cluster.ClusterBroadcastReceiver;
import com.ts.appservice.cluster.implementation.ClusterCommImpl;
import com.ts.lib.cluster.common.IClusterCommCallback;
import com.ts.lib.cluster.common.IClusterCommService;
import com.ts.lib.cluster.utils.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class ClusterCommService extends IClusterCommService.Stub {
    public static final String TAG = "ClusterCommService";
    private BroadcastReceiver mBroadcastReceiver;
    private ClusterCommImpl mClusterCommImpl;
    private Context mContext;

    public ClusterCommService(Context context) {
        LogUtil.logD(TAG, "ClusterCommService init start");
        if (this.mClusterCommImpl == null) {
            this.mClusterCommImpl = new ClusterCommImpl(context);
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ClusterBroadcastReceiver.CLUSTER_TEST);
        ClusterBroadcastReceiver clusterBroadcastReceiver = new ClusterBroadcastReceiver();
        this.mBroadcastReceiver = clusterBroadcastReceiver;
        this.mContext = context;
        context.registerReceiver(clusterBroadcastReceiver, filter);
        LogUtil.logD(TAG, "ClusterCommService init end");
    }

    public void setContext(Context context) {
        LogUtil.logD(TAG, "setContext start");
        if (this.mClusterCommImpl == null) {
            this.mClusterCommImpl = new ClusterCommImpl(context);
        }
        LogUtil.logD(TAG, "setContext end");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public IBinder getBinder() {
        return this;
    }

    public void sendCommand(int commandId, Bundle bundle) {
        LogUtil.logD(TAG, "sendCommand, commandId:" + commandId);
        try {
            this.mClusterCommImpl.sendCommand(commandId, bundle);
        } catch (Exception exception) {
            LogUtil.logW(TAG, exception.getMessage());
        }
    }

    public Bundle requestCacheData(int commandId, int dataType) {
        LogUtil.logD(TAG, "requestCacheData, commandId:" + commandId + "dataType:" + dataType);
        try {
            Bundle bundle = this.mClusterCommImpl.requestCacheData(commandId, dataType);
            return bundle;
        } catch (Exception exception) {
            LogUtil.logW(TAG, exception.getMessage());
            return null;
        }
    }

    public boolean registerCallback(int commandId, IClusterCommCallback callback) {
        LogUtil.logD(TAG, "registerCallback");
        try {
            boolean ret = this.mClusterCommImpl.registerClient(commandId, callback);
            return ret;
        } catch (Exception exception) {
            LogUtil.logW(TAG, exception.getMessage());
            return false;
        }
    }

    public boolean unregisterCallback(int commandId, IClusterCommCallback callback) {
        LogUtil.logD(TAG, "unregisterCallback");
        try {
            boolean ret = this.mClusterCommImpl.unregisterClient(commandId, callback);
            return ret;
        } catch (Exception exception) {
            LogUtil.logW(TAG, exception.getMessage());
            return false;
        }
    }

    public void destroy() {
        this.mContext.unregisterReceiver(this.mBroadcastReceiver);
        ClusterCommImpl clusterCommImpl = this.mClusterCommImpl;
        if (clusterCommImpl != null) {
            clusterCommImpl.quitSafty();
        } else {
            LogUtil.logD(TAG, "destroy: mClusterCommImpl is null");
        }
    }
}
