package com.android.bluetooth.opp;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.Process;
import android.util.Log;
import com.android.bluetooth.BluetoothMetricsProto;
import com.android.bluetooth.btservice.MetricsLogger;
import java.io.IOException;
import java.io.InputStream;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;
import javax.obex.ObexTransport;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothOppObexClientSession implements BluetoothOppObexSession {
    private static final boolean D = false;
    private static final String TAG = "BtOppObexClient";
    private static final boolean V = false;
    private Handler mCallback;
    private Context mContext;
    private volatile boolean mInterrupted;
    private int mNumFilesAttemptedToSend;
    private ClientThread mThread;
    private ObexTransport mTransport;
    private volatile boolean mWaitingForRemote;

    static /* synthetic */ int access$208(BluetoothOppObexClientSession x0) {
        int i = x0.mNumFilesAttemptedToSend;
        x0.mNumFilesAttemptedToSend = i + 1;
        return i;
    }

    public BluetoothOppObexClientSession(Context context, ObexTransport transport) {
        if (transport == null) {
            throw new NullPointerException("transport is null");
        }
        this.mContext = context;
        this.mTransport = transport;
    }

    @Override // com.android.bluetooth.opp.BluetoothOppObexSession
    public void start(Handler handler, int numShares) {
        this.mCallback = handler;
        ClientThread clientThread = new ClientThread(this.mContext, this.mTransport, numShares);
        this.mThread = clientThread;
        clientThread.start();
    }

    @Override // com.android.bluetooth.opp.BluetoothOppObexSession
    public void stop() {
        if (this.mThread != null) {
            this.mInterrupted = true;
            try {
                this.mThread.interrupt();
                this.mThread.join();
                this.mThread = null;
            } catch (InterruptedException e) {
            }
        }
        BluetoothOppUtility.cancelNotification(this.mContext);
        this.mCallback = null;
    }

    @Override // com.android.bluetooth.opp.BluetoothOppObexSession
    public void addShare(BluetoothOppShareInfo share) {
        this.mThread.addShare(share);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int readFully(InputStream is, byte[] buffer, int size) throws IOException {
        int done = 0;
        while (done < size) {
            int got = is.read(buffer, done, size - done);
            if (got <= 0) {
                break;
            }
            done += got;
        }
        return done;
    }

    private class ClientThread extends Thread {
        private static final int SLEEP_TIME = 500;
        private boolean mConnected;
        private Context mContext1;
        private ClientSession mCs;
        private BluetoothOppSendFileInfo mFileInfo;
        private BluetoothOppShareInfo mInfo;
        private int mNumShares;
        private ObexTransport mTransport1;
        private volatile boolean mWaitingForShare;
        private PowerManager.WakeLock mWakeLock;

        ClientThread(Context context, ObexTransport transport, int initialNumShares) {
            super("BtOpp ClientThread");
            this.mFileInfo = null;
            this.mConnected = false;
            this.mContext1 = context;
            this.mTransport1 = transport;
            this.mWaitingForShare = true;
            BluetoothOppObexClientSession.this.mWaitingForRemote = false;
            this.mNumShares = initialNumShares;
            PowerManager pm = (PowerManager) this.mContext1.getSystemService("power");
            this.mWakeLock = pm.newWakeLock(1, BluetoothOppObexClientSession.TAG);
        }

        public void addShare(BluetoothOppShareInfo info) {
            this.mInfo = info;
            this.mFileInfo = processShareInfo();
            this.mWaitingForShare = false;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws Throwable {
            Process.setThreadPriority(10);
            this.mWakeLock.acquire();
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                BluetoothOppObexClientSession.this.mInterrupted = true;
            }
            if (!BluetoothOppObexClientSession.this.mInterrupted) {
                connect(this.mNumShares);
            }
            BluetoothOppObexClientSession.this.mNumFilesAttemptedToSend = 0;
            while (!BluetoothOppObexClientSession.this.mInterrupted) {
                if (!this.mWaitingForShare) {
                    doSend();
                } else {
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException e2) {
                    }
                }
            }
            disconnect();
            if (this.mWakeLock.isHeld()) {
                this.mWakeLock.release();
            }
            if (BluetoothOppObexClientSession.this.mNumFilesAttemptedToSend > 0) {
                MetricsLogger.logProfileConnectionEvent(BluetoothMetricsProto.ProfileId.OPP);
            }
            Message msg = Message.obtain(BluetoothOppObexClientSession.this.mCallback);
            msg.what = 1;
            msg.obj = this.mInfo;
            msg.sendToTarget();
        }

        private void disconnect() {
            try {
                if (this.mCs != null) {
                    this.mCs.disconnect((HeaderSet) null);
                }
                this.mCs = null;
            } catch (IOException e) {
                Log.w(BluetoothOppObexClientSession.TAG, "OBEX session disconnect error" + e);
            }
            try {
                if (this.mCs != null) {
                    this.mCs.close();
                }
            } catch (IOException e2) {
                Log.w(BluetoothOppObexClientSession.TAG, "OBEX session close error" + e2);
            }
            ObexTransport obexTransport = this.mTransport1;
            if (obexTransport != null) {
                try {
                    obexTransport.close();
                } catch (IOException e3) {
                    Log.e(BluetoothOppObexClientSession.TAG, "mTransport.close error");
                }
            }
        }

        private void connect(int numShares) {
            try {
                this.mCs = new ClientSession(this.mTransport1);
                this.mConnected = true;
            } catch (IOException e) {
                Log.e(BluetoothOppObexClientSession.TAG, "OBEX session create error");
            }
            if (this.mConnected) {
                this.mConnected = false;
                HeaderSet hs = new HeaderSet();
                hs.setHeader(BluetoothShare.STATUS_RUNNING, Long.valueOf(numShares));
                synchronized (this) {
                    BluetoothOppObexClientSession.this.mWaitingForRemote = true;
                }
                try {
                    this.mCs.connect(hs);
                    this.mConnected = true;
                } catch (IOException e2) {
                    Log.e(BluetoothOppObexClientSession.TAG, "OBEX session connect error");
                }
            }
            synchronized (this) {
                BluetoothOppObexClientSession.this.mWaitingForRemote = false;
            }
        }

        private void doSend() throws Throwable {
            int i;
            int status = BluetoothShare.STATUS_SUCCESS;
            while (this.mFileInfo == null) {
                try {
                    Thread.sleep(50L);
                } catch (InterruptedException e) {
                    status = BluetoothShare.STATUS_CANCELED;
                }
            }
            if (!this.mConnected) {
                status = BluetoothShare.STATUS_CONNECTION_ERROR;
            }
            if (status == 200) {
                if (this.mFileInfo.mFileName != null) {
                    status = sendFile(this.mFileInfo);
                } else {
                    status = this.mFileInfo.mStatus;
                }
                this.mWaitingForShare = true;
            } else {
                Constants.updateShareStatus(this.mContext1, this.mInfo.mId, status);
            }
            Message msg = Message.obtain(BluetoothOppObexClientSession.this.mCallback);
            if (status == 200) {
                i = 0;
            } else {
                i = 2;
            }
            msg.what = i;
            this.mInfo.mStatus = status;
            msg.obj = this.mInfo;
            msg.sendToTarget();
        }

        private BluetoothOppSendFileInfo processShareInfo() {
            BluetoothOppSendFileInfo fileInfo = BluetoothOppUtility.getSendFileInfo(this.mInfo.mUri);
            if (fileInfo.mFileName == null || fileInfo.mLength == 0) {
                Constants.updateShareStatus(this.mContext1, this.mInfo.mId, fileInfo.mStatus);
            } else {
                ContentValues updateValues = new ContentValues();
                Uri contentUri = Uri.parse(BluetoothShare.CONTENT_URI + "/" + this.mInfo.mId);
                updateValues.put(BluetoothShare.FILENAME_HINT, fileInfo.mFileName);
                updateValues.put(BluetoothShare.TOTAL_BYTES, Long.valueOf(fileInfo.mLength));
                updateValues.put(BluetoothShare.MIMETYPE, fileInfo.mMimetype);
                this.mContext1.getContentResolver().update(contentUri, updateValues, null, null);
            }
            return fileInfo;
        }

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:95:0x022e
            	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:226)
            	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:196)
            	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:63)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
            */
        private int sendFile(com.android.bluetooth.opp.BluetoothOppSendFileInfo r39) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 2206
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.opp.BluetoothOppObexClientSession.ClientThread.sendFile(com.android.bluetooth.opp.BluetoothOppSendFileInfo):int");
        }

        private void handleSendException(String exception) {
            Log.e(BluetoothOppObexClientSession.TAG, "Error when sending file: " + exception);
            Constants.updateShareStatus(this.mContext1, this.mInfo.mId, BluetoothShare.STATUS_OBEX_DATA_ERROR);
            BluetoothOppObexClientSession.this.mCallback.removeMessages(4);
        }

        @Override // java.lang.Thread
        public void interrupt() {
            super.interrupt();
            synchronized (this) {
                if (BluetoothOppObexClientSession.this.mWaitingForRemote) {
                    try {
                        this.mTransport1.close();
                    } catch (IOException e) {
                        Log.e(BluetoothOppObexClientSession.TAG, "mTransport.close error");
                    }
                    Message msg = Message.obtain(BluetoothOppObexClientSession.this.mCallback);
                    msg.what = 3;
                    if (this.mInfo != null) {
                        msg.obj = this.mInfo;
                    }
                    msg.sendToTarget();
                }
            }
        }
    }

    public static void applyRemoteDeviceQuirks(HeaderSet request, String address, String filename) {
        if (address != null && address.startsWith("00:04:48")) {
            char[] c = filename.toCharArray();
            boolean firstDot = true;
            boolean modified = false;
            for (int i = c.length - 1; i >= 0; i--) {
                if (c[i] == '.') {
                    if (!firstDot) {
                        modified = true;
                        c[i] = '_';
                    }
                    firstDot = false;
                }
            }
            if (modified) {
                String newFilename = new String(c);
                request.setHeader(1, newFilename);
                Log.i(TAG, "Sending file \"" + filename + "\" as \"" + newFilename + "\" to workaround Poloroid filename quirk");
            }
        }
    }

    @Override // com.android.bluetooth.opp.BluetoothOppObexSession
    public void unblock() {
    }
}
