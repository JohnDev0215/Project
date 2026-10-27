package com.android.bluetooth.sap;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Icon;
import android.hardware.radio.V1_0.ISap;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.telephony.TelephonyManager;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.android.bluetooth.R;
import com.android.bluetooth.avrcpcontroller.AvrcpControllerStateMachine;
import com.android.bluetooth.mapapi.BluetoothMapContract;
import com.android.vcard.VCardConfig;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes.dex */
public class SapServer extends Thread implements Handler.Callback {
    public static final boolean DEBUG = false;
    private static final int DISCONNECT_TIMEOUT_IMMEDIATE = 5000;
    private static final int DISCONNECT_TIMEOUT_RFCOMM = 2000;
    public static final int ISAP_GET_SERVICE_DELAY_MILLIS = 3000;
    public static final int NOTIFICATION_ID = 17301632;
    public static final String SAP_DISCONNECT_ACTION = "com.android.bluetooth.sap.action.DISCONNECT_ACTION";
    public static final String SAP_DISCONNECT_TYPE_EXTRA = "com.android.bluetooth.sap.extra.DISCONNECT_TYPE";
    public static final int SAP_MSG_RFC_REPLY = 0;
    public static final int SAP_MSG_RIL_CONNECT = 1;
    public static final int SAP_MSG_RIL_IND = 3;
    public static final int SAP_MSG_RIL_REQ = 2;
    private static final String SAP_NOTIFICATION_CHANNEL = "sap_notification_channel";
    public static final int SAP_PROXY_DEAD = 5;
    public static final int SAP_RIL_SOCK_CLOSED = 4;
    private static final String TAG = "SapServer";
    private static final String TAG_HANDLER = "SapServerHandler";
    public static final boolean VERBOSE = false;
    private Context mContext;
    private BroadcastReceiver mIntentReceiver;
    private BufferedInputStream mRfcommIn;
    private BufferedOutputStream mRfcommOut;
    private Handler mSapServiceHandler;
    private SAP_STATE mState = SAP_STATE.DISCONNECTED;
    private SapRilReceiver mRilBtReceiver = null;
    private Handler mSapHandler = null;
    private HandlerThread mHandlerThread = null;
    private boolean mIsLocalInitDisconnect = false;
    private CountDownLatch mDeinitSignal = new CountDownLatch(1);
    private PendingIntent mPendingDiscIntent = null;
    private int mMaxMsgSize = 0;
    private int mTestMode = -1;

    private enum SAP_STATE {
        DISCONNECTED,
        CONNECTING,
        CONNECTING_CALL_ONGOING,
        CONNECTED,
        CONNECTED_BUSY,
        DISCONNECTING
    }

    public SapServer(Handler serviceHandler, Context context, InputStream inStream, OutputStream outStream) {
        this.mContext = null;
        this.mRfcommOut = null;
        this.mRfcommIn = null;
        this.mSapServiceHandler = null;
        this.mContext = context;
        this.mSapServiceHandler = serviceHandler;
        this.mRfcommIn = new BufferedInputStream(inStream);
        this.mRfcommOut = new BufferedOutputStream(outStream);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.PHONE_STATE");
        filter.addAction(SAP_DISCONNECT_ACTION);
        SapServerBroadcastReceiver sapServerBroadcastReceiver = new SapServerBroadcastReceiver();
        this.mIntentReceiver = sapServerBroadcastReceiver;
        this.mContext.registerReceiver(sapServerBroadcastReceiver, filter);
    }

    private class SapServerBroadcastReceiver extends BroadcastReceiver {
        private SapServerBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String state;
            if (intent.getAction().equals("android.intent.action.PHONE_STATE")) {
                if (SapServer.this.mState == SAP_STATE.CONNECTING_CALL_ONGOING && (state = intent.getStringExtra(AvrcpControllerStateMachine.KEY_STATE)) != null && state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                    SapMessage fakeConReq = new SapMessage(0);
                    fakeConReq.setMaxMsgSize(SapServer.this.mMaxMsgSize);
                    SapServer.this.onConnectRequest(fakeConReq);
                    return;
                }
                return;
            }
            if (intent.getAction().equals(SapServer.SAP_DISCONNECT_ACTION)) {
                int disconnectType = intent.getIntExtra(SapServer.SAP_DISCONNECT_TYPE_EXTRA, 0);
                Log.v(SapServer.TAG, " - Received SAP_DISCONNECT_ACTION type: " + disconnectType);
                if (disconnectType == 257) {
                    SapServer.this.shutdown();
                    return;
                } else {
                    if (SapServer.this.mState != SAP_STATE.DISCONNECTED && SapServer.this.mState != SAP_STATE.DISCONNECTING) {
                        SapServer.this.sendDisconnectInd(disconnectType);
                        return;
                    }
                    return;
                }
            }
            Log.w(SapServer.TAG, "RIL-BT received unexpected Intent: " + intent.getAction());
        }
    }

    public void setTestMode(int testMode) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendDisconnectInd(int discType) {
        if (discType != 256) {
            SapMessage discInd = new SapMessage(4);
            discInd.setDisconnectionType(discType);
            sendClientMessage(discInd);
            if (discType == 0) {
                setNotification(1, VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES);
                return;
            } else {
                if (discType == 1) {
                    startDisconnectTimer(256, 5000);
                    return;
                }
                return;
            }
        }
        SapMessage msg = new SapMessage(2);
        clearPendingRilResponses(msg);
        changeState(SAP_STATE.DISCONNECTING);
        sendRilThreadMessage(msg);
        this.mIsLocalInitDisconnect = true;
    }

    void setNotification(int type, int flags) {
        String title;
        String button;
        String text;
        String ticker;
        Notification notification;
        NotificationManager notificationManager = (NotificationManager) this.mContext.getSystemService(BluetoothMapContract.RECEPTION_STATE_NOTIFICATION);
        NotificationChannel notificationChannel = new NotificationChannel(SAP_NOTIFICATION_CHANNEL, this.mContext.getString(R.string.bluetooth_sap_notif_title), 4);
        notificationManager.createNotificationChannel(notificationChannel);
        int flags2 = flags | VCardConfig.FLAG_APPEND_TYPE_PARAM;
        String ptsEnabled = SystemProperties.get("bt.sap.pts");
        Boolean ptsTest = Boolean.valueOf(Boolean.parseBoolean(ptsEnabled));
        Intent sapDisconnectIntent = new Intent(SAP_DISCONNECT_ACTION);
        if (type == 0) {
            title = this.mContext.getString(R.string.bluetooth_sap_notif_title);
            button = this.mContext.getString(R.string.bluetooth_sap_notif_disconnect_button);
            text = this.mContext.getString(R.string.bluetooth_sap_notif_message);
            ticker = this.mContext.getString(R.string.bluetooth_sap_notif_ticker);
        } else {
            title = this.mContext.getString(R.string.bluetooth_sap_notif_title);
            button = this.mContext.getString(R.string.bluetooth_sap_notif_force_disconnect_button);
            text = this.mContext.getString(R.string.bluetooth_sap_notif_disconnecting);
            ticker = this.mContext.getString(R.string.bluetooth_sap_notif_ticker);
        }
        if (!ptsTest.booleanValue()) {
            sapDisconnectIntent.putExtra(SAP_DISCONNECT_TYPE_EXTRA, type);
            PendingIntent pIntentDisconnect = PendingIntent.getBroadcast(this.mContext, type, sapDisconnectIntent, flags2);
            Notification.Action actionDisconnect = new Notification.Action.Builder(Icon.createWithResource(this.mContext, 17301632), button, pIntentDisconnect).build();
            notification = new Notification.Builder(this.mContext, SAP_NOTIFICATION_CHANNEL).setOngoing(true).addAction(actionDisconnect).setContentTitle(title).setTicker(ticker).setContentText(text).setSmallIcon(17301632).setAutoCancel(false).setOnlyAlertOnce(true).setLocalOnly(true).build();
        } else {
            sapDisconnectIntent.putExtra(SAP_DISCONNECT_TYPE_EXTRA, 0);
            Intent sapForceDisconnectIntent = new Intent(SAP_DISCONNECT_ACTION);
            sapForceDisconnectIntent.putExtra(SAP_DISCONNECT_TYPE_EXTRA, 1);
            PendingIntent pIntentDisconnect2 = PendingIntent.getBroadcast(this.mContext, 0, sapDisconnectIntent, flags2);
            PendingIntent pIntentForceDisconnect = PendingIntent.getBroadcast(this.mContext, 1, sapForceDisconnectIntent, flags2);
            Notification.Action actionDisconnect2 = new Notification.Action.Builder(Icon.createWithResource(this.mContext, 17301632), this.mContext.getString(R.string.bluetooth_sap_notif_disconnect_button), pIntentDisconnect2).build();
            Icon iconCreateWithResource = Icon.createWithResource(this.mContext, 17301632);
            String ptsEnabled2 = this.mContext.getString(R.string.bluetooth_sap_notif_force_disconnect_button);
            Notification.Action actionForceDisconnect = new Notification.Action.Builder(iconCreateWithResource, ptsEnabled2, pIntentForceDisconnect).build();
            notification = new Notification.Builder(this.mContext, SAP_NOTIFICATION_CHANNEL).setOngoing(true).addAction(actionDisconnect2).addAction(actionForceDisconnect).setContentTitle(title).setTicker(ticker).setContentText(text).setSmallIcon(17301632).setAutoCancel(false).setOnlyAlertOnce(true).setLocalOnly(true).build();
        }
        notification.flags |= 40;
        notificationManager.notify(17301632, notification);
    }

    void clearNotification() {
        NotificationManager notificationManager = (NotificationManager) this.mContext.getSystemService(BluetoothMapContract.RECEPTION_STATE_NOTIFICATION);
        notificationManager.cancel(17301632);
    }

    /* JADX WARN: Removed duplicated region for block: B:224:0x033b A[PHI: r1
  0x033b: PHI (r1v46 android.os.Handler) = (r1v10 android.os.Handler), (r1v21 android.os.Handler), (r1v32 android.os.Handler), (r1v57 android.os.Handler) binds: [B:133:0x0227, B:223:0x0339, B:178:0x02b1, B:86:0x019c] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            Method dump skipped, instruction units count: 983
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.sap.SapServer.run():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConnectRequest(SapMessage msg) {
        SapMessage reply = new SapMessage(1);
        if (this.mState == SAP_STATE.CONNECTING) {
            reply = null;
            sendRilMessage(msg);
            stopDisconnectTimer();
        } else if (this.mState != SAP_STATE.DISCONNECTED && this.mState != SAP_STATE.CONNECTING_CALL_ONGOING) {
            reply.setConnectionStatus(1);
        } else {
            this.mMaxMsgSize = msg.getMaxMsgSize();
            if (isCallOngoing()) {
                reply.setConnectionStatus(4);
            } else {
                changeState(SAP_STATE.CONNECTING);
                SapRilReceiver sapRilReceiver = this.mRilBtReceiver;
                if (sapRilReceiver != null) {
                    sapRilReceiver.sendRilConnectMessage();
                    reply = null;
                } else {
                    reply = new SapMessage(1);
                    reply.setConnectionStatus(1);
                    sendClientMessage(reply);
                }
            }
        }
        if (reply != null) {
            sendClientMessage(reply);
        }
    }

    private void clearPendingRilResponses(SapMessage msg) {
        if (this.mState == SAP_STATE.CONNECTED_BUSY) {
            msg.setClearRilQueue(true);
        }
    }

    private void sendClientMessage(SapMessage sapMsg) {
        Message newMsg = this.mSapHandler.obtainMessage(0, sapMsg);
        this.mSapHandler.sendMessage(newMsg);
    }

    private void sendRilThreadMessage(SapMessage sapMsg) {
        Message newMsg = this.mSapHandler.obtainMessage(2, sapMsg);
        this.mSapHandler.sendMessage(newMsg);
    }

    private boolean isCallOngoing() {
        TelephonyManager tManager = (TelephonyManager) this.mContext.getSystemService("phone");
        if (tManager.getCallState() == 0) {
            return false;
        }
        return true;
    }

    private void changeState(SAP_STATE newState) {
        synchronized (this) {
            this.mState = newState;
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        int i = msg.what;
        if (i == 0) {
            handleRfcommReply((SapMessage) msg.obj);
        } else if (i == 1) {
            if (this.mTestMode != -1) {
                SapMessage rilTestModeReq = new SapMessage(513);
                rilTestModeReq.setTestMode(this.mTestMode);
                sendRilMessage(rilTestModeReq);
                this.mTestMode = -1;
            }
            SapMessage rilSapConnect = new SapMessage(0);
            rilSapConnect.setMaxMsgSize(this.mMaxMsgSize);
            sendRilMessage(rilSapConnect);
        } else if (i == 2) {
            SapMessage sapMsg = (SapMessage) msg.obj;
            if (sapMsg != null) {
                sendRilMessage(sapMsg);
            }
        } else if (i == 3) {
            handleRilInd((SapMessage) msg.obj);
        } else if (i == 4) {
            startDisconnectTimer(257, DISCONNECT_TIMEOUT_RFCOMM);
        } else {
            if (i != 5) {
                return false;
            }
            if (((Long) msg.obj).longValue() == this.mRilBtReceiver.mSapProxyCookie.get()) {
                this.mRilBtReceiver.notifyShutdown();
                this.mRilBtReceiver.resetSapProxy();
                this.mRilBtReceiver.getSapProxy();
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shutdown() {
        try {
            if (this.mRfcommOut != null) {
                this.mRfcommOut.close();
            }
        } catch (IOException e) {
        }
        try {
            if (this.mRfcommIn != null) {
                this.mRfcommIn.close();
            }
        } catch (IOException e2) {
        }
        this.mRfcommIn = null;
        this.mRfcommOut = null;
        stopDisconnectTimer();
        clearNotification();
    }

    private void startDisconnectTimer(int discType, int timeMs) {
        stopDisconnectTimer();
        synchronized (this) {
            Intent sapDisconnectIntent = new Intent(SAP_DISCONNECT_ACTION);
            sapDisconnectIntent.putExtra(SAP_DISCONNECT_TYPE_EXTRA, discType);
            AlarmManager alarmManager = (AlarmManager) this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM);
            this.mPendingDiscIntent = PendingIntent.getBroadcast(this.mContext, discType, sapDisconnectIntent, VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES);
            alarmManager.set(2, SystemClock.elapsedRealtime() + ((long) timeMs), this.mPendingDiscIntent);
        }
    }

    private void stopDisconnectTimer() {
        synchronized (this) {
            if (this.mPendingDiscIntent != null) {
                AlarmManager alarmManager = (AlarmManager) this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM);
                alarmManager.cancel(this.mPendingDiscIntent);
                this.mPendingDiscIntent.cancel();
                this.mPendingDiscIntent = null;
            }
        }
    }

    private void handleRfcommReply(SapMessage sapMsg) {
        if (sapMsg != null) {
            int msgType = sapMsg.getMsgType();
            if (msgType != 1) {
                if (msgType != 3) {
                    if (msgType == 17) {
                        if (this.mState == SAP_STATE.DISCONNECTED || this.mState == SAP_STATE.CONNECTING || this.mState == SAP_STATE.DISCONNECTING) {
                            sapMsg = null;
                        }
                        if (this.mSapServiceHandler != null && this.mState == SAP_STATE.CONNECTED) {
                            Message msg = Message.obtain(this.mSapServiceHandler);
                            msg.what = SapService.MSG_CHANGE_STATE;
                            msg.arg1 = 2;
                            msg.sendToTarget();
                            setNotification(0, 0);
                        }
                    }
                } else if (this.mState == SAP_STATE.DISCONNECTING) {
                    SapMessage disconnectResp = new SapMessage(3);
                    changeState(SAP_STATE.DISCONNECTED);
                    sapMsg = disconnectResp;
                    startDisconnectTimer(257, DISCONNECT_TIMEOUT_RFCOMM);
                    this.mDeinitSignal.countDown();
                } else {
                    this.mDeinitSignal.countDown();
                    if (this.mIsLocalInitDisconnect) {
                        shutdown();
                        sapMsg = null;
                    } else {
                        startDisconnectTimer(257, DISCONNECT_TIMEOUT_RFCOMM);
                    }
                }
            } else if (this.mState == SAP_STATE.CONNECTING_CALL_ONGOING) {
                if (sapMsg.getConnectionStatus() == 0) {
                    changeState(SAP_STATE.CONNECTED);
                }
                sapMsg = null;
            } else if (sapMsg.getConnectionStatus() == 0) {
                changeState(SAP_STATE.CONNECTED);
            } else if (sapMsg.getConnectionStatus() == 4) {
                changeState(SAP_STATE.CONNECTING_CALL_ONGOING);
            } else if (sapMsg.getConnectionStatus() != 0) {
                startDisconnectTimer(256, DISCONNECT_TIMEOUT_RFCOMM);
            }
        }
        if (this.mState == SAP_STATE.CONNECTED_BUSY && SapMessage.getNumPendingRilMessages() == 0) {
            changeState(SAP_STATE.CONNECTED);
        }
        if (sapMsg != null) {
            sendReply(sapMsg);
        }
    }

    private void handleRilInd(SapMessage sapMsg) {
        if (sapMsg != null && sapMsg.getMsgType() == 258) {
            if (this.mState != SAP_STATE.DISCONNECTED && this.mState != SAP_STATE.DISCONNECTING) {
                SapMessage reply = new SapMessage(4);
                reply.setDisconnectionType(sapMsg.getDisconnectionType());
                sendClientMessage(reply);
                return;
            }
            sendDisconnectInd(sapMsg.getDisconnectionType());
        }
    }

    private void sendRilMessage(SapMessage sapMsg) {
        Log.d(TAG_HANDLER, "sendRilMessage: calling getSapProxy");
        synchronized (this.mRilBtReceiver.getSapProxyLock()) {
            ISap sapProxy = this.mRilBtReceiver.getSapProxy();
            if (sapProxy == null) {
                Log.e(TAG_HANDLER, "sendRilMessage: Unable to send message to RIL; sapProxy is null");
                sendClientMessage(new SapMessage(18));
                return;
            }
            try {
                sapMsg.send(sapProxy);
            } catch (RemoteException | RuntimeException e) {
                Log.e(TAG_HANDLER, "sendRilMessage: Unable to send message to RIL: " + e);
                sendClientMessage(new SapMessage(18));
                this.mRilBtReceiver.notifyShutdown();
                this.mRilBtReceiver.resetSapProxy();
            } catch (IllegalArgumentException e2) {
                Log.e(TAG_HANDLER, "sendRilMessage: IllegalArgumentException", e2);
                sendClientMessage(new SapMessage(18));
            }
        }
    }

    private void sendReply(SapMessage msg) {
        BufferedOutputStream bufferedOutputStream = this.mRfcommOut;
        if (bufferedOutputStream != null) {
            try {
                msg.write(bufferedOutputStream);
                this.mRfcommOut.flush();
            } catch (IOException e) {
                Log.w(TAG_HANDLER, e);
            }
        }
    }

    private static String getMessageName(int messageId) {
        if (messageId == 0) {
            return "SAP_MSG_REPLY";
        }
        if (messageId == 1) {
            return "SAP_MSG_RIL_CONNECT";
        }
        if (messageId == 2) {
            return "SAP_MSG_RIL_REQ";
        }
        if (messageId == 3) {
            return "SAP_MSG_RIL_IND";
        }
        return "Unknown message ID";
    }
}
