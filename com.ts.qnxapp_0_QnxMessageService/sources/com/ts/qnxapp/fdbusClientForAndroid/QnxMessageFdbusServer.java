package com.ts.qnxapp.fdbusClientForAndroid;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemProperties;
import com.ts.qnxapp.MessageReceive;
import com.ts.qnxapp.util.InteractionToBundle;
import com.ts.qnxapp.util.LogUtil;
import ipc.fdbus.FdbusMessage;
import ipc.fdbus.FdbusServer;
import ipc.fdbus.FdbusServerListener;
import ipc.fdbus.SubscribeItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class QnxMessageFdbusServer {
    private static final String BOOT_COMPLETE_STATUS = "sys.boot_completed";
    private static final String BOOT_COMPLETE_SUCCESS = "1";
    private static final int CHIME_SOUND_CMD_QUER = 5;
    private static final int MSG_BOOT_COMPLETE_DELAY_MS = 100;
    private static final int MSG_CHECK_BOOT_COMPLETE_STATUS = 2;
    private static final int MSG_PRINT_CLIENT_INFO = 1;
    private static final int MSG_PRINT_CLIENT_INFO_DELAY_MS = 600000;
    private static final String QUERY_NUMBER = "queryNumber";
    private static final int SETTING_HOTKEYADAS_CMD_QUERY = 3;
    private static final int SETTING_HOTKEYINSDISPLAY_CMD_QUERY = 2;
    private static final int SETTING_HOTKEYNAVI_CMD_QUERY = 1;
    private static final int SETTING_HOTKEYTHEME_CMD_QUERY = 4;
    private static final int SETTING_THEME_CMD_QUERY = 0;
    private static final int STARTUP_QUERY = 13;
    private static final String TAG = "QnxMessageFdbusServer";
    private Object mAniMessage;
    private int mAniMsgCode;
    private boolean mAniStatus;
    private MessageReceive mCallBackReceive;
    private boolean mClusterOnlineStatus;
    private Handler mFdbusHandler;
    private HandlerThread mFdbusServerThread;
    private InteractionToBundle mInteractionToBundle;
    private Object mMapStateMessage;
    private int mMapStateMsgCode;
    private int mMsgType;
    private Object mScreenOrientationMessage;
    private int mScreenOrientationMsgCode;
    private FdbusServer mServer;
    private boolean mStatusFromSystemUi;
    private String mTopic;
    private Integer[] mSubscribeItems = new Integer[0];
    private HashMap<Integer, ArrayList<Integer>> mClientHasMap = new HashMap<>();

    public QnxMessageFdbusServer(String serverName, int msgType) {
        this.mMsgType = msgType;
        startFdbusThread();
        initServer(serverName);
        this.mInteractionToBundle = InteractionToBundle.getInstance();
        this.mFdbusHandler.sendEmptyMessageDelayed(1, 600000L);
    }

    private void startFdbusThread() {
        HandlerThread handlerThread = new HandlerThread("FdbusServer");
        this.mFdbusServerThread = handlerThread;
        handlerThread.start();
        this.mFdbusHandler = new Handler(this.mFdbusServerThread.getLooper()) { // from class: com.ts.qnxapp.fdbusClientForAndroid.QnxMessageFdbusServer.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int i = msg.what;
                if (i == 1) {
                    QnxMessageFdbusServer.this.printClientOnlineInfo();
                    QnxMessageFdbusServer.this.mFdbusHandler.sendEmptyMessageDelayed(1, 600000L);
                } else if (i == 2) {
                    QnxMessageFdbusServer qnxMessageFdbusServer = QnxMessageFdbusServer.this;
                    qnxMessageFdbusServer.notifyDiLinkStarted(qnxMessageFdbusServer.mAniMsgCode, QnxMessageFdbusServer.this.mAniMessage);
                }
            }
        };
    }

    private void initServer(String serverName) {
        this.mServer = new FdbusServer(serverName);
    }

    public void setListener(MessageReceive callBackReceive) {
        LogUtil.logI(TAG, "setSubscribeItems callBackReceive =:" + callBackReceive);
        setFDbusListener();
        if (callBackReceive != null) {
            this.mCallBackReceive = callBackReceive;
        } else {
            LogUtil.logE(TAG, "setReceiveQueue callBackReceive is null ");
        }
    }

    private boolean checkItems(Integer[] items) {
        LogUtil.logE(TAG, "checkItems: called items");
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
        FdbusServer fdbusServer = this.mServer;
        if (fdbusServer != null) {
            fdbusServer.setListener(listener);
        } else {
            LogUtil.logE(TAG, "setFDbusListener: mClient is null");
        }
    }

    public boolean sendFDbusMessageToQnx(int msgCode, Object obj) {
        boolean result = false;
        if (this.mMsgType == 9 && msgCode == 17) {
            FdbusServer fdbusServer = this.mServer;
            if (fdbusServer != null) {
                result = fdbusServer.broadcast(msgCode, obj);
                LogUtil.logW(TAG, "sendFDbusMessageToQnx screen broadcast : msg_code:" + msgCode + "result:" + result + " obj : " + obj);
            } else {
                LogUtil.logW(TAG, "sendFDbusMessageToQnx screen : Server is null");
            }
            this.mScreenOrientationMsgCode = msgCode;
            this.mScreenOrientationMessage = obj;
        } else {
            FdbusServer fdbusServer2 = this.mServer;
            if (fdbusServer2 != null) {
                result = fdbusServer2.broadcast(msgCode, obj);
                if (this.mMsgType == 9 && (msgCode == 6 || msgCode == 5 || msgCode == 4 || msgCode == 20 || msgCode == 7)) {
                    LogUtil.logI(TAG, "sendFDbusMessageToQnx broadcast : msg_code:" + msgCode + "result:" + result);
                } else {
                    LogUtil.logW(TAG, "sendFDbusMessageToQnx broadcast : msg_code:" + msgCode + " result:" + result + " obj : " + obj);
                    if (!this.mClusterOnlineStatus && msgCode == 15) {
                        LogUtil.logE(TAG, "sendFDbusMessageToQnx because of ClusterOnlineStatus is false to save map state msg");
                        this.mMapStateMsgCode = msgCode;
                        this.mMapStateMessage = obj;
                    }
                }
            } else {
                LogUtil.logW(TAG, "sendFDbusMessageToQnx: Server is null");
            }
        }
        return result;
    }

    public boolean notifyDiLinkStarted(int msgCode, Object obj) {
        if (isBootComplete()) {
            FdbusServer fdbusServer = this.mServer;
            if (fdbusServer != null && this.mAniStatus) {
                boolean result = fdbusServer.broadcast(msgCode, obj);
                LogUtil.logW(TAG, "notifyDiLinkStarted broadcast : msg_code:" + msgCode + "result:" + result + " obj: " + obj);
                return result;
            }
            LogUtil.logW(TAG, "notifyDiLinkStarted: Server is null and no Animation register");
            this.mAniMsgCode = msgCode;
            this.mAniMessage = obj;
            this.mStatusFromSystemUi = true;
            return true;
        }
        LogUtil.logI(TAG, "notifyDiLinkStarted: is not boot complete");
        this.mAniMsgCode = msgCode;
        this.mAniMessage = obj;
        this.mFdbusHandler.sendEmptyMessageDelayed(2, 100L);
        return true;
    }

    private boolean isBootComplete() {
        String propValue = SystemProperties.get(BOOT_COMPLETE_STATUS);
        LogUtil.logI(TAG, "isBootComplete :" + propValue);
        return BOOT_COMPLETE_SUCCESS.equals(propValue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callBackListener(FdbusMessage msg, int flag, int msgType) {
        MessageReceive messageReceive = this.mCallBackReceive;
        if (messageReceive != null) {
            messageReceive.receiveMessage(msg, flag, msgType);
        }
    }

    public void bindToServer(String serverUrl) {
        FdbusServer fdbusServer = this.mServer;
        if (fdbusServer != null) {
            fdbusServer.bind(serverUrl);
        } else {
            LogUtil.logE(TAG, "bindToServer: mServer is null");
        }
    }

    private class FDbusListener implements FdbusServerListener {
        public FDbusListener() {
        }

        @Override // ipc.fdbus.FdbusServerListener
        public void onOnline(int i, boolean b, int i1) {
            LogUtil.logE(QnxMessageFdbusServer.TAG, "FDbusServer is onOnline :" + i);
            if (QnxMessageFdbusServer.this.mMsgType == 9) {
                LogUtil.logE(QnxMessageFdbusServer.TAG, "FDbusServer Cluster Start Online");
                QnxMessageFdbusServer.this.mClusterOnlineStatus = true;
            }
        }

        @Override // ipc.fdbus.FdbusServerListener
        public void onOffline(int i, boolean b, int i1) {
            LogUtil.logE(QnxMessageFdbusServer.TAG, "FDbusServer is onOffline : " + i);
            if (QnxMessageFdbusServer.this.mMsgType == 9) {
                LogUtil.logE(QnxMessageFdbusServer.TAG, "FDbusServer Cluster Down Online");
                QnxMessageFdbusServer.this.mClusterOnlineStatus = false;
            }
            QnxMessageFdbusServer.this.mClientHasMap.remove(Integer.valueOf(i));
        }

        @Override // ipc.fdbus.FdbusServerListener
        public void onInvoke(FdbusMessage fdbusMessage) {
            LogUtil.logE(QnxMessageFdbusServer.TAG, "onInvoke fdbus Message code :" + fdbusMessage.code() + "byteArray :" + Arrays.toString(fdbusMessage.byteArray()));
            QnxMessageFdbusServer qnxMessageFdbusServer = QnxMessageFdbusServer.this;
            qnxMessageFdbusServer.callBackListener(fdbusMessage, 0, qnxMessageFdbusServer.mMsgType);
        }

        @Override // ipc.fdbus.FdbusServerListener
        public void onSubscribe(FdbusMessage fdbusMessage, ArrayList<SubscribeItem> arrayList) {
            ArrayList<Integer> clientList = new ArrayList<>();
            for (SubscribeItem sub : arrayList) {
                clientList.add(Integer.valueOf(sub.code()));
                LogUtil.logE(QnxMessageFdbusServer.TAG, "SubscribeItem arrayList :" + sub.code());
                if (QnxMessageFdbusServer.this.mMsgType == 9 && sub.code() == 13) {
                    QnxMessageFdbusServer.this.setStartupQuery(4);
                    QnxMessageFdbusServer.this.setStartupQuery(0);
                    QnxMessageFdbusServer.this.setStartupQuery(2);
                    QnxMessageFdbusServer.this.setStartupQuery(3);
                    QnxMessageFdbusServer.this.setStartupQuery(5);
                    QnxMessageFdbusServer.this.setStartupQuery(1);
                }
                if (QnxMessageFdbusServer.this.mMsgType == 6 && sub.code() == 1) {
                    QnxMessageFdbusServer.this.mAniStatus = true;
                    if (QnxMessageFdbusServer.this.mStatusFromSystemUi && QnxMessageFdbusServer.this.mAniMessage != null) {
                        QnxMessageFdbusServer.this.mServer.broadcast(QnxMessageFdbusServer.this.mAniMsgCode, QnxMessageFdbusServer.this.mAniMessage);
                        QnxMessageFdbusServer.this.mStatusFromSystemUi = false;
                        LogUtil.logE(QnxMessageFdbusServer.TAG, "mServer start broadcast animation status " + QnxMessageFdbusServer.this.mAniMessage);
                    }
                }
                if (QnxMessageFdbusServer.this.mMsgType == 9 && sub.code() == 17 && QnxMessageFdbusServer.this.mScreenOrientationMsgCode == 17 && QnxMessageFdbusServer.this.mScreenOrientationMessage != null) {
                    QnxMessageFdbusServer.this.mServer.broadcast(QnxMessageFdbusServer.this.mScreenOrientationMsgCode, QnxMessageFdbusServer.this.mScreenOrientationMessage);
                    LogUtil.logE(QnxMessageFdbusServer.TAG, "mServer start broadcast  :" + QnxMessageFdbusServer.this.mScreenOrientationMsgCode + "screen orientation :" + QnxMessageFdbusServer.this.mScreenOrientationMessage);
                }
                if (QnxMessageFdbusServer.this.mMsgType == 9 && sub.code() == 15 && QnxMessageFdbusServer.this.mMapStateMsgCode == 15 && QnxMessageFdbusServer.this.mMapStateMessage != null) {
                    QnxMessageFdbusServer.this.mServer.broadcast(QnxMessageFdbusServer.this.mMapStateMsgCode, QnxMessageFdbusServer.this.mMapStateMessage);
                    LogUtil.logE(QnxMessageFdbusServer.TAG, "mServer start broadcast map msg code:" + QnxMessageFdbusServer.this.mMapStateMsgCode + "map state :" + QnxMessageFdbusServer.this.mMapStateMessage);
                    QnxMessageFdbusServer.this.mMapStateMessage = null;
                    QnxMessageFdbusServer.this.mMapStateMsgCode = 0;
                }
            }
            QnxMessageFdbusServer.this.mClientHasMap.put(Integer.valueOf(fdbusMessage.sid()), clientList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStartupQuery(int queryValue) {
        Bundle bundle = new Bundle();
        bundle.putInt(QUERY_NUMBER, queryValue);
        Object msg = this.mInteractionToBundle.getInteractionMessage(13, null, bundle);
        sendFDbusMessageToQnx(13, msg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void printClientOnlineInfo() {
        StringBuilder clientOnlineInfo = new StringBuilder();
        for (Map.Entry<Integer, ArrayList<Integer>> next : this.mClientHasMap.entrySet()) {
            Integer key = next.getKey();
            ArrayList<Integer> clientEventId = next.getValue();
            clientOnlineInfo.append(" info sid :" + key + "client event id :" + clientEventId + " -- ");
        }
        LogUtil.logE(TAG, "printClientOnlineInfo current client online type :" + this.mMsgType + ((Object) clientOnlineInfo) + " end");
    }
}
