package com.ts.qnxapp;

import android.os.Bundle;
import com.ts.lib.qnx.IQnx;
import com.ts.lib.qnx.IQnxCallBack;
import com.ts.qnxapp.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class QnxMessageServiceImpl extends IQnx.Stub {
    private static String TAG = "QnxMessageServiceImpl";
    private QnxMessageManager manager = null;

    protected void init() {
        LogUtil.logI(TAG, "QnxMessageServiceImpl init");
        if (this.manager == null) {
            this.manager = QnxMessageManager.getInstance();
        }
        this.manager.init();
    }

    public QnxMessageServiceImpl() {
        LogUtil.logI(TAG, "QnxMessageServiceImpl new");
    }

    public boolean sendMessage(int msgType, int subType, String content, Bundle bundle) {
        boolean ret = this.manager.sendMessage(msgType, subType, content, bundle);
        if (msgType != 1 && msgType != 2 && msgType != 3) {
            LogUtil.logI(TAG, " sendMessage called + msgType: " + msgType + " subType: " + subType + " content: " + content + " bundle: " + bundle);
        } else if (msgType == 3 && bundle.getString("nextRoadName") != null) {
            Bundle logBundle = (Bundle) bundle.clone();
            logBundle.remove("nextRoadName");
            LogUtil.logI(TAG, " sendMessage called + msgType: " + msgType + " subType: " + subType + " content: " + content + " bundle: " + logBundle);
        } else {
            LogUtil.logI(TAG, " sendMessage called + msgType: " + msgType + " subType: " + subType);
        }
        return ret;
    }

    public boolean notifyDiLinkStarted() {
        boolean ret = this.manager.notifyDiLinkStarted();
        LogUtil.logI(TAG, "notifyDiLinkStarted result = " + ret);
        return ret;
    }

    public Bundle receiveMessage(int msgType, int subType, String content, Bundle bundle) {
        LogUtil.logI(TAG, "QnxMessageService receiveMessage called, unsupport interface");
        return new Bundle();
    }

    public boolean registerCallback(int msgType, int subType, IQnxCallBack callback) {
        boolean ret = this.manager.registerCallback(msgType, subType, callback);
        LogUtil.logI(TAG, "registerCallback called msgType: " + msgType + "registerResult" + ret);
        return ret;
    }

    public boolean unregisterCallback(int msgType, int subType, IQnxCallBack callback) {
        boolean ret = this.manager.unregisterCallback(msgType, subType, callback);
        LogUtil.logI(TAG, "unregisterCallback called msgType: " + msgType + "unregisterResult" + ret);
        return ret;
    }
}
