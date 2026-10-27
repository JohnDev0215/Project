package com.android.bluetooth.opp;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;
import com.android.bluetooth.BluetoothMetricsProto;
import com.android.bluetooth.BluetoothObexTransport;
import com.android.bluetooth.BluetoothStatsLog;
import com.android.bluetooth.btservice.MetricsLogger;
import java.io.IOException;
import javax.obex.Authenticator;
import javax.obex.HeaderSet;
import javax.obex.ObexTransport;
import javax.obex.ServerRequestHandler;
import javax.obex.ServerSession;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothOppObexServerSession extends ServerRequestHandler implements BluetoothOppObexSession {
    private static final boolean D = false;
    private static final String TAG = "BtOppObexServer";
    private static final boolean V = false;
    private BluetoothOppService mBluetoothOppService;
    private Context mContext;
    private BluetoothOppReceiveFileInfo mFileInfo;
    private BluetoothOppShareInfo mInfo;
    private int mLocalShareInfoId;
    private int mNumFilesAttemptedToReceive;
    private PowerManager.WakeLock mPartialWakeLock;
    private ServerSession mSession;
    private long mTimestamp;
    private ObexTransport mTransport;
    private Handler mCallback = null;
    private boolean mServerBlocking = true;
    private int mAccepted = 0;
    private boolean mInterrupted = false;
    boolean mTimeoutMsgSent = false;

    public BluetoothOppObexServerSession(Context context, ObexTransport transport, BluetoothOppService service) {
        this.mContext = context;
        this.mTransport = transport;
        this.mBluetoothOppService = service;
        PowerManager pm = (PowerManager) context.getSystemService("power");
        PowerManager.WakeLock wakeLockNewWakeLock = pm.newWakeLock(1, TAG);
        this.mPartialWakeLock = wakeLockNewWakeLock;
        wakeLockNewWakeLock.setReferenceCounted(false);
    }

    @Override // com.android.bluetooth.opp.BluetoothOppObexSession
    public void unblock() {
        this.mServerBlocking = false;
    }

    public void preStart() {
        try {
            this.mSession = new ServerSession(this.mTransport, this, (Authenticator) null);
        } catch (IOException e) {
            Log.e(TAG, "Create server session error" + e);
        }
    }

    @Override // com.android.bluetooth.opp.BluetoothOppObexSession
    public void start(Handler handler, int numShares) {
        this.mCallback = handler;
    }

    @Override // com.android.bluetooth.opp.BluetoothOppObexSession
    public void stop() {
        this.mInterrupted = true;
        ServerSession serverSession = this.mSession;
        if (serverSession != null) {
            try {
                serverSession.close();
                this.mTransport.close();
            } catch (IOException e) {
                Log.e(TAG, "close mTransport error" + e);
            }
        }
        this.mCallback = null;
        this.mSession = null;
    }

    @Override // com.android.bluetooth.opp.BluetoothOppObexSession
    public void addShare(BluetoothOppShareInfo info) {
        this.mInfo = info;
        this.mFileInfo = processShareInfo();
    }

    /* JADX WARN: Removed duplicated region for block: B:144:0x015b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int onPut(javax.obex.Operation r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 731
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.opp.BluetoothOppObexServerSession.onPut(javax.obex.Operation):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x016d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int receiveFile(com.android.bluetooth.opp.BluetoothOppReceiveFileInfo r28, javax.obex.Operation r29) throws java.io.FileNotFoundException {
        /*
            Method dump skipped, instruction units count: 384
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.opp.BluetoothOppObexServerSession.receiveFile(com.android.bluetooth.opp.BluetoothOppReceiveFileInfo, javax.obex.Operation):int");
    }

    private BluetoothOppReceiveFileInfo processShareInfo() {
        BluetoothOppReceiveFileInfo fileInfo = BluetoothOppReceiveFileInfo.generateFileInfo(this.mContext, this.mInfo.mId);
        return fileInfo;
    }

    public int onConnect(HeaderSet request, HeaderSet reply) {
        String destination;
        try {
            byte[] uuid = (byte[]) request.getHeader(70);
            if (uuid != null) {
                return 198;
            }
            Long objectCount = (Long) request.getHeader(BluetoothShare.STATUS_RUNNING);
            ObexTransport obexTransport = this.mTransport;
            if (obexTransport instanceof BluetoothObexTransport) {
                destination = ((BluetoothObexTransport) obexTransport).getRemoteAddress();
            } else {
                destination = "FF:FF:FF:00:00:00";
            }
            boolean isHandover = BluetoothOppManager.getInstance(this.mContext).isWhitelisted(destination);
            if (isHandover) {
                Intent intent = new Intent("android.nfc.handover.intent.action.HANDOVER_STARTED");
                if (objectCount != null) {
                    intent.putExtra("android.nfc.handover.intent.extra.OBJECT_COUNT", objectCount.intValue());
                } else {
                    intent.putExtra("android.nfc.handover.intent.extra.OBJECT_COUNT", -1);
                }
                intent.putExtra("android.nfc.handover.intent.extra.ADDRESS", destination);
                this.mContext.sendBroadcast(intent, "android.permission.NFC_HANDOVER_STATUS");
            }
            this.mTimestamp = System.currentTimeMillis();
            this.mNumFilesAttemptedToReceive = 0;
            return BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
        } catch (IOException e) {
            Log.e(TAG, e.toString());
            return 208;
        }
    }

    public void onDisconnect(HeaderSet req, HeaderSet resp) {
        if (this.mNumFilesAttemptedToReceive > 0) {
            MetricsLogger.logProfileConnectionEvent(BluetoothMetricsProto.ProfileId.OPP);
        }
        resp.responseCode = BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
    }

    private synchronized void releaseWakeLocks() {
        if (this.mPartialWakeLock.isHeld()) {
            this.mPartialWakeLock.release();
        }
    }

    public void onClose() {
        releaseWakeLocks();
        this.mBluetoothOppService.acceptNewConnections();
        BluetoothOppUtility.cancelNotification(this.mContext);
        Handler handler = this.mCallback;
        if (handler != null) {
            Message msg = Message.obtain(handler);
            msg.what = 1;
            msg.obj = this.mInfo;
            msg.sendToTarget();
        }
    }
}
