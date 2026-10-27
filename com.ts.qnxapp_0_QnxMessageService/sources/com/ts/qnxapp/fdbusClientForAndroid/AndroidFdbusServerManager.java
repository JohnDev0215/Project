package com.ts.qnxapp.fdbusClientForAndroid;

import android.os.Bundle;
import com.ts.qnxapp.MessageReceive;
import com.ts.qnxapp.util.AnimationToBundle;
import com.ts.qnxapp.util.InteractionToBundle;
import com.ts.qnxapp.util.LogUtil;
import ipc.fdbus.Fdbus;

/* JADX INFO: loaded from: classes.dex */
public class AndroidFdbusServerManager {
    private static final String TAG = "AndroidFdbusServerManager";
    private static volatile AndroidFdbusServerManager sInstance;
    private QnxMessageFdbusServer mAnimationServer;
    private QnxMessageFdbusServer mClusterServer;
    private InteractionToBundle mInteractionToBundle = InteractionToBundle.getInstance();
    private AnimationToBundle mAnimationToBundle = AnimationToBundle.getInstance();

    public static AndroidFdbusServerManager getInstance() {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (AndroidFdbusServerManager.class) {
            if (sInstance == null) {
                sInstance = new AndroidFdbusServerManager();
            }
        }
        return sInstance;
    }

    private AndroidFdbusServerManager() {
        LogUtil.logE(TAG, "AndroidFdbusServerManager new");
    }

    public void initAll(MessageReceive callBackReceive) {
        new Fdbus();
        QnxMessageFdbusServer qnxMessageFdbusServer = new QnxMessageFdbusServer(AndroidFdbusConfig.ANIMATION_SERVER_NAME, 6);
        this.mAnimationServer = qnxMessageFdbusServer;
        qnxMessageFdbusServer.setListener(callBackReceive);
        this.mAnimationServer.bindToServer(AndroidFdbusConfig.ANIMATION_SERVER_URL);
        QnxMessageFdbusServer qnxMessageFdbusServer2 = new QnxMessageFdbusServer(AndroidFdbusConfig.CLUSTER_SERVER_NAME, 9);
        this.mClusterServer = qnxMessageFdbusServer2;
        qnxMessageFdbusServer2.setListener(callBackReceive);
        this.mClusterServer.bindToServer(AndroidFdbusConfig.CLUSTER_SERVER_URL);
    }

    public boolean sendMessageToQnx(int msgType, int subType, String content, Bundle bundle) {
        if (msgType != 1 && msgType != 2 && msgType != 3) {
            if (msgType == 6) {
                Object animationMsg = this.mAnimationToBundle.getAnimationMessage(subType, content, bundle);
                return this.mAnimationServer.notifyDiLinkStarted(subType, animationMsg);
            }
            if (msgType != 9) {
                LogUtil.logI(TAG, "sendMessage2Qnx: type is not right " + msgType);
                return false;
            }
        }
        Object msg = this.mInteractionToBundle.getInteractionMessage(subType, content, bundle);
        return this.mClusterServer.sendFDbusMessageToQnx(subType, msg);
    }
}
