package com.ts.qnxapp.fdbusClientForAndroid;

import android.os.Bundle;
import com.ts.qnxapp.MessageReceive;
import com.ts.qnxapp.util.LogUtil;
import com.ts.qnxapp.util.OtaToBundle;
import com.ts.qnxapp.util.PowerToBundle;
import ipc.fdbus.Fdbus;

/* JADX INFO: loaded from: classes.dex */
public class AndroidFdbusClientManager {
    private static final String TAG = "AndroidFdbusClientManager";
    private static volatile AndroidFdbusClientManager sInstance;
    private QnxMessageFdbusClient mOTAClient;
    private QnxMessageFdbusClient mPowerClient;
    private QnxMessageFdbusClient mTemperatureClient;
    private OtaToBundle mOtaToBundle = OtaToBundle.getInstance();
    private PowerToBundle mPowerToBundle = PowerToBundle.getInstance();

    public static AndroidFdbusClientManager getInstance() {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (AndroidFdbusClientManager.class) {
            if (sInstance == null) {
                sInstance = new AndroidFdbusClientManager();
            }
        }
        return sInstance;
    }

    private AndroidFdbusClientManager() {
        LogUtil.logE(TAG, "AndroidFdbusClientManager new");
    }

    public void initAll(MessageReceive callBackReceive) {
        new Fdbus();
        QnxMessageFdbusClient qnxMessageFdbusClient = new QnxMessageFdbusClient(AndroidFdbusConfig.OTA_CLIENT_URL, 8);
        this.mOTAClient = qnxMessageFdbusClient;
        qnxMessageFdbusClient.setListener(AndroidFdbusConfig.mOTASubIdsArr, callBackReceive);
        this.mOTAClient.connectToServer(AndroidFdbusConfig.OTA_SERVER_URL);
        QnxMessageFdbusClient qnxMessageFdbusClient2 = new QnxMessageFdbusClient(AndroidFdbusConfig.TEMPERATURE_CLIENT_NAME, 10);
        this.mTemperatureClient = qnxMessageFdbusClient2;
        qnxMessageFdbusClient2.setListener(AndroidFdbusConfig.mTemperatureSubIdsArr, callBackReceive);
        this.mTemperatureClient.connectToServer(AndroidFdbusConfig.TEMPERATURE_SERVER_URL);
        QnxMessageFdbusClient qnxMessageFdbusClient3 = new QnxMessageFdbusClient(AndroidFdbusConfig.POWER_CLIENT_NAME, 4);
        this.mPowerClient = qnxMessageFdbusClient3;
        qnxMessageFdbusClient3.setListener(null, callBackReceive);
        this.mPowerClient.connectToServer(AndroidFdbusConfig.POWER_SERVER_URL);
    }

    public boolean sendMessageToQnx(int msgType, int subType, String content, Bundle bundle) {
        if (msgType == 4) {
            Object msg = this.mPowerToBundle.getPowerMessage(subType, content, bundle);
            return this.mPowerClient.sendFDbusMessageToQnx(subType, msg);
        }
        if (msgType == 8) {
            Object msg2 = this.mOtaToBundle.getOtaMessage(subType, content, bundle);
            return this.mOTAClient.sendFDbusMessageToQnx(subType, msg2);
        }
        LogUtil.logI(TAG, "sendMessage2Qnx: type is not right " + msgType);
        return false;
    }
}
