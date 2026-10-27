package com.ts.appservice.cluster.service;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.ts.lib.cluster.utils.LogUtil;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class ClusterCoreService extends Service {
    private static final int BUNDLE_KEY_VALUE_ARGS_DUMP_SERVICE = 2;
    private static final int INDEX0_ARGS_DUMP_SERVICE = 0;
    private static final int INDEX1_ARGS_DUMP_SERVICE = 1;
    private static final int INDEX2_ARGS_DUMP_SERVICE = 2;
    private static final int INDEX3_ARGS_DUMP_SERVICE = 3;
    private static final int MIN_ARGS_LENGTH_DUMP_SERVICE = 4;
    private static final String TAG = "ClusterCoreService";
    private ClusterCommService mClusterCommService = null;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.logD(TAG, "onCreate");
        this.mClusterCommService = new ClusterCommService(this);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.logD(TAG, "onBind, intent: " + intent);
        return this.mClusterCommService;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogUtil.logD(TAG, "onDestroy");
        ClusterCommService clusterCommService = this.mClusterCommService;
        if (clusterCommService == null) {
            LogUtil.logD(TAG, "onDestroy, mClusterCommService is null");
        } else {
            clusterCommService.destroy();
            this.mClusterCommService = null;
        }
        Process.killProcess(Process.myPid());
    }

    @Override // android.app.Service
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        if (args == null || args.length < 4 || TextUtils.equals(Build.USER, Build.TYPE)) {
            super.dump(fd, writer, args);
        } else {
            processInnerDump(args);
        }
    }

    private void processInnerDump(String[] args) {
        try {
            int dataType = Integer.parseInt(args[1]);
            int msgType = Integer.parseInt(args[2]);
            int msgSubType = Integer.parseInt(args[3]);
            Bundle bundle = new Bundle();
            bundle.putInt("DataType", dataType);
            bundle.putInt("KEY_MSG_TYPE", msgType);
            bundle.putInt("KEY_SUB_TYPE", msgSubType);
            int length = args.length;
            for (int index = 5; index < length; index += 2) {
                String key = args[index - 1];
                int value = Integer.parseInt(args[index]);
                bundle.putInt(key, value);
            }
            int commandId = Integer.parseInt(args[0]);
            LogUtil.logD(TAG, "processInnerDump, commandId:" + commandId + ", bundle:" + bundle);
            this.mClusterCommService.sendCommand(commandId, bundle);
        } catch (Throwable ex) {
            Log.w(TAG, "processInnerDump error", ex);
        }
    }
}
