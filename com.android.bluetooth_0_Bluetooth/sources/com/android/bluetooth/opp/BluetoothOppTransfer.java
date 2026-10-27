package com.android.bluetooth.opp;

import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.bluetooth.BluetoothUuid;
import android.bluetooth.SdpOppOpsRecord;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelUuid;
import android.util.Log;
import com.android.bluetooth.BluetoothObexTransport;
import com.android.bluetooth.mapapi.BluetoothMapContract;
import com.android.bluetooth.opp.BluetoothOppBatch;
import java.io.IOException;
import javax.obex.ObexTransport;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothOppTransfer implements BluetoothOppBatch.BluetoothOppBatchListener {
    private static final int CONNECT_RETRY_TIME = 100;
    private static final int CONNECT_WAIT_TIMEOUT = 45000;
    private static final boolean D = false;
    private static final int SOCKET_ERROR_RETRY = 13;
    private static final String SOCKET_LINK_KEY_ERROR = "Invalid exchange";
    private static final String TAG = "BtOppTransfer";
    private static final int TRANSPORT_CONNECTED = 11;
    private static final int TRANSPORT_ERROR = 10;
    private static final boolean V = false;
    private BluetoothAdapter mAdapter;
    private BluetoothOppBatch mBatch;
    private OppConnectionReceiver mBluetoothReceiver;
    private SocketConnectThread mConnectThread;
    private Context mContext;
    private BluetoothOppShareInfo mCurrentShare;
    private BluetoothDevice mDevice;
    private HandlerThread mHandlerThread;
    private BluetoothOppObexSession mSession;
    private EventHandler mSessionHandler;
    private long mTimestamp;
    private ObexTransport mTransport;

    private class OppConnectionReceiver extends BroadcastReceiver {
        private OppConnectionReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.bluetooth.device.action.ACL_DISCONNECTED")) {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (device != null && BluetoothOppTransfer.this.mBatch != null && BluetoothOppTransfer.this.mCurrentShare != null) {
                    try {
                        if (device.equals(BluetoothOppTransfer.this.mBatch.mDestination) && BluetoothOppTransfer.this.mCurrentShare.mConfirm == 0) {
                            BluetoothOppTransfer.this.mSessionHandler.removeMessages(4);
                            BluetoothOppTransfer.this.mSessionHandler.sendMessage(BluetoothOppTransfer.this.mSessionHandler.obtainMessage(4));
                            return;
                        }
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                Log.e(BluetoothOppTransfer.TAG, "device : " + device + " mBatch :" + BluetoothOppTransfer.this.mBatch + " mCurrentShare :" + BluetoothOppTransfer.this.mCurrentShare);
                return;
            }
            if (action.equals("android.bluetooth.device.action.SDP_RECORD")) {
                ParcelUuid uuid = (ParcelUuid) intent.getParcelableExtra("android.bluetooth.device.extra.UUID");
                if (uuid.equals(BluetoothUuid.OBEX_OBJECT_PUSH)) {
                    int status = intent.getIntExtra("android.bluetooth.device.extra.SDP_SEARCH_STATUS", -1);
                    Log.d(BluetoothOppTransfer.TAG, " -> status: " + status);
                    BluetoothDevice device2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    if (BluetoothOppTransfer.this.mDevice != null) {
                        if (!device2.getAddress().equalsIgnoreCase(BluetoothOppTransfer.this.mDevice.getAddress())) {
                            Log.w(BluetoothOppTransfer.TAG, " OPP SDP search for wrong device, ignoring!!");
                            return;
                        }
                        SdpOppOpsRecord record = intent.getParcelableExtra("android.bluetooth.device.extra.SDP_RECORD");
                        if (record == null) {
                            Log.w(BluetoothOppTransfer.TAG, " Invalid SDP , ignoring !!");
                            BluetoothOppTransfer.this.markConnectionFailed(null);
                            return;
                        }
                        BluetoothOppTransfer bluetoothOppTransfer = BluetoothOppTransfer.this;
                        BluetoothOppTransfer bluetoothOppTransfer2 = BluetoothOppTransfer.this;
                        bluetoothOppTransfer.mConnectThread = bluetoothOppTransfer2.new SocketConnectThread(bluetoothOppTransfer2.mDevice, false, true, record.getL2capPsm());
                        BluetoothOppTransfer.this.mConnectThread.start();
                        BluetoothOppTransfer.this.mDevice = null;
                        return;
                    }
                    Log.w(BluetoothOppTransfer.TAG, "OPP SDP search, target device is null, ignoring result");
                }
            }
        }
    }

    public BluetoothOppTransfer(Context context, BluetoothOppBatch batch, BluetoothOppObexSession session) {
        this.mContext = context;
        this.mBatch = batch;
        this.mSession = session;
        batch.registerListern(this);
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public BluetoothOppTransfer(Context context, BluetoothOppBatch batch) {
        this(context, batch, null);
    }

    public int getBatchId() {
        return this.mBatch.mId;
    }

    private class EventHandler extends Handler {
        EventHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i = msg.what;
            if (i == 0) {
                if (BluetoothOppTransfer.this.mBatch.mDirection == 0) {
                    BluetoothOppTransfer bluetoothOppTransfer = BluetoothOppTransfer.this;
                    bluetoothOppTransfer.mCurrentShare = bluetoothOppTransfer.mBatch.getPendingShare();
                    if (BluetoothOppTransfer.this.mCurrentShare != null) {
                        BluetoothOppTransfer.this.processCurrentShare();
                        return;
                    } else {
                        BluetoothOppTransfer.this.mSession.stop();
                        return;
                    }
                }
                return;
            }
            if (i == 1) {
                BluetoothOppTransfer.this.cleanUp();
                BluetoothOppShareInfo info1 = (BluetoothOppShareInfo) msg.obj;
                BluetoothOppTransfer.this.mBatch.mStatus = 2;
                BluetoothOppTransfer.this.tickShareStatus(info1);
                return;
            }
            if (i == 2) {
                BluetoothOppTransfer.this.cleanUp();
                try {
                    BluetoothOppShareInfo info2 = (BluetoothOppShareInfo) msg.obj;
                    if (BluetoothOppTransfer.this.mSession != null) {
                        BluetoothOppTransfer.this.mSession.stop();
                    }
                    BluetoothOppTransfer.this.mBatch.mStatus = 3;
                    BluetoothOppTransfer.this.markBatchFailed(info2.mStatus);
                    BluetoothOppTransfer.this.tickShareStatus(BluetoothOppTransfer.this.mCurrentShare);
                    return;
                } catch (Exception e) {
                    Log.e(BluetoothOppTransfer.TAG, "Exception while handling MSG_SESSION_ERROR");
                    e.printStackTrace();
                    return;
                }
            }
            if (i == 3) {
                BluetoothOppShareInfo info3 = (BluetoothOppShareInfo) msg.obj;
                if (BluetoothOppTransfer.this.mBatch.mDirection == 0) {
                    try {
                        if (BluetoothOppTransfer.this.mTransport != null) {
                            BluetoothOppTransfer.this.mTransport.close();
                        } else {
                            Log.v(BluetoothOppTransfer.TAG, "receive MSG_SHARE_INTERRUPTED but mTransport = null");
                        }
                    } catch (IOException e2) {
                        Log.e(BluetoothOppTransfer.TAG, "failed to close mTransport");
                    }
                    BluetoothOppTransfer.this.mBatch.mStatus = 3;
                    if (info3 != null) {
                        BluetoothOppTransfer.this.markBatchFailed(info3.mStatus);
                    } else {
                        BluetoothOppTransfer.this.markBatchFailed(BluetoothShare.STATUS_UNKNOWN_ERROR);
                    }
                    BluetoothOppTransfer bluetoothOppTransfer2 = BluetoothOppTransfer.this;
                    bluetoothOppTransfer2.tickShareStatus(bluetoothOppTransfer2.mCurrentShare);
                    return;
                }
                return;
            }
            if (i == 4) {
                if (BluetoothOppTransfer.this.mBatch.mDirection == 0) {
                    try {
                        if (BluetoothOppTransfer.this.mTransport != null) {
                            BluetoothOppTransfer.this.mTransport.close();
                        } else {
                            Log.v(BluetoothOppTransfer.TAG, "receive MSG_SHARE_INTERRUPTED but mTransport = null");
                        }
                        return;
                    } catch (IOException e3) {
                        Log.e(BluetoothOppTransfer.TAG, "failed to close mTransport");
                        return;
                    }
                }
                NotificationManager nm = (NotificationManager) BluetoothOppTransfer.this.mContext.getSystemService(BluetoothMapContract.RECEPTION_STATE_NOTIFICATION);
                nm.cancel(BluetoothOppTransfer.this.mCurrentShare.mId);
                Intent in = new Intent(BluetoothShare.USER_CONFIRMATION_TIMEOUT_ACTION);
                BluetoothOppTransfer.this.mContext.sendBroadcast(in);
                BluetoothOppTransfer bluetoothOppTransfer3 = BluetoothOppTransfer.this;
                bluetoothOppTransfer3.markShareTimeout(bluetoothOppTransfer3.mCurrentShare);
                return;
            }
            if (i == 10) {
                BluetoothOppTransfer.this.mConnectThread = null;
                BluetoothOppTransfer.this.markBatchFailed(BluetoothShare.STATUS_CONNECTION_ERROR);
                BluetoothOppTransfer.this.mBatch.mStatus = 3;
            } else {
                if (i == 11) {
                    BluetoothOppTransfer.this.mConnectThread = null;
                    BluetoothOppTransfer.this.mTransport = (ObexTransport) msg.obj;
                    BluetoothOppTransfer.this.startObexSession();
                    return;
                }
                if (i == 13) {
                    BluetoothOppTransfer.this.mConnectThread = BluetoothOppTransfer.this.new SocketConnectThread((BluetoothDevice) msg.obj, true);
                    BluetoothOppTransfer.this.mConnectThread.start();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void markShareTimeout(BluetoothOppShareInfo share) {
        Uri contentUri = Uri.parse(BluetoothShare.CONTENT_URI + "/" + share.mId);
        ContentValues updateValues = new ContentValues();
        updateValues.put(BluetoothShare.USER_CONFIRMATION, (Integer) 4);
        this.mContext.getContentResolver().update(contentUri, updateValues, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void markBatchFailed(int failReason) {
        synchronized (this) {
            try {
                wait(1000L);
            } catch (InterruptedException e) {
            }
        }
        BluetoothOppShareInfo bluetoothOppShareInfo = this.mCurrentShare;
        if (bluetoothOppShareInfo != null) {
            if (BluetoothShare.isStatusError(bluetoothOppShareInfo.mStatus)) {
                failReason = this.mCurrentShare.mStatus;
            }
            if (this.mCurrentShare.mDirection == 1 && this.mCurrentShare.mUri != null) {
                this.mContext.getContentResolver().delete(this.mCurrentShare.mUri, null, null);
            }
        }
        BluetoothOppBatch bluetoothOppBatch = this.mBatch;
        if (bluetoothOppBatch == null) {
            return;
        }
        BluetoothOppShareInfo info = bluetoothOppBatch.getPendingShare();
        while (info != null) {
            if (info.mStatus < 200) {
                info.mStatus = failReason;
                Uri contentUri = Uri.parse(BluetoothShare.CONTENT_URI + "/" + info.mId);
                ContentValues updateValues = new ContentValues();
                updateValues.put("status", Integer.valueOf(info.mStatus));
                if (info.mDirection == 0) {
                    BluetoothOppSendFileInfo fileInfo = BluetoothOppUtility.getSendFileInfo(info.mUri);
                    BluetoothOppUtility.closeSendFileInfo(info.mUri);
                    if (fileInfo.mFileName != null) {
                        updateValues.put(BluetoothShare.FILENAME_HINT, fileInfo.mFileName);
                        updateValues.put(BluetoothShare.TOTAL_BYTES, Long.valueOf(fileInfo.mLength));
                        updateValues.put(BluetoothShare.MIMETYPE, fileInfo.mMimetype);
                    }
                } else if (info.mStatus < 200 && info.mUri != null) {
                    this.mContext.getContentResolver().delete(info.mUri, null, null);
                }
                this.mContext.getContentResolver().update(contentUri, updateValues, null, null);
                Constants.sendIntentIfCompleted(this.mContext, contentUri, info.mStatus);
            }
            info = this.mBatch.getPendingShare();
        }
    }

    public void start() {
        if (!this.mAdapter.isEnabled()) {
            Log.e(TAG, "Can't start transfer when Bluetooth is disabled for " + this.mBatch.mId);
            markBatchFailed(BluetoothShare.STATUS_UNKNOWN_ERROR);
            this.mBatch.mStatus = 3;
            return;
        }
        registerConnectionreceiver();
        if (this.mHandlerThread == null) {
            HandlerThread handlerThread = new HandlerThread("BtOpp Transfer Handler", 10);
            this.mHandlerThread = handlerThread;
            handlerThread.start();
            this.mSessionHandler = new EventHandler(this.mHandlerThread.getLooper());
            if (this.mBatch.mDirection == 0) {
                startConnectSession();
            } else if (this.mBatch.mDirection == 1) {
                startObexSession();
            }
        }
    }

    public void stop() {
        BluetoothOppObexSession bluetoothOppObexSession = this.mSession;
        if (bluetoothOppObexSession != null) {
            bluetoothOppObexSession.stop();
        }
        cleanUp();
        SocketConnectThread socketConnectThread = this.mConnectThread;
        if (socketConnectThread != null) {
            try {
                socketConnectThread.interrupt();
                this.mConnectThread.join();
            } catch (InterruptedException e) {
            }
            this.mConnectThread = null;
        }
        synchronized (this) {
            if (this.mHandlerThread != null) {
                this.mHandlerThread.quit();
                this.mHandlerThread.interrupt();
                this.mHandlerThread = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startObexSession() {
        this.mBatch.mStatus = 1;
        BluetoothOppShareInfo pendingShare = this.mBatch.getPendingShare();
        this.mCurrentShare = pendingShare;
        if (pendingShare == null) {
            Log.e(TAG, "Unexpected error happened !");
            return;
        }
        if (this.mBatch.mDirection == 0) {
            this.mSession = new BluetoothOppObexClientSession(this.mContext, this.mTransport);
        } else if (this.mBatch.mDirection == 1 && this.mSession == null) {
            Log.e(TAG, "Unexpected error happened !");
            markBatchFailed(BluetoothShare.STATUS_UNKNOWN_ERROR);
            this.mBatch.mStatus = 3;
            return;
        }
        this.mSession.start(this.mSessionHandler, this.mBatch.getNumShares());
        processCurrentShare();
    }

    private void registerConnectionreceiver() {
        synchronized (this) {
            try {
            } catch (IllegalArgumentException e) {
                Log.e(TAG, "mBluetoothReceiver Registered already ", e);
            }
            if (this.mBluetoothReceiver == null) {
                this.mBluetoothReceiver = new OppConnectionReceiver();
                IntentFilter filter = new IntentFilter();
                filter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
                filter.addAction("android.bluetooth.device.action.SDP_RECORD");
                this.mContext.registerReceiver(this.mBluetoothReceiver, filter);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processCurrentShare() {
        this.mSession.addShare(this.mCurrentShare);
        if (this.mCurrentShare.mConfirm == 5) {
            confirmStatusChanged();
        }
    }

    public void confirmStatusChanged() {
        Thread notifyThread = new Thread("Server Unblock thread") { // from class: com.android.bluetooth.opp.BluetoothOppTransfer.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                synchronized (BluetoothOppTransfer.this.mSession) {
                    BluetoothOppTransfer.this.mSession.unblock();
                    BluetoothOppTransfer.this.mSession.notify();
                }
            }
        };
        notifyThread.start();
    }

    private void startConnectSession() {
        this.mDevice = this.mBatch.mDestination;
        if (!this.mBatch.mDestination.sdpSearch(BluetoothUuid.OBEX_OBJECT_PUSH)) {
            this.mDevice = null;
            SocketConnectThread socketConnectThread = new SocketConnectThread(this.mBatch.mDestination, false, false, -1);
            this.mConnectThread = socketConnectThread;
            socketConnectThread.start();
        }
    }

    private class SocketConnectThread extends Thread {
        private BluetoothSocket mBtSocket;
        private final int mChannel;
        private final BluetoothDevice mDevice;
        private final String mHost;
        private boolean mIsConnected;
        private boolean mIsInterrupted;
        private int mL2cChannel;
        private boolean mRetry;
        private boolean mSdpInitiated;
        private long mTimestamp;

        SocketConnectThread(BluetoothDevice device, boolean retry) {
            super("Socket Connect Thread");
            this.mL2cChannel = 0;
            this.mBtSocket = null;
            this.mRetry = false;
            this.mSdpInitiated = false;
            this.mIsInterrupted = false;
            this.mDevice = device;
            this.mHost = null;
            this.mChannel = -1;
            this.mIsConnected = false;
            this.mRetry = retry;
            this.mSdpInitiated = false;
        }

        SocketConnectThread(BluetoothDevice device, boolean retry, boolean sdpInitiated, int l2capChannel) {
            super("Socket Connect Thread");
            this.mL2cChannel = 0;
            this.mBtSocket = null;
            this.mRetry = false;
            this.mSdpInitiated = false;
            this.mIsInterrupted = false;
            this.mDevice = device;
            this.mHost = null;
            this.mChannel = -1;
            this.mIsConnected = false;
            this.mRetry = retry;
            this.mSdpInitiated = sdpInitiated;
            this.mL2cChannel = l2capChannel;
        }

        @Override // java.lang.Thread
        public void interrupt() {
            this.mIsInterrupted = true;
            BluetoothSocket bluetoothSocket = this.mBtSocket;
            if (bluetoothSocket != null) {
                try {
                    bluetoothSocket.close();
                } catch (IOException e) {
                    Log.v(BluetoothOppTransfer.TAG, "Error when close socket");
                }
            }
        }

        private void connectRfcommSocket() {
            try {
                if (this.mIsInterrupted) {
                    Log.d(BluetoothOppTransfer.TAG, "connectRfcommSocket interrupted");
                    BluetoothOppTransfer.this.markConnectionFailed(this.mBtSocket);
                    return;
                }
                BluetoothSocket bluetoothSocketCreateInsecureRfcommSocketToServiceRecord = this.mDevice.createInsecureRfcommSocketToServiceRecord(BluetoothUuid.OBEX_OBJECT_PUSH.getUuid());
                this.mBtSocket = bluetoothSocketCreateInsecureRfcommSocketToServiceRecord;
                try {
                    bluetoothSocketCreateInsecureRfcommSocketToServiceRecord.connect();
                    BluetoothObexTransport transport = new BluetoothObexTransport(this.mBtSocket);
                    BluetoothOppPreference.getInstance(BluetoothOppTransfer.this.mContext).setName(this.mDevice, this.mDevice.getName());
                    BluetoothOppTransfer.this.mSessionHandler.obtainMessage(11, transport).sendToTarget();
                } catch (IOException e) {
                    Log.e(BluetoothOppTransfer.TAG, "Rfcomm socket connect exception", e);
                    if (this.mRetry || !e.getMessage().equals(BluetoothOppTransfer.SOCKET_LINK_KEY_ERROR)) {
                        BluetoothOppTransfer.this.markConnectionFailed(this.mBtSocket);
                    } else {
                        Message msg = BluetoothOppTransfer.this.mSessionHandler.obtainMessage(13, -1, -1, this.mDevice);
                        BluetoothOppTransfer.this.mSessionHandler.sendMessageDelayed(msg, 1500L);
                    }
                }
            } catch (IOException e1) {
                Log.e(BluetoothOppTransfer.TAG, "Rfcomm socket create error", e1);
                BluetoothOppTransfer.this.markConnectionFailed(this.mBtSocket);
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int i;
            this.mTimestamp = System.currentTimeMillis();
            if (!this.mSdpInitiated || (i = this.mL2cChannel) < 0) {
                Log.d(BluetoothOppTransfer.TAG, "sdp not initiated, connecting on rfcomm");
                connectRfcommSocket();
                return;
            }
            this.mSdpInitiated = false;
            try {
                if (this.mIsInterrupted) {
                    Log.e(BluetoothOppTransfer.TAG, "btSocket connect interrupted ");
                    BluetoothOppTransfer.this.markConnectionFailed(this.mBtSocket);
                    return;
                }
                BluetoothSocket bluetoothSocketCreateInsecureL2capSocket = this.mDevice.createInsecureL2capSocket(i);
                this.mBtSocket = bluetoothSocketCreateInsecureL2capSocket;
                try {
                    bluetoothSocketCreateInsecureL2capSocket.connect();
                    BluetoothObexTransport transport = new BluetoothObexTransport(this.mBtSocket);
                    BluetoothOppPreference.getInstance(BluetoothOppTransfer.this.mContext).setName(this.mDevice, this.mDevice.getName());
                    BluetoothOppTransfer.this.mSessionHandler.obtainMessage(11, transport).sendToTarget();
                } catch (IOException e) {
                    Log.e(BluetoothOppTransfer.TAG, "L2cap socket connect exception", e);
                    try {
                        this.mBtSocket.close();
                    } catch (IOException e3) {
                        Log.e(BluetoothOppTransfer.TAG, "Bluetooth socket close error ", e3);
                    }
                    connectRfcommSocket();
                }
            } catch (IOException e1) {
                Log.e(BluetoothOppTransfer.TAG, "L2cap socket create error", e1);
                connectRfcommSocket();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void markConnectionFailed(BluetoothSocket s) {
        if (s != null) {
            try {
                s.close();
            } catch (IOException e) {
            }
        }
        this.mSessionHandler.obtainMessage(10).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tickShareStatus(BluetoothOppShareInfo share) {
        if (share == null) {
            Log.d(TAG, "Share is null");
            return;
        }
        Uri contentUri = Uri.parse(BluetoothShare.CONTENT_URI + "/" + share.mId);
        ContentValues updateValues = new ContentValues();
        updateValues.put(BluetoothShare.DIRECTION, Integer.valueOf(share.mDirection));
        this.mContext.getContentResolver().update(contentUri, updateValues, null, null);
    }

    @Override // com.android.bluetooth.opp.BluetoothOppBatch.BluetoothOppBatchListener
    public void onShareAdded(int id) {
        BluetoothOppShareInfo info = this.mBatch.getPendingShare();
        if (info.mDirection == 1) {
            BluetoothOppShareInfo pendingShare = this.mBatch.getPendingShare();
            this.mCurrentShare = pendingShare;
            if (pendingShare != null) {
                if (pendingShare.mConfirm == 2 || this.mCurrentShare.mConfirm == 5) {
                    processCurrentShare();
                    confirmStatusChanged();
                }
            }
        }
    }

    @Override // com.android.bluetooth.opp.BluetoothOppBatch.BluetoothOppBatchListener
    public void onShareDeleted(int id) {
    }

    @Override // com.android.bluetooth.opp.BluetoothOppBatch.BluetoothOppBatchListener
    public void onBatchCanceled() {
        stop();
        this.mBatch.mStatus = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanUp() {
        synchronized (this) {
            try {
            } catch (Exception e) {
                Log.e(TAG, "Exception:unregisterReceiver");
                e.printStackTrace();
            }
            if (this.mBluetoothReceiver != null) {
                this.mContext.unregisterReceiver(this.mBluetoothReceiver);
                this.mBluetoothReceiver = null;
            }
        }
    }
}
