package com.android.bluetooth.opp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import com.android.bluetooth.BluetoothObexTransport;
import com.android.bluetooth.IObexConnectionHandler;
import com.android.bluetooth.ObexServerSockets;
import com.android.bluetooth.btservice.ProfileService;
import com.android.bluetooth.sdp.SdpManager;
import com.android.vcard.VCardConfig;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.obex.ObexTransport;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothOppService extends ProfileService implements IObexConnectionHandler {
    private static final boolean D = false;
    private static final String INVISIBLE = "visibility=1";
    private static final int MEDIA_SCANNED = 2;
    private static final int MEDIA_SCANNED_FAILED = 3;
    private static final int MSG_INCOMING_BTOPP_CONNECTION = 100;
    private static final int MSG_INCOMING_CONNECTION_RETRY = 4;
    private static final int START_LISTENER = 1;
    private static final int STOP_LISTENER = 200;
    private static final byte[] SUPPORTED_OPP_FORMAT = {1, 2, 3, 4, -1};
    private static final String TAG = "BtOppService";
    private static final boolean V = false;
    private static final String WHERE_CONFIRM_PENDING_INBOUND = "direction=1 AND confirm=0";
    private static final String WHERE_INBOUND_SUCCESS = "direction=1 AND status=200 AND visibility=1";
    private static final String WHERE_INVISIBLE_UNCONFIRMED = "(status>=200 AND visibility=1) OR (direction=1 AND confirm=0)";
    private static BluetoothOppService sBluetoothOppService;
    boolean mAcceptNewConnections;
    private BluetoothAdapter mAdapter;
    private int mBatchId;
    private ArrayList<BluetoothOppBatch> mBatches;
    private int mIncomingRetries;
    private boolean mListenStarted;
    private boolean mMediaScanInProgress;
    private CharArrayBuffer mNewChars;
    private BluetoothOppNotification mNotifier;
    private BluetoothShareContentObserver mObserver;
    private CharArrayBuffer mOldChars;
    private ObexTransport mPendingConnection;
    private boolean mPendingUpdate;
    private BluetoothOppObexServerSession mServerSession;
    private ObexServerSockets mServerSocket;
    private BluetoothOppTransfer mServerTransfer;
    private ArrayList<BluetoothOppShareInfo> mShares;
    private BluetoothOppTransfer mTransfer;
    private UpdateThread mUpdateThread;
    private boolean mUpdateThreadRunning;
    private int mOppSdpHandle = -1;
    private Handler mHandler = new Handler() { // from class: com.android.bluetooth.opp.BluetoothOppService.2
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i = msg.what;
            if (i == 1) {
                if (BluetoothOppService.this.mAdapter.isEnabled()) {
                    BluetoothOppService.this.startSocketListener();
                    return;
                }
                return;
            }
            if (i == 2) {
                ContentValues updateValues = new ContentValues();
                Uri contentUri = Uri.parse(BluetoothShare.CONTENT_URI + "/" + msg.arg1);
                updateValues.put("scanned", (Integer) 1);
                updateValues.put("uri", msg.obj.toString());
                updateValues.put(BluetoothShare.MIMETYPE, BluetoothOppService.this.getContentResolver().getType(Uri.parse(msg.obj.toString())));
                BluetoothOppService.this.getContentResolver().update(contentUri, updateValues, null, null);
                synchronized (BluetoothOppService.this) {
                    BluetoothOppService.this.mMediaScanInProgress = false;
                }
                return;
            }
            if (i == 3) {
                Log.v(BluetoothOppService.TAG, "Update mInfo.id " + msg.arg1 + " for MEDIA_SCANNED_FAILED");
                ContentValues updateValues1 = new ContentValues();
                Uri contentUri1 = Uri.parse(BluetoothShare.CONTENT_URI + "/" + msg.arg1);
                updateValues1.put("scanned", (Integer) 2);
                BluetoothOppService.this.getContentResolver().update(contentUri1, updateValues1, null, null);
                synchronized (BluetoothOppService.this) {
                    BluetoothOppService.this.mMediaScanInProgress = false;
                }
                return;
            }
            if (i == 4) {
                if (BluetoothOppService.this.mBatches.size() != 0) {
                    if (BluetoothOppService.this.mIncomingRetries == 20) {
                        Log.w(BluetoothOppService.TAG, "Retried 20 seconds, reject connection");
                        try {
                            BluetoothOppService.this.mPendingConnection.close();
                        } catch (IOException e) {
                            Log.e(BluetoothOppService.TAG, "close tranport error");
                        }
                        if (BluetoothOppService.this.mServerSocket != null) {
                            BluetoothOppService.this.acceptNewConnections();
                        }
                        BluetoothOppService.this.mIncomingRetries = 0;
                        BluetoothOppService.this.mPendingConnection = null;
                        return;
                    }
                    Log.i(BluetoothOppService.TAG, "OPP busy! Retry after 1 second");
                    BluetoothOppService.this.mIncomingRetries++;
                    Message msg2 = Message.obtain(BluetoothOppService.this.mHandler);
                    msg2.what = 4;
                    BluetoothOppService.this.mHandler.sendMessageDelayed(msg2, 1000L);
                    return;
                }
                Log.i(BluetoothOppService.TAG, "Start Obex Server");
                BluetoothOppService bluetoothOppService = BluetoothOppService.this;
                bluetoothOppService.createServerSession(bluetoothOppService.mPendingConnection);
                BluetoothOppService.this.mIncomingRetries = 0;
                BluetoothOppService.this.mPendingConnection = null;
                return;
            }
            if (i == 100) {
                ObexTransport transport = (ObexTransport) msg.obj;
                if (BluetoothOppService.this.mBatches.size() != 0 || BluetoothOppService.this.mPendingConnection != null) {
                    if (BluetoothOppService.this.mPendingConnection != null) {
                        Log.w(BluetoothOppService.TAG, "OPP busy! Reject connection");
                        try {
                            transport.close();
                            return;
                        } catch (IOException e2) {
                            Log.e(BluetoothOppService.TAG, "close tranport error");
                            return;
                        }
                    }
                    Log.i(BluetoothOppService.TAG, "OPP busy! Retry after 1 second");
                    BluetoothOppService.this.mIncomingRetries++;
                    BluetoothOppService.this.mPendingConnection = transport;
                    Message msg1 = Message.obtain(BluetoothOppService.this.mHandler);
                    msg1.what = 4;
                    BluetoothOppService.this.mHandler.sendMessageDelayed(msg1, 1000L);
                    return;
                }
                Log.i(BluetoothOppService.TAG, "Start Obex Server");
                BluetoothOppService.this.createServerSession(transport);
                return;
            }
            if (i == 200) {
                BluetoothOppService.this.stopListeners();
                BluetoothOppService.this.mListenStarted = false;
                if (BluetoothOppService.this.mServerTransfer != null) {
                    BluetoothOppService.this.mServerTransfer.onBatchCanceled();
                    BluetoothOppService.this.mServerTransfer = null;
                }
                if (BluetoothOppService.this.mTransfer != null) {
                    BluetoothOppService.this.mTransfer.onBatchCanceled();
                    BluetoothOppService.this.mTransfer = null;
                }
                BluetoothOppService.this.unregisterReceivers();
                synchronized (BluetoothOppService.this) {
                    if (BluetoothOppService.this.mUpdateThread != null) {
                        BluetoothOppService.this.mUpdateThread.interrupt();
                    }
                }
                while (BluetoothOppService.this.mUpdateThread != null && BluetoothOppService.this.mUpdateThreadRunning) {
                    try {
                        Thread.sleep(50L);
                    } catch (Exception e3) {
                        Log.e(BluetoothOppService.TAG, "Thread sleep", e3);
                    }
                }
                synchronized (BluetoothOppService.this) {
                    if (BluetoothOppService.this.mUpdateThread != null) {
                        try {
                            BluetoothOppService.this.mUpdateThread.join();
                        } catch (InterruptedException e4) {
                            Log.e(BluetoothOppService.TAG, "Interrupted", e4);
                        }
                        BluetoothOppService.this.mUpdateThread = null;
                    }
                }
                BluetoothOppService.this.mNotifier.cancelNotifications();
            }
        }
    };
    private final BroadcastReceiver mBluetoothReceiver = new BroadcastReceiver() { // from class: com.android.bluetooth.opp.BluetoothOppService.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                if (intExtra != 12) {
                    if (intExtra == 13) {
                        BluetoothOppService.this.mHandler.sendMessage(BluetoothOppService.this.mHandler.obtainMessage(200));
                        return;
                    }
                    return;
                }
                BluetoothOppService.this.startListener();
                synchronized (this) {
                    if (BluetoothOppManager.getInstance(context).mSendingFlag) {
                        BluetoothOppManager.getInstance(context).mSendingFlag = false;
                        Intent in1 = new Intent("android.bluetooth.devicepicker.action.LAUNCH");
                        in1.putExtra("android.bluetooth.devicepicker.extra.NEED_AUTH", false);
                        in1.putExtra("android.bluetooth.devicepicker.extra.FILTER_TYPE", 2);
                        in1.putExtra("android.bluetooth.devicepicker.extra.LAUNCH_PACKAGE", "com.android.bluetooth");
                        in1.putExtra("android.bluetooth.devicepicker.extra.DEVICE_PICKER_LAUNCH_CLASS", BluetoothOppReceiver.class.getName());
                        in1.setFlags(VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES);
                        context.startActivity(in1);
                    }
                }
            }
        }
    };

    private class BluetoothShareContentObserver extends ContentObserver {
        BluetoothShareContentObserver() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            BluetoothOppService.this.updateFromProvider();
        }
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected ProfileService.IProfileServiceBinder initBinder() {
        return new OppBinder(this);
    }

    private static class OppBinder extends Binder implements ProfileService.IProfileServiceBinder {
        OppBinder(BluetoothOppService service) {
        }

        @Override // com.android.bluetooth.btservice.ProfileService.IProfileServiceBinder
        public void cleanup() {
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.android.bluetooth.opp.BluetoothOppService$1] */
    @Override // com.android.bluetooth.btservice.ProfileService
    protected void create() {
        this.mShares = new ArrayList<>();
        this.mBatches = new ArrayList<>();
        this.mBatchId = 1;
        final ContentResolver contentResolver = getContentResolver();
        new Thread("trimDatabase") { // from class: com.android.bluetooth.opp.BluetoothOppService.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                BluetoothOppService.trimDatabase(contentResolver);
            }
        }.start();
        IntentFilter filter = new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
        registerReceiver(this.mBluetoothReceiver, filter);
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        synchronized (this) {
            if (this.mAdapter == null) {
                Log.w(TAG, "Local BT device is not enabled");
            }
        }
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    public boolean start() {
        this.mObserver = new BluetoothShareContentObserver();
        getContentResolver().registerContentObserver(BluetoothShare.CONTENT_URI, true, this.mObserver);
        BluetoothOppNotification bluetoothOppNotification = new BluetoothOppNotification(this);
        this.mNotifier = bluetoothOppNotification;
        bluetoothOppNotification.mNotificationMgr.cancelAll();
        this.mNotifier.updateNotification();
        updateFromProvider();
        setBluetoothOppService(this);
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    public boolean stop() {
        if (sBluetoothOppService == null) {
            Log.w(TAG, "stop() called before start()");
            return true;
        }
        setBluetoothOppService(null);
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(200));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startListener() {
        if (!this.mListenStarted && this.mAdapter.isEnabled()) {
            Handler handler = this.mHandler;
            handler.sendMessage(handler.obtainMessage(1));
            this.mListenStarted = true;
        }
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    public void dump(StringBuilder sb) {
        super.dump(sb);
        if (this.mShares.size() > 0) {
            println(sb, "Shares:");
            for (BluetoothOppShareInfo info : this.mShares) {
                String dir = info.mDirection == 0 ? " -> " : " <- ";
                SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss", Locale.US);
                Date date = new Date(info.mTimestamp);
                println(sb, "  " + format.format(date) + dir + info.mCurrentBytes + "/" + info.mTotalBytes);
            }
        }
    }

    public static synchronized BluetoothOppService getBluetoothOppService() {
        if (sBluetoothOppService == null) {
            Log.w(TAG, "getBluetoothOppService(): service is null");
            return null;
        }
        if (!sBluetoothOppService.isAvailable()) {
            Log.w(TAG, "getBluetoothOppService(): service is not available");
            return null;
        }
        return sBluetoothOppService;
    }

    private static synchronized void setBluetoothOppService(BluetoothOppService instance) {
        sBluetoothOppService = instance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSocketListener() {
        ObexServerSockets obexServerSockets;
        stopListeners();
        this.mServerSocket = ObexServerSockets.createInsecure(this);
        acceptNewConnections();
        SdpManager sdpManager = SdpManager.getDefaultManager();
        if (sdpManager == null || (obexServerSockets = this.mServerSocket) == null) {
            Log.e(TAG, "ERROR:serversocket object is NULL  sdp manager :" + sdpManager + " mServerSocket:" + this.mServerSocket);
            return;
        }
        this.mOppSdpHandle = sdpManager.createOppOpsRecord("OBEX Object Push", obexServerSockets.getRfcommChannel(), this.mServerSocket.getL2capPsm(), 258, SUPPORTED_OPP_FORMAT);
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected void cleanup() {
        stopListeners();
        ArrayList<BluetoothOppBatch> arrayList = this.mBatches;
        if (arrayList != null) {
            arrayList.clear();
        }
        ArrayList<BluetoothOppShareInfo> arrayList2 = this.mShares;
        if (arrayList2 != null) {
            arrayList2.clear();
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterReceivers() {
        try {
            if (this.mObserver != null) {
                getContentResolver().unregisterContentObserver(this.mObserver);
                this.mObserver = null;
            }
            unregisterReceiver(this.mBluetoothReceiver);
        } catch (IllegalArgumentException e) {
            Log.w(TAG, "unregisterReceivers " + e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createServerSession(ObexTransport transport) {
        BluetoothOppObexServerSession bluetoothOppObexServerSession = new BluetoothOppObexServerSession(this, transport, this);
        this.mServerSession = bluetoothOppObexServerSession;
        bluetoothOppObexServerSession.preStart();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFromProvider() {
        synchronized (this) {
            this.mPendingUpdate = true;
            if (this.mUpdateThread == null) {
                UpdateThread updateThread = new UpdateThread();
                this.mUpdateThread = updateThread;
                updateThread.start();
                this.mUpdateThreadRunning = true;
            }
        }
    }

    private class UpdateThread extends Thread {
        private boolean mIsInterrupted;

        UpdateThread() {
            super("Bluetooth Share Service");
            this.mIsInterrupted = false;
        }

        @Override // java.lang.Thread
        public void interrupt() {
            this.mIsInterrupted = true;
            super.interrupt();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(10);
            while (!this.mIsInterrupted) {
                synchronized (BluetoothOppService.this) {
                    if (BluetoothOppService.this.mUpdateThread == this) {
                        if (!BluetoothOppService.this.mPendingUpdate) {
                            BluetoothOppService.this.mUpdateThread = null;
                            BluetoothOppService.this.mUpdateThreadRunning = false;
                            return;
                        }
                        BluetoothOppService.this.mPendingUpdate = false;
                        Cursor cursor = BluetoothOppService.this.getContentResolver().query(BluetoothShare.CONTENT_URI, null, null, null, "_id");
                        if (cursor == null) {
                            BluetoothOppService.this.mUpdateThreadRunning = false;
                            return;
                        }
                        cursor.moveToFirst();
                        int arrayPos = 0;
                        boolean isAfterLast = cursor.isAfterLast();
                        int idColumn = cursor.getColumnIndexOrThrow("_id");
                        while (true) {
                            if (!isAfterLast || (arrayPos < BluetoothOppService.this.mShares.size() && BluetoothOppService.this.mListenStarted)) {
                                if (isAfterLast) {
                                    BluetoothOppService.this.mShares.size();
                                    BluetoothOppService.this.deleteShare(arrayPos);
                                } else {
                                    int id = cursor.getInt(idColumn);
                                    if (arrayPos == BluetoothOppService.this.mShares.size()) {
                                        BluetoothOppService.this.insertShare(cursor, arrayPos);
                                        arrayPos++;
                                        cursor.moveToNext();
                                        isAfterLast = cursor.isAfterLast();
                                    } else {
                                        int arrayId = 0;
                                        if (BluetoothOppService.this.mShares.size() != 0) {
                                            arrayId = ((BluetoothOppShareInfo) BluetoothOppService.this.mShares.get(arrayPos)).mId;
                                        }
                                        if (arrayId < id) {
                                            BluetoothOppService.this.deleteShare(arrayPos);
                                        } else if (arrayId == id) {
                                            BluetoothOppService.this.updateShare(cursor, arrayPos);
                                            BluetoothOppService.this.scanFileIfNeeded(arrayPos);
                                            arrayPos++;
                                            cursor.moveToNext();
                                            isAfterLast = cursor.isAfterLast();
                                        } else {
                                            BluetoothOppService.this.insertShare(cursor, arrayPos);
                                            arrayPos++;
                                            cursor.moveToNext();
                                            isAfterLast = cursor.isAfterLast();
                                        }
                                    }
                                }
                            }
                        }
                        BluetoothOppService.this.mNotifier.updateNotification();
                        cursor.close();
                    } else {
                        BluetoothOppService.this.mUpdateThreadRunning = false;
                        throw new IllegalStateException("multiple UpdateThreads in BluetoothOppService");
                    }
                }
            }
            BluetoothOppService.this.mUpdateThreadRunning = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void insertShare(Cursor cursor, int arrayPos) {
        Uri uri;
        BluetoothOppTransfer bluetoothOppTransfer;
        BluetoothOppTransfer bluetoothOppTransfer2;
        BluetoothOppSendFileInfo sendFileInfo;
        String uriString = cursor.getString(cursor.getColumnIndexOrThrow("uri"));
        if (uriString != null) {
            uri = Uri.parse(uriString);
            Log.d(TAG, "insertShare parsed URI: " + uri);
        } else {
            uri = null;
            Log.e(TAG, "insertShare found null URI at cursor!");
        }
        BluetoothOppShareInfo info = new BluetoothOppShareInfo(cursor.getInt(cursor.getColumnIndexOrThrow("_id")), uri, cursor.getString(cursor.getColumnIndexOrThrow(BluetoothShare.FILENAME_HINT)), cursor.getString(cursor.getColumnIndexOrThrow(BluetoothShare._DATA)), cursor.getString(cursor.getColumnIndexOrThrow(BluetoothShare.MIMETYPE)), cursor.getInt(cursor.getColumnIndexOrThrow(BluetoothShare.DIRECTION)), cursor.getString(cursor.getColumnIndexOrThrow(BluetoothShare.DESTINATION)), cursor.getInt(cursor.getColumnIndexOrThrow(BluetoothShare.VISIBILITY)), cursor.getInt(cursor.getColumnIndexOrThrow(BluetoothShare.USER_CONFIRMATION)), cursor.getInt(cursor.getColumnIndexOrThrow("status")), cursor.getLong(cursor.getColumnIndexOrThrow(BluetoothShare.TOTAL_BYTES)), cursor.getLong(cursor.getColumnIndexOrThrow(BluetoothShare.CURRENT_BYTES)), cursor.getLong(cursor.getColumnIndexOrThrow("timestamp")), cursor.getInt(cursor.getColumnIndexOrThrow("scanned")) != 0);
        this.mShares.add(arrayPos, info);
        if (info.isObsolete()) {
            Constants.updateShareStatus(this, info.mId, BluetoothShare.STATUS_UNKNOWN_ERROR);
        }
        if (info.isReadyToStart()) {
            if (info.mDirection == 0 && ((sendFileInfo = BluetoothOppUtility.getSendFileInfo(info.mUri)) == null || sendFileInfo.mInputStream == null)) {
                Log.e(TAG, "Can't open file for OUTBOUND info " + info.mId);
                Constants.updateShareStatus(this, info.mId, BluetoothShare.STATUS_BAD_REQUEST);
                BluetoothOppUtility.closeSendFileInfo(info.mUri);
                return;
            }
            if (this.mBatches.size() == 0) {
                BluetoothOppBatch newBatch = new BluetoothOppBatch(this, info);
                newBatch.mId = this.mBatchId;
                this.mBatchId++;
                this.mBatches.add(newBatch);
                if (info.mDirection == 0) {
                    this.mTransfer = new BluetoothOppTransfer(this, newBatch);
                } else if (info.mDirection == 1) {
                    this.mServerTransfer = new BluetoothOppTransfer(this, newBatch, this.mServerSession);
                }
                if (info.mDirection == 0 && (bluetoothOppTransfer2 = this.mTransfer) != null) {
                    bluetoothOppTransfer2.start();
                    return;
                } else {
                    if (info.mDirection == 1 && (bluetoothOppTransfer = this.mServerTransfer) != null) {
                        bluetoothOppTransfer.start();
                        return;
                    }
                    return;
                }
            }
            int i = findBatchWithTimeStamp(info.mTimestamp);
            if (i == -1) {
                BluetoothOppBatch newBatch2 = new BluetoothOppBatch(this, info);
                newBatch2.mId = this.mBatchId;
                this.mBatchId++;
                this.mBatches.add(newBatch2);
                return;
            }
            this.mBatches.get(i).addShare(info);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateShare(Cursor cursor, int arrayPos) {
        int i;
        BluetoothOppShareInfo info = this.mShares.get(arrayPos);
        int statusColumn = cursor.getColumnIndexOrThrow("status");
        info.mId = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        if (info.mUri != null) {
            info.mUri = Uri.parse(stringFromCursor(info.mUri.toString(), cursor, "uri"));
        } else {
            Log.w(TAG, "updateShare() called for ID " + info.mId + " with null URI");
        }
        info.mHint = stringFromCursor(info.mHint, cursor, BluetoothShare.FILENAME_HINT);
        info.mFilename = stringFromCursor(info.mFilename, cursor, BluetoothShare._DATA);
        info.mMimetype = stringFromCursor(info.mMimetype, cursor, BluetoothShare.MIMETYPE);
        info.mDirection = cursor.getInt(cursor.getColumnIndexOrThrow(BluetoothShare.DIRECTION));
        info.mDestination = stringFromCursor(info.mDestination, cursor, BluetoothShare.DESTINATION);
        int newVisibility = cursor.getInt(cursor.getColumnIndexOrThrow(BluetoothShare.VISIBILITY));
        boolean confirmUpdated = false;
        int newConfirm = cursor.getInt(cursor.getColumnIndexOrThrow(BluetoothShare.USER_CONFIRMATION));
        if (info.mVisibility == 0 && newVisibility != 0 && (BluetoothShare.isStatusCompleted(info.mStatus) || newConfirm == 0)) {
            this.mNotifier.mNotificationMgr.cancel(info.mId);
        }
        info.mVisibility = newVisibility;
        if (info.mConfirm == 0 && newConfirm != 0) {
            confirmUpdated = true;
        }
        info.mConfirm = cursor.getInt(cursor.getColumnIndexOrThrow(BluetoothShare.USER_CONFIRMATION));
        int newStatus = cursor.getInt(statusColumn);
        if (BluetoothShare.isStatusCompleted(info.mStatus)) {
            this.mNotifier.mNotificationMgr.cancel(info.mId);
        }
        info.mStatus = newStatus;
        info.mTotalBytes = cursor.getLong(cursor.getColumnIndexOrThrow(BluetoothShare.TOTAL_BYTES));
        info.mCurrentBytes = cursor.getLong(cursor.getColumnIndexOrThrow(BluetoothShare.CURRENT_BYTES));
        info.mTimestamp = cursor.getLong(cursor.getColumnIndexOrThrow("timestamp"));
        info.mMediaScanned = cursor.getInt(cursor.getColumnIndexOrThrow("scanned")) != 0;
        if (confirmUpdated && (i = findBatchWithTimeStamp(info.mTimestamp)) != -1) {
            BluetoothOppBatch batch = this.mBatches.get(i);
            if (this.mServerTransfer != null && batch.mId == this.mServerTransfer.getBatchId()) {
                this.mServerTransfer.confirmStatusChanged();
            }
        }
        int i2 = findBatchWithTimeStamp(info.mTimestamp);
        if (i2 != -1) {
            BluetoothOppBatch batch2 = this.mBatches.get(i2);
            if (batch2.mStatus == 2 || batch2.mStatus == 3) {
                if (batch2.mDirection == 0) {
                    if (this.mTransfer == null) {
                        Log.e(TAG, "Unexpected error! mTransfer is null");
                    } else if (batch2.mId == this.mTransfer.getBatchId()) {
                        this.mTransfer.stop();
                    } else {
                        Log.e(TAG, "Unexpected error! batch id " + batch2.mId + " doesn't match mTransfer id " + this.mTransfer.getBatchId());
                    }
                    this.mTransfer = null;
                } else {
                    if (this.mServerTransfer == null) {
                        Log.e(TAG, "Unexpected error! mServerTransfer is null");
                    } else if (batch2.mId == this.mServerTransfer.getBatchId()) {
                        this.mServerTransfer.stop();
                    } else {
                        Log.e(TAG, "Unexpected error! batch id " + batch2.mId + " doesn't match mServerTransfer id " + this.mServerTransfer.getBatchId());
                    }
                    this.mServerTransfer = null;
                }
                removeBatch(batch2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteShare(int arrayPos) {
        BluetoothOppShareInfo info = this.mShares.get(arrayPos);
        int i = findBatchWithTimeStamp(info.mTimestamp);
        if (i != -1) {
            BluetoothOppBatch batch = this.mBatches.get(i);
            if (batch.hasShare(info)) {
                batch.cancelBatch();
            }
            if (batch.isEmpty()) {
                removeBatch(batch);
            }
        }
        this.mShares.remove(arrayPos);
    }

    private String stringFromCursor(String old, Cursor cursor, String column) {
        int index = cursor.getColumnIndexOrThrow(column);
        if (old == null) {
            return cursor.getString(index);
        }
        if (this.mNewChars == null) {
            this.mNewChars = new CharArrayBuffer(128);
        }
        cursor.copyStringToBuffer(index, this.mNewChars);
        int length = this.mNewChars.sizeCopied;
        if (length != old.length()) {
            return cursor.getString(index);
        }
        CharArrayBuffer charArrayBuffer = this.mOldChars;
        if (charArrayBuffer == null || charArrayBuffer.sizeCopied < length) {
            this.mOldChars = new CharArrayBuffer(length);
        }
        char[] oldArray = this.mOldChars.data;
        char[] newArray = this.mNewChars.data;
        old.getChars(0, length, oldArray, 0);
        for (int i = length - 1; i >= 0; i--) {
            if (oldArray[i] != newArray[i]) {
                return new String(newArray, 0, length);
            }
        }
        return old;
    }

    private int findBatchWithTimeStamp(long timestamp) {
        for (int i = this.mBatches.size() - 1; i >= 0; i--) {
            if (this.mBatches.get(i).mTimestamp == timestamp) {
                return i;
            }
        }
        return -1;
    }

    private void removeBatch(BluetoothOppBatch batch) {
        BluetoothOppObexServerSession bluetoothOppObexServerSession;
        this.mBatches.remove(batch);
        if (this.mBatches.size() > 0) {
            for (BluetoothOppBatch nextBatch : this.mBatches) {
                if (nextBatch.mStatus == 1) {
                    return;
                }
                if (nextBatch.mDirection == 0) {
                    BluetoothOppTransfer bluetoothOppTransfer = new BluetoothOppTransfer(this, nextBatch);
                    this.mTransfer = bluetoothOppTransfer;
                    bluetoothOppTransfer.start();
                    return;
                } else if (nextBatch.mDirection == 1 && (bluetoothOppObexServerSession = this.mServerSession) != null) {
                    BluetoothOppTransfer bluetoothOppTransfer2 = new BluetoothOppTransfer(this, nextBatch, bluetoothOppObexServerSession);
                    this.mServerTransfer = bluetoothOppTransfer2;
                    bluetoothOppTransfer2.start();
                    if (nextBatch.getPendingShare() != null && nextBatch.getPendingShare().mConfirm == 1) {
                        this.mServerTransfer.confirmStatusChanged();
                        return;
                    }
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scanFileIfNeeded(int arrayPos) {
        BluetoothOppShareInfo info = this.mShares.get(arrayPos);
        boolean isFileReceived = BluetoothShare.isStatusSuccess(info.mStatus) && info.mDirection == 1 && !info.mMediaScanned && info.mConfirm != 5;
        if (!isFileReceived) {
            return;
        }
        synchronized (this) {
            if (!this.mMediaScanInProgress) {
                this.mMediaScanInProgress = true;
                new MediaScannerNotifier(this, info, this.mHandler);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void trimDatabase(ContentResolver contentResolver) {
        contentResolver.delete(BluetoothShare.CONTENT_URI, WHERE_INVISIBLE_UNCONFIRMED, null);
        Cursor cursor = contentResolver.query(BluetoothShare.CONTENT_URI, new String[]{"_id"}, WHERE_INBOUND_SUCCESS, null, "_id");
        if (cursor == null) {
            return;
        }
        int recordNum = cursor.getCount();
        if (recordNum > 50) {
            int numToDelete = recordNum - 50;
            if (cursor.moveToPosition(numToDelete)) {
                int columnId = cursor.getColumnIndexOrThrow("_id");
                long id = cursor.getLong(columnId);
                contentResolver.delete(BluetoothShare.CONTENT_URI, "_id < " + id, null);
            }
        }
        cursor.close();
    }

    private static class MediaScannerNotifier implements MediaScannerConnection.MediaScannerConnectionClient {
        private Handler mCallback;
        private MediaScannerConnection mConnection;
        private Context mContext;
        private BluetoothOppShareInfo mInfo;

        MediaScannerNotifier(Context context, BluetoothOppShareInfo info, Handler handler) {
            this.mContext = context;
            this.mInfo = info;
            this.mCallback = handler;
            MediaScannerConnection mediaScannerConnection = new MediaScannerConnection(this.mContext, this);
            this.mConnection = mediaScannerConnection;
            mediaScannerConnection.connect();
        }

        @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
        public void onMediaScannerConnected() {
            this.mConnection.scanFile(this.mInfo.mFilename, this.mInfo.mMimetype);
        }

        @Override // android.media.MediaScannerConnection.OnScanCompletedListener
        public void onScanCompleted(String path, Uri uri) {
            try {
                try {
                    if (uri != null) {
                        Message msg = Message.obtain();
                        msg.setTarget(this.mCallback);
                        msg.what = 2;
                        msg.arg1 = this.mInfo.mId;
                        msg.obj = uri;
                        msg.sendToTarget();
                    } else {
                        Message msg2 = Message.obtain();
                        msg2.setTarget(this.mCallback);
                        msg2.what = 3;
                        msg2.arg1 = this.mInfo.mId;
                        msg2.sendToTarget();
                    }
                } catch (NullPointerException ex) {
                    Log.v(BluetoothOppService.TAG, "!!!MediaScannerConnection exception: " + ex);
                }
            } finally {
                this.mConnection.disconnect();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopListeners() {
        if (this.mAdapter != null && this.mOppSdpHandle >= 0 && SdpManager.getDefaultManager() != null) {
            boolean status = SdpManager.getDefaultManager().removeSdpRecord(this.mOppSdpHandle);
            Log.d(TAG, "RemoveSDPrecord returns " + status);
            this.mOppSdpHandle = -1;
        }
        ObexServerSockets obexServerSockets = this.mServerSocket;
        if (obexServerSockets != null) {
            obexServerSockets.shutdown(false);
            this.mServerSocket = null;
        }
    }

    @Override // com.android.bluetooth.IObexConnectionHandler
    public boolean onConnect(BluetoothDevice device, BluetoothSocket socket) {
        if (!this.mAcceptNewConnections) {
            Log.d(TAG, " onConnect BluetoothSocket :" + socket + " rejected");
            return false;
        }
        BluetoothObexTransport transport = new BluetoothObexTransport(socket);
        Message msg = this.mHandler.obtainMessage(100);
        msg.obj = transport;
        msg.sendToTarget();
        this.mAcceptNewConnections = false;
        return true;
    }

    @Override // com.android.bluetooth.IObexConnectionHandler
    public void onAcceptFailed() {
        Log.d(TAG, " onAcceptFailed:");
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(1));
    }

    void acceptNewConnections() {
        this.mAcceptNewConnections = true;
    }
}
