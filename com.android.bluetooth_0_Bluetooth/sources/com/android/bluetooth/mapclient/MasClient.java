package com.android.bluetooth.mapclient;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.bluetooth.SdpMasRecord;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import androidx.core.view.PointerIconCompat;
import com.android.bluetooth.BluetoothObexTransport;
import com.android.bluetooth.statemachine.StateMachine;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import java.io.IOException;
import java.lang.ref.WeakReference;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;

/* JADX INFO: loaded from: classes.dex */
public class MasClient {
    private static final byte[] BLUETOOTH_UUID_OBEX_MAS = {-69, 88, 43, SignedBytes.MAX_POWER_OF_TWO, 66, Ascii.FF, 17, -37, -80, -34, 8, 0, 32, Ascii.FF, -102, 102};
    private static final int CONNECT = 0;
    private static final boolean DBG = false;
    private static final int DISCONNECT = 1;
    private static final int L2CAP_INVALID_PSM = -1;
    private static final int MAP_FEATURE_BROWSING = 4;
    private static final int MAP_FEATURE_DELETE_BIT = 16;
    private static final int MAP_FEATURE_EXTENDED_EVENT_REPORT_1_1 = 64;
    private static final int MAP_FEATURE_NOTIFICATION = 2;
    private static final int MAP_FEATURE_NOTIFICATION_REGISTRATION = 1;
    private static final int MAP_FEATURE_UPLOADING = 8;
    static final int MAP_SUPPORTED_FEATURES = 95;
    private static final byte OAP_TAGID_MAP_SUPPORTED_FEATURES = 41;
    private static final int REQUEST = 2;
    private static final String TAG = "MasClient";
    private static final boolean VDBG = false;
    private final StateMachine mCallback;
    private boolean mConnected = false;
    private Handler mHandler;
    private BluetoothDevice mRemoteDevice;
    SdpMasRecord mSdpMasRecord;
    private ClientSession mSession;
    private BluetoothSocket mSocket;
    private HandlerThread mThread;
    private BluetoothObexTransport mTransport;

    public enum CharsetType {
        NATIVE,
        UTF_8
    }

    public MasClient(BluetoothDevice remoteDevice, StateMachine callback, SdpMasRecord sdpMasRecord) {
        if (remoteDevice == null) {
            throw new NullPointerException("Obex transport is null");
        }
        this.mRemoteDevice = remoteDevice;
        this.mCallback = callback;
        this.mSdpMasRecord = sdpMasRecord;
        HandlerThread handlerThread = new HandlerThread("Client");
        this.mThread = handlerThread;
        handlerThread.start();
        Looper looper = this.mThread.getLooper();
        MasClientHandler masClientHandler = new MasClientHandler(looper, this);
        this.mHandler = masClientHandler;
        masClientHandler.obtainMessage(0).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connect() {
        try {
            int l2capSocket = this.mSdpMasRecord.getL2capPsm();
            if (l2capSocket != -1) {
                this.mSocket = this.mRemoteDevice.createL2capSocket(l2capSocket);
            } else {
                this.mSocket = this.mRemoteDevice.createRfcommSocket(this.mSdpMasRecord.getRfcommCannelNumber());
            }
            this.mSocket.connect();
            this.mTransport = new BluetoothObexTransport(this.mSocket);
            this.mSession = new ClientSession(this.mTransport);
            HeaderSet headerset = new HeaderSet();
            headerset.setHeader(70, BLUETOOTH_UUID_OBEX_MAS);
            ObexAppParameters oap = new ObexAppParameters();
            oap.add(OAP_TAGID_MAP_SUPPORTED_FEATURES, MAP_SUPPORTED_FEATURES);
            oap.addToHeaderSet(headerset);
            if (this.mSession.connect(headerset).getResponseCode() == 160) {
                this.mConnected = true;
                this.mCallback.sendMessage(PointerIconCompat.TYPE_CONTEXT_MENU);
            } else {
                disconnect();
            }
        } catch (IOException e) {
            Log.e(TAG, "Caught an exception " + e.toString());
            disconnect();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disconnect() {
        ClientSession clientSession = this.mSession;
        if (clientSession != null) {
            try {
                clientSession.disconnect((HeaderSet) null);
            } catch (IOException e) {
                Log.e(TAG, "Caught an exception while disconnecting:" + e.toString());
            }
            try {
                this.mSession.close();
            } catch (IOException e2) {
                Log.e(TAG, "Caught an exception while closing:" + e2.toString());
            }
        }
        this.mConnected = false;
        this.mCallback.sendMessage(PointerIconCompat.TYPE_HAND);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeRequest(Request request) {
        try {
            request.execute(this.mSession);
            this.mCallback.sendMessage(PointerIconCompat.TYPE_HELP, request);
        } catch (IOException e) {
            disconnect();
        }
    }

    public boolean makeRequest(Request request) {
        Handler handler = this.mHandler;
        boolean status = handler.sendMessage(handler.obtainMessage(2, request));
        if (!status) {
            Log.e(TAG, "Adding messages failed, state: " + this.mConnected);
            return false;
        }
        return true;
    }

    public void shutdown() {
        this.mHandler.obtainMessage(1).sendToTarget();
        this.mThread.quitSafely();
    }

    SdpMasRecord getSdpMasRecord() {
        return this.mSdpMasRecord;
    }

    private static class MasClientHandler extends Handler {
        WeakReference<MasClient> mInst;

        MasClientHandler(Looper looper, MasClient inst) {
            super(looper);
            this.mInst = new WeakReference<>(inst);
        }

        @Override // android.os.Handler
        public void handleMessage(android.os.Message msg) {
            MasClient inst = this.mInst.get();
            int i = msg.what;
            if (i == 0) {
                if (!inst.mConnected) {
                    inst.connect();
                }
            } else {
                if (i != 1) {
                    if (i == 2 && inst.mConnected) {
                        inst.executeRequest((Request) msg.obj);
                        return;
                    }
                    return;
                }
                if (inst.mConnected) {
                    inst.disconnect();
                }
            }
        }
    }
}
