package com.ts.appservice.cluster;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.ts.lib.cluster.ClusterCommManager;
import com.ts.lib.cluster.utils.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class ClusterBroadcastReceiver extends BroadcastReceiver {
    public static final String CLUSTER_TEST = "clusterTest";
    public static final String COMMAND_ID = "commandId";
    public static final int DEFAULT = -1;
    private static final String TAG = "ClusterBroadcastReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if (((action.hashCode() == -1141555796 && action.equals(CLUSTER_TEST)) ? (byte) 0 : (byte) -1) == 0) {
            LogUtil.logD(TAG, ">>>>>CLUSTER_TEST start");
            int commandId = intent.getIntExtra(COMMAND_ID, -1);
            int dateType = intent.getIntExtra("DataType", -1);
            LogUtil.logD(TAG, ">>>>>CLUSTER_TEST commandId:" + commandId + " dateType:" + dateType);
            Bundle bundle = new Bundle();
            bundle.putInt("DataType", dateType);
            if (dateType == 1) {
                bundle.putInt("MusicSource", intent.getIntExtra("MusicSource", -1));
                bundle.putString("MediaName", intent.getStringExtra("MediaName"));
                bundle.putString("MusicSource", intent.getStringExtra("MusicSource"));
            } else if (dateType == 16) {
                bundle.putInt("NaviSource", intent.getIntExtra("NaviSource", -1));
                bundle.putInt("NaviIconTbt", intent.getIntExtra("NaviIconTbt", -1));
                bundle.putString("NaviSource", intent.getStringExtra("NaviSource"));
            } else if (dateType == 20) {
                bundle.putInt("ClusterThemeType", intent.getIntExtra("ClusterThemeType", -1));
            } else {
                LogUtil.logW(TAG, ">>>>>CLUSTER_TEST dateType error :" + dateType);
            }
            ClusterCommManager.getInstance(context).sendCommand(commandId, bundle);
            LogUtil.logD(TAG, ">>>>>CLUSTER_TEST end");
        }
    }
}
