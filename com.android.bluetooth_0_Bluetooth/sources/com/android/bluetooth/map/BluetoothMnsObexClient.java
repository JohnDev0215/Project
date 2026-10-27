package com.android.bluetooth.map;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.bluetooth.SdpMnsRecord;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelUuid;
import android.util.Log;
import android.util.SparseBooleanArray;
import com.android.bluetooth.BluetoothObexTransport;
import com.android.bluetooth.sap.SapService;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.OutputStream;
import javax.obex.ClientOperation;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;
import javax.obex.ObexTransport;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMnsObexClient {
    public static final ParcelUuid BLUETOOTH_UUID_OBEX_MNS = ParcelUuid.fromString("00001133-0000-1000-8000-00805F9B34FB");
    private static final boolean D = false;
    private static final int MNS_NOTIFICATION_DELAY = 10;
    private static final int MNS_SDP_SEARCH_DELAY = 6000;
    public static final int MSG_MNS_NOTIFICATION_REGISTRATION = 1;
    public static final int MSG_MNS_SDP_SEARCH_REGISTRATION = 3;
    public static final int MSG_MNS_SEND_EVENT = 2;
    private static final String TAG = "BluetoothMnsObexClient";
    private static final String TYPE_EVENT = "x-bt/MAP-event-report";
    private static final boolean V = false;
    private Handler mCallback;
    private ClientSession mClientSession;
    public Handler mHandler;
    private SdpMnsRecord mMnsRecord;
    BluetoothDevice mRemoteDevice;
    private ObexTransport mTransport;
    private volatile boolean mWaitingForRemote;
    private boolean mConnected = false;
    private SparseBooleanArray mRegisteredMasIds = new SparseBooleanArray(1);
    private HeaderSet mHsConnect = null;
    public MnsSdpSearchInfo mMnsLstRegRqst = null;

    public BluetoothMnsObexClient(BluetoothDevice remoteDevice, SdpMnsRecord mnsRecord, Handler callback) {
        this.mHandler = null;
        this.mCallback = null;
        if (remoteDevice == null) {
            throw new NullPointerException("Obex transport is null");
        }
        this.mRemoteDevice = remoteDevice;
        HandlerThread thread = new HandlerThread(TAG);
        thread.start();
        Looper looper = thread.getLooper();
        this.mHandler = new MnsObexClientHandler(looper);
        this.mCallback = callback;
        this.mMnsRecord = mnsRecord;
    }

    public Handler getMessageHandler() {
        return this.mHandler;
    }

    class MnsSdpSearchInfo {
        public int lastMasId;
        public int lastNotificationStatus;
        private boolean mIsSearchInProgress;

        MnsSdpSearchInfo(boolean isSearchON, int masId, int notification) {
            this.mIsSearchInProgress = isSearchON;
            this.lastMasId = masId;
            this.lastNotificationStatus = notification;
        }

        public boolean isSearchInProgress() {
            return this.mIsSearchInProgress;
        }

        public void setIsSearchInProgress(boolean isSearchON) {
            this.mIsSearchInProgress = isSearchON;
        }
    }

    private final class MnsObexClientHandler extends Handler {
        private MnsObexClientHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i = msg.what;
            if (i == 1) {
                if (BluetoothMnsObexClient.this.isValidMnsRecord()) {
                    BluetoothMnsObexClient.this.handleRegistration(msg.arg1, msg.arg2);
                }
            } else {
                if (i == 2) {
                    BluetoothMnsObexClient.this.sendEventHandler((byte[]) msg.obj, msg.arg1);
                    return;
                }
                if (i == 3) {
                    BluetoothMnsObexClient.this.notifyMnsSdpSearch();
                    BluetoothMnsObexClient bluetoothMnsObexClient = BluetoothMnsObexClient.this;
                    bluetoothMnsObexClient.mMnsLstRegRqst = bluetoothMnsObexClient.new MnsSdpSearchInfo(true, msg.arg1, msg.arg2);
                    Message msgReg = BluetoothMnsObexClient.this.mHandler.obtainMessage(1, msg.arg1, msg.arg2);
                    BluetoothMnsObexClient.this.mHandler.sendMessageDelayed(msgReg, 6000L);
                }
            }
        }
    }

    public boolean isConnected() {
        return this.mConnected;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002d A[Catch: all -> 0x000c, IOException -> 0x0035, TRY_LEAVE, TryCatch #0 {IOException -> 0x0035, blocks: (B:12:0x0029, B:14:0x002d), top: B:28:0x0029, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0050 A[Catch: all -> 0x000c, TRY_LEAVE, TryCatch #2 {, blocks: (B:4:0x0002, B:6:0x0006, B:12:0x0029, B:14:0x002d, B:18:0x0050, B:20:0x0054, B:23:0x0060, B:17:0x0036, B:11:0x000f), top: B:32:0x0002, inners: #0, #1, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0054 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void disconnect() {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 0
            javax.obex.ClientSession r1 = r5.mClientSession     // Catch: java.lang.Throwable -> Lc java.io.IOException -> Le
            if (r1 == 0) goto Lb
            javax.obex.ClientSession r1 = r5.mClientSession     // Catch: java.lang.Throwable -> Lc java.io.IOException -> Le
            r1.disconnect(r0)     // Catch: java.lang.Throwable -> Lc java.io.IOException -> Le
        Lb:
            goto L29
        Lc:
            r0 = move-exception
            goto L7c
        Le:
            r1 = move-exception
            java.lang.String r2 = "BluetoothMnsObexClient"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc
            r3.<init>()     // Catch: java.lang.Throwable -> Lc
            java.lang.String r4 = "OBEX session disconnect error "
            r3.append(r4)     // Catch: java.lang.Throwable -> Lc
            java.lang.String r4 = r1.getMessage()     // Catch: java.lang.Throwable -> Lc
            r3.append(r4)     // Catch: java.lang.Throwable -> Lc
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> Lc
            android.util.Log.w(r2, r3)     // Catch: java.lang.Throwable -> Lc
        L29:
            javax.obex.ClientSession r1 = r5.mClientSession     // Catch: java.lang.Throwable -> Lc java.io.IOException -> L35
            if (r1 == 0) goto L34
            javax.obex.ClientSession r1 = r5.mClientSession     // Catch: java.lang.Throwable -> Lc java.io.IOException -> L35
            r1.close()     // Catch: java.lang.Throwable -> Lc java.io.IOException -> L35
            r5.mClientSession = r0     // Catch: java.lang.Throwable -> Lc java.io.IOException -> L35
        L34:
            goto L50
        L35:
            r1 = move-exception
            java.lang.String r2 = "BluetoothMnsObexClient"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc
            r3.<init>()     // Catch: java.lang.Throwable -> Lc
            java.lang.String r4 = "OBEX session close error:"
            r3.append(r4)     // Catch: java.lang.Throwable -> Lc
            java.lang.String r4 = r1.getMessage()     // Catch: java.lang.Throwable -> Lc
            r3.append(r4)     // Catch: java.lang.Throwable -> Lc
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> Lc
            android.util.Log.w(r2, r3)     // Catch: java.lang.Throwable -> Lc
        L50:
            javax.obex.ObexTransport r1 = r5.mTransport     // Catch: java.lang.Throwable -> Lc
            if (r1 == 0) goto L7a
            javax.obex.ObexTransport r1 = r5.mTransport     // Catch: java.lang.Throwable -> Lc java.io.IOException -> L5f
            r1.close()     // Catch: java.lang.Throwable -> Lc java.io.IOException -> L5f
            r5.mTransport = r0     // Catch: java.lang.Throwable -> Lc java.io.IOException -> L5f
            r0 = 0
            r5.mConnected = r0     // Catch: java.lang.Throwable -> Lc java.io.IOException -> L5f
            goto L7a
        L5f:
            r0 = move-exception
            java.lang.String r1 = "BluetoothMnsObexClient"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc
            r2.<init>()     // Catch: java.lang.Throwable -> Lc
            java.lang.String r3 = "mTransport.close error: "
            r2.append(r3)     // Catch: java.lang.Throwable -> Lc
            java.lang.String r3 = r0.getMessage()     // Catch: java.lang.Throwable -> Lc
            r2.append(r3)     // Catch: java.lang.Throwable -> Lc
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> Lc
            android.util.Log.e(r1, r2)     // Catch: java.lang.Throwable -> Lc
        L7a:
            monitor-exit(r5)
            return
        L7c:
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.map.BluetoothMnsObexClient.disconnect():void");
    }

    public synchronized void shutdown() {
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
            Looper looper = this.mHandler.getLooper();
            if (looper != null) {
                looper.quit();
            }
        }
        disconnect();
        this.mRegisteredMasIds.clear();
    }

    public synchronized void handleRegistration(int masId, int notificationStatus) {
        boolean sendObserverRegistration = true;
        try {
            if (notificationStatus == 0) {
                this.mRegisteredMasIds.delete(masId);
                if (this.mMnsLstRegRqst != null && this.mMnsLstRegRqst.lastMasId == masId) {
                    this.mMnsLstRegRqst = null;
                }
            } else if (notificationStatus == 1) {
                if (!isConnected()) {
                    connect();
                }
                sendObserverRegistration = isConnected();
                this.mRegisteredMasIds.put(masId, true);
                this.mMnsLstRegRqst = null;
            }
            if (this.mRegisteredMasIds.size() == 0) {
                disconnect();
            }
            if (this.mCallback != null && sendObserverRegistration) {
                Message msg = Message.obtain(this.mCallback);
                msg.what = 5008;
                msg.arg1 = masId;
                msg.arg2 = notificationStatus;
                msg.sendToTarget();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public boolean isValidMnsRecord() {
        return this.mMnsRecord != null;
    }

    public void setMnsRecord(SdpMnsRecord mnsRecord) {
        if (isValidMnsRecord()) {
            Log.w(TAG, "MNS Record already available. Still update.");
        }
        this.mMnsRecord = mnsRecord;
        MnsSdpSearchInfo mnsSdpSearchInfo = this.mMnsLstRegRqst;
        if (mnsSdpSearchInfo != null) {
            mnsSdpSearchInfo.setIsSearchInProgress(false);
            if (this.mHandler.hasMessages(1)) {
                this.mHandler.removeMessages(1);
                if (!isValidMnsRecord()) {
                    this.mMnsLstRegRqst = null;
                    return;
                }
                Message msgReg = this.mHandler.obtainMessage(1);
                msgReg.arg1 = this.mMnsLstRegRqst.lastMasId;
                msgReg.arg2 = this.mMnsLstRegRqst.lastNotificationStatus;
                this.mHandler.sendMessageDelayed(msgReg, 10L);
            }
        }
    }

    public void connect() {
        BluetoothSocket btSocket;
        this.mConnected = true;
        try {
            if (isValidMnsRecord() && this.mMnsRecord.getL2capPsm() > 0) {
                btSocket = this.mRemoteDevice.createL2capSocket(this.mMnsRecord.getL2capPsm());
            } else if (isValidMnsRecord() && this.mMnsRecord.getRfcommChannelNumber() > 0) {
                btSocket = this.mRemoteDevice.createRfcommSocket(this.mMnsRecord.getRfcommChannelNumber());
            } else {
                Log.e(TAG, "Invalid SDP content - attempt a connect to UUID...");
                btSocket = this.mRemoteDevice.createInsecureRfcommSocketToServiceRecord(BLUETOOTH_UUID_OBEX_MNS.getUuid());
            }
            btSocket.connect();
            this.mTransport = new BluetoothObexTransport(btSocket);
            try {
                this.mClientSession = new ClientSession(this.mTransport);
            } catch (IOException e1) {
                Log.e(TAG, "OBEX session create error " + e1.getMessage());
                this.mConnected = false;
            }
            if (this.mConnected && this.mClientSession != null) {
                boolean connected = false;
                HeaderSet hs = new HeaderSet();
                byte[] mnsTarget = {-69, 88, 43, 65, 66, Ascii.FF, 17, -37, -80, -34, 8, 0, 32, Ascii.FF, -102, 102};
                hs.setHeader(70, mnsTarget);
                synchronized (this) {
                    this.mWaitingForRemote = true;
                }
                try {
                    this.mHsConnect = this.mClientSession.connect(hs);
                    connected = true;
                } catch (IOException e) {
                    Log.e(TAG, "OBEX session connect error " + e.getMessage());
                }
                this.mConnected = connected;
            }
            synchronized (this) {
                this.mWaitingForRemote = false;
            }
        } catch (IOException e2) {
            Log.e(TAG, "BtSocket Connect error " + e2.getMessage(), e2);
            this.mConnected = false;
        }
    }

    public void sendEvent(byte[] eventBytes, int masInstanceId) {
        Message msg;
        Handler handler = this.mHandler;
        if (handler != null && (msg = handler.obtainMessage(2, masInstanceId, 0, eventBytes)) != null) {
            msg.sendToTarget();
        }
        notifyUpdateWakeLock();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyMnsSdpSearch() {
        Handler handler = this.mCallback;
        if (handler != null) {
            Message msg = Message.obtain(handler);
            msg.what = SapService.MSG_CHANGE_STATE;
            msg.sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int sendEventHandler(byte[] eventBytes, int masInstanceId) {
        StringBuilder sb;
        boolean error = false;
        int responseCode = -1;
        int bytesWritten = 0;
        ClientSession clientSession = this.mClientSession;
        if (!this.mConnected || clientSession == null) {
            Log.w(TAG, "sendEvent after disconnect:" + this.mConnected);
            return -1;
        }
        HeaderSet request = new HeaderSet();
        BluetoothMapAppParams appParams = new BluetoothMapAppParams();
        appParams.setMasInstanceId(masInstanceId);
        ClientOperation putOperation = null;
        OutputStream outputStream = null;
        try {
            try {
                try {
                    request.setHeader(66, TYPE_EVENT);
                    request.setHeader(76, appParams.encodeParams());
                    if (this.mHsConnect.mConnectionID != null) {
                        request.mConnectionID = new byte[4];
                        System.arraycopy(this.mHsConnect.mConnectionID, 0, request.mConnectionID, 0, 4);
                    } else {
                        Log.w(TAG, "sendEvent: no connection ID");
                    }
                    synchronized (this) {
                        this.mWaitingForRemote = true;
                    }
                    try {
                        putOperation = (ClientOperation) clientSession.put(request);
                    } catch (IOException e) {
                        Log.e(TAG, "Error when put HeaderSet " + e.getMessage());
                        error = true;
                    }
                    synchronized (this) {
                        this.mWaitingForRemote = false;
                    }
                    if (!error) {
                        try {
                            outputStream = putOperation.openOutputStream();
                        } catch (IOException e2) {
                            Log.e(TAG, "Error when opening OutputStream " + e2.getMessage());
                            error = true;
                        }
                    }
                    if (!error) {
                        int maxChunkSize = putOperation.getMaxPacketSize();
                        while (bytesWritten < eventBytes.length) {
                            int bytesToWrite = Math.min(maxChunkSize, eventBytes.length - bytesWritten);
                            outputStream.write(eventBytes, bytesWritten, bytesToWrite);
                            bytesWritten += bytesToWrite;
                        }
                        if (bytesWritten == eventBytes.length) {
                            Log.i(TAG, "SendEvent finished send length" + eventBytes.length);
                        } else {
                            error = true;
                            putOperation.abort();
                            Log.i(TAG, "SendEvent interrupted");
                        }
                    }
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e3) {
                            Log.e(TAG, "Error when closing stream after send " + e3.getMessage());
                        }
                    }
                    if (!error && putOperation != null) {
                        try {
                            responseCode = putOperation.getResponseCode();
                            if (responseCode != -1 && responseCode != 160) {
                                Log.i(TAG, "Response error code is " + responseCode);
                            }
                        } catch (IOException e4) {
                            e = e4;
                            sb = new StringBuilder();
                            sb.append("Error when closing stream after send ");
                            sb.append(e.getMessage());
                            Log.e(TAG, sb.toString());
                        }
                    }
                    if (putOperation != null) {
                        putOperation.close();
                    }
                } catch (IndexOutOfBoundsException e5) {
                    handleSendException(e5.toString());
                    if (0 != 0) {
                        try {
                            outputStream.close();
                        } catch (IOException e6) {
                            Log.e(TAG, "Error when closing stream after send " + e6.getMessage());
                        }
                    }
                    if (1 == 0 && 0 != 0) {
                        try {
                            responseCode = putOperation.getResponseCode();
                            if (responseCode != -1 && responseCode != 160) {
                                Log.i(TAG, "Response error code is " + responseCode);
                            }
                        } catch (IOException e7) {
                            e = e7;
                            sb = new StringBuilder();
                            sb.append("Error when closing stream after send ");
                            sb.append(e.getMessage());
                            Log.e(TAG, sb.toString());
                        }
                    }
                    if (0 != 0) {
                        putOperation.close();
                    }
                }
            } catch (IOException e8) {
                handleSendException(e8.toString());
                if (0 != 0) {
                    try {
                        outputStream.close();
                    } catch (IOException e9) {
                        Log.e(TAG, "Error when closing stream after send " + e9.getMessage());
                    }
                }
                if (1 == 0 && 0 != 0) {
                    try {
                        responseCode = putOperation.getResponseCode();
                        if (responseCode != -1 && responseCode != 160) {
                            Log.i(TAG, "Response error code is " + responseCode);
                        }
                    } catch (IOException e10) {
                        e = e10;
                        sb = new StringBuilder();
                        sb.append("Error when closing stream after send ");
                        sb.append(e.getMessage());
                        Log.e(TAG, sb.toString());
                    }
                }
                if (0 != 0) {
                    putOperation.close();
                }
            }
            return responseCode;
        } finally {
        }
    }

    private void handleSendException(String exception) {
        Log.e(TAG, "Error when sending event: " + exception);
    }

    private void notifyUpdateWakeLock() {
        Handler handler = this.mCallback;
        if (handler != null) {
            Message msg = Message.obtain(handler);
            msg.what = SapService.MSG_ACQUIRE_WAKE_LOCK;
            msg.sendToTarget();
        }
    }
}
