package com.ts.qnxapp.fdbusClientForAndroid;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.ts.qnxapp.MessageReceive;
import com.ts.qnxapp.util.LogUtil;
import ipc.fdbus.FdbusClient;
import ipc.fdbus.FdbusClientListener;
import ipc.fdbus.FdbusMessage;
import ipc.fdbus.SubscribeItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class QnxMessageFdbusClient {
    private static final int MSG_PRINT_SERVER_INFO = 1;
    private static final int MSG_PRINT_SERVER_INFO_DELAY_MS = 600000;
    private static final String TAG = "QnxMessageFdbusClient";
    private MessageReceive mCallBackReceive;
    private FdbusClient mClient;
    private Handler mFdbusClientHandler;
    private HandlerThread mFdbusClientThread;
    private int mMsgType;
    private Integer[] mSubscribeItems = new Integer[0];
    private HashMap<Integer, Boolean> mServerHasMap = new HashMap<>();

    public QnxMessageFdbusClient(String clientName, int msgType) {
        this.mMsgType = msgType;
        startFdbusClientThread();
        initClient(clientName);
        this.mFdbusClientHandler.sendEmptyMessageDelayed(1, 600000L);
    }

    private void initClient(String clientName) {
        this.mClient = new FdbusClient(clientName);
    }

    private void startFdbusClientThread() {
        HandlerThread handlerThread = new HandlerThread("FdbusClient");
        this.mFdbusClientThread = handlerThread;
        handlerThread.start();
        this.mFdbusClientHandler = new Handler(this.mFdbusClientThread.getLooper()) { // from class: com.ts.qnxapp.fdbusClientForAndroid.QnxMessageFdbusClient.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    QnxMessageFdbusClient.this.printServerOnlineInfo();
                    QnxMessageFdbusClient.this.mFdbusClientHandler.sendEmptyMessageDelayed(1, 600000L);
                }
            }
        };
    }

    public void setListener(Integer[] items, MessageReceive callBackReceive) {
        LogUtil.logI(TAG, "setSubscribeItems: called items = " + Arrays.toString(items) + "callBackReceive = " + callBackReceive);
        if (checkItems(items)) {
            this.mSubscribeItems = (Integer[]) Arrays.copyOf(items, items.length);
        }
        setFDbusListener();
        if (callBackReceive != null) {
            this.mCallBackReceive = callBackReceive;
        } else {
            LogUtil.logE(TAG, "setReceiveQueue callBackReceive is null ");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkItems(Integer[] items) {
        Log.e(TAG, "checkItems: called items");
        if (items == null) {
            LogUtil.logE(TAG, "setSubscribeItems: items is null");
            return false;
        }
        if (items.length > 0) {
            LogUtil.logI(TAG, "checkItems: items OK");
            return true;
        }
        LogUtil.logE(TAG, "setSubscribeItems: items.size < 0");
        return false;
    }

    private void setFDbusListener() {
        FDbusListener listener = new FDbusListener();
        FdbusClient fdbusClient = this.mClient;
        if (fdbusClient != null) {
            fdbusClient.setListener(listener);
        } else {
            LogUtil.logE(TAG, "setFDbusListener: mClient is null");
        }
    }

    public boolean sendFDbusMessageToQnx(int msgCode, Object obj) {
        FdbusClient fdbusClient = this.mClient;
        if (fdbusClient != null) {
            boolean result = fdbusClient.invokeAsync(msgCode, obj, null);
            LogUtil.logW(TAG, "sendFDbusMessageToQnx: msg_code:" + msgCode + " result:" + result + " obj:" + obj);
            return result;
        }
        LogUtil.logW(TAG, "sendFDbusMessageToQnx: mClient is null");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callBackListener(FdbusMessage msg, int flag, int msgType) {
        MessageReceive messageReceive = this.mCallBackReceive;
        if (messageReceive != null) {
            messageReceive.receiveMessage(msg, flag, msgType);
        }
    }

    public void connectToServer(String serverUrl) {
        FdbusClient fdbusClient = this.mClient;
        if (fdbusClient != null) {
            fdbusClient.connect(serverUrl);
        } else {
            LogUtil.logE(TAG, "connectToServer: mClient is null");
        }
    }

    private class FDbusListener implements FdbusClientListener {
        public FDbusListener() {
        }

        @Override // ipc.fdbus.FdbusClientListener
        public void onOnline(int sid, int qos) {
            QnxMessageFdbusClient.this.mServerHasMap.put(Integer.valueOf(sid), true);
            LogUtil.logE(QnxMessageFdbusClient.TAG, "onOnline: " + QnxMessageFdbusClient.this.mClient.endpointName() + ": onOnline is received: " + sid + ", qos: " + qos + "mMsgType = " + QnxMessageFdbusClient.this.mMsgType);
            ArrayList<SubscribeItem> subscribe_items = new ArrayList<>();
            QnxMessageFdbusClient qnxMessageFdbusClient = QnxMessageFdbusClient.this;
            if (qnxMessageFdbusClient.checkItems(qnxMessageFdbusClient.mSubscribeItems)) {
                for (Integer item : QnxMessageFdbusClient.this.mSubscribeItems) {
                    LogUtil.logI(QnxMessageFdbusClient.TAG, "onOnline: subscribe_items.add: code = " + item);
                    subscribe_items.add(SubscribeItem.newEvent(item.intValue()));
                }
                QnxMessageFdbusClient.this.mClient.subscribe(subscribe_items);
                return;
            }
            LogUtil.logE(QnxMessageFdbusClient.TAG, "onOnline: the items of subscribing is not right Stop!!!");
        }

        @Override // ipc.fdbus.FdbusClientListener
        public void onOffline(int sid, int qos) {
            LogUtil.logE(QnxMessageFdbusClient.TAG, "onOffline: " + QnxMessageFdbusClient.this.mClient.endpointName() + ": onOffline is received: " + sid + ", qos: " + qos);
            QnxMessageFdbusClient.this.mServerHasMap.remove(Integer.valueOf(sid));
        }

        @Override // ipc.fdbus.FdbusClientListener
        public void onReply(FdbusMessage msg) {
            LogUtil.logW(QnxMessageFdbusClient.TAG, "on reply messageToBundle called cold = " + msg.code() + " topic = " + msg.topic() + "mMsgType = " + QnxMessageFdbusClient.this.mMsgType + "userData :" + msg.userData() + "userByteArray" + Arrays.toString(msg.byteArray()));
            QnxMessageFdbusClient qnxMessageFdbusClient = QnxMessageFdbusClient.this;
            qnxMessageFdbusClient.callBackListener(msg, 0, qnxMessageFdbusClient.mMsgType);
        }

        @Override // ipc.fdbus.FdbusClientListener
        public void onGetEvent(FdbusMessage msg) {
            LogUtil.logD(QnxMessageFdbusClient.TAG, "onGetEvent: FdbusMessage : " + msg);
        }

        @Override // ipc.fdbus.FdbusClientListener
        public void onBroadcast(FdbusMessage msg) {
            if (QnxMessageFdbusClient.this.mMsgType != 10) {
                LogUtil.logW(QnxMessageFdbusClient.TAG, "onBroadcast messageToBundle called cold = " + msg.code() + " topic = " + msg.topic() + "mMsgType = " + QnxMessageFdbusClient.this.mMsgType + "userData :" + msg.userData() + "userByteArray" + Arrays.toString(msg.byteArray()));
            }
            QnxMessageFdbusClient qnxMessageFdbusClient = QnxMessageFdbusClient.this;
            qnxMessageFdbusClient.callBackListener(msg, 1, qnxMessageFdbusClient.mMsgType);
        }
    }

    public void handleReplyMsg(FdbusMessage msg, int flag) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void printServerOnlineInfo() {
        StringBuilder serverOnlineInfo = new StringBuilder();
        for (Map.Entry<Integer, Boolean> next : this.mServerHasMap.entrySet()) {
            Integer key = next.getKey();
            Boolean serverStatus = next.getValue();
            serverOnlineInfo.append(" info sid :" + key + "status :" + serverStatus + " -- ");
        }
        LogUtil.logE(TAG, "printServerOnlineInfo current server online type :" + this.mMsgType + ((Object) serverOnlineInfo) + " end");
    }
}
