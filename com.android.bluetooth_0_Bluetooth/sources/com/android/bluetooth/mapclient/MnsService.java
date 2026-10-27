package com.android.bluetooth.mapclient;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.util.Log;
import com.android.bluetooth.BluetoothObexTransport;
import com.android.bluetooth.IObexConnectionHandler;
import com.android.bluetooth.ObexServerSockets;
import com.android.bluetooth.sdp.SdpManager;
import java.io.IOException;
import javax.obex.Authenticator;
import javax.obex.ServerSession;

/* JADX INFO: loaded from: classes.dex */
public class MnsService {
    static final int EVENT_REPORT = 1001;
    private static final int MNS_VERSION = 260;
    static final int MSG_EVENT = 1;
    private static final String TAG = "MnsService";
    private static MapClientService sContext;
    private int mSdpHandle;
    private volatile boolean mShutdown = false;
    private static final Boolean DBG = false;
    private static final Boolean VDBG = false;
    private static SocketAcceptor sAcceptThread = null;
    private static Handler sSessionHandler = null;
    private static BluetoothServerSocket sServerSocket = null;
    private static ObexServerSockets sServerSockets = null;

    MnsService(MapClientService context) {
        this.mSdpHandle = -1;
        if (VDBG.booleanValue()) {
            Log.v(TAG, "MnsService()");
        }
        sContext = context;
        SocketAcceptor socketAcceptor = new SocketAcceptor();
        sAcceptThread = socketAcceptor;
        sServerSockets = ObexServerSockets.create(socketAcceptor);
        SdpManager sdpManager = SdpManager.getDefaultManager();
        if (sdpManager == null) {
            Log.e(TAG, "SdpManager is null");
        } else {
            this.mSdpHandle = sdpManager.createMapMnsRecord("MAP Message Notification Service", sServerSockets.getRfcommChannel(), sServerSockets.getL2capPsm(), MNS_VERSION, 95);
        }
    }

    void stop() {
        if (VDBG.booleanValue()) {
            Log.v(TAG, "stop()");
        }
        this.mShutdown = true;
        cleanUpSdpRecord();
        ObexServerSockets obexServerSockets = sServerSockets;
        if (obexServerSockets != null) {
            obexServerSockets.shutdown(false);
            sServerSockets = null;
        }
    }

    private void cleanUpSdpRecord() {
        if (this.mSdpHandle < 0) {
            Log.e(TAG, "cleanUpSdpRecord, SDP record never created");
            return;
        }
        int sdpHandle = this.mSdpHandle;
        this.mSdpHandle = -1;
        SdpManager sdpManager = SdpManager.getDefaultManager();
        if (sdpManager == null) {
            Log.e(TAG, "cleanUpSdpRecord failed, sdpManager is null, sdpHandle=" + sdpHandle);
            return;
        }
        Log.i(TAG, "cleanUpSdpRecord, mSdpHandle=" + sdpHandle);
        if (!sdpManager.removeSdpRecord(sdpHandle)) {
            Log.e(TAG, "cleanUpSdpRecord, removeSdpRecord failed, sdpHandle=" + sdpHandle);
        }
    }

    private class SocketAcceptor implements IObexConnectionHandler {
        private boolean mInterrupted;

        private SocketAcceptor() {
            this.mInterrupted = false;
        }

        @Override // com.android.bluetooth.IObexConnectionHandler
        public synchronized void onAcceptFailed() {
            Log.e(MnsService.TAG, "OnAcceptFailed");
            ObexServerSockets unused = MnsService.sServerSockets = null;
            if (MnsService.this.mShutdown) {
                Log.e(MnsService.TAG, "Failed to accept incomming connection - shutdown");
            }
        }

        @Override // com.android.bluetooth.IObexConnectionHandler
        public synchronized boolean onConnect(BluetoothDevice device, BluetoothSocket socket) {
            if (MnsService.DBG.booleanValue()) {
                Log.d(MnsService.TAG, "onConnect" + device + " SOCKET: " + socket);
            }
            MceStateMachine stateMachine = MnsService.sContext.getMceStateMachineForDevice(device);
            if (stateMachine == null) {
                Log.e(MnsService.TAG, "Error: NO statemachine for device: " + device.getAddress() + " (name: " + device.getName());
                return false;
            }
            if (stateMachine.getState() != 2) {
                Log.e(MnsService.TAG, "Error: statemachine for device: " + device.getAddress() + " (name: " + device.getName() + ") is not currently CONNECTED : " + stateMachine.getCurrentState());
                return false;
            }
            MnsObexServer srv = new MnsObexServer(stateMachine, MnsService.sServerSockets);
            BluetoothObexTransport transport = new BluetoothObexTransport(socket);
            try {
                new ServerSession(transport, srv, (Authenticator) null);
                return true;
            } catch (IOException e) {
                Log.e(MnsService.TAG, e.toString());
                return false;
            }
        }
    }
}
